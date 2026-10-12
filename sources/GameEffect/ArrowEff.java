package GameEffect;

import CLib.mGraphics;
import GameObjects.DataSkillEff;
import GameObjects.MainObject;
import Model.CRes;

/* loaded from: KnightOnline_v403.jar:GameEffect/ArrowEff.class */
public class ArrowEff extends MainEffect {
    public static final int[] TRANSFORM = {0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
    public static final byte[] FRAME = {0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};
    public static final int[] ARROWINDEX = {0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
    int dx;
    int dy;
    int angle;
    int xto;
    int yto;
    int d;
    MainObject target;
    DataSkillEff arrow;
    int transform;
    short timelive;

    ArrowEff(int i, int i2, MainObject mainObject, int i3, int i4, int i5) {
        this.x = i;
        this.y = i2;
        this.target = mainObject;
        this.xto = mainObject.x;
        this.yto = mainObject.y;
        setspeed();
        this.timelive = (short) 500;
        if (FRAME[this.d] == 0) {
            this.arrow = new DataSkillEff(i3, this.transform);
        } else if (FRAME[this.d] == 1) {
            this.arrow = new DataSkillEff(i4, this.transform);
        } else if (FRAME[this.d] == 2) {
            this.arrow = new DataSkillEff(i5, this.transform);
        }
    }

    ArrowEff(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.x = i;
        this.y = i2;
        this.xto = i3;
        this.yto = i4;
        this.xto = this.target.x;
        this.yto = this.target.y;
        setspeed();
        this.timelive = (short) 50;
        if (FRAME[this.d] == 0) {
            this.arrow = new DataSkillEff(i5, this.transform);
        } else if (FRAME[this.d] == 1) {
            this.arrow = new DataSkillEff(i6, this.transform);
        } else if (FRAME[this.d] == 2) {
            this.arrow = new DataSkillEff(i7, this.transform);
        }
    }

    public void setspeed() {
        this.dx = this.xto - this.x;
        this.dy = (this.yto - 0) - this.y;
        this.angle = CRes.angle(this.dx, this.dy);
        this.vx = (1 * CRes.cos(this.angle)) >> 10;
        this.vy = (1 * CRes.sin(this.angle)) >> 10;
        this.d = findDirIndexFromAngle(CRes.angle(this.dx, -this.dy));
        this.transform = TRANSFORM[this.d];
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

    @Override // GameEffect.MainEffect
    public void paint(mGraphics mgraphics) {
        this.arrow.paintTopArrow(mgraphics, this.x, this.y);
        this.arrow.paintBottomArrow(mgraphics, this.x, this.y);
    }

    @Override // GameEffect.MainEffect
    public void update() {
        if (this.arrow != null) {
            this.arrow.updateArrow();
        }
        this.x += this.vx;
        this.y += this.vy;
        this.timelive = (short) (this.timelive - 1);
        if (this.timelive < 0) {
            this.isRemove = true;
        }
    }
}
