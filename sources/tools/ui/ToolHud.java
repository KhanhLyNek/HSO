package tools.ui;

import CLib.mFont;
import CLib.mGraphics;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import tools.core.ModeArbiter;
import tools.core.SuperToolEngine;

/**
 * Optional compact, paint-only in-game status widget.
 * Defaults to HIDDEN to preserve the original inventory/chat/skill UI.
 * It does not consume any pointer events.
 */
public final class ToolHud {
    private static boolean visible;
    private static int cachedPercent = -1;
    private static int cachedMode = -1;
    private static String caption = "";
    private static int cacheTick;

    private ToolHud() {}

    public static boolean isVisible() { return visible; }

    public static void toggle() {
        visible = !visible;
        cachedMode = -1;
    }

    public static String stateLabel() {
        return visible ? "BẬT" : "TẮT";
    }

    public static void paint(mGraphics graphics) {
        if (!visible || graphics == null || GameCanvas.currentScreen != GameCanvas.game ||
                GameCanvas.menu2 == null || GameCanvas.menu2.isShowMenu ||
                GameCanvas.currentDialog != null || GameCanvas.subDialog != null)
            return;
        try {
            Player player = GameScreen.player;
            if (player == null || GameCanvas.w < 140 || GameCanvas.h < 140)
                return;
            int owner = SuperToolEngine.activeMode();
            int hp = player.maxHp > 0 ? (int) ((long)player.hp * 100L / player.maxHp) : 0;
            if (hp < 0) hp = 0;
            if (hp > 100) hp = 100;
            // String construction only on mode/HP changes or once every ~25 ticks.
            if (cachedMode != owner || cachedPercent != hp || ++cacheTick >= 25) {
                cacheTick = 0;
                cachedMode = owner;
                cachedPercent = hp;
                String mode = owner == ModeArbiter.TRAINING ? "UP" :
                        owner == ModeArbiter.TRADING ? "BUÔN" :
                        owner == ModeArbiter.RECORDING ? "GHI" :
                        owner == ModeArbiter.BOSS ? "BOSS" : "NGHỈ";
                caption = "LyMeowz " + mode + " HP " + hp + "%";
            }
            // Center the tiny bar below the native top HUD, leaving bottom
            // touch controls, chat, minimap, inventory and skills unaffected.
            int barWidth = 136;
            int x = (GameCanvas.w - barWidth) / 2;
            int y = 51;
            graphics.setColor(0x18232e);
            graphics.fillRect(x, y, barWidth, 15, false);
            mFont.tahoma_7_white.drawString(graphics, caption,
                    GameCanvas.w / 2, y + 2, 2, false);
        } catch (Throwable ignored) {
            // Cosmetic HUD must never prevent the game from painting.
        }
    }
}
