package tools.core;

import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import tools.boss.BossController;
import tools.quest.QuestAssistant;
import tools.training.TrainingController;
import tools.training.TrainingSettings;
import tools.trading.TradeController;

/**
 * Central J2ME game-tick coordinator.
 * Only one controller can own movement and combat during a tick. Observation
 * (NPC discovery) may run without owning either. Keeps original game inputs
 * and inventory menu untouched.
 */
public final class SuperToolEngine {
    private static int currentOwner = ModeArbiter.IDLE;

    private SuperToolEngine() {}

    public static int activeMode() {
        return ModeArbiter.choose(TrainingSettings.isEnabled(),
                BossController.isEnabled(), TradeController.isRunning(),
                TradeController.isRecording());
    }

    public static String status() {
        return ModeArbiter.name(activeMode());
    }

    public static boolean isUserInterfaceBlocking() {
        return GameCanvas.currentScreen != GameCanvas.game ||
                GameCanvas.currentDialog != null ||
                GameCanvas.subDialog != null ||
                (GameCanvas.menu2 != null && GameCanvas.menu2.isShowMenu);
    }

    /** Used by the native Player.nextMonster override. */
    public static boolean onNextMonster(Player player) {
        if (player == null || player != GameScreen.player) return false;
        int mode = activeMode();
        if (mode == ModeArbiter.TRADING || mode == ModeArbiter.RECORDING)
            return true; // No auto monster selection during cargo transport.
        if (mode == ModeArbiter.TRAINING)
            return TrainingController.selectNextMonster(player);
        if (mode == ModeArbiter.BOSS)
            return BossController.selectNextMonster(player);
        return false;
    }

    public static void tick(Player player) {
        if (player == null || player != GameScreen.player ||
                GameCanvas.loadmap == null) return;
        // Observation is paced internally; it does not issue movement or attacks.
        QuestAssistant.observeNPCs();

        int owner = activeMode();
        if (ModeArbiter.mustStopTraining(owner) && TrainingSettings.isEnabled())
            TrainingController.stop("Đã chuyển sang chế độ khác.");
        if (ModeArbiter.mustStopBoss(owner) && BossController.isEnabled())
            BossController.stop("Đã chuyển sang Đi buôn.");

        owner = activeMode();
        currentOwner = owner;
        if (owner == ModeArbiter.TRADING || owner == ModeArbiter.RECORDING) {
            TradeController.tick(player);
        } else if (owner == ModeArbiter.BOSS) {
            BossController.tick(player);
        } else if (owner == ModeArbiter.TRAINING) {
            // Recovery state machine must keep running even after character
            // death or a map-load transition. Its own guards handle dialogs.
            TrainingController.tick(player);
        }
    }

    /** Stops only the modes that are currently active. */
    public static void stopAll() {
        if (TrainingSettings.isEnabled())
            TrainingController.stop("Đã dừng từ LyMeowz.");
        if (BossController.isEnabled())
            BossController.stop("Đã dừng từ LyMeowz.");
        if (TradeController.isRecording() || TradeController.isRunning())
            TradeController.stop();
        currentOwner = ModeArbiter.IDLE;
    }
}
