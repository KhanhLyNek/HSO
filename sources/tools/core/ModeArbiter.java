package tools.core;

/**
 * Pure, allocation-free arbitration of the tool that owns movement/combat.
 * Trading, boss hunting and training must not issue actions concurrently.
 */
public final class ModeArbiter {
    public static final int IDLE = 0;
    public static final int TRAINING = 1;
    public static final int BOSS = 2;
    public static final int TRADING = 3;
    public static final int RECORDING = 4;

    private ModeArbiter() {}

    public static int choose(boolean training, boolean boss,
                             boolean tradeRunning, boolean tradeRecording) {
        if (tradeRunning) return TRADING;
        if (tradeRecording) return RECORDING;
        if (boss) return BOSS;
        if (training) return TRAINING;
        return IDLE;
    }

    public static boolean mustStopTraining(int owner) {
        return owner == BOSS || owner == TRADING || owner == RECORDING;
    }

    public static boolean mustStopBoss(int owner) {
        return owner == TRADING || owner == RECORDING;
    }

    public static String name(int owner) {
        switch (owner) {
            case TRAINING: return "Luyện cấp";
            case BOSS: return "Săn Boss";
            case TRADING: return "Đi buôn";
            case RECORDING: return "Ghi tuyến";
            default: return "Đã dừng";
        }
    }
}
