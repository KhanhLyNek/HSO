package tools.dungeon;

public final class DungeonPlannerTest {
    private static void check(boolean ok, String message) {
        if (!ok) throw new RuntimeException(message);
    }

    public static void main(String[] args) {
        DungeonPlanner p = new DungeonPlanner();
        check(p.decide(true, false, false, true, 100, 100, 2,
                false, true, false, false, 1000) == DungeonPlanner.WAIT, "unverified");
        check(p.decide(true, true, false, true, 20, 100, 2,
                false, true, false, false, 2000) == DungeonPlanner.HEAL, "heal");
        check(p.decide(true, true, false, true, 20, 100, 0,
                false, true, false, false, 3000) == DungeonPlanner.RETREAT, "retreat");
        check(p.decide(true, true, false, true, 90, 100, 0,
                true, true, true, false, 4000) == DungeonPlanner.DODGE, "dodge");
        check(p.decide(true, true, false, true, 90, 100, 0,
                false, true, true, false, 5000) == DungeonPlanner.ATTACK, "attack");
        check(p.phase() == DungeonPlanner.BOSS, "boss phase");
        check(p.decide(true, true, true, true, 90, 100, 0,
                false, true, true, false, 6000) == DungeonPlanner.WAIT, "dialog");
        check(p.decide(true, true, false, true, 90, 100, 0,
                false, false, false, true, 7000) == DungeonPlanner.FINISH, "finish");
        check(!p.recordNavigationFailure(), "failure 1");
        check(!p.recordNavigationFailure(), "failure 2");
        check(p.recordNavigationFailure(), "failure 3");
        System.out.println("DungeonPlannerTest OK");
    }
}
