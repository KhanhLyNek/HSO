package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mVector;
import GameObjects.MainEvent;
import InterfaceComponents.ListNew;
import InterfaceComponents.MsgChat;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import Model.mCamera;

/* loaded from: KnightOnline_v403.jar:GameScreen/EventScreen.class */
public class EventScreen extends MainScreen {
    public int wDia;
    public int maxSizeList;
    public int hbutton;
    public int hDia;
    public int min;
    public int max;
    public int xDia;
    public int yDia;
    public int numw;
    public int numh;
    public int idSelect;
    public int idCommand;
    public static mVector vecListEvent = new mVector("EventScreen vecListEvent");
    public static mVector vecEventShow = new mVector("EventScreen vecEventShow");
    public static mVector cmdList = new mVector("EventScreen cmdList");
    ListNew list;
    int xBegin;
    int w2cmd;
    mCamera cameraDia = new mCamera();
    iCommand cmdSeL = new iCommand(T.view, 2, 0, this);
    iCommand cmdDel = new iCommand(T.delMess, 2, 1, this);
    public int hItem = GameCanvas.hCommand + 5;

    public EventScreen() {
        this.wDia = (GameCanvas.w / 4) * 3;
        if (this.wDia > 180) {
            this.wDia = 180;
        }
    }

    public void setCaptionCmd() {
        this.cmdSeL.caption = T.view;
        this.cmdDel.caption = T.delMess;
    }

    @Override // GameScreen.MainScreen
    public void Show(MainScreen mainScreen) {
        super.Show(mainScreen);
        PaintInfoGameScreen.numMess = 0;
    }

    public void init() {
        int i = 0;
        while (i < vecListEvent.size()) {
            MainEvent mainEvent = (MainEvent) vecListEvent.elementAt(i);
            if (mainEvent.isRemove) {
                vecListEvent.removeElement(mainEvent);
                i--;
            }
            i++;
        }
        this.hbutton = (iCommand.hButtonCmd * 3) / 2;
        if (GameCanvas.isTouch) {
            this.hbutton = 0;
        }
        this.maxSizeList = ((((GameCanvas.h / 4) * 3) - (10 + GameCanvas.hCommand)) / this.hItem) + 1;
        this.hDia = (this.hItem * this.maxSizeList) + 10 + GameCanvas.hCommand;
        this.maxSizeList += 2;
        this.min = 0;
        this.max = this.maxSizeList;
        if (this.max > vecListEvent.size()) {
            this.max = vecListEvent.size();
        }
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = (GameCanvas.hh - (this.hDia / 2)) - (GameCanvas.hCommand / 2);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        updateList();
    }

    private void updateList() {
        cmdList.removeAllElements();
        iCommand icommand = new iCommand(T.close, -1, this);
        if (GameCanvas.isTouch) {
            icommand.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            cmdList.addElement(icommand);
        } else {
            if (vecListEvent.size() > 0) {
                cmdList.addElement(new iCommand(T.menu, 1, this));
            }
            cmdList.addElement(icommand);
            setPosCmdNew(0);
            this.right = icommand;
        }
        this.cameraDia.setAll(0, (this.hItem * vecListEvent.size()) - ((this.hDia - GameCanvas.hCommand) - this.hbutton), 0, 0);
        this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, this.cameraDia.yLimit);
        if (this.idSelect >= vecListEvent.size()) {
            if (GameCanvas.isTouch) {
                this.idSelect = -1;
            } else {
                this.idSelect = 0;
            }
        }
    }

    public void setPosCmdNew(int i) {
        this.idCommand = 0;
        if (cmdList.size() > 0) {
            int size = cmdList.size();
            if (size == 1) {
                this.xBegin = this.xDia + (this.wDia / 2);
                this.w2cmd = 0;
            } else if (size == 2) {
                this.w2cmd = 20;
                this.xBegin = ((this.xDia + (this.wDia / 2)) - (this.w2cmd / 2)) - (iCommand.wButtonCmd / 2);
            } else {
                this.w2cmd = 20;
                this.xBegin = ((this.xDia + (this.wDia / 2)) - (this.w2cmd / 2)) - (iCommand.wButtonCmd / 2);
            }
            for (int i2 = 0; i2 < size; i2++) {
                iCommand icommand = (iCommand) cmdList.elementAt(i2);
                icommand.isSelect = false;
                if (size == 3 && i2 == 2) {
                    icommand.setPos(this.xDia + (this.wDia / 2), (((this.yDia + this.hDia) - iCommand.hButtonCmd) - (((size - 1) / 2) * (iCommand.hButtonCmd + 5))) + 7 + ((i2 / 2) * (iCommand.hButtonCmd + 5)) + i, null, icommand.caption);
                } else {
                    icommand.setPos(this.xBegin + ((i2 % 2) * (iCommand.wButtonCmd + this.w2cmd)), (((this.yDia + this.hDia) - iCommand.hButtonCmd) - (((size - 1) / 2) * (iCommand.hButtonCmd + 5))) + 7 + ((i2 / 2) * (iCommand.hButtonCmd + 5)) + i, null, icommand.caption);
                }
                if (i2 == 0) {
                    icommand.isSelect = true;
                }
            }
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                this.lastScreen.Show(this.lastScreen.lastScreen);
                return;
            case 0:
            default:
                return;
            case 1:
                if (this.idSelect < 0 || this.idSelect >= vecListEvent.size()) {
                    return;
                }
                if (this.idSelect > 0) {
                    mVector mvector = new mVector("EventScreen vec");
                    mvector.addElement(this.cmdSeL);
                    mvector.addElement(this.cmdDel);
                    GameCanvas.menu2.startAt(mvector, 2, T.mevent, false, null);
                    return;
                }
                this.cmdSeL.perform();
                return;
            case 2:
                if (this.idSelect < 0 || this.idSelect >= vecListEvent.size()) {
                    return;
                }
                boolean z = false;
                if (i2 == 1) {
                    z = true;
                }
                doEvent(z, (MainEvent) vecListEvent.elementAt(this.idSelect));
                return;
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
        paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, T.mevent);
        int i = this.yDia + GameCanvas.hCommand + 3;
        if (vecListEvent.size() > 0) {
            mgraphics.setClip(this.xDia + 4, i, this.wDia - 8, ((this.hDia - GameCanvas.hCommand) - this.hbutton) - 6);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(this.xDia + 4, i, this.wDia - 8, ((this.hDia - GameCanvas.hCommand) - this.hbutton) - 6);
            mgraphics.translate(0, -this.cameraDia.yCam);
            if (this.idSelect >= 0) {
                mgraphics.setColor(-4873075);
                mgraphics.fillRect(this.xDia + 4, (i - 2) + (this.idSelect * this.hItem), this.wDia - 8, this.hItem - 1, true);
            }
            int i2 = i + (this.hItem * this.min);
            for (int i3 = this.min; i3 < this.max; i3++) {
                if (i3 >= 0 || i3 < vecListEvent.size()) {
                    MainEvent mainEvent = (MainEvent) vecListEvent.elementAt(i3);
                    int i4 = 0;
                    if (mainEvent.isNew == 1 && GameCanvas.gameTick % 20 > 9) {
                        i4 = 1;
                    }
                    PaintInfoGameScreen.fraEvent.drawFrame(((mainEvent.IDCmd * 2) + 1) - i4, this.xDia + 20, (i2 + (this.hItem / 2)) - 2, 0, 3, mgraphics);
                    if (GameCanvas.isTouch && i3 > 0) {
                        PaintInfoGameScreen.fraClose.drawFrame(0, (this.xDia + this.wDia) - 20, (i2 + (this.hItem / 2)) - 2, 0, 3, mgraphics);
                    }
                    mFont.tahoma_7b_white.drawString(mgraphics, mainEvent.nameEvent, this.xDia + 35, i2 + 1, 0, true);
                    mFont.tahoma_7_white.drawString(mgraphics, mainEvent.contentEvent, this.xDia + 42, i2 + 13, 0, true);
                    i2 += this.hItem;
                    if (i3 < vecListEvent.size() - 1) {
                        mgraphics.setColor(AvMain.color[4]);
                        mgraphics.fillRect(this.xDia + 6, i2 - 3, this.wDia - 12, 1, true);
                    }
                }
            }
            mGraphics.resetTransAndroid(mgraphics);
            mgraphics.restoreCanvas();
            mgraphics.endClip();
        } else {
            mFont.tahoma_7_white.drawString(mgraphics, T.noEvent, this.xDia + (this.wDia / 2), i + 3, 2, true);
        }
        GameCanvas.resetTrans(mgraphics);
        if (cmdList != null) {
            for (int i5 = 0; i5 < cmdList.size(); i5++) {
                iCommand icommand = (iCommand) cmdList.elementAt(i5);
                icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
            }
        }
        paintCmd_OnlyText(mgraphics);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (GameCanvas.currentScreen == this) {
            this.lastScreen.update();
        }
        int i = this.cameraDia.yCam;
        if (GameCanvas.isTouch) {
            this.list.moveCamera();
        } else {
            this.cameraDia.UpdateCamera();
        }
        super.update();
        int size = vecListEvent.size();
        int i2 = 0;
        while (i2 < vecListEvent.size()) {
            MainEvent mainEvent = (MainEvent) vecListEvent.elementAt(i2);
            if (mainEvent.isRemove) {
                vecListEvent.removeElement(mainEvent);
                i2--;
            }
            i2++;
        }
        if (this.idSelect < 0 || this.idSelect >= vecListEvent.size()) {
            if (GameCanvas.isTouch) {
                this.idSelect = -1;
            } else {
                this.idSelect = 0;
            }
        }
        if (this.cameraDia.yCam != i || size != vecListEvent.size()) {
            this.min = this.cameraDia.yCam / this.hItem;
            this.max = this.min + this.maxSizeList;
            if (this.max > vecListEvent.size()) {
                this.max = vecListEvent.size();
                this.min = this.max - this.maxSizeList;
            }
            if (this.min < 0) {
                this.min = 0;
            }
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (vecListEvent.size() > 0) {
            if (GameCanvas.keyMyHold[2]) {
                this.idSelect--;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = resetSelect(this.idSelect, vecListEvent.size() - 1, true);
            this.cameraDia.moveCamera(0, (this.idSelect * this.hItem) - (((this.hDia / 2) - GameCanvas.hCommand) - this.hbutton));
        } else if (cmdList.size() > 1) {
            cmdList.removeAllElements();
            cmdList.addElement(new iCommand(T.close, -1, this));
            setPosCmdNew(0);
            this.idSelect = 0;
        }
        if (cmdList != null) {
            int size = cmdList.size();
            if (!GameCanvas.isTouch && size > 0) {
                int i = this.idCommand;
                if (GameCanvas.keyMyHold[4]) {
                    this.idCommand--;
                    GameCanvas.clearKeyHold(4);
                } else if (GameCanvas.keyMyHold[6]) {
                    this.idCommand++;
                    GameCanvas.clearKeyHold(6);
                }
                this.idCommand = resetSelect(this.idCommand, size - 1, false);
                if (i != this.idCommand) {
                    for (int i2 = 0; i2 < size; i2++) {
                        iCommand icommand = (iCommand) cmdList.elementAt(i2);
                        if (i2 == this.idCommand) {
                            icommand.isSelect = true;
                        } else {
                            icommand.isSelect = false;
                        }
                    }
                }
            }
        }
        if (GameCanvas.keyMyHold[5]) {
            GameCanvas.clearKeyHold(5);
            if (cmdList != null && this.idCommand < cmdList.size()) {
                ((iCommand) cmdList.elementAt(this.idCommand)).perform();
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        int i;
        if (GameCanvas.isTouch) {
            this.list.update_Pos_UP_DOWN();
            this.cameraDia.yCam = this.list.cmx;
        }
        if (vecListEvent.size() > 0 && GameCanvas.isPointSelect(this.xDia, this.yDia + GameCanvas.hCommand, this.wDia, this.hDia - GameCanvas.hCommand) && (i = (GameCanvas.py - (this.yDia + GameCanvas.hCommand)) / this.hItem) >= 0 && i <= vecListEvent.size() - 1) {
            this.idSelect = i;
            this.idSelect = resetSelect(this.idSelect, vecListEvent.size() - 1, false);
            boolean z = false;
            if (GameCanvas.isPointSelect((this.xDia + this.wDia) - 30, this.yDia + GameCanvas.hCommand, 30, this.hDia - GameCanvas.hCommand) && this.idSelect > 0) {
                z = true;
            }
            doEvent(z, (MainEvent) vecListEvent.elementAt(this.idSelect));
            GameCanvas.isPointerSelect = false;
        }
        if (cmdList != null) {
            for (int i2 = 0; i2 < cmdList.size(); i2++) {
                ((iCommand) cmdList.elementAt(i2)).updatePointer();
            }
        }
    }

    public void doEvent(boolean z, MainEvent mainEvent) {
        if (z) {
            mainEvent.isRemove = true;
            return;
        }
        if (PaintInfoGameScreen.numMess > 0) {
            PaintInfoGameScreen.numMess--;
        }
        switch (mainEvent.IDCmd) {
            case 0:
                MsgChat.setIndexFocus(mainEvent.nameEvent);
                GameCanvas.start_Chat_Dialog();
                mainEvent.isNew = 0;
                return;
            case 1:
                mVector mvector = new mVector("EventScreen vec2");
                mvector.addElement(new iCommand(T.chapnhan, 1, 1, mainEvent));
                mvector.addElement(new iCommand(T.tuchoi, 1, 2, mainEvent));
                mvector.addElement(new iCommand(T.close, -1));
                GameCanvas.start_Select_Dialog(new StringBuffer().append(mainEvent.nameEvent).append(T.yeucauketban).toString(), mvector);
                mainEvent.isNew = 0;
                return;
            case 2:
                mVector mvector2 = new mVector("EventScreen vec3");
                mvector2.addElement(new iCommand(T.chapnhan, 2, 1, mainEvent));
                mvector2.addElement(new iCommand(T.tuchoi, 2, 0, mainEvent));
                mvector2.addElement(new iCommand(T.close, -1));
                GameCanvas.start_Select_Dialog(new StringBuffer().append(mainEvent.nameEvent).append(T.loimoiParty).toString(), mvector2);
                mainEvent.isNew = 0;
                return;
            case 3:
                mVector mvector3 = new mVector("EventScreen vec4");
                mvector3.addElement(new iCommand(T.chapnhan, 3, 1, mainEvent));
                mvector3.addElement(new iCommand(T.tuchoi, 3, 0, mainEvent));
                mvector3.addElement(new iCommand(T.close, -1));
                GameCanvas.start_Select_Dialog(new StringBuffer().append(mainEvent.nameEvent).append(T.hoigiaodich).toString(), mvector3);
                mainEvent.isNew = 0;
                return;
            case 4:
                mVector mvector4 = new mVector("EventScreen vec5");
                mvector4.addElement(new iCommand(T.chapnhan, 4, 1, mainEvent));
                mvector4.addElement(new iCommand(T.info, 4, 2, mainEvent));
                mvector4.addElement(new iCommand(T.cancel, 4, 0, mainEvent));
                GameCanvas.start_Select_Dialog(new StringBuffer().append(mainEvent.nameEvent).append(T.hoithachdau).append(mainEvent.numThachDau).append(" ").append(T.coin).append(".").toString(), mvector4);
                mainEvent.isNew = 0;
                return;
            case 5:
                mVector mvector5 = new mVector("EventScreen vec6");
                mvector5.addElement(new iCommand(T.chapnhan, 5, 1, mainEvent));
                mvector5.addElement(new iCommand(T.close, -1));
                GameCanvas.start_Select_Dialog(new StringBuffer().append(mainEvent.nameEvent).append(T.moivaoclan).toString(), mvector5);
                mainEvent.isNew = 0;
                return;
            default:
                return;
        }
    }

    public static MainEvent setEvent(String str, byte b) {
        int i = 0;
        while (i < vecListEvent.size()) {
            MainEvent mainEvent = (MainEvent) vecListEvent.elementAt(i);
            if (mainEvent.isRemove) {
                vecListEvent.removeElement(mainEvent);
                i--;
            } else if (mainEvent.IDCmd == b && mainEvent.nameEvent.compareTo(str) == 0) {
                return mainEvent;
            }
            i++;
        }
        return null;
    }

    public static MainEvent setEventShow(String str, byte b) {
        for (int i = 0; i < vecEventShow.size(); i++) {
            MainEvent mainEvent = (MainEvent) vecEventShow.elementAt(i);
            if (mainEvent.IDCmd == b && mainEvent.nameEvent.compareTo(str) == 0) {
                return mainEvent;
            }
        }
        return null;
    }

    public static int setIndexEvent(String str, byte b) {
        for (int i = 0; i < vecListEvent.size(); i++) {
            MainEvent mainEvent = (MainEvent) vecListEvent.elementAt(i);
            if (mainEvent.IDCmd == b && mainEvent.nameEvent.compareTo(str) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void addEvent(String str, byte b, String str2, int i) {
        MainEvent event = setEvent(str, b);
        if (event == null) {
            event = new MainEvent(0, b, str, str2);
            vecListEvent.addElement(event);
            updateList();
            this.min = this.cameraDia.yCam / this.hItem;
            this.max = this.min + this.maxSizeList;
            if (this.max > vecListEvent.size()) {
                this.max = vecListEvent.size();
                this.min = this.max - this.maxSizeList;
            }
            if (this.min < 0) {
                this.min = 0;
            }
        } else if (b == 0) {
            event.contentEvent = str2;
        }
        if (str.compareTo(T.tinden) != 0) {
            PaintInfoGameScreen.numMess++;
            event.isNew = 1;
            event.numThachDau = i;
            if (b != 0 || (str.compareTo(T.tabBangHoi) != 0 && str.compareTo(T.tabThuLinh) != 0)) {
                MainEvent eventShow = setEventShow(str, b);
                if (eventShow == null) {
                    vecEventShow.addElement(new MainEvent(event.IDObj, event.IDCmd, event.nameEvent, event.contentEvent));
                } else if (b == 0) {
                    eventShow.contentEvent = event.contentEvent;
                }
            }
        }
    }
}
