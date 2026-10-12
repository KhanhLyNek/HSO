package GameObjects;

import Model.T;

/* loaded from: KnightOnline_v403.jar:GameObjects/MainClan.class */
public class MainClan {
    public short IdIcon;
    public int IdClan;
    public String shortName;
    public String name;
    public String slogan;
    public String nameThuLinh;
    public String noiquy;
    public int numMem;
    public int maxMem;
    public int Lv;
    public int hang;
    public int gold;
    public int ptLv;
    public long coin;
    public byte chucvu;
    public byte typeX2;
    public static final byte THU_LINH = Byte.MAX_VALUE;
    public static final byte PHO_CHI_HUY = 126;
    public static final byte DAI_HIEP_SI = 125;
    public static final byte HIEP_SI_CAO_QUI = 124;
    public static final byte HIEP_SI_DANH_DU = 123;
    public static final byte THANH_VIEN_MOI = 122;
    public static final byte DA_ROI_CLAN = 121;
    public static final byte XIN_GIA_NHAP = 0;
    public static final byte TIM_CLAN_NAME = 1;
    public static final byte THONG_BAO = 2;
    public static final byte ACCEP_JOIN_CLAN = 3;
    public static final byte PHONG_CAP = 4;
    public static final byte HA_CAP = 5;
    public static final byte GOP_XU = 6;
    public static final byte GOP_NGOC = 7;
    public static final byte NEXT_ICON_LIST = 8;
    public static final byte INFO_CLAN = 9;
    public static final byte INVITE_CLAN = 10;
    public static final byte OK_INVITE_CLAN = 11;
    public static final byte DELETE_CLAN = 12;
    public static final byte MEMBER_LIST = 13;
    public static final byte MEMBER_DETAIL = 14;
    public static final byte CLAN_DETAIL = 15;
    public static final byte CLAN_SLOGAN = 16;
    public static final byte CLAN_NOIQUY = 17;
    public static final byte REMOVE_MEM = 18;
    public static final byte UPDATE_INFO_CLAN_OBJ = 19;
    public static final byte ERROR_CREATE_NAME = 20;
    public static final byte INVENTORY_CLAN = 21;
    public static final byte X2_XP_CLAN = 22;
    public short timeX2 = 0;
    public MainThanhTichClan[] mthanhtich = null;
    public boolean isRemove = false;

    public MainClan(int i, short s, String str, byte b) {
        this.chucvu = (byte) 0;
        this.IdClan = i;
        this.IdIcon = s;
        this.shortName = str.toUpperCase();
        this.chucvu = b;
    }

    public void setInfoClan(String str, short s, short s2, short s3, short s4, short s5, String str2, String str3, String str4, long j, int i, MainThanhTichClan[] mainThanhTichClanArr) {
        this.name = str;
        this.Lv = s;
        this.ptLv = s2;
        this.hang = s3;
        this.numMem = s4;
        this.maxMem = s5;
        this.slogan = str2;
        this.noiquy = str3;
        this.nameThuLinh = str4;
        this.coin = j;
        this.gold = i;
        this.mthanhtich = mainThanhTichClanArr;
    }

    public boolean setAddMem() {
        if (this.chucvu == Byte.MAX_VALUE || this.chucvu == 126) {
            return true;
        }
        return false;
    }

    public int getEffChucVu() {
        if (this.chucvu == Byte.MAX_VALUE) {
            return 13;
        }
        if (this.chucvu == 126) {
            return 7;
        }
        return -1;
    }

    public boolean getChucNang() {
        if (this.chucvu == Byte.MAX_VALUE || this.chucvu == 126 || this.chucvu == 125) {
            return true;
        }
        return false;
    }

    public static String getNameChucVu(byte b) {
        if (b >= 121 && b <= Byte.MAX_VALUE) {
            return T.mChucVuClan[Byte.MAX_VALUE - b];
        }
        return "";
    }
}
