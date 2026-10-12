package GameScreen;

import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainClan;
import GameObjects.MainRMS;
import GameObjects.ObjectData;
import GameObjects.Other_Players;
import GameObjects.Player;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Map.BackGround;
import Model.AvMain;
import Model.MainImage;
import Model.T;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/SelectCharScreen.class */
public class SelectCharScreen extends MainScreen {
    int selectChar;
    int frame;
    iCommand cmdExit;
    public static mVector VecSelectChar = new mVector("PaintInfoGameScr VecSelectChar");
    public static int IDCHAR = -1;
    public static boolean isSelectOk = false;
    public static boolean reSelect = false;
    public static boolean Canselect = false;
    int wRectChar = 50;
    int hRectChar = 80;
    int[][] mShadow = mSystem.new_M_Int(3, 2);
    boolean isSelect = false;
    boolean isSend = false;
    int[] mplash = new int[0];
    int direction = 0;
    public byte[] frameicon = {0, 1, 2, 1};
    public byte frameClan = 0;
    Other_Players objSelect = null;
    int timeSelect = 0;
    int wCenter = (GameCanvas.w - (this.wRectChar * 3)) / 4;

    public SelectCharScreen() {
        init();
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        mSound.stopSoundAll();
        if (LoginScreen.MusicRandom == 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        } else {
            mSound.playMus(1, mSound.volumeMusic, true);
        }
        this.timeSelect = 0;
        this.objSelect = null;
        this.isSelect = false;
        this.isSend = false;
        Player.isNewPlayer = false;
        GameScreen.Vecplayers.removeAllElements();
        super.Show();
        isSelectOk = false;
        GameCanvas.saveImage.start();
        GameScreen.infoGame.setNameServer(mSystem.listServer[GameCanvas.IndexServer][0]);
        GameCanvas.countLogin = 0L;
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case 0:
                doSelect();
                break;
            case 1:
                GameCanvas.login.Show();
                Session_ME.gI().close();
                GameScreen.player = new Player(0, (byte) 0, "unname", 0, 0);
                break;
        }
        super.commandTab(i, i2);
    }

    public void init() {
        if (!GameCanvas.isTouch) {
            this.center = new iCommand(T.select, 0);
        }
        this.cmdExit = new iCommand(T.exit, 1);
        if (GameCanvas.isTouch) {
            this.cmdExit.setPos(PaintInfoGameScreen.fraBack.frameWidth / 2, GameCanvas.h - (PaintInfoGameScreen.fraBack.frameHeight / 2), PaintInfoGameScreen.fraBack, this.cmdExit.caption);
        }
        this.left = this.cmdExit;
    }

    public void setCaptionCmd() {
        this.cmdExit.caption = T.exit;
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        BackGround.paint(mgraphics);
        if (LoginScreen.logo != null) {
            mgraphics.drawImage(LoginScreen.logo, GameCanvas.hw, GameCanvas.hh - 60, 3, false);
        }
        BackGround.paint_CloudOnLogo(mgraphics);
        GameScreen.infoGame.paintNameServer(mgraphics, mSystem.listServer[GameCanvas.IndexServer][0]);
        BackGround.paint_FloatingPlatform(mgraphics);
        BackGround.paint_StaticCloud(mgraphics);
        for (int i = 0; i < 3; i++) {
            if (i < VecSelectChar.size()) {
                Other_Players other_Players = (Other_Players) VecSelectChar.elementAt(i);
                other_Players.paintPlayer(mgraphics, -1);
                mFont.tahoma_7b_black.drawString(mgraphics, new StringBuffer().append(T.level).append((int) other_Players.Lv).toString(), other_Players.x, (other_Players.y - other_Players.hOne) - 10, 2, false);
                paintName(mgraphics, other_Players.name, other_Players.x, other_Players.y - other_Players.hOne, i, other_Players.myClan);
            } else {
                paintName(mgraphics, T.createChar, this.mShadow[i][0], this.mShadow[i][1], i, null);
            }
        }
        BackGround.paintLight(mgraphics);
        super.paint(mgraphics);
        if (reSelect) {
            mgraphics.setColor(0);
            mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
            if (LoginScreen.logo != null) {
                mgraphics.drawImage(LoginScreen.logo, GameCanvas.hw, GameCanvas.hh - 16, 3, false);
                mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, GameCanvas.hw, (GameCanvas.hh + (mImage.getImageHeight(LoginScreen.logo.image) / 2)) - 9, 3, false);
            }
        }
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
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        GameScreen.infoGame.updateInfoChar();
        if (LoginScreen.hShowServer < 20) {
            LoginScreen.hShowServer += 4;
            if (LoginScreen.hShowServer > 20) {
                LoginScreen.hShowServer = 20;
            }
        }
        if (this.timeSelect > 0) {
            this.timeSelect++;
            if (this.timeSelect == 18) {
                this.objSelect.f = 0;
                this.objSelect.Action = 1;
            }
        }
        if (!GameCanvas.isTouch) {
            if (this.selectChar > VecSelectChar.size() - 1) {
                this.center.caption = T.create;
            } else {
                this.center.caption = T.select;
            }
        }
        if (MsgDialog.isAutologin) {
            autoSelect();
        }
        for (int i = 0; i < VecSelectChar.size(); i++) {
            Other_Players other_Players = (Other_Players) VecSelectChar.elementAt(i);
            if (other_Players.Action == 2) {
                other_Players.PlashNow.update(other_Players);
            } else if (other_Players.Action == 1) {
                other_Players.f++;
                if (other_Players.f > other_Players.A_Move.length - 1) {
                    other_Players.f = 0;
                }
                other_Players.frame = other_Players.A_Move[other_Players.f];
                if (this.isSelect && !this.isSend) {
                    Other_Players other_Players2 = (Other_Players) VecSelectChar.elementAt(this.selectChar);
                    MainRMS.isLoadShowAuto = true;
                    IDCHAR = other_Players2.ID;
                    GlobalService.gI().select_char((byte) 0, IDCHAR);
                    LoadMapScreen.isNextMap = false;
                    GameCanvas.load.Show();
                    this.isSend = true;
                    GameScreen.help.Step = -1;
                    GlobalService.gI().Save_RMS_Server((byte) 1, (byte) 2, null);
                    GlobalService.gI().Save_RMS_Server((byte) 1, (byte) 1, null);
                    reSelect = false;
                    GlobalLogicHandler.timeReconnect = 0L;
                    GlobalLogicHandler.isMelogin = false;
                    GlobalLogicHandler.isDisConect = false;
                    MsgDialog.isAutologin = false;
                }
                other_Players.y += other_Players.vy;
            } else {
                other_Players.updateActionPerson();
            }
            other_Players.updateEye();
        }
        BackGround.updateSky();
        if (reSelect && Canselect) {
            doSelect();
            Canselect = false;
        }
    }

    public void autoSelect() {
        Other_Players other_Players = (Other_Players) VecSelectChar.elementAt(this.selectChar);
        MainRMS.isLoadShowAuto = true;
        IDCHAR = other_Players.ID;
        GlobalService.gI().select_char((byte) 0, IDCHAR);
        LoadMapScreen.isNextMap = false;
        GameCanvas.load.Show();
        this.isSend = true;
        GameScreen.help.Step = -1;
        GlobalService.gI().Save_RMS_Server((byte) 1, (byte) 2, null);
        GlobalService.gI().Save_RMS_Server((byte) 1, (byte) 1, null);
        reSelect = false;
        MsgDialog.isAutologin = false;
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (!reSelect && !this.isSelect) {
            if (GameCanvas.keyMyHold[4]) {
                this.selectChar--;
                GameCanvas.clearKeyHold();
            } else if (GameCanvas.keyMyHold[6]) {
                this.selectChar++;
                GameCanvas.clearKeyHold();
            }
            this.selectChar = resetSelect(this.selectChar, 2, true);
            super.updatekey();
        }
    }

    public void setPosPaint(mVector mvector) {
        VecSelectChar.removeAllElements();
        if (mvector != null) {
            VecSelectChar = mvector;
        }
        for (int i = 0; i < 3; i++) {
            int i2 = (GameCanvas.hw - 80) + (i * 80);
            int i3 = (GameCanvas.h - 60) - ((i % 2) * 25);
            if (i < VecSelectChar.size()) {
                Other_Players other_Players = (Other_Players) VecSelectChar.elementAt(i);
                int i4 = (GameCanvas.hw - 80) + (i * 80);
                int i5 = (GameCanvas.h - 60) - ((i % 2) * 25);
                other_Players.x = i4;
                other_Players.y = i5;
            }
            this.mShadow[i][0] = i2;
            this.mShadow[i][1] = i3;
        }
    }

    private void paintName(mGraphics mgraphics, String str, int i, int i2, int i3, MainClan mainClan) {
        mFont.tahoma_7b_black.drawString(mgraphics, str, i - 1, i2 - 25, 2, false);
        mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 - 25, 2, false);
        mFont.tahoma_7b_black.drawString(mgraphics, str, i - 1, i2 - 24, 2, false);
        mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 - 24, 2, false);
        mFont.tahoma_7b_black.drawString(mgraphics, str, i - 1, i2 - 23, 2, false);
        mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 - 23, 2, false);
        mFont.tahoma_7b_black.drawString(mgraphics, str, i, i2 - 25, 2, false);
        mFont.tahoma_7b_black.drawString(mgraphics, str, i, i2 - 23, 2, false);
        mFont.tahoma_7b_white.drawString(mgraphics, str, i, i2 - 24, 2, false);
        if (mainClan != null) {
            MainImage imageIconClan = ObjectData.getImageIconClan(mainClan.IdIcon);
            if (imageIconClan.img != null) {
                int width = (mFont.tahoma_7b_white.getWidth(mainClan.shortName) + 11) / 2;
                if (imageIconClan.img != null) {
                    if (mImage.getImageHeight(imageIconClan.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length = this.frameicon.length;
                            if (length == 0) {
                                length = 1;
                            }
                            this.frameClan = (byte) ((this.frameClan + 1) % length);
                        }
                        mgraphics.drawRegion(imageIconClan.img, 0, this.frameicon[this.frameClan] * 18, 18, 18, 0, (i - width) + 6, i2 - 32, 3, true);
                    } else {
                        mgraphics.drawImage(imageIconClan.img, (i - width) + 6, i2 - 32, 3, false);
                    }
                    Item.eff_UpLv.paintUpgradeEffect((i - width) + 6, (i2 - 32) - 1, mainClan.getEffChucVu(), 14, mgraphics, 0);
                }
                mFont.tahoma_7b_white.drawString(mgraphics, mainClan.shortName, (i - width) + 15, (i2 - 32) - 6, 0, false);
                i2 -= 18;
            }
        }
        if (i3 == this.selectChar && !this.isSelect && !GameCanvas.isTouch) {
            mgraphics.drawImage(AvMain.imgSelect, i, (i2 - 35) + (GameCanvas.gameTick % 5), 3, false);
        }
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (reSelect) {
            return;
        }
        if (GameCanvas.isPointerSelect && !this.isSelect) {
            int i = 0;
            while (true) {
                if (i >= this.mShadow.length) {
                    break;
                }
                if (!GameCanvas.isPoint(this.mShadow[i][0] - 5, this.mShadow[i][1] - 65, 50, 90)) {
                    i++;
                } else {
                    this.selectChar = i;
                    doSelect();
                    this.selectChar = resetSelect(this.selectChar, 2, true);
                    GameCanvas.isPointerSelect = false;
                    break;
                }
            }
        }
        super.updatePointer();
    }

    public void doSelect() {
        if (this.selectChar > VecSelectChar.size() - 1) {
            mSound.playSound(41, mSound.volumeSound);
            GameCanvas.createChar = new CreateChar();
            GameCanvas.createChar.Show((byte) this.selectChar);
            return;
        }
        this.isSelect = true;
        this.timeSelect = 1;
        this.objSelect = (Other_Players) VecSelectChar.elementAt(this.selectChar);
        this.objSelect.Action = 2;
        this.objSelect.f = 0;
        this.objSelect.beginFire();
        this.objSelect.PlashNow.setPlash(this.objSelect.clazz == 3 ? 16 : 1);
        this.objSelect.Direction = 0;
        if (this.objSelect.clazz == 2 || this.objSelect.clazz == 3) {
            mSound.playSound(16, mSound.volumeSound);
        } else {
            mSound.playSound(6, mSound.volumeSound);
        }
    }

    @Override // GameScreen.MainScreen
    public void keyBack() {
        this.cmdExit.perform();
    }
}
