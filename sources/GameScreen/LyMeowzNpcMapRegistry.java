package GameScreen;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/** Last actually observed map and area for each NPC. Never infers a spawn. */
public final class LyMeowzNpcMapRegistry {
    private static final int LIMIT = 128;
    private static final int[] npcIds = new int[LIMIT];
    private static final int[] mapIds = new int[LIMIT];
    private static final int[] areaIds = new int[LIMIT];
    private static int size, revision;

    private LyMeowzNpcMapRegistry() {}

    public static synchronized void remember(int npcId, int mapId, int areaId) {
        if (npcId < 0 || npcId > 32767 || mapId < 0 || mapId > 32767 ||
                areaId < 0 || areaId > 255) return;
        for (int i = 0; i < size; i++) {
            if (npcIds[i] == npcId) {
                if (mapIds[i] != mapId || areaIds[i] != areaId) {
                    mapIds[i] = mapId;
                    areaIds[i] = areaId;
                    revision++;
                }
                return;
            }
        }
        if (size >= LIMIT) return;
        npcIds[size] = npcId;
        mapIds[size] = mapId;
        areaIds[size] = areaId;
        size++;
        revision++;
    }

    /** Backward compatibility for map-only tests. */
    public static synchronized void remember(int npcId, int mapId) {
        remember(npcId, mapId, 0);
    }

    public static synchronized int findMap(int npcId) {
        for (int i = 0; i < size; i++)
            if (npcIds[i] == npcId) return mapIds[i];
        return -1;
    }

    public static synchronized int findArea(int npcId) {
        for (int i = 0; i < size; i++)
            if (npcIds[i] == npcId) return areaIds[i];
        return -1;
    }

    public static synchronized void clear() {
        size = 0;
        revision++;
    }

    public static synchronized int revision() { return revision; }
    public static synchronized int size() { return size; }

    public static synchronized void write(DataOutput out) throws IOException {
        out.writeShort(size);
        for (int i = 0; i < size; i++) {
            out.writeShort(npcIds[i]);
            out.writeShort(mapIds[i]);
            out.writeByte(areaIds[i]);
        }
    }

    public static synchronized void read(DataInput in) throws IOException {
        int count = in.readUnsignedShort();
        if (count > LIMIT) throw new IOException("Too many NPC locations");
        int[] ids = new int[count], maps = new int[count], areas = new int[count];
        for (int i = 0; i < count; i++) {
            ids[i] = in.readUnsignedShort();
            maps[i] = in.readUnsignedShort();
            areas[i] = in.readUnsignedByte();
            if (ids[i] > 32767 || maps[i] > 32767)
                throw new IOException("Invalid NPC location");
        }
        size = count;
        for (int i = 0; i < count; i++) {
            npcIds[i] = ids[i]; mapIds[i] = maps[i]; areaIds[i] = areas[i];
        }
        revision++;
    }
}
