/* Persistent per-character farm target. Only target map/mob/anchor is stored.
 * A stored target never auto-enables Auto Farm after relogin: player must opt in.
 * Java ME MIDP RecordStore is used; failures do not prevent ordinary gameplay.
 */
package Tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;

public final class FarmMemory {
    private static final int VERSION = 1;
    private FarmMemory() {}

    private static String key(int playerId) {
        return "HSOAF" + playerId;
    }

    /** Data: selectedPlayerLevel, mapId, monsterId, monsterLevel, centerX, centerY. */
    public static int[] load(int playerId) {
        RecordStore rs = null;
        try {
            rs = RecordStore.openRecordStore(key(playerId), false);
            if (rs.getNumRecords() < 1) return null;
            byte[] bytes = rs.getRecord(1);
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
            if (in.readInt() != VERSION || in.readInt() != playerId) return null;
            int[] data = new int[6];
            for (int i = 0; i < data.length; i++) data[i] = in.readInt();
            in.close();
            return data;
        } catch (Exception ignored) {
            return null;
        } finally {
            try { if (rs != null) rs.closeRecordStore(); } catch (Exception ignored) {}
        }
    }

    public static void save(int playerId, int[] data) {
        if (data == null || data.length != 6) return;
        RecordStore rs = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeInt(VERSION);
            out.writeInt(playerId);
            for (int i = 0; i < data.length; i++) out.writeInt(data[i]);
            out.flush();
            byte[] buffer = bytes.toByteArray();
            rs = RecordStore.openRecordStore(key(playerId), true);
            if (rs.getNumRecords() == 0) rs.addRecord(buffer, 0, buffer.length);
            else rs.setRecord(1, buffer, 0, buffer.length);
            out.close();
        } catch (Exception ignored) {
        } finally {
            try { if (rs != null) rs.closeRecordStore(); } catch (Exception ignored) {}
        }
    }

    public static void clear(int playerId) {
        try {
            RecordStore.deleteRecordStore(key(playerId));
        } catch (Exception ignored) {}
    }
}
