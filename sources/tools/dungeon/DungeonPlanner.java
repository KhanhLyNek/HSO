package tools.dungeon;

/**
 * Conservative dungeon decision engine for CLDC/J2ME.
 * This class never sends network commands. Integration must supply verified
 * observations and execute only actions supported by the native game client.
 */
public final class DungeonPlanner {
    public static final int WAIT = 0;
    public static final int ATTACK = 1;
    public static final int RETREAT = 2;
    public static final int HEAL = 3;
    public static final int DODGE = 4;
    public static final int FINISH = 5;
    public static final int ABORT = 6;

    public static final int PREPARE = 0;
    public static final int CLEAR = 1;
    public static final int BOSS = 2;
    public static final int COMPLETE = 3;

    private int phase = PREPARE;
    private int failures;
    private long lastDecisionAt;

    public int phase() { return phase; }
    public void reset() { phase = PREPARE; failures = 0; lastDecisionAt = 0L; }

    /** Fail closed on uncertain or stale observations. */
    public int decide(boolean insideDungeon, boolean verified, boolean dialogOpen,
                      boolean alive, int hp, int maxHp, int healingItems,
                      boolean incomingDanger, boolean targetVisible,
                      boolean bossVisible, boolean exitConfirmed,
                      long now) {
        if (!insideDungeon || !verified || dialogOpen || now < 0L) return WAIT;
        if (!alive || maxHp <= 0 || hp < 0 || hp > maxHp) return ABORT;
        if (exitConfirmed) { phase = COMPLETE; return FINISH; }
        if (lastDecisionAt > 0 && now - lastDecisionAt < 250L) return WAIT;
        lastDecisionAt = now;
        if (incomingDanger) return DODGE;
        if ((long)hp * 100L <= (long)maxHp * 35L) {
            if (healingItems > 0) return HEAL;
            return RETREAT;
        }
        if (bossVisible) phase = BOSS;
        else if (phase == PREPARE) phase = CLEAR;
        if (targetVisible) return ATTACK;
        return WAIT;
    }

    public boolean recordNavigationFailure() {
        failures++;
        return failures >= 3;
    }

    public void clearNavigationFailures() { failures = 0; }
}
