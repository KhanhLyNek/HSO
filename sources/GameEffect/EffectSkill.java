package GameEffect;

import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.Monstervantieu;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.FrameImage;
import Model.Line;
import Model.Point;
import Model.Point_Focus;
import Model.T;
import Thread_More.LoadMap;

/* loaded from: KnightOnline_v403.jar:GameEffect/EffectSkill.class */
public class EffectSkill extends MainEffect {
    public static final byte EFF_NORMAL = 0;
    public static final byte EFF_SPEED = 1;
    public static final byte EFF_STAR = 6;
    public static final byte EFF_LIGHTING = 10;
    public static final byte EFF_STONE_DROP_MORE = 11;
    public static final byte EFF_CRACK_EARTH = 12;
    public static final byte EFF_ARROW_RAIN = 20;
    public static final byte EFF_KIEM_AND_2KIEM_LV1 = 21;
    public static final byte EFF_SUNG_LV2 = 22;
    public static final byte EFF_SUNG_LV3 = 23;
    public static final byte EFF_PS_LV2 = 25;
    public static final byte EFF_KIEM_LV3 = 26;
    public static final byte EFF_2KIEM_LV2 = 27;
    public static final byte EFF_KIEM_LV2 = 28;
    public static final byte EFF_STAR_LINE_IN = 29;
    public static final byte EFF_STAR_POINT_IN = 30;
    public static final byte EFF_SUNG_LV4 = 31;
    public static final byte EFF_2KIEM_LV4 = 34;
    public static final byte EFF_PS_AND_SUNG_LV1 = 38;
    public static final byte EFF_2KIEM_LV5 = 40;
    public static final byte EFF_XP = 41;
    public static final byte EFF_LEVEL_UP = 42;
    public static final byte EFF_LINE_LEVEL_UP = 43;
    public static final byte EFF_2KIEM_TRUNG_DOC = 46;
    public static final byte EFF_2KIEM_GAI_DOC = 47;
    public static final byte EFF_PS_XUNGKICH = 49;
    public static final byte EFF_MONSTER_NEAR = 50;
    public static final byte EFF_SUNG_LASER = 51;
    public static final byte EFF_KIEM_FIRE = 52;
    public static final byte EFF_KIEM_LV6 = 53;
    public static final byte EFF_KIEM_LV7 = 54;
    public static final byte EFF_2KIEM_CRI = 55;
    public static final byte EFF_BUFF = 56;
    public static final byte EFF_BUFF_POINT_IN = 57;
    public static final byte EFF_TELEPORT = 58;
    public static final byte EFF_PS_CAU_NOILUC = 59;
    public static final byte EFF_PS_DONGDAT = 60;
    public static final byte EFF_PS_ICE_RAIN = 61;
    public static final byte EFF_SUNG_BAO_DAN = 62;
    public static final byte EFF_SUNG_DAY_DAN = 63;
    public static final byte EFF_PS_ICE_UP = 64;
    public static final byte EFF_SUNG_RAIN_ROCKET = 65;
    public static final byte EFF_SUNG_RAIN_LIGHTNING = 66;
    public static final byte EFF_PHAN_DAMAGE = 67;
    public static final byte EFF_FOCUS = 68;
    public static final byte EFF_SMALL_SMOKE = 69;
    public static final byte EFF_MON_BUFF = 70;
    public static final byte EFF_BOSS_CAY_1 = 71;
    public static final byte EFF_BOSS_CAY_2 = 72;
    public static final byte EFF_BOSS_ONG_1 = 73;
    public static final byte EFF_BOSS_ONG_2 = 74;
    public static final byte EFF_BOSS_CUA_1 = 75;
    public static final byte EFF_BOSS_CUA_2 = 76;
    public static final byte EFF_BOSS_SOI_1 = 77;
    public static final byte EFF_BOSS_SOI_2 = 14;
    public static final byte EFF_BOSS_DA_1 = 78;
    public static final byte EFF_BOSS_DA_2 = 79;
    public static final byte EFF_KILL_GHOST = 80;
    public static final byte EFF_PHO_BANG = 81;
    public static final byte EFF_SUNG_SET_NEW = 82;
    public static final byte EFF_HUT_MP_HP = 83;
    public static final byte EFF_STAR_LINE_IN_SLOW = 84;
    public static final byte EFF_NHANBAN_LV2 = 85;
    public static final byte EFF_SERVER_THIEN_THACH = 86;
    public static final byte EFF_BUFF_MO_BANGHOI = 87;
    public static final byte EFF_DAN_FOCUS = 88;
    public static final byte EFF_HUT_MP = 89;
    public static final byte EFF_ICE_NOVA = 90;
    public static final byte EFF_POISON_NOVA = 91;
    public static final byte EFF_HUT_HP = 92;
    public static final byte EFF_BOSS_DE_1 = 93;
    public static final byte EFF_BOSS_DE_2 = 94;
    public static final byte EFF_Tower_1 = 95;
    public static final byte EFF_Tower_2 = 96;
    public static final byte EFF_Tower_3 = 97;
    public static final byte EFF_Tower_4 = 98;
    public static final byte EFF_BOSS_Medusa1 = 99;
    public static final byte EFF_BOSS_Medusa2 = 100;
    public static final byte EFF_PET_OWL = 101;
    public static final byte EFF_PET_EAGLE = 102;
    public static final byte EFF_BOSS_84_NO = 103;
    public static final byte EFF_BOSS_34_DAM_TUNG = 104;
    public static final byte EFF_BOSS_34_LASER = 105;
    public static final byte EFF_BOSS_34_LASERLAN = 106;
    public static final byte EFF_BOSS_NO = 107;
    public static final byte EFF_BOSS_84_MUNTI = 108;
    public static final byte EFF_PHAPSU_BANG = 109;
    public static final byte EFF_KIEM_LON_NGU = 110;
    public static final byte EFF_SUNG_NGU = 111;
    public static final byte EFF_KIEM_NHO_TROI = 112;
    public static final byte EFF_BOSS_NOEL_1 = 113;
    public static final byte EFF_BOSS_NOEL_2 = 114;
    public static final byte EFF_BOSS_NOEL_3 = 115;
    public static final int EFF_BIG_SWORD_115 = 116;
    public static final int EFF_SMALL_SWORD_115 = 117;
    public static final int EFF_SUNG_115 = 118;
    public static final int EFF_PHAPSU_115 = 119;
    public static final int EFF_BIG_SWORD_115_2 = 120;
    public static final int EFF_SMALL_SWORD_115_2 = 121;
    public static final int EFF_SUNG_115_2 = 122;
    public static final int EFF_PHAPSU_115_2 = 123;
    public static final int EFF_PHAO_THANH = 124;
    public static final int EFF_TRU_THANH_1 = 125;
    public static final int EFF_TRU_THANH_2 = 126;
    public static final byte SUB_STAR_LINE_IN_KIEM = 0;
    public static final byte SUB_STAR_LINE_IN_SUNG = 1;
    public static final byte SUB_STAR_LINE_IN_BUFF_0 = 2;
    public static final byte SUB_STAR_LINE_IN_2KIEM = 3;
    public static final byte SUB_STAR_LINE_IN_PS = 4;
    public static final byte SUB_BUFF_LUA_1 = 0;
    public static final byte SUB_BUFF_DOC_1 = 1;
    public static final byte SUB_BUFF_BANG_1 = 2;
    public static final byte SUB_BUFF_DIEN_1 = 3;
    public static final byte SUB_BUFF_OTHER_MAIN = 10;
    public static final byte SUB_BUFF_OTHER_SUB = 11;
    long time;
    public int subType;
    public int indexSound;
    public boolean ispaintArena;
    int vXTam;
    int vYTam;
    int x1000;
    int y1000;
    int vX1000;
    int vY1000;
    int xEff;
    int yEff;
    int angle;
    int R;
    int size1;
    int[][] mTamgiac;
    int lT_Arc;
    int gocT_Arc;
    int r;
    public static byte countSkillArena;
    int[] colorpaint;
    int colorBuff;
    int indexColorStar;
    int timedelay;
    int ysai;
    byte timeAddNum;
    mVector VecEff;
    mVector VecSubEff;
    byte[] mpaintone_three;
    byte[] mpaintone_Bullet;
    byte[] mImageBullet;
    byte[] mXoayBullet;
    int gocArrow;
    int frameArrow;
    int xline;
    int yline;
    int yFly;
    int ydArchor;
    int yArchor;
    int indexLan;
    MainObject objKill;
    public mVector vecObjBeKill;
    boolean isEff;
    boolean ispaintsleep;
    int indexAdd;
    int dxTower;
    int dyTower;
    int xdichTower;
    int ydichTower;
    int delay;
    public byte[] arr_R;
    public int[] arr_radian;
    public int xto;
    public int yto;
    public int dx;
    public int dy;
    public int nPosition;
    public int xO;
    public int yO;
    public int dem;
    public int radian;
    public int frame;
    public int effect;
    private byte indexpost;
    public int Xsleep;
    public int vxSleep;
    public int Ysleep;
    public int ystun;
    int test;
    public int frSleep;
    public int[] frameSleep;
    public byte frStun;
    public int[] frameStun;
    mVector vectam;
    int life;
    int va;
    public boolean isSpeedUp;
    int savex;
    int savey;
    public static byte[][] frameBuff = {new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2}};
    public static byte[][] arrInfoEff = {new byte[0], new byte[]{5, 5, 4}, new byte[]{9, 9, 4}, new byte[]{20, 20, 4}, new byte[]{32, 32, 5}, new byte[]{7, 7, 4}, new byte[]{8, 8, 4}, new byte[]{25, 25, 2}, new byte[]{22, 30, 4}, new byte[]{14, 15, 4}, new byte[]{8, 8, 4}, new byte[]{24, 19, 3}, new byte[]{19, 13, 3}, new byte[]{24, 24, 3}, new byte[]{32, 32, 6}, new byte[]{36, 30, 3}, new byte[]{23, 32, 8}, new byte[]{20, 20, 4}, new byte[]{32, 32, 2}, new byte[]{41, 17, 3}, new byte[]{32, 14, 3}, new byte[]{48, 48, 3}, new byte[]{21, 34, 3}, new byte[]{12, 12, 5}, new byte[]{38, 38, 3}, new byte[0], new byte[]{36, 30, 3}, new byte[]{32, 31, 3}, new byte[]{12, 12, 4}, new byte[]{40, 31, 3}, new byte[]{20, 18, 3}, new byte[]{12, 13, 4}, new byte[]{14, 14, 4}, new byte[]{30, 37, 3}, new byte[0], new byte[0], new byte[]{37, 40, 4}, new byte[]{50, 24, 4}, new byte[]{9, 12, 3}, new byte[0], new byte[]{23, 33, 2}, new byte[]{32, 32, 3}, new byte[]{24, 24, 5}, new byte[0], new byte[0], new byte[]{14, 11, 3}, new byte[0], new byte[]{15, 15, 3}, new byte[]{14, 11, 3}, new byte[0], new byte[]{24, 35, 3}, new byte[]{70, 70, 3}, new byte[]{50, 46, 4}, new byte[]{62, 64, 3}, new byte[]{38, 38, 4}, new byte[0], new byte[]{10, 10, 2}, new byte[]{25, 25, 3}, new byte[]{32, 32, 4}, new byte[]{32, 32, 4}, new byte[]{30, 32, 4}, new byte[]{50, 50, 4}, new byte[0], new byte[]{46, 50, 4}, new byte[0], new byte[]{42, 27, 6}, new byte[]{5, 5, 4}, new byte[]{59, 17, 1}, new byte[]{17, 23, 4}, new byte[0], new byte[]{50, 50, 4}, new byte[]{31, 31, 2}, new byte[]{34, 38, 3}, new byte[0], new byte[]{21, 17, 9}, new byte[]{32, 22, 3}, new byte[0], new byte[0], new byte[0], new byte[0], new byte[]{10, 10, 18}, new byte[]{17, 10, 3}, new byte[0], new byte[]{27, 32, 3}, new byte[0], new byte[]{19, 45, 1}, new byte[]{50, 26, 3}, new byte[]{24, 30, 5}, new byte[]{32, 14, 3}, new byte[]{32, 14, 3}, new byte[]{24, 14, 3}, new byte[]{8, 5, 2}, new byte[]{16, 16, 3}, new byte[0], new byte[]{30, 14, 2}, new byte[]{25, 45, 1}, new byte[]{53, 28, 3}, new byte[]{12, 12, 3}, new byte[]{14, 14, 4}, new byte[]{14, 14, 4}, new byte[]{14, 14, 4}, new byte[]{14, 14, 4}, new byte[]{14, 14, 4}, new byte[]{12, 12, 5}, new byte[]{12, 12, 5}, new byte[]{12, 11, 4}, new byte[]{50, 25, 4}, new byte[]{50, 25, 4}, new byte[]{50, 25, 4}, new byte[]{50, 25, 4}, new byte[]{50, 25, 4}, new byte[]{32, 30, 6}, new byte[]{28, 15, 4}, new byte[]{88, 60, 3}, new byte[]{31, 31, 2}, new byte[]{16, 16, 3}, new byte[]{7, 7, 4}, new byte[]{19, 45, 1}, new byte[]{55, 55, 3}, new byte[]{55, 55, 3}, new byte[]{55, 55, 3}, new byte[]{55, 55, 3}, new byte[]{24, 40, 3}, new byte[]{13, 13, 3}, new byte[]{59, 64, 4}, new byte[]{25, 25, 3}, new byte[]{13, 13, 3}, new byte[]{36, 30, 4}, new byte[]{53, 24, 4}, new byte[]{44, 44, 4}, new byte[]{50, 51, 4}, new byte[]{40, 40, 4}, new byte[]{31, 23, 3}, new byte[]{26, 24, 3}, new byte[]{32, 32, 4}, new byte[]{32, 24, 3}, new byte[]{29, 20, 3}, new byte[]{32, 32, 6}, new byte[]{22, 19, 8}, new byte[]{23, 25, 3}, new byte[]{16, 21, 3}, new byte[]{8, 8, 4}, new byte[]{20, 15, 2}, new byte[]{30, 20, 3}, new byte[]{64, 57, 4}, new byte[]{20, 20, 3}, new byte[]{24, 16, 3}, new byte[]{30, 20, 3}, new byte[]{22, 16, 3}, new byte[]{25, 17, 3}, new byte[]{30, 30, 4}, new byte[]{30, 30, 4}, new byte[]{16, 16, 2}, new byte[]{7, 7, 4}, new byte[]{9, 9, 4}, new byte[]{13, 13, 5}, new byte[]{7, 7, 4}, new byte[]{9, 9, 4}, new byte[]{13, 13, 5}, new byte[]{7, 7, 4}, new byte[]{9, 9, 4}, new byte[]{13, 13, 5}, new byte[]{16, 16, 4}, new byte[]{13, 22, 1}};
    public static int[][] colorStar = {new int[]{-466912, -479160, -1}, new int[]{-9732096, -4133256, -1}};
    static int[][] colorBuffMain = {new int[]{-4898304, -479160, -1, -1}, new int[]{-532951, -479160, -1, -1}, new int[]{-7294728, -479160, -1, -1}, new int[]{-5207896, -479160, -1, -1}, new int[]{-131200, -3420104, -1, -1}};
    public static int[][] posx = {new int[]{4, 11, 11, 15, 10, 7, 5, 6, 4, 3, 11, 13, 13, 13, 12, 15}, new int[]{14, 14, 16, 15, 18, 20, 22, 22, 23, 21, 9, 11, 9, 10, 9, 9}};
    public static int[][] posy = {new int[]{11, 9, 11, 11, 13, 14, 13, 14, 11, 13, 15, 14, 13, 13, 12, 15}, new int[]{9, 12, 12, 14, 14, 15, 13, 11, 13, 14, 9, 11, 9, 10, 9, 9}};

    public EffectSkill(int i, int i2, int i3, int i4, byte b) {
        this.ispaintArena = true;
        this.VecEff = new mVector("EffectSkill VecEff");
        this.VecSubEff = new mVector("EffectSkill VecSubEff");
        this.mpaintone_three = new byte[]{4, 3, 2, 1, 0, 7, 6, 5};
        this.mpaintone_Bullet = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
        this.mImageBullet = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
        this.mXoayBullet = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
        this.gocArrow = 0;
        this.frameArrow = 0;
        this.isEff = false;
        this.indexAdd = 0;
        this.arr_R = new byte[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
        this.arr_radian = new int[]{0, 30, 60, 90, EFF_BIG_SWORD_115_2, 150, 180, 210, 240, 270, 300, 330};
        this.xO = 0;
        this.yO = 0;
        this.test = 0;
        this.frSleep = 0;
        this.frameSleep = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        this.frStun = (byte) 0;
        this.frameStun = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        this.life = 0;
        this.isSpeedUp = false;
        GameScreen.addEffectEndKill(14, i, i2);
        GameScreen.addEffectEndKill(14, i + 24, i2);
        GameScreen.addEffectEndKill(14, i - 24, i2);
        GameScreen.addEffectEndKill(57, i, i2);
        this.fraImgEff = new FrameImage(162);
        this.indexpost = b;
        this.x = i;
        this.y = i2;
        this.xto = i3;
        this.yto = i4;
        if (this.xto == i) {
            this.vx = 0;
        } else if (this.xto > i) {
            this.vx = 1;
        } else {
            this.vx = -1;
        }
        this.vy = -5;
        this.typeEffect = 124;
    }

    public EffectSkill(int i, Object_Effect_Skill object_Effect_Skill, mVector mvector, int i2) {
        this.ispaintArena = true;
        this.VecEff = new mVector("EffectSkill VecEff");
        this.VecSubEff = new mVector("EffectSkill VecSubEff");
        this.mpaintone_three = new byte[]{4, 3, 2, 1, 0, 7, 6, 5};
        this.mpaintone_Bullet = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
        this.mImageBullet = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
        this.mXoayBullet = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
        this.gocArrow = 0;
        this.frameArrow = 0;
        this.isEff = false;
        this.indexAdd = 0;
        this.arr_R = new byte[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
        this.arr_radian = new int[]{0, 30, 60, 90, EFF_BIG_SWORD_115_2, 150, 180, 210, 240, 270, 300, 330};
        this.xO = 0;
        this.yO = 0;
        this.test = 0;
        this.frSleep = 0;
        this.frameSleep = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        this.frStun = (byte) 0;
        this.frameStun = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        this.life = 0;
        this.isSpeedUp = false;
        if (!LoadMapScreen.isNextMap) {
            return;
        }
        this.subType = i2;
        this.isStop = false;
        this.isRemove = false;
        set_New_Effect(i, object_Effect_Skill, mvector);
    }

    public EffectSkill(int i, Object_Effect_Skill object_Effect_Skill, mVector mvector, int i2, boolean z) {
        this.ispaintArena = true;
        this.VecEff = new mVector("EffectSkill VecEff");
        this.VecSubEff = new mVector("EffectSkill VecSubEff");
        this.mpaintone_three = new byte[]{4, 3, 2, 1, 0, 7, 6, 5};
        this.mpaintone_Bullet = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
        this.mImageBullet = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
        this.mXoayBullet = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
        this.gocArrow = 0;
        this.frameArrow = 0;
        this.isEff = false;
        this.indexAdd = 0;
        this.arr_R = new byte[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
        this.arr_radian = new int[]{0, 30, 60, 90, EFF_BIG_SWORD_115_2, 150, 180, 210, 240, 270, 300, 330};
        this.xO = 0;
        this.yO = 0;
        this.test = 0;
        this.frSleep = 0;
        this.frameSleep = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        this.frStun = (byte) 0;
        this.frameStun = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        this.life = 0;
        this.isSpeedUp = false;
        if (!LoadMapScreen.isNextMap) {
            return;
        }
        this.subType = i2;
        this.ispaintArena = z;
        this.isStop = false;
        this.isRemove = false;
        set_New_Effect(i, object_Effect_Skill, mvector);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v392, types: [int] */
    /* JADX WARN: Type inference failed for: r17v15, types: [int] */
    private void set_New_Effect(int i, Object_Effect_Skill object_Effect_Skill, mVector mvector) {
        MainObject mainObject;
        MainObject mainObject2;
        int i2;
        int i3;
        MainObject mainObject3;
        MainObject mainObject4;
        MainObject mainObject5;
        MainObject mainObject6;
        MainObject mainObject7;
        if (mvector == null || mvector.size() == 0) {
            return;
        }
        this.vecObjBeKill = mvector;
        this.indexSound = -1;
        this.f = -1;
        this.isPaint = true;
        this.ysai = 0;
        this.typeEffect = i;
        this.time = GameCanvas.timeNow;
        this.objKill = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
        Object_Effect_Skill object_Effect_Skill2 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(0);
        this.objBeKillMain = MainObject.get_Object(object_Effect_Skill2.ID, object_Effect_Skill2.tem);
        this.isEff = false;
        if (this.objKill == null || this.objBeKillMain == null) {
            return;
        }
        if (this.typeEffect == 92 || this.typeEffect == 89) {
            this.x = this.objBeKillMain.x;
            this.y = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
            this.toX = this.objKill.x;
            this.toY = this.objKill.y - (this.objKill.hOne / 2);
        } else {
            this.x = this.objKill.x;
            this.y = this.objKill.y - (this.objKill.hOne / 2);
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
        }
        if (this.typeEffect != 10 && this.typeEffect != 66 && this.typeEffect != 30 && this.typeEffect != 29 && this.typeEffect != 107) {
            this.Direction = setDirection(this.objKill, this.objBeKillMain);
            this.objKill.Direction = this.Direction;
        } else {
            this.Direction = this.objKill.Direction;
        }
        this.timeAddNum = (byte) -1;
        if (this.typeEffect != 41 && this.objKill == GameScreen.player) {
            this.isEff = true;
        }
        if (!MainObject.isInScreen(this.objBeKillMain) && !MainObject.isInScreen(this.objKill)) {
            this.isStop = true;
            return;
        }
        switch (i) {
            case 0:
                this.fRemove = 60;
                switch (this.subType) {
                    case 0:
                        this.fraImgEff = new FrameImage(101);
                        break;
                    case 1:
                        this.fraImgEff = new FrameImage(98);
                        break;
                    case 2:
                        this.fraImgEff = new FrameImage(100);
                        break;
                    case 3:
                        this.fraImgEff = new FrameImage(99);
                        break;
                    case 4:
                        this.fraImgEff = new FrameImage(32);
                        break;
                    case 5:
                        this.fraImgEff = new FrameImage(EFF_PET_EAGLE);
                        break;
                    default:
                        this.fraImgEff = new FrameImage(32);
                        break;
                }
                this.vMax = 8000;
                createNormal();
                break;
            case 1:
            case 38:
            case 59:
                this.vMax = 14;
                if (i == 38) {
                    if (this.objKill.clazz == 2) {
                        this.indexSound = 2;
                    } else {
                        this.indexSound = 1;
                    }
                }
                if (i == 1) {
                    this.fraImgEff = new FrameImage(32);
                }
                if (i == 59) {
                    this.indexSound = 13;
                    this.y += 10;
                    this.fraImgEff = new FrameImage(17);
                    this.fraImgSubEff = new FrameImage(24);
                }
                create_Speed(this.toX - this.x, this.toY - this.y, null);
                if (i == 59) {
                    this.timeAddNum = (byte) this.fRemove;
                    break;
                }
                break;
            case 6:
                this.isEff = true;
                create_Star();
                break;
            case 10:
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y;
                }
                this.fRemove = 10;
                this.timeAddNum = (byte) 7;
                createLighting(this.toX + CRes.random_Am_0(20), MainScreen.cameraMain.yCam - 5, this.toX, this.toY, true);
                if (this.objKill == GameScreen.player) {
                    mSound.playSound(32, mSound.volumeSound);
                } else {
                    GameScreen.addSoundEff(32);
                }
                GameScreen.addEffectEndKill(40, this.toX, this.toY + 10);
                break;
            case 11:
            case 108:
            case 114:
                this.indexSound = 5;
                if (this.typeEffect == 114) {
                    this.fraImgEff = new FrameImage(24);
                    this.fraImgSubEff = new FrameImage(7);
                    this.fraImgSub2Eff = new FrameImage(9);
                } else {
                    this.fraImgEff = new FrameImage(30);
                    this.fraImgSubEff = new FrameImage(31);
                    this.fraImgSub2Eff = new FrameImage(40);
                }
                this.vMax = 14;
                this.vx = this.vMax;
                for (int i4 = 0; i4 < this.vecObjBeKill.size(); i4++) {
                    Object_Effect_Skill object_Effect_Skill3 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i4);
                    if (object_Effect_Skill3 != null && (mainObject7 = MainObject.get_Object(object_Effect_Skill3.ID, object_Effect_Skill3.tem)) != null) {
                        this.VecEff.addElement(create_Stone_Drop(mainObject7.x, mainObject7.y));
                    }
                }
                break;
            case 12:
                mSound.playSound(32, mSound.volumeSound);
                this.indexSound = 15;
                create_Crack_Earth();
                break;
            case 14:
            case 115:
                mSound.playSound(37, mSound.volumeSound);
                this.timeAddNum = (byte) 18;
                if (this.typeEffect == 14) {
                    create_Fire_Arc();
                    break;
                } else {
                    create_Ice_Arc();
                    break;
                }
            case 20:
            case 113:
                this.indexSound = 11;
                this.fraImgEff = new FrameImage(91);
                setBeginKill(5);
                if (this.objBeKillMain != null) {
                    this.x1000 = this.objBeKillMain.x - 70;
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y;
                } else {
                    this.x1000 = this.toX - 70;
                }
                this.y1000 = MainScreen.cameraMain.yCam - CRes.random(10, 20);
                this.vMax = 18;
                this.fRemove = 20;
                this.timeAddNum = (byte) 11;
                create_Arrow_Rain();
                break;
            case 21:
                if (this.objKill.clazz == 0) {
                    this.indexSound = 3;
                } else {
                    this.indexSound = 0;
                }
                if (this.objBeKillMain != null) {
                    this.x = this.objBeKillMain.x;
                    this.y = this.objBeKillMain.y - (this.objKill.hOne / 2);
                } else {
                    this.x = this.toX;
                    this.y = this.toY;
                }
                this.frame = CRes.random(4);
                do {
                    this.frameArrow = CRes.random(4);
                } while (this.frame == this.frameArrow);
                this.fraImgEff = new FrameImage(EFF_SUNG_115 + this.frame);
                this.fraImgSubEff = new FrameImage(EFF_SUNG_115 + this.frameArrow);
                this.fRemove = 6;
                break;
            case 22:
            case 31:
                create_Sung_LV2_LV4();
                break;
            case 23:
                this.indexSound = 12;
                create_Sung_Lv3();
                break;
            case 25:
                this.indexSound = 13;
                create_PS_LV2_3_5();
                break;
            case 26:
                this.indexSound = 5;
                create_Kiem_Lv3();
                break;
            case 27:
                create_Kill_2Kiem_Lv2();
                break;
            case 28:
                this.indexSound = 4;
                create_Kiem_Lv2();
                break;
            case 29:
                this.isEff = true;
                if (this.objKill != null) {
                    this.indexColorStar = this.objKill.clazz == 1 ? 1 : 0;
                } else {
                    this.indexColorStar = 0;
                }
                setPosLineIn(this.subType);
                this.x1000 = this.x * ObjectData.MONSTER;
                if (this.objKill != null) {
                    this.y1000 = (this.y - (this.objKill.hOne / 2)) * ObjectData.MONSTER;
                } else {
                    this.y1000 = this.toY * ObjectData.MONSTER;
                }
                this.fRemove = 2;
                this.vMax = 5;
                this.xline = 10;
                this.yline = 20;
                create_Star_Line_In(this.vMax, this.xline, this.yline);
                break;
            case 30:
                this.isEff = true;
                setPosLineIn(this.subType);
                this.x1000 = this.x * ObjectData.MONSTER;
                this.y1000 = (this.y - (this.objKill.hOne / 2)) * ObjectData.MONSTER;
                if (this.objKill.clazz == 3) {
                    this.fraImgEff = new FrameImage(6);
                } else {
                    this.fraImgEff = new FrameImage(10);
                }
                this.fRemove = 5;
                create_Star_Point_In();
                break;
            case 34:
                this.fraImgEff = new FrameImage(33);
                this.fraImgSubEff = new FrameImage(18);
                this.fRemove = 57;
                if (this.objKill == null || this.objBeKillMain == null) {
                    this.isRemove = true;
                    return;
                }
                this.objKill.isTanHinh = true;
                this.objKill.timeTanHinh = 0;
                if (this.objKill.isMainChar()) {
                    GameScreen.player.lastX = GameScreen.player.x;
                    GameScreen.player.lastY = GameScreen.player.y;
                }
                create_2Kiem_Lv4(0, this.objBeKillMain);
                this.indexLan = 1;
                break;
                break;
            case 40:
                this.indexSound = 10;
                create_2Kiem_Lv5();
                break;
            case 41:
                this.fRemove = 60;
                if (this.subType == 0) {
                    this.fraImgEff = new FrameImage(103);
                } else {
                    this.fraImgEff = new FrameImage(104);
                }
                this.vMax = 12000;
                createXP();
                break;
            case 42:
                this.isEff = true;
                this.indexSound = 26;
                create_Level_up();
                break;
            case 43:
                this.isEff = true;
                this.fraImgEff = new FrameImage(50);
                this.fRemove = 11;
                this.y += this.objKill.hOne / 2;
                for (int i5 = 0; i5 < 10; i5++) {
                    Point point = new Point();
                    int random = CRes.random(0, 180);
                    point.x = (17 * CRes.cos(random)) / ObjectData.MONSTER;
                    point.y = ((15 * CRes.sin(random)) / ObjectData.MONSTER) - 5;
                    point.fRe = CRes.random(2);
                    point.frame = CRes.random(4);
                    point.limitY = 60;
                    this.VecEff.addElement(point);
                }
                break;
            case 46:
                this.indexSound = 7;
                this.fraImgEff = new FrameImage(70);
                this.fraImgSubEff = new FrameImage(72);
                this.fRemove = 15;
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y;
                    break;
                }
                break;
            case 47:
            case EFF_KIEM_NHO_TROI /* 112 */:
                this.indexSound = 8;
                create_2Kiem_GaiDoc();
                break;
            case 49:
                this.indexSound = 14;
                this.fraImgEff = new FrameImage(23);
                this.fraImgSubEff = new FrameImage(24);
                create_PS_Xungkich();
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
                    break;
                }
                break;
            case 50:
                create_Monster();
                if (this.objBeKillMain != null && this.objBeKillMain.typeObject == 9) {
                    this.indexSound = 25;
                    break;
                }
                break;
            case 51:
                this.timeAddNum = (byte) 3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.y += 7;
                setBeginKill(10);
                break;
            case 52:
            case 54:
                if (i == 52) {
                    this.indexSound = 4;
                } else if (i == 54) {
                    this.indexSound = 5;
                }
                this.fraImgEff = new FrameImage(83);
                this.fraImgSubEff = new FrameImage(68);
                this.fRemove = 15;
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y;
                    break;
                }
                break;
            case 53:
            case 78:
            case 104:
                if (i == 53) {
                    this.indexSound = 6;
                    this.fraImgEff = new FrameImage(85);
                } else if (i == 78 || i == 104) {
                    mSound.playSound(32, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(EFF_SMALL_SWORD_115);
                    if (i == 104) {
                        this.objBeKillMain.jum();
                    }
                }
                this.fraImgSubEff = new FrameImage(53);
                this.fRemove = 20;
                for (int i6 = 0; i6 < this.vecObjBeKill.size(); i6++) {
                    Point point2 = new Point();
                    Object_Effect_Skill object_Effect_Skill4 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i6);
                    if (object_Effect_Skill4 != null && (mainObject6 = MainObject.get_Item_Object(object_Effect_Skill4.ID, object_Effect_Skill4.tem)) != null) {
                        point2.x = mainObject6.x;
                        point2.y = mainObject6.y + mainObject6.ysai;
                        this.VecEff.addElement(point2);
                    }
                }
                break;
            case 55:
                this.indexSound = 7;
                this.fraImgEff = new FrameImage(51);
                this.fRemove = 12;
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
                }
                this.timeAddNum = (byte) 5;
                break;
            case 56:
                if (this.subType == 10) {
                    this.fraImgEff = new FrameImage(29);
                } else {
                    this.fraImgEff = new FrameImage(22);
                }
                this.y = this.objKill.y;
                this.fRemove = 6;
                this.isEff = true;
                break;
            case 57:
                if (this.objKill.clazz == 2 || this.objKill.clazz == 3) {
                    this.indexSound = 30;
                } else {
                    this.indexSound = 31;
                }
                this.isEff = true;
                this.x1000 = this.x * ObjectData.MONSTER;
                this.y1000 = this.y * ObjectData.MONSTER;
                create_Buff_Point_In();
                break;
            case 58:
                mSound.playSound(45, mSound.volumeSound);
                this.isEff = true;
                this.y = this.objKill.y + this.objKill.ysai;
                this.fraImgEff = new FrameImage(65);
                this.fraImgSubEff = new FrameImage(94);
                this.fRemove = 8;
                break;
            case 60:
                this.indexSound = 16;
                LoadMap.timeVibrateScreen = 16;
                for (int i7 = 0; i7 < this.vecObjBeKill.size(); i7++) {
                    Object_Effect_Skill object_Effect_Skill5 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i7);
                    if (object_Effect_Skill5 != null && (mainObject5 = MainObject.get_Object(object_Effect_Skill5.ID, object_Effect_Skill5.tem)) != null) {
                        Point point3 = new Point();
                        point3.x = mainObject5.x;
                        point3.y = mainObject5.y + mainObject5.ysai;
                        this.VecEff.addElement(point3);
                    }
                }
                this.fRemove = 20;
                break;
            case 61:
            case 77:
                if (i == 61) {
                    if (this.vecObjBeKill.size() > 1) {
                        this.indexSound = 16;
                    } else {
                        this.indexSound = 15;
                    }
                    this.fraImgEff = new FrameImage(71);
                    this.fraImgSubEff = new FrameImage(5);
                    this.fraImgSub2Eff = new FrameImage(92);
                } else if (i == 77) {
                    this.fraImgEff = new FrameImage(114);
                    this.fraImgSubEff = new FrameImage(EFF_BIG_SWORD_115);
                    this.fraImgSub2Eff = new FrameImage(115);
                }
                this.vMax = 22;
                this.vx = this.vMax;
                for (int i8 = 0; i8 < this.vecObjBeKill.size(); i8++) {
                    Object_Effect_Skill object_Effect_Skill6 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i8);
                    if (object_Effect_Skill6 != null && (mainObject4 = MainObject.get_Object(object_Effect_Skill6.ID, object_Effect_Skill6.tem)) != null) {
                        Point create_ICE_Drop = create_ICE_Drop(mainObject4.x, mainObject4.y);
                        create_ICE_Drop.dis = CRes.random(2);
                        create_ICE_Drop.vecEffPoint = new mVector("EffectSkill vecEffPoint");
                        int random2 = CRes.random(3, 7);
                        for (int i9 = 0; i9 < random2; i9++) {
                            Point point4 = new Point();
                            point4.x = create_ICE_Drop.x + CRes.random_Am_0(20);
                            point4.y = (create_ICE_Drop.y - 10) - CRes.random(35);
                            point4.dis = 0;
                            if (CRes.random(6) == 0) {
                                point4.dis = 1;
                            } else {
                                point4.frame = CRes.random(4);
                            }
                            create_ICE_Drop.vecEffPoint.addElement(point4);
                        }
                        this.VecEff.addElement(create_ICE_Drop);
                    }
                }
                break;
            case 62:
                this.indexSound = 20;
                this.y += 5;
                this.fRemove = 20;
                this.timeAddNum = (byte) 10;
                this.fraImgEff = new FrameImage(47);
                this.vMax = 22;
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
                    break;
                }
                break;
            case 63:
                this.y += 8;
                this.fraImgEff = new FrameImage(74);
                if (this.objBeKillMain != null) {
                    this.toX = this.objBeKillMain.x;
                    this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
                }
                this.fRemove = 20;
                this.timeAddNum = (byte) 10;
                this.vMax = 16;
                break;
            case 64:
                this.indexSound = 14;
                this.f = -1;
                this.fraImgEff = new FrameImage(95);
                this.fraImgSubEff = new FrameImage(53);
                this.fRemove = 20;
                for (int i10 = 0; i10 < this.vecObjBeKill.size(); i10++) {
                    Point point5 = new Point();
                    Object_Effect_Skill object_Effect_Skill7 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i10);
                    if (object_Effect_Skill7 != null && (mainObject3 = MainObject.get_Item_Object(object_Effect_Skill7.ID, object_Effect_Skill7.tem)) != null) {
                        point5.x = mainObject3.x;
                        point5.y = mainObject3.y + mainObject3.ysai;
                        this.VecEff.addElement(point5);
                    }
                }
                break;
            case 65:
                this.indexSound = 23;
                this.fRemove = 2;
                this.isEff = true;
                break;
            case 66:
                this.indexSound = 22;
                this.objKill.Direction = 1;
                this.fRemove = 2;
                this.isEff = true;
                break;
            case 67:
                this.isEff = false;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(6);
                this.x1000 = this.x;
                this.y1000 = this.y;
                this.vMax = 18;
                if (this.objBeKillMain != null) {
                    i2 = this.objBeKillMain.x - this.x;
                    i3 = (this.objBeKillMain.y - (this.objBeKillMain.hOne / 2)) - this.y;
                } else {
                    i2 = this.toX - this.x;
                    i3 = this.toY - this.y;
                }
                create_Speed(i2, i3, null);
                break;
            case 68:
                this.x = this.objBeKillMain.x;
                if (this.subType == 0) {
                    this.y = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
                } else {
                    this.y = (this.objBeKillMain.y - this.objBeKillMain.hOne) - 8;
                }
                this.timeAddNum = (byte) 20;
                this.isEff = true;
                if (this.subType == 0) {
                    this.fraImgEff = new FrameImage(EFF_SUNG_NGU);
                    this.fRemove = 6;
                    break;
                } else if (this.subType == 1) {
                    this.fraImgEff = new FrameImage(EFF_KIEM_NHO_TROI);
                    this.fRemove = 8;
                    break;
                }
                break;
            case 69:
                this.fRemove = 16;
                this.isEff = true;
                this.fraImgEff = new FrameImage(9);
                this.vx = CRes.random_Am_0(2);
                this.vy = -2;
                this.y -= CRes.random(this.objKill.hOne / 2);
                break;
            case 70:
                this.isEff = true;
                this.x1000 = this.x * ObjectData.MONSTER;
                this.y1000 = this.y * ObjectData.MONSTER;
                if (this.subType >= 28) {
                    this.subType -= 28;
                }
                create_Mon_Buff();
                break;
            case 71:
            case 75:
                if (i == 71) {
                    mSound.playSound(32, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(36);
                    this.fraImgSubEff = new FrameImage(53);
                } else {
                    mSound.playSound(35, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(61);
                }
                this.fRemove = 16;
                for (int i11 = 0; i11 < this.vecObjBeKill.size(); i11++) {
                    Point point6 = new Point();
                    Object_Effect_Skill object_Effect_Skill8 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i11);
                    if (object_Effect_Skill8 != null && (mainObject2 = MainObject.get_Item_Object(object_Effect_Skill8.ID, object_Effect_Skill8.tem)) != null) {
                        point6.x = mainObject2.x;
                        point6.y = mainObject2.y + mainObject2.ysai;
                        this.VecEff.addElement(point6);
                    }
                }
                break;
            case 72:
            case 74:
                this.vMax = 14;
                if (i == 72) {
                    mSound.playSound(34, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(45);
                    this.fraImgSubEff = new FrameImage(37);
                } else if (i == 74) {
                    mSound.playSound(34, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(63);
                }
                for (int i12 = 0; i12 < this.vecObjBeKill.size(); i12++) {
                    Object_Effect_Skill object_Effect_Skill9 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i12);
                    if (object_Effect_Skill9 != null && (mainObject = MainObject.get_Item_Object(object_Effect_Skill9.ID, object_Effect_Skill9.tem)) != null) {
                        int i13 = mainObject.x - this.x;
                        int i14 = (mainObject.y - (mainObject.hOne / 2)) - this.y;
                        Point_Focus create_Speed_More = create_Speed_More(i13, i14, new Point_Focus(), mainObject);
                        create_Speed_More.x = this.x;
                        create_Speed_More.y = this.y;
                        create_Speed_More.frame = setFrameAngle(CRes.angle(i13, i14));
                        this.VecEff.addElement(create_Speed_More);
                    }
                }
                this.fRemove = 10;
                break;
            case 73:
                this.y += 8;
                if (i == 73) {
                    mSound.playSound(34, mSound.volumeSound);
                    this.fraImgEff = new FrameImage(48);
                    this.fraImgSubEff = new FrameImage(38);
                }
                this.vMax = 20;
                this.fRemove = 40;
                break;
            case 76:
                mSound.playSound(36, mSound.volumeSound);
                this.levelPaint = -1;
                this.fraImgSubEff = new FrameImage(113);
                this.fraImgEff = new FrameImage(81);
                this.fRemove = 20;
                this.timeAddNum = (byte) 13;
                this.vMax = 5;
                for (int i15 = 0; i15 < 16; i15++) {
                    Point point7 = new Point();
                    point7.x = this.x * ObjectData.MONSTER;
                    point7.y = this.y * ObjectData.MONSTER;
                    point7.vx = 2 * CRes.cos((225 * i15) / 10) * this.vMax;
                    point7.vy = CRes.sin((225 * i15) / 10) * this.vMax;
                    point7.f = 0;
                    this.VecEff.addElement(point7);
                }
                break;
            case 79:
                this.fRemove = 1;
                this.isEff = true;
                break;
            case EFF_SUNG_SET_NEW /* 82 */:
            case EFF_SUNG_NGU /* 111 */:
                this.indexSound = 21;
                int angle = CRes.angle(this.toX - this.x, this.toY - this.y);
                int abs = CRes.abs(MainObject.getDistance(this.x, this.y, this.toX, this.toY)) + 30;
                this.x1000 = this.x + ((CRes.cos(angle) * abs) / ObjectData.MONSTER);
                this.y1000 = this.y + ((CRes.sin(angle) * abs) / ObjectData.MONSTER);
                this.fRemove = 10;
                this.timeAddNum = (byte) 7;
                setBeginKill(0);
                createLighting(this.x, this.y - (this.hOne / 2), this.x1000, this.y1000, true);
                if (this.objKill == GameScreen.player) {
                    mSound.playSound(32, mSound.volumeSound);
                }
                GameScreen.addEffectEndKill(40, this.x1000, this.y1000 + 10);
                break;
            case EFF_DAN_FOCUS /* 88 */:
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(97);
                this.fraImgSubEff = new FrameImage(9);
                this.vMax = 8000;
                createDanFocus();
                this.frame = setFrameAngle(this.gocT_Arc);
                break;
            case 89:
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(10);
                this.fraImgSubEff = new FrameImage(10);
                this.vMax = 8000;
                createDanFocus();
                this.frame = setFrameAngle(this.gocT_Arc);
                break;
            case 90:
                this.timeAddNum = (byte) 18;
                create_Nova();
                if (this.objBeKillMain != null && this.objBeKillMain.typeObject == 9) {
                    this.indexSound = 36;
                    break;
                }
                break;
            case 91:
                this.timeAddNum = (byte) 18;
                create_Poison_Nova();
                if (this.objBeKillMain != null && this.objBeKillMain.typeObject == 9) {
                    this.indexSound = 36;
                    break;
                }
                break;
            case 92:
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(141);
                this.fraImgSubEff = new FrameImage(141);
                this.vMax = 8000;
                createDanFocus();
                this.frame = setFrameAngle(this.gocT_Arc);
                break;
            case 93:
                mSound.playSound(34, mSound.volumeSound);
                this.fraImgEff = new FrameImage(63);
                this.fraImgSubEff = new FrameImage(EFF_BOSS_NO);
                this.fRemove = 60;
                for (int i16 = 0; i16 < this.vecObjBeKill.size(); i16++) {
                    Point point8 = new Point();
                    Object_Effect_Skill object_Effect_Skill10 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i16);
                    if (object_Effect_Skill10 != null && MainObject.get_Item_Object(object_Effect_Skill10.ID, object_Effect_Skill10.tem) != null) {
                        point8.x = this.x;
                        point8.y = this.y + this.ysai;
                        this.VecEff.addElement(point8);
                    }
                }
                break;
            case 95:
                this.indexSound = 21;
                int angle2 = CRes.angle(this.toX - this.x, this.toY - this.y);
                int abs2 = CRes.abs(MainObject.getDistance(this.x, this.y, this.toX, this.toY));
                this.x1000 = this.x + ((CRes.cos(angle2) * abs2) / ObjectData.MONSTER);
                this.y1000 = this.y + ((CRes.sin(angle2) * abs2) / ObjectData.MONSTER);
                this.fRemove = 10;
                this.timeAddNum = (byte) 7;
                setBeginKill(0);
                this.dxTower = 0;
                this.dyTower = 50;
                createLighting(this.x - this.dxTower, (this.y - (this.hOne / 2)) - this.dyTower, this.x1000, this.y1000, true);
                if (this.objKill == GameScreen.player) {
                    mSound.playSound(32, mSound.volumeSound);
                }
                GameScreen.addEffectEndKill(40, this.x1000, this.y1000 + 20);
                break;
            case 96:
                this.fRemove = 60;
                this.fraImgEff = new FrameImage(Monstervantieu.ID_TEMPLATE_BO_DEN);
                this.fraImgSubEff = new FrameImage(9);
                this.vMax = 8000;
                createDanFocus();
                this.frame = setFrameAngle(this.gocT_Arc);
                mSound.playSound(57, mSound.volumeSound);
                this.dxTower = 4;
                this.dyTower = -50;
                this.x += this.dxTower;
                this.y += this.dyTower;
                break;
            case 97:
                this.timeAddNum = (byte) 3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.dxTower = 0;
                this.dyTower = -86;
                this.x += this.dxTower;
                this.y += this.dyTower;
                break;
            case 98:
                create_FireBall_Tower();
                break;
            case 99:
                if (this.objKill.Direction == 3) {
                    this.dxTower = -10;
                    this.dyTower = -20;
                } else {
                    this.dxTower = 10;
                    this.dyTower = -20;
                }
                create_Sung_Medusa();
                GameScreen.addEffectEndKill(48, this.objKill.x + this.dxTower, this.objKill.y + this.dyTower);
                break;
            case 105:
                this.timeAddNum = (byte) 3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(60);
                this.fRemove = 16;
                this.y -= 12;
                setBeginKill(10);
                break;
            case 106:
                this.timeAddNum = (byte) 3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.R = 240;
                this.y -= 15;
                this.x -= 6;
                this.angle = 0;
                this.size1 = 4;
                setBeginKill(10);
                break;
            case EFF_PHAPSU_BANG /* 109 */:
            case EFF_KIEM_LON_NGU /* 110 */:
                this.indexSound = 16;
                this.indexSound = 14;
                this.f = 0;
                if (this.typeEffect == 109) {
                    this.fraImgEff = new FrameImage(92);
                } else {
                    this.fraImgEff = new FrameImage(115);
                }
                short s = (short) (this.toX - this.x);
                short s2 = (short) ((this.toY - 0) - this.y);
                this.angle = (short) CRes.angle(s, s2);
                short abs3 = (Math.abs((int) s) + Math.abs((int) s2)) / 20;
                if (abs3 < 2) {
                    abs3 = 2;
                }
                for (short s3 = 0; s3 < abs3; s3++) {
                    Point point9 = new Point();
                    point9.x = this.x + ((s3 * s) / abs3);
                    point9.y = this.y + ((s3 * s2) / abs3);
                    this.VecEff.addElement(point9);
                }
                break;
            case 125:
                this.y = this.objKill.y - 100;
                this.timeAddNum = (byte) 3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.R = 240;
                this.angle = 0;
                this.size1 = 4;
                break;
            case 126:
                this.y = this.objKill.y - EFF_BIG_SWORD_115_2;
                this.x -= 12;
                this.timeAddNum = (byte) 3;
                this.fraImgEff = new FrameImage(21);
                this.fraImgSubEff = new FrameImage(59);
                this.fRemove = 16;
                this.R = 240;
                this.angle = 0;
                this.size1 = 4;
                break;
        }
        if (!this.isEff && this.objKill != null && this.objKill.typeObject != 1) {
            int i17 = 0;
            while (i17 < this.vecObjBeKill.size()) {
                Object_Effect_Skill object_Effect_Skill11 = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i17);
                if (object_Effect_Skill11 == null) {
                    this.vecObjBeKill.removeElement(object_Effect_Skill11);
                    i17--;
                } else {
                    MainObject mainObject8 = MainObject.get_Object(object_Effect_Skill11.ID, object_Effect_Skill11.tem);
                    if (mainObject8 == null) {
                        this.vecObjBeKill.removeElement(object_Effect_Skill11);
                        i17--;
                    } else if (mainObject8.Action != 4) {
                        mainObject8.hp = object_Effect_Skill11.hpLast;
                        if (mainObject8.hp <= 0 && mainObject8 != GameScreen.player) {
                            mainObject8.resetAction();
                            mainObject8.Action = 4;
                            mainObject8.timedie = GameCanvas.timeNow;
                            GameScreen.addEffectEndKill(11, mainObject8.x, mainObject8.y);
                        }
                    }
                    if (this.vecObjBeKill.size() == 0) {
                        this.isStop = true;
                    }
                }
                i17++;
            }
        }
        if (this.indexSound >= 0) {
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            } else {
                GameScreen.addSoundEff(this.indexSound);
            }
        }
    }

    public EffectSkill(int i, int i2, int i3, int i4, short s, byte b) {
        this.ispaintArena = true;
        this.VecEff = new mVector("EffectSkill VecEff");
        this.VecSubEff = new mVector("EffectSkill VecSubEff");
        this.mpaintone_three = new byte[]{4, 3, 2, 1, 0, 7, 6, 5};
        this.mpaintone_Bullet = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
        this.mImageBullet = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
        this.mXoayBullet = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
        this.gocArrow = 0;
        this.frameArrow = 0;
        this.isEff = false;
        this.indexAdd = 0;
        this.arr_R = new byte[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
        this.arr_radian = new int[]{0, 30, 60, 90, EFF_BIG_SWORD_115_2, 150, 180, 210, 240, 270, 300, 330};
        this.xO = 0;
        this.yO = 0;
        this.test = 0;
        this.frSleep = 0;
        this.frameSleep = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        this.frStun = (byte) 0;
        this.frameStun = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        this.life = 0;
        this.isSpeedUp = false;
        this.typeEffect = i;
        this.x = i2;
        this.y = i3;
        this.timeRemove = i4;
        this.time = GameCanvas.timeNow;
        switch (i) {
            case 29:
                this.isEff = true;
                this.indexColorStar = CRes.random(2);
                this.x1000 = i2 * ObjectData.MONSTER;
                this.y1000 = i3 * ObjectData.MONSTER;
                this.fRemove = CRes.random(4, 6);
                this.vMax = 5;
                this.xline = 10;
                this.yline = 20;
                create_Star_Line_In(this.vMax, this.xline, this.yline);
                return;
            case 69:
                this.fRemove = 16;
                this.isEff = true;
                this.fraImgEff = new FrameImage(9);
                this.vx = CRes.random_Am_0(2);
                this.vy = -2;
                return;
            case EFF_KILL_GHOST /* 80 */:
                this.fRemove = 16;
                this.isEff = true;
                this.fraImgEff = new FrameImage(16);
                this.objBeKillMain = MainObject.get_Object(s, (byte) 1);
                if (this.objBeKillMain == null) {
                    return;
                } else {
                    return;
                }
            case EFF_PHO_BANG /* 81 */:
                this.fRemove = 30;
                this.isEff = true;
                create_Fire_Arc();
                return;
            case 83:
                this.isEff = true;
                this.fRemove = 60;
                int random = CRes.random(3);
                if (random == 0) {
                    this.fraImgEff = new FrameImage(103);
                } else if (random == 1) {
                    this.fraImgEff = new FrameImage(104);
                } else if (random == 2) {
                    this.fraImgEff = new FrameImage(23);
                }
                this.toX = this.x;
                this.toY = this.y;
                this.x += CRes.random_Am(24, 48);
                this.y += CRes.random_Am(24, 48);
                createHut_Mp_Hp();
                return;
            case 84:
                this.isEff = true;
                this.indexColorStar = CRes.random(2);
                this.x1000 = i2 * ObjectData.MONSTER;
                this.y1000 = i3 * ObjectData.MONSTER;
                this.fRemove = CRes.random(6, 8);
                this.vMax = 2;
                this.xline = 10;
                this.yline = 20;
                create_Star_Line_In(this.vMax, this.xline, this.yline);
                return;
            case 85:
                this.timeRemove = i4 * ObjectData.MONSTER;
                this.isEff = true;
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null || this.objBeKillMain.isRemove || this.objBeKillMain.isStop) {
                    removeEff();
                    return;
                }
                this.indexColorStar = CRes.random(2);
                this.x1000 = i2;
                this.y1000 = i3;
                this.fRemove = 5;
                return;
            case 86:
                this.fraImgEff = new FrameImage(30);
                this.fraImgSubEff = new FrameImage(31);
                this.fraImgSub2Eff = new FrameImage(40);
                this.isEff = true;
                for (int i5 = 0; i5 < b; i5++) {
                    this.vMax = CRes.random(5, 10);
                    this.vx = this.vMax;
                    this.VecEff.addElement(create_Stone_Drop(MainScreen.cameraMain.xCam + 20 + CRes.random((GameCanvas.w * 5) / 4), MainScreen.cameraMain.yCam + 20 + CRes.random((GameCanvas.h * 5) / 4)));
                }
                return;
            case 87:
                this.fraImgEff = new FrameImage(11);
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null || this.objBeKillMain.isRemove || this.objBeKillMain.isStop) {
                    removeEff();
                    return;
                }
                int i6 = this.objBeKillMain.x;
                int i7 = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
                this.isEff = true;
                this.fRemove = 10;
                return;
            case 94:
                this.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3};
                mSound.playSound(32, mSound.volumeSound);
                this.fraImgEff = new FrameImage(127);
                this.fraImgSubEff = new FrameImage(53);
                this.fRemove = 60;
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null) {
                    return;
                }
                int i8 = this.objBeKillMain.x;
                int i9 = this.objBeKillMain.y;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = i8;
                this.objBeKillMain.toY = i9;
                this.objBeKillMain.isBinded = true;
                this.objBeKillMain.timeBind = mSystem.currentTimeMillis() + (i4 * ObjectData.MONSTER);
                LoadMap.timeVibrateScreen = 103;
                return;
            case 100:
                mSound.playSound(32, mSound.volumeSound);
                this.fRemove = 60;
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null) {
                    return;
                }
                int i10 = this.objBeKillMain.x;
                int i11 = this.objBeKillMain.y;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = i10;
                this.objBeKillMain.toY = i11;
                this.objBeKillMain.isDongBang = true;
                this.objBeKillMain.timeDongBang = mSystem.currentTimeMillis() + (i4 * ObjectData.MONSTER);
                this.levelPaint = -1;
                this.fRemove = 6;
                return;
            case 101:
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null) {
                    return;
                }
                this.ispaintsleep = false;
                this.delay = 10;
                int i12 = this.objBeKillMain.x;
                int i13 = this.objBeKillMain.y;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = i12;
                this.objBeKillMain.toY = i13;
                this.objBeKillMain.isSleep = true;
                this.objBeKillMain.timeSleep = mSystem.currentTimeMillis() + (i4 * ObjectData.MONSTER);
                return;
            case EFF_PET_EAGLE /* 102 */:
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null) {
                    return;
                }
                int i14 = this.objBeKillMain.x;
                int i15 = this.objBeKillMain.y - 25;
                this.objBeKillMain.vx = 0;
                this.objBeKillMain.vy = 0;
                this.objBeKillMain.toX = i14;
                this.objBeKillMain.toY = i15;
                if (this.objBeKillMain.typeObject == 1) {
                    this.ystun = 0;
                } else {
                    this.ystun = 17;
                }
                this.objBeKillMain.isStun = true;
                this.objBeKillMain.timeStun = mSystem.currentTimeMillis() + (i4 * ObjectData.MONSTER);
                return;
            case 103:
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null) {
                    return;
                }
                this.fraImgEff = new FrameImage(27);
                this.r = 72;
                this.timedelay = 20;
                this.ysai = 40;
                this.objBeKillMain.isnoBoss84 = true;
                this.objBeKillMain.timenoBoss84 = mSystem.currentTimeMillis() + (i4 * ObjectData.MONSTER);
                return;
            case EFF_BOSS_NO /* 107 */:
                this.objBeKillMain = MainObject.get_Object(s, b);
                if (this.objBeKillMain == null) {
                    return;
                }
                this.isEff = true;
                this.indexColorStar = CRes.random(2);
                int i16 = this.objBeKillMain.x;
                int i17 = this.objBeKillMain.y - 15;
                this.x1000 = i16 * ObjectData.MONSTER;
                this.y1000 = i17 * ObjectData.MONSTER;
                this.objBeKillMain.toX = i16;
                this.objBeKillMain.toY = i17;
                this.fRemove = CRes.random(4, 6);
                this.vMax = 5;
                this.xline = 10;
                this.yline = 20;
                create_Star_Line_In(this.vMax, this.xline, this.yline);
                this.objBeKillMain.isno = true;
                this.objBeKillMain.timeno = mSystem.currentTimeMillis() + (i4 * ObjectData.MONSTER);
                return;
            default:
                return;
        }
    }

    public EffectSkill(MainObject mainObject, int i) {
        this.ispaintArena = true;
        this.VecEff = new mVector("EffectSkill VecEff");
        this.VecSubEff = new mVector("EffectSkill VecSubEff");
        this.mpaintone_three = new byte[]{4, 3, 2, 1, 0, 7, 6, 5};
        this.mpaintone_Bullet = new byte[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13};
        this.mImageBullet = new byte[]{0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2, 0, 0, 2, 1, 1, 2};
        this.mXoayBullet = new byte[]{2, 2, 3, 3, 3, 4, 5, 5, 5, 5, 5, 1, 0, 0, 0, 0, 0, 7, 6, 6, 6, 6, 6, 2};
        this.gocArrow = 0;
        this.frameArrow = 0;
        this.isEff = false;
        this.indexAdd = 0;
        this.arr_R = new byte[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
        this.arr_radian = new int[]{0, 30, 60, 90, EFF_BIG_SWORD_115_2, 150, 180, 210, 240, 270, 300, 330};
        this.xO = 0;
        this.yO = 0;
        this.test = 0;
        this.frSleep = 0;
        this.frameSleep = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        this.frStun = (byte) 0;
        this.frameStun = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        this.life = 0;
        this.isSpeedUp = false;
        this.typeEffect = i;
        this.x = mainObject.x;
        this.y = mainObject.y;
        switch (i) {
            case 14:
                this.isEff = true;
                create_Fire_Arc();
                return;
            default:
                return;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 1057
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:64)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    @Override // GameEffect.MainEffect
    public void paint(CLib.mGraphics r13) {
        /*
            Method dump skipped, instructions count: 10665
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: GameEffect.EffectSkill.paint(CLib.mGraphics):void");
    }

    public void updateEffectCharWearing() {
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 1642
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:64)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    @Override // GameEffect.MainEffect
    public void update() {
        /*
            Method dump skipped, instructions count: 13617
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: GameEffect.EffectSkill.update():void");
    }

    private void SetAddSoundTimeAdd() {
        if (this.objKill == null) {
            return;
        }
        if (this.typeEffect == 27) {
            this.indexSound = 9;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                return;
            } else {
                GameScreen.addSoundEff(this.indexSound);
                return;
            }
        }
        if (this.typeEffect == 51) {
            this.indexSound = 19;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                return;
            } else {
                GameScreen.addSoundEff(this.indexSound);
                return;
            }
        }
        if (this.typeEffect == 125 || this.typeEffect == 126 || this.typeEffect == 97 || this.typeEffect == 106 || this.typeEffect == 105) {
            this.indexSound = 13;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                return;
            } else {
                GameScreen.addSoundEff(this.indexSound);
                return;
            }
        }
        if (this.typeEffect == 62) {
            this.indexSound = 20;
            if (this.objKill == GameScreen.player) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
            } else {
                GameScreen.addSoundEff(this.indexSound);
            }
        }
    }

    public void endUpdate() {
    }

    public static int setDirection(MainObject mainObject, MainObject mainObject2) {
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

    public int setDirection(int i, int i2) {
        int i3;
        if (CRes.abs(i) > CRes.abs(i2)) {
            if (i > 0) {
                i3 = 2;
            } else {
                i3 = 3;
            }
        } else if (i2 > 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        return i3;
    }

    private void setBeginKill(int i) {
        switch (this.Direction) {
            case 0:
                this.y += 10 + i;
                return;
            case 1:
                this.y -= 10 + i;
                return;
            case 2:
                this.x -= 10 + i;
                return;
            case 3:
                this.x += 10 + i;
                return;
            default:
                return;
        }
    }

    private void setBeginKillXY1000(int i) {
        switch (this.Direction) {
            case 0:
                this.y1000 += 10 + i;
                return;
            case 1:
                this.y1000 -= 10 + i;
                return;
            case 2:
                this.x1000 -= 10 + i;
                return;
            case 3:
                this.x1000 += 10 + i;
                return;
            default:
                return;
        }
    }

    public int setFrameAngle(int i) {
        byte b;
        if (i <= 15 || i > 345) {
            b = 12;
        } else {
            int i2 = ((i - 15) / 15) + 1;
            if (i2 > 24) {
                i2 = 24;
            }
            b = this.mpaintone_Bullet[i2];
        }
        return b;
    }

    private void setPosLineIn(int i) {
        switch (i) {
            case 0:
                switch (this.Direction) {
                    case 0:
                        this.x -= 8;
                        this.y += 20;
                        return;
                    case 1:
                        this.x += 8;
                        this.y += 40;
                        return;
                    case 2:
                        this.x += 15;
                        this.y += 20;
                        return;
                    case 3:
                        this.x -= 15;
                        this.y += 20;
                        return;
                    default:
                        return;
                }
            case 1:
                switch (this.Direction) {
                    case 0:
                        this.y += 45;
                        return;
                    case 1:
                        this.y += 10;
                        return;
                    case 2:
                        this.x -= 16;
                        this.y += 30;
                        return;
                    case 3:
                        this.x += 18;
                        this.y += 30;
                        return;
                    default:
                        return;
                }
            case 2:
            default:
                return;
            case 3:
                switch (this.Direction) {
                    case 0:
                        this.x += 8;
                        this.y += 25;
                        return;
                    case 1:
                        this.x += 5;
                        this.y += 45;
                        return;
                    case 2:
                        this.x += 13;
                        this.y += 35;
                        return;
                    case 3:
                        this.x -= 13;
                        this.y += 35;
                        return;
                    default:
                        return;
                }
            case 4:
                switch (this.Direction) {
                    case 0:
                        this.x -= 15;
                        this.y += 17;
                        return;
                    case 1:
                        this.x += 15;
                        this.y += 33;
                        return;
                    case 2:
                        this.x += 3;
                        this.y += 11;
                        return;
                    case 3:
                        this.x -= 3;
                        this.y += 11;
                        return;
                    default:
                        return;
                }
        }
    }

    public void removeEff() {
        if (this.typeEffect == 103) {
            return;
        }
        if (this.ispaintArena && mSystem.isj2me) {
            countSkillArena = (byte) (countSkillArena - 1);
        }
        if (this.VecEff.size() > 0) {
            this.VecEff.removeAllElements();
        }
        if (this.VecSubEff.size() > 0) {
            this.VecSubEff.removeAllElements();
        }
        boolean z = true;
        if (mSystem.isj2me && GameScreen.infoGame.ismapHouse(GameCanvas.loadmap.idMap)) {
            z = false;
        }
        if (this.timeAddNum == -1 && z) {
            addNum();
        }
        this.isStop = true;
        this.isRemove = true;
        this.f = -1;
    }

    public void addNum() {
        MainObject mainObject;
        int addEffKillPlus;
        if (this.objKill != null && this.objKill.ID != GameScreen.player.ID && GameScreen.infoGame.isMapchienthanh()) {
            return;
        }
        for (int i = 0; i < this.vecObjBeKill.size(); i++) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i);
            if (object_Effect_Skill != null && (mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem)) != null && mainObject != null) {
                if (!this.isEff) {
                    int i2 = 6;
                    if (object_Effect_Skill.hpShow == 0) {
                        if (mainObject == GameScreen.player) {
                            i2 = 7;
                        }
                        GameScreen.addEffectNum(T.hut, mainObject.x, mainObject.y - mainObject.hOne, i2, object_Effect_Skill.ID);
                    } else {
                        if (this.objKill != null && (addEffKillPlus = setAddEffKillPlus(object_Effect_Skill.mEffTypePlus, this.objKill, mainObject, object_Effect_Skill.mEff_HP_Plus)) != -1) {
                            i2 = addEffKillPlus;
                        }
                        if (this.typeEffect == 41) {
                            GameScreen.addEffectNum(new StringBuffer().append("+").append(object_Effect_Skill.hpShow).toString(), mainObject.x, mainObject.y - mainObject.hOne, this.subType == 0 ? 3 : 4, object_Effect_Skill.ID);
                        } else {
                            GameScreen.addEffectNum(new StringBuffer().append("-").append(object_Effect_Skill.hpShow).toString(), mainObject.x, mainObject.y - mainObject.hOne, i2, object_Effect_Skill.ID);
                        }
                    }
                    if (mainObject.typeObject == 1) {
                        mainObject.hp = object_Effect_Skill.hpLast;
                        if (mainObject.hp <= 0) {
                            if (this.objKill != null) {
                                MainObject.startDeadFly((MainMonster) mainObject, this.objKill.ID, CRes.random(3));
                            }
                            mainObject.resetAction();
                            mainObject.Action = 4;
                            mainObject.timedie = GameCanvas.timeNow;
                            GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                        }
                    }
                }
                if (this.typeEffect != 41 && this.typeEffect != 58 && this.typeEffect != 42 && (object_Effect_Skill.hpShow > 0 || (this.objKill != null && this.objKill == GameScreen.player))) {
                    if (mainObject.typeObject == 1) {
                        if (mainObject.Action != 3) {
                            if (!mainObject.isServerControl) {
                                mainObject.resetAction();
                            }
                            mainObject.Action = 3;
                            mainObject.f = 0;
                        }
                    } else if (mainObject.typeObject == 0) {
                        if (mainObject.eye != 3) {
                            if (this.objKill != null && this.objKill.typeObject == 0 && mainObject.hp > mainObject.maxHp / 2 && object_Effect_Skill.hpShow <= mainObject.maxHp / 20) {
                                mainObject.eye = 4;
                                mainObject.timeEye = -6;
                            } else {
                                mainObject.eye = 2;
                                mainObject.timeEye = 0;
                            }
                        }
                        if (CRes.random(5) == 0) {
                            mainObject.dy = -CRes.random(2, 5);
                        }
                        if (mainObject == GameScreen.player && CRes.random(3) == 0) {
                            mSound.playSound(38, mSound.volumeSound);
                        }
                    }
                }
            }
        }
        if (!this.isEff && this.objKill != null && this.objKill.typeObject != 9 && this.objKill.hp <= 0 && this.objKill.Action != 4) {
            this.objKill.resetAction();
            this.objKill.Action = 4;
            GameScreen.addEffectEndKill(11, this.objKill.x, this.objKill.y);
        }
    }

    private void createXP() {
        if (this.objKill == null) {
            this.gocT_Arc = 0;
        } else {
            switch (this.objKill.Direction) {
                case 0:
                    this.gocT_Arc = 0;
                    break;
                case 1:
                    this.gocT_Arc = 180;
                    break;
                case 2:
                    this.gocT_Arc = 90;
                    break;
                case 3:
                    this.gocT_Arc = 270;
                    break;
            }
        }
        this.va = 6144;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
        this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
    }

    private void createHut_Mp_Hp() {
        switch (CRes.random(4)) {
            case 0:
                this.gocT_Arc = 0;
                break;
            case 1:
                this.gocT_Arc = 180;
                break;
            case 2:
                this.gocT_Arc = 90;
                break;
            case 3:
                this.gocT_Arc = 270;
                break;
        }
        this.va = 6144;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
        this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
    }

    public void createNormal() {
        if (this.objKill == null) {
            this.gocT_Arc = 0;
        } else {
            switch (this.objKill.Direction) {
                case 0:
                    this.gocT_Arc = 90;
                    break;
                case 1:
                    this.gocT_Arc = 270;
                    break;
                case 2:
                    this.gocT_Arc = 180;
                    break;
                case 3:
                    this.gocT_Arc = 0;
                    break;
            }
        }
        this.va = 4096;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
        this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
    }

    public void createDanFocus() {
        switch (CRes.random(4)) {
            case 0:
                this.gocT_Arc = 90;
                break;
            case 1:
                this.gocT_Arc = 270;
                break;
            case 2:
                this.gocT_Arc = 180;
                break;
            case 3:
                this.gocT_Arc = 0;
                break;
        }
        this.va = 4096;
        this.vx = 0;
        this.vy = 0;
        this.life = 0;
        this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
        this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
    }

    public Point_Focus create_Speed(int i, int i2, Point_Focus point_Focus) {
        if (i2 == 0) {
            i2 = 1;
        }
        if (i == 0) {
            i = 1;
        }
        int distance = MainObject.getDistance(i, i2) / this.vMax;
        if (distance == 0) {
            distance = 1;
        }
        int i3 = i / distance;
        int i4 = i2 / distance;
        if (CRes.abs(i3) > CRes.abs(i)) {
            i3 = i;
        }
        if (CRes.abs(i4) > CRes.abs(i2)) {
            i4 = i2;
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
        }
        if (point_Focus != null) {
            point_Focus.vx = i3;
            point_Focus.vy = i4;
            point_Focus.toX = this.toX;
            point_Focus.toY = this.toY;
            point_Focus.fRe = distance;
        } else {
            this.fRemove = distance;
            this.vx = i3;
            this.vy = i4;
        }
        return point_Focus;
    }

    public Point_Focus create_Speed_More(int i, int i2, Point_Focus point_Focus, MainObject mainObject) {
        if (i2 == 0) {
            i2 = 1;
        }
        if (i == 0) {
            i = 1;
        }
        int distance = MainObject.getDistance(i, i2) / this.vMax;
        if (distance == 0) {
            distance = 1;
        }
        int i3 = i / distance;
        int i4 = i2 / distance;
        if (CRes.abs(i3) > CRes.abs(i)) {
            i3 = i;
        }
        if (CRes.abs(i4) > CRes.abs(i2)) {
            i4 = i2;
        }
        this.toX = mainObject.x;
        this.toY = mainObject.y - (mainObject.hOne / 2);
        if (point_Focus != null) {
            point_Focus.vx = i3;
            point_Focus.vy = i4;
            point_Focus.toX = this.toX;
            point_Focus.toY = this.toY;
            point_Focus.fRe = distance;
        } else {
            this.fRemove = distance;
            this.vx = i3;
            this.vy = i4;
        }
        return point_Focus;
    }

    public Point_Focus create_Speed_noToXY(int i, int i2, Point_Focus point_Focus) {
        if (i2 == 0) {
            i2 = 1;
        }
        if (i == 0) {
            i = 1;
        }
        int distance = MainObject.getDistance(i, i2) / this.vMax;
        if (distance == 0) {
            distance = 1;
        }
        int i3 = i / distance;
        int i4 = i2 / distance;
        if (CRes.abs(i3) > CRes.abs(i)) {
            i3 = i;
        }
        if (CRes.abs(i4) > CRes.abs(i2)) {
            i4 = i2;
        }
        if (point_Focus != null) {
            point_Focus.vx = i3;
            point_Focus.vy = i4;
            point_Focus.toX = this.toX;
            point_Focus.toY = this.toY;
            point_Focus.fRe = distance;
        } else {
            this.fRemove = distance;
            this.vx = i3;
            this.vy = i4;
        }
        return point_Focus;
    }

    public void create_Speed_Kiem_LV5(int i, int i2) {
        if (i2 == 0) {
            i2 = 1;
        }
        if (i == 0) {
            i = 1;
        }
        int distance = MainObject.getDistance(i, i2) / this.vMax;
        if (distance == 0) {
            distance = 1;
        }
        int i3 = i / distance;
        int i4 = i2 / distance;
        if (CRes.abs(i3) > CRes.abs(i)) {
            i3 = i;
        }
        if (CRes.abs(i4) > CRes.abs(i2)) {
            i4 = i2;
        }
        this.fRemove = distance;
        this.vx = i3;
        this.vy = i4;
    }

    private void create_Star() {
        this.x1000 = this.objBeKillMain.x * ObjectData.MONSTER;
        this.y1000 = (this.objBeKillMain.y * ObjectData.MONSTER) - ((this.objBeKillMain.hOne / 2) * ObjectData.MONSTER);
        this.colorpaint = new int[3];
        for (int i = 0; i < 3; i++) {
            this.colorpaint[i] = colorStar[0][CRes.random(3)];
        }
        this.fRemove = CRes.random(5, 9);
        this.mTamgiac = mSystem.new_M_Int(6, 4);
        for (int i2 = 0; i2 < 3; i2++) {
            int random = CRes.random(5 + (60 * i2), 55 + (60 * i2));
            int random2 = CRes.random(20, 30);
            this.mTamgiac[i2 * 2][0] = (CRes.sin(CRes.fixangle(random)) * random2) + this.x1000;
            this.mTamgiac[i2 * 2][1] = (CRes.cos(CRes.fixangle(random)) * random2) + this.y1000;
            int random_Am = random + CRes.random_Am(4, 12);
            int random_Am2 = random2 + CRes.random_Am(3, 10);
            this.mTamgiac[i2 * 2][2] = (CRes.sin(CRes.fixangle(random_Am)) * random_Am2) + this.x1000;
            this.mTamgiac[i2 * 2][3] = (CRes.cos(CRes.fixangle(random_Am)) * random_Am2) + this.y1000;
            int i3 = random + 180;
            int random_Am3 = random2 + CRes.random_Am(3, 10);
            this.mTamgiac[(i2 * 2) + 1][0] = (CRes.sin(CRes.fixangle(i3)) * random_Am3) + this.x1000;
            this.mTamgiac[(i2 * 2) + 1][1] = (CRes.cos(CRes.fixangle(i3)) * random_Am3) + this.y1000;
            int i4 = random_Am + 180;
            int random_Am4 = random_Am3 + CRes.random_Am(3, 10);
            this.mTamgiac[(i2 * 2) + 1][2] = (CRes.sin(CRes.fixangle(i4)) * random_Am4) + this.x1000;
            this.mTamgiac[(i2 * 2) + 1][3] = (CRes.cos(CRes.fixangle(i4)) * random_Am4) + this.y1000;
        }
    }

    private void createLighting(int i, int i2, int i3, int i4, boolean z) {
        int random;
        int random2;
        int i5 = 0;
        int distance = (MainObject.getDistance(i, i2, i3, i4) / 15) + CRes.random(3);
        int random3 = CRes.random(2);
        int angle = CRes.angle(i3 - i, i4 - i2);
        for (int i6 = 0; i6 < distance; i6++) {
            Point point = new Point();
            if (i6 == 0) {
                point.x = i * ObjectData.MONSTER;
                point.y = i2 * ObjectData.MONSTER;
            } else {
                Point point2 = (Point) this.VecEff.elementAt(i6 - 1);
                int random_Am_0 = 20 + CRes.random_Am_0(10);
                if ((MainObject.getDistance(point2.x / ObjectData.MONSTER, point2.y / ObjectData.MONSTER, i3, i4) < 25 || i6 == distance - 1) && z) {
                    point.x = i3 * ObjectData.MONSTER;
                    point.y = i4 * ObjectData.MONSTER;
                    this.VecEff.addElement(point);
                    return;
                }
                if (z) {
                    if (CRes.abs(i5) > 50) {
                        if (i5 > 0) {
                            random = angle - CRes.random(5, 50);
                        } else {
                            random = angle + CRes.random(5, 50);
                        }
                    } else if (i6 % 2 == random3) {
                        random = angle - CRes.random(5, 50);
                    } else {
                        random = angle + CRes.random(5, 50);
                    }
                } else if (CRes.abs(i5) > 50) {
                    if (i5 > 0) {
                        random = angle - CRes.random(5, 50);
                    } else {
                        random = angle + CRes.random(5, 50);
                    }
                } else if (i6 % 2 == random3) {
                    random = angle - CRes.random(10, 40);
                } else {
                    random = angle + CRes.random(10, 40);
                }
                i5 += random - angle;
                int fixangle = CRes.fixangle(random);
                point.x = point2.x + (CRes.cos(fixangle) * random_Am_0);
                point.y = point2.y + (CRes.sin(fixangle) * random_Am_0);
                if (CRes.random(6) < 5) {
                    this.VecSubEff.addElement(point);
                    new Point();
                    Point point3 = point;
                    int random4 = CRes.random(3, 7);
                    for (int i7 = 0; i7 < random4; i7++) {
                        int random_Am_02 = 5 + CRes.random_Am_0(3);
                        if (i7 % 2 == random3) {
                            random2 = fixangle - CRes.random(10, 40);
                        } else {
                            random2 = fixangle + CRes.random(10, 40);
                        }
                        int fixangle2 = CRes.fixangle(random2);
                        Point point4 = new Point();
                        point4.x = point3.x + (CRes.cos(fixangle2) * random_Am_02);
                        point4.y = point3.y + (CRes.sin(fixangle2) * random_Am_02);
                        this.VecSubEff.addElement(point4);
                        point3 = point4;
                    }
                    point3.x = -1;
                    this.VecSubEff.addElement(point3);
                }
            }
            this.VecEff.addElement(point);
        }
    }

    public Point create_Stone_Drop(int i, int i2) {
        Point point = new Point();
        int random = CRes.random(0, 40);
        int random2 = CRes.random(20, 50);
        this.x = MainScreen.cameraMain.xCam - random;
        int abs = CRes.abs(this.x - i);
        this.fRemove = CRes.abs(abs / this.vx);
        if (this.fRemove == 0) {
            this.fRemove = 1;
        }
        this.y = i2 - ((abs * ObjectData.MONSTER) / CRes.tan(random2));
        this.vY1000 = (CRes.abs(this.y - i2) * ObjectData.MONSTER) / this.fRemove;
        point.y = this.y;
        point.x = this.x;
        point.vx = this.vx;
        point.vy = this.vY1000;
        point.f = this.fRemove;
        return point;
    }

    public Point create_ICE_Drop(int i, int i2) {
        Point point = new Point();
        this.x = i;
        this.y = MainScreen.cameraMain.yCam;
        int abs = CRes.abs(this.y - i2);
        this.fRemove = abs / this.vMax;
        if (this.fRemove == 0) {
            this.fRemove = 1;
        }
        this.vY1000 = (abs * ObjectData.MONSTER) / this.fRemove;
        point.y = this.y;
        point.x = this.x;
        point.vx = 0;
        point.vy = this.vY1000;
        point.f = this.fRemove;
        return point;
    }

    private void create_Crack_Earth() {
        LoadMap.timeVibrateScreen = CRes.random(6, 12);
        this.levelPaint = -1;
        this.fRemove = 20;
        this.timeAddNum = (byte) 8;
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
        }
        if (CRes.abs(this.x - this.toX) > CRes.abs(this.y - this.toY)) {
            this.xline = 0;
            this.yline = 1;
        } else {
            this.xline = 1;
            this.yline = 0;
        }
        int distance = MainObject.getDistance(this.x, this.y, this.toX, this.toY) + 24;
        int angle = CRes.angle(this.toX - this.x, this.toY - this.y);
        createLighting(this.x, this.y, this.x + ((CRes.cos(angle) * distance) / ObjectData.MONSTER), this.y + ((CRes.sin(angle) * distance) / ObjectData.MONSTER), false);
    }

    private void create_Nova() {
        this.fraImgEff = new FrameImage(57);
        this.fraImgSubEff = new FrameImage(3);
        this.fRemove = 14;
        this.vMax = 6;
        for (int i = 0; i < 16; i++) {
            Point point = new Point();
            point.x = this.x * ObjectData.MONSTER;
            point.y = this.y * ObjectData.MONSTER;
            point.vx = 2 * CRes.cos((225 * i) / 10) * this.vMax;
            point.vy = 1 * CRes.sin((225 * i) / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
            point.frame = setFrameAngle(CRes.angle(((this.x + point.vx) * 100) - this.x, ((this.y + point.vy) * 100) - this.y));
        }
    }

    private void create_Poison_Nova() {
        this.fraImgEff = new FrameImage(125);
        this.fraImgSubEff = new FrameImage(3);
        this.fRemove = 14;
        this.vMax = 5;
        for (int i = 0; i < 16; i++) {
            Point point = new Point();
            point.x = this.x * ObjectData.MONSTER;
            point.y = this.y * ObjectData.MONSTER;
            point.vx = 2 * CRes.cos((225 * i) / 10) * this.vMax;
            point.vy = CRes.sin((225 * i) / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
            point.frame = setFrameAngle(CRes.angle(((this.x + point.vx) * 100) - this.x, ((this.y + point.vy) * 100) - this.y));
        }
    }

    private void create_Fire_Arc() {
        this.fraImgEff = new FrameImage(41);
        this.fraImgSubEff = new FrameImage(42);
        this.fRemove = 25;
        this.vMax = 5;
        for (int i = 0; i < 16; i++) {
            Point point = new Point();
            point.x = this.x * ObjectData.MONSTER;
            point.y = this.y * ObjectData.MONSTER;
            point.vx = 2 * CRes.cos((225 * i) / 10) * this.vMax;
            point.vy = 1 * CRes.sin((225 * i) / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
        }
    }

    private void create_Ice_Arc() {
        this.fraImgEff = new FrameImage(29);
        this.fraImgSubEff = new FrameImage(42);
        this.fRemove = 25;
        this.vMax = 5;
        for (int i = 0; i < 16; i++) {
            Point point = new Point();
            point.x = this.x * ObjectData.MONSTER;
            point.y = this.y * ObjectData.MONSTER;
            point.vx = 2 * CRes.cos((225 * i) / 10) * this.vMax;
            point.vy = 1 * CRes.sin((225 * i) / 10) * this.vMax;
            point.f = 0;
            this.VecEff.addElement(point);
        }
    }

    private void create_Arrow_Rain() {
        MainObject mainObject;
        if (this.f == -1) {
            this.VecEff.removeAllElements();
        }
        for (int i = 0; i < this.vecObjBeKill.size(); i++) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjBeKill.elementAt(i);
            if (object_Effect_Skill != null && (mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem)) != null) {
                int random = CRes.random(1, 4);
                if (GameCanvas.lowGraphic) {
                    random = CRes.random(1, 3);
                }
                for (int i2 = 0; i2 < random; i2++) {
                    this.vMax = CRes.random(14, 18);
                    Point point = new Point();
                    point.x = (mainObject.x - 70) + CRes.random_Am_0(15);
                    point.y = this.y1000;
                    int random_Am_0 = (mainObject.x - point.x) + CRes.random_Am_0(30);
                    int random_Am_02 = (mainObject.y - point.y) + 8 + CRes.random_Am_0(10);
                    if (random_Am_02 / this.vMax > 8) {
                        this.vMax = random_Am_02 / 8;
                    }
                    Point_Focus create_Speed = create_Speed(random_Am_0, random_Am_02, new Point_Focus());
                    point.vx = create_Speed.vx;
                    point.vy = create_Speed.vy;
                    point.fRe = create_Speed.fRe;
                    point.f = 0;
                    this.VecEff.addElement(point);
                }
            }
        }
    }

    public void create_Kill_2Kiem_Lv2() {
        int i;
        int i2;
        int tile;
        this.savex = this.objKill.x;
        this.savey = this.objKill.y;
        if (this.objKill != null) {
            this.y += this.objKill.hOne / 2;
            this.objKill.isTanHinh = true;
            this.objKill.timeTanHinh = 0;
        }
        this.fraImgEff = new FrameImage(50);
        this.vMax = 20;
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x - this.x;
            i2 = this.objBeKillMain.y - this.y;
        } else {
            i = this.toX - this.x;
            i2 = this.toY - this.y;
        }
        int distance = MainObject.getDistance(i, i2);
        int angle = CRes.angle(i, i2);
        int i3 = distance;
        this.toX = this.x + ((i3 * CRes.cos(angle)) / ObjectData.MONSTER);
        this.toY = this.y + ((i3 * CRes.sin(angle)) / ObjectData.MONSTER);
        int i4 = this.toX;
        int i5 = this.toY;
        do {
            this.toX = i4;
            this.toY = i5;
            if (i3 > 120) {
                tile = -1;
            } else {
                i3 += 10;
                i4 = this.x + ((i3 * CRes.cos(angle)) / ObjectData.MONSTER);
                i5 = this.y + ((i3 * CRes.sin(angle)) / ObjectData.MONSTER);
                tile = GameCanvas.loadmap.getTile(this.toX, this.toY);
            }
            if (tile == -1) {
                break;
            }
        } while (tile != 1);
        int i6 = this.toX - this.x;
        int i7 = this.toY - this.y;
        if (i7 == 0) {
            i7 = 1;
        }
        if (i6 == 0) {
            i6 = 1;
        }
        int distance2 = MainObject.getDistance(i6, i7) / this.vMax;
        if (distance2 == 0) {
            distance2 = 1;
        }
        int i8 = i6 / distance2;
        int i9 = i7 / distance2;
        if (CRes.abs(i8) > CRes.abs(i6)) {
            i8 = i6;
        }
        if (CRes.abs(i9) > CRes.abs(i7)) {
            i9 = i7;
        }
        this.vx = i8;
        this.vy = i9;
        this.fRemove = distance2;
        if (this.fRemove > 0) {
            this.timeAddNum = (byte) (this.fRemove / 2);
        }
        if (this.objKill != null && this.objBeKillMain != null) {
            this.toX = this.objKill.x + ((this.fRemove - 1) * i8);
            if (this.objBeKillMain != null) {
                this.toY = this.objKill.y + (this.objBeKillMain.hOne / 4) + ((this.fRemove - 1) * i9);
            } else {
                this.toY = this.objKill.y + ((this.fRemove - 1) * i9);
            }
        }
    }

    private void create_Star_Line_In(int i, int i2, int i3) {
        if (this.f == -1) {
            this.VecEff.removeAllElements();
        }
        this.colorpaint = new int[4];
        if (i3 <= i2) {
            i3 = i2 + 1;
        }
        for (int i4 = 0; i4 < 4; i4++) {
            if (CRes.random(2) == 0) {
                this.colorpaint[i4] = colorStar[this.indexColorStar][CRes.random(3)];
            } else {
                this.colorpaint[i4] = colorStar[this.indexColorStar][2];
            }
        }
        for (int i5 = 0; i5 < 4; i5++) {
            Line line = new Line();
            int i6 = 5 + ((180 / 4) * i5);
            int i7 = ((180 / 4) + ((180 / 4) * i5)) - 5;
            if (i7 <= i6) {
                i7 = i6 + 1;
            }
            int random = CRes.random(i2, i3);
            int random2 = CRes.random(i, i + 3);
            int random3 = CRes.random(i6, i7);
            int random4 = CRes.random(13, 23);
            boolean z = CRes.random(4) == 0;
            int fixangle = CRes.fixangle(random3);
            line.setLine(this.x1000 - (CRes.sin(fixangle) * (random + random4)), this.y1000 - (CRes.cos(fixangle) * (random + random4)), this.x1000 - (CRes.sin(fixangle) * random4), this.y1000 - (CRes.cos(fixangle) * random4), CRes.sin(fixangle) * random2, CRes.cos(fixangle) * random2, z);
            this.VecEff.addElement(line);
            Line line2 = new Line();
            int fixangle2 = CRes.fixangle(fixangle + 180 + CRes.random_Am(2, 5));
            line2.setLine(this.x1000 - (CRes.sin(fixangle2) * (random + random4)), this.y1000 - (CRes.cos(fixangle2) * (random + random4)), this.x1000 - (CRes.sin(fixangle2) * random4), this.y1000 - (CRes.cos(fixangle2) * random4), CRes.sin(fixangle2) * random2, CRes.cos(fixangle2) * random2, z);
            this.VecEff.addElement(line2);
        }
    }

    private void create_Line_NHANBAN_LV2() {
        int i;
        int random = CRes.random(1, 3);
        for (int i2 = 0; i2 < random; i2++) {
            Line line = new Line();
            int random2 = CRes.random(3, 12);
            if (random2 <= 5) {
                line.fRe = 16;
                i = 2;
            } else if (random2 <= 8) {
                i = 4;
                line.fRe = 12;
            } else {
                i = 5;
                line.fRe = 9;
            }
            int random_Am_0 = this.x1000 + CRes.random_Am_0(15);
            int random_Am_02 = this.y1000 - CRes.random_Am_0(10);
            line.setLine(random_Am_0, random_Am_02, random_Am_0, random_Am_02 - random2, 0, -i, false);
            line.idColor = CRes.random(3);
            this.VecEff.addElement(line);
        }
    }

    private void create_Star_Point_In() {
        int random = CRes.random(0, 45);
        for (int i = 0; i < 8; i++) {
            Point point = new Point();
            int i2 = random + ((360 * i) / 8);
            point.x = this.x1000 - (CRes.sin(CRes.fixangle(i2)) * 30);
            point.y = this.y1000 - (CRes.cos(CRes.fixangle(i2)) * 30);
            point.vx = CRes.sin(CRes.fixangle(i2)) * 4;
            point.vy = CRes.cos(CRes.fixangle(i2)) * 4;
            this.VecEff.addElement(point);
        }
    }

    private void create_Buff_Point_In() {
        int i = this.subType;
        if (i >= 9) {
            i = 4;
        } else if (i > 3 && i > 4 && i < 9) {
            i -= 5;
        }
        this.colorBuff = colorBuffMain[i][CRes.random(4)];
        this.colorpaint = new int[12];
        int random = CRes.random(0, 30);
        for (int i2 = 0; i2 < 12; i2++) {
            this.colorpaint[i2] = colorBuffMain[i][CRes.random(3)];
            Line line = new Line();
            int i3 = 5;
            int i4 = random + ((360 * i2) / 12);
            int random2 = CRes.random(20, 40);
            if (CRes.random(3) == 0) {
                i3 = 8;
                random2 = CRes.random(40, 55);
            }
            line.vx = CRes.sin(CRes.fixangle(i4)) * i3;
            line.vy = CRes.cos(CRes.fixangle(i4)) * i3;
            line.x0 = this.x1000 - (CRes.sin(CRes.fixangle(i4)) * random2);
            line.y0 = this.y1000 - (CRes.cos(CRes.fixangle(i4)) * random2);
            line.y1 = this.y1000 - (CRes.cos(CRes.fixangle(i4)) * (random2 + 6));
            line.x1 = this.x1000 - (CRes.sin(CRes.fixangle(i4)) * (random2 + 6));
            line.is2Line = CRes.random(3) == 0;
            this.VecEff.addElement(line);
        }
    }

    private void create_Mon_Buff() {
        int i = this.subType;
        if (i > 3 && i > 4 && i < 9) {
            i -= 5;
        }
        this.colorBuff = colorBuffMain[i][CRes.random(4)];
        this.colorpaint = new int[12];
        int random = CRes.random(0, 30);
        for (int i2 = 0; i2 < 12; i2++) {
            this.colorpaint[i2] = colorBuffMain[i][CRes.random(3)];
            Line line = new Line();
            int i3 = 4;
            int i4 = random + ((360 * i2) / 12);
            int random2 = CRes.random(10, 25);
            if (CRes.random(3) == 0) {
                i3 = 6;
                random2 = CRes.random(20, 40);
            }
            line.vx = CRes.sin(CRes.fixangle(i4)) * i3;
            line.vy = CRes.cos(CRes.fixangle(i4)) * i3;
            line.x0 = this.x1000 - (CRes.sin(CRes.fixangle(i4)) * random2);
            line.y0 = this.y1000 - (CRes.cos(CRes.fixangle(i4)) * random2);
            line.y1 = this.y1000 - (CRes.cos(CRes.fixangle(i4)) * (random2 + 6));
            line.x1 = this.x1000 - (CRes.sin(CRes.fixangle(i4)) * (random2 + 6));
            line.is2Line = CRes.random(3) == 0;
            this.VecEff.addElement(line);
        }
    }

    public void create_2Kiem_Lv4(int i, MainObject mainObject) {
        int i2;
        int i3;
        if (mainObject != null) {
            i2 = this.x - mainObject.x;
            i3 = this.y - (mainObject.y - (mainObject.hOne / 2));
        } else {
            i2 = this.x - this.toX;
            i3 = this.y - this.toY;
        }
        int fixangle = CRes.fixangle(CRes.angle(i2, i3) + i);
        int fixangle2 = CRes.fixangle(CRes.angle(-i2, -i3) + i);
        Point point = new Point();
        if (mainObject != null) {
            point.x = mainObject.x + ((50 * CRes.cos(fixangle)) / ObjectData.MONSTER);
            point.y = (mainObject.y - (mainObject.hOne / 2)) + ((50 * CRes.sin(fixangle)) / ObjectData.MONSTER);
        } else {
            point.x = this.toX + ((50 * CRes.cos(fixangle)) / ObjectData.MONSTER);
            point.y = this.toY + ((50 * CRes.sin(fixangle)) / ObjectData.MONSTER);
        }
        point.vx = (30 * CRes.cos(fixangle2)) / ObjectData.MONSTER;
        point.vy = (30 * CRes.sin(fixangle2)) / ObjectData.MONSTER;
        this.VecEff.addElement(point);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:14:0x00ba. Please report as an issue. */
    public void create_2Kiem_Lv3() {
        this.fraImgEff = new FrameImage(15);
        this.fraImgSubEff = new FrameImage(24);
        this.fraImgSub2Eff = new FrameImage(26);
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
        }
        this.vMax = 16;
        this.fRemove = 6;
        switch (this.Direction) {
            case 0:
                this.frame = 6;
                break;
            case 1:
                this.frame = 18;
                break;
            case 2:
                this.frame = 0;
                break;
            case 3:
                this.frame = 12;
                break;
        }
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            Point point = new Point();
            switch (this.Direction) {
                case 0:
                    if (i2 == 0) {
                        point.y = this.y + 25;
                        point.x = this.x;
                        break;
                    } else if (i2 == 1) {
                        point.y = (this.y + 25) - 18;
                        point.x = this.x + 14;
                        break;
                    } else if (i2 == 2) {
                        point.y = (this.y + 25) - 18;
                        point.x = this.x - 14;
                        break;
                    }
                    break;
                case 1:
                    if (i2 == 0) {
                        point.y = this.y - 25;
                        point.x = this.x;
                        break;
                    } else if (i2 == 1) {
                        point.y = (this.y - 25) + 18;
                        point.x = this.x + 14;
                        break;
                    } else if (i2 == 2) {
                        point.y = (this.y - 25) + 18;
                        point.x = this.x - 14;
                        break;
                    }
                    break;
                case 2:
                    if (i2 == 0) {
                        point.y = this.y;
                        point.x = this.x - 25;
                        break;
                    } else if (i2 == 1) {
                        point.y = this.y + 14;
                        point.x = (this.x - 25) + 18;
                        break;
                    } else if (i2 == 2) {
                        point.y = this.y - 14;
                        point.x = (this.x - 25) + 18;
                        break;
                    }
                    break;
                case 3:
                    if (i2 == 0) {
                        point.y = this.y;
                        point.x = this.x + 25;
                        break;
                    } else if (i2 == 1) {
                        point.y = this.y + 14;
                        point.x = (this.x + 25) - 18;
                        break;
                    } else if (i2 == 2) {
                        point.y = this.y - 14;
                        point.x = (this.x + 25) - 18;
                        break;
                    }
                    break;
            }
            Point_Focus point_Focus = new Point_Focus();
            create_Speed(this.toX - point.x, this.toY - point.y, point_Focus);
            point.vx = point_Focus.vx;
            point.vy = point_Focus.vy;
            point.fRe = point_Focus.fRe;
            if (i2 == 0) {
                i = setFrameAngle(CRes.angle(this.toX - point.x, this.toY - point.y));
            }
            point.frame = i;
            point.dis = CRes.random(4);
            this.VecEff.addElement(point);
        }
        setBeginKill(5);
        this.y += 10;
    }

    void create_2Kiem_Lv5() {
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
        }
        this.fRemove = 20;
        this.vMax = 20;
        this.fraImgEff = new FrameImage(15);
        this.fraImgSubEff = new FrameImage(17);
        this.fraImgSub2Eff = new FrameImage(26);
        this.lT_Arc = 32;
        int random = CRes.random(360);
        for (int i = 0; i < 5; i++) {
            Point point = new Point();
            point.x = this.toX * ObjectData.MONSTER;
            point.y = this.toY * ObjectData.MONSTER;
            point.g = CRes.fixangle(random + (i * 72));
            point.vx = CRes.sin(CRes.fixangle(point.g)) * this.lT_Arc;
            point.vy = CRes.cos(CRes.fixangle(point.g)) * this.lT_Arc;
            point.frame = setFrameAngle(CRes.angle(-point.vx, -point.vy));
            this.VecEff.addElement(point);
            Point point2 = new Point();
            point2.x = point.x + point.vx;
            point2.y = point.y + point.vy;
            point2.vx = CRes.sin(CRes.fixangle(point.g + 180)) * 5;
            point2.vy = CRes.cos(CRes.fixangle(point.g + 180)) * 5;
            this.VecSubEff.addElement(point2);
        }
    }

    private void create_Kiem_Lv2() {
        LoadMap.timeVibrateScreen = 103;
        if (this.objBeKillMain != null) {
            this.x = this.objBeKillMain.x;
            this.y = this.objBeKillMain.y + (this.objBeKillMain.hOne / 4);
        } else {
            this.x = this.toX;
            this.y = this.toY;
        }
        this.fraImgEff = new FrameImage(53);
        this.vMax = 4;
        this.fRemove = 6;
        if (this.fRemove > 0) {
            this.timeAddNum = (byte) (this.fRemove / 2);
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 4);
        }
    }

    private void create_Kiem_Lv3() {
        int i;
        int i2;
        this.y += 10;
        if (this.Direction == 1) {
            this.y -= 5;
        }
        this.fraImgEff = new FrameImage(8);
        this.fraImgSubEff = new FrameImage(53);
        this.vMax = 14;
        setBeginKill(10);
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x - this.x;
            i2 = this.objBeKillMain.y - this.y;
        } else {
            i = this.toX - this.x;
            i2 = this.toY - this.y;
        }
        create_Speed(i, i2, null);
        if (this.Direction != 1) {
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            this.VecEff.addElement(point);
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 4);
        }
    }

    private void create_PS_LV2_3_5() {
        int i;
        int i2;
        this.y += 8;
        this.vMax = 12;
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x - this.x;
            i2 = (this.objBeKillMain.y - (this.objBeKillMain.hOne / 2)) - this.y;
        } else {
            i = this.toX - this.x;
            i2 = this.toY - this.y;
        }
        this.y += 6;
        this.vMax = 16;
        this.fraImgEff = new FrameImage(57);
        this.fraImgSubEff = new FrameImage(3);
        this.frame = setFrameAngle(CRes.angle(i, i2));
        create_Speed(i, i2, null);
        if (this.vx >= 0) {
            this.frameArrow = 0;
        } else {
            this.frameArrow = 2;
        }
    }

    private void createArrowProjectile(int i) {
        int i2;
        int i3;
        this.vMax = 12;
        if (this.objBeKillMain != null) {
            i2 = this.objBeKillMain.x - this.x;
            i3 = (this.objBeKillMain.y - (this.objBeKillMain.hOne / 2)) - this.y;
        } else {
            i2 = this.toX - this.x;
            i3 = this.toY - this.y;
        }
        this.fraImgEff = new FrameImage(i);
        this.frame = setFrameAngle(CRes.angle(i2, i3));
        create_Speed(i2, i3, null);
        if (this.vx >= 0) {
            this.frameArrow = 0;
        } else {
            this.frameArrow = 2;
        }
    }

    private void create_Sung_LV2_LV4() {
        int i;
        int i2;
        this.y += 5;
        if (this.typeEffect == 22) {
            this.indexSound = 17;
            this.fraImgEff = new FrameImage(74);
            this.vMax = 22;
        } else if (this.typeEffect == 31) {
            this.indexSound = 18;
            this.y += 3;
            this.fraImgEff = new FrameImage(97);
            this.fraImgSubEff = new FrameImage(31);
            this.vMax = 16;
        }
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x - this.x;
            i2 = (this.objBeKillMain.y - (this.objBeKillMain.hOne / 2)) - this.y;
        } else {
            i = this.toX - this.x;
            i2 = this.toY - this.y;
        }
        create_Speed(i, i2, null);
        this.frame = setFrameAngle(CRes.angle(i, i2));
    }

    private void create_Sung_Medusa() {
        int i;
        int i2;
        this.y += 5;
        if (this.typeEffect == 99) {
            this.indexSound = 34;
            this.y += 3;
            this.fraImgEff = new FrameImage(135);
            this.vMax = 16;
        }
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x - this.x;
            i2 = (this.objBeKillMain.y - (this.objBeKillMain.hOne / 2)) - this.y;
        } else {
            i = this.toX - this.x;
            i2 = this.toY - this.y;
        }
        create_Speed(i, i2, null);
        this.frame = setFrameAngle(CRes.angle(i, i2));
    }

    private void create_FireBall_Tower() {
        int i;
        int i2;
        this.dxTower = 20;
        this.dyTower = -50;
        this.y += this.dyTower;
        this.x += this.dxTower;
        this.indexSound = 32;
        this.fraImgEff = new FrameImage(133);
        this.fraImgSubEff = new FrameImage(31);
        this.vMax = 16;
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x - this.x;
            i2 = this.objBeKillMain.y - this.y;
        } else {
            i = this.toX - this.x;
            i2 = this.toY - this.y;
        }
        create_Speed(i, i2, null);
        this.frame = setFrameAngle(CRes.angle(i, i2));
    }

    private void create_Sung_DAY_DAN(int i, int i2) {
        int i3 = i - this.x;
        int i4 = i2 - this.y;
        Point_Focus point_Focus = new Point_Focus();
        create_Speed_noToXY(i3, i4, point_Focus);
        Point point = new Point();
        point.x = this.x;
        point.y = this.y;
        point.vx = point_Focus.vx;
        point.vy = point_Focus.vy;
        point.fRe = point_Focus.fRe;
        point.frame = setFrameAngle(CRes.angle(i3, i4));
        this.VecEff.addElement(point);
    }

    private void create_Sung_Lv3() {
        int i;
        int i2;
        this.y += 5;
        this.fRemove = 5;
        this.fraImgEff = new FrameImage(47);
        this.vMax = 22;
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x;
            i2 = this.objBeKillMain.y - (this.objBeKillMain.hOne / 2);
        } else {
            i = this.toX;
            i2 = this.toY;
        }
        this.frame = setFrameAngle(CRes.angle(i - this.x, i2 - this.y));
        for (int i3 = 0; i3 < 4; i3++) {
            int random_Am = (i + CRes.random_Am(0, 13)) - this.x;
            int random_Am2 = (i2 + CRes.random_Am(0, 13)) - this.y;
            Point_Focus point_Focus = new Point_Focus();
            create_Speed(random_Am, random_Am2, point_Focus);
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            point.vx = point_Focus.vx;
            point.vy = point_Focus.vy;
            point.fRe = point_Focus.fRe;
            this.VecEff.addElement(point);
        }
    }

    private void create_Sung_BaoDan() {
        if (this.objBeKillMain == null) {
            if (this.f < this.fRemove) {
                this.f = this.fRemove;
                return;
            }
            return;
        }
        this.frame = setFrameAngle(CRes.angle(this.toX - this.x, this.toY - this.y));
        for (int i = 0; i < 6; i++) {
            int random_Am = (this.toX + CRes.random_Am(0, 35)) - this.x;
            int random_Am2 = (this.toY + CRes.random_Am(0, 35)) - this.y;
            Point_Focus point_Focus = new Point_Focus();
            create_Speed_noToXY(random_Am, random_Am2, point_Focus);
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            point.vx = point_Focus.vx;
            point.vy = point_Focus.vy;
            point.fRe = point_Focus.fRe + CRes.random(2);
            point.frame = this.frame;
            this.VecEff.addElement(point);
        }
    }

    private void create_Level_up() {
        this.levelPaint = -1;
        if (this.objKill != null) {
            this.y += this.objKill.hOne / 2;
        }
        this.fraImgEff = new FrameImage(65);
        this.fraImgSubEff = new FrameImage(67);
        this.fRemove = 23;
        for (int i = 0; i < 10; i++) {
            Point point = new Point();
            int random = CRes.random(180, 360);
            point.x = (17 * CRes.cos(random)) / ObjectData.MONSTER;
            point.y = ((15 * CRes.sin(random)) / ObjectData.MONSTER) - 5;
            point.fRe = CRes.random(2);
            point.frame = CRes.random(4);
            point.limitY = 50;
            this.VecEff.addElement(point);
        }
    }

    private void create_2Kiem_GaiDoc() {
        this.timeAddNum = (byte) 4;
        if (this.typeEffect == 47) {
            this.fraImgEff = new FrameImage(87);
        } else {
            this.fraImgEff = new FrameImage(127);
        }
        this.fraImgSubEff = new FrameImage(75);
        if (this.objKill != null) {
            this.y = this.objKill.y;
        }
        if (this.objBeKillMain != null) {
            this.toX = this.objBeKillMain.x;
            this.toY = this.objBeKillMain.y - (this.objBeKillMain.hOne / 4);
        }
        this.fRemove = 20;
        int i = this.x * ObjectData.MONSTER;
        int i2 = this.y * ObjectData.MONSTER;
        int angle = CRes.angle(this.toX - this.x, this.toY - this.y);
        int distance = MainObject.getDistance(this.toX - this.x, this.toY - this.y);
        if (distance > 60) {
            i = (this.x * ObjectData.MONSTER) + ((distance - 50) * CRes.cos(angle));
            i2 = (this.y * ObjectData.MONSTER) + ((distance - 50) * CRes.sin(angle));
        }
        int cos = 18 * CRes.cos(angle);
        int sin = 18 * CRes.sin(angle);
        Point point = new Point();
        point.fRe = 0;
        point.x = i + cos;
        point.y = i2 + sin;
        point.frame = CRes.random(5);
        if (this.Direction == 2) {
            point.dis = 2;
        }
        this.VecEff.addElement(point);
        for (int i3 = 1; i3 < 6; i3++) {
            Point point2 = new Point();
            point2.fRe = i3 / 2;
            Point point3 = (Point) this.VecEff.elementAt(i3 - 1);
            point2.x = point3.x + cos + (i3 % 2 == 0 ? -0 : 0);
            if (angle > 1575 && angle <= 3375) {
                point2.y = point3.y + sin + (i3 % 2 == 0 ? -0 : 0);
            } else {
                point2.y = point3.y + sin + (i3 % 2 == 0 ? 0 : -0);
            }
            point2.dis = 0;
            point2.frame = CRes.random(5);
            if (this.Direction == 2) {
                point2.dis = 2;
            }
            this.VecEff.addElement(point2);
        }
    }

    private void create_Monster() {
        this.vMax = 5;
        if (this.objKill != null && this.objBeKillMain != null && MainObject.getDistance(this.objKill.x, this.objBeKillMain.y, this.objKill.y, this.objBeKillMain.y) < 15) {
            this.vMax = 2;
        }
        switch (this.Direction) {
            case 0:
                this.y += this.vMax;
                this.y1000 = this.vMax;
                this.frame = 17;
                this.frameArrow = 5;
                break;
            case 1:
                this.y -= this.vMax;
                this.y1000 = -this.vMax;
                this.frame = 33;
                this.frameArrow = 6;
                break;
            case 2:
                this.x -= this.vMax;
                this.x1000 = -this.vMax;
                this.frame = 10;
                this.frameArrow = 2;
                break;
            case 3:
                this.x += this.vMax;
                this.x1000 = this.vMax;
                this.frame = 6;
                this.frameArrow = 0;
                break;
        }
        switch (this.subType) {
            case 0:
                this.fraImgEff = new FrameImage(106);
                break;
            case 1:
                this.fraImgEff = new FrameImage(EFF_BOSS_NO);
                break;
            case 2:
                this.fraImgEff = new FrameImage(108);
                break;
            case 3:
                this.fraImgEff = new FrameImage(EFF_PHAPSU_BANG);
                break;
            case 4:
                this.fraImgEff = new FrameImage(EFF_KIEM_LON_NGU);
                break;
            default:
                this.fraImgEff = new FrameImage(106);
                break;
        }
        this.fRemove = 8;
    }

    private void create_PS_Xungkich() {
        int i;
        int i2;
        this.vMax = 12;
        if (this.objBeKillMain != null) {
            i = this.objBeKillMain.x - this.x;
            i2 = this.objBeKillMain.y - this.y;
        } else {
            i = this.toX - this.x;
            i2 = this.toY - this.y;
        }
        create_Speed(i, i2, null);
        this.x1000 = this.x;
        this.y1000 = this.y;
        this.y1000 += 10;
        if (this.Direction == 1) {
            this.y1000 -= 5;
        }
        setBeginKillXY1000(10);
    }

    public void updateAngleNormal() {
        if (this.objBeKillMain == null) {
            removeEff();
            return;
        }
        int i = this.objBeKillMain.x - this.x;
        int i2 = (this.objBeKillMain.y - (this.objBeKillMain.hOne >> 1)) - this.y;
        this.life++;
        if ((CRes.abs(i) < 16 && CRes.abs(i2) < 16) || this.life > this.fRemove) {
            removeEff();
            return;
        }
        int angle = CRes.angle(i, i2);
        if (Math.abs(angle - this.gocT_Arc) < 90 || (i * i) + (i2 * i2) > 4096) {
            if (Math.abs(angle - this.gocT_Arc) < 15) {
                this.gocT_Arc = angle;
            } else if ((angle - this.gocT_Arc >= 0 && angle - this.gocT_Arc < 180) || angle - this.gocT_Arc < -180) {
                this.gocT_Arc = CRes.fixangle(this.gocT_Arc + 15);
            } else {
                this.gocT_Arc = CRes.fixangle(this.gocT_Arc - 15);
            }
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va += 2048;
        }
        this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
        this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
        int i3 = i + this.vX1000;
        this.x += i3 >> 10;
        int i4 = i3 & 1023;
        int i5 = i2 + this.vY1000;
        this.y += i5 >> 10;
        int i6 = i5 & 1023;
    }

    public void updateAngleXP() {
        int i;
        int i2;
        if (this.typeEffect == 96) {
            Point point = new Point();
            point.x = this.x;
            point.y = this.y;
            this.VecEff.addElement(point);
        }
        if (this.typeEffect == 88) {
            Point point2 = new Point();
            point2.x = this.x;
            point2.y = this.y;
            this.VecEff.addElement(point2);
        }
        if (this.objBeKillMain == null || this.objBeKillMain.isRemove || this.f >= this.fRemove || this.objBeKillMain.isStop) {
            this.f = this.fRemove;
            return;
        }
        if (this.typeEffect == 92 || this.typeEffect == 89) {
            i = this.objKill.x - this.x;
            i2 = (this.objKill.y - (this.objKill.hOne >> 1)) - this.y;
        } else {
            i = this.objBeKillMain.x - this.x;
            i2 = (this.objBeKillMain.y - (this.objBeKillMain.hOne >> 1)) - this.y;
        }
        this.life++;
        if ((CRes.abs(i) < 16 && CRes.abs(i2) < 16) || this.life > this.fRemove) {
            this.f = this.fRemove;
            return;
        }
        int angle = CRes.angle(i, i2);
        if (Math.abs(angle - this.gocT_Arc) < 90 || (i * i) + (i2 * i2) > 4096) {
            if (Math.abs(angle - this.gocT_Arc) < 15) {
                this.gocT_Arc = angle;
            } else if ((angle - this.gocT_Arc >= 0 && angle - this.gocT_Arc < 180) || angle - this.gocT_Arc < -180) {
                this.gocT_Arc = CRes.fixangle(this.gocT_Arc + 15);
            } else {
                this.gocT_Arc = CRes.fixangle(this.gocT_Arc - 15);
            }
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va += 3096;
        }
        this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
        this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
        int i3 = i + this.vX1000;
        this.x += i3 >> 10;
        int i4 = i3 & 1023;
        int i5 = i2 + this.vY1000;
        this.y += i5 >> 10;
        int i6 = i5 & 1023;
        if (this.typeEffect != 88) {
            Point point3 = new Point();
            point3.x = this.x;
            point3.y = this.y;
            this.VecEff.addElement(point3);
        }
    }

    public void updateAngleHut_Mp_Hp() {
        if (this.f >= this.fRemove) {
            this.f = this.fRemove;
            return;
        }
        int i = this.toX - this.x;
        int i2 = this.toY - this.y;
        this.life++;
        if ((CRes.abs(i) < 16 && CRes.abs(i2) < 16) || this.life > this.fRemove) {
            this.f = this.fRemove;
            return;
        }
        int angle = CRes.angle(i, i2);
        if (Math.abs(angle - this.gocT_Arc) < 90 || (i * i) + (i2 * i2) > 4096) {
            if (Math.abs(angle - this.gocT_Arc) < 15) {
                this.gocT_Arc = angle;
            } else if ((angle - this.gocT_Arc >= 0 && angle - this.gocT_Arc < 180) || angle - this.gocT_Arc < -180) {
                this.gocT_Arc = CRes.fixangle(this.gocT_Arc + 15);
            } else {
                this.gocT_Arc = CRes.fixangle(this.gocT_Arc - 15);
            }
        }
        if (!this.isSpeedUp && this.va < 8192) {
            this.va += 3096;
        }
        this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
        this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
        int i3 = i + this.vX1000;
        this.x += i3 >> 10;
        int i4 = i3 & 1023;
        int i5 = i2 + this.vY1000;
        this.y += i5 >> 10;
        int i6 = i5 & 1023;
        Point point = new Point();
        point.x = this.x;
        point.y = this.y;
        this.VecEff.addElement(point);
    }

    public void paint_phuong_hoang(mGraphics mgraphics, FrameImage frameImage, int i, int i2, int i3) {
        int i4 = frameImage.nFrame / 3;
        switch (i) {
            case 0:
                frameImage.drawFrame(this.f % i4, i2, i3, 2, 3, mgraphics);
                return;
            case 1:
                frameImage.drawFrame((i4 * 2) + (this.f % i4), i2, i3, 4, 3, mgraphics);
                return;
            case 2:
                frameImage.drawFrame(i4 + (this.f % i4), i2, i3, 0, 3, mgraphics);
                return;
            case 3:
                frameImage.drawFrame((i4 * 2) + (this.f % i4), i2, i3, 5, 3, mgraphics);
                return;
            case 4:
                frameImage.drawFrame(this.f % i4, i2, i3, 0, 3, mgraphics);
                return;
            case 5:
                frameImage.drawFrame((i4 * 2) + (this.f % i4), i2, i3, 0, 3, mgraphics);
                return;
            case 6:
                frameImage.drawFrame(i4 + (this.f % i4), i2, i3, 1, 3, mgraphics);
                return;
            case 7:
                frameImage.drawFrame((i4 * 2) + (this.f % i4), i2, i3, 2, 3, mgraphics);
                return;
            default:
                return;
        }
    }

    private void paint_Ice_Nova_Effect(mGraphics mgraphics) {
        if (this.fraImgEff == null) {
            return;
        }
        for (int i = 0; i < this.VecEff.size(); i++) {
            Point point = (Point) this.VecEff.elementAt(i);
            if (point.f < this.fRemove) {
                paint_Bullet(mgraphics, this.fraImgEff, point.frame, point.x / ObjectData.MONSTER, point.y / ObjectData.MONSTER, false);
            }
        }
    }

    private void paint_Poison_Nova_Effect(mGraphics mgraphics) {
        if (this.fraImgEff == null) {
            return;
        }
        for (int i = 0; i < this.VecEff.size(); i++) {
            Point point = (Point) this.VecEff.elementAt(i);
            if (point.f < this.fRemove) {
                paint_Bullet(mgraphics, this.fraImgEff, point.frame, point.x / ObjectData.MONSTER, point.y / ObjectData.MONSTER, false);
            }
        }
    }

    private void update_Nova_Effect() {
        if (this.f >= this.fRemove) {
            for (int i = 0; i < this.VecEff.size(); i++) {
                Point point = (Point) this.VecEff.elementAt(i);
                if (this.typeEffect == 90) {
                    GameScreen.addEffectEndKill(10, point.x / ObjectData.MONSTER, point.y / ObjectData.MONSTER);
                    GameScreen.addEffectEndKill(15, point.x / ObjectData.MONSTER, (point.y / ObjectData.MONSTER) + (this.objBeKillMain.hOne / 4));
                } else if (this.typeEffect == 91) {
                    GameScreen.addEffectEndKill(44, point.x / ObjectData.MONSTER, point.y / ObjectData.MONSTER);
                }
            }
            removeEff();
            return;
        }
        for (int i2 = 0; i2 < this.VecEff.size(); i2++) {
            Point point2 = (Point) this.VecEff.elementAt(i2);
            point2.x += point2.vx;
            point2.y += point2.vy;
            int tile = GameCanvas.loadmap.getTile(point2.x / ObjectData.MONSTER, point2.y / ObjectData.MONSTER);
            if (tile == -1 || tile == 1 || !MainEffect.isInScreen(point2.x / ObjectData.MONSTER, point2.y / ObjectData.MONSTER, this.fraImgEff.frameWidth, this.fraImgEff.frameHeight)) {
                if (this.typeEffect == 90) {
                    GameScreen.addEffectEndKill(10, point2.x / ObjectData.MONSTER, point2.y / ObjectData.MONSTER);
                    GameScreen.addEffectEndKill(15, point2.x / ObjectData.MONSTER, (point2.y / ObjectData.MONSTER) + (this.objBeKillMain.hOne / 4));
                } else if (this.typeEffect == 91) {
                    GameScreen.addEffectEndKill(44, point2.x / ObjectData.MONSTER, point2.y / ObjectData.MONSTER);
                }
                this.VecEff.removeElement(point2);
            }
        }
    }

    public void paint_Bullet(mGraphics mgraphics, FrameImage frameImage, int i, int i2, int i3, boolean z) {
        int i4 = frameImage.nFrame / 3;
        frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2, i3, this.mXoayBullet[i], 3, mgraphics);
        if (z) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 23:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + (2 * 3), i3, this.mXoayBullet[i], 3, mgraphics);
                    return;
                case 3:
                case 4:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    return;
                case 5:
                case 6:
                case 7:
                case 8:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2, i3 - (2 * 3), this.mXoayBullet[i], 3, mgraphics);
                    return;
                case 9:
                case 10:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    return;
                case 11:
                case 12:
                case 13:
                case 14:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3 - 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - (2 * 3), i3, this.mXoayBullet[i], 3, mgraphics);
                    return;
                case 15:
                case 16:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    return;
                case 17:
                case 18:
                case 19:
                case 20:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 - 3, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2, i3 + (2 * 3), this.mXoayBullet[i], 3, mgraphics);
                    return;
                case 21:
                case 22:
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    frameImage.drawFrameEffectSkill((i4 * this.mImageBullet[i]) + (this.f % i4), i2 + 3, i3 + 3, this.mXoayBullet[i], 3, mgraphics);
                    return;
                default:
                    return;
            }
        }
    }

    public void setSendMove(int i, int i2, int i3, int i4) {
        int i5;
        int tile = GameCanvas.loadmap.getTile(i2, i4);
        if (tile == 1 || tile == -1) {
            this.objKill.toX = i2;
            this.objKill.toY = i4;
            this.objKill.x = i2;
            this.objKill.y = i4;
            removeEff();
            return;
        }
        if (this.objKill == null || this.objKill != GameScreen.player) {
            this.objKill.toX = i2;
            this.objKill.toY = i4;
            this.objKill.x = i2;
            this.objKill.y = i4;
            return;
        }
        int i6 = 0;
        int i7 = 0;
        int abs = CRes.abs(i - i2);
        int abs2 = CRes.abs(i3 - i4);
        if (abs >= 4) {
            if (i > i2) {
                i6 = -4;
            } else {
                i6 = 4;
            }
        }
        if (abs2 >= 4) {
            if (i3 > i4) {
                i7 = -4;
            } else {
                i7 = 4;
            }
        }
        int i8 = abs / 4;
        int i9 = abs2 / 4;
        if (abs > abs2) {
            i5 = i8;
        } else {
            i5 = i9;
        }
        for (int i10 = 0; i10 < i5; i10++) {
            if (i10 < i8) {
                i += i6;
            }
            if (i10 < i9) {
                i3 += i7;
            }
        }
        this.objKill.toX = i2;
        this.objKill.toY = i4;
        this.objKill.x = this.savex;
        this.objKill.y = this.savey;
        this.objKill.xStand = i2;
        this.objKill.yStand = i4;
        this.objKill.xFire = i2;
        this.objKill.yFire = i4;
    }

    public static int setAddEffKillPlus(int[] iArr, MainObject mainObject, MainObject mainObject2, int[] iArr2) {
        int i = -1;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            switch (iArr[i2]) {
                case 1:
                    GameScreen.addEffectEndKill(29, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2));
                    if (mainObject == GameScreen.player) {
                        GameScreen.addEffectNum(T.dcxuyengiap, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), 7);
                        i = 7;
                        break;
                    } else {
                        GameScreen.addEffectNum(T.dcxuyengiap, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), 6);
                        break;
                    }
                case 2:
                case 3:
                    GameScreen.addEffectKillSubTime(41, mainObject2.ID, mainObject2.typeObject, mainObject.ID, mainObject.typeObject, iArr2[i2], mainObject.hp, (byte) (iArr[i2] - 2), ObjectData.NPC);
                    GameScreen.addEffectNum(new StringBuffer().append("-").append(iArr2[i2]).toString(), mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), iArr[i2] == 2 ? 3 : 4);
                    break;
                case 4:
                    GameScreen.addEffectKill(6, mainObject2.ID, mainObject2.typeObject, mainObject2.ID, mainObject2.typeObject, iArr2[i2], mainObject.hp);
                    if (mainObject == GameScreen.player) {
                        GameScreen.addEffectNum(T.dcchimang, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), 7);
                        i = 7;
                        break;
                    } else {
                        GameScreen.addEffectNum(T.dcchimang, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), 6);
                        break;
                    }
                case 5:
                    GameScreen.addEffectKill(67, mainObject2.ID, mainObject2.typeObject, mainObject.ID, mainObject.typeObject, iArr2[i2], mainObject.hp);
                    if (mainObject2 == GameScreen.player) {
                        GameScreen.addEffectNum(T.phandon, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), 7);
                    } else {
                        GameScreen.addEffectNum(T.phandon, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), 6);
                    }
                    if (mainObject.hp <= 0 && mainObject.Action != 4) {
                        mainObject.resetAction();
                        mainObject.Action = 4;
                        mainObject.timedie = GameCanvas.timeNow;
                        GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                        break;
                    }
                    break;
            }
        }
        return i;
    }

    public static boolean isMultiTarget(int i) {
        return i == 93 || i == 99 || i == 95 || i == 96 || i == 97 || i == 98 || i == 108 || i == 113 || i == 104 || i == 114 || i == 115;
    }

    private void update_Boss_De2() {
        if (this.objBeKillMain != null) {
            if (this.f == 1) {
                LoadMap.timeVibrateScreen = 103;
                GameScreen.addEffectEndKill(9, this.objBeKillMain.x, this.objBeKillMain.y - 2);
                GameScreen.addEffectEndKill_Time(47, this.objBeKillMain.x, this.objBeKillMain.y, this.objBeKillMain.timeBind);
            }
            if (!this.objBeKillMain.isBinded || (this.objBeKillMain != null && this.objBeKillMain.isRemove)) {
                this.isRemove = true;
            }
        }
    }

    private void update_Boss_Medusa2() {
        if (this.objBeKillMain != null) {
            if (this.objBeKillMain.Action == 4 || !this.objBeKillMain.isDongBang || this.objBeKillMain == null || (this.objBeKillMain != null && this.objBeKillMain.isRemove)) {
                this.isRemove = true;
            }
        }
    }

    private void update_Boss_De1() {
        for (int i = 0; i < this.VecEff.size(); i++) {
            Point point = (Point) this.VecEff.elementAt(i);
            if (this.objBeKillMain == null) {
                removeEff();
                return;
            }
            int i2 = this.objBeKillMain.x - point.x;
            int i3 = (this.objBeKillMain.y - (this.objBeKillMain.hOne >> 1)) - point.y;
            this.life++;
            if ((CRes.abs(i2) < 16 && CRes.abs(i3) < 16) || this.life > this.fRemove) {
                removeEff();
                GameScreen.addEffectEndKill(45, this.objBeKillMain.x, this.objBeKillMain.y - 20);
                return;
            }
            int angle = CRes.angle(i2, i3);
            if (Math.abs(angle - this.gocT_Arc) < 90 || (i2 * i2) + (i3 * i3) > 4096) {
                if (Math.abs(angle - this.gocT_Arc) < 15) {
                    this.gocT_Arc = angle;
                } else if ((angle - this.gocT_Arc >= 0 && angle - this.gocT_Arc < 180) || angle - this.gocT_Arc < -180) {
                    this.gocT_Arc = CRes.fixangle(this.gocT_Arc + 15);
                } else {
                    this.gocT_Arc = CRes.fixangle(this.gocT_Arc - 15);
                }
            }
            if (!this.isSpeedUp && this.va < 8192) {
                this.va += 2048;
            }
            this.vX1000 = (this.va * CRes.cos(this.gocT_Arc)) >> 10;
            this.vY1000 = (this.va * CRes.sin(this.gocT_Arc)) >> 10;
            int i4 = i2 + this.vX1000;
            point.x += i4 >> 10;
            int i5 = i4 & 1023;
            int i6 = i3 + this.vY1000;
            point.y += i6 >> 10;
            int i7 = i6 & 1023;
            if (this.f % 2 == 0) {
                GameScreen.addEffectEndKill(9, point.x, point.y);
                GameScreen.addEffectEndKill(46, point.x, point.y - 2);
            }
            point.f++;
        }
        if (this.f >= this.fRemove) {
            removeEff();
        }
    }

    @Override // GameEffect.MainEffect
    public void setTimeRemove(short s) {
        this.timeRemove = s;
        this.x = this.objBeKillMain.x;
        this.y = this.objBeKillMain.y;
        this.objBeKillMain.vx = 0;
        this.objBeKillMain.vy = 0;
        this.objBeKillMain.toX = this.x;
        this.objBeKillMain.toY = this.y;
        if (this.typeEffect == 101) {
            this.objBeKillMain.isSleep = true;
            this.objBeKillMain.timeSleep = mSystem.currentTimeMillis() + (s * ObjectData.MONSTER);
            return;
        }
        if (this.typeEffect == 102) {
            this.objBeKillMain.isStun = true;
            this.objBeKillMain.timeStun = mSystem.currentTimeMillis() + (s * ObjectData.MONSTER);
            return;
        }
        if (this.typeEffect == 107) {
            this.objBeKillMain.timeno = mSystem.currentTimeMillis() + (s * ObjectData.MONSTER);
            this.objBeKillMain.isno = true;
        } else if (this.typeEffect == 103) {
            this.objBeKillMain.isnoBoss84 = true;
            this.objBeKillMain.timenoBoss84 = mSystem.currentTimeMillis() + (s * ObjectData.MONSTER);
        } else if (this.typeEffect == 100) {
            this.objBeKillMain.isDongBang = true;
            this.objBeKillMain.timeDongBang = mSystem.currentTimeMillis() + (s * ObjectData.MONSTER);
        } else {
            this.objBeKillMain.isBinded = true;
            this.objBeKillMain.timeBind = mSystem.currentTimeMillis() + (s * ObjectData.MONSTER);
        }
    }

    @Override // GameEffect.MainEffect
    public void setObjFrom(short s, byte b) {
        this.objKill = MainObject.get_Object(s, b);
    }
}
