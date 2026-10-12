package Model;

import CLib.mHashtable;
import CLib.mVector;
import GameObjects.Item;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:Model/MainTemplateItem.class */
public class MainTemplateItem {
    public int ID;
    public int IconId;
    public String name;
    int timenull;
    public int typeItem;
    public int idPartItem;
    public int classItem;
    public int[] mValueItem;
    public byte[] mValueName;
    public int sellPotion;
    public int valuePotion;
    public byte moneyType;
    public long PricePoition;
    public byte typePotion;
    public String contentPotion;
    public byte typeSell;
    public byte typeTrade;
    public static final byte TYPE_AO = 0;
    public static final byte TYPE_QUAN = 1;
    public static final byte TYPE_NON = 2;
    public static final byte TYPE_GANGTAY = 3;
    public static final byte TYPE_NHAN = 4;
    public static final byte TYPE_DAYCHUYEN = 5;
    public static final byte TYPE_GIAY = 6;
    public static final byte TYPE_CANH = 7;
    public static final byte TYPE_VUKHI1 = 8;
    public static final byte TYPE_VUKHI2 = 9;
    public static final byte TYPE_VUKHI3 = 10;
    public static final byte TYPE_VUKHI4 = 11;
    public static final byte TYPE_PET = 14;
    public static final byte I_VUKHI = 0;
    public static final byte I_AO = 1;
    public static final byte I_GANGTAY = 2;
    public static final byte I_NHAN1 = 3;
    public static final byte I_DAYCHUYEN = 4;
    public static final byte I_PET = 5;
    public static final byte I_NON = 6;
    public static final byte I_QUAN = 7;
    public static final byte I_GIAY = 8;
    public static final byte I_NHAN2 = 9;
    public static final byte I_CANH = 10;
    public static final byte I_HEAD = 12;
    public static final byte I_EYE = 13;
    public static final byte I_HAIR = 14;
    boolean canTrade;
    public static boolean isload = true;
    public static int[] mItem_Equip_Tem = {-1, 0, 3, 4, 5, 12, 2, 1, 6, 4, 7, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2};
    public static int[] mItem_Rotate_Tem_Equip = {1, 7, 6, 2, -1, 4, 8, 10, 0, 0, 0, 0, 5};
    public static mHashtable hashPotionTem = new mHashtable();
    public static mHashtable hashMaterialTem = new mHashtable();
    public static mHashtable hashMaterialTem2 = new mHashtable();
    public static mHashtable hashItemTem = new mHashtable();
    public static mHashtable hashPetTem = new mHashtable();

    public MainTemplateItem() {
        this.canTrade = false;
    }

    public MainTemplateItem(short s, String str, byte b, byte b2, byte b3, short s2, int[] iArr, byte[] bArr) {
        this.canTrade = false;
        this.ID = s;
        this.IconId = s2;
        this.name = str;
        this.typeItem = b;
        this.idPartItem = b2;
        this.classItem = b3;
        this.mValueItem = iArr;
        this.mValueName = bArr;
    }

    public MainTemplateItem(short s, short s2, long j, String str, String str2, byte b, byte b2, byte b3, short s3, boolean z) {
        this.canTrade = false;
        this.ID = s;
        this.IconId = s2;
        this.PricePoition = j;
        this.name = str;
        this.contentPotion = str2;
        this.typePotion = b;
        this.moneyType = b2;
        this.sellPotion = b3;
        this.valuePotion = s3;
        this.canTrade = z;
    }

    public static void removeUpdateItemInventory(int i) {
        int i2 = 0;
        while (i2 < Item.VecInvetoryPlayer.size()) {
            Item item = (Item) Item.VecInvetoryPlayer.elementAt(i2);
            if (item.ItemCatagory == i) {
                Item.VecInvetoryPlayer.removeElement(item);
                i2--;
            }
            i2++;
        }
    }

    public static void removeUpdateItemChest(int i) {
        int i2 = 0;
        while (i2 < Item.VecChestPlayer.size()) {
            Item item = (Item) Item.VecChestPlayer.elementAt(i2);
            if (item.ItemCatagory == i) {
                Item.VecInvetoryPlayer.removeElement(item);
                i2--;
            }
            i2++;
        }
    }

    public static void removeUpdateItemVec(int i, mVector mvector) {
        int i2 = 0;
        while (i2 < mvector.size()) {
            Item item = (Item) mvector.elementAt(i2);
            if (item.ItemCatagory == i) {
                mvector.removeElement(item);
                i2--;
            }
            i2++;
        }
    }

    public static MainTemplateItem getItemTem(short s) {
        MainTemplateItem mainTemplateItem = (MainTemplateItem) hashItemTem.get(new StringBuffer().append("").append((int) s).toString());
        if (mainTemplateItem == null) {
            mainTemplateItem = new MainTemplateItem();
            hashItemTem.put(new StringBuffer().append("").append((int) s).toString(), mainTemplateItem);
            GlobalService.gI().getItemTem(s);
        }
        if (mainTemplateItem.name == null) {
            mainTemplateItem.timenull++;
            if (mainTemplateItem.timenull >= 200) {
                GlobalService.gI().getItemTem(s);
                mainTemplateItem.timenull = 0;
            }
        }
        return mainTemplateItem;
    }

    public static MainTemplateItem getPotionTem(short s) {
        return (MainTemplateItem) hashPotionTem.get(new StringBuffer().append("").append((int) s).toString());
    }
}
