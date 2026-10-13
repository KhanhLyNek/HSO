package tools.training;

public final class PatrolBrainTest {
    private static void check(boolean b, String s) {
        if (!b) throw new AssertionError(s);
    }

    public static void main(String[] args) {
        PatrolBrain brain = new PatrolBrain(6);
        check(brain.next(0, 1, 10000L) == 0, "one point");
        check(brain.next(0, 3, 10000L) == 1, "unknown points explored sequentially");
        for (int i = 0; i < 4; i++) {
            brain.observe(2, 6);
            brain.observe(1, 0);
        }
        check(brain.next(0, 3, 10000L) == 2, "choose productive monster hotspot");
        brain.markObstacle(2);
        brain.markObstacle(2);
        check(brain.next(0, 3, 11000L) == 1, "avoid stuck waypoint");
        brain.clear();
        check(brain.getDensity(2) == 0, "clear learned weights");
        brain.markVisit(1, 20000L);
        check(brain.next(0, 3, 20001L) == 2, "avoid returning too soon");
        check(brain.next(0, 3, 30000L) == 1, "revisit after cooldown");
        System.out.println("PatrolBrainTest: ALL PASS");
    }
}
