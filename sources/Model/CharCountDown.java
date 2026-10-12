package Model;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;

/* loaded from: KnightOnline_v403.jar:Model/CharCountDown.class */
public class CharCountDown {
    private long maxTime;
    private int w = 60;
    public boolean WantDestroy;
    public String tile;
    long timesv;
    int timeper;
    int per;

    public CharCountDown(long j, String str) {
        this.tile = "";
        this.tile = str;
        this.maxTime = j;
        this.timeper = (int) (this.maxTime - mSystem.currentTimeMillis());
    }

    public void paint(mGraphics mgraphics, int i, int i2) {
        int currentTimeMillis = (int) (60 - (((this.maxTime - mSystem.currentTimeMillis()) * this.w) / this.timeper));
        mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, 62, 9, 0, i, i2, 0, false);
        mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 7, currentTimeMillis, 7, 0, i + 1, i2 + 1, 0, false);
        mFont.tahoma_7_black.drawString(mgraphics, this.tile, i + (this.w / 2) + 1, ((i2 - 10) + 1) - 3, 2, false);
        mFont.tahoma_7_white.drawString(mgraphics, this.tile, i + (this.w / 2), (i2 - 10) - 3, 2, false);
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append("").append(this.per).append("%").toString(), i + (this.w / 2), i2 - 1, 2, false);
    }

    public void update() {
        this.per = (int) (100 - (((this.maxTime - mSystem.currentTimeMillis()) * 100) / this.timeper));
        if (this.maxTime - mSystem.currentTimeMillis() <= 0) {
            this.WantDestroy = true;
        }
    }
}
