package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainItem;
import InterfaceComponents.InputDialog;
import InterfaceComponents.ListNew;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.PopupChat;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.FrameImage;
import Model.T;
import Model.mCamera;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/Buy_Sell_Screen.class */
public class Buy_Sell_Screen extends MainScreen {
    int wDia;
    int idSelect;
    int idSelectBuy;
    int maxSize;
    int hDia;
    int xDia;
    int yDia;
    int numw;
    int numh;
    public static final byte TYPE_BUY_MOI = 0;
    public static final byte TYPE_BUY_CHAPNHAN = 1;
    public static final byte TYPE_BUY_GOIITEM = 2;
    public static final byte TYPE_BUY_LAYITEM = 3;
    public static final byte TYPE_BUY_KHOA = 4;
    public static final byte TYPE_BUY_GIAODICH = 5;
    public static int[] mRedSell;
    public static MainItem[][] mItemOther;
    MainTabNew maintab;
    public iCommand cmdGiaodich;
    public iCommand cmdLock;
    public iCommand cmdChuyentien;
    public iCommand cmdchat;
    InputDialog input;
    InputDialog inputChat;
    ListNew list;
    public String nameBuy = "";
    public int typeActionBuySell = -1;
    public int[] moneyBuySell = new int[2];
    public byte typeLock = -1;
    boolean isINVEN_BUY = false;
    int hItem = 0;
    mCamera cameraDia = new mCamera();
    public PopupChat[] mchat = new PopupChat[2];
    public String[] strchat = new String[2];
    boolean isSmall = false;
    boolean isTran = false;
    int yCamBegin = 0;

    @Override // GameScreen.MainScreen
    public void Show() {
        mSystem.outloi("goi ham show kia");
        Show(GameCanvas.currentScreen);
    }

    @Override // GameScreen.MainScreen
    public void Show(MainScreen mainScreen) {
        this.lastScreen = mainScreen;
        super.Show(mainScreen.lastScreen);
        this.maintab.listContent = null;
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case 6:
                GameCanvas.start_Left_Dialog(T.banmuongiaodich, new iCommand(T.buySell, 1, this));
                break;
            case 7:
                setItemBuySell();
                break;
            case 8:
                GlobalService.gI().Buy_Sell((byte) 6, "", (byte) 0, (short) 0, 0);
                break;
            case 9:
                doMenu();
                break;
        }
        GameCanvas.clearKeyHold();
        super.commandTab(i, i2);
    }

    @Override // Model.AvMain
    public void commandMenu(int i, int i2) {
        switch (i) {
            case 0:
                if (this.typeLock == -1) {
                    this.typeLock = (byte) 1;
                    this.left = null;
                    this.center = null;
                } else if (this.typeLock == 0) {
                    this.typeLock = (byte) 2;
                    this.left = null;
                    this.center = null;
                }
                GlobalService.gI().Buy_Sell((byte) 4, "", (byte) 0, (short) 0, 0);
                MainTabNew.timePaintInfo = 0;
                return;
            case 1:
                this.input.setinfo(T.nhapsotien, new iCommand(T.xacnhan, 0, this), true, T.buySell);
                GameCanvas.currentDialog = this.input;
                return;
            default:
                return;
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        int i3;
        switch (i) {
            case 0:
                try {
                    i3 = Integer.parseInt(this.input.tfInput.getText());
                } catch (Exception e) {
                    i3 = 0;
                }
                if (i3 >= 0) {
                    if (i3 <= GameScreen.player.coin) {
                        this.moneyBuySell[0] = i3;
                        GlobalService.gI().Buy_Sell((byte) 7, "", (byte) 0, (short) 0, i3);
                        GameCanvas.end_Dialog();
                        return;
                    }
                    GameCanvas.start_Ok_Dialog(T.giatrinhapsai);
                    return;
                }
                return;
            case 1:
                GlobalService.gI().Buy_Sell((byte) 5, "", (byte) 0, (short) 0, 0);
                GameCanvas.end_Dialog();
                return;
            case 2:
                String text = this.inputChat.tfInput.getText();
                if (text == null || text.length() == 0) {
                    return;
                }
                GlobalService.gI().Buy_Sell((byte) 9, text, (byte) 0, (short) 0, 0);
                this.strchat[0] = text;
                this.inputChat.tfInput.setText("");
                return;
            case 3:
                this.inputChat.setinfo(new StringBuffer().append(T.trochuyenvoi).append(this.nameBuy).toString(), new iCommand(T.chat, 2, this), false, T.buySell);
                GameCanvas.currentDialog = this.inputChat;
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Type inference failed for: r0v14, types: [GameObjects.MainItem[], GameObjects.MainItem[][]] */
    public void setinfoBuySell(String str) {
        GameScreen.player.resetVx_vy();
        this.cmdGiaodich = new iCommand(T.buySell, 6);
        this.cmdLock = new iCommand(T.khoa, 0);
        this.cmdChuyentien = new iCommand(T.chuyentien, 1);
        this.cmdchat = new iCommand(T.chat, 3, this);
        if (!GameCanvas.isTouch) {
            this.center = new iCommand(T.select, 7);
        }
        this.left = new iCommand(T.menu, 9);
        this.right = new iCommand(T.cancel, 8);
        this.wDia = MainTabNew.wOneItem * 8;
        mRedSell = new int[9];
        for (int i = 0; i < mRedSell.length; i++) {
            mRedSell[i] = -1;
        }
        mItemOther = new MainItem[2];
        for (int i2 = 0; i2 < mItemOther.length; i2++) {
            mItemOther[i2] = new MainItem[9];
        }
        this.moneyBuySell[0] = 0;
        this.moneyBuySell[1] = 0;
        this.input = new InputDialog();
        this.inputChat = new InputDialog();
        this.mchat[0] = null;
        this.mchat[1] = null;
        setmIdBuy(null);
        setmItemOther(null, (byte) 0, (byte) 0);
        this.nameBuy = str;
        this.idSelect = 0;
        this.idSelectBuy = 0;
        this.maxSize = Item.VecInvetoryPlayer.size();
        this.hDia = (MainTabNew.wOneItem * 9) + (GameCanvas.hCommand / 2) + 2;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.hh - (GameCanvas.hCommand / 2)) - (this.hDia / 2)) + 2;
        if (this.yDia < 0) {
            this.hDia = (MainTabNew.wOneItem * 8) + 6;
            int i3 = iCommand.hButtonCmd;
            if (GameCanvas.isTouch) {
                i3 = 20;
            }
            this.yDia = (GameCanvas.hh - (this.hDia / 2)) - (i3 / 2);
            this.isSmall = true;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.cameraDia.setAll(0, ((((this.maxSize - 1) / 3) - 6) + 1) * MainTabNew.wOneItem, 0, 0);
        this.list = new ListNew((this.xDia + (MainTabNew.wOneItem * 4)) - (MainTabNew.wOneItem / 2), ((this.yDia + GameCanvas.hCommand) + MainTabNew.wOneItem) - (MainTabNew.wOneItem / 2), MainTabNew.wOneItem * 4, MainTabNew.wOneItem * 6, 0, 0, this.cameraDia.yLimit);
        if (this.maxSize < 18) {
            this.maxSize = 18;
        }
        this.typeLock = (byte) -1;
        this.maintab = new MainTabNew();
        MainTabNew.timePaintInfo = 0;
        setPosCmd();
        if (GameCanvas.isTouch) {
            FrameImage frameImage = new FrameImage(PaintInfoGameScreen.imgOther[1], 25, 25);
            if (this.isSmall) {
                this.cmdchat.setPos(this.xDia + 15, this.yDia + (GameCanvas.hCommand / 2) + 1, frameImage, "");
            } else {
                this.cmdchat.setPos((this.xDia + this.wDia) - 25, this.yDia + 20, frameImage, "");
            }
        }
    }

    public void setPosCmd() {
        if (GameCanvas.isTouch) {
            if (this.isSmall) {
                if (this.left != null && this.center != null && this.right != null) {
                    this.center.setPos(GameCanvas.hw, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.center.caption);
                    this.left.setPos(GameCanvas.hw - 66, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.left.caption);
                    this.right.setPos(GameCanvas.hw + 66, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.right.caption);
                    return;
                }
                if (this.left != null) {
                    this.left.setPos(GameCanvas.hw - (this.wDia / 4), this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.left.caption);
                }
                if (this.center != null) {
                    this.center.setPos(GameCanvas.hw, this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.center.caption);
                }
                if (this.right != null) {
                    this.right.setPos(GameCanvas.hw + (this.wDia / 4), this.yDia + this.hDia + 10, PaintInfoGameScreen.fraButton2, this.right.caption);
                    return;
                }
                return;
            }
            if (this.left != null && this.center != null && this.right != null) {
                this.center.setPos(GameCanvas.hw, this.yDia + this.hDia, null, this.center.caption);
                this.left.setPos(GameCanvas.hw - 80, this.yDia + this.hDia, null, this.left.caption);
                this.right.setPos(GameCanvas.hw + 80, this.yDia + this.hDia, null, this.right.caption);
                return;
            }
            if (this.left != null) {
                this.left.setPos(GameCanvas.hw - 50, this.yDia + this.hDia, null, this.left.caption);
            }
            if (this.center != null) {
                this.center.setPos(GameCanvas.hw, this.yDia + this.hDia, null, this.center.caption);
            }
            if (this.right != null) {
                this.right.setPos(GameCanvas.hw + 50, this.yDia + this.hDia, null, this.right.caption);
            }
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (this.lastScreen != null) {
            this.lastScreen.paint(mgraphics);
        }
        if (GameCanvas.currentScreen != this) {
            return;
        }
        GameCanvas.resetTrans(mgraphics);
        int i = this.yDia;
        int i2 = this.xDia + MainTabNew.wOneItem;
        AvMain.paintDialogNew(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, 0);
        if (!this.isSmall) {
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mgraphics, GameCanvas.hw - 32, i + 8, 64, 14, 2);
            } else {
                for (int i3 = 0; i3 < 2; i3++) {
                    mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 32, 14, 0, (GameCanvas.hw - 32) + (i3 * 32), i + 8, 0, true);
                }
            }
            mFont.tahoma_7b_white.drawString(mgraphics, T.buySell, GameCanvas.hw, i + 9, 2, true);
            i = this.yDia + GameCanvas.hCommand;
        }
        if (GameCanvas.isTouch && this.typeLock != 2) {
            this.cmdchat.paint(mgraphics, this.cmdchat.xCmd, this.cmdchat.yCmd);
        }
        paintBuySell(mgraphics, i2, i);
        GameCanvas.resetTrans(mgraphics);
        for (int i4 = 0; i4 < this.mchat.length; i4++) {
            if (this.mchat[i4] != null) {
                this.mchat[i4].paint(mgraphics);
            }
        }
        super.paint(mgraphics);
    }

    public void paintBuySell(mGraphics mgraphics, int i, int i2) {
        mgraphics.setColor(-4478311);
        AvMain.paintRectNice(mgraphics, i + (MainTabNew.wOneItem * 3), i2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 3, MainTabNew.wOneItem * 6, (byte) 2);
        AvMain.paintRectNice(mgraphics, i, i2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 3, MainTabNew.wOneItem * 6, (byte) 1);
        mgraphics.drawRect(i, i2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 6, MainTabNew.wOneItem * 6, true);
        mgraphics.setColor(-4478311);
        int i3 = 0;
        if (GameCanvas.isSmallScreen) {
            i3 = 2;
        }
        mFont.tahoma_7b_black.drawString(mgraphics, GameScreen.player.name, i + (this.isSmall ? 5 : 0), i2 + (MainTabNew.wOneItem / 4), 0, true);
        if (this.typeActionBuySell == 0) {
            mFont.tahoma_7b_red.drawString(mgraphics, "- Ok -", i + (MainTabNew.wOneItem * 3), i2 + (MainTabNew.wOneItem / 4), 2, true);
        }
        mFont.tahoma_7b_black.drawString(mgraphics, this.nameBuy, i, ((i2 + (MainTabNew.wOneItem / 4)) + (MainTabNew.wOneItem * 7)) - i3, 0, true);
        if (this.typeActionBuySell == 1) {
            mFont.tahoma_7b_red.drawString(mgraphics, "- Ok -", i + (MainTabNew.wOneItem * 3), ((i2 + (MainTabNew.wOneItem / 4)) + (MainTabNew.wOneItem * 7)) - i3, 2, true);
        }
        if (this.moneyBuySell[0] > 0) {
            mFont.tahoma_7_black.drawString(mgraphics, new StringBuffer().append(this.moneyBuySell[0]).append(" $").toString(), i + (MainTabNew.wOneItem * 6), i2 + (MainTabNew.wOneItem / 4), 1, true);
        }
        if (this.moneyBuySell[1] > 0) {
            mFont.tahoma_7_black.drawString(mgraphics, new StringBuffer().append(this.moneyBuySell[1]).append(" $").toString(), i + (MainTabNew.wOneItem * 6), ((i2 + (MainTabNew.wOneItem / 4)) + (MainTabNew.wOneItem * 7)) - i3, 1, true);
        }
        mgraphics.setClip(i, i2 + MainTabNew.wOneItem, MainTabNew.wOneItem * 6, MainTabNew.wOneItem * 6);
        mgraphics.translate(0, -this.cameraDia.yCam);
        if (this.typeLock == -1 || this.typeLock == 0) {
            for (int i4 = 0; i4 < Item.VecInvetoryPlayer.size(); i4++) {
                MainItem mainItem = (MainItem) Item.VecInvetoryPlayer.elementAt(i4);
                if (mainItem.ItemCatagory == 7) {
                    MainItem material = Item.getMaterial(mainItem.Id);
                    if (material != null) {
                        material.paintItem(mgraphics, i + (MainTabNew.wOneItem * 3) + (MainTabNew.wOneItem / 2) + ((i4 % 3) * MainTabNew.wOneItem), i2 + MainTabNew.wOneItem + (MainTabNew.wOneItem / 2) + ((i4 / 3) * MainTabNew.wOneItem), MainTabNew.wOneItem, 0, 0);
                    } else {
                        Item.put_Material(mainItem.Id);
                    }
                } else {
                    mainItem.paintItem(mgraphics, i + (MainTabNew.wOneItem * 3) + (MainTabNew.wOneItem / 2) + ((i4 % 3) * MainTabNew.wOneItem), i2 + MainTabNew.wOneItem + (MainTabNew.wOneItem / 2) + ((i4 / 3) * MainTabNew.wOneItem), MainTabNew.wOneItem, 0, 0);
                }
                if (mainItem.canTrade == 0) {
                    mgraphics.drawRegion(AvMain.imgDelaySkill, 0, 0, MainTabNew.wOneItem - 1, MainTabNew.wOneItem - 1, 0, i + (MainTabNew.wOneItem * 3) + (MainTabNew.wOneItem / 2) + ((i4 % 3) * MainTabNew.wOneItem), i2 + MainTabNew.wOneItem + (MainTabNew.wOneItem / 2) + ((i4 / 3) * MainTabNew.wOneItem), 3, true);
                }
            }
            mgraphics.setColor(-2737107);
            for (int i5 = 0; i5 < mRedSell.length; i5++) {
                if (mRedSell[i5] >= 0) {
                    mgraphics.drawRect(i + (MainTabNew.wOneItem * 3) + ((mRedSell[i5] % 3) * MainTabNew.wOneItem) + 3, i2 + MainTabNew.wOneItem + ((mRedSell[i5] / 3) * MainTabNew.wOneItem) + 3, MainTabNew.wOneItem - 6, MainTabNew.wOneItem - 6, true);
                }
            }
        }
        mgraphics.setColor(-4478311);
        mgraphics.drawRect(i + (MainTabNew.wOneItem * 4), i2 + MainTabNew.wOneItem, MainTabNew.wOneItem, MainTabNew.wOneItem * (this.maxSize / 3), true);
        for (int i6 = 0; i6 < (this.maxSize / 3) + 1; i6++) {
            mgraphics.drawLine(i + (MainTabNew.wOneItem * 3), i2 + MainTabNew.wOneItem + (MainTabNew.wOneItem * i6), i + (MainTabNew.wOneItem * 3) + (MainTabNew.wOneItem * 3), i2 + MainTabNew.wOneItem + (MainTabNew.wOneItem * i6), true);
        }
        mgraphics.setColor(-1);
        if (!this.isINVEN_BUY && this.typeLock < 1 && this.idSelect >= 0) {
            mgraphics.drawRect(i + (MainTabNew.wOneItem * (3 + (this.idSelect % 3))) + 1, i2 + (MainTabNew.wOneItem * ((this.idSelect / 3) + 1)) + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
        }
        GameCanvas.resetTrans(mgraphics);
        for (int i7 = 0; i7 < mItemOther[0].length; i7++) {
            int i8 = i + (MainTabNew.wOneItem / 2) + ((i7 % 3) * MainTabNew.wOneItem);
            int i9 = i2 + MainTabNew.wOneItem + (MainTabNew.wOneItem / 2) + ((i7 / 3) * MainTabNew.wOneItem);
            if (mItemOther[0][i7] != null) {
                if (mItemOther[0][i7].ItemCatagory == 7) {
                    MainItem material2 = Item.getMaterial(mItemOther[0][i7].Id);
                    if (material2 != null) {
                        if (mItemOther[0][i7].content == null) {
                            mItemOther[0][i7].itemName = material2.itemName;
                            mItemOther[0][i7].mMoreContent(material2.content);
                            mItemOther[0][i7].content = material2.content;
                        }
                    } else {
                        Item.put_Material(mItemOther[0][i7].Id);
                    }
                    mItemOther[0][i7].paintItem(mgraphics, i8, i9, MainTabNew.wOneItem, 0, 0);
                } else {
                    mItemOther[0][i7].paintItem(mgraphics, i8, i9, MainTabNew.wOneItem, 0, 0);
                }
            }
            if (this.typeLock > 0) {
                mgraphics.setColor(-2737107);
                mgraphics.drawRect((i8 - (MainTabNew.wOneItem / 2)) + 1, (i9 - (MainTabNew.wOneItem / 2)) + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
            }
            int i10 = i + (MainTabNew.wOneItem / 2) + ((i7 % 3) * MainTabNew.wOneItem);
            int i11 = i2 + (MainTabNew.wOneItem * 4) + (MainTabNew.wOneItem / 2) + ((i7 / 3) * MainTabNew.wOneItem);
            if (mItemOther[1][i7] != null) {
                if (mItemOther[1][i7].ItemCatagory == 7) {
                    MainItem material3 = Item.getMaterial(mItemOther[1][i7].Id);
                    if (material3 != null) {
                        if (mItemOther[1][i7].content == null) {
                            mItemOther[1][i7].itemName = material3.itemName;
                            mItemOther[1][i7].content = material3.content;
                            mItemOther[1][i7].mMoreContent(material3.content);
                        }
                    } else {
                        Item.put_Material(mItemOther[1][i7].Id);
                    }
                    mItemOther[1][i7].paintItem(mgraphics, i10, i11, MainTabNew.wOneItem, 0, 0);
                } else {
                    mItemOther[1][i7].paintItem(mgraphics, i10, i11, MainTabNew.wOneItem, 0, 0);
                }
            }
            if (this.typeLock == 0 || this.typeLock == 2) {
                mgraphics.setColor(-2737107);
                mgraphics.drawRect((i10 - (MainTabNew.wOneItem / 2)) + 1, (i11 - (MainTabNew.wOneItem / 2)) + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
            }
        }
        mgraphics.setColor(-4478311);
        int i12 = 0;
        while (i12 < 3) {
            mgraphics.drawRect(i, i2 + MainTabNew.wOneItem + (MainTabNew.wOneItem * 2 * i12), MainTabNew.wOneItem * (3 + (i12 == 3 ? 3 : 0)), MainTabNew.wOneItem, true);
            i12++;
        }
        mgraphics.drawRect(i + MainTabNew.wOneItem, i2 + MainTabNew.wOneItem, MainTabNew.wOneItem, MainTabNew.wOneItem * 6, true);
        mgraphics.setColor(0);
        mgraphics.fillRect(i + (MainTabNew.wOneItem * 3), i2 + MainTabNew.wOneItem + 1, 1, (MainTabNew.wOneItem * 6) - 1, true);
        mgraphics.fillRect(i + 1, i2 + (MainTabNew.wOneItem * 4), MainTabNew.wOneItem * 3, 1, true);
        mgraphics.setColor(-1);
        if (this.isINVEN_BUY && this.idSelectBuy >= 0) {
            mgraphics.drawRect(i + (MainTabNew.wOneItem * (this.idSelectBuy % 3)) + 1, i2 + (MainTabNew.wOneItem * ((this.idSelectBuy / 3) + 4)) + 1, MainTabNew.wOneItem - 2, MainTabNew.wOneItem - 2, true);
        }
        mgraphics.endClip();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            mgraphics.translate(0, -this.cameraDia.yCam);
            this.maintab.paintContent(mgraphics, false);
        }
        GameCanvas.resetTrans(mgraphics);
    }

    public void doMenu() {
        mVector mvector = new mVector("Buy_sell_scr menu");
        mvector.addElement(this.cmdChuyentien);
        mvector.addElement(this.cmdLock);
        if (!GameCanvas.isTouch) {
            mvector.addElement(this.cmdchat);
        }
        GameCanvas.menu2.startAt(mvector, 2, T.select, false, null);
    }

    public void setmIdBuy(MainItem mainItem) {
        if (mainItem == null) {
            for (int i = 0; i < mItemOther[0].length; i++) {
                mItemOther[0][i] = null;
            }
            return;
        }
        for (int i2 = 0; i2 < mItemOther[0].length; i2++) {
            if (mItemOther[0][i2] != null && mItemOther[0][i2].ItemCatagory == mainItem.ItemCatagory && mItemOther[0][i2].Id == mainItem.Id) {
                GlobalService.gI().Buy_Sell((byte) 3, "", (byte) mainItem.ItemCatagory, (short) mainItem.Id, 0);
                mItemOther[0][i2] = null;
                mRedSell[i2] = -1;
                return;
            }
        }
        for (int i3 = 0; i3 < mItemOther[0].length; i3++) {
            if (mItemOther[0][i3] == null) {
                mItemOther[0][i3] = mainItem;
                GlobalService.gI().Buy_Sell((byte) 2, "", (byte) mainItem.ItemCatagory, (short) mainItem.Id, 0);
                mRedSell[i3] = this.idSelect;
                return;
            }
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        this.lastScreen.update();
        if (this.maintab.listContent != null) {
            this.maintab.listContent.moveCamera();
        }
        if (GameCanvas.isTouch) {
            this.list.moveCamera();
        } else {
            this.cameraDia.UpdateCamera();
        }
        for (int i = 0; i < this.mchat.length; i++) {
            if (this.mchat[i] != null && this.mchat[i].setOff()) {
                this.mchat[i] = null;
            }
        }
        for (int i2 = 0; i2 < this.strchat.length; i2++) {
            if (this.strchat[i2] != null) {
                ChatBuySell((byte) i2, this.strchat[i2]);
                this.strchat[i2] = null;
            }
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        MainItem mainItem;
        if (this.typeLock >= 1) {
            this.isINVEN_BUY = true;
        }
        if (this.isINVEN_BUY) {
            int i = this.idSelectBuy;
            if (this.maintab.listContent != null) {
                if (GameCanvas.keyMyHold[2]) {
                    if (this.maintab.listContent.cmtoX > 0) {
                        this.maintab.listContent.cmtoX -= GameCanvas.hText;
                    } else {
                        this.maintab.listContent.cmtoX = 0;
                    }
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    if (this.maintab.listContent.cmtoX < this.maintab.listContent.cmxLim) {
                        this.maintab.listContent.cmtoX += GameCanvas.hText;
                    } else {
                        this.maintab.listContent.cmtoX = this.maintab.listContent.cmxLim;
                    }
                    GameCanvas.clearKeyHold(8);
                }
            } else if (GameCanvas.keyMyHold[2]) {
                this.idSelectBuy -= 3;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelectBuy += 3;
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                this.idSelectBuy--;
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.idSelectBuy % 3 == 2 && this.typeLock < 1) {
                    this.isINVEN_BUY = false;
                    MainTabNew.timePaintInfo = 0;
                } else {
                    this.idSelectBuy++;
                }
                GameCanvas.clearKeyHold(6);
            }
            this.idSelectBuy = resetSelect(this.idSelectBuy, 8, false);
            if (this.idSelectBuy != i) {
                MainTabNew.timePaintInfo = 0;
                this.maintab.listContent = null;
            }
        } else {
            int i2 = this.idSelect;
            if (this.maintab.listContent != null) {
                if (GameCanvas.keyMyHold[2]) {
                    if (this.maintab.listContent.cmtoX > 0) {
                        this.maintab.listContent.cmtoX -= GameCanvas.hText;
                    } else {
                        this.maintab.listContent.cmtoX = 0;
                    }
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    if (this.maintab.listContent.cmtoX < this.maintab.listContent.cmxLim) {
                        this.maintab.listContent.cmtoX += GameCanvas.hText;
                    } else {
                        this.maintab.listContent.cmtoX = this.maintab.listContent.cmxLim;
                    }
                    GameCanvas.clearKeyHold(8);
                }
            } else if (GameCanvas.keyMyHold[2]) {
                this.idSelect -= 3;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect += 3;
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                if (this.idSelect % 3 == 0) {
                    this.isINVEN_BUY = true;
                    MainTabNew.timePaintInfo = 0;
                } else {
                    this.idSelect--;
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(6);
            }
            if (!GameCanvas.isTouch) {
                this.idSelect = resetSelect(this.idSelect, Item.VecInvetoryPlayer.size() - 1, false);
            }
            if (this.idSelect < -1) {
                this.idSelect = -1;
            } else if (this.idSelect > Item.VecInvetoryPlayer.size() - 1) {
                this.idSelect = Item.VecInvetoryPlayer.size();
            }
            if (this.idSelect != i2 && this.idSelect >= 0) {
                this.cameraDia.moveCamera(0, ((this.idSelect / 3) - 3) * MainTabNew.wOneItem);
                MainTabNew.timePaintInfo = 0;
            }
        }
        int i3 = this.idSelect;
        if (this.isINVEN_BUY) {
            i3 = this.idSelectBuy;
        }
        if (!this.isINVEN_BUY) {
            mainItem = (MainItem) Item.VecInvetoryPlayer.elementAt(i3);
        } else {
            mainItem = mItemOther[1][i3];
        }
        updateContent(mainItem);
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        int i = this.xDia + MainTabNew.wOneItem;
        int i2 = this.yDia + MainTabNew.wOneItem;
        if (!this.isSmall) {
            i2 += GameCanvas.hCommand;
        }
        boolean z = false;
        if (this.maintab.listContent != null && GameCanvas.isPoint(this.maintab.listContent.x, this.maintab.listContent.y, this.maintab.listContent.maxW, this.maintab.listContent.maxH)) {
            this.maintab.listContent.update_Pos_UP_DOWN();
            z = true;
        }
        if (!z) {
            if (GameCanvas.isPointerMove) {
                MainTabNew.timePaintInfo = 0;
            }
            if (GameCanvas.isTouch) {
                this.list.update_Pos_UP_DOWN();
                this.cameraDia.yCam = this.list.cmx;
            }
            if (this.typeLock < 1 && GameCanvas.isPointSelect(i + (3 * MainTabNew.wOneItem), i2, 3 * MainTabNew.wOneItem, 6 * MainTabNew.wOneItem)) {
                int i3 = ((GameCanvas.px - (i + (3 * MainTabNew.wOneItem))) / MainTabNew.wOneItem) + ((((this.cameraDia.yCam + GameCanvas.py) - i2) / MainTabNew.wOneItem) * 3);
                int size = Item.VecInvetoryPlayer.size();
                if (this.isINVEN_BUY) {
                    this.idSelect = -1;
                }
                this.isINVEN_BUY = false;
                if (i3 >= 0 && i3 < size) {
                    GameCanvas.isPointerSelect = false;
                    if (i3 == this.idSelect) {
                        setItemBuySell();
                    } else {
                        this.idSelect = i3;
                        MainTabNew.timePaintInfo = 0;
                        this.maintab.listContent = null;
                    }
                } else {
                    this.idSelect = -1;
                    MainTabNew.timePaintInfo = 0;
                    this.maintab.listContent = null;
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(i, i2 + (3 * MainTabNew.wOneItem), 3 * MainTabNew.wOneItem, 3 * MainTabNew.wOneItem)) {
                int i4 = ((GameCanvas.px - i) / MainTabNew.wOneItem) + (((GameCanvas.py - (i2 + (3 * MainTabNew.wOneItem))) / MainTabNew.wOneItem) * 3);
                if (!this.isINVEN_BUY) {
                    MainTabNew.timePaintInfo = 0;
                }
                this.isINVEN_BUY = true;
                if (i4 >= 0 && i4 < 9) {
                    GameCanvas.isPointerSelect = false;
                    if (i4 != this.idSelectBuy) {
                        this.idSelectBuy = i4;
                        MainTabNew.timePaintInfo = 0;
                    }
                } else {
                    this.idSelectBuy = -1;
                    MainTabNew.timePaintInfo = 0;
                }
                GameCanvas.isPointerSelect = false;
            }
        }
        if (GameCanvas.isTouch && this.typeLock != 2) {
            this.cmdchat.updatePointer();
        }
        super.updatePointer();
    }

    public static void setmItemOther(MainItem mainItem, byte b, byte b2) {
        if (mainItem == null) {
            for (int i = 0; i < mItemOther.length; i++) {
                mItemOther[1][i] = null;
            }
            return;
        }
        if (b == 3) {
            for (int i2 = 0; i2 < mItemOther[b2].length; i2++) {
                if (mItemOther[b2][i2] != null && mItemOther[b2][i2].ItemCatagory == mainItem.ItemCatagory && mItemOther[b2][i2].Id == mainItem.Id) {
                    mItemOther[b2][i2] = null;
                    if (b2 == 0) {
                        mRedSell[i2] = -1;
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (b == 2) {
            for (int i3 = 0; i3 < mItemOther[b2].length; i3++) {
                if (mItemOther[b2][i3] == null) {
                    mItemOther[b2][i3] = mainItem;
                    return;
                }
            }
        }
    }

    public void updateContent(MainItem mainItem) {
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2) {
            MainTabNew.timePaintInfo++;
            if (MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                setPaintInfo(mainItem);
            }
        }
        if (this.maintab.mContent == null && mainItem != null && mainItem.ItemCatagory != 5) {
            if (mainItem.mcontent == null) {
                if (mainItem.timeupdateMore % 100 == 3) {
                    if (this.maintab.typeTab == 0) {
                        GlobalService.gI().Item_More_Info((byte) 0, (byte) mainItem.Id);
                    }
                    mainItem.timeupdateMore++;
                    return;
                }
                mainItem.timeupdateMore++;
                return;
            }
            this.maintab.mContent = mainItem.mcontent;
            this.maintab.mcolor = mainItem.mColor;
            setYCon(mainItem);
        }
    }

    public void setPaintInfo(MainItem mainItem) {
        this.maintab.mContent = null;
        this.maintab.mSubContent = null;
        this.maintab.mPlusContent = null;
        if (mainItem == null) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (mainItem.setNameNull()) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        this.maintab.wContent = mainItem.sizeW;
        if (this.isINVEN_BUY) {
            this.maintab.xCon = this.xDia + (MainTabNew.wOneItem * 4) + 3;
            if (this.maintab.xCon + this.maintab.wContent > GameCanvas.w - 3) {
                this.maintab.xCon = (GameCanvas.w - 3) - this.maintab.wContent;
            }
        } else {
            this.maintab.xCon = ((this.xDia + (MainTabNew.wOneItem * 4)) - this.maintab.wContent) - 3;
            if (this.maintab.xCon < 3) {
                this.maintab.xCon = 3;
            }
        }
        setYCon(mainItem);
        this.maintab.name = mainItem.itemName;
        this.maintab.mPlusContent = mainItem.mPlusContent;
        this.maintab.mPlusColor = mainItem.mPlusColor;
        this.maintab.colorName = mainItem.colorNameItem;
    }

    public void setYCon(MainItem mainItem) {
        int i = 1;
        this.maintab.mContent = mainItem.mcontent;
        this.maintab.mcolor = mainItem.mColor;
        if (mainItem.mcontent != null) {
            i = 1 + this.maintab.mContent.length;
        }
        if (mainItem.mPlusContent != null) {
            i += mainItem.mPlusContent.length;
        }
        int i2 = this.idSelect;
        int i3 = 0;
        if (this.isINVEN_BUY) {
            i2 = this.idSelectBuy + 9;
            i3 = this.cameraDia.yCam;
        }
        this.maintab.yCon = ((((i2 / 3) + 1) * MainTabNew.wOneItem) - (i * GameCanvas.hText)) + this.yDia + GameCanvas.hCommand + i3;
        if (this.maintab.yCon - this.cameraDia.yCam < 3) {
            this.maintab.yCon = 3 + this.cameraDia.yCam;
        }
        this.maintab.listContent = null;
        if ((i + 1) * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.maintab.listContent = new ListNew(this.maintab.xCon, this.maintab.yCon, this.maintab.wContent, MainTabNew.hMaxContent, 0, 0, ((i + 1) * GameCanvas.hText) - MainTabNew.hMaxContent);
        }
    }

    public void setItemBuySell() {
        MainItem mainItem = (MainItem) Item.VecInvetoryPlayer.elementAt(this.idSelect);
        if (mainItem.canTrade == 0) {
            GameCanvas.start_Ok_Dialog(T.khongthetraodoi);
        } else {
            setmIdBuy(mainItem);
        }
    }

    public int tinhThue(int i) {
        int i2 = 0;
        if (i > 5000000) {
            i2 = (((i - 5000000) * 30) / 100) + 1250000;
        } else if (i > 5000000) {
            i2 = (((i - 5000000) * 30) / 100) + 1250000;
        }
        return i2;
    }

    public void ChatBuySell(byte b, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        if (this.mchat[b] == null) {
            this.mchat[b] = new PopupChat();
        }
        this.mchat[b].setChat(str, true);
        int i = this.yDia;
        if (!this.isSmall) {
            i = this.yDia + GameCanvas.hCommand;
        }
        int i2 = this.xDia + MainTabNew.wOneItem;
        if (b == 0) {
            this.mchat[b].indexpaint = 1;
            this.mchat[b].updatePos(i2 + MainTabNew.wOneItem, i + MainTabNew.wOneItem + this.mchat[b].h);
        } else {
            this.mchat[b].updatePos(i2 + MainTabNew.wOneItem, i + (MainTabNew.wOneItem * 7));
        }
    }
}
