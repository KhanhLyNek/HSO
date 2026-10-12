package GameObjects;

/* loaded from: KnightOnline_v403.jar:GameObjects/AutoGetItem.class */
public class AutoGetItem {
    byte valueColorItem;
    byte isGetMoney;
    byte isGetPotion;
    public static byte POI_NHAT_HET = 0;
    public static byte POI_NHAT_MAU = 1;
    public static byte POI_NHAT_NANG_LUONG = 2;
    public static byte POI_KHONG_NHAT = 3;
    public boolean isremove = false;

    public AutoGetItem(byte b, byte b2, byte b3) {
        this.valueColorItem = b;
        this.isGetMoney = b3;
        this.isGetPotion = b2;
    }
}
