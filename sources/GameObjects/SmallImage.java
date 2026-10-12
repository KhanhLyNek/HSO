package GameObjects;

import CLib.mGraphics;
import CLib.mImage;

/* loaded from: KnightOnline_v403.jar:GameObjects/SmallImage.class */
public class SmallImage {
    public short id;
    public short x;
    public short y;
    public short w;
    public short h;

    public SmallImage(int i, int i2, int i3, int i4, int i5) {
        this.id = (short) i;
        this.x = (short) i2;
        this.y = (short) i3;
        this.w = (short) i4;
        this.h = (short) i5;
    }

    public void paint(mGraphics mgraphics, mImage mimage, int i, int i2) {
    }
}
