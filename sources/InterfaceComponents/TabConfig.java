package InterfaceComponents;

import Build_Usa.Usa_Server;
import CLib.Main;
import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.MainRMS;
import GameObjects.Monstervantieu;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Main.GameMidlet;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Thread_More.MiniMapFull_Screen;
import com.silverknight.TemMidlet;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabConfig.class */
public class TabConfig extends MainTabNew {
    mVector vecConfig;
    int w2;
    int wFocus;
    int idSelect;
    int hItem;
    iCommand cmdSelect;
    iCommand cmdlogout;
    iCommand cmdSetAuto;
    iCommand cmdHelp;
    iCommand cmdSetting;
    iCommand cmdChucNang;
    iCommand cmdShowFullMini;
    iCommand cmdChatWorld;
    iCommand cmdDiamond;
    iCommand cmdSoundSetting;
    iCommand cmdVongquay;
    iCommand cmdkhac;
    iCommand cmdHieuUng;
    iCommand cmdtaitho;
    public static iCommand cmdEvent;
    public static iCommand cmdKeypad;
    public static iCommand cmdShowClan;
    public static iCommand cmdXuongNgua;
    public static iCommand cmdChangeScreen;
    MainList list;
    InputDialog inputWorld;
    String textWorld = "";

    public TabConfig(String str, mVector mvector, byte b) {
        this.vecConfig = new mVector("TabConfig vecConfig");
        this.typeTab = b;
        this.nameTab = str;
        this.vecConfig = mvector;
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        this.w2 = MainTabNew.wblack / 2;
        this.wFocus = MainTabNew.wblack - (MainTabNew.wOne5 * 2);
        if (this.wFocus > 130) {
            this.wFocus = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
        }
        this.hItem = GameCanvas.hCommand;
        if (GameCanvas.isTouch) {
            this.hItem = 28;
        }
        this.cmdBack = new iCommand(T.back, -1, this);
        this.cmdSelect = new iCommand(T.select, 0, this);
        this.cmdlogout = new iCommand(T.logout, 4, this);
        cmdKeypad = new iCommand(T.chuyensang, 7, this);
        this.cmdHelp = new iCommand(T.help, 10, this);
        this.cmdSetting = new iCommand(T.auto, 11, this);
        this.cmdChucNang = new iCommand(T.chucnang, 12, this);
        cmdEvent = new iCommand(T.mevent, 13, this);
        cmdShowClan = new iCommand(T.clan, 14, this);
        this.cmdShowFullMini = new iCommand(T.minimap, 15, this);
        this.cmdChatWorld = new iCommand(T.textkenhthegioi, 16, this);
        this.cmdDiamond = new iCommand(new StringBuffer().append(T.naptien).append(" ").append(T.gem).toString(), 19, this);
        cmdXuongNgua = new iCommand(new StringBuffer().append(T.textXuongNgua).append("").toString(), 37, this);
        if (mSound.isEnableSound) {
            this.cmdSoundSetting = new iCommand(T.SetMusic, 36, this);
        }
        this.cmdVongquay = new iCommand(T.annguoichoi, 40, this);
        this.cmdkhac = new iCommand(T.khac, 45, this);
        this.cmdHieuUng = new iCommand(T.tathieuung, 46, this);
        this.cmdtaitho = new iCommand(T.taitho, 47, this);
        if (PaintInfoGameScreen.taitho == 0) {
            this.cmdtaitho.caption = T.taitho;
        } else {
            this.cmdtaitho.caption = T.antaitho;
        }
        init();
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        mVector mvector = new mVector("TabConfig mcmdTest");
        if (GameCanvas.isTouch) {
            if (GameScreen.help.Step >= 0) {
                mvector.addElement(GameScreen.gI().cmdEndHelp);
            }
            mvector.addElement(this.cmdHelp);
            if (GameScreen.player.myClan != null) {
                mvector.addElement(cmdShowClan);
            }
            if (Player.party != null) {
                mvector.addElement(GameScreen.gI().cmdParty);
            }
            mvector.addElement(this.cmdChatWorld);
            if (mSound.isEnableSound) {
                mvector.addElement(this.cmdSoundSetting);
            }
            if (Main.isPC) {
                mvector.addElement(cmdChangeScreen);
            }
            if (GameScreen.player.typeMount != -1) {
                mvector.addElement(cmdXuongNgua);
            }
        } else if (this.typeTab == 7) {
            String stringBuffer = new StringBuffer().append(T.off).append(" ").append(T.dosat).toString();
            if (GameScreen.player.typePk != 0) {
                stringBuffer = new StringBuffer().append(T.on).append(" ").append(T.dosat).toString();
            }
            GameScreen.gI().cmdSetDoSat.caption = stringBuffer;
            mvector.addElement(GameScreen.gI().cmdSetPk);
            mvector.addElement(GameScreen.gI().cmdSetDoSat);
            mvector.addElement(this.cmdSetting);
            if (GameScreen.help.Step >= 0) {
                mvector.addElement(GameScreen.gI().cmdEndHelp);
            }
            mvector.addElement(this.cmdHelp);
            if (GameScreen.player.Lv >= 10) {
                mvector.addElement(this.cmdDiamond);
            }
            mvector.addElement(this.cmdlogout);
        } else if (this.typeTab == 11) {
            mvector.addElement(GameScreen.gI().cmdListFriend);
            cmdEvent.caption = T.mevent;
            if (PaintInfoGameScreen.numMess > 0) {
                cmdEvent.caption = new StringBuffer().append(T.mevent).append("*").toString();
            }
            mvector.addElement(cmdEvent);
            if (GameScreen.player.myClan != null) {
                mvector.addElement(cmdShowClan);
            }
            if (Player.party != null) {
                mvector.addElement(GameScreen.gI().cmdParty);
            }
            mvector.addElement(GameScreen.gI().cmdChangeMap);
            mvector.addElement(this.cmdShowFullMini);
            mvector.addElement(this.cmdChatWorld);
            if (GameScreen.player.typeMount != -1) {
                mvector.addElement(cmdXuongNgua);
            }
        }
        mvector.addElement(this.cmdVongquay);
        mvector.addElement(this.cmdkhac);
        mvector.addElement(this.cmdHieuUng);
        if (!mSystem.isj2me) {
            mvector.addElement(this.cmdtaitho);
        }
        this.vecConfig = mvector;
        int size = (this.vecConfig.size() * this.hItem) - MainTabNew.hblack;
        if (size < 0) {
            size = 0;
        }
        this.idSelect = 0;
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            this.left = this.cmdSelect;
        } else {
            this.idSelect = -1;
        }
        this.list = new MainList(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack, this.hItem, this.vecConfig.size(), size, this.idSelect, this.vecConfig);
        MainScreen.cameraSub.setAll(0, size, 0, 0);
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
        iCommand icommand;
        switch (i) {
            case -1:
                backTab();
                return;
            case 0:
                if (this.idSelect > -1 && (icommand = (iCommand) this.vecConfig.elementAt(this.idSelect)) != null) {
                    if (icommand.action != null) {
                        icommand.action.perform();
                        return;
                    } else if (icommand.Pointer != null) {
                        icommand.Pointer.commandPointer(icommand.indexMenu, icommand.subIndex);
                        return;
                    } else {
                        GameCanvas.currentScreen.commandMenu(icommand.indexMenu, icommand.subIndex);
                        return;
                    }
                }
                return;
            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 12:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            default:
                return;
            case 4:
                GameScreen.player.resetAction();
                Session_ME.gI().close();
                GameCanvas.login.Show();
                GameScreen.player = new Player(0, (byte) 0, "unname", 0, 0);
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isMelogin = false;
                GlobalLogicHandler.isDisConect = false;
                MsgDialog.isAutologin = false;
                return;
            case 6:
                GameMidlet.destroy();
                return;
            case 7:
                PaintInfoGameScreen.isLevelPoint = !PaintInfoGameScreen.isLevelPoint;
                MainRMS.setSaveTouch();
                if (PaintInfoGameScreen.isLevelPoint) {
                    cmdKeypad.caption = new StringBuffer().append(T.chuyensang).append(T.keypad).toString();
                } else {
                    cmdKeypad.caption = new StringBuffer().append(T.chuyensang).append(T.touch).toString();
                }
                GameCanvas.isPointerSelect = false;
                PaintInfoGameScreen.setPosTouch();
                return;
            case 9:
                GlobalService.gI().set_Pk((byte) i2);
                return;
            case 10:
                GameCanvas.start_Show_Dialog(T.strhelp, T.help);
                return;
            case 11:
                GameScreen.gI().doMenuAuto();
                return;
            case 13:
                GameCanvas.mevent.init();
                GameCanvas.mevent.Show(GameCanvas.currentScreen);
                return;
            case 14:
                if (GameScreen.player.myClan != null) {
                    GlobalService.gI().ChucNang_Clan((byte) 15, GameScreen.player.myClan.IdClan);
                    GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                    return;
                }
                return;
            case 15:
                MiniMapFull_Screen.gI().Show();
                return;
            case 16:
                this.inputWorld = new InputDialog();
                this.inputWorld.setinfo(T.nhapnoidung, new iCommand(T.chat, 17, this), false, T.textkenhthegioi);
                GameCanvas.currentDialog = this.inputWorld;
                return;
            case 17:
                this.textWorld = this.inputWorld.tfInput.getText();
                if (this.textWorld != null && this.textWorld.length() > 0) {
                    GameCanvas.start_Left_Dialog(new StringBuffer().append(T.kenhthegioi).append(" (").append(T.phi).append((int) TabShopNew.PriceChatWorld).append(" ").append(T.gem).append(")").append(T.noidungnhusau).append("\n").append(this.textWorld).toString(), new iCommand(T.chat, 18, this));
                    return;
                }
                return;
            case 18:
                GlobalService.gI().Chat_World(this.textWorld);
                GameCanvas.end_Dialog();
                return;
            case 19:
                if (mSystem.isIpAppstore()) {
                    mVector mvector = new mVector("Menu2 vec2");
                    for (int i3 = 0; i3 < TemMidlet.google_listGems.length; i3++) {
                        iCommand icommand2 = new iCommand(TemMidlet.google_listGems[i3], 20, i3, this);
                        icommand2.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mvector.addElement(icommand2);
                    }
                    GameCanvas.menu2.startAt(mvector, 2, T.naptien, false, null);
                    return;
                }
                if (TemMidlet.currentIAPStore == 1) {
                    mVector mvector2 = new mVector("TabConfig vec");
                    for (int i4 = 0; i4 < TemMidlet.listGems.length; i4++) {
                        iCommand icommand3 = new iCommand(TemMidlet.listGems[i4], 20, i4, this);
                        icommand3.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mvector2.addElement(icommand3);
                    }
                    GameCanvas.menu2.startAt(mvector2, 2, T.naptien, false, null);
                    return;
                }
                if (TemMidlet.currentIAPStore == 2) {
                    if (Usa_Server.isUsa_server) {
                        if (GameCanvas.naptien > 0) {
                            mVector mvector3 = new mVector("Menu2 vec2");
                            for (int i5 = 0; i5 < TemMidlet.google_listGems.length; i5++) {
                                iCommand icommand4 = new iCommand(TemMidlet.google_listGems[i5], 20, i5, this);
                                icommand4.setFraCaption(AvMain.fraDiamond, 1, 1);
                                mvector3.addElement(icommand4);
                            }
                            GameCanvas.menu2.startAt(mvector3, 2, T.naptien, false, null);
                            return;
                        }
                        if (GameCanvas.naptien <= 0) {
                            GameCanvas.start_Ok_Dialog(T.hetlannap);
                            return;
                        }
                    }
                    mVector mvector4 = new mVector("TabConfig vec2");
                    for (int i6 = 0; i6 < TemMidlet.google_listGems.length; i6++) {
                        iCommand icommand5 = new iCommand(TemMidlet.google_listGems[i6], 20, i6, this);
                        icommand5.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mvector4.addElement(icommand5);
                    }
                    GameCanvas.menu2.startAt(mvector4, 2, T.naptien, false, null);
                    return;
                }
                GlobalService.gI().send_cmd_server((byte) -56);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                return;
            case 20:
                if (mSystem.isIpAppstore()) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[i2]);
                    return;
                }
                if (TemMidlet.currentIAPStore == 1) {
                    if (TemMidlet.isBlockNOKIAStore) {
                        GameCanvas.start_Ok_Dialog("Comming soon !");
                        return;
                    } else {
                        if (!TemMidlet.isBlockNOKIAStore) {
                            GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                            TemMidlet.makePurchase(TemMidlet.productIds[0]);
                            return;
                        }
                        return;
                    }
                }
                if (TemMidlet.currentIAPStore == 2) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[i2]);
                    return;
                }
                return;
            case 36:
                GameCanvas.start_Volume_Dialog();
                return;
            case 37:
                mVector mvector5 = new mVector("TabConfig vec3");
                mvector5.addElement(new iCommand(T.yes, 38, this));
                mvector5.addElement(new iCommand(T.cancel, 39, this));
                GameCanvas.start_Select_Dialog(T.textHoiXuongNgua, mvector5);
                return;
            case 38:
                GlobalService.gI().useMount((byte) -1);
                closeDialog();
                return;
            case 39:
                closeDialog();
                return;
            case 40:
                if (!mSystem.isj2me) {
                    if (!GameScreen.isHideOderPlayer) {
                        mVector mvector6 = new mVector();
                        iCommand icommand6 = new iCommand(T.yes, 41, this);
                        iCommand icommand7 = new iCommand(T.no, 42, this);
                        mvector6.addElement(icommand6);
                        mvector6.addElement(icommand7);
                        GameCanvas.start_Select_Dialog(T.hoiannguoichoi, mvector6);
                        return;
                    }
                    mVector mvector7 = new mVector();
                    iCommand icommand8 = new iCommand(T.yes, 43, this);
                    iCommand icommand9 = new iCommand(T.no, 42, this);
                    mvector7.addElement(icommand8);
                    mvector7.addElement(icommand9);
                    GameCanvas.start_Select_Dialog(T.hoihienguoichoi, mvector7);
                    return;
                }
                if (!GameScreen.isHideOderPlayer && !GameScreen.isHideFullOderPlayer) {
                    mVector mvector8 = new mVector();
                    iCommand icommand10 = new iCommand(T.hide, 41, this);
                    iCommand icommand11 = new iCommand(T.hidefull, 44, this);
                    mvector8.addElement(icommand10);
                    mvector8.addElement(icommand11);
                    GameCanvas.menu2.startAt(mvector8, 2, T.annguoichoi, false, null);
                    return;
                }
                mVector mvector9 = new mVector();
                iCommand icommand12 = new iCommand(T.yes, 43, this);
                iCommand icommand13 = new iCommand(T.no, 42, this);
                mvector9.addElement(icommand12);
                mvector9.addElement(icommand13);
                GameCanvas.start_Select_Dialog(T.hoihienguoichoi, mvector9);
                return;
            case 41:
                GameScreen.isHideOderPlayer = true;
                this.cmdVongquay.caption = T.hiennguoichoi;
                GameCanvas.end_Dialog();
                return;
            case 42:
                GameCanvas.end_Dialog();
                return;
            case 43:
                GameScreen.isHideOderPlayer = false;
                GameScreen.isHideFullOderPlayer = false;
                this.cmdVongquay.caption = T.annguoichoi;
                GameCanvas.end_Dialog();
                return;
            case 44:
                GameScreen.isHideFullOderPlayer = true;
                this.cmdVongquay.caption = T.hiennguoichoi;
                GameCanvas.end_Dialog();
                return;
            case 45:
                GlobalService.gI().request_LotteryItems((byte) 5, (byte) 0);
                return;
            case 46:
                if (MainObject.hideEff == 0) {
                    MainObject.hideEff = (byte) 1;
                    this.cmdHieuUng.caption = T.bathieuung;
                    return;
                } else {
                    MainObject.hideEff = (byte) 0;
                    this.cmdHieuUng.caption = T.tathieuung;
                    return;
                }
            case 47:
                if (PaintInfoGameScreen.taitho == 0) {
                    PaintInfoGameScreen.taitho = (byte) 1;
                    this.cmdtaitho.caption = T.antaitho;
                    PaintInfoGameScreen.xPointMove = 85;
                    PaintInfoGameScreen.mPosOther[1][0] = 38;
                } else {
                    PaintInfoGameScreen.taitho = (byte) 0;
                    this.cmdtaitho.caption = T.taitho;
                    PaintInfoGameScreen.xPointMove = 55;
                    PaintInfoGameScreen.mPosOther[1][0] = 8;
                }
                PaintInfoGameScreen.reloadmove();
                CRes.save_TaiTho();
                return;
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
    public void paint(mGraphics mgraphics) {
        mgraphics.setClip(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(this.xBegin, this.yBegin, MainTabNew.wblack, MainTabNew.hblack);
        mgraphics.translate(0, -this.list.cmx);
        if (MainTabNew.Focus == 1 && this.idSelect > -1) {
            paintFocus(mgraphics);
        }
        for (int i = 0; i < this.vecConfig.size(); i++) {
            mFont.tahoma_7b_white.drawString(mgraphics, ((iCommand) this.vecConfig.elementAt(i)).caption, this.xBegin + this.w2, ((this.yBegin + (this.hItem / 2)) + (i * this.hItem)) - 6, 2, true);
            if (i < this.vecConfig.size() - 1) {
                mgraphics.setColor(MainTabNew.color[4]);
                mgraphics.fillRect(this.xBegin + 8, (this.yBegin + ((i + 1) * this.hItem)) - 1, MainTabNew.wblack - 16, 1, true);
            }
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
    }

    public void paintFocus(mGraphics mgraphics) {
        mgraphics.setColor(MainTabNew.color[5]);
        mgraphics.fillRect(((this.xBegin + this.w2) - (this.wFocus / 2)) - 1, ((this.yBegin + (this.idSelect * this.hItem)) + (this.hItem / 2)) - 11, this.wFocus + 2, 22, false);
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mgraphics, (this.xBegin + this.w2) - (this.wFocus / 2), ((this.yBegin + (this.idSelect * this.hItem)) + (this.hItem / 2)) - 10, this.wFocus, 20, 4);
            return;
        }
        for (int i = 0; i <= this.wFocus / 24; i++) {
            int i2 = ((this.xBegin + this.w2) - (this.wFocus / 2)) + (24 * i);
            if (i == this.wFocus / 24) {
                i2 = ((this.xBegin + this.w2) + (this.wFocus / 2)) - 24;
            }
            mgraphics.drawRegion(MainTabNew.imgTab[8], 0, 0, 24, 20, 0, i2, ((this.yBegin + (this.idSelect * this.hItem)) + (this.hItem / 2)) - 10, 0, false);
        }
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        MainScreen.cameraSub.UpdateCamera();
        if (MainTabNew.Focus != 1 || GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null || GameCanvas.currentScreen != GameCanvas.AllInfo) {
            return;
        }
        this.list.updateMenu();
        this.idSelect = this.list.value;
        if (this.idSelect >= this.vecConfig.size()) {
            this.idSelect = -1;
            this.list.value = -1;
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (MainTabNew.Focus == 1 && (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6])) {
            MainTabNew.Focus = (byte) 0;
            this.idSelect = 0;
            GameCanvas.clearKeyHold(4);
            GameCanvas.clearKeyHold(6);
        }
        super.updatekey();
    }
}
