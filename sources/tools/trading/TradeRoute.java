package tools.trading;

import GameObjects.MainObject;

/** In-memory waypoints captured during a real, manually walked trade journey. */
public final class TradeRoute {
    public static final int MAX_STEPS = 400;

    public static final class Step {
        public final int mapId;
        public final byte area;
        public final int x;
        public final int y;
        public final boolean exit;
        Step(int map, byte zone, int px, int py, boolean portal) {
            mapId = map; area = zone; x = px; y = py; exit = portal;
        }
    }

    private final Step[] steps = new Step[MAX_STEPS];
    private int count;

    public int size() { return count; }
    public Step get(int index) {
        return index >= 0 && index < count ? steps[index] : null;
    }
    public void clear() {
        for (int i = 0; i < count; i++) steps[i] = null;
        count = 0;
    }
    public boolean add(int map, byte zone, int x, int y, boolean exit) {
        if (count == MAX_STEPS) return false;
        Step last = get(count - 1);
        if (last != null && last.mapId == map && last.area == zone
                && MainObject.getDistance(last.x, last.y, x, y) < (exit ? 1 : 48)
                && !exit) return true;
        if (exit && last != null && last.mapId == map && last.area == zone
                && MainObject.getDistance(last.x, last.y, x, y) < 48
                && !last.exit) {
            steps[count - 1] = new Step(map, zone, x, y, true);
            return true;
        }
        steps[count++] = new Step(map, zone, x, y, exit);
        return true;
    }
}
