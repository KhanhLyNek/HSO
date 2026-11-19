#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Khung server giả lập KnightOnline v4.0.3 chạy local (Termux).

Đã làm:  bắt tay -40 (cấp key XOR), đọc gói LOGIN (cmd 1), trả LOGIN_FAIL
         kèm thông báo để xác nhận client <-> server đã thông.
Cần viết tiếp: từng handler theo định dạng trong ReadMessenge.class
         (xem README). Mỗi handler = 1 hàm đăng ký bằng @on(cmd).

Chạy:  python server.py            (nghe 0.0.0.0:19129)
"""
import argparse, asyncio, os
from ko_proto import (XorCipher, StreamParser, Reader, Writer, build_s2c,
                      encode_key, cmd_name, hexdump)

HANDLERS = {}


def on(cmd):
    def deco(fn):
        HANDLERS[cmd] = fn
        return fn
    return deco


class Client:
    def __init__(self, reader, writer):
        self.r, self.w = reader, writer
        self.rx = StreamParser("c2s", XorCipher())   # con trỏ đọc
        self.tx = XorCipher()                        # con trỏ ghi
        self.user = None

    async def send(self, cmd, data=b""):
        if isinstance(data, Writer):
            data = data.bytes()
        self.w.write(build_s2c(self.tx, cmd, data))
        await self.w.drain()
        print("  S->C cmd=%d (%s) len=%d" % (cmd, cmd_name(cmd), len(data)))

    async def dialog_ok(self, text):
        """Dùng LOGIN_FAIL (cmd 2) để bật hộp thoại OK: readUTF + readByte."""
        await self.send(2, Writer().utf(text).byte(0))


# ------------------------------ Handlers ------------------------------
@on(-40)
async def h_get_key(c, r):
    key = os.urandom(8)
    # gửi key khi CHƯA mã hoá, rồi mới bật mã hoá 2 chiều
    await c.send(-40, encode_key(key))
    c.tx.key = key
    c.rx.c.key = key
    print("  key =", key.hex())


@on(1)
async def h_login(c, r):
    # Thứ tự lấy từ GlobalService.login(...)
    f = {}
    f["user"] = r.utf()
    f["pass"] = r.utf()
    f["version"] = r.utf()
    f["s4"] = r.utf()
    f["s5"] = r.utf()
    f["s6"] = r.utf()
    f["zoom"] = r.byte()
    f["device"] = r.byte()
    f["i7"] = r.int()
    f["b8"] = r.byte()
    f["qwerty"] = r.byte()
    f["indexRes"] = r.byte()
    f["indexInfoLogin"] = r.byte()
    f["lowGraphic"] = r.byte()
    f["indexCharPar"] = r.short()
    f["package"] = r.utf()
    print("  LOGIN:", f)
    c.user = f["user"]
    # TODO: thay bằng gói LIST_CHAR (cmd 13) khi đã biết định dạng
    await c.dialog_ok("Server local OK! Xin chao %s" % c.user)


# ----------------------------------------------------------------------
async def handle(reader, writer):
    c = Client(reader, writer)
    print("client kết nối", writer.get_extra_info("peername"))
    try:
        while True:
            chunk = await reader.read(65536)
            if not chunk:
                break
            for cmd, data in c.rx.feed(chunk):
                print("C->S cmd=%d (%s) len=%d" % (cmd, cmd_name(cmd), len(data)))
                fn = HANDLERS.get(cmd)
                if fn is None:
                    print("  (chưa có handler)")
                    print(hexdump(data))
                    continue
                try:
                    await fn(c, Reader(data))
                except Exception as e:
                    print("  lỗi handler:", repr(e))
                    print(hexdump(data))
    except ConnectionError:
        pass
    finally:
        writer.close()
        print("client ngắt kết nối")


async def main(host, port):
    srv = await asyncio.start_server(handle, host, port)
    print("Server KO giả lập nghe %s:%d" % (host, port))
    async with srv:
        await srv.serve_forever()


if __name__ == "__main__":
    ap = argparse.ArgumentParser()
    ap.add_argument("--host", default="0.0.0.0")
    ap.add_argument("--port", type=int, default=19129)
    a = ap.parse_args()
    asyncio.run(main(a.host, a.port))
