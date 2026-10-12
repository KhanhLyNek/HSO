package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/Info_Quayso.class */
public class Info_Quayso extends MainDialog {
    private long currTime;
    private long maxTime;
    private byte indexcolor;
    private byte indexcolorpercen;
    private String[] infopaint;
    private String texpercen = "";
    iCommand cmdClose = new iCommand(T.close, -1, this);
    private int[] color = {-15665338, -329963, -357611, -261612, -211511};
    private int Height = 22;
    private int h = 15;
    private int timesv = 0;
    private int percenWin = 0;
    private int percenTime = 0;

    public void setInfo(String[] strArr, int i, int i2, String str) {
        this.timesv = i;
        this.percenWin = i2;
        this.texpercen = str;
        this.infopaint = strArr;
        this.maxTime = (mSystem.currentTimeMillis() / 1000) + this.timesv;
        this.currTime = this.maxTime;
        this.hDia = this.infopaint.length * this.Height;
        this.wDia = GameCanvas.w - 20;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) - 5;
        this.cmdClose.setPos(GameCanvas.hw, ((this.yDia + this.hDia) - (iCommand.hButtonCmd / 2)) - 2, PaintInfoGameScreen.fraButton, this.cmdClose.caption);
        if (i2 >= 75) {
            this.indexcolorpercen = (byte) 3;
            return;
        }
        if (i2 >= 50) {
            this.indexcolorpercen = (byte) 2;
        } else if (i2 >= 25) {
            this.indexcolorpercen = (byte) 1;
        } else {
            this.indexcolorpercen = (byte) 0;
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                GameCanvas.end_Dialog();
                break;
        }
        super.commandPointer(i, i2);
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        AvMain.paintDialog(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, 2);
        for (int i = 0; i < this.infopaint.length; i++) {
            int width = mFont.tahoma_7b_white.getWidth(this.infopaint[0]);
            mFont.tahoma_7b_white.drawString(mgraphics, this.infopaint[i], this.xDia + 10, this.yDia + 10 + (i * this.h), 0, false);
            if (i == 0) {
                int i2 = (int) ((this.currTime * (this.wDia - width)) / this.timesv);
                if (i2 <= 7) {
                    i2 = 7;
                }
                if (this.currTime <= 0) {
                    i2 = 0;
                }
                mgraphics.setColor(-8967919);
                mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5, this.yDia + 7 + (i * this.h), this.wDia - width, 14, false);
                mgraphics.setColor(-526425);
                mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5 + 1, this.yDia + 7 + (i * this.h) + 1, (this.wDia - width) - 2, 12, false);
                mgraphics.setColor(-8952235);
                mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5 + 2, this.yDia + 7 + (i * this.h) + 2, (this.wDia - width) - 4, 10, false);
                if (this.percenTime <= 15) {
                    if (GameCanvas.gameTick % 2 == 0) {
                        this.indexcolor = (byte) 3;
                    } else {
                        this.indexcolor = (byte) 4;
                    }
                }
                mgraphics.setColor(this.color[this.indexcolor]);
                if (this.percenTime > 0) {
                    mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5 + 3, this.yDia + 7 + (i * this.h) + 3, i2 - 6, 8, false);
                }
                mFont.tahoma_7b_black.drawString(mgraphics, GameScreen.converSecon2minutes((int) this.currTime), ((this.xDia + width) - (width / 4)) + 5 + 2 + (((this.wDia - width) - 4) / 2), this.yDia + 7 + (i * this.h) + 1, 2, false);
            }
            if (i == 2) {
                int i3 = (this.percenWin * (this.wDia - width)) / 100;
                if (i3 <= 0) {
                    i3 = 7;
                }
                if (this.percenWin <= 0) {
                    i3 = 0;
                }
                mgraphics.setColor(-8967919);
                mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5, this.yDia + 7 + (i * this.h), this.wDia - width, 14, false);
                mgraphics.setColor(-526425);
                mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5 + 1, this.yDia + 7 + (i * this.h) + 1, (this.wDia - width) - 2, 12, false);
                mgraphics.setColor(-8952235);
                mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5 + 2, this.yDia + 7 + (i * this.h) + 2, (this.wDia - width) - 4, 10, false);
                mgraphics.setColor(this.color[this.indexcolorpercen]);
                mgraphics.fillRect(((this.xDia + width) - (width / 4)) + 5 + 3, this.yDia + 7 + (i * this.h) + 3, i3 - 6, 8, false);
                mFont.tahoma_7b_black.drawString(mgraphics, this.texpercen, ((this.xDia + width) - (width / 4)) + 5 + 2 + (((this.wDia - width) - 4) / 2), this.yDia + 7 + (i * this.h) + 1, 2, false);
            }
        }
        this.cmdClose.paint(mgraphics, GameCanvas.hw, (this.yDia + this.hDia) - iCommand.hButtonCmd);
    }

    @Override // Model.AvMain
    public void update() {
        if (this.currTime > 0) {
            this.currTime = this.maxTime - (mSystem.currentTimeMillis() / 1000);
        }
        this.percenTime = (int) ((this.currTime * 100) / this.timesv);
        if (this.percenTime >= 75) {
            this.indexcolor = (byte) 0;
        } else if (this.percenTime >= 50) {
            this.indexcolor = (byte) 1;
        } else if (this.percenTime >= 25) {
            this.indexcolor = (byte) 2;
        } else {
            this.indexcolor = (byte) 3;
        }
        if (GameCanvas.isTouch) {
            this.cmdClose.updatePointer();
        } else if (GameCanvas.keyMyPressed[5]) {
            this.cmdClose.perform();
            GameCanvas.keyMyPressed[5] = false;
        }
        super.update();
    }
}
