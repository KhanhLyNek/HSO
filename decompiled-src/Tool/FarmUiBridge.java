/*
 * Bridge between the original KnightOnline v4.0.3 bytecode and the new
 * decompiled-src/Tool Auto Farm sources.  Only original game APIs are used.
 * The original JAR's Player / GameScreen classes are patched at the bytecode
 * level by tools/BuildFarmJar.java to avoid recompiling CFR output.
 */
package Tool;

import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.MainMonster;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;

public final class FarmUiBridge {
    private static final int FARM_MENU_ID = 91;
    private static final int FARM_STATUS_ID = 92;

    private FarmUiBridge() {}

    public static void appendMenu(mVector menu) {
        if (menu == null) return;
        String label = AutoFarmController.isEnabled() ?
                "Tat Auto Farm" : "Bat Auto Farm";
        menu.addElement(new iCommand(label, FARM_MENU_ID, GameScreen.gI()));
        menu.addElement(new iCommand("Trang thai Auto Farm", FARM_STATUS_ID, GameScreen.gI()));
    }

    public static boolean handleCommand(int command) {
        if (command == FARM_MENU_ID) {
            AutoFarmController.toggle();
            return true;
        }
        if (command == FARM_STATUS_ID) {
            int current = GameCanvas.loadmap == null ? -1 : GameCanvas.loadmap.idMap;
            int target = AutoFarmController.getTargetMapId();
            GameCanvas.addInfoChar("Farm " + AutoFarmController.getStatus() +
                    " - map " + current + " -> " + target);
            if (target >= 0) {
                GameCanvas.addInfoChar("Quai ID " + AutoFarmController.getTargetMonsterId() +
                        " Lv " + AutoFarmController.getTargetMonsterLevel() +
                        " | bai (" + AutoFarmController.getFarmAnchorX() +
                        "," + AutoFarmController.getFarmAnchorY() + ")");
            }
            return true;
        }
        return false;
    }

    /**
     * Filters the native nextMonster() selection while Auto Farm is farming.
     * Returns true to consume nextMonster(), false for the original logic.
     */
    public static boolean selectNextMonster(Player player) {
        if (!AutoFarmController.isFarming() || player == null) return false;
        int targetId = AutoFarmController.getTargetMonsterId();
        if (targetId < 0) return false;

        MainObject nearest = null;
        int bestDistance = player.wFocus * 3 / 2;
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject obj = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (!(obj instanceof MainMonster) || obj.typeObject != 1 ||
                    obj.Action == 4 || obj.isRemove || obj.isSend ||
                    obj.typeBoss == 2 || obj.isLuaThieng()) continue;
            if (((MainMonster)obj).catalogyMonster != targetId) continue;
            int d = MainObject.getDistance(player.x, player.y, obj.x, obj.y);
            if (d >= bestDistance) continue;
            nearest = obj;
            bestDistance = d;
        }
        GameScreen.ObjFocus = nearest;
        PaintInfoGameScreen.isPaintInfoFocus = nearest != null;
        // Keep the original nextMonster() bookkeeping: without this, native
        // auto-fire may believe every search is unsuccessful and move away
        // from the saved center even when a matching monster was acquired.
        if (nearest != null) {
            Player.demUnFire = 0;
        } else if (Player.demUnFire < 100) {
            Player.demUnFire = (short)(Player.demUnFire + 1);
        }
        return true;
    }
}
