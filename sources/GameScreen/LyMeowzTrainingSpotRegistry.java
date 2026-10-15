package GameScreen;

/**
 * Bounded, observation-only monster hunting area catalogue for J2ME.
 * No guessed spawn tables or packets. More recent low-crowd sightings win.
 */
public final class LyMeowzTrainingSpotRegistry {
    private static final int CAPACITY = 192;
    private static final int[] monsterIds = new int[CAPACITY];
    private static final int[] monsterLevels = new int[CAPACITY];
    private static final int[] maps = new int[CAPACITY];
    private static final int[] areas = new int[CAPACITY];
    private static final int[] xs = new int[CAPACITY];
    private static final int[] ys = new int[CAPACITY];
    private static final int[] crowds = new int[CAPACITY];
    private static final int[] sightings = new int[CAPACITY];
    private static int size;
    private LyMeowzTrainingSpotRegistry() {}

    public static synchronized void observe(int category, int level, int map, int area,
            int x, int y, int nearbyPlayers) {
        if (category < 0 || level < 0 || map < 0 || area < 0 ||
                x < 0 || y < 0) return;
        if (nearbyPlayers < 0) nearbyPlayers = 0;
        if (nearbyPlayers > 8) nearbyPlayers = 8;
        for (int i = 0; i < size; i++) {
            if (monsterIds[i] == category && maps[i] == map && areas[i] == area) {
                xs[i] = x; ys[i] = y;
                monsterLevels[i] = level;
                crowds[i] = (crowds[i] * 3 + nearbyPlayers) / 4;
                if (sightings[i] < 255) sightings[i]++;
                return;
            }
        }
        if (size >= CAPACITY) return;
        monsterIds[size] = category;
        monsterLevels[size] = level;
        maps[size] = map;
        areas[size] = area;
        xs[size] = x; ys[size] = y;
        crowds[size] = nearbyPlayers;
        sightings[size] = 1;
        size++;
    }

    /**
     * Returns {map,area,x,y,category,level,crowd}; null when not observed.
     * A specified quest monster category has strict priority.
     */
    public static synchronized int[] best(int playerLevel, int questCategory,
            int currentMap, int currentArea) {
        int bestScore = Integer.MAX_VALUE;
        int bestIdx = -1;
        for (int i = 0; i < size; i++) {
            if (monsterLevels[i] > playerLevel || monsterLevels[i] < playerLevel - 3)
                continue;
            if (questCategory >= 0 && monsterIds[i] != questCategory) continue;
            int difference = playerLevel - monsterLevels[i];
            int score = difference * 60 + crowds[i] * 45;
            if (sightings[i] < 2) score += 30;
            if (maps[i] != currentMap || areas[i] != currentArea) score += 40;
            if (score < bestScore) { bestScore = score; bestIdx = i; }
        }
        if (bestIdx < 0) return null;
        return new int[] {maps[bestIdx], areas[bestIdx], xs[bestIdx], ys[bestIdx],
                monsterIds[bestIdx], monsterLevels[bestIdx], crowds[bestIdx]};
    }

    public static synchronized int size() { return size; }
    public static synchronized void clear() { size = 0; }
}
