package tools.dungeon;

/** Pure CLDC-compatible scoring for defend-the-objective dungeon waves. */
public final class DefenseTargetPolicy {
    public static final int INVALID = -100000000;
    private DefenseTargetPolicy() {}

    public static int score(boolean alive, boolean hostile, boolean attackingObjective,
                            int distanceToObjective, int distanceToPlayer,
                            int pursuitLimit, int hp, int maxHp, int threatLevel) {
        if (!alive || !hostile || hp <= 0 || maxHp <= 0 ||
                distanceToObjective < 0 || distanceToPlayer < 0 ||
                pursuitLimit < 0 || distanceToPlayer > pursuitLimit) return INVALID;
        int threat = threatLevel < 0 ? 0 : (threatLevel > 100 ? 100 : threatLevel);
        int objectiveDistance = distanceToObjective > 2000 ? 2000 : distanceToObjective;
        int playerDistance = distanceToPlayer > 2000 ? 2000 : distanceToPlayer;
        int hpPercent = (int)((long)hp * 100L / maxHp);
        return (attackingObjective ? 20000 : 0) +
                (objectiveDistance <= 120 ? 8000 : 0) +
                threat * 40 + (100 - hpPercent) * 12 -
                objectiveDistance * 8 - playerDistance * 3;
    }

    public static boolean emergency(int objectiveHp, int objectiveMaxHp) {
        return objectiveMaxHp > 0 && objectiveHp >= 0 &&
                (long)objectiveHp * 100L <= (long)objectiveMaxHp * 30L;
    }

    public static boolean shouldSwitch(int candidateScore, int currentScore,
                                       boolean candidateAttacksObjective) {
        if (candidateScore == INVALID) return false;
        if (currentScore == INVALID) return true;
        return candidateScore > currentScore +
                (candidateAttacksObjective ? 0 : 600);
    }
}
