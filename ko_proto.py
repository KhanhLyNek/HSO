# -*- coding: utf-8 -*-
"""
Giao thức mạng KnightOnline / Hiệp Sĩ Online v4.0.3 (TeaMobi, J2ME)
Dịch ngược từ CLib/Session_ME.class.

Khung gói tin (TCP, cổng 19129):
  [cmd: 1 byte][len: 2 byte big-endian][data: len byte]

Bắt tay:
  1. Client gửi cmd -40 (0xD8), len=0, KHÔNG mã hoá.
  2. Server trả cmd -40, KHÔNG mã hoá, data = [n][e0..e(n-1)]
     Client giải key: key[0]=e0, key[i]=e[i]^key[i-1]
  3. Từ đó mọi byte (cmd, len, data) đều XOR với key theo vòng.
     Client và server mỗi bên có 2 con trỏ riêng: đọc (curR) và ghi (curW).

Server -> client: nếu cmd (đã giải) là -51,-52,-54 hoặc 126 thì độ dài dùng 4 byte.
  - Với 126: byte kế tiếp là cmd thật (đã mã hoá).
  - 4 byte độ dài b0 b1 b2 b3 được giải theo thứ tự b3,b2,b1,b0 (big-endian).
"""
import struct

CMD_NAMES = {
    -110: "SHOW_INFO", -109: "GET_NAP_STORE_APPLE", -108: "FILL_REC_UPDATE_TIME",
    -107: "USE_MATERIAL", -106: "GET_MATERITAL_TEMPLATE", -105: "HOP_RAC",
    -104: "UPDATE_INFO_CLAN_HOLD", -103: "INFO_MI_NUONG", -102: "MUA_BAN",
    -101: "COMPETITION", -100: "KHAM_NGOC", -99: "LAST_LOGIN", -98: "USE_SHIP",
    -97: "USE_MOUNT", -96: "SERVER_ADD_NPC", -95: "MARKKILLER", -94: "STATUS_ARENA",
    -93: "IN_APP_PURCHASE", -92: "USE_ITEM_ARENA", -91: "MINI_GAME",
    -76: "NOKIA_PURCHASE", -75: "GOOGLE_PURCHASE", -57: "UPDATE_DATA",
    -56: "NAP_DIAMOND", -54: "CHECK_UPDATE_DATA", -53: "NAP_TIEN",
    -52: "LOAD_IMAGE_DATA_PART_CHAR", -51: "LOAD_IMAGE", -50: "NPC_BIG",
    -49: "LOAD_IMAGE_DATA_AUTO_EFF", -44: "NEW_NPC_INFO", -40: "GET_KEY",
    -32: "DIALOG_SERVER", -31: "DIALOG_MORE_OPTION_SERVER", -30: "DYNAMIC_MENU",
    -28: "MONSTER_CAPCHAR",
    1: "LOGIN", 2: "LOGIN_FAIL", 3: "MAIN_CHAR_INFO", 4: "OBJECT_MOVE",
    5: "CHAR_INFO", 6: "FIRE_PK", 7: "MONSTER_INFO", 8: "PLAYER_EXIT",
    9: "FIRE_MONSTER", 10: "MONSTER_FIRE", 11: "USE_ITEM", 12: "CHANGE_MAP",
    13: "LIST_CHAR/SELECT_CHAR", 14: "CREATE_CHAR", 15: "CHAR_WEARING",
    16: "CHAR_INVENTORY", 17: "DIE_MONSTER", 18: "DEL_ITEM", 19: "ITEM_DROP",
    20: "GET_ITEM_MAP", 21: "ITEM_MORE_INFO", 22: "ADD_BASE_SKILL_POINT",
    23: "NPC_INFO", 24: "BUY_ITEM", 25: "ITEM_TEMPLATE", 26: "CATALORY_MONSTER",
    27: "CHAT_POPUP", 28: "GET_ITEM_TEM", 29: "LIST_SKILL", 30: "SET_EXP",
    31: "GO_HOME", 32: "USE_POTION", 33: "LEVEL_UP", 34: "CHAT_TAB",
    35: "FRIEND", 36: "BUY_SELL", 37: "INFO_FROM_SERVER", 39: "REGISTER",
    40: "BUFF", 41: "DIE_PLAYER", 42: "PK", 44: "UPDATE_PET_CONTAINER",
    45: "PET_EAT", 48: "PARTY", 49: "OTHER_PLAYER_INFO", 50: "EFF_PLUS_TIME",
    51: "CHANGE_AREA", 52: "QUEST", 53: "INFO_EASY_SERVER",
    54: "UPDATE_STATUS_AREA", 55: "SAVE_RMS", 56: "LIST_SERVER",
    57: "LIST_PLAYER_PK", 59: "PLAYER_SUCKHOE", 60: "CHAT_NPC",
    61: "NAME_SERVER", 62: "X2_XP", 63: "DELETE_RMS", 64: "HELP_FROM_SERVER",
    65: "UPDATE_CHAR_CHEST", 67: "REBUILD_ITEM", 68: "THACH_DAU", 69: "CLAN",
    70: "UPDATE_HP_NPC", 71: "CHAT_WORLD", 73: "REPLACE_PLUS_ITEM",
    74: "SHOW_NUM_EFF", 75: "EFF_SERVER", 76: "EFF_WEATHER",
    77: "REBUILD_WING", 78: "OPEN_BOX", 84: "PET_ATTACK",
    85: "MONSTER_DETONATE", 86: "MONSTER_SKILL_INFO", 87: "PET_GAIN_XP",
    90: "REMOVE_ACTOR", 126: "BIG_MSG",
}

BIG_LEN_CMDS = (-51, -52, -54, 126)


def s8(b):
    """unsigned -> signed byte"""
    b &= 0xFF
    return b - 256 if b > 127 else b


def cmd_name(c):
    return CMD_NAMES.get(c, "CMD_%d" % c)


class XorCipher:
    def __init__(self, key=None):
        self.key = key
        self.i = 0

    def ready(self):
        return self.key is not None

    def step(self, b):
        if self.key is None:
            return b & 0xFF
        r = (self.key[self.i] ^ b) & 0xFF
        self.i = (self.i + 1) % len(self.key)
        return r


def encode_key(key):
    """Server gửi key dạng đã 'xor dây chuyền' để client giải lại."""
    out = [key[0]]
    for i in range(1, len(key)):
        out.append(key[i] ^ key[i - 1])
    return bytes([len(key)] + out)


def decode_key(data):
    n = data[0]
    k = list(data[1:1 + n])
    for i in range(n - 1):
        k[i + 1] ^= k[i]
    return bytes(k)


# ---------------- Đọc dữ liệu kiểu Java DataInputStream ----------------
class Reader:
    def __init__(self, data):
        self.d = data
        self.p = 0

    def left(self):
        return len(self.d) - self.p

    def byte(self):
        v = struct.unpack_from(">b", self.d, self.p)[0]; self.p += 1; return v

    def ubyte(self):
        v = self.d[self.p]; self.p += 1; return v

    def bool(self):
        return self.ubyte() != 0

    def short(self):
        v = struct.unpack_from(">h", self.d, self.p)[0]; self.p += 2; return v

    def int(self):
        v = struct.unpack_from(">i", self.d, self.p)[0]; self.p += 4; return v

    def long(self):
        v = struct.unpack_from(">q", self.d, self.p)[0]; self.p += 8; return v

    def utf(self):
        n = struct.unpack_from(">H", self.d, self.p)[0]; self.p += 2
        s = self.d[self.p:self.p + n]; self.p += n
        return s.decode("utf-8", "replace")  # Java modified-UTF8 ~ UTF8 cho text thường

    def raw(self, n):
        s = self.d[self.p:self.p + n]; self.p += n; return s


class Writer:
    def __init__(self):
        self.b = bytearray()

    def byte(self, v):  self.b += struct.pack(">b", s8(v)); return self
    def bool(self, v):  return self.byte(1 if v else 0)
    def short(self, v): self.b += struct.pack(">h", v); return self
    def int(self, v):   self.b += struct.pack(">i", v); return self
    def long(self, v):  self.b += struct.pack(">q", v); return self
    def raw(self, d):   self.b += d; return self

    def utf(self, s):
        e = s.encode("utf-8")
        self.b += struct.pack(">H", len(e)) + e
        return self

    def bytes(self):
        return bytes(self.b)


# ---------------- Mã hoá / giải mã khung gói ----------------
def build_s2c(cipher, cmd, data):
    """Đóng gói server -> client (cipher = con trỏ GHI của server)."""
    out = bytearray()
    if not cipher.ready():
        out.append(cmd & 0xFF)
        out += struct.pack(">H", len(data))
        out += data
        return bytes(out)
    big = cmd in BIG_LEN_CMDS
    if len(data) > 0xFFFF and not big:
        # gói lớn -> bọc trong cmd 126
        out.append(cipher.step(126))
        out.append(cipher.step(cmd))
        big = True
    else:
        out.append(cipher.step(cmd))
    n = len(data)
    if big:
        b = [0, 0, 0, 0]
        b[3] = cipher.step(n & 0xFF)
        b[2] = cipher.step((n >> 8) & 0xFF)
        b[1] = cipher.step((n >> 16) & 0xFF)
        b[0] = cipher.step((n >> 24) & 0xFF)
        out += bytes(b)
    else:
        out.append(cipher.step(n >> 8))
        out.append(cipher.step(n & 0xFF))
    out += bytes(cipher.step(x) for x in data)
    return bytes(out)


class StreamParser:
    """
    Bộ tách gói tăng dần từ luồng byte thô.
    direction: 'c2s' (client->server) hoặc 's2c' (server->client).
    Trả về list (cmd, data). Chỉ "chốt" con trỏ key khi đã đủ cả gói.
    """

    def __init__(self, direction, cipher):
        self.dir = direction
        self.c = cipher
        self.buf = bytearray()

    def feed(self, chunk):
        self.buf += chunk
        msgs = []
        while True:
            r = self._try_one()
            if r is None:
                break
            msgs.append(r)
        return msgs

    def _try_one(self):
        buf = self.buf
        if not buf:
            return None
        save_i = self.c.i
        enc = self.c.ready()
        p = 0

        def take():
            nonlocal p
            if p >= len(buf):
                raise IndexError
            v = buf[p]; p += 1
            return self.c.step(v)

        try:
            cmd = s8(take())
            if not enc:
                if len(buf) < 3:
                    raise IndexError
                n = (buf[1] << 8) | buf[2]; p = 3
            elif self.dir == "s2c" and cmd in BIG_LEN_CMDS:
                if cmd == 126:
                    cmd = s8(take())
                if len(buf) < p + 4:
                    raise IndexError
                raw4 = buf[p:p + 4]; p += 4
                d3 = self.c.step(raw4[3]); d2 = self.c.step(raw4[2])
                d1 = self.c.step(raw4[1]); d0 = self.c.step(raw4[0])
                n = (d0 << 24) | (d1 << 16) | (d2 << 8) | d3
            else:
                hi = take(); lo = take()
                n = (hi << 8) | lo
            if len(buf) < p + n:
                raise IndexError
            data = bytes(self.c.step(x) for x in buf[p:p + n])
            p += n
        except IndexError:
            self.c.i = save_i
            return None
        del self.buf[:p]
        return cmd, data


def hexdump(data, width=16, limit=512):
    lines = []
    for off in range(0, min(len(data), limit), width):
        chunk = data[off:off + width]
        hx = " ".join("%02x" % b for b in chunk)
        asc = "".join(chr(b) if 32 <= b < 127 else "." for b in chunk)
        lines.append("    %04x  %-*s  %s" % (off, width * 3, hx, asc))
    if len(data) > limit:
        lines.append("    ... (%d byte nữa)" % (len(data) - limit))
    return "\n".join(lines)
