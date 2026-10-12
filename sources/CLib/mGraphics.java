package CLib;

import Main.GameCanvas;
import Model.AvMain;
import javax.microedition.lcdui.Graphics;

/* loaded from: KnightOnline_v403.jar:CLib/mGraphics.class */
public class mGraphics {
    public Graphics g;
    public static int zoomLevel = 1;
    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    public static final int BASELINE = 64;
    public static final int SOLID = 0;
    public static final int DOTTED = 1;
    public static final boolean isTrue = true;
    public static final boolean isFalse = false;
    int wImg;
    int hImg;

    public mGraphics(Graphics graphics) {
        this.g = graphics;
    }

    public mGraphics() {
    }

    public void drawImage(mImage mimage, int i, int i2, int i3, boolean z) {
        this.g.drawImage(mimage.image, i * zoomLevel, i2 * zoomLevel, i3);
    }

    public void drawLine(int i, int i2, int i3, int i4, boolean z) {
        int i5 = i * zoomLevel;
        int i6 = i2 * zoomLevel;
        int i7 = i3 * zoomLevel;
        int i8 = i4 * zoomLevel;
        for (int i9 = 0; i9 < zoomLevel; i9++) {
            this.g.drawLine(i5 + i9, i6 + i9, i7 + i9, i8 + i9);
            if (i9 > 0) {
                this.g.drawLine(i5 + i9, i6, i7 + i9, i8);
                this.g.drawLine(i5, i6 + i9, i7, i8 + i9);
            }
        }
    }

    public void drawRect(int i, int i2, int i3, int i4, boolean z) {
        int i5 = i * zoomLevel;
        int i6 = i2 * zoomLevel;
        int i7 = i3 * zoomLevel;
        int i8 = i4 * zoomLevel;
        for (int i9 = 0; i9 < zoomLevel; i9++) {
            this.g.drawRect(i5 + i9, i6 + i9, i7 - (i9 * 2), i8 - (i9 * 2));
        }
    }

    public void drawRegion(mImage mimage, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        int i9 = i6 * zoomLevel;
        int i10 = i7 * zoomLevel;
        this.g.drawRegion(mimage.image, i * zoomLevel, i2 * zoomLevel, i3 * zoomLevel, i4 * zoomLevel, i5, i9, i10, i8);
    }

    public void drawRoundRect(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.g.drawRoundRect(i * zoomLevel, i2 * zoomLevel, i3 * zoomLevel, i4 * zoomLevel, i5 * zoomLevel, i6 * zoomLevel);
    }

    public void drawString(String str, int i, int i2, int i3, boolean z) {
        this.g.drawString(str, i * zoomLevel, i2 * zoomLevel, i3);
    }

    public void fillRect(int i, int i2, int i3, int i4, boolean z) {
        this.g.fillRect(i * zoomLevel, i2 * zoomLevel, i3 * zoomLevel, i4 * zoomLevel);
    }

    public void fillRoundRect(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.g.fillRoundRect(i * zoomLevel, i2 * zoomLevel, i3 * zoomLevel, i4 * zoomLevel, i5 * zoomLevel, i6 * zoomLevel);
    }

    public void drawArc(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.g.drawArc(i * zoomLevel, i2 * zoomLevel, i3 * zoomLevel, i4 * zoomLevel, i5, i6);
    }

    public void fillTriangle(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.g.fillTriangle(i * zoomLevel, i2 * zoomLevel, i3 * zoomLevel, i4 * zoomLevel, i5 * zoomLevel, i6 * zoomLevel);
    }

    public int getTranslateX() {
        return this.g.getTranslateX() / zoomLevel;
    }

    public int getTranslateY() {
        return this.g.getTranslateY() / zoomLevel;
    }

    public void setClip(int i, int i2, int i3, int i4) {
        this.g.setClip(i * zoomLevel, i2 * zoomLevel, i3 * zoomLevel, i4 * zoomLevel);
    }

    public void endClip() {
    }

    public void setColor(int i) {
        this.g.setColor(i);
    }

    public void setColor(int i, float f) {
        this.g.setColor(i);
    }

    public void translate(int i, int i2) {
        this.g.translate(i * zoomLevel, i2 * zoomLevel);
    }

    public void fillRecAlpla(int i, int i2, int i3, int i4, int i5) {
        drawRecAlpa(0, 0, GameCanvas.loadmap.mapW * 24, i2, i5);
        drawRecAlpa(0, i2, i, (GameCanvas.loadmap.mapH * 24) - i2, i5);
        drawRecAlpa(i, i2 + i4, (GameCanvas.loadmap.mapW * 24) - i, (GameCanvas.loadmap.mapH * 24) - (i2 + i4), i5);
        drawRecAlpa(i + i3, i2, (GameCanvas.loadmap.mapW * 24) - (i + i3), i4, i5);
    }

    public void drawRecAlpa(int i, int i2, int i3, int i4, int i5) {
        setColor(i5);
        fillRect(i, i2, i3, i4, false);
    }

    public void fillRect(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        int i7 = i * zoomLevel;
        int i8 = i2 * zoomLevel;
        int i9 = i3 * zoomLevel;
        int i10 = i4 * zoomLevel;
        if (this.wImg != mImage.getImageWidth(AvMain.imgRect.image)) {
            this.wImg = mImage.getImageWidth(AvMain.imgRect.image);
        }
        if (this.hImg != mImage.getImageHeight(AvMain.imgRect.image)) {
            this.hImg = mImage.getImageHeight(AvMain.imgRect.image);
        }
        setClip(i7, i8, i9, i10);
        int i11 = i7 % this.wImg;
        while (true) {
            int i12 = i11;
            if (i12 < i9 + this.wImg) {
                int i13 = i8 % this.hImg;
                while (true) {
                    int i14 = i13;
                    if (i14 < i10 + this.hImg) {
                        drawImage(AvMain.imgRect, (i7 + i9) - i12, (i8 + i10) - i14, 20, true);
                        i13 = i14 + this.hImg;
                    }
                }
                i11 = i12 + this.wImg;
            } else {
                setClip(-this.g.getTranslateX(), -this.g.getTranslateY(), GameCanvas.w, GameCanvas.h);
                return;
            }
        }
    }

    public void restoreCanvas() {
    }

    public void saveCanvas() {
    }

    public void ClipRec(int i, int i2, int i3, int i4) {
    }

    public static void resetTransAndroid(mGraphics mgraphics) {
    }

    public void translateAndroid(int i, int i2) {
    }
}
