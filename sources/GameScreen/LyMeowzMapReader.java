package GameScreen;

/**
 * Lightweight CLDC-compatible tile navigation. A route is encoded as packed
 * (x << 8) | y minimap coordinates, starting at the next step.
 * No Java collections, recursion or platform-specific APIs are required.
 */
public final class LyMeowzMapReader {
    private LyMeowzMapReader() {}

    public static boolean walkable(int[] tiles, int width, int height, int x, int y) {
        if (tiles == null || width <= 0 || height <= 0 || x < 0 || y < 0 ||
                x >= width || y >= height || (long) width * height > tiles.length)
            return false;
        int type = tiles[y * width + x];
        return type != 1 && type != -1;
    }

    /** Returns an empty route if the target cannot be reached. */
    public static short[] route(int[] tiles, int width, int height,
                                int sx, int sy, int tx, int ty, int maxSteps) {
        if (width <= 0 || height <= 0 || width > 255 || height > 255 ||
                maxSteps <= 0 || (long) width * height > 16384 ||
                !walkable(tiles, width, height, sx, sy) ||
                !walkable(tiles, width, height, tx, ty))
            return new short[0];
        if (sx == tx && sy == ty)
            return new short[0];

        int n = width * height;
        int[] parent = new int[n];
        int[] queue = new int[n];
        for (int i = 0; i < n; i++) parent[i] = -1;
        int start = sy * width + sx;
        int goal = ty * width + tx;
        int head = 0, tail = 0;
        queue[tail++] = start;
        parent[start] = start;
        while (head < tail && parent[goal] == -1) {
            int p = queue[head++];
            int x = p % width, y = p / width;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + (dir == 0 ? 1 : dir == 1 ? -1 : 0);
                int ny = y + (dir == 2 ? 1 : dir == 3 ? -1 : 0);
                if (!walkable(tiles, width, height, nx, ny)) continue;
                int q = ny * width + nx;
                if (parent[q] != -1) continue;
                parent[q] = p;
                queue[tail++] = q;
            }
        }
        if (parent[goal] == -1) return new short[0];
        int steps = 0;
        for (int p = goal; p != start; p = parent[p]) {
            if (++steps > maxSteps) return new short[0];
        }
        short[] result = new short[steps];
        int p = goal;
        for (int i = steps - 1; i >= 0; i--) {
            result[i] = (short) (((p % width) << 8) | (p / width));
            p = parent[p];
        }
        return result;
    }
}
