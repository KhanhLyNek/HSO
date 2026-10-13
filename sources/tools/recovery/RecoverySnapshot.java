package tools.recovery;

/**
 * Keeps the last server map/area/position observed while the hero is alive.
 * Prevents capturing a spawn point in town after a death teleport.
 *
 * Pure Java with no external dependencies, suitable for CLDC preverification.
 */
public final class RecoverySnapshot {
    public static final long MAX_SAMPLE_AGE_MS = 30000L;

    private int liveMap = -1, liveArea, liveX, liveY;
    private String liveName = "";
    private long lastLiveAt = -1L;
    private int deathMap = -1, deathArea, deathX, deathY;
    private String deathName = "";
    private boolean captured;

    public void clear() {
        liveMap = -1;
        liveArea = liveX = liveY = 0;
        liveName = "";
        lastLiveAt = -1L;
        deathMap = -1;
        deathArea = deathX = deathY = 0;
        deathName = "";
        captured = false;
    }

    public void recordAlive(int map, int area, int x, int y,
                            String name, long now) {
        if (captured || map < 0 || x < 0 || y < 0 || now < 0) return;
        liveMap = map;
        liveArea = area;
        liveX = x;
        liveY = y;
        liveName = name == null ? "" : name;
        lastLiveAt = now;
    }

    /** Capture once. Repeated death packets cannot overwrite the location. */
    public void captureDeath(int map, int area, int x, int y,
                             String name, long now) {
        if (captured) return;
        if (liveMap >= 0 && lastLiveAt >= 0 &&
                now >= lastLiveAt && now - lastLiveAt <= MAX_SAMPLE_AGE_MS) {
            deathMap = liveMap;
            deathArea = liveArea;
            deathX = liveX;
            deathY = liveY;
            deathName = liveName;
        } else {
            deathMap = map;
            deathArea = area;
            deathX = x;
            deathY = y;
            deathName = name == null ? "" : name;
        }
        captured = true;
    }

    public boolean hasDeathLocation() { return captured && deathMap >= 0; }
    public int mapId() { return deathMap; }
    public int area() { return deathArea; }
    public int x() { return deathX; }
    public int y() { return deathY; }
    public String mapName() { return deathName; }
}
