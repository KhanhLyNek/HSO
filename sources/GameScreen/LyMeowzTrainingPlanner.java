package GameScreen;

/**
 * Lightweight and deterministic safe-target scoring for CLDC 1.0.
 * Works on already-visible monsters; no unverified cross-map spawn guesses.
 */
public final class LyMeowzTrainingPlanner {
    private LyMeowzTrainingPlanner() {}

    public static int healthPercent(int hp, int maxHp) {
        if (maxHp <= 0 || hp <= 0) return 0;
        if (hp >= maxHp) return 100;
        return (int) (((long) hp * 100L) / maxHp);
    }

    public static boolean needsRecovery(int hp, int maxHp, int mp, int maxMp) {
        // Avoid choosing fresh combat targets while critically low on HP/MP.
        return (maxHp > 0 && healthPercent(hp, maxHp) < 25) ||
                (maxMp > 0 && healthPercent(mp, maxMp) < 10);
    }

    /**
     * Lower score is preferable. Distant/crowded/underleveled targets lose.
     * Quest filtering and range checks remain game-specific in Player.
     */
    public static int score(int distanceWithCrowdingPenalty,
                            int playerLevel, int monsterLevel,
                            int monsterHp, int monsterMaxHp) {
        if (monsterHp <= 0 || playerLevel < monsterLevel ||
                distanceWithCrowdingPenalty < 0) return Integer.MAX_VALUE;
        int difference = playerLevel - monsterLevel;
        if (difference < 0) return Integer.MAX_VALUE;
        int score = distanceWithCrowdingPenalty;
        // Favor monsters close to the player's level unless objectives force
        // a lower-level mob; only relative to other eligible candidates.
        int levelPenalty = difference > 5 ? 90 : difference * 10;
        int hpPenalty = (monsterMaxHp > 0 && monsterHp > monsterMaxHp / 2)
                ? 0 : 8;
        if (score > Integer.MAX_VALUE - levelPenalty - hpPenalty)
            return Integer.MAX_VALUE;
        return score + levelPenalty + hpPenalty;
    }

    /** Crowd penalty per competing player, capped to avoid overflow. */
    public static int crowdPenalty(int radius, int otherPlayers) {
        if (radius <= 0 || otherPlayers <= 0) return 0;
        int capped = otherPlayers > 4 ? 4 : otherPlayers;
        return (radius / 4) * capped;
    }
}
