package Thread_More;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.ObjectData;
import GameObjects.ThacNuoc;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Map.ItemMap;
import Map.MainItemMap;
import Model.CRes;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:Thread_More/LoadMap.class */
public class LoadMap {
    public static final int T_MAP_NULL = -1;
    public static final int T_MAP_NORMAL = 0;
    public static final int T_MAP_STAND = 1;
    public static final int T_MAP_SLOW = 2;
    public static final int MAP_NORMAL = 0;
    public static final int MAP_THACH_DAU = 1;
    public static final int MAP_PHO_BANG = 2;
    public static final int MAP_PET_CONTAINER = 3;
    public int idMap;
    public int idMapMini;
    public int mapW;
    public int mapH;
    public int limitW;
    public int limitH;
    public int limitMap;
    public int maxWMap;
    public int maxHMap;
    public int[] mapPaint;
    public int[] mapType;
    public static int[] mStatusArea;
    public byte[][] mapFind;
    public static mImage imgTile;
    public static mImage imgTileWater;
    public static mImage imgTileSmall;
    public mImage itemLogin0;
    public mImage itemLogin1;
    public mImage itemLogin2;
    int fStand;
    int fWater;
    int fStartWater;
    int fEndWater;
    public int mapWLogin;
    public int mapHLogin;
    public int[] mapPaintLogin;
    public mImage imgTileLogin;
    public static mVector vecMapItem = new mVector("LoadMap vecMapItem");
    public static mVector vecPointChange = new mVector("LoadMap vecPointChange");
    public static mVector mItemMap = new mVector("LoadMap mItemMap");
    public static int[] mTranPointChangeMap = {5, 4, 1, 0};
    public static byte isShowEffAuto = 0;
    public static byte EFF_NORMAL = 0;
    public static byte EFF_PHOBANG_END = 1;
    public static int idTile = -1;
    public static int wTile = 24;
    public static int timeVibrateScreen = 0;
    public static byte Area = 0;
    public static byte MaxArea = 10;
    public static byte typeMap = 0;
    public static long lastTime = 0;
    public static mVector Thacnuoc = new mVector("LoadMap Thacnuoc");
    public static MainItemMap[] mItemMapLogin = new MainItemMap[0];
    public String nameMap = "";
    public int maxX = (GameCanvas.w / wTile) + 1;
    public int maxY = (GameCanvas.h / wTile) + 1;

    public LoadMap() {
        load_Table_Map();
    }

    public boolean mapLang() {
        return GameScreen.isMapLang;
    }

    public void load_Table_Map() {
        try {
            DataInputStream openFile = mImage.openFile("/table_item");
            short readShort = openFile.readShort();
            for (short s = 0; s < readShort; s = (short) (s + 1)) {
                short readShort2 = openFile.readShort();
                openFile.readByte();
                short readShort3 = openFile.readShort();
                short readShort4 = openFile.readShort();
                int readByte = openFile.readByte();
                int[][] new_M_Int = mSystem.new_M_Int(readByte, 2);
                for (int i = 0; i < readByte; i++) {
                    new_M_Int[i][0] = openFile.readByte();
                    new_M_Int[i][1] = openFile.readByte();
                }
                vecMapItem.addElement(new MainItemMap(s, readShort2, readShort3, readShort4, new_M_Int));
            }
        } catch (Exception e) {
            mSystem.println("loi load map 1");
            e.printStackTrace();
        }
    }

    public static void load_Table_Map(byte[] bArr) {
        try {
            vecMapItem.removeAllElements();
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            short readShort = dataInputStream.readShort();
            for (short s = 0; s < readShort; s = (short) (s + 1)) {
                short readShort2 = dataInputStream.readShort();
                dataInputStream.readByte();
                short readShort3 = dataInputStream.readShort();
                short readShort4 = dataInputStream.readShort();
                int readByte = dataInputStream.readByte();
                int[][] new_M_Int = mSystem.new_M_Int(readByte, 2);
                for (int i = 0; i < readByte; i++) {
                    new_M_Int[i][0] = dataInputStream.readByte();
                    new_M_Int[i][1] = dataInputStream.readByte();
                }
                vecMapItem.addElement(new MainItemMap(s, readShort2, readShort3, readShort4, new_M_Int));
            }
        } catch (Exception e) {
            mSystem.outloi("loi load map 1");
        }
    }

    public void loadmap(byte[] bArr) {
        try {
            Thacnuoc.removeAllElements();
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            this.mapW = dataInputStream.readByte();
            this.mapH = dataInputStream.readByte();
            byte readByte = dataInputStream.readByte();
            if (idTile != readByte) {
                idTile = readByte;
                imgTile = null;
                imgTileWater = null;
                imgTileSmall = null;
                imgTile = mImage.createImage(new StringBuffer().append("/tile").append(idTile).append(".png").toString());
                if (imgTile == null || imgTile.image == null) {
                    imgTile = getTileMapDataFromRMS((byte) 100, (short) idTile);
                }
                if (imgTile == null || imgTile.image == null) {
                    GlobalService.gI().load_image((short) (idTile + ObjectData.IMAGE_TILE));
                }
                imgTileWater = mImage.createImage(new StringBuffer().append("/tilewater").append(idTile).append(".png").toString());
                if (imgTileWater == null || imgTileWater.image == null) {
                    imgTileWater = getTileMapDataFromRMS((byte) 102, (short) idTile);
                }
                if (imgTileWater == null || imgTileWater.image == null) {
                    GlobalService.gI().load_image((short) (idTile + ObjectData.IMAGE_TILE_WATER));
                }
                imgTileSmall = mImage.createImage(new StringBuffer().append("/tile_small").append(idTile).append(".png").toString());
                if (imgTileSmall == null || imgTileSmall.image == null) {
                    imgTileSmall = getTileMapDataFromRMS((byte) 101, (short) idTile);
                }
                if (imgTileSmall == null || imgTileSmall.image == null) {
                    GlobalService.gI().load_image((short) (idTile + ObjectData.IMAGE_TILE_SMALL));
                }
                DataInputStream openFile = mImage.openFile(new StringBuffer().append("/tile_map_info_").append(idTile).toString());
                if (!mSystem.isMaHoa) {
                    this.fWater = openFile.read();
                    this.fStand = openFile.read();
                    if (isTileMoi()) {
                        if (idTile == 9) {
                            this.fWater = 127;
                            this.fStand = 19;
                            this.fStartWater = 19;
                            this.fEndWater = 26;
                        } else if (idTile == 10) {
                            this.fWater = 127;
                            this.fStand = 5;
                            this.fStartWater = 4;
                            this.fEndWater = 25;
                        } else if (idTile == 11) {
                            this.fWater = 127;
                            this.fStand = 21;
                            this.fStartWater = 20;
                            this.fEndWater = 37;
                        } else if (idTile == 12) {
                            this.fWater = 127;
                            this.fStand = 34;
                            this.fStartWater = 35;
                            this.fEndWater = 44;
                        } else if (idTile == 13) {
                            this.fWater = 0;
                            this.fStand = 47;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 14) {
                            this.fWater = 0;
                            this.fStand = 26;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 15) {
                            this.fWater = 0;
                            this.fStand = 9;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 16) {
                            this.fWater = 0;
                            this.fStand = 42;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        }
                    }
                } else {
                    DataInputStream dataInputStream2 = new DataInputStream(openFile);
                    byte[] bArr2 = new byte[dataInputStream2.available()];
                    dataInputStream2.read(bArr2);
                    for (int i = 0; i < bArr2.length; i++) {
                        bArr2[i] = (byte) (bArr2[i] ^ 3);
                    }
                    DataInputStream dataInputStream3 = new DataInputStream(new ByteArrayInputStream(bArr2));
                    this.fWater = dataInputStream3.read();
                    this.fStand = dataInputStream3.read();
                    if (isTileMoi()) {
                        if (idTile == 9) {
                            this.fWater = 127;
                            this.fStand = 19;
                            this.fStartWater = 19;
                            this.fEndWater = 26;
                        } else if (idTile == 10) {
                            this.fWater = 127;
                            this.fStand = 5;
                            this.fStartWater = 4;
                            this.fEndWater = 25;
                        } else if (idTile == 11) {
                            this.fWater = 127;
                            this.fStand = 21;
                            this.fStartWater = 20;
                            this.fEndWater = 37;
                        } else if (idTile == 12) {
                            this.fWater = 127;
                            this.fStand = 34;
                            this.fStartWater = 35;
                            this.fEndWater = 44;
                        } else if (idTile == 13) {
                            this.fWater = 0;
                            this.fStand = 47;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 14) {
                            this.fWater = 0;
                            this.fStand = 26;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 15) {
                            this.fWater = 0;
                            this.fStand = 9;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        } else if (idTile == 16) {
                            this.fWater = 0;
                            this.fStand = 42;
                            this.fStartWater = 0;
                            this.fEndWater = 0;
                        }
                    }
                }
            }
            this.maxWMap = this.mapW * wTile;
            this.maxHMap = this.mapH * wTile;
            this.limitW = this.maxWMap - GameCanvas.w;
            this.limitH = this.maxHMap - GameCanvas.h;
            MainScreen.cameraMain.setAll(this.limitW, this.limitH, GameScreen.player.x - GameCanvas.hw, GameScreen.player.y - GameCanvas.hh);
            this.mapPaint = new int[this.mapW * this.mapH];
            this.mapType = new int[this.mapW * this.mapH];
            this.mapFind = new byte[this.mapW][this.mapH];
            this.limitMap = this.mapW * this.mapH;
            for (int i2 = 0; i2 < this.mapW * this.mapH; i2++) {
                byte readByte2 = dataInputStream.readByte();
                this.mapPaint[i2] = readByte2;
                if (!isTileMoi()) {
                    if (readByte2 >= this.fStand || readByte2 == 0) {
                        this.mapType[i2] = 1;
                    } else if (readByte2 >= this.fWater) {
                        this.mapType[i2] = 2;
                    } else {
                        this.mapType[i2] = 0;
                    }
                } else {
                    if (idTile == 9) {
                        if (readByte2 == 27 || readByte2 == 28 || readByte2 == 29 || readByte2 == 30 || readByte2 == 31) {
                            Thacnuoc.addElement(new ThacNuoc(readByte2 - 27, (i2 % this.mapW) * 24, (i2 / this.mapW) * 24));
                        }
                    } else if (idTile == 10) {
                        if (readByte2 == 18 || readByte2 == 19) {
                            Thacnuoc.addElement(new ThacNuoc((readByte2 - 18) + 5, (i2 % this.mapW) * 24, (i2 / this.mapW) * 24));
                        }
                    } else if (idTile == 11) {
                        if (readByte2 == 38 || readByte2 == 39) {
                            Thacnuoc.addElement(new ThacNuoc((readByte2 - 38) + 7, (i2 % this.mapW) * 24, (i2 / this.mapW) * 24));
                        }
                    } else if (idTile != 12 && idTile != 13 && idTile == 14) {
                    }
                    if (readByte2 >= this.fStand || readByte2 == 0) {
                        this.mapType[i2] = 1;
                    } else {
                        this.mapType[i2] = 0;
                    }
                }
            }
            isShowEffAuto = EFF_NORMAL;
        } catch (IOException e) {
            e.printStackTrace();
            mSystem.outloi("loi load map 2");
        }
    }

    public void load_ItemMap(byte[] bArr) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            mItemMap.removeAllElements();
            int readShort = dataInputStream.readShort();
            int i = 0;
            for (int i2 = 0; i2 < readShort; i2++) {
                MainItemMap mainItemMap = get_Item(dataInputStream.readShort());
                if (mainItemMap == null) {
                    mainItemMap = get_Item(85);
                }
                ItemMap itemMap = new ItemMap(mainItemMap.IDItem, mainItemMap.IDImage, mainItemMap.dx, mainItemMap.dy, mainItemMap.Block);
                short readShort2 = dataInputStream.readShort();
                short readShort3 = dataInputStream.readShort();
                if (!GameCanvas.lowGraphic || mainItemMap.Block.length > 0) {
                    Block_TileMap_Item(readShort2, readShort3, mainItemMap.Block);
                    itemMap.setInfoItem(readShort2 * wTile, readShort3 * wTile);
                    mItemMap.addElement(itemMap);
                    i++;
                }
            }
            if (dataInputStream.available() > 0 && !GameCanvas.lowGraphic) {
                int readShort4 = dataInputStream.readShort();
                for (int i3 = 0; i3 < readShort4; i3++) {
                    int readByte = dataInputStream.readByte();
                    String str = "";
                    for (int i4 = 0; i4 < readByte; i4++) {
                        str = new StringBuffer().append(str).append((char) dataInputStream.readByte()).toString();
                    }
                    int readByte2 = dataInputStream.readByte();
                    String str2 = "";
                    for (int i5 = 0; i5 < readByte2; i5++) {
                        str2 = new StringBuffer().append(str2).append((char) dataInputStream.readByte()).toString();
                    }
                    if (!mSystem.isj2me) {
                        mItemMap.addElement(GameScreen.addEffectAuto(str, str2));
                    }
                }
            }
            CRes.quickSort1(mItemMap);
        } catch (Exception e) {
            mSystem.println("loi load map 3");
            e.printStackTrace();
        }
    }

    public void paint(mGraphics mgraphics) {
        try {
            int i = (MainScreen.cameraMain.xCam / wTile) - 1;
            int i2 = (MainScreen.cameraMain.yCam / wTile) - 1;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int i3 = i + this.maxX + 2;
            int i4 = i2 + this.maxY + 2;
            if (i3 > this.mapW) {
                i3 = this.mapW;
            }
            if (i4 > this.mapH) {
                i4 = this.mapH;
            }
            for (int i5 = i; i5 < i3; i5++) {
                for (int i6 = i2; i6 < i4; i6++) {
                    if (!LoadMapScreen.isNextMap) {
                        break;
                    }
                    int i7 = this.mapPaint[(i6 * this.mapW) + i5] - 1;
                    if (!isTileMoi()) {
                        if (i7 >= this.fWater - 1 && i7 < this.fStand - 1 && ((GameCanvas.gameTick % 14 < 7 && i5 % 2 == 0) || (GameCanvas.gameTick % 14 > 7 && i5 % 2 != 0))) {
                            if (imgTileWater != null && imgTileWater.image != null) {
                                mgraphics.drawRegion(imgTileWater, ((i7 - (this.fWater - 1)) / 10) * wTile, ((i7 - (this.fWater - 1)) % 10) * wTile, wTile, wTile, 0, i5 * wTile, i6 * wTile, 0, false);
                            }
                        } else if (i7 > -1 && imgTile != null && imgTile.image != null) {
                            mgraphics.drawRegion(imgTile, 0, i7 * wTile, wTile, wTile, 0, i5 * wTile, i6 * wTile, 0, false);
                        }
                    } else if (i7 >= this.fStartWater - 1 && i7 < this.fEndWater && ((GameCanvas.gameTick % 14 < 7 && i5 % 2 == 0) || (GameCanvas.gameTick % 14 > 7 && i5 % 2 != 0))) {
                        if (imgTileWater != null && imgTileWater.image != null) {
                            mgraphics.drawRegion(imgTileWater, ((i7 - (this.fStartWater - 1)) / 10) * wTile, ((i7 - (this.fStartWater - 1)) % 10) * wTile, wTile, wTile, 0, i5 * wTile, i6 * wTile, 0, false);
                        }
                    } else if (i7 > -1 && imgTile != null && imgTile.image != null) {
                        mgraphics.drawRegion(imgTile, 0, i7 * wTile, wTile, wTile, 0, i5 * wTile, i6 * wTile, 0, false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
    }

    public int getTile(int i, int i2) {
        int i3 = ((i2 / wTile) * this.mapW) + (i / wTile);
        if (i3 > this.limitMap || i < 0 || i >= this.limitW + GameCanvas.w || i2 < 0 || i2 >= this.limitH + GameCanvas.h) {
            return 1;
        }
        return this.mapType[i3];
    }

    public int getIndex(int i, int i2) {
        return ((i2 / wTile) * this.mapW) + (i / wTile);
    }

    public MainItemMap get_Item(int i) {
        for (int i2 = 0; i2 < vecMapItem.size(); i2++) {
            MainItemMap mainItemMap = (MainItemMap) vecMapItem.elementAt(i2);
            if (mainItemMap.IDItem == i) {
                return mainItemMap;
            }
        }
        return null;
    }

    public void Block_TileMap_Item(int i, int i2, int[][] iArr) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            try {
                if (i + iArr[i3][0] >= 0 && i + iArr[i3][0] < this.mapW && i2 + iArr[i3][1] >= 0 && i2 + iArr[i3][1] < this.mapH) {
                    this.mapType[((i2 + iArr[i3][1]) * this.mapW) + i + iArr[i3][0]] = 1;
                }
            } catch (Exception e) {
                mSystem.println("loi load map 4");
                e.printStackTrace();
                return;
            }
        }
    }

    public void setBlockNPC(int i, int i2, int i3, int i4) {
        if (this.mapW == 0) {
            this.mapW = 1;
        }
        int index = getIndex(i, i2);
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                if (((index % this.mapW) - (i3 / 2)) + i5 >= 0 && ((index % this.mapW) - (i3 / 2)) + i5 < this.mapW && ((index / this.mapW) - (i4 / 2)) + i6 >= 0 && (index / this.mapW) - (i4 / 2) < this.mapH) {
                    this.mapType[((((index / this.mapW) - (i4 / 2)) + i6) * this.mapW) + ((index % this.mapW) - (i3 / 2)) + i5] = 1;
                }
            }
        }
    }

    public void setBlockNPC_Server(int i, int i2, int i3, int i4) {
        if (this.mapW == 0) {
            this.mapW = 1;
        }
        int index = getIndex(i, i2);
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                if (((index % this.mapW) - (i3 / 2)) + i5 >= 0 && ((index % this.mapW) - (i3 / 2)) + i5 < this.mapW && ((index / this.mapW) - (i4 / 2)) + i6 >= 0 && (index / this.mapW) - (i4 / 2) < this.mapH) {
                    this.mapType[((((index / this.mapW) - (i4 / 2)) + i6) * this.mapW) + (index % this.mapW) + i5] = 1;
                }
            }
        }
    }

    public static byte getAreaPaint() {
        return (byte) (Area + 1);
    }

    public static String getTimeSpecialRegion() {
        String stringBuffer;
        long currentTimeMillis = (GameScreen.timeSpRegion - mSystem.currentTimeMillis()) / 1000;
        if (currentTimeMillis <= 0) {
            return "";
        }
        long j = currentTimeMillis / 3600;
        long j2 = currentTimeMillis / 60;
        if (j > 0) {
            stringBuffer = new StringBuffer().append(j).append("h").append((currentTimeMillis % 3600) / 60).append("'").toString();
        } else if (j2 <= 0) {
            stringBuffer = new StringBuffer().append(currentTimeMillis).append("s").toString();
        } else {
            stringBuffer = new StringBuffer().append(j2).append("p").append(currentTimeMillis % 60).append("s").toString();
        }
        return new StringBuffer().append(GameScreen.nameSpecialRegion).append("").append(stringBuffer).toString();
    }

    public static mImage getTileMapDataFromRMS(byte b, short s) {
        mImage mimage = new mImage();
        lastTime = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char(b, s);
        return mimage;
    }

    public static boolean isTileMoi() {
        if (idTile < 9) {
            return false;
        }
        return true;
    }

    public static String getTimeArena(long j) {
        String stringBuffer;
        long currentTimeMillis = ((j + 3600000) - mSystem.currentTimeMillis()) / 1000;
        if (currentTimeMillis <= 0) {
            return "";
        }
        long j2 = currentTimeMillis / 60;
        if (j2 <= 0) {
            stringBuffer = currentTimeMillis < 10 ? new StringBuffer().append("0").append(currentTimeMillis).append("s").toString() : new StringBuffer().append(currentTimeMillis).append("s").toString();
        } else if (j2 < 10) {
            if (currentTimeMillis % 60 >= 0 && currentTimeMillis % 60 < 10) {
                stringBuffer = new StringBuffer().append("0").append(j2).append(":").append("0").append(currentTimeMillis % 60).toString();
            } else {
                stringBuffer = new StringBuffer().append("0").append(j2).append(":").append(currentTimeMillis % 60).toString();
            }
        } else if (currentTimeMillis % 60 >= 0 && currentTimeMillis % 60 < 10) {
            stringBuffer = new StringBuffer().append(j2).append(":").append("0").append(currentTimeMillis % 60).toString();
        } else {
            stringBuffer = new StringBuffer().append(j2).append(":").append(currentTimeMillis % 60).toString();
        }
        return stringBuffer;
    }

    public static String convertSecondsToHMmSs(long j) {
        int i = ((int) (j / 1000)) % 60;
        int i2 = (int) ((j / 60000) % 60);
        int i3 = (int) ((j / 3600000) % 24);
        if (i <= 0 && i3 <= 0 && i2 <= 0) {
            return "0h: 0': 0";
        }
        return new StringBuffer().append(i3).append("h: ").append(i2).append("': ").append(i).toString();
    }

    public static String getTimeCountDown(long j, int i) {
        String stringBuffer;
        long currentTimeMillis = ((j + (i * ObjectData.MONSTER)) - mSystem.currentTimeMillis()) / 1000;
        if (currentTimeMillis <= 0) {
            return "";
        }
        long j2 = currentTimeMillis / 60;
        if (j2 <= 0) {
            stringBuffer = currentTimeMillis < 10 ? new StringBuffer().append("0").append(currentTimeMillis).append("s").toString() : new StringBuffer().append(currentTimeMillis).append("s").toString();
        } else if (j2 < 10) {
            if (currentTimeMillis % 60 >= 0 && currentTimeMillis % 60 < 10) {
                stringBuffer = new StringBuffer().append("0").append(j2).append(":").append("0").append(currentTimeMillis % 60).toString();
            } else {
                stringBuffer = new StringBuffer().append("0").append(j2).append(":").append(currentTimeMillis % 60).toString();
            }
        } else if (currentTimeMillis % 60 >= 0 && currentTimeMillis % 60 < 10) {
            stringBuffer = new StringBuffer().append(j2).append(":").append("0").append(currentTimeMillis % 60).toString();
        } else {
            stringBuffer = new StringBuffer().append(j2).append(":").append(currentTimeMillis % 60).toString();
        }
        return stringBuffer;
    }

    public static String converSecon2hours(int i) {
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = i3 % 60;
        int i5 = i3 / 60;
        if (i5 > 0) {
            return new StringBuffer().append(i5).append(":").append(i4).toString();
        }
        if (i4 > 0) {
            return new StringBuffer().append(i4).append(":").append(i2).toString();
        }
        if (i2 < 10) {
            return new StringBuffer().append("0:").append(i2).toString();
        }
        return new StringBuffer().append(i2).append("").toString();
    }
}
