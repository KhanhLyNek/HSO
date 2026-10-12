package GameScreen;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectAuto;
import GameEffect.EffectSkill;
import GameObjects.DelaySkill;
import GameObjects.Item;
import GameObjects.MainEvent;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.Monstervantieu;
import GameObjects.ObjectData;
import GameObjects.ObjectParty;
import GameObjects.Player;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.MainTabNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.MainImage;
import Model.MainTemplateItem;
import Model.Point;
import Model.T;
import Skill.HotKey;
import Skill.MainListSkill;
import Skill.Skill;
import Thread_More.LoadMap;
import Thread_More.MiniMap;
import Thread_More.MiniMapFull_Screen;

/* loaded from: KnightOnline_v403.jar:GameScreen/PaintInfoGameScreen.class */
public class PaintInfoGameScreen {
    int timepaintServer;
    int timePaintInfoChar;
    public int timeInfoCharCline;
    public int maxtimeChar;
    public int ydInfoChar;
    boolean vipInfoChar;
    public static int xMess;
    public static int yMess;
    public static int numMess;
    public static int yBeginInfo;
    public static int wInfoServer;
    public static int winfo18plus;
    public static mImage[] imgMove;
    public static mImage[] imgFire;
    public static mImage[] imgOther;
    public static mImage imgInfoFocus;
    public static mImage imgauto;
    public static mImage imgxp;
    public static mImage imgmove;
    public static mImage imgBackQuick;
    public static FrameImage fraClose;
    public static FrameImage fraFocusIngame;
    public static FrameImage fraBack;
    public static FrameImage fraMenu;
    public static FrameImage fraButton;
    public static FrameImage fraStatusArea;
    public static FrameImage fraCloseMenu;
    public static FrameImage fralevelup;
    public static FrameImage fraButton2;
    public static FrameImage fraContact;
    public static FrameImage fraEvent;
    public static FrameImage[] mfraIconQuick;
    public static int keyPoint;
    public static int timeChange;
    public static int timeNameMap;
    public static int wNameMap;
    public static int vyNameMap;
    public static int yNameMap;
    public String strInfoCharCline;
    public String strInfoCharServer;
    public static byte taitho;
    public static int xFocus;
    static int yFocus;
    static int xParty;
    static int yParty;
    public static int delta;
    public static long timeThachdau;
    public static int WBlackclolor;
    public static int WRedclor;
    int indexEvent;
    int hShowEvent;
    public static int wShowEvent;
    public short[] posTam;
    int timeMove;
    int xlast;
    int ylast;
    public static int timeHS;
    public static int curTimeHS;
    public static boolean isCountTime;
    public static FrameImage imgArenaIcon;
    public EffectAuto eff;
    public static String nameclan;
    public static int xPointMove = 55;
    public static int yPointMove = GameCanvas.h - 55;
    public static int wArrowMove = 30;
    public static int wPointArrow = 38;
    public static int[] mKeySkill = {1, 3, 7, 9};
    public static int[] mValueHotKey = {1, 3, 5, 7, 9};
    public static String[] mValueChar = {"R", "T", "Y", "U", "I"};
    public static int[][] mPosKill = mSystem.new_M_Int(4, 2);
    public static int[][] mPosMove = mSystem.new_M_Int(4, 2);
    public static int[][] mPosOther = mSystem.new_M_Int(5, 2);
    public static int[][] mSizeImgOther = mSystem.new_M_Int(5, 2);
    public static int timePointer = 0;
    public static String namemap = "";
    public static int xPointKill = GameCanvas.w - 35;
    public static int yPointKill = GameCanvas.h - 50;
    public static int wSkill = 24;
    public static int wMainSkill = 50;
    static int gocBegin = 285;
    static int lSkill = 50;
    static int xPaintSkill = GameCanvas.hw - 60;
    static int yPaintSkill = (GameCanvas.h - GameCanvas.hCommand) - 14;
    public static boolean isLevelPoint = false;
    public static boolean isShowInfoAuto = true;
    public static boolean isPaintInfoFocus = false;
    public static int hShowInGame = 0;
    public static boolean isShowInGame = true;
    public static long timeDoNotClick = 0;
    public static int[] imgHitWidth = new int[3];
    public static int[] imgHitHeight = new int[3];
    public static mVector vecfocus = new mVector("PaintInfoGameScr vecfocus");
    public static byte paint18plush = 0;
    public static int idicon = -1;
    public short idCharLoiDai1 = -1;
    public short idCharLoiDai2 = -1;
    int xPaintInfo = 0;
    int xPaintInfoChar = 0;
    int xmaxInfo = 0;
    int xmaxInfoChar = 0;
    int speedInfo = 2;
    int speedInfoChar = 2;
    public int hImgInfo = 22;
    int[] mRotateMove = {2, 0, 3, 1};
    int[] mKeyMove = {4, 6, 2, 8};
    int[] mKeyOther = {100, 101, EffectSkill.EFF_PET_EAGLE, 103};
    public String strInfoServer = null;
    int indexTab = 0;
    int timeEvent = 0;
    MainEvent eventShow = null;
    private long timeDownChat = -1;
    private int hClip = 0;
    boolean isTouchFocus = true;
    byte[] totalHouse = {0, 0, 0, 0};
    short[] totalPlayer = {0, 0, 0, 0};
    short totalPoint = 0;
    byte[] typePK = {1, 4, 5, 2, 0};
    byte[] typePaint = {3, 2, 0, 1, 4};
    public boolean isShipMove = false;
    int xPos_minimap = 0;
    int yPos_minimap = 0;

    public static void loadPaintInfo() {
        wInfoServer = ((GameCanvas.w * 2) / 3) - 63;
        winfo18plus = GameCanvas.hw - (GameCanvas.hw / 3);
        yBeginInfo = (GameCanvas.h / 4) - (GameCanvas.isTouch ? 75 : 30);
        if (yBeginInfo < 1) {
            yBeginInfo = 1;
        }
        xFocus = GameCanvas.w - 52;
        yFocus = 0;
        xParty = 2;
        yParty = 60;
        xMess = 70;
        yMess = 45;
        if (GameCanvas.isTouch) {
            loadImagePointer();
            xFocus = GameCanvas.hw;
        }
        wShowEvent = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
    }

    public static void reloadmove() {
        mPosMove = mSystem.new_M_Int(4, 2);
        int i = 0;
        while (i < mPosMove.length) {
            mPosMove[i][0] = xPointMove + (i < 2 ? (-wArrowMove) + (wArrowMove * 2 * (i % 2)) : 0);
            mPosMove[i][1] = yPointMove + (i > 1 ? (-wArrowMove) + (wArrowMove * 2 * (i % 2)) : 0);
            i++;
        }
    }

    public static void loadImagePointer() {
        wSkill = 32;
        int i = gocBegin;
        for (int i2 = 0; i2 < mPosKill.length; i2++) {
            mPosKill[i2][0] = xPointKill + ((CRes.cos(CRes.fixangle(i)) * lSkill) / ObjectData.MONSTER);
            mPosKill[i2][1] = yPointKill + ((CRes.sin(CRes.fixangle(i)) * lSkill) / ObjectData.MONSTER);
            i -= 45;
        }
        xPaintSkill = GameCanvas.w - (wSkill * 6);
        yPaintSkill = GameCanvas.h - 24;
        mPosOther[0][0] = 8;
        mPosOther[0][1] = 43;
        if (taitho == 0) {
            mPosOther[1][0] = 8;
        } else {
            mPosOther[1][0] = 38;
        }
        mPosOther[1][1] = 73;
        mPosOther[2][0] = GameCanvas.w - 27;
        mPosOther[2][1] = GameCanvas.h - 145;
        mPosOther[3][0] = GameCanvas.w - 27;
        mPosOther[3][1] = GameCanvas.h - 175;
        if (mSystem.isIP_GDX) {
            mPosOther[3][0] = GameCanvas.w - 60;
            mPosOther[3][1] = GameCanvas.h - 146;
        }
        mPosOther[4][0] = GameCanvas.hw - 20;
        mPosOther[4][1] = GameCanvas.h - 16;
        setPosTouch();
        xMess = 45;
        yMess = 45;
        imgOther = new mImage[5];
        for (int i3 = 0; i3 < imgOther.length; i3++) {
            imgOther[i3] = mImage.createImage(new StringBuffer().append("/point/other_").append(i3).append(".img").toString());
            mSizeImgOther[i3][0] = mImage.getImageWidth(imgOther[i3].image);
            mSizeImgOther[i3][1] = mImage.getImageHeight(imgOther[i3].image) / 2;
        }
        imgMove = new mImage[3];
        for (int i4 = 0; i4 < imgMove.length; i4++) {
            if (i4 != 1) {
                imgMove[i4] = mImage.createImage(new StringBuffer().append("/point/move_").append(i4).append(".img").toString());
            }
        }
        int i5 = 0;
        while (i5 < mPosMove.length) {
            mPosMove[i5][0] = xPointMove + (i5 < 2 ? (-wArrowMove) + (wArrowMove * 2 * (i5 % 2)) : 0);
            mPosMove[i5][1] = yPointMove + (i5 > 1 ? (-wArrowMove) + (wArrowMove * 2 * (i5 % 2)) : 0);
            i5++;
        }
        imgFire = new mImage[2];
        for (int i6 = 0; i6 < imgFire.length; i6++) {
            imgFire[i6] = mImage.createImage(new StringBuffer().append("/point/fire_").append(i6).append(".img").toString());
        }
        fraClose = new FrameImage(mImage.createImage("/point/close.img"), 14, 14);
        fraCloseMenu = new FrameImage(mImage.createImage("/point/closemenu.img"), 21, 21);
        fraBack = new FrameImage(mImage.createImage("/point/buttonback.png"), 57, 30);
        fraMenu = new FrameImage(mImage.createImage("/point/buttonmenu.img"), 32, 32);
        fraButton = new FrameImage(mImage.createImage("/point/button.img"), 80, 30);
        fraButton2 = new FrameImage(mImage.createImage("/point/button2.img"), 60, 19);
        fraContact = new FrameImage(mImage.createImage("/point/contact.img"), 26, 26);
        mfraIconQuick = new FrameImage[11];
        for (int i7 = 0; i7 < mfraIconQuick.length; i7++) {
            mfraIconQuick[i7] = new FrameImage(mImage.createImage(new StringBuffer().append("/point/quick_").append(i7).append(".png").toString()), 30, 30);
        }
        imgBackQuick = mImage.createImage("/point/backquick.img");
        imgmove = mImage.createImage("/interface/move.img");
    }

    public static void paintHitscr(mGraphics mgraphics, boolean z) {
        if (z) {
            delta = 0;
        } else {
            delta = 10;
        }
        mgraphics.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 0, (GameCanvas.w - imgHitWidth[0]) + delta, -delta, 0, false);
        mgraphics.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 1, (GameCanvas.w - imgHitWidth[0]) + delta, (GameCanvas.h - imgHitHeight[0]) + delta, 0, false);
        mgraphics.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 2, -delta, -delta, 0, false);
        mgraphics.drawRegion(AvMain.imghitScr[0], 0, 0, imgHitWidth[0], imgHitHeight[0], 4, -delta, (GameCanvas.h - imgHitHeight[0]) + delta, 0, false);
        int i = (((GameCanvas.w + delta) - (2 * imgHitWidth[0])) / imgHitWidth[1]) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            mgraphics.drawRegion(AvMain.imghitScr[1], 0, 0, imgHitWidth[1], imgHitHeight[1], 0, (imgHitWidth[0] + (imgHitWidth[1] * i2)) - delta, -delta, 0, false);
            mgraphics.drawRegion(AvMain.imghitScr[1], 0, 0, imgHitWidth[1], imgHitHeight[1], 1, (imgHitWidth[0] + (imgHitWidth[1] * i2)) - delta, (GameCanvas.h - imgHitHeight[1]) + delta, 0, false);
        }
        int i3 = (((GameCanvas.w + delta) - (2 * imgHitHeight[0])) / imgHitHeight[2]) + 1;
        for (int i4 = 0; i4 < i3; i4++) {
            mgraphics.drawRegion(AvMain.imghitScr[2], 0, 0, imgHitWidth[2], imgHitHeight[2], 0, -delta, (imgHitHeight[0] + (imgHitHeight[2] * i4)) - delta, 0, false);
            mgraphics.drawRegion(AvMain.imghitScr[2], 0, 0, imgHitWidth[2], imgHitHeight[2], 2, (GameCanvas.w - imgHitWidth[2]) + delta, (imgHitHeight[0] + (imgHitHeight[2] * i4)) - delta, 0, false);
        }
    }

    public void paintKillPlayer(mGraphics mgraphics) {
        String stringBuffer;
        if (timeChange == 0) {
            int i = yPaintSkill + hShowInGame;
            if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2) {
                for (int i2 = 0; i2 < 5; i2++) {
                    mgraphics.drawImage(AvMain.imgHotKey, xPaintSkill + (i2 * wSkill), i - 5, 0, false);
                    if (!GameCanvas.isTouch && TField.isQwerty) {
                        AvMain.Font3dWhite(mgraphics, new StringBuffer().append(mValueChar[i2]).append("").toString(), xPaintSkill + (i2 * wSkill) + 11, i, 2);
                    } else {
                        AvMain.Font3dWhite(mgraphics, new StringBuffer().append(mValueHotKey[i2]).append("").toString(), xPaintSkill + (i2 * wSkill) + 11, i, 2);
                    }
                }
                return;
            }
            int i3 = 5 - hShowInGame;
            for (int i4 = 0; i4 < Player.mhotkey[0].length; i4++) {
                mgraphics.drawImage(AvMain.imgHotKey, xPaintSkill + (i4 * wSkill), yPaintSkill - i3, 0, false);
                HotKey hotKey = Player.mhotkey[Player.levelTab][i4];
                DelaySkill delaySkill = null;
                if (hotKey.type == HotKey.SKILL) {
                    Skill skillFormId = MainListSkill.getSkillFormId(hotKey.id);
                    if (skillFormId != null) {
                        skillFormId.paint(mgraphics, xPaintSkill + (i4 * wSkill) + 11, (yPaintSkill - i3) + 11, 3);
                    }
                    delaySkill = Player.timeDelaySkill[hotKey.id];
                } else if (hotKey.type == HotKey.POTION && MainTemplateItem.isload) {
                    Item itemInventory = Item.getItemInventory(4, hotKey.id);
                    if (itemInventory != null && itemInventory.typePotion < 2) {
                        itemInventory.paintItem(mgraphics, xPaintSkill + (i4 * wSkill) + 11, (yPaintSkill + 11) - i3, MainTabNew.wOneItem, 0, 3);
                        delaySkill = Player.timeDelayPotion[itemInventory.typePotion];
                    } else {
                        hotKey.setHotKey(0, HotKey.NULL, (byte) 0);
                        MainItem.setAddHotKey((byte) 1, false);
                        MainItem.setAddHotKey((byte) 0, false);
                    }
                }
                if (hotKey.type != HotKey.NULL && delaySkill != null && delaySkill.limit > 0) {
                    if (delaySkill.value > 0) {
                        int i5 = (delaySkill.value * 20) / delaySkill.limit;
                        if (i5 < 1) {
                            i5 = 1;
                        }
                        mgraphics.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, i5, 0, xPaintSkill + (i4 * wSkill) + 1, (yPaintSkill + 1) - i3, 0, false);
                        int i6 = delaySkill.value / ObjectData.MONSTER;
                        if (i6 == 0) {
                            stringBuffer = new StringBuffer().append("0.").append((delaySkill.value % ObjectData.MONSTER) / 100).toString();
                        } else {
                            stringBuffer = new StringBuffer().append("").append(i6).toString();
                        }
                        mFont.tahoma_7b_white.drawString(mgraphics, stringBuffer, xPaintSkill + (i4 * wSkill) + 11, (yPaintSkill + 5) - i3, 2, false);
                    } else if (delaySkill.value > -150) {
                        mgraphics.setColor(-1118516);
                        mgraphics.fillRoundRect(xPaintSkill + (i4 * wSkill) + 1, (yPaintSkill - i3) + 1, 20, 20, 4, 4, false);
                    }
                }
                if (!GameCanvas.isTouch) {
                    if (TField.isQwerty) {
                        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(mValueChar[i4]).append("").toString(), xPaintSkill + (i4 * wSkill) + 12, (yPaintSkill - i3) - 11, 2, false);
                    } else {
                        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(mValueHotKey[i4]).append("").toString(), xPaintSkill + (i4 * wSkill) + 12, (yPaintSkill - i3) - 11, 2, false);
                    }
                }
            }
            return;
        }
        paintChangeSkill(mgraphics);
    }

    public void paintBuffPlayer(mGraphics mgraphics) {
        for (int i = 0; i < GameScreen.player.vecBuff.size(); i++) {
        }
    }

    private void paintChangeSkill(mGraphics mgraphics) {
        int i;
        Item itemInventory;
        for (int i2 = 0; i2 < 10; i2++) {
            int i3 = Player.levelTab;
            if (i2 < 5) {
                i = timeChange * 8;
            } else {
                i = 64 - (timeChange * 8);
                if (Player.levelTab == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
            }
            mgraphics.drawImage(AvMain.imgHotKey, (xPaintSkill + ((i2 % 5) * wSkill)) - 1, (i + yPaintSkill) - 1, 0, false);
            HotKey hotKey = Player.mhotkey[i3][i2 % 5];
            if (hotKey.type == HotKey.SKILL) {
                MainListSkill.getSkillFormId(hotKey.id).paint(mgraphics, xPaintSkill + ((i2 % 5) * wSkill) + 11, i + yPaintSkill + 11, 3);
            } else if (hotKey.type == HotKey.POTION && (itemInventory = Item.getItemInventory(4, hotKey.id)) != null) {
                itemInventory.paintItem(mgraphics, xPaintSkill + ((i2 % 5) * wSkill) + 11, i + yPaintSkill + 11, MainTabNew.wOneItem, 0, 3);
            }
        }
    }

    public void paintInfoThachDau(mGraphics mgraphics, int i, int i2) {
        if (!isMapThachdau()) {
            return;
        }
        GameCanvas.resetTrans(mgraphics);
        if (this.idCharLoiDai1 != -1) {
            MainObject findObjByteCat = GameScreen.findObjByteCat(this.idCharLoiDai1, (byte) 0);
            if (findObjByteCat != null) {
                int i3 = (GameCanvas.w / 2) - 20;
                int i4 = i3 / (WBlackclolor / 2);
                if (i4 < 0) {
                    i4 = 1;
                }
                if (i4 == 1) {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, i, i2 + 3, 0, false);
                } else {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, i, i2 + 3, 0, true);
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, ((i + i3) - (WBlackclolor - 2)) + 2, i2 + 3, 0, true);
                    int i5 = (i3 - ((WBlackclolor - 2) * 2)) / 5;
                    if (i5 <= 0) {
                        i5 = 1;
                    }
                    for (int i6 = 0; i6 < i5 + 1; i6++) {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, ((i + WBlackclolor) - 4) + (i6 * 5), i2 + 3, 0, true);
                    }
                }
                if (findObjByteCat.hp > 0) {
                    int i7 = (int) ((findObjByteCat.hp * i3) / findObjByteCat.maxHp);
                    int i8 = i3 + 1;
                    mgraphics.setClip(i, i2 + 4, i8 - (i3 - i7), 7);
                    mgraphics.saveCanvas();
                    mgraphics.ClipRec(i, i2 + 4, i8 - (i3 - i7), 7);
                    int i9 = i3 / (WRedclor / 2);
                    if (i9 < 0) {
                        i9 = 1;
                    }
                    if (i9 == 1) {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, i + 1, i2 + 4, 0, true);
                    } else {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, i + 1, i2 + 4, 0, true);
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, ((i + 1) + i3) - (WRedclor - 2), i2 + 4, 0, true);
                        int i10 = (i3 - ((WRedclor - 2) * 2)) / 5;
                        if (i10 <= 0) {
                            i10 = 1;
                        }
                        for (int i11 = 0; i11 < i10 + 1; i11++) {
                            mgraphics.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, ((i + WRedclor) - 2) + (i11 * 5), i2 + 4, 0, true);
                        }
                    }
                }
                if (findObjByteCat.mp > 0) {
                }
                mgraphics.restoreCanvas();
                mgraphics.endClip();
                GameCanvas.resetTrans(mgraphics);
                AvMain.Font3dColor(mgraphics, new StringBuffer().append(findObjByteCat.name.toUpperCase()).append(" Lv: ").append((int) findObjByteCat.Lv).toString(), i + 2, (i2 + 24) - 10, 0, (byte) 0);
                if (!findObjByteCat.overHP) {
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(findObjByteCat.hp).append("/").append(findObjByteCat.maxHp).toString(), i3 / 2, i2 + 2, 2, true);
                } else {
                    mFont mfont = mFont.tahoma_7_white;
                    if (MainObject.countmp > 5) {
                        mfont = mFont.tahoma_7_red;
                    }
                    mfont.drawString(mgraphics, new StringBuffer().append(findObjByteCat.hp).append("/").append(findObjByteCat.maxHp).toString(), i3 / 2, i2 + 2, 2, true);
                }
                if (timeThachdau - (mSystem.currentTimeMillis() / 1000) > 0) {
                    long currentTimeMillis = timeThachdau - (mSystem.currentTimeMillis() / 1000);
                    mgraphics.drawRegion(AvMain.imgBackInfo, 0, 0, 140, 20, 0, GameCanvas.w / 2, i2 + 35, 3, false);
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.TimeThachDau).append(currentTimeMillis).toString(), GameCanvas.w / 2, i2 + 30, 2, false);
                    return;
                }
                return;
            }
            return;
        }
        int i12 = (GameCanvas.w / 2) - 20;
        int i13 = i12 / (WBlackclolor / 2);
        if (i13 < 0) {
            i13 = 1;
        }
        if (i13 == 1) {
            mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, i, i2 + 3, 0, false);
            mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor, 9, 0, i, i2 + 15, 0, false);
        } else {
            mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, i, i2 + 3, 0, true);
            mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor - 2, 9, 0, i, i2 + 15, 0, true);
            mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, ((i + i12) - (WBlackclolor - 2)) + 2, i2 + 3, 0, true);
            mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 2, 9, WBlackclolor - 2, 9, 0, ((i + i12) - (WBlackclolor - 2)) + 2, i2 + 15, 0, true);
            int i14 = (i12 - ((WBlackclolor - 2) * 2)) / 5;
            if (i14 <= 0) {
                i14 = 1;
            }
            for (int i15 = 0; i15 < i14 + 1; i15++) {
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, ((i + WBlackclolor) - 4) + (i15 * 5), i2 + 3, 0, true);
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 10, 9, 15, 9, 0, ((i + WBlackclolor) - 4) + (i15 * 5), i2 + 15, 0, true);
            }
        }
        if (GameScreen.player.hp > 0) {
            int i16 = (int) ((GameScreen.player.hp * i12) / GameScreen.player.maxHp);
            int i17 = i12 + 1;
            mgraphics.setClip(i, i2 + 4, i17 - (i12 - i16), 7);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(i, i2 + 4, i17 - (i12 - i16), 7);
            int i18 = i12 / (WRedclor / 2);
            if (i18 < 0) {
                i18 = 1;
            }
            if (i18 == 1) {
                mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, i + 1, i2 + 4, 0, true);
            } else {
                mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, i + 1, i2 + 4, 0, true);
                mgraphics.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, ((i + 1) + i12) - (WRedclor - 2), i2 + 4, 0, true);
                int i19 = (i12 - ((WRedclor - 2) * 2)) / 5;
                if (i19 <= 0) {
                    i19 = 1;
                }
                for (int i20 = 0; i20 < i19 + 1; i20++) {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, ((i + WRedclor) - 2) + (i20 * 5), i2 + 4, 0, true);
                }
            }
            mgraphics.restoreCanvas();
        }
        if (GameScreen.player.mp > 0) {
            long j = GameScreen.player.mp * i12;
            int i21 = i12 + 2;
            int i22 = (int) (j / GameScreen.player.maxMp);
            mgraphics.setClip(i, i2 + 16, i21 - (i12 - i22), 7);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(i, i2 + 16, i21 - (i12 - i22), 7);
            int i23 = i12 / (WRedclor / 2);
            if (i23 < 0) {
                i23 = 1;
            }
            if (i23 == 1) {
                mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor, 7, 0, i + 1, i2 + 16, 0, true);
            } else {
                mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor - 2, 7, 0, i + 1, i2 + 16, 0, true);
                mgraphics.drawRegion(AvMain.imgcolorhpmp, 2, 7, WRedclor - 2, 7, 0, ((i + 1) + i12) - (WRedclor - 2), i2 + 16, 0, true);
                int i24 = (i12 - ((WRedclor - 2) * 2)) / 5;
                if (i24 <= 0) {
                    i24 = 1;
                }
                for (int i25 = 0; i25 < i24 + 1; i25++) {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 10, 7, 15, 7, 0, ((i + WRedclor) - 2) + (i25 * 5), i2 + 16, 0, true);
                }
            }
            mgraphics.restoreCanvas();
        }
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
        AvMain.Font3dColor(mgraphics, new StringBuffer().append(GameScreen.player.name.toUpperCase()).append(" Lv: ").append((int) GameScreen.player.Lv).toString(), i + 2, i2 + 24, 0, (byte) 0);
        if (!GameScreen.player.overHP) {
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(GameScreen.player.hp).append("/").append(GameScreen.player.maxHp).toString(), i12 / 2, i2 + 2, 2, true);
        } else {
            mFont mfont2 = mFont.tahoma_7_white;
            if (MainObject.countmp > 5) {
                mfont2 = mFont.tahoma_7_red;
            }
            mfont2.drawString(mgraphics, new StringBuffer().append(GameScreen.player.hp).append("/").append(GameScreen.player.maxHp).toString(), i12 / 2, i2 + 2, 2, true);
        }
        if (!GameScreen.player.overMP) {
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(GameScreen.player.mp).append("/").append(GameScreen.player.maxMp).toString(), i12 / 2, i2 + 14, 2, false);
        } else {
            mFont mfont3 = mFont.tahoma_7_white;
            if (MainObject.countmp > 5) {
                mfont3 = mFont.tahoma_7_blue;
            }
            mfont3.drawString(mgraphics, new StringBuffer().append(GameScreen.player.mp).append("/").append(GameScreen.player.maxMp).toString(), i12 / 2, i2 + 14, 2, true);
        }
        if (timeThachdau - (mSystem.currentTimeMillis() / 1000) > 0) {
            long currentTimeMillis2 = timeThachdau - (mSystem.currentTimeMillis() / 1000);
            mgraphics.drawRegion(AvMain.imgBackInfo, 0, 0, 140, 20, 0, GameCanvas.w / 2, i2 + 35, 3, false);
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.TimeThachDau).append(currentTimeMillis2).toString(), GameCanvas.w / 2, i2 + 30, 2, false);
        }
    }

    public void paintInfoThachDauOtherPlayer(mGraphics mgraphics, int i, int i2) {
        if (!isMapThachdau()) {
            return;
        }
        if (this.idCharLoiDai2 != -1) {
            MainObject findObjByteCat = GameScreen.findObjByteCat(this.idCharLoiDai2, (byte) 0);
            if (findObjByteCat != null) {
                GameCanvas.resetTrans(mgraphics);
                int i3 = (GameCanvas.w / 2) - 20;
                int i4 = i3 / (WBlackclolor / 2);
                if (i4 < 0) {
                    i4 = 1;
                }
                if (i4 == 1) {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, i, i2 + 3, 0, true);
                } else {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, i, i2 + 3, 0, true);
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, GameCanvas.w - WBlackclolor, i2 + 3, 0, true);
                    int i5 = (i3 - ((WBlackclolor - 2) * 2)) / 5;
                    if (i5 <= 0) {
                        i5 = 1;
                    }
                    for (int i6 = 0; i6 < i5 + 1; i6++) {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, ((i + WBlackclolor) - 4) + (i6 * 5), i2 + 3, 0, true);
                    }
                }
                if (findObjByteCat.hp > 0) {
                    int i7 = (int) ((findObjByteCat.hp * i3) / findObjByteCat.maxHp);
                    mgraphics.setClip(i + (i3 - i7), i2 + 4, (GameCanvas.w / 2) - (i3 - i7), 7);
                    mgraphics.saveCanvas();
                    mgraphics.ClipRec(i + (i3 - i7), i2 + 4, (GameCanvas.w / 2) - (i3 - i7), 7);
                    int i8 = i3 / (WRedclor / 2);
                    if (i8 < 0) {
                        i8 = 1;
                    }
                    if (i8 == 1) {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, i + 1, i2 + 4, 0, true);
                    } else {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, i + 1, i2 + 4, 0, true);
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, GameCanvas.w - WBlackclolor, i2 + 4, 0, true);
                        int i9 = (i3 - ((WRedclor - 2) * 2)) / 5;
                        if (i9 <= 0) {
                            i9 = 1;
                        }
                        for (int i10 = 0; i10 < i9 + 1; i10++) {
                            mgraphics.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, ((i + WRedclor) - 2) + (i10 * 5), i2 + 4, 0, true);
                        }
                    }
                }
                if (findObjByteCat.mp <= 0 || findObjByteCat.maxMp > 0) {
                }
                mgraphics.restoreCanvas();
                mgraphics.endClip();
                GameCanvas.resetTrans(mgraphics);
                AvMain.Font3dColor(mgraphics, new StringBuffer().append(findObjByteCat.name.toUpperCase()).append(" Lv: ").append((int) findObjByteCat.Lv).toString(), (i + i3) - 2, (i2 + 24) - 10, 1, (byte) 0);
                if (!findObjByteCat.overHP) {
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(findObjByteCat.hp).append("/").append(findObjByteCat.maxHp).toString(), i + (i3 / 2), i2 + 2, 2, true);
                    return;
                }
                mFont mfont = mFont.tahoma_7_white;
                if (MainObject.countmp > 5) {
                    mfont = mFont.tahoma_7_red;
                }
                mfont.drawString(mgraphics, new StringBuffer().append(findObjByteCat.hp).append("/").append(findObjByteCat.maxHp).toString(), i + (i3 / 2), i2 + 2, 2, true);
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus != null) {
            if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject != 0) {
                return;
            }
            GameCanvas.resetTrans(mgraphics);
            int i11 = (GameCanvas.w / 2) - 20;
            int i12 = i11 / (WBlackclolor / 2);
            if (i12 < 0) {
                i12 = 1;
            }
            if (i12 == 1) {
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor, 9, 0, i, i2 + 3, 0, true);
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor, 9, 0, i, i2 + 15, 0, false);
            } else {
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, WBlackclolor - 2, 9, 0, i, i2 + 3, 0, true);
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, WBlackclolor - 2, 9, 0, i, i2 + 15, 0, true);
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 2, 0, WBlackclolor - 2, 9, 0, GameCanvas.w - WBlackclolor, i2 + 3, 0, true);
                mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 2, 9, WBlackclolor - 2, 9, 0, GameCanvas.w - WBlackclolor, i2 + 15, 0, true);
                int i13 = (i11 - ((WBlackclolor - 2) * 2)) / 5;
                if (i13 <= 0) {
                    i13 = 1;
                }
                for (int i14 = 0; i14 < i13 + 1; i14++) {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 10, 0, 15, 9, 0, ((i + WBlackclolor) - 4) + (i14 * 5), i2 + 3, 0, true);
                    mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 10, 9, 15, 9, 0, ((i + WBlackclolor) - 4) + (i14 * 5), i2 + 15, 0, true);
                }
            }
            if (GameScreen.ObjFocus.hp > 0) {
                int i15 = (int) ((GameScreen.ObjFocus.hp * i11) / GameScreen.ObjFocus.maxHp);
                mgraphics.setClip(i + (i11 - i15), i2 + 4, (GameCanvas.w / 2) - (i11 - i15), 7);
                mgraphics.saveCanvas();
                mgraphics.ClipRec(i + (i11 - i15), i2 + 4, (GameCanvas.w / 2) - (i11 - i15), 7);
                int i16 = i11 / (WRedclor / 2);
                if (i16 < 0) {
                    i16 = 1;
                }
                if (i16 == 1) {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor, 7, 0, i + 1, i2 + 4, 0, true);
                } else {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, WRedclor - 2, 7, 0, i + 1, i2 + 4, 0, true);
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 2, 0, WRedclor - 2, 7, 0, GameCanvas.w - WBlackclolor, i2 + 4, 0, true);
                    int i17 = (i11 - ((WRedclor - 2) * 2)) / 5;
                    if (i17 <= 0) {
                        i17 = 1;
                    }
                    for (int i18 = 0; i18 < i17 + 1; i18++) {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 10, 0, 15, 7, 0, ((i + WRedclor) - 2) + (i18 * 5), i2 + 4, 0, true);
                    }
                }
                mgraphics.restoreCanvas();
            }
            if (GameScreen.ObjFocus.mp > 0 && GameScreen.ObjFocus.maxMp > 0) {
                int i19 = (int) ((GameScreen.ObjFocus.mp * i11) / GameScreen.ObjFocus.maxMp);
                mgraphics.setClip(i + (i11 - i19), i2 + 16, (GameCanvas.w / 2) - (i11 - i19), 7);
                mgraphics.saveCanvas();
                mgraphics.ClipRec(i + (i11 - i19), i2 + 16, (GameCanvas.w / 2) - (i11 - i19), 7);
                int i20 = i11 / (WRedclor / 2);
                if (i20 < 0) {
                    i20 = 1;
                }
                if (i20 == 1) {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor, 7, 0, i + 1, i2 + 16, 0, true);
                } else {
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 7, WRedclor - 2, 7, 0, i + 1, i2 + 16, 0, true);
                    mgraphics.drawRegion(AvMain.imgcolorhpmp, 2, 7, WRedclor - 2, 7, 0, GameCanvas.w - WBlackclolor, i2 + 16, 0, true);
                    int i21 = (i11 - ((WRedclor - 2) * 2)) / 5;
                    if (i21 <= 0) {
                        i21 = 1;
                    }
                    for (int i22 = 0; i22 < i21 + 1; i22++) {
                        mgraphics.drawRegion(AvMain.imgcolorhpmp, 10, 7, 15, 7, 0, ((i + WRedclor) - 2) + (i22 * 5), i2 + 16, 0, true);
                    }
                }
                mgraphics.restoreCanvas();
            }
            mgraphics.endClip();
            GameCanvas.resetTrans(mgraphics);
            AvMain.Font3dColor(mgraphics, new StringBuffer().append(GameScreen.ObjFocus.name.toUpperCase()).append(" Lv: ").append((int) GameScreen.ObjFocus.Lv).toString(), (i + i11) - 2, i2 + 24, 1, (byte) 0);
            if (!GameScreen.ObjFocus.overHP) {
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(GameScreen.ObjFocus.hp).append("/").append(GameScreen.ObjFocus.maxHp).toString(), i + (i11 / 2), i2 + 2, 2, true);
            } else {
                mFont mfont2 = mFont.tahoma_7_white;
                if (MainObject.countmp > 5) {
                    mfont2 = mFont.tahoma_7_red;
                }
                mfont2.drawString(mgraphics, new StringBuffer().append(GameScreen.ObjFocus.hp).append("/").append(GameScreen.ObjFocus.maxHp).toString(), i + (i11 / 2), i2 + 2, 2, true);
            }
            if (!GameScreen.ObjFocus.overMP) {
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(GameScreen.ObjFocus.mp).append("/").append(GameScreen.ObjFocus.maxMp).toString(), i + (i11 / 2), i2 + 14, 2, false);
                return;
            }
            mFont mfont3 = mFont.tahoma_7_white;
            if (MainObject.countmp > 5) {
                mfont3 = mFont.tahoma_7_blue;
            }
            mfont3.drawString(mgraphics, new StringBuffer().append(GameScreen.ObjFocus.mp).append("/").append(GameScreen.ObjFocus.maxMp).toString(), i + (i11 / 2), i2 + 14, 2, true);
        }
    }

    public void paintInfoPlayer(mGraphics mgraphics, int i, int i2, boolean z, mFont mfont) {
        if (isMapThachdau()) {
            return;
        }
        if (z) {
            mgraphics.drawRegion(AvMain.imgInfo, 0, 0, 16, 42, 0, i + 1, i2 + 2, 0, false);
            mgraphics.drawRegion(AvMain.imgInfo, 0, 84, 16, 42, 0, i + 96, i2 + 2, 24, false);
            for (int i3 = 0; i3 < 4; i3++) {
                mgraphics.drawRegion(AvMain.imgInfo, 0, 42, 16, 42, 0, i + 17 + (16 * i3), i2 + 2, 0, false);
            }
            i += 8;
            i2 += 4;
        }
        mgraphics.drawImage(AvMain.imghpmp, i + 2, i2 + 3, 0, false);
        mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 0, 62, 9, 0, i + 19, i2 + 3, 0, false);
        mgraphics.drawRegion(AvMain.imgcolorhpmp_back, 0, 9, 62, 9, 0, i + 19, i2 + 15, 0, false);
        if (GameScreen.player.hp > 0) {
            int i4 = (GameScreen.player.hp * 60) / GameScreen.player.maxHp;
            if (i4 <= 0) {
                i4 = 1;
            } else if (i4 > 60) {
                i4 = 60;
            }
            mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 0, i4, 7, 0, i + 20, i2 + 4, 0, false);
        }
        if (GameScreen.player.mp > 0) {
            int i5 = (GameScreen.player.mp * 60) / GameScreen.player.maxMp;
            if (i5 <= 0) {
                i5 = 1;
            } else if (i5 > 60) {
                i5 = 60;
            }
            mgraphics.drawRegion(AvMain.imgcolorhpmp, 0, 7, i5, 7, 0, i + 20, i2 + 16, 0, false);
        }
        mfont.drawString(mgraphics, new StringBuffer().append("Lv.").append((int) GameScreen.player.Lv).append(" + ").append(GameScreen.player.phantramLv / 10).append(",").append(GameScreen.player.phantramLv % 10).append("%").toString(), i + 3, i2 + 24, 0, false);
        if (GameScreen.player.phantramLv > 0) {
            int i6 = ((GameScreen.player.phantramLv / 10) * 77) / 100;
            mgraphics.setColor(-13263058);
            mgraphics.fillRect(i + 3, i2 + 35, i6, 2, false);
        }
        if (!GameScreen.player.overHP) {
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(GameScreen.player.hp).append("/").append(GameScreen.player.maxHp).toString(), i + 50, i2 + 2, 2, false);
        } else {
            mFont mfont2 = mFont.tahoma_7_white;
            if (MainObject.countmp > 5) {
                mfont2 = mFont.tahoma_7_red;
            }
            mfont2.drawString(mgraphics, new StringBuffer().append(GameScreen.player.hp).append("/").append(GameScreen.player.maxHp).toString(), i + 50, i2 + 2, 2, false);
        }
        if (!GameScreen.player.overMP) {
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(GameScreen.player.mp).append("/").append(GameScreen.player.maxMp).toString(), i + 50, i2 + 14, 2, false);
            return;
        }
        mFont mfont3 = mFont.tahoma_7_white;
        if (MainObject.countmp > 5) {
            mfont3 = mFont.tahoma_7_blue;
        }
        mfont3.drawString(mgraphics, new StringBuffer().append(GameScreen.player.mp).append("/").append(GameScreen.player.maxMp).toString(), i + 50, i2 + 14, 2, false);
    }

    public void paintInfoChar(mGraphics mgraphics) {
        GameCanvas.resetTrans(mgraphics);
        int i = yBeginInfo;
        int i2 = 0;
        if (GameCanvas.isTouch) {
            i2 = 25;
        }
        if (this.strInfoServer != null) {
            mgraphics.setClip(GameCanvas.hw - (wInfoServer / 2), i, wInfoServer - i2, 20);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(GameCanvas.hw - (wInfoServer / 2), i, wInfoServer - i2, 20);
            for (int i3 = 0; i3 < (wInfoServer / 140) + 1; i3++) {
                if (i3 == wInfoServer / 140) {
                    mgraphics.drawRegion(AvMain.imgBackInfo, 0, 0, wInfoServer % 140, 20, 0, (GameCanvas.hw - (wInfoServer / 2)) + (i3 * 140), i, 0, false);
                } else {
                    mgraphics.drawImage(AvMain.imgBackInfo, (GameCanvas.hw - (wInfoServer / 2)) + (i3 * 140), i, 0, false);
                }
            }
            mFont.tahoma_7_yellow.drawString(mgraphics, this.strInfoServer, (GameCanvas.hw + (wInfoServer / 2)) - this.xPaintInfo, i + 4, 0, true);
            i += this.hImgInfo;
            mgraphics.restoreCanvas();
            mgraphics.endClip();
        }
        if (this.strInfoCharServer != null) {
            mgraphics.setClip(GameCanvas.hw - (wInfoServer / 2), i, wInfoServer - i2, 20);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(GameCanvas.hw - (wInfoServer / 2), i, wInfoServer - i2, 20);
            for (int i4 = 0; i4 < (wInfoServer / 140) + 1; i4++) {
                if (i4 == wInfoServer / 140) {
                    mgraphics.drawRegion(AvMain.imgBackInfo, 0, 0, wInfoServer % 140, 20, 0, (GameCanvas.hw - (wInfoServer / 2)) + (i4 * 140), i, 0, false);
                } else {
                    mgraphics.drawImage(AvMain.imgBackInfo, (GameCanvas.hw - (wInfoServer / 2)) + (i4 * 140), i, 0, false);
                }
            }
            mFont.tahoma_7b_white.drawString(mgraphics, this.strInfoCharServer, (GameCanvas.hw + (wInfoServer / 2)) - this.xPaintInfoChar, i + 4, 0, true);
            i += this.hImgInfo;
            mgraphics.restoreCanvas();
            mgraphics.endClip();
        }
        if (this.strInfoCharCline != null) {
            mgraphics.setClip(GameCanvas.hw - 70, i, 140, 20);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(GameCanvas.hw - 70, i, 140, 20);
            mgraphics.drawImage(AvMain.imgBackInfo, GameCanvas.hw - 70, i + this.ydInfoChar, 0, false);
            mFont.tahoma_7_white.drawString(mgraphics, this.strInfoCharCline, GameCanvas.hw, i + 4 + this.ydInfoChar, 2, true);
            mgraphics.restoreCanvas();
            mgraphics.endClip();
        }
        GameCanvas.resetTrans(mgraphics);
    }

    public void PaintIconPlayer(mGraphics mgraphics) {
        int i = 102;
        int i2 = 8 - hShowInGame;
        if (GameCanvas.isSmallScreen) {
            i = 90;
            i2 = 7 - hShowInGame;
        }
        if (Player.diemTiemNang > 0 && !isMapThachdau()) {
            fralevelup.drawFrame((GameCanvas.gameTick / 4) % 2, i, i2, 0, 3, mgraphics);
        }
        if (Player.diemKyNang > 0 && !isMapThachdau()) {
            fralevelup.drawFrame(2 + ((GameCanvas.gameTick / 4) % 2), i, i2 + 14, 0, 3, mgraphics);
        }
        if (Player.isAutoFire > -1) {
            mgraphics.drawImage(imgauto, i + 1, i2 + 28, 3, false);
        }
        if (Player.typeX2 == 1) {
            if (GameCanvas.gameTick % 200 < 100) {
                mgraphics.drawImage(imgxp, i + 1, i2 + 42, 3, false);
            } else {
                mFont.tahoma_7_green.drawString(mgraphics, getTimex2(), i - 7, i2 + 36, 0, false);
            }
        }
    }

    public String getTimex2() {
        if (Player.timeX2 > 0 && (GameCanvas.timeNow - Player.timeSetX2) / 1000 > 60) {
            Player.timeSetX2 += 60000;
            Player.timeX2--;
        }
        return getStringTime(Player.timeX2);
    }

    public static String getStringTime(int i) {
        if (i >= 60) {
            return new StringBuffer().append(i / 60).append("h").append(i % 60).append("'").toString();
        }
        return new StringBuffer().append(i).append("'").toString();
    }

    public static String getStringTime2(int i) {
        return new StringBuffer().append(i / 60).append("'").toString();
    }

    public void updateInfoServer() {
        if (GameScreen.VecInfoServer.size() > 0) {
            if (this.strInfoServer == null) {
                this.strInfoServer = (String) GameScreen.VecInfoServer.elementAt(0);
                if (this.strInfoServer != null && this.strInfoServer.trim().length() > 0) {
                    GameCanvas.msgchat.addNewChat(T.tinden, T.text2kenhthegioi, this.strInfoServer, (byte) 1, false);
                }
                int size = GameScreen.VecInfoServer.size();
                if (size < 2) {
                    this.speedInfo = 2;
                } else if (size < 5) {
                    this.speedInfo = 3;
                } else {
                    this.speedInfo = 4;
                }
                this.xPaintInfo = 0;
                this.xmaxInfo = mFont.tahoma_7_white.getWidth(this.strInfoServer) + wInfoServer;
                if (this.xmaxInfo < wInfoServer) {
                    this.xmaxInfo = wInfoServer;
                    return;
                }
                return;
            }
            if (this.xPaintInfo >= this.xmaxInfo) {
                this.timepaintServer++;
                this.timepaintServer = 0;
                this.strInfoServer = null;
                GameScreen.VecInfoServer.removeElementAt(0);
            }
            this.xPaintInfo += this.speedInfo;
        }
    }

    public void updateInfoCharServer() {
        if (GameScreen.VecInfoChar.size() > 0) {
            if (this.strInfoCharServer == null) {
                this.strInfoCharServer = (String) GameScreen.VecInfoChar.elementAt(0);
                int size = GameScreen.VecInfoChar.size();
                if (size < 2) {
                    this.speedInfoChar = 2;
                } else if (size < 5) {
                    this.speedInfoChar = 3;
                } else {
                    this.speedInfoChar = 4;
                }
                this.xPaintInfoChar = 0;
                this.xmaxInfoChar = mFont.tahoma_7b_white.getWidth(this.strInfoCharServer) + wInfoServer;
                if (this.xmaxInfoChar < wInfoServer) {
                    this.xmaxInfoChar = wInfoServer;
                    return;
                }
                return;
            }
            if (this.xPaintInfoChar >= this.xmaxInfoChar) {
                this.timePaintInfoChar = 0;
                this.strInfoCharServer = null;
                GameScreen.VecInfoChar.removeElementAt(0);
            }
            this.xPaintInfoChar += this.speedInfoChar;
        }
    }

    public void paintInfoFocus(mGraphics mgraphics) {
        if (isMapThachdau()) {
            return;
        }
        if ((isPaintInfoFocus || !isLevelPoint) && GameScreen.ObjFocus != null) {
            int i = (yFocus - hShowInGame) + 2;
            int i2 = xFocus;
            if (isMapchienthanh()) {
                i2 = GameCanvas.w - 62;
                i = (yFocus - hShowInGame) + 2 + GameCanvas.hText;
            }
            MainObject mainObject = GameScreen.ObjFocus;
            if (mainObject.typeObject == 3) {
                AvMain.Font3dColor(mgraphics, mainObject.name, i2 + 48, i + 2, 1, mainObject.colorName);
            } else if (mainObject.typeObject == 0 || mainObject.typeObject == 1) {
                if (mainObject.myClan != null && mainObject.typeSpec == 0) {
                    mainObject.paintIconClan(mgraphics, (i2 + 48) - ((mFont.tahoma_7b_white.getWidth(mainObject.name) + 1) / 2), i + 7, 2);
                    i += 12;
                }
                if (mainObject.typeObject == 1) {
                    AvMain.Font3dColorAndColor(mgraphics, mainObject.name, i2 + 48, i + 2, 1, (byte) 7, mainObject.colorName);
                } else {
                    AvMain.Font3dWhite(mgraphics, mainObject.name, i2 + 48, i + 2, 1);
                }
                i += 10;
                AvMain.Font3dColorAndColor(mgraphics, new StringBuffer().append(T.Lv).append((int) mainObject.Lv).toString(), i2 + 48, i + 2, 1, (byte) 7, mainObject.colorName);
            } else {
                AvMain.Font3dWhite(mgraphics, mainObject.name, i2 + 48, i + 2, 1);
            }
            if (mainObject.typeObject == 0 || mainObject.typeObject == 1 || mainObject.typeObject == 2) {
                mgraphics.drawImage(AvMain.imgcolorhpSmall_back, i2 - 4, i + 14, 0, false);
                if (mainObject.maxHp > 0 && mainObject.hp > 0) {
                    int i3 = (int) ((50 * mainObject.hp) / mainObject.maxHp);
                    if (i3 <= 0) {
                        i3 = 1;
                    } else if (i3 > 50) {
                        i3 = 50;
                    }
                    mgraphics.drawRegion(AvMain.imgcolorhpSmall, 0, 0, i3 + 1, 7, 0, i2 - 4, i + 14, 0, false);
                }
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(mainObject.hp).append("/").append(mainObject.maxHp).toString(), i2 + 48, i + 20, 1, false);
            }
        }
    }

    public void updateInfoChar() {
        if (this.strInfoCharCline != null) {
            this.timeInfoCharCline++;
            if (this.timeInfoCharCline >= 120) {
                this.timeInfoCharCline = 0;
                this.strInfoCharCline = null;
            }
            if (this.ydInfoChar > 0) {
                this.ydInfoChar -= 2;
            }
        }
        if (!GameCanvas.isTouch && timeChange > 0) {
            timeChange++;
            if (timeChange > 8) {
                timeChange = 0;
                Player.levelTab++;
                if (Player.levelTab > 2) {
                    Player.levelTab = 0;
                }
            }
        }
        if (timeNameMap > 0) {
            if (timeNameMap == 20) {
                vyNameMap = 10;
            } else if (timeNameMap < 20 && vyNameMap > -20) {
                vyNameMap -= 4;
            }
            if (yNameMap > -30) {
                yNameMap += vyNameMap;
            } else {
                timeNameMap = 0;
            }
        }
    }

    public void updateEvent() {
        if (EventScreen.vecEventShow.size() > 0) {
            if (this.eventShow == null) {
                this.eventShow = (MainEvent) EventScreen.vecEventShow.elementAt(0);
                this.timeEvent = 100;
                this.hShowEvent = 0;
            } else {
                this.timeEvent--;
                if (this.timeEvent <= 0) {
                    this.eventShow = null;
                    EventScreen.vecEventShow.removeElementAt(0);
                }
                if (this.hShowEvent < 35) {
                    this.hShowEvent += 10;
                }
                if (this.hShowEvent > 35) {
                    this.hShowEvent = 35;
                }
            }
            if (GameCanvas.isPointSelect(GameCanvas.hw - (wShowEvent / 2), 0, wShowEvent, 35)) {
                MainEvent event = EventScreen.setEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (event != null) {
                    GameCanvas.mevent.doEvent(false, event);
                }
                if (this.timeEvent > 40) {
                    this.timeEvent = 40;
                }
                GameCanvas.isPointerSelect = false;
            }
            if (GameCanvas.keyMyHold[11]) {
                GameCanvas.clearKeyHold(11);
                int indexEvent = EventScreen.setIndexEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (indexEvent >= 0) {
                    GameCanvas.mevent.idSelect = indexEvent;
                }
                GameCanvas.mevent.init();
                GameCanvas.mevent.Show(GameCanvas.currentScreen);
                return;
            }
            return;
        }
        if (this.hShowEvent > 0) {
            this.hShowEvent -= 20;
        }
    }

    public void paintShowEvent(mGraphics mgraphics) {
        if (this.eventShow != null || this.hShowEvent > 0) {
            GameCanvas.resetTrans(mgraphics);
            int i = GameCanvas.hw - (wShowEvent / 2);
            int i2 = 2;
            if (GameCanvas.gameTick % 16 > 7) {
                i2 = 12;
            }
            AvMain.paintDialogNew(mgraphics, i, -5, wShowEvent, this.hShowEvent + 5, i2);
            if (this.eventShow != null) {
                fraEvent.drawFrame(this.eventShow.IDCmd * 2, i + 20, (-35) + this.hShowEvent + 17 + 3, 0, 3, mgraphics);
                mFont.tahoma_7b_white.drawString(mgraphics, this.eventShow.nameEvent, i + 35, (-35) + this.hShowEvent + 5, 0, false);
                mFont.tahoma_7_white.drawString(mgraphics, this.eventShow.contentEvent, i + 42, (-35) + this.hShowEvent + 18, 0, false);
            }
        }
    }

    public void paintPoiterAll(mGraphics mgraphics) {
        Item itemInventory;
        int i;
        int i2;
        Item itemInventory2;
        String stringBuffer;
        int i3;
        int i4;
        if (!GameCanvas.isTouch) {
            return;
        }
        for (int i5 = 0; i5 < mPosOther.length; i5++) {
            if (i5 != 3 || !isLevelPoint) {
                int i6 = 0;
                if (timePointer > 0 && keyPoint == 100 + i5) {
                    i6 = 1;
                }
                if (GameScreen.player.Action != 4) {
                    int i7 = mPosOther[i5][0];
                    int i8 = mPosOther[i5][1];
                    if (i5 == 0) {
                        i8 -= hShowInGame;
                    } else if (i5 == 1) {
                        i7 -= hShowInGame;
                    } else if (i5 == 4) {
                        i8 += hShowInGame;
                    } else {
                        i7 += hShowInGame;
                    }
                    boolean z = true;
                    if (i5 == 0 && GameScreen.player.Lv > 10) {
                        z = false;
                    }
                    if (z) {
                        if (i5 != 1) {
                            mgraphics.drawRegion(imgOther[i5], 0, i6 * mSizeImgOther[i5][1], mSizeImgOther[i5][0], mSizeImgOther[i5][1], 0, i7, i8, 0, false);
                        } else {
                            mgraphics.drawRegion(imgOther[i5], 0, 0, mSizeImgOther[i5][0], mSizeImgOther[i5][1], 0, i7, i8, 0, true);
                            if (this.timeDownChat != -1 && mSystem.currentTimeMillis() - this.timeDownChat >= 100) {
                                if (GameCanvas.gameTick % 2 == 0) {
                                    this.hClip++;
                                }
                                if (this.hClip >= mSizeImgOther[i5][1]) {
                                    this.hClip = mSizeImgOther[i5][1];
                                }
                            }
                            mgraphics.setClip(i7, i8, mSizeImgOther[i5][0], this.hClip);
                            mgraphics.saveCanvas();
                            mgraphics.ClipRec(i7, i8, mSizeImgOther[i5][0], this.hClip);
                            mgraphics.drawRegion(imgOther[i5], 0, mSizeImgOther[i5][1], mSizeImgOther[i5][0], mSizeImgOther[i5][1], 0, i7, i8, 0, true);
                            mgraphics.restoreCanvas();
                            GameCanvas.resetTrans(mgraphics);
                        }
                    }
                }
            }
        }
        if (GameScreen.player.Action == 4) {
            return;
        }
        if (isLevelPoint) {
            paintKillPlayer(mgraphics);
            return;
        }
        mgraphics.drawImage(imgMove[0], xPointMove - hShowInGame, yPointMove, 3, false);
        int i9 = 0;
        while (i9 < 4) {
            if (timePointer > 0 && this.mKeyMove[i9] == keyPoint) {
                mgraphics.drawRegion(imgMove[2], 0, 0, 32, 56, this.mRotateMove[i9] + (i9 > 1 ? 4 : 0), mPosMove[i9][0] - hShowInGame, mPosMove[i9][1], 3, false);
            }
            i9++;
        }
        int i10 = 0;
        if (timePointer > 0 && keyPoint == 5) {
            i10 = 1;
        }
        mgraphics.drawImage(AvMain.imgHotKey, xPointKill + hShowInGame, yPointKill, 3, false);
        if (timeChange == 0) {
            if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2) {
                int i11 = 0;
                while (i11 < 5) {
                    int i12 = 0;
                    if (timePointer > 0 && keyPoint == mValueHotKey[i11]) {
                        i12 = 1;
                    }
                    if (i11 == 2) {
                        i3 = xPointKill + hShowInGame;
                        i4 = yPointKill;
                    } else {
                        i3 = mPosKill[i11 - (i11 > 2 ? 1 : 0)][0] + hShowInGame;
                        i4 = mPosKill[i11 - (i11 > 2 ? 1 : 0)][1];
                    }
                    int i13 = i4;
                    mgraphics.drawRegion(imgFire[1], 0, i12, 50, 50, 0, i3, i13, 3, false);
                    mgraphics.drawImage(AvMain.imgHotKey, i3, i13, 3, false);
                    AvMain.Font3dWhite(mgraphics, new StringBuffer().append(mValueHotKey[i11]).append("").toString(), i3, i13 - 5, 2);
                    i11++;
                }
                return;
            }
            HotKey hotKey = Player.mhotkey[Player.levelTab][2];
            if (GameScreen.player.checkGiaoTiep()) {
                mgraphics.drawImage(AvMain.imgicongt, xPointKill + hShowInGame, yPointKill, 3, false);
            } else if (hotKey != null && hotKey.type == HotKey.SKILL) {
                Skill skillFormId = MainListSkill.getSkillFormId(hotKey.id);
                if (skillFormId != null) {
                    skillFormId.paint(mgraphics, xPointKill + hShowInGame, yPointKill, 3);
                }
            } else if (hotKey != null && hotKey.type == HotKey.POTION && (itemInventory = Item.getItemInventory(4, hotKey.id)) != null) {
                itemInventory.paintItem(mgraphics, xPointKill + hShowInGame, yPointKill, MainTabNew.wOneItem, 0, 3);
            }
            int i14 = 0;
            while (i14 < mPosKill.length) {
                int i15 = 0;
                if (timePointer > 0 && keyPoint == mKeySkill[i14]) {
                    i15 = 1;
                }
                mgraphics.drawRegion(imgFire[1], 0, i15 * 50, 50, 50, 0, mPosKill[i14][0] + hShowInGame, mPosKill[i14][1], 3, false);
                mgraphics.drawImage(AvMain.imgHotKey, mPosKill[i14][0] + hShowInGame, mPosKill[i14][1], 3, false);
                HotKey hotKey2 = Player.mhotkey[Player.levelTab][i14 + (i14 > 1 ? 1 : 0)];
                if (hotKey2 != null && hotKey2.type == HotKey.SKILL) {
                    Skill skillFormId2 = MainListSkill.getSkillFormId(hotKey2.id);
                    if (skillFormId2 != null) {
                        skillFormId2.paint(mgraphics, mPosKill[i14][0] + hShowInGame, mPosKill[i14][1], 3);
                    }
                } else if (hotKey2 != null && hotKey2.type == HotKey.POTION && MainTemplateItem.isload) {
                    Item itemInventory3 = Item.getItemInventory(4, hotKey2.id);
                    if (itemInventory3 != null) {
                        if (MainTemplateItem.isload) {
                            itemInventory3.paintItem(mgraphics, mPosKill[i14][0] + hShowInGame, mPosKill[i14][1], MainTabNew.wOneItem, 0, 3);
                        }
                    } else {
                        hotKey2.setHotKey(0, HotKey.NULL, (byte) 0);
                        MainItem.setAddHotKey((byte) 1, false);
                        MainItem.setAddHotKey((byte) 0, false);
                    }
                }
                i14++;
            }
            int i16 = 0;
            while (i16 < 5) {
                HotKey hotKey3 = Player.mhotkey[Player.levelTab][i16];
                if (hotKey3 != null && hotKey3.type != HotKey.NULL) {
                    if (i16 == 2) {
                        i = xPointKill + hShowInGame;
                        i2 = yPointKill;
                    } else {
                        i = mPosKill[i16 - (i16 > 2 ? 1 : 0)][0] + hShowInGame;
                        i2 = mPosKill[i16 - (i16 > 2 ? 1 : 0)][1];
                    }
                    DelaySkill delaySkill = null;
                    if (hotKey3 != null && hotKey3.type == HotKey.SKILL) {
                        delaySkill = Player.timeDelaySkill[hotKey3.id];
                    } else if (hotKey3 != null && hotKey3.type == HotKey.POTION && MainTemplateItem.isload && (itemInventory2 = Item.getItemInventory(4, hotKey3.id)) != null && itemInventory2.typePotion < 2) {
                        delaySkill = Player.timeDelayPotion[itemInventory2.typePotion];
                    }
                    if (delaySkill != null && delaySkill.limit > 0) {
                        if (delaySkill.value > 0) {
                            int i17 = (delaySkill.value * 20) / delaySkill.limit;
                            if (i17 < 1) {
                                i17 = 1;
                            }
                            mgraphics.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, i17, 0, i - 10, i2 - 10, 0, false);
                            int i18 = delaySkill.value / ObjectData.MONSTER;
                            if (i18 == 0) {
                                stringBuffer = new StringBuffer().append("0.").append((delaySkill.value % ObjectData.MONSTER) / 100).toString();
                            } else {
                                stringBuffer = new StringBuffer().append("").append(i18).toString();
                            }
                            mFont.tahoma_7b_white.drawString(mgraphics, stringBuffer, i, i2 - 5, 2, false);
                        } else if (delaySkill.value > -150) {
                            mgraphics.setColor(-1118516);
                            mgraphics.fillRoundRect(i - 10, i2 - 10, 20, 20, 4, 4, false);
                        }
                    }
                }
                i16++;
            }
        } else {
            paintChangeTab(mgraphics);
        }
        mgraphics.drawRegion(imgFire[0], 0, i10 * 50, 50, 50, 0, xPointKill + hShowInGame, yPointKill, 3, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updatePoiterAll() {
        Object[] objArr;
        if (timePointer > 0) {
            timePointer--;
        }
        if (timeChange > 0) {
            timeChange++;
            if (timeChange > 6) {
                timeChange = 0;
                Player.levelTab++;
                if (Player.levelTab > 2) {
                    Player.levelTab = 0;
                }
            }
        }
        if (LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) {
            return;
        }
        Object[] objArr2 = true;
        if (GameCanvas.isPointerSelect) {
            if (isLevelPoint) {
                if (GameCanvas.isPoint((xPaintSkill + 11) - (wSkill / 2), (yPaintSkill + 11) - (wSkill / 2), 5 * wSkill, wSkill)) {
                    int i = ((GameCanvas.px - xPaintSkill) + 11) / wSkill;
                    GameCanvas.isPointerSelect = false;
                    if (i >= 0 && i < Player.mhotkey[Player.levelTab].length) {
                        HotKey hotKey = Player.mhotkey[Player.levelTab][i];
                        if (isLevelPoint) {
                            if (hotKey.type == HotKey.SKILL) {
                                if (GameScreen.ObjFocus != null) {
                                    GameScreen.player.setActionHotKey(i, false);
                                }
                            } else {
                                if (i == 2) {
                                    keyPoint = 5;
                                } else {
                                    keyPoint = mKeySkill[i > 1 ? i - 1 : i];
                                }
                                GameCanvas.keyMyPressed[20 + keyPoint] = true;
                            }
                        }
                        timePointer = 3;
                        objArr2 = false;
                    }
                }
            } else if (GameCanvas.isPoint(xPointKill - (wMainSkill / 2), yPointKill - (wMainSkill / 2), wMainSkill, wMainSkill)) {
                GameCanvas.isPointerSelect = false;
                keyPoint = 5;
                timePointer = 3;
                GameCanvas.keyMyPressed[25] = true;
                GameCanvas.keyMyPressed[5] = true;
                objArr2 = false;
            } else {
                int i2 = 0;
                while (true) {
                    if (i2 >= mPosKill.length) {
                        break;
                    }
                    if (!GameCanvas.isPoint(mPosKill[i2][0] - (wSkill / 2), mPosKill[i2][1] - (wSkill / 2), wSkill, wSkill)) {
                        i2++;
                    } else {
                        GameCanvas.isPointerSelect = false;
                        keyPoint = mKeySkill[i2];
                        GameCanvas.keyMyPressed[20 + keyPoint] = true;
                        timePointer = 3;
                        objArr2 = false;
                        break;
                    }
                }
            }
            if (GameCanvas.isPointerSelect) {
                int i3 = 0;
                while (true) {
                    if (i3 >= mPosOther.length) {
                        break;
                    }
                    if ((i3 == 3 && isLevelPoint) || !GameCanvas.isPoint(mPosOther[i3][0] - 2, mPosOther[i3][1] - 2, mSizeImgOther[i3][0] + 4, mSizeImgOther[i3][1] + 4)) {
                        i3++;
                    } else {
                        GameCanvas.isPointerSelect = false;
                        if (i3 == 1 && this.timeDownChat == -1) {
                            this.timeDownChat = mSystem.currentTimeMillis();
                        }
                        selectPointer(i3);
                        objArr2 = false;
                    }
                }
                if (GameCanvas.isPoint(MainTabNew.gI().xChar, MainTabNew.gI().yChar, 90, 35)) {
                    GameCanvas.isPointerSelect = false;
                    selectPointer(0);
                    return;
                }
                if (GameCanvas.isPoint(xMess - 4, yMess - 4, 24, 20)) {
                    GameCanvas.isPointerSelect = false;
                    selectPointer(-1);
                }
                int i4 = GameCanvas.w - 50;
                int i5 = GameCanvas.minimap.maxY;
                MiniMap miniMap = GameCanvas.minimap;
                if (GameCanvas.isPoint(i4, (i5 * MiniMap.wMini) - 8, 50, 30)) {
                    GameCanvas.isPointerSelect = false;
                    selectPointer(-2);
                } else {
                    int i6 = GameCanvas.w;
                    int i7 = GameCanvas.minimap.maxX;
                    MiniMap miniMap2 = GameCanvas.minimap;
                    int i8 = i6 - (i7 * MiniMap.wMini);
                    int i9 = GameCanvas.minimap.maxX;
                    MiniMap miniMap3 = GameCanvas.minimap;
                    int i10 = i9 * MiniMap.wMini;
                    int i11 = GameCanvas.minimap.maxY;
                    MiniMap miniMap4 = GameCanvas.minimap;
                    if (GameCanvas.isPoint(i8, 0, i10, i11 * MiniMap.wMini) && !isMapThachdau()) {
                        GameCanvas.isPointerSelect = false;
                        selectPointer(-4);
                    }
                }
                if (GameCanvas.isPoint(95, 0, 24, 40)) {
                    GameCanvas.isPointerSelect = false;
                    selectPointer(-3);
                }
                int i12 = 0;
                while (true) {
                    if (i12 >= LoadMap.vecPointChange.size()) {
                        break;
                    }
                    Point point = (Point) LoadMap.vecPointChange.elementAt(i12);
                    if (!GameCanvas.isPoint((point.x - MainScreen.cameraMain.xCam) - 12, (point.y - MainScreen.cameraMain.yCam) - 12, 24, 24)) {
                        i12++;
                    } else {
                        GameScreen.player.toX = GameScreen.player.x;
                        GameScreen.player.toY = GameScreen.player.y;
                        this.posTam = GameCanvas.game.updateFindRoad(point.x / LoadMap.wTile, point.y / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 16);
                        if (this.posTam != null && this.posTam.length > 16) {
                            this.posTam = null;
                        }
                        GameScreen.player.posTransRoad = this.posTam;
                        Player.xFocus = point.x;
                        Player.yFocus = point.y;
                        Player.timeFocus = 9;
                        GameCanvas.isPointerSelect = false;
                        if (Player.isAutoFire == 1) {
                            Player.setCurAutoFire();
                        }
                    }
                }
            }
        } else if (!isLevelPoint) {
            if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
                int i13 = 0;
                while (true) {
                    if (i13 >= mPosOther.length) {
                        break;
                    }
                    if (!GameCanvas.isPoint(mPosOther[i13][0] - 4, mPosOther[i13][1] - 4, mSizeImgOther[i13][0] + 8, mSizeImgOther[i13][1] + 8)) {
                        i13++;
                    } else {
                        if (i13 == 1 && this.timeDownChat == -1) {
                            this.timeDownChat = mSystem.currentTimeMillis();
                        }
                        keyPoint = 100 + i13;
                        timePointer = 3;
                    }
                }
                if (GameCanvas.isPointLast(xPointMove - (2 * wArrowMove), yPointMove - (2 * wArrowMove), wArrowMove * 4, wArrowMove * 4)) {
                    int angle = CRes.angle(GameCanvas.px - xPointMove, GameCanvas.py - yPointMove);
                    if (angle > 45 && angle <= 135) {
                        objArr = 3;
                    } else if (angle > 135 && angle <= 225) {
                        objArr = false;
                    } else if (angle > 225 && angle <= 315) {
                        objArr = 2;
                    } else {
                        objArr = true;
                    }
                    GameCanvas.clearKeyHold();
                    GameCanvas.isPointerDown = true;
                    GameCanvas.isPointerSelect = false;
                    keyPoint = this.mKeyMove[objArr == true ? 1 : 0];
                    GameCanvas.keyMyHold[keyPoint] = true;
                    timePointer = 3;
                    objArr2 = false;
                    if (Player.isAutoFire == 1) {
                        Player.setCurAutoFire();
                    }
                }
            }
        } else if (isLevelPoint && (GameCanvas.isPointerDown || GameCanvas.isPointerMove)) {
            int i14 = 0;
            while (true) {
                if (i14 >= mPosOther.length) {
                    break;
                }
                if (!GameCanvas.isPoint(mPosOther[i14][0] - 4, mPosOther[i14][1] - 4, mSizeImgOther[i14][0] + 8, mSizeImgOther[i14][1] + 8)) {
                    i14++;
                } else {
                    if (i14 == 1 && this.timeDownChat == -1) {
                        this.timeDownChat = mSystem.currentTimeMillis();
                    }
                    keyPoint = 100 + i14;
                    timePointer = 3;
                }
            }
        }
        if (objArr2 != false) {
            updatePointMoveIngame();
        }
        if (isLevelPoint && GameCanvas.currentScreen == GameCanvas.game) {
            if (GameCanvas.isPointerMove) {
                if (!GameScreen.isMoveCamera && (CRes.abs(GameCanvas.px - GameCanvas.pxLast) > 36 || CRes.abs(GameCanvas.py - GameCanvas.pyLast) > 36)) {
                    GameScreen.isMoveCamera = true;
                }
                GameScreen.xMoveCam = GameCanvas.px - GameCanvas.pxLast;
                GameScreen.yMoveCam = GameCanvas.py - GameCanvas.pyLast;
                GameScreen.timeResetCam = 40;
                return;
            }
            if (GameCanvas.isPointerDown) {
                GameScreen.xCur = MainScreen.cameraMain.xCam;
                GameScreen.yCur = MainScreen.cameraMain.yCam;
                GameScreen.xMoveCam = 0;
                GameScreen.yMoveCam = 0;
            }
        }
    }

    public void updatePointMoveIngame() {
        if (GameCanvas.isPointerSelect) {
            if (!isLevelPoint && ((GameCanvas.isPoint((xPointKill - lSkill) - 25, (yPointKill - lSkill) - 25, (lSkill * 2) + 50, (lSkill * 2) + 50) || GameCanvas.isPoint((xPointMove - wArrowMove) - 30, (yPointMove - wArrowMove) - 30, (wArrowMove * 2) + 60, (wArrowMove * 2) + 60)) && GameScreen.player.Action != 4)) {
                GameCanvas.isPointerSelect = false;
                GameCanvas.isPointerDown = false;
                return;
            }
            int i = GameCanvas.px + MainScreen.cameraMain.xCam;
            int i2 = GameCanvas.py + MainScreen.cameraMain.yCam;
            MainObject mainObject = null;
            if ((GameScreen.ObjFocus == null || GameScreen.ObjFocus.typeBoss != 2) && (MainObject.getDistance(i, i2, GameScreen.player.x, GameScreen.player.y) <= GameScreen.player.wFocus - 15 || GameScreen.player.Action == 4)) {
                mainObject = setObjectNear(i, i2);
                if (mainObject != null && mainObject.typeObject != 1 && Player.isAutoFire == 1) {
                    Player.setCurAutoFire();
                }
            }
            if (this.isTouchFocus && GameCanvas.isTouch) {
                if (mainObject != null) {
                    if (!mainObject.canFocus()) {
                        return;
                    }
                    if (isMarket(GameCanvas.loadmap.idMap) && !mainObject.isSelling() && mainObject.typeObject == 0) {
                        return;
                    }
                    mainObject.timeStand = 5;
                    GameScreen.ObjFocus = mainObject;
                    GameCanvas.isPointerSelect = false;
                    if (MainObject.getDistance(mainObject.x, mainObject.y, GameScreen.player.x, GameScreen.player.y) <= GameScreen.player.wFocus) {
                        GameScreen.player.setPointFocus();
                        isPaintInfoFocus = true;
                        GameScreen.addEffectKill(68, GameScreen.player.ID, (byte) 0, GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject, 0, GameScreen.ObjFocus.hp, (byte) (GameScreen.ObjFocus.typeObject == 1 ? 0 : 1));
                        this.posTam = null;
                    }
                    if (GameCanvas.isPointerSelect) {
                        int tile = GameCanvas.loadmap.getTile(i, i2);
                        if (tile != -1 && tile != 1) {
                            GameScreen.player.toX = GameScreen.player.x;
                            GameScreen.player.toY = GameScreen.player.y;
                            this.posTam = GameCanvas.game.updateFindRoad(i / LoadMap.wTile, i2 / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 100);
                            if (this.posTam != null && this.posTam.length > 100) {
                                this.posTam = null;
                            }
                            this.timeMove = 3;
                            GameCanvas.isPointerSelect = false;
                            Player.xFocus = i;
                            Player.yFocus = i2;
                        } else {
                            this.posTam = null;
                        }
                    }
                } else {
                    if (GameScreen.player.Action == 4) {
                        return;
                    }
                    int tile2 = GameCanvas.loadmap.getTile(i, i2);
                    if (tile2 != -1 && tile2 != 1) {
                        GameScreen.player.toX = GameScreen.player.x;
                        GameScreen.player.toY = GameScreen.player.y;
                        this.posTam = GameCanvas.game.updateFindRoad(i / LoadMap.wTile, i2 / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 100);
                        if (this.posTam != null && this.posTam.length > 100) {
                            this.posTam = null;
                        }
                        if (GameScreen.player.posTransRoad != null) {
                            this.timeMove = 1;
                        } else {
                            this.timeMove = 3;
                        }
                        GameCanvas.isPointerSelect = false;
                        Player.xFocus = i;
                        Player.yFocus = i2;
                    } else {
                        this.posTam = null;
                        GameCanvas.isPointerSelect = false;
                    }
                }
            } else if (!isLevelPoint && mainObject != null && mainObject.typeObject != 9 && mainObject.typeObject != 10 && !mainObject.isLuaThieng() && !mainObject.isDongBang) {
                vecfocus.removeAllElements();
                if (GameScreen.ObjFocus == mainObject) {
                    if (Player.mhotkey[Player.levelTab][2].type == HotKey.SKILL) {
                        GameCanvas.keyMyPressed[25] = true;
                        GameCanvas.keyMyPressed[5] = true;
                    }
                    this.timeMove = 0;
                    this.posTam = null;
                } else {
                    if (!mainObject.canFocus()) {
                        return;
                    }
                    mainObject.timeStand = 5;
                    GameScreen.ObjFocus = mainObject;
                }
                GameCanvas.isPointerSelect = false;
            }
        }
        if (isLevelPoint && this.timeMove > 0) {
            if (this.timeMove == 1 && this.posTam != null && GameScreen.player.Action != 4 && GameScreen.player.Action != 2 && GameScreen.player.currentQuest == null) {
                GameScreen.player.xStopMove = 0;
                GameScreen.player.yStopMove = 0;
                if (GameScreen.player.posTransRoad != null) {
                    GameScreen.player.countAutoMove = 1;
                }
                GameScreen.player.resetMove();
                GameScreen.player.posTransRoad = this.posTam;
                this.posTam = null;
                Player.timeFocus = 9;
                if (Player.timeResetAuto <= 0 && Player.isAutoFire == 1) {
                    Player.setCurAutoFire();
                }
            }
            this.timeMove--;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0159 A[EDGE_INSN: B:68:0x0159->B:52:0x0159 BREAK  A[LOOP:1: B:44:0x0120->B:66:0x0153], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GameObjects.MainObject setObjectNear(int r8, int r9) {
        /*
            Method dump skipped, instructions count: 435
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: GameScreen.PaintInfoGameScreen.setObjectNear(int, int):GameObjects.MainObject");
    }

    public void paintChangeTab(mGraphics mgraphics) {
        Item itemInventory;
        int i = gocBegin;
        if (timeChange > 0) {
            i -= timeChange * 30;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            int cos = xPointKill + ((CRes.cos(CRes.fixangle(i)) * lSkill) / ObjectData.MONSTER) + hShowInGame;
            int sin = yPointKill + ((CRes.sin(CRes.fixangle(i)) * lSkill) / ObjectData.MONSTER);
            mgraphics.drawImage(AvMain.imgHotKey, cos, sin, 3, false);
            int i3 = Player.levelTab;
            if (i2 > 3) {
                if (Player.levelTab == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
            }
            HotKey hotKey = Player.mhotkey[i3][(i2 % 4) + (i2 % 4 > 1 ? 1 : 0)];
            if (hotKey != null && hotKey.type == HotKey.SKILL) {
                MainListSkill.getSkillFormId(hotKey.id).paint(mgraphics, cos, sin, 3);
            } else if (hotKey != null && hotKey.type == HotKey.POTION && (itemInventory = Item.getItemInventory(4, hotKey.id)) != null) {
                itemInventory.paintItem(mgraphics, cos, sin, MainTabNew.wOneItem, 0, 3);
            }
            i -= 45;
        }
    }

    public void selectPointer(int i) {
        switch (i) {
            case -4:
                if (!isMapchienthanh()) {
                    MiniMapFull_Screen.gI().Show();
                    mSound.playSound(41, mSound.volumeSound);
                    return;
                }
                return;
            case -3:
                if (Player.diemTiemNang > 0) {
                    mSound.playSound(41, mSound.volumeSound);
                    GameCanvas.AllInfo.Show(GameCanvas.currentScreen);
                    GameCanvas.AllInfo.selectTab = 2;
                    return;
                } else {
                    if (Player.diemKyNang > 0) {
                        mSound.playSound(41, mSound.volumeSound);
                        GameCanvas.AllInfo.Show(GameCanvas.currentScreen);
                        GameCanvas.AllInfo.selectTab = 3;
                        return;
                    }
                    return;
                }
            case -2:
            default:
                return;
            case -1:
                if (numMess > 0) {
                    mSound.playSound(41, mSound.volumeSound);
                    GameCanvas.mevent.init();
                    GameCanvas.mevent.Show(GameCanvas.currentScreen);
                    return;
                }
                return;
            case 0:
                if (GameScreen.isMoveCamera) {
                    GameScreen.isMoveCamera = false;
                    return;
                } else {
                    GameScreen.gI().cmdMenu.perform();
                    return;
                }
            case 1:
                mSound.playSound(41, mSound.volumeSound);
                if (mSystem.currentTimeMillis() - this.timeDownChat <= 500) {
                    ChatTextField.gI().setChat();
                    this.timeDownChat = -1L;
                    this.hClip = 0;
                    return;
                } else {
                    GameScreen.gI().cmdQuickChat.perform();
                    this.timeDownChat = -1L;
                    this.hClip = 0;
                    return;
                }
            case 2:
                if ((GameScreen.ObjFocus == null || GameScreen.ObjFocus.typeBoss != 2) && timeChange == 0) {
                    mSound.playSound(41, mSound.volumeSound);
                    timeChange = 1;
                    return;
                }
                return;
            case 3:
                if (GameScreen.ObjFocus != null) {
                    Player.cmdNextFocus.perform();
                    return;
                }
                return;
            case 4:
                if (!ChatTextField.isShow) {
                    GameCanvas.menu2.setAt_Quick();
                    return;
                }
                return;
            case 5:
                mSound.playSound(41, mSound.volumeSound);
                GameScreen.gI().cmdQuickChat.perform();
                return;
        }
    }

    public void paintParty(mGraphics mgraphics) {
        if (Player.party != null) {
            int i = 0;
            while (i < Player.party.vecPartys.size()) {
                ObjectParty objectParty = (ObjectParty) Player.party.vecPartys.elementAt(i);
                i = (objectParty.name.compareTo(GameScreen.player.name) == 0 || objectParty.isRemove) ? i + 1 : i + 1;
            }
        }
    }

    public void paintNameMap(mGraphics mgraphics) {
        if (timeNameMap > 0) {
            timeNameMap--;
            AvMain.paintDialog(mgraphics, (GameCanvas.hw - (wNameMap / 2)) - 10, yNameMap, wNameMap + 20, 35, 12);
            mFont.tahoma_7b_white.drawString(mgraphics, namemap, GameCanvas.hw, yNameMap + 7, 2, false);
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("- ").append(T.Area).append((int) LoadMap.getAreaPaint()).append(" -").toString(), GameCanvas.hw, yNameMap + 20, 2, false);
        }
    }

    public void paintNameServer(mGraphics mgraphics, String str) {
        if (timeNameMap > 0) {
            timeNameMap--;
            AvMain.paintDialog(mgraphics, (GameCanvas.hw - (wNameMap / 2)) - 10, yNameMap, wNameMap + 20, 20, 12);
            mFont.tahoma_7b_white.drawString(mgraphics, str, GameCanvas.hw, yNameMap + 4, 2, false);
        }
    }

    public void setNameServer(String str) {
        timeNameMap = 80;
        wNameMap = mFont.tahoma_7b_white.getWidth(str);
        yNameMap = GameCanvas.h / 8;
        vyNameMap = 0;
        if (wNameMap < 80) {
            wNameMap = 80;
        }
    }

    public static void setNameMap() {
        timeNameMap = 80;
        namemap = "map";
        if (WorldMapScreen.namePos != null && GameCanvas.loadmap.idMap < WorldMapScreen.namePos.length) {
            namemap = WorldMapScreen.namePos[GameCanvas.loadmap.idMap];
        }
        wNameMap = mFont.tahoma_7b_white.getWidth(namemap);
        yNameMap = GameCanvas.h / 8;
        vyNameMap = 0;
        if (wNameMap < 80) {
            wNameMap = 80;
        }
    }

    public void updateShowIngame() {
        if (isShowInGame) {
            if (GameCanvas.isSmallScreen) {
                if ((GameCanvas.timeNow - timeDoNotClick) / 1000 > 15) {
                    isShowInGame = false;
                }
            } else if ((GameCanvas.timeNow - timeDoNotClick) / 1000 > 2) {
                isShowInGame = false;
            }
            if (hShowInGame > 0) {
                hShowInGame -= 20;
                if (hShowInGame < 0) {
                    hShowInGame = 0;
                    return;
                }
                return;
            }
            return;
        }
        if (hShowInGame < 100) {
            hShowInGame += 10;
        }
    }

    public static void setPosTouch() {
        if (isLevelPoint) {
            mPosOther[2][1] = GameCanvas.h - 31;
            mPosOther[4][0] = 0;
        } else {
            mPosOther[2][1] = GameCanvas.h - 145;
            mPosOther[4][0] = GameCanvas.hw - 20;
        }
    }

    public boolean isMapCountTime(int i) {
        if (i == 59 || i == 57 || i == 55 || i == 53) {
            return true;
        }
        return false;
    }

    public boolean isMapChienTruong(int i) {
        if (i == 61 || i == 60 || i == 58 || i == 56 || i == 54) {
            return true;
        }
        return false;
    }

    public boolean isMapPetcage(int i) {
        return i == 50;
    }

    public boolean isMapLight(int i) {
        return i == 51 || (i >= 37 && i <= 41);
    }

    public boolean isMapDark(int i) {
        return i == 52 || i == 62 || (i >= 42 && i <= 45);
    }

    public boolean isMapArena(int i) {
        return isMapChienTruong(i) || isMapCountTime(i);
    }

    public boolean isMapThachdau() {
        return GameScreen.isShowHoiSinh;
    }

    public boolean isMapchienthanh() {
        return GameCanvas.loadmap.idMap == 83 || GameCanvas.loadmap.idMap == 84 || GameCanvas.loadmap.idMap == 85 || GameCanvas.loadmap.idMap == 86 || GameCanvas.loadmap.idMap == 87;
    }

    public boolean ismapHouse(int i) {
        if (i == 60 || i == 58 || i == 56 || i == 54) {
            return true;
        }
        return false;
    }

    public void setCountTimeHS(int i, long j, int i2) {
        if (isMapCountTime(i)) {
            isCountTime = true;
            curTimeHS = (int) ((j / 1000) + i2);
        } else {
            isCountTime = false;
            timeHS = 0;
        }
    }

    public void countTimeHS() {
        if (isCountTime) {
            if (timeHS >= 0) {
                timeHS = (int) (curTimeHS - (mSystem.currentTimeMillis() / 1000));
            } else {
                isCountTime = false;
                timeHS = 0;
            }
        }
    }

    public void paintTimeHS(mGraphics mgraphics) {
        if (isCountTime && timeHS >= 0) {
            AvMain.Font3dWhite(mgraphics, new StringBuffer().append(T.backBattlefield).append(timeHS).append("s").toString(), 70 - GameCanvas.w, mPosOther[0][1] + 15, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setSttArena(byte b, byte b2, short s) {
        Object[] objArr;
        if (b == -1) {
            return;
        }
        if (b == 4) {
            objArr = true;
        } else if (b == 5) {
            objArr = 2;
        } else if (b == 2) {
            objArr = 3;
        } else {
            objArr = false;
        }
        if (b2 != -1) {
            this.totalHouse[objArr == true ? 1 : 0] = b2;
        }
        if (s != -1) {
            this.totalPlayer[objArr == true ? 1 : 0] = s;
        }
    }

    public void paintSttArena(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        AvMain.Font3dWhite(mgraphics, T.infoArena, i - 5, i2 - (2 * i4), 0);
        imgArenaIcon.drawFrame(this.typePaint[4], i, i2 - i4, 0, 3, mgraphics);
        AvMain.Font3dWhite(mgraphics, new StringBuffer().append("").append((int) GameScreen.player.markKiller).toString(), i + i3, (i2 - i4) - 3, 0);
        for (int i5 = 0; i5 < this.totalHouse.length; i5++) {
            if (this.totalHouse[i5] > 0) {
                AvMain.fraPk.drawFrame((this.typePK[i5] * 3) + ((GameCanvas.gameTick / 3) % 3), i, i2 + (i5 * i4) + 2, 0, 3, mgraphics);
            } else {
                AvMain.fraPk.drawFrame((this.typePK[4] * 3) + ((GameCanvas.gameTick / 3) % 3), i, i2 + (i5 * i4), 0, 3, mgraphics);
            }
            imgArenaIcon.drawFrame(this.typePaint[i5], i + i3, (i2 + (i5 * i4)) - 2, 0, 3, mgraphics);
            AvMain.Font3dWhite(mgraphics, new StringBuffer().append("").append((int) this.totalHouse[i5]).toString(), i + i3 + 10, (i2 + (i5 * i4)) - 3, 0);
            mgraphics.drawRegion(MainTabNew.imgTab[3], 0, 32, 16, 16, 0, i + (3 * i3), i2 + (i5 * i4), 3, false);
            AvMain.Font3dWhite(mgraphics, new StringBuffer().append("").append((int) this.totalPlayer[i5]).toString(), i + (3 * i3) + 10, (i2 + (i5 * i4)) - 3, 0);
        }
    }

    public void resetShip() {
        this.eff = null;
        this.isShipMove = false;
    }

    public void paintShip(mGraphics mgraphics) {
        if (GameCanvas.loadmap.idMap == 19) {
            if (this.eff == null) {
                this.eff = new EffectAuto(50, 1080, 96, 0, 0, 1, 0);
                return;
            }
            this.eff.update();
            if (!this.isShipMove) {
                this.eff.paint(mgraphics);
                return;
            }
            this.eff.x += 2;
            this.eff.paint(mgraphics);
            GameScreen.player.dyWater = 0;
            GameScreen.player.Action = 0;
            GameScreen.player.Direction = 3;
            GameScreen.player.x = this.eff.x + 30;
            GameScreen.player.y = this.eff.y - 40;
            GameScreen.player.updateActionPerson();
            GameScreen.player.paintPlayer(mgraphics, -1);
            if (this.eff.x > 1200) {
                ShipScr.gI().typeMap = (byte) 1;
                ShipScr.gI().Show();
                return;
            }
            return;
        }
        if (GameCanvas.loadmap.idMap == 67) {
            if (this.eff == null) {
                this.eff = new EffectAuto(50, 360, 672, 0, 0, 1, 0);
                return;
            }
            this.eff.update();
            if (!this.isShipMove) {
                this.eff.paint(mgraphics);
                return;
            }
            this.eff.x += 2;
            this.eff.paint(mgraphics);
            GameScreen.player.dyWater = 0;
            GameScreen.player.Action = 0;
            GameScreen.player.Direction = 3;
            GameScreen.player.x = this.eff.x + 30;
            GameScreen.player.y = this.eff.y - 40;
            GameScreen.player.updateActionPerson();
            GameScreen.player.paintPlayer(mgraphics, -1);
            if (this.eff.x > 480) {
                ShipScr.gI().typeMap = (byte) 3;
                ShipScr.gI().Show();
            }
        }
    }

    public static boolean isMarket(int i) {
        return i == 82;
    }

    public void paintPos_minimap(mGraphics mgraphics, int i, int i2) {
        if (isMapThachdau()) {
            return;
        }
        this.xPos_minimap = GameScreen.player.x / LoadMap.wTile;
        this.yPos_minimap = GameScreen.player.y / LoadMap.wTile;
        mFont.tahoma_7_yellow.drawString(mgraphics, new StringBuffer().append(this.xPos_minimap).append(":").append(this.yPos_minimap).toString(), i, i2, 1, false);
    }

    public static void paintinfo18plush(mGraphics mgraphics) {
        if (paint18plush == 0) {
            return;
        }
        GameCanvas.resetTrans(mgraphics);
        int i = 110;
        if (GameCanvas.currentScreen != GameCanvas.game) {
            i = 0;
        }
        int width = mFont.tahoma_7_white.getWidth("18+ Chơi quá 180 phút mỗi ngày sẽ hại sức khỏe.");
        mgraphics.setColor(0, 0.6f);
        mgraphics.fillRect(i, 0, width, 12, false);
        mFont.tahoma_7_white.drawString(mgraphics, "18+ Chơi quá 180 phút mỗi ngày sẽ hại sức khỏe.", i, 0, 0, false);
    }

    public void paintIconClan(mGraphics mgraphics) {
        if (isMapchienthanh() && idicon != -1) {
            MainImage imageIconClan = ObjectData.getImageIconClan((short) idicon);
            if (imageIconClan.img != null) {
                AvMain.Font3dColor(mgraphics, nameclan.toUpperCase(), GameCanvas.w / 2, 5, 2, (byte) 0);
                mgraphics.drawImage(imageIconClan.img, (GameCanvas.w / 2) + mFont.tahoma_7_black.getWidth(nameclan), 10, 3, false);
            }
        }
    }
}
