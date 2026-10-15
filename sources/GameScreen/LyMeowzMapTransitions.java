package GameScreen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * CLDC-compatible graph of server-confirmed map/area transitions.
 * Entries are learned ONLY when the player was observed approaching a real gate.
 * Teleport stones, scripted transport and server redirects must not silently
 * teach arbitrary edges. No client-side map-change packets are invented.
 */
public final class LyMeowzMapTransitions {
    private static final int CAPACITY = 192;
    private static final int MAX_GATE_DISTANCE = 48;
    private static final long ARM_TIMEOUT_MS = 10000L;
    private static final int[] fromMap = new int[CAPACITY];
    private static final int[] fromArea = new int[CAPACITY];
    private static final int[] toMap = new int[CAPACITY];
    private static final int[] toArea = new int[CAPACITY];
    private static final int[] x = new int[CAPACITY];
    private static final int[] y = new int[CAPACITY];
    private static int size, revision;
    private static int armedMap = -1, armedArea = -1, armedX, armedY;
    private static long armedAt;

    private LyMeowzMapTransitions() {}

    private static boolean valid(int map, int area) {
        return map >= 0 && map <= 32767 && area >= 0 && area <= 255;
    }

    public static synchronized void armGate(int map, int area, int gateX, int gateY,
                                            long now) {
        if (!valid(map, area) || gateX < 0 || gateY < 0 || now <= 0) return;
        armedMap = map;
        armedArea = area;
        armedX = gateX;
        armedY = gateY;
        armedAt = now;
    }

    /** Never learn unless both a recently approached gate and server map change agree. */
    public static synchronized boolean confirmedTransition(int source, int sourceArea,
            int destination, int destinationArea, int playerX, int playerY, long now) {
        boolean match = valid(source, sourceArea) && valid(destination, destinationArea)
                && (source != destination || sourceArea != destinationArea)
                && armedMap == source && armedArea == sourceArea
                && now >= armedAt && now - armedAt <= ARM_TIMEOUT_MS
                && Math.abs(playerX - armedX) <= MAX_GATE_DISTANCE
                && Math.abs(playerY - armedY) <= MAX_GATE_DISTANCE;
        int px = armedX, py = armedY;
        disarm();
        if (match) remember(source, sourceArea, destination, destinationArea, px, py);
        return match;
    }

    public static synchronized void disarm() {
        armedMap = -1;
        armedArea = -1;
        armedAt = 0;
    }

    public static synchronized void remember(int source, int sourceArea,
            int destination, int destinationArea, int gateX, int gateY) {
        if (!valid(source, sourceArea) || !valid(destination, destinationArea) ||
                (source == destination && sourceArea == destinationArea) ||
                gateX < 0 || gateY < 0) return;
        for (int i = 0; i < size; i++) {
            if (fromMap[i] == source && fromArea[i] == sourceArea
                    && toMap[i] == destination && toArea[i] == destinationArea) {
                if (x[i] != gateX || y[i] != gateY) {
                    x[i] = gateX;
                    y[i] = gateY;
                    revision++;
                }
                return;
            }
        }
        if (size == CAPACITY) return;
        fromMap[size] = source; fromArea[size] = sourceArea;
        toMap[size] = destination; toArea[size] = destinationArea;
        x[size] = gateX; y[size] = gateY;
        size++;
        revision++;
    }

    /** Legacy map-only test helper. Runtime navigation MUST specify the area. */
    public static synchronized void remember(int source, int destination, int gateX, int gateY) {
        remember(source, 0, destination, 0, gateX, gateY);
    }

    /** Returns {gateX,gateY,nextMap,nextArea}, or null if no verified route. */
    public static synchronized int[] nextGate(int source, int sourceArea,
                                               int destination, int destinationArea) {
        if (!valid(source, sourceArea) || !valid(destination, destinationArea) ||
                (source == destination && sourceArea == destinationArea)) return null;
        int[] mapQueue = new int[CAPACITY + 1];
        int[] areaQueue = new int[CAPACITY + 1];
        int[] firstEdge = new int[CAPACITY + 1];
        int head = 0, tail = 1;
        mapQueue[0] = source;
        areaQueue[0] = sourceArea;
        firstEdge[0] = -1;
        while (head < tail) {
            for (int edge = 0; edge < size; edge++) {
                if (fromMap[edge] != mapQueue[head] ||
                        fromArea[edge] != areaQueue[head]) continue;
                boolean seen = false;
                for (int i = 0; i < tail; i++) {
                    if (mapQueue[i] == toMap[edge] && areaQueue[i] == toArea[edge]) {
                        seen = true;
                        break;
                    }
                }
                if (seen || tail >= mapQueue.length) continue;
                int first = firstEdge[head] == -1 ? edge : firstEdge[head];
                if (toMap[edge] == destination && toArea[edge] == destinationArea)
                    return new int[] {x[first], y[first], toMap[first], toArea[first]};
                mapQueue[tail] = toMap[edge];
                areaQueue[tail] = toArea[edge];
                firstEdge[tail] = first;
                tail++;
            }
            head++;
        }
        return null;
    }

    /** Compatibility for existing map-only smoke checks. */
    public static synchronized int[] nextGate(int source, int destination) {
        int[] hop = nextGate(source, 0, destination, 0);
        return hop == null ? null : new int[] {hop[0], hop[1], hop[2]};
    }

    public static synchronized int revision() { return revision; }
    public static synchronized int size() { return size; }

    public static synchronized void write(DataOutput out) throws IOException {
        out.writeShort(size);
        for (int i = 0; i < size; i++) {
            out.writeShort(fromMap[i]); out.writeByte(fromArea[i]);
            out.writeShort(toMap[i]); out.writeByte(toArea[i]);
            out.writeShort(x[i]); out.writeShort(y[i]);
        }
    }

    public static synchronized void read(DataInput in) throws IOException {
        int count = in.readUnsignedShort();
        if (count > CAPACITY) throw new IOException("Too many gate edges");
        int[] fm = new int[count], fa = new int[count], tm = new int[count],
                ta = new int[count], gx = new int[count], gy = new int[count];
        for (int i = 0; i < count; i++) {
            fm[i] = in.readUnsignedShort(); fa[i] = in.readUnsignedByte();
            tm[i] = in.readUnsignedShort(); ta[i] = in.readUnsignedByte();
            gx[i] = in.readUnsignedShort(); gy[i] = in.readUnsignedShort();
            if (!valid(fm[i], fa[i]) || !valid(tm[i], ta[i]))
                throw new IOException("Bad gate edge");
        }
        size = count;
        for (int i = 0; i < count; i++) {
            fromMap[i] = fm[i]; fromArea[i] = fa[i];
            toMap[i] = tm[i]; toArea[i] = ta[i];
            x[i] = gx[i]; y[i] = gy[i];
        }
        disarm();
        revision++;
    }
}
