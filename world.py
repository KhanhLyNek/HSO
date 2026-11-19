# -*- coding: utf-8 -*-
"""
Thế giới của server Thỏ Mika: tileset tự vẽ + bản đồ tự tạo.
Không dùng tài nguyên gốc của TeaMobi; mọi hình ảnh được vẽ bằng code.

Định dạng bản đồ (Thread_More/LoadMap.loadmap):
  [byte mapW][byte mapH][byte idTile][mapW*mapH byte: giá trị ô]
  Giá trị ô v: 0 = trống (chặn), vẽ khung (v-1) trong tileset dải dọc 24x24.
  Với idTile < 9, từ file tile_map_info_<idTile> trong JAR (fWater, fStand):
    1 <= v < fWater        -> đi được
    fWater <= v < fStand   -> nước
    v >= fStand hoặc v = 0 -> vật cản
  idTile 0: fWater = 26, fStand = 36.
"""
import random
import struct
import zlib

TILE = 24
ID_TILE = 0          # dùng thông số fWater/fStand của tile_map_info_0
F_WATER, F_STAND = 26, 36

# khung (1-based) trong tileset
GRASS = [1, 2, 3, 4, 5]
DIRT = [6, 7, 8]
FLOWER = [9, 10, 11]
SAND = [12, 13]
WATER = [26, 27, 28]
TREE, ROCK, BUSH, FENCE = 36, 37, 38, 39
N_FRAMES = 40


# ------------------------------ PNG thuần Python ------------------------------
def png_rgba(w, h, pixels):
    """pixels: bytearray RGBA, w*h*4"""
    raw = bytearray()
    stride = w * 4
    for y in range(h):
        raw.append(0)
        raw += pixels[y * stride:(y + 1) * stride]

    def chunk(t, d):
        c = struct.pack(">I", len(d)) + t + d
        return c + struct.pack(">I", zlib.crc32(t + d) & 0xFFFFFFFF)

    return (b"\x89PNG\r\n\x1a\n"
            + chunk(b"IHDR", struct.pack(">IIBBBBB", w, h, 8, 6, 0, 0, 0))
            + chunk(b"IDAT", zlib.compress(bytes(raw), 9))
            + chunk(b"IEND", b""))


class Canvas:
    def __init__(self, w, h):
        self.w, self.h = w, h
        self.p = bytearray(w * h * 4)

    def px(self, x, y, c):
        if 0 <= x < self.w and 0 <= y < self.h:
            i = (y * self.w + x) * 4
            self.p[i:i + 4] = bytes(c if len(c) == 4 else (*c, 255))

    def rect(self, x0, y0, w, h, c):
        for y in range(y0, y0 + h):
            for x in range(x0, x0 + w):
                self.px(x, y, c)

    def circle(self, cx, cy, r, c):
        for y in range(cy - r, cy + r + 1):
            for x in range(cx - r, cx + r + 1):
                if (x - cx) ** 2 + (y - cy) ** 2 <= r * r:
                    self.px(x, y, c)


def _noise_fill(cv, oy, base, var, rnd, dots=None):
    for y in range(TILE):
        for x in range(TILE):
            d = rnd.randint(-var, var)
            cv.px(x, oy + y, tuple(max(0, min(255, b + d)) for b in base))
    if dots:
        col, n = dots
        for _ in range(n):
            cv.px(rnd.randrange(TILE), oy + rnd.randrange(TILE), col)


def build_tileset():
    rnd = random.Random(1234)
    cv = Canvas(TILE, TILE * N_FRAMES)
    for f in range(1, N_FRAMES + 1):
        oy = (f - 1) * TILE
        if f in GRASS:
            _noise_fill(cv, oy, (86, 168, 70), 10, rnd, ((60, 130, 50), 14))
        elif f in DIRT:
            _noise_fill(cv, oy, (176, 138, 92), 9, rnd, ((140, 105, 70), 10))
        elif f in FLOWER:
            _noise_fill(cv, oy, (86, 168, 70), 10, rnd)
            petal = [(255, 120, 170), (255, 230, 90), (240, 240, 255)][f - FLOWER[0]]
            for _ in range(4):
                x, y = rnd.randrange(3, 21), rnd.randrange(3, 21)
                for dx, dy in ((0, -1), (-1, 0), (1, 0), (0, 1)):
                    cv.px(x + dx, oy + y + dy, petal)
                cv.px(x, oy + y, (250, 200, 40))
        elif f in SAND:
            _noise_fill(cv, oy, (226, 206, 150), 8, rnd)
        elif F_WATER <= f < F_STAND:
            _noise_fill(cv, oy, (60, 130, 210), 8, rnd)
            for _ in range(3):
                x, y = rnd.randrange(2, 18), rnd.randrange(2, 22)
                cv.rect(x, oy + y, 5, 1, (150, 200, 245))
        elif f == TREE:
            _noise_fill(cv, oy, (86, 168, 70), 10, rnd)
            cv.rect(10, oy + 15, 4, 8, (110, 70, 40))
            cv.circle(12, oy + 10, 9, (40, 120, 50))
            cv.circle(9, oy + 8, 4, (70, 160, 70))
        elif f == ROCK:
            _noise_fill(cv, oy, (86, 168, 70), 10, rnd)
            cv.circle(12, oy + 13, 8, (120, 120, 128))
            cv.circle(10, oy + 11, 3, (170, 170, 178))
        elif f == BUSH:
            _noise_fill(cv, oy, (86, 168, 70), 10, rnd)
            cv.circle(8, oy + 14, 6, (50, 135, 55))
            cv.circle(16, oy + 14, 6, (50, 135, 55))
            cv.circle(12, oy + 10, 6, (65, 150, 60))
        elif f == FENCE:
            _noise_fill(cv, oy, (86, 168, 70), 10, rnd)
            cv.rect(0, oy + 8, 24, 3, (150, 105, 60))
            cv.rect(0, oy + 15, 24, 3, (150, 105, 60))
            for x in (3, 11, 19):
                cv.rect(x, oy + 4, 3, 18, (125, 85, 45))
        else:
            _noise_fill(cv, oy, (86, 168, 70), 10, rnd)
    return png_rgba(cv.w, cv.h, cv.p)


# ------------------------------ Bản đồ ------------------------------
class GameMap:
    def __init__(self, mid, name, w, h, tiles, spawn):
        self.id, self.name, self.w, self.h = mid, name, w, h
        self.tiles = tiles            # bytearray w*h
        self.spawn = spawn            # (tx, ty)

    def data(self):
        return bytes([self.w, self.h, ID_TILE]) + bytes(self.tiles)

    def walkable(self, tx, ty):
        if not (0 <= tx < self.w and 0 <= ty < self.h):
            return False
        v = self.tiles[ty * self.w + tx]
        return 1 <= v < F_WATER


def make_village():
    """Làng Thỏ Mika: bãi cỏ có rào, đường đất chữ thập, hồ nước, cây và đá."""
    rnd = random.Random(42)
    w, h = 40, 30
    t = bytearray(rnd.choice(GRASS) for _ in range(w * h))

    def put(x, y, v):
        if 0 <= x < w and 0 <= y < h:
            t[y * w + x] = v

    for x in range(w):
        put(x, 0, TREE); put(x, h - 1, TREE)
    for y in range(h):
        put(0, y, TREE); put(w - 1, y, TREE)
    for x in range(1, w - 1):           # đường ngang
        put(x, 14, rnd.choice(DIRT)); put(x, 15, rnd.choice(DIRT))
    for y in range(1, h - 1):           # đường dọc
        put(19, y, rnd.choice(DIRT)); put(20, y, rnd.choice(DIRT))
    for y in range(4, 10):              # hồ
        for x in range(26, 35):
            if (x - 30) ** 2 / 20 + (y - 6.5) ** 2 / 9 <= 1:
                put(x, y, rnd.choice(WATER))
            elif (x - 30) ** 2 / 26 + (y - 6.5) ** 2 / 13 <= 1:
                put(x, y, rnd.choice(SAND))
    for x in range(4, 14):              # vườn hoa có rào
        put(x, 4, FENCE); put(x, 10, FENCE)
    for y in range(5, 10):
        for x in range(5, 13):
            put(x, y, rnd.choice(FLOWER))
    for _ in range(45):                 # cây, đá, bụi rải rác
        x, y = rnd.randrange(2, w - 2), rnd.randrange(2, h - 2)
        if t[y * w + x] in GRASS and abs(x - 19.5) > 3 and abs(y - 14.5) > 3:
            put(x, y, rnd.choice([TREE, TREE, ROCK, BUSH]))
    return GameMap(1, "Làng Thỏ Mika", w, h, t, (17, 16))


MAPS = {}
TILESET_PNG = None


def init():
    global TILESET_PNG
    m = make_village()
    MAPS[m.id] = m
    TILESET_PNG = build_tileset()


def image_for(img_id):
    """Ảnh trả cho LOAD_IMAGE. None nếu server chưa có."""
    if img_id == 10400 + ID_TILE:
        return TILESET_PNG
    return None


init()
