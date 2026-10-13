package tools.ui;

import CLib.mVector;
import GameScreen.GameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.Menu2;
import Model.T;
import tools.boss.BossController;
import tools.training.TrainingController;
import tools.training.TrainingSettings;
import tools.trading.TradeController;

/**
 * Unified LyMeowz menu. Keeps existing game menus intact and exposes one
 * simple entrypoint on both the regular menu and the touch quick bar.
 *
 * Every command id fits the signed-byte iCommand.indexMenu range.
 * All display labels use Unicode Vietnamese; no external assets.
 */
public final class ToolMenus {
    private static final int HUB = 80;
    private ToolMenus() {}

    public static void onShow(GameScreen game) {
        game.cmdMenu = new iCommand(T.menu, 70, game);
        if (!GameCanvas.isTouch) game.left = game.cmdMenu;
    }

    private static iCommand action(String label, int id) {
        return new iCommand(label, id, GameScreen.gI());
    }

    private static mVector items(String category) {
        return new mVector("LyMeowz: " + category);
    }

    private static void show(String title, mVector choices) {
        GameCanvas.menu2.startAt(choices, 2, title, false, null);
    }

    private static void addHub(mVector choices) {
        choices.addElement(action("LyMeowz - Công cụ", HUB));
    }

    public static void openMain(Menu2 menu, mVector choices, int position,
                                String title, boolean interaction, mVector targets) {
        // Decompiled client occasionally leaves an uninitialized menu command.
        for (int i = choices.size() - 1; i >= 0; i--) {
            if (choices.elementAt(i) == null) choices.removeElementAt(i);
        }
        addHub(choices);
        menu.startAt(choices, position, title, interaction, targets);
    }

    public static void openFunctions(Menu2 menu, mVector choices, int position,
                                     String title, boolean interaction, mVector targets) {
        addHub(choices);
        menu.startAt(choices, position, title, interaction, targets);
    }

    public static void openAuto(Menu2 menu, mVector choices, int position,
                                String title, boolean interaction, mVector targets) {
        addHub(choices);
        menu.startAt(choices, position, title, interaction, targets);
    }

    public static void openHub() {
        mVector choices = items("Trang chủ");
        choices.addElement(action("Luyện cấp thông minh", 97));
        choices.addElement(action("Săn Boss", 98));
        choices.addElement(action("Đi buôn", 86));
        choices.addElement(action("Tiện ích & trạng thái", 99));
        choices.addElement(action("Về menu trò chơi", 70));
        show("LyMeowz | Trung tâm", choices);
    }

    public static void openTrainingMenu() {
        mVector choices = items("Luyện cấp");
        choices.addElement(action(TrainingSettings.isEnabled() ?
                "Dừng luyện cấp" : "Bắt đầu luyện cấp", 81));
        choices.addElement(action("Chế độ: " + TrainingSettings.modeName(), 101));
        choices.addElement(action("Tuần tra bãi luyện", 102));
        choices.addElement(action("Sinh tồn & hồi sinh", 106));
        choices.addElement(action("Thống kê luyện cấp", 109));
        choices.addElement(action("Quay lại LyMeowz", 117));
        show("LyMeowz | Luyện cấp", choices);
    }

    private static void openPatrolMenu() {
        mVector choices = items("Tuần tra");
        choices.addElement(action(TrainingSettings.isPatrolEnabled() ?
                "Tuần tra: BẬT" : "Tuần tra: TẮT", 105));
        choices.addElement(action("Lưu tọa độ (" + TrainingController.getPatrolCount() + "/6)", 103));
        choices.addElement(action("Xóa các mốc đã lưu", 104));
        choices.addElement(action("Phạm vi đánh: " + TrainingSettings.getMaximumRange(), 84));
        choices.addElement(action("Về Luyện cấp", 118));
        show("LyMeowz | Tuần tra", choices);
    }

    private static void openSurvivalMenu() {
        mVector choices = items("Sinh tồn");
        choices.addElement(action("Ngừng đánh khi HP < " + TrainingSettings.getMinimumHp() + "%", 107));
        choices.addElement(action(TrainingSettings.isReturnAfterDeath() ?
                "Tự trở lại bãi: BẬT" : "Tự trở lại bãi: TẮT", 82));
        choices.addElement(action("Chọn đá dịch chuyển", 83));
        choices.addElement(action("Trạng thái luyện cấp", 85));
        choices.addElement(action("Về Luyện cấp", 118));
        show("LyMeowz | Sinh tồn", choices);
    }

    private static void openBossMenu() {
        mVector choices = items("Săn Boss");
        choices.addElement(action(BossController.isEnabled() ?
                "Dừng săn Boss" : "Bật săn Boss gần đây", 111));
        choices.addElement(action("Phạm vi quét: " + BossController.getRadius(), 112));
        choices.addElement(action("Xem Boss trong vùng", 113));
        choices.addElement(action("Quay lại LyMeowz", 117));
        show("LyMeowz | Săn Boss", choices);
    }

    public static void openTradeMenu() {
        mVector choices = items("Đi buôn");
        choices.addElement(action("Ghi tuyến & dẫn hàng", 114));
        choices.addElement(action("Giao dịch NPC", 115));
        choices.addElement(action("Cấu hình & trạng thái", 116));
        choices.addElement(action("Quay lại LyMeowz", 117));
        show("LyMeowz | Đi buôn", choices);
    }

    private static void openTradeRoute() {
        mVector choices = items("Tuyến đường");
        choices.addElement(action("Bắt đầu ghi tuyến", 87));
        choices.addElement(action("Kết thúc ghi tuyến", 88));
        choices.addElement(action("Bắt đầu dẫn hàng", 89));
        choices.addElement(action("Dừng dẫn hàng", 90));
        choices.addElement(action("Về Đi buôn", 119));
        show("LyMeowz | Tuyến buôn", choices);
    }

    private static void openTradeNPC() {
        mVector choices = items("Giao dịch NPC");
        choices.addElement(action("Lưu NPC mua hàng", 93));
        choices.addElement(action("Lưu NPC bán hàng", 94));
        choices.addElement(action("Mở NPC mua hàng", 95));
        choices.addElement(action("Mở NPC bán hàng", 96));
        choices.addElement(action("Về Đi buôn", 119));
        show("LyMeowz | Giao dịch", choices);
    }

    private static void openTradeConfig() {
        mVector choices = items("Cấu hình buôn");
        choices.addElement(action("Khoảng cách thú: " + TradeController.getDistanceLimit(), 92));
        choices.addElement(action("Trạng thái đi buôn", 91));
        choices.addElement(action("Về Đi buôn", 119));
        show("LyMeowz | Vận chuyển", choices);
    }

    private static void openUtilities() {
        mVector choices = items("Tiện ích");
        choices.addElement(action("Tổng quan các tool", 120));
        choices.addElement(action("Thông tin phiên bản", 121));
        choices.addElement(action("Quay lại LyMeowz", 117));
        show("LyMeowz | Tiện ích", choices);
    }

    private static void showStatus(String message) {
        GameCanvas.addInfoChar(message);
    }

    /** Routed through the existing GameScreen.commandPointer bytecode hook. */
    public static boolean dispatch(int id) {
        switch (id) {
            case 70: GameScreen.gI().doMenu(); return true;
            case 80: openHub(); return true;
            case 97: openTrainingMenu(); return true;
            case 98: openBossMenu(); return true;
            case 99: openUtilities(); return true;
            case 117: openHub(); return true;
            case 118: openTrainingMenu(); return true;
            case 119: openTradeMenu(); return true;
            case 101:
                TrainingSettings.cycleMode(); openTrainingMenu(); return true;
            case 102: openPatrolMenu(); return true;
            case 103:
                TrainingController.recordWaypoint(); openPatrolMenu(); return true;
            case 104:
                TrainingController.clearWaypoints(); openPatrolMenu(); return true;
            case 105:
                TrainingSettings.togglePatrol(); openPatrolMenu(); return true;
            case 106: openSurvivalMenu(); return true;
            case 107:
                TrainingSettings.cycleMinimumHp(); openSurvivalMenu(); return true;
            case 109:
                showStatus(TrainingController.statistics()); openTrainingMenu(); return true;
            case 81:
                TrainingController.toggle(); openTrainingMenu(); return true;
            case 82:
                TrainingSettings.toggleReturnAfterDeath(); openSurvivalMenu(); return true;
            case 83:
                TrainingController.selectFocusedStone(); openSurvivalMenu(); return true;
            case 84:
                int range = TrainingSettings.getMaximumRange();
                TrainingSettings.setMaximumRange(range == 120 ? 180 : range == 180 ? 280 : 120);
                openPatrolMenu(); return true;
            case 85:
                showStatus(TrainingController.status()); openSurvivalMenu(); return true;
            case 111:
                BossController.toggle(); openBossMenu(); return true;
            case 112:
                BossController.cycleRadius(); openBossMenu(); return true;
            case 113:
                showStatus(BossController.status()); openBossMenu(); return true;
            case 86: openTradeMenu(); return true;
            case 114: openTradeRoute(); return true;
            case 115: openTradeNPC(); return true;
            case 116: openTradeConfig(); return true;
            case 87:
                TradeController.startRecording(); openTradeRoute(); return true;
            case 88:
                TradeController.finishRecording(); openTradeRoute(); return true;
            case 89:
                TradeController.beginEscort(); openTradeRoute(); return true;
            case 90:
                TradeController.stop(); openTradeRoute(); return true;
            case 91:
                showStatus(TradeController.status()); openTradeConfig(); return true;
            case 92:
                TradeController.cycleDistanceLimit(); openTradeConfig(); return true;
            case 93:
                TradeController.captureSourceNpc(); openTradeNPC(); return true;
            case 94:
                TradeController.captureDestinationNpc(); openTradeNPC(); return true;
            case 95: TradeController.openSourceNpc(); return true;
            case 96: TradeController.openDestinationNpc(); return true;
            case 120:
                showStatus("UP: " + TrainingController.status());
                showStatus("Boss: " + BossController.status());
                showStatus("Buôn: " + TradeController.status());
                openUtilities(); return true;
            case 121:
                showStatus("LyMeowz Smart Tools 2.0 | AI chạy trực tiếp trên máy.");
                openUtilities(); return true;
            default: return false;
        }
    }

    public static boolean quickAction(int id) {
        if (id == 15 || id == 16) {
            GameCanvas.menu2.isShowMenu = false;
            openHub();
            return true;
        }
        return false;
    }

    public static void showDynamic(Menu2 menu, mVector choices, int place,
                                   int menuId, int npcId, String message) {
        if (!TrainingController.onDynamicMenu((short) npcId, (byte) menuId, choices)) {
            menu.setinfoDynamic(choices, place, menuId, npcId, message);
        }
    }

    public static void playerTick(GameObjects.Player player) {
        TrainingController.tick(player);
        BossController.tick(player);
        TradeController.tick(player);
    }
}
