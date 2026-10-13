package tools.ui;

import CLib.mVector;
import GameScreen.GameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.Menu2;
import Model.T;
import tools.training.TrainingController;
import tools.training.TrainingSettings;
import tools.trading.TradeController;

/**
 * Entry point used by the CLDC-compatible bytecode integration build.
 * The original classes in resources/ are the executable client base.
 */
public final class ToolMenus {
    private ToolMenus() {}

    public static void onShow(GameScreen game) {
        game.cmdMenu = new iCommand(T.menu, 70, game);
        if (!GameCanvas.isTouch) {
            game.left = game.cmdMenu;
        }
    }

    private static iCommand action(String text, int id) {
        return new iCommand(text, id, GameScreen.gI());
    }

    private static void start(Menu2 menu, mVector entries, int i,
                              String title, boolean interaction, mVector targets) {
        menu.startAt(entries, i, title, interaction, targets);
    }

    private static void addTools(mVector items) {
        items.addElement(action("Auto UP Level", 80));
        items.addElement(action("Auto Di Buon", 86));
    }

    public static void openMain(Menu2 menu, mVector items, int i, String title,
                                boolean interaction, mVector targets) {
        for (int n = items.size() - 1; n >= 0; n--) {
            if (items.elementAt(n) == null) items.removeElementAt(n);
        }
        addTools(items);
        start(menu, items, i, title, interaction, targets);
    }

    public static void openFunctions(Menu2 menu, mVector items, int i,
                                     String title, boolean interaction, mVector targets) {
        addTools(items);
        start(menu, items, i, title, interaction, targets);
    }

    public static void openAuto(Menu2 menu, mVector items, int i,
                                String title, boolean interaction, mVector targets) {
        addTools(items);
        start(menu, items, i, title, interaction, targets);
    }

    public static void openTrainingMenu() {
        mVector items = new mVector("AutoUP Tool menu");
        items.addElement(action(TrainingSettings.isEnabled() ? "Tat luyen cap" : "Bat luyen cap", 81));
        items.addElement(action(TrainingSettings.isReturnAfterDeath() ? "Ve bai sau chet: BAT" : "Ve bai sau chet: TAT", 82));
        items.addElement(action("Luu NPC dich chuyen", 83));
        items.addElement(action("Pham vi: " + TrainingSettings.getMaximumRange(), 84));
        items.addElement(action("Trang thai", 85));
        GameCanvas.menu2.startAt(items, 2, "Auto UP Level", false, null);
    }

    public static void openTradeMenu() {
        mVector items = new mVector("Trade Tool menu");
        items.addElement(action("Bat dau ghi tuyen", 87));
        items.addElement(action("Ket thuc ghi tuyen", 88));
        items.addElement(action("Bat dau dan hang", 89));
        items.addElement(action("Dung di buon", 90));
        items.addElement(action("Khoang cach thu: " + TradeController.getDistanceLimit(), 92));
        items.addElement(action("Luu NPC mua hang", 93));
        items.addElement(action("Luu NPC ban hang", 94));
        items.addElement(action("Mo NPC mua hang", 95));
        items.addElement(action("Mo NPC ban hang", 96));
        items.addElement(action("Trang thai", 91));
        GameCanvas.menu2.startAt(items, 2, "Auto Di Buon", false, null);
    }

    /** Handles synthetic menu IDs, all within signed byte range. */
    public static boolean dispatch(int id) {
        switch (id) {
            case 70: GameScreen.gI().doMenu(); return true;
            case 80: openTrainingMenu(); return true;
            case 81: TrainingController.toggle(); openTrainingMenu(); return true;
            case 82: TrainingSettings.toggleReturnAfterDeath(); openTrainingMenu(); return true;
            case 83: TrainingController.selectFocusedStone(); openTrainingMenu(); return true;
            case 84:
                int range = TrainingSettings.getMaximumRange();
                TrainingSettings.setMaximumRange(range == 120 ? 180 : range == 180 ? 280 : 120);
                openTrainingMenu();
                return true;
            case 85:
                GameCanvas.addInfoChar(TrainingController.status());
                openTrainingMenu();
                return true;
            case 86: openTradeMenu(); return true;
            case 87: TradeController.startRecording(); openTradeMenu(); return true;
            case 88: TradeController.finishRecording(); openTradeMenu(); return true;
            case 89: TradeController.beginEscort(); openTradeMenu(); return true;
            case 90: TradeController.stop(); openTradeMenu(); return true;
            case 91: GameCanvas.addInfoChar(TradeController.status()); openTradeMenu(); return true;
            case 92: TradeController.cycleDistanceLimit(); openTradeMenu(); return true;
            case 93: TradeController.captureSourceNpc(); openTradeMenu(); return true;
            case 94: TradeController.captureDestinationNpc(); openTradeMenu(); return true;
            case 95: TradeController.openSourceNpc(); return true;
            case 96: TradeController.openDestinationNpc(); return true;
            default: return false;
        }
    }

    public static boolean quickAction(int id) {
        if (id == 15) {
            GameCanvas.menu2.isShowMenu = false;
            openTrainingMenu();
            return true;
        }
        if (id == 16) {
            GameCanvas.menu2.isShowMenu = false;
            openTradeMenu();
            return true;
        }
        return false;
    }

    public static void showDynamic(Menu2 menu, mVector choices, int place,
                                   byte menuId, short npcId, String message) {
        if (!TrainingController.onDynamicMenu(npcId, menuId, choices)) {
            menu.setinfoDynamic(choices, place, menuId, npcId, message);
        }
    }

    public static void playerTick(GameObjects.Player player) {
        TrainingController.tick(player);
        TradeController.tick(player);
    }
}
