package CLib;

/* loaded from: KnightOnline_v403.jar:CLib/mSystem.class */
public class mSystem {
    public static int ID_REGION;
    public static boolean isLoad_REGION;
    public static final byte INDEX_SV_GLOBAL = 2;
    public static boolean Android = false;
    public static String link_region = "http://dl.teamobi.com/geo/kn.php?ip=";
    public static String[][] listServer = {new String[]{"Chiến Thần", "hs1.teamobi.com"}, new String[]{"Rồng Lửa", "hs2.teamobi.com"}, new String[]{"Global Server", "hsglobal.teamobi.com"}, new String[]{"Phượng Hoàng", "hs3.teamobi.com"}, new String[]{"Nhân Mã", "hs5.teamobi.com"}, new String[]{"Kì Lân", "hs6.teamobi.com"}, new String[]{"Thiên Hà (New)", "hs7.teamobi.com"}, new String[]{"Thách Đấu", "hs4.teamobi.com"}};
    public static String[][] listServer_VN = {new String[]{"Chiến Thần", "hs1.teamobi.com"}, new String[]{"Rồng Lửa", "hs2.teamobi.com"}, new String[]{"Global Server", "hsglobal.teamobi.com"}, new String[]{"Phượng Hoàng", "hs3.teamobi.com"}, new String[]{"Nhân Mã", "hs5.teamobi.com"}, new String[]{"Kì Lân (New)", "hs6.teamobi.com"}, new String[]{"Thách Đấu", "hs4.teamobi.com"}};
    public static String[][] listServer_In_Do = {new String[]{"Indo Naga", "hsindo.teamobi.com"}, new String[]{"Indo Garuda(new)", "hsindo.teamobi.com"}, new String[]{"Knight Age (ENG)", "hsglobal.teamobi.com"}};
    public static String[][] listServer_Usa = {new String[]{"Fire Dragon (ENG)", "46.137.254.172"}, new String[]{"Sky Dragon (SPN)", "54.254.156.202"}, new String[]{"Knight Age (ENG)", "hsglobal.teamobi.com"}};
    public static boolean isMaHoa = false;
    public static boolean isIP_GDX = false;
    public static boolean isIP_TrucTiep = false;
    public static boolean isj2me = true;
    public static int dyCharStep = 0;
    public static boolean isImgLocal = true;
    public static boolean isWinphone = false;
    public static boolean isIphone = false;

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void outz(String str) {
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static void freeDataChangeMap() {
    }

    public static void outloi(String str) {
    }

    public static void my_Gc() {
        System.gc();
    }

    public static int[][] new_M_Int(int i, int i2) {
        return new int[i][i2];
    }

    public static String[][] new_M_String(int i, int i2) {
        return new String[i][i2];
    }

    public static byte[][] new_M_Byte(int i, int i2) {
        return new byte[i][i2];
    }

    public static byte[][][] new_M_Byte(int i, int i2, int i3) {
        return new byte[i][i2][i3];
    }

    public static String[] split(String str, String str2) {
        mVector mvector = new mVector("mSys nodes");
        int indexOf = str.indexOf(str2);
        while (true) {
            int i = indexOf;
            if (i < 0) {
                break;
            }
            mvector.addElement(str.substring(0, i));
            str = str.substring(i + str2.length());
            indexOf = str.indexOf(str2);
        }
        mvector.addElement(str);
        String[] strArr = new String[mvector.size()];
        if (mvector.size() > 0) {
            for (int i2 = 0; i2 < mvector.size(); i2++) {
                strArr[i2] = (String) mvector.elementAt(i2);
            }
        }
        return strArr;
    }

    public static String getPackageName() {
        return "";
    }

    public static void doChangeMenuNapapple() {
    }

    public static String getLat() {
        return "";
    }

    public static String getLong() {
        return "";
    }

    public static boolean isIpAppstore() {
        return false;
    }

    public static boolean isHideNaptien() {
        return false;
    }
}
