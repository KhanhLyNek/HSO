package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameEffect.EffectEnd;
import GameEffect.MainEffect;
import GameObjects.Item;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.PetItem;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Model.Vector2;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabLottery.class */
public class TabLottery extends MainTabNew {
    public static final byte SECTION_CHOOSE_REWARD = 0;
    public static final byte SECTION_LOTTERY_DRAWING = 1;
    private int maxw;
    private int maxh;
    private int numHPaint;
    private int moveCount;
    private int selectIdx;
    private int curSelectPointerIdx;
    private int itemIdSelect;
    private static boolean waitForNewPlay;
    private static boolean beginDraw;
    private boolean endDraw;
    private boolean moveDone;
    public static boolean isReady;
    private Vector2[] posArray;
    private Vector2[] objPosArray;
    private Vector2[] fixPosArray;
    private Vector2 rewardPos;
    private static iCommand cmdStartDraw;
    private static iCommand cmdGetItem;
    private static iCommand cmdRepick;
    private static iCommand cmdContinue;
    private static iCommand cmdSelectGlass;
    private static mVector vecListCmd = new mVector("TabLottery vecListCmd");
    private mVector vecRewardList;
    private Vector2 moveVec1;
    private Vector2 moveVec2;
    private Vector2 moveVec3;
    private Vector2 moveVec4;
    private float velocity;
    private float acceleration;
    private float itemVel;
    private int test_posX;
    private int test_posY;
    private static byte sectionType;
    private static byte luckyNumber;
    private int selectNumber;
    private ListNew list;
    public static boolean isWin;
    private final int AT_DESTINATION_LIMIT = 20;
    private final int MAX_MOVE_COUNT = 30;
    private final int MAX_VELO = 16;
    private int indexPaint = 12;
    private int winfo = 140;
    private int numW = 6;
    private int numH = 6;
    private int maxSize = 60;
    private int hcmd = 0;
    private mVector vecEffEnd = new mVector("TabLottery vecEffEnd");
    private int[] ranIdx = {-1, -1, -1, -1};

    public static void changeSection(byte b) {
        sectionType = b;
        setCommand();
    }

    public static void setLuckyNumber(byte b) {
        luckyNumber = b;
    }

    private static void setCommand() {
        if (GameCanvas.isTouch) {
            vecListCmd.removeAllElements();
            if (sectionType == 0) {
                vecListCmd.addElement(cmdGetItem);
                return;
            }
            if (waitForNewPlay) {
                vecListCmd.addElement(cmdRepick);
                vecListCmd.addElement(cmdContinue);
            } else if (!beginDraw) {
                vecListCmd.addElement(cmdStartDraw);
            }
        }
    }

    public TabLottery(String str, mVector mvector, byte b) {
        this.vecRewardList = new mVector("TabLottery vecRewardList");
        this.nameTab = str;
        waitForNewPlay = false;
        beginDraw = false;
        this.endDraw = false;
        isReady = false;
        this.moveDone = true;
        isWin = false;
        this.moveCount = 0;
        this.velocity = 0.0f;
        this.acceleration = 1.0f;
        this.itemVel = 1.0f;
        this.selectIdx = -1;
        this.curSelectPointerIdx = -1;
        this.selectNumber = -1;
        luckyNumber = (byte) -1;
        this.vecRewardList = mvector;
        sectionType = b;
        this.typeTab = (byte) 0;
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        this.maxw = (MainTabNew.wblack - 8) / 32;
        this.maxh = (MainTabNew.hblack - 8) / 32;
        this.fixPosArray = new Vector2[5];
        this.fixPosArray[0] = new Vector2((this.xBegin + (MainTabNew.wblack / 2)) - 52, (this.yBegin + (MainTabNew.hblack / 2)) - 16);
        this.fixPosArray[1] = new Vector2((this.xBegin + (MainTabNew.wblack / 2)) - 32, this.yBegin + (MainTabNew.hblack / 2) + 45);
        this.fixPosArray[2] = new Vector2(this.xBegin + (MainTabNew.wblack / 2) + 32, this.yBegin + (MainTabNew.hblack / 2) + 45);
        this.fixPosArray[3] = new Vector2(this.xBegin + (MainTabNew.wblack / 2) + 52, (this.yBegin + (MainTabNew.hblack / 2)) - 16);
        this.fixPosArray[4] = new Vector2(this.xBegin + (MainTabNew.wblack / 2), (this.yBegin + (MainTabNew.hblack / 2)) - 52);
        this.posArray = new Vector2[5];
        this.posArray[0] = new Vector2((this.xBegin + (MainTabNew.wblack / 2)) - 52, (this.yBegin + (MainTabNew.hblack / 2)) - 16);
        this.posArray[1] = new Vector2((this.xBegin + (MainTabNew.wblack / 2)) - 32, this.yBegin + (MainTabNew.hblack / 2) + 45);
        this.posArray[2] = new Vector2(this.xBegin + (MainTabNew.wblack / 2) + 32, this.yBegin + (MainTabNew.hblack / 2) + 45);
        this.posArray[3] = new Vector2(this.xBegin + (MainTabNew.wblack / 2) + 52, (this.yBegin + (MainTabNew.hblack / 2)) - 16);
        this.posArray[4] = new Vector2(this.xBegin + (MainTabNew.wblack / 2), (this.yBegin + (MainTabNew.hblack / 2)) - 52);
        this.objPosArray = new Vector2[5];
        this.objPosArray[0] = new Vector2((this.xBegin + (MainTabNew.wblack / 2)) - 52, (this.yBegin + (MainTabNew.hblack / 2)) - 16);
        this.objPosArray[1] = new Vector2((this.xBegin + (MainTabNew.wblack / 2)) - 32, this.yBegin + (MainTabNew.hblack / 2) + 45);
        this.objPosArray[2] = new Vector2(this.xBegin + (MainTabNew.wblack / 2) + 32, this.yBegin + (MainTabNew.hblack / 2) + 45);
        this.objPosArray[3] = new Vector2(this.xBegin + (MainTabNew.wblack / 2) + 52, (this.yBegin + (MainTabNew.hblack / 2)) - 16);
        this.objPosArray[4] = new Vector2(this.xBegin + (MainTabNew.wblack / 2), (this.yBegin + (MainTabNew.hblack / 2)) - 52);
        this.rewardPos = new Vector2(this.xBegin + (MainTabNew.wblack / 2), this.yBegin + (MainTabNew.hblack / 2));
        cmdStartDraw = new iCommand(T.startdraw, 0, this);
        cmdGetItem = new iCommand(T.select, 1, this);
        cmdRepick = new iCommand(T.chonlai, 2, this);
        cmdContinue = new iCommand(T.choitiep, 4, this);
        cmdSelectGlass = new iCommand(T.select, 3, this);
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        if (MainTabNew.longwidth > 0) {
            int i = MainTabNew.ylongwidth + this.hSmall;
            int i2 = MainTabNew.xlongwidth;
            if (MainTabNew.is320) {
                cmdStartDraw.setPos(i2 + (MainTabNew.longwidth / 2), i - 10, PaintInfoGameScreen.fraButton2, cmdStartDraw.caption);
                cmdGetItem.setPos(i2 + (MainTabNew.longwidth / 2), i - 10, PaintInfoGameScreen.fraButton2, cmdGetItem.caption);
                cmdRepick.setPos((i2 + (MainTabNew.longwidth / 2)) - 30, i - 10, PaintInfoGameScreen.fraButton2, cmdRepick.caption);
                cmdContinue.setPos(i2 + (MainTabNew.longwidth / 2) + 30, i - 10, PaintInfoGameScreen.fraButton2, cmdContinue.caption);
            } else {
                cmdStartDraw.setPos(i2 + (MainTabNew.longwidth / 2), i - 15, null, cmdStartDraw.caption);
                cmdGetItem.setPos(i2 + (MainTabNew.longwidth / 2), i - 15, null, cmdGetItem.caption);
                cmdRepick.setPos((i2 + (MainTabNew.longwidth / 2)) - 42, i - 15, null, cmdRepick.caption);
                cmdContinue.setPos(i2 + (MainTabNew.longwidth / 2) + 42, i - 15, null, cmdContinue.caption);
            }
        } else if (GameCanvas.isTouch) {
            cmdStartDraw.setPos((iCommand.wButtonCmd / 2) + 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdStartDraw.caption);
            cmdGetItem.setPos((iCommand.wButtonCmd / 2) + 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdGetItem.caption);
            cmdRepick.setPos(((iCommand.wButtonCmd / 2) + 2) - 42, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdRepick.caption);
            cmdContinue.setPos((iCommand.wButtonCmd / 2) + 2 + 42, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdContinue.caption);
        }
        init();
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        this.list = new ListNew(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack, 0, 0, MainScreen.cameraSub.yLimit);
        setCommand();
        if (!GameCanvas.isTouch) {
            this.center = cmdGetItem;
            this.right = this.cmdBack;
        }
        this.vecEffEnd.removeAllElements();
        this.imgStarRebuild = mImage.createImage("/interface/rebuild.img");
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (sectionType == 0) {
            paintChooseRewardSection(mgraphics);
        } else if (sectionType == 1) {
            paintDrawingSection(mgraphics);
            mFont.tahoma_7b_white.drawString(mgraphics, T.chondemo, this.xBegin + 5, this.yBegin + 5, 0, false);
        }
    }

    private void paintChooseRewardSection(mGraphics mgraphics) {
        try {
            GameCanvas.resetTrans(mgraphics);
            mgraphics.setClip(this.xBegin - MainTabNew.wOne5, this.yBegin, MainTabNew.wblack + (MainTabNew.wOne5 * 2), (MainTabNew.hblack - (MainTabNew.wOne5 / 2)) + 1);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(this.xBegin - MainTabNew.wOne5, this.yBegin, MainTabNew.wblack + (MainTabNew.wOne5 * 2), (MainTabNew.hblack - (MainTabNew.wOne5 / 2)) + 1);
            mgraphics.translate(-MainScreen.cameraSub.xCam, -MainScreen.cameraSub.yCam);
            for (int i = 0; i < this.vecRewardList.size(); i++) {
                Item item = (Item) this.vecRewardList.elementAt(i);
                if (item != null) {
                    if (item.ItemCatagory == 7) {
                        MainItem material = Item.getMaterial(item.Id);
                        if (material != null) {
                            item.setinfo(material.itemName, material.imageId, 7, material.priceItem, material.priceType, material.content, (short) material.value, material.typeMaterial, (short) 1, material.canSell, material.canTrade);
                            item.paintItem(mgraphics, this.xBegin + (MainTabNew.wOneItem / 2) + ((i % this.numW) * MainTabNew.wOneItem), this.yBegin + (MainTabNew.wOneItem / 2) + ((i / this.numW) * MainTabNew.wOneItem), MainTabNew.wOneItem, 0, 0);
                            if (item.timeUse > 0) {
                                mgraphics.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, this.xBegin + (MainTabNew.wOneItem / 2) + ((i % this.numW) * MainTabNew.wOneItem), this.yBegin + (MainTabNew.wOneItem / 2) + ((i / this.numW) * MainTabNew.wOneItem), 3, true);
                            }
                        } else {
                            Item.put_Material(item.Id);
                        }
                    } else {
                        item.paintItem(mgraphics, this.xBegin + (MainTabNew.wOneItem / 2) + ((i % this.numW) * MainTabNew.wOneItem), this.yBegin + (MainTabNew.wOneItem / 2) + ((i / this.numW) * MainTabNew.wOneItem), MainTabNew.wOneItem, 0, 0);
                        if (item.timeUse > 0) {
                            mgraphics.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, this.xBegin + (MainTabNew.wOneItem / 2) + ((i % this.numW) * MainTabNew.wOneItem), this.yBegin + (MainTabNew.wOneItem / 2) + ((i / this.numW) * MainTabNew.wOneItem), 3, true);
                        }
                    }
                }
            }
            mgraphics.setColor(MainTabNew.color[1]);
            mgraphics.drawRect(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.wOneItem * this.numH, true);
            for (int i2 = 0; i2 < this.numW / 2; i2++) {
                mgraphics.drawRect(this.xBegin + MainTabNew.wOneItem + (i2 * MainTabNew.wOneItem * 2), this.yBegin, MainTabNew.wOneItem, MainTabNew.wOneItem * this.numH, true);
            }
            for (int i3 = 0; i3 < this.numH / 2; i3++) {
                mgraphics.drawRect(this.xBegin, this.yBegin + MainTabNew.wOneItem + (i3 * MainTabNew.wOneItem * 2), MainTabNew.wblack, MainTabNew.wOneItem, true);
            }
            if (this.itemIdSelect > -1 && MainTabNew.Focus == 1) {
                mgraphics.setColor(MainTabNew.color[2]);
                mgraphics.drawRect(this.xBegin + ((this.itemIdSelect % this.numW) * MainTabNew.wOneItem) + 1, this.yBegin + ((this.itemIdSelect / this.numW) * MainTabNew.wOneItem) + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
                mgraphics.setColor(MainTabNew.color[3]);
                mgraphics.drawRect(this.xBegin + ((this.itemIdSelect % this.numW) * MainTabNew.wOneItem), this.yBegin + ((this.itemIdSelect / this.numW) * MainTabNew.wOneItem), MainTabNew.wOneItem, MainTabNew.wOneItem, true);
            }
            mGraphics.resetTransAndroid(mgraphics);
            mgraphics.restoreCanvas();
            mgraphics.endClip();
            if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && MainTabNew.Focus == 1 && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
                paintPopupContent(mgraphics, false);
                if (vecListCmd != null) {
                    for (int i4 = 0; i4 < vecListCmd.size(); i4++) {
                        iCommand icommand = (iCommand) vecListCmd.elementAt(i4);
                        icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void paintDrawingSection(mGraphics mgraphics) {
        try {
            mgraphics.fillRect(this.xBegin + 2, this.yBegin + 2, MainTabNew.wblack - 4, MainTabNew.hblack - 4, true);
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mgraphics, this.xBegin + 4, this.yBegin + 4, MainTabNew.wblack - 8, MainTabNew.hblack - 8, 4);
            } else {
                paintRect(mgraphics);
            }
            mgraphics.drawImage(this.imgStarRebuild, (this.xBegin + (MainTabNew.wblack / 2)) - 54, (this.yBegin + (MainTabNew.hblack / 2)) - 52, 0, true);
            mgraphics.drawRegion(this.imgStarRebuild, 0, 0, 54, 105, 2, this.xBegin + (MainTabNew.wblack / 2), (this.yBegin + (MainTabNew.hblack / 2)) - 52, 0, true);
            Item item = (Item) this.vecRewardList.elementAt(this.itemIdSelect);
            if (isReady || item != null) {
            }
            for (int i = 0; i < this.objPosArray.length; i++) {
                if (this.selectNumber == -1) {
                    mgraphics.drawImage(AvMain.imgGlass, (int) this.objPosArray[i].x, (int) this.objPosArray[i].y, 3, true);
                } else if (isWin) {
                    if (luckyNumber == i) {
                        mgraphics.drawImage(AvMain.imgHotKey, (int) this.fixPosArray[i].x, (int) this.fixPosArray[i].y, 3, true);
                        item.paintItem(mgraphics, (int) this.fixPosArray[i].x, (int) this.fixPosArray[i].y, 20, 0, 0);
                    } else {
                        mgraphics.drawImage(AvMain.imgGlass, (int) this.fixPosArray[i].x, (int) this.fixPosArray[i].y, 3, true);
                    }
                } else if (luckyNumber == i) {
                    mgraphics.drawImage(AvMain.imgHotKey, (int) this.fixPosArray[i].x, (int) this.fixPosArray[i].y, 3, true);
                    item.paintItem(mgraphics, (int) this.fixPosArray[i].x, (int) this.fixPosArray[i].y, 20, 0, 0);
                } else if (this.selectNumber != i) {
                    mgraphics.drawImage(AvMain.imgGlass, (int) this.fixPosArray[i].x, (int) this.fixPosArray[i].y, 3, true);
                }
                if (!GameCanvas.isTouch && this.endDraw && this.curSelectPointerIdx > -1) {
                    if (MainObject.Wfc == 0) {
                        MainObject.Wfc = mImage.getImageWidth(MainObject.newfocus.image);
                        MainObject.Hfc = mImage.getImageHeight(MainObject.newfocus.image) / 10;
                    }
                    mgraphics.drawRegion(MainObject.newfocus, 0, 0, MainObject.Wfc, MainObject.Hfc, 0, (int) this.fixPosArray[this.curSelectPointerIdx].x, (((int) this.fixPosArray[this.curSelectPointerIdx].y) - (GameCanvas.gameTick % 5)) - 10, 3, true);
                }
            }
            for (int i2 = 0; i2 < this.vecEffEnd.size(); i2++) {
                ((MainEffect) this.vecEffEnd.elementAt(i2)).paint(mgraphics);
            }
            if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && ((MainTabNew.Focus == 1 || MainTabNew.longwidth > 0) && vecListCmd != null)) {
                for (int i3 = 0; i3 < vecListCmd.size(); i3++) {
                    iCommand icommand = (iCommand) vecListCmd.elementAt(i3);
                    icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void paintRect(mGraphics mgraphics) {
        for (int i = 0; i <= this.maxw; i++) {
            for (int i2 = 0; i2 <= this.maxh; i2++) {
                this.indexPaint = 12;
                if (i2 == 0) {
                    this.indexPaint = 12;
                }
                if (i == this.maxw) {
                    if (i2 == this.maxh) {
                        mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], ((this.xBegin + 4) + (MainTabNew.wblack - 8)) - 32, (((this.yBegin + 4) + MainTabNew.hblack) - 8) - 32, 0, true);
                    } else {
                        mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], ((this.xBegin + 4) + (MainTabNew.wblack - 8)) - 32, this.yBegin + 4 + (i2 * 32), 0, true);
                    }
                } else if (i2 == this.maxh) {
                    mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (i * 32), (((this.yBegin + 4) + MainTabNew.hblack) - 8) - 32, 0, true);
                } else {
                    mgraphics.drawImage(MainTabNew.imgTab[this.indexPaint], this.xBegin + 4 + (i * 32), this.yBegin + 4 + (i2 * 32), 0, true);
                }
            }
        }
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        int i;
        try {
            if (sectionType == 0) {
                if (MainTabNew.Focus == 1) {
                    if (this.listContent != null) {
                        this.listContent.moveCamera();
                    }
                    if (GameCanvas.isTouch) {
                        this.list.moveCamera();
                    } else {
                        MainScreen.cameraSub.UpdateCamera();
                    }
                    mVector mvector = this.vecRewardList;
                    if (mvector.size() == 0) {
                        MainTabNew.Focus = (byte) 0;
                        return;
                    }
                    updateContent(mvector, this.itemIdSelect);
                } else {
                    MainTabNew.timePaintInfo = 0;
                }
            } else if (sectionType == 1) {
                for (int i2 = 0; i2 < this.vecEffEnd.size(); i2++) {
                    MainEffect mainEffect = (MainEffect) this.vecEffEnd.elementAt(i2);
                    mainEffect.update();
                    if (mainEffect.isStop) {
                        this.vecEffEnd.removeElement(mainEffect);
                    }
                }
                if (this.selectIdx > -1) {
                    this.vecEffEnd.addElement(new EffectEnd(34, (int) this.fixPosArray[this.selectIdx].x, (int) this.fixPosArray[this.selectIdx].y));
                    GlobalService.gI().request_LotteryItems((byte) 2, (byte) this.selectIdx);
                    this.selectNumber = this.selectIdx;
                    this.selectIdx = -1;
                    this.endDraw = false;
                    waitForNewPlay = true;
                    setCommand();
                }
                if (!isReady) {
                    Vector2 substract = Vector2.substract(this.rewardPos, this.posArray[luckyNumber]);
                    substract.normalize();
                    this.itemVel += 0.5f;
                    this.rewardPos.add(substract.x * this.itemVel, substract.y * this.itemVel);
                    if (atDestination(this.rewardPos, this.posArray[luckyNumber])) {
                        luckyNumber = (byte) -1;
                        isReady = true;
                    }
                }
                if (this.moveCount >= 30) {
                    beginDraw = false;
                }
                if (beginDraw) {
                    GameCanvas.isPointerSelect = false;
                    this.moveDone = true;
                    this.endDraw = true;
                    if (this.moveDone) {
                        int i3 = 0;
                        if (this.moveCount > 16) {
                            i3 = CRes.random(0, 2);
                        }
                        if (i3 == 0) {
                            i = 2;
                        } else {
                            i = 4;
                        }
                        int i4 = 0;
                        while (i4 < i) {
                            boolean z = false;
                            int random = CRes.random(0, 5);
                            for (int i5 = 0; i5 < this.ranIdx.length; i5++) {
                                if (random == this.ranIdx[i5]) {
                                    z = true;
                                }
                            }
                            if (!z) {
                                this.ranIdx[i4] = random;
                                i4++;
                            }
                        }
                        this.moveVec1 = Vector2.substract(this.objPosArray[this.ranIdx[0]], this.objPosArray[this.ranIdx[1]]);
                        this.moveVec2 = Vector2.substract(this.objPosArray[this.ranIdx[1]], this.objPosArray[this.ranIdx[0]]);
                        this.moveVec1.normalize();
                        this.moveVec2.normalize();
                        if (this.ranIdx[2] > -1 && this.ranIdx[3] > -1) {
                            this.moveVec3 = Vector2.substract(this.objPosArray[this.ranIdx[2]], this.objPosArray[this.ranIdx[3]]);
                            this.moveVec4 = Vector2.substract(this.objPosArray[this.ranIdx[3]], this.objPosArray[this.ranIdx[2]]);
                            this.moveVec3.normalize();
                            this.moveVec4.normalize();
                        }
                        this.moveDone = false;
                        this.velocity = this.moveCount < 16 ? this.moveCount : 16.0f;
                    } else {
                        if (this.velocity < 16.0f) {
                            this.velocity += this.acceleration;
                        }
                        this.objPosArray[this.ranIdx[0]].add(this.moveVec1.x * this.velocity, this.moveVec1.y * this.velocity);
                        this.objPosArray[this.ranIdx[1]].add(this.moveVec2.x * this.velocity, this.moveVec2.y * this.velocity);
                        if (this.ranIdx[2] > -1 && this.ranIdx[3] > -1) {
                            this.objPosArray[this.ranIdx[2]].add(this.moveVec3.x * this.velocity, this.moveVec3.y * this.velocity);
                            this.objPosArray[this.ranIdx[3]].add(this.moveVec4.x * this.velocity, this.moveVec4.y * this.velocity);
                        }
                        if (atDestination(this.objPosArray[this.ranIdx[0]], this.posArray[this.ranIdx[1]])) {
                            this.objPosArray[this.ranIdx[0]].set(this.posArray[this.ranIdx[1]]);
                            this.objPosArray[this.ranIdx[1]].set(this.posArray[this.ranIdx[0]]);
                            this.posArray[this.ranIdx[0]].set(this.objPosArray[this.ranIdx[0]]);
                            this.posArray[this.ranIdx[1]].set(this.objPosArray[this.ranIdx[1]]);
                            if (this.ranIdx[2] > -1 && this.ranIdx[3] > -1) {
                                this.objPosArray[this.ranIdx[2]].set(this.posArray[this.ranIdx[3]]);
                                this.objPosArray[this.ranIdx[3]].set(this.posArray[this.ranIdx[2]]);
                                this.posArray[this.ranIdx[2]].set(this.objPosArray[this.ranIdx[2]]);
                                this.posArray[this.ranIdx[3]].set(this.objPosArray[this.ranIdx[3]]);
                            }
                            if (this.moveCount == 29) {
                                this.endDraw = true;
                                this.curSelectPointerIdx = 0;
                                if (!GameCanvas.isTouch) {
                                    this.center = cmdSelectGlass;
                                }
                            }
                            this.moveDone = true;
                            this.moveCount++;
                            for (int i6 = 0; i6 < this.ranIdx.length; i6++) {
                                this.ranIdx[i6] = -1;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateContent(mVector mvector, int i) {
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2) {
            MainTabNew.timePaintInfo++;
            if (MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                setPaintInfo();
            }
        }
        if (this.mContent == null && i >= 0 && i < mvector.size()) {
            Item item = (Item) mvector.elementAt(i);
            if (item.ItemCatagory != 5) {
                if (item.mcontent == null) {
                    if (item.timeupdateMore % 100 == 3) {
                        if (this.typeTab == 0) {
                            GlobalService.gI().Item_More_Info((byte) 0, (byte) item.Id);
                        }
                        item.timeupdateMore++;
                        return;
                    }
                    item.timeupdateMore++;
                    return;
                }
                this.mContent = item.mcontent;
                this.mcolor = item.mColor;
                setYCon(item);
            }
        }
    }

    public void setYCon(Item item) {
        int i = 0;
        if (MainScreen.cameraSub.yCam > 0) {
            i = MainScreen.cameraSub.yCam / MainTabNew.wOneItem;
        }
        int i2 = 1;
        this.mContent = item.mcontent;
        this.mcolor = item.mColor;
        if (item.mcontent != null) {
            i2 = 1 + this.mContent.length;
        }
        if (item.mPlusContent != null) {
            i2 += item.mPlusContent.length;
        }
        if (this.itemIdSelect / this.numW < (this.numHPaint / 2) + i) {
            this.yCon = this.yBegin + (((this.itemIdSelect / this.numW) + 1) * MainTabNew.wOneItem) + 2;
            if ((this.yCon - MainScreen.cameraSub.yCam) + ((i2 + 1) * GameCanvas.hText) > GameCanvas.h - (GameCanvas.hCommand - 5)) {
                this.yCon = (GameCanvas.h - (GameCanvas.hCommand - 5)) - (((i2 + 1) * GameCanvas.hText) - MainScreen.cameraSub.yCam);
            }
        } else {
            this.yCon = (((this.yBegin + ((this.itemIdSelect / this.numW) * MainTabNew.wOneItem)) - 7) - (i2 * GameCanvas.hText)) - MainScreen.cameraSub.yCam;
            if (this.yCon - MainScreen.cameraSub.yCam < 6) {
                this.yCon = 6 + MainScreen.cameraSub.yCam;
            }
        }
        if ((i2 + 1) * GameCanvas.hText > MainTabNew.hMaxContent - this.hcmd) {
            this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, ((i2 + 1) * GameCanvas.hText) - (MainTabNew.hMaxContent - this.hcmd));
        }
    }

    @Override // InterfaceComponents.MainTabNew
    public void setPaintInfo() {
        mVector mvector = this.vecRewardList;
        this.mContent = null;
        this.mSubContent = null;
        this.mPlusContent = null;
        if (this.itemIdSelect >= mvector.size() || this.itemIdSelect < 0) {
            if (this.itemIdSelect > mvector.size() - 1) {
                this.itemIdSelect = mvector.size() - 1;
            }
            MainTabNew.timePaintInfo = 0;
            return;
        }
        Item item = (Item) mvector.elementAt(this.itemIdSelect);
        if (item.setNameNull()) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (item.ItemCatagory == 9) {
            MsgDialog.pet = (PetItem) item;
            this.isPet = true;
        } else {
            this.isPet = false;
        }
        this.name = item.itemName;
        this.colorName = item.colorNameItem;
        if (item.ItemCatagory == 3 || this.typeTab == 8) {
            this.mPlusContent = item.mPlusContent;
            this.mPlusColor = item.mPlusColor;
        }
        this.listContent = null;
        if (MainTabNew.longwidth > 0) {
            int i = 1;
            this.mContent = item.mcontent;
            this.mcolor = item.mColor;
            if (item.mcontent != null) {
                i = 1 + this.mContent.length;
            }
            if (item.mPlusContent != null) {
                i += item.mPlusContent.length;
            }
            if (i * GameCanvas.hText > MainTabNew.hMaxContent) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, (i * GameCanvas.hText) - MainTabNew.hMaxContent);
                return;
            } else {
                if (GameCanvas.isTouch) {
                    this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, 0);
                    return;
                }
                return;
            }
        }
        this.wContent = item.sizeW;
        setYCon(item);
        if (this.itemIdSelect % this.numW < 2) {
            this.xCon = this.xBegin + (MainTabNew.wOneItem / 2) + ((this.itemIdSelect % this.numW) * MainTabNew.wOneItem);
        } else if (this.itemIdSelect % this.numW < 5) {
            this.xCon = ((this.xBegin + (MainTabNew.wOneItem / 2)) + ((this.itemIdSelect % this.numW) * MainTabNew.wOneItem)) - (this.wContent / 2);
        } else {
            this.xCon = ((this.xBegin + (MainTabNew.wOneItem / 2)) + ((this.itemIdSelect % this.numW) * MainTabNew.wOneItem)) - this.wContent;
        }
    }

    @Override // InterfaceComponents.MainTabNew
    public void backTab() {
        MainTabNew.Focus = (byte) 0;
        super.backTab();
    }

    private boolean atDestination(Vector2 vector2, Vector2 vector22) {
        if (Vector2.distance(vector2, vector22) < 20.0f) {
            return true;
        }
        return false;
    }

    @Override // Model.AvMain
    public void updatekey() {
        mVector mvector = this.vecRewardList;
        if (MainTabNew.Focus == 1) {
            int i = this.itemIdSelect;
            boolean z = false;
            if (sectionType == 1) {
                if (this.endDraw) {
                    if (GameCanvas.keyMyHold[4]) {
                        this.curSelectPointerIdx--;
                        if (this.curSelectPointerIdx < 0) {
                            this.curSelectPointerIdx = 4;
                        }
                        GameCanvas.clearKeyHold(4);
                    } else if (GameCanvas.keyMyHold[6]) {
                        this.curSelectPointerIdx++;
                        if (this.curSelectPointerIdx > 4) {
                            this.curSelectPointerIdx = 0;
                        }
                        GameCanvas.clearKeyHold(6);
                    }
                }
            } else {
                if (this.listContent != null) {
                    if (GameCanvas.keyMyHold[2]) {
                        if (this.listContent.cmtoX > 0) {
                            this.listContent.cmtoX -= GameCanvas.hText;
                        } else {
                            this.listContent.cmtoX = 0;
                        }
                        GameCanvas.clearKeyHold(2);
                    } else if (GameCanvas.keyMyHold[8]) {
                        if (this.listContent.cmtoX < this.listContent.cmxLim) {
                            this.listContent.cmtoX += GameCanvas.hText;
                        } else {
                            this.listContent.cmtoX = this.listContent.cmxLim;
                        }
                        GameCanvas.clearKeyHold(8);
                    }
                } else if (GameCanvas.keyMyHold[2]) {
                    this.itemIdSelect -= this.numW;
                    GameCanvas.clearKeyHold(2);
                    z = true;
                } else if (GameCanvas.keyMyHold[8]) {
                    this.itemIdSelect += this.numW;
                    GameCanvas.clearKeyHold(8);
                    z = true;
                }
                if (GameCanvas.keyMyHold[4]) {
                    if (this.itemIdSelect % this.numW == 0) {
                        MainTabNew.Focus = (byte) 0;
                    } else {
                        this.itemIdSelect--;
                    }
                    GameCanvas.clearKeyHold(4);
                    z = true;
                } else if (GameCanvas.keyMyHold[6]) {
                    this.itemIdSelect++;
                    GameCanvas.clearKeyHold(6);
                    z = true;
                }
                if (z) {
                    this.listContent = null;
                    this.itemIdSelect = resetSelect(this.itemIdSelect, mvector.size() - 1, false);
                    if (GameCanvas.isTouch || this.typeTab == 0 || this.typeTab == 9) {
                    }
                    TabScreenNew.timeRepaint = 10;
                }
                if (i != this.itemIdSelect) {
                    MainScreen.cameraSub.moveCamera(0, ((this.itemIdSelect / this.numW) - 1) * MainTabNew.wOneItem);
                    MainTabNew.timePaintInfo = 0;
                }
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (sectionType == 1) {
            int imageWidth = mImage.getImageWidth(AvMain.imgGlass.image);
            int imageHeight = mImage.getImageHeight(AvMain.imgGlass.image);
            if (this.endDraw) {
                int i = 0;
                while (true) {
                    if (i >= this.posArray.length) {
                        break;
                    }
                    if (!GameCanvas.isPointSelect(((int) this.fixPosArray[i].x) - (imageWidth / 2), ((int) this.fixPosArray[i].y) - (imageHeight / 2), imageWidth, imageHeight)) {
                        i++;
                    } else {
                        this.selectIdx = i;
                        GameCanvas.isPointerSelect = false;
                        break;
                    }
                }
            }
        } else if (sectionType == 0) {
            boolean z = false;
            if (this.listContent != null && GameCanvas.isPoint(this.listContent.x, this.listContent.y, this.listContent.maxW, this.listContent.maxH)) {
                this.listContent.update_Pos_UP_DOWN();
                z = true;
            }
            if (GameCanvas.isTouch && !z) {
                this.list.update_Pos_UP_DOWN();
                MainScreen.cameraSub.yCam = this.list.cmx;
            }
            if (GameCanvas.isPointSelect(this.xBegin, this.yBegin, this.numW * MainTabNew.wOneItem, MainTabNew.hblack - (MainTabNew.wOne5 / 2)) && !z) {
                int i2 = ((GameCanvas.px - this.xBegin) / MainTabNew.wOneItem) + ((((GameCanvas.py - this.yBegin) + MainScreen.cameraSub.yCam) / MainTabNew.wOneItem) * this.numW);
                if (i2 >= 0 && i2 < this.vecRewardList.size()) {
                    GameCanvas.isPointerSelect = false;
                    MainTabNew.timePaintInfo = 0;
                    this.itemIdSelect = i2;
                    if (MainTabNew.Focus != 1) {
                        MainTabNew.Focus = (byte) 1;
                    }
                } else {
                    MainTabNew.timePaintInfo = 0;
                    this.itemIdSelect = -1;
                }
            }
        }
        if (vecListCmd != null && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && (MainTabNew.Focus == 1 || MainTabNew.longwidth > 0)) {
            for (int i3 = 0; i3 < vecListCmd.size(); i3++) {
                ((iCommand) vecListCmd.elementAt(i3)).updatePointer();
            }
        }
        super.updatePointer();
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        super.commandTab(i, i2);
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                if (waitForNewPlay) {
                    GameCanvas.game.Show();
                    break;
                } else {
                    backTab();
                    break;
                }
            case 0:
                if (beginDraw || this.endDraw) {
                    return;
                }
                beginDraw = true;
                this.moveCount = 0;
                this.velocity = 0.0f;
                if (!GameCanvas.isTouch) {
                    this.center = null;
                    this.right = null;
                    this.left = null;
                }
                setCommand();
                break;
                break;
            case 1:
                if (!waitForNewPlay) {
                    GlobalService.gI().request_LotteryItems((byte) 1, (byte) this.itemIdSelect);
                    if (!GameCanvas.isTouch) {
                        this.center = cmdStartDraw;
                        this.right = null;
                        this.left = null;
                        break;
                    }
                }
                break;
            case 2:
                GlobalService.gI().request_LotteryItems((byte) 0, (byte) 0);
                break;
            case 3:
                this.selectIdx = this.curSelectPointerIdx;
                if (!GameCanvas.isTouch) {
                    this.center = cmdContinue;
                    this.left = cmdRepick;
                    this.cmdBack.caption = T.close;
                    this.right = this.cmdBack;
                    break;
                }
                break;
            case 4:
                GlobalService.gI().request_LotteryItems((byte) 1, (byte) this.itemIdSelect);
                if (!GameCanvas.isTouch) {
                    this.center = cmdStartDraw;
                    this.right = null;
                    this.left = null;
                }
                waitForNewPlay = false;
                beginDraw = false;
                this.endDraw = false;
                this.moveDone = true;
                isWin = false;
                this.moveCount = 0;
                this.selectIdx = -1;
                this.curSelectPointerIdx = -1;
                this.selectNumber = -1;
                luckyNumber = (byte) -1;
                this.itemVel = 1.0f;
                this.rewardPos = new Vector2(this.xBegin + (MainTabNew.wblack / 2), this.yBegin + (MainTabNew.hblack / 2));
                break;
        }
        super.commandPointer(i, i2);
    }
}
