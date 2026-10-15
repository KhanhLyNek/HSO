import GameScreen.LyMeowzMapReader;
import GameScreen.LyMeowzMapTransitions;
import GameScreen.LyMeowzNpcMapRegistry;
import GameScreen.LyMeowzWorldStore;

/** JVM smoke tests for CLDC-safe navigation data structures. */
public final class LyMeowzNavigationTest {
    private static void check(boolean valid, String message) {
        if (!valid) throw new AssertionError(message);
    }

    public static void main(String[] args) throws Exception {
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
        // Guard against false teleport->gate associations.
        LyMeowzMapTransitions.clear();
        check(!LyMeowzMapTransitions.confirmedTransition(11, 3, 12, 4, 241, 120, 1000L),
                "teleport cannot teach without armed gate");
        LyMeowzMapTransitions.armGate(11, 3, 240, 120, 1000L);
        check(LyMeowzMapTransitions.confirmedTransition(11, 3, 12, 4, 244, 123, 1100L),
                "server transition from approached gate");
        int[] areaHop = LyMeowzMapTransitions.nextGate(11, 3, 12, 4);
        check(areaHop != null && areaHop.length == 4 && areaHop[2] == 12 && areaHop[3] == 4,
                "destination map AND area");
        check(LyMeowzMapTransitions.nextGate(11, 2, 12, 4) == null,
                "wrong origin area must not use portal");
        LyMeowzMapTransitions.armGate(12, 4, 100, 100, 1000L);
        check(!LyMeowzMapTransitions.confirmedTransition(12, 4, 13, 1, 100, 100, 21001L),
                "expired gate arm must not learn");
        LyMeowzNpcMapRegistry.remember(420, 12, 4);
        check(LyMeowzNpcMapRegistry.findArea(420) == 4, "NPC area recorded");
        byte[] saved = LyMeowzWorldStore.encode();
        LyMeowzMapTransitions.clear();
        LyMeowzNpcMapRegistry.clear();
        check(LyMeowzMapTransitions.nextGate(11, 3, 12, 4) == null, "cleared graph");
        LyMeowzWorldStore.decode(saved);
        check(LyMeowzNpcMapRegistry.findMap(420) == 12 &&
                LyMeowzNpcMapRegistry.findArea(420) == 4, "persist NPC location");
        check(LyMeowzMapTransitions.nextGate(11, 3, 12, 4) != null,
                "persist confirmed gate");
        byte[] bad = new byte[] {0,1,2,3,4,5,6,7,8,9,10};
        boolean invalid = false;
        try { LyMeowzWorldStore.decode(bad); }
        catch (java.io.IOException expected) { invalid = true; }
        check(invalid, "reject malformed RMS cache");
        System.out.println("LyMeowz navigation / NPC registry / map-graph tests: PASS");
    }
}
