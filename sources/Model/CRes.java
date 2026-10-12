package Model;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.CharPartInfo;
import GameObjects.MainInfoItem;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.WPSplashInfo;
import GameObjects.WeaponInfo;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Map.MainItemMap;
import Skill.Skill;
import Thread_More.Load_Data_And_Img;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:Model/CRes.class */
public class CRes {
    private static short[] sin = {0, 18, 36, 54, 71, 89, 107, 125, 143, 160, 178, 195, 213, 230, 248, 265, 282, 299, 316, 333, 350, 367, 384, 400, 416, 433, 449, 465, 481, 496, 512, 527, 543, 558, 573, 587, 602, 616, 630, 644, 658, 672, 685, 698, 711, 724, 737, 749, 761, 773, 784, 796, 807, 818, 828, 839, 849, 859, 868, 878, 887, 896, 904, 912, 920, 928, 935, 943, 949, 956, 962, 968, 974, 979, 984, 989, 994, 998, 1002, 1005, 1008, 1011, 1014, 1016, 1018, 1020, 1022, 1023, 1023, 1024, 1024};
    public static mHashtable hashWeapon = new mHashtable();
    public static String RMS_REGION = "region_Rms";
    private static short[] cos = new short[91];
    private static int[] tan = new int[91];
    static int value;
    public static Random r;
    public static final int LEG = 0;
    public static final int BODY = 1;
    public static final int HEAD = 2;
    public static final int HAT = 3;
    public static final int EYE = 4;
    public static final int HAIR = 5;
    public static final int WING = 6;
    public static CharPartInfo[][] charPartInfo;
    public static WPSplashInfo[][][] wpSplashInfos;

    /* JADX WARN: Type inference failed for: r0v13, types: [GameObjects.CharPartInfo[], GameObjects.CharPartInfo[][]] */
    static {
        for (int i = 0; i <= 90; i++) {
            cos[i] = sin[90 - i];
            if (cos[i] == 0) {
                tan[i] = Integer.MAX_VALUE;
            } else {
                tan[i] = (sin[i] << 10) / cos[i];
            }
        }
        value = 1;
        r = new Random();
        charPartInfo = new CharPartInfo[7];
        for (int i2 = 0; i2 < charPartInfo.length; i2++) {
            charPartInfo[i2] = new CharPartInfo[256];
        }
        wpSplashInfos = new WPSplashInfo[4][5][3];
    }

    public static final int sin(int i) {
        if (i >= 0 && i < 90) {
            return sin[i];
        }
        if (i >= 90 && i < 180) {
            return sin[180 - i];
        }
        if (i >= 180 && i < 270) {
            return -sin[i - 180];
        }
        return -sin[360 - i];
    }

    public static final int cos(int i) {
        if (i >= 0 && i < 90) {
            return cos[i];
        }
        if (i >= 90 && i < 180) {
            return -cos[180 - i];
        }
        if (i >= 180 && i < 270) {
            return -cos[i - 180];
        }
        return cos[360 - i];
    }

    public static final int tan(int i) {
        if (i >= 0 && i < 90) {
            return tan[i];
        }
        if (i >= 90 && i < 180) {
            return -tan[180 - i];
        }
        if (i >= 180 && i < 270) {
            return tan[i - 180];
        }
        return -tan[360 - i];
    }

    public static final int atan(int i) {
        for (int i2 = 0; i2 <= 90; i2++) {
            if (tan[i2] >= i) {
                return i2;
            }
        }
        return 0;
    }

    public static final int angle(int i, int i2) {
        int i3;
        if (i != 0) {
            i3 = atan(Math.abs((i2 << 10) / i));
            if (i2 >= 0 && i < 0) {
                i3 = 180 - i3;
            }
            if (i2 < 0 && i < 0) {
                i3 = 180 + i3;
            }
            if (i2 < 0 && i >= 0) {
                i3 = 360 - i3;
            }
        } else {
            i3 = i2 > 0 ? 90 : 270;
        }
        return i3;
    }

    public static final int fixangle(int i) {
        if (i >= 360) {
            i %= 360;
        }
        if (i < 0) {
            i = 360 + (i % 360);
        }
        return i;
    }

    public static final int subangle(int i, int i2) {
        int i3 = i2 - i;
        if (i3 < -180) {
            return i3 + 360;
        }
        if (i3 > 180) {
            return i3 - 360;
        }
        return i3;
    }

    public static int abs(int i) {
        if (i < 0) {
            return -i;
        }
        return i;
    }

    public static int random(int i) {
        return r.nextInt(i);
    }

    public static int random_Am_0(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 == 0) {
                i2 = r.nextInt() % i;
            } else {
                return i3;
            }
        }
    }

    public static int random_Am(int i, int i2) {
        int nextInt = i + r.nextInt(i2 - i);
        if (random(2) == 0) {
            nextInt = -nextInt;
        }
        return nextInt;
    }

    public static int random(int i, int i2) {
        return i + r.nextInt(i2 - i);
    }

    public static int sqrt(int i) {
        int i2;
        if (i <= 0) {
            return 0;
        }
        int i3 = (i + 1) / 2;
        do {
            i2 = i3;
            i3 = (i3 / 2) + (i / (2 * i3));
        } while (Math.abs(i2 - i3) > 1);
        return i3;
    }

    public static float sqrt(float f) {
        float f2;
        if (f <= 0.0f) {
            return 0.0f;
        }
        float f3 = (f + 1.0f) / 2.0f;
        do {
            f2 = f3;
            f3 = (f3 / 2.0f) + (f / (2.0f * f3));
        } while (Math.abs(f2 - f3) > 1.0f);
        return f3;
    }

    public static int setDis(int i, int i2, int i3, int i4) {
        return abs(i - i3) + abs(i2 - i4);
    }

    public static void quickSort(mVector mvector) {
        recQuickSort(mvector, 0, mvector.size() - 1);
    }

    private static void recQuickSort(mVector mvector, int i, int i2) {
        try {
            if (i2 - i <= 0) {
                return;
            }
            int partitionIt = partitionIt(mvector, i, i2, ((MainObject) mvector.elementAt(i2)).ySort);
            recQuickSort(mvector, i, partitionIt - 1);
            recQuickSort(mvector, partitionIt + 1, i2);
        } catch (Exception e) {
            mSystem.println("loi Cres 1");
            e.printStackTrace();
        }
    }

    private static int partitionIt(mVector mvector, int i, int i2, int i3) {
        int i4 = i - 1;
        int i5 = i2;
        while (true) {
            try {
                i4++;
                if (((MainObject) mvector.elementAt(i4)).ySort >= i3) {
                    while (i5 > 0) {
                        i5--;
                        if (((MainObject) mvector.elementAt(i5)).ySort <= i3) {
                            break;
                        }
                    }
                    if (i4 >= i5) {
                        break;
                    }
                    swap(mvector, i4, i5);
                }
            } catch (Exception e) {
                mSystem.println("loi Cres 2");
                e.printStackTrace();
            }
        }
        swap(mvector, i4, i2);
        return i4;
    }

    private static void swap(mVector mvector, int i, int i2) {
        Object elementAt = mvector.elementAt(i2);
        if (((MainObject) mvector.elementAt(i2)).ySort != ((MainObject) mvector.elementAt(i)).ySort) {
            mvector.setElementAt(mvector.elementAt(i), i2);
            mvector.setElementAt(elementAt, i);
        }
    }

    public static void quickSort1(mVector mvector) {
        recQuickSort1(mvector, 0, mvector.size() - 1);
    }

    private static void recQuickSort1(mVector mvector, int i, int i2) {
        try {
            if (i2 - i <= 0) {
                return;
            }
            int partitionIt1 = partitionIt1(mvector, i, i2, ((MainItemMap) mvector.elementAt(i2)).y);
            recQuickSort1(mvector, i, partitionIt1 - 1);
            recQuickSort1(mvector, partitionIt1 + 1, i2);
        } catch (Exception e) {
            mSystem.println("loi Cres 1");
            e.printStackTrace();
        }
    }

    private static int partitionIt1(mVector mvector, int i, int i2, int i3) {
        int i4 = i - 1;
        int i5 = i2;
        while (true) {
            try {
                i4++;
                if (((MainItemMap) mvector.elementAt(i4)).y >= i3) {
                    while (i5 > 0) {
                        i5--;
                        if (((MainItemMap) mvector.elementAt(i5)).y <= i3) {
                            break;
                        }
                    }
                    if (i4 >= i5) {
                        break;
                    }
                    swap1(mvector, i4, i5);
                }
            } catch (Exception e) {
                mSystem.println("loi Cres 2");
                e.printStackTrace();
            }
        }
        swap1(mvector, i4, i2);
        return i4;
    }

    private static void swap1(mVector mvector, int i, int i2) {
        Object elementAt = mvector.elementAt(i2);
        if (((MainItemMap) mvector.elementAt(i2)).y != ((MainItemMap) mvector.elementAt(i)).y) {
            mvector.setElementAt(mvector.elementAt(i), i2);
            mvector.setElementAt(elementAt, i);
        }
    }

    public static void quickSort(MainItemMap[] mainItemMapArr) {
        recQuickSort(mainItemMapArr, 0, mainItemMapArr.length - 1);
    }

    private static void recQuickSort(MainItemMap[] mainItemMapArr, int i, int i2) {
        try {
            if (i2 - i <= 0) {
                return;
            }
            int partitionIt = partitionIt(mainItemMapArr, i, i2, mainItemMapArr[i2].y);
            recQuickSort(mainItemMapArr, i, partitionIt - 1);
            recQuickSort(mainItemMapArr, partitionIt + 1, i2);
        } catch (Exception e) {
            mSystem.println("loi Cres 3");
            e.printStackTrace();
        }
    }

    private static int partitionIt(MainItemMap[] mainItemMapArr, int i, int i2, int i3) {
        int i4 = i - 1;
        int i5 = i2;
        while (true) {
            try {
                i4++;
                if (mainItemMapArr[i4].y >= i3) {
                    while (i5 > 0) {
                        i5--;
                        if (mainItemMapArr[i5].y <= i3) {
                            break;
                        }
                    }
                    if (i4 >= i5) {
                        break;
                    }
                    swap(mainItemMapArr, i4, i5);
                }
            } catch (Exception e) {
                mSystem.println("LOI PAINT partitionIt TRONG UTIL");
                e.printStackTrace();
            }
        }
        swap(mainItemMapArr, i4, i2);
        return i4;
    }

    private static void swap(MainItemMap[] mainItemMapArr, int i, int i2) {
        MainItemMap mainItemMap = mainItemMapArr[i2];
        if (mainItemMapArr[i2].y != mainItemMapArr[i].y) {
            mainItemMapArr[i2] = mainItemMapArr[i];
            mainItemMapArr[i] = mainItemMap;
        }
    }

    public static MainInfoItem[] selectionSort(MainInfoItem[] mainInfoItemArr) {
        int length = mainInfoItemArr.length;
        for (int i = 0; i < length - 1; i++) {
            int i2 = i;
            for (int i3 = i + 1; i3 < length; i3++) {
                if (mainInfoItemArr[i3].id < mainInfoItemArr[i2].id) {
                    i2 = i3;
                }
            }
            if (i2 != i) {
                MainInfoItem mainInfoItem = mainInfoItemArr[i];
                mainInfoItemArr[i] = mainInfoItemArr[i2];
                mainInfoItemArr[i2] = mainInfoItem;
            }
        }
        return mainInfoItemArr;
    }

    public static mVector selectionSortIDSkill(mVector mvector) {
        int size = mvector.size();
        for (int i = 0; i < size - 1; i++) {
            int i2 = i;
            for (int i3 = i + 1; i3 < size; i3++) {
                if (((Skill) mvector.elementAt(i3)).Id < ((Skill) mvector.elementAt(i2)).Id) {
                    i2 = i3;
                }
            }
            if (i2 != i) {
                swapSkill(mvector, i, i2);
            }
        }
        return mvector;
    }

    public static mVector selectionSortSkill(mVector mvector) {
        int size = mvector.size();
        for (int i = 0; i < size - 1; i++) {
            int i2 = i;
            for (int i3 = i + 1; i3 < size; i3++) {
                if (((Skill) mvector.elementAt(i3)).lvMin < ((Skill) mvector.elementAt(i2)).lvMin) {
                    i2 = i3;
                }
            }
            if (i2 != i) {
                swapSkill(mvector, i, i2);
            }
        }
        return mvector;
    }

    private static void swapSkill(mVector mvector, int i, int i2) {
        Object elementAt = mvector.elementAt(i2);
        if (((Skill) mvector.elementAt(i2)).lvMin != ((Skill) mvector.elementAt(i)).lvMin) {
            mvector.setElementAt(mvector.elementAt(i), i2);
            mvector.setElementAt(elementAt, i);
        }
    }

    public static mVector selectionSortInven(mVector mvector) {
        int size = mvector.size();
        for (int i = 0; i < size - 1; i++) {
            int i2 = i;
            for (int i3 = i + 1; i3 < size; i3++) {
                if (((MainItem) mvector.elementAt(i3)).IndexSort < ((MainItem) mvector.elementAt(i2)).IndexSort) {
                    i2 = i3;
                }
            }
            if (i2 != i) {
                swapItem(mvector, i, i2);
            }
        }
        return mvector;
    }

    private static void swapItem(mVector mvector, int i, int i2) {
        Object elementAt = mvector.elementAt(i2);
        mvector.setElementAt(mvector.elementAt(i), i2);
        mvector.setElementAt(elementAt, i);
    }

    public static int readSignByte(InputStream inputStream) {
        byte[] bArr = new byte[1];
        try {
            inputStream.read(bArr, 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
            mSystem.outloi("loi Cres 5");
        }
        return bArr[0];
    }

    public static void setRemoveCharPartInfo() {
        if (charPartInfo == null) {
            return;
        }
        for (int i = 0; i < charPartInfo.length; i++) {
            for (int i2 = 0; i2 < charPartInfo[i].length; i2++) {
                if (charPartInfo[i][i2] != null && (GameCanvas.timeNow - charPartInfo[i][i2].timeRemove) / 1000 > 60) {
                    charPartInfo[i][i2] = null;
                }
            }
        }
    }

    public static synchronized CharPartInfo getCharPartInfo(int i, int i2) {
        if (charPartInfo[i][i2] == null) {
            charPartInfo[i][i2] = new CharPartInfo((byte) i, (short) i2);
        }
        return charPartInfo[i][i2];
    }

    public static void getImgWeaPone(int i, int i2, int i3) {
        try {
            MainObject.imgWeapone[i][i2][i3] = new WeaponInfo();
            if (mSystem.isImgLocal) {
                String[] strArr = {"kiem/", "songkiem/", "phapsu/", "sung/"};
                mImage mimage = null;
                try {
                    mimage = mImage.createImage(new StringBuffer().append(FilePack.weapon).append(strArr[i]).append(i2).append(".img").toString());
                } catch (Exception e) {
                }
                if (mimage != null && mimage.image != null) {
                    DataInputStream dataInputStream = null;
                    try {
                        dataInputStream = mImage.openFile(new StringBuffer().append(FilePack.weapon).append(strArr[i]).append(i2).append("_data").toString());
                    } catch (Exception e2) {
                    }
                    if (dataInputStream != null) {
                        MainObject.imgWeapone[i][i2][i3].img = mimage;
                        for (int i4 = 0; i4 < 4; i4++) {
                            for (int i5 = 0; i5 < 3; i5++) {
                                MainObject.imgWeapone[i][i2][i3].mPos[i4][i5][0] = (byte) dataInputStream.read();
                                MainObject.imgWeapone[i][i2][i3].mPos[i4][i5][1] = (byte) dataInputStream.read();
                            }
                            MainObject.imgWeapone[i][i2][i3].mRegion[i4][0] = (byte) dataInputStream.read();
                            MainObject.imgWeapone[i][i2][i3].mRegion[i4][1] = (byte) dataInputStream.read();
                        }
                        MainObject.imgWeapone[i][i2][i3].himg = mImage.getImageHeight(MainObject.imgWeapone[i][i2][i3].img.image);
                        return;
                    }
                }
            }
            MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar) hashWeapon.get(new StringBuffer().append(i).append("_").append(i2).toString());
            if (mainImageDataPartChar == null) {
                MainImageDataPartChar mainImageDataPartChar2 = new MainImageDataPartChar();
                hashWeapon.put(new StringBuffer().append(i).append("_").append(i2).toString(), mainImageDataPartChar2);
                mainImageDataPartChar2.timeImageNull = GameCanvas.timeNow;
                getFromRms((byte) i, (short) i2);
                MainObject.imgWeapone[i][i2][i3] = null;
            } else if (mainImageDataPartChar.img != null) {
                MainObject.imgWeapone[i][i2][i3].img = mainImageDataPartChar.img;
                DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(mainImageDataPartChar.isData));
                FilePack.reset();
                for (int i6 = 0; i6 < 4; i6++) {
                    for (int i7 = 0; i7 < 3; i7++) {
                        try {
                            MainObject.imgWeapone[i][i2][i3].mPos[i6][i7][0] = (byte) dataInputStream2.read();
                            MainObject.imgWeapone[i][i2][i3].mPos[i6][i7][1] = (byte) dataInputStream2.read();
                        } catch (Exception e3) {
                        }
                    }
                    MainObject.imgWeapone[i][i2][i3].mRegion[i6][0] = (byte) dataInputStream2.read();
                    MainObject.imgWeapone[i][i2][i3].mRegion[i6][1] = (byte) dataInputStream2.read();
                }
                MainObject.imgWeapone[i][i2][i3].himg = mImage.getImageHeight(MainObject.imgWeapone[i][i2][i3].img.image);
                hashWeapon.remove(mainImageDataPartChar);
            } else if ((GameCanvas.timeNow - mainImageDataPartChar.timeImageNull) / 1000 >= 15) {
                getFromRms((byte) i, (short) i2);
            }
        } catch (Exception e4) {
            e4.printStackTrace();
            mSystem.println("loi Cres 7");
        }
        FilePack.reset();
    }

    public static void getFromRms(byte b, short s) {
        MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar) hashWeapon.get(new StringBuffer().append((int) b).append("_").append((int) s).toString());
        if (mainImageDataPartChar != null) {
            mainImageDataPartChar.timeImageNull = GameCanvas.timeNow;
        }
        GlobalService.gI().load_image_data_part_char((byte) (b + 50), s);
    }

    public static WPSplashInfo GetWPSplashInfo(int i, int i2, int i3) {
        try {
            String[] strArr = {"kiem/", "songkiem/", "phapsu/", "sung/"};
            wpSplashInfos[i][i2][i3] = new WPSplashInfo();
            wpSplashInfos[i][i2][i3].image = mImage.createImage(new StringBuffer().append(FilePack.wps).append(strArr[i]).append(i2).append(".img").toString());
            DataInputStream openFile = mImage.openFile(new StringBuffer().append(FilePack.wps).append(strArr[i]).append(i2).append("_data").toString());
            if (openFile != null) {
                for (int i4 = 0; i4 < 4; i4++) {
                    for (int i5 = 0; i5 < 8; i5++) {
                        wpSplashInfos[i][i2][i3].P0_X[i4][i5] = openFile.read();
                        wpSplashInfos[i][i2][i3].P0_Y[i4][i5] = openFile.read();
                        wpSplashInfos[i][i2][i3].PF_X[i4][i5] = readSignByte(openFile);
                        wpSplashInfos[i][i2][i3].PF_Y[i4][i5] = readSignByte(openFile);
                        wpSplashInfos[i][i2][i3].PF_W[i4][i5] = openFile.read();
                        wpSplashInfos[i][i2][i3].PF_H[i4][i5] = openFile.read();
                    }
                }
            }
            FilePack.reset();
        } catch (IOException e) {
            wpSplashInfos[i][i2][i3] = null;
            mSystem.println("loi Cres 8");
            e.printStackTrace();
        }
        return wpSplashInfos[i][i2][i3];
    }

    public static WeaponInfo loadImgWeaPone(int i, int i2, int i3) {
        if (MainObject.imgWeapone[i][i2][i3] == null) {
            Load_Data_And_Img.load.loadImgWeaPone(i, i2, i3);
        }
        return MainObject.imgWeapone[i][i2][i3];
    }

    public static WPSplashInfo loadWpsPlash(int i, int i2, int i3) {
        if (wpSplashInfos[i2][i3] == null) {
            Load_Data_And_Img.load.loadWpsPlash(i2, i3);
        }
        return wpSplashInfos[i][i2][i3];
    }

    public static byte[] encoding(byte[] bArr) {
        if (bArr != null) {
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (bArr[i] ^ (-1));
            }
        }
        return bArr;
    }

    public static void saveRMS(String str, byte[] bArr) throws Exception {
        TemMidlet.saveRMS(str, bArr);
    }

    public static byte[] loadRMS(String str) {
        return TemMidlet.loadRMS(str);
    }

    public static String loadRMSString(String str) {
        byte[] loadRMS = loadRMS(str);
        if (loadRMS == null) {
            return null;
        }
        try {
            return new String(loadRMS, "UTF-8");
        } catch (Exception e) {
            return new String(loadRMS);
        }
    }

    public static void saveRMSString_(String str, String str2) {
        try {
            saveRMS(str, str2.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveRMSName(byte b, byte[] bArr) throws Exception {
        GlobalService.gI().Save_RMS_Server((byte) 0, b, bArr);
    }

    public static void saveRMSString(String str, String str2) throws Exception {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeUTF(str2);
            saveRMS(str, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        } catch (Exception e) {
        }
    }

    public static boolean ktvc(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i > i4 || i2 < i3 || i5 > i8 || i6 < i7) {
            return false;
        }
        return true;
    }

    public static boolean CheckDelRMS(String str) {
        if (str.compareTo("isLowDevice") == 0 || str.compareTo("isQty") == 0 || str.compareTo("user_pass") == 0 || str.compareTo("isIndexPart") == 0 || str.compareTo("isIndexServer") == 0) {
            return false;
        }
        return true;
    }

    public static int rnd(int i, int i2) {
        if (r.nextInt(2) == 0) {
            return i;
        }
        return i2;
    }

    public static int rnd(int i) {
        return r.nextInt(i);
    }

    public static void load_TaiTho() {
        try {
            byte[] loadRMS = loadRMS("taitho");
            if (loadRMS != null) {
                PaintInfoGameScreen.taitho = new DataInputStream(new ByteArrayInputStream(loadRMS)).readByte();
                if (PaintInfoGameScreen.taitho == 0) {
                    PaintInfoGameScreen.xPointMove = 55;
                } else {
                    PaintInfoGameScreen.xPointMove = 85;
                }
            }
        } catch (Exception e) {
        }
    }

    public static void save_TaiTho() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DataOutputStream(byteArrayOutputStream).writeByte(PaintInfoGameScreen.taitho);
            saveRMS("taitho", byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
        }
    }

    public static void saveID_REGION() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new DataOutputStream(byteArrayOutputStream).writeByte(mSystem.ID_REGION);
            saveRMS(RMS_REGION, byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
        }
    }
}
