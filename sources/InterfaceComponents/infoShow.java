package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/infoShow.class */
public class infoShow extends MainScreen {
    int xbegin;
    int ybegin;
    int wScreen;
    int numW;
    int numH;
    int hitem;
    int idSelect;
    int xEquip;
    int yEquip;
    iCommand cmdClose;
    iCommand cmdMenu;
    iCommand cmdChat;
    iCommand cmdAddF;
    iCommand cmdParty;
    iCommand cmdBuy_Sell;
    iCommand cmdOkThachDau;
    iCommand cmdThachDau;
    iCommand cmdBuyFastionItem;
    iCommand cmdchon;
    byte type;
    public short lv;
    public String[] info;
    Item itemSelect;
    public static mHashtable vecEquipShow = new mHashtable();
    public static byte VIEW = 0;
    public static byte THACH_DAU = 1;
    public static byte THACH_DAU_INFO = 100;
    int indexTab = 0;
    public String nameshow = "";
    public String exp = "";
    MainTabNew maintab = new MainTabNew();
    int wsize = MainTabNew.wOneItem;

    @Override // GameScreen.MainScreen
    public void Show(MainScreen mainScreen) {
        if (GameCanvas.currentScreen != this) {
            super.Show(mainScreen);
        }
        if (!GameCanvas.isTouch) {
            this.idSelect = 0;
            this.itemSelect = (Item) vecEquipShow.get(new StringBuffer().append("").append(this.idSelect + this.indexTab).toString());
        } else {
            this.idSelect = -1;
        }
        this.maintab.listContent = null;
        this.itemSelect = null;
    }

    public infoShow() {
        this.wScreen = 230;
        if (GameCanvas.isSmallScreen) {
            this.wScreen = 160;
        }
        this.hitem = 12;
        this.numW = 6;
        this.numH = 2;
        this.xbegin = GameCanvas.hw - (this.wScreen / 2);
        this.ybegin = GameCanvas.hh - (this.wScreen / 2);
        if (!GameCanvas.isTouch) {
            this.ybegin -= iCommand.hButtonCmd / 2;
        }
        this.xEquip = (this.xbegin + (this.wScreen / 2)) - ((this.numW * this.wsize) / 2);
        this.yEquip = this.ybegin + ((this.wScreen / 3) * 2);
        this.cmdClose = new iCommand(T.close, -1);
        this.cmdMenu = new iCommand(T.menu, 0);
        this.cmdChat = new iCommand(T.trochuyen, 0);
        this.cmdAddF = new iCommand(T.addFriend, 1);
        this.cmdParty = new iCommand(T.moiParty, 2);
        this.cmdBuy_Sell = new iCommand(T.buySell, 3);
        this.cmdThachDau = new iCommand(T.thachdau, 4);
        this.cmdOkThachDau = new iCommand(T.chapnhan, 1);
        this.cmdchon = new iCommand(T.select, 5, this);
        if (GameCanvas.isTouch) {
            this.cmdOkThachDau.setPos(this.xbegin + (this.wScreen / 2), this.ybegin + this.wScreen + (iCommand.hButtonCmd / 2), null, this.cmdOkThachDau.caption);
            this.cmdClose.setPos((this.xbegin + this.wScreen) - 12, this.ybegin + 10, PaintInfoGameScreen.fraCloseMenu, "");
        }
        this.cmdBuyFastionItem = new iCommand(T.buy, 0, this);
        this.center = this.cmdchon;
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case -1:
                if (this.lastScreen.lastScreen != null) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    break;
                } else {
                    this.lastScreen.Show();
                    break;
                }
            case 0:
                doMenu();
                break;
        }
        super.commandTab(i, i2);
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                return;
            case 5:
                GlobalService.gI().requestinfoMount((byte) 1);
                return;
        }
    }

    @Override // Model.AvMain
    public void commandMenu(int i, int i2) {
        switch (i) {
            case 0:
                GameCanvas.start_Chat_Dialog();
                break;
        }
        super.commandMenu(i, i2);
    }

    public void init(byte b) {
        this.type = b;
        if (b != THACH_DAU) {
            this.type = VIEW;
            if (!GameCanvas.isTouch) {
                this.right = this.cmdClose;
                this.left = this.cmdMenu;
            } else {
                this.right = this.cmdClose;
            }
        }
    }

    private void doMenu() {
        mVector mvector = new mVector("Info_Other_Player menu");
        mvector.addElement(this.cmdChat);
        mvector.addElement(this.cmdAddF);
        if (Player.party == null || Player.party.vecPartys.size() < 5) {
            mvector.addElement(this.cmdParty);
        }
        mvector.addElement(this.cmdBuy_Sell);
        if (this.type == VIEW && LoadMap.typeMap != 1) {
            mvector.addElement(this.cmdThachDau);
        }
        GameCanvas.menu2.startAt(mvector, 2, T.giaotiep, false, null);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        this.lastScreen.paint(mgraphics);
        if (GameCanvas.currentScreen != this) {
            return;
        }
        GameCanvas.resetTrans(mgraphics);
        int i = this.ybegin;
        int i2 = this.xbegin;
        paintFormList(mgraphics, i2, i, this.wScreen, this.wScreen, this.name);
        int i3 = i2 + 3;
        int i4 = i + GameCanvas.hCommand + 2;
        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.level).append((int) this.lv).append(" + ").append(this.exp).toString(), i3, i4, 0, false);
        int i5 = i4 + this.hitem;
        if (this.info != null) {
            for (int i6 = 0; i6 < this.info.length; i6++) {
                mFont.tahoma_7_white.drawString(mgraphics, this.info[i6], i3, i5 + (this.hitem * i6), 0, false);
            }
        }
        GameScreen.player.paintHorseUI(mgraphics, i3 + (this.wScreen / 2) + 65, i5 + 60);
        byte b = 0;
        while (true) {
            byte b2 = b;
            if (b2 >= TabMySeftNew.maxSize) {
                break;
            }
            int i7 = this.xEquip + ((b2 % this.numW) * this.wsize);
            int i8 = this.yEquip + ((b2 / this.numW) * this.wsize);
            Item item = (Item) vecEquipShow.get(new StringBuffer().append("").append(b2 + this.indexTab).toString());
            if (item != null && item.Id > -1) {
                item.paintItem(mgraphics, i7 + (MainTabNew.wOneItem / 2), i8 + (MainTabNew.wOneItem / 2), MainTabNew.wOneItem, 0, 0);
            }
            mgraphics.setColor(MainTabNew.color[4]);
            mgraphics.drawRect(i7, i8, this.wsize, this.wsize, false);
            b = (byte) (b2 + 1);
        }
        mgraphics.setColor(MainTabNew.color[3]);
        if (this.idSelect >= 0) {
            int i9 = this.xEquip + ((this.idSelect % this.numW) * this.wsize);
            int i10 = this.yEquip + ((this.idSelect / this.numW) * this.wsize);
            mgraphics.drawRect(i9, i10, this.wsize, this.wsize, false);
            mgraphics.setColor(MainTabNew.color[2]);
            mgraphics.drawRect(i9 + 1, i10 + 1, this.wsize - 2, this.wsize - 2, false);
        }
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            this.maintab.paintContent(mgraphics, false);
        }
        super.paint(mgraphics);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        this.lastScreen.update();
        if (this.maintab.listContent != null) {
            this.maintab.listContent.moveCamera();
        }
        if (this.itemSelect != null) {
            updateContent(this.itemSelect);
        }
        if (this.maintab != null) {
            this.maintab.update();
        }
    }

    public void updateContent(Item item) {
        if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2) {
            MainTabNew.timePaintInfo++;
            if (MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                setPaintInfo(item);
            }
        }
        if (this.maintab.mContent == null && item != null && item.ItemCatagory != 5) {
            if (item.mcontent == null) {
                if (item.timeupdateMore % 100 == 3) {
                    if (this.maintab.typeTab == 0) {
                        GlobalService.gI().Item_More_Info((byte) 0, (byte) item.Id);
                    }
                    item.timeupdateMore++;
                    return;
                }
                item.timeupdateMore++;
                return;
            }
            this.maintab.moreInfoconten = item.moreContenGem;
            this.maintab.mContent = item.mcontent;
            this.maintab.mcolor = item.mColor;
            setYCon(item);
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        int i = this.idSelect;
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
            if (this.idSelect >= this.numW) {
                this.idSelect -= this.numW;
            }
            GameCanvas.clearKeyHold(2);
        } else if (GameCanvas.keyMyHold[8]) {
            if (this.idSelect < TabMySeftNew.maxSize - this.numW) {
                this.idSelect += this.numW;
            }
            GameCanvas.clearKeyHold(8);
        }
        if (GameCanvas.keyMyHold[4]) {
            this.idSelect--;
            GameCanvas.clearKeyHold(4);
        } else if (GameCanvas.keyMyHold[6]) {
            this.idSelect++;
            GameCanvas.clearKeyHold(6);
        }
        if (i != this.idSelect) {
            if (this.idSelect >= 0) {
                this.idSelect = (byte) resetSelect(this.idSelect, TabMySeftNew.maxSize - 1, false);
                MainTabNew.timePaintInfo = 0;
                this.itemSelect = (Item) vecEquipShow.get(new StringBuffer().append("").append(this.idSelect + this.indexTab).toString());
            } else {
                this.idSelect = -1;
            }
            this.maintab.listContent = null;
        }
        super.updatekey();
    }

    public void setPaintInfo(Item item) {
        if (this.idSelect < 0) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        this.maintab.mContent = null;
        this.maintab.mSubContent = null;
        this.maintab.mPlusContent = null;
        int i = this.idSelect;
        if (item == null) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        if (item.setNameNull()) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        this.maintab.wContent = item.sizeW;
        this.maintab.xCon = ((this.xEquip + ((i % this.numW) * this.wsize)) + (MainTabNew.wOneItem / 2)) - (this.maintab.wContent / 2);
        if (this.maintab.xCon < 0) {
            this.maintab.xCon = 0;
        }
        if (this.maintab.xCon + this.maintab.wContent > GameCanvas.w) {
            this.maintab.xCon = GameCanvas.w - this.maintab.wContent;
        }
        setYCon(item);
        this.maintab.name = item.itemName;
        this.maintab.mPlusContent = item.mPlusContent;
        this.maintab.mPlusColor = item.mPlusColor;
        this.maintab.colorName = item.colorNameItem;
        this.maintab.moreInfoconten = item.moreContenGem;
    }

    public void setYCon(Item item) {
        int i = 2;
        this.maintab.mContent = item.mcontent;
        this.maintab.moreInfoconten = item.moreContenGem;
        this.maintab.mcolor = item.mColor;
        if (item.mcontent != null) {
            i = 2 + item.mcontent.length;
        }
        if (item.mPlusContent != null) {
            i += item.mPlusContent.length;
        }
        this.maintab.yCon = this.yEquip + (((this.idSelect / this.numW) + 1) * this.wsize);
        if (GameCanvas.isTouch) {
            if (this.maintab.yCon + (i * GameCanvas.hText) > GameCanvas.h) {
                this.maintab.yCon = GameCanvas.h - (i * GameCanvas.hText);
            }
        } else if (this.maintab.yCon + (i * GameCanvas.hText) > GameCanvas.h - iCommand.hButtonCmd) {
            this.maintab.yCon = (GameCanvas.h - iCommand.hButtonCmd) - (i * GameCanvas.hText);
        }
        if (this.type == THACH_DAU && GameCanvas.isTouch && this.maintab.yCon + (i * GameCanvas.hText) > this.ybegin + this.wScreen) {
            this.maintab.yCon = (this.ybegin + this.wScreen) - (i * GameCanvas.hText);
        }
        this.maintab.listContent = null;
        if ((i + 1) * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.maintab.listContent = new ListNew(this.maintab.xCon, this.maintab.yCon, this.maintab.wContent, MainTabNew.hMaxContent, 0, 0, ((i + 1) * GameCanvas.hText) - MainTabNew.hMaxContent);
        }
        if (item.can_sell_for_other_player == 1) {
            this.maintab.wContent += iCommand.wButtonCmd / 2;
            this.maintab.cmd = this.cmdBuyFastionItem;
            return;
        }
        this.maintab.cmd = null;
        this.maintab.xpos_cmd = 0;
        this.maintab.ypos_cmd = 0;
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (this.maintab.listContent != null && GameCanvas.isPoint(this.maintab.listContent.x, this.maintab.listContent.y, this.maintab.listContent.maxW, this.maintab.listContent.maxH)) {
            this.maintab.listContent.update_Pos_UP_DOWN();
        }
        if (0 == 0 && GameCanvas.isPointSelect(this.xEquip, this.yEquip, this.wsize * this.numW, this.wsize * this.numH)) {
            GameCanvas.isPointerSelect = false;
            byte b = (byte) (((GameCanvas.px - this.xEquip) / this.wsize) + (((GameCanvas.py - this.yEquip) / this.wsize) * this.numW));
            if (b >= 0 && b < TabMySeftNew.maxSize && b != this.idSelect) {
                this.idSelect = b;
                MainTabNew.timePaintInfo = 0;
                this.itemSelect = (Item) vecEquipShow.get(new StringBuffer().append("").append(this.idSelect + this.indexTab).toString());
                this.maintab.listContent = null;
            }
        }
        super.updatePointer();
    }
}
