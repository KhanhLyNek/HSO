package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectEnd;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.Item;
import GameObjects.MainItem;
import GameObjects.ObjectData;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Model.AvMain;
import Model.Menu2;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabRebuildItem.class */
public class TabRebuildItem extends MainTabNew {
    byte typeRebuild;
    int maxw;
    int maxh;
    int winfo;
    int[][] posMaterial;
    public static String[] mNameMaterial;
    int xinfo;
    int yinfo;
    public static DataRebuildItem[] dataRebuild;
    public static MainItem itemRe;
    public static MainItem itemPlus;
    public static MainItem itemFree;
    public static MainItem itemWing;
    public static int[] numMaterialInven;
    public static short[] idMaterial;
    public static int[] numWingMaterialInven;
    public static DataRebuildItem[] dataWing;
    public static int timeUseWing;
    public static String tilemaymanafter;
    public static boolean isShow;
    iCommand cmdHoiDap;
    iCommand cmdView;
    iCommand cmcloseInfo;
    iCommand cmdHoiReplace;
    iCommand cmdHoiReWing;
    iCommand cmdHopThanh;
    iCommand cmdDapnhanh;
    public byte typekham;
    int timeShowReplace;
    long time;
    public static byte TYPE_REBUILD = 0;
    public static byte TYPE_REPLACE_PLUS = 1;
    public static byte TYPE_REBUILD_WING = 2;
    public static byte TYPE_KHAM_NGOC = 3;
    public static byte TYPE_GHEP_NGOC = 4;
    public static byte TYPE_DUC_LO = 5;
    public static byte TYPE_ANY = 6;
    public static byte KHAM_NGOC = 0;
    public static byte GHEP_NGOC = 1;
    public static byte DUC_LO = 2;
    public static String[] numofGem = {"", "", "", "", ""};
    public static byte[] numColor = {0, 0, 0, 0, 0};
    public static int priceWing = 0;
    public static String nameWing = "";
    public static int isChetao = 0;
    public static short lvReWing = 0;
    public static short idWingOk = 0;
    public static String tilemayman = "test nao";
    public static byte isBeginEff = 0;
    public static byte isNextRebuild = 0;
    public static boolean isLucky = false;
    public static mVector vecEffRe = new mVector("TabRebuildItem vecEffRe");
    public static String contentShow = "";
    public static mVector vecGem = new mVector("TabRebuildItem vecGem");
    public static boolean resetItemReplace = false;
    public static boolean isDapnhanh = false;
    public static Item itemDaMayMan = null;
    public static byte countSetpaintinfo = 0;
    int indexPaint = 12;
    mVector vecListCmd = new mVector("TabRebuildItem vecListCmd");
    Scroll scr = new Scroll();
    public boolean ispaintitemRe = false;

    public TabRebuildItem(String str, byte b) {
        this.typeRebuild = (byte) 0;
        this.winfo = 140;
        isDapnhanh = false;
        this.isTabHopNguyenLieu = false;
        this.imgStarRebuild = null;
        this.typeRebuild = b;
        this.typeTab = (byte) 10;
        this.nameTab = str;
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        this.maxw = (MainTabNew.wblack - 8) / 32;
        this.maxh = (MainTabNew.hblack - 8) / 32;
        if (MainTabNew.longwidth > 0) {
            this.winfo = MainTabNew.longwidth;
            this.xinfo = MainTabNew.xlongwidth;
            this.yinfo = MainTabNew.ylongwidth + (MainTabNew.wOneItem / 2);
        } else {
            this.xinfo = (this.xBegin + (MainTabNew.wblack / 2)) - (this.winfo / 2);
            this.yinfo = this.yBegin + 4;
            if (this.yinfo > ((GameCanvas.h - GameCanvas.hCommand) - 150) - GameCanvas.hCommand) {
                this.yinfo = ((GameCanvas.h - GameCanvas.hCommand) - 150) - GameCanvas.hCommand;
            }
        }
        if (GameCanvas.isSmallScreen) {
            this.yinfo = ((GameCanvas.h - GameCanvas.hCommand) - 150) - GameCanvas.hCommand;
        }
        this.cmdBack = new iCommand(T.back, -1, this);
        this.cmdView = new iCommand(T.info, 1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        if (this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_DUC_LO || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_ANY) {
            this.posMaterial = mSystem.new_M_Int(6, 2);
            this.posMaterial[4][0] = this.xBegin + (MainTabNew.wblack / 2);
            this.posMaterial[4][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 52;
            this.posMaterial[2][0] = this.xBegin + (MainTabNew.wblack / 2) + 52;
            this.posMaterial[2][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 16;
            this.posMaterial[1][0] = this.xBegin + (MainTabNew.wblack / 2) + 32;
            this.posMaterial[1][1] = this.yBegin + (MainTabNew.hblack / 2) + 45;
            this.posMaterial[3][0] = (this.xBegin + (MainTabNew.wblack / 2)) - 32;
            this.posMaterial[3][1] = this.yBegin + (MainTabNew.hblack / 2) + 45;
            this.posMaterial[0][0] = (this.xBegin + (MainTabNew.wblack / 2)) - 52;
            this.posMaterial[0][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 16;
            this.posMaterial[5][0] = this.xBegin + (MainTabNew.wblack / 2);
            this.posMaterial[5][1] = this.yBegin + (MainTabNew.hblack / 2);
            isLucky = false;
            this.right = null;
            isShow = false;
            this.cmdHoiDap = new iCommand(T.dapdo, 0, this);
            this.cmdDapnhanh = new iCommand(T.dapnhanh, 9, this);
            if (MainTabNew.longwidth > 0) {
                int i = MainTabNew.ylongwidth + this.hSmall;
                int i2 = MainTabNew.xlongwidth;
                if (MainTabNew.is320) {
                    this.cmdHoiDap.setPos(i2 + (MainTabNew.longwidth / 2), i - 10, PaintInfoGameScreen.fraButton2, this.cmdHoiDap.caption);
                    this.cmdDapnhanh.setPos(i2 + (MainTabNew.longwidth / 2), i - 10, PaintInfoGameScreen.fraButton2, this.cmdDapnhanh.caption);
                } else {
                    this.cmdHoiDap.setPos(i2 + (MainTabNew.longwidth / 2), i - 15, null, this.cmdHoiDap.caption);
                    this.cmdDapnhanh.setPos(i2 + (MainTabNew.longwidth / 2), i - 15, null, this.cmdDapnhanh.caption);
                }
            } else if (GameCanvas.isTouch) {
                this.cmdHoiDap.setPos((iCommand.wButtonCmd / 2) + 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, this.cmdHoiDap.caption);
                this.cmdDapnhanh.setPos((iCommand.wButtonCmd / 2) + 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, this.cmdDapnhanh.caption);
                this.cmdView.setPos((GameCanvas.w - (iCommand.wButtonCmd / 2)) - 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, this.cmdView.caption);
            }
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            this.posMaterial = mSystem.new_M_Int(2, 2);
            this.posMaterial[1][0] = this.xBegin + (MainTabNew.wblack / 2) + 52;
            this.posMaterial[1][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 16;
            this.posMaterial[0][0] = (this.xBegin + (MainTabNew.wblack / 2)) - 52;
            this.posMaterial[0][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 16;
            this.cmdHoiReplace = new iCommand(T.replace, 3, this);
            if (MainTabNew.longwidth > 0) {
                int i3 = MainTabNew.ylongwidth + this.hSmall;
                int i4 = MainTabNew.xlongwidth;
                if (MainTabNew.is320) {
                    this.cmdHoiReplace.setPos(i4 + (MainTabNew.longwidth / 2), i3 - 10, PaintInfoGameScreen.fraButton2, this.cmdHoiReplace.caption);
                } else {
                    this.cmdHoiReplace.setPos(i4 + (MainTabNew.longwidth / 2), i3 - 15, null, this.cmdHoiReplace.caption);
                }
            } else if (GameCanvas.isTouch) {
                this.cmdHoiReplace.setPos((iCommand.wButtonCmd / 2) + 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, this.cmdHoiReplace.caption);
                this.cmdView.setPos((GameCanvas.w - (iCommand.wButtonCmd / 2)) - 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, this.cmdView.caption);
            }
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.posMaterial = mSystem.new_M_Int(7, 2);
            this.posMaterial[1][0] = this.xBegin + (MainTabNew.wblack / 2);
            this.posMaterial[1][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 52;
            this.posMaterial[5][0] = this.xBegin + (MainTabNew.wblack / 2) + 46;
            this.posMaterial[5][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 26;
            this.posMaterial[2][0] = this.xBegin + (MainTabNew.wblack / 2) + 46;
            this.posMaterial[2][1] = this.yBegin + (MainTabNew.hblack / 2) + 26;
            this.posMaterial[4][0] = this.xBegin + (MainTabNew.wblack / 2);
            this.posMaterial[4][1] = this.yBegin + (MainTabNew.hblack / 2) + 52;
            this.posMaterial[0][0] = (this.xBegin + (MainTabNew.wblack / 2)) - 46;
            this.posMaterial[0][1] = this.yBegin + (MainTabNew.hblack / 2) + 26;
            this.posMaterial[3][0] = (this.xBegin + (MainTabNew.wblack / 2)) - 46;
            this.posMaterial[3][1] = (this.yBegin + (MainTabNew.hblack / 2)) - 26;
            this.posMaterial[6][0] = this.xBegin + (MainTabNew.wblack / 2);
            this.posMaterial[6][1] = this.yBegin + (MainTabNew.hblack / 2);
            this.cmdHoiReWing = new iCommand(T.nangcap, 4, this);
            if (MainTabNew.longwidth > 0) {
                int i5 = MainTabNew.ylongwidth + this.hSmall;
                int i6 = MainTabNew.xlongwidth;
                if (MainTabNew.is320) {
                    this.cmdHoiReWing.setPos(i6 + (MainTabNew.longwidth / 2), i5 - 10, PaintInfoGameScreen.fraButton2, this.cmdHoiReWing.caption);
                } else {
                    this.cmdHoiReWing.setPos(i6 + (MainTabNew.longwidth / 2), i5 - 15, null, this.cmdHoiReWing.caption);
                }
            } else if (GameCanvas.isTouch) {
                this.cmdHoiReWing.setPos((iCommand.wButtonCmd / 2) + 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, this.cmdHoiReWing.caption);
                this.cmdView.setPos((GameCanvas.w - (iCommand.wButtonCmd / 2)) - 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, this.cmdView.caption);
            }
        }
        init();
        if (this.typeRebuild == TYPE_DUC_LO || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_REPLACE_PLUS || this.typeRebuild == TYPE_ANY) {
            this.imgStarRebuild = mImage.createImage("/interface/rebuild.img");
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            this.imgStarRebuild = mImage.createImage("/interface/rebuild2.img");
        }
    }

    @Override // InterfaceComponents.MainTabNew
    public void setPaintInfo() {
        this.mContent = null;
        this.mSubContent = null;
        this.mPlusContent = null;
        if (itemRe != null && itemRe.itemName != null) {
            this.name = itemRe.itemName;
        }
        this.listContent = null;
        if (itemRe != null) {
            int i = 1;
            this.mContent = itemRe.mcontent;
            this.moreInfoconten = itemRe.moreContenGem;
            this.mPlusContent = itemRe.mPlusContent;
            this.mPlusColor = itemRe.mPlusColor;
            this.mcolor = itemRe.mColor;
            this.colorName = itemRe.colorNameItem;
            if (itemRe.mcontent != null) {
                i = 1 + this.mContent.length;
            }
            if (itemRe.mPlusContent != null) {
                i += itemRe.mPlusContent.length;
            }
            if (i * GameCanvas.hText > MainTabNew.hMaxContent - 30) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, (i * GameCanvas.hText) - (MainTabNew.hMaxContent - 30));
            } else if (GameCanvas.isTouch) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, 0);
            }
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        String stringBuffer;
        switch (i) {
            case -1:
                backTab();
                break;
            case 0:
            case 9:
                if (i == 9) {
                    isDapnhanh = true;
                }
                if (this.typeRebuild == TYPE_ANY) {
                    if (this.isUPgradeMedal && itemRe != null) {
                        GlobalService.gI().Hop_rac((byte) 4, (short) itemRe.Id, (byte) itemRe.ItemCatagory);
                        vecGem.removeAllElements();
                        return;
                    }
                    if (this.isCreate_medal) {
                        GlobalService.gI().Hop_rac((byte) 3);
                        vecGem.removeAllElements();
                        break;
                    } else if (vecGem.size() > 0) {
                        MainItem mainItem = (MainItem) vecGem.elementAt(0);
                        if (mainItem != null) {
                            GlobalService.gI().Hop_rac((byte) 2, (short) mainItem.Id, (byte) mainItem.ItemCatagory);
                            vecGem.removeAllElements();
                            break;
                        }
                    } else {
                        GameCanvas.start_Ok_Dialog(T.bonguyenlieu);
                        break;
                    }
                } else if (this.typeRebuild == TYPE_DUC_LO) {
                    int i3 = -1;
                    if (itemRe != null && vecGem.size() > 0) {
                        MainItem mainItem2 = (MainItem) vecGem.elementAt(0);
                        if (mainItem2 != null) {
                            i3 = mainItem2.Id;
                        }
                        GlobalService.gI().KhamNgoc(DUC_LO, itemRe.Id, i3, -1, -1);
                        break;
                    } else {
                        GameCanvas.start_Ok_Dialog(T.chuaboduclo);
                        break;
                    }
                } else if (this.typeRebuild == TYPE_GHEP_NGOC) {
                    if (vecGem.size() > 0) {
                        MainItem mainItem3 = (MainItem) vecGem.elementAt(0);
                        if (mainItem3 != null) {
                            GlobalService.gI().KhamNgoc(GHEP_NGOC, mainItem3.Id, -1, -1, -1);
                            break;
                        }
                    } else {
                        GameCanvas.start_Ok_Dialog(T.bongoc);
                        break;
                    }
                } else if (this.typeRebuild == TYPE_KHAM_NGOC) {
                    if (itemRe != null && vecGem.size() > 0) {
                        int[] iArr = new int[3];
                        iArr[0] = -1;
                        iArr[1] = -1;
                        iArr[2] = -1;
                        for (int i4 = 0; i4 < vecGem.size(); i4++) {
                            MainItem mainItem4 = (MainItem) vecGem.elementAt(i4);
                            if (mainItem4 != null) {
                                iArr[i4] = mainItem4.Id;
                            }
                        }
                        GlobalService.gI().KhamNgoc(KHAM_NGOC, itemRe.Id, iArr[0], iArr[1], iArr[2]);
                        break;
                    } else {
                        GameCanvas.start_Ok_Dialog(T.chuaboitem);
                        break;
                    }
                } else if (itemRe != null) {
                    String str = "";
                    int i5 = 0;
                    mVector mvector = new mVector("TabRebuildItem menu");
                    if (dataRebuild[itemRe.tier].priceCoin != 0) {
                        mvector.addElement(new iCommand(T.coin, 2, 0, this));
                        str = new StringBuffer().append(T.dapbangxu).append(MainItem.getDotNumber(dataRebuild[itemRe.tier].priceCoin)).append(" ").append(T.coin).append("?").toString();
                        i5 = 0 + 1;
                    }
                    if (dataRebuild[itemRe.tier].priceGold != 0) {
                        mvector.addElement(new iCommand(T.gem, 2, 1, this));
                        str = new StringBuffer().append(T.dapbangxu).append(dataRebuild[itemRe.tier].priceGold).append(" ").append(T.gem).append("?").toString();
                        i5++;
                    }
                    if (i5 == 2) {
                        str = new StringBuffer().append(T.hoidapxuluong).append(MainItem.getDotNumber(dataRebuild[itemRe.tier].priceCoin)).append(" ").append(T.coin).append(T.hay).append(dataRebuild[itemRe.tier].priceGold).append(" ").append(T.gem).append("?").toString();
                    }
                    GameCanvas.menu2.startAt_NPC(mvector, str, Menu2.IdNPCLast, (byte) 2, false, 0);
                    break;
                }
                break;
            case 1:
                if (MainTabNew.longwidth == 0) {
                    if ((itemRe != null && (this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_KHAM_NGOC)) || ((itemPlus != null && itemFree != null && this.typeRebuild == TYPE_REPLACE_PLUS) || ((this.typeRebuild == TYPE_REBUILD_WING && dataWing != null) || this.typeRebuild == TYPE_ANY))) {
                        isShow = !isShow;
                    } else {
                        isShow = false;
                    }
                    if (isShow) {
                        if (!GameCanvas.isTouch) {
                            this.cmdView.caption = T.close;
                            break;
                        } else {
                            this.cmdView.setPos((this.xinfo + this.winfo) - 12, this.yinfo + 10, PaintInfoGameScreen.fraCloseMenu, "");
                            break;
                        }
                    } else if (!GameCanvas.isTouch) {
                        this.cmdView.caption = T.info;
                        break;
                    } else {
                        this.cmdView.caption = T.info;
                        this.cmdView.setPos(GameCanvas.w - (iCommand.wButtonCmd / 2), GameCanvas.h - (iCommand.hButtonCmd / 2), null, this.cmdView.caption);
                        break;
                    }
                }
                break;
            case 2:
                if (itemRe != null) {
                    GameCanvas.menu2.doCloseMenu();
                    GlobalService.gI().Rebuild_Item((byte) 2, (short) 0, (byte) i2);
                    if (MainTabNew.longwidth == 0) {
                        isShow = false;
                        if (!GameCanvas.isTouch) {
                            this.cmdView.caption = T.info;
                        } else {
                            this.cmdView.caption = T.info;
                            this.cmdView.setPos(GameCanvas.w - (iCommand.wButtonCmd / 2), GameCanvas.h - (iCommand.hButtonCmd / 2), null, this.cmdView.caption);
                        }
                    }
                    GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                    break;
                }
                break;
            case 3:
                if (itemFree != null && itemPlus != null) {
                    GlobalService.gI().Replace_Item((byte) 1, (short) 0);
                    GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                    break;
                }
                break;
            case 4:
                mVector mvector2 = new mVector("TabRebuildItem menu2");
                if (itemWing == null) {
                    stringBuffer = new StringBuffer().append(T.hoiTaoCanh).append(this.nameTab).append("? ").append(T.phi).append(": ").append(MainItem.getDotNumber(priceWing)).append(" ").append(T.coin).append(", ").append(T.LVyeucau).append((int) lvReWing).append(", ").append(T.timeyeucau).append(PaintInfoGameScreen.getStringTime(timeUseWing)).append(".").toString();
                    mvector2.addElement(new iCommand(T.taoCanh, 5, 0, this));
                } else {
                    stringBuffer = new StringBuffer().append(T.hoinangcapcanh).append(nameWing).append("? ").append(T.phi).append(": ").append(MainItem.getDotNumber(priceWing)).append(" ").append(T.coin).append(", ").append(T.LVyeucau).append((int) lvReWing).append(", ").append(T.timeyeucau).append(PaintInfoGameScreen.getStringTime(timeUseWing)).append(".").toString();
                    mvector2.addElement(new iCommand(T.nangcap, 6, 0, this));
                }
                GameCanvas.menu2.startAt_NPC(mvector2, stringBuffer, Menu2.IdNPCLast, (byte) 2, false, 0);
                break;
            case 5:
                GameCanvas.menu2.doCloseMenu();
                GlobalService.gI().Rebuild_Wing((byte) 1, isChetao, (short) 0);
                break;
            case 6:
                GameCanvas.menu2.doCloseMenu();
                if (itemWing != null) {
                    GlobalService.gI().Rebuild_Wing((byte) 3, isChetao, (short) itemWing.Id);
                    break;
                }
                break;
            case 7:
                GameCanvas.menu2.doCloseMenu();
                GlobalService.gI().Rebuild_Item((byte) 5, (short) itemRe.Id, (byte) itemRe.ItemCatagory);
                if (MainTabNew.longwidth == 0) {
                    isShow = false;
                    if (!GameCanvas.isTouch) {
                        this.cmdView.caption = T.info;
                    } else {
                        this.cmdView.caption = T.info;
                        this.cmdView.setPos(GameCanvas.w - (iCommand.wButtonCmd / 2), GameCanvas.h - (iCommand.hButtonCmd / 2), null, this.cmdView.caption);
                    }
                }
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                break;
        }
        super.commandPointer(i, i2);
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        this.cmdView.caption = T.info;
        if (this.typeRebuild == TYPE_REBUILD || this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_DUC_LO || this.typeRebuild == TYPE_ANY) {
            initRebuild();
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            initReplace();
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            initReWing();
        }
    }

    private void initReplace() {
        MainTabNew.timePaintInfo = 0;
        if (MainTabNew.longwidth > 0) {
            isShow = true;
        }
        if (!GameCanvas.isTouch) {
            if (itemPlus != null && itemFree != null && !resetItemReplace) {
                this.left = this.cmdView;
                this.center = this.cmdHoiReplace;
            }
            this.right = this.cmdBack;
        } else {
            this.vecListCmd.removeAllElements();
            if (!resetItemReplace && itemPlus != null && itemFree != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiReplace);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiReplace);
                }
            }
        }
        this.listContent = null;
        vecEffRe.removeAllElements();
        isBeginEff = (byte) 0;
        isNextRebuild = (byte) -1;
        if (resetItemReplace) {
            itemFree = null;
            itemPlus = null;
            resetItemReplace = false;
        }
        if (itemPlus == null && itemFree == null) {
            GameCanvas.menu2.startAt_NPC(null, T.boitemreplace, Menu2.IdNPCLast, (byte) 2, false, 0);
        }
    }

    private void initRebuild() {
        isDapnhanh = false;
        MainTabNew.timePaintInfo = 0;
        if (MainTabNew.longwidth > 0) {
            isShow = true;
        }
        if (!GameCanvas.isTouch) {
            if (itemRe != null || this.typeRebuild == TYPE_GHEP_NGOC || this.typeRebuild == TYPE_ANY) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
            }
            this.right = this.cmdBack;
        } else {
            this.vecListCmd.removeAllElements();
            if (itemRe != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiDap);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiDap);
                }
            }
            if (this.typeRebuild == TYPE_GHEP_NGOC) {
                this.vecListCmd.addElement(this.cmdHoiDap);
                this.cmdHoiDap.caption = T.hopngoc;
            }
            if (this.typeRebuild == TYPE_ANY) {
                this.vecListCmd.addElement(this.cmdHoiDap);
                this.cmdHoiDap.caption = T.hoprac;
            }
        }
        this.listContent = null;
        vecEffRe.removeAllElements();
        isBeginEff = (byte) 0;
        isNextRebuild = (byte) 0;
        if (itemRe == null && this.typeRebuild != TYPE_GHEP_NGOC && this.typeRebuild != TYPE_ANY) {
            GameCanvas.menu2.startAt_NPC(null, this.isTabHopNguyenLieu ? T.hopNguyenLieu : T.bovatphamvao, Menu2.IdNPCLast, (byte) 2, false, 0);
        }
    }

    private void initReWing() {
        MainTabNew.timePaintInfo = 0;
        if (MainTabNew.longwidth > 0) {
            isShow = true;
        }
        if (!GameCanvas.isTouch) {
            if (!resetItemReplace && dataWing != null) {
                this.left = this.cmdView;
                this.cmdHoiReWing.caption = T.nangcap;
                if (itemWing == null) {
                    this.cmdHoiReWing.caption = T.taoCanh;
                }
                this.center = this.cmdHoiReWing;
            }
            this.right = this.cmdBack;
        } else {
            this.vecListCmd.removeAllElements();
            if (!resetItemReplace && dataWing != null) {
                this.cmdHoiReWing.caption = T.nangcap;
                if (itemWing == null) {
                    this.cmdHoiReWing.caption = T.taoCanh;
                }
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiReWing);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiReWing);
                }
            }
        }
        this.listContent = null;
        vecEffRe.removeAllElements();
        isBeginEff = (byte) 0;
        isNextRebuild = (byte) 0;
        if (resetItemReplace) {
            itemWing = null;
            resetItemReplace = false;
        }
    }

    @Override // InterfaceComponents.MainTabNew
    public void backTab() {
        if (MainTabNew.longwidth == 0) {
            isShow = false;
        }
        MainTabNew.timePaintInfo = 0;
        MainTabNew.Focus = (byte) 0;
        vecEffRe.removeAllElements();
        super.backTab();
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (isBeginEff == 0 && !GameCanvas.isTouch && MainTabNew.Focus == 1) {
            mgraphics.fillRect(this.xBegin + 2, this.yBegin + 2, MainTabNew.wblack - 4, MainTabNew.hblack - 4, false);
        }
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mgraphics, this.xBegin + 4, this.yBegin + 4, MainTabNew.wblack - 8, MainTabNew.hblack - 8, 4);
        } else {
            paintRect(mgraphics);
        }
        mgraphics.drawImage(this.imgStarRebuild, (this.xBegin + (MainTabNew.wblack / 2)) - 54, (this.yBegin + (MainTabNew.hblack / 2)) - 52, 0, false);
        mgraphics.drawRegion(this.imgStarRebuild, 0, 0, 54, 105, 2, this.xBegin + (MainTabNew.wblack / 2), (this.yBegin + (MainTabNew.hblack / 2)) - 52, 0, false);
        if (this.typeRebuild == TYPE_ANY) {
            paintAny(mgraphics);
        } else if (this.typeRebuild == TYPE_DUC_LO) {
            paintDucLo(mgraphics);
        } else if (this.typeRebuild == TYPE_GHEP_NGOC) {
            paintGhepNgoc(mgraphics);
        } else if (this.typeRebuild == TYPE_KHAM_NGOC) {
            paintKhamNgoc(mgraphics);
        } else if (this.typeRebuild == TYPE_REBUILD) {
            paintRebuild(mgraphics);
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            paintReplace(mgraphics);
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            paintReWing(mgraphics);
        }
        for (int i = 0; i < vecEffRe.size(); i++) {
            ((MainEffect) vecEffRe.elementAt(i)).paint(mgraphics);
        }
        if (isShow || MainTabNew.longwidth > 0) {
            paintInfo(mgraphics, this.xinfo, this.yinfo);
        }
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null) {
            if ((MainTabNew.Focus == 1 || MainTabNew.longwidth > 0) && this.vecListCmd != null) {
                for (int i2 = 0; i2 < this.vecListCmd.size(); i2++) {
                    iCommand icommand = (iCommand) this.vecListCmd.elementAt(i2);
                    icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
                }
            }
        }
    }

    private void paintReWing(mGraphics mgraphics) {
        for (int i = 0; i < this.posMaterial.length; i++) {
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mgraphics, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
            } else {
                mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
            if (i == 6) {
                if (itemWing != null) {
                    itemWing.paintItem(mgraphics, this.posMaterial[6][0], this.posMaterial[6][1], 21, 1, 0);
                    if (isBeginEff == 0) {
                        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("Lv ").append((int) itemWing.tier).toString(), this.posMaterial[6][0], this.posMaterial[6][1] - 22, 2, false);
                    }
                }
            } else if (dataWing != null && i < dataWing.length && isBeginEff == 0) {
                MainItem material = Item.getMaterial(dataWing[i].id);
                if (material != null) {
                    material.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 0, 0);
                    if (numWingMaterialInven[i] >= dataWing[i].valueWing) {
                        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("").append((int) dataWing[i].valueWing).toString(), this.posMaterial[i][0], this.posMaterial[i][1] + 11, 2, false);
                    } else {
                        mFont.tahoma_7_red.drawString(mgraphics, new StringBuffer().append("").append((int) dataWing[i].valueWing).toString(), this.posMaterial[i][0], this.posMaterial[i][1] + 11, 2, false);
                    }
                } else {
                    Item.put_Material(dataWing[i].id);
                }
            }
            mgraphics.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
        }
    }

    public static void addGem(MainItem mainItem) {
        vecGem.addElement(mainItem);
    }

    public static void removeKhamNgoc(Item item) {
        for (int i = 0; i < vecGem.size(); i++) {
            MainItem mainItem = (MainItem) vecGem.elementAt(i);
            if (mainItem != null && mainItem.Id == item.Id) {
                vecGem.removeElement(mainItem);
            }
        }
    }

    public static boolean isKham(Item item) {
        for (int i = 0; i < vecGem.size(); i++) {
            MainItem mainItem = (MainItem) vecGem.elementAt(i);
            if (mainItem != null && mainItem.Id == item.Id) {
                return true;
            }
        }
        return false;
    }

    public void paintAny(mGraphics mgraphics) {
        for (int i = 0; i < this.posMaterial.length; i++) {
            if (isBeginEff == 0 || i == 5) {
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mgraphics, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
                } else {
                    mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                }
                if (itemRe != null && i == 5 && (isBeginEff != 5 || this.time > 15)) {
                    if (itemRe.ItemCatagory == 7) {
                        MainItem material = Item.getMaterial(itemRe.Id);
                        if (material != null) {
                            material.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                        } else {
                            Item.put_Material(itemRe.Id);
                        }
                    } else {
                        itemRe.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                    }
                }
                mgraphics.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
        }
        for (int i2 = 0; i2 < vecGem.size(); i2++) {
            MainItem mainItem = (MainItem) vecGem.elementAt(i2);
            if (mainItem != null) {
                MainItem material2 = Item.getMaterial(mainItem.Id);
                if (material2 != null) {
                    material2.paintItemNew(mgraphics, this.posMaterial[i2][0], this.posMaterial[i2][1], 21, 0, 0);
                } else {
                    Item.put_Material(mainItem.Id);
                }
                if (!numofGem[i2].equals("")) {
                }
            }
        }
    }

    public void paintGhepNgoc(mGraphics mgraphics) {
        for (int i = 0; i < this.posMaterial.length; i++) {
            if (isBeginEff == 0 || i == 5) {
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mgraphics, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
                } else {
                    mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                }
                if (itemRe != null && i == 5 && (isBeginEff != 5 || this.time > 15)) {
                    MainItem material = Item.getMaterial(itemRe.Id);
                    if (material != null) {
                        material.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                    } else {
                        Item.put_Material(itemRe.Id);
                    }
                }
                mgraphics.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
        }
        for (int i2 = 0; i2 < vecGem.size(); i2++) {
            MainItem mainItem = (MainItem) vecGem.elementAt(i2);
            if (mainItem != null) {
                mainItem.paintItemNew(mgraphics, this.posMaterial[i2][0], this.posMaterial[i2][1], 21, 0, 0);
            }
        }
    }

    public void updateAny() {
        if (itemRe != null && countSetpaintinfo > 0) {
            setPaintInfo();
        }
        if (countSetpaintinfo > 0) {
            countSetpaintinfo = (byte) (countSetpaintinfo - 1);
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                byte b = itemRe.tier;
                if (b > 14) {
                    b = 14;
                }
                for (int i = 0; i < dataRebuild[b].mValue.length; i++) {
                    if (dataRebuild[b].mValue[i] > 0) {
                        addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                    }
                }
                if (isLucky) {
                    addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte) 2;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 2) {
            if (this.time < 10) {
                this.time++;
                if (this.time == 10) {
                    mSound.playSound(24, mSound.volumeSound);
                    return;
                }
                return;
            }
            if (GameCanvas.timeNow - this.time > 12000) {
                this.time = GameCanvas.timeNow;
                addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
            }
            if (GameCanvas.timeNow - this.time > 3700) {
                if (isNextRebuild == 3 || isNextRebuild == 4) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                    return;
                }
                return;
            }
            return;
        }
        if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 5) {
            this.time++;
            if (vecEffRe.size() == 0 || this.time >= 100) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte) 2, false, 0);
                this.time = 0L;
                isBeginEff = (byte) 6;
                this.ispaintitemRe = true;
                return;
            }
            return;
        }
        if (isBeginEff == 6) {
            if (itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = (byte) 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = (byte) 0;
                return;
            }
            this.vecListCmd.removeAllElements();
            if (itemRe != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiDap);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiDap);
                }
            }
        }
    }

    public void updateGhepngoc() {
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                for (int i = 0; i < dataRebuild[itemRe.tier].mValue.length; i++) {
                    if (dataRebuild[itemRe.tier].mValue[i] > 0) {
                        addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                    }
                }
                if (isLucky) {
                    addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte) 2;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 2) {
            if (this.time < 10) {
                this.time++;
                if (this.time == 10) {
                    mSound.playSound(24, mSound.volumeSound);
                    return;
                }
                return;
            }
            if (GameCanvas.timeNow - this.time > 12000) {
                this.time = GameCanvas.timeNow;
                addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
            }
            if (GameCanvas.timeNow - this.time > 3700) {
                if (isNextRebuild == 3 || isNextRebuild == 4) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                    return;
                }
                return;
            }
            return;
        }
        if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 5) {
            this.time++;
            if (vecEffRe.size() == 0 || this.time >= 100) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte) 2, false, 0);
                this.time = 0L;
                isBeginEff = (byte) 6;
                this.ispaintitemRe = true;
                return;
            }
            return;
        }
        if (isBeginEff == 6) {
            if (itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = (byte) 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = (byte) 0;
                return;
            }
            this.vecListCmd.removeAllElements();
            if (itemRe != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiDap);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiDap);
                }
            }
        }
    }

    public void paintKhamNgoc(mGraphics mgraphics) {
        for (int i = 0; i < this.posMaterial.length; i++) {
            if (isBeginEff == 0 || i == 5) {
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mgraphics, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
                } else {
                    mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                }
                if (itemRe != null && i == 5 && (isBeginEff != 5 || this.time > 15)) {
                    itemRe.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                }
                mgraphics.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
        }
        for (int i2 = 0; i2 < vecGem.size(); i2++) {
            MainItem mainItem = (MainItem) vecGem.elementAt(i2);
            if (mainItem != null) {
                mainItem.paintItemNew(mgraphics, this.posMaterial[i2][0], this.posMaterial[i2][1], 21, 0, 0);
            }
        }
    }

    public void paintDucLo(mGraphics mgraphics) {
        for (int i = 0; i < this.posMaterial.length; i++) {
            if (isBeginEff == 0 || i == 5) {
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mgraphics, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
                } else {
                    mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                }
                if (itemRe != null && i == 5 && (isBeginEff != 5 || this.time > 15)) {
                    itemRe.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                }
                mgraphics.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
        }
        for (int i2 = 0; i2 < vecGem.size(); i2++) {
            MainItem mainItem = (MainItem) vecGem.elementAt(i2);
            if (mainItem != null) {
                mainItem.paintItemNew(mgraphics, this.posMaterial[i2][0], this.posMaterial[i2][1], 21, 0, 0);
            }
        }
    }

    public void paintRebuild(mGraphics mgraphics) {
        for (int i = 0; i < this.posMaterial.length; i++) {
            if (isBeginEff == 0 || i == 5) {
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mgraphics, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
                } else {
                    mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                }
                if (itemRe != null) {
                    if (this.isTabHopNguyenLieu) {
                        if (i < 5) {
                            itemRe.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                        } else if (itemRe.itemClone != null) {
                            MainItem material = Item.getMaterial(itemRe.itemClone.Id);
                            if (material != null) {
                                material.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                            } else {
                                Item.put_Material(itemRe.itemClone.Id);
                            }
                        }
                    } else if (i < 5) {
                        MainItem material2 = Item.getMaterial(idMaterial[i]);
                        if (material2 != null) {
                            if ((material2.typeMaterial == 11 && isLucky) || (i < 4 && dataRebuild[itemRe.tier].mValue[i] > 0)) {
                                if (material2.typeMaterial == 11 && isLucky && itemDaMayMan != null) {
                                    itemDaMayMan.paintItem_notnum(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 0, 0);
                                } else {
                                    material2.paintItem_notnum(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 0, 0);
                                }
                            }
                        } else {
                            Item.put_Material(idMaterial[i]);
                        }
                    } else if (i == 5) {
                        if (isBeginEff != 5 || this.time > 15) {
                            itemRe.paintItem(mgraphics, this.posMaterial[i][0], this.posMaterial[i][1], 21, 1, 0);
                        }
                        if (isBeginEff == 0) {
                            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("Lv ").append((int) itemRe.tier).toString(), this.posMaterial[i][0], this.posMaterial[i][1] - 22, 2, false);
                        }
                    }
                }
                mgraphics.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                if (itemRe != null) {
                    if (i < 4 && dataRebuild[itemRe.tier].mValue[i] > numMaterialInven[i]) {
                        mgraphics.drawImage(AvMain.imgHotKey2, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                    } else if (i == 5 && isBeginEff == 5 && isNextRebuild == 4) {
                        mgraphics.drawImage(AvMain.imgHotKey2, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
                    }
                }
            }
        }
    }

    public void paintRect(mGraphics mgraphics) {
        for (int i = 0; i <= this.maxw; i++) {
            for (int i2 = 0; i2 <= this.maxh; i2++) {
                this.indexPaint = 12;
                if (i2 == 0) {
                    this.indexPaint = 12;
                }
                if (i == this.maxw) {
                    if (i2 == this.maxh) {
                        mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], ((this.xBegin + 4) + (MainTabNew.wblack - 8)) - 32, (((this.yBegin + 4) + MainTabNew.hblack) - 8) - 32, 0, false);
                    } else {
                        mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], ((this.xBegin + 4) + (MainTabNew.wblack - 8)) - 32, this.yBegin + 4 + (i2 * 32), 0, false);
                    }
                } else if (i2 == this.maxh) {
                    mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (i * 32), (((this.yBegin + 4) + MainTabNew.hblack) - 8) - 32, 0, false);
                } else {
                    mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (i * 32), this.yBegin + 4 + (i2 * 32), 0, false);
                }
            }
        }
    }

    public void paintInfo(mGraphics mgraphics, int i, int i2) {
        if (this.typeRebuild == TYPE_ANY) {
            paintinfoAny(mgraphics, i, i2);
            if ((itemRe == null && this.isCreate_medal) || (itemRe != null && this.isUPgradeMedal)) {
                paintconTenAny(mgraphics, i, i2 - 10);
                return;
            }
            return;
        }
        if (this.typeRebuild == TYPE_GHEP_NGOC) {
            paintinfoGhepNgoc(mgraphics, i, i2);
            return;
        }
        if (this.typeRebuild == TYPE_KHAM_NGOC || this.typeRebuild == TYPE_DUC_LO) {
            paintinfoKhamNgoc(mgraphics, i, i2);
            return;
        }
        if (this.typeRebuild == TYPE_REBUILD) {
            paintInfoRebuild(mgraphics, i, i2);
            return;
        }
        if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            if (!resetItemReplace) {
                paintInfoReplace(mgraphics, i, i2);
            }
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            paintInfoReWing(mgraphics, i, i2);
        }
    }

    private void paintInfoReWing(mGraphics mgraphics, int i, int i2) {
        int i3;
        int i4;
        if (dataWing == null) {
            return;
        }
        int i5 = GameCanvas.hText;
        if (MainTabNew.longwidth > 0) {
            MainTabNew.paintNameItem(mgraphics, i + (MainTabNew.longwidth / 2), i2 - (MainTabNew.wOneItem / 4), MainTabNew.longwidth, nameWing, 5);
            i3 = i2 + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
            i4 = i + 4;
        } else {
            paintFormList(mgraphics, i, i2, this.winfo, 150, nameWing);
            i3 = i2 + GameCanvas.hCommand + 2;
            i4 = i + 4;
        }
        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.LVyeucau).append((int) lvReWing).toString(), i4, i3, 0, false);
        int i6 = i3 + i5;
        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.timeyeucau).append(PaintInfoGameScreen.getStringTime(timeUseWing)).toString(), i4, i6, 0, false);
        int i7 = i6 + i5;
        for (int i8 = 0; i8 < dataWing.length; i8++) {
            MainItem material = Item.getMaterial(dataWing[i8].id);
            if (material != null) {
                mFont mfont = mFont.tahoma_7_white;
                if (dataWing[i8].valueWing > numWingMaterialInven[i8]) {
                    mfont = mFont.tahoma_7_red;
                }
                if (material.itemName != null) {
                    mfont.drawString(mgraphics, material.itemName, i4 + 4, i7, 0, false);
                }
                mfont.drawString(mgraphics, new StringBuffer().append((int) dataWing[i8].valueWing).append("/").append(numWingMaterialInven[i8]).toString(), i4 + (this.winfo / 2) + 10, i7, 0, false);
                i7 += i5;
            } else {
                Item.put_Material(dataWing[i8].id);
            }
        }
    }

    private void paintInfoReplace(mGraphics mgraphics, int i, int i2) {
        int i3;
        int i4;
        if (itemPlus == null || itemFree == null) {
            return;
        }
        int i5 = GameCanvas.hText;
        if (MainTabNew.longwidth > 0) {
            mFont.tahoma_7b_white.drawString(mgraphics, T.replace, i + (MainTabNew.longwidth / 2), i2 - (MainTabNew.wOneItem / 4), 2, false);
            i3 = i2 + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
            i4 = i + 4;
        } else {
            paintFormList(mgraphics, i, i2, this.winfo, 100, T.replace);
            i3 = i2 + GameCanvas.hCommand + 2;
            i4 = i + 4;
        }
        MainTabNew.setTextColor(itemPlus.colorNameItem).drawString(mgraphics, itemPlus.itemName, i4, i3, 0, false);
        int i6 = i3 + i5;
        MainTabNew.setTextColor(itemFree.colorNameItem).drawString(mgraphics, itemFree.itemName, i4, i6, 0, false);
        int i7 = i6 + i5;
        mFont.tahoma_7_white.drawString(mgraphics, T.plusnhanduoc, i4, i7, 0, false);
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(itemPlus.tier - 2).append(" > ").append((int) itemPlus.tier).toString(), i4, i7 + i5, 0, false);
    }

    public void paintinfoKhamNgoc(mGraphics mgraphics, int i, int i2) {
        if (itemRe == null) {
            return;
        }
        paintContentNew(mgraphics, false);
    }

    public void paintinfoAny(mGraphics mgraphics, int i, int i2) {
        if (itemRe == null) {
            return;
        }
        if (MainTabNew.longwidth > 0 && itemRe.itemName != null) {
            MainTabNew.paintNameItem(mgraphics, i + (MainTabNew.longwidth / 2), i2 - (MainTabNew.wOneItem / 4), MainTabNew.longwidth, itemRe.itemName, itemRe.colorNameItem);
            int i3 = i2 + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
            int i4 = i + 4;
        } else {
            if (itemRe.itemName != null) {
                paintFormList(mgraphics, i, i2, this.winfo, 150, itemRe.itemName);
            }
            int i5 = i2 + GameCanvas.hCommand + 2;
            int i6 = i + 4;
        }
    }

    public void paintconTenAny(mGraphics mgraphics, int i, int i2) {
        int i3;
        int i4;
        int i5 = GameCanvas.hText - 2;
        if (MainTabNew.longwidth > 0) {
            i3 = i2 + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
            i4 = i + 4;
        } else {
            String str = T.info;
            if (itemRe != null && itemRe.itemName != null) {
                str = itemRe.itemName;
            }
            paintFormList(mgraphics, i, i2, this.winfo, 150, str);
            i3 = i2 + GameCanvas.hCommand + 2;
            i4 = i + 4;
        }
        int i6 = i3 + i5;
        int i7 = 0;
        if (!GameCanvas.isTouch) {
            i7 = 20;
        }
        GameCanvas.resetTrans(mgraphics);
        if (GameCanvas.isTouch) {
            this.scr.setStyle(vecGem.size() + 2, GameCanvas.hText + 2, i4, i6 - (GameCanvas.hText * 2), this.winfo, i5 * (vecGem.size() + 2) * 2, true, 1);
            this.scr.setClip(mgraphics, i4, i6, this.winfo, i5 * (vecGem.size() + 2) * 2);
        }
        mFont.tahoma_7b_white.drawString(mgraphics, T.nguyenlieu, i4, i6 - i7, 0, false);
        int i8 = i6 + i5;
        for (int i9 = 0; i9 < vecGem.size(); i9++) {
            MainItem mainItem = (MainItem) vecGem.elementAt(i9);
            if (mainItem != null) {
                MainItem material = Item.getMaterial(mainItem.Id);
                if (material != null) {
                    mFont mfont = mFont.tahoma_7_green;
                    if (material.itemName != null && !material.itemName.equals("")) {
                        MainTabNew.setTextColorName(material.colorNameItem).drawString(mgraphics, material.itemName, i4 + 4, i8 - i7, 0, true);
                    }
                    if (numColor[i9] == 0) {
                        mfont = mFont.tahoma_7_red;
                    }
                    int i10 = i8 + i5;
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.soluong).append(" ").toString(), i4 + 4, i10 - i7, 0, true);
                    mfont.drawString(mgraphics, numofGem[i9], i4 + 50, i10 - i7, 0, true);
                    i8 = i10 + i5;
                } else {
                    Item.put_Material(mainItem.Id);
                }
            }
        }
        GameCanvas.resetTrans(mgraphics);
    }

    public void paintinfoGhepNgoc(mGraphics mgraphics, int i, int i2) {
        if (itemRe == null) {
            return;
        }
        if (MainTabNew.longwidth > 0 && itemRe.itemName != null) {
            MainTabNew.paintNameItem(mgraphics, i + (MainTabNew.longwidth / 2), i2 - (MainTabNew.wOneItem / 4), MainTabNew.longwidth, itemRe.itemName, itemRe.colorNameItem);
            int i3 = i2 + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
            int i4 = i + 4;
        } else {
            if (itemRe.itemName != null) {
                paintFormList(mgraphics, i, i2, this.winfo, 150, itemRe.itemName);
            }
            int i5 = i2 + GameCanvas.hCommand + 2;
            int i6 = i + 4;
        }
    }

    public void paintInfoRebuild(mGraphics mgraphics, int i, int i2) {
        int i3;
        int i4;
        if (itemRe == null) {
            return;
        }
        int i5 = GameCanvas.hText - 2;
        if (MainTabNew.longwidth > 0) {
            if (itemRe.itemName != null) {
                MainTabNew.paintNameItem(mgraphics, i + (MainTabNew.longwidth / 2), i2 - (MainTabNew.wOneItem / 4), MainTabNew.longwidth, itemRe.itemName, itemRe.colorNameItem);
            }
            if (itemRe.tier >= 15) {
                return;
            }
            i3 = i2 + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
            i4 = i + 4;
        } else {
            paintFormList(mgraphics, i, i2, this.winfo, 150, itemRe.itemName);
            i3 = i2 + GameCanvas.hCommand + 2;
            i4 = i + 4;
        }
        if (this.isTabHopNguyenLieu) {
            if (itemRe.itemClone == null) {
                return;
            }
            mFont.tahoma_7b_blue.getWidth(itemRe.infoHop[0]);
            for (String str : mFont.tahoma_7b_blue.splitFontArray(itemRe.infoHop[0], MainTabNew.longwidth == 0 ? (this.maxw - 1) * 32 : MainTabNew.longwidth)) {
                mFont.tahoma_7b_blue.drawString(mgraphics, str, i4, i3, 0, false);
                i3 += i5;
            }
            mFont.tahoma_7b_white.drawString(mgraphics, T.chiphi, i4, i3, 0, false);
            mFont.tahoma_7_white.drawString(mgraphics, itemRe.infoHop[1], i4 + 4, i3 + (i5 - 2), 0, false);
            return;
        }
        if (itemRe.tier < 15) {
            mFont.tahoma_7b_blue.drawString(mgraphics, new StringBuffer().append("+").append((int) itemRe.tier).append(" > +").append(itemRe.tier + 1).toString(), i4, i3, 0, false);
        }
        int i6 = i3 + i5;
        mFont.tahoma_7b_white.drawString(mgraphics, T.nguyenlieu, i4, i6, 0, false);
        int i7 = i6 + i5;
        for (int i8 = 0; i8 < dataRebuild[itemRe.tier].mValue.length; i8++) {
            mFont mfont = mFont.tahoma_7_white;
            if (dataRebuild[itemRe.tier].mValue[i8] > numMaterialInven[i8]) {
                mfont = mFont.tahoma_7_red;
            }
            MainItem material = Item.getMaterial(idMaterial[i8]);
            if (material != null) {
                mNameMaterial[i8] = material.itemName;
            } else {
                Item.put_Material(idMaterial[i8]);
            }
            if (mNameMaterial[i8] != null) {
                mfont.drawString(mgraphics, mNameMaterial[i8], i4 + 4, i7, 0, false);
            }
            mfont.drawString(mgraphics, new StringBuffer().append((int) dataRebuild[itemRe.tier].mValue[i8]).append("/").append(numMaterialInven[i8]).toString(), i4 + (this.winfo / 2) + 10, i7, 0, false);
            i7 += i5;
        }
        mFont.tahoma_7b_white.drawString(mgraphics, T.tilemayman, i4, i7, 0, false);
        int i9 = i7 + i5;
        mFont.tahoma_7_white.drawString(mgraphics, tilemayman, i4 + 4, i9, 0, false);
        int i10 = i9 + i5;
        mFont.tahoma_7b_white.drawString(mgraphics, T.chiphi, i4, i10, 0, false);
        paintPrice(mgraphics, i4, i10 + (i5 - 2));
    }

    public void paintPrice(mGraphics mgraphics, int i, int i2) {
        if (dataRebuild[itemRe.tier].priceCoin == 0) {
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(dataRebuild[itemRe.tier].priceGold).append(" ").append(T.gem).toString(), i + 4, i2, 0, false);
        } else if (dataRebuild[itemRe.tier].priceGold == 0) {
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(dataRebuild[itemRe.tier].priceCoin).append(" ").append(T.coin).toString(), i + 4, i2, 0, false);
        } else {
            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(dataRebuild[itemRe.tier].priceCoin).append(" ").append(T.coin).append(T.hoac).append(dataRebuild[itemRe.tier].priceGold).append(" ").append(T.gem).toString(), i + 4, i2, 0, false);
        }
    }

    public void paintReplace(mGraphics mgraphics) {
        for (int i = 0; i < this.posMaterial.length; i++) {
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mgraphics, this.posMaterial[i][0] - 10, this.posMaterial[i][1] - 10, 20, 20, 2);
            } else {
                mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 20, 20, 0, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
            }
            if (itemPlus != null && i == 0) {
                itemPlus.paintItem(mgraphics, this.posMaterial[0][0], this.posMaterial[0][1], 21, 1, 0);
                if (isBeginEff == 0) {
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("Lv ").append((int) itemPlus.tier).toString(), this.posMaterial[0][0], this.posMaterial[0][1] - 22, 2, false);
                }
            }
            if (itemFree != null && i == 1) {
                itemFree.paintItem(mgraphics, this.posMaterial[1][0], this.posMaterial[1][1], 21, 1, 0);
                if (isBeginEff == 0) {
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("Lv ").append((int) itemFree.tier).toString(), this.posMaterial[1][0], this.posMaterial[1][1] - 22, 2, false);
                }
            }
            mgraphics.drawImage(AvMain.imgHotKey, this.posMaterial[i][0], this.posMaterial[i][1], 3, false);
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (isBeginEff != 0) {
            return;
        }
        if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
            MainTabNew.Focus = (byte) 0;
            GameCanvas.clearKeyHold();
        }
        super.updatekey();
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        if (this.typeRebuild == TYPE_REBUILD) {
            updateRebuild();
            if (this.isTabHopNguyenLieu) {
                this.cmdHoiDap.caption = T.hopThanh;
                this.cmdHoiDap.indexMenu = (byte) 7;
            } else {
                this.cmdHoiDap.caption = T.dapdo;
                this.cmdHoiDap.indexMenu = (byte) 0;
            }
        } else if (this.typeRebuild == TYPE_REPLACE_PLUS) {
            updateReplace();
        } else if (this.typeRebuild == TYPE_REBUILD_WING) {
            updateReWing();
        } else if (this.typeRebuild == TYPE_KHAM_NGOC) {
            updateKhamNgoc();
            this.cmdHoiDap.caption = T.khamNgoc;
            this.cmdHoiDap.indexMenu = (byte) 0;
        } else if (this.typeRebuild == TYPE_GHEP_NGOC) {
            updateGhepngoc();
            this.cmdHoiDap.caption = T.hopngoc;
            this.cmdHoiDap.indexMenu = (byte) 0;
        } else if (this.typeRebuild == TYPE_DUC_LO) {
            updateDucLo();
            this.cmdHoiDap.caption = T.DucLo;
            this.cmdHoiDap.indexMenu = (byte) 0;
        } else if (this.typeRebuild == TYPE_ANY) {
            updateAny();
            ScrollResult updateKey = this.scr.updateKey();
            if (updateKey.isDowning || updateKey.isFinish) {
            }
            if (updateKey.isFinish || GameCanvas.isKeyPressed(5)) {
            }
            this.scr.updatecm();
            this.cmdHoiDap.caption = T.hoprac;
            this.cmdHoiDap.indexMenu = (byte) 0;
        }
        for (int i = 0; i < vecEffRe.size(); i++) {
            MainEffect mainEffect = (MainEffect) vecEffRe.elementAt(i);
            mainEffect.update();
            if (mainEffect.isStop) {
                vecEffRe.removeElement(mainEffect);
            }
        }
    }

    private void updateReWing() {
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (dataWing != null) {
                vecEffRe.removeAllElements();
                for (int i = 0; i < dataWing.length; i++) {
                    addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[6][0], this.posMaterial[6][1], 1);
                }
            }
            isBeginEff = (byte) 2;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 2) {
            if (this.time < 10) {
                this.time++;
                if (this.time == 10) {
                    mSound.playSound(24, mSound.volumeSound);
                    return;
                }
                return;
            }
            if (GameCanvas.timeNow - this.time > 12000) {
                this.time = GameCanvas.timeNow;
                addEffectRebuild(29, this.posMaterial[6][0], this.posMaterial[6][1], 12100);
            }
            if (GameCanvas.timeNow - this.time > 3700) {
                isBeginEff = (byte) 3;
                this.time = 0L;
                return;
            }
            return;
        }
        if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            if (itemWing != null) {
                addEffectEnd_ReBuild_ss(32, this.posMaterial[6][0], this.posMaterial[6][1] - 11);
            }
            addEffectEnd_ReBuild_ss(33, this.posMaterial[6][0], this.posMaterial[6][1]);
            addEffectEnd_ReBuild_ss(34, this.posMaterial[6][0], this.posMaterial[6][1]);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 5) {
            this.time++;
            MainItem mainItem = (MainItem) Item.getItemInventory(3, idWingOk);
            if (mainItem != null) {
                itemWing = MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short) mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte) 0, (byte) 0);
            } else {
                itemWing = null;
            }
            if (vecEffRe.size() == 0 || this.time >= 100) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte) 2, false, 0);
                this.time = 0L;
                isBeginEff = (byte) 6;
                return;
            }
            return;
        }
        if (isBeginEff == 6) {
            dataWing = null;
            resetItemReplace = true;
            isBeginEff = (byte) 0;
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
                MainTabNew.Focus = (byte) 0;
                return;
            }
            this.vecListCmd.removeAllElements();
        }
    }

    private void updateReplace() {
        if (isBeginEff == 1) {
            if (itemPlus == null || itemFree == null) {
                isBeginEff = (byte) 0;
                return;
            }
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            vecEffRe.removeAllElements();
            addEffectEndRebuild(37, this.posMaterial[0][0], this.posMaterial[0][1], this.posMaterial[1][0], this.posMaterial[1][1], itemPlus.tier);
            this.timeShowReplace = itemPlus.tier * EffectSkill.EFF_BIG_SWORD_115_2;
            isBeginEff = (byte) 2;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 2) {
            if (this.time < 10) {
                this.time++;
                if (this.time == 10) {
                    mSound.playSound(24, mSound.volumeSound);
                    return;
                }
                return;
            }
            if (this.time == 10) {
                this.time = GameCanvas.timeNow;
                addEffectRebuild(29, this.posMaterial[1][0], this.posMaterial[1][1], 1200 + this.timeShowReplace);
            }
            if (GameCanvas.timeNow - this.time > ObjectData.ITEM + this.timeShowReplace) {
                vecEffRe.removeAllElements();
                isBeginEff = (byte) 3;
                this.time = 0L;
                return;
            }
            return;
        }
        if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte) 2, false, 0);
            if (itemPlus != null) {
                MainItem mainItem = (MainItem) Item.getItemInventory(itemPlus.ItemCatagory, (short) itemPlus.Id);
                if (mainItem != null) {
                    itemPlus = MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short) mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte) 0, (byte) 0);
                } else {
                    itemPlus = null;
                }
            }
            if (itemFree != null) {
                MainItem mainItem2 = (MainItem) Item.getItemInventory(itemFree.ItemCatagory, (short) itemFree.Id);
                if (mainItem2 != null) {
                    itemFree = MainItem.MainItem_Item(mainItem2.Id, mainItem2.itemNameExcludeLv, mainItem2.imageId, mainItem2.tier, mainItem2.colorNameItem, mainItem2.classcharItem, mainItem2.ItemCatagory, mainItem2.mInfo, mainItem2.type_Only_Item, false, mainItem2.IdTem, mainItem2.priceItem, (short) mainItem2.LvItem, mainItem2.canSell, mainItem2.canTrade, mainItem2.timeUse, (byte) 0, (byte) 0);
                } else {
                    itemFree = null;
                }
            }
            isBeginEff = (byte) 0;
            resetItemReplace = true;
            this.vecListCmd.removeAllElements();
            isShow = false;
            this.center = null;
            this.left = null;
        }
    }

    public void updateKhamNgoc() {
        if (itemRe != null && countSetpaintinfo > 0) {
            setPaintInfo();
        }
        if (countSetpaintinfo > 0) {
            countSetpaintinfo = (byte) (countSetpaintinfo - 1);
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                byte b = itemRe.tier;
                if (b > 14) {
                    b = 14;
                }
                for (int i = 0; i < dataRebuild[b].mValue.length; i++) {
                    if (dataRebuild[b].mValue[i] > 0) {
                        addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                    }
                }
                if (isLucky) {
                    addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte) 2;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 2) {
            if (this.time < 10) {
                this.time++;
                if (this.time == 10) {
                    mSound.playSound(24, mSound.volumeSound);
                    return;
                }
                return;
            }
            if (GameCanvas.timeNow - this.time > 12000) {
                this.time = GameCanvas.timeNow;
                addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
            }
            if (GameCanvas.timeNow - this.time > 3700) {
                if (isNextRebuild == 3 || isNextRebuild == 4) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                    return;
                }
                return;
            }
            return;
        }
        if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 5) {
            this.time++;
            MainItem mainItem = (MainItem) Item.getItemInventory(itemRe.ItemCatagory, (short) itemRe.Id);
            if (!this.isTabHopNguyenLieu) {
                if (mainItem != null) {
                    itemRe = MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short) mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte) 0, (byte) 0);
                } else {
                    itemRe = null;
                }
            }
            tilemayman = tilemaymanafter;
            if (vecEffRe.size() == 0 || this.time >= 100) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte) 2, false, 0);
                this.time = 0L;
                isBeginEff = (byte) 6;
            }
            countSetpaintinfo = (byte) 1;
            return;
        }
        if (isBeginEff == 6) {
            if (itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = (byte) 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = (byte) 0;
                return;
            }
            this.vecListCmd.removeAllElements();
            if (itemRe != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiDap);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiDap);
                }
            }
        }
    }

    public void updateDucLo() {
        if (itemRe != null && countSetpaintinfo > 0) {
            setPaintInfo();
        }
        if (countSetpaintinfo > 0) {
            countSetpaintinfo = (byte) (countSetpaintinfo - 1);
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                byte b = itemRe.tier;
                if (b > 14) {
                    b = 14;
                }
                for (int i = 0; i < dataRebuild[b].mValue.length; i++) {
                    if (dataRebuild[b].mValue[i] > 0) {
                        addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                    }
                }
                if (isLucky) {
                    addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte) 2;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 2) {
            if (this.time < 10) {
                this.time++;
                if (this.time == 10) {
                    mSound.playSound(24, mSound.volumeSound);
                    return;
                }
                return;
            }
            if (GameCanvas.timeNow - this.time > 12000) {
                this.time = GameCanvas.timeNow;
                addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
            }
            if (GameCanvas.timeNow - this.time > 3700) {
                if (isNextRebuild == 3 || isNextRebuild == 4) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                    return;
                }
                return;
            }
            return;
        }
        if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 5) {
            this.time++;
            MainItem mainItem = (MainItem) Item.getItemInventory(itemRe.ItemCatagory, (short) itemRe.Id);
            if (!this.isTabHopNguyenLieu) {
                if (mainItem != null) {
                    itemRe = MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short) mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte) 0, (byte) 0);
                } else {
                    itemRe = null;
                }
            }
            tilemayman = tilemaymanafter;
            if (vecEffRe.size() == 0 || this.time >= 100) {
                GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte) 2, false, 0);
                this.time = 0L;
                isBeginEff = (byte) 6;
            }
            countSetpaintinfo = (byte) 1;
            return;
        }
        if (isBeginEff == 6) {
            if (itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = (byte) 0;
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = (byte) 0;
                return;
            }
            this.vecListCmd.removeAllElements();
            if (itemRe != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiDap);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiDap);
                }
            }
        }
    }

    public void updateRebuild() {
        if (isDapnhanh && isBeginEff == 2) {
            isBeginEff = isNextRebuild;
            this.time = 0L;
        }
        if (isBeginEff == 1) {
            if (MainTabNew.longwidth == 0) {
                isShow = false;
            }
            GameCanvas.end_Dialog();
            if (!GameCanvas.isTouch) {
                this.left = null;
                this.center = null;
            } else {
                this.vecListCmd.removeAllElements();
            }
            if (itemRe != null) {
                vecEffRe.removeAllElements();
                for (int i = 0; i < dataRebuild[itemRe.tier].mValue.length; i++) {
                    if (dataRebuild[itemRe.tier].mValue[i] > 0) {
                        addEffectEndRebuild(31, this.posMaterial[i][0], this.posMaterial[i][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                    }
                }
                if (isLucky) {
                    addEffectEndRebuild(31, this.posMaterial[4][0], this.posMaterial[4][1], this.posMaterial[5][0], this.posMaterial[5][1], 1);
                }
            }
            isBeginEff = (byte) 2;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 2) {
            if (this.time < 10) {
                this.time++;
                if (this.time == 10) {
                    mSound.playSound(24, mSound.volumeSound);
                    return;
                }
                return;
            }
            if (GameCanvas.timeNow - this.time > 12000) {
                this.time = GameCanvas.timeNow;
                addEffectRebuild(29, this.posMaterial[5][0], this.posMaterial[5][1], 12100);
            }
            if (GameCanvas.timeNow - this.time > 3700) {
                if (isNextRebuild == 3 || isNextRebuild == 4) {
                    isBeginEff = isNextRebuild;
                    this.time = 0L;
                    return;
                }
                return;
            }
            return;
        }
        if (isBeginEff == 3) {
            mSound.playSound(26, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(32, this.posMaterial[5][0], this.posMaterial[5][1] - 11);
            addEffectEnd_ReBuild_ss(33, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectEnd_ReBuild_ss(34, this.posMaterial[5][0], this.posMaterial[5][1]);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 4) {
            mSound.playSound(27, mSound.volumeSound);
            vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(11, this.posMaterial[5][0], this.posMaterial[5][1]);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            addEffectRebuild(69, this.posMaterial[5][0], this.posMaterial[5][1] - 11, 300);
            isBeginEff = (byte) 5;
            this.time = 0L;
            return;
        }
        if (isBeginEff == 5) {
            this.time++;
            MainItem mainItem = (MainItem) Item.getItemInventory(itemRe.ItemCatagory, (short) itemRe.Id);
            if (!this.isTabHopNguyenLieu) {
                if (mainItem != null) {
                    itemRe = MainItem.MainItem_Item(mainItem.Id, mainItem.itemNameExcludeLv, mainItem.imageId, mainItem.tier, mainItem.colorNameItem, mainItem.classcharItem, mainItem.ItemCatagory, mainItem.mInfo, mainItem.type_Only_Item, false, mainItem.IdTem, mainItem.priceItem, (short) mainItem.LvItem, mainItem.canSell, mainItem.canTrade, mainItem.timeUse, (byte) 0, (byte) 0);
                } else {
                    itemRe = null;
                }
            }
            tilemayman = tilemaymanafter;
            if (vecEffRe.size() == 0 || this.time >= 100) {
                if (!isDapnhanh) {
                    GameCanvas.menu2.startAt_NPC(null, contentShow, Menu2.IdNPCLast, (byte) 2, false, 0);
                } else if (isNextRebuild == 3) {
                    GameCanvas.addInfoChar(T.thanhcong);
                } else if (isNextRebuild == 4) {
                    GameCanvas.addInfoChar(T.thatbai);
                }
                this.time = 0L;
                isBeginEff = (byte) 6;
                return;
            }
            return;
        }
        if (isBeginEff == 6) {
            isDapnhanh = false;
            if (itemRe.tier == 15) {
                itemRe = null;
            }
            if (this.isTabHopNguyenLieu && itemRe != null) {
                itemRe = null;
            }
            isBeginEff = (byte) 0;
            if (isLucky) {
                checkAddIsLucky();
            }
            if (!GameCanvas.isTouch) {
                this.left = this.cmdView;
                this.center = this.cmdHoiDap;
                MainTabNew.Focus = (byte) 0;
                return;
            }
            this.vecListCmd.removeAllElements();
            if (itemRe != null) {
                if (MainTabNew.longwidth > 0) {
                    this.vecListCmd.addElement(this.cmdHoiDap);
                } else {
                    this.vecListCmd.addElement(this.cmdView);
                    this.vecListCmd.addElement(this.cmdHoiDap);
                }
            }
        }
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (isBeginEff != 0) {
            return;
        }
        if (this.vecListCmd != null && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && (MainTabNew.Focus == 1 || MainTabNew.longwidth > 0)) {
            for (int i = 0; i < this.vecListCmd.size(); i++) {
                ((iCommand) this.vecListCmd.elementAt(i)).updatePointer();
            }
        }
        super.updatePointer();
    }

    public static void addEffectEndRebuild(int i, int i2, int i3, int i4, int i5, int i6) {
        vecEffRe.addElement(new EffectEnd(i, i2, i3, i4, i5, i6));
    }

    public static void addEffectEnd_ReBuild_ss(int i, int i2, int i3) {
        vecEffRe.addElement(new EffectEnd(i, i2, i3));
    }

    public void addEffectRebuild(int i, int i2, int i3, int i4) {
        vecEffRe.addElement(new EffectSkill(i, i2, i3, i4, (short) 0, (byte) 0));
    }

    public void checkAddIsLucky() {
        for (int i = 0; i < Item.VecInvetoryPlayer.size(); i++) {
            MainItem mainItem = (MainItem) Item.VecInvetoryPlayer.elementAt(i);
            if (mainItem.ItemCatagory == 7 && mainItem.typeMaterial == 11 && itemDaMayMan != null && itemDaMayMan.Id == mainItem.Id) {
                GlobalService.gI().Rebuild_Item((byte) 0, (short) mainItem.Id, (byte) mainItem.ItemCatagory);
                return;
            }
        }
        isLucky = false;
    }
}
