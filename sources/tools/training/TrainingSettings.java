package tools.training;

/** Local-only training menu settings. No network packet or CAPTCHA modification. */
public final class TrainingSettings {
    private static boolean enabled;
    private static int minimumHp = 35;
    private static int maximumRange = 180;
    private static boolean returnAfterDeath = true;
    private TrainingSettings() {}
    public static boolean isEnabled() { return enabled; }
    public static boolean isReturnAfterDeath() { return returnAfterDeath; }
    public static void toggleReturnAfterDeath() { returnAfterDeath = !returnAfterDeath; }
    public static void toggle() { enabled = !enabled; }
    public static void stop() { enabled = false; }
    public static int getMinimumHp() { return minimumHp; }
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
