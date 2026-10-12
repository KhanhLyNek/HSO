package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import Main.GameCanvas;
import Model.AvMain;
import Thread_More.LoadMap;

/* loaded from: KnightOnline_v403.jar:GameObjects/TimecountDown.class */
public class TimecountDown {
    short mysecond;
    String tile;
    private long currT = mSystem.currentTimeMillis();
    private long lastT = mSystem.currentTimeMillis();
    public boolean wantdestroy;
    int x;
    int y;

    public TimecountDown(int i, String str, int i2, int i3) {
        this.mysecond = (short) i;
        this.tile = str;
        this.x = i2;
        this.y = i3;
    }

    public void paint(mGraphics mgraphics) {
        mgraphics.drawImage(AvMain.imgBackInfo, this.x, this.y + (GameCanvas.hText / 2), 3, false);
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(this.tile).append(" : ").append(LoadMap.converSecon2hours(this.mysecond)).toString(), this.x, this.y, 2, false);
    }

    public void update() {
        this.currT = mSystem.currentTimeMillis();
        if (this.currT - this.lastT >= 1000) {
            this.lastT = mSystem.currentTimeMillis();
            this.mysecond = (short) (this.mysecond - 1);
        }
        if (this.mysecond <= 0) {
            this.wantdestroy = true;
        }
    }
}
