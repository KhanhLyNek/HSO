# Mo menu mod HSO: Auto UP Level / Auto Di Buon

> Tinh trang: Menu action IDs and access paths have been corrected on branch `feature/training-assistant-20261013`. This is source-level verification only; build and in-game UI testing are still required.

## Che do cam ung (J2ME touch / Android emulator)

**Duong 1 - nhanh nhat:**
1. Vao man hinh game.
2. Cham nut menu nhanh o **giua duoi man hinh** (original `PaintInfoGameScreen.selectPointer(4)`, opening `Menu2.setAt_Quick()`).
3. Vuot thanh menu ngang neu can, tim `Auto UP Level` hoac `Auto Di Buon`.
4. Cham chon tool, sau do chon lenh bat/tat tu menu con.

**Duong 2:**
- Cham nut `Menu` cua nhan vat (original top-left game UI).
- `Menu chinh -> Chuc nang -> Luyen cap` hoac `Menu chinh -> Auto Di Buon`.
- Ben trong `Tu dong` goc cua menu nhanh cung co hai muc mod.

## Che do phim / may tinh

- Nhan phim mem trai `Menu`.
- Chon `Chuc nang -> Luyen cap` de bat/tat Auto UP Level.
- Hoac chon `Auto Di Buon` truc tiep trong menu chinh.
- Dung phim huong va nut giua/Enter de chon.
- Muc `Hanh trang` van duoc giu trong menu chinh.

## Luu y ve code

- `iCommand.indexMenu` is a Java `byte` (-128..127). The old IDs 180..196 were unreachable via positive switch cases.
- The code now uses 80..96 for tool actions and 70 for the root `Menu` command. `Menu2` quick-toolbar actions use 15 and 16.
- The original `cmdMyseft` root item was never initialized; it is now initialized to the inventory action.
- Root menu's `Chuyen khu` item has been reconnected to `doArea()`.
- Touch quick-menu icon slots reuse valid existing index 2; no additional sprite asset is required.
- **Chinh sua chi o nhanh rieng**. Game da cai dat hoac nhanh `main` se khong thay doi cho den khi build phien ban moi tu nhanh nay.

## Soat xet thu cong tren source

- Tool command IDs and switch cases match: 80..96.
- Main menu opens via command 70 on keypad / touch.
- `Menu2.setAt_Quick()` lists both tools and dispatches action 15/16.
- `doMenuAuto()` lists both tools.
- `iCommand` dispatches callbacks through the owning `GameScreen`.
- The two modified UI source files have balanced Java braces.

## Checklist sau khi build

- [ ] Nhan nut Menu mo menu chinh, co Hanh trang, Chuc nang, Auto Di Buon.
- [ ] Chon Chuc nang -> Luyen cap -> Bat luyen cap, trang thai thay doi.
- [ ] Cham nut giua duoi -> menu nhanh, thay ca hai tool.
- [ ] Chon Auto -> thay link toi Auto UP Level va Auto Di Buon.
- [ ] Cham nut dong / Back khong lam keo theo hanh dong ngoai y muon.
- [ ] Chuyen khu va Hanh trang van hoat dong.
- [ ] Chua duoc xem la da kiem thu in-game truoc khi build va chay thu client.
