#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Khung server giả lập KnightOnline v4.0.3 chạy local (Termux).

Đã làm:  bắt tay -40, đăng nhập (tự tạo tài khoản mới), danh sách nhân vật (13),
         vào map (3 MAIN_CHAR_INFO + 12 CHANGE_MAP), tileset tự vẽ (-51), di chuyển (4),
         tạo nhân vật (14). Dữ liệu lưu ở data/accounts.json.
Cần viết tiếp: từng handler theo định dạng trong ReadMessenge.class
         (xem README). Mỗi handler = 1 hàm đăng ký bằng @on(cmd).

Chạy:  python server.py            (nghe 0.0.0.0:19129)
"""
import argparse, asyncio, json, os, random
import world
from ko_proto import (XorCipher, StreamParser, Reader, Writer, build_s2c,
                      encode_key, cmd_name, hexdump)

HANDLERS = {}
SENSITIVE = {1, 39}   # LOGIN, REGISTER: có mật khẩu -> không hexdump ra log
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
        self.char = None
        self.dirty = False

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
    if c.user is None:
        return
    ch = next((x for x in DB["accounts"][c.user]["chars"] if x["id"] == char_id), None)
    if ch is None:
        await c.dialog_ok("Không tìm thấy nhân vật")
        return
    c.char = ch
    m = world.MAPS.get(ch.get("map")) or world.MAPS[1]
    if ch.get("map") != m.id or not m.walkable(ch.get("x", 0), ch.get("y", 0)):
        ch["map"], (ch["x"], ch["y"]) = m.id, m.spawn
        save_db()
    await send_main_char_info(c, ch)
    await send_change_map(c, m, ch["x"], ch["y"])
    await send_skill_list(c, ch)


async def send_skill_list(c, ch):
    """cmd 29 – ReadMessenge.Skill_List. Gói này bật LoadMapScreen.isLoadSkill
    (màn tải map chờ cờ này). Tạm thời chưa có kỹ năng."""
    await c.send(29, Writer().byte(0))


async def send_main_char_info(c, ch):
    """cmd 3 – ReadMessenge.mainCharInfo"""
    lv = ch.get("lv", 1)
    hp = mp = 100 + 20 * lv
    w = Writer()
    w.short(ch["id"]).utf(ch["name"])
    w.int(hp).int(hp).int(mp).int(mp)
    w.byte(ch["head"]).byte(ch["clazz"]).byte(ch["eye"]).byte(ch["hair"])
    w.byte(0)                          # số chỉ số phụ: (ubyte id, int value) x n
    w.short(lv).short(0)               # cấp, % kinh nghiệm
    w.short(0).short(0)                # điểm tiềm năng, điểm kỹ năng
    for _ in range(8):                 # tiềm năng gốc [4] + cộng thêm [4]
        w.short(5)
    for _ in range(21 * 2):            # cấp kỹ năng [21] + cộng thêm [21]
        w.byte(0)
    w.byte(0).short(0)                 # typePk, pointPk
    w.byte(42)                         # số ô hành trang
    w.short(-1)                        # không bang
    w.utf("").long(0)                  # vùng đặc biệt, thời gian
    w.byte(0)                          # thời trang
    w.byte(0)                          # naptien
    w.short(-1).byte(0)                # mặt nạ, vẽ mặt nạ trước
    for _ in range(9):                 # phi phong, vũ khí, thú cưỡi, tóc, cánh, danh hiệu, thân, chân, biến hình
        w.short(-1)
    await c.send(3, w)


async def send_change_map(c, m, tx, ty):
    """cmd 12 – ReadMessenge.changeMap"""
    data = m.data()
    w = Writer()
    w.short(m.id).short(tx).short(ty)
    w.short(m.id).utf(m.name)          # id minimap, tên map
    w.short(len(data)).raw(data)
    w.byte(-1)                         # không có ảnh nền
    w.short(0)                         # vật trang trí trên map
    w.byte(0)                          # điểm chuyển map: (short x, short y, UTF tên) x n
    w.byte(0)                          # isTele
    w.byte(0)                          # khu vực
    w.byte(0)                          # loại map
    w.bool(False).bool(False)          # map làng, hiện hồi sinh
    await c.send(12, w)


@on(12)
async def h_ok_change_map(c, r):
    print("  client đã tải xong map")


@on(4)
async def h_move(c, r):
    x, y = r.short(), r.short()
    ch = getattr(c, "char", None)
    if ch is None:
        return
    tx, ty = x // world.TILE, y // world.TILE
    m = world.MAPS.get(ch["map"])
    if m and m.walkable(tx, ty):
        ch["x"], ch["y"] = tx, ty
        c.dirty = True


@on(-51)
async def h_load_image(c, r):
    img_id = r.short()
    png = world.image_for(img_id)
    if png is None:
        print("  LOAD_IMAGE %d: chưa có ảnh" % img_id)
        return
    await c.send(-51, Writer().short(img_id).raw(png))


@on(5)
async def h_char_info(c, r):
    pass   # TODO: thông tin nhân vật khác


@on(55)
async def h_save_rms(c, r):
    """Client đồng bộ RMS với server.
    [1][kiểu][short 0]        -> xin dữ liệu: trả [kiểu][short len][data]
    [0][kiểu][short len][data] -> lưu
    Kiểu 2 bật MainRMS.isLoadBegin (màn tải map chờ cờ này)."""
    action, kind = r.byte(), r.byte()
    n = r.short()
    data = r.raw(n) if n > 0 else b""
    ch = c.char
    store = ch.setdefault("rms", {}) if ch else {}
    if action == 1:
        blob = bytes.fromhex(store.get(str(kind), ""))
        print("  RMS tải kiểu %d (%d byte)" % (kind, len(blob)))
        await c.send(55, Writer().byte(kind).short(len(blob)).raw(blob))
    else:
        print("  RMS lưu kiểu %d (%d byte)" % (kind, len(data)))
        if ch is not None:
            store[str(kind)] = data.hex()
            c.dirty = True


@on(59)
async def h_suckhoe(c, r):
    pass   # client hỏi điểm sức khoẻ


@on(-52)
async def h_part_char(c, r):
    kind, pid = r.byte(), r.short()
    print("  PART_CHAR %d/%d: chưa có dữ liệu" % (kind, pid))


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
                    print(hexdump(data) if cmd not in SENSITIVE else "    (ẩn: có mật khẩu)")
                    continue
                try:
                    await fn(c, Reader(data))
                except Exception as e:
                    print("  lỗi handler:", repr(e))
                    print(hexdump(data) if cmd not in SENSITIVE else "    (ẩn: có mật khẩu)")
    except ConnectionError:
        pass
    finally:
        if getattr(c, "dirty", False):
            save_db()
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
