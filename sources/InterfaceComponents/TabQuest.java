package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.CatalogyMonster;
import GameObjects.MainMonster;
import GameObjects.MainQuest;
import GameScreen.MainScreen;
import GameScreen.MapScr;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabQuest.class */
public class TabQuest extends MainTabNew {
    int idSelect;
    int maxSize;
    int hmax;
    static TabQuest me;
    int xdich;
    int wMainTab;
    int xselect;
    int HQuest;
    iCommand cmdView;
    iCommand cmdRead;
    iCommand cmdBack;
    iCommand cmdCancle;
    int xMap;
    int yMap;
    public static String[] nameItemQuest = null;
    MainQuest questCurrent;
    Scroll scr = new Scroll();
    int tabSelect = 0;
    mVector vecListCmd = new mVector("TabQuest vecListCmd");
    boolean isTran = false;
    int yCambegin = 0;

    public TabQuest(String str) {
        this.typeTab = (byte) 4;
        me = this;
        this.nameTab = "";
        this.HQuest = GameCanvas.hText;
        if (GameCanvas.isTouch) {
            this.HQuest = GameCanvas.hCommand;
        }
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        init();
        this.cmdView = new iCommand(T.view, 0, this);
        this.cmdRead = new iCommand(T.read, 1, this);
        this.cmdBack = new iCommand(T.back, -1, this);
        this.cmdCancle = new iCommand(T.cancel, 2, this);
        int i = MainTabNew.ylongwidth + this.hSmall;
        this.cmdCancle.setPos(MainTabNew.xlongwidth + (MainTabNew.longwidth / 2), i - 15, null, this.cmdCancle.caption);
        if (MainTabNew.is320) {
            this.cmdCancle.setPos(this.cmdCancle.xCmd, i - 10, PaintInfoGameScreen.fraButton2, this.cmdCancle.caption);
        }
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        resetTab(true);
        this.xdich = (MainTabNew.wblack - (this.wMainTab * 2)) / 2;
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
        }
        this.name = null;
        this.mContent = null;
        this.mPlusContent = null;
        this.questCurrent = null;
        this.listContent = null;
        super.init();
    }

    @Override // InterfaceComponents.MainTabNew
    public void backTab() {
        MainTabNew.Focus = (byte) 0;
        this.idSelect = 0;
        super.backTab();
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                backTab();
                break;
            case 0:
                MapScr.gI().Show();
                break;
            case 1:
                MainQuest QuestSelect = QuestSelect();
                if (QuestSelect != null) {
                    GameCanvas.start_Quest_DialogRead(QuestSelect);
                    break;
                }
                break;
            case 2:
                if (this.questCurrent == null) {
                    return;
                }
                GameCanvas.start_Left_Dialog(new StringBuffer().append(T.hoihuyQuest).append(this.questCurrent.name).toString(), new iCommand(T.cancel, 3, this));
                break;
            case 3:
                if (this.questCurrent == null) {
                    return;
                }
                GlobalService.gI().quest((short) this.questCurrent.ID, (byte) (this.questCurrent.isMain ? 0 : 1), (byte) 2);
                mSystem.println("huy nhiem vu ");
                resetTab(true);
                GameCanvas.end_Dialog();
                break;
        }
        super.commandPointer(i, i2);
    }

    public MainQuest QuestSelect() {
        MainQuest mainQuest;
        String str;
        CatalogyMonster catalogyMonster;
        int size = MainQuest.vecQuestDoing_Main.size();
        if (this.idSelect < 0 || this.idSelect >= size + MainQuest.vecQuestDoing_Sub.size()) {
            return null;
        }
        if (this.idSelect < size) {
            mainQuest = (MainQuest) MainQuest.vecQuestDoing_Main.elementAt(this.idSelect);
            mainQuest.strShowDialog = mainQuest.strShortDetail;
            if (mainQuest.typeQuest == 0 || mainQuest.typeQuest == 1) {
                mainQuest.strShowDialog = new StringBuffer().append(mainQuest.strShowDialog).append("\n ").append(T.mucdohoanthanh).toString();
                for (int i = 0; i < mainQuest.mIdQuest.length; i++) {
                    String str2 = "";
                    if (mainQuest.typeQuest == 1) {
                        CatalogyMonster catalogyMonster2 = MainMonster.getCatalogyMonster(mainQuest.mIdQuest[i]);
                        if (catalogyMonster2 != null) {
                            str2 = catalogyMonster2.name;
                        }
                    } else if (nameItemQuest != null) {
                        str2 = nameItemQuest[mainQuest.mIdQuest[i]];
                    }
                    mainQuest.strShowDialog = new StringBuffer().append(mainQuest.strShowDialog).append("\n ").append(str2).append(str2.equals("") ? "" : ": ").append((int) mainQuest.mQuestGot[i]).append("/").append((int) mainQuest.mtotalQuest[i]).toString();
                }
            }
        } else {
            mainQuest = (MainQuest) MainQuest.vecQuestDoing_Sub.elementAt(this.idSelect - size);
            mainQuest.strShowDialog = mainQuest.strShortDetail;
            if (mainQuest.typeQuest == 0 || mainQuest.typeQuest == 1) {
                mainQuest.strShowDialog = new StringBuffer().append(mainQuest.strShowDialog).append("\n ").append(T.mucdohoanthanh).toString();
                for (int i2 = 0; i2 < mainQuest.mIdQuest.length; i2++) {
                    if (mainQuest.typeQuest == 1) {
                        str = "";
                        if (MainMonster.getCatalogyMonster(mainQuest.mIdQuest[i2]) != null && (catalogyMonster = MainMonster.getCatalogyMonster(mainQuest.mIdQuest[i2])) != null) {
                            str = catalogyMonster.name;
                        }
                    } else {
                        str = nameItemQuest[mainQuest.mIdQuest[i2]];
                    }
                    mainQuest.strShowDialog = new StringBuffer().append(mainQuest.strShowDialog).append("\n ").append(str).append(str.equals("") ? "" : ": ").append((int) mainQuest.mQuestGot[i2]).append("/").append((int) mainQuest.mtotalQuest[i2]).toString();
                }
            }
        }
        return mainQuest;
    }

    public void setPaint() {
        if (MainTabNew.longwidth == 0) {
            return;
        }
        this.vecListCmd.removeAllElements();
        this.questCurrent = QuestSelect();
        if (this.questCurrent != null) {
            this.name = this.questCurrent.name;
            this.mContent = mFont.tahoma_7_white.splitFontArray(this.questCurrent.strShowDialog, MainTabNew.longwidth - 8);
            int length = (this.questCurrent.strShowDialog.length() * GameCanvas.hText) - (MainTabNew.hMaxContent - (iCommand.hButtonCmd * 2));
            if (length < 0) {
                length = 0;
            }
            this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, length);
            if (GameCanvas.isTouch) {
                this.vecListCmd.addElement(this.cmdCancle);
            }
            this.mPlusContent = null;
            return;
        }
        this.name = null;
        this.mContent = null;
        this.mPlusContent = null;
        this.questCurrent = null;
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        int i = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3) + this.xdich;
        int i2 = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOne5;
        if (this.tabSelect == 0) {
            paintTabBig(mgraphics, i + this.wMainTab, i2 - 1, this.wMainTab);
            mFont.tahoma_7_white.drawString(mgraphics, T.mQuest[1], i + this.wMainTab + (this.wMainTab / 2), (i2 + (MainTabNew.wOneItem / 2)) - 7, 2, false);
        } else {
            paintTabBig(mgraphics, i, i2 - 1, this.wMainTab);
            mFont.tahoma_7_white.drawString(mgraphics, T.mQuest[0], i + (this.wMainTab / 2), (i2 + (MainTabNew.wOneItem / 2)) - 7, 2, false);
            i += this.wMainTab;
        }
        this.xselect = i;
        paintTabFocus(mgraphics, this.xselect, i2 - 1, this.wMainTab);
        mFont mfont = mFont.tahoma_7b_white;
        if (MainTabNew.Focus == 0) {
            mfont = mFont.tahoma_7_white;
        }
        mfont.drawString(mgraphics, T.mQuest[this.tabSelect], this.xselect + (this.wMainTab / 2), (i2 + (MainTabNew.wOneItem / 2)) - 7, 2, false);
        mgraphics.setClip(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack);
        mgraphics.translate(0, -MainScreen.cameraSub.yCam);
        int i3 = this.yBegin + 4;
        int i4 = this.xBegin + 4;
        int i5 = 0;
        if (this.maxSize == 0) {
            paintRong(mgraphics, (i4 + (MainTabNew.wblack / 2)) - 4, i3 + MainTabNew.wOne5);
            i3 += this.HQuest;
        } else if (this.tabSelect == 1) {
            for (int i6 = 0; i6 < MainQuest.vecQuestFinish.size(); i6++) {
                if (MainTabNew.Focus == 1 && this.idSelect == i6) {
                    mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, i4 - (GameCanvas.gameTick % 3), i3, 0, true);
                }
                MainQuest mainQuest = (MainQuest) MainQuest.vecQuestFinish.elementAt(i6);
                mFont.tahoma_7b_white.drawString(mgraphics, mainQuest.name, i4 + 18, i3, 0, true);
                if (MainTabNew.Focus == 1 && this.idSelect == i6) {
                    for (int i7 = 0; i7 < mainQuest.mstrHelp.length; i7++) {
                        i3 += GameCanvas.hText;
                        mFont.tahoma_7_white.drawString(mgraphics, mainQuest.mstrHelp[i7], i4 + 25, i3, 0, true);
                    }
                }
                i3 += this.HQuest;
                i5++;
            }
        } else {
            if (MainQuest.vecQuestDoing_Main.size() > 0) {
                AvMain.Font3dWhite(mgraphics, T.mainQuest, i4, i3, 0);
                i3 += this.HQuest;
                for (int i8 = 0; i8 < MainQuest.vecQuestDoing_Main.size(); i8++) {
                    if (MainTabNew.Focus == 1 && this.idSelect == i5) {
                        mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, i4 - (GameCanvas.gameTick % 3), i3, 0, false);
                    }
                    mFont.tahoma_7b_white.drawString(mgraphics, ((MainQuest) MainQuest.vecQuestDoing_Main.elementAt(i8)).name, i4 + 18, i3, 0, false);
                    i3 += this.HQuest;
                    i5++;
                }
            }
            if (MainQuest.vecQuestDoing_Sub.size() > 0) {
                AvMain.Font3dWhite(mgraphics, T.subQuest, i4, i3, 0);
                i3 += this.HQuest;
                for (int i9 = 0; i9 < MainQuest.vecQuestDoing_Sub.size(); i9++) {
                    MainQuest mainQuest2 = (MainQuest) MainQuest.vecQuestDoing_Sub.elementAt(i9);
                    if (MainTabNew.Focus == 1 && this.idSelect == i5) {
                        mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, i4 - (GameCanvas.gameTick % 3), i3, 0, false);
                    }
                    mFont.tahoma_7b_white.drawString(mgraphics, mainQuest2.name, i4 + 18, i3, 0, false);
                    i3 += this.HQuest;
                    i5++;
                }
            }
        }
        int i10 = i3 + (this.HQuest / 2);
        if (MainTabNew.Focus == 1 && this.idSelect == i5) {
            mgraphics.setColor(MainTabNew.color[3]);
            mgraphics.fillRect((this.xBegin + (MainTabNew.wblack / 2)) - 49, i10 - 1, 98, 18, false);
        }
        this.xMap = (this.xBegin + (MainTabNew.wblack / 2)) - 48;
        this.yMap = i10;
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mgraphics, this.xMap, this.yMap, 96, 16, 4);
        } else {
            for (int i11 = 0; i11 < 4; i11++) {
                mgraphics.drawRegion(MainTabNew.imgTab[8], 0, 0, 24, 16, 0, this.xMap + (24 * i11), this.yMap, 0, false);
            }
        }
        mFont.tahoma_7_white.drawString(mgraphics, T.viewMap, this.xBegin + (MainTabNew.wblack / 2), i10 + 2, 2, false);
        if (MainTabNew.longwidth > 0 && MainTabNew.Focus == 1 && this.name != null) {
            paintInfoQuest(mgraphics);
            if (this.vecListCmd != null) {
                for (int i12 = 0; i12 < this.vecListCmd.size(); i12++) {
                    iCommand icommand = (iCommand) this.vecListCmd.elementAt(i12);
                    icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
                }
            }
        }
        mgraphics.endClip();
    }

    public void paintInfoQuest(mGraphics mgraphics) {
        mFont mfont;
        TabScreenNew.timeRepaint = 10;
        GameCanvas.resetTrans(mgraphics);
        int i = MainTabNew.xlongwidth;
        int i2 = MainTabNew.ylongwidth;
        MainTabNew.paintNameItem(mgraphics, i + (MainTabNew.longwidth / 2), (i2 + (MainTabNew.wOneItem / 2)) - 5, MainTabNew.longwidth, this.name, this.colorName);
        mgraphics.setClip(i + 1, i2 + 1, MainTabNew.longwidth - 2, this.hSmall - 2);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(i, (((i2 + MainTabNew.wOneItem) + 2) - GameCanvas.hText) + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4), MainTabNew.longwidth, ((MainTabNew.hMaxContent - MainTabNew.wOneItem) - 2) - (iCommand.hButtonCmd / 2));
        int i3 = i2 + (MainTabNew.wOneItem - GameCanvas.hText) + (GameCanvas.hText / 4);
        GameCanvas.resetTrans(mgraphics);
        this.scr.setStyle(this.mContent.length + 2, GameCanvas.hText + 2, i, ((i3 + MainTabNew.wOneItem) + 2) - (GameCanvas.hText * 2), MainTabNew.longwidth, MainTabNew.hMaxContent + GameCanvas.hText, true, 1);
        this.scr.setClip(mgraphics, i, ((i3 + MainTabNew.wOneItem) + 2) - GameCanvas.hText, MainTabNew.longwidth, ((MainTabNew.hMaxContent - MainTabNew.wOneItem) - 2) - (iCommand.hButtonCmd / 2));
        if (this.mContent != null) {
            for (int i4 = 0; i4 < this.mContent.length; i4++) {
                if (this.mContent[i4] != null) {
                    if (this.mcolor != null) {
                        mfont = MainTabNew.setTextColor(this.mcolor[i4]);
                    } else {
                        mfont = mFont.tahoma_7_white;
                    }
                    mfont.drawString(mgraphics, this.mContent[i4], i + 4, i3 + 2 + ((i4 + 1) * GameCanvas.hText), 0, true);
                    if (this.mSubContent != null) {
                        MainTabNew.setTextColor(this.mSubColor[i4]).drawString(mgraphics, this.mSubContent[i4], i + mfont.getWidth(this.mContent[i4]) + 5 + 4, i3 + 2 + ((i4 + 1) * GameCanvas.hText), 0, true);
                    }
                }
            }
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
    }

    public void paintTabFocus(mGraphics mgraphics, int i, int i2, int i3) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mgraphics, i, i2, i3, 32, 2);
            return;
        }
        for (int i4 = 0; i4 <= i3 / 32; i4++) {
            if (i4 == 0) {
                mgraphics.drawImage(MainTabNew.imgTab[9], i, i2, 0, false);
            } else if (i4 == i3 / 32) {
                mgraphics.drawRegion(MainTabNew.imgTab[9], 0, 0, 32, 32, 2, (i + i3) - 32, i2, 0, false);
            } else {
                mgraphics.drawImage(MainTabNew.imgTab[2], i + (i4 * 32), i2, 0, false);
            }
        }
    }

    public void paintTabBig(mGraphics mgraphics, int i, int i2, int i3) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mgraphics, i, i2, i3, (MainTabNew.wOneItem - MainTabNew.wOne5) + 1, 3);
            return;
        }
        for (int i4 = 0; i4 <= i3 / 32; i4++) {
            if (i4 == 0) {
                mgraphics.drawRegion(MainTabNew.imgTab[11], 0, 0, 32, (MainTabNew.wOneItem - MainTabNew.wOne5) + 1, 0, i, i2, 0, false);
            } else if (i4 == i3 / 32) {
                mgraphics.drawRegion(MainTabNew.imgTab[11], 0, 0, 32, (MainTabNew.wOneItem - MainTabNew.wOne5) + 1, 2, (i + i3) - 32, i2, 0, false);
            } else {
                mgraphics.drawRegion(MainTabNew.imgTab[10], 0, 0, 32, (MainTabNew.wOneItem - MainTabNew.wOne5) + 1, 0, i + (i4 * 32), i2, 0, false);
            }
        }
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        ScrollResult updateKey = this.scr.updateKey();
        if (updateKey.isDowning || updateKey.isFinish) {
        }
        if (updateKey.isFinish || GameCanvas.isKeyPressed(5)) {
        }
        this.scr.updatecm();
        MainScreen.cameraSub.UpdateCamera();
        super.update();
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (MainTabNew.Focus == 1) {
            int i = this.idSelect;
            if (GameCanvas.keyMyHold[2]) {
                this.idSelect--;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                if (this.tabSelect == 0) {
                    MainTabNew.Focus = (byte) 0;
                    this.idSelect = 0;
                } else {
                    this.tabSelect = 0;
                    resetTab(true);
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.tabSelect == 1) {
                    this.tabSelect = 0;
                    resetTab(true);
                } else {
                    this.tabSelect = 1;
                    resetTab(true);
                }
                GameCanvas.clearKeyHold(6);
            }
            if (this.maxSize > 0) {
                this.idSelect = resetSelect(this.idSelect, this.maxSize, true);
            } else {
                this.idSelect = 0;
            }
            setPaint();
            if (this.tabSelect == 1) {
                if (!GameCanvas.isTouch) {
                    if (this.idSelect < this.maxSize) {
                        if (this.center != null) {
                            this.center = null;
                        }
                    } else if (this.center != this.cmdView) {
                        this.center = this.cmdView;
                        TabScreenNew.timeRepaint = 10;
                    }
                    if (this.left != null) {
                        this.left = null;
                    }
                }
                if (i != this.idSelect) {
                    resetTab(false);
                    if (this.idSelect == this.maxSize) {
                        MainScreen.cameraSub.moveCamera(0, MainScreen.cameraSub.yLimit);
                    } else {
                        MainScreen.cameraSub.moveCamera(0, ((this.idSelect * this.HQuest) + (((MainQuest) MainQuest.vecQuestFinish.elementAt(this.idSelect)).mstrHelp.length * GameCanvas.hText)) - (MainTabNew.hblack / 2));
                    }
                }
            } else if (this.idSelect < this.maxSize) {
                if (!GameCanvas.isTouch) {
                    if (this.center != this.cmdRead) {
                        this.center = this.cmdRead;
                        TabScreenNew.timeRepaint = 10;
                    }
                    if (i != this.idSelect) {
                        MainScreen.cameraSub.moveCamera(0, ((this.idSelect + 2) * this.HQuest) - (MainTabNew.hblack / 2));
                    }
                }
            } else if (this.center != this.cmdView && !GameCanvas.isTouch) {
                this.center = this.cmdView;
                TabScreenNew.timeRepaint = 10;
                if (i != this.idSelect) {
                    MainScreen.cameraSub.moveCamera(0, MainScreen.cameraSub.yLimit);
                }
                if (this.left != null) {
                    this.left = null;
                }
            }
        }
        super.updatekey();
    }

    public void resetTab(boolean z) {
        int i;
        TabScreenNew.timeRepaint = 10;
        if (this.tabSelect == 0) {
            this.maxSize = MainQuest.vecQuestDoing_Main.size() + MainQuest.vecQuestDoing_Sub.size();
            i = this.maxSize + (MainQuest.vecQuestDoing_Main.size() > 0 ? 1 : 0) + (MainQuest.vecQuestDoing_Sub.size() > 0 ? 1 : 0) + 2;
        } else {
            this.maxSize = MainQuest.vecQuestFinish.size();
            if (this.idSelect < this.maxSize) {
                i = this.maxSize + 2 + ((MainQuest) MainQuest.vecQuestFinish.elementAt(this.idSelect)).mstrHelp.length;
            } else {
                i = this.maxSize + 2;
            }
        }
        this.hmax = ((i * this.HQuest) - MainTabNew.hblack) + 5;
        if (this.hmax < 0) {
            this.hmax = 0;
        }
        this.wMainTab = MainTabNew.wOneItem * 3;
        if (z) {
            MainScreen.cameraSub.setAll(0, this.hmax, 0, 0);
        } else {
            MainScreen.cameraSub.yLimit = this.hmax;
        }
    }

    private void paintRong(mGraphics mgraphics, int i, int i2) {
        mFont.tahoma_7_white.drawString(mgraphics, T.khongconhiemvu, i, i2, 2, false);
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (GameCanvas.isPointerSelect) {
            int i = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3) + this.xdich;
            int i2 = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOne5;
            if (GameCanvas.isPoint(i, i2, this.wMainTab, (MainTabNew.wOneItem - MainTabNew.wOne5) + 1)) {
                if (this.tabSelect != 0) {
                    this.tabSelect = 0;
                    resetTab(true);
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPoint(i + this.wMainTab, i2, this.wMainTab, (MainTabNew.wOneItem - MainTabNew.wOne5) + 1)) {
                if (this.tabSelect != 1) {
                    this.tabSelect = 1;
                    resetTab(true);
                }
                GameCanvas.isPointerSelect = false;
            }
        }
        if (GameCanvas.isPointerSelect && GameCanvas.isPoint(this.xMap, (this.yMap - MainScreen.cameraSub.yCam) - 4, 96, 24)) {
            GameCanvas.isPointerSelect = false;
            this.cmdView.perform();
        }
        if (this.maxSize > 0) {
            if (GameCanvas.isPointSelect(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack)) {
                if (this.tabSelect == 0) {
                    int i3 = ((GameCanvas.py - ((this.yBegin + 4) + this.HQuest)) + MainScreen.cameraSub.yCam) / this.HQuest;
                    if (MainQuest.vecQuestDoing_Main.size() > 0 && MainQuest.vecQuestDoing_Sub.size() > 0) {
                        if (i3 == MainQuest.vecQuestDoing_Main.size()) {
                            i3 = -1;
                        } else if (i3 > MainQuest.vecQuestDoing_Main.size()) {
                            i3--;
                        }
                    }
                    if (i3 > -1 && i3 <= this.maxSize) {
                        if (i3 == this.idSelect) {
                            if (MainTabNew.longwidth == 0 && this.idSelect < this.maxSize) {
                                this.cmdRead.perform();
                            }
                        } else {
                            this.idSelect = i3;
                            this.idSelect = resetSelect(this.idSelect, this.maxSize, false);
                        }
                        GameCanvas.isPointerSelect = false;
                    }
                    setPaint();
                } else {
                    int i4 = ((GameCanvas.py - this.yBegin) + MainScreen.cameraSub.yCam) / this.HQuest;
                    if (i4 < this.idSelect) {
                        this.idSelect = i4;
                    } else if (this.idSelect < this.maxSize) {
                        int length = this.yBegin + (this.idSelect * this.HQuest) + (((MainQuest) MainQuest.vecQuestFinish.elementAt(this.idSelect)).mstrHelp.length * GameCanvas.hText);
                        if (length < GameCanvas.py + MainScreen.cameraSub.yCam) {
                            this.idSelect = (((GameCanvas.py + MainScreen.cameraSub.yCam) - length) / this.HQuest) + this.idSelect;
                            this.idSelect = resetSelect(this.idSelect, this.maxSize, false);
                            GameCanvas.isPointerSelect = false;
                        }
                    }
                    setPaint();
                }
            }
            if (GameCanvas.isPointerMove) {
                if (this.hmax > 0 && GameCanvas.pxLast > this.xBegin && GameCanvas.pxLast < this.xBegin + MainTabNew.wblack && GameCanvas.pyLast > this.yBegin && GameCanvas.pyLast < this.yBegin + MainTabNew.hblack) {
                    if (!this.isTran) {
                        this.isTran = true;
                        this.yCambegin = MainScreen.cameraSub.yCam;
                    } else {
                        MainScreen.cameraSub.yTo = (this.yCambegin + GameCanvas.pyLast) - GameCanvas.py;
                        if (MainScreen.cameraSub.yTo > MainScreen.cameraSub.yLimit) {
                            MainScreen.cameraSub.yTo = MainScreen.cameraSub.yLimit;
                        }
                        if (MainScreen.cameraSub.yTo < 0) {
                            MainScreen.cameraSub.yTo = 0;
                        }
                        TabScreenNew.timeRepaint = 10;
                    }
                }
            } else {
                this.isTran = false;
                this.yCambegin = 0;
            }
            if (this.vecListCmd != null && MainTabNew.Focus == 1 && this.name != null) {
                for (int i5 = 0; i5 < this.vecListCmd.size(); i5++) {
                    ((iCommand) this.vecListCmd.elementAt(i5)).updatePointer();
                }
            }
        }
        super.updatePointer();
    }
}
