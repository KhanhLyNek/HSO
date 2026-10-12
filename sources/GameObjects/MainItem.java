package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Skill.HotKey;

/* loaded from: KnightOnline_v403.jar:GameObjects/MainItem.class */
public class MainItem extends Item {
    public static final int FOOT = 8;
    public static final int ATB_FOOT_SNOW = 67;

    public MainItem(int i, int i2, String str, String str2, int i3, int i4, long j, byte b, byte b2, byte b3, byte b4) {
        this.Id = i;
        this.imageId = i2;
        this.itemName = str;
        this.typePotion = b;
        this.numPotion = i3;
        this.ItemCatagory = i4;
        this.priceItem = j;
        this.priceType = b2;
        this.canSell = b3;
        this.canTrade = b4;
        mMoreContent(str2);
        this.IndexSort = (byte) 0;
    }

    public MainItem clonePotion() {
        MainItem mainItem = new MainItem();
        mainItem.Id = this.Id;
        mainItem.imageId = this.imageId;
        mainItem.itemName = this.itemName;
        mainItem.typePotion = this.typePotion;
        mainItem.numPotion = this.numPotion;
        mainItem.ItemCatagory = this.ItemCatagory;
        mainItem.priceItem = this.priceItem;
        mainItem.priceType = this.priceType;
        mainItem.canSell = this.priceType;
        mainItem.canTrade = this.priceType;
        if (this.content != null) {
            mainItem.mMoreContent(this.content);
        }
        this.IndexSort = (byte) 0;
        return mainItem;
    }

    @Override // GameObjects.Item
    public void mMoreContent(String str) {
        super.mMoreContent(str);
    }

    public MainItem() {
    }

    public MainItem clone() {
        MainItem mainItem = new MainItem();
        mainItem.itemNameExcludeLv = this.itemNameExcludeLv;
        mainItem.itemName = this.itemName;
        mainItem.imageId = this.imageId;
        mainItem.Id = this.Id;
        mainItem.tier = this.tier;
        mainItem.colorNameItem = this.colorNameItem;
        mainItem.classcharItem = this.classcharItem;
        mainItem.ItemCatagory = this.ItemCatagory;
        mainItem.mInfo = this.mInfo;
        mainItem.type_Only_Item = this.type_Only_Item;
        mainItem.IdTem = this.IdTem;
        mainItem.priceItem = this.priceItem;
        mainItem.LvItem = this.LvItem;
        mainItem.canSell = this.canSell;
        mainItem.canTrade = this.canTrade;
        mainItem.timeUse = this.timeUse;
        mainItem.isLock = this.isLock;
        mainItem.timeDem = this.timeDem;
        mainItem.numPotion = this.numPotion;
        mainItem.priceType = this.priceType;
        mainItem.mInfo = this.mInfo;
        mainItem.IndexSort = this.IndexSort;
        mainItem.infoHop = new String[]{"", ""};
        return mainItem;
    }

    public static MainItem MainItem_Item(int i, String str, int i2, byte b, int i3, int i4, int i5, MainInfoItem[] mainInfoItemArr, int i6, boolean z, short s, long j, short s2, byte b2, byte b3, int i7, byte b4, byte b5) {
        return new MainItem(i, str, i2, b, i3, i4, i5, mainInfoItemArr, i6, z, s, j, s2, b2, b3, i7, b4, b5);
    }

    public static MainItem MainItem_Item(int i, String str, int i2, byte b, int i3, int i4, int i5, MainInfoItem[] mainInfoItemArr, int i6, boolean z, short s, long j, short s2, byte b2, byte b3, int i7, byte b4, byte b5, int i8, long j2) {
        return new MainItem(i, str, i2, b, i3, i4, i5, mainInfoItemArr, i6, z, s, j, s2, b2, b3, i7, b4, b5, i8, j2);
    }

    public MainItem(int i, String str, int i2, byte b, int i3, int i4, int i5, MainInfoItem[] mainInfoItemArr, int i6, boolean z, short s, long j, short s2, byte b2, byte b3, int i7, byte b4, byte b5, int i8, long j2) {
        this.itemNameExcludeLv = str;
        this.itemName = str;
        this.imageId = i2;
        this.Id = i;
        this.tier = b;
        if (this.tier > 0) {
            this.itemName = new StringBuffer().append(this.itemName).append(" +").append((int) this.tier).toString();
        }
        this.colorNameItem = i3;
        this.classcharItem = i4;
        this.ItemCatagory = i5;
        this.mInfo = mainInfoItemArr;
        this.type_Only_Item = i6;
        this.IdTem = s;
        this.priceItem = j;
        this.LvItem = s2;
        this.canSell = b2;
        this.canTrade = b3;
        this.timeUse = i7;
        this.isLock = b5;
        this.timeDefaultItemFashion = i8;
        this.currentTimeItemFashion = j2;
        if (i7 > 0) {
            this.timeDem = GameCanvas.timeNow;
        } else {
            this.timeDem = 0L;
        }
        this.priceType = b4;
        if ((this.mInfo != null && this.mInfo.length > 0) || this.priceItem > 0 || this.timeDem > 0) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                CRes.selectionSort(mainInfoItemArr);
            }
            if (!z) {
                setContentItem();
            } else {
                setContentTem();
            }
        }
        this.IndexSort = (byte) 10;
    }

    public MainItem(int i, String str, int i2, byte b, int i3, int i4, int i5, MainInfoItem[] mainInfoItemArr, int i6, boolean z, short s, long j, short s2, byte b2, byte b3, int i7, byte b4, byte b5) {
        this.itemNameExcludeLv = str;
        this.itemName = str;
        this.imageId = i2;
        this.Id = i;
        this.tier = b;
        if (this.tier > 0) {
            this.itemName = new StringBuffer().append(this.itemName).append(" +").append((int) this.tier).toString();
        }
        this.colorNameItem = i3;
        this.classcharItem = i4;
        this.ItemCatagory = i5;
        this.mInfo = mainInfoItemArr;
        this.type_Only_Item = i6;
        this.IdTem = s;
        this.priceItem = j;
        this.LvItem = s2;
        this.canSell = b2;
        this.canTrade = b3;
        this.timeUse = i7;
        this.isLock = b5;
        if (i7 > 0) {
            this.timeDem = GameCanvas.timeNow;
        } else {
            this.timeDem = 0L;
        }
        this.priceType = b4;
        if ((this.mInfo != null && this.mInfo.length > 0) || this.priceItem > 0 || this.timeDem > 0) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                CRes.selectionSort(mainInfoItemArr);
            }
            if (!z) {
                setContentItem();
            } else {
                setContentTem();
            }
        }
        this.IndexSort = (byte) 10;
        this.infoHop = new String[]{"", ""};
    }

    public static MainItem MainItem_Toc(int i, String str, int i2, byte b, int i3, MainInfoItem[] mainInfoItemArr, boolean z, short s, long j, byte b2, byte b3, byte b4) {
        return new MainItem(i, str, i2, b, i3, mainInfoItemArr, z, s, j, b2, b3, b4);
    }

    public MainItem(int i, String str, int i2, byte b, int i3, MainInfoItem[] mainInfoItemArr, boolean z, short s, long j, byte b2, byte b3, byte b4) {
        this.itemName = str;
        this.imageId = i2;
        this.Id = i;
        this.tier = b;
        this.ItemCatagory = i3;
        this.mInfo = mainInfoItemArr;
        this.IdTem = s;
        this.priceItem = j;
        this.priceType = b2;
        this.canSell = b3;
        this.canTrade = b4;
        if (this.mInfo != null) {
            CRes.selectionSort(mainInfoItemArr);
            setContentHair();
        }
        this.IndexSort = (byte) 1;
    }

    public MainItem(int i, long j) {
        this.Id = i;
        this.priceItem = j;
        this.priceType = (byte) 1;
        this.ItemCatagory = 8;
        this.itemName = T.iconclan;
        mMoreContent(T.contentclan);
        this.IndexSort = (byte) 1;
    }

    public static MainItem MainItem_Material(int i, String str, int i2, int i3, long j, byte b, String str2, int i4, byte b2, short s, byte b3, byte b4) {
        return new MainItem(i, str, i2, i3, j, b, str2, i4, b2, s, b3, b4);
    }

    public MainItem cloneNguyenLieu() {
        MainItem mainItem = new MainItem();
        mainItem.itemName = this.itemName;
        mainItem.imageId = this.imageId;
        mainItem.Id = this.Id;
        mainItem.ItemCatagory = this.ItemCatagory;
        mainItem.priceItem = this.priceItem;
        mainItem.priceType = this.priceType;
        mainItem.value = this.value;
        mainItem.canSell = this.canSell;
        mainItem.canTrade = this.canTrade;
        mainItem.content = this.content;
        mainItem.typeMaterial = this.typeMaterial;
        if (this.content != null) {
            mainItem.mMoreContent(this.content);
        }
        mainItem.IndexSort = (byte) 2;
        return mainItem;
    }

    public MainItem(int i, String str, int i2, int i3, long j, byte b, String str2, int i4, byte b2, short s, byte b3, byte b4) {
        this.itemName = str;
        this.imageId = i2;
        this.Id = i;
        this.ItemCatagory = i3;
        this.priceItem = j;
        this.priceType = b;
        this.value = i4;
        this.numPotion = s;
        this.canSell = b3;
        this.canTrade = b4;
        this.content = str2;
        this.typeMaterial = b2;
        if (str2 != null) {
            mMoreContent(str2);
        }
        this.IndexSort = (byte) 2;
    }

    public MainItem(int i, String str, int i2, String str2, byte b, byte b2) {
        this.Id = i;
        this.imageId = i;
        this.itemName = str;
        this.numPotion = i2;
        this.ItemCatagory = 5;
        this.canSell = b;
        this.canTrade = b2;
        mMoreContent(str2);
        this.IndexSort = (byte) 3;
    }

    public MainItem(int i, String str, int i2, int i3, byte b, String str2, MainInfoItem[] mainInfoItemArr, byte b2, byte b3, short s, byte b4, byte b5, byte b6) {
        this.Id = i;
        this.itemName = str;
        this.numPotion = i2;
        this.imageId = i3;
        this.ItemCatagory = b;
        this.colorNameItem = b2;
        this.mInfo = mainInfoItemArr;
        this.classcharItem = b3;
        this.LvItem = s;
        this.canSell = b5;
        this.canTrade = b6;
        this.tier = b4;
        if (this.tier > 0) {
            this.itemName = new StringBuffer().append(this.itemName).append(" +").append((int) this.tier).toString();
        }
        if (str2 != null) {
            mMoreContent(str2);
        } else {
            setContentItem();
        }
        this.IndexSort = (byte) 4;
    }

    public MainItem(String str, int i, int i2, byte b, byte b2, byte b3) {
        this.colorNameItem = b3;
        this.itemName = str;
        this.numPotion = i2;
        this.imageId = i;
        this.ItemCatagory = b;
        this.tier = b2;
        if (this.tier > 0) {
            this.itemName = new StringBuffer().append(this.itemName).append(" +").append((int) this.tier).toString();
        }
    }

    public void setContentHair() {
        if (this.itemName == null) {
            this.itemName = null;
            return;
        }
        int length = this.mInfo.length;
        this.mcontent = new String[length];
        this.mColor = new int[length];
        int width = mFont.tahoma_7b_white.getWidth(this.itemName);
        if (width > this.sizeW - 10) {
            this.sizeW = width + 10;
        }
        for (int i = 0; i < length; i++) {
            MainInfoItem mainInfoItem = this.mInfo[i];
            this.mcontent[i] = new StringBuffer().append(Item.nameInfoItem[mainInfoItem.id]).append(": ").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value)).toString();
            this.mColor[i] = Item.colorInfoItem[mainInfoItem.id];
            int width2 = mFont.tahoma_7_black.getWidth(this.mcontent[i]);
            if (width2 > this.sizeW - 10) {
                this.sizeW = width2 + 10;
            }
        }
        int i2 = 0 + 1;
        if (i2 > 0) {
            this.mPlusContent = new String[i2];
            this.mPlusColor = new int[i2];
            if (this.priceItem > 0) {
                this.mPlusContent[0] = new StringBuffer().append(T.price).append(": ").append(this.priceItem).append(getPriceType()).toString();
                this.mPlusColor[0] = 2;
                int i3 = 0 + 1;
                return;
            } else {
                this.mPlusContent[0] = T.dasohuu;
                this.mPlusColor[0] = 2;
                int i4 = 0 + 1;
                return;
            }
        }
        this.mPlusContent = null;
        this.mPlusColor = null;
    }

    public static String getDotNumber(long j) {
        String stringBuffer = new StringBuffer().append("").append(j).toString();
        int length = stringBuffer.length() / 3;
        if (stringBuffer.length() % 3 == 0) {
            length--;
        }
        for (int i = 0; i < length; i++) {
            int length2 = (stringBuffer.length() - ((i + 1) * 3)) - i;
            stringBuffer = new StringBuffer().append(stringBuffer.substring(0, length2)).append(".").append(stringBuffer.substring(length2, stringBuffer.length())).toString();
        }
        return stringBuffer;
    }

    public static String getNopercent(int i) {
        if (i % 100 == 0) {
            return new StringBuffer().append(i / 100).append("").toString();
        }
        if (i % 10 == 0) {
            return new StringBuffer().append(i / 100).append(".").append((i % 100) / 10).toString();
        }
        return new StringBuffer().append(i / 100).append(".").append((i % 100) / 10).append("").append(i % 10).toString();
    }

    public void paint(mGraphics mgraphics) {
    }

    public void paintColorItem(mGraphics mgraphics, int i, int i2, int i3) {
    }

    @Override // GameObjects.Item
    public void paintItem(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        super.paintItem(mgraphics, i, i2, i3, i4, i5);
    }

    public void paintItem_not_num(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        super.paintItem_notnum(mgraphics, i, i2, i3, i4, i5);
    }

    @Override // GameObjects.Item
    public void paintItemNew(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        super.paintItemNew(mgraphics, i, i2, i3, i4, i5);
    }

    public static void setAddHotKey(byte b, boolean z) {
        HotKey hotKey = null;
        if (b == 0) {
            hotKey = Player.mhotkey[Player.levelTab][4];
        } else if (b == 1) {
            hotKey = Player.mhotkey[Player.levelTab][3];
        }
        if (hotKey == null || hotKey.type != HotKey.NULL) {
            hotKey = new HotKey();
            hotKey.type = HotKey.NULL;
        }
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); i++) {
            MainItem mainItem = (MainItem) Item.VecInvetoryPlayer.elementAt(i);
            if (mainItem.ItemCatagory == 4 && mainItem.typePotion == b && ((hotKey.id < mainItem.Id && z) || ((hotKey.id > mainItem.Id && !z) || hotKey.type == HotKey.NULL))) {
                hotKey.setHotKey(mainItem.Id, HotKey.POTION, b);
            }
        }
    }

    public void set_can_Sell(byte b) {
        this.can_sell_for_other_player = b;
    }

    public void setCanShell_notCanTrade(byte b) {
        this.CanShell_CanNotTrade = b;
    }

    @Override // GameObjects.Item
    public boolean isMaterialHopNguyenLieu() {
        if (this.ItemCatagory == 7 && MainObject.isMaHopNguyenLieu(this.Id)) {
            return true;
        }
        return false;
    }
}
