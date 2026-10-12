package GameScreen;

import CLib.Session_ME;
import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.Other_Players;
import GameObjects.Player;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Map.BackGround;
import Model.AvMain;
import Model.CRes;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/CreateChar.class */
public class CreateChar extends MainScreen {
    int wRectChar;
    int hRectChar;
    TField tfNameChar;
    public static mVector VecDefaultChar = new mVector("CreateChar VecDefaultchar");
    public static final int SELECT_CLASS = 0;
    int frame;
    int timeChangeClass;
    int fFocus;
    byte[] mselect;
    iCommand cmdclass;
    iCommand cmdtoc;
    iCommand cmdmat;
    iCommand cmddau;
    iCommand cmdBack;
    int direction;
    int selectClass = 0;
    int indexChar = 0;
    byte[][] mCreateChar = mSystem.new_M_Byte(4, 3);
    byte[][] mEye = {new byte[]{8, 9}, new byte[]{10, 11}};
    byte[][] mHair = {new byte[]{0, 1}, new byte[]{2, 3}};
    int numHair = 2;
    int numEye = 2;
    byte index = 0;
    mVector vecCmd = new mVector("CreateChar veccmd");

    public void Show(byte b) {
        mSound.stopSoundAll();
        if (LoginScreen.MusicRandom == 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        } else {
            mSound.playMus(1, mSound.volumeMusic, true);
        }
        this.index = b;
        super.Show();
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r1v9, types: [byte[], byte[][]] */
    public CreateChar() {
        this.wRectChar = 180;
        this.hRectChar = 160;
        if (GameCanvas.isTouch) {
            this.hRectChar = 182;
            this.wRectChar = 200;
        }
        init();
    }

    public void init() {
        VecDefaultChar.removeAllElements();
        this.center = new iCommand(T.create, 0);
        this.cmdBack = new iCommand(T.back, 1);
        if (GameCanvas.isTouch) {
            int i = iCommand.wButtonCmd / 2;
            if (i < this.wRectChar / 4) {
                i = this.wRectChar / 4;
            }
            this.center.setPos(GameCanvas.hw - i, GameCanvas.hh + (this.hRectChar / 2) + 4, null, this.center.caption);
            this.cmdBack.setPos(GameCanvas.hw + i, GameCanvas.hh + (this.hRectChar / 2) + 4, null, this.cmdBack.caption);
        }
        this.left = this.cmdBack;
        for (int i2 = 0; i2 < 4; i2++) {
            Other_Players other_Players = new Other_Players(i2, (byte) 0, "", 0, 0);
            other_Players.clazz = (byte) i2;
            other_Players.leg = i2;
            other_Players.body = i2;
            other_Players.hat = -1;
            other_Players.head = 0;
            other_Players.hair = i2;
            other_Players.eye = 8 + i2;
            other_Players.EyeMain = other_Players.eye;
            other_Players.Direction = 0;
            other_Players.wing = -1;
            other_Players.x = (this.wRectChar / 5) + ((i2 * this.wRectChar) / 4);
            other_Players.y = (this.hRectChar / 5) * 2;
            other_Players.weaponType = 0;
            VecDefaultChar.addElement(other_Players);
        }
        this.mselect = new byte[4];
        this.indexChar = CRes.random(4);
        this.selectClass = 0;
        MainScreen.cameraSub.setAll((VecDefaultChar.size() * this.wRectChar) / 4, 0, (this.indexChar * this.wRectChar) / 4, 0);
        this.tfNameChar = new TField(((GameCanvas.hw - (this.wRectChar / 2)) + (this.wRectChar / 5)) - 25, (GameCanvas.hh - (this.hRectChar / 2)) + (this.hRectChar / 2) + 10, 60);
        if (!GameCanvas.isTouch) {
            this.tfNameChar.setFocus(true);
            return;
        }
        this.cmdclass = new iCommand(T.mClass[this.indexChar], 0, this);
        this.vecCmd.addElement(this.cmdclass);
        Other_Players other_Players2 = (Other_Players) VecDefaultChar.elementAt(this.indexChar);
        this.cmdtoc = new iCommand(T.mCreateChar_HAIR[this.indexChar / 2][other_Players2.hair % this.numHair], 1, this);
        this.vecCmd.addElement(this.cmdtoc);
        int i3 = other_Players2.eye;
        if (i3 < 8) {
            i3 = other_Players2.EyeMain;
        }
        this.cmdmat = new iCommand(T.mCreateChar_EYE_FACE[0][i3 - 8], 2, this);
        this.vecCmd.addElement(this.cmdmat);
        this.cmddau = new iCommand(T.mCreateChar_EYE_FACE[1][other_Players2.head], 3, this);
        this.vecCmd.addElement(this.cmddau);
        int i4 = GameCanvas.hw - (this.wRectChar / 2);
        int i5 = (GameCanvas.hh - (this.hRectChar / 2)) - (GameCanvas.hCommand / 2);
        for (int i6 = 0; i6 < this.vecCmd.size(); i6++) {
            iCommand icommand = (iCommand) this.vecCmd.elementAt(i6);
            icommand.setPos(i4 + ((this.wRectChar / 3) * 2), i5 + ((this.hRectChar / 5) * (i6 + 1)) + 5, PaintInfoGameScreen.fraButton2, icommand.caption);
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                this.selectClass = 0;
                this.indexChar++;
                this.timeChangeClass = 0;
                if (this.indexChar == 4) {
                    MainScreen.cameraSub.xCam = (-this.wRectChar) / 4;
                }
                this.indexChar = resetSelect(this.indexChar, T.mClass.length - 1, true);
                MainScreen.cameraSub.xTo = (this.indexChar * this.wRectChar) / 4;
                this.cmdclass.caption = T.mClass[this.indexChar];
                this.cmdtoc.caption = T.mCreateChar_HAIR[this.indexChar / 2][((Other_Players) VecDefaultChar.elementAt(this.indexChar)).hair % this.numHair];
                return;
            case 1:
            case 2:
            case 3:
                this.selectClass = i;
                editChar(1);
                return;
            default:
                return;
        }
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case 0:
                String text = this.tfNameChar.getText();
                if (text.length() < 6) {
                    GameCanvas.start_Ok_Dialog(T.nameMin6char);
                    break;
                } else {
                    Other_Players other_Players = (Other_Players) VecDefaultChar.elementAt(this.indexChar);
                    GlobalService.gI().create_char(other_Players.clazz, text, (byte) other_Players.hair, (byte) other_Players.EyeMain, (byte) other_Players.head, this.index);
                    GameCanvas.start_Wait_Dialog(T.pleaseWait, null);
                    break;
                }
            case 1:
                if (SelectCharScreen.VecSelectChar.size() == 0) {
                    GameCanvas.login.Show();
                    Session_ME.gI().close();
                    GameScreen.player = new Player(0, (byte) 0, "unname", 0, 0);
                    break;
                } else {
                    GameCanvas.selectChar.Show();
                    break;
                }
        }
        super.commandTab(i, i2);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        BackGround.paint(mgraphics);
        BackGround.paintLight(mgraphics);
        AvMain.paintTabNew(mgraphics, GameCanvas.hw - (this.wRectChar / 2), (GameCanvas.hh - (this.hRectChar / 2)) - (GameCanvas.hCommand / 2), this.wRectChar, this.hRectChar, true, (byte) 0);
        mgraphics.translate(GameCanvas.hw - (this.wRectChar / 2), (GameCanvas.hh - (this.hRectChar / 2)) - (GameCanvas.hCommand / 2));
        Other_Players other_Players = (Other_Players) VecDefaultChar.elementAt(this.indexChar);
        for (int i = 0; i < T.textCreateChar.length; i++) {
            mFont.tahoma_7b_black.drawString(mgraphics, T.textCreateChar[i], (this.wRectChar / 3) * 2, ((this.hRectChar / 5) * i) + (this.hRectChar / 10), 2, false);
        }
        if (!GameCanvas.isTouch) {
            AvMain.FontBorderColor(mgraphics, T.mClass[this.indexChar], (this.wRectChar / 3) * 2, this.hRectChar / 5, 2, 2);
            AvMain.FontBorderColor(mgraphics, T.mCreateChar_HAIR[this.indexChar / 2][other_Players.hair % this.numHair], (this.wRectChar / 3) * 2, (this.hRectChar / 5) * 2, 2, 2);
            int i2 = other_Players.eye;
            if (i2 < 8) {
                i2 = other_Players.EyeMain;
            }
            AvMain.FontBorderColor(mgraphics, T.mCreateChar_EYE_FACE[0][i2 - 8], (this.wRectChar / 3) * 2, (this.hRectChar / 5) * 3, 2, 2);
            AvMain.FontBorderColor(mgraphics, T.mCreateChar_EYE_FACE[1][other_Players.head], (this.wRectChar / 3) * 2, (this.hRectChar / 5) * 4, 2, 2);
        }
        if (!GameCanvas.isTouch) {
            mgraphics.drawRegion(MainObject.focus, 0, 0, 11, 9, 5, (((this.wRectChar / 3) * 2) - 35) - ((this.fFocus / 2) % 4), ((this.hRectChar / 5) * (this.selectClass + 1)) + 4, 3, false);
            mgraphics.drawRegion(MainObject.focus, 0, 0, 11, 9, 6, ((this.wRectChar / 3) * 2) + 35 + ((this.fFocus / 2) % 4), ((this.hRectChar / 5) * (this.selectClass + 1)) + 4, 3, false);
        }
        mFont.tahoma_7_black.drawString(mgraphics, T.namePlayer, (this.wRectChar / 5) + 4, (this.hRectChar / 2) + 5, 2, false);
        mgraphics.setClip(8, 2, ((this.wRectChar / 5) * 2) - 12, this.hRectChar - 4);
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.saveCanvas();
        mgraphics.translateAndroid(GameCanvas.hw - (this.wRectChar / 2), (GameCanvas.hh - (this.hRectChar / 2)) - (GameCanvas.hCommand / 2));
        mgraphics.ClipRec(8, 2, ((this.wRectChar / 5) * 2) - 12, this.hRectChar - 4);
        mgraphics.translate(-MainScreen.cameraSub.xCam, 0);
        for (int i3 = 0; i3 < VecDefaultChar.size(); i3++) {
            Other_Players other_Players2 = (Other_Players) VecDefaultChar.elementAt(i3);
            if (i3 == 0) {
                other_Players2.paintShowPlayer(mgraphics, other_Players2.x + ((this.wRectChar / 4) * VecDefaultChar.size()), other_Players2.y, other_Players2.Direction);
            }
            if (i3 == VecDefaultChar.size() - 1) {
                other_Players2.paintShowPlayer(mgraphics, other_Players2.x - ((this.wRectChar / 4) * VecDefaultChar.size()), other_Players2.y, other_Players2.Direction);
            }
            other_Players2.paintPlayer(mgraphics, -1);
            if (other_Players2.Action == 2) {
                other_Players2.PlashNow.update(other_Players2);
            } else if (other_Players2.Action == 1) {
                other_Players2.f++;
                if (other_Players2.f > other_Players2.A_Move.length - 1) {
                    other_Players2.f = 0;
                }
                other_Players2.frame = other_Players2.A_Move[other_Players2.f];
            } else {
                other_Players2.updateActionPerson();
            }
            other_Players2.updateEye();
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        GameCanvas.resetTrans(mgraphics);
        for (int i4 = 0; i4 < this.vecCmd.size(); i4++) {
            iCommand icommand = (iCommand) this.vecCmd.elementAt(i4);
            icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
        }
        this.tfNameChar.paint(mgraphics);
        mgraphics.endClip();
        if (PaintInfoGameScreen.paint18plush == 0) {
            if (!mSystem.isIphone) {
                mgraphics.drawImage(AvMain.img18Plus, 0, 0, 0, false);
            }
        } else if (PaintInfoGameScreen.paint18plush == 1) {
            PaintInfoGameScreen.paintinfo18plush(mgraphics);
        }
        if (LoginScreen.isPaintHotLine) {
            mFont.tahoma_7_yellow.drawString(mgraphics, T.HotLine, GameCanvas.w, 0, 1, false);
            mgraphics.setColor(16514362);
            mgraphics.fillRect((GameCanvas.w - LoginScreen.wLine) + (mSystem.isIphone ? 14 : 0), 10, LoginScreen.wLine, 1, false);
        }
        super.paint(mgraphics);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (LoginScreen.hShowServer < 20) {
            LoginScreen.hShowServer += 4;
            if (LoginScreen.hShowServer > 20) {
                LoginScreen.hShowServer = 20;
            }
        }
        if (this.timeChangeClass == 0) {
            ((Other_Players) VecDefaultChar.elementAt(this.indexChar)).Direction = 0;
        } else if (this.timeChangeClass == 30 || this.timeChangeClass == 10) {
            Other_Players other_Players = (Other_Players) VecDefaultChar.elementAt(this.indexChar);
            other_Players.Action = 2;
            other_Players.f = 0;
            other_Players.beginFire();
            other_Players.PlashNow.setPlash(other_Players.clazz);
        } else if (this.timeChangeClass == 50) {
            Other_Players other_Players2 = (Other_Players) VecDefaultChar.elementAt(this.indexChar);
            other_Players2.Action = 1;
            other_Players2.f = 0;
        } else if (this.timeChangeClass > 80) {
            Other_Players other_Players3 = (Other_Players) VecDefaultChar.elementAt(this.indexChar);
            this.timeChangeClass = 1;
            other_Players3.Action = 0;
            other_Players3.f = 0;
            int i = other_Players3.Direction + 1;
            other_Players3.Direction = i;
            other_Players3.Direction = i % 4;
        }
        this.timeChangeClass++;
        this.tfNameChar.update();
        this.fFocus++;
        MainScreen.cameraSub.UpdateCameraCreate();
        MainScreen.cameraSub.UpdateCameraCreate();
        BackGround.updateSky();
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (GameCanvas.keyMyHold[4]) {
            if (this.selectClass == 0) {
                this.indexChar--;
                this.timeChangeClass = 0;
            } else {
                editChar(-1);
            }
            GameCanvas.clearKeyHold(4);
            this.tfNameChar.caretPos = this.tfNameChar.getText().length() + 1;
        } else if (GameCanvas.keyMyHold[6]) {
            if (this.selectClass == 0) {
                this.indexChar++;
                this.timeChangeClass = 0;
            } else {
                editChar(1);
            }
            GameCanvas.clearKeyHold(6);
            this.tfNameChar.caretPos = this.tfNameChar.getText().length() + 1;
        } else if (GameCanvas.keyMyHold[2]) {
            this.selectClass--;
            GameCanvas.clearKeyHold(2);
        } else if (GameCanvas.keyMyHold[8]) {
            this.selectClass++;
            GameCanvas.clearKeyHold(8);
        }
        this.selectClass = resetSelect(this.selectClass, T.textCreateChar.length - 1, true);
        if (this.indexChar == 4) {
            MainScreen.cameraSub.xCam = (-this.wRectChar) / 4;
        } else if (this.indexChar == -1) {
            MainScreen.cameraSub.xCam = (4 * this.wRectChar) / 4;
        }
        this.indexChar = resetSelect(this.indexChar, T.mClass.length - 1, true);
        MainScreen.cameraSub.xTo = (this.indexChar * this.wRectChar) / 4;
        if (this.tfNameChar.isFocused()) {
            this.right = this.tfNameChar.setCmdClear();
        } else {
            this.right = null;
        }
        if (GameCanvas.keyMyPressed[3]) {
            this.direction = (this.direction + 1) % 4;
        }
        super.updatekey();
    }

    public void editChar(int i) {
        Other_Players other_Players = (Other_Players) VecDefaultChar.elementAt(this.indexChar);
        switch (this.selectClass) {
            case 1:
                other_Players.hair += i;
                other_Players.hair = ((this.indexChar / this.numHair) * this.numHair) + resetSelect(other_Players.hair - ((this.indexChar / this.numHair) * this.numHair), this.numHair - 1, true);
                if (GameCanvas.isTouch) {
                    this.cmdtoc.caption = T.mCreateChar_HAIR[this.indexChar / 2][other_Players.hair % this.numHair];
                    return;
                }
                return;
            case 2:
                other_Players.eye += i;
                other_Players.eye = 8 + ((this.indexChar / this.numEye) * this.numEye) + resetSelect((other_Players.eye - 8) - ((this.indexChar / this.numEye) * this.numEye), this.numEye - 1, true);
                other_Players.EyeMain = other_Players.eye;
                if (GameCanvas.isTouch) {
                    int i2 = other_Players.eye;
                    if (i2 < 8) {
                        i2 = other_Players.EyeMain;
                    }
                    this.cmdmat.caption = T.mCreateChar_EYE_FACE[0][i2 - 8];
                    return;
                }
                return;
            case 3:
                other_Players.head += i;
                other_Players.head = resetSelect(other_Players.head, T.mCreateChar_EYE_FACE[1].length - 1, true);
                if (GameCanvas.isTouch) {
                    this.cmddau.caption = T.mCreateChar_EYE_FACE[1][other_Players.head];
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // GameScreen.MainScreen
    public void keyPress(int i) {
        if (this.tfNameChar.isFocused()) {
            this.tfNameChar.keyPressed(i);
        }
        super.keyPress(i);
    }

    @Override // Model.AvMain
    public void updatePointer() {
        this.tfNameChar.updatePoiter();
        for (int i = 0; i < this.vecCmd.size(); i++) {
            ((iCommand) this.vecCmd.elementAt(i)).updatePointer();
        }
        super.updatePointer();
    }

    @Override // GameScreen.MainScreen
    public void keyBack() {
        this.cmdBack.perform();
    }
}
