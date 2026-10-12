package GameObjects;

import Model.CRes;

/* loaded from: KnightOnline_v403.jar:GameObjects/MagicLogic.class */
public class MagicLogic {
    public int angle;
    public int vx;
    public int vy;
    public short va;
    public int x;
    public int y;
    public int z;
    public MainObject target;
    int life;
    public int headArrowFrame;
    public int headTransform;
    public int index = 0;
    public boolean isSpeedUp = false;
    public boolean isEnd = false;

    public void setAngle(int i) {
        this.angle = i;
        this.vx = (this.va * CRes.cos(i)) >> 10;
        this.vy = (this.va * CRes.sin(i)) >> 10;
    }

    public void set(int i, int i2, int i3, int i4, MainObject mainObject) {
        this.x = i2;
        this.y = i3;
        this.target = mainObject;
        this.isEnd = false;
        switch (i4) {
            case 0:
                this.angle = 90;
                break;
            case 1:
                this.angle = 270;
                break;
            case 2:
                this.angle = 180;
                break;
            case 3:
                this.angle = 0;
                break;
        }
        if (i == 20) {
        }
        this.va = (short) 15360;
        this.z = 0;
        this.life = 0;
        this.vx = (this.va * CRes.cos(this.angle)) >> 10;
        this.vy = (this.va * CRes.sin(this.angle)) >> 10;
    }

    public void updateAngle() {
        if (this.target == null) {
            this.isEnd = true;
            return;
        }
        int i = this.target.x - this.x;
        int height = (this.target.y - (this.target.getHeight() >> 1)) - this.y;
        this.life++;
        if (CRes.abs(i) < 16 && CRes.abs(height) < 16) {
            this.isEnd = true;
            return;
        }
        int angle = CRes.angle(i, height);
        if (Math.abs(angle - this.angle) < 90 || (i * i) + (height * height) > 4096) {
            if (Math.abs(angle - this.angle) < 15) {
                this.angle = angle;
            } else if ((angle - this.angle >= 0 && angle - this.angle < 180) || angle - this.angle < -180) {
                this.angle = CRes.fixangle(this.angle + 15);
            } else {
                this.angle = CRes.fixangle(this.angle - 15);
            }
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va = (short) (this.va + 1024);
        }
        this.vx = (this.va * CRes.cos(this.angle)) >> 10;
        this.vy = (this.va * CRes.sin(this.angle)) >> 10;
        int i2 = i + this.vx;
        int i3 = i2 >> 10;
        this.x += i3;
        int i4 = i2 & 1023;
        int i5 = height + this.vy;
        int i6 = i5 >> 10;
        this.y += i6;
        int i7 = i5 & 1023;
        this.index = Arrow.findDirIndexFromAngle(CRes.angle(i3, -i6));
        this.headArrowFrame = Arrow.FRAME[this.index];
        this.headTransform = Arrow.TRANSFORM[this.index];
    }
}
