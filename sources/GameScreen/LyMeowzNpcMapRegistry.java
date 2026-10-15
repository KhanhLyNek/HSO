package GameScreen;

/**
 * Small CLDC-compatible cache of NPC locations observed on loaded maps.
 * Never guesses a map or sends a map-change packet.
 */
public final class LyMeowzNpcMapRegistry {
    private static final int LIMIT = 128;
    private static final int[] npcIds = new int[LIMIT];
    private static final int[] mapIds = new int[LIMIT];
    private static int size;

    private LyMeowzNpcMapRegistry() {}

    public static synchronized void remember(int npcId, int mapId) {
        if (npcId < 0 || mapId < 0) return;
        for (int i = 0; i < size; i++) {
            if (npcIds[i] == npcId) {
                mapIds[i] = mapId;
                return;
            }
        }
        if (size < LIMIT) {
            npcIds[size] = npcId;
            mapIds[size] = mapId;
            size++;
        }
    }

    public static synchronized int findMap(int npcId) {
        for (int i = 0; i < size; i++) {
            if (npcIds[i] == npcId) return mapIds[i];
        }
        return -1;
    }
}
