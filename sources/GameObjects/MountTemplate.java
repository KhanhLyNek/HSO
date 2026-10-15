package GameObjects;

/* loaded from: KnightOnline_v403.jar:GameObjects/MountTemplate.class */
public class MountTemplate {
    public short idTemplate;
    public short idImage;
    public short lv;
    public byte typemove;
    public String name = "";
    public static final byte NGUA_NAU = 0;
    public static final byte NGUA_TRANG = 1;
    public static final byte NGUA_CHIENGIAP = 2;
    public static final byte NGUA_DO = 3;
    public static final byte NGUA_DEN = 4;
    public static MountTemplate me;
    public static byte[] speed;
    public static byte[][] FRAME_VE_TRUOC;
    public static byte[][] DY_CHAR_STAND;
    public static byte[][] DY_CHAR_MOVE;
    public static byte[][] dx;
    public static byte[] Arr_Fly;
    public static byte[][] dy;
    public static byte[][] FRAME_MOVE_LR;
    public static byte[][] FRAME_MOVE_DOWN;
    public static byte[][] FRAME_MOVE_UP;

    /** Guard both incomplete server data and invalid/signed mount IDs. */
    private static boolean hasRow(byte[][] matrix, int type, int requiredLength) {
        return matrix != null && type >= 0 && type < matrix.length &&
                matrix[type] != null && matrix[type].length >= requiredLength;
    }

    /**
     * The network catalogue is loaded asynchronously, separately from player
     * objects. A mount can arrive before its animation rows are available.
     * Do NOT change typeMount: its animation can resume after the data arrives.
     */
    public static boolean hasAnimationData(int type) {
        return type >= 0 &&
                speed != null && type < speed.length &&
                hasRow(dx, type, 8) &&
                hasRow(dy, type, 8) &&
                hasRow(DY_CHAR_STAND, type, 4) &&
                hasRow(DY_CHAR_MOVE, type, 12) &&
                hasRow(FRAME_VE_TRUOC, type, 0) &&
                hasRow(FRAME_MOVE_LR, type, 1) &&
                hasRow(FRAME_MOVE_DOWN, type, 1) &&
                hasRow(FRAME_MOVE_UP, type, 1);
    }

    /** Optional flight metadata must never be indexed before it is loaded. */
    public static boolean canFly(int type) {
        return type >= 0 && Arr_Fly != null &&
                type < Arr_Fly.length && Arr_Fly[type] == 1;
    }

    /** Speed may be unavailable while the server catalogue is streaming. */
    public static int safeSpeed(int type) {
        return type >= 0 && speed != null && type < speed.length ? speed[type] : 0;
    }

    public static MountTemplate gI() {
        if (me != null) {
            return me;
        }
        MountTemplate mountTemplate = new MountTemplate();
        me = mountTemplate;
        return mountTemplate;
    }
}
