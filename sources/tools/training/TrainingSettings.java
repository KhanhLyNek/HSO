package tools.training;

/**
 * Settings for the local, lightweight trainer. Unpersisted per-game-session
 * until the client confirms an appropriate RMS save format.
 */
public final class TrainingSettings {
    private static boolean enabled;
    private static int minimumHp = 35;
    private static int maximumRange = 180;
    private static boolean returnAfterDeath = true;
    private static int mode = TargetPolicy.SMART;
    private static boolean patrolEnabled = false;

    private TrainingSettings() {}

    public static boolean isEnabled() { return enabled; }
    public static boolean isReturnAfterDeath() { return returnAfterDeath; }
    public static void toggleReturnAfterDeath() { returnAfterDeath = !returnAfterDeath; }
    public static void toggle() { enabled = !enabled; }
    public static void stop() { enabled = false; }

    public static int getMode() { return mode; }
    public static void cycleMode() { mode = (mode + 1) % 3; }
    public static String modeName() {
        switch (mode) {
            case TargetPolicy.SAFE: return "An toàn";
            case TargetPolicy.CLASSIC: return "Cổ điển";
            default: return "Thông minh";
        }
    }

    public static boolean isPatrolEnabled() { return patrolEnabled; }
    public static void togglePatrol() { patrolEnabled = !patrolEnabled; }

    public static int getMinimumHp() { return minimumHp; }
    public static void cycleMinimumHp() {
        minimumHp = minimumHp == 25 ? 35 : minimumHp == 35 ? 50 : 25;
    }
    public static void setMinimumHp(int hp) {
        if (hp < 1 || hp > 100) throw new IllegalArgumentException("hp");
        minimumHp = hp;
    }
    public static int getMaximumRange() { return maximumRange; }
    public static void setMaximumRange(int range) {
        if (range < 20 || range > 500) throw new IllegalArgumentException("range");
        maximumRange = range;
    }
}
