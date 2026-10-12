package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainBuff;
import GameObjects.MainInfoItem;
import GameObjects.MainItem;
import GameObjects.PetItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.TabScreenNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.MainTemplateItem;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabMySeftNew.class */
public class TabMySeftNew extends MainTabNew {
    int numW;
    int numH;
    int h12;
    int w5;
    byte idSelect;
    int xStart;
    int yStart;
    public static MainInfoItem[] mItemInfo;
    public static MainInfoItem[] mItemInfoShow;
    int[] mColorInfo;
    int maxList;
    int selectList;
    int xList;
    int yList;
    int timeUpdateInfo;
    private byte indexTab;
    iCommand cmdChange;
    iCommand cmdChangeEquip;
    iCommand cmdCloseChange;
    iCommand cmdPetInfo;
    iCommand cmdPetFeed;
    iCommand cmdNexTab;
    iCommand cmdReturn;
    iCommand cmdxem;
    ListNew list;
    public static int maxSize = 12;
    public static int delta = 0;
    public static String[] meffskill = new String[5];
    mVector vecItemMenu = new mVector("TabMySeftNew vecItemMenu");
    boolean isList = false;
    boolean isShowInfo = false;
    mVector vecListCmd = new mVector("vec List cmd");
    int wsize = MainTabNew.wOneItem;

    public TabMySeftNew(String str) {
        this.typeTab = (byte) 1;
        if (GameCanvas.isSmallScreen) {
            delta = 10;
        }
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        this.h12 = MainTabNew.hblack / 12;
        this.w5 = MainTabNew.wblack / 5;
        this.numW = MainTabNew.wblack / MainTabNew.wOneItem;
        if (this.numW > 6) {
            this.numW = 6;
        }
        this.xStart = ((this.xBegin + (MainTabNew.wblack / 2)) - ((this.numW * MainTabNew.wOneItem) / 2)) + (GameCanvas.isSmallScreen ? 0 : this.numW / 2);
        this.yStart = (this.yBegin + (this.h12 * 10)) - MainTabNew.wOneItem;
        this.numH = maxSize / this.numW;
        this.nameTab = str;
        if (str == null || str.length() == 0) {
            this.nameTab = "Name Tab";
        }
        this.cmdCloseChange = new iCommand(T.close, -2, this);
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        this.cmdChange = new iCommand(T.change, 0, this);
        this.cmdChangeEquip = new iCommand(T.equip, 1, this);
        this.cmdPetInfo = new iCommand(T.info, 2, this);
        this.cmdPetFeed = new iCommand(T.choan, 3, this);
        this.cmdNexTab = new iCommand(T.trangbi2, 4, this);
        this.cmdReturn = new iCommand(T.trangbi1, 5, this);
        this.cmdxem = new iCommand(T.xem, 6, this);
        if (!GameCanvas.isTouch) {
            this.left = this.cmdNexTab;
        }
        this.indexTab = (byte) 0;
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        if (GameCanvas.isTouch) {
            this.idSelect = (byte) -1;
        } else {
            this.idSelect = (byte) 0;
        }
        this.isList = false;
        this.listContent = null;
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            this.center = this.cmdChange;
        }
        MainTabNew.timePaintInfo = 0;
        super.init();
    }

    @Override // InterfaceComponents.MainTabNew
    public void backTab() {
        MainTabNew.Focus = (byte) 0;
        if (GameCanvas.isTouch) {
            this.idSelect = (byte) -1;
        } else {
            this.idSelect = (byte) 0;
        }
        super.backTab();
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        if (this.idSelect == -1 && i != -1) {
            return;
        }
        switch (i) {
            case -2:
                MainTabNew.timePaintInfo = 0;
                TabScreenNew.timeRepaint = 10;
                if (!GameCanvas.isTouch) {
                    this.right = this.cmdBack;
                    this.center = this.cmdChange;
                }
                this.isList = false;
                this.vecItemMenu.removeAllElements();
                this.selectList = 0;
                break;
            case -1:
                backTab();
                break;
            case 0:
                mSystem.outz(new StringBuffer().append("Command pointer tab myself index = ").append(i).toString());
                int i3 = MainTemplateItem.mItem_Equip_Tem[this.idSelect + this.indexTab];
                Item item = (Item) Item.VecEquipPlayer.get(new StringBuffer().append("").append(this.idSelect + this.indexTab).toString());
                if (item == null) {
                    if (i3 == -2) {
                        return;
                    }
                    if (i3 == -1) {
                        if (GameScreen.player.clazz == 2) {
                            i3 = 11;
                        } else if (GameScreen.player.clazz == 3) {
                            i3 = 10;
                        } else {
                            i3 = 8 + GameScreen.player.clazz;
                        }
                    }
                } else {
                    i3 = item.type_Only_Item;
                }
                if (i3 == 14) {
                    if (item != null) {
                        GameCanvas.start_Pet_Info((PetItem) item, (byte) 1);
                        return;
                    }
                    return;
                }
                listItemMenu(i3);
                if (this.vecItemMenu != null && this.vecItemMenu.size() > 0) {
                    TabScreenNew.timeRepaint = 10;
                    this.maxList = this.vecItemMenu.size();
                    if (this.maxList > this.numW) {
                        this.maxList = this.numW;
                    }
                    int i4 = ((this.xStart + ((this.idSelect % this.numW) * this.wsize)) - ((this.maxList * this.wsize) / 2)) + (MainTabNew.wOneItem / 2);
                    int i5 = this.yStart + ((this.idSelect / this.numW) * this.wsize) + MainTabNew.wOneItem + MainTabNew.wOne5;
                    if (i4 < this.xBegin + (MainTabNew.wOne5 * 2)) {
                        i4 = this.xBegin + (MainTabNew.wOne5 * 2);
                    } else if (i4 + (this.maxList * this.wsize) + MainTabNew.wOne5 > (this.xBegin + MainTabNew.wblack) - MainTabNew.wOne5) {
                        i4 = ((this.xBegin + MainTabNew.wblack) - MainTabNew.wOne5) - ((this.maxList * this.wsize) + MainTabNew.wOne5);
                    }
                    if (i5 + MainTabNew.wOneItem + (MainTabNew.wOne5 * 2) > GameCanvas.h - (GameCanvas.hCommand / 2)) {
                        i5 = ((GameCanvas.h - (GameCanvas.hCommand / 2)) - MainTabNew.wOneItem) - (MainTabNew.wOne5 * 2);
                    }
                    this.xList = i4;
                    this.yList = i5;
                    MainScreen.cameraSub.setAllTo((this.vecItemMenu.size() - this.maxList) * this.wsize, 0, 0, 0);
                    this.list = new ListNew(this.xList, this.yList, this.maxList * this.wsize, this.wsize, 0, 0, (this.vecItemMenu.size() - this.maxList) * this.wsize);
                    this.isList = true;
                    mItemInfo = null;
                    mItemInfoShow = null;
                    if (!GameCanvas.isTouch) {
                        this.center = this.cmdChangeEquip;
                        if (!GameCanvas.isTouch) {
                            this.right = this.cmdCloseChange;
                        } else {
                            this.right = null;
                        }
                    }
                    MainTabNew.timePaintInfo = 0;
                    break;
                } else {
                    if (!GameCanvas.isTouch) {
                        this.right = this.cmdBack;
                        this.center = this.cmdChange;
                    }
                    this.isList = false;
                    GameCanvas.start_Ok_Dialog(T.khongcovatphanphuhop);
                    break;
                }
                break;
            case 1:
                Item item2 = (Item) this.vecItemMenu.elementAt(this.selectList);
                if (item2.setNameNull()) {
                    return;
                }
                GlobalService.gI().Use_Item((byte) item2.Id, this.idSelect);
                MainTabNew.timePaintInfo = 0;
                TabScreenNew.timeRepaint = 10;
                if (!GameCanvas.isTouch) {
                    this.right = this.cmdBack;
                    this.center = this.cmdChange;
                }
                this.isList = false;
                this.vecItemMenu.removeAllElements();
                this.selectList = 0;
                break;
            case 2:
                Item item3 = (Item) Item.VecEquipPlayer.get(new StringBuffer().append("").append((int) this.idSelect).toString());
                if (item3 != null && item3.ItemCatagory == 9) {
                    GameCanvas.start_Pet_Info((PetItem) item3, (byte) 1);
                    break;
                }
                break;
            case 3:
                Item item4 = (Item) Item.VecEquipPlayer.get(new StringBuffer().append("").append((int) this.idSelect).toString());
                if (item4 != null && item4.ItemCatagory == 9) {
                    GameCanvas.start_Pet_Info((PetItem) item4, (byte) 1);
                }
                mVector mvector = new mVector("TabMySeftNew vecItemMenu");
                TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, (byte) 0, T.choan, -1, TabShopNew.INVEN_FOOD_PET);
                tabShopNew.petCur = MsgDialog.pet;
                mvector.addElement(tabShopNew);
                GameCanvas.foodPet = new TabScreenNew();
                GameCanvas.foodPet.selectTab = 0;
                GameCanvas.foodPet.addMoreTab(mvector);
                GameCanvas.foodPet.Show(GameCanvas.currentScreen);
                break;
            case 4:
                this.indexTab = (byte) 12;
                if (GameCanvas.isTouch) {
                    this.vecListCmd = doMenu(null);
                    setPosCmd(this.vecListCmd);
                }
                if (!GameCanvas.isTouch || (mSystem.isj2me && GameCanvas.isTouch)) {
                    this.left = this.cmdReturn;
                    break;
                }
                break;
            case 5:
                this.indexTab = (byte) 0;
                if (GameCanvas.isTouch) {
                    this.vecListCmd = doMenu(null);
                    setPosCmd(this.vecListCmd);
                }
                if (!GameCanvas.isTouch || (mSystem.isj2me && GameCanvas.isTouch)) {
                    this.left = this.cmdNexTab;
                    break;
                }
                break;
            case 6:
                GlobalService.gI().requestinfoMount((byte) 0);
                break;
        }
        super.commandPointer(i, i2);
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        GameScreen.player.paintShowPlayer(mgraphics, this.xBegin + this.w5 + (delta / 2), this.yBegin + ((this.h12 * 5) / 2) + 15, 0);
        mgraphics.setColor(MainTabNew.color[1]);
        if (!GameCanvas.isSmallScreen) {
            mgraphics.fillRect(this.xBegin + (this.w5 / 4), (this.yBegin + (this.h12 * 6)) - (this.h12 / 2), ((this.w5 * 2) - (this.w5 / 2)) + delta, 1, false);
            mgraphics.fillRect(this.xBegin + (this.w5 * 2) + delta + (this.w5 / 4), (this.yBegin + (this.h12 * 6)) - (this.h12 / 2), (this.w5 * 2) + delta + (this.w5 / 2), 1, false);
        }
        mgraphics.fillRect(this.xBegin + (this.w5 * 2) + delta, this.yBegin + (this.h12 / 4), 1, (this.h12 * 8) - (this.h12 / 2), false);
        mFont.tahoma_7_orange.drawString(mgraphics, new StringBuffer().append("").append((int) GameScreen.player.pointPk).toString(), this.xBegin + 15, (((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + 4) - (GameCanvas.isSmallScreen ? 8 : 0), 0, false);
        mFont.tahoma_7_orange.drawString(mgraphics, new StringBuffer().append("").append(Player.PointSucKhoe).toString(), this.xBegin + 15, ((((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + GameCanvas.hText) + 4) - (GameCanvas.isSmallScreen ? 12 : 0), 0, false);
        mgraphics.drawImage(MainTabNew.img_pkIcn, this.xBegin + 4, (((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + 4) - (GameCanvas.isSmallScreen ? 8 : 0), 0, false);
        mgraphics.drawImage(MainTabNew.img_skIcn, this.xBegin + 4, ((((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + GameCanvas.hText) + 4) - (GameCanvas.isSmallScreen ? 12 : 0), 0, false);
        if (!GameCanvas.isSmallScreen) {
            for (int i = 0; i < 5; i++) {
                mgraphics.drawRegion(MainTabNew.imgTab[5], 0, i * 10, 10, 10, 0, this.xBegin + (this.w5 * 2) + delta + 10 + ((i % 3) * this.w5) + 3, ((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + 9 + ((i / 3) * this.h12), 10, false);
                String str = "";
                if (this.isList) {
                    if (mItemInfoShow != null) {
                        str = new StringBuffer().append(meffskill[i]).append("").toString();
                    }
                } else {
                    str = GameScreen.player.mKhangChar[i];
                }
                mFont.tahoma_7_white.drawString(mgraphics, str, this.xBegin + (this.w5 * 2) + delta + 14 + ((i % 3) * this.w5), ((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + 3 + ((i / 3) * this.h12), 0, false);
            }
        }
        paintArenaPoint(mgraphics);
        mgraphics.setColor(MainTabNew.color[4]);
        byte b = 0;
        while (true) {
            byte b2 = b;
            if (b2 >= maxSize) {
                break;
            }
            int i2 = this.xStart + ((b2 % this.numW) * this.wsize);
            int i3 = this.yStart + ((b2 / this.numW) * this.wsize);
            Item item = (Item) Item.VecEquipPlayer.get(new StringBuffer().append("").append(b2 + this.indexTab).toString());
            if (item != null) {
                if (item.Id > -1) {
                    item.paintItem(mgraphics, i2 + (MainTabNew.wOneItem / 2), i3 + (MainTabNew.wOneItem / 2), MainTabNew.wOneItem, 0, 0);
                } else if (this.indexTab <= 0) {
                    mgraphics.drawRegion(MainTabNew.imgTab[6], 0, b2 * 20, 20, 20, 0, i2 + (MainTabNew.wOneItem / 2), i3 + (MainTabNew.wOneItem / 2), 3, false);
                }
            } else if (this.indexTab <= 0) {
                mgraphics.drawRegion(MainTabNew.imgTab[6], 0, b2 * 20, 20, 20, 0, i2 + (MainTabNew.wOneItem / 2), i3 + (MainTabNew.wOneItem / 2), 3, false);
            }
            mgraphics.setColor(MainTabNew.color[4]);
            mgraphics.drawRect(i2, i3, this.wsize, this.wsize, false);
            b = (byte) (b2 + 1);
        }
        mgraphics.setColor(MainTabNew.color[3]);
        if (MainTabNew.Focus == 1 && this.idSelect >= 0) {
            int i4 = this.xStart + ((this.idSelect % this.numW) * this.wsize);
            int i5 = this.yStart + ((this.idSelect / this.numW) * this.wsize);
            mgraphics.drawRect(i4, i5, this.wsize, this.wsize, false);
            mgraphics.setColor(MainTabNew.color[2]);
            mgraphics.drawRect(i4 + 1, i5 + 1, this.wsize - 2, this.wsize - 2, false);
        }
        if (this.isList) {
            if (mItemInfoShow != null) {
                paintInfoPlayer(mgraphics, this.xBegin + (this.w5 * 2) + 4 + delta, this.yBegin + 4, mItemInfoShow, this.isShowInfo);
            } else {
                mFont.tahoma_7_white.drawString(mgraphics, T.danglaydulieu, this.xBegin + (this.w5 * 2) + 4 + delta, this.yBegin + 4, 0, false);
            }
        } else {
            paintInfoPlayer(mgraphics, this.xBegin + (this.w5 * 2) + 4 + delta, this.yBegin + 4, GameScreen.player.mInfoChar, true);
        }
        if (MainTabNew.Focus == 1) {
            if ((MainTabNew.timePaintInfo > MainTabNew.timeRequest || (this.isList && MainTabNew.timePaintInfo > 5)) && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) {
                paintPopupContent(mgraphics, MainTabNew.longwidth > 0 ? false : this.isList);
            }
            if (this.isList) {
                paintList(mgraphics);
            }
        }
        if (this.vecListCmd != null) {
            for (int i6 = 0; i6 < this.vecListCmd.size(); i6++) {
                iCommand icommand = (iCommand) this.vecListCmd.elementAt(i6);
                icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
            }
        }
    }

    public void paintList(mGraphics mgraphics) {
        int size = this.vecItemMenu.size();
        int i = MainTabNew.wOneItem + (MainTabNew.wOne5 * 2);
        if (i < 32) {
            i = 32;
        }
        AvMain.paintDialog(mgraphics, (this.xList - (this.maxList / 2)) - MainTabNew.wOne5, this.yList - MainTabNew.wOne5, (this.maxList * MainTabNew.wOneItem) + (MainTabNew.wOne5 * 2), i, 12);
        mgraphics.setClip(this.xList + (size == 1 ? 1 : 0), this.yList, (this.maxList * this.wsize) + 2, MainTabNew.wOneItem + (MainTabNew.wOne5 * 2));
        mgraphics.saveCanvas();
        mgraphics.ClipRec(this.xList + (size == 1 ? 1 : 0), this.yList, (this.maxList * this.wsize) + 2, MainTabNew.wOneItem + (MainTabNew.wOne5 * 2));
        mgraphics.translate(-MainScreen.cameraSub.xCam, 0);
        for (int i2 = 0; i2 < size; i2++) {
            ((Item) this.vecItemMenu.elementAt(i2)).paintItem(mgraphics, this.xList + (i2 * this.wsize) + (MainTabNew.wOneItem / 2) + (size == 1 ? 1 : 0), this.yList + (MainTabNew.wOneItem / 2), MainTabNew.wOneItem, 0, 0);
        }
        if (size > 0) {
            mgraphics.setColor(MainTabNew.color[2]);
            mgraphics.drawRect(this.xList + (this.selectList * this.wsize) + (size == 1 ? 1 : 0), this.yList + 1, this.wsize, this.wsize, false);
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
    }

    public void paintInfoPlayer(mGraphics mgraphics, int i, int i2, MainInfoItem[] mainInfoItemArr, boolean z) {
        mFont textColor;
        String stringBuffer;
        if (mainInfoItemArr != null) {
            for (MainInfoItem mainInfoItem : mainInfoItemArr) {
                if (mainInfoItem != null && ((mainInfoItem.id <= 4 || mainInfoItem.id == 40 || mainInfoItem.id == 14) && mainInfoItem.value != 0)) {
                    mFont mfont = mFont.tahoma_7_white;
                    if (!z) {
                        textColor = mFont.tahoma_7_black;
                    } else {
                        textColor = MainTabNew.setTextColor(Item.colorInfoItem[mainInfoItem.id]);
                    }
                    String stringBuffer2 = new StringBuffer().append(Item.nameInfoItem[mainInfoItem.id]).append(": ").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value)).toString();
                    textColor.drawString(mgraphics, stringBuffer2, i, i2, 0, false);
                    int i3 = 0;
                    if (GameScreen.player.vecBuff != null && !GameCanvas.isSmallScreen) {
                        for (int i4 = 0; i4 < GameScreen.player.vecBuff.size(); i4++) {
                            MainBuff mainBuff = (MainBuff) GameScreen.player.vecBuff.elementAt(i4);
                            if (mainBuff.minfo != null) {
                                for (int i5 = 0; i5 < mainBuff.minfo.length; i5++) {
                                    if (mainInfoItem.id == mainBuff.minfo[i5].id) {
                                        i3 += mainBuff.minfo[i5].value;
                                    }
                                }
                            }
                        }
                    }
                    if (i3 != 0) {
                        mFont mfont2 = mFont.tahoma_7_green;
                        if (i3 > 0) {
                            stringBuffer = new StringBuffer().append(" +").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], i3)).toString();
                        } else {
                            stringBuffer = new StringBuffer().append(" ").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], i3)).toString();
                            mfont2 = mFont.tahoma_7_red;
                        }
                        mfont2.drawString(mgraphics, new StringBuffer().append(" ").append(stringBuffer).toString(), i + mFont.tahoma_7_white.getWidth(stringBuffer2), i2, 0, false);
                    }
                    i2 += GameCanvas.hText;
                }
            }
        }
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        if (MainTabNew.Focus == 1) {
            if (this.listContent != null) {
                this.listContent.moveCamera();
            }
            if (this.isList) {
                if (GameCanvas.isTouch) {
                    this.list.moveCamera();
                } else {
                    MainScreen.cameraSub.UpdateCamera();
                }
            }
            updateContent();
        } else {
            MainTabNew.timePaintInfo = 0;
        }
        GameScreen.player.updateEye();
    }

    public void updateContent() {
        MainItem mainItem;
        if (this.idSelect == -1) {
            return;
        }
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2) {
            MainTabNew.timePaintInfo++;
            if (this.isList) {
                if (MainTabNew.timePaintInfo == 5) {
                    setPaintInfo();
                    return;
                }
                return;
            } else {
                if (MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                    setPaintInfo();
                    return;
                }
                return;
            }
        }
        if (this.isList && !this.isShowInfo) {
            if (mItemInfo == null) {
                this.timeUpdateInfo++;
                if (this.timeUpdateInfo % 100 == 3 && (mainItem = (MainItem) this.vecItemMenu.elementAt(this.selectList)) != null) {
                    GlobalService.gI().Item_More_Info(this.idSelect, (byte) mainItem.Id);
                    return;
                }
                return;
            }
            this.mColorInfo = new int[mItemInfo.length];
            for (int i = 0; i < mItemInfo.length; i++) {
                MainInfoItem mainInfoItem = mItemInfo[i];
                this.mColorInfo[i] = 0;
                int i2 = 0;
                while (true) {
                    if (i2 < GameScreen.player.mInfoChar.length) {
                        MainInfoItem mainInfoItem2 = GameScreen.player.mInfoChar[i];
                        if (mainInfoItem.id != mainInfoItem2.id) {
                            i2++;
                        } else if (mainInfoItem.value > mainInfoItem2.value) {
                            this.mColorInfo[i] = 2;
                        } else if (mainInfoItem.value < mainInfoItem2.value) {
                            this.mColorInfo[i] = 3;
                        }
                    }
                }
            }
            this.isShowInfo = true;
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (MainTabNew.Focus == 1) {
            if (this.isList) {
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
                }
                int i = this.selectList;
                if (GameCanvas.keyMyHold[4]) {
                    this.selectList--;
                    GameCanvas.clearKeyHold(4);
                } else if (GameCanvas.keyMyHold[6]) {
                    this.selectList++;
                    GameCanvas.clearKeyHold(6);
                }
                this.selectList = resetSelect(this.selectList, this.vecItemMenu.size() - 1, true);
                if (i != this.selectList) {
                    MainScreen.cameraSub.moveCamera((this.selectList * this.wsize) - ((this.maxList * this.wsize) / 2), 0);
                    MainTabNew.timePaintInfo = 0;
                }
            } else {
                byte b = this.idSelect;
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
                    if (this.idSelect >= this.numW) {
                        this.idSelect = (byte) (this.idSelect - this.numW);
                    }
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    if (this.idSelect < maxSize - this.numW) {
                        this.idSelect = (byte) (this.idSelect + this.numW);
                    }
                    GameCanvas.clearKeyHold(8);
                }
                if (GameCanvas.keyMyHold[4]) {
                    if (this.idSelect % this.numW == 0) {
                        MainTabNew.Focus = (byte) 0;
                    } else {
                        this.idSelect = (byte) (this.idSelect - 1);
                    }
                    GameCanvas.clearKeyHold(4);
                } else if (GameCanvas.keyMyHold[6]) {
                    this.idSelect = (byte) (this.idSelect + 1);
                    GameCanvas.clearKeyHold(6);
                }
                if (this.idSelect >= 0) {
                    this.idSelect = (byte) resetSelect(this.idSelect, maxSize - 1, false);
                } else {
                    this.idSelect = (byte) -1;
                    this.vecListCmd = null;
                }
                if (b != this.idSelect) {
                    MainTabNew.timePaintInfo = 0;
                    this.listContent = null;
                }
            }
        }
        super.updatekey();
    }

    @Override // InterfaceComponents.MainTabNew
    public void setPaintInfo() {
        Item item;
        int length;
        int length2;
        if (this.idSelect == -1 && !this.isList) {
            return;
        }
        if (this.isList) {
            item = (Item) this.vecItemMenu.elementAt(this.selectList);
        } else {
            item = (Item) Item.VecEquipPlayer.get(new StringBuffer().append("").append(this.idSelect + this.indexTab).toString());
        }
        if (item == null) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
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
        this.mContent = item.mcontent;
        this.moreInfoconten = item.moreContenGem;
        this.mPlusContent = item.mPlusContent;
        this.mPlusColor = item.mPlusColor;
        this.mcolor = item.mColor;
        this.name = item.itemName;
        this.colorName = item.colorNameItem;
        if (this.isList) {
            mItemInfo = null;
            this.timeUpdateInfo = 0;
            this.isShowInfo = false;
            int width = mFont.tahoma_7b_white.getWidth(item.itemName) + 8;
            if (width < 40) {
                width = 40;
            }
            this.wContent = width;
        }
        this.listContent = null;
        if (MainTabNew.longwidth > 0) {
            if (this.mContent == null) {
                length2 = 0 + 1;
            } else {
                length2 = 0 + this.mContent.length;
            }
            if (this.mPlusContent != null) {
                length2 += this.mPlusContent.length;
            }
            if (length2 * GameCanvas.hText > MainTabNew.hMaxContent) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, ((length2 * GameCanvas.hText) - MainTabNew.hMaxContent) + (4 * GameCanvas.hText));
                return;
            } else {
                if (GameCanvas.isTouch) {
                    this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, 4 * GameCanvas.hText);
                    return;
                }
                return;
            }
        }
        if (this.mContent == null) {
            length = 0 + 1;
        } else {
            length = 0 + this.mContent.length;
        }
        if (this.mPlusContent != null) {
            length += this.mPlusContent.length;
        }
        if (this.isList) {
            this.yCon = (this.yList - (GameCanvas.hText * 2)) + (MainTabNew.wOne5 / 2);
            this.mSubContent = null;
            this.mContent = null;
            this.mPlusContent = null;
            this.xCon = (this.xList + ((this.maxList * MainTabNew.wOneItem) / 2)) - (this.wContent / 2);
        } else {
            this.yCon = ((this.yStart + ((this.idSelect / this.numW) * MainTabNew.wOneItem)) - 9) - ((length + 1) * GameCanvas.hText);
            this.wContent = item.sizeW;
            if (this.idSelect % this.numW < 2) {
                this.xCon = this.xStart + (MainTabNew.wOneItem / 2) + ((this.idSelect % this.numW) * MainTabNew.wOneItem);
            } else if (this.idSelect % this.numW < 4) {
                this.xCon = ((this.xStart + (MainTabNew.wOneItem / 2)) + ((this.idSelect % this.numW) * MainTabNew.wOneItem)) - 45;
            } else {
                this.xCon = ((this.xStart + (MainTabNew.wOneItem / 2)) + ((this.idSelect % this.numW) * MainTabNew.wOneItem)) - 90;
            }
        }
        if (this.yCon + MainScreen.cameraSub.yCam < 2) {
            this.yCon = 2 - MainScreen.cameraSub.yCam;
        }
        if ((length + 1) * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, ((length + 1) * GameCanvas.hText) - MainTabNew.hMaxContent);
        }
    }

    public void listItemMenu(int i) {
        this.vecItemMenu.removeAllElements();
        for (int i2 = 0; i2 < Item.VecInvetoryPlayer.size(); i2++) {
            Item item = (Item) Item.VecInvetoryPlayer.elementAt(i2);
            if (item.ItemCatagory == 3 && item.type_Only_Item == i && (item.classcharItem == GameScreen.player.clazz || item.classcharItem > 3)) {
                this.vecItemMenu.addElement(item);
            }
        }
    }

    @Override // Model.AvMain
    public void updatePointer() {
        boolean z = false;
        if (this.isList) {
            if (this.listContent != null && GameCanvas.isPoint(this.listContent.x, this.listContent.y, this.listContent.maxW, this.listContent.maxH)) {
                this.listContent.update_Pos_UP_DOWN();
                z = true;
            }
            if (GameCanvas.isTouch && !z) {
                this.list.updatePos_LEFT_RIGHT();
                MainScreen.cameraSub.xCam = this.list.cmx;
            }
            if (GameCanvas.isPointerSelect && !z) {
                if (GameCanvas.isPoint(this.xList, this.yList, this.wsize * this.maxList, MainTabNew.wOneItem)) {
                    byte b = (byte) (((MainScreen.cameraSub.xCam + GameCanvas.px) - this.xList) / this.wsize);
                    if (b >= 0 && b < this.vecItemMenu.size()) {
                        if (b == this.selectList) {
                            this.cmdChangeEquip.perform();
                        } else {
                            this.selectList = b;
                            MainTabNew.timePaintInfo = 0;
                        }
                        this.listContent = null;
                    }
                    GameCanvas.isPointerSelect = false;
                } else if (!GameCanvas.isPoint(0, GameCanvas.h - GameCanvas.hCommand, GameCanvas.w, GameCanvas.hCommand)) {
                    this.cmdCloseChange.perform();
                    GameCanvas.isPointerSelect = false;
                }
            }
        } else {
            if (this.listContent != null && GameCanvas.isPoint(this.listContent.x, this.listContent.y, this.listContent.maxW, this.listContent.maxH)) {
                this.listContent.update_Pos_UP_DOWN();
                z = true;
            }
            if (GameCanvas.isPointSelect(this.xStart, this.yStart, this.wsize * this.numW, this.wsize * this.numH) && !z) {
                GameCanvas.isPointerSelect = false;
                byte b2 = (byte) (((GameCanvas.px - this.xStart) / this.wsize) + (((GameCanvas.py - this.yStart) / this.wsize) * this.numW));
                if (b2 >= 0 && b2 < maxSize) {
                    if (b2 == this.idSelect) {
                        this.cmdChange.perform();
                    } else {
                        this.idSelect = b2;
                        MainTabNew.timePaintInfo = 0;
                        if (this.idSelect >= 0) {
                            Item item = (Item) Item.VecEquipPlayer.get(new StringBuffer().append("").append(this.idSelect + this.indexTab).toString());
                            if (item != null) {
                                if (MainTabNew.longwidth > 0) {
                                    this.vecListCmd = doMenu(item);
                                    setPosCmd(this.vecListCmd);
                                }
                            } else if (this.indexTab > 0) {
                                this.vecListCmd = doMenu(item);
                                setPosCmd(this.vecListCmd);
                            }
                        }
                    }
                    this.listContent = null;
                    if (MainTabNew.Focus != 1) {
                        MainTabNew.Focus = (byte) 1;
                    }
                }
            }
        }
        if (this.vecListCmd != null) {
            for (int i = 0; i < this.vecListCmd.size(); i++) {
                iCommand icommand = (iCommand) this.vecListCmd.elementAt(i);
                if (icommand != null) {
                    icommand.updatePointer();
                }
            }
        }
        super.updatePointer();
    }

    public mVector doMenu(Item item) {
        mVector mvector = new mVector("TabMySeftNew menu");
        if (this.indexTab <= 0) {
            mvector.addElement(this.cmdNexTab);
        } else if (this.indexTab > 0) {
            mvector.addElement(this.cmdReturn);
        }
        if (item != null && item.type_Only_Item == 14) {
            mvector.addElement(this.cmdPetFeed);
        }
        if (this.idSelect == 9 && this.indexTab == 12) {
            mvector.addElement(this.cmdxem);
        }
        return mvector;
    }

    /* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabMySeftNew$infoItem.class */
    public class infoItem {
        short index;
        MainInfoItem[] mInfo;
        private final TabMySeftNew this$0;

        public infoItem(TabMySeftNew tabMySeftNew, MainInfoItem[] mainInfoItemArr, short s) {
            this.this$0 = tabMySeftNew;
            this.mInfo = mainInfoItemArr;
            this.index = s;
        }
    }

    public void paintArenaPoint(mGraphics mgraphics) {
        if (!GameCanvas.isSmallScreen) {
            mgraphics.drawRegion(MainTabNew.img_arenaIcn, 0, 0, 10, 9, 0, this.xBegin + (this.w5 * 2) + delta + 10 + ((5 % 3) * this.w5) + 3, ((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + 9 + ((5 / 3) * this.h12), 10, false);
            mFont.tahoma_7_orange.drawString(mgraphics, new StringBuffer().append("").append(Player.PointArena).toString(), this.xBegin + (this.w5 * 2) + delta + 14 + ((5 % 3) * this.w5), ((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + 3 + ((5 / 3) * this.h12), 0, false);
        } else {
            mFont.tahoma_7_orange.drawString(mgraphics, new StringBuffer().append("").append(Player.PointArena).toString(), this.xBegin + 15, ((((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + (2 * GameCanvas.hText)) + 3) - (GameCanvas.isSmallScreen ? 16 : 0), 0, false);
            mgraphics.drawImage(MainTabNew.img_arenaIcn, this.xBegin + 4, ((((this.yBegin + (this.h12 * 6)) - (this.h12 / 2)) + (2 * GameCanvas.hText)) + 4) - (GameCanvas.isSmallScreen ? 16 : 0), 0, false);
        }
    }
}
