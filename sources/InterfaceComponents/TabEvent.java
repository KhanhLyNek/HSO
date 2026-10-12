package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.Monstervantieu;
import GameObjects.Player;
import GameScreen.MainScreen;
import GameScreen.TabScreenNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabEvent.class */
public class TabEvent extends MainTabNew {
    public static int[][] XYpaint;
    int idSelect;
    int hcmd;
    iCommand cmdNhanthuong;
    ListNew list;
    mVector vecListCmd;
    public static mVector vecItem = new mVector();
    public int MaxItem;
    int[] stylePaint;
    int w8;
    int sizeKill = 22;
    boolean isSaveSkill = false;
    int hKill = 4;

    public TabEvent(String str) {
        this.MaxItem = 21;
        this.stylePaint = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1};
        this.MaxItem = Item.VecInvetoryPlayer.size();
        this.typeTab = (byte) -1;
        this.nameTab = str;
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3) + ((MainTabNew.wblack % 8) / 2);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        XYpaint = mSystem.new_M_Int(this.MaxItem, 2);
        int i = this.yBegin + (MainTabNew.wOneItem / 2) + GameCanvas.hText;
        for (int i2 = 0; i2 < this.MaxItem; i2++) {
            if (i2 == 0) {
                XYpaint[i2][0] = this.xBegin + (MainTabNew.wblack / 2);
                XYpaint[i2][1] = i;
                i += this.sizeKill * 2;
            } else {
                XYpaint[i2][1] = i;
                if (i2 % 2 == 1) {
                    XYpaint[i2][0] = this.xBegin + (MainTabNew.wblack / 4);
                } else {
                    XYpaint[i2][0] = this.xBegin + ((MainTabNew.wblack / 4) * 3);
                    i += this.sizeKill * 2;
                }
            }
        }
        this.cmdBack = new iCommand(T.close, -1, this);
        this.cmdNhanthuong = new iCommand(T.nhan, 6, this);
        this.w8 = MainTabNew.wblack / 8;
        vecItem = Item.VecInvetoryPlayer;
        init();
        if (!GameCanvas.isTouch) {
            this.center = this.cmdNhanthuong;
        }
        this.stylePaint = new int[this.MaxItem];
        for (int i3 = 0; i3 < this.MaxItem; i3++) {
            this.stylePaint[i3] = 0;
        }
        this.stylePaint[0] = 1;
        this.stylePaint[this.MaxItem - 1] = -1;
        this.stylePaint[this.MaxItem - 2] = -1;
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        MainTabNew.timePaintInfo = 0;
        MainScreen.cameraSub.setAll(0, (((XYpaint[this.MaxItem - 1][1] - this.yBegin) + this.sizeKill) - MainTabNew.hblack) + 5, 0, 0);
        this.list = new ListNew(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, (MainTabNew.hblack - GameCanvas.hText) - 2, 0, 0, MainScreen.cameraSub.yLimit);
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
        }
        if (GameCanvas.isTouch) {
            this.idSelect = -1;
        } else {
            this.idSelect = 0;
        }
        this.listContent = null;
    }

    @Override // InterfaceComponents.MainTabNew
    public void backTab() {
        MainTabNew.timePaintInfo = 0;
        if (GameCanvas.isTouch) {
            this.idSelect = -1;
        } else {
            this.idSelect = 0;
        }
        super.backTab();
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                backTab();
                break;
            case 6:
                mSystem.println(new StringBuffer().append("nhận ").append(this.idSelect).toString());
                break;
        }
        super.commandPointer(i, i2);
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.diemkynang).append(Player.diemKyNang).toString(), this.xBegin + 2, this.yBegin + 3, 0, true);
        if (((!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) || MainTabNew.longwidth > 0) && MainTabNew.timePaintInfo > MainTabNew.timeRequest && this.vecListCmd != null) {
            for (int i = 0; i < this.vecListCmd.size(); i++) {
                iCommand icommand = (iCommand) this.vecListCmd.elementAt(i);
                icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
            }
        }
        mgraphics.setClip(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, (MainTabNew.hblack - 2) - GameCanvas.hText);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, (MainTabNew.hblack - 2) - GameCanvas.hText);
        mgraphics.translate(0, -MainScreen.cameraSub.yCam);
        int i2 = this.MaxItem;
        for (int i3 = 0; i3 < i2; i3++) {
            paintLine(mgraphics, i3);
            mgraphics.setColor(-8688548);
            mgraphics.fillRect(((XYpaint[i3][0] - (this.sizeKill / 2)) - 2) + 2, (XYpaint[i3][1] - 2) + 2, 22, 22, true);
            Item item = (Item) vecItem.elementAt(i3);
            if (item != null) {
                item.paintItem(mgraphics, ((XYpaint[i3][0] - (this.sizeKill / 2)) - 2) + 13, (XYpaint[i3][1] - 2) + 13, 22, 0, 0);
            }
            mgraphics.drawImage(AvMain.imgHotKey, ((XYpaint[i3][0] - (this.sizeKill / 2)) - 2) + 2, (XYpaint[i3][1] - 2) + 2, 0, true);
            if (i3 == this.idSelect && MainTabNew.Focus == 1) {
                mgraphics.drawImage(AvMain.imgSelect_1, (XYpaint[i3][0] - (this.sizeKill / 2)) - 2, XYpaint[i3][1] - 2, 0, true);
            }
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        if (((!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) || MainTabNew.longwidth > 0) && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            paintPopupContent(mgraphics, false);
        }
    }

    public void paintLine(mGraphics mgraphics, int i) {
        mgraphics.setColor(MainTabNew.color[6]);
        if (this.stylePaint[i] == 0) {
            mgraphics.fillRect(XYpaint[i][0], XYpaint[i][1] + this.sizeKill, 1, (this.hKill * 2) + this.sizeKill, true);
            return;
        }
        if (this.stylePaint[i] == 1) {
            mgraphics.fillRect(XYpaint[i][0], XYpaint[i][1] + this.sizeKill, 1, this.sizeKill / 2, true);
            mgraphics.fillRect(XYpaint[i][0] - (2 * this.w8), XYpaint[i][1] + this.sizeKill + (this.sizeKill / 2), (this.w8 * 4) + 1, 1, true);
            mgraphics.fillRect(XYpaint[i][0] - (2 * this.w8), XYpaint[i][1] + this.sizeKill + (this.sizeKill / 2) + 1, 1, this.sizeKill / 2, true);
            mgraphics.fillRect(XYpaint[i][0] + (2 * this.w8), XYpaint[i][1] + this.sizeKill + (this.sizeKill / 2) + 1, 1, this.sizeKill / 2, true);
            return;
        }
        if (this.stylePaint[i] == 2) {
            mgraphics.fillRect(XYpaint[i][0], XYpaint[i][1] + this.sizeKill, 1, this.hKill / 2, true);
            mgraphics.fillRect(XYpaint[i][0] - this.w8, XYpaint[i][1] + this.sizeKill + (this.hKill / 2), (this.w8 * 2) + 1, 1, true);
            mgraphics.fillRect(XYpaint[i][0] - this.w8, XYpaint[i][1] + this.sizeKill + (this.hKill / 2) + 1, 1, (this.hKill / 2) + 4, true);
            mgraphics.fillRect(XYpaint[i][0] + this.w8, XYpaint[i][1] + this.sizeKill + (this.hKill / 2) + 1, 1, (this.hKill / 2) + 4, true);
        }
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        try {
            if (MainTabNew.Focus == 1) {
                if (this.listContent != null) {
                    this.listContent.moveCamera();
                }
                if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2) {
                    MainTabNew.timePaintInfo++;
                    if (MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                        setPaintInfo();
                    }
                }
                if (GameCanvas.isTouch) {
                    this.list.moveCamera();
                } else {
                    MainScreen.cameraSub.UpdateCamera();
                    if (this.yCon < MainScreen.cameraSub.yCam + 4) {
                        this.yCon = MainScreen.cameraSub.yCam + 4;
                    }
                }
            } else {
                MainTabNew.timePaintInfo = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        int i = this.idSelect;
        if (GameCanvas.keyMyHold[5]) {
            System.out.println(new StringBuffer().append("nhan thuong ").append(this.idSelect).toString());
            GameCanvas.clearKeyHold();
        }
        if (GameCanvas.keyMyHold[13]) {
            backTab();
            GameCanvas.game.Show();
            GameCanvas.clearKeyHold();
        }
        if (GameCanvas.keyMyHold[2] || GameCanvas.keyMyHold[8] || GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
            TabScreenNew.timeRepaint = 10;
            if (this.idSelect == -1) {
                GameCanvas.clearKeyHold();
                this.idSelect = 0;
                return;
            }
        }
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
            this.idSelect -= 2;
            if (this.idSelect < 0) {
                this.idSelect = 0;
            }
            TabScreenNew.timeRepaint = 10;
            GameCanvas.clearKeyHold(2);
        } else if (GameCanvas.keyMyHold[8]) {
            TabScreenNew.timeRepaint = 10;
            this.idSelect += 2;
            if (this.idSelect > vecItem.size() - 1) {
                this.idSelect = vecItem.size() - 1;
            }
            GameCanvas.clearKeyHold(8);
        }
        if (GameCanvas.keyMyHold[4]) {
            TabScreenNew.timeRepaint = 10;
            this.idSelect--;
            if (this.idSelect <= 0) {
                this.idSelect = 0;
            }
            GameCanvas.clearKeyHold(4);
        } else if (GameCanvas.keyMyHold[6]) {
            TabScreenNew.timeRepaint = 10;
            if (this.idSelect < vecItem.size() - 1) {
                this.idSelect++;
            }
            GameCanvas.clearKeyHold(6);
        }
        if (this.idSelect >= 0) {
            this.idSelect = resetSelect(this.idSelect, this.MaxItem - 1, false);
            if (this.idSelect != i && this.idSelect >= 0 && this.idSelect <= vecItem.size() - 1) {
                this.listContent = null;
                cmdListBig();
                MainTabNew.timePaintInfo = 0;
                MainScreen.cameraSub.moveCamera(0, (XYpaint[this.idSelect][1] - this.yBegin) - (MainTabNew.hblack / 2));
            }
            super.updatekey();
            return;
        }
        this.idSelect = -1;
    }

    @Override // InterfaceComponents.MainTabNew
    public void setPaintInfo() {
        try {
            if (this.idSelect >= vecItem.size() || this.idSelect == -1) {
                MainTabNew.timePaintInfo = 0;
                return;
            }
            this.mContent = null;
            this.mSubContent = null;
            this.mPlusContent = null;
            if (this.idSelect >= this.MaxItem || this.idSelect == -1) {
                MainTabNew.timePaintInfo = 0;
                return;
            }
            Item item = (Item) vecItem.elementAt(this.idSelect);
            if (item == null) {
                this.mContent = null;
                this.mPlusContent = null;
                this.listContent = null;
                return;
            }
            this.name = item.itemName;
            this.mContent = item.mcontent;
            this.mcolor = item.mColor;
            this.mPlusContent = null;
            this.listContent = null;
            if (MainTabNew.longwidth > 0) {
                int length = this.mContent.length;
                if (length * GameCanvas.hText > MainTabNew.hMaxContent - this.hcmd) {
                    this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, (length * GameCanvas.hText) - (MainTabNew.hMaxContent - this.hcmd));
                } else if (GameCanvas.isTouch) {
                    this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent - this.hcmd, 0, 0, 0);
                }
                cmdListBig();
                return;
            }
            this.xCon = XYpaint[this.idSelect][0] - 45;
            if (this.xCon < 2) {
                this.xCon = 2;
            } else if (this.xCon + 92 > GameCanvas.w) {
                this.xCon = GameCanvas.w - 92;
            }
            this.wContent = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
            int i = XYpaint[this.idSelect][1] - MainScreen.cameraSub.yCam;
            if (i < this.yBegin + (MainTabNew.hblack / 2)) {
                this.yCon = i + this.sizeKill + 2 + MainScreen.cameraSub.yCam;
            } else {
                this.yCon = (((i + 1) - (GameCanvas.hText * (this.mContent.length + 1))) - (this.sizeKill / 2)) + MainScreen.cameraSub.yCam;
            }
            if ((this.yCon - MainScreen.cameraSub.yCam) + ((this.mContent.length + 1) * GameCanvas.hText) + 8 > GameCanvas.h - GameCanvas.hCommand) {
                this.yCon = (GameCanvas.h - GameCanvas.hCommand) - ((((this.mContent.length + 1) * GameCanvas.hText) + 8) - MainScreen.cameraSub.yCam);
            }
            if (this.yCon < MainScreen.cameraSub.yCam) {
                this.yCon = MainScreen.cameraSub.yCam;
            }
            int length2 = this.mContent.length;
            if (length2 * GameCanvas.hText > MainTabNew.hMaxContent) {
                this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, (length2 * GameCanvas.hText) - MainTabNew.hMaxContent);
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0109, code lost:
    
        if (InterfaceComponents.MainTabNew.longwidth != 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x010c, code lost:
    
        InterfaceComponents.MainTabNew.timePaintInfo = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0110, code lost:
    
        Main.GameCanvas.isPointerSelect = false;
     */
    @Override // Model.AvMain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updatePointer() {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: InterfaceComponents.TabEvent.updatePointer():void");
    }

    public mVector doMenu() {
        mVector mvector = new mVector("TabSkillsNew menu2");
        mvector.addElement(this.cmdNhanthuong);
        this.hcmd = ((mvector.size() + 1) / 2) * iCommand.hButtonCmd;
        return mvector;
    }

    public void cmdListBig() {
        if (MainTabNew.longwidth > 0 && GameCanvas.isTouch && this.idSelect >= 0) {
            this.vecListCmd = doMenu();
            setPosCmd(this.vecListCmd);
        }
    }
}
