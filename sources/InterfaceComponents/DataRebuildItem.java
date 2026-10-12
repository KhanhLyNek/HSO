package InterfaceComponents;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/DataRebuildItem.class */
public class DataRebuildItem {
    public short id;
    public short valueWing;
    public byte lv;
    public byte cat;
    public int priceCoin;
    public int priceGold;
    public byte[] mValue;

    public DataRebuildItem() {
    }

    public DataRebuildItem(byte b, int i, int i2, byte[] bArr) {
        this.lv = b;
        this.priceCoin = i;
        this.priceGold = i2;
        this.mValue = bArr;
    }

    public DataRebuildItem(short s, short s2) {
        this.id = s;
        this.valueWing = s2;
    }

    public DataRebuildItem(short s, byte b) {
        this.id = s;
        this.cat = b;
    }
}
