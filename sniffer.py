#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Proxy bắt gói tin (MITM trong suốt) cho KnightOnline v4.0.3.

  Game (J2ME Loader) --> 127.0.0.1:19129 (sniffer) --> server thật
Byte được chuyển tiếp nguyên vẹn, đồng thời sniffer giải mã XOR và ghi log.

Dùng:
  python sniffer.py --upstream hs1.teamobi.com
  python sniffer.py --upstream 1.2.3.4 --port 19129 --log packets.jsonl
"""
import argparse, asyncio, json, time, datetime
from ko_proto import XorCipher, StreamParser, decode_key, cmd_name, hexdump

ARGS = None


class Session:
    n = 0

    def __init__(self, logf):
        Session.n += 1
        self.id = Session.n
        self.logf = logf
        # Hai chiều dùng chung 1 key nhưng con trỏ độc lập
        self.c2s = StreamParser("c2s", XorCipher())
        self.s2c = StreamParser("s2c", XorCipher())

    def on_msgs(self, direction, msgs):
        for cmd, data in msgs:
            if direction == "s2c" and cmd == -40 and not self.s2c.c.ready():
                key = decode_key(data)
                self.s2c.c.key = key
                self.c2s.c.key = key
                print("[#%d] KEY = %s" % (self.id, key.hex()))
            arrow = "C->S" if direction == "c2s" else "S->C"
            ts = datetime.datetime.now().strftime("%H:%M:%S.%f")[:-3]
            print("[#%d %s] %s cmd=%d (%s) len=%d" % (
                self.id, ts, arrow, cmd, cmd_name(cmd), len(data)))
            if not ARGS.quiet:
                print(hexdump(data))
            if self.logf:
                self.logf.write(json.dumps({
                    "t": time.time(), "s": self.id, "dir": direction,
                    "cmd": cmd, "name": cmd_name(cmd), "hex": data.hex()
                }) + "\n")
                self.logf.flush()


async def pipe(reader, writer, sess, direction):
    parser = sess.c2s if direction == "c2s" else sess.s2c
    try:
        while True:
            chunk = await reader.read(65536)
            if not chunk:
                break
            writer.write(chunk)
            await writer.drain()
            try:
                sess.on_msgs(direction, parser.feed(chunk))
            except Exception as e:
                print("[#%d] lỗi parse %s: %r" % (sess.id, direction, e))
    finally:
        try:
            writer.close()
        except Exception:
            pass


async def handle(cr, cw, logf):
    sess = Session(logf)
    print("[#%d] client kết nối %s" % (sess.id, cw.get_extra_info("peername")))
    try:
        sr, sw = await asyncio.open_connection(ARGS.upstream, ARGS.upstream_port)
    except Exception as e:
        print("[#%d] không nối được server thật: %r" % (sess.id, e))
        cw.close()
        return
    await asyncio.gather(pipe(cr, sw, sess, "c2s"), pipe(sr, cw, sess, "s2c"))
    print("[#%d] đóng" % sess.id)


async def main():
    logf = open(ARGS.log, "a", encoding="utf-8") if ARGS.log else None
    srv = await asyncio.start_server(lambda r, w: handle(r, w, logf),
                                     ARGS.host, ARGS.port)
    print("Sniffer nghe %s:%d -> %s:%d" % (ARGS.host, ARGS.port,
                                           ARGS.upstream, ARGS.upstream_port))
    async with srv:
        await srv.serve_forever()


if __name__ == "__main__":
    ap = argparse.ArgumentParser()
    ap.add_argument("--host", default="0.0.0.0")
    ap.add_argument("--port", type=int, default=19129)
    ap.add_argument("--upstream", required=True, help="IP/host server thật")
    ap.add_argument("--upstream-port", type=int, default=19129)
    ap.add_argument("--log", default="packets.jsonl")
    ap.add_argument("--quiet", action="store_true", help="không in hexdump")
    ARGS = ap.parse_args()
    asyncio.run(main())
