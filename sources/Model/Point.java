package Model;

import CLib.mGraphics;
import CLib.mVector;
import GameScreen.GameScreen;

/* loaded from: KnightOnline_v403.jar:Model/Point.class */
public class Point {
    public static final byte DAU_CHAN = 0;
    public static final byte DONG_NUOC = 1;
    public int x;
    public int y;
    public int g;
    public int v;
    public int w;
    public int h;
    public int limitY;
    public int vx;
    public int vy;
    public int x2;
    public int y2;
    public int dis;
    public int f;
    public int fRe;
    public int frame;
    public int maxframe;
    public int fSmall;
    public mVector vecEffPoint;
    public String name;
    public static FrameImage[] FraEffInMap;
    public int color = 0;
    public boolean isRemove = false;
    public boolean isSmall = false;

    public Point() {
    }

    public Point(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public void update() {
        this.f++;
        this.x += this.vx;
        this.y += this.vy;
    }

    public void paint(mGraphics mgraphics) {
        if (!this.isRemove) {
            int i = 0;
            if (this.isSmall && this.f >= this.fSmall) {
                i = 1;
            }
            FraEffInMap[this.color].drawFrame((this.frame / 2) + i, this.x, this.y, this.dis, 3, mgraphics);
        }
    }

    public void updateInMap() {
        boolean z = true;
        if (this.color == 0 && GameScreen.vecStep.size() < 10) {
            z = false;
        }
        if (!z) {
            return;
        }
        this.f++;
        if (this.maxframe > 1) {
            this.frame++;
            if (this.frame / 2 >= this.maxframe) {
                this.frame = 0;
            }
        }
        if (this.f >= this.fRe) {
            this.isRemove = true;
        }
    }
}
