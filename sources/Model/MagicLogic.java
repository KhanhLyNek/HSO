package Model;

import GameObjects.MainObject;

/* loaded from: KnightOnline_v403.jar:Model/MagicLogic.class */
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
    public static final short[] SPEED = {1, 60, 8, 1, 1, 16, 60, 18};
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
            i = 2;
        }
        this.va = (short) (256 * SPEED[i]);
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
        int i2 = (this.target.y - (this.target.hOne >> 1)) - this.y;
        this.life++;
        if ((CRes.abs(i) < 16 && CRes.abs(i2) < 16) || this.life > 60) {
            this.isEnd = true;
            return;
        }
        int angle = CRes.angle(i, i2);
        if (Math.abs(angle - this.angle) < 90 || (i * i) + (i2 * i2) > 4096) {
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
        int i3 = i + this.vx;
        this.x += i3 >> 10;
        int i4 = i3 & 1023;
        int i5 = i2 + this.vy;
        this.y += i5 >> 10;
        int i6 = i5 & 1023;
    }
}
