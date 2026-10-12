package com.silverknight;

import CLib.TemGraphics;
import CLib.mGraphics;
import CLib.mSystem;
import Main.GameCanvas;
import Thread_More.Load_Data_And_Img;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/* loaded from: KnightOnline_v403.jar:com/silverknight/TemCanvas.class */
public class TemCanvas extends Canvas implements Runnable {
    public static TemCanvas instance;
    public static GameCanvas gamecanvas;
    public static TemGraphics tem = new TemGraphics();
    public static int wMain;
    public static int hMain;
    public static Vector listPoint;

    public TemCanvas() {
        instance = this;
        setFullScreenMode(true);
        hMain = getHeight();
        wMain = getWidth();
        checkZoomLevel(wMain, hMain);
        GameCanvas.isTouch = hasPointerEvents();
        gamecanvas = new GameCanvas();
        new Thread(Load_Data_And_Img.load).start();
    }

    private void checkZoomLevel(int i, int i2) {
        mGraphics.zoomLevel = 1;
        wMain = ((wMain + mGraphics.zoomLevel) - 1) / mGraphics.zoomLevel;
        hMain = ((hMain + mGraphics.zoomLevel) - 1) / mGraphics.zoomLevel;
    }

    public void start() {
        new Thread(this).start();
    }

    protected void paint(Graphics graphics) {
        tem.g = graphics;
        gamecanvas.paint(tem);
    }

    protected void update() {
        gamecanvas.update();
    }

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            long currentTimeMillis = mSystem.currentTimeMillis();
            update();
            repaint();
            serviceRepaints();
            long currentTimeMillis2 = mSystem.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis2 < 40) {
                try {
                    Thread.sleep(40 - currentTimeMillis2);
                } catch (InterruptedException e) {
                }
            } else {
                mSystem.outloi(new StringBuffer().append(currentTimeMillis2).append("  >>>>>>>>").toString());
                Thread.sleep(1L);
                GameCanvas.timeNow = mSystem.currentTimeMillis();
            }
        }
    }

    protected void keyPressed(int i) {
        gamecanvas.keyPressed(i);
    }

    protected void keyReleased(int i) {
        gamecanvas.keyReleased(i);
    }

    protected void pointerDragged(int i, int i2) {
        gamecanvas.pointerDragged(i, i2);
    }

    protected void pointerPressed(int i, int i2) {
        gamecanvas.pointerPressed(i, i2);
    }

    protected void pointerReleased(int i, int i2) {
        gamecanvas.pointerReleased(i, i2);
    }
}
