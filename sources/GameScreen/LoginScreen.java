package GameScreen;

import Build_Indo.IndoServer;
import Build_Usa.Usa_Server;
import CLib.Main;
import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Monstervantieu;
import InterfaceComponents.InputDialog;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabQuest;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Main.GameMidlet;
import Map.BackGround;
import Model.AvMain;
import Model.CRes;
import Model.T;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/LoginScreen.class */
public class LoginScreen extends MainScreen {
    public static final byte LOGIN = 0;
    public static final byte USER_INFO = 1;
    public static final byte SERVER_INFO = 2;
    int hBorder;
    int wBorder;
    int yBorder;
    public static TField tfusername;
    public static TField tfpassword;
    public static mImage logo;
    public static String userSv;
    String[] strhelpregister;
    InputDialog input;
    public static int wLine;
    private iCommand cmdLogin;
    private iCommand cmdThoat;
    private iCommand cmdChooseServer;
    private iCommand cmdQuickPlay;
    private iCommand cmdNewPlay;
    private iCommand cmdPlay;
    private iCommand cmdUserAccount;
    private iCommand cmdOK;
    private iCommand cmdSubMenu;
    private iCommand cmdChangePassword;
    private iCommand cmdGraphicsSetting;
    private iCommand cmdSoundSetting;
    private iCommand cmdNokiaInfo;
    private iCommand cmdHotLine;
    private iCommand cmdCumSv;
    private int yLoginBox;
    private int wBoxText;
    private int hBoxText;
    public static boolean isAutoLogin = true;
    public static boolean isPaintHotLine = true;
    public static byte isServer = 0;
    public static String username = "";
    public static String password = "";
    public static int hShowServer = 0;
    public static byte indexInfoLogin = 1;
    static String[] server = {"anh Trí", "ku Ngân", "Server"};
    public static String ip = null;
    public static int MusicRandom = 0;
    public int hpaint = 0;
    public int section = 0;
    public int hitem = 10;
    int t = 0;
    private boolean isPaintLogo = true;

    public LoginScreen() {
        init();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00d2 A[Catch: Exception -> 0x0145, TryCatch #2 {Exception -> 0x0145, blocks: (B:2:0x0000, B:4:0x0013, B:5:0x0026, B:7:0x0030, B:9:0x003a, B:11:0x004c, B:14:0x0058, B:15:0x005d, B:33:0x0067, B:34:0x0072, B:36:0x007e, B:37:0x008b, B:39:0x0097, B:18:0x00bf, B:20:0x00d2, B:22:0x0103, B:23:0x0107, B:25:0x010d, B:27:0x013e, B:42:0x006e, B:43:0x00aa, B:46:0x00b4, B:49:0x00bb, B:50:0x001e), top: B:1:0x0000, inners: #0, #1, #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010d A[Catch: Exception -> 0x0145, TryCatch #2 {Exception -> 0x0145, blocks: (B:2:0x0000, B:4:0x0013, B:5:0x0026, B:7:0x0030, B:9:0x003a, B:11:0x004c, B:14:0x0058, B:15:0x005d, B:33:0x0067, B:34:0x0072, B:36:0x007e, B:37:0x008b, B:39:0x0097, B:18:0x00bf, B:20:0x00d2, B:22:0x0103, B:23:0x0107, B:25:0x010d, B:27:0x013e, B:42:0x006e, B:43:0x00aa, B:46:0x00b4, B:49:0x00bb, B:50:0x001e), top: B:1:0x0000, inners: #0, #1, #3 }] */
    @Override // GameScreen.MainScreen
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void Show() {
        /*
            Method dump skipped, instructions count: 327
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: GameScreen.LoginScreen.Show():void");
    }

    public void showCumSv2() {
        mVector mvector = new mVector();
        for (int i = 0; i < T.list_sv2.length; i++) {
            mvector.addElement(new iCommand(T.list_sv2[i], 27, i, this));
        }
        GameCanvas.menu2.startAt_Sv(mvector, 2, T.chonsv, false, null);
    }

    public void showCumSv() {
        mVector mvector = new mVector();
        for (int i = 0; i < T.list_sv.length; i++) {
            mvector.addElement(new iCommand(T.list_sv[i], 24, i, this));
        }
        GameCanvas.menu2.startAt_Sv(mvector, 2, T.chonsv, false, null);
    }

    private void login(String str, String str2) {
        if (tfusername.getText().intern() == "doiip" && tfpassword.getText().equals("master")) {
            GameCanvas.linkIP = "http://hiepsionline.com/srvip2/";
            GameCanvas.start_Ok_Dialog("Da doi ip thanh cong");
            return;
        }
        GlobalService.gI().login(str, str2, GameMidlet.version, "0", "0", "0", -1, (byte) -1);
        if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
            GlobalService.gI().send_cmd_server((byte) 61);
        }
    }

    public void init() {
        if (GameCanvas.h < 240) {
            this.hpaint = 10;
        }
        this.yBorder = (GameCanvas.hh - 30) + this.hpaint;
        if (GameCanvas.h - this.yBorder < 160) {
            this.yBorder = (GameCanvas.h - 160) + this.hpaint + 20;
        }
        if (GameCanvas.isTouch) {
            this.wBorder = GameCanvas.hw + 40;
            this.hitem = 10;
            this.yLoginBox = GameCanvas.hh - 50;
        } else {
            this.wBorder = GameCanvas.w / 2;
        }
        wLine = mFont.tahoma_7_yellow.getWidth(T.HotLine);
        if (IndoServer.isIndoSv || Usa_Server.isUsa_server) {
            isPaintHotLine = false;
        }
        if (GameCanvas.langServer[GameCanvas.IndexServer] != 0) {
            isPaintHotLine = false;
        }
        if (this.wBorder < 130) {
            this.wBorder = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN + (GameCanvas.hw / 2);
        }
        if (GameCanvas.isTouch) {
            tfusername = new TField((GameCanvas.hw - (this.wBorder / 2)) + 15, this.yLoginBox + this.hitem + (this.hitem / 2), this.wBorder - 30);
            tfpassword = new TField((GameCanvas.hw - (this.wBorder / 2)) + 15, this.yLoginBox + (this.hitem * 2) + (this.hitem / 2) + tfusername.height, this.wBorder - 30, 40);
        } else {
            tfusername = new TField((GameCanvas.hw - (this.wBorder / 2)) + 15, this.yBorder + this.hitem + (this.hitem / 2), this.wBorder - 30);
            tfpassword = new TField((GameCanvas.hw - (this.wBorder / 2)) + 15, this.yBorder + (this.hitem * 2) + (this.hitem / 2) + tfusername.height, this.wBorder - 30);
        }
        tfusername.setStringNull(T.username);
        tfpassword.setStringNull(T.password);
        tfpassword.setIputType(2);
        if (!GameCanvas.isTouch) {
            tfusername.setFocus(true);
        }
        this.cmdLogin = new iCommand(T.choi_daco_TK, 0);
        this.cmdQuickPlay = new iCommand(T.choimoi, 1);
        this.cmdNewPlay = new iCommand(T.choimoi, 1);
        this.cmdOK = new iCommand("OK", 2);
        this.cmdSubMenu = new iCommand(T.menu, 3);
        this.cmdPlay = new iCommand(T.choi_daco_TK, 4);
        this.cmdUserAccount = new iCommand(T.daco_TK, 5, this);
        this.cmdChooseServer = new iCommand(new StringBuffer().append(T.maychu).append(": ").append(mSystem.listServer[GameCanvas.IndexServer][0]).toString(), 6, this);
        this.cmdChangePassword = new iCommand(T.quenpass, 7, this);
        this.cmdGraphicsSetting = new iCommand(T.cauhinhthap, 8, this);
        this.cmdSoundSetting = new iCommand(T.SetMusic, 9, this);
        this.cmdHotLine = new iCommand(T.HotLine, 23, this);
        if (TemMidlet.currentIAPStore == 1) {
            this.cmdNokiaInfo = new iCommand(T.about, 21, this);
        }
        this.cmdThoat = new iCommand(new StringBuffer().append(T.exit).append(" Game").toString(), -1, this);
        if (GameCanvas.h < 240) {
            this.hpaint = -15;
        }
        this.strhelpregister = mFont.tahoma_7_black.splitFontArray(T.texthelpRegister, this.wBorder - 20);
        setHeightBorder(false);
    }

    public void setCaptionCmd() {
        tfusername.setStringNull(T.username);
        tfpassword.setStringNull(T.password);
        tfusername.cmdClear.caption = T.del;
        tfpassword.cmdClear.caption = T.del;
        this.cmdLogin.caption = T.choi_daco_TK;
        this.cmdQuickPlay.caption = T.choimoi;
        this.cmdNewPlay.caption = T.choimoi;
        this.cmdPlay.caption = T.choi_daco_TK;
        this.cmdOK.caption = "OK";
        if (!GameCanvas.isTouch) {
            this.cmdSubMenu.caption = T.menu;
        }
        this.cmdThoat.caption = T.exit;
        this.cmdChangePassword.caption = T.quenpass;
        this.cmdGraphicsSetting.caption = T.cauhinhthap;
        this.cmdSoundSetting.caption = T.SetMusic;
        setCmdCap();
        if (TemMidlet.currentIAPStore == 1) {
            this.cmdNokiaInfo.caption = T.about;
        }
        this.strhelpregister = mFont.tahoma_7_black.splitFontArray(T.texthelpRegister, this.wBorder - 20);
    }

    public void setPosCmd() {
        int i = ((this.yBorder + this.hBorder) - (iCommand.hButtonCmd / 2)) - this.hitem;
        if (i + (iCommand.hButtonCmd / 2) > GameCanvas.h) {
            i = GameCanvas.h - (iCommand.hButtonCmd / 2);
        }
        int i2 = ((this.yLoginBox + this.hBorder) - (iCommand.hButtonCmd / 2)) - this.hitem;
        if (i2 + (iCommand.hButtonCmd / 2) > GameCanvas.h) {
            i2 = GameCanvas.h - (iCommand.hButtonCmd / 2);
        }
        this.wBoxText = 160;
        this.hBoxText = 25;
        if (GameCanvas.isTouch) {
            this.cmdOK.setPos((GameCanvas.hw - (iCommand.wButtonCmd / 2)) - 5, i2, null, this.cmdOK.caption);
            this.cmdChangePassword.setPos(((tfpassword.x + tfpassword.width) - (AvMain.fraFogetPass.frameWidth / 2)) - 10, i2 - 37, AvMain.fraFogetPass, this.cmdChangePassword.caption);
            this.cmdChangePassword.setFraCaption(AvMain.fraFogetPass, 2, 0);
            this.cmdNewPlay.setPos(GameCanvas.hw + (iCommand.wButtonCmd / 2) + 5, i2, null, this.cmdNewPlay.caption);
            this.cmdPlay.setPos_BoxText(GameCanvas.hw - (this.wBoxText / 2), i - 80, null, this.cmdPlay.caption, this.wBoxText, this.hBoxText);
            this.cmdLogin.setPos_BoxText(GameCanvas.hw - (this.wBoxText / 2), i - 80, null, this.cmdLogin.caption, this.wBoxText, this.hBoxText);
            this.cmdQuickPlay.setPos_BoxText(GameCanvas.hw - (this.wBoxText / 2), i - 80, null, this.cmdQuickPlay.caption, this.wBoxText, this.hBoxText);
            this.cmdUserAccount.setPos_BoxText(GameCanvas.hw - (this.wBoxText / 2), i - 45, null, this.cmdUserAccount.caption, this.wBoxText, this.hBoxText);
            this.cmdChooseServer.setPos_BoxText(GameCanvas.hw - (this.wBoxText / 2), i - 10, null, this.cmdChooseServer.caption, this.wBoxText, this.hBoxText);
            return;
        }
        this.wBoxText = 140;
        this.cmdUserAccount.setPos_BoxText(GameCanvas.hw - (this.wBoxText / 2), i - 60, null, this.cmdUserAccount.caption, this.wBoxText, this.hBoxText);
        this.cmdChooseServer.setPos_BoxText(GameCanvas.hw - (this.wBoxText / 2), i - 20, null, this.cmdChooseServer.caption, this.wBoxText, this.hBoxText);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        BackGround.paint(mgraphics);
        BackGround.paint_StaticCloud(mgraphics);
        if (this.isPaintLogo) {
            if (this.section != 1 && logo != null) {
                if (GameCanvas.isTouch) {
                    mgraphics.drawImage(logo, GameCanvas.hw, GameCanvas.hh - (GameCanvas.hh / 2), 3, false);
                } else {
                    mgraphics.drawImage(logo, GameCanvas.hw, (GameCanvas.hh - 60) - (GameCanvas.hh / 8), 3, false);
                }
            }
            BackGround.paint_CloudOnLogo(mgraphics);
            BackGround.paintLight(mgraphics);
            switch (this.section) {
                case 0:
                    this.cmdUserAccount.paintbutton(mgraphics, this.cmdUserAccount.xCmd, this.cmdUserAccount.yCmd);
                    this.cmdChooseServer.paintbutton(mgraphics, this.cmdChooseServer.xCmd, this.cmdChooseServer.yCmd);
                    this.cmdUserAccount.paintCaptionImage(mgraphics, this.cmdUserAccount.xCmd, this.cmdUserAccount.yCmd - 6, 2);
                    this.cmdChooseServer.paintCaptionImage(mgraphics, this.cmdChooseServer.xCmd, this.cmdChooseServer.yCmd - 6, 2);
                    break;
                case 1:
                    if (GameCanvas.isTouch) {
                        AvMain.paintTabNew(mgraphics, GameCanvas.hw - (this.wBorder / 2), this.yLoginBox - 5, this.wBorder, this.hBorder + 5, true, (byte) 14);
                    } else {
                        AvMain.paintTabNew(mgraphics, GameCanvas.hw - (this.wBorder / 2), this.yBorder - 5, this.wBorder, this.hBorder + 5, true, (byte) 14);
                    }
                    tfusername.paint(mgraphics);
                    tfpassword.paint(mgraphics);
                    this.cmdChangePassword.paintImage(mgraphics, this.cmdChangePassword.xCmd, this.cmdChangePassword.yCmd - 6, 3, 1);
                    break;
            }
        }
        GameCanvas.resetTrans(mgraphics);
        if (!mSystem.isIP_TrucTiep) {
            mFont.tahoma_7_yellow.drawString(mgraphics, new StringBuffer().append("version: ").append(GameMidlet.version).toString(), GameCanvas.w - 2, 2, 1, false);
        } else {
            mFont.tahoma_7_yellow.drawString(mgraphics, new StringBuffer().append("in-house test version: ").append(GameMidlet.version).toString(), GameCanvas.w - 2, 2, 1, false);
        }
        if (PaintInfoGameScreen.paint18plush == 0) {
            if (!mSystem.isIphone) {
                mgraphics.drawImage(AvMain.img18Plus, 0, 0, 0, false);
            }
        } else if (PaintInfoGameScreen.paint18plush == 1) {
            PaintInfoGameScreen.paintinfo18plush(mgraphics);
        }
        if (isPaintHotLine) {
            mFont.tahoma_7_yellow.drawString(mgraphics, T.HotLine, GameCanvas.w, 15, 1, false);
            mgraphics.setColor(16514362);
            mgraphics.fillRect((GameCanvas.w - wLine) + (mSystem.isIphone ? 14 : 0), 25, wLine, 1, false);
        }
        if (mSystem.isIphone || (mSystem.Android && this.cmdCumSv != null)) {
            this.cmdCumSv.paint(mgraphics, 40, GameCanvas.h - 15);
        }
        super.paint(mgraphics);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (hShowServer < 20) {
            hShowServer += 4;
            if (hShowServer > 20) {
                hShowServer = 20;
            }
        }
        if (this.section != 0) {
            tfusername.update();
            tfpassword.update();
        } else {
            setCmdCap();
        }
        if (GameCanvas.menu2.isShowMenu) {
            this.isPaintLogo = false;
        } else {
            this.isPaintLogo = true;
        }
        BackGround.updateSky();
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (GameCanvas.keyMyHold[8]) {
            if (tfusername.isFocused()) {
                tfusername.setFocus(false);
                if (!GameCanvas.isTouch) {
                    tfpassword.setFocus(true);
                }
            } else if (tfpassword.isFocused()) {
                if (!GameCanvas.isTouch) {
                    tfusername.setFocus(true);
                }
                tfpassword.setFocus(false);
            }
            GameCanvas.clearKeyHold(8);
        } else if (GameCanvas.keyMyHold[2]) {
            if (tfusername.isFocused()) {
                tfusername.setFocus(false);
                if (!GameCanvas.isTouch) {
                    tfpassword.setFocus(true);
                }
            } else if (tfpassword.isFocused()) {
                if (!GameCanvas.isTouch) {
                    tfusername.setFocus(true);
                }
                tfpassword.setFocus(false);
            }
            GameCanvas.clearKeyHold(2);
        }
        if (!GameCanvas.isTouch) {
            if (tfusername.isFocused()) {
                this.right = tfusername.setCmdClear();
            } else if (tfpassword.isFocused()) {
                this.right = tfpassword.setCmdClear();
            } else {
                this.right = null;
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (mSystem.isIphone || (mSystem.Android && this.cmdCumSv != null)) {
            this.cmdCumSv.updatePointer();
        }
        if (this.section != 0) {
            tfusername.updatePoiter();
            tfpassword.updatePoiter();
        }
        if (this.section == 1) {
            this.cmdChangePassword.updatePointer();
        } else if (this.section == 0) {
            this.cmdChooseServer.updatePointer();
            this.cmdUserAccount.updatePointer();
        } else if (this.section == 2) {
        }
        super.updatePointer();
    }

    @Override // GameScreen.MainScreen
    public void keyPress(int i) {
        if (tfusername.isFocused()) {
            tfusername.keyPressed(i);
        } else if (tfpassword.isFocused()) {
            tfpassword.keyPressed(i);
        }
        super.keyPress(i);
    }

    public static void saveUser_Pass() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF(tfusername.getText());
            dataOutputStream.writeUTF(tfpassword.getText());
            CRes.saveRMS("user_pass", byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        } catch (Exception e) {
        }
    }

    public static void saveIndexServer() {
        try {
            CRes.saveRMS("isIndexServer", new byte[]{GameCanvas.IndexServer});
            LogoScreen.saveSelectedServer();
        } catch (Exception e) {
        }
    }

    public static void loadUser_Pass() throws IOException {
        byte[] loadRMS = CRes.loadRMS("user_pass");
        if (loadRMS == null) {
            return;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(loadRMS));
        tfusername.setText(dataInputStream.readUTF());
        tfpassword.setText(dataInputStream.readUTF());
        String text = tfusername.getText();
        if (text.length() >= 10) {
            text = text.substring(0, 10);
        }
        if (text.compareTo("knightauto") == 0) {
            username = tfusername.getText();
            password = tfpassword.getText();
            tfusername.setText("");
            tfpassword.setText("");
        }
    }

    public void doMenu() {
        mVector mvector = new mVector();
        if (this.section == 0) {
            if (!GameCanvas.isTouch) {
                mvector.addElement(this.cmdUserAccount);
                mvector.addElement(this.cmdChooseServer);
                mvector.addElement(this.cmdChangePassword);
                mvector.addElement(this.cmdHotLine);
            }
            if (GameCanvas.lowGraphic) {
                this.cmdGraphicsSetting.caption = new StringBuffer().append(T.off).append(T.cauhinhthap).toString();
            } else {
                this.cmdGraphicsSetting.caption = new StringBuffer().append(T.on).append(T.cauhinhthap).toString();
            }
            mvector.addElement(this.cmdGraphicsSetting);
            if (TemMidlet.currentIAPStore == 1) {
                mvector.addElement(this.cmdNokiaInfo);
            }
        } else if (this.section != 1 && this.section != 2) {
        }
        mvector.addElement(this.cmdThoat);
        GameCanvas.menu2.startAt(mvector, 2, T.menuChinh, false, null);
    }

    public void setScreen() {
        switch (this.section) {
            case 0:
                if (GameCanvas.h < 240) {
                    this.hpaint = 10;
                }
                tfusername.setFocus(Main.isPC);
                tfpassword.setFocus(false);
                if (!GameCanvas.isTouch) {
                    this.left = this.cmdSubMenu;
                } else {
                    this.left = null;
                }
                if (tfusername.getText().length() > 0) {
                    this.center = this.cmdLogin;
                    return;
                } else if (username.length() > 0) {
                    this.center = this.cmdPlay;
                    return;
                } else {
                    this.center = this.cmdQuickPlay;
                    return;
                }
            case 1:
                if (!GameCanvas.isTouch) {
                    tfusername.setFocus(true);
                    tfpassword.setFocus(false);
                }
                this.left = this.cmdNewPlay;
                this.center = this.cmdOK;
                setHeightBorder(false);
                if (GameCanvas.h < 240) {
                    this.hpaint = 10;
                }
                hShowServer = 0;
                return;
            case 2:
                this.left = null;
                this.center = null;
                return;
            default:
                return;
        }
    }

    public void setHeightBorder(boolean z) {
        this.hBorder = (tfusername.height * 2) + (this.hitem * 4);
        if (GameCanvas.isTouch) {
            this.hBorder += iCommand.hButtonCmd + 3;
        } else {
            this.hBorder += GameCanvas.hh / 8;
        }
        if (z) {
            this.hBorder += this.strhelpregister.length * GameCanvas.hText;
        }
        setPosCmd();
    }

    protected void doRegister() {
        if (tfusername.getText().equals("")) {
            GameCanvas.start_Ok_Dialog(T.chuanhapsdt);
            return;
        }
        if (tfpassword.getText().equals("")) {
            GameCanvas.start_Ok_Dialog(T.chuanhapmk);
            return;
        }
        if (tfusername.getText().length() < 5) {
            GameCanvas.start_Ok_Dialog(T.nameMin6char);
            return;
        }
        String str = null;
        try {
            Long.parseLong(tfusername.getText());
            if (tfusername.getText().length() < 8 || tfusername.getText().length() > 12 || (!tfusername.getText().startsWith("0") && tfusername.getText().startsWith("84"))) {
                str = T.sdtkhople;
            }
        } catch (Exception e) {
            if (tfusername.getText().indexOf("@") == -1 || tfusername.getText().indexOf(".") == -1) {
                str = T.emailkhople;
            }
        }
        if (str != null) {
            GameCanvas.start_Ok_Dialog(str);
        }
    }

    @Override // GameScreen.MainScreen
    public void keyBack() {
        this.cmdThoat.perform();
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case 0:
                GameCanvas.connect();
                login(tfusername.getText(), tfpassword.getText());
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                return;
            case 1:
                GameCanvas.connect();
                login("1", "1");
                return;
            case 2:
                this.section = 0;
                setScreen();
                return;
            case 3:
                doMenu();
                return;
            case 4:
                GameCanvas.connect();
                login(username, password);
                return;
            default:
                return;
        }
    }

    @Override // Model.AvMain
    public void commandMenu(int i, int i2) {
        super.commandMenu(i, i2);
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        String str;
        switch (i) {
            case -1:
                GameCanvas.start_Left_Dialog(T.hoithoat, new iCommand(T.exit, 10, this));
                break;
            case 5:
                this.section = 1;
                setScreen();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                break;
            case 6:
                GameCanvas.listIp = new List_Ip_Server_Screen();
                GameCanvas.listIp.Show();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                break;
            case 7:
                if (IndoServer.isIndoSv) {
                    str = "http://ksatriaonline.indonaga.com/forum/app/index.php?for=event&do=changepass";
                } else if (GameCanvas.IndexServer == 2) {
                    str = "http://forum.knightageonline.com/app/index.php?for=event&do=resetpass&lang=en";
                } else if (Usa_Server.isUsa_server) {
                    str = "http://knightandmagic.com/forum/app/index.php?for=event&do=resetpass";
                } else {
                    str = "http://forum.knightageonline.com/app/index.php?for=event&do=resetpass&lang=store";
                }
                GameCanvas.start_Download_Dialog(T.lienhe, str);
                break;
            case 8:
                GameCanvas.lowGraphic = !GameCanvas.lowGraphic;
                try {
                    CRes.saveRMS("isLowDevice", new byte[]{(byte) (GameCanvas.lowGraphic ? 1 : 0)});
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            case 9:
                GameCanvas.start_Volume_Dialog();
                break;
            case 10:
                GameMidlet.destroy();
                break;
            case 21:
                GameCanvas.start_Left_Dialog(new StringBuffer().append(T.textabout1).append(GameMidlet.version).append("\n").append(T.textabout2).toString(), new iCommand(T.nokiaprivacy, 22, this));
                break;
            case 22:
                TemMidlet.openUrl("http://teamobi.com/terms.htm");
                break;
            case 23:
                TemMidlet.instance.call(T.numberHotLine);
                break;
            case 24:
                if (i2 == 0) {
                    mSystem.listServer = mSystem.listServer_VN;
                    GameCanvas.t = new T();
                    IndoServer.isIndoSv = false;
                    Usa_Server.isUsa_server = false;
                    GameCanvas.IndexServer = (byte) 5;
                    mSystem.ID_REGION = 0;
                }
                if (i2 == 1) {
                    IndoServer.isIndoSv = true;
                    Usa_Server.isUsa_server = false;
                    IndoServer.setLinkIp();
                    mSystem.ID_REGION = 1;
                    GameCanvas.IndexServer = (byte) 1;
                }
                if (i2 == 2) {
                    Usa_Server.isUsa_server = true;
                    IndoServer.isIndoSv = false;
                    Usa_Server.setLinkIp();
                    mSystem.ID_REGION = 2;
                    GameCanvas.IndexServer = (byte) 0;
                }
                CRes.saveID_REGION();
                LogoScreen.setChangeLang();
                setCmdCap();
                this.cmdCumSv.caption = T.chonsv;
                break;
            case 25:
                showCumSv();
                break;
            case 26:
                showCumSv2();
                break;
            case 27:
                if (i2 == 0) {
                    mSystem.listServer = mSystem.listServer_VN;
                    GameCanvas.t = new T();
                    IndoServer.isIndoSv = false;
                    Usa_Server.isUsa_server = false;
                    GameCanvas.IndexServer = (byte) 4;
                    mSystem.ID_REGION = 0;
                }
                if (i2 == 1) {
                    Usa_Server.isUsa_server = true;
                    IndoServer.isIndoSv = false;
                    Usa_Server.setLinkIp();
                    mSystem.ID_REGION = 1;
                    GameCanvas.IndexServer = (byte) 0;
                }
                CRes.saveID_REGION();
                LogoScreen.setChangeLang();
                setCmdCap();
                this.cmdCumSv.caption = T.chonsv;
                break;
        }
        super.commandPointer(i, i2);
    }

    private void setCmdCap() {
        String str;
        if (mSystem.isIphone && mSystem.listServer.length - 1 < GameCanvas.IndexServer) {
            return;
        }
        if (this.cmdChooseServer.caption != new StringBuffer().append(T.maychu).append(": ").append(mSystem.listServer[GameCanvas.IndexServer][0]).toString()) {
            this.cmdChooseServer.caption = new StringBuffer().append(T.maychu).append(": ").append(mSystem.listServer[GameCanvas.IndexServer][0]).toString();
        }
        if (GameCanvas.isTouch) {
            if (tfusername.getText().length() > 0) {
                if (this.cmdUserAccount.caption != T.doi_TK_khac) {
                    this.cmdUserAccount.caption = T.doi_TK_khac;
                }
                if (tfusername.getText().length() > 13) {
                    if (!tfusername.getText().equals(userSv)) {
                        str = new StringBuffer().append(T.choi_daco_TK).append(": ").append(tfusername.getText().substring(0, 12)).append("...").toString();
                    } else {
                        str = T.choimoi;
                    }
                    if (this.cmdLogin.caption != str) {
                        this.cmdLogin.caption = str;
                    }
                    if (this.cmdPlay.caption != str) {
                        this.cmdPlay.caption = str;
                        return;
                    }
                    return;
                }
                if (this.cmdLogin.caption != new StringBuffer().append(T.choi_daco_TK).append(": ").append(tfusername.getText()).toString()) {
                    this.cmdLogin.caption = new StringBuffer().append(T.choi_daco_TK).append(": ").append(tfusername.getText()).toString();
                    this.cmdPlay.caption = new StringBuffer().append(T.choi_daco_TK).append(": ").append(tfusername.getText()).toString();
                    return;
                }
                return;
            }
            if (username.length() > 0) {
                if (this.cmdUserAccount.caption != T.doi_TK_khac) {
                    this.cmdUserAccount.caption = T.doi_TK_khac;
                    return;
                }
                return;
            }
            if (this.cmdUserAccount.caption != T.daco_TK) {
                this.cmdUserAccount.caption = T.daco_TK;
                return;
            }
            return;
        }
        if (tfusername.getText().length() > 0) {
            if (tfusername.getText().length() > 8) {
                String stringBuffer = new StringBuffer().append(T.username).append(": ").append(tfusername.getText().substring(0, 8)).append("...").toString();
                if (this.cmdUserAccount.caption != stringBuffer) {
                    this.cmdUserAccount.caption = stringBuffer;
                    return;
                }
                return;
            }
            if (this.cmdUserAccount.caption != new StringBuffer().append(T.username).append(": ").append(tfusername.getText()).toString()) {
                this.cmdUserAccount.caption = new StringBuffer().append(T.username).append(": ").append(tfusername.getText()).toString();
                return;
            }
            return;
        }
        if (username.length() > 0) {
            if (this.cmdUserAccount.caption != T.doi_TK_khac) {
                this.cmdUserAccount.caption = T.doi_TK_khac;
                return;
            }
            return;
        }
        if (this.cmdUserAccount.caption != T.daco_TK) {
            this.cmdUserAccount.caption = T.daco_TK;
        }
    }

    public void checkLoginAgain(byte b) {
        try {
            username = tfusername.getText();
            password = tfpassword.getText();
            if (username.length() > 0) {
                this.center = this.cmdPlay;
            } else {
                this.center = this.cmdQuickPlay;
            }
            byte[] loadRMS = CRes.loadRMS("isIndexServer");
            byte b2 = b;
            if (loadRMS != null) {
                b2 = loadRMS[0];
            }
            if (b2 == b && CRes.loadRMS("user_pass") != null) {
                try {
                    loadUser_Pass();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GameCanvas.connect();
                if (username.length() > 0) {
                    login(username, password);
                } else if (tfusername.getText().length() > 0) {
                    login(tfusername.getText(), tfpassword.getText());
                }
            }
        } catch (Exception e2) {
        }
    }
}
