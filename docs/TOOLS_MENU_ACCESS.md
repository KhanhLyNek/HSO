# Cách mở Menu LyMeowz Smart Tools 2.0

**Nhánh:** `feature/training-assistant-20261013`. Phải cài JAR build từ nhánh này; JAR trên `main` không có menu mới.

## Cảm ứng / J2ME Loader Android

1. Trong game, mở **thanh menu nhanh ở giữa cạnh dưới màn hình**.
2. Chọn **LyMeowz - Công cụ**, là mục mod duy nhất được chèn ở đầu thanh nhanh.
3. Chọn nhóm **Luyện cấp thông minh**, **Săn Boss**, **Đi buôn** hoặc **Tiện ích & trạng thái**.
4. Trong mỗi menu có mục **Quay lại**.

## Bàn phím / PC

1. Bấm phím mềm trái **Menu**.
2. Vào **LyMeowz - Công cụ** (một mục duy nhất nằm trong menu chính).
3. Chọn nhóm chức năng cần thiết; điều hướng bằng các phím hướng và phím chọn.
4. LyMeowz còn được mở từ menu **Chức năng** hoặc menu **Auto** gốc nếu bạn đang ở các mục đó.

## Chi tiết menu

- **Luyện cấp thông minh** -> Bắt đầu/Dừng; Chế độ (Thông minh / An toàn / Cổ điển); Tuần tra bãi luyện (lưu tối đa 6 mốc); Sinh tồn & hồi sinh; Thống kê.
- **Săn Boss** -> Bật/Dừng quét trên bản đồ hiện tại; chỉnh phạm vi 160/240/320; xem boss đang phát hiện.
- **Đi buôn** -> Ghi tuyến & dẫn hàng; Giao dịch NPC; Cấu hình & trạng thái.
- **Tiện ích** -> Xem trạng thái và phiên bản.

Các nhãn sử dụng tiếng Việt có dấu, đã xác minh nằm trong bảng ký tự sprite-font HSO (`sources/CLib/mFont.java`).

Xem [tài liệu chi tiết](LYMEOWZ_SMART_2.md).

## Kiểm thử cần thực hiện trên thiết bị

- [ ] Chạm thanh nhanh thấy đúng **một** mục LyMeowz.
- [ ] Menu phụ có mục Quay lại và hiển thị dấu tiếng Việt.
- [ ] Bật Luyện cấp; đổi chế độ; lưu 2-6 mốc; bật Tuần tra.
- [ ] Để bãi hết quái, nhân vật di chuyển giữa các mốc và ưu tiên bãi có nhiều quái hơn khi đã học.
- [ ] Săn Boss không chọn NPC, thú đi buôn hoặc CAPTCHA.
- [ ] Đi buôn vẫn ghi và dẫn hàng như trước.
- [ ] Menu mặc định, Hành trang, Chuyển khu và Auto gốc còn hoạt động.

**Lưu ý:** Test CI không thay thế test trên điện thoại / server thật.
