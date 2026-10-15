import GameScreen.LyMeowzMapReader;
import GameScreen.LyMeowzMapTransitions;
import GameScreen.LyMeowzNpcMapRegistry;

/** JVM smoke tests for CLDC-safe navigation data structures. */
public final class LyMeowzNavigationTest {
    private static void check(boolean valid, String message) {
        if (!valid) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        check(LyMeowzNpcMapRegistry.findMap(420) == -1, "unknown NPC");
        LyMeowzNpcMapRegistry.remember(420, 20);
        check(LyMeowzNpcMapRegistry.findMap(420) == 20, "remember NPC map");
        LyMeowzNpcMapRegistry.remember(420, 30);
        check(LyMeowzNpcMapRegistry.findMap(420) == 30, "update NPC map");
        LyMeowzNpcMapRegistry.remember(-1, 10);
        check(LyMeowzNpcMapRegistry.findMap(-1) == -1, "ignore invalid NPC");

        // Graph edges only become available after an actual server map transition.
        check(LyMeowzMapTransitions.nextGate(10, 30) == null, "no invented edge");
        LyMeowzMapTransitions.remember(10, 20, 240, 120);
        LyMeowzMapTransitions.remember(20, 30, 360, 60);
        LyMeowzMapTransitions.remember(30, 10, 100, 150);
        int[] first = LyMeowzMapTransitions.nextGate(10, 30);
        check(first != null && first.length == 3, "two-hop route");
        check(first[0] == 240 && first[1] == 120 && first[2] == 20,
                "first gate/next map, not the final destination");
        check(LyMeowzMapTransitions.nextGate(10, 9999) == null, "unlearned map");
        check(LyMeowzMapTransitions.nextGate(10, 10) == null, "same map");
        LyMeowzMapTransitions.remember(10, 20, 252, 132);
        first = LyMeowzMapTransitions.nextGate(10, 30);
        check(first[0] == 252 && first[1] == 132, "updated gate coordinates");
        check(LyMeowzMapTransitions.nextGate(30, 20) != null, "cycle-safe BFS");

        int[] tiles = {0,1,0, 0,1,0, 0,0,0};
        short[] path = LyMeowzMapReader.route(tiles, 3, 3, 0, 0, 2, 0, 20);
        check(path.length == 6, "detour");
        check(path[0] == 1, "first step must be (0,1)");
        check(path[path.length - 1] == (short)(2 << 8), "last step target (2,0)");
        check(LyMeowzMapReader.route(tiles,3,3,0,0,2,0,4).length == 0, "step limit");
        check(LyMeowzMapReader.route(tiles,3,3,0,0,1,0,20).length == 0, "blocked target");
        check(LyMeowzMapReader.route(tiles,3,3,-1,0,2,0,20).length == 0, "bounds");
        System.out.println("LyMeowz navigation / NPC registry / map-graph tests: PASS");
    }
}
