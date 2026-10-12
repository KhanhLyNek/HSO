package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import GameEffect.EffectSkill;
import GameObjects.Monstervantieu;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Thread_More.MiniMap;

/* loaded from: KnightOnline_v403.jar:GameScreen/MapScr.class */
public class MapScr extends MainScreen {
    private static MapScr instance;
    boolean modeCurrentMap;
    public static int mapW;
    public static int mapH;
    public static int mfx;
    public static int mfy;
    public static int mpoint;
    public static int tick3;
    public static int mcmtoX;
    public static int mcmtoY;
    public static int mcmvx;
    public static int mcmvy;
    public static int mcmdx;
    public static int mcmdy;
    public static int mcmx;
    public static int mcmy;
    public static int mcmxLim;
    public static int mcmyLim;
    public static int taskmapId;
    public static String[] mapNames;
    int maxPX;
    int maxPY;
    int xM;
    int yM;
    boolean trans = false;
    int lastX;
    int lastY;
    public static mImage imgMap = null;
    private static int dx = 0;
    private static int dy = 0;
    public static int cmdH = 22;
    public static int TOP_CENTER = 17;
    public static int TOP_LEFT = 20;
    public static int TOP_RIGHT = 24;
    public static int BOTTOM_HCENTER = 33;
    public static int BOTTOM_LEFT = 36;
    public static int BOTTOM_RIGHT = 40;
    public static int VCENTER_HCENTER = 3;
    public static int VCENTER_LEFT = 6;
    public static int[] x = {108, EffectSkill.EFF_SUNG_NGU, Monstervantieu.ID_TEMPLATE_BO_TRANG, 93, 76, 58, 39, EffectSkill.EFF_PET_EAGLE, 83, 66, 49, 121, 135, 154, 160, 100, 94, 80, Monstervantieu.ID_TEMPLATE_LUA_DEN, 73, 100, 88, 53, 83, 73, 127, EffectSkill.EFF_PHAPSU_BANG, 89, 72, 171, 172, 185, 202, 134, EffectSkill.EFF_SMALL_SWORD_115, 157, 135, 99, EffectSkill.EFF_PET_EAGLE, 122, 139, 156, 47, 44, 28, 23, 0, 47, 87, 0, 133, 159, 23, 207, 207, 239, 226, 207, 207, 168, 182, 217, 23, 71, 24, 88, 25, 66, 99, 40, 47, 28, 64, 47, 47, 67, 83, 98, EffectSkill.EFF_SUNG_NGU, Monstervantieu.ID_TEMPLATE_LUA_DEN, 0, 0, EffectSkill.EFF_SMALL_SWORD_115, 219, 191, 219, 241, 218, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    public static int[] y = {247, 233, 220, 235, 236, 238, 237, 216, 212, 216, 205, 210, 204, 203, 182, 196, 180, 172, 183, EffectSkill.EFF_SMALL_SWORD_115, 144, Monstervantieu.ID_TEMPLATE_LAC_DA_DEN, 134, 146, 134, 162, 160, 160, 160, 141, 125, 114, EffectSkill.EFF_BIG_SWORD_115, 145, 144, 147, Monstervantieu.ID_TEMPLATE_LUA_DEN, EffectSkill.EFF_SUNG_115, 98, 96, 88, 75, 149, 165, 165, 181, 0, EffectSkill.EFF_SUNG_115, 224, 0, 235, 59, 200, 201, 187, 162, 167, Monstervantieu.ID_TEMPLATE_LAC_DA_TRANG, 143, 171, 171, 166, 217, 57, 62, 60, 83, 92, 72, 92, 69, 43, 42, 35, 18, 14, 8, 14, 28, 27, 0, 0, Monstervantieu.ID_TEMPLATE_BO_DEN, EffectSkill.EFF_BIG_SWORD_115, 96, 71, 94, 94, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    public MapScr() {
        this.center = new iCommand(T.close, 0, this);
    }

    protected void resetCMLim() {
        int i = GameCanvas.loadmap.idMap;
        if (i > x.length) {
            i = x.length - 1;
        }
        int imageWidth = mImage.getImageWidth(MiniMap.imgMiniMap.image);
        this.xM = (GameCanvas.w - imageWidth) / 2;
        this.yM = ((GameCanvas.h - 20) - mImage.getImageHeight(MiniMap.imgMiniMap.image)) / 2;
        if (this.xM < 0) {
            this.xM = 0;
        }
        if (this.yM < 0) {
            this.yM = 0;
        }
        if (this.modeCurrentMap) {
            mcmxLim = (imageWidth + 20) - GameCanvas.w;
            mcmyLim = (mImage.getImageHeight(MiniMap.imgMiniMap.image) + 40) - GameCanvas.h;
            this.maxPX = imageWidth + 20;
            this.maxPY = imageWidth + 40;
            if (this.maxPY < GameCanvas.h - 26) {
                this.maxPY = GameCanvas.h - 26;
            }
            if (this.maxPX < GameCanvas.w) {
                this.maxPX = GameCanvas.w;
            }
            mfx = this.xM + (GameScreen.player.x / 12);
            mfy = this.yM + (GameScreen.player.y / 12);
        } else {
            mcmxLim = 340 - GameCanvas.w;
            mcmyLim = 340 - GameCanvas.h;
            mfx = x[i] + dx;
            mfy = y[i] + dy;
            this.maxPX = 330 + dx;
            this.maxPY = 310 + dy;
        }
        this.maxPX -= 10;
        this.maxPY -= 10;
        if (mcmxLim < 0) {
            mcmxLim = 0;
        }
        if (mcmyLim < 0) {
            mcmyLim = 0;
        }
        mcmy = 0;
        mcmx = 0;
        mcmtoY = 0;
        mcmtoX = 0;
        mcmtoX = mfx - GameCanvas.hw;
        mcmtoY = mfy - GameCanvas.hh;
    }

    public static MapScr gI() {
        if (instance == null) {
            instance = new MapScr();
        }
        return instance;
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        super.Show();
        this.center = new iCommand(T.close, 0, this);
        if (imgMap == null) {
            imgMap = mImage.createImage("/wm.png");
            mapW = mImage.getImageWidth(imgMap.image);
            mapH = mImage.getImageWidth(imgMap.image);
        }
        if (GameCanvas.w > mapW) {
            dx = (GameCanvas.w / 2) - (mapW / 2);
        }
        if (GameCanvas.h > mapH) {
            dy = (GameCanvas.h / 2) - (mapH / 2);
        }
        resetCMLim();
        findMapNearestPoint();
        System.gc();
        mapNames = T.mapName;
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        mgraphics.setColor(0);
        mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        mgraphics.translate(10, 10);
        mgraphics.translate(-mcmx, -mcmy);
        if (GameCanvas.w > mapW && GameCanvas.h > mapH) {
            mgraphics.drawImage(imgMap, GameCanvas.hw, GameCanvas.hh, VCENTER_HCENTER, false);
        } else if (GameCanvas.w > mapW) {
            mgraphics.drawImage(imgMap, GameCanvas.hw, 0, TOP_CENTER, false);
        } else if (GameCanvas.h > mapH) {
            mgraphics.drawImage(imgMap, 0, GameCanvas.hh, VCENTER_LEFT, false);
        } else {
            mgraphics.drawImage(imgMap, 0, 0, 0, false);
        }
        if (GameCanvas.loadmap.idMap < mapNames.length && GameCanvas.loadmap.idMap >= 0) {
            int i = 0;
            if (x[GameCanvas.loadmap.idMap] != 1 || y[GameCanvas.loadmap.idMap] != 1) {
                i = x[GameCanvas.loadmap.idMap] < 100 ? 0 : x[GameCanvas.loadmap.idMap] > 200 ? 1 : 2;
                mgraphics.drawRegion(AvMain.imgFocusMap, 0, (GameCanvas.gameTick % 3) * 10, 10, 10, 0, x[GameCanvas.loadmap.idMap] + dx, y[GameCanvas.loadmap.idMap] + dy, 3, false);
            }
            int i2 = 0;
            if (x[GameCanvas.loadmap.idMap] != 1 || y[GameCanvas.loadmap.idMap] != 1) {
                i2 = y[GameCanvas.loadmap.idMap] - 20;
                mFont.tahoma_7b_black.drawString(mgraphics, mapNames[GameCanvas.loadmap.idMap], x[GameCanvas.loadmap.idMap] + dx + 1, ((y[GameCanvas.loadmap.idMap] + dy) - 20) + 1, i, false);
                mFont.tahoma_7b_yellow.drawString(mgraphics, mapNames[GameCanvas.loadmap.idMap], x[GameCanvas.loadmap.idMap] + dx, (y[GameCanvas.loadmap.idMap] + dy) - 20, i, false);
            }
            if (mpoint >= 0 && ((taskmapId < 0 && GameCanvas.loadmap.idMap != mpoint) || (taskmapId >= 0 && mpoint != taskmapId))) {
                int i3 = x[mpoint] < 100 ? 0 : x[mpoint] > 200 ? 1 : 2;
                int i4 = x[mpoint];
                int i5 = y[mpoint] - 20;
                if (i5 > i2 && i5 - i2 < 30) {
                    i5 += 40;
                }
                if (i5 < i2 && i2 - i5 < 20) {
                    i5 -= 5;
                }
                mFont.tahoma_7b_black.drawString(mgraphics, mapNames[mpoint], i4 + dx + 1, i5 + dy + 1, i3, false);
                mFont.tahoma_7b_yellow.drawString(mgraphics, mapNames[mpoint], i4 + dx, i5 + dy, i3, false);
            }
        }
        if (!GameCanvas.isTouch) {
            mgraphics.drawRegion(MainTabNew.imgTab[5], 0, 0, 10, 10, 0, mfx - 2, mfy, 0, false);
        } else if (mpoint >= 0) {
            mgraphics.drawRegion(MainTabNew.imgTab[5], 0, 0, 10, 10, 0, (x[mpoint] - 9) + dx, y[mpoint] + dy, 0, false);
        }
        mgraphics.translate(-mgraphics.getTranslateX(), -mgraphics.getTranslateY());
        super.paint(mgraphics);
    }

    @Override // Model.AvMain
    public void updatekey() {
        super.updatekey();
        tick3++;
        if (tick3 > 10000) {
            tick3 = 0;
        }
        if (mcmx != mcmtoX || mcmy != mcmtoY) {
            mcmvx = (mcmtoX - mcmx) << 1;
            mcmvy = (mcmtoY - mcmy) << 1;
            mcmdx += mcmvx;
            mcmx += mcmdx >> 4;
            mcmdx &= 15;
            mcmdy += mcmvy;
            mcmy += mcmdy >> 4;
            mcmdy &= 15;
            if (mcmx < 0) {
                mcmx = 0;
            }
            if (mcmx > mcmxLim) {
                mcmx = mcmxLim;
            }
            if (mcmy < 0) {
                mcmy = 0;
            }
            if (mcmy > mcmyLim) {
                mcmy = mcmyLim;
            }
        }
        boolean z = false;
        if (GameCanvas.keyMyHold[2]) {
            mfy -= 4;
            if (mfy < dy - 10) {
                mfy = dy - 10;
            }
            z = true;
        }
        if (GameCanvas.keyMyHold[8]) {
            mfy += 4;
            if (mfy > this.maxPY) {
                mfy = this.maxPY;
            }
            z = true;
        }
        if (GameCanvas.keyMyHold[4]) {
            mfx -= 4;
            if (mfx < dx - 10) {
                mfx = dx - 10;
            }
            z = true;
        }
        if (GameCanvas.keyMyHold[6]) {
            mfx += 4;
            if (mfx > this.maxPX) {
                mfx = this.maxPX;
            }
            z = true;
        }
        if (z) {
            mcmtoX = mfx - GameCanvas.hw;
            mcmtoY = mfy - GameCanvas.hh;
            findMapNearestPoint();
        }
        if (GameCanvas.isPointerClick && GameCanvas.py < GameCanvas.h - cmdH) {
            GameCanvas.isPointerClick = false;
            this.trans = true;
            this.lastX = GameCanvas.px;
            this.lastY = GameCanvas.py;
        } else if (GameCanvas.isPointerDown && this.trans) {
            mcmtoX -= GameCanvas.px - this.lastX;
            mcmtoY -= GameCanvas.py - this.lastY;
            if (mcmtoX < 0) {
                mcmtoX = 0;
            }
            if (mcmtoY < 0) {
                mcmtoY = 0;
            }
            if (mcmtoX > mcmxLim) {
                mcmtoX = mcmxLim;
            }
            if (mcmtoY > mcmyLim) {
                mcmtoY = mcmyLim;
            }
            mcmx = mcmtoX;
            mcmy = mcmtoY;
            this.lastX = GameCanvas.px;
            this.lastY = GameCanvas.py;
        }
        if (GameCanvas.isPointerRelease) {
            int i = GameCanvas.pxLast - GameCanvas.px;
            int i2 = GameCanvas.pyLast - GameCanvas.py;
            if (i < 10 && i2 < 10) {
                mfx = (mcmx + GameCanvas.pxLast) - 8;
                mfy = (mcmy + GameCanvas.pyLast) - 8;
                findMapNearestPoint();
            }
            this.trans = false;
            GameCanvas.isPointerRelease = false;
        }
        if (GameCanvas.isTouch && GameCanvas.w >= 320) {
            this.center.xCmd = (GameCanvas.w / 2) - 35;
        }
    }

    private static void findMapNearestPoint() {
        mpoint = -1;
        int i = 10;
        if (!GameCanvas.isTouch) {
            i = 13;
        }
        for (int i2 = 0; i2 < x.length; i2++) {
            if (CRes.abs(mfx - (x[i2] + dx)) < i && CRes.abs(mfy - (y[i2] + dy)) < i) {
                mpoint = i2;
                return;
            }
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                imgMap = null;
                GameScreen.gI().Show();
                return;
            case 1:
                this.modeCurrentMap = !this.modeCurrentMap;
                resetCMLim();
                return;
            default:
                return;
        }
    }
}
