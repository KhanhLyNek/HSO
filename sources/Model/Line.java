package Model;

/* loaded from: KnightOnline_v403.jar:Model/Line.class */
public class Line {
    public int x0;
    public int y0;
    public int x1;
    public int y1;
    public int vx;
    public int vy;
    public int f;
    public int fRe;
    public int idColor;
    public boolean is2Line = false;

    public void setLine(int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.x0 = i;
        this.y0 = i2;
        this.x1 = i3;
        this.y1 = i4;
        this.vx = i5;
        this.vy = i6;
        this.is2Line = z;
    }

    public void update() {
        this.x0 += this.vx;
        this.x1 += this.vx;
        this.y0 += this.vy;
        this.y1 += this.vy;
        this.f++;
    }
}
