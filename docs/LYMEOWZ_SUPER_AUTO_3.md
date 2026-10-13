# LyMeowz Super Auto 3.0 — Auto UP, điều hướng và nhiệm vụ

Nhánh phát triển riêng: `feature/training-assistant-20261013`. Không chỉnh nhánh `main`.

## Một menu chung

Mở thanh menu nhanh ở cạnh dưới màn hình trong HSO -> **LyMeowz - Công cụ**.

- **Luyện cấp thông minh** -> Bắt đầu/Dừng, Chế độ, **Tự tìm bản đồ & quái**, Tuần tra, Sinh tồn, Thống kê.
- **Tự tìm bản đồ & quái** -> Tự tìm quái, Tự đổi khu, Tự qua bản đồ, Bản đồ và đường đã học (mặc định bật).
- **Nhiệm vụ tự động** -> Bật/Tắt hỗ trợ nhiệm vụ và xem tiến độ (mặc định tắt).
- **Săn Boss**, **Đi buôn**, **Tiện ích** vẫn nằm ở trung tâm.

Các chuỗi hiển thị được kiểm tra theo bảng chữ có dấu của sprite font gốc.

## Vì sao bản 2.0 có thể đứng yên?

Hàm tìm đường cũ dùng chỉ số minimap/đường trả ngược; bộ điều khiển bản 2.0 cũng đối chiếu điểm cuối sai, có thể báo lỗi dù đường hợp lệ. Super Auto 3.0 dùng `tools.navigation.TilePathfinder` và `SmartNavigator`, đi theo danh sách ô **từ nguồn tới đích**, chia đoạn tối đa 45 bước, kiểm tra ô cản và tự phát hiện kẹt.

Bộ đường mới không gọi server để ép dịch chuyển, mà sử dụng chuyển động có sẵn trong Player.

## Auto UP tự tìm bãi

1. Quét quái đã được server gửi trong map hiện tại, lựa mục tiêu theo level, HP và khoảng cách; tránh boss, thú đi buôn và mục tiêu không hợp lệ.
2. Nếu quái tương đương cấp xuất hiện ở xa: tự tìm đường tới gần và ghi nhận vị trí luyện mới.
3. Không có quái: khảo sát 15 ô vùng trong bản đồ theo trình tự có kiểm soát.
4. Khi vùng khảo sát vẫn thiếu quái: thử tối đa 2 khu khác qua lệnh đổi khu gốc và chờ server xác nhận.
5. Khi được bật tính năng tự qua map: chọn cổng được server gửi; ưu tiên map đã biết có quái phù hợp. Việc khám phá bị giới hạn tối đa 6 lần đổi map và không suy đoán mapId.
6. Nếu không xác định được đường, dừng di chuyển và báo tình trạng thay vì gửi liên tiếp nhiều lệnh sai.

Việc đánh quái cần dữ liệu quái hợp lệ do server cung cấp. Client không thể nhìn trước quái trên những bản đồ chưa được tải.

## Chết -> về làng -> quay lại

1. Lưu mapId, khu, tọa độ chết thực tế.
2. Gửi yêu cầu về làng gốc (một lần, đợi phản hồi).
3. Ưu tiên cổng có tên trùng bản đồ cần đến hoặc các cổng đã được **học từ lần chuyển map được server xác nhận**.
4. Nếu không có đường qua cổng đã biết, thử NPC đá dịch chuyển bằng menu gốc.
5. Đá không phản hồi/menu mơ hồ: đi tìm đường qua các cổng với giới hạn thời gian và số lần thử.
6. Vào đúng bản đồ: đổi về khu đã lưu, dùng đường đi theo ô có vật cản để về tọa độ, sau đó luyện tiếp.

**Giới hạn:** Đây không phải bản đồ toàn thế giới định tuyến sẵn. Khi mới cài, bộ nhớ đường đi chưa được học; lần đầu qua nhiều map có thể không tìm được đường, nhất là cổng khóa nhiệm vụ, map động, NPC đổi lựa chọn, bản đồ dài hơn khả năng mã hóa signed byte của client cũ, hoặc map không có lối đi thực tế. Tool sẽ dừng an toàn và báo để người chơi tự qua một lần; lần sau có thể dùng đường đã học trong **cùng phiên chơi**.

## Nhiệm vụ

Đọc danh sách `MainQuest.vecQuestDoing_Main/Sub` và `MainQuest.vecQuestFinish` từ client.

- Quái có catalogId trùng mục tiêu giết còn thiếu của nhiệm vụ được ưu tiên trong lúc Auto UP.
- NPC được ghi nhớ khi **thực sự xuất hiện** trong map. Nếu nhiệm vụ được server đánh dấu hoàn thành, có thể tìm đường tới NPC báo cáo đã biết.
- Gửi yêu cầu trả nhiệm vụ gốc **một lần**, chỉ khi quest được đánh dấu `isComplete` và NPC đã đến gần; chặn gửi lặp trong 60 giây.
- Chưa tự nhận mọi nhiệm vụ chưa mở khóa, chưa tự xử lý câu thoại/đối thoại không xác định, nhiệm vụ có điều kiện đặc biệt hay CAPTCHA.

## Kiến trúc nhẹ cho Java ME

- Bản đồ: `WorldMemory` đồ thị 64 map/180 đường nối, chỉ học qua map chuyển thật.
- Đường đi: `TilePathfinder` BFS theo ô có vật cản, có giới hạn kích thước và số bước mỗi chặng. Các ô tọa độ vượt khả năng ký hiệu byte gốc được từ chối an toàn.
- Điều khiển di chuyển: `SmartNavigator` điều tiết lần tìm đường, phát hiện kẹt và tránh xử lý mỗi frame.
- Khảo sát: `LocalSurvey`; mục tiêu & đổi khu/map: `AutoFarmExplorer`.
- Nhiệm vụ: `QuestAssistant` đọc thông tin nhiệm vụ/NPC từ server gốc.
- `BytecodePatcher` nối các hook vào class .class gốc trong `resources/`, không giả vờ build từ mã decompile.
- Menu & AI chọn quái: `ToolMenus`, `TrainingController`, `TargetPolicy`, `PatrolBrain`.

## Kiểm thử tự động / GitHub Actions

Workflow `.github/workflows/auto-tools-j2me.yml` phải:

- Build các class mod J2ME và vá vào class gốc.
- Chạy `TargetPolicyTest`, `PatrolBrainTest`, `TilePathfinderTest`, `WorldMemoryTest`, `TradeRouteTest`.
- Kiểm tra ký tự menu với font tiếng Việt gốc.
- Preverify CLDC bằng ProGuard, kiểm tra JAR và các class bắt buộc rồi upload artifact.

**CI không tương đương kiểm thử trực tiếp trên server HSO.** Để xác minh hành vi chuyển map, tự hồi sinh, chuyển khu và báo nhiệm vụ, vẫn cần kiểm thử trong J2ME Loader và log thực tế.

## Tình huống kiểm thử ưu tiên trên điện thoại

1. Bật Auto UP tại bản đồ không có quái quanh người: nhân vật đi tìm thay vì đứng im.
2. Bản đồ có nhiều quái xa: chọn quái trong khoảng cấp phù hợp và tự tiếp cận.
3. Ghi đường qua hai map bằng cách đi thủ công một lần; chết rồi xác nhận nó dùng lại cổng đã ghi, về đúng tọa độ.
4. Đá dịch chuyển không có NPC/menu: fallback tìm cổng hoặc dừng với thông báo có nguyên nhân, không mắc kẹt vô hạn.
5. Auto đổi khu không spam lệnh khi server không xác nhận.
6. Nhiệm vụ giết quái: ưu tiên đúng catalogId; sau khi hoàn thành chỉ báo ở NPC có thật.
7. Tắt Auto hoặc mở menu/dialog: không tự đánh hoặc can thiệp vào thao tác thủ công.
