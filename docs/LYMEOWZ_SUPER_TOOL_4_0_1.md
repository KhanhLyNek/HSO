# LyMeowz Super Tool 4.0.1 — ổn định menu, điều phối và hồi sinh

Nhánh thử nghiệm `feature/training-assistant-20261013`. Không sửa `main`.

## Mục tiêu và thay đổi thực tế

### 1. Một vòng lặp điều phối thay vì ba tool cùng phát lệnh

`sources/tools/core/SuperToolEngine.java` nay là điểm gọi duy nhất từ hook `Player.update()` cho Auto UP, Săn Boss và Đi Buôn.

`ModeArbiter.choose()` đặt ưu tiên:

1. **Đi buôn đang dẫn hàng**.
2. **Ghi tuyến đường đi buôn**.
3. **Săn Boss**.
4. **Auto UP Level**.
5. **Dừng / idle**.

Khi trạng thái bị trùng, SuperToolEngine dừng các chế độ thấp ưu tiên trước khi phát lệnh cho chế độ đang nắm quyền. Các callback của người chơi khác không được phép khởi chạy tool cho nhân vật hiện tại. Việc quan sát NPC của QuestAssistant vẫn có thể hoạt động mà không chiếm quyền điều khiển.

### 2. Menu LyMeowz không xâm phạm Hành trang gốc

- Hành trang của HSO được bảo toàn và khôi phục tại lệnh native `GameScreen.commandPointer(1)`.
- Hook `Model.Menu2.commandPointer(15)` giờ **chỉ** mở LyMeowz khi thanh menu nhanh đang hiển thị đúng lệnh `LyMeowz - Công cụ`. Cùng ID 15 của menu gốc không bị điều hướng sang tool.
- Giữ đường mở từ thanh menu nhanh hoặc menu chính, có tiếng Việt có dấu.
- Có nút **Dừng tất cả Auto** ở Trung tâm và Tiện ích.
- Có mục **Widget nhỏ** trong Tiện ích. Widget mặc định **TẮT**, không bắt sự kiện chạm; chỉ vẽ tên chế độ và % HP ở vùng trên màn hình, không thay đổi logic game.

### 3. Địa điểm hồi sinh được chụp khi nhân vật còn sống

`RecoverySnapshot` giữ mapId, khu, tọa độ và tên bản đồ từ lần gần nhất nhân vật được quan sát đang sống. Khi callback chết đến, nó ưu tiên thông tin sống còn mới (trong vòng 30 giây) trước tọa độ hiện thời, nhằm tránh trường hợp server đã đưa nhân vật về làng.

- Các gói tin chết lặp lại không thể ghi đè snapshot.
- Snapshot hết hạn không được dùng; khi đó sử dụng thông tin hiện tại.
- Khi kết thúc trở về bãi, snapshot được đặt lại để phục vụ lần chết kế tiếp.
- **Giới hạn:** Chưa có bản đồ toàn thế giới, nên không bảo đảm về được những map chưa có đường/gateway đã biết hoặc nơi server khóa cổng.

### 4. Thử thách chống Auto

Các hộp thoại trong game (bao gồm trường hợp có thể là xác minh chống Auto) làm tool dừng hành động chiến đấu/di chuyển cho tới khi giao diện được người chơi xử lý. Không có module tự giải CAPTCHA hay vượt cơ chế xác minh.

## Menu trong game

```text
LyMeowz - Trung tâm
  Luyện cấp thông minh
    Bắt đầu / Dừng
    Chế độ chiến đấu
    Tự tìm bản đồ & quái
    Tuần tra bãi luyện
    Sinh tồn & hồi sinh
    Thống kê
  Săn Boss
  Đi buôn
  Nhiệm vụ tự động
  Hành trang (lệnh gốc HSO)
  Tiện ích & trạng thái
    Tổng quan
    Trạng thái tìm đường
    Widget nhỏ: BẬT / TẮT
    Dừng tất cả Auto
  Dừng tất cả Auto
  Về menu trò chơi
```

Widget hiển thị tối giản: `LyMeowz UP HP 85%`, `LyMeowz BUÔN HP 85%`, hoặc `LyMeowz NGHỈ HP 85%`. Không che Hành trang vì mặc định ẩn và tự ẩn khi mở menu hoặc hộp thoại.

## Build / kiểm thử

Workflow `.github/workflows/auto-tools-j2me.yml` biên dịch các module mới cùng class gốc, tích hợp `ToolHud.paint()` bằng BytecodePatcher sau lớp vẽ trò chơi rồi preverify ProGuard CLDC.

Bài kiểm thử:
- `ModeArbiterTest`: không chạy chồng hai chế độ.
- `RecoverySnapshotTest`: tránh tọa độ sai sau khi chết, gói tin lặp và dữ liệu quá cũ.
- `TargetPolicyTest`, `PatrolBrainTest`: chọn mục tiêu và tự học bãi.
- `TilePathfinderTest`, `WorldMemoryTest`: đường đi và liên kết map.
- `TradeRouteTest`: tuyến buôn.
- Kiểm tra font sprite tiếng Việt, đầu ra classfile v45, `ToolHud.paint` trong bytecode game và `cmdMyseft` mở Hành trang gốc.

## Kiểm thử trên J2ME Loader cần làm

1. Mở Menu -> Hành trang, sau đó mở từ Trung tâm LyMeowz, xác nhận vẫn sử dụng kho đồ gốc.
2. Ở thanh nhanh, bấm LyMeowz; kiểm tra các mục chính của HSO không bị lấy nhầm ID 15.
3. Bật UP, chuyển sang Săn Boss, thử bật ghi tuyến: phải chỉ có một chế độ điều khiển.
4. Bật Widget nhỏ, kiểm tra giao diện không che phím; tắt sẽ biến mất.
5. Chết khi đang UP, chờ về làng, kiểm tra menu trạng thái hồi sinh và tuyến đường đã học.
6. Mở menu/đối thoại khi đi buôn: nhân vật phải tạm dừng.
7. Xác minh tiếng Việt có dấu hiển thị trên thiết bị.

CI chỉ kiểm chứng tính đúng cấu trúc/logic cục bộ; **chưa thay thế chạy thật trên server HSO và J2ME Loader**.
