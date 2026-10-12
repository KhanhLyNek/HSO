package GameEffect;

import CLib.mGraphics;
import GameObjects.MainObject;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.FrameImage;

/* loaded from: KnightOnline_v403.jar:GameEffect/MainEffect.class */
public class MainEffect {
    public int valueEffect;
    public int timeRemove;
    int fRemove;
    public int x;
    public int y;
    public int toX;
    public int toY;
    public int f;
    public int frame;
    public int ysai;
    public int Direction;
    public int vMax;
    public int vx;
    public int vy;
    public int hOne;
    public int subf;
    long timeBegin;
    public MainObject objBeKillMain;
    public byte[] nFrame;
    public byte[] nSubFrame;
    public FrameImage fraImgEff;
    public FrameImage fraImgSubEff;
    public FrameImage fraImgSub2Eff;
    public FrameImage fraImgSub3Eff;
    public boolean isRemove;
    public boolean isStop;
    boolean isPaint = true;
    public int typeEffect = 0;
    public int levelPaint = 0;

    public void paint(mGraphics mgraphics) {
    }

    public void clearData() {
        if (this.fraImgEff != null) {
            this.fraImgEff.clearData();
            this.fraImgEff = null;
        }
        if (this.fraImgSubEff != null) {
            this.fraImgSubEff.clearData();
            this.fraImgSubEff = null;
        }
        if (this.fraImgSub2Eff != null) {
            this.fraImgSub2Eff.clearData();
            this.fraImgSub2Eff = null;
        }
        if (this.fraImgSub3Eff != null) {
            this.fraImgSub3Eff.clearData();
            this.fraImgSub3Eff = null;
        }
    }

    public void update() {
        this.x += this.vx;
        this.y += this.vy;
    }

    public void setPosition(int i, int i2, int i3, int i4) {
        this.x = i;
        this.y = i2;
        this.toX = i3;
        this.toY = i4;
    }

    public static boolean isInScreen(int i, int i2, int i3, int i4) {
        if (i < MainScreen.cameraMain.xCam - i3 || i > MainScreen.cameraMain.xCam + GameCanvas.w + i3 || i2 < MainScreen.cameraMain.yCam - i4 || i2 > MainScreen.cameraMain.yCam + GameCanvas.h + i4) {
            return false;
        }
        return true;
    }

    public void setObjFrom(short s, byte b) {
    }

    public void setTimeRemove(short s) {
    }
}
