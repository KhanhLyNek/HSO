# LyMeowz Smart Tools 2.0 — HSO / Hiệp Sĩ Online

**Nhánh thử nghiệm:** `feature/training-assistant-20261013`. Không sửa `main`.

## Mở tool

**Điện thoại cảm ứng:** mở thanh phím tắt nhanh ở cạnh dưới màn hình và chọn **LyMeowz - Công cụ** (mục đầu tiên).

**Thiết bị dùng bàn phím:** nhấn phím mềm **Menu**, vào **LyMeowz - Công cụ**. Mục này cũng xuất hiện trong **Chức năng** và **Auto** gốc.

Từ trung tâm LyMeowz, chọn một nhóm:

| Nhóm | Chức năng |
| --- | --- |
| **Luyện cấp thông minh** | Bắt đầu/dừng, chế độ chọn quái, tuần tra, hồi phục/hồi sinh, xem thống kê |
| **Săn Boss** | Quét boss trên cùng bản đồ, tự chọn boss ở gần, bật/dừng, điều chỉnh phạm vi |
| **Đi buôn** | Ghi/phát lại tuyến hàng, theo dõi thú chở, mở NPC mua/bán đã ghi nhận |
| **Tiện ích & trạng thái** | Trạng thái tổng hợp và số phiên bản |

Mỗi menu con đều có lệnh **Quay lại** rõ ràng. Menu dùng ký tự tiếng Việt có dấu; đã kiểm tra toàn bộ chuỗi hiển thị với bảng ký tự trong `sources/CLib/mFont.java`.

## Luyện cấp thông minh

Có 3 chế độ:

- **Thông minh** (mặc định): ưu tiên quái gần và phù hợp cấp, cộng điểm cho quái đã yếu; giữ mục tiêu ít nhất 2,5 giây trước khi đổi vì lợi thế nhỏ.
- **An toàn**: tránh quái quá cấp, tăng ưu tiên mục tiêu gần. Có thể chỉnh ngưỡng ngừng tấn công dưới 25%, 35%, 50% HP.
- **Cổ điển**: gần với cách chọn quái truyền thống, nhưng vẫn chặn quái boss, thú đi buôn, NPC, quái nhiệm vụ và CAPTCHA.

Dữ liệu được xử lý theo thuật toán chấm điểm **ngay trên máy**, không kết nối AI trên Internet. Quét mục tiêu khoảng 1 lần/giây để giảm lag.

### Tuần tra tự học

1. Đến bãi luyện và lưu tối thiểu 2 (tối đa 6) tọa độ khác nhau, trong cùng bản đồ/khu.
2. Vào **Luyện cấp -> Tuần tra bãi luyện**, bật **Tuần tra**.
3. Chọn **Thông minh** hoặc **An toàn**.
4. Khi không còn quái phù hợp trong 4,5 giây, nhân vật đi tới mốc tiếp theo.
5. Bộ nhớ bãi quái ghi số lượng quái quan sát được gần mỗi mốc, ưu tiên các mốc có nhiều quái và giảm ưu tiên mốc bị kẹt hoặc vừa ghé qua.
6. Khi phát hiện quái phù hợp, nhân vật ngừng đi và tiếp tục đánh.
7. Nếu không tìm được đường hoặc mắc kẹt nhiều lần, tool dừng an toàn.

**Giới hạn:** Mô hình tự học nhỏ này chỉ ghi nhớ trong phiên chạy hiện tại; không tự khám phá cổng qua bản đồ mới. Nếu chưa ghi mốc, bật Tuần tra sẽ không có tác dụng.

### Tự hồi sinh

Giữ chức năng gốc: chết -> về làng -> tìm NPC đá dịch chuyển -> quay lại bản đồ/khu/toạ độ đã lưu -> tiếp tục luyện. Server có thể trả menu đá dịch chuyển không đúng mẫu; tool sẽ dừng thay vì đoán lựa chọn.

## Săn Boss

- Là chế độ **bật riêng**, không chạy đồng thời với Auto UP hoặc Đi buôn.
- Chỉ nhận boss được game đánh dấu `typeBoss == 1` hoặc `isBossNew()`, đang sống và có mặt trên cùng bản đồ.
- Không quét xuyên map, không tự tele tìm boss, không tự PK, không can thiệp CAPTCHA.
- Có phạm vi 160 / 240 / 320 pixel, ưu tiên boss đang đánh và boss gần.
- HP nhân vật dưới 45% sẽ tạm ngưng đánh. Chuyển bản đồ hoặc nhân vật chết thì dừng chế độ.

## Đi buôn

Các tính năng đi buôn cũ vẫn còn nhưng được gom thành 3 menu trung gian: **Ghi tuyến & dẫn hàng**, **Giao dịch NPC**, **Cấu hình & trạng thái**. Việc mua/bán đá quý và qua cổng vẫn do người chơi xác nhận để tránh mua/bán hoặc dịch chuyển nhầm.

## Kiến trúc bản build JAR

- Bản phát hành dùng thư mục `resources/` (các `.class` gốc) làm nền; mã decompile ở `sources/GameScreen` **không được biên dịch toàn bộ**.
- `sources/tools/ui/ToolMenus.java`: phân cấp menu, tất cả lệnh dùng ID hợp lệ cho `byte`.
- `sources/tools/training/TargetPolicy.java`: bộ chấm điểm quái.
- `sources/tools/training/PatrolBrain.java`: bộ nhớ mật độ bãi quái.
- `sources/tools/training/TrainingController.java`: điều khiển auto luyện, tuần tra, hồi sinh.
- `sources/tools/boss/BossController.java`: quét boss an toàn.
- `sources/tools/trading/TradeController.java`: chở hàng.
- `build-tools/tools/build/BytecodePatcher.java`: chèn menu và cơ chế chọn mục tiêu vào bytecode game gốc.
- `.github/workflows/auto-tools-j2me.yml`: biên dịch, kiểm thử, preverify CLDC và xuất artifact JAR.

## Xác minh

Workflow phải chạy thành công và xuất artifact **HSO-LyMeowz-Smart-CLDC-JAR**.

Các bước tự động gồm: `TargetPolicyTest`, `PatrolBrainTest`, `TradeRouteTest`, kiểm ký tự font, kiểm các hook trong client gốc, preverify ProGuard CLDC, kiểm classfile v45 và JAR toàn vẹn.

Chưa phải kiểm thử trong game / server thực tế. Nếu thiết bị gặp lỗi font, menu, pathfinding hoặc quái boss cụ thể, cần ảnh/log thiết bị để điều chỉnh và xác minh thêm.
