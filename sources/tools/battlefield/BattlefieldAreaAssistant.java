package tools.battlefield;

import CLib.mSystem;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;
import tools.core.ModeArbiter;
import tools.core.SuperToolEngine;

/**
 * Battlefield zone assistant using HSO's original Change_Area request.
 * It does not modify speed, collision, packets, or combat targeting.
 */
public final class BattlefieldAreaAssistant {
    private static boolean enabled;
    private static long lastScan, emptySince, lastAreaRequest;
    private static int originalMap = -1, lastArea = -1, failures;
    private static boolean awaitingAck;
    private static int visibleMonsters;

    private BattlefieldAreaAssistant() {}

    public static boolean isEnabled() { return enabled; }
    public static int getVisibleMonsters() { return visibleMonsters; }
    public static String status() {
        return (enabled ? "BẬT" : "TẮT") + " - Quái " + visibleMonsters +
                " - Khu " + (lastArea + 1) +
                (awaitingAck ? " - Chờ server" : "");
    }

    public static void toggle() {
        if (enabled) {
            stop();
            return;
        }
        if (SuperToolEngine.activeMode() != ModeArbiter.IDLE) {
            GameCanvas.addInfoChar("[Chiến Trường] Hãy dừng Auto khác trước.");
            return;
        }
        if (GameCanvas.loadmap == null || GameScreen.player == null ||
                GameScreen.player.hp <= 0 ||
                !BattlefieldPolicy.isBattlefieldMap(GameCanvas.loadmap.idMap)) {
            GameCanvas.addInfoChar("[Chiến Trường] Hãy vào map chiến trường.");
            return;
        }
        enabled = true;
        originalMap = GameCanvas.loadmap.idMap;
        lastArea = LoadMap.Area;
        failures = visibleMonsters = 0;
        awaitingAck = false;
        lastAreaRequest = 0L;
        lastScan = 0L;
        emptySince = mSystem.currentTimeMillis();
        GameCanvas.addInfoChar("[Chiến Trường] Đang giám sát khu.");
    }

    public static void stop() {
        enabled = false;
        awaitingAck = false;
    }

    public static void tick(Player player) {
        if (!enabled) return;
        if (SuperToolEngine.activeMode() != ModeArbiter.IDLE) {
            stop();
            return;
        }
        if (player == null || player != GameScreen.player ||
                player.hp <= 0 || player.Action == 4 ||
                GameCanvas.loadmap == null ||
                !BattlefieldPolicy.isBattlefieldMap(GameCanvas.loadmap.idMap)) {
            stop();
            return;
        }
        long now = mSystem.currentTimeMillis();
        if (GameCanvas.loadmap.idMap != originalMap || LoadMap.Area != lastArea) {
            originalMap = GameCanvas.loadmap.idMap;
            lastArea = LoadMap.Area;
            awaitingAck = false;
            failures = 0;
            emptySince = now;
        }
        if (GameCanvas.currentScreen != GameCanvas.game ||
                GameCanvas.currentDialog != null ||
                GameCanvas.subDialog != null ||
                (GameCanvas.menu2 != null && GameCanvas.menu2.isShowMenu)) return;
        if (now - lastScan < 1250L) return;
        lastScan = now;
        int count = 0;
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject obj = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (obj != null && obj.typeObject == 1 && obj.hp > 0 &&
                    !obj.isDie && !obj.isRemove && obj.Action != 4 &&
                    !obj.isMonstervantieu()) count++;
        }
        visibleMonsters = count;
        if (count > 0) {
            emptySince = now;
            return;
        }
        if (now - emptySince < 45000L || LoadMap.MaxArea < 2) return;
        if (awaitingAck) {
            if (now - lastAreaRequest < 16000L) return;
            awaitingAck = false;
            failures++;
            if (BattlefieldPolicy.shouldStopAreaRetries(failures)) {
                stop();
                GameCanvas.addInfoChar("[Chiến Trường] Server không xác nhận đổi khu.");
                return;
            }
        }
        if (now - lastAreaRequest < 8000L) return;
        int next = BattlefieldPolicy.nextArea(LoadMap.Area, LoadMap.MaxArea);
        if (next < 0) return;
        lastAreaRequest = now;
        awaitingAck = true;
        GlobalService.gI().Change_Area((byte) next);
        GameCanvas.addInfoChar("[Chiến Trường] Thử chuyển sang khu " + (next+1));
    }
}
