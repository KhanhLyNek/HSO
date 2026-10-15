package GameScreen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import Model.CRes;

/**
 * Compact local per-character RMS cache. Map IDs are shared only when actually
 * observed by that character. No server-wide RMS slot is overwritten.
 */
public final class LyMeowzWorldStore {
    private static final int MAGIC = 0x4c594d57; // LYMW
    private static final int VERSION = 2;
    private static final int MAX_BYTES = 4096;
    private static String activeStore;
    private static int savedNpcRevision, savedEdgeRevision;
    private static boolean loaded;
    private static int errorCount;

    private LyMeowzWorldStore() {}

    private static String storeName(String characterName) {
        // CLDC RMS store names have a 32-character limit.
        return "LyMeowz_" + Integer.toHexString(characterName.hashCode());
    }

    public static synchronized boolean ensureLoaded(String characterName) {
        if (characterName == null || characterName.length() == 0) return false;
        String key = storeName(characterName);
        if (loaded && key.equals(activeStore)) return true;
        if (loaded) flushIfDirty();
        LyMeowzNpcMapRegistry.clear();
        LyMeowzMapTransitions.clear();
        activeStore = key;
        loaded = true;
        try {
            byte[] raw = CRes.loadRMS(key);
            if (raw != null) decode(raw);
        } catch (Exception e) {
            errorCount++;
            LyMeowzNpcMapRegistry.clear();
            LyMeowzMapTransitions.clear();
        }
        savedNpcRevision = LyMeowzNpcMapRegistry.revision();
        savedEdgeRevision = LyMeowzMapTransitions.revision();
        return true;
    }

    public static synchronized byte[] encode() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.writeInt(MAGIC);
        out.writeByte(VERSION);
        LyMeowzNpcMapRegistry.write(out);
        LyMeowzMapTransitions.write(out);
        out.writeInt(0x6c794f4b);
        out.flush();
        if (bytes.size() > MAX_BYTES) throw new IOException("World cache oversized");
        return bytes.toByteArray();
    }

    public static synchronized void decode(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length < 11 || bytes.length > MAX_BYTES)
            throw new IOException("World cache size invalid");
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        if (in.readInt() != MAGIC || in.readUnsignedByte() != VERSION)
            throw new IOException("World cache version mismatch");
        try {
            LyMeowzNpcMapRegistry.read(in);
            LyMeowzMapTransitions.read(in);
            if (in.readInt() != 0x6c794f4b || in.available() != 0)
                throw new IOException("World cache corrupted");
        } catch (IOException error) {
            LyMeowzNpcMapRegistry.clear();
            LyMeowzMapTransitions.clear();
            throw error;
        }
    }

    public static synchronized boolean flushIfDirty() {
        if (!loaded || activeStore == null) return false;
        int n = LyMeowzNpcMapRegistry.revision();
        int e = LyMeowzMapTransitions.revision();
        if (savedNpcRevision == n && savedEdgeRevision == e) return false;
        try {
            CRes.saveRMS(activeStore, encode());
            savedNpcRevision = n;
            savedEdgeRevision = e;
            return true;
        } catch (Exception error) {
            errorCount++;
            return false;
        }
    }

    public static synchronized int errorCount() { return errorCount; }
}
