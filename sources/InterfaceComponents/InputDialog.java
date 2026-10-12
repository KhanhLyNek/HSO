package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/InputDialog.class */
public class InputDialog extends MainDialog {
    public TField tfInput;
    TField[] mtfInput;
    boolean iscroll;
    long price;
    String name;
    String info;
    String xuluong;
    static int hTouch = 0;
    static int istouchMore = 0;
    static int hitem;
    ListNew list;
    String strtam;
    boolean isMore = false;
    boolean isNew = false;
    short type = 0;
    short idNPC = -1;
    mFont fontInput = mFont.tahoma_7_white;
    int idSelect = 0;
    iCommand cmdClose = new iCommand(T.close, -1);

    public InputDialog() {
        istouchMore = 15;
        if (GameCanvas.isTouch) {
            hTouch = iCommand.hButtonCmd + 5;
        }
    }

    public void closeDialog() {
        if (GameCanvas.currentDialog != null) {
            GameCanvas.currentDialog = null;
        } else {
            GameCanvas.subDialog = null;
        }
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case -1:
                closeDialog();
                break;
            case 0:
                String[] strArr = new String[this.mtfInput.length];
                for (int i3 = 0; i3 < strArr.length; i3++) {
                    strArr[i3] = "";
                    if (this.mtfInput[i3].getText().length() > 0) {
                        strArr[i3] = this.mtfInput[i3].getText();
                    }
                }
                GlobalService.gI().nap_tien(this.type, strArr);
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                break;
            case 1:
                mVector mvector = new mVector();
                boolean z = this.idNPC == GameScreen.ID_DLG_BUY_KC;
                for (int i4 = 0; i4 < this.mtfInput.length; i4++) {
                    String str = "";
                    if (this.mtfInput[i4].getText().length() > 0) {
                        str = this.mtfInput[i4].getText();
                    }
                    mvector.addElement(str);
                }
                if (z) {
                    mvector.addElement(mSystem.getLong());
                    mvector.addElement(mSystem.getLat());
                }
                GlobalService.gI().sendMoreServerInfo(this.idNPC, this.type, mvector);
                closeDialog();
                break;
        }
        super.commandTab(i, i2);
    }

    public void setinfo(String str, iCommand icommand, boolean z, String str2) {
        this.isMore = false;
        this.isNew = false;
        this.left = null;
        this.right = null;
        this.center = null;
        if (icommand == null) {
            GameCanvas.currentDialog = null;
        }
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.strinfo = this.fontInput.splitFontArray(str, this.wDia - 20);
        this.name = str2;
        this.hDia = (15 * this.strinfo.length) + 10 + TField.getHeight() + GameCanvas.hCommand;
        this.hDia += hTouch + istouchMore;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) + 15;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.tfInput = new TField(this.xDia + 10, ((this.yDia + this.hDia) - hTouch) - (TField.getHeight() + 8), this.wDia - 20);
        this.tfInput.setMaxTextLenght(100);
        if (z) {
            this.tfInput.setIputType(1);
        }
        this.tfInput.setText("");
        if (GameCanvas.isTouch) {
            icommand.setPos(this.xDia + (this.wDia / 2), ((this.yDia + this.hDia) - (iCommand.hButtonCmd / 2)) - 5, null, icommand.caption);
            this.cmdClose.setPos((this.xDia + this.wDia) - 13, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.right = this.cmdClose;
            this.left = icommand;
            this.tfInput.title = str;
            return;
        }
        this.center = icommand;
        this.left = this.cmdClose;
        this.tfInput.setFocus(true);
        this.right = this.tfInput.cmdClear;
    }

    public void setinfo(String[] strArr, iCommand icommand, short s, short s2, String str, String[] strArr2, byte b) {
        this.isMore = true;
        this.isNew = false;
        this.left = null;
        this.right = null;
        this.center = null;
        this.mtfInput = null;
        hitem = 50;
        this.type = s;
        this.idNPC = s2;
        if (icommand == null) {
            GameCanvas.subDialog = null;
        }
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 140) {
            this.wDia = 140;
        }
        this.mtfInput = new TField[strArr.length];
        this.strinfo = strArr;
        this.name = str;
        if (this.mtfInput.length > 3) {
            this.iscroll = true;
        }
        if (this.iscroll) {
            this.wDia = (GameCanvas.w / 4) * 3;
            this.hDia = (GameCanvas.h / 5) * 4;
            this.wDia = GameCanvas.w - 30;
            if (this.wDia > 140) {
                this.wDia = 140;
            }
            if (this.hDia < 210) {
                this.hDia = 210;
            } else if (this.hDia > 280) {
                this.hDia = 280;
            }
            if (this.hDia < 210) {
                this.hDia = 210;
            } else if (this.hDia > 280) {
                this.hDia = 280;
            }
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = GameCanvas.hh - (this.hDia / 2);
            if (this.yDia < 20) {
                this.yDia = 20;
            }
            if (GameCanvas.isSmallScreen) {
                this.yDia = 0;
            }
        } else {
            this.hDia = ((TField.getHeight() + 18) * this.strinfo.length) + 6 + GameCanvas.hCommand;
            this.hDia += hTouch + istouchMore;
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) + 15;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        for (int i = 0; i < this.mtfInput.length; i++) {
            this.mtfInput[i] = new TField(this.xDia + 10, this.yDia + 8 + ((TField.getHeight() + 18) * i) + istouchMore + GameCanvas.hCommand, this.wDia - 20);
            this.mtfInput[i].setText("");
        }
        if (GameCanvas.isTouch) {
            icommand.setPos(this.xDia + (this.wDia / 2), ((this.yDia + this.hDia) - (iCommand.hButtonCmd / 2)) - 5, null, icommand.caption);
            this.cmdClose.setPos((this.xDia + this.wDia) - 13, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            if (GameCanvas.currentScreen != GameCanvas.createChar) {
                this.right = this.cmdClose;
            }
            this.left = icommand;
        } else {
            this.center = icommand;
            this.left = this.cmdClose;
        }
        if (!GameCanvas.isTouch) {
            this.mtfInput[0].setFocus(true);
            this.right = this.mtfInput[0].cmdClear;
        }
        if (this.iscroll) {
            MainScreen.cameraSub.setAll(0, ((this.mtfInput.length * hitem) - this.hDia) + GameCanvas.hCommand + 30, 0, 0);
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, hitem, 0, MainScreen.cameraSub.yLimit);
        }
        if (b == 0) {
            for (int i2 = 0; i2 < this.mtfInput.length; i2++) {
                this.mtfInput[i2].setStringNull(strArr[i2]);
            }
        }
        for (int i3 = 0; i3 < this.mtfInput.length; i3++) {
            this.mtfInput[i3].setText(strArr2[i3]);
        }
    }

    public void setinfo(String[] strArr, iCommand icommand, short s, short s2, String str) {
        this.isMore = true;
        this.isNew = false;
        this.left = null;
        this.right = null;
        this.center = null;
        this.mtfInput = null;
        hitem = 50;
        this.type = s;
        this.idNPC = s2;
        if (icommand == null) {
            GameCanvas.subDialog = null;
        }
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 140) {
            this.wDia = 140;
        }
        this.mtfInput = new TField[strArr.length];
        this.strinfo = strArr;
        this.name = str;
        if (this.mtfInput.length > 3) {
            this.iscroll = true;
        }
        if (this.iscroll) {
            this.wDia = (GameCanvas.w / 4) * 3;
            this.hDia = (GameCanvas.h / 5) * 4;
            this.wDia = GameCanvas.w - 30;
            if (this.wDia > 140) {
                this.wDia = 140;
            }
            if (this.hDia < 210) {
                this.hDia = 210;
            } else if (this.hDia > 280) {
                this.hDia = 280;
            }
            if (this.hDia < 210) {
                this.hDia = 210;
            } else if (this.hDia > 280) {
                this.hDia = 280;
            }
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = GameCanvas.hh - (this.hDia / 2);
            if (this.yDia < 20) {
                this.yDia = 20;
            }
            if (GameCanvas.isSmallScreen) {
                this.yDia = 0;
            }
        } else {
            this.hDia = ((TField.getHeight() + 18) * this.strinfo.length) + 6 + GameCanvas.hCommand;
            this.hDia += hTouch + istouchMore;
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) + 15;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        for (int i = 0; i < this.mtfInput.length; i++) {
            this.mtfInput[i] = new TField(this.xDia + 10, this.yDia + 8 + ((TField.getHeight() + 18) * i) + istouchMore + GameCanvas.hCommand, this.wDia - 20);
            this.mtfInput[i].setText("");
        }
        if (GameCanvas.isTouch) {
            icommand.setPos(this.xDia + (this.wDia / 2), ((this.yDia + this.hDia) - (iCommand.hButtonCmd / 2)) - 5, null, icommand.caption);
            this.cmdClose.setPos((this.xDia + this.wDia) - 13, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.right = this.cmdClose;
            this.left = icommand;
        } else {
            this.center = icommand;
            this.left = this.cmdClose;
        }
        if (!GameCanvas.isTouch) {
            this.mtfInput[0].setFocus(true);
            this.right = this.mtfInput[0].cmdClear;
        }
        if (this.iscroll) {
            MainScreen.cameraSub.setAll(0, ((this.mtfInput.length * hitem) - this.hDia) + GameCanvas.hCommand + 30, 0, 0);
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, hitem, 0, MainScreen.cameraSub.yLimit);
        }
    }

    public void setinfoSmallNew(String str, iCommand icommand, boolean z, int i, long j, String str2) {
        this.isMore = false;
        this.isNew = true;
        this.left = null;
        this.right = null;
        this.center = null;
        if (icommand == null) {
            GameCanvas.currentDialog = null;
        }
        this.price = j;
        this.info = str;
        this.name = "";
        this.xuluong = str2;
        this.wDia = (GameCanvas.w / 3) * 2;
        if (this.wDia > 160) {
            this.wDia = 160;
        }
        this.strinfo = this.fontInput.splitFontArray(new StringBuffer().append(str).append(" ").append("").toString(), this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + 10 + TField.getHeight() + GameCanvas.hCommand;
        this.hDia += hTouch + istouchMore;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) + 15;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        int i2 = this.wDia - 100;
        if (i2 < 40) {
            i2 = 40;
        }
        this.tfInput = new TField((this.xDia + (this.wDia / 2)) - (i2 / 2), ((this.yDia + this.hDia) - hTouch) - (TField.getHeight() + 8), i2);
        if (z) {
            this.tfInput.setIputType(1);
        }
        if (i >= 0) {
            this.tfInput.setText(new StringBuffer().append("").append(i).toString());
        }
        this.strtam = this.tfInput.getText();
        if (GameCanvas.isTouch) {
            icommand.setPos(this.xDia + (this.wDia / 2), ((this.yDia + this.hDia) - (iCommand.hButtonCmd / 2)) - 5, null, icommand.caption);
            this.cmdClose.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.right = this.cmdClose;
            this.left = icommand;
            return;
        }
        this.center = icommand;
        this.left = this.cmdClose;
        this.tfInput.setFocus(true);
        this.right = this.tfInput.cmdClear;
    }

    public void setinfoSmallNew(String str, iCommand icommand, boolean z, int i, long j, String str2, String str3) {
        this.isMore = false;
        this.isNew = true;
        this.left = null;
        this.right = null;
        this.center = null;
        if (icommand == null) {
            GameCanvas.currentDialog = null;
        }
        this.price = j;
        this.info = str;
        this.name = str2;
        this.xuluong = str3;
        this.wDia = (GameCanvas.w / 3) * 2;
        if (this.wDia > 160) {
            this.wDia = 160;
        }
        this.strinfo = this.fontInput.splitFontArray(new StringBuffer().append(str).append(" ").append(new StringBuffer().append("\n").append(T.price).append(" ").append(j).append(" ").append(str3).toString()).toString(), this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + 10 + TField.getHeight() + GameCanvas.hCommand;
        this.hDia += hTouch + istouchMore;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) + 15;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        int i2 = this.wDia - 100;
        if (i2 < 40) {
            i2 = 40;
        }
        this.tfInput = new TField((this.xDia + (this.wDia / 2)) - (i2 / 2), ((this.yDia + this.hDia) - hTouch) - (TField.getHeight() + 8), i2);
        if (z) {
            this.tfInput.setIputType(1);
        }
        if (i >= 0) {
            this.tfInput.setText(new StringBuffer().append("").append(i).toString());
        }
        this.strtam = this.tfInput.getText();
        if (GameCanvas.isTouch) {
            icommand.setPos(this.xDia + (this.wDia / 2), ((this.yDia + this.hDia) - (iCommand.hButtonCmd / 2)) - 5, null, icommand.caption);
            this.cmdClose.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.right = this.cmdClose;
            this.left = icommand;
            return;
        }
        this.center = icommand;
        this.left = this.cmdClose;
        this.tfInput.setFocus(true);
        this.right = this.tfInput.cmdClear;
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        GameCanvas.resetTrans(mgraphics);
        paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, this.name);
        if (this.iscroll) {
            mgraphics.setClip(this.xDia + 10, this.yDia + GameCanvas.hCommand, this.wDia, (this.hDia - hitem) - 10);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(this.xDia + 10, this.yDia + GameCanvas.hCommand, this.wDia, (this.hDia - hitem) - 10);
            mgraphics.translate(0, -MainScreen.cameraSub.yCam);
        }
        int i = this.yDia + istouchMore + GameCanvas.hCommand;
        if (this.isMore) {
            for (int i2 = 0; i2 < this.mtfInput.length; i2++) {
                this.fontInput.drawString(mgraphics, this.strinfo[i2], GameCanvas.w / 2, (i - 5) + (i2 * (TField.getHeight() + 18)), 2, true);
                if (this.iscroll) {
                    this.mtfInput[i2].paintByList(mgraphics);
                } else {
                    this.mtfInput[i2].paint(mgraphics);
                }
            }
        } else {
            for (int i3 = 0; i3 < this.strinfo.length; i3++) {
                this.fontInput.drawString(mgraphics, this.strinfo[i3], GameCanvas.w / 2, (i + (i3 * 15)) - 5, 2, true);
            }
            this.tfInput.paint(mgraphics);
        }
        if (this.iscroll) {
            mGraphics.resetTransAndroid(mgraphics);
            mgraphics.restoreCanvas();
        }
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
        super.paintCmd(mgraphics);
    }

    @Override // InterfaceComponents.MainDialog, Model.AvMain
    public void keypress(int i) {
        if (this.isMore) {
            for (int i2 = 0; i2 < this.mtfInput.length; i2++) {
                if (this.mtfInput[i2].isFocused()) {
                    this.mtfInput[i2].keyPressed(i);
                    return;
                }
            }
        } else {
            this.tfInput.keyPressed(i);
        }
        super.keypress(i);
    }

    @Override // Model.AvMain
    public void update() {
        int i;
        updatekey();
        updatePointer();
        if (this.isMore) {
            for (int i2 = 0; i2 < this.mtfInput.length; i2++) {
                this.mtfInput[i2].update();
            }
        } else if (this.tfInput != null) {
            this.tfInput.update();
            if (!GameCanvas.isTouch && this.right != this.tfInput.cmdClear) {
                this.right = this.tfInput.cmdClear;
            }
            if (this.isNew && this.tfInput.getText().compareTo(this.strtam) != 0) {
                this.strtam = this.tfInput.getText();
                try {
                    i = Integer.parseInt(this.strtam);
                } catch (Exception e) {
                    i = 0;
                }
                this.strinfo = this.fontInput.splitFontArray(new StringBuffer().append(this.info).append(" ").append(new StringBuffer().append("\n").append(T.price).append(" ").append(this.price * i).append(" ").append(this.xuluong).toString()).toString(), this.wDia - 20);
                this.hDia = (15 * this.strinfo.length) + 10 + TField.getHeight() + hTouch + istouchMore + GameCanvas.hCommand;
                int i3 = this.wDia - 100;
                if (i3 < 40) {
                    i3 = 40;
                }
                this.xDia = GameCanvas.hw - (this.wDia / 2);
                this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) + 15;
                this.numw = (this.wDia - 6) / 32;
                this.numh = (this.hDia - 6) / 32;
                this.tfInput.x = (this.xDia + (this.wDia / 2)) - (i3 / 2);
                this.tfInput.y = ((this.yDia + this.hDia) - (TField.getHeight() + 8)) - hTouch;
            }
        }
        super.update();
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (this.isMore) {
            int i = this.idSelect;
            if (GameCanvas.keyMyHold[8]) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.mtfInput.length) {
                        break;
                    }
                    if (!this.mtfInput[i2].isFocused()) {
                        i2++;
                    } else {
                        this.mtfInput[i2].setFocus(false);
                        if (i2 < this.mtfInput.length - 1) {
                            this.mtfInput[i2 + 1].setFocus(true);
                            this.idSelect = i2 + 1;
                            if (!GameCanvas.isTouch) {
                                this.right = this.mtfInput[i2 + 1].cmdClear;
                            }
                        } else {
                            this.mtfInput[0].setFocus(true);
                            this.idSelect = 0;
                            if (!GameCanvas.isTouch) {
                                this.right = this.mtfInput[0].cmdClear;
                            }
                        }
                    }
                }
                GameCanvas.clearKeyHold(8);
            } else if (GameCanvas.keyMyHold[2]) {
                int i3 = 0;
                while (true) {
                    if (i3 >= this.mtfInput.length) {
                        break;
                    }
                    if (!this.mtfInput[i3].isFocused()) {
                        i3++;
                    } else {
                        this.mtfInput[i3].setFocus(false);
                        if (i3 > 0) {
                            this.mtfInput[i3 - 1].setFocus(true);
                            this.idSelect = i3 - 1;
                        } else {
                            this.mtfInput[this.mtfInput.length - 1].setFocus(true);
                            this.idSelect = this.mtfInput.length - 1;
                        }
                    }
                }
                GameCanvas.clearKeyHold(2);
            }
            this.idSelect = resetSelect(this.idSelect, this.mtfInput.length - 1, false);
            if (i != this.idSelect) {
                MainScreen.cameraSub.moveCamera(0, ((this.idSelect * 40) - (this.hDia / 2)) + 40 + GameCanvas.hCommand);
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        super.updatePointer();
        if (this.isMore) {
            if (this.iscroll) {
                if (GameCanvas.isPointSelect(this.xDia, this.yDia + GameCanvas.hCommand, this.wDia, this.hDia - GameCanvas.hCommand)) {
                    for (int i = 0; i < this.mtfInput.length; i++) {
                        if (this.mtfInput[i].isFocus) {
                            return;
                        }
                    }
                    int i2 = (((MainScreen.cameraSub.yCam + GameCanvas.py) - this.yDia) - GameCanvas.hCommand) / hitem;
                    if (i2 >= 0 && i2 < this.mtfInput.length) {
                        if (GameCanvas.isTouch) {
                            this.mtfInput[i2].setFocus(true);
                            this.mtfInput[i2].updatepointerByList();
                        } else {
                            this.mtfInput[i2].updatePoiter();
                        }
                    }
                    GameCanvas.isPointerSelect = false;
                }
            } else {
                for (int i3 = 0; i3 < this.mtfInput.length; i3++) {
                    this.mtfInput[i3].updatePoiter();
                }
            }
        } else if (this.tfInput != null) {
            this.tfInput.updatePoiter();
        }
        if (this.iscroll) {
            if (GameCanvas.isTouch && this.list != null) {
                this.list.moveCamera();
            } else {
                MainScreen.cameraSub.UpdateCamera();
            }
        }
        if (GameCanvas.isTouch && this.list != null) {
            this.list.update_Pos_UP_DOWN();
            MainScreen.cameraSub.yCam = this.list.cmx;
        }
    }

    public String[] getarrayText() {
        String[] strArr = new String[this.mtfInput.length];
        for (int i = 0; i < this.mtfInput.length; i++) {
            strArr[i] = this.mtfInput[i].getText();
        }
        return strArr;
    }
}
