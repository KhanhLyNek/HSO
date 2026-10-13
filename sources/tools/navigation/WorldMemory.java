package tools.navigation;

/**
 * Bounded world graph learned from *confirmed* server map transitions.
 * Never manufactures map IDs or assumes an unknown gateway's destination.
 * Keeps learned edges only for the current process.
 */
public final class WorldMemory {
    public static final int MAX_MAPS = 64;
    public static final int MAX_EDGES = 180;
    private final int[] mapIds = new int[MAX_MAPS];
    private final String[] names = new String[MAX_MAPS];
    private final int[] mobLevel = new int[MAX_MAPS];
    private final int[] mobs = new int[MAX_MAPS];
    private final int[] visitCount = new int[MAX_MAPS];
    private final int[] from = new int[MAX_EDGES];
    private final int[] to = new int[MAX_EDGES];
    private final int[] gateX = new int[MAX_EDGES];
    private final int[] gateY = new int[MAX_EDGES];
    private int nodes, edges;

    public void clear() {
        nodes = edges = 0;
    }

    private int index(int id) {
        for (int i = 0; i < nodes; i++) if (mapIds[i] == id) return i;
        return -1;
    }

    private int ensure(int id) {
        int i = index(id);
        if (i >= 0) return i;
        if (nodes == MAX_MAPS) return -1;
        mapIds[nodes] = id;
        mobLevel[nodes] = -1;
        names[nodes] = "";
        return nodes++;
    }

    public void visit(int id, String mapName) {
        int i = ensure(id);
        if (i < 0) return;
        if (mapName != null) names[i] = mapName;
        if (visitCount[i] < 1000) visitCount[i]++;
    }

    public int visitsByName(String text) {
        if (text == null) return 0;
        String target = text.trim().toLowerCase();
        if (target.length() == 0) return 0;
        for (int i = 0; i < nodes; i++) {
            if (names[i] != null && names[i].trim().toLowerCase().equals(target))
                return visitCount[i];
        }
        return 0;
    }

    public int visits(int id) {
        int i = index(id);
        return i < 0 ? 0 : visitCount[i];
    }

    public void observeMonsters(int id, int level, int count) {
        int i = ensure(id);
        if (i < 0 || level <= 0 || count < 1) return;
        mobLevel[i] = mobLevel[i] < 0 ? level : (mobLevel[i] * 3 + level) / 4;
        mobs[i] = mobs[i] < 1 ? count : (mobs[i] * 3 + count) / 4;
    }

    public void connect(int fromId, int toId, int px, int py) {
        if (fromId == toId || ensure(fromId) < 0 || ensure(toId) < 0) return;
        for (int i = 0; i < edges; i++) {
            if (from[i] == fromId && to[i] == toId) {
                gateX[i] = px;
                gateY[i] = py;
                return;
            }
        }
        if (edges == MAX_EDGES) return;
        from[edges] = fromId;
        to[edges] = toId;
        gateX[edges] = px;
        gateY[edges] = py;
        edges++;
    }

    /** First known direct neighbor toward target, -1 if graph has no route. */
    public int nextMap(int startId, int goalId) {
        int start = index(startId), goal = index(goalId);
        if (start < 0 || goal < 0 || start == goal) return -1;
        int[] distance = new int[MAX_MAPS], previous = new int[MAX_MAPS];
        int[] queue = new int[MAX_MAPS];
        for (int i = 0; i < nodes; i++) { distance[i] = -1; previous[i] = -1; }
        int head = 0, tail = 0;
        queue[tail++] = start;
        distance[start] = 0;
        while (head < tail) {
            int cur = queue[head++];
            for (int i = 0; i < edges; i++) {
                if (from[i] != mapIds[cur]) continue;
                int n = index(to[i]);
                if (n < 0 || distance[n] >= 0) continue;
                distance[n] = distance[cur] + 1;
                previous[n] = cur;
                queue[tail++] = n;
            }
        }
        if (distance[goal] < 0) return -1;
        int n = goal;
        while (previous[n] != start) {
            n = previous[n];
            if (n < 0) return -1;
        }
        return mapIds[n];
    }

    public int edgeX(int source, int destination) {
        for (int i = 0; i < edges; i++)
            if (from[i] == source && to[i] == destination) return gateX[i];
        return -1;
    }
    public int edgeY(int source, int destination) {
        for (int i = 0; i < edges; i++)
            if (from[i] == source && to[i] == destination) return gateY[i];
        return -1;
    }

    public int bestKnownLevelMap(int currentId, int playerLevel) {
        int best = -1, bestScore = -100000;
        for (int i = 0; i < nodes; i++) {
            if (mobLevel[i] < 0 || mapIds[i] == currentId ||
                    nextMap(currentId, mapIds[i]) < 0) continue;
            int diff = Math.abs(playerLevel - mobLevel[i]);
            if (diff > 8) continue;
            int score = mobs[i] * 35 - diff * 120 -
                    visitCount[i] * 5;
            if (score > bestScore) {bestScore = score; best = mapIds[i];}
        }
        return best;
    }

    public int knownMaps() { return nodes; }
    public int knownEdges() { return edges; }
}
