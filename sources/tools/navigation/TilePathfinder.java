package tools.navigation;

/**
 * Bounded, deterministic breadth-first pathfinder over server tile collisions.
 * Returns forward encoded [x<<8 | y] waypoints for Player.posTransRoad.
 * Main client decodes x and y with signed bytes, so tiles >=128 are rejected.
 * No game-engine, server or Java SE dependencies.
 */
public final class TilePathfinder {
    private static final int MAX_TILES = 16384;
    private final short[] parent = new short[MAX_TILES];
    private final short[] queue = new short[MAX_TILES];
    private final short[] reverse = new short[MAX_TILES];
    private int generation;
    private final short[] seen = new short[MAX_TILES];

    public static final class Route {
        public short[] steps;
        public int goalX;
        public int goalY;
        public boolean complete;
    }

    private TilePathfinder() {}
    private static final TilePathfinder SHARED = new TilePathfinder();
    public static TilePathfinder shared() { return SHARED; }

    private static boolean passable(int[] tiles, int w, int h, int x, int y) {
        return x >= 0 && y >= 0 && x < w && y < h &&
                tiles[y * w + x] != 1 && tiles[y * w + x] != -1;
    }

    /** Return up to 50 forward steps, or null when no safe path exists. */
    public synchronized Route route(int[] tiles, int width, int height,
                                    int startX, int startY, int goalX, int goalY,
                                    int maxSteps, int goalTolerance) {
        if (tiles == null || width <= 0 || height <= 0 || width > 127 ||
                height > 127 || width * height > MAX_TILES ||
                tiles.length < width * height || maxSteps < 2 || maxSteps > 70 ||
                !passable(tiles,width,height,startX,startY))
            return null;
        if (goalX < 0 || goalY < 0 || goalX >= width || goalY >= height)
            return null;
        int originalX = goalX, originalY = goalY;
        int goal = goalY * width + goalX;
        int start = startY * width + startX;
        if (start == goal) {
            Route done = new Route();
            done.steps = new short[]{ (short) ((startX << 8) | startY) };
            done.goalX = goalX;
            done.goalY = goalY;
            done.complete = true;
            return done;
        }
        if (++generation == 32767) {
            for (int i = 0; i < seen.length; i++) seen[i] = 0;
            generation = 1;
        }
        short gen = (short) generation;
        int head = 0, tail = 0;
        queue[tail++] = (short) start;
        seen[start] = gen;
        parent[start] = -1;
        int found = -1;
        int best = start;
        int bestDist = Math.abs(startX - goalX) + Math.abs(startY - goalY);
        // BFS, bounded by map dimensions.
        while (head < tail) {
            int cell = queue[head++] & 65535;
            int x = cell % width, y = cell / width;
            int d = Math.abs(x - originalX) + Math.abs(y - originalY);
            if (d < bestDist) { bestDist = d; best = cell; }
            if (d <= goalTolerance) { found = cell; break; }
            // Four neighbors, no temporary arrays in the hot BFS loop.
            for (int j = 0; j < 4; j++) {
                int nx = x + (j == 0 ? 1 : j == 1 ? -1 : 0);
                int ny = y + (j == 2 ? 1 : j == 3 ? -1 : 0);
                if (!passable(tiles,width,height,nx,ny)) continue;
                int neighbor = ny * width + nx;
                if (seen[neighbor] == gen) continue;
                seen[neighbor] = gen;
                parent[neighbor] = (short) cell;
                queue[tail++] = (short) neighbor;
            }
        }
        if (found < 0) {
            // Do not pretend a long path through a wall is reachable.
            return null;
        }
        int len = 0, cur = found;
        while (cur != start && len < MAX_TILES - 1) {
            reverse[len++] = (short) cur;
            cur = parent[cur] & 65535;
        }
        if (cur != start) return null;
        int take = Math.min(len, maxSteps - 1);
        short[] path = new short[take+1];
        path[0] = (short) ((startX << 8) | startY);
        for (int i = 0; i < take; i++) {
            int cell = reverse[len-1-i] & 65535;
            path[i+1] = (short) (((cell % width) << 8) | (cell / width));
        }
        Route result = new Route();
        result.steps = path;
        int end = path[path.length-1] & 65535;
        result.goalX = (end >> 8) & 255;
        result.goalY = end & 255;
        result.complete = take == len && bestDist <= goalTolerance;
        return result;
    }
}
