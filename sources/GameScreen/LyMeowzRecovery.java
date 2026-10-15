package GameScreen;

/**
 * Explicit opt-in, one-request-per-death recovery controller.
 * No network call or movement is performed here.
 */
public final class LyMeowzRecovery {
    public static final byte OFF = 0, WATCH = 1, REVIVE_REQUESTED = 2,
            RETURNING = 3, COMPLETE = 4, FAILED = 5;
    public static final int NONE = 0, REQUEST_REVIVE = 1, RETURN_TO_CHECKPOINT = 2;
    private boolean enabled;
    private byte state = OFF;
    private int mapId = -1, areaId = -1, x, y;
    private long reviveRequestedAt;
    private boolean observedAlive;

    public void setEnabled(boolean value) {
        enabled = value;
        if (!value) state = OFF;
        else if (state == OFF || state == COMPLETE || state == FAILED) state = WATCH;
    }

    public int update(boolean alive, int map, int area, int playerX, int playerY,
                      long now) {
        if (!enabled || map < 0 || area < 0) return NONE;
        if (alive) {
            observedAlive = true;
            if (state == REVIVE_REQUESTED) {
                state = RETURNING;
                return RETURN_TO_CHECKPOINT;
            }
            if (state == RETURNING) return RETURN_TO_CHECKPOINT;
            mapId = map;
            areaId = area;
            x = playerX;
            y = playerY;
            state = WATCH;
            return NONE;
        }
        if (state == WATCH && observedAlive && mapId >= 0) {
            state = REVIVE_REQUESTED;
            reviveRequestedAt = now;
            return REQUEST_REVIVE;
        }
        if (state == REVIVE_REQUESTED && now - reviveRequestedAt > 20000L)
            state = FAILED;
        return NONE;
    }

    public void completed() { if (state == RETURNING) state = COMPLETE; }
    public void failed() { state = FAILED; }
    public boolean enabled() { return enabled; }
    public byte state() { return state; }
    public int targetMap() { return mapId; }
    public int targetArea() { return areaId; }
    public int targetX() { return x; }
    public int targetY() { return y; }
}
