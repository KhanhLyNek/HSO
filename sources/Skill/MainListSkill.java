package Skill;

import GameEffect.EffectSkill;
import GameObjects.MainObject;
import GameObjects.Player;
import InterfaceComponents.TabSkillsNew;
import Model.CRes;

/* loaded from: KnightOnline_v403.jar:Skill/MainListSkill.class */
public class MainListSkill {
    public static MainListSkill me;
    int typeKill;
    int maxSize;
    public static final byte KILL_STONE_DROP_MORE = 5;
    public static final byte KILL_CRACK_EARTH = 6;
    public static final byte KILL_ARROW_RAIN = 14;
    public static final byte KILL_KIEM_LV1 = 15;
    public static final byte KILL_SUNG_LV2 = 18;
    public static final byte KILL_SUNG_LV3 = 19;
    public static final byte KILL_PS_LV2 = 20;
    public static final byte KILL_KIEM_LV3 = 21;
    public static final byte KILL_2KIEM_LV2 = 22;
    public static final byte KILL_KIEM_LV2 = 23;
    public static final byte KILL_SUNG_LV4 = 24;
    public static final byte KILL_2KIEM_LV4 = 27;
    public static final byte KILL_PS_LV1 = 31;
    public static final byte KILL_2KIEM_LV5 = 34;
    public static final byte KILL_NULL = 35;
    public static final byte KILL_2KIEM_TRUNGDOC = 36;
    public static final byte KILL_2KIEM_GAIDOC = 37;
    public static final byte KILL_PS_XUNGKICH = 39;
    public static final byte KILL_SUNG_LASER = 40;
    public static final byte KILL_KIEM_FIRE = 41;
    public static final byte KILL_KIEM_LV6 = 42;
    public static final byte KILL_KIEM_LV7 = 43;
    public static final byte KILL_2KIEM_CRI = 44;
    public static final byte KILL_BUFF = 45;
    public static final byte KILL_PS_CAU_NOILUC = 46;
    public static final byte KILL_PS_DONGDAT = 47;
    public static final byte KILL_PS_ICE_RAIN = 48;
    public static final byte KILL_SUNG_BAO_DAN = 49;
    public static final byte KILL_SUNG_SET_NEW = 50;
    public static final byte KILL_PS_ICE_UP = 51;
    public static final byte KILL_SUNG_RAIN_ROCKET = 52;
    public static final byte KILL_SUNG_RAIN_LIGHTNING = 53;
    public static final byte EFF_KIEMLON_NGU = 54;
    public static final byte EFF_KIEMNHO_TROI = 55;
    public static final byte EFF_PHAP_SU_BANG = 56;
    public static final byte EFF_SUNGNGU = 57;
    public static final byte EFF_CHIEN_BINH_BUFFDAME = 58;
    public static final byte EFF_THICH_KHACH_BUFFHP = 59;
    public static final byte EFF_XA_THU_BUFF_BUFFAMOR = 60;
    public static final byte EFF_PHAP_SU_BUFFGOLD = 61;
    public static final byte KILL_PHAP_SU_115 = 62;
    public static final byte KILL_SUNG_115 = 63;
    public static final byte KILL_BIG_SWORD_115 = 64;
    public static final byte KILL_SMALL_SWORD_115 = 65;
    public static final byte KILL_PHAP_SU_115_2 = 66;
    public static final byte KILL_SUNG_115_2 = 67;
    public static final byte KILL_BIG_SWORD_115_2 = 68;
    public static final byte KILL_SMALL_SWORD_115_2 = 69;
    public static int[][] mSkillAllClasses = {new int[]{15, 23, 41, 21, 5, 42, 43, 42, 5, 0, 0, 0, 0, 45, 45, 0, 0, 54, 58, 64, 68}, new int[]{15, 44, 36, 22, 37, 27, 34, 27, 14, 0, 0, 0, 0, 45, 45, 0, 0, 55, 60, 65, 69}, new int[]{31, 46, 20, 39, 51, 6, 48, 47, 48, 0, 0, 0, 0, 45, 45, 0, 0, 56, 61, 62, 66}, new int[]{31, 19, 18, 24, 40, 49, 50, 52, 53, 0, 0, 0, 0, 45, 45, 0, 0, 57, 59, 63, 67}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 70, 70, 70, 70}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45}};
    public static int[][] mBuffAllClasses = {new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 6, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 7, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 8, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, 29, 30, 31}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}};
    static int[] mRange = {200, 60, 200, 50, 200, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, 70, 100, 200, 200, 200, 200, 200, EffectSkill.EFF_KIEM_LON_NGU, 60, 30, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, 75, 60, EffectSkill.EFF_KIEM_LON_NGU, 100, EffectSkill.EFF_BIG_SWORD_115_2, EffectSkill.EFF_KIEM_LON_NGU, 160, 140, EffectSkill.EFF_BIG_SWORD_115_2, 70, 60, 100, EffectSkill.EFF_KIEM_LON_NGU, 50, 60, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, 60, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, 60, EffectSkill.EFF_BIG_SWORD_115_2, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU, EffectSkill.EFF_KIEM_LON_NGU};
    static int[] mPlash = {0, 0, 0, 0, 0, 5, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 16, 16, 1, 5, 1, 1, 11, 2, 5, 4, 10, 7, 0, 0, 0, 12, 7, 14, 1, 5, 0, 5, 6, 1, 7, 7, 1, 9, 1, 7, 2, 13, 11, 5, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static int[] mFramePlash = {5, 5, 5, 5, 5, 11, 15, 5, 5, 5, 5, 5, 5, 5, 15, 5, 5, 5, 9, 9, 9, 11, 9, 9, 11, 5, 5, 9, 5, 5, 5, 5, 5, 5, 15, 5, 9, 11, 5, 11, 11, 9, 15, 15, 9, 5, 9, 15, 15, 11, 11, 11, 20, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    public static int[] mEffectKill;

    public static MainListSkill gI() {
        if (me == null) {
            me = new MainListSkill();
        }
        return me;
    }

    public static void loadIndexEffKill() {
        mEffectKill = new int[mPlash.length];
        mEffectKill[5] = 11;
        mEffectKill[6] = 12;
        mEffectKill[14] = 20;
        mEffectKill[15] = 21;
        mEffectKill[18] = 22;
        mEffectKill[19] = 23;
        mEffectKill[20] = 25;
        mEffectKill[21] = 26;
        mEffectKill[22] = 27;
        mEffectKill[23] = 28;
        mEffectKill[24] = 31;
        mEffectKill[27] = 34;
        mEffectKill[31] = 38;
        mEffectKill[34] = 40;
        mEffectKill[35] = 38;
        mEffectKill[36] = 46;
        mEffectKill[37] = 47;
        mEffectKill[39] = 49;
        mEffectKill[40] = 51;
        mEffectKill[41] = 52;
        mEffectKill[42] = 53;
        mEffectKill[43] = 54;
        mEffectKill[44] = 55;
        mEffectKill[45] = 57;
        mEffectKill[46] = 59;
        mEffectKill[47] = 60;
        mEffectKill[48] = 61;
        mEffectKill[49] = 62;
        mEffectKill[50] = 82;
        mEffectKill[51] = 64;
        mEffectKill[52] = 65;
        mEffectKill[53] = 66;
        mEffectKill[54] = 110;
        mEffectKill[55] = 112;
        mEffectKill[56] = 109;
        mEffectKill[57] = 111;
        mEffectKill[58] = 0;
        mEffectKill[59] = 0;
        mEffectKill[60] = 0;
        mEffectKill[61] = 0;
        mEffectKill[62] = 119;
        mEffectKill[63] = 118;
        mEffectKill[64] = 116;
        mEffectKill[65] = 117;
        mEffectKill[66] = 123;
        mEffectKill[67] = 122;
        mEffectKill[68] = 120;
        mEffectKill[69] = 121;
    }

    public static void setKill(int i) {
        Player.mKillPlayer = new int[TabSkillsNew.vecPaintSkill.size()];
        for (int i2 = 0; i2 < Player.mKillPlayer.length; i2++) {
            Player.mKillPlayer[i2] = mSkillAllClasses[i][i2];
        }
    }

    public int setDirection(MainObject mainObject, MainObject mainObject2) {
        int i;
        int i2 = mainObject.x - mainObject2.x;
        int i3 = mainObject.y - mainObject2.y;
        if (CRes.abs(i2) > CRes.abs(i3)) {
            if (i2 > 0) {
                i = 2;
            } else {
                i = 3;
            }
        } else if (i3 > 0) {
            i = 1;
        } else {
            i = 0;
        }
        return i;
    }

    public static int getRange(int i) {
        Skill skillFormId = getSkillFormId(i);
        if (skillFormId != null) {
            return skillFormId.range;
        }
        return 50;
    }

    public static Skill getSkillFormId(int i) {
        for (int i2 = 0; i2 < TabSkillsNew.vecPaintSkill.size(); i2++) {
            Skill skill = (Skill) TabSkillsNew.vecPaintSkill.elementAt(i2);
            if (skill.Id == i) {
                return skill;
            }
        }
        return null;
    }
}
