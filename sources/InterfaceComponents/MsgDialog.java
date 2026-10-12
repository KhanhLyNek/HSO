package InterfaceComponents;

import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.AutoGetItem;
import GameObjects.Item;
import GameObjects.MainHelp;
import GameObjects.MainItem;
import GameObjects.MainQuest;
import GameObjects.MainRMS;
import GameObjects.ObjectParty;
import GameObjects.PetItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.SelectCharScreen;
import GameScreen.TabScreenNew;
import GameScreen.WorldMapScreen;
import Main.GameCanvas;
import Main.GameMidlet;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.T;
import Model.mCamera;
import Skill.HotKey;
import Skill.Skill;
import Thread_More.LoadMap;
import com.silverknight.TemMidlet;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import netcommand.global.GlobalLogicHandler;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/MsgDialog.class */
public class MsgDialog extends MainDialog {
    MsgDialog LastDia;
    String status;
    String nameShow;
    int wStatus;
    public static int maxSizeParty;
    public int maxSizeList;
    int IdQuest;
    int typeQuest;
    int idSelect;
    int idSelectBuy;
    int idCommand;
    int sizeParty;
    byte main_sub_quest;
    public static final byte NORMAL = 0;
    public static final byte QUEST = 1;
    public static final byte PARTY = 2;
    public static final byte LIST_PARTY = 3;
    public static final byte AUTO_HP_MP = 4;
    public static final byte HELP = 5;
    public static final byte SHOW = 6;
    public static final byte AUTO_GET_ITEM = 7;
    public static final byte AUTO_BUFF = 8;
    public static final byte DIA_TIME = 9;
    public static final byte SHOW_CHANGE_ITEM = 10;
    public static final byte SHOW_OPEN_BOX = 11;
    public static final byte UPDATE_DATA = 12;
    public static final byte SET_VOLUME = 13;
    public static final byte PET_INFO = 14;
    public static final byte Info_arena = 15;
    iCommand cmdchucnang;
    iCommand cmdHelp;
    iCommand cmdPet;
    public static FrameImage fraWaiting;
    ListNew list;
    int hWait;
    MainQuest quest;
    int min;
    int max;
    int hbutton;
    public static int[][] Autobuff;
    byte isLottery;
    MainItem[] datanguyenlieu;
    MainItem sanpham;
    int timeShow;
    int[][] posItemNguyenlieu;
    MainItem[] itemsanpham;
    int wsizeBox;
    public static final byte OPEN_BOX = 0;
    public static final byte BOX_QUEST = 1;
    public static int maxupdate;
    public static int curupdate;
    public static int wUpdate;
    public static int hUpdate;
    public static PetItem pet;
    public static final byte INVEN = 0;
    public static final byte EQUIP = 1;
    int xBegin;
    int w2cmd;
    public static byte MaxSkillBuff = 0;
    public static int hPlus = 52;
    public static int timePaintParty = 0;
    public static int isUutien = 0;
    public static int[] mHPMP = {50, 50};
    public static byte[] mvalueVolume = null;
    public static mVector vecListEvent = new mVector("MsgDiaLog vecListEvent");
    public static byte isInven_Equip = 0;
    public static boolean isAutologin = false;
    String link = "";
    int fWait = 0;
    boolean isWaiting = false;
    boolean isSpec = false;
    mVector cmdList = new mVector("MsgChat cmdList");
    iCommand cmdClose = new iCommand(T.close, -1, this);
    iCommand cmdCancleQuest = new iCommand(T.cancel, 10, this);
    mCamera cameraDia = new mCamera();
    int hItem = 0;
    int hSpe = 0;
    int archor = -1;
    byte[] mvalueItem = new byte[3];
    int wbuff = 0;
    mFont fontDia = mFont.tahoma_7_white;
    int sizeButtonQuest = 1;
    short timeDia = 0;
    long timeset = 0;
    int StepShow = 0;
    int indexShow1 = 0;
    int indexShow2 = 0;
    byte isFullEff = 0;
    boolean isTran = false;
    int yCamBegin = 0;

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        GameCanvas.clearKeyHold();
        GameCanvas.clearKeyPressed();
        switch (i) {
            case -1:
            case 7:
                closeDialog();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isDisConect = false;
                GlobalLogicHandler.isMelogin = false;
                isAutologin = false;
                return;
            case 0:
                if (GameCanvas.currentScreen != GameCanvas.login && GameCanvas.currentScreen != GameCanvas.load) {
                    if (SelectCharScreen.isSelectOk) {
                        GameCanvas.login.Show();
                        if (CRes.loadRMS("user_pass") != null) {
                            try {
                                LoginScreen.loadUser_Pass();
                            } catch (Exception e) {
                            }
                            GameCanvas.connect();
                            GlobalService.gI().login(LoginScreen.tfusername.getText(), LoginScreen.tfpassword.getText(), GameMidlet.version, "0", "0", "0", SelectCharScreen.IDCHAR, LoadMap.Area);
                            GameScreen.player.resetPlayer();
                            if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
                                GlobalService.gI().send_cmd_server((byte) 61);
                            }
                            closeDialog();
                        } else {
                            GameCanvas.login.Show();
                            closeDialog();
                        }
                    }
                } else {
                    if (GameCanvas.currentScreen != GameCanvas.login) {
                        GameCanvas.login.Show();
                    }
                    closeDialog();
                }
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isDisConect = false;
                GlobalLogicHandler.isMelogin = false;
                isAutologin = false;
                return;
            case 1:
                closeDialog();
                return;
            case 2:
                GlobalService.gI().quest((short) this.IdQuest, this.main_sub_quest, (byte) this.typeQuest);
                closeDialog();
                return;
            case 3:
                GameCanvas.worldmap.Show(GameCanvas.game);
                closeDialog();
                return;
            case 4:
                setMenuParty();
                return;
            case 5:
                Player.isAutoHPMP = true;
                Player.mhotkey[Player.levelTab][4].type = HotKey.NULL;
                Player.mhotkey[Player.levelTab][3].type = HotKey.NULL;
                MainItem.setAddHotKey((byte) 1, isUutien != 0);
                MainItem.setAddHotKey((byte) 0, isUutien != 0);
                MainRMS.setSaveAuto();
                TabSkillsNew.saveSkill();
                closeDialog();
                return;
            case 6:
                GameScreen.player.resetPlayer();
                GameCanvas.login.Show();
                closeDialog();
                GlobalLogicHandler.timeReconnect = 0L;
                GlobalLogicHandler.isDisConect = false;
                GlobalLogicHandler.isMelogin = false;
                isAutologin = false;
                return;
            case 8:
                if (Player.party == null) {
                    return;
                }
                if (GameScreen.player.name.compareTo(Player.party.nameMain) == 0) {
                    setChucNangParty();
                    return;
                }
                mVector mvector = new mVector("MsgChat menu");
                mvector.addElement(new iCommand(T.leave, 8, this));
                mvector.addElement(new iCommand(T.chatParty, 15, this));
                GameCanvas.menu2.startAt(mvector, 2, T.chucnang, false, null);
                return;
            case 9:
                byte b = -1;
                if (this.mvalueItem[0] < ((byte) (T.mValueAutoItem[0].length - 1))) {
                    b = this.mvalueItem[0];
                }
                Player.autoItem = new AutoGetItem(b, this.mvalueItem[1], this.mvalueItem[2]);
                MainRMS.setSaveAuto();
                closeDialog();
                return;
            case 10:
                if (this.idSelect >= 0 && this.idSelect < MaxSkillBuff) {
                    setAutoBuff(this.idSelect);
                    return;
                }
                return;
            case 11:
                setMusic();
                closeDialog();
                return;
            case 12:
            case 13:
            case 14:
            default:
                closeDialog();
                return;
            case 15:
                closeDialog();
                GlobalService.gI().arena((byte) i);
                return;
            case 16:
                closeDialog();
                GameScreen.player.resetAction();
                Session_ME.gI().close();
                GameCanvas.login.Show();
                GameScreen.player = new Player(0, (byte) 0, "unname", 0, 0);
                SelectCharScreen.reSelect = false;
                SelectCharScreen.Canselect = false;
                return;
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        GameCanvas.clearKeyHold();
        GameCanvas.clearKeyPressed();
        switch (i) {
            case -1:
                closeDialog();
                return;
            case 0:
            case 1:
            case 4:
            case 5:
            case 13:
            default:
                return;
            case 2:
                if (Player.party != null && this.idSelect >= 0 && this.idSelect < Player.party.vecPartys.size()) {
                    GameCanvas.start_Left_Dialog(new StringBuffer().append(T.hoivaonhom).append(((ObjectParty) Player.party.vecPartys.elementAt(this.idSelect)).name).append("?").toString(), new iCommand(T.gianhap, 0, this));
                    return;
                }
                return;
            case 3:
                if (Player.party != null && this.idSelect >= 0 && this.idSelect < Player.party.vecPartys.size()) {
                    GameCanvas.start_Left_Dialog(T.hoilapnhom, new iCommand(T.gianhap, 1, this));
                    return;
                }
                return;
            case 6:
                if (Player.party == null) {
                    return;
                }
                GlobalService.gI().Party((byte) 3, ((ObjectParty) Player.party.vecPartys.elementAt(this.idSelect)).name);
                return;
            case 7:
                closeDialog();
                GlobalService.gI().Party((byte) 4, "");
                return;
            case 8:
                closeDialog();
                GlobalService.gI().Party((byte) 5, "");
                return;
            case 9:
                if (Player.party == null) {
                    return;
                }
                GlobalService.gI().Friend((byte) 0, ((ObjectParty) Player.party.vecPartys.elementAt(this.idSelect)).name);
                return;
            case 10:
                if (this.quest == null) {
                    return;
                }
                GameCanvas.start_Left_Dialog(new StringBuffer().append(T.hoihuyQuest).append(this.quest.name).toString(), new iCommand(T.cancel, 11, this));
                return;
            case 11:
                if (this.quest == null) {
                    return;
                }
                GlobalService.gI().quest((short) this.quest.ID, (byte) (this.quest.isMain ? 0 : 1), (byte) 2);
                TabQuest.me.resetTab(true);
                if (!GameScreen.help.setStep_Next(9, 0)) {
                    closeDialog();
                    closeDialog();
                    return;
                }
                return;
            case 12:
                if (this.link.length() > 0) {
                    TemMidlet.openUrl(this.link);
                    return;
                }
                return;
            case 14:
                if (Player.party == null) {
                    return;
                }
                GameCanvas.msgchat.addNewChat(((ObjectParty) Player.party.vecPartys.elementAt(this.idSelect)).name, "", "", (byte) 0, true);
                GameCanvas.start_Chat_Dialog();
                return;
            case 15:
                GameCanvas.msgchat.addNewChat(T.party, "", "", (byte) 0, true);
                GameCanvas.start_Chat_Dialog();
                return;
            case 16:
                mVector mvector = new mVector("MsgChat vec");
                TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, (byte) 0, T.choan, -1, TabShopNew.INVEN_FOOD_PET);
                tabShopNew.petCur = pet;
                mvector.addElement(tabShopNew);
                GameCanvas.foodPet = new TabScreenNew();
                GameCanvas.foodPet.selectTab = 0;
                GameCanvas.foodPet.addMoreTab(mvector);
                GameCanvas.foodPet.Show(GameCanvas.currentScreen);
                return;
        }
    }

    public void beginDia() {
        GameScreen.player.resetVx_vy();
        this.left = null;
        this.right = null;
        this.center = null;
        this.cmdList.removeAllElements();
    }

    public void setinfo(String str, iCommand icommand, boolean z) {
        beginDia();
        if (icommand == null) {
            GameCanvas.currentDialog = null;
        }
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 0;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist2");
        this.cmdList.addElement(icommand);
        if (!z) {
            this.cmdList.addElement(this.cmdClose);
        }
        int size = this.cmdList.size();
        if (this.wDia < (size * iCommand.wButtonCmd) + ((size - 1) * 10) + 10) {
            this.wDia = (size * iCommand.wButtonCmd) + ((size - 1) * 10) + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        this.strinfo = this.fontDia.splitFontArray(str, this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + hPlus;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(0);
    }

    public void setinfoDownload(String str, String str2, boolean z) {
        beginDia();
        this.link = str2;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 0;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist3");
        this.cmdList.addElement(new iCommand(T.yes, 12, this));
        this.cmdList.addElement(this.cmdClose);
        int size = this.cmdList.size();
        if (this.wDia < (size * iCommand.wButtonCmd) + ((size - 1) * 10) + 10) {
            this.wDia = (size * iCommand.wButtonCmd) + ((size - 1) * 10) + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        this.strinfo = this.fontDia.splitFontArray(str, this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + hPlus;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(0);
    }

    public void setinfoWait(String str, iCommand icommand) {
        beginDia();
        this.isWaiting = true;
        this.isSpec = false;
        this.type = 0;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdList7");
        this.hWait = 0;
        if (icommand != null) {
            this.cmdList.addElement(icommand);
            this.hWait = iCommand.hButtonCmd;
        }
        this.strinfo = this.fontDia.splitFontArray(str, this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + hPlus + this.hWait;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(0);
    }

    public void setinfo(String str, mVector mvector) {
        beginDia();
        if (mvector == null || mvector.size() <= 0) {
            GameCanvas.currentDialog = null;
        }
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 0;
        this.cmdList = mvector;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        int size = this.cmdList.size();
        if (this.wDia < (2 * iCommand.wButtonCmd) + 10) {
            this.wDia = (2 * iCommand.wButtonCmd) + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        int i = 0;
        if (this.cmdList.size() > 2) {
            i = iCommand.hButtonCmd;
        }
        this.strinfo = this.fontDia.splitFontArray(str, this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + hPlus + i;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = (((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) - 5) + (size > 2 ? iCommand.hButtonCmd + 5 : 0);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(0);
    }

    public void setinfoQuest(String str, String str2, int i, int i2, byte b) {
        beginDia();
        this.hSpe = 2;
        this.IdQuest = i;
        this.typeQuest = i2;
        this.main_sub_quest = b;
        this.isSpec = true;
        iCommand icommand = new iCommand(T.nhan, 2);
        if (i2 == 1) {
            icommand.caption = T.tra;
            this.cmdList.addElement(icommand);
        } else {
            this.cmdList.addElement(icommand);
            iCommand icommand2 = new iCommand(T.close, 1);
            if (b == 1) {
                this.cmdList.addElement(icommand2);
            }
        }
        this.status = str2;
        this.wStatus = mFont.tahoma_7b_white.getWidth(str2) + 20;
        this.isWaiting = false;
        this.type = 1;
        this.sizeButtonQuest = 1;
        this.wDia = (GameCanvas.w / 5) * 4;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.strinfo = mFont.tahoma_7_white.splitFontArray(str, this.wDia - 20);
        this.hDia = (GameCanvas.hText * (this.strinfo.length + 1)) + hPlus + 20 + (this.cmdList.size() > 2 ? iCommand.hButtonCmd + 5 : 0);
        if (this.hDia > (GameCanvas.h / 2) + 10) {
            this.hDia = (GameCanvas.h / 2) + 10;
        }
        this.cameraDia.setAll(0, ((GameCanvas.hText * (this.strinfo.length + 1)) + 30) - this.hDia, 0, 0);
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = (GameCanvas.hh - GameCanvas.hCommand) - (this.hDia / 2);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(0);
    }

    public void setinfoQuestRead(MainQuest mainQuest) {
        beginDia();
        this.hSpe = 2;
        this.status = mainQuest.name;
        this.quest = mainQuest;
        this.isSpec = true;
        this.wStatus = mFont.tahoma_7b_white.getWidth(this.status) + 20;
        this.isWaiting = false;
        this.type = 1;
        this.sizeButtonQuest = 2;
        this.wDia = (GameCanvas.w / 5) * 4;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList.addElement(new iCommand(T.viewMap, 3));
        this.cmdList.addElement(this.cmdCancleQuest);
        this.cmdList.addElement(this.cmdClose);
        if (!GameCanvas.isTouch) {
            this.right = new iCommand(T.back, -1, this);
        }
        this.strinfo = mFont.tahoma_7_white.splitFontArray(mainQuest.strShowDialog, this.wDia - 20);
        this.hDia = (GameCanvas.hText * (this.strinfo.length + 1)) + hPlus + 15 + (this.cmdList.size() > 2 ? iCommand.hButtonCmd + 5 : 0);
        if (!GameCanvas.isTouch && this.hDia > (GameCanvas.h * 6) / 7) {
            this.hDia = (GameCanvas.h * 6) / 7;
        }
        this.cameraDia.setAll(0, (((GameCanvas.hText * (this.strinfo.length + 1)) + 45) - this.hDia) + (iCommand.hButtonCmd * this.sizeButtonQuest), 0, 0);
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = GameCanvas.hh - (this.hDia / 2);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(4);
    }

    public void setinfoParty() {
        beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = true;
        this.type = 2;
        this.hItem = GameCanvas.hCommand + 5;
        this.wDia = (GameCanvas.w / 4) * 3;
        if (this.wDia > 180) {
            this.wDia = 180;
        }
        if (Player.party != null) {
            maxSizeParty = Player.party.vecPartys.size();
        } else {
            maxSizeParty = 0;
        }
        this.hDia = (((this.hItem * maxSizeParty) + hPlus) - 10) + GameCanvas.hCommand;
        iCommand icommand = new iCommand(T.giaotiep, 4);
        this.cmdchucnang = new iCommand(T.chucnang, 8);
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.hh - GameCanvas.hCommand) - (this.hDia / 2)) + (GameCanvas.isTouch ? GameCanvas.hCommand : 0);
        iCommand icommand2 = this.cmdClose;
        if (GameCanvas.isTouch) {
            this.cmdList.addElement(this.cmdchucnang);
            setPosCmdNew(4);
            icommand2.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(icommand2);
        } else {
            this.cmdList.addElement(this.cmdchucnang);
            this.cmdList.addElement(icommand);
            setPosCmdNew(0);
            this.right = icommand2;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoAutoHP_MP() {
        beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 4;
        this.hItem = GameCanvas.hCommand;
        this.wDia = GameCanvas.w;
        if (this.wDia > 220) {
            this.wDia = 220;
        }
        this.hDia = (((this.hItem * 3) + hPlus) + GameCanvas.hCommand) - 5;
        if (!GameCanvas.isTouch) {
            this.hDia -= iCommand.hButtonCmd;
        }
        iCommand icommand = new iCommand("Ok", 5);
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = (GameCanvas.hh - GameCanvas.hCommand) - (this.hDia / 2);
        iCommand icommand2 = this.cmdClose;
        if (GameCanvas.isTouch) {
            icommand2.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(icommand2);
            icommand.setPos(this.xDia + (this.wDia / 2), (((this.yDia + this.hDia) - iCommand.hButtonCmd) + 7) - this.hSpe, null, icommand.caption);
            this.cmdList.addElement(icommand);
        } else {
            this.right = icommand2;
            this.left = icommand;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoAutoGetItem() {
        beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 7;
        this.hItem = GameCanvas.hCommand;
        this.wDia = GameCanvas.w;
        if (this.wDia > 220) {
            this.wDia = 220;
        }
        this.hDia = (((this.hItem * 3) + hPlus) - 5) + GameCanvas.hCommand;
        if (!GameCanvas.isTouch) {
            this.hDia -= iCommand.hButtonCmd;
        }
        iCommand icommand = new iCommand("Ok", 9);
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = (GameCanvas.hh - GameCanvas.hCommand) - (this.hDia / 2);
        iCommand icommand2 = this.cmdClose;
        if (GameCanvas.isTouch) {
            icommand2.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(icommand2);
            icommand.setPos(this.xDia + (this.wDia / 2), (((this.yDia + this.hDia) - iCommand.hButtonCmd) + 7) - this.hSpe, null, icommand.caption);
            this.cmdList.addElement(icommand);
        } else {
            this.right = icommand2;
            this.left = icommand;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoVolume() {
        beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 13;
        this.hItem = GameCanvas.hCommand;
        this.wDia = GameCanvas.w;
        if (this.wDia > 180) {
            this.wDia = 180;
        }
        this.hDia = (((this.hItem * 2) + hPlus) - 5) + GameCanvas.hCommand;
        if (!GameCanvas.isTouch) {
            this.hDia -= iCommand.hButtonCmd;
        }
        iCommand icommand = new iCommand("Ok", 11);
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = GameCanvas.hh - (this.hDia / 2);
        iCommand icommand2 = this.cmdClose;
        if (GameCanvas.isTouch) {
            icommand2.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(icommand2);
            icommand.setPos(this.xDia + (this.wDia / 2), (((this.yDia + this.hDia) - iCommand.hButtonCmd) + 7) - this.hSpe, null, icommand.caption);
            this.cmdList.addElement(icommand);
        } else {
            this.right = icommand2;
            this.left = icommand;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setDiaHelp(String str, iCommand icommand, int i, int i2, int i3, boolean z, int i4) {
        beginDia();
        if (icommand == null) {
            GameCanvas.currentDialog = null;
        }
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 5;
        this.wDia = i4;
        this.cmdHelp = icommand;
        this.cmdList = new mVector("MsgChat cmdlist");
        this.cmdHelp.setPos(GameCanvas.hw, GameCanvas.h - (iCommand.hButtonCmd / 2), null, this.cmdHelp.caption);
        this.cmdList.addElement(this.cmdHelp);
        this.strinfo = mFont.tahoma_7_white.splitFontArray(str, this.wDia - 4);
        this.hDia = GameCanvas.hText * this.strinfo.length;
        this.xDia = i;
        this.yDia = i2;
        if (i3 == 5 || i3 == 3 || i3 == 4 || i3 == 6) {
            this.yDia += this.hDia;
        }
        if (i3 == 8) {
            this.xDia -= this.wDia;
        }
        this.archor = i3;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setinfoSHOW(String str, iCommand icommand, boolean z, String str2) {
        beginDia();
        if (icommand == null) {
            GameCanvas.currentDialog = null;
        }
        this.nameShow = str2;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 6;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist4");
        this.cmdList.addElement(icommand);
        if (!z) {
            this.cmdList.addElement(this.cmdClose);
        }
        if (!GameCanvas.isTouch) {
            new iCommand(T.back, -1, this);
            this.right = icommand;
        }
        int size = this.cmdList.size();
        if (this.wDia < (size * iCommand.wButtonCmd) + ((size - 1) * 10) + 10) {
            this.wDia = (size * iCommand.wButtonCmd) + ((size - 1) * 10) + 10;
        }
        if (this.wDia > GameCanvas.w) {
            this.wDia = GameCanvas.w;
        }
        this.strinfo = mFont.tahoma_7_white.splitFontArray(str, this.wDia - 20);
        this.hDia = (GameCanvas.hText * (this.strinfo.length + 1)) + iCommand.hButtonCmd + 20;
        if (this.hDia > GameCanvas.h - GameCanvas.hCommand) {
            this.hDia = GameCanvas.h - GameCanvas.hCommand;
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = GameCanvas.hh - (this.hDia / 2);
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, (((GameCanvas.hText * (this.strinfo.length + 1)) + iCommand.hButtonCmd) + 20) - this.hDia);
        } else {
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = GameCanvas.hh - (this.hDia / 2);
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, 0);
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(GameCanvas.isTouch ? 4 : 0);
    }

    public void setInfoAutoBuff() {
        beginDia();
        timePaintParty = 0;
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 8;
        this.hItem = GameCanvas.hCommand;
        this.wDia = MaxSkillBuff * 60;
        if (this.wDia > 220) {
            this.wDia = 220;
        }
        this.wbuff = this.wDia / MaxSkillBuff;
        this.hDia = (this.hItem * 2) + GameCanvas.hCommand;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = (GameCanvas.hh - GameCanvas.hCommand) - (this.hDia / 2);
        if (GameCanvas.isTouch) {
            this.cmdClose.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
            this.cmdList.addElement(this.cmdClose);
        } else {
            this.left = new iCommand(T.select, 10);
            this.right = this.cmdClose;
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setInfoTime(String str, short s) {
        beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 9;
        this.timeDia = s;
        this.timeset = GameCanvas.timeNow;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        this.cmdList = new mVector("MsgChat cmdlist5");
        this.cmdList.addElement(this.cmdClose);
        this.strinfo = this.fontDia.splitFontArray(str, this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + hPlus + this.hWait;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = ((GameCanvas.h - (GameCanvas.hCommand * 2)) - this.hDia) - 5;
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        setPosCmdNew(0);
    }

    public void setShowChangeItem(String str, String str2, MainItem[] mainItemArr, MainItem mainItem) {
        TabRebuildItem.vecEffRe.removeAllElements();
        beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.nameShow = str;
        this.datanguyenlieu = mainItemArr;
        this.sanpham = mainItem;
        this.StepShow = 0;
        this.timeShow = 0;
        this.type = 10;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 240) {
            this.wDia = 240;
        }
        this.cmdList = new mVector("MsgDiaLog cmdList2");
        this.strinfo = this.fontDia.splitFontArray(str2, this.wDia - 20);
        this.hDia = (15 * this.strinfo.length) + hPlus + 50;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = GameCanvas.hh - (this.hDia / 2);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.posItemNguyenlieu = new int[mainItemArr.length + 1][2];
        int i = 0;
        if (mainItemArr.length > 4) {
            i = 30;
        } else if (mainItemArr.length > 2) {
            i = 15;
        }
        for (int i2 = 0; i2 < this.posItemNguyenlieu.length; i2++) {
            if (i2 == this.posItemNguyenlieu.length - 1) {
                this.posItemNguyenlieu[i2][0] = this.xDia + (this.wDia / 2);
                this.posItemNguyenlieu[i2][1] = ((this.yDia + this.hDia) - GameCanvas.hCommand) - 35;
            } else {
                this.posItemNguyenlieu[i2][0] = this.xDia + (this.wDia / 6) + ((i2 % 2) * (this.wDia / 3) * 2);
                this.posItemNguyenlieu[i2][1] = ((((this.yDia + this.hDia) - GameCanvas.hCommand) - 35) + i) - ((i2 / 2) * 30);
                TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posItemNguyenlieu[i2][0], this.posItemNguyenlieu[i2][1]);
            }
        }
    }

    public void setShowOpenBox(String str, MainItem[] mainItemArr, String str2, byte b, byte b2) {
        TabRebuildItem.vecEffRe.removeAllElements();
        beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.isLottery = b2;
        this.isFullEff = b;
        this.nameShow = str;
        this.itemsanpham = mainItemArr;
        this.StepShow = 0;
        this.timeShow = 0;
        this.indexShow1 = 0;
        this.indexShow2 = 0;
        this.type = 11;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 240) {
            this.wDia = 240;
        }
        if (str2 != null) {
            this.strinfo = this.fontDia.splitFontArray(str2, this.wDia - 20);
        } else {
            this.strinfo = null;
        }
        this.cmdList = new mVector("MsgDiaLog cmdList3");
        this.hDia = hPlus + 60;
        if (this.strinfo != null) {
            this.hDia += this.strinfo.length * GameCanvas.hText;
        }
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = GameCanvas.hh - (this.hDia / 2);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        this.wsizeBox = 55;
        if (this.wsizeBox * (mainItemArr.length - 1) > this.wDia - 30) {
            this.wsizeBox = 30;
        }
        int i = 0;
        if (mainItemArr.length % 2 == 0) {
            i = this.wsizeBox / 2;
        }
        int length = i + (((mainItemArr.length - 1) / 2) * this.wsizeBox);
        this.posItemNguyenlieu = new int[this.itemsanpham.length][2];
        for (int i2 = 0; i2 < this.posItemNguyenlieu.length; i2++) {
            this.posItemNguyenlieu[i2][0] = ((this.xDia + (this.wDia / 2)) - length) + (i2 * this.wsizeBox);
            this.posItemNguyenlieu[i2][1] = ((this.yDia + this.hDia) - GameCanvas.hCommand) - 45;
        }
    }

    public void setUpdateData() {
        this.nameShow = T.update;
        curupdate = 0;
        maxupdate = 20;
        beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 12;
        this.wDia = GameCanvas.w - 30;
        if (this.wDia > 240) {
            this.wDia = 240;
        }
        wUpdate = (this.wDia / 4) * 3;
        hUpdate = 16;
        this.cmdList = new mVector("MsgDiaLog cmdList4");
        this.strinfo = this.fontDia.splitFontArray(T.updateData, this.wDia - 20);
        this.hDia = 30 + hPlus;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = GameCanvas.hh - (this.hDia / 2);
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
    }

    public void setPetInfo(PetItem petItem, byte b) {
        if (b != -1) {
            isInven_Equip = b;
        }
        pet = petItem;
        this.nameShow = petItem.itemName;
        this.hItem = GameCanvas.hText;
        beginDia();
        this.isWaiting = false;
        this.isSpec = false;
        this.type = 14;
        this.wDia = GameCanvas.w - 30;
        hUpdate = 8;
        if (this.wDia > 200) {
            this.wDia = 200;
        }
        wUpdate = this.wDia / 2;
        this.cmdPet = new iCommand(T.choan, 16, this);
        this.cmdList = new mVector("MsgDiaLog cmdList");
        this.cmdList.addElement(this.cmdPet);
        int length = ((7 + petItem.mcontent.length) * GameCanvas.hText) + hPlus + 10;
        if (length > GameCanvas.h - (GameCanvas.hCommand * 2)) {
            this.hDia = GameCanvas.h - (GameCanvas.hCommand * 2);
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = GameCanvas.hh - (this.hDia / 2);
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, length - this.hDia);
        } else {
            this.hDia = length;
            this.xDia = GameCanvas.hw - (this.wDia / 2);
            this.yDia = GameCanvas.hh - (this.hDia / 2);
            this.list = new ListNew(this.xDia, this.yDia, this.wDia, this.hDia, 0, 0, 0);
        }
        this.numw = (this.wDia - 6) / 32;
        this.numh = (this.hDia - 6) / 32;
        if (GameCanvas.isTouch) {
            setPosCmdNew(4);
        }
        this.cmdList.addElement(this.cmdClose);
        if (GameCanvas.isTouch) {
            this.cmdClose.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
        } else {
            setPosCmdNew(0);
        }
    }

    public void setPosCmdNew(int i) {
        this.idCommand = 0;
        if (this.cmdList.size() > 0) {
            int size = this.cmdList.size();
            if (size == 1) {
                this.xBegin = this.xDia + (this.wDia / 2);
                this.w2cmd = 0;
            } else if (size == 2) {
                this.w2cmd = 10;
                this.xBegin = ((this.xDia + (this.wDia / 2)) - (this.w2cmd / 2)) - (iCommand.wButtonCmd / 2);
            } else {
                this.w2cmd = 10;
                this.xBegin = ((this.xDia + (this.wDia / 2)) - (this.w2cmd / 2)) - (iCommand.wButtonCmd / 2);
            }
            for (int i2 = 0; i2 < size; i2++) {
                iCommand icommand = (iCommand) this.cmdList.elementAt(i2);
                icommand.isSelect = false;
                if (size == 3 && i2 == 2) {
                    icommand.setPos(this.xDia + (this.wDia / 2), (((((((this.yDia + this.hDia) - iCommand.hButtonCmd) - (((size - 1) / 2) * (iCommand.hButtonCmd + 5))) + 7) - this.hSpe) + ((i2 / 2) * (iCommand.hButtonCmd + 5))) - (this.isSpec ? 4 : 0)) + i, null, icommand.caption);
                } else {
                    icommand.setPos(this.xBegin + ((i2 % 2) * (iCommand.wButtonCmd + this.w2cmd)), (((((((this.yDia + this.hDia) - iCommand.hButtonCmd) - (((size - 1) / 2) * (iCommand.hButtonCmd + 5))) + 7) - this.hSpe) + ((i2 / 2) * (iCommand.hButtonCmd + 5))) - (this.isSpec ? 4 : 0)) + i, null, icommand.caption);
                }
                if (i2 == 0) {
                    icommand.isSelect = true;
                }
            }
        }
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        GameCanvas.resetTrans(mgraphics);
        switch (this.type) {
            case 0:
                AvMain.paintDialog(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, 12);
                for (int i = 0; i < this.strinfo.length; i++) {
                    this.fontDia.drawString(mgraphics, this.strinfo[i], GameCanvas.w / 2, this.yDia + 12 + (i * 15), 2, true);
                }
                if (this.isWaiting) {
                    fraWaiting.drawFrame(this.fWait % fraWaiting.nFrame, GameCanvas.hw, this.yDia + 25 + (this.strinfo.length * 15), 0, 3, mgraphics);
                    break;
                }
                break;
            case 1:
                AvMain.paintTabNew(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, true, (byte) 0);
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mgraphics, GameCanvas.hw - 32, this.yDia + 11, 64, 14, 2);
                } else {
                    for (int i2 = 0; i2 < 2; i2++) {
                        mgraphics.drawRegion(MainTabNew.imgTab[2], 0, 0, 32, 14, 0, (GameCanvas.hw - 32) + (i2 * 32), this.yDia + 11, 0, true);
                    }
                }
                mFont.tahoma_7b_white.drawString(mgraphics, T.quest, GameCanvas.hw, this.yDia + 12, 2, false);
                mFont.tahoma_7b_black.drawString(mgraphics, this.status, this.xDia + 10, this.yDia + 27, 0, true);
                mgraphics.setClip(this.xDia + 3, this.yDia + 39, this.wDia - 6, (this.hDia - 55) - (iCommand.hButtonCmd * this.sizeButtonQuest));
                mgraphics.saveCanvas();
                mgraphics.ClipRec(this.xDia + 3, this.yDia + 39, this.wDia - 6, (this.hDia - 55) - (iCommand.hButtonCmd * this.sizeButtonQuest));
                mgraphics.translate(0, -this.cameraDia.yCam);
                for (int i3 = 0; i3 < this.strinfo.length; i3++) {
                    mFont.tahoma_7_black.drawString(mgraphics, this.strinfo[i3], this.xDia + 11, this.yDia + 27 + ((i3 + 1) * GameCanvas.hText), 0, true);
                }
                mGraphics.resetTransAndroid(mgraphics);
                mgraphics.restoreCanvas();
                mgraphics.endClip();
                if (!GameCanvas.isTouch) {
                    paintCmd_OnlyText(mgraphics);
                    break;
                }
                break;
            case 2:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, T.party);
                int i4 = this.yDia + GameCanvas.hCommand + 3;
                if (Player.party != null) {
                    if (!GameCanvas.isTouch || timePaintParty > 0) {
                        mgraphics.setColor(-4873075);
                        mgraphics.fillRect(this.xDia + 9, (i4 - 2) + (this.idSelect * this.hItem), this.wDia - 17, this.hItem - 1, true);
                    }
                    if (Player.party != null) {
                        for (int i5 = 0; i5 < Player.party.vecPartys.size(); i5++) {
                            ObjectParty objectParty = (ObjectParty) Player.party.vecPartys.elementAt(i5);
                            if (objectParty.name.compareTo(Player.party.nameMain) == 0) {
                                AvMain.Font3dColorAndColor(mgraphics, new StringBuffer().append(objectParty.name).append(" ").append(T.Lv).append((int) objectParty.Lv).toString(), this.xDia + 11, i4, 0, (byte) 7, (byte) 0);
                            } else {
                                mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(objectParty.name).append(" ").append(T.Lv).append((int) objectParty.Lv).toString(), this.xDia + 11, i4, 0, true);
                            }
                            if (objectParty.name.compareTo(GameScreen.player.name) == 0) {
                                objectParty.hp = GameScreen.player.hp;
                                objectParty.maxhp = GameScreen.player.maxHp;
                            }
                            mgraphics.setColor(0);
                            mgraphics.fillRect(this.xDia + 11, (i4 + 14) - 2, 42, 4, true);
                            mgraphics.setColor(-8714722);
                            mgraphics.fillRect(this.xDia + 12, (i4 + 15) - 2, 40, 2, true);
                            mgraphics.setColor(-579511);
                            mgraphics.fillRect(this.xDia + 12, (i4 + 15) - 2, (40 * objectParty.hp) / objectParty.maxhp, 2, true);
                            String str = "map";
                            if (WorldMapScreen.namePos != null && objectParty.idMap < WorldMapScreen.namePos.length) {
                                str = WorldMapScreen.namePos[objectParty.idMap];
                            }
                            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(str).append(" - ").append(T.Area).append(objectParty.idArea + 1).toString(), this.xDia + 11, (i4 + 20) - (2 * 2), 0, true);
                            i4 += this.hItem;
                            if (i5 < Player.party.vecPartys.size() - 1) {
                                mgraphics.setColor(AvMain.color[4]);
                                mgraphics.fillRect(this.xDia + 12, i4 - 3, this.wDia - 24, 1, true);
                            }
                        }
                    }
                } else {
                    mFont.tahoma_7_black.drawString(mgraphics, T.noParty, this.xDia + (this.wDia / 2), i4, 2, true);
                }
                super.paint(mgraphics);
                break;
            case 4:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, T.auto);
                int i6 = this.yDia + this.hItem + 11;
                int i7 = (this.xDia + 30) - (GameCanvas.isTouch ? 10 : 0);
                mgraphics.setColor(MainTabNew.color[0]);
                for (int i8 = 0; i8 < T.mAuto.length; i8++) {
                    mFont.tahoma_7_white.drawString(mgraphics, T.mAuto[i8], i7, i6, 0, true);
                    int width = mFont.tahoma_7_black.getWidth(T.mAuto[0]);
                    mgraphics.fillRect(i7 + width, i6 - 3, 35, 18, true);
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("  ").append(mHPMP[i8]).append("%").toString(), i7 + 3 + width, i6, 0, true);
                    i6 += this.hItem;
                }
                mFont.tahoma_7_white.drawString(mgraphics, T.mUtien[isUutien], i7, i6, 0, true);
                if (!GameCanvas.isTouch) {
                    mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, (i7 - 4) + (GameCanvas.gameTick % 3), this.yDia + this.hItem + 17 + (this.idSelect * this.hItem), 10, true);
                }
                GameCanvas.resetTrans(mgraphics);
                paintCmd(mgraphics);
                break;
            case 5:
                MainHelp.paintPopup(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, this.archor, this.strinfo);
                break;
            case 6:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                mgraphics.setClip(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, ((this.hDia - GameCanvas.hCommand) - iCommand.hButtonCmd) - 8);
                mgraphics.saveCanvas();
                mgraphics.ClipRec(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, ((this.hDia - GameCanvas.hCommand) - iCommand.hButtonCmd) - 8);
                mgraphics.translate(0, -this.list.cmx);
                for (int i9 = 0; i9 < this.strinfo.length; i9++) {
                    mFont.tahoma_7_white.drawString(mgraphics, this.strinfo[i9], this.xDia + 8, this.yDia + GameCanvas.hCommand + 2 + (i9 * GameCanvas.hText), 0, true);
                }
                mGraphics.resetTransAndroid(mgraphics);
                mgraphics.restoreCanvas();
                mgraphics.endClip();
                if (!GameCanvas.isTouch) {
                    paintCmd_OnlyText(mgraphics);
                    break;
                }
                break;
            case 7:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, T.auto);
                int i10 = this.yDia + this.hItem + 11;
                int i11 = (this.xDia + 30) - (GameCanvas.isTouch ? 10 : 0);
                mgraphics.setColor(MainTabNew.color[3]);
                for (int i12 = 0; i12 < T.mAutoItem.length; i12++) {
                    mFont.tahoma_7b_white.drawString(mgraphics, T.mAutoItem[i12], i11, i10, 0, true);
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("< ").append(T.mValueAutoItem[i12][this.mvalueItem[i12]]).append(" >").toString(), i11 + 3 + mFont.tahoma_7b_white.getWidth(T.mAutoItem[i12]), i10, 0, true);
                    i10 += this.hItem;
                }
                if (!GameCanvas.isTouch) {
                    mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, (i11 - 4) + (GameCanvas.gameTick % 3), this.yDia + this.hItem + 17 + (this.idSelect * this.hItem), 10, true);
                }
                GameCanvas.resetTrans(mgraphics);
                paintCmd(mgraphics);
                break;
            case 8:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, T.auto);
                int i13 = this.yDia + GameCanvas.hCommand + this.hItem;
                int i14 = this.xDia + (this.wbuff / 2);
                if (!GameCanvas.isTouch) {
                    mgraphics.setColor(-1054968);
                    mgraphics.fillRect((i14 - 12) + (this.idSelect * this.wbuff), i13 - 12, 24, 24, true);
                }
                for (int i15 = 0; i15 < MaxSkillBuff; i15++) {
                    ((Skill) TabSkillsNew.vecPaintSkill.elementAt(Autobuff[i15][2])).paint(mgraphics, i14, i13, 3);
                    if (Autobuff[i15][1] == 0) {
                        mgraphics.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, 20, 0, i14, i13, 3, true);
                    }
                    i14 += this.wbuff;
                }
                GameCanvas.resetTrans(mgraphics);
                paintCmd(mgraphics);
                break;
            case 9:
                AvMain.paintDialog(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, 12);
                for (int i16 = 0; i16 < this.strinfo.length; i16++) {
                    if (i16 == this.strinfo.length - 1) {
                        this.fontDia.drawString(mgraphics, new StringBuffer().append(this.strinfo[i16]).append(" ").append(this.timeDia > 0 ? new StringBuffer().append((int) this.timeDia).append("").toString() : "").append("'.").toString(), GameCanvas.w / 2, this.yDia + 12 + (i16 * 15), 2, true);
                    } else {
                        this.fontDia.drawString(mgraphics, this.strinfo[i16], GameCanvas.w / 2, this.yDia + 12 + (i16 * 15), 2, true);
                    }
                }
                break;
            case 10:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                if (this.StepShow < 2) {
                    for (int i17 = 0; i17 < this.posItemNguyenlieu.length; i17++) {
                        mgraphics.drawImage(AvMain.imgHotKey, this.posItemNguyenlieu[i17][0], this.posItemNguyenlieu[i17][1], 3, true);
                    }
                    if (this.StepShow == 0) {
                        for (int i18 = 0; i18 < this.datanguyenlieu.length; i18++) {
                            if (this.timeShow >= 4 && this.datanguyenlieu[i18] != null) {
                                this.datanguyenlieu[i18].paintItem(mgraphics, this.posItemNguyenlieu[i18][0], this.posItemNguyenlieu[i18][1], 21, 1, 0);
                            }
                        }
                    }
                } else if (this.StepShow == 2) {
                    for (int i19 = 0; i19 < this.strinfo.length; i19++) {
                        mFont.tahoma_7_white.drawString(mgraphics, this.strinfo[i19], this.xDia + 8, this.yDia + GameCanvas.hCommand + 2 + (i19 * GameCanvas.hText), 0, true);
                    }
                    int length = this.posItemNguyenlieu.length - 1;
                    if (this.sanpham != null) {
                        this.sanpham.paintItem(mgraphics, this.posItemNguyenlieu[length][0], this.posItemNguyenlieu[length][1], 21, 1, 0);
                    }
                    mgraphics.drawImage(AvMain.imgHotKey, this.posItemNguyenlieu[length][0], this.posItemNguyenlieu[length][1], 3, true);
                }
                for (int i20 = 0; i20 < TabRebuildItem.vecEffRe.size(); i20++) {
                    ((MainEffect) TabRebuildItem.vecEffRe.elementAt(i20)).paint(mgraphics);
                }
                break;
            case 11:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                if (this.strinfo != null) {
                    for (int i21 = 0; i21 < this.strinfo.length; i21++) {
                        mFont.tahoma_7_white.drawString(mgraphics, this.strinfo[i21], this.xDia + 8, this.yDia + GameCanvas.hCommand + 2 + (i21 * GameCanvas.hText), 0, true);
                    }
                }
                for (int i22 = 0; i22 < this.posItemNguyenlieu.length; i22++) {
                    if (this.indexShow1 == -1 || i22 <= this.indexShow1) {
                        mgraphics.drawImage(AvMain.imgHotKey, this.posItemNguyenlieu[i22][0], this.posItemNguyenlieu[i22][1], 3, true);
                    }
                }
                for (int i23 = 0; i23 < this.itemsanpham.length; i23++) {
                    if (this.itemsanpham[i23].canSell != 0) {
                        this.itemsanpham[i23].paintItem(mgraphics, this.posItemNguyenlieu[i23][0], this.posItemNguyenlieu[i23][1], 21, 1, 0);
                        if (this.isLottery == 0) {
                            if (this.itemsanpham[i23].ItemCatagory == 3) {
                                MainTabNew.setTextColorName(this.itemsanpham[i23].colorNameItem).drawString(mgraphics, this.itemsanpham[i23].itemName, this.posItemNguyenlieu[i23][0], this.posItemNguyenlieu[i23][1] + 14, 2, true);
                            }
                        } else if (i23 == 1) {
                            MainTabNew.setTextColorName(this.itemsanpham[i23].colorNameItem).drawString(mgraphics, this.itemsanpham[i23].itemName, this.posItemNguyenlieu[i23][0], this.posItemNguyenlieu[i23][1] + 14, 2, true);
                        }
                    }
                }
                for (int i24 = 0; i24 < TabRebuildItem.vecEffRe.size(); i24++) {
                    ((MainEffect) TabRebuildItem.vecEffRe.elementAt(i24)).paint(mgraphics);
                }
                break;
            case 12:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                for (int i25 = 0; i25 < this.strinfo.length; i25++) {
                    mFont.tahoma_7_white.drawString(mgraphics, this.strinfo[i25], this.xDia + 8, this.yDia + GameCanvas.hCommand + 4 + (i25 * GameCanvas.hText), 0, true);
                }
                int i26 = (this.xDia + (this.wDia / 2)) - (wUpdate / 2);
                int i27 = hUpdate - 10;
                int i28 = (((this.yDia + this.hDia) - i27) - 25) - 5;
                mgraphics.setColor(2698542);
                mgraphics.fillRect(i26 - 4, i28 + 15, wUpdate + 2, i27, true);
                mgraphics.fillRect((i26 - 4) + 1, i28 + 14, wUpdate, 1, true);
                mgraphics.fillRect((i26 - 4) + 1, i28 + 15 + i27, wUpdate, 1, true);
                mgraphics.setColor(3027507);
                mgraphics.fillRect((i26 - 4) + 1, i28 + 15, wUpdate, i27, true);
                if (maxupdate > 0 && curupdate > 0) {
                    int i29 = (curupdate * wUpdate) / maxupdate;
                    if (i29 <= 0) {
                        i29 = 1;
                    } else if (i29 > wUpdate) {
                        i29 = wUpdate;
                    }
                    mgraphics.setColor(-6437568);
                    mgraphics.fillRect((i26 - 4) + 1, i28 + 15, i29, i27, true);
                }
                mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append((curupdate * 100) / maxupdate).append("%").toString(), this.xDia + (this.wDia / 2), i28, 2, true);
                break;
            case 13:
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, T.SetMusic);
                int i30 = this.yDia + this.hItem + 11;
                int i31 = (this.xDia + 30) - (GameCanvas.isTouch ? 10 : 0);
                mgraphics.setColor(MainTabNew.color[3]);
                for (int i32 = 0; i32 < T.mVolume.length; i32++) {
                    mFont.tahoma_7b_white.drawString(mgraphics, T.mVolume[i32], i31, i30, 0, true);
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append("< ").append(mvalueVolume[i32] == 0 ? T.on : T.off).append(" >").toString(), i31 + 3 + mFont.tahoma_7b_white.getWidth(T.mVolume[i32]), i30, 0, true);
                    i30 += this.hItem;
                }
                if (!GameCanvas.isTouch) {
                    mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, (i31 - 4) + (GameCanvas.gameTick % 3), this.yDia + this.hItem + 17 + (this.idSelect * this.hItem), 10, true);
                }
                GameCanvas.resetTrans(mgraphics);
                paintCmd(mgraphics);
                break;
            case 14:
                if (pet == null) {
                    return;
                }
                paintFormList(mgraphics, this.xDia, this.yDia, this.wDia, this.hDia, this.nameShow);
                int i33 = this.yDia + (this.hItem * 2);
                int i34 = this.xDia + 10;
                mgraphics.setClip(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, ((this.hDia - GameCanvas.hCommand) - iCommand.hButtonCmd) - 8);
                mgraphics.saveCanvas();
                mgraphics.ClipRec(this.xDia, this.yDia + GameCanvas.hCommand + 2, this.wDia, ((this.hDia - GameCanvas.hCommand) - iCommand.hButtonCmd) - 8);
                mgraphics.translate(0, -this.list.cmx);
                pet.paintShowPet(mgraphics, i34 + (MainTabNew.wOneItem / 2), i33 + (MainTabNew.wOneItem / 2) + (MainTabNew.wOneItem / 4), MainTabNew.wOneItem, MainTabNew.wOneItem / 2, 0, 1);
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.level).append(pet.LvItem).append(" + ").append(pet.experience / 10).append(",").append(pet.experience % 10).append("%").toString(), i34 + 40, i33, 0, true);
                int i35 = i33 + GameCanvas.hText;
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.tuoi).append(pet.age / 24).append("d ").append(pet.age % 24).append("h").toString(), i34 + 40, i35, 0, true);
                int i36 = i35 + GameCanvas.hText;
                MainTabNew.setTextColor(Item.colorInfoItem[pet.petAttack.id]).drawString(mgraphics, new StringBuffer().append(Item.nameInfoItem[pet.petAttack.id]).append(": ").append(pet.petAttack.value).append("-").append(pet.petAttack.maxDam).toString(), i34 + 40, i36, 0, true);
                int i37 = i36 + this.hItem;
                mFont.tahoma_7_white.drawString(mgraphics, T.choan, i34 + 8, i37, 0, true);
                paintStatus(mgraphics, pet.growpoint, i34 + 65, i37 + 1, pet.maxgrow);
                int i38 = i37 + this.hItem;
                for (int i39 = 0; i39 < T.mKynangPet.length; i39++) {
                    mFont.tahoma_7_white.drawString(mgraphics, T.mKynangPet[i39], i34 + 8, i38, 0, true);
                    paintStatus(mgraphics, pet.mvaluetiemnang[i39], i34 + 65, i38 + 1, pet.maxtiemnang);
                    i38 += this.hItem;
                }
                for (int i40 = 0; i40 < pet.mInfo.length; i40++) {
                    if (pet.mInfo[i40].id > 6) {
                        MainTabNew.setTextColor(Item.colorInfoItem[pet.mInfo[i40].id]).drawString(mgraphics, new StringBuffer().append(Item.nameInfoItem[pet.mInfo[i40].id]).append(": ").append(Item.getPercent(Item.isPercentInfoItem[pet.mInfo[i40].id], pet.mInfo[i40].value)).toString(), i34 + 8, i38, 0, true);
                        i38 += this.hItem;
                    }
                }
                mGraphics.resetTransAndroid(mgraphics);
                mgraphics.restoreCanvas();
                mgraphics.endClip();
                GameCanvas.resetTrans(mgraphics);
                paintCmd(mgraphics);
                break;
        }
        GameCanvas.resetTrans(mgraphics);
        if (this.cmdList != null) {
            for (int i41 = 0; i41 < this.cmdList.size(); i41++) {
                iCommand icommand = (iCommand) this.cmdList.elementAt(i41);
                icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
            }
        }
    }

    public void paintStatus(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        mgraphics.setColor(-14078674);
        mgraphics.fillRect(i2 - 4, i3 + 1, wUpdate + 2, hUpdate, true);
        mgraphics.fillRect((i2 - 4) + 1, i3, wUpdate, 1, true);
        mgraphics.fillRect((i2 - 4) + 1, i3 + 1 + hUpdate, wUpdate, 1, true);
        mgraphics.setColor(-13749709);
        mgraphics.fillRect((i2 - 4) + 1, i3 + 1, wUpdate, hUpdate, true);
        if ((i * 100) / i4 > 0) {
            int i5 = (((i * 100) / i4) * wUpdate) / 100;
            if (i5 <= 0) {
                i5 = 1;
            } else if (i5 > wUpdate) {
                i5 = wUpdate;
            }
            mgraphics.setColor(-6437568);
            mgraphics.fillRect((i2 - 4) + 1, i3 + 1, i5, hUpdate, true);
        }
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(i).append("").toString(), i2 + (wUpdate / 2), i3, 2, true);
    }

    @Override // Model.AvMain
    public void update() {
        if (this.isWaiting) {
            this.fWait++;
            if (this.fWait > 1200) {
                if (Session_ME.gI().isConnected() && GameCanvas.currentScreen != GameCanvas.login && GameCanvas.currentScreen != GameCanvas.load) {
                    GameCanvas.start_Ok_Dialog(T.thulai);
                } else {
                    mVector mvector = new mVector("MsgDiaLog vec");
                    if (SelectCharScreen.isSelectOk && GameCanvas.currentScreen != GameCanvas.login) {
                        mvector.addElement(new iCommand(T.again, 0));
                    }
                    mvector.addElement(new iCommand(T.exit, 6));
                    GameCanvas.start_Select_Dialog(T.disconnect, mvector);
                    GlobalLogicHandler.isDisConect = true;
                    GlobalLogicHandler.timeReconnect = mSystem.currentTimeMillis() + 30000;
                }
            }
        }
        if (this.type == 6 || this.type == 14) {
            this.list.moveCamera();
        }
        if (this.type == 6) {
            Player.timeStand = 1;
        }
        if (GlobalLogicHandler.isDisConect && GlobalLogicHandler.timeReconnect - mSystem.currentTimeMillis() <= 0) {
            relogin();
        }
        updatekey();
        updatePointer();
        if (this.type == 2) {
            if (Player.party != null) {
                if (Player.party.vecPartys.size() != maxSizeParty || maxSizeParty == -1) {
                    maxSizeParty = Player.party.vecPartys.size();
                    this.hDia = (((this.hItem * maxSizeParty) + hPlus) - 10) + GameCanvas.hCommand;
                    this.yDia = ((GameCanvas.hh - GameCanvas.hCommand) - (this.hDia / 2)) + (GameCanvas.isTouch ? GameCanvas.hCommand : 0);
                    this.numh = (this.hDia - 6) / 32;
                    this.cmdList.removeAllElements();
                    iCommand icommand = new iCommand(T.giaotiep, 4);
                    this.cmdchucnang = new iCommand(T.leave, 8);
                    if (GameScreen.player.name.compareTo(Player.party.nameMain) == 0) {
                        this.cmdchucnang.caption = T.chucnang;
                    }
                    iCommand icommand2 = this.cmdClose;
                    if (GameCanvas.isTouch) {
                        this.cmdList.addElement(this.cmdchucnang);
                        setPosCmdNew(4);
                        icommand2.setPos((this.xDia + this.wDia) - 12, this.yDia + 10, PaintInfoGameScreen.fraCloseMenu, "");
                        this.cmdList.addElement(icommand2);
                    } else {
                        this.cmdList.addElement(this.cmdchucnang);
                        this.cmdList.addElement(icommand);
                        setPosCmdNew(4);
                        this.right = icommand2;
                    }
                }
                if (timePaintParty > 0) {
                    timePaintParty--;
                    return;
                }
                return;
            }
            this.cmdList.removeAllElements();
            iCommand icommand3 = this.cmdClose;
            if (GameCanvas.isTouch) {
                icommand3.setPos((this.xDia + this.wDia) - 6, this.yDia + 4, PaintInfoGameScreen.fraCloseMenu, "");
                this.cmdList.addElement(icommand3);
                return;
            } else {
                this.right = icommand3;
                return;
            }
        }
        if (this.type == 3 || this.type == 6 || this.type == 14) {
            this.cameraDia.UpdateCamera();
            return;
        }
        if (this.type == 9) {
            if ((GameCanvas.timeNow - this.timeset) / 1000 > 1) {
                this.timeset += 1000;
                this.timeDia = (short) (this.timeDia - 1);
                if (this.timeDia <= 0) {
                    this.cmdClose.perform();
                    return;
                }
                return;
            }
            return;
        }
        if (this.type == 10) {
            this.timeShow++;
            if (this.StepShow == 0) {
                if (this.timeShow == 30) {
                    this.timeShow = 0;
                    this.StepShow = 1;
                }
            } else if (this.StepShow == 1) {
                if (this.timeShow == 1) {
                    mSound.playSound(29, mSound.volumeSound);
                    for (int i = 0; i < this.posItemNguyenlieu.length - 1; i++) {
                        TabRebuildItem.addEffectEndRebuild(41, this.posItemNguyenlieu[i][0], this.posItemNguyenlieu[i][1], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][0], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][1], 1);
                    }
                }
                if (this.timeShow >= 16) {
                    mSound.playSound(26, mSound.volumeSound);
                    this.StepShow = 2;
                    this.timeShow = 0;
                    this.cmdList.addElement(this.cmdClose);
                    setPosCmdNew(0);
                    TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][0], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][1]);
                    TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][0], this.posItemNguyenlieu[this.posItemNguyenlieu.length - 1][1]);
                }
            } else if (this.StepShow == 2) {
            }
            for (int i2 = 0; i2 < TabRebuildItem.vecEffRe.size(); i2++) {
                MainEffect mainEffect = (MainEffect) TabRebuildItem.vecEffRe.elementAt(i2);
                mainEffect.update();
                if (mainEffect.isStop) {
                    TabRebuildItem.vecEffRe.removeElement(mainEffect);
                }
            }
            return;
        }
        if (this.type == 11) {
            this.timeShow++;
            if (this.StepShow == 0) {
                if (this.indexShow1 >= 0 && this.timeShow % 5 == 1) {
                    TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.posItemNguyenlieu[this.indexShow1][0], this.posItemNguyenlieu[this.indexShow1][1]);
                    if (this.indexShow1 < this.itemsanpham.length - 1) {
                        this.indexShow1++;
                    } else {
                        this.indexShow1 = -1;
                    }
                }
                if (this.timeShow > 5 && this.timeShow % 5 == 1) {
                    if (this.isFullEff == 0) {
                        TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.posItemNguyenlieu[this.indexShow2][0], this.posItemNguyenlieu[this.indexShow2][1]);
                    }
                    this.itemsanpham[this.indexShow2].canSell = (byte) 1;
                    if (this.indexShow2 < this.itemsanpham.length - 1) {
                        this.indexShow2++;
                        mSound.playSound(29, mSound.volumeSound);
                    } else {
                        mSound.playSound(29, mSound.volumeSound);
                        this.indexShow2 = -1;
                        this.indexShow1 = -1;
                        this.timeShow = 0;
                        this.StepShow = 1;
                        this.cmdList.addElement(this.cmdClose);
                        setPosCmdNew(0);
                    }
                }
            }
            for (int i3 = 0; i3 < TabRebuildItem.vecEffRe.size(); i3++) {
                MainEffect mainEffect2 = (MainEffect) TabRebuildItem.vecEffRe.elementAt(i3);
                mainEffect2.update();
                if (mainEffect2.isStop) {
                    TabRebuildItem.vecEffRe.removeElement(mainEffect2);
                }
            }
            return;
        }
        if (this.type == 12) {
            if (Session_ME.gI().isConnected()) {
                if (curupdate >= maxupdate && GameCanvas.gameTick % 40 == 20) {
                    GameCanvas.start_Ok_Dialog(T.updateok);
                    return;
                }
                return;
            }
            GameCanvas.start_Center_Dialog_Only(T.disconnect, new iCommand(T.exit, -1));
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (this.type == 1) {
            this.cameraDia.UpdateCamera();
            if (GameCanvas.keyMyHold[2]) {
                this.cameraDia.yTo -= GameCanvas.hText;
                if (this.cameraDia.yTo < 0) {
                    this.cameraDia.yTo = 0;
                }
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.cameraDia.yTo += GameCanvas.hText;
                if (this.cameraDia.yTo > this.cameraDia.yLimit) {
                    this.cameraDia.yTo = this.cameraDia.yLimit;
                }
                GameCanvas.clearKeyHold(8);
            }
        } else if (this.type == 2) {
            if (Player.party != null) {
                if (GameCanvas.keyMyHold[2]) {
                    this.idSelect--;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.idSelect++;
                    GameCanvas.clearKeyHold(8);
                }
                this.idSelect = resetSelect(this.idSelect, Player.party.vecPartys.size() - 1, true);
            } else {
                this.left = null;
            }
        } else if (this.type == 6 || this.type == 14) {
            if (GameCanvas.keyMyHold[2]) {
                this.list.cmtoX -= GameCanvas.hText;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.list.cmtoX += GameCanvas.hText;
                GameCanvas.clearKeyHold(8);
            }
            if (this.list.cmtoX > this.list.cmxLim) {
                this.list.cmtoX = this.list.cmxLim;
            }
            if (this.list.cmtoX < 0) {
                this.list.cmtoX = 0;
            }
        } else if (this.type == 4) {
            if (GameCanvas.keyMyHold[2]) {
                this.idSelect--;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = resetSelect(this.idSelect, 2, true);
            if (GameCanvas.keyMyHold[4]) {
                switch (this.idSelect) {
                    case 0:
                        if (mHPMP[0] > 10) {
                            int[] iArr = mHPMP;
                            iArr[0] = iArr[0] - 10;
                            break;
                        }
                        break;
                    case 1:
                        if (mHPMP[1] > 10) {
                            int[] iArr2 = mHPMP;
                            iArr2[1] = iArr2[1] - 10;
                            break;
                        }
                        break;
                    case 2:
                        if (isUutien == 1) {
                            isUutien = 0;
                            break;
                        }
                        break;
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                switch (this.idSelect) {
                    case 0:
                        if (mHPMP[0] < 90) {
                            int[] iArr3 = mHPMP;
                            iArr3[0] = iArr3[0] + 10;
                            break;
                        }
                        break;
                    case 1:
                        if (mHPMP[1] < 90) {
                            int[] iArr4 = mHPMP;
                            iArr4[1] = iArr4[1] + 10;
                            break;
                        }
                        break;
                    case 2:
                        if (isUutien == 0) {
                            isUutien = 1;
                            break;
                        }
                        break;
                }
                GameCanvas.clearKeyHold(6);
            }
        } else if (this.type == 7) {
            if (GameCanvas.keyMyHold[2]) {
                this.idSelect--;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = resetSelect(this.idSelect, 2, true);
            if (GameCanvas.keyMyHold[4]) {
                if (this.mvalueItem[this.idSelect] == 0) {
                    this.mvalueItem[this.idSelect] = (byte) (T.mValueAutoItem[this.idSelect].length - 1);
                } else {
                    byte[] bArr = this.mvalueItem;
                    int i = this.idSelect;
                    bArr[i] = (byte) (bArr[i] - 1);
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.mvalueItem[this.idSelect] == ((byte) (T.mValueAutoItem[this.idSelect].length - 1))) {
                    this.mvalueItem[this.idSelect] = 0;
                } else {
                    byte[] bArr2 = this.mvalueItem;
                    int i2 = this.idSelect;
                    bArr2[i2] = (byte) (bArr2[i2] + 1);
                }
                GameCanvas.clearKeyHold(6);
            }
        } else if (this.type == 13) {
            if (GameCanvas.keyMyHold[2]) {
                this.idSelect--;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = resetSelect(this.idSelect, 1, true);
            if (GameCanvas.keyMyHold[4]) {
                if (this.mvalueItem[this.idSelect] == 0) {
                    this.mvalueItem[this.idSelect] = 1;
                } else {
                    byte[] bArr3 = this.mvalueItem;
                    int i3 = this.idSelect;
                    bArr3[i3] = (byte) (bArr3[i3] - 1);
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                if (this.mvalueItem[this.idSelect] == 1) {
                    this.mvalueItem[this.idSelect] = 0;
                } else {
                    byte[] bArr4 = this.mvalueItem;
                    int i4 = this.idSelect;
                    bArr4[i4] = (byte) (bArr4[i4] + 1);
                }
                GameCanvas.clearKeyHold(6);
            }
        } else if (this.type == 8) {
            if (GameCanvas.keyMyHold[4]) {
                this.idSelect--;
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(6);
            }
            this.idSelect = resetSelect(this.idSelect, MaxSkillBuff - 1, true);
        }
        if (this.cmdList != null) {
            int size = this.cmdList.size();
            if (!GameCanvas.isTouch && size > 0) {
                int i5 = this.idCommand;
                if (GameCanvas.keyMyHold[4]) {
                    this.idCommand--;
                    GameCanvas.clearKeyHold(4);
                } else if (GameCanvas.keyMyHold[6]) {
                    this.idCommand++;
                    GameCanvas.clearKeyHold(6);
                }
                this.idCommand = resetSelect(this.idCommand, size - 1, false);
                if (this.typeQuest == 2 && ((iCommand) this.cmdList.elementAt(this.idCommand)) == this.cmdClose) {
                    this.idCommand = 0;
                }
                if (i5 != this.idCommand) {
                    for (int i6 = 0; i6 < size; i6++) {
                        iCommand icommand = (iCommand) this.cmdList.elementAt(i6);
                        if (i6 == this.idCommand) {
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
            if (this.cmdList != null && this.idCommand < this.cmdList.size()) {
                ((iCommand) this.cmdList.elementAt(this.idCommand)).perform();
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        int i;
        if (this.type == 2) {
            if (Player.party != null && GameCanvas.isPointSelect(this.xDia, this.yDia + GameCanvas.hCommand, this.wDia, this.hDia - GameCanvas.hCommand) && (i = (GameCanvas.py - (this.yDia + GameCanvas.hCommand)) / this.hItem) >= 0 && i <= Player.party.vecPartys.size() - 1) {
                mSound.playSound(42, mSound.volumeSound);
                this.idSelect = i;
                this.idSelect = resetSelect(this.idSelect, Player.party.vecPartys.size() - 1, false);
                timePaintParty = 3;
                setMenuParty();
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 4) {
            int i2 = this.yDia + this.hItem + 11;
            int width = ((this.xDia + 30) - (GameCanvas.isTouch ? 10 : 0)) + mFont.tahoma_7_black.getWidth(T.mAuto[0]);
            if (GameCanvas.isPointSelect(width, i2 - 5, 40, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (mHPMP[0] < 90) {
                    int[] iArr = mHPMP;
                    iArr[0] = iArr[0] + 10;
                } else {
                    mHPMP[0] = 10;
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(width, (i2 + this.hItem) - 5, 40, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (mHPMP[1] < 90) {
                    int[] iArr2 = mHPMP;
                    iArr2[1] = iArr2[1] + 10;
                } else {
                    mHPMP[1] = 10;
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect((this.xDia + 30) - (GameCanvas.isTouch ? 10 : 0), i2 + (this.hItem * 2), EffectSkill.EFF_BIG_SWORD_115_2, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (isUutien == 0) {
                    isUutien = 1;
                } else {
                    isUutien = 0;
                }
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 7) {
            int i3 = this.yDia + this.hItem + 11;
            int i4 = this.xDia;
            if (GameCanvas.isPointSelect(i4, i3 - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (this.mvalueItem[0] == ((byte) (T.mValueAutoItem[0].length - 1))) {
                    this.mvalueItem[0] = 0;
                } else {
                    byte[] bArr = this.mvalueItem;
                    bArr[0] = (byte) (bArr[0] + 1);
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(i4, (i3 + this.hItem) - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (this.mvalueItem[1] == ((byte) (T.mValueAutoItem[1].length - 1))) {
                    this.mvalueItem[1] = 0;
                } else {
                    byte[] bArr2 = this.mvalueItem;
                    bArr2[1] = (byte) (bArr2[1] + 1);
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(i4, i3 + (this.hItem * 2), this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (this.mvalueItem[2] == ((byte) (T.mValueAutoItem[2].length - 1))) {
                    this.mvalueItem[2] = 0;
                } else {
                    byte[] bArr3 = this.mvalueItem;
                    bArr3[2] = (byte) (bArr3[2] + 1);
                }
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 13) {
            int i5 = this.yDia + this.hItem + 11;
            int i6 = this.xDia;
            if (GameCanvas.isPointSelect(i6, i5 - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (mvalueVolume[0] == 1) {
                    mvalueVolume[0] = 0;
                } else {
                    byte[] bArr4 = mvalueVolume;
                    bArr4[0] = (byte) (bArr4[0] + 1);
                }
                GameCanvas.isPointerSelect = false;
            } else if (GameCanvas.isPointSelect(i6, (i5 + this.hItem) - 4, this.wDia, 20)) {
                mSound.playSound(42, mSound.volumeSound);
                if (mvalueVolume[1] == 1) {
                    mvalueVolume[1] = 0;
                } else {
                    byte[] bArr5 = mvalueVolume;
                    bArr5[1] = (byte) (bArr5[1] + 1);
                }
                GameCanvas.isPointerSelect = false;
            }
        } else if (this.type == 6 || this.type == 14) {
            this.list.update_Pos_UP_DOWN();
        } else if (this.type == 8) {
            int i7 = this.yDia + GameCanvas.hCommand + this.hItem;
            int i8 = this.xDia + (this.wbuff / 2);
            int i9 = 0;
            while (true) {
                if (i9 >= MaxSkillBuff) {
                    break;
                }
                if (!GameCanvas.isPointSelect((i8 + (i9 * this.wbuff)) - 20, i7 - 20, 40, 40)) {
                    i9++;
                } else {
                    mSound.playSound(42, mSound.volumeSound);
                    GameCanvas.isPointerSelect = false;
                    setAutoBuff(i9);
                    break;
                }
            }
        } else if (this.type == 5 && GameCanvas.isPointSelect(0, 0, GameCanvas.w, GameCanvas.h)) {
            this.cmdHelp.perform();
            GameCanvas.isPointerSelect = false;
        }
        if (this.cmdList != null) {
            for (int i10 = 0; i10 < this.cmdList.size(); i10++) {
                ((iCommand) this.cmdList.elementAt(i10)).updatePointer();
            }
        }
    }

    public void setMenuParty() {
        if (Player.party == null) {
            return;
        }
        ObjectParty objectParty = (ObjectParty) Player.party.vecPartys.elementAt(this.idSelect);
        if (objectParty.name.compareTo(GameScreen.player.name) == 0) {
            return;
        }
        mVector mvector = new mVector("MsgDiaLog menu");
        mvector.addElement(new iCommand(T.addFriend, 9, this));
        mvector.addElement(new iCommand(T.trochuyen, 14, this));
        if (GameScreen.player.name.compareTo(Player.party.nameMain) == 0) {
            String str = objectParty.name;
            if (str.length() > 8) {
                str = new StringBuffer().append(str.substring(0, 7)).append("...").toString();
            }
            mvector.addElement(new iCommand(new StringBuffer().append(T.yeucau).append(str).append(" ").append(T.leave).toString(), 6, this));
        }
        GameCanvas.menu2.startAt(mvector, 2, objectParty.name, false, null);
    }

    private void setChucNangParty() {
        if (Player.party == null) {
            return;
        }
        ObjectParty objectParty = (ObjectParty) Player.party.vecPartys.elementAt(this.idSelect);
        mVector mvector = new mVector("MsgChat menu2");
        if (objectParty.name.compareTo(GameScreen.player.name) != 0) {
            String str = objectParty.name;
            if (str.length() > 8) {
                str = new StringBuffer().append(str.substring(0, 7)).append("...").toString();
            }
            mvector.addElement(new iCommand(new StringBuffer().append(T.yeucau).append(" ").append(str).append(" ").append(T.leave).toString(), 6, this));
        }
        mvector.addElement(new iCommand(T.leave, 8, this));
        mvector.addElement(new iCommand(T.mainCancle, 7, this));
        mvector.addElement(new iCommand(T.chatParty, 15, this));
        GameCanvas.menu2.startAt(mvector, 2, T.chucnang, false, null);
    }

    public void closeDialog() {
        if (GameCanvas.currentDialog != null) {
            GameCanvas.currentDialog = null;
        } else {
            GameCanvas.subDialog = null;
        }
    }

    public void setAutoBuff(int i) {
        if (Autobuff[i][1] == 0) {
            if (Player.mCurentLvSkill[Autobuff[i][0]] > 0) {
                Autobuff[i][1] = 1;
                Player.isAutoBuff = (byte) 1;
            } else {
                GameCanvas.start_Ok_Dialog(T.chuahoc);
            }
        } else {
            Autobuff[i][1] = 0;
            Player.isAutoBuff = (byte) 0;
            int i2 = 0;
            while (true) {
                if (i2 >= Autobuff.length) {
                    break;
                }
                if (Autobuff[i2][1] != 1) {
                    i2++;
                } else {
                    Player.isAutoBuff = (byte) 1;
                    break;
                }
            }
        }
        MainRMS.setSaveAuto();
    }

    public void relogin() {
        GlobalLogicHandler.isDisConect = true;
        GlobalLogicHandler.timeReconnect = mSystem.currentTimeMillis() + 30000;
        if (GameCanvas.currentScreen.isGameScr()) {
            if (SelectCharScreen.isSelectOk) {
                GameCanvas.login.Show();
                if (CRes.loadRMS("user_pass") != null) {
                    try {
                        LoginScreen.loadUser_Pass();
                    } catch (Exception e) {
                    }
                    GameCanvas.connect();
                    GlobalService.gI().login(LoginScreen.tfusername.getText(), LoginScreen.tfpassword.getText(), GameMidlet.version, "0", "0", "0", SelectCharScreen.IDCHAR, LoadMap.Area);
                    isAutologin = true;
                    GameScreen.player.resetPlayer();
                    if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
                        GlobalService.gI().send_cmd_server((byte) 61);
                    }
                    closeDialog();
                } else {
                    GameCanvas.login.Show();
                    closeDialog();
                }
            }
        } else if (CRes.loadRMS("user_pass") != null) {
            try {
                LoginScreen.loadUser_Pass();
            } catch (Exception e2) {
            }
            GameCanvas.connect();
            GlobalService.gI().login(LoginScreen.tfusername.getText(), LoginScreen.tfpassword.getText(), GameMidlet.version, "0", "0", "0", SelectCharScreen.IDCHAR, LoadMap.Area);
            isAutologin = true;
            GameScreen.player.resetPlayer();
            if (WorldMapScreen.namePos == null || TabQuest.nameItemQuest == null) {
                GlobalService.gI().send_cmd_server((byte) 61);
            }
            closeDialog();
        } else {
            GameCanvas.login.Show();
            closeDialog();
        }
        GameCanvas.start_Wait_Dialog(T.dangdangnhap, new iCommand(T.close, 7));
        GameCanvas.countLogin = mSystem.currentTimeMillis();
    }

    public static void setMusic() {
        if (mvalueVolume != null) {
            mSound.isMusic = mvalueVolume[0] == 0;
            mSound.isSound = mvalueVolume[1] == 0;
            if (!mSound.isMusic) {
                mSound.pauseCurMusic();
            } else if (LoginScreen.MusicRandom == 0) {
                mSound.playMus(0, mSound.volumeMusic, true);
            } else {
                mSound.playMus(1, mSound.volumeMusic, true);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeByte(mvalueVolume[0]);
                dataOutputStream.writeByte(mvalueVolume[1]);
                CRes.saveRMS("isVolume", byteArrayOutputStream.toByteArray());
                dataOutputStream.close();
            } catch (Exception e) {
            }
        }
    }
}
