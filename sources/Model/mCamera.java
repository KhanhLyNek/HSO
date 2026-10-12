package Model;

import GameScreen.GameScreen;
import Main.GameCanvas;

/* loaded from: KnightOnline_v403.jar:Model/mCamera.class */
public class mCamera {
    public static mCamera instance;
    public int xCam;
    public int yCam;
    public int xTo;
    public int yTo;
    public int xLimit;
    public int yLimit;
    public long timeDelay;
    int cmvx;
    int cmdx;
    int cmvy;
    int cmdy;

    public void setAll(int i, int i2, int i3, int i4) {
        this.xLimit = i;
        this.yLimit = i2;
        if (this.yLimit < 0) {
            this.yLimit = 0;
        }
        if (this.xLimit < 0) {
            this.xLimit = 0;
        }
        if (i3 > i) {
            i3 = i;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (i4 > i2) {
            i4 = i2;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        this.xCam = i3;
        this.yCam = i4;
        this.xTo = i3;
        this.yTo = i4;
    }

    public void setAllTo(int i, int i2, int i3, int i4) {
        this.xLimit = i;
        this.yLimit = i2;
        if (this.yLimit < 0) {
            this.yLimit = 0;
        }
        if (this.xLimit < 0) {
            this.xLimit = 0;
        }
        this.xTo = i3;
        this.yTo = i4;
    }

    public void setCamera(int i, int i2) {
        this.xCam = i;
        this.yCam = i2;
        this.xTo = i;
        this.yTo = i2;
    }

    public void setCameraWithLim(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.xLimit) {
            i = this.xLimit;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > this.yLimit) {
            i2 = this.yLimit;
        }
        this.xCam = i;
        this.yCam = i2;
        this.xTo = i;
        this.yTo = i2;
    }

    public void moveCamera(int i, int i2) {
        this.xTo = i;
        this.yTo = i2;
    }

    public void UpdateCamera() {
        if (GameScreen.player.useShip) {
            if (GameCanvas.loadmap.idMap == 19 && this.yCam != 0) {
                this.yTo = 0;
            } else {
                return;
            }
        }
        if (this.xCam != this.xTo) {
            this.cmvx = (this.xTo - this.xCam) << 1;
            this.cmdx += this.cmvx;
            this.xCam += this.cmdx >> 4;
            this.cmdx &= 15;
            if (this.xCam < 0) {
                this.xCam = 0;
            }
            if (this.xCam > this.xLimit) {
                this.xCam = this.xLimit;
            }
        }
        if (this.yCam != this.yTo) {
            this.cmvy = (this.yTo - this.yCam) << 1;
            this.cmdy += this.cmvy;
            this.yCam += this.cmdy >> 4;
            this.cmdy &= 15;
            if (this.yCam < 0) {
                this.yCam = 0;
            }
            if (this.yCam > this.yLimit) {
                this.yCam = this.yLimit;
            }
        }
    }

    public void UpdateCameraCreate() {
        if (this.xCam != this.xTo) {
            this.cmvx = (this.xTo - this.xCam) << 1;
            this.cmdx += this.cmvx;
            this.xCam += this.cmdx >> 4;
            this.cmdx &= 15;
        }
        if (this.yCam != this.yTo) {
            this.cmvy = (this.yTo - this.yCam) << 1;
            this.cmdy += this.cmvy;
            this.yCam += this.cmdy >> 4;
            this.cmdy &= 15;
            if (this.yCam < 0) {
                this.yCam = 0;
            }
            if (this.yCam > this.yLimit) {
                this.yCam = this.yLimit;
            }
        }
    }
}
