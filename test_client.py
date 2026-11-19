#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Client giả lập đúng logic Session_ME (để test server/sniffer không cần điện thoại)."""
import socket, struct, sys
from ko_proto import XorCipher, Writer, Reader, decode_key, s8, BIG_LEN_CMDS

host = sys.argv[1] if len(sys.argv) > 1 else "127.0.0.1"
port = int(sys.argv[2]) if len(sys.argv) > 2 else 19129
s = socket.create_connection((host, port))
f = s.makefile("rb")
rd, wr = XorCipher(), XorCipher()


def send(cmd, data=b""):
    if wr.ready():
        out = bytes([wr.step(cmd), wr.step(len(data) >> 8), wr.step(len(data) & 255)])
        out += bytes(wr.step(b) for b in data)
    else:
        out = bytes([cmd & 255]) + struct.pack(">H", len(data)) + data
    s.sendall(out)


def recv():
    cmd = s8(rd.step(f.read(1)[0]))
    if rd.ready() and cmd in BIG_LEN_CMDS:
        if cmd == 126:
            cmd = s8(rd.step(f.read(1)[0]))
        b = f.read(4)
        n = rd.step(b[3]) | rd.step(b[2]) << 8 | rd.step(b[1]) << 16 | rd.step(b[0]) << 24
    elif rd.ready():
        n = rd.step(f.read(1)[0]) << 8; n |= rd.step(f.read(1)[0])
    else:
        n = struct.unpack(">H", f.read(2))[0]
    data = bytes(rd.step(b) for b in f.read(n))
    return cmd, data


send(-40)
cmd, data = recv()
assert cmd == -40
rd.key = wr.key = decode_key(data)
w = Writer()
for x in ["tester", "123456", "4.0.3", "0", "0", "0"]:
    w.utf(x)
w.byte(1).byte(2).int(-1).byte(0).byte(1).byte(0).byte(0).byte(0).short(0).utf("com.test")
send(1, w.bytes())


def parse_list(data):
    r = Reader(data); out = []
    for _ in range(r.byte()):
        cid, name = r.int(), r.utf()
        head, hair, eye = r.byte(), r.byte(), r.byte()
        for _ in range(r.byte()):
            r.byte(); r.byte()
        lv, clazz = r.short(), r.byte(); r.byte(); r.byte()
        if r.short() >= 0:
            r.utf(); r.byte()
        out.append((cid, name, clazz, lv))
    assert r.left() == 0, "thừa byte"
    return out


def expect(want):
    cmd, data = recv()
    if cmd == 2:
        print("  Hộp thoại:", Reader(data).utf())
    assert cmd == want, "chờ cmd %d, nhận %d" % (want, cmd)
    return data


chars = parse_list(expect(13))
print("Danh sách nhân vật:", chars)
if not chars:
    send(14, Writer().byte(0).utf("ThoMika%d" % (id(s) % 1000)).byte(1).byte(2).byte(3).bytes())
    chars = parse_list(expect(13))
    print("Sau khi tạo:", chars)
send(13, Writer().byte(0).int(chars[0][0]).bytes())
expect(2)
s.close()
print("OK")
