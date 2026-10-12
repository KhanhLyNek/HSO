package Model;

/* loaded from: KnightOnline_v403.jar:Model/Point_Focus.class */
public class Point_Focus {
    public boolean isSpeedUp = false;
    public int x;
    public int y;
    public int dis;
    public int fRe;
    public int f;
    public int frame;
    public int vx;
    public int vy;
    public int toX;
    public int toY;
    public int maxdis;
    int goc;

    public Point_Focus(int i, int i2, int i3, int i4, int i5) {
        this.x = i;
        this.y = i2;
        this.toX = i3;
        this.toY = i4;
        this.maxdis = i5;
    }

    public Point_Focus() {
    }

    public void createNormal() {
        this.vx = CRes.random_Am(0, 5);
        this.vy = CRes.random_Am(0, 5);
    }

    public boolean update() {
        if (this.x < this.toX) {
            if (this.vx < 4) {
                this.vx++;
            }
        } else if (this.vx > -4) {
            this.vx--;
        }
        if (this.y < this.toY) {
            if (this.vy < 4) {
                this.vy++;
            }
        } else if (this.vy > -4) {
            this.vy--;
        }
        int i = this.toX - this.x;
        int i2 = this.toY - this.y;
        if (CRes.abs(i) < this.maxdis && CRes.abs(i2) < this.maxdis) {
            this.vx = 0;
            this.vy = 0;
            return true;
        }
        this.x += this.vx;
        this.y += this.vy;
        return false;
    }

    public void update_Vx_Vy() {
        this.f++;
        this.x += this.vx;
        this.y += this.vy;
    }
}
