package tools.battlefield;

/**
 * Pure policy for HSO's battlefield-specific targets and zone retry budget.
 * Does not perform PK or choose real players as targets.
 */
public final class BattlefieldPolicy {
    private BattlefieldPolicy() {}

    public static boolean isBattlefieldMap(int mapId) {
        return mapId == 54 || mapId == 56 || mapId == 58 ||
                mapId == 60 || mapId == 61;
    }

    public static boolean isBattlefieldWaitingMap(int mapId) {
        return mapId == 53 || mapId == 55 || mapId == 57 || mapId == 59;
    }

    public static boolean isValidMonster(boolean alive, boolean ordinaryNpc,
                                         boolean tradeAnimal, boolean questVerification,
                                         boolean alliedBattlefieldObject) {
        return alive && !ordinaryNpc && !tradeAnimal &&
                !questVerification && !alliedBattlefieldObject;
    }

    public static int score(int level, int mobLevel, int distance, int hp,
                            int maxHp, boolean priorityBattlefield, boolean currentTarget) {
        if (hp <= 0 || maxHp <= 0 || distance < 0 || distance > 1600)
            return Integer.MIN_VALUE;
        int diff = mobLevel - level;
        if (diff > 12) return Integer.MIN_VALUE;
        int percent = (int) Math.min(100L, (long) hp * 100L / maxHp);
        int score = 10000 - distance * 6;
        if (diff > 0) score -= diff * 115;
        else if (diff < -18) score -= (-18 - diff) * 85;
        score += (100 - percent) * 4;
        if (priorityBattlefield) score += 1800;
        if (currentTarget) score += 350;
        return score;
    }

    public static boolean shouldSwitch(int chosen, int previous, long age) {
        return chosen != Integer.MIN_VALUE &&
                (previous == Integer.MIN_VALUE ||
                 age >= 2200L && chosen > previous + 200);
    }

    public static int nextArea(int area, int maxAreas) {
        if (maxAreas < 2 || maxAreas > 100 ||
                area < 0 || area >= maxAreas) return -1;
        return (area + 1) % maxAreas;
    }

    /** How many timed-out change requests should trigger a safe pause. */
    public static boolean shouldStopAreaRetries(int timeouts) {
        return timeouts >= 3;
    }
}
