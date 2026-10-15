package GameScreen;

/** Bounded CLDC-compatible observed map-transition graph. */
public final class LyMeowzMapTransitions {
    private static final int CAPACITY = 192;
    private static final int[] from = new int[CAPACITY];
    private static final int[] to = new int[CAPACITY];
    private static final int[] x = new int[CAPACITY];
    private static final int[] y = new int[CAPACITY];
    private static int size;

    private LyMeowzMapTransitions() {}

    public static synchronized void remember(int source, int destination, int gateX, int gateY) {
        if (source < 0 || destination < 0 || source == destination) return;
        for (int i = 0; i < size; i++) {
            if (from[i] == source && to[i] == destination) {
                x[i] = gateX;
                y[i] = gateY;
                return;
            }
        }
        if (size == CAPACITY) return;
        from[size] = source;
        to[size] = destination;
        x[size] = gateX;
        y[size] = gateY;
        size++;
    }

    /** Returns {gatePixelX, gatePixelY, immediateNextMapId}, or null if no observed route exists. */
    public static synchronized int[] nextGate(int source, int destination) {
        if (source < 0 || destination < 0 || source == destination) return null;
        int[] queue = new int[CAPACITY + 1];
        int[] visited = new int[CAPACITY + 1];
        int[] firstEdge = new int[CAPACITY + 1];
        int head = 0, tail = 1;
        queue[0] = source;
        visited[0] = source;
        firstEdge[0] = -1;
        while (head < tail) {
            int node = queue[head];
            for (int e = 0; e < size; e++) {
                if (from[e] != node) continue;
                int next = to[e];
                boolean seen = false;
                for (int v = 0; v < tail; v++) {
                    if (visited[v] == next) { seen = true; break; }
                }
                if (seen || tail >= queue.length) continue;
                int first = firstEdge[head] < 0 ? e : firstEdge[head];
                if (next == destination) return new int[] {x[first], y[first], to[first]};
                visited[tail] = next;
                queue[tail] = next;
                firstEdge[tail] = first;
                tail++;
            }
            head++;
        }
        return null;
    }
}
