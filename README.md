# HSO - NpcFinder mod

Mod cho client Knight Online J2ME v4.0.3 (MIDlet `com.silverknight.TemMidlet`).

Repo này **chỉ chứa code do mình viết**. Không có jar hay mã nguồn game; bạn cần tự có client hợp lệ.

## Tính năng
- Mục **"Tìm NPC"** trong MENU chính: liệt kê NPC trong map (gần nhất trước), chọn một NPC để đánh dấu trên minimap và tự chạy tới.
- **"Tự động tìm Đứa bé"**: với mỗi map, dò lần lượt từng khu (Request_Area / Change_Area), rồi đi qua các cổng chưa thử sang map khác. Thấy NPC thì dừng và chạy tới. Tên NPC đổi ở hằng `AUTO_TARGET`.

## Cài đặt
1. Giải nén jar, decompile (ví dụ CFR) để lấy `GameScreen/GameScreen.java`.
2. Chép `NpcFinder.java` vào package `GameScreen`.
3. Sửa `GameScreen.java`:
   - Thêm field `public iCommand cmdFindNpc;`
   - Trong constructor, sau khi tạo `cmdinfoMiNuong`: `this.cmdFindNpc = new iCommand("Tìm NPC", 32, this);`
   - Trong `doMenu()`, trước `mVector2.addElement(this.cmdChangeMap);` thêm `mVector2.addElement(this.cmdFindNpc);`
   - Trong `commandPointer(int, int)`, sau `case 31` (thêm `break;` cho case 31) thêm:
     - `case 32:` gọi `NpcFinder.show();`
     - `case 33:` gọi `NpcFinder.select(n2);`
     - `case 34:` gọi `NpcFinder.startAuto();`
     - `case 35:` gọi `NpcFinder.stopAuto();`
   - Ở đầu thân `try` của `public void update()` thêm `NpcFinder.tick();`
4. Biên dịch với classpath là jar gốc (`javac --release 8 -cp game.jar ...`). Decompiler có thể làm mất vài phép ép kiểu `byte`, cần thêm lại cho đến khi biên dịch được.
5. Thay `GameScreen.class` và thêm `NpcFinder.class` vào jar.

## Lưu ý
- Chưa kiểm tra trên trình giả lập thật; cần theo dõi lần chạy đầu.
- Chế độ tự tìm điều khiển nhân vật qua nhiều map/khu, nên dùng có chủ ý.
