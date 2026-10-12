package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.T;
import Model.mCamera;

/* loaded from: KnightOnline_v403.jar:GameScreen/WorldMapScreen.class */
public class WorldMapScreen extends MainScreen {
    int x;
    int y;
    int dy;
    public int xPoint;
    public int yPoint;
    public int idPoint;
    public int xplayer;
    public int yplayer;
    public static FrameImage fraMyPos;
    int beginx;
    int beginy;
    int xafter;
    int yafter;
    boolean ismove;
    public static byte idMyPos = 5;
    public static String[] namePos = null;
    int timeSetPos = 0;
    int[][] mPosPoint = {new int[]{222, 263}, new int[]{266, 213}, new int[]{266, 213}, new int[]{238, 205}, new int[]{225, 197}, new int[]{201, 195}, new int[]{201, 195}, new int[]{329, 182}, new int[]{317, 161}, new int[]{305, 151}, new int[]{305, 151}, new int[]{362, 189}, new int[]{387, 195}, new int[]{415, 191}, new int[]{415, 191}, new int[]{372, 165}, new int[]{386, 148}, new int[]{390, 134}, new int[]{345, 161}};
    mCamera cam = new mCamera();

    @Override // GameScreen.MainScreen
    public void Show(MainScreen mainScreen) {
        GameCanvas.start_Ok_Dialog(T.lockMap);
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        Show(GameCanvas.currentScreen);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [int[], int[][]] */
    public WorldMapScreen() {
        this.x = (GameCanvas.w - 480) / 2;
        this.y = (GameCanvas.h - 320) / 2;
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y < 0) {
            this.y = 0;
        }
        this.cam.setAll(480 - GameCanvas.w, 320 - GameCanvas.h, 0, 0);
        this.xPoint = this.mPosPoint[1][0];
        this.yPoint = this.mPosPoint[1][1];
        this.cam.xCam = this.xPoint - GameCanvas.hw;
        this.cam.yCam = this.yPoint - GameCanvas.hh;
        if (this.cam.xCam < 0) {
            this.cam.xCam = 0;
        }
        if (this.cam.xCam > this.cam.xLimit) {
            this.cam.xCam = this.cam.xLimit;
        }
        if (this.cam.yCam < 0) {
            this.cam.yCam = 0;
        }
        if (this.cam.yCam > this.cam.yLimit) {
            this.cam.yCam = this.cam.yLimit;
        }
        this.cam.xTo = this.cam.xCam;
        this.cam.yTo = this.cam.yCam;
        iCommand icommand = new iCommand(T.close, -1);
        if (!GameCanvas.isTouch) {
            this.left = new iCommand(T.select, 0);
        } else {
            icommand.setPos(PaintInfoGameScreen.fraBack.frameWidth / 2, GameCanvas.h - (PaintInfoGameScreen.fraBack.frameHeight / 2), PaintInfoGameScreen.fraBack, icommand.caption);
        }
        this.right = icommand;
    }

    public void setPosPlayer(int i) {
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case -1:
                if (this.lastScreen == GameCanvas.AllInfo) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    break;
                } else {
                    this.lastScreen.Show();
                    break;
                }
        }
        super.commandTab(i, i2);
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
            default:
                super.commandPointer(i, i2);
                return;
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        mgraphics.setColor(-11181978);
        mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        mgraphics.translate(-this.cam.xCam, -this.cam.yCam);
        mgraphics.drawImage(AvMain.imgWorldMap, this.x, this.y, 0, false);
        if (this.timeSetPos == -1 && (!GameCanvas.isTouch || GameCanvas.isPointerDown || GameCanvas.isPointerMove)) {
            mFont.tahoma_7b_black.drawString(mgraphics, namePos[this.idPoint], (this.cam.xCam + GameCanvas.w) - 5, this.cam.yCam + 4, 1, false);
            mFont.tahoma_7b_white.drawString(mgraphics, namePos[this.idPoint], (this.cam.xCam + GameCanvas.w) - 4, this.cam.yCam + 5, 1, false);
        }
        fraMyPos.drawFrame((GameCanvas.gameTick / 2) % fraMyPos.nFrame, (this.x + this.xplayer) - 1, (this.y + this.yplayer) - 1, 0, 3, mgraphics);
        if (!GameCanvas.isTouch) {
            mgraphics.drawImage(AvMain.imgSelect, this.x + this.xPoint, (this.y + this.yPoint) - this.dy, 33, false);
        }
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) {
            if (GameCanvas.isTouch) {
                super.paint(mgraphics);
            } else {
                paintCmd_OnlyText(mgraphics);
            }
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        updateKey();
        if (this.timeSetPos > 0) {
            this.timeSetPos--;
            if (this.timeSetPos == 1) {
                int i = 0;
                while (true) {
                    if (i < this.mPosPoint.length) {
                        if (CRes.abs(this.mPosPoint[i][0] - this.xPoint) > 6 || CRes.abs(this.mPosPoint[i][1] - this.yPoint) > 6) {
                            i++;
                        } else {
                            this.xPoint = this.mPosPoint[i][0];
                            this.yPoint = this.mPosPoint[i][1];
                            this.idPoint = i;
                            this.timeSetPos = -1;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        if (this.timeSetPos < 0) {
            this.dy = GameCanvas.gameTick % 4;
        } else {
            this.dy = 0;
        }
        this.cam.UpdateCamera();
    }

    public void updateKey() {
        if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[24]) {
            if (this.xPoint > 10) {
                this.xPoint -= 5;
            } else {
                this.xPoint = 5;
            }
            this.timeSetPos = 3;
            if (this.cam.xLimit > 0) {
                this.cam.xTo -= 5;
            }
        } else if (GameCanvas.keyMyHold[6] || GameCanvas.keyMyHold[26]) {
            if (this.xPoint < 470) {
                this.xPoint += 5;
            } else {
                this.xPoint = 475;
            }
            this.timeSetPos = 3;
            if (this.cam.xLimit > 0) {
                this.cam.xTo += 5;
            }
        } else if (GameCanvas.keyMyHold[2] || GameCanvas.keyMyHold[22]) {
            if (this.yPoint > 10) {
                this.yPoint -= 5;
            } else {
                this.yPoint = 5;
            }
            this.timeSetPos = 3;
            if (this.cam.yLimit > 0) {
                this.cam.yTo -= 5;
            }
        } else if (GameCanvas.keyMyHold[8] || GameCanvas.keyMyHold[28]) {
            if (this.yPoint < 315) {
                this.yPoint += 5;
            } else {
                this.yPoint = 320;
            }
            this.timeSetPos = 3;
            if (this.cam.yLimit > 0) {
                this.cam.yTo += 5;
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        super.updatePointer();
        if (GameCanvas.isPointerMove) {
            if (!this.ismove) {
                this.beginx = GameCanvas.px;
                this.beginy = GameCanvas.py;
                this.xafter = this.cam.xCam;
                this.yafter = this.cam.yCam;
                this.ismove = true;
            } else {
                this.cam.xCam = this.xafter - (GameCanvas.px - this.beginx);
                this.cam.yCam = this.yafter - (GameCanvas.py - this.beginy);
                if (this.cam.xCam < 0) {
                    this.cam.xCam = 0;
                }
                if (this.cam.xCam > this.cam.xLimit) {
                    this.cam.xCam = this.cam.xLimit;
                }
                if (this.cam.yCam < 0) {
                    this.cam.yCam = 0;
                }
                if (this.cam.yCam > this.cam.yLimit) {
                    this.cam.yCam = this.cam.yLimit;
                }
                this.cam.xTo = this.cam.xCam;
                this.cam.yTo = this.cam.yCam;
            }
        }
        if (GameCanvas.isPointerRelease || !GameCanvas.isPointerMove) {
            this.ismove = false;
            this.beginx = 0;
            this.beginy = 0;
        }
        if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
            this.xPoint = (GameCanvas.px + this.cam.xCam) - this.x;
            this.yPoint = (GameCanvas.py + this.cam.yCam) - this.y;
        }
        for (int i = 0; i < this.mPosPoint.length; i++) {
            if (CRes.abs(this.mPosPoint[i][0] - this.xPoint) <= 8 && CRes.abs(this.mPosPoint[i][1] - this.yPoint) <= 8) {
                this.idPoint = i;
                this.timeSetPos = -1;
                return;
            }
        }
        this.timeSetPos = 3;
    }
}
