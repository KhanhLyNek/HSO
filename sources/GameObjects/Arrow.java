package GameObjects;

import CLib.mGraphics;
import Model.CRes;
import Model.FrameImage;

/* loaded from: KnightOnline_v403.jar:GameObjects/Arrow.class */
public class Arrow extends IArrow {
    public int power;
    public int effect;
    public int typeEffEnd = -1;
    private MainObject target;
    private int[] xw;
    private int[] yw;
    int frame;
    int transform;
    int pos;
    private FrameImage img;
    private short endeff;
    public static final int[] ARROWINDEX = {0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
    public static final byte[][] ARROWSIZE = {new byte[0], new byte[0]};
    public static final int[] TRANSFORM = {0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
    public static final byte[] FRAME = {0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};

    public Arrow(int i) {
    }

    public Arrow() {
    }

    @Override // GameObjects.IArrow
    public void setAngle(int i) {
    }

    @Override // GameObjects.IArrow
    public void onArrowTouchTarget() {
        this.wantDestroy = true;
    }

    @Override // GameObjects.IArrow
    public void paint(mGraphics mgraphics) {
        if (this.img != null) {
            this.img.drawFrameEffectSkill(this.frame, this.xw[this.pos], this.yw[this.pos], this.transform, 3, mgraphics);
        }
    }

    @Override // GameObjects.IArrow
    public void set(int i, int i2, int i3, int i4, short s, MainObject mainObject, MainObject mainObject2) {
    }

    public void set(int i, int i2, int i3, int i4, short s, MainObject mainObject, MainObject mainObject2, int i5) {
        this.effect = i5;
        this.effect = s;
        this.target = mainObject2;
        this.power = i4;
        int i6 = mainObject2.x - i2;
        int dy = (mainObject2.y + mainObject2.getDy()) - i3;
        if (i == 7) {
            i6 = (mainObject2.x + 10) - i2;
            dy = (mainObject2.y + mainObject2.getDy()) - i3;
        } else if (i == 8) {
            i6 = (mainObject2.x - 10) - i2;
            dy = ((mainObject2.y - 10) + mainObject2.getDy()) - i3;
        }
        int abs = (Math.abs(i6) + Math.abs(dy)) / 20;
        if (abs < 2) {
            abs = 2;
        }
        this.xw = new int[abs];
        this.yw = new int[abs];
        for (int i7 = 1; i7 < abs; i7++) {
            this.xw[i7] = i2 + ((i7 * i6) / abs);
            this.yw[i7] = i3 + ((i7 * dy) / abs);
        }
        int findDirIndexFromAngle = findDirIndexFromAngle(CRes.angle(i6, -dy));
        this.frame = FRAME[findDirIndexFromAngle];
        this.transform = TRANSFORM[findDirIndexFromAngle];
    }

    public static int findDirIndexFromAngle(int i) {
        for (int i2 = 0; i2 < ARROWINDEX.length - 1; i2++) {
            if (i >= ARROWINDEX[i2] && i <= ARROWINDEX[i2 + 1]) {
                if (i2 >= 16) {
                    return 0;
                }
                return i2;
            }
        }
        return 0;
    }

    @Override // GameObjects.IArrow
    public void update() {
        this.pos++;
        if (this.pos >= this.xw.length) {
            this.pos = this.xw.length;
        }
        if (this.pos == this.xw.length) {
            onArrowTouchTarget();
        }
    }

    @Override // GameObjects.IArrow
    public void SetEffFollow(int i) {
    }
}
