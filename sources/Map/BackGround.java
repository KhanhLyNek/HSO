package Map;

import CLib.mGraphics;
import CLib.mImage;
import Main.GameCanvas;
import Model.CRes;
import Model.FrameImage;
import Model.Vector2;
import Thread_More.LoadMap;

/* loaded from: KnightOnline_v403.jar:Map/BackGround.class */
public class BackGround {
    public static final int CLOUD_POS_OFFSET_Y = 10;
    public static FrameImage mImgSeaAuto;
    private static int numSkyDraw;
    private static int skyWidth;
    private static int skyHeight;
    private static int numSeaDrawW;
    private static int numSeaDrawH;
    private static int seaWidth;
    private static int seaHeight;
    private static int numCloudDraw;
    private static int cloudWidth;
    private static int cloudHeight;
    public static mImage mImgSky = new mImage();
    public static mImage mImgSea = new mImage();
    public static mImage[] mImgCloud = new mImage[3];
    public static mImage mImgBoat = new mImage();
    public static mImage mImgFloating = new mImage();
    public static Vector2[] cloudPos = new Vector2[7];
    public static Vector2[] floatingPos = new Vector2[3];
    public static int[] cloudType = new int[7];
    public static int[] cloudVelo = new int[7];
    public static int fillSkyH = 70;
    public static int[] staticCloudPos = new int[3];

    public static void init() {
        LoadBackGround();
        skyWidth = mImage.getImageWidth(mImgSky.image);
        skyHeight = mImage.getImageHeight(mImgSky.image);
        numSkyDraw = GameCanvas.w / skyWidth;
        seaWidth = mImage.getImageWidth(mImgSea.image);
        seaHeight = mImage.getImageHeight(mImgSea.image);
        numSeaDrawW = GameCanvas.w / seaWidth;
        numSeaDrawH = (GameCanvas.h - (fillSkyH + skyHeight)) / seaHeight;
        cloudWidth = mImage.getImageWidth(mImgCloud[0].image);
        cloudHeight = mImage.getImageHeight(mImgCloud[0].image);
        numCloudDraw = GameCanvas.w / cloudWidth;
        for (int i = 0; i < cloudPos.length; i++) {
            cloudPos[i] = new Vector2();
            cloudPos[i].x = CRes.random(0, GameCanvas.w);
            cloudPos[i].y = CRes.random(6) * 10;
            cloudType[i] = CRes.random(1, 3);
            cloudVelo[i] = CRes.random(0, 2);
        }
        for (int i2 = 0; i2 < floatingPos.length; i2++) {
            floatingPos[i2] = new Vector2();
            floatingPos[i2].x = (GameCanvas.hw - 80) + (i2 * 80);
            floatingPos[i2].y = (GameCanvas.h - 50) - ((i2 % 2) * 25);
        }
        staticCloudPos = new int[numCloudDraw + 2];
        for (int i3 = 0; i3 < staticCloudPos.length; i3++) {
            staticCloudPos[i3] = (i3 * cloudWidth) - mImage.getImageWidth(mImgCloud[0].image);
        }
    }

    public static void paint(mGraphics mgraphics) {
        if (mImgSky == null || mImgSea == null || mImgFloating == null) {
            LoadBackGround();
        } else {
            paint_Sky(mgraphics);
            paint_Boat(mgraphics);
            paint_Sea(mgraphics);
            paint_Cloud(mgraphics);
        }
        mgraphics.translate(GameCanvas.hw - ((GameCanvas.loadmap.mapWLogin * LoadMap.wTile) / 2), GameCanvas.h - (GameCanvas.loadmap.mapHLogin * LoadMap.wTile));
        GameCanvas.resetTrans(mgraphics);
    }

    public static void paintLight(mGraphics mgraphics) {
    }

    public static void paint_Boat(mGraphics mgraphics) {
        mgraphics.drawImage(mImgBoat, 10, fillSkyH + (skyHeight / 2), 0, false);
    }

    public static void paint_Sky(mGraphics mgraphics) {
        mgraphics.setColor(-11835756);
        mgraphics.fillRect(0, 0, GameCanvas.w, fillSkyH, false);
        for (int i = 0; i < numSkyDraw + 1; i++) {
            mgraphics.drawImage(mImgSky, i * skyWidth, fillSkyH, 0, false);
        }
    }

    public static void paint_Sea(mGraphics mgraphics) {
        int i = fillSkyH + skyHeight;
        for (int i2 = 0; i2 < numSeaDrawW + 1; i2++) {
            for (int i3 = 0; i3 < numSeaDrawH + 1; i3++) {
                mgraphics.drawImage(mImgSea, i2 * seaWidth, (i3 * seaHeight) + i, 0, false);
            }
        }
    }

    public static void paint_Cloud(mGraphics mgraphics) {
        for (int i = 0; i < 4; i++) {
            mgraphics.drawImage(mImgCloud[cloudType[i]], (int) cloudPos[i].x, (int) cloudPos[i].y, 0, false);
        }
    }

    public static void paint_StaticCloud(mGraphics mgraphics) {
        int i = GameCanvas.h - cloudHeight;
        for (int i2 = 0; i2 < staticCloudPos.length; i2++) {
            mgraphics.drawImage(mImgCloud[0], staticCloudPos[i2], i, 0, false);
        }
    }

    public static void paint_CloudOnLogo(mGraphics mgraphics) {
        for (int i = 4; i < cloudPos.length; i++) {
            mgraphics.drawImage(mImgCloud[cloudType[i]], (int) cloudPos[i].x, (int) cloudPos[i].y, 0, false);
        }
    }

    public static void paint_FloatingPlatform(mGraphics mgraphics) {
        for (int i = 0; i < floatingPos.length; i++) {
            mgraphics.drawImage(mImgFloating, (int) floatingPos[i].x, (int) floatingPos[i].y, 3, false);
        }
    }

    public static void updateSky() {
        for (int i = 0; i < cloudPos.length; i++) {
            cloudVelo[i] = 1;
            cloudPos[i].x += cloudVelo[i];
            if (cloudPos[i].x > GameCanvas.w) {
                cloudPos[i].x = -mImage.getImageWidth(mImgCloud[1].image);
                cloudPos[i].y = CRes.random(6) * 10;
                cloudType[i] = CRes.random(1, 3);
                cloudVelo[i] = CRes.random(1, 2);
            }
        }
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < staticCloudPos.length; i4++) {
            int[] iArr = staticCloudPos;
            int i5 = i4;
            iArr[i5] = iArr[i5] + 2;
            if (staticCloudPos[i4] > GameCanvas.w) {
                int i6 = i4 + 1;
                if (i6 > staticCloudPos.length - 1) {
                    i6 = 0;
                }
                if (i2 == -1) {
                    i2 = i4;
                    i3 = i6;
                }
            }
        }
        if (i2 != -1 && i3 != -1) {
            staticCloudPos[i2] = staticCloudPos[i3] - cloudWidth;
        }
    }

    public static void LoadBackGround() {
        mImgSky = mImage.createImage("/bg/sky.img");
        mImgSea = mImage.createImage("/bg/sea.img");
        for (int i = 0; i < mImgCloud.length; i++) {
            mImgCloud[i] = mImage.createImage(new StringBuffer().append("/bg/cloud").append(i).append(".img").toString());
        }
        mImgBoat = mImage.createImage("/bg/boat.img");
        mImgFloating = mImage.createImage("/bg/floating.img");
        if (mImgSeaAuto == null) {
            mImgSeaAuto = new FrameImage(mImage.createImage("/bg/seabg.png"), 24, 24);
        }
    }

    public static void LoadImgCloud() {
        for (int i = 0; i < mImgCloud.length; i++) {
            if (mImgCloud[i] == null) {
                mImgCloud[i] = mImage.createImage(new StringBuffer().append("/bg/cloud").append(i).append(".img").toString());
            }
        }
    }

    public static void paint_SeaCloud(mGraphics mgraphics) {
        for (int i = 0; i < 4; i++) {
            mgraphics.drawImage(mImgCloud[cloudType[i]], (int) cloudPos[i].x, ((int) cloudPos[i].y) + (GameCanvas.h / 2), 0, false);
        }
    }

    public static void paint_SeaAuto(mGraphics mgraphics) {
        for (int i = 0; i < (GameCanvas.w / 24) + 1; i++) {
            for (int i2 = 0; i2 < (GameCanvas.h / 24) + 1; i2++) {
                mImgSeaAuto.drawFrame(GameCanvas.gameTick % 14 < 7 ? 1 : 0, i * 24, i2 * 24, 0, mgraphics);
            }
        }
    }
}
