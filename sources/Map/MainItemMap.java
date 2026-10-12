package Map;

import CLib.mGraphics;
import CLib.mImage;
import GameObjects.ObjectData;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.MainImage;

/* loaded from: KnightOnline_v403.jar:Map/MainItemMap.class */
public class MainItemMap {
    public byte TypeItem;
    public static final byte ITEM_MAP = 0;
    public static final byte EFF_MAP = 1;
    public static final byte EFF_FROM_SV = 2;
    public static final byte EFF_SKILL_FROM_SV = 3;
    public static final byte EFF_MAT_NA = 4;
    public short IDItem;
    public short IDImage;
    public int dx;
    public int dy;
    public int x;
    public int y;
    public int wOne;
    public int hOne;
    public short idActor;
    public int[][] Block;
    mImage img;

    public MainItemMap() {
    }

    public void setDataEff(byte[] bArr) {
    }

    public MainItemMap(short s, short s2, int i, int i2, int[][] iArr) {
        this.IDItem = s;
        this.IDImage = s2;
        this.dx = i;
        this.dy = i2;
        this.Block = iArr;
    }

    public void paint(mGraphics mgraphics) {
    }

    public void update() {
    }

    public boolean isInScreen() {
        if (this.hOne == 0 || this.wOne == 0) {
            MainImage imagePartItemMap = ObjectData.getImagePartItemMap(this.IDItem);
            if (imagePartItemMap.img != null) {
                this.wOne = mImage.getImageWidth(imagePartItemMap.img.image);
                this.hOne = mImage.getImageHeight(imagePartItemMap.img.image);
            }
        }
        if (this.x + this.dx + this.wOne < MainScreen.cameraMain.xCam || (this.x + this.dx) - this.wOne > MainScreen.cameraMain.xCam + GameCanvas.w || this.y + this.dy + this.hOne < MainScreen.cameraMain.yCam || (this.y + this.dy) - this.hOne > MainScreen.cameraMain.yCam + GameCanvas.h) {
            return false;
        }
        return true;
    }
}
