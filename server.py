#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Khung server giả lập KnightOnline v4.0.3 chạy local (Termux).

Đã làm:  bắt tay -40, đăng nhập (tự tạo tài khoản mới), danh sách nhân vật (13),
         tạo nhân vật (14). Dữ liệu lưu ở data/accounts.json.
Cần viết tiếp: từng handler theo định dạng trong ReadMessenge.class
         (xem README). Mỗi handler = 1 hàm đăng ký bằng @on(cmd).

Chạy:  python server.py            (nghe 0.0.0.0:19129)
"""
import argparse, asyncio, json, os, random
from ko_proto import (XorCipher, StreamParser, Reader, Writer, build_s2c,
                      encode_key, cmd_name, hexdump)

HANDLERS = {}
DATA_FILE = os.path.join(os.path.dirname(os.path.abspath(__file__)), "data", "accounts.json")


# ------------------------------ Lưu trữ ------------------------------
def load_db():
    try:
        with open(DATA_FILE, encoding="utf-8") as f:
            return json.load(f)
    except (FileNotFoundError, ValueError):
        return {"next_id": 1, "accounts": {}}


def save_db():
    os.makedirs(os.path.dirname(DATA_FILE), exist_ok=True)
    tmp = DATA_FILE + ".tmp"
    with open(tmp, "w", encoding="utf-8") as f:
        json.dump(DB, f, ensure_ascii=False, indent=1)
    os.replace(tmp, DATA_FILE)


DB = load_db()


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
    print("  LOGIN:", {k: f[k] for k in ("user", "version", "device")})
    accs = DB["accounts"]
    acc = accs.get(f["user"])
    if acc is None:                       # tài khoản mới -> tự đăng ký
        acc = accs[f["user"]] = {"pass": f["pass"], "chars": []}
        save_db()
        print("  tạo tài khoản mới:", f["user"])
    elif acc["pass"] != f["pass"]:
        await c.dialog_ok("Sai mật khẩu!")
        return
    c.user = f["user"]
    await send_list_char(c)


async def send_list_char(c):
    """cmd 13 – ReadMessenge.listChar. 0 nhân vật -> client mở màn tạo nhân vật."""
    chars = DB["accounts"][c.user]["chars"]
    w = Writer().byte(len(chars))
    for ch in chars:
        w.int(ch["id"]).utf(ch["name"])
        w.byte(ch["head"]).byte(ch["hair"]).byte(ch["eye"])
        w.byte(0)                  # số món đang mặc: (slot, id) x n
        w.short(ch["lv"])
        w.byte(ch["clazz"]).byte(0).byte(0)
        w.short(-1)                # không có bang (>=0 thì kèm UTF tên + byte chức vụ)
    await c.send(13, w)


@on(14)
async def h_create_char(c, r):
    # GlobalService.create_char: clazz, tên, tóc, mắt, đầu
    clazz, name, hair, eye, head = r.byte(), r.utf().strip(), r.byte(), r.byte(), r.byte()
    print("  CREATE_CHAR:", clazz, name, hair, eye, head)
    if c.user is None:
        return
    if not (3 <= len(name) <= 15):
        await c.dialog_ok("Tên nhân vật phải từ 3 đến 15 ký tự")
        return
    for acc in DB["accounts"].values():
        if any(ch["name"].lower() == name.lower() for ch in acc["chars"]):
            await c.dialog_ok("Tên nhân vật đã tồn tại")
            return
    chars = DB["accounts"][c.user]["chars"]
    if len(chars) >= 3:
        await c.dialog_ok("Mỗi tài khoản tối đa 3 nhân vật")
        return
    chars.append({"id": DB["next_id"], "name": name, "clazz": clazz,
                  "head": head, "hair": hair, "eye": eye, "lv": 1,
                  "map": 0, "x": 0, "y": 0})
    DB["next_id"] += 1
    save_db()
    await send_list_char(c)


@on(13)
async def h_select_char(c, r):
    kind, char_id = r.byte(), r.int()
    print("  SELECT_CHAR:", kind, char_id)
    # TODO: gửi MAIN_CHAR_INFO(3), CHAR_WEARING(15), CHAR_INVENTORY(16), CHANGE_MAP(12)...
    await c.dialog_ok("Thỏ Mika: phần vào game đang được phát triển!")


@on(61)
async def h_name_server(c, r):
    pass   # client hỏi tên server; chưa cần trả lời


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
    try:
        asyncio.run(main(a.host, a.port))
    except KeyboardInterrupt:
        print("\nĐã tắt server Thỏ Mika.")
