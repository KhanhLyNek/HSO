package tools.training;

/**
 * Deterministic, allocation-free target scoring for low-memory J2ME devices.
 * This is an on-device heuristic policy, not a network AI model.
 */
public final class TargetPolicy {
    public static final int SMART = 0;
    public static final int SAFE = 1;
    public static final int CLASSIC = 2;
    public static final int INVALID = -100000000;

    private TargetPolicy() {}

    public static int score(int mode, int playerLevel, int mobLevel,
                            int distanceFromPlayer, int distanceFromCamp,
                            int maximumRange, int hp, int maxHp,
                            boolean normalMonster, boolean captchaOrDead) {
        if (!normalMonster || captchaOrDead || distanceFromPlayer < 0 ||
                distanceFromCamp > maximumRange || distanceFromPlayer > maximumRange ||
                hp <= 0 || maxHp <= 0) return INVALID;
        if (mode == CLASSIC) {
            return 4000 - distanceFromPlayer * 8;
        }
        int diff = mobLevel - playerLevel;
        if (mode == SAFE && diff > 2) return INVALID;
        if (mode == SMART && diff > 7) return INVALID;
        if (diff < -12) return INVALID;
        int score = 12000 - distanceFromPlayer * (mode == SAFE ? 9 : 6);
        score -= Math.abs(diff) * (mode == SAFE ? 110 : 55);
        if (diff > 0) score -= diff * (mode == SAFE ? 250 : 90);
        // Finish weakened targets quickly; less downtime between monsters.
        int healthPercent = (int) ((long) hp * 100L / maxHp);
        if (healthPercent < 0) healthPercent = 0;
        if (healthPercent > 100) healthPercent = 100;
        score += (100 - healthPercent) * 2;
        return score;
    }

    public static boolean shouldSwitch(int selectedScore, int currentScore,
                                       long millisecondsOnCurrent) {
        if (selectedScore == INVALID) return false;
        if (currentScore == INVALID) return true;
        // Stick with a valid enemy for 2.5s; require a meaningful upgrade.
        return millisecondsOnCurrent >= 2500L && selectedScore > currentScore + 250;
    }
}
