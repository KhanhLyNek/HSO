package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectAuto;
import GameEffect.EffectCharWearing;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.PopupChat;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.MainImage;
import Model.T;
import Skill.ListSkill;
import Skill.Monster_Skill;
import Skill.SplashSkill;
import Thread_More.LoadMap;
import java.util.Enumeration;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/MainObject.class */
public class MainObject extends AvMain {
    public static final byte DIR_UP = 1;
    public static final byte DIR_DOWN = 0;
    public static final byte DIR_LEFT = 2;
    public static final byte DIR_RIGHT = 3;
    public static final byte AC_STAND = 0;
    public static final byte AC_MOVE = 1;
    public static final byte AC_FIRE = 2;
    public static final byte AC_HIT = 3;
    public static final byte AC_DIE = 4;
    public static final byte CAT_PLAYER = 0;
    public static final byte CAT_MONSTER = 1;
    public static final byte CAT_NPC = 2;
    public static final byte CAT_ITEM = 3;
    public static final byte CAT_POTION = 4;
    public static final byte CAT_QUEST_ITEM = 5;
    public static final byte CAT_ITEM_TOC = 6;
    public static final byte CAT_MATERIAL = 7;
    public static final byte CAT_ICON_CLAN = 8;
    public static final byte CAT_PET = 9;
    public static final byte CAT_MOUNT = 10;
    public static final byte SPEC_NORMAL = 0;
    public static final byte SPEC_MONSTER = 1;
    public static final byte KIEMSI = 0;
    public static final byte SONGKIEM = 1;
    public static final byte PHAPSU = 2;
    public static final byte XATHU = 3;
    public static final byte TEM_ANIMAL = 0;
    public static final byte TEM_ITEM = 1;
    public static final byte TEM_ALL = -1;
    public static final byte TEM_NPC = 2;
    public static final byte TEM_PLAYER = 3;
    public static final byte MON_NOR = 0;
    public static final byte MON_BOSS = 1;
    public static final byte MON_CAPCHAR = 2;
    public static final byte MON_PHOBANG = 3;
    public static final byte BOSS_PHOBANG = 4;
    public static final byte CUCDA_BANGHOI = 5;
    public static final byte MON_NHANBANG = 6;
    public static final byte CHARMONSTER = -126;
    public static final byte MONSTERARENA = -125;
    public static final byte newMONSTER = 92;
    public short idMatna;
    public short idPhiPhong;
    public short idWeaPon;
    public short idHorse;
    public short idHair;
    public short idWing;
    public short idName;
    public short idBody;
    public short idLeg;
    public short idBienhinh;
    public static final byte isThucLui = Byte.MAX_VALUE;
    public long timedie;
    public EffectAuto effAuto;
    public int x;
    public int y;
    public int f;
    public int vx;
    public int vy;
    public int wOne;
    public int hOne;
    public int toX;
    public int toY;
    public int wFocus;
    public int vMax;
    public int dy;
    public int dx;
    public int dyWater;
    public int dyKill;
    public int xFire;
    public int yFire;
    public int xsai;
    public int ysai;
    public int ySort;
    public int yEffAuto;
    int timeDyKill;
    int vDy;
    public int hp;
    public int maxHp;
    public int mp;
    public int maxMp;
    public int hpEffect;
    public int yjum;
    public int vjum;
    public byte clazz;
    public byte index;
    public short Lv;
    public short phantramLv;
    public short countCharStand;
    public short idImageHenshin;
    public long gold;
    public long coin;
    public int hang;
    public int idClanMoDa;
    int timeHuyKill;
    public boolean isTanHinh;
    public boolean isShowHP;
    public boolean isBinded;
    public boolean isDongBang;
    public boolean moveToBoss;
    public boolean isFootSnow;
    public long timeBind;
    public long timeSleep;
    public long timeStun;
    public long timeno;
    public long timenoBoss84;
    public long timeDongBang;
    public int timeTanHinh;
    public int timeStop;
    public int timeEye;
    public int EyeMain;
    public int endEye;
    public long timeRePlayerInfo;
    public int hat;
    public int head;
    public int body;
    public int leg;
    public int eye;
    public int hair;
    public int wing;
    public int pet;
    public int frame;
    public int frameLeg;
    byte[] A_Stand;
    public byte[] A_Move;
    public static byte framefocus;
    public int xStand;
    public int yStand;
    public int countSendMove;
    public int xStopMove;
    public int yStopMove;
    int frameDie;
    public int typeNPC;
    public byte isPerson;
    public boolean isObject;
    public byte colorName;
    public int ID;
    public byte typeObject;
    public byte typePk;
    public byte typeOnline;
    public byte typeBoss;
    public byte typeSpec;
    public int KillFire;
    public short pointPk;
    public String name;
    public String nameGiaotiep;
    public String infoObject;
    public String strChatPopup;
    public int Action;
    public int Direction;
    public int PrevDir;
    public int countAutoMove;
    public int timeFreeMove;
    public boolean isRemove;
    public boolean isRemoveObjFocus;
    boolean isDirMove;
    public boolean isStop;
    public boolean iscuop;
    public short[] posTransRoad;
    public MainClan myClan;
    public int fplash;
    public int IDAttack;
    public mVector vecObjFocusSkill;
    public int imageId;
    public int IdBigAvatar;
    public boolean isRunAttack;
    public boolean isWater;
    public boolean isInfo;
    public boolean isParty;
    public long timeLoadInfo;
    public static mImage focus;
    public static mImage newfocus;
    public static mImage shadow;
    public static mImage shadow1;
    public static mImage water;
    public mVector vecBuff;
    public SplashSkill PlashNow;
    public ListSkill ListKillNow;
    public int typeMonster;
    public boolean isDie;
    public PopupChat chat;
    public boolean ispaintArena;
    public boolean isjum;
    public int timeStand;
    public int timeCapchar;
    public Monster_Skill skillDefault;
    public boolean isMonPhoBangDie;
    public boolean overMP;
    public boolean overHP;
    public boolean isPhiHanh;
    public static byte countmp;
    public mVector vecEffauto;
    public mVector veclowEffauto;
    public mVector vecEffectCharWearing;
    public mVector vecDataSkillEff;
    public int coutEff;
    public boolean isSend;
    public int timeGet;
    public int timeReveice;
    public byte numFrame;
    public boolean isDetonateInDest;
    public boolean isMove;
    public boolean isServerControl;
    public short[] idPartFashion;
    public static short[] idMaterialHopNguyenLieu;
    public short markKiller;
    public boolean isPaint_No;
    public boolean isStun;
    public boolean isSleep;
    public boolean isno;
    public boolean isnoBoss84;
    public static final byte TYPE_PK_THUONG_GIA = 11;
    public static final byte TYPE_PK_CUOP = 12;
    public static final byte TYPE_PK_HIEP_SY = 13;
    public byte isBot;
    public byte StepMovebocap;
    public boolean Namearena;
    public boolean useShip;
    public boolean canFocusMon;
    public byte typefocus;
    public byte typefire;
    public static final byte CAN_FOCUS = 1;
    public static final byte CAN_NOT_FOCUS = 0;
    public static final byte CAN_FIRE = 1;
    public static final byte CAN_NOT_FIRE = 0;
    public byte frameClan;
    int[][][] mAction;
    int mjum;
    int range;
    public byte FrameBody;
    public byte FrameLeg;
    public byte FrameBienhinh;
    public int FramePP;
    public byte FrameHair;
    public byte FrameWing;
    public byte FrameName;
    public int frameThuCuoi;
    public int Fhorse;
    public byte FrameMatNa;
    public byte FrameWP;
    public boolean paintMatnaTruocNon;
    byte ffire;
    public int time;
    public int idLight;
    public int weapon_frame;
    public int weaponType;
    static final byte[] hwp;
    static final byte[] fixY;
    public int frNo;
    public int[] frameNo;
    public boolean isMoveOut;
    public int xMoveOut;
    public int yMoveOut;
    public int dyMount;
    public int xMount;
    public int yMount;
    public byte typeMount;
    public byte frameMount;
    public byte fMount;
    public static final byte NGUA_NAU = 0;
    public static final byte NGUA_TRANG = 1;
    public static final byte NGUA_CHIENGIAP = 2;
    public static final byte NGUA_DO = 3;
    public static final byte NGUA_DEN = 4;
    public static byte[] ArrMount;
    public static byte[] ArrMount1;
    public short[][] arrayGemKham;
    public byte[] slotGem;
    public FrameImage weaponEff;
    public static FrameImage[] weaponEff_Gem;
    int timeEff;
    int dxEff;
    int dyEff;
    byte fweapon;
    byte timeShow;
    byte[] transEff;
    byte[][] eff_dx;
    byte[][] eff_dy;
    public static byte hideEff = 0;
    public static int Wfc = 0;
    public static int Hfc = 0;
    public static String[] strHelpNPC = null;
    public static byte StepHelpServer = 0;
    public static mImage imgCapchar = null;
    public static mVector vecCapchar = new mVector("MainObject vecCapchar");
    public static String strCapchar = "";
    public static byte[] frameicon = {0, 1, 2, 1};
    public static byte[][] mTypePartPaintPlayer = {new byte[]{6, 0, 1, 2, 5, 4, 3, 6}, new byte[]{6, 0, 1, 2, 5, 4, 3, 6}, new byte[]{6, 0, 1, 2, 5, 4, 3, 6}, new byte[]{6, 0, 1, 2, 5, 4, 3, 6}};
    public static mHashtable ALL_EFF_MAT_NA = new mHashtable();
    public static byte[] horseMove = {2, 2, 2, 3, 3, 4, 4, 4};
    public static WeaponInfo[][][] imgWeapone = new WeaponInfo[4];

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v16, types: [GameObjects.WeaponInfo[][], GameObjects.WeaponInfo[][][]] */
    static {
        for (int i = 0; i < imgWeapone.length; i++) {
            imgWeapone[i] = new WeaponInfo[40];
            for (int i2 = 0; i2 < imgWeapone[i].length; i2++) {
                imgWeapone[i][i2] = new WeaponInfo[8];
            }
        }
        hwp = new byte[]{24, 21, 19, 13};
        fixY = new byte[]{0, -1, 0, 0, 0};
        ArrMount = new byte[]{3};
        ArrMount1 = new byte[]{2, 2, 2, 0, 0, 3, 3, 3, 0, 0};
    }

    public static boolean isMaHopNguyenLieu(int i) {
        for (int i2 = 0; i2 < idMaterialHopNguyenLieu.length; i2++) {
            try {
                if (idMaterialHopNguyenLieu[i2] == i) {
                    return true;
                }
            } catch (Exception e) {
                mSystem.println("----Err mainobj:-- isMaHopNguyenLieu");
                return false;
            }
        }
        return false;
    }

    public String getnameOwner() {
        return "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v104, types: [short[], short[][]] */
    /* JADX WARN: Type inference failed for: r1v115, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r1v117, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r1v85, types: [int[][], int[][][]] */
    public MainObject() {
        this.idMatna = (short) -1;
        this.idPhiPhong = (short) -1;
        this.idWeaPon = (short) -1;
        this.idHorse = (short) -1;
        this.idHair = (short) -1;
        this.idWing = (short) -1;
        this.idName = (short) -1;
        this.idBody = (short) -1;
        this.idLeg = (short) -1;
        this.idBienhinh = (short) -1;
        this.effAuto = null;
        this.vDy = 0;
        this.countCharStand = (short) 0;
        this.idImageHenshin = (short) -1;
        this.hang = 0;
        this.timeHuyKill = 0;
        this.isTanHinh = false;
        this.isBinded = false;
        this.isDongBang = false;
        this.moveToBoss = false;
        this.isFootSnow = false;
        this.timeBind = 0L;
        this.timeSleep = 0L;
        this.timeStun = 0L;
        this.timeno = 0L;
        this.timenoBoss84 = 0L;
        this.timeDongBang = 0L;
        this.hat = 2;
        this.head = 1;
        this.body = 2;
        this.leg = 2;
        this.eye = 2;
        this.hair = 2;
        this.wing = -1;
        this.pet = -1;
        this.A_Stand = new byte[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        this.A_Move = new byte[]{2, 2, 2, 0, 0, 3, 3, 3, 0, 0};
        this.frameDie = 0;
        this.typeNPC = 0;
        this.isPerson = (byte) 0;
        this.isObject = false;
        this.typePk = (byte) -1;
        this.typeBoss = (byte) 0;
        this.KillFire = -1;
        this.pointPk = (short) 0;
        this.name = "";
        this.nameGiaotiep = T.giaotiep;
        this.infoObject = "";
        this.strChatPopup = null;
        this.countAutoMove = 0;
        this.timeFreeMove = 0;
        this.isDirMove = false;
        this.myClan = null;
        this.isRunAttack = false;
        this.isWater = false;
        this.isInfo = false;
        this.isParty = false;
        this.vecBuff = new mVector("MainObject vecBuff");
        this.isDie = false;
        this.timeStand = 0;
        this.timeCapchar = -1;
        this.isMonPhoBangDie = false;
        this.vecEffauto = new mVector("MainObject VecEffauto");
        this.veclowEffauto = new mVector("Low VecEffauto");
        this.vecEffectCharWearing = new mVector("Effect CharWearing");
        this.vecDataSkillEff = new mVector();
        this.timeGet = 0;
        this.timeReveice = 1500;
        this.isServerControl = false;
        this.idPartFashion = new short[]{-1, -1, -1, -1, -1, -1, -1};
        this.isPaint_No = false;
        this.isStun = false;
        this.isSleep = false;
        this.isno = false;
        this.isnoBoss84 = false;
        this.isBot = (byte) -1;
        this.StepMovebocap = (byte) -1;
        this.useShip = false;
        this.typefocus = (byte) 1;
        this.typefire = (byte) 0;
        this.frameClan = (byte) 0;
        this.mAction = new int[][]{new int[]{new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}}, new int[]{new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, new int[]{1, 1, 1, 0, 0, 0, 2, 2, 2}, new int[]{2, 2, 2, 0, 0, 0, 1, 1, 1}}, new int[]{new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3}, new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3}, new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3}}, new int[]{new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}}, new int[]{new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}}};
        this.Fhorse = 0;
        this.FrameMatNa = (byte) 0;
        this.FrameWP = (byte) 0;
        this.paintMatnaTruocNon = true;
        this.time = 0;
        this.weapon_frame = 0;
        this.weaponType = -1;
        this.frNo = 0;
        this.frameNo = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        this.isMoveOut = false;
        this.xMoveOut = 0;
        this.yMoveOut = 0;
        this.dyMount = 0;
        this.xMount = 0;
        this.yMount = 0;
        this.typeMount = (byte) -1;
        this.arrayGemKham = new short[12];
        this.slotGem = new byte[]{-1, -1, -1};
        this.timeEff = 0;
        this.dxEff = 0;
        this.dyEff = 0;
        this.fweapon = (byte) 0;
        this.timeShow = (byte) 8;
        this.transEff = new byte[]{2, 0, 2, 2};
        this.eff_dx = new byte[]{new byte[]{-2, 4, 2, -10}, new byte[]{-2, 4, 2, -10}};
        this.eff_dy = new byte[]{new byte[]{6, 5, 5, 2}, new byte[]{6, 5, 5, 2}};
    }

    public boolean isMainChar() {
        return false;
    }

    public boolean isMonstervantieu() {
        return false;
    }

    public void setspeedVantieu(int i) {
    }

    public boolean isMonsterVantieuTrang() {
        return false;
    }

    public boolean isMonsterVantieuDen() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v104, types: [short[], short[][]] */
    /* JADX WARN: Type inference failed for: r1v115, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r1v117, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r1v85, types: [int[][], int[][][]] */
    public MainObject(int i, byte b, String str, int i2, int i3) {
        this.idMatna = (short) -1;
        this.idPhiPhong = (short) -1;
        this.idWeaPon = (short) -1;
        this.idHorse = (short) -1;
        this.idHair = (short) -1;
        this.idWing = (short) -1;
        this.idName = (short) -1;
        this.idBody = (short) -1;
        this.idLeg = (short) -1;
        this.idBienhinh = (short) -1;
        this.effAuto = null;
        this.vDy = 0;
        this.countCharStand = (short) 0;
        this.idImageHenshin = (short) -1;
        this.hang = 0;
        this.timeHuyKill = 0;
        this.isTanHinh = false;
        this.isBinded = false;
        this.isDongBang = false;
        this.moveToBoss = false;
        this.isFootSnow = false;
        this.timeBind = 0L;
        this.timeSleep = 0L;
        this.timeStun = 0L;
        this.timeno = 0L;
        this.timenoBoss84 = 0L;
        this.timeDongBang = 0L;
        this.hat = 2;
        this.head = 1;
        this.body = 2;
        this.leg = 2;
        this.eye = 2;
        this.hair = 2;
        this.wing = -1;
        this.pet = -1;
        this.A_Stand = new byte[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        this.A_Move = new byte[]{2, 2, 2, 0, 0, 3, 3, 3, 0, 0};
        this.frameDie = 0;
        this.typeNPC = 0;
        this.isPerson = (byte) 0;
        this.isObject = false;
        this.typePk = (byte) -1;
        this.typeBoss = (byte) 0;
        this.KillFire = -1;
        this.pointPk = (short) 0;
        this.name = "";
        this.nameGiaotiep = T.giaotiep;
        this.infoObject = "";
        this.strChatPopup = null;
        this.countAutoMove = 0;
        this.timeFreeMove = 0;
        this.isDirMove = false;
        this.myClan = null;
        this.isRunAttack = false;
        this.isWater = false;
        this.isInfo = false;
        this.isParty = false;
        this.vecBuff = new mVector("MainObject vecBuff");
        this.isDie = false;
        this.timeStand = 0;
        this.timeCapchar = -1;
        this.isMonPhoBangDie = false;
        this.vecEffauto = new mVector("MainObject VecEffauto");
        this.veclowEffauto = new mVector("Low VecEffauto");
        this.vecEffectCharWearing = new mVector("Effect CharWearing");
        this.vecDataSkillEff = new mVector();
        this.timeGet = 0;
        this.timeReveice = 1500;
        this.isServerControl = false;
        this.idPartFashion = new short[]{-1, -1, -1, -1, -1, -1, -1};
        this.isPaint_No = false;
        this.isStun = false;
        this.isSleep = false;
        this.isno = false;
        this.isnoBoss84 = false;
        this.isBot = (byte) -1;
        this.StepMovebocap = (byte) -1;
        this.useShip = false;
        this.typefocus = (byte) 1;
        this.typefire = (byte) 0;
        this.frameClan = (byte) 0;
        this.mAction = new int[][]{new int[]{new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}}, new int[]{new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, new int[]{1, 1, 1, 0, 0, 0, 2, 2, 2}, new int[]{2, 2, 2, 0, 0, 0, 1, 1, 1}}, new int[]{new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3}, new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3}, new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3}}, new int[]{new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}}, new int[]{new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}, new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4}}};
        this.Fhorse = 0;
        this.FrameMatNa = (byte) 0;
        this.FrameWP = (byte) 0;
        this.paintMatnaTruocNon = true;
        this.time = 0;
        this.weapon_frame = 0;
        this.weaponType = -1;
        this.frNo = 0;
        this.frameNo = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
        this.isMoveOut = false;
        this.xMoveOut = 0;
        this.yMoveOut = 0;
        this.dyMount = 0;
        this.xMount = 0;
        this.yMount = 0;
        this.typeMount = (byte) -1;
        this.arrayGemKham = new short[12];
        this.slotGem = new byte[]{-1, -1, -1};
        this.timeEff = 0;
        this.dxEff = 0;
        this.dyEff = 0;
        this.fweapon = (byte) 0;
        this.timeShow = (byte) 8;
        this.transEff = new byte[]{2, 0, 2, 2};
        this.eff_dx = new byte[]{new byte[]{-2, 4, 2, -10}, new byte[]{-2, 4, 2, -10}};
        this.eff_dy = new byte[]{new byte[]{6, 5, 5, 2}, new byte[]{6, 5, 5, 2}};
        this.ID = i;
        this.name = str;
        this.typeObject = b;
        this.x = i2;
        this.y = i3;
        this.toX = i2;
        this.toY = i3;
        this.Direction = 0;
    }

    public void setInfo(byte b, short s, short s2, byte b2, byte b3, byte b4, String str, String str2, short s3, short s4, byte b5, byte b6, byte[] bArr, byte[] bArr2, byte[] bArr3) {
    }

    public void setInfo(int i, int i2, int i3) {
        if (i2 < 8) {
            i2 += 8;
        }
        this.head = i;
        this.eye = i2;
        this.hair = i3;
        this.EyeMain = i2;
    }

    public void setEffectauto(int i, int i2, short s) {
    }

    public void addEffAutoFromsv(int i, int i2, int i3, int i4, int i5, int i6, int i7, byte[] bArr, byte b, long j, byte b2, int i8, int i9) {
        EffectAuto effectAuto = new EffectAuto(i, i2, i3, i4, i5, i6, i7, bArr, j, b2, i8, i9);
        if (b == 0) {
            for (int i10 = 0; i10 < this.vecEffauto.size(); i10++) {
                EffectAuto effectAuto2 = (EffectAuto) this.vecEffauto.elementAt(i10);
                if (effectAuto2 != null && effectAuto2.IDItem == i) {
                    this.vecEffauto.removeElement(effectAuto2);
                }
            }
            this.vecEffauto.addElement(effectAuto);
            return;
        }
        for (int i11 = 0; i11 < this.veclowEffauto.size(); i11++) {
            EffectAuto effectAuto3 = (EffectAuto) this.veclowEffauto.elementAt(i11);
            if (effectAuto3 != null && effectAuto3.IDItem == i) {
                this.veclowEffauto.removeElement(effectAuto3);
            }
        }
        this.veclowEffauto.addElement(effectAuto);
    }

    public void addeffAuto(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (isMonstervantieu()) {
            this.vecEffauto.removeAllElements();
        }
        for (int i8 = 0; i8 < this.vecEffauto.size(); i8++) {
            EffectAuto effectAuto = (EffectAuto) this.vecEffauto.elementAt(i8);
            if (effectAuto != null && effectAuto.IDItem == i) {
                this.vecEffauto.removeElement(effectAuto);
            }
        }
        this.vecEffauto.addElement(new EffectAuto(i, i2, i3, i4, i5, i6, i7));
    }

    public boolean isPkVantieu() {
        return this.typePk == 12 || this.typePk == 13 || this.typePk == 11;
    }

    public static void paintShadow(mGraphics mgraphics, int i, int i2, int i3) {
        mgraphics.drawRegion(shadow1, 0, 0, mImage.getImageWidth(shadow1.image), mImage.getImageHeight(shadow1.image), 0, i2, i3, 3, false);
    }

    public boolean isLuaThieng() {
        return false;
    }

    public void setWearingListChar(byte[] bArr) {
        try {
            if (bArr == null) {
                this.body = -1;
                this.leg = -1;
                this.hat = -1;
                this.wing = -1;
                this.hair = -1;
                this.eye = -1;
                this.head = -1;
                this.weaponType = -1;
                this.pet = -1;
            } else {
                this.body = bArr[0];
                this.leg = bArr[1];
                this.hat = bArr[2];
                this.wing = bArr[7];
                byte b = this.clazz;
                if (b == 3) {
                    b = 2;
                } else if (b == 2) {
                    b = 3;
                }
                this.weaponType = bArr[8 + b];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPainthit(byte b, boolean z) {
    }

    public void setWearingEquip(byte[] bArr) {
        if (bArr == null) {
            this.body = -1;
            this.leg = -1;
            this.hat = -1;
            this.wing = -1;
            this.weaponType = -1;
            this.pet = -1;
            return;
        }
        this.body = bArr[1];
        this.leg = bArr[7];
        this.hat = bArr[6];
        this.wing = bArr[10];
        this.weaponType = bArr[0];
        this.pet = bArr[5];
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
    }

    public void paintBuffLast(mGraphics mgraphics) {
        if (this.vecBuff == null) {
            return;
        }
        for (int size = this.vecBuff.size() - 1; size >= 0; size--) {
            MainBuff mainBuff = (MainBuff) this.vecBuff.elementAt(size);
            if (mainBuff != null && mainBuff.isPaintLast) {
                if (mainBuff.typeBuff == 4) {
                    mainBuff.paint(mgraphics, this.x + this.xsai, (this.y - this.hOne) + 5 + this.ysai);
                } else {
                    mainBuff.paint(mgraphics, this.x + this.xsai, this.y + this.ysai);
                }
            }
        }
    }

    public void paintBuffFirst(mGraphics mgraphics) {
        if (this.vecBuff == null) {
            return;
        }
        for (int size = this.vecBuff.size() - 1; size >= 0; size--) {
            MainBuff mainBuff = (MainBuff) this.vecBuff.elementAt(size);
            if (mainBuff != null && !mainBuff.isPaintLast) {
                if (mainBuff.typeBuff == 4) {
                    mainBuff.paint(mgraphics, this.x + this.xsai, (this.y - this.hOne) + 5 + this.ysai);
                } else {
                    mainBuff.paint(mgraphics, this.x + this.xsai, this.y + this.ysai);
                }
            }
        }
    }

    public void paintAvatarFocus(mGraphics mgraphics, int i, int i2) {
        if (this.head != -1) {
            CRes.getCharPartInfo(2, this.head).paint(mgraphics, i - 1, i2 + 23, 0, 0);
        }
        if (this.eye != -1) {
            CRes.getCharPartInfo(4, this.eye).paint(mgraphics, i - 1, i2 + 23, 0, 0);
        }
        if (this.hair != -1) {
            CRes.getCharPartInfo(5, this.hair).paint(mgraphics, i - 1, i2 + 23, 0, 0);
        }
        if (this.hat != -1) {
            CRes.getCharPartInfo(3, this.hat).paint(mgraphics, i - 1, i2 + 23, 0, 0);
        }
    }

    public String getNameAndClan(String str) {
        String str2 = this.name;
        if (this.myClan != null) {
            str2 = new StringBuffer().append(this.myClan.shortName).append(str).append(str2).toString();
        }
        return str2;
    }

    public void paintIconClan(mGraphics mgraphics, int i, int i2, int i3) {
        if (this.myClan != null) {
            MainImage imageIconClan = ObjectData.getImageIconClan(this.myClan.IdIcon);
            if (i3 == 2) {
                int width = (mFont.tahoma_7_white.getWidth(this.myClan.shortName) + 12) / 2;
                if (imageIconClan.img != null) {
                    if (mImage.getImageHeight(imageIconClan.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length = frameicon.length;
                            if (length == 0) {
                                length = 1;
                            }
                            this.frameClan = (byte) ((this.frameClan + 1) % length);
                        }
                        mgraphics.drawRegion(imageIconClan.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, (i - width) + 6, i2, 3, true);
                    } else {
                        mgraphics.drawImage(imageIconClan.img, (i - width) + 6, i2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect((i - width) + 6, i2 - 1, this.myClan.getEffChucVu(), 14, mgraphics, 0);
                }
                mFont.tahoma_7_white.drawString(mgraphics, this.myClan.shortName, (i - width) + 15, i2 - 6, 0, true);
                return;
            }
            if (i3 == 0) {
                if (imageIconClan.img != null) {
                    if (mImage.getImageHeight(imageIconClan.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length2 = frameicon.length;
                            if (length2 == 0) {
                                length2 = 1;
                            }
                            this.frameClan = (byte) ((this.frameClan + 1) % length2);
                        }
                        mgraphics.drawRegion(imageIconClan.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, i, i2, 3, true);
                    } else {
                        mgraphics.drawImage(imageIconClan.img, i, i2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect(i - 1, i2 - 1, this.myClan.getEffChucVu(), 14, mgraphics, 0);
                }
                mFont.tahoma_7b_white.drawString(mgraphics, this.myClan.shortName, i + 13, i2, 0, true);
                return;
            }
            if (i3 == -1) {
                if (imageIconClan.img != null) {
                    if (mImage.getImageHeight(imageIconClan.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length3 = frameicon.length;
                            if (length3 == 0) {
                                length3 = 1;
                            }
                            this.frameClan = (byte) ((this.frameClan + 1) % length3);
                        }
                        mgraphics.drawRegion(imageIconClan.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, i - 1, i2, 3, true);
                    } else {
                        mgraphics.drawImage(imageIconClan.img, i - 1, i2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect(i - 1, i2 - 1, this.myClan.getEffChucVu(), 14, mgraphics, 0);
                    return;
                }
                return;
            }
            if (i3 == -2) {
                int width2 = (mFont.tahoma_7_white.getWidth(new StringBuffer().append(this.myClan.shortName).append(" - ").append(this.myClan.name).toString()) + 15) / 2;
                if (imageIconClan.img != null) {
                    if (mImage.getImageHeight(imageIconClan.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length4 = frameicon.length;
                            if (length4 == 0) {
                                length4 = 1;
                            }
                            this.frameClan = (byte) ((this.frameClan + 1) % length4);
                        }
                        mgraphics.drawRegion(imageIconClan.img, 0, frameicon[this.frameClan] * 18, 18, 18, 0, (i - width2) + 7, i2, 3, true);
                    } else {
                        mgraphics.drawImage(imageIconClan.img, (i - width2) + 7, i2, 3, true);
                    }
                    Item.eff_UpLv.paintUpgradeEffect((i - width2) + 6, i2 - 1, this.myClan.getEffChucVu(), 14, mgraphics, 0);
                }
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(this.myClan.shortName).append(" - ").append(this.myClan.name).toString(), (i - width2) + 15, i2 - 6, 0, true);
            }
        }
    }

    public void paintObject(mGraphics mgraphics, int i) {
        MainImage imagePartNPC = ObjectData.getImagePartNPC((short) this.imageId);
        if (imagePartNPC.img != null) {
            this.hOne = mImage.getImageHeight(imagePartNPC.img.image) / this.numFrame;
            this.wOne = mImage.getImageWidth(imagePartNPC.img.image);
            mgraphics.drawRegion(imagePartNPC.img, 0, ((GameCanvas.gameTick / 7) % this.numFrame) * this.hOne, this.wOne, this.hOne, 0, this.x, this.y, 33, false);
        }
        if (GameScreen.ObjFocus == null || ((GameScreen.ObjFocus != null && this != GameScreen.ObjFocus) || PaintInfoGameScreen.isLevelPoint)) {
            paintName(mgraphics, 0);
        }
        int i2 = this.x;
        int i3 = (this.y - this.hOne) - 8;
        int i4 = 0;
        if (this.maxHp > 0) {
            if (this.hp > 0) {
                i4 = (this.hp * 50) / this.maxHp;
                if (i4 <= 0) {
                    i4 = 1;
                } else if (i4 > 50) {
                    i4 = 50;
                }
            }
            mgraphics.setColor(-8714722);
            mgraphics.fillRect((i2 - (this.wOne / 2)) + 10, i3, 50, 5, true);
            mgraphics.setColor(-579511);
            mgraphics.fillRect((i2 - (this.wOne / 2)) + 10, i3, i4, 5, true);
        }
    }

    public void jum() {
        this.isjum = true;
        this.yjum = 0;
        this.vjum = 10;
        this.mjum = 5;
        this.range = 72;
    }

    public void paintNameStore(mGraphics mgraphics, int i, int i2) {
    }

    public void painthidePlayer(mGraphics mgraphics, int i) {
        if (!this.isTanHinh) {
            if (Canpaint()) {
                if (this.Action == 4 && !this.isDongBang) {
                    mgraphics.drawImage(shadow, this.x + 1, this.y + 1, 3, true);
                    paintBuffFirst(mgraphics);
                    AvMain.fraPlayerDie.drawFrame(0, this.x + 1, (this.y - this.ysai) + 5, 0, 33, mgraphics);
                    if (this.effAuto != null) {
                        this.isDongBang = false;
                        this.timeDongBang = mSystem.currentTimeMillis();
                        GameScreen.addEffectEndKill(15, this.x, this.y);
                        this.effAuto = null;
                    }
                    if (this.frameDie == 0) {
                        mgraphics.drawImage(AvMain.imgEyeDie, this.x + 1, ((this.y - this.ysai) + 5) - 24, 17, true);
                    }
                } else if (!checktanghinh()) {
                    if (this.idImageHenshin == -1 && this.idHorse == -1) {
                        mgraphics.drawImage(shadow, this.x + 1, (this.y - this.ysai) + 2, 3, true);
                    }
                    paintBuffFirst(mgraphics);
                    paintEffauto_Low(mgraphics, this.x, this.y);
                    paintDataEff_Top(mgraphics, this.x, this.y);
                    boolean z = (this.Direction == 0 || this.Direction == 2 || this.Direction == 3) && ((this.clazz == 3 && this.frame != 4) || !(this.clazz == 3 || this.frame == 5));
                    if (this.idImageHenshin != -1) {
                        MainImage imagePartMonster = ObjectData.getImagePartMonster(this.idImageHenshin);
                        if (imagePartMonster.img != null) {
                            int i2 = this.Action;
                            if (i2 > this.mAction.length - 1) {
                                i2 = 0;
                            }
                            this.hOne = mImage.getImageHeight(imagePartMonster.img.image) / 3;
                            this.wOne = mImage.getImageWidth(imagePartMonster.img.image) / 2;
                            if (this.eye == 4 || this.eye == 2) {
                                i2 = 3;
                            }
                            int i3 = this.mAction[i2][this.Direction > 2 ? 2 : this.Direction][this.frame] * this.hOne;
                            mgraphics.drawRegion(imagePartMonster.img, (this.mAction[i2][this.Direction > 2 ? 2 : this.Direction][this.frame] / 3) * this.wOne, (this.mAction[i2][this.Direction > 2 ? 2 : this.Direction][this.frame] % 3) * this.hOne, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, (this.y - this.dy) + this.dyWater, 33, false);
                        }
                    } else {
                        if (z) {
                            try {
                                paintWeaponhideplayer(mgraphics, 0);
                            } catch (Exception e) {
                            }
                        }
                        boolean z2 = false;
                        for (int i4 = 0; i4 < mTypePartPaintPlayer[this.Direction].length; i4++) {
                            byte b = mTypePartPaintPlayer[this.Direction][i4];
                            if ((b != 6 && b != 4) || ((b == 6 && this.Direction == 1 && i4 == 7) || ((b == 6 && this.Direction != 1 && i4 == 0) || (b == 4 && this.Direction != 1)))) {
                                if (getTypeParthide(i4) >= 0) {
                                    if (this.typeMount == -1) {
                                        CRes.getCharPartInfo(b, getTypeParthide(i4)).paint(mgraphics, this.x, (((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum, this.Direction, this.frame);
                                    } else if (i4 != 1) {
                                        CRes.getCharPartInfo(b, getTypeParthide(i4)).paint(mgraphics, this.x, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, this.Direction, this.frame);
                                    } else {
                                        paintMount_Sau(mgraphics);
                                    }
                                } else if ((b == 0 || b == 1 || b == 2 || b == 4 || b == 5) && !z2) {
                                    z2 = true;
                                    setReInfo();
                                }
                            }
                        }
                        if (this.typeMount != -1) {
                            paintMount_Truoc(mgraphics);
                        }
                        if (!z) {
                            try {
                                paintWeaponhideplayer(mgraphics, 0);
                            } catch (Exception e2) {
                            }
                        }
                    }
                } else {
                    for (int i5 = 0; i5 < mTypePartPaintPlayer[this.Direction].length; i5++) {
                        byte b2 = mTypePartPaintPlayer[this.Direction][i5];
                        if (b2 == 4 && getTypeParthide(i5) >= 0) {
                            if (this.typeMount == -1) {
                                CRes.getCharPartInfo(b2, getTypeParthide(i5)).paint(mgraphics, this.x, (((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum, this.Direction, this.frame);
                            } else if (i5 != 1) {
                                CRes.getCharPartInfo(b2, getTypeParthide(i5)).paint(mgraphics, this.x, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, this.Direction, this.frame);
                            } else {
                                paintMount_Sau(mgraphics);
                            }
                        }
                    }
                }
                if (!checktanghinh()) {
                    paintDataEff_Bot(mgraphics, this.x, this.y);
                    if (!this.useShip && this.isWater && this.dy == 0) {
                        int i6 = 1;
                        if (this.Direction == 2) {
                            i6 = 1 + 2;
                        } else if (this.Direction == 3) {
                            i6 = 1 - 2;
                        }
                        mgraphics.drawRegion(water, 0, ((this.Action != 0 ? 2 : 0) * 17) + (((GameCanvas.gameTick / 2) % 2) * 17), 28, 17, 0, this.x + i6, ((this.y - this.ysai) - 2) + this.dyWater, 3, true);
                    }
                }
            }
            if (!checktanghinh()) {
                if ((PaintInfoGameScreen.isLevelPoint || this != GameScreen.ObjFocus) && i != -1) {
                    paintName(mgraphics, i);
                }
                paintBuffLast(mgraphics);
                paint_no(mgraphics);
                paintEffauto(mgraphics, this.x, this.y);
                if (this.hp > 0) {
                    paintDongBang(mgraphics);
                }
            }
        }
    }

    public int getFrameLeg() {
        if (this.Direction == 0) {
            return this.frame + (this.FrameLeg * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FrameLeg * 18);
        }
        return this.frame + 12 + (this.FrameLeg * 18);
    }

    public int getFrameBien() {
        if (this.Direction == 0) {
            return this.frame + (this.FrameBienhinh * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FrameBienhinh * 18);
        }
        return this.frame + 12 + (this.FrameBienhinh * 18);
    }

    public int getFrameBody() {
        if (this.Direction == 0) {
            return this.frame + (this.FrameBody * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FrameBody * 18);
        }
        return this.frame + 12 + (this.FrameBody * 18);
    }

    public int getFramePP() {
        if (this.Direction == 0) {
            return this.frame + (this.FramePP * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FramePP * 18);
        }
        return this.frame + 12 + (this.FramePP * 18);
    }

    public int getFrameWing() {
        if (this.Direction == 0) {
            return this.frame + (this.FrameWing * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FrameWing * 18);
        }
        return this.frame + 12 + (this.FrameWing * 18);
    }

    public int getFrameHair() {
        if (this.Direction == 0) {
            return this.frame + (this.FrameHair * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FrameHair * 18);
        }
        return this.frame + 12 + (this.FrameHair * 18);
    }

    public int getFrameWing_Wearing(int i) {
        return i + (this.FrameWing * 18);
    }

    public int getFrameHair_Wearing(int i) {
        return i + (this.FrameHair * 18);
    }

    public int getFrameBody_Wearing(int i) {
        return i + (this.FrameBody * 18);
    }

    public int getFrameLeg_Wearing(int i) {
        return i + (this.FrameLeg * 18);
    }

    public int getFrameHorse() {
        if (this.Direction == 0) {
            return this.Fhorse + (this.frameThuCuoi * 21);
        }
        if (this.Direction == 1) {
            return this.Fhorse + 7 + (this.frameThuCuoi * 21);
        }
        return this.Fhorse + 14 + (this.frameThuCuoi * 21);
    }

    public int getFramePP_Wearing(int i) {
        return i + (this.FramePP * 18);
    }

    public int getFrameMatNa_Wearing(int i) {
        return i + (this.FrameMatNa * 18);
    }

    public int getFrameWP_Wearing(int i) {
        return i + (this.FrameWP * 18);
    }

    public int getFrameMatNa() {
        if (this.Direction == 0) {
            return this.frame + (this.FrameMatNa * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FrameMatNa * 18);
        }
        return this.frame + 12 + (this.FrameMatNa * 18);
    }

    public int getFrameWP() {
        if (this.Direction == 0) {
            return this.frame + (this.FrameWP * 18);
        }
        if (this.Direction == 1) {
            return (this.Direction * 6) + this.frame + (this.FrameWP * 18);
        }
        return this.frame + 12 + (this.FrameWP * 18);
    }

    public static DataSkillEff getEffMatNa(int i) {
        if (i == -1) {
            return null;
        }
        DataSkillEff dataSkillEff = (DataSkillEff) ALL_EFF_MAT_NA.get(new StringBuffer().append(i).append("").toString());
        if (dataSkillEff == null) {
            dataSkillEff = new DataSkillEff(i);
            dataSkillEff.typRequestImg = 113;
            ALL_EFF_MAT_NA.put(new StringBuffer().append(i).append("").toString(), dataSkillEff);
            GameData.getImgIcon((short) (i + GameData.ID_START_SKILL), dataSkillEff.typRequestImg);
            dataSkillEff.timeRemove = (int) (mSystem.currentTimeMillis() / 1000);
        } else {
            dataSkillEff.timeRemove = GameCanvas.timeNow;
        }
        return dataSkillEff;
    }

    public static void SetRemove() {
        Enumeration keys = ALL_EFF_MAT_NA.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            DataSkillEff dataSkillEff = (DataSkillEff) ALL_EFF_MAT_NA.get(str);
            if ((GameCanvas.timeNow - dataSkillEff.timeRemove) / 1000 > 60) {
                if (dataSkillEff.img_pp != null) {
                    dataSkillEff.img_pp.clearData();
                }
                ALL_EFF_MAT_NA.remove(str);
            }
        }
    }

    void testQuayphim() {
        this.name = "";
        if (this.clazz == 2) {
            this.idPhiPhong = (short) 38;
            this.idWeaPon = (short) 53;
            this.hat = 92;
            this.leg = 91;
            this.body = 91;
            return;
        }
        if (this.clazz == 3) {
            this.idPhiPhong = (short) 39;
            this.idWeaPon = (short) 56;
            this.hat = 93;
            this.leg = 92;
            this.body = 92;
            return;
        }
        if (this.clazz == 1) {
            this.idPhiPhong = (short) 40;
            this.idWeaPon = (short) 55;
            this.hat = 91;
            this.leg = 90;
            this.body = 90;
            return;
        }
        this.idPhiPhong = (short) 41;
        this.idWeaPon = (short) 54;
        this.hat = 90;
        this.leg = 89;
        this.body = 89;
    }

    public void paintHorseUI(mGraphics mgraphics, int i, int i2) {
        DataSkillEff effMatNa = getEffMatNa(this.idHorse);
        if (effMatNa != null) {
            effMatNa.paintBottomHorse(mgraphics, i, i2, getFrameHorse(), 0);
            effMatNa.paintTopHorse(mgraphics, i, i2, getFrameHorse(), 0);
        }
    }

    public void paintPlayer(mGraphics mgraphics, int i) {
        DataSkillEff effMatNa;
        if (this.Action == 2 && this.idHorse != -1) {
            this.Fhorse = this.frame + 1;
        }
        if (!this.isTanHinh) {
            if (Canpaint()) {
                if (this.Action == 4 && !this.isDongBang) {
                    mgraphics.drawImage(shadow, this.x + 1, this.y + 1, 3, true);
                    paintBuffFirst(mgraphics);
                    AvMain.fraPlayerDie.drawFrame(0, this.x + 1, (this.y - this.ysai) + 5, 0, 33, mgraphics);
                    if (this.effAuto != null) {
                        this.isDongBang = false;
                        this.timeDongBang = mSystem.currentTimeMillis();
                        GameScreen.addEffectEndKill(15, this.x, this.y);
                        this.effAuto = null;
                    }
                    if (this.frameDie == 0) {
                        mgraphics.drawImage(AvMain.imgEyeDie, this.x + 1, ((this.y - this.ysai) + 5) - 24, 17, true);
                    }
                } else {
                    if (!checktanghinh()) {
                        if (this.idImageHenshin == -1 && this.idHorse == -1) {
                            mgraphics.drawImage(shadow, this.x + 1, (this.y - this.ysai) + 2, 3, true);
                        }
                        paintBuffFirst(mgraphics);
                        paintEffauto_Low(mgraphics, this.x, this.y);
                        paintDataEff_Top(mgraphics, this.x, this.y);
                        boolean z = (this.Direction == 0 || this.Direction == 2 || this.Direction == 3) && ((this.clazz == 3 && this.frame != 4) || !(this.clazz == 3 || this.frame == 5));
                        DataSkillEff effMatNa2 = getEffMatNa(this.idWeaPon);
                        if (GameCanvas.gameTick % 5 == 0 && effMatNa2 != null) {
                            int size = effMatNa2.listFrame.size() / 18;
                            if (size == 0) {
                                size = 1;
                            }
                            this.FrameWP = (byte) ((this.FrameWP + 1) % size);
                        }
                        DataSkillEff effMatNa3 = getEffMatNa(this.idHorse);
                        if (effMatNa3 != null) {
                            effMatNa3.paintBottomHorse(mgraphics, this.x + this.xMount, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum) + this.yMount, getFrameHorse(), this.Direction == 3 ? 2 : 0);
                        }
                        if (this.idImageHenshin != -1) {
                            MainImage imagePartMonster = ObjectData.getImagePartMonster(this.idImageHenshin);
                            if (imagePartMonster.img != null) {
                                int i2 = this.Action;
                                if (i2 > this.mAction.length - 1) {
                                    i2 = 0;
                                }
                                this.hOne = mImage.getImageHeight(imagePartMonster.img.image) / 3;
                                this.wOne = mImage.getImageWidth(imagePartMonster.img.image) / 2;
                                if (this.eye == 4 || this.eye == 2) {
                                    i2 = 3;
                                }
                                int i3 = this.mAction[i2][this.Direction > 2 ? 2 : this.Direction][this.frame] * this.hOne;
                                mgraphics.drawRegion(imagePartMonster.img, (this.mAction[i2][this.Direction > 2 ? 2 : this.Direction][this.frame] / 3) * this.wOne, (this.mAction[i2][this.Direction > 2 ? 2 : this.Direction][this.frame] % 3) * this.hOne, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, (this.y - this.dy) + this.dyWater, 33, false);
                            }
                        } else {
                            if (z) {
                                try {
                                    paintWeapon(mgraphics, 0);
                                    if (effMatNa2 != null && this.Action != 2) {
                                        effMatNa2.paintBottomWeaPon(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameWP(), this.Direction == 3 ? 2 : 0);
                                        effMatNa2.paintTopWeaPon(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameWP(), this.Direction == 3 ? 2 : 0);
                                    }
                                } catch (Exception e) {
                                }
                            }
                            boolean z2 = false;
                            DataSkillEff effMatNa4 = getEffMatNa(this.idPhiPhong);
                            DataSkillEff effMatNa5 = getEffMatNa(this.idHair);
                            DataSkillEff effMatNa6 = getEffMatNa(this.idBody);
                            DataSkillEff effMatNa7 = getEffMatNa(this.idLeg);
                            DataSkillEff effMatNa8 = getEffMatNa(this.idBienhinh);
                            if (GameCanvas.gameTick % 6 == 0 && effMatNa8 != null) {
                                int size2 = effMatNa8.listFrame.size() / 18;
                                if (size2 == 0) {
                                    size2 = 1;
                                }
                                this.FrameBienhinh = (byte) ((this.FrameBienhinh + 1) % size2);
                            }
                            if (GameCanvas.gameTick % 6 == 0 && effMatNa7 != null) {
                                int size3 = effMatNa7.listFrame.size() / 18;
                                if (size3 == 0) {
                                    size3 = 1;
                                }
                                this.FrameLeg = (byte) ((this.FrameLeg + 1) % size3);
                            }
                            if (GameCanvas.gameTick % 10 == 0 && effMatNa4 != null) {
                                int size4 = effMatNa4.listFrame.size() / 18;
                                if (size4 == 0) {
                                    size4 = 1;
                                }
                                this.FramePP = (byte) ((this.FramePP + 1) % size4);
                            }
                            if (GameCanvas.gameTick % 6 == 0 && effMatNa6 != null) {
                                int size5 = effMatNa6.listFrame.size() / 18;
                                if (size5 == 0) {
                                    size5 = 1;
                                }
                                this.FrameBody = (byte) ((this.FrameBody + 1) % size5);
                            }
                            if (GameCanvas.gameTick % 6 == 0 && effMatNa5 != null) {
                                int size6 = effMatNa5.listFrame.size() / 18;
                                if (size6 == 0) {
                                    size6 = 1;
                                }
                                this.FrameHair = (byte) ((this.FrameHair + 1) % size6);
                            }
                            DataSkillEff effMatNa9 = getEffMatNa(this.idWing);
                            if (GameCanvas.gameTick % 6 == 0 && effMatNa9 != null) {
                                int size7 = effMatNa9.listFrame.size() / 18;
                                if (size7 == 0) {
                                    size7 = 1;
                                }
                                this.FrameWing = (byte) ((this.FrameWing + 1) % size7);
                            }
                            if (this.Direction != 1 && effMatNa4 != null) {
                                effMatNa4.paintBottomPP(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFramePP(), this.Direction == 3 ? 2 : 0);
                            }
                            DataSkillEff effMatNa10 = getEffMatNa(this.idMatna);
                            if (GameCanvas.gameTick % 5 == 0 && effMatNa10 != null) {
                                int size8 = effMatNa10.listFrame.size() / 18;
                                if (size8 == 0) {
                                    size8 = 1;
                                }
                                this.FrameMatNa = (byte) ((this.FrameMatNa + 1) % size8);
                            }
                            if (effMatNa8 != null && this.idBienhinh != -1) {
                                effMatNa8.paintBottomAll(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameBody(), this.Direction == 3 ? 2 : 0);
                                effMatNa8.paintTopAll(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameBody(), this.Direction == 3 ? 2 : 0);
                            }
                            for (int i4 = 0; i4 < mTypePartPaintPlayer[this.Direction].length; i4++) {
                                byte b = mTypePartPaintPlayer[this.Direction][i4];
                                if (b == -1) {
                                    if (effMatNa10 != null) {
                                        effMatNa10.paintBottom(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                    }
                                } else {
                                    if (this.Direction == 1) {
                                        if (effMatNa4 != null) {
                                            effMatNa4.paintBottomPP(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFramePP(), this.Direction == 3 ? 2 : 0);
                                            effMatNa4.paintTopPP(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFramePP(), this.Direction == 3 ? 2 : 0);
                                        }
                                        if (b == 2 && effMatNa10 != null) {
                                            effMatNa10.paintBottom(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                            effMatNa10.paintTop(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                        }
                                    } else if (b == 4 && effMatNa10 != null && this.paintMatnaTruocNon) {
                                        effMatNa10.paintBottom(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                        effMatNa10.paintTop(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                    }
                                    if ((b != 6 && b != 4) || ((b == 6 && this.Direction == 1 && i4 == 7) || ((b == 6 && this.Direction != 1 && i4 == 0) || (b == 4 && this.Direction != 1)))) {
                                        if (b == 6 && effMatNa9 != null && this.idWing != -1) {
                                            effMatNa9.paintBottomWing(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameWing(), this.Direction == 3 ? 2 : 0);
                                            effMatNa9.paintTopWing(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameWing(), this.Direction == 3 ? 2 : 0);
                                        } else if (this.idBienhinh == -1 || effMatNa8 == null || (b != 1 && b != 0 && b != 3 && b != 5 && b != 2)) {
                                            if (b == 1 && this.idBody != -1 && effMatNa6 != null) {
                                                effMatNa6.paintBottomAll(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameBody(), this.Direction == 3 ? 2 : 0);
                                                effMatNa6.paintTopAll(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameBody(), this.Direction == 3 ? 2 : 0);
                                            } else if (b == 0 && this.idLeg != -1 && effMatNa7 != null) {
                                                effMatNa7.paintBottomAll(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameLeg(), this.Direction == 3 ? 2 : 0);
                                                effMatNa7.paintTopAll(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameLeg(), this.Direction == 3 ? 2 : 0);
                                            } else if (b == 5 && this.idHair != -1 && effMatNa5 != null) {
                                                effMatNa5.paintBottomHair(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameHair(), this.Direction == 3 ? 2 : 0);
                                                effMatNa5.paintTopHair(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameHair(), this.Direction == 3 ? 2 : 0);
                                            } else if (getTypePart(i4) >= 0) {
                                                if (this.typeMount == -1) {
                                                    if (b == 6 && (this.Direction == 0 || this.Direction == 2 || this.Direction == 3)) {
                                                        CRes.getCharPartInfo(b, getTypePart(i4)).paint(mgraphics, this.x, (((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum, this.Direction, this.frame);
                                                        if (effMatNa4 != null) {
                                                            effMatNa4.paintBottomPP(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFramePP(), this.Direction == 3 ? 2 : 0);
                                                            effMatNa4.paintTopPP(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFramePP(), this.Direction == 3 ? 2 : 0);
                                                        }
                                                    } else {
                                                        CRes.getCharPartInfo(b, getTypePart(i4)).paint(mgraphics, this.x, (((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum, this.Direction, this.frame);
                                                    }
                                                } else if (i4 != 1) {
                                                    if (b == 6 && (this.Direction == 0 || this.Direction == 2 || this.Direction == 3)) {
                                                        CRes.getCharPartInfo(b, getTypePart(i4)).paint(mgraphics, this.x, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, this.Direction, this.frame);
                                                        if (effMatNa4 != null) {
                                                            effMatNa4.paintBottomPP(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFramePP(), this.Direction == 3 ? 2 : 0);
                                                            effMatNa4.paintTopPP(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFramePP(), this.Direction == 3 ? 2 : 0);
                                                        }
                                                    } else {
                                                        CRes.getCharPartInfo(b, getTypePart(i4)).paint(mgraphics, this.x, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, this.Direction, this.frame);
                                                    }
                                                } else {
                                                    paintMount_Sau(mgraphics);
                                                }
                                            } else if ((b == 0 || b == 1 || b == 2 || b == 4 || b == 5) && !z2) {
                                                z2 = true;
                                                setReInfo();
                                            }
                                        }
                                    }
                                    if (b == 3 && effMatNa10 != null && !this.paintMatnaTruocNon && this.Direction != 1) {
                                        effMatNa10.paintBottom(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                        effMatNa10.paintTop(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameMatNa(), this.Direction == 3 ? 2 : 0);
                                    }
                                }
                            }
                            if (this.Direction != 1 && effMatNa4 != null) {
                                effMatNa4.paintTopPP(mgraphics, this.x, this.y + (this.typeMount != -1 ? -this.dyMount : 0) + this.dyWater, getFramePP(), this.Direction == 3 ? 2 : 0);
                            }
                            if (this.typeMount != -1) {
                                paintMount_Truoc(mgraphics);
                            }
                            if (!z) {
                                try {
                                    paintWeapon(mgraphics, 0);
                                    if (effMatNa2 != null && this.Action != 2) {
                                        effMatNa2.paintBottomWeaPon(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameWP(), this.Direction == 3 ? 2 : 0);
                                        effMatNa2.paintTopWeaPon(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, getFrameWP(), this.Direction == 3 ? 2 : 0);
                                    }
                                } catch (Exception e2) {
                                }
                            }
                        }
                    } else {
                        for (int i5 = 0; i5 < mTypePartPaintPlayer[this.Direction].length; i5++) {
                            byte b2 = mTypePartPaintPlayer[this.Direction][i5];
                            if (b2 == 4 && getTypePart(i5) >= 0) {
                                if (this.typeMount == -1) {
                                    CRes.getCharPartInfo(b2, getTypePart(i5)).paint(mgraphics, this.x, (((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum, this.Direction, this.frame);
                                } else if (i5 != 1) {
                                    CRes.getCharPartInfo(b2, getTypePart(i5)).paint(mgraphics, this.x, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, this.Direction, this.frame);
                                } else {
                                    paintMount_Sau(mgraphics);
                                }
                            }
                        }
                    }
                    DataSkillEff effMatNa11 = getEffMatNa(this.idHorse);
                    if (effMatNa11 != null) {
                        effMatNa11.paintTopHorse(mgraphics, this.x + this.xMount, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum) + this.yMount, getFrameHorse(), this.Direction == 3 ? 2 : 0);
                    }
                }
                if (!checktanghinh()) {
                    paintDataEff_Bot(mgraphics, this.x, this.y);
                    if (!this.useShip && this.isWater && this.dy == 0) {
                        int i6 = 1;
                        if (this.Direction == 2) {
                            i6 = 1 + 2;
                        } else if (this.Direction == 3) {
                            i6 = 1 - 2;
                        }
                        mgraphics.drawRegion(water, 0, ((this.Action != 0 ? 2 : 0) * 17) + (((GameCanvas.gameTick / 2) % 2) * 17), 28, 17, 0, this.x + i6, ((this.y - this.ysai) - 2) + this.dyWater, 3, true);
                    }
                }
            }
            if (!canpaintDataSkillEff()) {
                paintDataEff_Top(mgraphics, this.x, this.y);
                paintDataEff_Bot(mgraphics, this.x, this.y);
            }
            if (!checktanghinh() && (effMatNa = getEffMatNa(this.idName)) != null) {
                if (GameCanvas.gameTick % 5 == 0 && effMatNa != null) {
                    int size9 = effMatNa.listFrame.size();
                    if (size9 == 0) {
                        size9 = 1;
                    }
                    this.FrameName = (byte) ((this.FrameName + 1) % size9);
                }
                effMatNa.paintBottomName(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, this.FrameName, 0);
                effMatNa.paintTopName(mgraphics, this.x, ((this.y + (this.typeMount != -1 ? -this.dyMount : 0)) + this.dyWater) - this.yjum, this.FrameName, 0);
            }
            if (!checktanghinh()) {
                if ((PaintInfoGameScreen.isLevelPoint || this != GameScreen.ObjFocus) && i != -1) {
                    paintName(mgraphics, i);
                }
                paintBuffLast(mgraphics);
                paint_no(mgraphics);
                paintEffauto(mgraphics, this.x, this.y);
                if (this.hp > 0) {
                    paintDongBang(mgraphics);
                }
            }
        }
    }

    public void paintEffauto(mGraphics mgraphics, int i, int i2) {
        if (!isMainChar() && hideEff == 1) {
            return;
        }
        for (int i3 = 0; i3 < this.vecEffauto.size(); i3++) {
            EffectAuto effectAuto = (EffectAuto) this.vecEffauto.elementAt(i3);
            if (effectAuto != null) {
                effectAuto.paint(mgraphics, i, i2 - this.yEffAuto);
            }
        }
    }

    public void paintDataEff_Top(mGraphics mgraphics, int i, int i2) {
        int size;
        if ((isMainChar() || hideEff != 1) && (size = this.vecDataSkillEff.size()) > 0) {
            for (int i3 = 0; i3 < size; i3++) {
                DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i3);
                if (dataSkillEff != null) {
                    dataSkillEff.paintTop(mgraphics, i, i2);
                }
            }
        }
    }

    public void paintDataEff_Bot(mGraphics mgraphics, int i, int i2) {
        int size;
        if ((isMainChar() || hideEff != 1) && (size = this.vecDataSkillEff.size()) > 0) {
            for (int i3 = 0; i3 < size; i3++) {
                DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i3);
                if (dataSkillEff != null) {
                    dataSkillEff.paintBottom(mgraphics, i, i2);
                }
            }
        }
    }

    public void paintEffauto_Low(mGraphics mgraphics, int i, int i2) {
        if (!isMainChar() && hideEff == 1) {
            return;
        }
        for (int i3 = 0; i3 < this.veclowEffauto.size(); i3++) {
            EffectAuto effectAuto = (EffectAuto) this.veclowEffauto.elementAt(i3);
            if (effectAuto != null) {
                effectAuto.paint(mgraphics, i, i2 - this.yEffAuto);
            }
        }
    }

    public void updateEffauto() {
        for (int i = 0; i < this.vecEffauto.size(); i++) {
            EffectAuto effectAuto = (EffectAuto) this.vecEffauto.elementAt(i);
            if (effectAuto != null) {
                effectAuto.update();
                if (effectAuto.wantdestroy) {
                    this.vecEffauto.removeElement(effectAuto);
                }
            }
        }
        for (int i2 = 0; i2 < this.veclowEffauto.size(); i2++) {
            EffectAuto effectAuto2 = (EffectAuto) this.veclowEffauto.elementAt(i2);
            if (effectAuto2 != null) {
                effectAuto2.update();
                if (effectAuto2.wantdestroy) {
                    this.veclowEffauto.removeElement(effectAuto2);
                }
            }
        }
    }

    public boolean checktanghinh() {
        for (int i = 0; i < this.vecDataSkillEff.size(); i++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff != null && dataSkillEff.isTanghinhbyEffauto()) {
                return true;
            }
        }
        return false;
    }

    public boolean Canpaint() {
        int size = this.vecEffauto.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                EffectAuto effectAuto = (EffectAuto) this.vecEffauto.elementAt(i);
                if (effectAuto != null && effectAuto.CanpaintByeffauto()) {
                    return false;
                }
            }
        }
        for (int i2 = 0; i2 < this.vecDataSkillEff.size(); i2++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i2);
            if (dataSkillEff != null && dataSkillEff.CanpaintByeffauto()) {
                return false;
            }
        }
        return true;
    }

    public boolean canpaintDataSkillEff() {
        for (int i = 0; i < this.vecDataSkillEff.size(); i++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i);
            if (dataSkillEff != null && dataSkillEff.CanpaintByeffauto()) {
                return false;
            }
        }
        return true;
    }

    public boolean Canmove() {
        if (this.isSleep || this.isStun || this.isno || this.isDongBang) {
            return false;
        }
        int size = this.vecEffauto.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                EffectAuto effectAuto = (EffectAuto) this.vecEffauto.elementAt(i);
                if (effectAuto != null && effectAuto.lockmoveByeffAuto()) {
                    return false;
                }
            }
        }
        if (this.veclowEffauto.size() > 0) {
            for (int i2 = 0; i2 < this.veclowEffauto.size(); i2++) {
                EffectAuto effectAuto2 = (EffectAuto) this.veclowEffauto.elementAt(i2);
                if (effectAuto2 != null && effectAuto2.lockmoveByeffAuto()) {
                    return false;
                }
            }
        }
        for (int i3 = 0; i3 < this.vecDataSkillEff.size(); i3++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i3);
            if (dataSkillEff != null && dataSkillEff.lockmoveByeffAuto()) {
                return false;
            }
        }
        return true;
    }

    public void paintDongBang(mGraphics mgraphics) {
        if (this.isDongBang && this.Action != 4 && this.isDongBang && this.effAuto != null) {
            this.effAuto.paint(mgraphics);
        }
    }

    public int getTypePart(int i) {
        switch (mTypePartPaintPlayer[this.Direction][i]) {
            case 0:
                return this.idPartFashion[1] != -1 ? this.idPartFashion[1] : this.leg;
            case 1:
                return this.idPartFashion[0] != -1 ? this.idPartFashion[0] : this.body;
            case 2:
                if (this.idPartFashion.length > 6) {
                    return this.idPartFashion[6] != -1 ? this.idPartFashion[6] : this.head;
                }
                return this.head;
            case 3:
                if (isHairNotHat()) {
                    return -1;
                }
                return this.idPartFashion[2] != -1 ? this.idPartFashion[2] : this.hat;
            case 4:
                return this.eye;
            case 5:
                return this.idPartFashion[5] != -1 ? this.idPartFashion[5] : this.hair;
            case 6:
                return this.idPartFashion[4] != -1 ? this.idPartFashion[4] : this.wing;
            default:
                return -1;
        }
    }

    public int getTypeParthide(int i) {
        switch (mTypePartPaintPlayer[this.Direction][i]) {
            case 0:
                return this.clazz;
            case 1:
                return this.clazz;
            case 2:
                return 0;
            case 3:
                return -1;
            case 4:
                return this.clazz + 8;
            case 5:
                return this.clazz;
            case 6:
                return -1;
            default:
                return -1;
        }
    }

    public void setReInfo() {
        if ((GameCanvas.timeNow - this.timeRePlayerInfo) / 1000 > 60) {
            GlobalService.gI().char_info((short) this.ID);
            this.timeRePlayerInfo = GameCanvas.timeNow;
        }
    }

    public void paintShowPlayer(mGraphics mgraphics, int i, int i2, int i3) {
        int i4 = (GameCanvas.gameTick / 6) % 2;
        DataSkillEff effMatNa = getEffMatNa(this.idPhiPhong);
        DataSkillEff effMatNa2 = getEffMatNa(this.idHair);
        DataSkillEff effMatNa3 = getEffMatNa(this.idBody);
        DataSkillEff effMatNa4 = getEffMatNa(this.idWing);
        DataSkillEff effMatNa5 = getEffMatNa(this.idLeg);
        if (GameCanvas.gameTick % 6 == 0 && effMatNa4 != null) {
            int size = effMatNa4.listFrame.size() / 18;
            if (size == 0) {
                size = 1;
            }
            this.FrameWing = (byte) ((this.FrameWing + 1) % size);
        }
        if (GameCanvas.gameTick % 10 == 0 && effMatNa != null) {
            int size2 = effMatNa.listFrame.size() / 18;
            if (size2 == 0) {
                size2 = 1;
            }
            this.FramePP = (byte) ((this.FramePP + 1) % size2);
        }
        if (effMatNa != null && this.wing == -1 && this.idPartFashion[4] == -1) {
            effMatNa.paintBottomPP(mgraphics, i, i2, getFramePP_Wearing(i4), 0);
            effMatNa.paintTopPP(mgraphics, i, i2, getFramePP_Wearing(i4), 0);
        }
        DataSkillEff effMatNa6 = getEffMatNa(this.idMatna);
        DataSkillEff effMatNa7 = getEffMatNa(this.idWeaPon);
        if (GameCanvas.gameTick % 5 == 0 && effMatNa6 != null) {
            int size3 = effMatNa6.listFrame.size() / 18;
            if (size3 == 0) {
                size3 = 1;
            }
            this.FrameMatNa = (byte) ((this.FrameMatNa + 1) % size3);
        }
        if (GameCanvas.gameTick % 6 == 0 && effMatNa2 != null) {
            int size4 = effMatNa2.listFrame.size() / 18;
            if (size4 == 0) {
                size4 = 1;
            }
            this.FrameHair = (byte) ((this.FrameHair + 1) % size4);
        }
        if (GameCanvas.gameTick % 6 == 0 && effMatNa3 != null) {
            int size5 = effMatNa3.listFrame.size() / 18;
            if (size5 == 0) {
                size5 = 1;
            }
            this.FrameBody = (byte) ((this.FrameBody + 1) % size5);
        }
        if (GameCanvas.gameTick % 6 == 0 && effMatNa5 != null) {
            int size6 = effMatNa5.listFrame.size() / 18;
            if (size6 == 0) {
                size6 = 1;
            }
            this.FrameLeg = (byte) ((this.FrameLeg + 1) % size6);
        }
        if (effMatNa7 != null) {
            effMatNa7.paintBottomWeaPon(mgraphics, i, i2, getFrameWP_Wearing(i4), 0);
            effMatNa7.paintTopWeaPon(mgraphics, i, i2, getFrameWP_Wearing(i4), 0);
        }
        mgraphics.drawImage(shadow, i + 1, i2 + 2, 3, true);
        int i5 = this.weaponType;
        if (i5 != -1 && CRes.loadImgWeaPone(this.clazz, i5, 0) != null) {
            WeaponInfo loadImgWeaPone = CRes.loadImgWeaPone(this.clazz, i5, 0);
            if (loadImgWeaPone.img != null) {
                mgraphics.drawRegion(loadImgWeaPone.img, loadImgWeaPone.mRegion[i3][0], 0, loadImgWeaPone.mRegion[i3][1], loadImgWeaPone.himg, 0, i + loadImgWeaPone.mPos[i3][i4][0], i2 + loadImgWeaPone.mPos[i3][i4][1], 0, true);
            }
        }
        paintDataEff_Top(mgraphics, i, i2);
        for (int i6 = 0; i6 < mTypePartPaintPlayer[this.Direction].length - 1; i6++) {
            byte b = mTypePartPaintPlayer[0][i6];
            if (b == -1 && effMatNa6 != null) {
                effMatNa6.paintBottom(mgraphics, i, i2 + 2, getFrameMatNa_Wearing(i4), 0);
            }
            if (b == 5 && this.idHair != -1 && effMatNa2 != null) {
                effMatNa2.paintBottomHair(mgraphics, i, i2 + 2, getFrameHair_Wearing(i4), 0);
                effMatNa2.paintTopHair(mgraphics, i, i2 + 2, getFrameHair_Wearing(i4), 0);
            } else if (b == 1 && this.idBody != -1 && effMatNa3 != null) {
                effMatNa3.paintBottomAll(mgraphics, i, i2 + 2, getFrameBody_Wearing(i4), 0);
                effMatNa3.paintTopAll(mgraphics, i, i2 + 2, getFrameBody_Wearing(i4), 0);
            } else if (b == 0 && this.idLeg != -1 && effMatNa5 != null) {
                effMatNa5.paintBottomAll(mgraphics, i, i2 + 2, getFrameLeg_Wearing(i4), 0);
                effMatNa5.paintTopAll(mgraphics, i, i2 + 2, getFrameLeg_Wearing(i4), 0);
            } else if (b == 6 && this.idWing != -1 && effMatNa4 != null) {
                effMatNa4.paintBottomWing(mgraphics, i, i2 + 2, getFrameWing_Wearing(i4), 0);
                effMatNa4.paintTopWing(mgraphics, i, i2 + 2, getFrameWing_Wearing(i4), 0);
            } else if (getTypePart(i6) > -1) {
                if (b == 6 && (this.wing != -1 || this.idPartFashion[4] != -1)) {
                    CRes.getCharPartInfo(b, getTypePart(i6)).paint(mgraphics, i, i2, i3, i4);
                    if (effMatNa != null) {
                        effMatNa.paintBottomPP(mgraphics, i, i2, getFramePP_Wearing(i4), 0);
                        effMatNa.paintTopPP(mgraphics, i, i2, getFramePP_Wearing(i4), 0);
                    }
                } else {
                    CRes.getCharPartInfo(b, getTypePart(i6)).paint(mgraphics, i, i2, i3, i4);
                }
            }
        }
        if (effMatNa6 != null) {
            effMatNa6.paintBottom(mgraphics, i, i2 + 2, getFrameMatNa_Wearing(i4), 0);
            effMatNa6.paintTop(mgraphics, i, i2 + 2, getFrameMatNa_Wearing(i4), 0);
        }
        paintDataEff_Bot(mgraphics, i, i2);
    }

    public void paintShowHairPlayer(mGraphics mgraphics, int i, int i2, int i3) {
        int i4 = (GameCanvas.gameTick / 6) % 2;
        mgraphics.drawImage(shadow, i + 1, i2 + 2, 3, false);
        int i5 = this.weaponType;
        if (i5 != -1 && CRes.loadImgWeaPone(this.clazz, i5, 0) != null) {
            WeaponInfo loadImgWeaPone = CRes.loadImgWeaPone(this.clazz, i5, 0);
            if (loadImgWeaPone.img != null) {
                mgraphics.drawRegion(loadImgWeaPone.img, loadImgWeaPone.mRegion[this.Direction][0], 0, loadImgWeaPone.mRegion[this.Direction][1], loadImgWeaPone.himg, 0, i + loadImgWeaPone.mPos[this.Direction][i4][0], i2 + loadImgWeaPone.mPos[this.Direction][i4][1], 0, false);
            }
        }
        for (int i6 = 0; i6 < mTypePartPaintPlayer[this.Direction].length - 1; i6++) {
            byte b = mTypePartPaintPlayer[this.Direction][i6];
            if (b == 5) {
                if (i3 != -1) {
                    CRes.getCharPartInfo(5, i3).paint(mgraphics, i, i2, 0, i4);
                }
            } else if (getTypePart(i6) > -1) {
                CRes.getCharPartInfo(b, getTypePart(i6)).paint(mgraphics, i, i2, 0, i4);
            }
        }
    }

    public void paintBigAvatar(mGraphics mgraphics, int i, int i2) {
    }

    public void paintName(mGraphics mgraphics, int i) {
        if (GameScreen.infoGame.isMapThachdau()) {
            return;
        }
        String str = this.name;
        mFont mfont = mFont.tahoma_7_white;
        mFont textColor = MainTabNew.setTextColor(i);
        boolean z = true;
        int i2 = 0;
        if (this.typeSpec == 1) {
            if (this.typePk > 0) {
                z = false;
            }
            i2 = 5;
        }
        if (this.idName != -1) {
            z = false;
        }
        int i3 = 18;
        if (PaintInfoGameScreen.isLevelPoint) {
            i3 = 12;
        }
        if (this.typeMonster == 7) {
            i3 += 8;
        }
        if (this.isObject && PaintInfoGameScreen.isLevelPoint) {
            i3 += 6;
        }
        if (z) {
            textColor.drawString(mgraphics, str, this.x, (((((((this.y - this.ysai) - this.dy) + this.dyWater) - (this.isDongBang ? 5 : 0)) - this.hOne) - i3) - this.dyMount) - this.yjum, 2, false);
        }
        if (this.typeObject == 0 && this.typeSpec == 1 && z && !this.iscuop) {
            i3 += 10;
            textColor.drawString(mgraphics, T.nhanban, this.x, (((((((this.y - this.ysai) - this.dy) + this.dyWater) - (this.isDongBang ? 5 : 0)) - this.hOne) - i3) - this.dyMount) - this.yjum, 2, false);
        }
        if (this.typeObject == 2 && this.chat == null) {
            AvMain.fraQuest.drawFrame(this.typeNPC, this.x - 6, (((((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - i3) - 18) - 4) + ((GameCanvas.gameTick / 2) % 4), 0, mgraphics);
        }
        int i4 = 0;
        if ((Player.party != null && this.isParty) || this.isShowHP || this.typeMonster == 7) {
            int i5 = 44;
            if (this.typeObject == 2 || this.typeMonster == 7) {
                i5 = this.hOne + 5;
            }
            mgraphics.setColor(-8714722);
            mgraphics.fillRect(this.x - 20, ((((this.y - this.ysai) - this.dy) + this.dyWater) - i5) - i3, 40, 3, false);
            mgraphics.setColor(-579511);
            mgraphics.fillRect(this.x - 20, ((((this.y - this.ysai) - this.dy) + this.dyWater) - i5) - i3, (40 * this.hp) / this.maxHp, 3, false);
            i4 = 0 + 5;
        }
        if (this.myClan != null && this.typeSpec != 1) {
            paintIconClan(mgraphics, this.x - 1, ((((((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - i3) - 8) - i4) - this.dyMount) - this.yjum, 2);
            i4 += 16;
        }
        if (this.typePk >= 0 && this.typeObject == 0 && !isPkVantieu()) {
            AvMain.fraPk.drawFrame((this.typePk * 3) + ((GameCanvas.gameTick / 3) % 3), this.x, ((((((((this.y - this.dy) + this.dyWater) - this.ysai) - (i4 + 59)) + 18) - i3) + i2) - this.dyMount) - this.yjum, 0, 33, mgraphics);
        }
    }

    public void paintNameShow(mGraphics mgraphics, int i, int i2, boolean z) {
        String nameAndClan = getNameAndClan(" - ");
        if (this.myClan != null) {
            i += 16;
            paintIconClan(mgraphics, i - 8, i2 + 6, -1);
        }
        mFont.tahoma_7b_white.drawString(mgraphics, nameAndClan, i, (i2 - this.dyMount) - this.yjum, 0, true);
    }

    public void paintNameFocus(mGraphics mgraphics) {
        if (!GameScreen.infoGame.isMapThachdau() && GameScreen.ObjFocus != null && !PaintInfoGameScreen.isLevelPoint) {
            MainObject mainObject = GameScreen.ObjFocus;
            int i = mainObject.x;
            int i2 = (((mainObject.y - mainObject.ysai) - mainObject.dy) + mainObject.dyWater) - mainObject.hOne;
            int i3 = 18;
            boolean z = true;
            int i4 = 0;
            if (this.typeSpec == 1) {
                if (this.typePk > 0) {
                    z = false;
                }
                i4 = 5;
            }
            mFont mfont = mFont.tahoma_7b_white;
            if (this.typeObject == 3) {
                mfont = MainTabNew.setTextColorName(this.colorName);
            }
            if (this.typeObject == 2 && this.chat == null) {
                AvMain.fraQuest.drawFrame(this.typeNPC, i - 6, (((i2 - 18) - 18) - 4) + ((GameCanvas.gameTick / 2) % 4), 0, mgraphics);
            }
            if (this.idName == -1) {
                mfont.drawString(mgraphics, new StringBuffer().append(this.name).append("").toString(), i, (((i2 - 18) - this.dyMount) - (this.isDongBang ? 5 : 0)) - this.yjum, 2, false);
            }
            int i5 = 0;
            if (this.typeObject == 0 && this.typeSpec == 1 && z && !this.iscuop) {
                i3 = 18 + 10;
                i5 = 0 + 10;
                mfont.drawString(mgraphics, T.nhanban, i, (((i2 - i3) - this.dyMount) - (this.isDongBang ? 5 : 0)) - this.yjum, 2, false);
            }
            if (Player.party != null && mainObject.isParty) {
                i5 = 5;
            }
            if (this.myClan != null && mainObject.typeSpec != 1) {
                paintIconClan(mgraphics, i, ((((i2 - i3) - 8) - i5) - this.dyMount) - this.yjum, 2);
                i5 += 16;
            }
            if (mainObject.typePk >= 0 && mainObject.typeObject == 0 && !isPkVantieu()) {
                AvMain.fraPk.drawFrame((this.typePk * 3) + ((GameCanvas.gameTick / 3) % 3), i, (((i2 - (i5 + 20)) + i4) - this.dyMount) - this.yjum, 0, 33, mgraphics);
            }
        }
    }

    public static void updatefocus() {
        if (GameScreen.ObjFocus != null) {
            if (GameScreen.ObjFocus.maxHp > 0 && !PaintInfoGameScreen.isLevelPoint && GameScreen.ObjFocus.typeObject != 10) {
                framefocus = (byte) ((GameScreen.ObjFocus.hp * 10) / GameScreen.ObjFocus.maxHp);
                if (framefocus > 9) {
                    framefocus = (byte) 9;
                }
            }
            framefocus = (byte) CRes.abs(framefocus - 9);
            if (GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 4) {
                framefocus = (byte) 0;
            }
        }
    }

    public static void paintFocus(mGraphics mgraphics) {
        if (GameScreen.ObjFocus != null && !PaintInfoGameScreen.isLevelPoint && GameScreen.ObjFocus.typeObject != 10) {
            MainObject mainObject = GameScreen.ObjFocus;
            int i = 1;
            if (mainObject.typeObject == 1) {
                if (!mainObject.canFocusMon) {
                    return;
                }
                i = 4;
                if (mainObject.typeMonster == 3 || mainObject.typeMonster == 5 || mainObject.typeMonster == 10 || mainObject.typeMonster == 8) {
                    i = 12;
                }
            }
            if (Wfc == 0) {
                Wfc = mImage.getImageWidth(newfocus.image);
                Hfc = mImage.getImageHeight(newfocus.image) / 10;
            }
            mgraphics.drawRegion(newfocus, 0, framefocus * Hfc, Wfc, Hfc, 0, mainObject.x, ((((((mainObject.y - mainObject.hOne) - mainObject.dy) + mainObject.dyWater) - i) - (GameCanvas.gameTick % 7)) - mainObject.dyMount) - mainObject.yjum, 3, false);
            if ((Player.party != null && mainObject.isParty) || mainObject.isShowHP) {
                int i2 = 64;
                if (mainObject.typeObject == 2) {
                    i2 = mainObject.hOne + 23;
                }
                mgraphics.setColor(-8714722);
                mgraphics.fillRect(mainObject.x - 20, ((mainObject.y - i2) - mainObject.dyMount) - mainObject.yjum, 40, 3, false);
                mgraphics.setColor(-579511);
                mgraphics.fillRect(mainObject.x - 20, ((mainObject.y - i2) - mainObject.dyMount) - mainObject.yjum, (40 * mainObject.hp) / mainObject.maxHp, 3, false);
            }
        }
    }

    public static void paintHPQuai(mGraphics mgraphics, int i, int i2, int i3) {
        mgraphics.setColor(0);
        mgraphics.fillRect(i - 13, i2 - 3, 26, 5, true);
        mgraphics.setColor(-1);
        mgraphics.fillRect(i - 12, i2 - 2, 24, 3, true);
        mgraphics.setColor(-4052663);
        mgraphics.fillRect(i - 12, i2 - 2, i3, 3, true);
    }

    public void updateDataEffect() {
        int size = this.vecDataSkillEff.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i);
                if (dataSkillEff != null) {
                    dataSkillEff.update();
                    if (dataSkillEff.wantDetroy) {
                        this.vecDataSkillEff.removeElement(dataSkillEff);
                    }
                }
            }
        }
    }

    @Override // Model.AvMain
    public void update() {
        this.x += this.vx;
        this.y += this.vy;
        if (this.isjum) {
            this.yjum += this.vjum;
            this.vjum += this.mjum;
            if (this.yjum > this.range) {
                this.yjum = this.range;
                this.vjum = -5;
                this.mjum = -2;
            }
            if (this.yjum <= 0) {
                this.yjum = 0;
                this.isjum = false;
            }
        }
        if (this.isMoveOut) {
            move_to_point();
        }
        updateDy();
        if (this.chat != null) {
            this.chat.updatePos(this.x, (this.y - this.hOne) - 30);
            if ((this != GameScreen.player || GameScreen.player.currentQuest == null) && this.chat.setOff()) {
                this.chat = null;
            }
        }
        if (this.isTanHinh) {
            this.timeTanHinh++;
            if (this.timeTanHinh > 40) {
                this.timeTanHinh = 0;
                this.isTanHinh = false;
            }
        }
        int i = 0;
        while (i < this.vecBuff.size()) {
            MainBuff mainBuff = (MainBuff) this.vecBuff.elementAt(i);
            mainBuff.update();
            if (mainBuff.isRemove) {
                mainBuff.clearData();
                this.vecBuff.removeElement(mainBuff);
                i--;
            }
            i++;
        }
        if (this.isSend) {
            this.timeGet++;
            if (this.timeGet > 40) {
                this.isSend = false;
                this.timeGet = 0;
            }
        }
        if (this.strChatPopup != null) {
            addChat(this.strChatPopup, true);
            this.strChatPopup = null;
        }
        if (this.myClan != null && this.myClan.isRemove) {
            this.myClan = null;
        }
        if (this.isSleep) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            this.eye = 0;
            if (this.isDie || this.timeSleep - mSystem.currentTimeMillis() < 0) {
                this.isSleep = false;
                this.timeSleep = mSystem.currentTimeMillis();
            }
        }
        if (this.isStun) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.isDie || this.timeStun - mSystem.currentTimeMillis() < 0) {
                this.isStun = false;
                this.timeStun = mSystem.currentTimeMillis();
            }
        }
        if (this.isDongBang) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.effAuto == null) {
                this.effAuto = new EffectAuto(51, this.x, this.y, 0, 0, 0, 0);
            }
            if (this.effAuto != null) {
                this.effAuto.update();
            }
            if (this.isDie || this.timeDongBang - mSystem.currentTimeMillis() < 0) {
                this.isDongBang = false;
                this.timeDongBang = mSystem.currentTimeMillis();
                GameScreen.addEffectEndKill(15, this.x, this.y);
                this.effAuto = null;
            }
        }
        if (this.isBinded) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.isDie || this.timeBind - mSystem.currentTimeMillis() < 0) {
                this.isBinded = false;
                this.timeBind = mSystem.currentTimeMillis();
            }
        }
        if (this.isno) {
            this.vx = 0;
            this.vy = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (this.isDie || this.timeno - mSystem.currentTimeMillis() < 0) {
                this.isno = false;
                this.timeno = mSystem.currentTimeMillis();
            }
        }
        if (this.isnoBoss84 && (this.isDie || (this.timenoBoss84 - mSystem.currentTimeMillis()) / 1000 <= 0)) {
            this.isnoBoss84 = false;
            this.timenoBoss84 = mSystem.currentTimeMillis();
        }
        if (isNPC() && this.Direction != 0) {
            this.Direction = 0;
        }
        if (this.typeMount == -1 && this.dyMount != 0) {
            resetMount();
        }
        updateMount();
        updateEffauto();
        updatefocus();
        updateEffectWeapon();
    }

    public void updateActionPerson() {
        if (this.Action == 4) {
            if (this.typeMount != -1) {
                this.typeMount = (byte) -1;
            }
            if (this.frameDie == 0) {
                if (CRes.random(20) == 0) {
                    this.frameDie = 1;
                }
            } else if (CRes.random(3) == 0) {
                this.frameDie = 0;
            }
            this.isDongBang = false;
            this.effAuto = null;
            if (!GameCanvas.lowGraphic && CRes.random(50) == 0) {
                GameScreen.addEffectKill(69, this.ID, this.typeObject, this.ID, this.typeObject, 0, 0);
                if (CRes.random(10) == 0) {
                    GameScreen.addEffectKill(69, this.ID, this.typeObject, this.ID, this.typeObject, 0, 0);
                    return;
                }
                return;
            }
            return;
        }
        switch (this.Action) {
            case 0:
                this.f++;
                if (this.f > this.A_Stand.length - 1) {
                    this.f = 0;
                }
                this.frame = this.A_Stand[this.f];
                this.Fhorse = this.frame;
                return;
            case 1:
                this.f++;
                if (this.idHorse != -1) {
                    if (this.f > horseMove.length - 1) {
                        this.f = 0;
                    }
                } else if (this.isPhiHanh) {
                    if (this.f > this.A_Stand.length - 1) {
                        this.f = 0;
                    }
                } else if (this.f > this.A_Move.length - 1) {
                    this.f = 0;
                }
                if (this.vx == 0 && this.vy == 0 && this.posTransRoad == null) {
                    this.Action = 0;
                    this.frameLeg = CRes.random(4);
                    this.f = 0;
                }
                if (this.idHorse != -1) {
                    this.Fhorse = horseMove[this.f];
                    this.frame = 3;
                } else if (this.isPhiHanh) {
                    this.frame = this.A_Stand[this.f];
                } else {
                    this.frame = this.A_Move[this.f];
                }
                if (isMainChar() && this.idHorse == 114 && !this.isWater && GameCanvas.gameTick % 5 == 0) {
                    GameScreen.addEffectEndKill_Direction(58, this.x, this.y + ((this.Direction == 2 || this.Direction == 3) ? 10 : 0), this.Direction, (byte) -1);
                }
                if (this.isFootSnow && !this.isWater && GameCanvas.gameTick % 5 == 0) {
                    GameScreen.addEffectEndKill_Direction(56, this.x, this.y, this.Direction, (byte) -1);
                    return;
                }
                return;
            case 2:
                if (this.PlashNow != null) {
                    this.ListKillNow.updateEffSkill();
                    this.PlashNow.update(this);
                }
                this.ffire = (byte) (this.ffire + 1);
                if (this.ffire > this.A_Stand.length - 1) {
                    this.ffire = (byte) 0;
                }
                this.Fhorse = this.A_Stand[this.ffire];
                return;
            default:
                return;
        }
    }

    public void updateDy() {
        if (this.dyKill > 0) {
            this.dy = this.dyKill;
            this.timeDyKill++;
            if (this.timeDyKill > 30) {
                this.dyKill = 30;
                return;
            }
            return;
        }
        if (this.dy > 0) {
            this.vDy -= 2;
            this.dy += this.vDy;
        }
        if (this.dy < 0) {
            this.dy = -this.dy;
            this.vDy = 0;
        }
        if (this.dy < 3) {
            this.dy = 0;
        }
        this.timeDyKill = 0;
    }

    public void updateEye() {
        if (this.hp < this.hpEffect) {
            this.eye = 3;
            return;
        }
        if (this.eye == 3) {
            this.eye = this.EyeMain;
        }
        if (this.eye != -1) {
            this.timeEye++;
            if (this.eye < 3 || this.eye == 4 || this.eye == 5) {
                if (this.eye == 4 && this.timeEye > 2) {
                    this.eye = 5;
                }
                if (this.eye == 2) {
                    if (this.timeEye >= 8) {
                        this.timeEye = 0;
                        this.eye = this.EyeMain;
                        if (this.clazz < 2) {
                            this.endEye = CRes.random(30, 80);
                            return;
                        } else {
                            this.endEye = CRes.random(10, 60);
                            return;
                        }
                    }
                    return;
                }
                if (this.timeEye >= 3) {
                    this.timeEye = 0;
                    this.eye = this.EyeMain;
                    if (this.clazz < 2) {
                        this.endEye = CRes.random(30, 80);
                        return;
                    } else {
                        this.endEye = CRes.random(10, 60);
                        return;
                    }
                }
                return;
            }
            if (this.timeEye >= this.endEye) {
                this.timeEye = 0;
                this.eye = 0;
            }
        }
    }

    public void move_to_XY() {
        if (!Canmove() || this.isBinded) {
            this.toX = this.x;
            this.toY = this.y;
            return;
        }
        if (this.isMoveOut) {
            return;
        }
        if (CRes.abs(this.x - this.toX) > this.vMax + getVmount()) {
            this.vy = 0;
            this.Action = 1;
            if (CRes.abs(this.x - this.toX) > this.vMax + getVmount()) {
                if (this.x > this.toX) {
                    this.vx = -(this.vMax + getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 2;
                    return;
                } else {
                    this.vx = this.vMax + getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 3;
                    return;
                }
            }
            this.vx = this.toX - this.x;
            return;
        }
        if (CRes.abs(this.y - this.toY) > this.vMax + getVmount()) {
            this.vx = 0;
            this.Action = 1;
            if (CRes.abs(this.y - this.toY) > this.vMax + getVmount()) {
                if (this.y > this.toY) {
                    this.vy = -(this.vMax + getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 1;
                    return;
                } else {
                    this.vy = this.vMax + getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 0;
                    return;
                }
            }
            this.vy = this.toY - this.y;
            return;
        }
        if (this.isDetonateInDest) {
            GameScreen.addEffectEndKill(43, this.x, this.y);
            LoadMap.timeVibrateScreen = 10;
            this.isStop = true;
        }
        this.vx = 0;
        this.vy = 0;
    }

    public static MainObject get_Object(int i, byte b) {
        for (int size = GameScreen.Vecplayers.size() - 1; size >= 0; size--) {
            MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(size);
            if (mainObject != null) {
                if (b == 2 && mainObject.getIDnpc() == i) {
                    return mainObject;
                }
                if (mainObject.typeObject == b && mainObject.ID == i) {
                    return mainObject;
                }
            }
        }
        return null;
    }

    public static MainObject get_Item_Object(int i, int i2) {
        for (int i3 = 0; i3 < GameScreen.Vecplayers.size(); i3++) {
            MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i3);
            if (mainObject.typeObject == i2 && mainObject.ID == i) {
                return mainObject;
            }
        }
        return null;
    }

    public static int getDistance(int i, int i2, int i3, int i4) {
        return getDistance(i - i3, i2 - i4);
    }

    public static int getDistance(int i, int i2) {
        return CRes.sqrt((i * i) + (i2 * i2));
    }

    public boolean isMountFly() {
        if (this.typeMount != -1 && MountTemplate.Arr_Fly != null && MountTemplate.Arr_Fly[this.typeMount] == 1) {
            return true;
        }
        return false;
    }

    public void setMove(boolean z) {
        boolean z2;
        int tile;
        if (this.isMoveOut) {
            return;
        }
        int tile2 = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        if (tile2 == 2 && !isMountFly()) {
            if (this.vy != 0) {
                if (this.vy > 0) {
                    this.vy = (this.vMax + getVmount()) - 1;
                } else {
                    this.vy = (-(this.vMax + getVmount())) + 1;
                }
            }
            if (this.vx != 0) {
                if (this.vx > 0) {
                    this.vx = (this.vMax + getVmount()) - 1;
                } else {
                    this.vx = (-(this.vMax + getVmount())) + 1;
                }
            }
            this.isWater = true;
            this.dyWater = 3;
        } else {
            this.isWater = false;
            this.dyWater = 0;
            if (tile2 == 1) {
                if (z) {
                    setAutoMoveNear();
                } else {
                    this.Action = 0;
                    this.frameLeg = CRes.random(4);
                    this.vx = 0;
                    this.vy = 0;
                }
            }
            if (GameCanvas.loadmap.getTile(this.x, this.y) == 2 && !isMountFly()) {
                this.isWater = true;
                this.dyWater = 3;
            }
        }
        if (this.vx == 0 && this.vy == 0) {
            int tile3 = GameCanvas.loadmap.getTile(this.x, this.y);
            if (tile3 == 1 || tile3 == -1) {
                int i = 24;
                int i2 = this.x * ObjectData.MONSTER;
                int i3 = this.y * ObjectData.MONSTER;
                int i4 = 0;
                do {
                    z2 = false;
                    int cos = i2 + (CRes.cos(i4) * i);
                    int sin = i3 + (CRes.sin(i4) * i);
                    if (cos >= 0 && sin >= 0 && ((tile = GameCanvas.loadmap.getTile(cos / ObjectData.MONSTER, sin / ObjectData.MONSTER)) == 0 || tile == 2)) {
                        this.x = cos / ObjectData.MONSTER;
                        this.y = sin / ObjectData.MONSTER;
                        resetAction();
                        z2 = true;
                    }
                    i4 += 44;
                    if (i4 >= 360) {
                        i4 = 0;
                        i += 24;
                    }
                } while (!z2);
            }
        }
    }

    public void setAutoMoveNear() {
        int index = GameCanvas.loadmap.getIndex(this.x + this.vx, this.y + this.vy);
        if (this.vy != 0) {
            if (index % GameCanvas.loadmap.mapW > 0 && ((GameCanvas.loadmap.getTile((this.x + this.vx) - LoadMap.wTile, this.y + this.vy) == 0 || GameCanvas.loadmap.getTile((this.x + this.vx) - LoadMap.wTile, this.y + this.vy) == 2) && (GameCanvas.loadmap.getTile((this.x + this.vx) - LoadMap.wTile, this.y) == 0 || GameCanvas.loadmap.getTile((this.x + this.vx) - LoadMap.wTile, this.y) == 2))) {
                this.vx = -(this.vMax + getVmount());
                this.Direction = 2;
                this.vy = 0;
            } else if (index % GameCanvas.loadmap.mapW < GameCanvas.loadmap.mapW - 1 && ((GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y + this.vy) == 0 || GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y + this.vy) == 2) && (GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y) == 0 || GameCanvas.loadmap.getTile(this.x + this.vx + LoadMap.wTile, this.y) == 2))) {
                this.vx = this.vMax + getVmount();
                this.Direction = 3;
                this.vy = 0;
            } else {
                this.vy = 0;
            }
        } else if (this.vx != 0) {
            if ((GameCanvas.loadmap.getTile(this.x + this.vx, (this.y + this.vy) - LoadMap.wTile) == 0 || GameCanvas.loadmap.getTile(this.x + this.vx, (this.y + this.vy) - LoadMap.wTile) == 2) && (GameCanvas.loadmap.getTile(this.x, (this.y + this.vy) - LoadMap.wTile) == 0 || GameCanvas.loadmap.getTile(this.x, (this.y + this.vy) - LoadMap.wTile) == 2)) {
                this.vy = -(this.vMax + getVmount());
                this.Direction = 1;
                this.vx = 0;
            } else if ((GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy + LoadMap.wTile) == 0 || GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy + LoadMap.wTile) == 2) && (GameCanvas.loadmap.getTile(this.x, this.y + this.vy + LoadMap.wTile) == 0 || GameCanvas.loadmap.getTile(this.x, this.y + this.vy + LoadMap.wTile) == 2)) {
                this.vy = this.vMax + getVmount();
                this.Direction = 0;
                this.vx = 0;
            } else {
                this.vx = 0;
            }
        }
        if (this.vx == 0 && this.vy == 0) {
            this.Action = 0;
            this.frameLeg = CRes.random(4);
        }
    }

    public void setSpeed(int i) {
        switch (this.Direction) {
            case 0:
                this.vy = i;
                this.vx = 0;
                return;
            case 1:
                this.vy = -i;
                this.vx = 0;
                return;
            case 2:
                this.vy = 0;
                this.vx = -i;
                return;
            case 3:
                this.vy = 0;
                this.vx = i;
                return;
            default:
                return;
        }
    }

    public void setSpeedInDirection(int i) {
        int random_Am_0 = CRes.random_Am_0(3);
        if (CRes.abs(random_Am_0) > 1) {
            i--;
        }
        switch (this.Direction) {
            case 0:
                this.vy = i;
                this.vx = random_Am_0;
                break;
            case 1:
                this.vy = -i;
                this.vx = random_Am_0;
                break;
            case 2:
                this.vy = random_Am_0;
                this.vx = -i;
                break;
            case 3:
                this.vy = random_Am_0;
                this.vx = i;
                break;
        }
        if (this.vx == 0 && CRes.random(3) == 0) {
            this.time = 0;
            this.Action = 0;
            this.vx = 0;
            this.vy = 0;
        }
        if (this.vx > 0) {
            this.Direction = 3;
        } else {
            this.Direction = 2;
        }
    }

    public boolean setIsInScreen(int i, int i2, int i3, int i4) {
        if (i < MainScreen.cameraMain.xCam - i3 || i > MainScreen.cameraMain.xCam + GameCanvas.w + i3 || i2 < MainScreen.cameraMain.yCam - (i4 / 2) || i2 > MainScreen.cameraMain.yCam + GameCanvas.h + ((i4 * 3) / 2)) {
            return false;
        }
        return true;
    }

    public static boolean isInScreen(MainObject mainObject) {
        if (mainObject.x < MainScreen.cameraMain.xCam - mainObject.wOne || mainObject.x > MainScreen.cameraMain.xCam + GameCanvas.w + mainObject.wOne || mainObject.y < MainScreen.cameraMain.yCam - mainObject.hOne || mainObject.y > MainScreen.cameraMain.yCam + GameCanvas.h + ((mainObject.hOne * 3) / 2)) {
            return false;
        }
        return true;
    }

    public void Move_to_Focus_Person() {
        int tile;
        if ((this.x == this.toX && this.y == this.toY) || this.isMoveOut) {
            return;
        }
        if (this.iscuop && ((tile = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy)) == 1 || tile == -1)) {
            this.isWater = false;
            this.dyWater = 0;
            this.vx = 0;
            this.vy = 0;
            this.x = this.toX;
            this.y = this.toY;
            this.timeFreeMove++;
            if (this.timeFreeMove >= 10) {
                this.timeFreeMove = 25;
            }
            GameScreen.addEffectEndKill(35, this.x, this.y - 20);
        }
        if (CRes.abs(this.x - this.toX) > this.vMax + getVmount() || CRes.abs(this.y - this.toY) > this.vMax + getVmount()) {
            move_to_XY_Normal();
            return;
        }
        if (this.Action != 2 && this.Action != 3 && this.Action != 4) {
            this.xFire = this.x;
            this.yFire = this.y;
            this.toX = this.x;
            this.toY = this.y;
            this.vx = 0;
            this.vy = 0;
            this.Action = 0;
            this.frameLeg = CRes.random(4);
        }
    }

    public void moveX() {
        if (this.isMoveOut) {
            return;
        }
        this.vy = 0;
        this.Action = 1;
        if (CRes.abs(this.x - this.toX) >= this.vMax + getVmount()) {
            if (this.x >= this.toX) {
                this.vx = -(this.vMax + getVmount());
                this.Direction = 2;
                return;
            } else {
                this.vx = this.vMax + getVmount();
                this.Direction = 3;
                return;
            }
        }
        this.vx = this.toX - this.x;
    }

    public void moveY() {
        if (this.isMoveOut) {
            return;
        }
        this.vx = 0;
        this.Action = 1;
        if (CRes.abs(this.y - this.toY) >= this.vMax + getVmount()) {
            if (this.y > this.toY) {
                this.vy = -(this.vMax + getVmount());
                this.Direction = 1;
                return;
            } else {
                this.vy = this.vMax + getVmount();
                this.Direction = 0;
                return;
            }
        }
        this.vy = this.toY - this.y;
    }

    public void move_to_XY_Normal() {
        if (this.isMoveOut) {
            return;
        }
        if (this.isDirMove) {
            if (CRes.abs(this.x - this.toX) >= this.vMax + getVmount()) {
                moveX();
                return;
            } else if (CRes.abs(this.y - this.toY) >= this.vMax + getVmount()) {
                moveY();
                return;
            } else {
                this.vx = 0;
                this.vy = 0;
                return;
            }
        }
        if (CRes.abs(this.y - this.toY) >= this.vMax + getVmount()) {
            moveY();
        } else if (CRes.abs(this.x - this.toX) >= this.vMax + getVmount()) {
            moveX();
        } else {
            this.vx = 0;
            this.vy = 0;
        }
    }

    public void setMove(int i, int i2) {
        if (this.isMoveOut || this.isBinded || this.isDongBang) {
            return;
        }
        if ((i2 == 1 || i2 == -1) && this.timeFreeMove < 12) {
            this.isWater = false;
            this.dyWater = 0;
            this.vx = 0;
            this.vy = 0;
            this.isDirMove = !this.isDirMove;
            this.timeFreeMove++;
            if (this.timeFreeMove >= 10) {
                this.timeFreeMove = 25;
                return;
            }
            return;
        }
        this.isWater = false;
        this.dyWater = 0;
        if (this.timeFreeMove > 0) {
            this.timeFreeMove--;
        }
        if (i2 == 2 && !isMountFly()) {
            this.isWater = true;
            this.dyWater = 3;
            if (this.vy != 0) {
                if (this.vy > 0) {
                    this.vy = (this.vMax + getVmount()) - i;
                } else {
                    this.vy = (-(this.vMax + getVmount())) + i;
                }
            }
            if (this.vx != 0) {
                if (this.vx > 0) {
                    this.vx = (this.vMax + getVmount()) - i;
                } else {
                    this.vx = (-(this.vMax + getVmount())) + i;
                }
            }
        }
    }

    public void paintWeaponhideplayer(mGraphics mgraphics, int i) {
        if (0 < 0 || 0 >= 5) {
            return;
        }
        byte b = this.clazz;
        int i2 = 0;
        if (this.Action != 2) {
            i2 = 0;
        }
        if (this.weapon_frame >= 0 && 0 >= 0) {
            WPSplashInfo wPSplashInfo = CRes.wpSplashInfos[b][0][i2];
            if (wPSplashInfo == null) {
                CRes.GetWPSplashInfo(b, 0, i2);
                return;
            }
            if (this.Action != 2 || this.weapon_frame < 4) {
                if (this.weapon_frame > 1) {
                    this.weapon_frame = 0;
                }
                if (this.weapon_frame < 0) {
                    this.weapon_frame = 0;
                }
                if (0 != -1 && CRes.loadImgWeaPone(b, 0, 0) != null) {
                    WeaponInfo loadImgWeaPone = CRes.loadImgWeaPone(b, 0, 0);
                    int i3 = this.frame;
                    if (i3 > 2) {
                        i3 = 2;
                    }
                    if (loadImgWeaPone.img != null) {
                        mgraphics.drawRegion(loadImgWeaPone.img, loadImgWeaPone.mRegion[this.Direction][0], 0, loadImgWeaPone.mRegion[this.Direction][1], loadImgWeaPone.himg, 0, this.x + loadImgWeaPone.mPos[this.Direction][i3][0], (((((this.y - this.ysai) + loadImgWeaPone.mPos[this.Direction][i3][1]) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, 0, true);
                        paintEffectWeapon(mgraphics);
                    }
                }
            } else if (this.weapon_frame < wPSplashInfo.P0_X[this.Direction].length && wPSplashInfo.image != null) {
                mgraphics.drawRegion(wPSplashInfo.image, wPSplashInfo.P0_X[this.Direction][this.weapon_frame], wPSplashInfo.P0_Y[this.Direction][this.weapon_frame], wPSplashInfo.PF_W[this.Direction][this.weapon_frame], wPSplashInfo.PF_H[this.Direction][this.weapon_frame], 0, this.x + wPSplashInfo.PF_X[this.Direction][this.weapon_frame], ((((((i + this.y) - this.ysai) + wPSplashInfo.PF_Y[this.Direction][this.weapon_frame]) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, 0, true);
            }
            if ((this.Action == 0 || this.Action == 1) && 0 > 1) {
                int i4 = 0 - 1;
                if (i4 > 3) {
                    i4 = 3;
                }
                paintEffectVukhi(mgraphics, i4, wPSplashInfo, b);
            }
        }
    }

    public void paintWeapon(mGraphics mgraphics, int i) {
        if (0 < 0 || 0 >= 5) {
            return;
        }
        byte b = this.clazz;
        int i2 = 0;
        if (this.Action != 2) {
            i2 = 0;
        }
        if (this.weapon_frame >= 0 && 0 >= 0) {
            WPSplashInfo wPSplashInfo = CRes.wpSplashInfos[b][0][i2];
            if (wPSplashInfo == null) {
                CRes.GetWPSplashInfo(b, 0, i2);
                return;
            }
            int i3 = this.weaponType;
            if (this.Action != 2 || this.weapon_frame < 4) {
                if (this.weapon_frame > 1) {
                    this.weapon_frame = 0;
                }
                if (this.weapon_frame < 0) {
                    this.weapon_frame = 0;
                }
                if (i3 != -1 && CRes.loadImgWeaPone(b, i3, 0) != null) {
                    WeaponInfo loadImgWeaPone = CRes.loadImgWeaPone(b, i3, 0);
                    int i4 = this.frame;
                    if (i4 > 2) {
                        i4 = 2;
                    }
                    if (loadImgWeaPone.img != null) {
                        mgraphics.drawRegion(loadImgWeaPone.img, loadImgWeaPone.mRegion[this.Direction][0], 0, loadImgWeaPone.mRegion[this.Direction][1], loadImgWeaPone.himg, 0, this.x + loadImgWeaPone.mPos[this.Direction][i4][0], (((((this.y - this.ysai) + loadImgWeaPone.mPos[this.Direction][i4][1]) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, 0, true);
                        paintEffectWeapon(mgraphics);
                    }
                }
            } else if (this.weapon_frame < wPSplashInfo.P0_X[this.Direction].length && wPSplashInfo.image != null) {
                mgraphics.drawRegion(wPSplashInfo.image, wPSplashInfo.P0_X[this.Direction][this.weapon_frame], wPSplashInfo.P0_Y[this.Direction][this.weapon_frame], wPSplashInfo.PF_W[this.Direction][this.weapon_frame], wPSplashInfo.PF_H[this.Direction][this.weapon_frame], 0, this.x + wPSplashInfo.PF_X[this.Direction][this.weapon_frame], ((((((i + this.y) - this.ysai) + wPSplashInfo.PF_Y[this.Direction][this.weapon_frame]) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, 0, true);
            }
            if ((this.Action == 0 || this.Action == 1) && 0 > 1) {
                int i5 = 0 - 1;
                if (i5 > 3) {
                    i5 = 3;
                }
                paintEffectVukhi(mgraphics, i5, wPSplashInfo, b);
            }
        }
    }

    private void paintEffectVukhi(mGraphics mgraphics, int i, WPSplashInfo wPSplashInfo, int i2) {
    }

    public void resetXY() {
        this.toX = this.x;
        this.toY = this.y;
        this.vx = 0;
        this.vy = 0;
    }

    public void beginFire() {
        this.f = 0;
        this.Action = 2;
        this.fplash = 0;
        this.vx = 0;
        this.vy = 0;
    }

    public static void startDeadFly(MainMonster mainMonster, int i, int i2) {
        MainObject mainObject = get_Object(i, (byte) 0);
        if (mainObject == null) {
            return;
        }
        int i3 = 5;
        int i4 = 0;
        if (mainMonster != null) {
            int i5 = 0;
            int i6 = 0;
            if (mainMonster.typeMonster != 7 && mainObject != null) {
                i5 = (mainMonster.x - mainObject.x) * 2;
                int i7 = (mainMonster.y - mainObject.y) * 2;
                while (true) {
                    i6 = i7;
                    if (getDistance(i5, i6) <= 20) {
                        break;
                    }
                    i5 = (i5 * 2) / 3;
                    i7 = (i6 * 2) / 3;
                }
                if (i2 == 1) {
                    i5 *= 2;
                    i6 *= 2;
                } else if (i2 == 2) {
                    while (getDistance(i5, i6) > 16) {
                        i5 = (i5 * 2) / 3;
                        i6 = (i6 * 2) / 3;
                    }
                    i3 = 20;
                    i4 = 18;
                }
            }
            mainMonster.startDeadFly(i5, i6, i3, i4);
        }
    }

    public void GiaoTiep() {
    }

    public void addChat(String str, boolean z) {
        if (this.chat == null) {
            this.chat = new PopupChat();
        }
        this.chat.setChat(str, z);
        this.chat.updatePos(this.x, (this.y - this.hOne) - 30);
    }

    public void resetAction() {
        this.vy = 0;
        this.vx = 0;
        this.toX = this.x;
        this.toY = this.y;
        if (this.Action != 2 && this.Action != 4) {
            this.Action = 0;
            this.weapon_frame = 0;
        }
    }

    public void resetVx_vy() {
        this.vy = 0;
        this.vx = 0;
        if (this.Action == 1) {
            this.Action = 0;
        }
    }

    public static void resetDirection(MainObject mainObject, MainObject mainObject2) {
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
        mainObject.Direction = i;
    }

    public void addnewBuff(int i, int i2) {
        for (int i3 = 0; i3 < this.vecBuff.size(); i3++) {
            MainBuff mainBuff = (MainBuff) this.vecBuff.elementAt(i3);
            if (mainBuff != null && mainBuff.typeBuff == i) {
                mainBuff.settimebuff(i2);
                return;
            }
        }
        this.vecBuff.addElement(new MainBuff(i, i2));
    }

    public void addBuff(int i, int i2, int i3) {
        MainBuff buff = MainBuff.getBuff(i, i3);
        if (buff != null) {
            buff.timeBegin = GameCanvas.timeNow;
            buff.timeOff = i2;
        } else {
            this.vecBuff.addElement(new MainBuff(i, i2, i3));
        }
    }

    public void setEye(int i) {
        this.eye = i;
        this.timeEye = 0;
    }

    public int skillMonster() {
        return 0;
    }

    public void startDie() {
    }

    public mVector vectorObjectNear() {
        mVector mvector = new mVector("MainObject vec");
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (mainObject != null && mainObject != GameScreen.player && mainObject != this && mainObject.typeObject == 0 && getDistance(this.x, this.y, mainObject.x, mainObject.y - (mainObject.hOne / 2)) <= 30) {
                mvector.addElement(mainObject);
            }
        }
        return mvector;
    }

    public void setVx_Vy_Item(int i, int i2) {
        this.toX = i;
        this.toY = i2;
        int i3 = this.toX - this.x;
        int i4 = this.toY - this.y;
        if (i4 == 0) {
            i4 = 1;
        }
        if (i3 == 0) {
            i3 = 1;
        }
        int distance = getDistance(i3, i4) / (this.vMax + getVmount());
        if (distance == 0) {
            distance = 1;
        }
        this.vx = i3 / distance;
        this.vy = i4 / distance;
        if (CRes.abs(this.vx) > CRes.abs(i3)) {
            this.vx = i3;
        }
        if (CRes.abs(this.vy) > CRes.abs(i4)) {
            this.vy = i4;
        }
        this.timeHuyKill = distance;
    }

    public boolean isThacNuoc() {
        return false;
    }

    public boolean findOwner(MainObject mainObject) {
        return false;
    }

    public boolean isMonsterHouse() {
        return false;
    }

    public boolean isItemBox() {
        return false;
    }

    public boolean isNPC() {
        return this.isBot >= -100 && this.isBot < -2;
    }

    public int getIDnpc() {
        return this.ID;
    }

    public boolean canFocus() {
        return true;
    }

    public boolean isNPC_server() {
        return false;
    }

    public void paint_no(mGraphics mgraphics) {
        if (this.isPaint_No) {
            this.frNo++;
            if (this.frNo > this.frameNo.length - 1) {
                this.frNo = 0;
            }
            AvMain.fraPlayerNo.drawFrame(this.frameNo[this.frNo], this.x, this.y + 5, 0, 33, mgraphics);
        }
    }

    public void move_to_point() {
        if (this.isMoveOut) {
            if (this.ID == GameScreen.player.ID) {
                GameCanvas.clearAll();
                this.posTransRoad = null;
            }
            this.vx = 0;
            this.vy = 0;
            boolean z = false;
            int i = (this.yMoveOut - this.y) >> 1;
            this.y += i;
            if (i <= 0) {
                this.y = this.yMoveOut;
                z = true;
            }
            if (z) {
                this.vx = 0;
                this.vy = 0;
                this.Action = 0;
                this.toX = this.x;
                this.toY = this.y;
                this.yMoveOut = this.y;
                this.yMoveOut = this.y;
                this.xStand = this.x;
                this.yStand = this.y;
                this.isMoveOut = false;
                return;
            }
            if (GameCanvas.loadmap.getTile(this.x, this.y) == 2 && !isMountFly()) {
                this.isWater = true;
                this.dyWater = 3;
            }
            if (GameCanvas.gameTick % 2 == 0) {
                GameScreen.addEffectEndKill(9, this.x, this.y);
                GameScreen.addEffectEndKill(46, this.x, this.y - 2);
            }
        }
    }

    public static void createImageMount() {
    }

    public int getVmount() {
        if (this.typeMount == -1) {
            return 0;
        }
        return MountTemplate.speed[this.typeMount];
    }

    public boolean isFramePaintMount_Truoc() {
        if (this.typeMount == -1) {
            return false;
        }
        for (byte b : MountTemplate.FRAME_VE_TRUOC[this.typeMount]) {
            if (this.frameMount == b && this.frameMount != -1) {
                return true;
            }
        }
        return false;
    }

    public void updateMount() {
        if (this.typeMount == -1) {
            return;
        }
        DataSkillEff effMatNa = getEffMatNa(this.idHorse);
        if (GameCanvas.gameTick % 5 == 0 && effMatNa != null) {
            int size = effMatNa.listFrame.size() / 21;
            if (size == 0) {
                size = 1;
            }
            this.frameThuCuoi = (byte) ((this.frameThuCuoi + 1) % size);
        }
        if (this.typeMount != -1) {
            if (effMatNa == null) {
                this.A_Move = ArrMount;
            }
            if (this.Action == 0) {
                if (this.Direction == 2) {
                    if (this.frame == 1) {
                        this.frameMount = (byte) 1;
                    } else {
                        this.frameMount = (byte) 0;
                    }
                    this.xMount = MountTemplate.dx[this.typeMount][0];
                    this.yMount = MountTemplate.dy[this.typeMount][0];
                    this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][0];
                    return;
                }
                if (this.Direction == 3) {
                    if (this.frame == 1) {
                        this.frameMount = (byte) 1;
                    } else {
                        this.frameMount = (byte) 0;
                    }
                    this.xMount = MountTemplate.dx[this.typeMount][2];
                    this.yMount = MountTemplate.dy[this.typeMount][2];
                    this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][1];
                    return;
                }
                if (this.Direction == 1) {
                    if (this.frame == 1) {
                        this.frameMount = (byte) 11;
                    } else {
                        this.frameMount = (byte) 10;
                    }
                    this.xMount = MountTemplate.dx[this.typeMount][4];
                    this.yMount = MountTemplate.dy[this.typeMount][4];
                    this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][2];
                    return;
                }
                if (this.frame == 1) {
                    this.frameMount = (byte) 6;
                } else {
                    this.frameMount = (byte) 5;
                }
                this.xMount = MountTemplate.dx[this.typeMount][6];
                this.yMount = MountTemplate.dy[this.typeMount][6];
                this.dyMount = MountTemplate.DY_CHAR_STAND[this.typeMount][3];
                return;
            }
            if (this.Action == 1) {
                this.fMount = (byte) (this.fMount + 1);
                if (this.Direction == 2) {
                    if (this.fMount > MountTemplate.FRAME_MOVE_LR[this.typeMount].length - 1) {
                        this.fMount = (byte) 0;
                    }
                    this.frameMount = MountTemplate.FRAME_MOVE_LR[this.typeMount][this.fMount];
                    this.xMount = MountTemplate.dx[this.typeMount][1];
                    this.yMount = MountTemplate.dy[this.typeMount][1];
                    if (this.frameMount == 3) {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][0];
                    } else if (this.frameMount == 2) {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][1];
                    } else {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][2];
                    }
                    if (this.fMount == 4 && !isMountFly()) {
                        GameScreen.addEffectEndKill_Direction(55, this.x + 19, this.y - 5, this.Direction, (byte) -1);
                        return;
                    }
                    return;
                }
                if (this.Direction == 3) {
                    if (this.fMount > MountTemplate.FRAME_MOVE_LR[this.typeMount].length - 1) {
                        this.fMount = (byte) 0;
                    }
                    this.frameMount = MountTemplate.FRAME_MOVE_LR[this.typeMount][this.fMount];
                    this.xMount = MountTemplate.dx[this.typeMount][3];
                    this.yMount = MountTemplate.dy[this.typeMount][3];
                    if (this.frameMount == 3) {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][3];
                    } else if (this.frameMount == 2) {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][4];
                    } else {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][5];
                    }
                    if (this.fMount == 4 && !isMountFly()) {
                        GameScreen.addEffectEndKill_Direction(55, this.x - 11, this.y - 5, this.Direction, (byte) -1);
                        return;
                    }
                    return;
                }
                if (this.Direction == 1) {
                    if (this.fMount > MountTemplate.FRAME_MOVE_UP[this.typeMount].length - 1) {
                        this.fMount = (byte) 0;
                    }
                    this.frameMount = MountTemplate.FRAME_MOVE_UP[this.typeMount][this.fMount];
                    this.xMount = MountTemplate.dx[this.typeMount][5];
                    this.yMount = MountTemplate.dy[this.typeMount][5];
                    if (this.frameMount == 13) {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][6];
                    } else if (this.frameMount == 12) {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][7];
                    } else {
                        this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][8];
                    }
                    if (this.fMount == 4 && !isMountFly()) {
                        GameScreen.addEffectEndKill_Direction(55, this.x - 14, this.y - 7, 3, (byte) -1);
                        GameScreen.addEffectEndKill_Direction(55, this.x + 16, this.y - 7, 2, (byte) -1);
                        return;
                    }
                    return;
                }
                if (this.fMount > MountTemplate.FRAME_MOVE_DOWN[this.typeMount].length - 1) {
                    this.fMount = (byte) 0;
                }
                this.frameMount = MountTemplate.FRAME_MOVE_DOWN[this.typeMount][this.fMount];
                this.xMount = MountTemplate.dx[this.typeMount][7];
                this.yMount = MountTemplate.dy[this.typeMount][7];
                if (this.frameMount == 8) {
                    this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][9];
                } else if (this.frameMount == 7) {
                    this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][10];
                } else {
                    this.dyMount = MountTemplate.DY_CHAR_MOVE[this.typeMount][11];
                }
                if (this.fMount == 4 && !isMountFly()) {
                    GameScreen.addEffectEndKill_Direction(55, this.x - 15, this.y - 10, 3, (byte) -1);
                    GameScreen.addEffectEndKill_Direction(55, this.x + 17, this.y - 10, 2, (byte) -1);
                    return;
                }
                return;
            }
            return;
        }
        resetMount();
    }

    public void resetMount() {
        this.A_Move = ArrMount1;
        this.dyMount = 0;
    }

    public void paintMount_Truoc(mGraphics mgraphics) {
        FrameImage frameImageMount;
        if (this.idHorse == -1 && this.typeMount != -1 && !this.isDongBang && (frameImageMount = FrameImage.getFrameImageMount(this.typeMount, 3, 5, 0)) != null && isFramePaintMount_Truoc()) {
            frameImageMount.drawFrameNew(this.frameMount, this.x + this.xMount, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum) + this.yMount, this.Direction > 2 ? 2 : 0, 33, mgraphics);
        }
    }

    public void paintMount_Sau(mGraphics mgraphics) {
        FrameImage frameImageMount;
        if (this.idHorse == -1 && this.typeMount != -1 && !this.isDongBang && (frameImageMount = FrameImage.getFrameImageMount(this.typeMount, 3, 5, 0)) != null && !isFramePaintMount_Truoc()) {
            frameImageMount.drawFrameNew(this.frameMount, this.x + this.xMount, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.yjum) + this.yMount, this.Direction > 2 ? 2 : 0, 33, mgraphics);
        }
    }

    public void setArrayGemKham(short[][] sArr) {
        this.arrayGemKham = sArr;
        byte b = this.clazz;
        if (b == 3) {
            b = 2;
        } else if (b == 2) {
            b = 3;
        }
        this.slotGem = new byte[this.arrayGemKham[8 + b].length];
        for (int i = 0; i < this.arrayGemKham.length; i++) {
            for (int i2 = 0; i2 < this.arrayGemKham[i].length; i2++) {
                if (this.arrayGemKham[8 + b][i2] >= 23 && this.arrayGemKham[8 + b][i2] <= 27) {
                    this.slotGem[i2] = 0;
                } else if (this.arrayGemKham[8 + b][i2] >= 28 && this.arrayGemKham[8 + b][i2] <= 32) {
                    this.slotGem[i2] = 1;
                } else {
                    this.slotGem[i2] = -1;
                }
            }
        }
    }

    private void paintEffectWeapon(mGraphics mgraphics) {
        if (this.weaponEff != null) {
            this.weaponEff.drawFrameEffectSkill(this.fweapon, this.x + this.dxEff, (((((this.y - this.ysai) + this.dyEff) - this.dy) + this.dyWater) - this.dyMount) - this.yjum, this.transEff[this.Direction], 33, mgraphics);
        }
    }

    private void updateEffectWeapon() {
        if (GameCanvas.gameTick % 2 == 0) {
            this.fweapon = (byte) (this.fweapon + 1);
        }
        if (this.fweapon > 4) {
            this.fweapon = (byte) 0;
        }
        this.timeEff++;
        if (this.timeEff < this.timeShow) {
            if (this.slotGem[0] == -1) {
                if (this.weaponEff != null) {
                    this.weaponEff = null;
                }
                if (this.fweapon != 0) {
                    this.fweapon = (byte) 0;
                }
            } else {
                this.weaponEff = weaponEff_Gem[this.slotGem[0]];
            }
        } else if (this.timeEff >= this.timeShow * 2 && this.timeEff < this.timeShow * 3) {
            if (this.slotGem[1] == -1) {
                if (this.weaponEff != null) {
                    this.weaponEff = null;
                }
                if (this.fweapon != 0) {
                    this.fweapon = (byte) 0;
                }
            } else {
                this.weaponEff = weaponEff_Gem[this.slotGem[1]];
            }
        } else if (this.timeEff >= this.timeShow * 4 && this.timeEff < this.timeShow * 5) {
            if (this.slotGem[2] == -1) {
                if (this.weaponEff != null) {
                    this.weaponEff = null;
                }
                if (this.fweapon != 0) {
                    this.fweapon = (byte) 0;
                }
            } else {
                this.weaponEff = weaponEff_Gem[this.slotGem[2]];
            }
        } else if (this.timeEff >= this.timeShow * 6) {
            this.timeEff = 0;
            if (this.weaponEff != null) {
                this.weaponEff = null;
            }
            if (this.fweapon != 0) {
                this.fweapon = (byte) 0;
            }
        } else {
            if (this.weaponEff != null) {
                this.weaponEff = null;
            }
            if (this.fweapon != 0) {
                this.fweapon = (byte) 0;
            }
        }
        if (this.slotGem[0] != -1 && this.weaponEff == weaponEff_Gem[this.slotGem[0]]) {
            this.dxEff = this.eff_dx[this.slotGem[0]][this.Direction];
            if (this.frame == 1) {
                this.dyEff = this.eff_dy[this.slotGem[0]][this.Direction] + 1;
            } else {
                this.dyEff = this.eff_dy[this.slotGem[0]][this.Direction];
            }
        }
        if (this.slotGem[1] != -1 && this.weaponEff == weaponEff_Gem[this.slotGem[1]]) {
            this.dxEff = this.eff_dx[this.slotGem[1]][this.Direction];
            if (this.frame == 1) {
                this.dyEff = this.eff_dy[this.slotGem[1]][this.Direction] + 1;
            } else {
                this.dyEff = this.eff_dy[this.slotGem[1]][this.Direction];
            }
        }
        if (this.slotGem[2] != -1 && this.weaponEff == weaponEff_Gem[this.slotGem[2]]) {
            this.dxEff = this.eff_dx[this.slotGem[2]][this.Direction];
            if (this.frame == 1) {
                this.dyEff = this.eff_dy[this.slotGem[2]][this.Direction] + 1;
            } else {
                this.dyEff = this.eff_dy[this.slotGem[2]][this.Direction];
            }
        }
    }

    public void addEffectCharWearing(int i, int i2) {
    }

    public void setNameStore(String str) {
    }

    public void removeNameStore() {
    }

    public void updateoverHP_MP() {
        countmp = (byte) (countmp + 1);
        if (countmp > 10) {
            countmp = (byte) 0;
        }
        if (this.mp < this.maxMp / 10) {
            this.overMP = true;
        } else {
            this.overMP = false;
        }
        if (this.hp < this.maxHp / 10 && this.hp > 0) {
            this.overHP = true;
        } else {
            this.overHP = false;
        }
    }

    public boolean isSelling() {
        return false;
    }

    public void paintEffectCharWearing(mGraphics mgraphics) {
        if (this.vecEffectCharWearing.size() > 0) {
            for (int i = 0; i < this.vecEffectCharWearing.size(); i++) {
                EffectCharWearing effectCharWearing = (EffectCharWearing) this.vecEffectCharWearing.elementAt(i);
                if (effectCharWearing != null) {
                    effectCharWearing.paint(mgraphics, this.x, this.y);
                }
            }
        }
    }

    public void updateEffectCharWearing() {
        if (this.vecEffectCharWearing.size() > 0) {
            for (int i = 0; i < this.vecEffectCharWearing.size(); i++) {
                EffectCharWearing effectCharWearing = (EffectCharWearing) this.vecEffectCharWearing.elementAt(i);
                if (effectCharWearing != null) {
                    effectCharWearing.update();
                }
            }
        }
    }

    public void removeEffectCharWearing(int i) {
        int size = this.vecEffectCharWearing.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                EffectCharWearing effectCharWearing = (EffectCharWearing) this.vecEffectCharWearing.elementAt(i2);
                if (effectCharWearing != null && effectCharWearing.type == i) {
                    this.vecEffectCharWearing.removeElement(effectCharWearing);
                }
            }
        }
    }

    public void removeAllEffCharWearing() {
        this.vecEffectCharWearing.removeAllElements();
    }

    public boolean isMiNuong() {
        return false;
    }

    public void SetnameOwner(String str) {
    }

    public void addDataEff(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < this.vecDataSkillEff.size(); i4++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i4);
            if (dataSkillEff != null && dataSkillEff.idEff == i) {
                this.vecDataSkillEff.removeElement(dataSkillEff);
            }
        }
        this.vecDataSkillEff.addElement(new DataSkillEff(bArr, (short) i, i2, i3));
    }

    public void addDataEffMatna(int i, byte[] bArr) {
        for (int i2 = 0; i2 < this.vecDataSkillEff.size(); i2++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i2);
            if (dataSkillEff != null && dataSkillEff.idEff == i) {
                this.vecDataSkillEff.removeElement(dataSkillEff);
            }
        }
        DataSkillEff dataSkillEff2 = new DataSkillEff(bArr, (short) i, 0, 0);
        dataSkillEff2.isMatna = true;
        dataSkillEff2.typRequestImg = 113;
        this.vecDataSkillEff.addElement(dataSkillEff2);
    }

    public void addDataEff(int i, byte[] bArr, int i2, int i3, long j, byte b) {
        for (int i4 = 0; i4 < this.vecDataSkillEff.size(); i4++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i4);
            if (dataSkillEff != null && dataSkillEff.idEff == i) {
                this.vecDataSkillEff.removeElement(dataSkillEff);
            }
        }
        this.vecDataSkillEff.addElement(new DataSkillEff(bArr, (short) i, i2, i3, j, b));
    }

    public void addDataEff2(int i, byte[] bArr, int i2, int i3, long j, byte b) {
        for (int i4 = 0; i4 < this.vecDataSkillEff.size(); i4++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i4);
            if (dataSkillEff != null && dataSkillEff.idEff == i) {
                this.vecDataSkillEff.removeElement(dataSkillEff);
            }
        }
        DataSkillEff dataSkillEff2 = new DataSkillEff(bArr, (short) i, i2, i3, j, b);
        dataSkillEff2.isremovebyTime = false;
        dataSkillEff2.isremovebyFrame = true;
        this.vecDataSkillEff.addElement(dataSkillEff2);
    }

    public void removeDataSkillEff(int i) {
        for (int i2 = 0; i2 < this.vecDataSkillEff.size(); i2++) {
            DataSkillEff dataSkillEff = (DataSkillEff) this.vecDataSkillEff.elementAt(i2);
            if (dataSkillEff != null && dataSkillEff.idEff == i) {
                this.vecDataSkillEff.removeElement(dataSkillEff);
            }
        }
    }

    public boolean isHairNotHat() {
        if (Player.ID_HAIR_NO_HAT == null || this.idPartFashion[2] != -1) {
            return false;
        }
        int length = Player.ID_HAIR_NO_HAT.length;
        for (int i = 0; i < length; i++) {
            if (Player.ID_HAIR_NO_HAT[i] == this.hair) {
                return true;
            }
        }
        return false;
    }

    public int getDy() {
        return 0;
    }

    public int getHeight() {
        return this.hOne;
    }

    public int getDir() {
        return this.Direction;
    }

    public void addiconClan() {
    }

    public boolean canNotMove() {
        return false;
    }

    public boolean canfire() {
        return false;
    }

    public boolean isBossNew() {
        return false;
    }
}
