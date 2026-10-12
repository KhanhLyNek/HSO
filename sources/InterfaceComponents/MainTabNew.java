package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectSkill;
import GameObjects.Item;
import GameObjects.MainItem;
import GameObjects.Monstervantieu;
import GameObjects.PetItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.InfocontenNew;
import Model.T;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/MainTabNew.class */
public class MainTabNew extends AvMain {
    public byte typeTab;
    public static final byte INVENTORY = 0;
    public static final byte EQUIP = 1;
    public static final byte MY_INFO = 2;
    public static final byte SKILLS = 3;
    public static final byte QUEST = 4;
    public static final byte CHAT = 5;
    public static final byte GOLD = 6;
    public static final byte CONFIG = 7;
    public static final byte SHOP = 8;
    public static final byte CHEST = 9;
    public static final byte REBUILD = 10;
    public static final byte FUNCTION = 11;
    public static final byte CLAN_INVENTORY = 12;
    public static final byte PET_KEEPER = 13;
    public static final byte IMFO_VANTIEU = 14;
    public static final byte SELLITEM = 15;
    public static final byte INVEN_AND_STORE = 16;
    public static final byte OTHER_PLAYER_STORE = 17;
    public static final byte COLOR_WHITE = 0;
    public static final byte COLOR_BLUE = 1;
    public static final byte COLOR_YELLOW = 2;
    public static final byte COLOR_VIOLET = 3;
    public static final byte COLOR_ORANGE = 4;
    public static final byte COLOR_GREEN = 5;
    public static final byte COLOR_RED = 6;
    public static final byte COLOR_BLACK = 7;
    public static final byte COLOR_GREY = 8;
    public int xTab;
    public int yTab;
    public int wSmall;
    public int hSmall;
    public int numWSmall;
    public int numHSmall;
    public int xMoney;
    public int yMoney;
    public int xChar;
    public int yChar;
    public int sizeFocus;
    public int numWBlack;
    public int numHBlack;
    public static int wbackground;
    public static int hbackground;
    public static int wblack;
    public static int hblack;
    public static int hMaxContent;
    int xBegin;
    int yBegin;
    public static byte wOne5;
    public static final byte TAB = 0;
    public static final byte INFO = 1;
    public static mImage img_skIcn;
    public static mImage img_pkIcn;
    public static mImage img_arenaIcn;
    public static MainTabNew instance;
    iCommand cmdBack;
    public static int xlongwidth;
    public static int ylongwidth;
    mImage imgStarRebuild;
    public boolean isTabHopNguyenLieu;
    public boolean isSetpostCMD;
    public iCommand cmd;
    public boolean isUPgradeMedal;
    public static int timePaintInfo;
    public String[] mContent;
    public String[] mSubContent;
    public int[] mcolor;
    public int[] mSubColor;
    public int[] mPlusColor;
    public String name;
    public int wContent;
    public int colorName;
    public int xCon;
    public int yCon;
    public static byte maxTypeTab = 13;
    public static byte wOneItem = 20;
    public static byte Focus = 0;
    public static mImage[] imgTab = new mImage[15];
    public static int longwidth = 0;
    public static int timeRequest = 15;
    public static boolean is320 = false;
    public static int[] color = {-5727870, -4543854, -477112, -1315861, -5859456, -3689060, -4807282, -9411756, -1845826, -5267575, -460632};
    static String nameCur = "";
    static String[] namePaint = new String[2];
    static int[] colorLow = {-2701394, AvMain.SELECTED_COLOR, -9411756, -7832985, -11844807};
    public boolean isClan = false;
    public boolean isPet = false;
    public String nameTab = "";
    public ListNew listContent = null;
    public boolean isCreate_medal = false;
    public int xpos_cmd = 0;
    public int ypos_cmd = 0;
    public String[] mPlusContent = null;
    public mVector moreInfoconten = new mVector("MainTabNew moreInfoconten");

    public static MainTabNew gI() {
        if (instance == null) {
            instance = new MainTabNew();
        }
        return instance;
    }

    public MainTabNew() {
        if (GameCanvas.isTouch) {
            wOneItem = (byte) 26;
        } else if (GameCanvas.w >= 240) {
            wOneItem = (byte) 24;
        }
        if (GameCanvas.h < 240 && wOneItem > 24) {
            wOneItem = (byte) 24;
        }
        hMaxContent = GameCanvas.h - (GameCanvas.hCommand * 2);
        wOne5 = (byte) (wOneItem / 5);
        wbackground = (GameCanvas.w / 32) + 1;
        hbackground = (GameCanvas.h / 32) + 1;
        int i = GameCanvas.w / wOneItem;
        i = i > 9 ? 9 : i;
        int i2 = ((GameCanvas.h / 5) * 4) - (GameCanvas.hCommand / 2);
        int i3 = (GameCanvas.isTouch ? i2 + (GameCanvas.hCommand / 2) : i2) / wOneItem;
        i3 = i3 > 8 ? 8 : i3;
        this.wSmall = (((i - 1) * wOneItem) - (wOne5 * 3)) + (GameCanvas.isSmallScreen ? wOne5 : (byte) 0);
        this.hSmall = (i3 * wOneItem) + wOne5;
        if (this.hSmall % 2 != 0) {
            this.hSmall--;
        }
        if (GameCanvas.isTouch) {
            if (GameCanvas.w >= 380) {
                longwidth = 170;
                timeRequest = 5;
                hMaxContent = (this.hSmall - wOneItem) - wOne5;
                this.xTab = ((GameCanvas.w - (i * wOneItem)) - longwidth) / 2;
                xlongwidth = (GameCanvas.w - this.xTab) - longwidth;
                ylongwidth = this.yTab + (GameCanvas.h / 5);
            } else if (GameCanvas.w > 315) {
                is320 = true;
                if (LoginScreen.indexInfoLogin == 1) {
                    LoginScreen.indexInfoLogin = (byte) 2;
                }
                i = 8;
                this.wSmall = ((8 - 1) * wOneItem) - (wOne5 * 3);
                longwidth = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
                timeRequest = 5;
                hMaxContent = (this.hSmall - wOneItem) - wOne5;
                xlongwidth = ((GameCanvas.w - this.xTab) - longwidth) + 5;
                ylongwidth = this.yTab + (GameCanvas.h / 5);
            }
        }
        if (is320) {
            this.xTab = -5;
            xlongwidth = this.xTab + this.wSmall + wOneItem + (wOne5 * 2);
            longwidth = GameCanvas.w - xlongwidth;
        } else {
            this.xTab = ((GameCanvas.w - (i * wOneItem)) - longwidth) / 2;
        }
        this.yTab = 0;
        this.numWSmall = this.wSmall / 32;
        this.numHSmall = this.hSmall / 32;
        wblack = (this.wSmall / wOneItem) * wOneItem;
        hblack = ((this.hSmall / wOneItem) - 1) * wOneItem;
        this.numWBlack = wblack / 32;
        this.numHBlack = hblack / 32;
        this.xMoney = (GameCanvas.w - (this.xTab - 9)) - 72;
        if (GameCanvas.isTouch && this.xMoney > GameCanvas.w - EffectSkill.EFF_KIEM_NHO_TROI) {
            this.xMoney = GameCanvas.w - EffectSkill.EFF_KIEM_NHO_TROI;
        }
        this.yMoney = 5;
        if (GameCanvas.isSmallScreen) {
            this.yMoney = 2;
        }
        this.xChar = 0;
        this.yChar = (GameCanvas.h / 10) - 21;
        if (GameCanvas.isSmallScreen) {
            this.yChar += 4;
        }
        this.sizeFocus = wOne5 + wOneItem;
        if (this.sizeFocus > 32) {
            this.sizeFocus = 32;
        }
    }

    public void init() {
    }

    public void setNameCmd(String str) {
    }

    public void backTab() {
        MainScreen.cameraSub.setAll(0, 0, 0, 0);
        if (GameCanvas.isTouch) {
            if (GameCanvas.currentScreen == GameCanvas.AllInfo) {
                if (GameCanvas.AllInfo.lastScreen != null && GameCanvas.AllInfo.lastScreen != GameCanvas.AllInfo && GameCanvas.AllInfo.lastScreen != GameCanvas.shopNpc) {
                    GameCanvas.AllInfo.lastScreen.Show();
                    return;
                } else {
                    GameCanvas.game.Show();
                    return;
                }
            }
            if (GameCanvas.currentScreen == GameCanvas.shopNpc) {
                if (GameCanvas.AllInfo.lastScreen != null && GameCanvas.AllInfo.lastScreen != GameCanvas.AllInfo && GameCanvas.AllInfo.lastScreen != GameCanvas.shopNpc) {
                    GameCanvas.shopNpc.lastScreen.Show();
                    return;
                } else {
                    GameCanvas.game.Show();
                    return;
                }
            }
            if (GameCanvas.currentScreen == GameCanvas.foodPet) {
                if (GameCanvas.foodPet.lastScreen != null) {
                    GameCanvas.foodPet.lastScreen.Show();
                } else {
                    GameCanvas.game.Show();
                }
            }
        }
    }

    @Override // Model.AvMain
    public void keypress(int i) {
    }

    public void paintHairShop(mGraphics mgraphics, int i) {
        GameCanvas.resetTrans(mgraphics);
        GameScreen.player.paintShowHairPlayer(mgraphics, GameCanvas.hw, (GameCanvas.h / 10) + 15, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void paintTab(mGraphics mgraphics, String str, int i, mVector mvector, boolean z) {
        int size = mvector.size();
        if (GameCanvas.lowGraphic) {
            paintRectLowGraphic(mgraphics, 0, 0, GameCanvas.w, GameCanvas.h, 0);
            paintRectLowGraphic(mgraphics, this.xMoney, this.yMoney, 66, 32, 1);
            paintRectLowGraphic(mgraphics, this.xTab + wOneItem + (wOne5 * 2), this.yTab + (GameCanvas.h / 5), this.wSmall, this.hSmall, 1);
        } else {
            for (int i2 = 0; i2 < wbackground; i2++) {
                for (int i3 = 0; i3 < hbackground; i3++) {
                    mgraphics.drawImage(imgTab[0], i2 * 32, i3 * 32, 0, false);
                }
            }
            for (int i4 = 0; i4 < 3; i4++) {
                mgraphics.drawImage(imgTab[1], this.xMoney + (22 * i4), this.yMoney, 0, false);
            }
            for (int i5 = 0; i5 <= this.numWSmall; i5++) {
                for (int i6 = 0; i6 <= this.numHSmall; i6++) {
                    if (i5 == 0 || i5 == this.numWSmall || i6 == this.numHSmall || i6 == 0) {
                        if (i5 == this.numWSmall) {
                            if (i6 == this.numHSmall) {
                                mgraphics.drawImage(imgTab[1], (((this.xTab + wOneItem) + (wOne5 * 2)) + this.wSmall) - 32, ((this.yTab + (GameCanvas.h / 5)) + this.hSmall) - 32, 0, false);
                            } else {
                                mgraphics.drawImage(imgTab[1], (((this.xTab + wOneItem) + (wOne5 * 2)) + this.wSmall) - 32, this.yTab + (i6 * 32) + (GameCanvas.h / 5), 0, false);
                            }
                        } else if (i6 == this.numHSmall) {
                            mgraphics.drawImage(imgTab[1], this.xTab + wOneItem + (wOne5 * 2) + (i5 * 32), ((this.yTab + (GameCanvas.h / 5)) + this.hSmall) - 32, 0, false);
                        } else {
                            mgraphics.drawImage(imgTab[1], this.xTab + wOneItem + (wOne5 * 2) + (i5 * 32), this.yTab + (i6 * 32) + (GameCanvas.h / 5), 0, false);
                        }
                    }
                }
            }
        }
        AvMain.Font3dWhite(mgraphics, str, this.xTab + wOneItem + (wOne5 * 2) + (this.wSmall / 2), ((this.yTab + (GameCanvas.h / 5)) + (wOneItem / 2)) - 6, 2);
        GameScreen.infoGame.paintInfoPlayer(mgraphics, 0, 0, !GameCanvas.isSmallScreen, GameCanvas.isSmallScreen ? mFont.tahoma_7_black : mFont.tahoma_7_white);
        mgraphics.drawRegion(imgTab[4], 0, 0, 14, 14, 0, this.xMoney + 4, this.yMoney + 2, 0, false);
        mgraphics.drawRegion(imgTab[4], 0, 14, 14, 14, 0, this.xMoney + 4, this.yMoney + 17, 0, false);
        if (z) {
            PaintInfoGameScreen.fraEvent.drawFrame(10, this.xMoney - 12, this.yMoney + 10, 0, 3, mgraphics);
            if (GameScreen.player.myClan != null) {
                mFont.tahoma_7_white.drawString(mgraphics, MainItem.getDotNumber(GameScreen.player.myClan.coin), this.xMoney + 19, this.yMoney + 3, 0, false);
                mFont.tahoma_7_white.drawString(mgraphics, MainItem.getDotNumber(GameScreen.player.myClan.gold), this.xMoney + 19, this.yMoney + 18, 0, false);
            }
        } else {
            mFont.tahoma_7_white.drawString(mgraphics, MainItem.getDotNumber(GameScreen.player.coin), this.xMoney + 19, this.yMoney + 3, 0, false);
            mFont.tahoma_7_white.drawString(mgraphics, MainItem.getDotNumber(GameScreen.player.gold), this.xMoney + 19, this.yMoney + 18, 0, false);
        }
        if (GameCanvas.lowGraphic) {
            paintRectLowGraphic(mgraphics, this.xTab + wOne5, this.yTab + (GameCanvas.h / 5) + (wOneItem * i), wOne5 + wOneItem, wOneItem, 1);
        } else if (wOne5 + wOneItem > 32) {
            mgraphics.drawRegion(imgTab[1], 0, 0, wOneItem, wOneItem, 0, this.xTab + wOne5, this.yTab + (GameCanvas.h / 5) + (wOneItem * i), 0, false);
            mgraphics.drawRegion(imgTab[1], 0, 0, wOne5, wOneItem, 0, this.xTab + wOne5 + wOneItem, this.yTab + (GameCanvas.h / 5) + (wOneItem * i), 0, false);
        } else {
            mgraphics.drawRegion(imgTab[1], 0, 0, wOne5 + wOneItem, wOneItem, 0, this.xTab + wOne5, this.yTab + (GameCanvas.h / 5) + (wOneItem * i), 0, false);
        }
        if (!GameCanvas.isTouch && Focus == 0) {
            mgraphics.setColor(color[3]);
            mgraphics.drawRect(this.xTab + wOne5 + 1, this.yTab + (GameCanvas.h / 5) + (wOneItem * i) + 1, (wOne5 + wOneItem) - 3, wOneItem - 5, false);
        }
        mgraphics.setColor(color[0]);
        for (int i7 = 0; i7 < size; i7++) {
            MainTabNew mainTabNew = (MainTabNew) mvector.elementAt(i7);
            int i8 = 0;
            if (i7 != i) {
                mgraphics.drawRect(this.xTab + wOne5, this.yTab + (GameCanvas.h / 5) + (wOneItem * i7), wOne5 + wOneItem, wOneItem, false);
            } else if (Focus == 0 || GameCanvas.isTouch) {
                i8 = (-1) + ((GameCanvas.gameTick / 2) % 3);
            }
            byte b = mainTabNew.typeTab;
            if (b > maxTypeTab) {
                b = this.typeTab;
            }
            mgraphics.drawRegion(imgTab[3], 0, b * 16, 16, 16, 0, this.xTab + wOne5 + (wOne5 / 2) + (wOneItem / 2) + i8, this.yTab + (GameCanvas.h / 5) + (wOneItem / 2) + (wOneItem * i7), 3, false);
            if (mainTabNew.typeTab == 2 && Player.diemTiemNang > 0) {
                PaintInfoGameScreen.fralevelup.drawFrame((GameCanvas.gameTick / 4) % 2, ((((this.xTab + wOne5) + (wOne5 / 2)) + wOneItem) + i8) - 4, (((this.yTab + (GameCanvas.h / 5)) + wOneItem) + (wOneItem * i7)) - 6, 0, 3, mgraphics);
            } else if (mainTabNew.typeTab == 3 && Player.diemKyNang > 0) {
                PaintInfoGameScreen.fralevelup.drawFrame(2 + ((GameCanvas.gameTick / 4) % 2), ((((this.xTab + wOne5) + (wOne5 / 2)) + wOneItem) + i8) - 4, (((this.yTab + (GameCanvas.h / 5)) + wOneItem) + (wOneItem * i7)) - 6, 0, 3, mgraphics);
            }
        }
        if (GameCanvas.lowGraphic) {
            paintRectLowGraphic(mgraphics, this.xTab + wOneItem + (wOne5 * 3), this.yTab + (GameCanvas.h / 5) + wOneItem, wblack, hblack, 2);
        } else {
            for (int i9 = 0; i9 <= this.numWBlack; i9++) {
                for (int i10 = 0; i10 <= this.numHBlack; i10++) {
                    if (i9 == this.numWBlack) {
                        if (i10 == this.numHBlack) {
                            mgraphics.drawImage(imgTab[2], (((this.xTab + wOneItem) + (wOne5 * 3)) + wblack) - 32, (((this.yTab + (GameCanvas.h / 5)) + wOneItem) + hblack) - 32, 0, false);
                        } else {
                            mgraphics.drawImage(imgTab[2], (((this.xTab + wOneItem) + (wOne5 * 3)) + wblack) - 32, this.yTab + (GameCanvas.h / 5) + wOneItem + (i10 * 32), 0, false);
                        }
                    } else if (i10 == this.numHBlack) {
                        mgraphics.drawImage(imgTab[2], this.xTab + wOneItem + (wOne5 * 3) + (i9 * 32), (((this.yTab + (GameCanvas.h / 5)) + wOneItem) + hblack) - 32, 0, false);
                    } else {
                        mgraphics.drawImage(imgTab[2], this.xTab + wOneItem + (wOne5 * 3) + (i9 * 32), this.yTab + (GameCanvas.h / 5) + wOneItem + (i10 * 32), 0, false);
                    }
                }
            }
        }
        if (longwidth > 0) {
            GameCanvas.resetTrans(mgraphics);
            if (GameCanvas.lowGraphic) {
                paintRectLowGraphic(mgraphics, xlongwidth, ylongwidth, longwidth, this.hSmall, 12);
            } else {
                int i11 = longwidth / 32;
                int i12 = this.hSmall / 32;
                for (int i13 = 0; i13 <= i11; i13++) {
                    for (int i14 = 0; i14 <= i12; i14++) {
                        Object[] objArr = 12;
                        if (i14 == 0) {
                            objArr = 12;
                        }
                        if (i13 == i11) {
                            if (i14 == i12) {
                                mgraphics.drawImage(imgTab[objArr == true ? 1 : 0], (xlongwidth + longwidth) - 32, (ylongwidth + this.hSmall) - 32, 0, false);
                            } else {
                                mgraphics.drawImage(imgTab[objArr == true ? 1 : 0], (xlongwidth + longwidth) - 32, ylongwidth + (i14 * 32), 0, false);
                            }
                        } else if (i14 == i12) {
                            mgraphics.drawImage(imgTab[objArr == true ? 1 : 0], xlongwidth + (i13 * 32), (ylongwidth + this.hSmall) - 32, 0, false);
                        } else {
                            mgraphics.drawImage(imgTab[objArr == true ? 1 : 0], xlongwidth + (i13 * 32), ylongwidth + (i14 * 32), 0, false);
                        }
                    }
                }
            }
            for (int i15 = xlongwidth; i15 < xlongwidth + longwidth; i15 += 6) {
                mgraphics.fillRect(i15, ylongwidth + wOneItem, 4, 1, false);
            }
        }
    }

    public void paintPopupContent(mGraphics mgraphics, boolean z) {
        if (longwidth > 0) {
            paintContentNew(mgraphics, z);
        } else {
            paintContent(mgraphics, z);
        }
    }

    public void paintContent(mGraphics mgraphics, boolean z) {
        mFont mfont;
        TabScreenNew.timeRepaint = 10;
        mgraphics.setClip(-mgraphics.getTranslateX(), -mgraphics.getTranslateY(), GameCanvas.w, GameCanvas.h);
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(-mgraphics.getTranslateX(), -mgraphics.getTranslateY(), GameCanvas.w, GameCanvas.h);
        int i = 1;
        if (this.mContent != null) {
            i = this.mContent.length;
        }
        if (this.mPlusContent != null) {
            i += this.mPlusContent.length;
        }
        if (this.moreInfoconten != null && this.moreInfoconten.size() > 0 && this.mPlusContent == null) {
            i++;
        }
        int i2 = ((i + 1) * GameCanvas.hText) + 8;
        if (i2 > hMaxContent) {
            i2 = hMaxContent;
        }
        if (this.xCon + this.wContent > GameCanvas.w) {
            this.xCon = (GameCanvas.w / 2) - (this.wContent / 2);
        }
        int i3 = this.yCon;
        mgraphics.setColor(color[10]);
        mgraphics.fillRect(this.xCon - 1, i3 - 1, this.wContent + 2, i2 + 2, true);
        mgraphics.setColor(color[2]);
        mgraphics.fillRect(this.xCon, i3, this.wContent + 1, i2 + 1, true);
        if (GameCanvas.lowGraphic) {
            paintRectLowGraphic(mgraphics, this.xCon, i3, this.wContent, i2, 4);
        } else {
            int i4 = this.wContent / 32;
            int i5 = i2 / 32;
            for (int i6 = 0; i6 <= i4; i6++) {
                for (int i7 = 0; i7 <= i5; i7++) {
                    if (i6 == i4) {
                        if (i7 == i5) {
                            mgraphics.drawImage(imgTab[12], (this.xCon + this.wContent) - 32, (i3 + i2) - 32, 0, true);
                        } else {
                            mgraphics.drawImage(imgTab[12], (this.xCon + this.wContent) - 32, i3 + (i7 * 32), 0, true);
                        }
                    } else if (i7 == i5) {
                        mgraphics.drawImage(imgTab[12], this.xCon + (i6 * 32), (i3 + i2) - 32, 0, true);
                    } else {
                        mgraphics.drawImage(imgTab[12], this.xCon + (i6 * 32), i3 + (i7 * 32), 0, true);
                    }
                }
            }
        }
        mgraphics.restoreCanvas();
        mgraphics.setClip(this.xCon + 1, i3 + 1, this.wContent - 2, i2 - 2);
        if (!z && this.name != null) {
            int i8 = i3 + 2;
            if (this.mContent == null && mSystem.isj2me) {
                i8 = i3 + 6;
            }
            paintNameItem(mgraphics, this.xCon + (this.wContent / 2), i8, this.wContent, this.name, this.colorName);
        }
        mgraphics.saveCanvas();
        mgraphics.ClipRec(this.xCon + 1, i3 + 1 + 13, this.wContent - 2, (i2 - 2) - 13);
        if (!z && this.listContent != null) {
            mgraphics.setClip(this.xCon, i3 + GameCanvas.hText, this.wContent, (hMaxContent - GameCanvas.hText) - 20);
            mgraphics.translate(0, -this.listContent.cmx);
        }
        if (this.mPlusContent != null) {
            for (int i9 = 0; i9 < this.mPlusContent.length; i9++) {
                i3 += GameCanvas.hText;
                setTextColor(this.mPlusColor[i9]).drawString(mgraphics, this.mPlusContent[i9], this.xCon + 4, i3 + 2, 0, true);
            }
        }
        if (this.mPlusContent == null && this.moreInfoconten.size() > 0) {
            i3 += GameCanvas.hText;
        }
        for (int i10 = 0; i10 < this.moreInfoconten.size(); i10++) {
            InfocontenNew infocontenNew = (InfocontenNew) this.moreInfoconten.elementAt(i10);
            if (infocontenNew != null) {
                if (this.mPlusContent != null) {
                    Item.eff_UpLv.paintUpgradeEffect(this.xCon + 4 + (this.mPlusContent[0].length() * 5) + 3 + (15 * i10), (i3 - (GameCanvas.hText / 2)) + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 13, 13, mgraphics, 0);
                } else {
                    Item.eff_UpLv.paintUpgradeEffect(this.xCon + 4 + 16 + (15 * i10), (i3 + GameCanvas.hText) - 8, 13, 13, mgraphics, 0);
                }
                if (infocontenNew.idimage != -1) {
                    MainItem material = Item.getMaterial(infocontenNew.idimage);
                    if (material != null) {
                        if (this.mPlusContent != null && this.mPlusContent[0] != null) {
                            material.paintItem_not_num(mgraphics, this.xCon + 4 + (this.mPlusContent[0].length() * 5) + 3 + (15 * i10) + 1, (i3 - (GameCanvas.hText / 2)) + 1 + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 21, 1, 0);
                        } else {
                            material.paintItem_not_num(mgraphics, this.xCon + 4 + 16 + (15 * i10), ((i3 + GameCanvas.hText) + 1) - 8, 21, 1, 0);
                        }
                    } else {
                        Item.put_Material(infocontenNew.idimage);
                    }
                }
            }
        }
        if (this.mContent != null) {
            for (int i11 = 0; i11 < this.mContent.length; i11++) {
                if (this.mContent[i11] != null) {
                    if (this.mcolor != null) {
                        mfont = setTextColor(this.mcolor[i11]);
                    } else {
                        mfont = mFont.tahoma_7_white;
                    }
                    mfont.drawString(mgraphics, this.mContent[i11], this.xCon + 4, i3 + 2 + ((i11 + 1) * GameCanvas.hText), 0, true);
                    if (this.mSubContent != null) {
                        setTextColor(this.mSubColor[i11]).drawString(mgraphics, this.mSubContent[i11], this.xCon + mfont.getWidth(this.mContent[i11]) + 5 + 4, i3 + 2 + ((i11 + 1) * GameCanvas.hText), 0, true);
                    }
                }
            }
        } else if (!z) {
            if (this.name != null) {
                int i12 = i3 + 2;
                if (this.mContent == null && mSystem.isj2me) {
                    i12 = i3 + 6;
                }
                paintNameItem(mgraphics, this.xCon + (this.wContent / 2), i12, this.wContent, this.name, this.colorName);
            }
        } else if (this.name != null) {
            paintNameItem(mgraphics, this.xCon + (this.wContent / 2), i3 + (GameCanvas.hText / 4), this.wContent, this.name, this.colorName);
        }
        if (this.cmd != null) {
            this.cmd.paint(mgraphics, (this.xCon + this.wContent) - (iCommand.wButtonCmd / 2), (i3 + i2) - iCommand.hButtonCmd);
            if (this.xpos_cmd == 0 || this.ypos_cmd == 0) {
                this.xpos_cmd = (this.xCon + this.wContent) - (iCommand.wButtonCmd / 2);
                this.ypos_cmd = (i3 + i2) - iCommand.hButtonCmd;
            }
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
    }

    public void updateCMD() {
        if (this.cmd != null) {
            if (this.xpos_cmd != 0 && this.ypos_cmd != 0) {
                this.cmd.setPos(this.xpos_cmd, this.ypos_cmd, PaintInfoGameScreen.fraButton, this.cmd.caption);
            }
            if (GameCanvas.isTouch) {
                this.cmd.updatePointer();
            } else if (GameCanvas.keyMyPressed[5]) {
                GameCanvas.keyMyPressed[5] = false;
                this.cmd.perform();
            }
        }
    }

    @Override // Model.AvMain
    public void update() {
        updateCMD();
    }

    public void paintContentNew(mGraphics mgraphics, boolean z) {
        mFont mfont;
        TabScreenNew.timeRepaint = 10;
        GameCanvas.resetTrans(mgraphics);
        int i = xlongwidth;
        int i2 = ylongwidth;
        int i3 = i + 1;
        int i4 = i2 + 1;
        int i5 = longwidth - 2;
        int i6 = this.hSmall - 2;
        boolean z2 = false;
        if (!z) {
            paintNameItem(mgraphics, i + (longwidth / 2), (i2 + (wOneItem / 2)) - 5, longwidth, this.name, this.colorName);
            if (this.listContent != null) {
                mgraphics.setClip(i, i2 + wOneItem + 2, longwidth, (hMaxContent - wOneItem) - 2);
                i3 = i;
                i4 = i2 + wOneItem + 2;
                i5 = longwidth;
                i6 = (hMaxContent - wOneItem) - 2;
                z2 = true;
            }
            i2 += (wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
        }
        mgraphics.setClip(i3, i4, i5, i6);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(i3, i4, i5, i6);
        if (z2) {
            mgraphics.translate(0, -this.listContent.cmx);
        }
        if (this.mPlusContent != null) {
            for (int i7 = 0; i7 < this.mPlusContent.length; i7++) {
                i2 += GameCanvas.hText;
                setTextColor(this.mPlusColor[i7]).drawString(mgraphics, this.mPlusContent[i7], i + 4, i2 + 2, 0, true);
            }
        }
        if (this.mPlusContent == null && this.moreInfoconten.size() > 0) {
            i2 += GameCanvas.hText;
        }
        for (int i8 = 0; i8 < this.moreInfoconten.size(); i8++) {
            InfocontenNew infocontenNew = (InfocontenNew) this.moreInfoconten.elementAt(i8);
            if (infocontenNew != null) {
                if (this.mPlusColor != null) {
                    Item.eff_UpLv.paintUpgradeEffect(i + 4 + (this.mPlusContent[0].length() * 5) + 3 + (15 * i8), (i2 - (GameCanvas.hText / 2)) + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 13, 13, mgraphics, 0);
                } else {
                    Item.eff_UpLv.paintUpgradeEffect(i + 4 + 16 + (15 * i8), (i2 - (GameCanvas.hText / 2)) + GameCanvas.hText, 13, 13, mgraphics, 0);
                }
                if (infocontenNew.idimage != -1) {
                    MainItem material = Item.getMaterial(infocontenNew.idimage);
                    if (material != null) {
                        if (this.mPlusContent != null && this.mPlusContent[0] != null) {
                            material.paintItem_not_num(mgraphics, i + 4 + (this.mPlusContent[0].length() * 5) + 3 + (15 * i8) + 1, (i2 - (GameCanvas.hText / 2)) + 1 + (this.mPlusContent.length == 1 ? GameCanvas.hText : 0), 21, 1, 0);
                        } else {
                            material.paintItem_not_num(mgraphics, i + 4 + 16 + (15 * i8), (i2 - (GameCanvas.hText / 2)) + GameCanvas.hText + 1, 21, 1, 0);
                        }
                    } else {
                        Item.put_Material(infocontenNew.idimage);
                    }
                }
            }
        }
        if (this.mContent != null) {
            PetItem petItem = MsgDialog.pet;
            if (this.isPet && petItem != null) {
                int i9 = i2 + GameCanvas.hText;
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.level).append(petItem.LvItem).append(" + ").append(petItem.experience / 10).append(",").append(petItem.experience % 10).append("%").toString(), i + 4, i9 + 2, 0, true);
                int i10 = i9 + GameCanvas.hText;
                int i11 = petItem.age / 24;
                int i12 = petItem.age % 24;
                int timeItemFashion = (int) (petItem.timeDefaultItemFashion - (petItem.getTimeItemFashion() / 60000));
                if (timeItemFashion > 0) {
                    mFont.tahoma_7_red.drawString(mgraphics, new StringBuffer().append(T.sudungsau).append(" ").append(PaintInfoGameScreen.getStringTime(timeItemFashion)).toString(), i + 4, i10 + 2, 0, true);
                    i10 += GameCanvas.hText;
                }
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.tuoi).append(i11).append("d ").append(i12).append("h").toString(), i + 4, i10 + 2, 0, true);
                int i13 = i10 + GameCanvas.hText;
                if (petItem.petAttack != null) {
                    setTextColor(Item.colorInfoItem[petItem.petAttack.id]).drawString(mgraphics, new StringBuffer().append(Item.nameInfoItem[petItem.petAttack.id]).append(": ").append(petItem.petAttack.value).append("-").append(petItem.petAttack.maxDam).toString(), i + 4, i13 + 2, 0, true);
                }
                int i14 = i13 + GameCanvas.hText;
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.choan).append(": ").append((int) petItem.growpoint).append("/").append((int) petItem.maxgrow).toString(), i + 4, i14 + 2, 0, true);
                int i15 = i14 + GameCanvas.hText;
                for (int i16 = 0; i16 < T.mKynangPet.length; i16++) {
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.mKynangPet[i16]).append(": ").append((int) petItem.mvaluetiemnang[i16]).append("/").append((int) petItem.maxtiemnang).toString(), i + 4, i15 + 2, 0, true);
                    i15 += GameCanvas.hText;
                }
                for (int i17 = 0; i17 < petItem.mInfo.length; i17++) {
                    if (petItem.mInfo[i17].id > 6) {
                        setTextColor(Item.colorInfoItem[petItem.mInfo[i17].id]).drawString(mgraphics, new StringBuffer().append(Item.nameInfoItem[petItem.mInfo[i17].id]).append(": ").append(Item.getPercent(Item.isPercentInfoItem[petItem.mInfo[i17].id], petItem.mInfo[i17].value)).toString(), i + 4, i15 + 2, 0, true);
                        i15 += GameCanvas.hText;
                    }
                }
            } else {
                for (int i18 = 0; i18 < this.mContent.length; i18++) {
                    if (this.mContent[i18] != null) {
                        if (this.mcolor != null) {
                            mfont = setTextColor(this.mcolor[i18]);
                        } else {
                            mfont = mFont.tahoma_7_white;
                        }
                        mfont.drawString(mgraphics, this.mContent[i18], i + 4, i2 + 2 + ((i18 + 1) * GameCanvas.hText), 0, true);
                        if (this.mSubContent != null) {
                            setTextColor(this.mSubColor[i18]).drawString(mgraphics, this.mSubContent[i18], i + mfont.getWidth(this.mContent[i18]) + 5 + 4, i2 + 2 + ((i18 + 1) * GameCanvas.hText), 0, true);
                        }
                    }
                }
            }
        } else if (z) {
            mFont.tahoma_7b_white.drawString(mgraphics, this.name, i + (longwidth / 2), (i2 + (wOneItem / 2)) - 5, 2, true);
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
    }

    public static void paintNameItem(mGraphics mgraphics, int i, int i2, int i3, String str, int i4) {
        if (mFont.tahoma_7b_black.getWidth(str) <= i3) {
            setTextColorName(i4).drawString(mgraphics, str, i, i2, 2, true);
            return;
        }
        if (nameCur.compareTo(str.trim()) != 0) {
            getTextName(str);
        }
        mFont textColor = setTextColor(i4);
        textColor.drawString(mgraphics, namePaint[0], i, i2 - 6, 2, true);
        textColor.drawString(mgraphics, namePaint[1], i, i2 + 6, 2, true);
    }

    public static void getTextName(String str) {
        nameCur = str.trim();
        namePaint = new String[2];
        for (int i = 0; i < namePaint.length; i++) {
            namePaint[i] = "";
        }
        String[] split = mFont.split(nameCur, " ");
        for (int i2 = 0; i2 < split.length; i2++) {
            if (i2 <= split.length / 2) {
                StringBuffer stringBuffer = new StringBuffer();
                String[] strArr = namePaint;
                strArr[0] = stringBuffer.append(strArr[0]).append(split[i2]).toString();
                if (i2 < split.length / 2) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    String[] strArr2 = namePaint;
                    strArr2[0] = stringBuffer2.append(strArr2[0]).append(" ").toString();
                }
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                String[] strArr3 = namePaint;
                strArr3[1] = stringBuffer3.append(strArr3[1]).append(split[i2]).toString();
                if (i2 < split.length - 1) {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    String[] strArr4 = namePaint;
                    strArr4[1] = stringBuffer4.append(strArr4[1]).append(" ").toString();
                }
            }
        }
    }

    public static mFont setTextColor(int i) {
        int i2 = i;
        if (i >= 20 && i < 30) {
            i2 = i - 20;
        } else if (i >= 30 && i < 40) {
            i2 = i - 30;
        } else if (i >= 40 && i < 50) {
            i2 = i - 40;
        }
        switch (i2) {
            case 0:
                return mFont.tahoma_7_white;
            case 1:
                return mFont.tahoma_7_blue;
            case 2:
                return mFont.tahoma_7_yellow;
            case 3:
                return mFont.tahoma_7_violet;
            case 4:
                return mFont.tahoma_7_orange;
            case 5:
                return mFont.tahoma_7_green;
            case 6:
                return mFont.tahoma_7_red;
            case 7:
                return mFont.tahoma_7_black;
            case 8:
                return mFont.tahoma_7_gray;
            default:
                return mFont.tahoma_7_white;
        }
    }

    public static mFont setTextColorName(int i) {
        int i2 = i;
        if (i >= 20 && i < 30) {
            i2 = i - 20;
        } else if (i >= 30 && i < 40) {
            i2 = i - 30;
        } else if (i >= 40 && i < 50) {
            i2 = i - 40;
        }
        switch (i2) {
            case 0:
                return mFont.tahoma_7b_white;
            case 1:
                return mFont.tahoma_7b_blue;
            case 2:
                return mFont.tahoma_7b_yellow;
            case 3:
                return mFont.tahoma_7b_violet;
            case 4:
                return mFont.tahoma_7b_orange;
            case 5:
                return mFont.tahoma_7b_green;
            case 6:
            default:
                return mFont.tahoma_7b_white;
            case 7:
                return mFont.tahoma_7b_black;
            case 8:
                return mFont.tahoma_7_gray;
        }
    }

    public void setPaintInfo() {
    }

    public void setYCon(MainItem mainItem) {
    }

    public void setinfoContennew(int i, int i2) {
        this.moreInfoconten.addElement(new InfocontenNew(i, i2));
    }

    public void setPosCmd(mVector mvector) {
        int size;
        if (mvector == null || (size = mvector.size()) == 0) {
            return;
        }
        int i = ylongwidth + this.hSmall;
        int i2 = xlongwidth;
        if (size == 1) {
            iCommand icommand = (iCommand) mvector.elementAt(0);
            if (is320) {
                icommand.setPos(i2 + (longwidth / 2), i - 10, PaintInfoGameScreen.fraButton2, icommand.caption);
                return;
            } else {
                icommand.setPos(i2 + (longwidth / 2), i - 15, null, icommand.caption);
                return;
            }
        }
        if (size == 2) {
            iCommand icommand2 = (iCommand) mvector.elementAt(0);
            if (is320) {
                icommand2.setPos(i2 + (longwidth / 4), i - 10, PaintInfoGameScreen.fraButton2, icommand2.caption);
            } else {
                icommand2.setPos(i2 + (longwidth / 4), i - 15, null, icommand2.caption);
            }
            iCommand icommand3 = (iCommand) mvector.elementAt(1);
            if (is320) {
                icommand3.setPos(i2 + ((longwidth / 4) * 3) + 2, i - 10, PaintInfoGameScreen.fraButton2, icommand3.caption);
                return;
            } else {
                icommand3.setPos(i2 + ((longwidth / 4) * 3) + 2, i - 15, null, icommand3.caption);
                return;
            }
        }
        for (int i3 = 0; i3 < size; i3++) {
            iCommand icommand4 = (iCommand) mvector.elementAt(i3);
            if (i3 == size - 1 && size % 2 == 1) {
                if (is320) {
                    icommand4.setPos(i2 + (longwidth / 2), i - 10, PaintInfoGameScreen.fraButton2, icommand4.caption);
                } else {
                    icommand4.setPos(i2 + (longwidth / 2), ((i - 15) - (((size - 1) / 2) * 30)) + ((i3 / 2) * 30), null, icommand4.caption);
                }
            } else if (is320) {
                icommand4.setPos(i2 + (longwidth / 4) + ((i3 % 2) * ((longwidth / 2) + 2)), ((i - 10) - (((size - 1) / 2) * 22)) + ((i3 / 2) * 22), PaintInfoGameScreen.fraButton2, icommand4.caption);
            } else {
                icommand4.setPos(i2 + (longwidth / 4) + ((i3 % 2) * ((longwidth / 2) + 2)), ((i - 15) - (((size - 1) / 2) * 30)) + ((i3 / 2) * 30), null, icommand4.caption);
            }
        }
    }

    public static void paintRectLowGraphic(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        mgraphics.setColor(colorLow[i5]);
        mgraphics.fillRect(i, i2, i3, i4, false);
    }
}
