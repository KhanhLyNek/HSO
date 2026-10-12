package Model;

/* loaded from: KnightOnline_v403.jar:Model/Position.class */
public class Position {
    public int x;
    public int y;
    public int anchor;
    public byte depth;
    public short index;

    public Position() {
        this.depth = (byte) 0;
        this.index = (short) -1;
        this.x = 0;
        this.y = 0;
    }

    public Position(int i, int i2) {
        this.depth = (byte) 0;
        this.index = (short) -1;
        this.x = i;
        this.y = i2;
    }

    public Position(int i, int i2, int i3) {
        this.depth = (byte) 0;
        this.index = (short) -1;
        this.x = i;
        this.y = i2;
        this.anchor = i3;
    }

    public boolean setDetectX(int i, int i2) {
        if (CRes.abs(i - this.x) <= i2) {
            return true;
        }
        return false;
    }

    public boolean setDetectY(int i, int i2) {
        if (CRes.abs(i - this.y) <= i2) {
            return true;
        }
        return false;
    }
}
