package GameObjects;

/* loaded from: KnightOnline_v403.jar:GameObjects/PartFrame.class */
public class PartFrame {
    public short dx;
    public short dy;
    public byte flip;
    public byte onTop = 0;
    public byte idSmallImg;

    public PartFrame(short s, short s2, byte b) {
        this.idSmallImg = b;
        this.dx = s;
        this.dy = s2;
    }
}
