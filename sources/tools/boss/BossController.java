package tools.boss;

import CLib.mSystem;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import tools.training.TrainingController;
import tools.training.TrainingSettings;
import tools.trading.TradeController;

/**
 * Local, opt-in boss scanner. Only targets a server-confirmed boss visible
 * within the same map. No teleport guesses, auto-PK or CAPTCHA handling.
 */
public final class BossController {
    private static boolean enabled;
    private static int radius = 240;
    private static long lastScan;
    private static String playerName = "";
    private static int mapId = -1;
    private static int targetId = -1;
    private static int spotted;

    private BossController() {}

    private static void notice(String s) { GameCanvas.addInfoChar("[Săn Boss] " + s); }

    public static boolean isEnabled() { return enabled; }
    public static int getRadius() { return radius; }
    public static void cycleRadius() { radius = radius == 160 ? 240 : radius == 240 ? 320 : 160; }
    public static int getSpotted() { return spotted; }
    public static String status() {
        return (enabled ? "Đang quét" : "Đã tắt") + " - Bán kính " + radius +
                " - Boss phát hiện: " + spotted + (targetId >= 0 ? " - ID " + targetId : "");
    }

    public static void toggle() {
        if (enabled) {
            stop("Đã dừng săn Boss.");
            return;
        }
        Player p = GameScreen.player;
        if (p == null || p.Action == 4 || p.hp <= 0 || GameCanvas.loadmap == null) {
            notice("Cần nhân vật đang sống trên bản đồ.");
            return;
        }
        if (TradeController.isRecording() || TradeController.isRunning()) {
            notice("Hãy dừng Đi buôn trước.");
            return;
        }
        if (TrainingSettings.isEnabled()) TrainingController.stop("Đã chuyển sang Săn Boss.");
        enabled = true;
        playerName = p.name;
        mapId = GameCanvas.loadmap.idMap;
        targetId = -1;
        spotted = 0;
        lastScan = 0;
        Player.isAutoFire = (byte) -1;
        Player.isCurAutoFire = false;
        notice("Đã bật quét boss gần đây. Không tự chuyển bản đồ.");
    }

    public static void stop(String reason) {
        if (!enabled) return;
        enabled = false;
        targetId = -1;
        Player.isAutoFire = (byte) -1;
        Player.isCurAutoFire = false;
        notice(reason);
    }

    public static boolean selectNextMonster(Player p) {
        if (!enabled) return false;
        lastScan = 0;
        tick(p);
        return true;
    }

    public static void tick(Player p) {
        if (!enabled) return;
        if (p == null || !playerName.equals(p.name) || p.hp <= 0 || p.Action == 4) {
            stop("Nhân vật không còn sẵn sàng.");
            return;
        }
        if (GameCanvas.loadmap == null || GameCanvas.loadmap.idMap != mapId) {
            stop("Đã thay đổi bản đồ.");
            return;
        }
        if (GameCanvas.currentScreen != GameCanvas.game ||
                GameCanvas.currentDialog != null || GameCanvas.subDialog != null ||
                GameCanvas.menu2.isShowMenu) {
            Player.isAutoFire = (byte) -1;
            return;
        }
        int percent = p.maxHp > 0 ? (int) ((long)p.hp * 100L / p.maxHp) : 0;
        if (percent < 45) {
            Player.isAutoFire = (byte) -1;
            return;
        }
        if (mSystem.currentTimeMillis() - lastScan < 1000L) return;
        lastScan = mSystem.currentTimeMillis();
        MainObject best = null;
        int bestScore = Integer.MIN_VALUE;
        int found = 0;
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject mob = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (mob == null || mob.typeObject != 1 || mob.typeBoss == 2 ||
                    mob.isMonstervantieu() || mob.isMiNuong() || mob.isLuaThieng() ||
                    mob.isSend || mob.isDie || mob.Action == 4 || mob.hp <= 0 ||
                    !(mob.typeBoss == 1 || mob.isBossNew())) continue;
            int distance = MainObject.getDistance(p.x, p.y, mob.x, mob.y);
            if (distance > radius || distance > p.wFocus * 3 / 2) continue;
            found++;
            // Prefer an already engaged boss, then nearby ones.
            int score = 5000 - distance * 5 + (mob.ID == targetId ? 350 : 0);
            if (score > bestScore) { best = mob; bestScore = score; }
        }
        spotted = found;
        if (best == null) {
            targetId = -1;
            Player.isAutoFire = (byte) -1;
            return;
        }
        targetId = best.ID;
        GameScreen.ObjFocus = best;
        if (Player.isAutoFire != 1) p.dofire_();
    }
}
