# HSO – Server local "Thỏ Mika" cho KnightOnline / Hiệp Sĩ Online v4.0.3 (Termux)

> File `.jar` của game không có trong repo. Bạn tự chép `KnightOnline_v403.jar` vào rồi chạy `patch_jar.py`.

## Cài nhanh trên Termux (lệnh `hso`)
```bash
pkg install -y curl
curl -fsSL https://raw.githubusercontent.com/KhanhLyNek/HSO/main/install.sh | bash
```
Sau khi cài, mỗi lần gõ `hso` thì Termux sẽ **tự kéo bản mới nhất từ repo về rồi chạy server**.

| Lệnh | Tác dụng |
|---|---|
| `hso` | Cập nhật, rồi chạy server Thỏ Mika (cổng 19129) |
| `hso patch [ip]` | Vá `KnightOnline_v403.jar` (để trong `~/HSO` hoặc Download) thành `KO_ThoMika.jar`, rồi chép vào Download |
| `hso sniff <host>` | Proxy bắt gói tin tới server thật |
| `hso test` | Client giả lập để kiểm tra server |
| `hso help` | Xem các lệnh |

Nếu bạn sửa file ngay trong `~/HSO` trên điện thoại, lệnh `git pull` sẽ không cập nhật được. Khi đó `hso` vẫn chạy bản đang có trong máy.
Muốn bỏ hết thay đổi trong máy để lấy bản mới nhất: `git -C ~/HSO reset --hard origin/main`

## Bản vá game (`patch_jar.py`)
- Xoá toàn bộ server gốc (VN, Global, Indo, USA). Chỉ còn đúng **1 server tên "Thỏ Mika"**, trỏ về `127.0.0.1:19129`.
- Tắt việc game tải danh sách server qua HTTP hoặc RMS, để danh sách không bị ghi đè.
- Đổi tên hoặc IP: `python patch_jar.py KnightOnline_v403.jar KO_ThoMika.jar --name "Thỏ Mika" --ip 192.168.1.10`
- Các chỗ sửa giữ nguyên độ dài bytecode (phần thừa được lấp bằng `nop`), nên StackMap của CLDC vẫn hợp lệ.

## Những gì đã dịch ngược được từ JAR
- Game J2ME của TeaMobi (Hiệp Sĩ Online / Knight Age), MIDlet `com.silverknight.TemMidlet`.
- Kết nối TCP `socket://<ip>:19129` (`CLib/Session_ME`, `Main/GameCanvas.connect`).
- Danh sách server cứng trong `CLib/mSystem`: `hs1..hs7.teamobi.com`, `hsglobal`, `hsindo`…
- Khung gói tin: `[cmd 1B][len 2B][data]`. Bắt tay bằng cmd `-40`: server gửi key XOR,
  sau đó cả cmd, độ dài lẫn dữ liệu đều được XOR theo vòng. Gói lớn (cmd -51/-52/-54/126)
  dùng độ dài 4 byte. Chi tiết nằm trong `ko_proto.py`.
- Bảng mã lệnh lấy từ `netcommand/Cmd_Message` (có trong `CMD_NAMES`).

## Các file
| File | Chức năng |
|---|---|
| `patch_jar.py` | Xoá server gốc, thêm server "Thỏ Mika" (127.0.0.1 hoặc IP tuỳ chọn) |
| `ko_proto.py` | Bộ mã hoá/giải mã giao thức, Reader/Writer kiểu Java |
| `sniffer.py` | Proxy chuyển tiếp tới server thật và ghi lại từng gói đã giải mã (`packets.jsonl`) |
| `server.py` | Khung server giả lập: đã chạy được bắt tay + đăng nhập, phần còn lại bạn viết thêm |
| `test_client.py` | Client giả lập để test mà không cần điện thoại |

## Cài trên Termux
```bash
pkg update && pkg install python
cd ~/ko            # chép các file vào đây
python patch_jar.py KnightOnline_v403.jar KO_ThoMika.jar
cp KO_ThoMika.jar /sdcard/Download/
```
Cài **J2ME Loader** (Play Store / F-Droid) rồi mở `KO_ThoMika.jar`.

### Cách A: bắt gói tin từ server thật (nếu server còn sống)
```bash
python sniffer.py --upstream hs1.teamobi.com
```
Mở game rồi chơi bình thường. Mọi gói sẽ được in ra màn hình và ghi vào `packets.jsonl`.

### Cách B: chạy server riêng
```bash
python server.py
```
Đăng nhập bằng tài khoản bất kỳ. Nếu thấy hộp thoại "Server local OK!" thì đường truyền đã thông.

## Viết lại server: quy trình
1. Mở game với `server.py`. Lệnh nào chưa có handler sẽ được in kèm hexdump.
2. Tìm hàm đọc gói đó trong `netcommand/global/ReadMessenge.class` (bảng chuyển lệnh nằm
   trong `GlobalMessageHandler.onMessage`):
   `javap -p -c -constants -cp x netcommand.global.ReadMessenge > rm.txt`
   Lần theo chuỗi `readByte/readShort/readUTF…` để biết định dạng.
   Nếu dùng được jadx hoặc CFR trên PC thì đọc mã Java sẽ nhanh hơn nhiều.
3. Thêm handler:
```python
@on(13)  # SELECT_CHAR từ client
async def h_select(c, r):
    idx = r.byte(); x = r.int()
    await c.send(3, Writer().int(...).utf(...))   # MAIN_CHAR_INFO
```
4. Thứ tự nên làm: `LIST_CHAR(13)` → `CREATE_CHAR(14)` → `MAIN_CHAR_INFO(3)` →
   `CHANGE_MAP(12)` → `OBJECT_MOVE(4)` → `MONSTER_INFO(7)` → combat → item.
5. Có `packets.jsonl` từ Cách A thì có thể phát lại nguyên văn các gói server gửi để
   dựng nhanh từng màn.

## Lưu ý
- `test_client.py` đã chạy thử với `server.py` và `sniffer.py`; cả hai đều nhận đúng
  gói và giải mã đúng.

- Server chỉ nên dùng để học và chơi một mình trong máy. Mở công khai bản giả lập game của
  TeaMobi có thể vi phạm điều khoản sử dụng hoặc bản quyền của họ.
