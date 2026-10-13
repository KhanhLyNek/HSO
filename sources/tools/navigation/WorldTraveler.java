package tools.navigation;

import CLib.mSystem;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.Point;
import Thread_More.LoadMap;

/**
 * Explores map exits and replays confirmed world connections.
 * Restricts exploration to ordinary maps and a small transition budget.
 */
public final class WorldTraveler {
    public static final int FAILED = -1;
    public static final int MOVING = 0;
    public static final int ARRIVED = 1;
    public static final int NO_ROUTE = 2;
    private static final WorldMemory memory = new WorldMemory();
    private static int previousMap = -1, previousX = -1, previousY = -1;
    private static int currentTarget = -1, currentGateX = -1, currentGateY = -1;
    private static int currentGateExpected = -1, transitions;
    private static long lastGateProgress, lastMessage;
    private static boolean awaitingTransition, gateTouchSent;
    private static int lastMapSeen = -1, currentArea = -1;

    private WorldTraveler() {}

    public static void onMapLeaving() {
        if (GameCanvas.loadmap == null || GameScreen.player == null) return;
        previousMap = GameCanvas.loadmap.idMap;
        previousX = previousY = -1;
        int nearest = 200;
        for (int i = 0; i < LoadMap.vecPointChange.size(); i++) {
            Point gate = (Point) LoadMap.vecPointChange.elementAt(i);
            if (gate == null) continue;
            int dist = MainObject.getDistance(GameScreen.player.x, GameScreen.player.y,
                    gate.x, gate.y);
            if (dist < nearest) {
                nearest = dist;
                previousX = gate.x;
                previousY = gate.y;
            }
        }
    }

    public static void onMapChanged() {
        if (GameCanvas.loadmap == null) return;
        int current = GameCanvas.loadmap.idMap;
        memory.visit(current, GameCanvas.loadmap.nameMap);
        if (previousMap >= 0 && previousMap != current &&
                previousX >= 0 && previousY >= 0) {
            memory.connect(previousMap, current, previousX, previousY);
        }
        if (lastMapSeen != current) {
            if (awaitingTransition) transitions++;
            awaitingTransition = false;
            gateTouchSent = false;
            currentTarget = -1;
            currentGateX = currentGateY = -1;
            lastMapSeen = current;
        }
        currentArea = LoadMap.Area;
    }

    public static void resetJourney() {
        transitions = 0;
        awaitingTransition = false;
        gateTouchSent = false;
        currentTarget = currentGateX = currentGateY = -1;
    }

    public static void observeMonsters(int averageLevel, int count) {
        if (GameCanvas.loadmap != null && count > 0)
            memory.observeMonsters(GameCanvas.loadmap.idMap, averageLevel, count);
    }

    public static int suggestedMap(int level) {
        return GameCanvas.loadmap == null ? -1 :
                memory.bestKnownLevelMap(GameCanvas.loadmap.idMap, level);
    }

    public static String knownMapName(int mapId) { return memory.nameOf(mapId); }

    public static int edgesLearned() { return memory.knownEdges(); }
    public static int mapsLearned() { return memory.knownMaps(); }
    public static int journeyHops() { return transitions; }

    public static String status() {
        return "Đã học " + memory.knownMaps() + " bản đồ, " +
                memory.knownEdges() + " đường nối, " + transitions + " lượt qua cổng.";
    }

    private static Point gateAt(int x, int y) {
        if (x < 0 || y < 0) return null;
        Point found = null;
        int near = 120;
        for (int i = 0; i < LoadMap.vecPointChange.size(); i++) {
            Point p = (Point) LoadMap.vecPointChange.elementAt(i);
            if (p == null) continue;
            int d = MainObject.getDistance(p.x, p.y, x, y);
            if (d < near) {near = d; found = p;}
        }
        return found;
    }

    private static boolean named(Point point, String name) {
        if (point == null || point.name == null || name == null ||
                name.length() < 2) return false;
        String gateName = point.name.trim().toLowerCase();
        String target = name.trim().toLowerCase();
        return gateName.equals(target) || gateName.indexOf(target) >= 0 ||
                target.indexOf(gateName) >= 0 && gateName.length() >= 4;
    }

    /**
     * Auto recover: only use confirmed edges or a gate explicitly named after
     * the original map, to avoid losing the player's saved recovery position.
     */
    private static int goToGate(Player p) {
        if (p == null || GameCanvas.loadmap == null) return FAILED;
        int d = MainObject.getDistance(p.x,p.y,currentGateX,currentGateY);
        if (d > 38) {
            int nav = SmartNavigator.approach(p,currentGateX,currentGateY,28);
            return nav == SmartNavigator.FAILED ? FAILED : MOVING;
        }
        if (!gateTouchSent && p.posTransRoad == null && d > 5) {
            // Final short step to the actual server gate point, not merely
            // the nearest tile center. Stop sending after this single request.
            int tx = p.x / LoadMap.wTile, ty = p.y / LoadMap.wTile;
            if (tx < 128 && ty < 128 && tx >= 0 && ty >= 0) {
                p.toX = p.x;
                p.toY = p.y;
                p.xStopMove = currentGateX;
                p.yStopMove = currentGateY;
                p.countAutoMove = 0;
                p.posTransRoad = new short[]{(short)((tx<<8)|ty)};
                gateTouchSent = true;
            }
        }
        return MOVING; // only a server-confirmed map transition counts
    }

    public static int moveToMap(Player p, int wantedMap, String wantedName) {
        if (GameCanvas.loadmap == null) return NO_ROUTE;
        if (GameCanvas.loadmap.idMap == wantedMap) {
            awaitingTransition = false;
            return ARRIVED;
        }
        if (LoadMap.typeMap != LoadMap.MAP_NORMAL || transitions > 9) return NO_ROUTE;
        if (!awaitingTransition || currentTarget != wantedMap) {
            int next = memory.nextMap(GameCanvas.loadmap.idMap, wantedMap);
            Point gate = next < 0 ? null : gateAt(
                    memory.edgeX(GameCanvas.loadmap.idMap, next),
                    memory.edgeY(GameCanvas.loadmap.idMap, next));
            if (gate == null) {
                for (int i = 0; i < LoadMap.vecPointChange.size(); i++) {
                    Point test = (Point) LoadMap.vecPointChange.elementAt(i);
                    if (named(test, wantedName)) {gate = test; break;}
                }
            }
            if (gate == null) return NO_ROUTE;
            currentGateX = gate.x;
            currentGateY = gate.y;
            currentTarget = wantedMap;
            awaitingTransition = true;
            gateTouchSent = false;
            lastGateProgress = mSystem.currentTimeMillis();
        }
        if (mSystem.currentTimeMillis() - lastGateProgress > 18000L) {
            awaitingTransition = false;
            return FAILED;
        }
        return goToGate(p);
    }

    /**
     * Bounded exploration for auto-find-training. Only follows non-empty gate
     * labels and prefers destinations whose names are not recently revisited.
     */
    public static int exploreNextMap(Player p) {
        if (GameCanvas.loadmap == null || LoadMap.typeMap != 0 ||
                transitions >= 6 || LoadMap.vecPointChange.size() == 0) return NO_ROUTE;
        if (!awaitingTransition) {
            Point chosen = null;
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < LoadMap.vecPointChange.size(); i++) {
                Point gate = (Point) LoadMap.vecPointChange.elementAt(i);
                if (gate == null || gate.name == null || gate.name.length() == 0) continue;
                // Prefer a nearby gateway; don't assume server map IDs from text.
                int dist = MainObject.getDistance(p.x, p.y, gate.x, gate.y);
                int visits = memory.visitsByName(gate.name);
                int score = dist + visits * 2500;
                if (score < best) {best = score; chosen = gate;}
            }
            if (chosen == null) return NO_ROUTE;
            currentTarget = -2;
            gateTouchSent = false;
            currentGateX = chosen.x;
            currentGateY = chosen.y;
            awaitingTransition = true;
            lastGateProgress = mSystem.currentTimeMillis();
        }
        if (mSystem.currentTimeMillis() - lastGateProgress > 18000L) {
            awaitingTransition = false;
            return FAILED;
        }
        return goToGate(p);
    }
}
