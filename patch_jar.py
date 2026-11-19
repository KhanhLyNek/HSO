#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Vá KnightOnline_v403.jar:
  1. Xoá toàn bộ server gốc (VN / Global / Indo / USA), chỉ để lại 1 server
     của bạn (mặc định "Thỏ Mika" @ 127.0.0.1:19129).
  2. Tắt việc tải danh sách server từ HTTP/RMS (LogoScreen.applyServerList
     luôn trả false) để danh sách không bị ghi đè.
  3. Thay mọi host/IP server cứng còn sót lại thành IP của bạn.

Mọi chỉnh sửa giữ nguyên độ dài bytecode (dùng nop), không đổi offset nhánh,
nên StackMap của CLDC vẫn hợp lệ.

Dùng:
  python patch_jar.py KnightOnline_v403.jar KO_ThoMika.jar
  python patch_jar.py KnightOnline_v403.jar KO_ThoMika.jar --ip 192.168.1.10 --name "Thỏ Mika"
"""
import argparse, re, struct, zipfile

HOST_PATTERNS = [
    rb"^hs[0-9a-z]*\.teamobi\.com$",
    rb"^46\.137\.254\.172$", rb"^54\.254\.156\.202$", rb"^54\.151\.177\.35$",
    rb"^192\.168\.1\.188$",
]
SERVER_LIST_FIELDS = {b"listServer", b"listServer_VN", b"listServer_In_Do",
                      b"listServer_Usa"}

CP_SIZE = {3: 5, 4: 5, 5: 9, 6: 9, 7: 3, 8: 3, 9: 5, 10: 5, 11: 5,
           12: 5, 15: 4, 16: 3, 17: 5, 18: 5, 19: 3, 20: 3}

# độ dài lệnh JVM (không có tableswitch/lookupswitch/wide trong code ta sửa)
OPLEN = [1] * 256
for o in (0x10, 0x12, 0x15, 0x16, 0x17, 0x18, 0x19, 0x36, 0x37, 0x38, 0x39,
          0x3a, 0xa9, 0xbc):
    OPLEN[o] = 2
for o in list(range(0x99, 0xa9)) + [0x11, 0x13, 0x14, 0x84, 0xb2, 0xb3, 0xb4,
                                     0xb5, 0xb6, 0xb7, 0xb8, 0xbb, 0xbd, 0xc0,
                                     0xc1, 0xc6, 0xc7]:
    OPLEN[o] = 3
for o in (0xb9, 0xba, 0xc8, 0xc9):
    OPLEN[o] = 5
OPLEN[0xc5] = 4


class ClassFile:
    def __init__(self, data):
        self.d = bytearray(data)
        self.cp = [None]
        p, n = 10, struct.unpack_from(">H", data, 8)[0]
        i = 1
        while i < n:
            tag = data[p]
            if tag == 1:
                ln = struct.unpack_from(">H", data, p + 1)[0]
                self.cp.append((1, bytes(data[p + 3:p + 3 + ln]), p))
                p += 3 + ln
            else:
                sz = CP_SIZE[tag]
                self.cp.append((tag, bytes(data[p + 1:p + sz]), p))
                p += sz
                if tag in (5, 6):
                    self.cp.append(None); i += 1
            i += 1
        self.cp_end = p
        p += 6
        ni = struct.unpack_from(">H", data, p)[0]; p += 2 + 2 * ni
        self.fields, p = self._members(p)
        self.methods, p = self._members(p)

    def _members(self, p):
        out = []
        n = struct.unpack_from(">H", self.d, p)[0]; p += 2
        for _ in range(n):
            _, ni, di, na = struct.unpack_from(">HHHH", self.d, p); p += 8
            code = None
            for _ in range(na):
                an, al = struct.unpack_from(">HI", self.d, p)
                if self.utf(an) == b"Code":
                    cl = struct.unpack_from(">I", self.d, p + 10)[0]
                    code = (p + 14, cl)
                p += 6 + al
            out.append((self.utf(ni), self.utf(di), code))
        return out, p

    def utf(self, i):
        return self.cp[i][1]

    def u2(self, b, o=0):
        return struct.unpack_from(">H", b, o)[0]

    def field_name(self, fref):     # Fieldref -> tên field
        nat = self.u2(self.cp[fref][1], 2)
        return self.utf(self.u2(self.cp[nat][1], 0))

    def string_utf_index(self, sidx):
        return self.u2(self.cp[sidx][1], 0)

    def method_code(self, name, desc=None):
        for n, d, c in self.methods:
            if n == name and (desc is None or d == desc):
                return c
        return None

    def rebuild(self, utf_repl):
        """Ghi lại constant pool, thay nội dung UTF8 theo dict {index: bytes}."""
        out = bytearray(self.d[:10])
        for i, e in enumerate(self.cp):
            if i == 0 or e is None:
                continue
            tag, body, _ = e
            if tag == 1:
                s = utf_repl.get(i, body)
                out += b"\x01" + struct.pack(">H", len(s)) + s
            else:
                out += bytes([tag]) + body
        out += self.d[self.cp_end:]
        return bytes(out)


def insns(code, start, length):
    p = start
    while p < start + length:
        op = code[p]
        if op in (0xaa, 0xab, 0xc4):
            raise ValueError("gặp switch/wide - không hỗ trợ")
        yield p, op
        p += OPLEN[op]


def patch_msystem(data, name, ip):
    cf = ClassFile(data)
    off, ln = cf.method_code(b"<clinit>")
    code = cf.d
    # tìm các vùng khởi tạo mảng server: từ sau putstatic trước đó tới putstatic listServer*
    regions, prev_end = [], None
    name_sidx = ip_sidx = None
    for p, op in insns(code, off, ln):
        if op == 0xb3:
            fname = cf.field_name(cf.u2(code, p + 1))
            if fname in SERVER_LIST_FIELDS and prev_end is not None:
                regions.append((prev_end, p, fname))
            prev_end = p + 3
        if op == 0x12 and name_sidx is None and regions == [] and prev_end:
            pass
    # lấy 2 hằng String đầu tiên trong vùng đầu (tên + host của server #1)
    s0, e0, _ = regions[0]
    ldcs = [code[p + 1] for p, op in insns(code, s0, e0 - s0) if op == 0x12]
    name_sidx, ip_sidx = ldcs[0], ldcs[1]
    # index class String[] và String từ lệnh anewarray
    anew = [cf.u2(code, p + 1) for p, op in insns(code, s0, e0 - s0) if op == 0xbd]
    arr_cls, str_cls = anew[0], anew[1]
    seq = bytes([0x04, 0xbd]) + struct.pack(">H", arr_cls) + bytes(
        [0x59, 0x03, 0x05, 0xbd]) + struct.pack(">H", str_cls) + bytes(
        [0x59, 0x03, 0x12, name_sidx, 0x53,
         0x59, 0x04, 0x12, ip_sidx, 0x53, 0x53])
    for s, e, fname in regions:
        assert e - s >= len(seq), "vùng quá nhỏ"
        code[s:e] = seq + b"\x00" * (e - s - len(seq))
        print("  mSystem.%s -> 1 server" % fname.decode())
    repl = {cf.string_utf_index(name_sidx): name.encode("utf-8"),
            cf.string_utf_index(ip_sidx): ip.encode()}
    return cf.rebuild(repl)


def patch_logoscreen(data):
    cf = ClassFile(data)
    off, ln = cf.method_code(b"applyServerList")
    # đầu hàm: aload_0; ifnull +11  ->  aconst_null; ifnull +11  (luôn return false)
    assert cf.d[off] == 0x2a and cf.d[off + 1] == 0xc6, "không khớp mẫu"
    cf.d[off] = 0x01
    print("  LogoScreen.applyServerList -> tắt danh sách server từ HTTP/RMS")
    return bytes(cf.d)


def patch_hosts(data, ip):
    cf = ClassFile(data)
    repl = {}
    for i, e in enumerate(cf.cp):
        if e and e[0] == 1 and any(re.match(pt, e[1]) for pt in HOST_PATTERNS):
            repl[i] = ip.encode()
    return (cf.rebuild(repl), len(repl)) if repl else (data, 0)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("src"); ap.add_argument("dst")
    ap.add_argument("--ip", default="127.0.0.1")
    ap.add_argument("--name", default="Thỏ Mika")
    a = ap.parse_args()
    with zipfile.ZipFile(a.src) as zi, \
            zipfile.ZipFile(a.dst, "w", zipfile.ZIP_DEFLATED) as zo:
        names = sorted(zi.namelist(), key=lambda n: n != "META-INF/MANIFEST.MF")
        for n in names:
            d = zi.read(n)
            if n == "CLib/mSystem.class":
                d = patch_msystem(d, a.name, a.ip)
            elif n == "GameScreen/LogoScreen.class":
                d = patch_logoscreen(d)
            if n.endswith(".class"):
                d, h = patch_hosts(d, a.ip)
                if h:
                    print("  %-36s %d host -> %s" % (n, h, a.ip))
            zo.writestr(zi.getinfo(n), d)
    print("Xong: %s (server '%s' @ %s:19129)" % (a.dst, a.name, a.ip))


if __name__ == "__main__":
    main()
