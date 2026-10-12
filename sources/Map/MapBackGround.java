package Map;

import CLib.mGraphics;
import CLib.mImage;
import GameEffect.EffectSkill;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.Point;
import Thread_More.LoadMap;

/* loaded from: KnightOnline_v403.jar:Map/MapBackGround.class */
public class MapBackGround {
    byte typeMap;
    byte[] mList3;
    int w1;
    int w2;
    int w3;
    int size1;
    int size2;
    int size3;
    int h1;
    int h2;
    int h3;
    int sizeScreen;
    int hBack;
    int hLimit;
    int valueRandom;
    int sizeScreen2;
    int nX;
    Point[] PosCloud;
    mImage[] mImg;
    mImage[] mImgCloud;
    public int color;
    public int colorMini;

    public void setBackGround(byte b, short s) {
        this.typeMap = b;
        this.hBack = s;
        if (GameCanvas.lowGraphic) {
            this.color = -9784873;
            this.colorMini = -9784873;
            return;
        }
        switch (this.typeMap) {
            case 1:
                this.w1 = 256;
                this.size1 = ((GameCanvas.loadmap.mapW * LoadMap.wTile) / this.w1) + 1;
                this.color = -8725531;
                this.colorMini = -8725531;
                this.h1 = 92;
                this.h2 = 85;
                this.h3 = EffectSkill.EFF_KIEM_LON_NGU;
                this.hLimit = this.hBack - ((this.h1 + this.h2) + this.h3);
                this.sizeScreen = (GameCanvas.w / this.w1) + 1;
                this.mImg = new mImage[3];
                this.mImg[0] = mImage.createImage("/bg/bg1_0.img");
                this.mImg[1] = mImage.createImage("/bg/bg1_1.img");
                this.mImg[2] = mImage.createImage("/bg/bg1_2.img");
                this.valueRandom = (this.hLimit + 30) / 2;
                break;
            case 2:
                this.w1 = EffectSkill.EFF_BIG_SWORD_115_2;
                this.size1 = ((GameCanvas.loadmap.mapW * LoadMap.wTile) / this.w1) + 1;
                this.color = -10836481;
                this.colorMini = -11759720;
                this.h1 = 72;
                this.h2 = 28;
                this.h3 = 77;
                this.hLimit = this.hBack - ((this.h1 + this.h2) + this.h3);
                this.sizeScreen = (GameCanvas.w / this.w1) + 1;
                this.mImg = new mImage[5];
                this.mImg[0] = mImage.createImage("/bg/bg2_0.img");
                this.mImg[1] = mImage.createImage("/bg/bg2_1.img");
                this.mImg[2] = mImage.createImage("/bg/bg2_20.img");
                this.mImg[3] = mImage.createImage("/bg/bg2_21.img");
                this.mImg[4] = mImage.createImage("/bg/bg2_22.img");
                this.mList3 = new byte[this.size1];
                for (int i = 0; i < this.size1; i++) {
                    this.mList3[i] = (byte) CRes.random(3);
                }
                this.valueRandom = 25;
                break;
            case 3:
                this.w1 = 253;
                this.w2 = 96;
                this.size1 = ((GameCanvas.loadmap.mapW * LoadMap.wTile) / this.w1) + 1;
                this.size2 = ((GameCanvas.loadmap.mapW * LoadMap.wTile) / this.w2) + 1;
                this.color = -9784873;
                this.colorMini = -9784873;
                this.h1 = 108;
                this.h2 = 72;
                this.nX = (this.hBack - EffectSkill.EFF_BIG_SWORD_115_2) / this.h2;
                if (this.nX > 5) {
                    this.nX = 5;
                }
                this.hLimit = this.hBack - (this.h1 + (this.h2 * this.nX));
                this.sizeScreen = (GameCanvas.w / this.w1) + 1;
                this.sizeScreen2 = (GameCanvas.w / this.w2) + 1;
                this.mImg = new mImage[2];
                this.mImg[0] = mImage.createImage("/bg/bg3_0.img");
                this.mImg[1] = mImage.createImage("/bg/bg3_1.img");
                this.valueRandom = (this.hLimit + 30) / 2;
                break;
        }
        this.PosCloud = new Point[((GameCanvas.loadmap.mapW * LoadMap.wTile) / 250) + 1];
        for (int i2 = 0; i2 < this.PosCloud.length; i2++) {
            this.PosCloud[i2] = new Point();
            this.PosCloud[i2].x = 125 + CRes.random_Am_0(125) + (i2 * 250);
            this.PosCloud[i2].y = this.valueRandom + CRes.random_Am_0(this.valueRandom);
            this.PosCloud[i2].vx = -CRes.random(1, 3);
            this.PosCloud[i2].frame = CRes.random(0, 2);
        }
        this.mImgCloud = new mImage[2];
        this.mImgCloud[0] = mImage.createImage("/bg/may0.img");
        this.mImgCloud[1] = mImage.createImage("/bg/may1.img");
    }

    public void paint(mGraphics mgraphics) {
        if (GameCanvas.lowGraphic) {
            if (MainScreen.cameraMain.yCam <= this.hBack) {
                mgraphics.setColor(this.color);
                mgraphics.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, GameCanvas.h, false);
                return;
            }
            return;
        }
        switch (this.typeMap) {
            case 1:
                int i = MainScreen.cameraMain.xCam / this.w1;
                int i2 = i + this.sizeScreen + 1;
                if (i2 > this.size1) {
                    i2 = this.size1;
                }
                if (MainScreen.cameraMain.yCam <= this.hLimit) {
                    mgraphics.setColor(this.color);
                    mgraphics.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, this.hLimit, false);
                }
                for (int i3 = i; i3 < i2; i3++) {
                    if (MainScreen.cameraMain.yCam <= this.h1 + this.hLimit) {
                        mgraphics.drawImage(this.mImg[0], i3 * this.w1, this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam <= this.h2 + this.h1 + this.hLimit) {
                        mgraphics.drawImage(this.mImg[1], i3 * this.w1, this.h1 + this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam <= this.h2 + this.h1 + this.h3 + this.hLimit) {
                        mgraphics.drawImage(this.mImg[2], i3 * this.w1, this.h1 + this.h2 + this.hLimit, 0, false);
                    }
                }
                break;
            case 2:
                int i4 = MainScreen.cameraMain.xCam / this.w1;
                if (i4 < 0) {
                    i4 = 0;
                }
                int i5 = i4 + this.sizeScreen + 1;
                if (i5 > this.size1) {
                    i5 = this.size1;
                }
                if (MainScreen.cameraMain.yCam <= this.hLimit) {
                    mgraphics.setColor(this.color);
                    mgraphics.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, this.hLimit, false);
                }
                for (int i6 = i4; i6 < i5; i6++) {
                    if (MainScreen.cameraMain.yCam <= this.h1 + this.hLimit) {
                        mgraphics.drawImage(this.mImg[0], i6 * this.w1, this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam <= this.h2 + this.h1 + this.hLimit) {
                        mgraphics.drawImage(this.mImg[1], i6 * this.w1, this.h1 + this.hLimit, 0, false);
                    }
                    if (MainScreen.cameraMain.yCam <= this.h2 + this.h1 + this.h3 + this.hLimit) {
                        mgraphics.drawImage(this.mImg[2 + this.mList3[i6]], i6 * this.w1, this.h1 + this.h2 + this.hLimit, 0, false);
                    }
                }
                break;
            case 3:
                if (MainScreen.cameraMain.yCam <= this.hLimit) {
                    mgraphics.setColor(this.color);
                    mgraphics.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, this.hLimit, false);
                }
                int i7 = MainScreen.cameraMain.xCam / this.w2;
                if (i7 < 0) {
                    i7 = 0;
                }
                int i8 = i7 + this.sizeScreen2 + 1;
                if (i8 > this.size2) {
                    i8 = this.size2;
                }
                for (int i9 = i7; i9 < i8; i9++) {
                    for (int i10 = 0; i10 < this.nX; i10++) {
                        mgraphics.drawImage(this.mImg[1], i9 * this.w2, this.hLimit + this.h1 + (i10 * this.h2), 0, false);
                    }
                }
                int i11 = MainScreen.cameraMain.xCam / this.w1;
                if (i11 < 0) {
                    i11 = 0;
                }
                int i12 = i11 + this.sizeScreen + 1;
                if (i12 > this.size1) {
                    i12 = this.size1;
                }
                for (int i13 = i11; i13 < i12; i13++) {
                    if (MainScreen.cameraMain.yCam <= this.h1 + this.hLimit) {
                        mgraphics.drawImage(this.mImg[0], i13 * this.w1, this.hLimit, 0, false);
                    }
                }
                break;
        }
        for (int i14 = 0; i14 < this.PosCloud.length; i14++) {
            if (MainScreen.cameraMain.yCam - 10 <= this.PosCloud[i14].y) {
                mgraphics.drawImage(this.mImgCloud[this.PosCloud[i14].frame], this.PosCloud[i14].x, this.PosCloud[i14].y, 6, false);
            }
        }
    }

    public void updateCloud() {
        if (GameCanvas.lowGraphic) {
            return;
        }
        for (int i = 0; i < this.PosCloud.length; i++) {
            this.PosCloud[i].x += this.PosCloud[i].vx;
            if (this.PosCloud[i].x < -80) {
                this.PosCloud[i].x = (GameCanvas.loadmap.mapW * LoadMap.wTile) + CRes.random_Am_0(125);
                this.PosCloud[i].y = this.valueRandom + CRes.random_Am_0(this.valueRandom);
                this.PosCloud[i].vx = -CRes.random(1, 3);
                this.PosCloud[i].frame = CRes.random(0, 2);
            }
        }
    }
}
