package GameObjects;

/* loaded from: KnightOnline_v403.jar:GameObjects/item_sell.class */
public class item_sell {
    public int price;
    public short id;
    public short soluuong;
    public byte cat;

    public item_sell(short s, int i) {
        this.id = s;
        this.price = i;
    }

    public item_sell() {
    }

    public item_sell(short s, int i, int i2) {
        this.id = s;
        this.price = i;
        this.soluuong = (short) i2;
    }

    public item_sell(short s, int i, int i2, int i3) {
        this.id = s;
        this.price = i;
        this.soluuong = (short) i2;
        this.cat = (byte) i3;
    }
}
