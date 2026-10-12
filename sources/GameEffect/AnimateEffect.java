package GameEffect;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.Point;

/* loaded from: KnightOnline_v403.jar:GameEffect/AnimateEffect.class */
public class AnimateEffect extends MainEffect {
    public static final byte RAIN = 0;
    public static final byte FALLING_LEAVES = 1;
    public static final byte SNOW = 2;
    public static final byte FALLING_FLOWER = 3;
    public static final byte THIEN_THACH = 4;
    public static final byte CLOUD = 5;
    public byte type;
    public int number;
    public int timeLimit;
    public int numClound;
    public boolean isStop;
    private static int countWind;
    public mImage imgEffect;
    public mImage[] imgEffectCloud;
    int wimg;
    int himg;
    int vx;
    int vy;
    private static int wind = 5;
    private static int dirWind = CRes.random_Am_0(2);
    public mVector list = new mVector("AnimateEffect list");
    public int curTime = (int) (GameCanvas.timeNow / 1000);

    public void close() {
    }

    public void stop() {
        this.isStop = true;
    }

    public AnimateEffect(byte b, boolean z, int i, int i2) {
        Point point;
        this.type = (byte) 0;
        this.number = 0;
        this.timeLimit = i2;
        this.type = b;
        int i3 = 1;
        switch (b) {
            case 1:
                i3 = 3;
                this.imgEffect = mImage.createImage("/efleaf.img");
                break;
            case 2:
                i3 = 2;
                this.imgEffect = mImage.createImage("/efsnow.img");
                break;
            case 3:
                this.imgEffect = mImage.createImage("/efhoa.img");
                break;
            case 4:
                i3 = 12;
                break;
            case 5:
                this.isStop = true;
                return;
        }
        if (i > 0) {
            this.number = i;
        } else if (i == -1) {
            this.number = (GameCanvas.w * GameCanvas.h) / ((8 * i3) * 200);
        } else if (i == -2) {
            this.number = (GameCanvas.w * GameCanvas.h) / ((6 * i3) * 200);
        } else if (i == -3) {
            this.number = (GameCanvas.w * GameCanvas.h) / ((3 * i3) * 200);
        } else if (i == -4) {
            this.number = (GameCanvas.w * GameCanvas.h) / ((2 * i3) * 200);
        } else {
            this.number = 10;
        }
        if (b != 4) {
            for (int i4 = 0; i4 < this.number; i4++) {
                if (z) {
                    point = new Point(((MainScreen.cameraMain.xCam - GameCanvas.hw) + CRes.random(GameCanvas.w * 2)) * 10, ((MainScreen.cameraMain.yCam - (GameCanvas.h * 2)) + CRes.random(GameCanvas.h * 2)) * 10);
                } else {
                    point = new Point();
                    rndPos(point);
                }
                if (b == 2 || this.type == 3) {
                    point.h = CRes.random(3);
                } else {
                    point.h = CRes.random(4);
                }
                if (b == 5) {
                    point.vx = CRes.random(2, 8);
                    point.vy = CRes.random(2, 12);
                }
                point.limitY = 16 + (CRes.random(3) * 4);
                point.v = CRes.random(-1, 1);
                point.color = CRes.random(point.limitY);
                point.frame = CRes.random(2);
                if (b == 2) {
                    point.dis = (byte) CRes.random(6);
                } else if (b == 1) {
                    point.dis = (byte) CRes.random(4);
                } else {
                    point.dis = (byte) CRes.random(20);
                }
                this.list.addElement(point);
            }
        }
    }

    @Override // GameEffect.MainEffect
    public void paint(mGraphics mgraphics) {
        GameCanvas.resetTrans(mgraphics);
        mgraphics.translate(-MainScreen.cameraMain.xCam, -MainScreen.cameraMain.yCam);
        switch (this.type) {
            case 0:
                paintRain(mgraphics);
                return;
            case 1:
                paintFallingLeaves(mgraphics);
                return;
            case 2:
                paintSnow(mgraphics);
                return;
            case 3:
                paintFallingFlower(mgraphics);
                return;
            case 4:
            default:
                return;
            case 5:
                paintCloud(mgraphics);
                return;
        }
    }

    private void paintCloud(mGraphics mgraphics) {
        if (this.imgEffectCloud != null) {
            for (int i = 0; i < this.list.size(); i++) {
                Point point = (Point) this.list.elementAt(i);
                if (point.x / 10 > MainScreen.cameraMain.xCam - (this.wimg / 2) && point.x / 10 < MainScreen.cameraMain.xCam + GameCanvas.w + (this.wimg / 2) && point.y / 10 > MainScreen.cameraMain.yCam - (this.himg / 2)) {
                    mgraphics.drawImage(this.imgEffectCloud[point.frame], point.x / 10, point.y / 10, 3, false);
                }
            }
        }
    }

    private void paintSnow(mGraphics mgraphics) {
        if (this.imgEffect != null) {
            for (int i = 0; i < this.number; i++) {
                Point point = (Point) this.list.elementAt(i);
                if (point.x / 10 > MainScreen.cameraMain.xCam && point.x / 10 < MainScreen.cameraMain.xCam + GameCanvas.w && point.y / 10 > MainScreen.cameraMain.yCam) {
                    mgraphics.drawRegion(this.imgEffect, 0, 7 * point.dis, 7, 7, 0, point.x / 10, point.y / 10, 3, false);
                }
            }
        }
    }

    private void paintFallingFlower(mGraphics mgraphics) {
        if (this.imgEffect != null) {
            for (int i = 0; i < this.number; i++) {
                Point point = (Point) this.list.elementAt(i);
                if (point.x / 10 > MainScreen.cameraMain.xCam && point.x / 10 < MainScreen.cameraMain.xCam + GameCanvas.w && point.y / 10 > MainScreen.cameraMain.yCam) {
                    int i2 = (2 - point.h) + 1;
                    if (i2 < 2) {
                        i2 = point.dis / 10;
                    }
                    mgraphics.drawRegion(this.imgEffect, 0, i2 * 10, 10, 10, 0, point.x / 10, point.y / 10, 3, false);
                    point.dis++;
                    if (point.dis >= 20) {
                        point.dis = 0;
                    }
                }
            }
        }
    }

    private void paintFallingLeaves(mGraphics mgraphics) {
        if (this.imgEffect != null) {
            for (int i = 0; i < this.number; i++) {
                Point point = (Point) this.list.elementAt(i);
                if (point.x / 10 > MainScreen.cameraMain.xCam && point.x / 10 < MainScreen.cameraMain.xCam + GameCanvas.w && point.y / 10 > MainScreen.cameraMain.yCam) {
                    if (CRes.random(6) == 0) {
                        point.dis = (byte) CRes.random(4);
                    }
                    mgraphics.drawRegion(this.imgEffect, 0, point.dis * 10, 16, 10, 0, point.x / 10, point.y / 10, 3, false);
                }
            }
        }
    }

    private void paintRain(mGraphics mgraphics) {
        mgraphics.setColor(-2236963);
        for (int i = 0; i < this.number; i++) {
            Point point = (Point) this.list.elementAt(i);
            if (point.x / 10 > MainScreen.cameraMain.xCam && point.x / 10 < MainScreen.cameraMain.xCam + GameCanvas.w && point.y / 10 > MainScreen.cameraMain.yCam) {
                mgraphics.fillRect(point.x / 10, point.y / 10, 1, point.h + 2, false);
            }
        }
    }

    public static void updateWind() {
        int i = 1;
        if (GameCanvas.gameTick % 6 == 3) {
            i = CRes.random(15);
        }
        if (i == 0 && wind == 5) {
            wind = 5 + CRes.random(20);
            countWind = 50 + CRes.random(100);
        }
        if (countWind > 0) {
            countWind--;
        }
        if (countWind == 0 && wind > 5 && GameCanvas.gameTick % 4 == 2) {
            wind--;
        }
    }

    @Override // GameEffect.MainEffect
    public void update() {
        if (this.timeLimit > 0 && (GameCanvas.timeNow / 1000) - this.curTime > this.timeLimit) {
            this.isStop = true;
        }
        switch (this.type) {
            case 0:
                updateRain();
                return;
            case 1:
                updateFallingLeaves();
                return;
            case 2:
                updateSnow();
                return;
            case 3:
                updateFlower();
                return;
            case 4:
                updateThienThach();
                return;
            case 5:
                updateCloud();
                return;
            default:
                return;
        }
    }

    private void updateCloud() {
        int i = 0;
        while (i < this.list.size()) {
            Point point = (Point) this.list.elementAt(i);
            point.y += point.vy;
            point.x += point.vx * dirWind;
            if (point.y / 10 < MainScreen.cameraMain.yCam - GameCanvas.hw || point.y / 10 > MainScreen.cameraMain.yCam + GameCanvas.h + GameCanvas.hh || point.x / 10 < MainScreen.cameraMain.xCam - GameCanvas.hw || point.x / 10 > MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) {
                this.list.removeElement(point);
                i--;
            }
            i++;
        }
        if ((this.timeLimit <= 0 || (GameCanvas.timeNow / 1000) - this.curTime <= this.timeLimit) && !this.isStop && CRes.random(350) < this.numClound) {
            Point point2 = new Point();
            rndPos(point2);
            point2.h = CRes.random(4);
            point2.limitY = 16 + (CRes.random(3) * 4);
            point2.v = CRes.random(-1, 1);
            point2.color = CRes.random(point2.limitY);
            point2.frame = CRes.random(2);
            point2.dis = (byte) CRes.random(20);
            point2.vx = CRes.random(2, 8);
            point2.vy = CRes.random(2, 12);
            this.list.addElement(point2);
        }
    }

    private void updateThienThach() {
        if (CRes.random(250) < this.number) {
            GameScreen.addEffectKill(86, 0, 0, 0, (short) 0, (byte) CRes.random(1, 3));
        }
    }

    private void updateSnow() {
        for (int i = 0; i < this.number; i++) {
            Point point = (Point) this.list.elementAt(i);
            point.y += (point.h + 4) * 3;
            point.x += ((point.h + 1) * 2) + (wind * dirWind);
            if ((point.y / 10 < MainScreen.cameraMain.yCam - GameCanvas.hw || point.y / 10 > (MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh)) - ((4 - point.h) * 50) || point.x / 10 < MainScreen.cameraMain.xCam - GameCanvas.hw || point.x / 10 > MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) && CRes.random(40) == 0) {
                rndPos(point);
            }
        }
    }

    private void updateFlower() {
        for (int i = 0; i < this.number; i++) {
            Point point = (Point) this.list.elementAt(i);
            point.y += (point.h + 2) * 5;
            point.x += ((point.h + 1) * 2) + (wind * dirWind);
            if (point.y / 10 < MainScreen.cameraMain.yCam - GameCanvas.hw || point.y / 10 > (MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh)) - ((4 - point.h) * 50) || point.x / 10 < MainScreen.cameraMain.xCam - GameCanvas.hw || point.x / 10 > MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) {
                rndPos(point);
            }
        }
    }

    private void updateFallingLeaves() {
        for (int i = 0; i < this.number; i++) {
            Point point = (Point) this.list.elementAt(i);
            point.y += 15;
            point.x += (point.v * 10) + (wind * dirWind);
            point.color++;
            if (point.color >= point.limitY) {
                point.color = 0;
            }
            if (point.y / 10 < MainScreen.cameraMain.yCam - GameCanvas.hw || point.y / 10 > (MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh)) - ((4 - point.h) * 50) || point.x / 10 < MainScreen.cameraMain.xCam - GameCanvas.hw || point.x / 10 > MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) {
                rndPos(point);
            }
        }
    }

    private void updateRain() {
        for (int i = 0; i < this.number; i++) {
            Point point = (Point) this.list.elementAt(i);
            point.y += ((point.h + 1) * 15) + ((3 - point.h) * 3);
            point.g++;
            point.x += (((3 - point.h) + 1) * 2) + (wind * dirWind);
            if (point.y / 10 < MainScreen.cameraMain.yCam - GameCanvas.hw || point.y / 10 > (MainScreen.cameraMain.yCam + (GameCanvas.h + GameCanvas.hh)) - ((4 - point.h) * 50) || point.x / 10 < MainScreen.cameraMain.xCam - GameCanvas.hw || point.x / 10 > MainScreen.cameraMain.xCam + GameCanvas.w + GameCanvas.hw) {
                rndPos(point);
            }
        }
    }

    private void rndPos(Point point) {
        if (this.isStop) {
            this.list.removeElement(point);
            this.number = this.list.size();
            if (this.list.size() == 0) {
                close();
                return;
            }
            return;
        }
        if (this.type == 5) {
            point.y = ((MainScreen.cameraMain.yCam - GameCanvas.hh) + CRes.random(GameCanvas.h / 2)) * 10;
            if (dirWind > 0) {
                point.x = ((MainScreen.cameraMain.xCam - GameCanvas.hw) + CRes.random(GameCanvas.w)) * 10;
            } else {
                point.x = (MainScreen.cameraMain.xCam + GameCanvas.hw + CRes.random(GameCanvas.w)) * 10;
            }
            point.frame = CRes.random(2);
            point.vx = CRes.random(2, 8);
            point.vy = CRes.random(2, 12);
        } else {
            point.y = ((MainScreen.cameraMain.yCam - GameCanvas.hh) + CRes.random(GameCanvas.h * 2)) * 10;
            point.x = ((MainScreen.cameraMain.xCam - GameCanvas.hw) + CRes.random(GameCanvas.w * 2)) * 10;
        }
        if (this.type == 2 || this.type == 3) {
            point.h = CRes.random(3);
        } else if (this.type == 0) {
            point.h = CRes.random(1, 5);
        } else {
            point.h = CRes.random(4);
        }
    }
}
