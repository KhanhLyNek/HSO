package GameObjects;

import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectCharWearing;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.PopupChat;
import InterfaceComponents.TabConfig;
import InterfaceComponents.TabInfoChar;
import InterfaceComponents.TabMySeftNew;
import InterfaceComponents.TabQuest;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.TabSkillsNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Skill.HotKey;
import Skill.ListSkill;
import Skill.LvSkill;
import Skill.MainListSkill;
import Skill.Skill;
import Skill.SplashSkill;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/Player.class */
public class Player extends MainObject {
    public static iCommand cmdNextFocus;
    public static iCommand cmdRevice;
    public static iCommand cmdVeLang;
    public static long timeSetX2;
    public static AutoGetItem autoItem;
    public static long timeFristSkill;
    public byte timeHit;
    public long timeSendmove;
    public boolean ispaintHit;
    public boolean isMaxdame;
    public int timeCombo;
    public static byte[] ID_HAIR_NO_HAT;
    private PopupChat nameStore;
    public int lastX;
    public int lastY;
    public static int[] mKillPlayer;
    public static int[] mCurentLvSkill;
    public static int[] mPlusLvSkill;
    public String[] mKhangChar;
    public MainInfoItem[] mInfoChar;
    public MainQuest currentQuest;
    public static MainParty party;
    public static int xFocus;
    public static int yFocus;
    public static int timeFocus;
    public static int skillDefault;
    public static int xBeginAutoFire;
    public static int yBeginAutofire;
    public static short[] vecPlayerPk;
    public TabSkillsNew tabskills;
    int coutauto;
    public static byte IndexFire;
    long timeAutoBuff;
    public static short demUnFire;
    int xtam;
    int ytam;
    int counttam;
    int skilltam;
    public static int testloadmap;
    public static int timeStand;
    public static int timeQuest;
    public static byte typeFocusLast;
    public static byte timeFocusLast;
    int indexFocus;
    public static boolean isFocusNPC;
    public static int timeFocusNPC;
    public static boolean isCurAutoFire;
    public static int levelTab = 0;
    public static int PointSucKhoe = 0;
    public static int MaxSkill = 21;
    public static int timeX2 = 0;
    public static int timeResetAuto = 0;
    public static int PointArena = 0;
    public static boolean isNewPlayer = false;
    public static boolean isAutoHPMP = false;
    public static boolean isLockKey = false;
    public static boolean isSendMove = true;
    public static boolean isPhong = false;
    public static byte isAutoFire = 1;
    public static byte isAutoBuff = 0;
    public static boolean isFullInven = false;
    public static DelaySkill[] timeDelaySkill = new DelaySkill[MaxSkill];
    public static DelaySkill[] timeDelayPotion = new DelaySkill[3];
    public static int diemTiemNang = 0;
    public static int diemKyNang = 0;
    public static int[][] mTiemnang = mSystem.new_M_Int(2, 4);
    public static HotKey[][] mhotkey = new HotKey[3][];
    public static short maxInven = 42;
    public static short maxChest = 20;
    public static short maxPet = 24;
    public static byte typeX2 = 0;

    /* JADX WARN: Type inference failed for: r0v23, types: [Skill.HotKey[], Skill.HotKey[][]] */
    static {
        for (int i = 0; i < mhotkey.length; i++) {
            mhotkey[i] = new HotKey[5];
        }
        mKillPlayer = new int[MaxSkill];
        mCurentLvSkill = new int[MaxSkill];
        mPlusLvSkill = new int[MaxSkill];
        xFocus = -1;
        yFocus = -1;
        timeFocus = 0;
        skillDefault = 0;
        xBeginAutoFire = -1;
        yBeginAutofire = -1;
        vecPlayerPk = new short[0];
        IndexFire = (byte) 0;
        demUnFire = (short) 0;
        testloadmap = 0;
        timeStand = 0;
        typeFocusLast = (byte) -1;
        timeFocusLast = (byte) 0;
        isFocusNPC = false;
        timeFocusNPC = 0;
        isCurAutoFire = false;
    }

    @Override // GameObjects.MainObject
    public boolean isMainChar() {
        return true;
    }

    public void doResetLastXY() {
        this.lastX = 1000000;
        this.lastY = 1000000;
    }

    public Player(int i, byte b, String str, int i2, int i3) {
        super(i, b, str, i2, i3);
        this.lastX = 1000000;
        this.lastY = 1000000;
        this.mKhangChar = new String[5];
        this.skilltam = 0;
        this.indexFocus = 0;
        party = null;
        this.typeObject = (byte) 0;
        this.hOne = 40;
        this.wOne = 30;
        this.vMax = 6;
        this.name = str;
        this.wFocus = 140;
        cmdNextFocus = new iCommand(T.next, 0, this);
        cmdRevice = new iCommand(T.hoisinh, 1, this);
        cmdRevice.setPos(iCommand.wButtonCmd / 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdRevice.caption);
        cmdVeLang = new iCommand(T.velang, 2, this);
        cmdVeLang.setPos(GameCanvas.w - (iCommand.wButtonCmd / 2), (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdVeLang.caption);
        this.PlashNow = new SplashSkill();
        this.ListKillNow = new ListSkill();
        isNewPlayer = false;
        this.xsai = 1;
        this.ysai = 2;
        for (int i4 = 0; i4 < timeDelaySkill.length; i4++) {
            timeDelaySkill[i4] = new DelaySkill();
            timeDelaySkill[i4].value = 0;
            timeDelaySkill[i4].timebegin = mSystem.currentTimeMillis();
        }
        for (int i5 = 0; i5 < timeDelayPotion.length; i5++) {
            timeDelayPotion[i5] = new DelaySkill();
            timeDelayPotion[i5].value = 0;
            timeDelayPotion[i5].timebegin = mSystem.currentTimeMillis();
        }
        this.timeSendmove = mSystem.currentTimeMillis();
        this.timeCombo = -1;
    }

    public void init() {
        TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, (byte) 0, T.tabhanhtrang, -1, TabShopNew.NORMAL);
        mVector mvector = new mVector("Player (init) v");
        mvector.addElement(tabShopNew);
        mvector.addElement(new TabMySeftNew(T.tabtrangbi));
        mvector.addElement(new TabInfoChar(T.tabthongtin));
        this.tabskills = new TabSkillsNew(T.tabkynang);
        mvector.addElement(this.tabskills);
        mvector.addElement(new TabQuest(T.tabnhiemvu));
        mVector mvector2 = new mVector("Player mcmdTest");
        if (GameCanvas.isTouch) {
            mvector.addElement(new TabConfig(T.tabchucnang, mvector2, (byte) 11));
        } else {
            mvector.addElement(new TabConfig(T.tabhethong, mvector2, (byte) 7));
            mvector.addElement(new TabConfig(T.tabchucnang, mvector2, (byte) 11));
        }
        GameCanvas.AllInfo.addMoreTab(mvector);
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                nextFocus();
                return;
            case 1:
                GlobalService.gI().gohome((byte) 1);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                return;
            case 2:
                GameCanvas.start_Left_Dialog(T.muonvelang, new iCommand(cmdVeLang.caption, 3, this));
                return;
            case 3:
                GlobalService.gI().gohome((byte) 0);
                GameCanvas.end_Dialog();
                return;
            case 4:
                GlobalService.gI().do_Buy_Sell_Item(4, null, "", (short) 0, 0, (byte) 0);
                return;
            case 5:
                GameCanvas.end_Dialog();
                return;
            default:
                return;
        }
    }

    @Override // GameObjects.MainObject
    public void setPainthit(byte b, boolean z) {
        this.timeHit = b;
        this.isMaxdame = z;
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        paintPlayer(mgraphics, 100);
        paintEffectCharWearing(mgraphics);
        paintNameStore(mgraphics, this.x, this.y);
    }

    @Override // GameObjects.MainObject
    public void paintNameStore(mGraphics mgraphics, int i, int i2) {
        if (this.nameStore != null && !this.nameStore.name.equals("")) {
            this.nameStore.paint(mgraphics);
        }
    }

    @Override // GameObjects.MainObject
    public void paintBigAvatar(mGraphics mgraphics, int i, int i2) {
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void update() {
        try {
            if (this.moveToBoss && this.vx == 0 && this.vy == 0) {
                this.moveToBoss = false;
            }
            if (this.moveToBoss) {
                if (this.vx != 0) {
                    this.vx = 0;
                }
                if (this.vy != 0) {
                    this.vy = 0;
                }
                this.frameLeg = CRes.random(0, 4);
            }
            updateActionPerson();
            if (this.currentQuest == null) {
                setFocus();
            }
            setMove(true);
            updatePlayer();
            updateEye();
            if (party != null && party.update()) {
                party = null;
            }
            updateDelaySkill();
            updateDataEffect();
            if (timeResetAuto > 0) {
                timeResetAuto--;
            }
            if ((this.vx != 0 || this.vy != 0) && !this.isTanHinh && GameCanvas.gameTick % 3 == 0) {
                if (!this.isWater && !this.isFootSnow) {
                    if (this.Direction == 2) {
                        GameScreen.addEffInMap(this.x + CRes.random_Am(0, 3), this.y + CRes.random_Am(0, 3) + mSystem.dyCharStep, 0, this.Direction);
                    } else if (this.Direction == 3) {
                        GameScreen.addEffInMap(this.x + CRes.random_Am(0, 3), (this.y + CRes.random_Am(0, 3)) - mSystem.dyCharStep, 0, this.Direction);
                    } else {
                        GameScreen.addEffInMap(this.x + CRes.random_Am(0, 3), this.y + CRes.random_Am(0, 3), 0, this.Direction);
                    }
                } else {
                    GameScreen.addEffInMap(this.x, this.y, 1, this.Direction);
                }
            }
            updateoverHP_MP();
            updateEffectCharWearing();
            if (this.nameStore != null) {
                this.nameStore.updatePos(this.x, (this.y - this.hOne) - 30);
            }
            super.update();
            if (this.timeHit > 0) {
                this.ispaintHit = true;
                this.timeHit = (byte) (this.timeHit - 1);
            } else {
                this.ispaintHit = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePlayer() {
        HotKey hotKey;
        Item itemInventory;
        Item itemInventory2;
        if (isAutoHPMP && this.Action != 4 && GameCanvas.gameTick % 5 == 0) {
            if ((this.hp * 100) / this.maxHp < MsgDialog.mHPMP[0]) {
                HotKey hotKey2 = mhotkey[levelTab][4];
                if (hotKey2.type == HotKey.POTION && hotKey2.typePotion == 0 && (itemInventory2 = Item.getItemInventory(4, hotKey2.id)) != null && itemInventory2.typePotion < 2 && timeDelayPotion[itemInventory2.typePotion].value <= 0) {
                    GlobalService.gI().Use_Potion((short) itemInventory2.Id);
                    timeDelayPotion[itemInventory2.typePotion].value = ObjectData.ITEM;
                    timeDelayPotion[itemInventory2.typePotion].limit = ObjectData.ITEM;
                    timeDelayPotion[itemInventory2.typePotion].timebegin = mSystem.currentTimeMillis();
                }
            }
            if ((this.mp * 100) / this.maxMp < MsgDialog.mHPMP[1] && (hotKey = mhotkey[levelTab][3]) != null && hotKey.type == HotKey.POTION && hotKey.typePotion == 1 && (itemInventory = Item.getItemInventory(4, hotKey.id)) != null && timeDelayPotion[itemInventory.typePotion].value <= 0) {
                GlobalService.gI().Use_Potion((short) itemInventory.Id);
                timeDelayPotion[itemInventory.typePotion].value = ObjectData.ITEM;
                timeDelayPotion[itemInventory.typePotion].limit = ObjectData.ITEM;
                timeDelayPotion[itemInventory.typePotion].timebegin = mSystem.currentTimeMillis();
            }
        }
        if (this.KillFire != -1) {
            if (CRes.abs(this.x - this.xFire) <= 4 && CRes.abs(this.y - this.yFire) <= 4) {
                this.timeHuyKill++;
                this.posTransRoad = null;
                this.ListKillNow.setFireSkill(this, this.vecObjFocusSkill, this.KillFire, (byte) -1);
                if (this.timeHuyKill > 5) {
                    this.ListKillNow.fireSkillFree();
                    this.timeHuyKill = 0;
                    this.KillFire = -1;
                }
            }
            if (this.posTransRoad == null && (CRes.abs(this.x - this.xFire) > 4 || CRes.abs(this.y - this.yFire) > 4)) {
                Move_to_Focus_Person();
            }
        } else {
            this.timeHuyKill = 0;
        }
        if (this.hp <= 0 && this.Action != 4) {
            resetAction();
            this.Action = 4;
            if (LoadMap.typeMap == 1) {
                GameScreen.gI().center = cmdVeLang;
                cmdVeLang.setPos(GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdVeLang.caption);
            } else if (LoadMap.typeMap == 2) {
                GameScreen.gI().center = cmdRevice;
                cmdRevice.setPos(GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdRevice.caption);
            } else if (!GameScreen.infoGame.isMapThachdau()) {
                GameScreen.gI().left = cmdRevice;
                GameScreen.gI().right = cmdVeLang;
                cmdRevice.setPos(iCommand.wButtonCmd / 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdRevice.caption);
                cmdVeLang.setPos(GameCanvas.w - (iCommand.wButtonCmd / 2), (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdVeLang.caption);
            }
            GameScreen.addEffectEndKill(11, this.x, this.y);
        }
        if (this.currentQuest == null) {
            if (this.Action == 4 && !GameScreen.infoGame.isMapThachdau()) {
                if (LoadMap.typeMap == 1) {
                    if (GameScreen.gI().center != cmdVeLang) {
                        GameScreen.gI().center = cmdVeLang;
                        cmdVeLang.setPos(GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdVeLang.caption);
                    }
                } else if (LoadMap.typeMap == 2) {
                    if (GameScreen.gI().center != cmdRevice) {
                        GameScreen.gI().center = cmdRevice;
                        cmdRevice.setPos(GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdRevice.caption);
                    }
                } else {
                    if (GameScreen.gI().left != cmdRevice) {
                        GameScreen.gI().left = cmdRevice;
                        cmdRevice.setPos(iCommand.wButtonCmd / 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdRevice.caption);
                    }
                    if (GameScreen.gI().right != cmdVeLang) {
                        GameScreen.gI().right = cmdVeLang;
                        cmdVeLang.setPos(GameCanvas.w - (iCommand.wButtonCmd / 2), (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, cmdVeLang.caption);
                    }
                }
            } else if (GameCanvas.isTouch) {
                if (LoadMap.typeMap == 1) {
                    if (GameScreen.gI().center == cmdVeLang) {
                        GameScreen.gI().center = null;
                    }
                } else if (LoadMap.typeMap == 2) {
                    if (GameScreen.gI().center == cmdRevice) {
                        GameScreen.gI().center = null;
                    }
                } else {
                    if (GameScreen.gI().left == cmdRevice) {
                        GameScreen.gI().left = null;
                    }
                    if (GameScreen.gI().right == cmdVeLang) {
                        GameScreen.gI().right = null;
                    }
                }
            } else {
                if (this.center == cmdRevice) {
                    this.center = null;
                }
                if (GameScreen.gI().right == null || GameScreen.gI().right != cmdNextFocus) {
                    GameScreen.gI().right = cmdNextFocus;
                }
                if (GameScreen.gI().left != GameScreen.gI().cmdMenu) {
                    GameScreen.gI().left = GameScreen.gI().cmdMenu;
                }
            }
        }
        if (this.posTransRoad != null && !this.moveToBoss) {
            if (this.isBinded) {
                return;
            }
            if (CRes.abs(this.x - this.toX) <= 6 && CRes.abs(this.y - this.toY) <= 6) {
                if (this.countAutoMove > this.posTransRoad.length - 1) {
                    this.countAutoMove = 0;
                    this.posTransRoad = null;
                    timeStand = 0;
                    this.xStopMove = 0;
                    this.yStopMove = 0;
                } else {
                    if (this.countAutoMove == this.posTransRoad.length - 1 && this.xStopMove > 0 && this.yStopMove > 0) {
                        this.toX = this.xStopMove;
                        this.toY = this.yStopMove;
                    } else {
                        byte b = (byte) (this.posTransRoad[this.countAutoMove] >> 8);
                        byte b2 = (byte) (this.posTransRoad[this.countAutoMove] & 255);
                        this.toX = (b * LoadMap.wTile) + (LoadMap.wTile / 2);
                        this.toY = (b2 * LoadMap.wTile) + (LoadMap.wTile / 2);
                    }
                    this.countAutoMove++;
                }
            }
            move_to_XY();
        } else {
            if (!isSendMove) {
                isSendMove = true;
                this.xStand = this.x;
                this.yStand = this.y;
            }
            if (isLockKey && !GameScreen.help.setStep_Next(0, -4) && !GameScreen.help.setStep_Next(0, -2)) {
                isLockKey = false;
            }
        }
        int distance = MainObject.getDistance(this.xStand, this.yStand, this.x, this.y);
        boolean z = false;
        if (mSystem.currentTimeMillis() - this.timeSendmove > 250 && timeStand <= 0) {
            z = true;
        }
        if (this.posTransRoad != null && mSystem.currentTimeMillis() - this.timeSendmove > 250) {
            z = true;
        }
        if (z || (timeStand > 20 && distance >= 5 && this.posTransRoad == null)) {
            if (isSendMove && !this.isMoveOut) {
                GlobalService.gI().player_move((short) this.x, (short) this.y);
                this.timeSendmove = mSystem.currentTimeMillis();
            }
            this.xStand = this.x;
            this.yStand = this.y;
            this.countSendMove = 0;
        }
        if (autoItem != null && GameCanvas.gameTick % 5 == 0) {
            autoGetItem();
        }
        if (isAutoFire == 1 && this.posTransRoad == null) {
            if (PointSucKhoe <= 0) {
                isAutoFire = (byte) 0;
            }
            if (isAutoFire == 1) {
                if (this.Action == 0) {
                    this.coutauto++;
                } else {
                    this.coutauto = 0;
                }
                if (this.coutauto > 500) {
                    GameCanvas.addInfoChar(T.farfocus);
                    this.KillFire = -1;
                    this.IDAttack = -1;
                    this.vecObjFocusSkill = null;
                    this.coutauto = 0;
                    dofire();
                }
            }
            if (this.Action != 2 && this.Action != 4 && (MainObject.getDistance(this.x, this.y, xBeginAutoFire, yBeginAutofire) > (this.wFocus * 3) / 2 || demUnFire > 100)) {
                this.toX = this.x;
                this.toY = this.y;
                this.xStopMove = xBeginAutoFire;
                this.yStopMove = yBeginAutofire;
                this.posTransRoad = GameCanvas.game.updateFindRoad(this.xStopMove / LoadMap.wTile, this.yStopMove / LoadMap.wTile, this.x / LoadMap.wTile, this.y / LoadMap.wTile, 20);
                xFocus = -1;
                yFocus = -1;
                if (this.posTransRoad != null && this.posTransRoad.length > 20) {
                    this.posTransRoad = null;
                }
            }
            if (this.posTransRoad == null && this.Action != 2 && this.Action != 4) {
                setAutoFire();
            }
            if (this.Action == 4) {
                setCurAutoFire();
            }
        }
        if (GameScreen.ObjFocus != null) {
            typeFocusLast = GameScreen.ObjFocus.typeObject;
            timeFocusLast = (byte) 0;
        } else if (timeFocusLast < 20) {
            timeFocusLast = (byte) (timeFocusLast + 1);
        }
        if ((this.vx != 0 || this.vy != 0) && timeFocusNPC < 5) {
            timeFocusNPC = 10;
        }
        if (!isFocusNPC && timeFocusNPC > 5) {
            timeFocusNPC++;
            if (timeFocusNPC > 60) {
                isFocusNPC = true;
                timeFocusNPC = 0;
            }
        }
        if (isAutoFire == 1 && GameScreen.ObjFocus != null && MainObject.getDistance(xBeginAutoFire, yBeginAutofire, GameScreen.ObjFocus.x, GameScreen.ObjFocus.y) > 240) {
            nextMonster();
        }
    }

    private void autoGetItem() {
        if (GameCanvas.gameTick % 200 == 0) {
            int size = Item.VecInvetoryPlayer.size();
            if (isFullInven) {
                if (size < maxInven) {
                    isFullInven = false;
                } else {
                    GameCanvas.addInfoChar(T.fullInven);
                    if (GameScreen.ObjFocus != null && (GameScreen.ObjFocus.typeObject == 4 || GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 5 || GameScreen.ObjFocus.typeObject == 7 || GameScreen.ObjFocus.typeObject == 3)) {
                        nextFocus();
                    }
                }
            } else if (size >= maxInven) {
                isFullInven = true;
            }
        }
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (mainObject != null && !mainObject.isSend && (mainObject.typeObject == 4 || mainObject.typeObject == 3 || mainObject.typeObject == 5 || mainObject.typeObject == 7)) {
                if (setGetItem(mainObject) && MainObject.getDistance(this.x, this.y, mainObject.x, mainObject.y) < this.wFocus) {
                    GlobalService.gI().Get_Item_Map((short) mainObject.ID, mainObject.typeObject);
                }
                mainObject.isSend = true;
            }
        }
    }

    public boolean isItem(MainObject mainObject) {
        return mainObject.typeObject == 4 || mainObject.typeObject == 3 || mainObject.typeObject == 5 || mainObject.typeObject == 7;
    }

    private boolean setGetItem(MainObject mainObject) {
        switch (mainObject.typeObject) {
            case 3:
                if (!isFullInven && autoItem.valueColorItem != -1 && mainObject.colorName >= autoItem.valueColorItem) {
                    return true;
                }
                return false;
            case 4:
                if (mainObject.colorName == 0) {
                    if (autoItem.isGetPotion == AutoGetItem.POI_NHAT_MAU || autoItem.isGetPotion == AutoGetItem.POI_NHAT_HET) {
                        return true;
                    }
                    return false;
                }
                if (mainObject.colorName == 1) {
                    if (autoItem.isGetPotion == AutoGetItem.POI_NHAT_NANG_LUONG || autoItem.isGetPotion == AutoGetItem.POI_NHAT_HET) {
                        return true;
                    }
                    return false;
                }
                if (mainObject.colorName != 2 && mainObject.colorName == 6) {
                    return true;
                }
                return true;
            case 5:
            case 7:
                return true;
            case 6:
            default:
                return false;
        }
    }

    public void setAutoFire() {
        if (!ListSkill.canAttack()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        MainObject mainObject = GameScreen.ObjFocus;
        if (mainObject == null || mainObject.Action == 4 || mainObject.typeObject != 1 || mainObject.isSend || mainObject.isDie) {
            if (mainObject != null && mainObject.isDie) {
                GameScreen.ObjFocus = null;
            }
            nextMonster();
        }
        if (GameScreen.ObjFocus == null || GameScreen.ObjFocus.typeObject != 1 || GameScreen.ObjFocus.typeBoss == 2) {
            return;
        }
        if (isAutoBuff == 1 && (currentTimeMillis - this.timeAutoBuff) / ListSkill.limitTimeAtt > 2) {
            this.timeAutoBuff = currentTimeMillis;
            for (int i = 0; i < MsgDialog.MaxSkillBuff; i++) {
                if (MsgDialog.Autobuff[i][1] != 0 && setDelaySkill(MsgDialog.Autobuff[i][0], -1)) {
                    Skill skillFormId = MainListSkill.getSkillFormId(MsgDialog.Autobuff[i][0]);
                    if (skillFormId.typeSkill == 1) {
                        setAutoBuff(skillFormId.typeBuff, MsgDialog.Autobuff[i][0], GameScreen.ObjFocus);
                    }
                }
            }
        }
        if (IndexFire < mhotkey[0].length - 1) {
            for (int i2 = IndexFire + 1; i2 < mhotkey[0].length; i2++) {
                HotKey hotKey = mhotkey[levelTab][i2];
                if (hotKey.type == HotKey.SKILL && setDelaySkill(hotKey.id, -1)) {
                    setActionHotKey(i2, false);
                    timeFristSkill = currentTimeMillis;
                    IndexFire = (byte) i2;
                    return;
                }
            }
        }
        if (IndexFire > 0) {
            for (int i3 = 0; i3 <= IndexFire; i3++) {
                HotKey hotKey2 = mhotkey[levelTab][i3];
                if (hotKey2.type == HotKey.SKILL && setDelaySkill(hotKey2.id, -1)) {
                    setActionHotKey(i3, false);
                    timeFristSkill = currentTimeMillis;
                    IndexFire = (byte) i3;
                    return;
                }
            }
        }
        if (setDelaySkill(0, -1)) {
            setActionHotKey(-1, false);
            timeFristSkill = currentTimeMillis;
        }
    }

    /** Prefer normal monsters at own level or 1-3 levels below. */
    private boolean selectLyMeowzLevelTarget() {
        if (GameCanvas.game == null || !GameCanvas.game.isLyMeowzIntelligenceEnabled())
            return false;
        // Respect the native auto-combat cancellation and never retarget while dead.
        if (!isCurAutoFire || isAutoFire < 0 || this.hp <= 0 || this.Action == 4)
            return true;
        MainObject best = null;
        int bestDistance = 2147483647;
        int bestCrowding = 2147483647;
        int range = (this.wFocus * 3) / 2;
        // A smaller crowd penalty than a full screen prevents chasing distant mobs.
        int crowdPenalty = range / 4;
        for (int n = 0; n < GameScreen.Vecplayers.size(); n++) {
            MainObject mob = (MainObject) GameScreen.Vecplayers.elementAt(n);
            if (mob == null || mob.typeObject != 1 || mob.typeBoss != 0 ||
                    mob.typeMonster != 0 || mob.Action == 4 || mob.isDie || mob.isSend ||
                    mob.hp <= 0 || mob.Lv > this.Lv || mob.Lv < this.Lv - 3)
                continue;
            int distance = MainObject.getDistance(this.x, this.y, mob.x, mob.y);
            if (distance >= range) continue;
            // Prefer quieter visible spots; never switch zones or move automatically here.
            int nearbyPlayers = 0;
            for (int j = 0; j < GameScreen.Vecplayers.size(); j++) {
                MainObject other = (MainObject) GameScreen.Vecplayers.elementAt(j);
                if (other != null && other != this && other.typeObject == 0 &&
                        other.Action != 4 &&
                        MainObject.getDistance(mob.x, mob.y, other.x, other.y) < 160) {
                    nearbyPlayers++;
                    // Beyond four nearby players this spot is already heavily contested.
                    if (nearbyPlayers >= 4) break;
                }
            }
            if (best == null ||
                    distance + nearbyPlayers * crowdPenalty < bestDistance + bestCrowding * crowdPenalty ||
                    (distance + nearbyPlayers * crowdPenalty == bestDistance + bestCrowding * crowdPenalty && distance < bestDistance)) {
                best = mob;
                bestDistance = distance;
                bestCrowding = nearbyPlayers;
            }
        }
        if (best != null) {
            PaintInfoGameScreen.isPaintInfoFocus = true;
            GameScreen.ObjFocus = best;
            demUnFire = (short) 0;
        } else {
            PaintInfoGameScreen.isPaintInfoFocus = false;
            GameScreen.ObjFocus = null;
            demUnFire = (short) (demUnFire + 1);
        }
        return true;
    }

    public void nextMonster() {
        if (selectLyMeowzLevelTarget()) return;
        int distance;
        if (!isCurAutoFire) {
            isAutoFire = (byte) -1;
            return;
        }
        int i = (this.wFocus * 3) / 2;
        MainObject mainObject = null;
        for (int i2 = 0; i2 < GameScreen.Vecplayers.size(); i2++) {
            MainObject mainObject2 = (MainObject) GameScreen.Vecplayers.elementAt(i2);
            if (mainObject2 != null && mainObject2.Action != 4 && !mainObject2.isSend && mainObject2.typeObject == 1 && !mainObject2.isLuaThieng() && ((distance = MainObject.getDistance(this.x, this.y, mainObject2.x, mainObject2.y)) < i || mainObject2.typeBoss == 2)) {
                i = distance;
                mainObject = mainObject2;
                if (mainObject2.typeBoss == 2) {
                    break;
                }
            }
        }
        if (mainObject != null) {
            PaintInfoGameScreen.isPaintInfoFocus = true;
            GameScreen.ObjFocus = mainObject;
            demUnFire = (short) 0;
        } else {
            PaintInfoGameScreen.isPaintInfoFocus = false;
            demUnFire = (short) (demUnFire + 1);
        }
    }

    public void updateKey() {
        if (!Canmove() || LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END || isLockKey) {
            return;
        }
        if (this.currentQuest != null) {
            if (!GameCanvas.menu2.isShowMenu) {
                timeQuest++;
                if (timeQuest > 100) {
                    this.currentQuest = null;
                    GameScreen.gI().center = null;
                    GameCanvas.clearKeyHold();
                    GameCanvas.isPointerSelect = false;
                }
            } else {
                timeQuest = 0;
            }
        } else {
            if (this.Action != 4 && !this.isTanHinh) {
                if (!this.isBinded && !this.isSleep && !this.isStun && !this.isMoveOut && !this.isDongBang && this.Action != 2 && this.Action != 3) {
                    this.vx = 0;
                    this.vy = 0;
                    if (GameCanvas.keyMove(2)) {
                        if (!isSelling()) {
                            this.Action = 1;
                            this.Direction = 2;
                            this.vx = -(this.vMax + getVmount());
                            this.vy = 0;
                            resetMove();
                            setValueAuto(0);
                        } else {
                            mVector mvector = new mVector();
                            mvector.addElement(new iCommand(T.yes, 4, this));
                            mvector.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mvector);
                        }
                    } else if (GameCanvas.keyMove(3)) {
                        if (!isSelling()) {
                            this.Action = 1;
                            this.Direction = 3;
                            this.vx = this.vMax + getVmount();
                            this.vy = 0;
                            resetMove();
                            setValueAuto(0);
                        } else {
                            mVector mvector2 = new mVector();
                            mvector2.addElement(new iCommand(T.yes, 4, this));
                            mvector2.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mvector2);
                        }
                    } else if (GameCanvas.keyMove(1)) {
                        if (!isSelling()) {
                            this.Action = 1;
                            this.Direction = 1;
                            this.vy = -(this.vMax + getVmount());
                            this.vx = 0;
                            resetMove();
                            setValueAuto(0);
                        } else {
                            mVector mvector3 = new mVector();
                            mvector3.addElement(new iCommand(T.yes, 4, this));
                            mvector3.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mvector3);
                        }
                    } else if (GameCanvas.keyMove(0)) {
                        if (!isSelling()) {
                            this.Action = 1;
                            this.Direction = 0;
                            this.vy = this.vMax + getVmount();
                            this.vx = 0;
                            resetMove();
                            setValueAuto(0);
                        } else {
                            mVector mvector4 = new mVector();
                            mvector4.addElement(new iCommand(T.yes, 4, this));
                            mvector4.addElement(new iCommand(T.cancel, 5, this));
                            GameCanvas.start_Select_Dialog(T.can_not_move, mvector4);
                        }
                    }
                    if (this.vx == 0 && this.vy == 0) {
                        timeStand++;
                    } else {
                        timeStand = 0;
                    }
                }
                if (GameCanvas.keyMyHold[10] && !GameCanvas.isTouch) {
                    GameCanvas.clearKeyHold(10);
                    ChatTextField.isShow = !ChatTextField.isShow;
                }
                if (GameCanvas.keyMyHold[11] && !GameCanvas.isTouch) {
                    GameCanvas.clearKeyHold(11);
                    GameCanvas.mevent.init();
                    GameCanvas.mevent.Show(GameCanvas.currentScreen);
                }
            }
            if (GameCanvas.keyMyPressed[21]) {
                setActionHotKey(0, true);
            } else if (GameCanvas.keyMyPressed[23]) {
                setActionHotKey(1, true);
            } else if (GameCanvas.keyMyHold[5] || GameCanvas.keyMyPressed[25]) {
                setActionHotKey(2, true);
            } else if (GameCanvas.keyMyPressed[27]) {
                setActionHotKey(3, true);
            } else if (GameCanvas.keyMyPressed[29]) {
                setActionHotKey(4, true);
            }
        }
        if (GameCanvas.keyMyHold[20]) {
            if ((GameScreen.ObjFocus == null || GameScreen.ObjFocus.typeBoss != 2) && PaintInfoGameScreen.timeChange == 0) {
                PaintInfoGameScreen.timeChange = 1;
            }
            GameCanvas.clearKeyHold(20);
        }
    }

    public void setFocus() {
        if (isAutoFire == 1) {
            return;
        }
        if (GameScreen.ObjFocus != null && (GameScreen.ObjFocus.typeBoss == 2 || GameScreen.ObjFocus.isLuaThieng())) {
            return;
        }
        if (GameScreen.ObjFocus != null) {
            if ((GameScreen.ObjFocus.Action == 4 && GameScreen.ObjFocus.typeObject != 0) || MainObject.getDistance(GameScreen.ObjFocus.x, GameScreen.ObjFocus.y, this.x, this.y) > this.wFocus + 20 || (GameScreen.ObjFocus.isDie && GameScreen.ObjFocus.typeObject == 1)) {
                if (!GameScreen.infoGame.isMapThachdau()) {
                    GameScreen.ObjFocus = null;
                    PaintInfoGameScreen.isPaintInfoFocus = false;
                }
            } else if (!GameCanvas.isTouch && (GameScreen.ObjFocus.typeObject == 2 || (GameScreen.ObjFocus.typeObject == 0 && !setFirePlayer(GameScreen.ObjFocus) && GameScreen.gI().center != GameScreen.gI().cmdGiaotiep && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang())))) {
                GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
            }
        }
        if (GameScreen.ObjFocus == null) {
            if (GameScreen.gI().center != null) {
                GameScreen.gI().center = null;
            }
            int size = GameScreen.Vecplayers.size();
            if (this.indexFocus > size - 1) {
                this.indexFocus = size - 1;
            }
            int i = 1000;
            int i2 = -1;
            MainObject mainObject = null;
            for (int i3 = 0; i3 < size; i3++) {
                int i4 = 0;
                MainObject mainObject2 = (MainObject) GameScreen.Vecplayers.elementAt((i3 + this.indexFocus) % size);
                if (mainObject2 != null) {
                    i4 = MainObject.getDistance(mainObject2.x, mainObject2.y, this.x, this.y);
                }
                if (i4 <= this.wFocus) {
                    int typeFocus = setTypeFocus(mainObject2.typeObject);
                    if (mainObject2.ID != GameScreen.player.ID && ((mainObject2.Action != 4 || mainObject2.typeObject == 0) && !mainObject2.isRemove && typeFocus >= i2 && ((!mainObject2.isDie || mainObject2.typeObject != 1) && mainObject2.typeObject != 9 && !mainObject2.isLuaThieng() && !mainObject2.isDongBang && ((i4 < this.wFocus || (mainObject != null && ((mainObject.typeObject == 0 || mainObject.typeObject == 2) && (mainObject2.typeObject == 3 || mainObject2.typeObject == 1 || mainObject2.typeObject == 4 || mainObject2.typeObject == 5 || mainObject2.typeObject == 7)))) && ((i4 < i || typeFocus > i2) && ((mainObject2.typeObject != 0 && mainObject2.typeObject != 2) || timeFocusLast >= 20 || typeFocusLast == 0 || typeFocusLast == 2)))))) {
                        mainObject = mainObject2;
                        i = i4;
                        this.indexFocus = i3;
                        i2 = typeFocus;
                    }
                }
            }
            if (mainObject != null) {
                if (!mainObject.canFocus()) {
                    return;
                }
                GameScreen.ObjFocus = mainObject;
                if (!GameCanvas.isTouch) {
                    if ((GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 2) || (GameScreen.ObjFocus.typeObject == 0 && !setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()))) {
                        GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                    } else if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                        GameScreen.gI().center = null;
                    }
                    GameScreen.gI().right = cmdNextFocus;
                    return;
                }
                return;
            }
            if (GameScreen.gI().right == cmdNextFocus) {
                GameScreen.gI().right = null;
            }
        }
    }

    public boolean setStatusPk(MainObject mainObject) {
        if (GameCanvas.loadmap.mapLang() || mainObject.typeObject == 2 || this.typePk == -1 || this.typePk == 10) {
            return true;
        }
        if ((mainObject.typeObject == 1 && mainObject.typeMonster == 7) || mainObject.isMonstervantieu()) {
            return true;
        }
        if (mainObject.typeObject != 0) {
            return false;
        }
        if (mainObject.typePk == 0 || mainObject.typePk == 10) {
            return true;
        }
        if ((mainObject.typePk != this.typePk && mainObject.typePk != -1) || this.typePk == 0) {
            return true;
        }
        return false;
    }

    private int setTypeFocus(int i) {
        int i2 = -1;
        switch (i) {
            case 0:
            case 2:
                i2 = 0;
                break;
            case 1:
                i2 = 1;
                break;
            case 3:
            case 4:
            case 5:
            case 7:
                i2 = 2;
                break;
        }
        return i2;
    }

    public void nextFocus() {
        if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeBoss == 2) {
            return;
        }
        setValueAuto(0);
        int i = -1;
        if (isFocusNPC) {
            for (int i2 = 0; i2 < GameScreen.Vecplayers.size(); i2++) {
                MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i2);
                if ((!GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap) || mainObject.typePk != this.typePk || mainObject.typeObject != 0) && mainObject.isNPC()) {
                    if (GameScreen.ObjFocus != null && mainObject == GameScreen.ObjFocus) {
                        i = i2;
                    } else if ((0 == 0 || i >= 0) && MainObject.getDistance(mainObject.x, mainObject.y, this.x, this.y) < this.wFocus) {
                        this.indexFocus = i2;
                        GameScreen.ObjFocus = mainObject;
                        if (!GameCanvas.isTouch) {
                            if (mainObject.typeObject == 2 || (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 0 && !setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()))) {
                                GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                            } else if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                                GameScreen.gI().center = null;
                            }
                        }
                        isFocusNPC = false;
                        return;
                    }
                }
            }
        }
        MainObject mainObject2 = null;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < GameScreen.Vecplayers.size(); i5++) {
            MainObject mainObject3 = (MainObject) GameScreen.Vecplayers.elementAt(i5);
            if (mainObject3 != GameScreen.player && ((mainObject3.Action != 4 || mainObject3.typeObject == 0) && setStatusPk(mainObject3) && mainObject3.typeObject != 9 && mainObject3.typeObject != 10 && !mainObject3.isLuaThieng() && !mainObject3.isDongBang && mainObject3.canFocus() && ((!PaintInfoGameScreen.isMarket(GameCanvas.loadmap.idMap) || mainObject3.typeObject != 0 || mainObject3.isSelling()) && (!isItem(mainObject3) || autoItem == null)))) {
                if (GameScreen.ObjFocus != null && mainObject3 == GameScreen.ObjFocus) {
                    i4 = i5;
                } else if ((mainObject2 == null || i4 >= 0) && MainObject.getDistance(mainObject3.x, mainObject3.y, this.x, this.y) < this.wFocus) {
                    mainObject2 = mainObject3;
                    i3 = i5;
                }
                if (i4 >= 0 && i3 > i4) {
                    this.indexFocus = i5;
                    GameScreen.ObjFocus = mainObject2;
                    if (!GameCanvas.isTouch) {
                        if (mainObject2.typeObject == 2 || (GameScreen.ObjFocus.typeObject == 0 && !setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()))) {
                            GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                            return;
                        } else {
                            if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                                GameScreen.gI().center = null;
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
            }
        }
        if (mainObject2 != null) {
            GameScreen.ObjFocus = mainObject2;
            if (!GameCanvas.isTouch) {
                if (mainObject2.typeObject == 2 || (GameScreen.ObjFocus.typeObject == 0 && !setFirePlayer(GameScreen.ObjFocus) && (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()))) {
                    GameScreen.gI().center = GameScreen.gI().cmdGiaotiep;
                } else if (GameScreen.gI().center == GameScreen.gI().cmdGiaotiep) {
                    GameScreen.gI().center = null;
                }
            }
        }
    }

    public void setActionHotKey(int i, boolean z) {
        HotKey hotKey;
        GameCanvas.clearAll();
        if (this.Action != 4 && GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 1 && GameScreen.ObjFocus.typeBoss == 2) {
            if (i >= 0 && i <= 5) {
                GlobalService.gI().Mon_Capchar((byte) i);
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 1) {
            if (GameScreen.ObjFocus.isMonstervantieu()) {
                if (GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                    GameScreen.gI().cmdInfoVantieu.perform();
                    return;
                }
                if (!GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                    if (this.typePk != 0 && !isPkVantieu()) {
                        return;
                    }
                    if (this.typePk == 11) {
                        if (!GameScreen.ObjFocus.isMonsterVantieuDen()) {
                            return;
                        }
                    } else if (this.typePk == 12) {
                        if (!GameScreen.ObjFocus.isMonsterVantieuTrang()) {
                            return;
                        }
                    } else if (this.typePk == 13 && !GameScreen.ObjFocus.isMonsterVantieuDen()) {
                        return;
                    }
                }
            }
            if (GameScreen.ObjFocus.isMiNuong()) {
                GameScreen.gI().cmdinfoMiNuong.perform();
                return;
            }
        }
        if (i == -1) {
            hotKey = new HotKey();
            hotKey.setHotKey(0, HotKey.SKILL, (byte) 0);
        } else {
            hotKey = mhotkey[levelTab][i];
        }
        if (hotKey.type == HotKey.SKILL) {
            skillDefault = hotKey.id;
        }
        if (hotKey.type == HotKey.POTION) {
            Item itemInventory = Item.getItemInventory(4, hotKey.id);
            if (itemInventory != null && itemInventory.typePotion < 2) {
                if (timeDelayPotion[itemInventory.typePotion].value <= 0) {
                    if (itemInventory.typePotion == 0 && this.hp == this.maxHp) {
                        return;
                    }
                    if (itemInventory.typePotion != 1 || this.mp != this.maxMp) {
                        GlobalService.gI().Use_Potion((short) itemInventory.Id);
                        timeDelayPotion[itemInventory.typePotion].value = ObjectData.ITEM;
                        timeDelayPotion[itemInventory.typePotion].limit = ObjectData.ITEM;
                        timeDelayPotion[itemInventory.typePotion].timebegin = mSystem.currentTimeMillis();
                        return;
                    }
                    return;
                }
                return;
            }
            if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 2 && i == 2) {
                GameScreen.gI().cmdGiaotiep.perform();
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus == null) {
            if (hotKey.type == HotKey.SKILL && setDelaySkill(hotKey.id, i)) {
                Skill skillFormId = MainListSkill.getSkillFormId(hotKey.id);
                if (skillFormId.typeSkill == 1) {
                    if (skillFormId.typeBuff == 1 || skillFormId.typeBuff == 2) {
                        this.posTransRoad = null;
                        this.IDAttack = this.ID;
                        mVector mvector = new mVector("Player vec");
                        mvector.addElement(new Object_Effect_Skill((short) this.ID, this.typeObject));
                        this.ListKillNow.setFireSkill(this, mvector, hotKey.id, (byte) -1);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 4 || GameScreen.ObjFocus.typeObject == 5 || GameScreen.ObjFocus.typeObject == 7) {
            if (!GameScreen.ObjFocus.isSend) {
                GlobalService.gI().Get_Item_Map((short) GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject);
                GameScreen.ObjFocus.isSend = true;
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus.typeObject == 2 && i == 2) {
            GameScreen.gI().cmdGiaotiep.perform();
            return;
        }
        if (this.Action == 2 || this.Action == 4 || this.currentQuest != null || this.isTanHinh) {
            if (this.Action == 4 && GameScreen.ObjFocus.typeObject == 0 && i == 2) {
                if (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()) {
                    GameScreen.gI().cmdGiaotiep.perform();
                    return;
                }
                return;
            }
            return;
        }
        if (hotKey.type == HotKey.SKILL && setDelaySkill(hotKey.id, i)) {
            Skill skillFormId2 = MainListSkill.getSkillFormId(hotKey.id);
            if (skillFormId2.typeSkill == 1) {
                if (setAutoBuff(skillFormId2.typeBuff, hotKey.id, GameScreen.ObjFocus)) {
                    return;
                }
            } else if (skillFormId2.typeSkill == 0 && GameScreen.ObjFocus.Action != 4 && GameScreen.ObjFocus.typeObject != 2) {
                if (setFirePlayer(GameScreen.ObjFocus)) {
                    setStartSkill(hotKey.id, z);
                    return;
                }
                if (GameScreen.ObjFocus.typeObject == 1 && GameScreen.ObjFocus.typeMonster == 7 && this.myClan != null && GameScreen.ObjFocus.myClan != null && this.myClan.IdClan == GameScreen.ObjFocus.myClan.IdClan) {
                    mVector mvector2 = new mVector("Player vec2");
                    mvector2.addElement(new Object_Effect_Skill((short) GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject));
                    mSystem.println("Att 1");
                    GlobalService.gI().fire_monster(mvector2, (byte) 0);
                    if (isAutoFire == 1) {
                        setCurAutoFire();
                        return;
                    }
                    return;
                }
            }
        }
        if (GameScreen.ObjFocus != null && GameScreen.ObjFocus.typeObject == 0 && i == 2 && !setFirePlayer(GameScreen.ObjFocus)) {
            if (GameScreen.ObjFocus.isSelling()) {
                GameScreen.gI().cmdplayerStore.perform();
            } else if (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()) {
                GameScreen.gI().cmdGiaotiep.perform();
            }
        }
    }

    public void setStartSkill(int i, boolean z) {
        this.posTransRoad = null;
        if (GameScreen.ObjFocus == null) {
            return;
        }
        this.IDAttack = GameScreen.ObjFocus.ID;
        mVector mvector = new mVector("Player vec8");
        mvector.addElement(new Object_Effect_Skill((short) GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject));
        this.ListKillNow.setFireSkill(this, mvector, i, (byte) -1);
        timeFristSkill = System.currentTimeMillis();
        if (GameScreen.ObjFocus.typeObject == 1 && isAutoFire == 0) {
            isAutoFire = (byte) 1;
            isCurAutoFire = true;
            xBeginAutoFire = this.x;
            yBeginAutofire = this.y;
        }
    }

    public boolean setDelaySkill(int i, int i2) {
        if (mCurentLvSkill[i] - 1 < 0) {
            if (i2 >= 0) {
                if (i2 == 2) {
                    mhotkey[levelTab][i2].setHotKey(0, HotKey.SKILL, (byte) 0);
                    return false;
                }
                mhotkey[levelTab][i2].type = HotKey.NULL;
                return false;
            }
            return false;
        }
        if (timeDelaySkill[i].value > 0) {
            return false;
        }
        if (this.mp < MainListSkill.getSkillFormId(i).mLvSkill[(mCurentLvSkill[i] + mPlusLvSkill[i]) - 1].mpLost) {
            return false;
        }
        if (this.typeMount == 0) {
            GameCanvas.addInfoChar(T.TisNguaNau);
            return false;
        }
        if (this.KillFire == i) {
            return false;
        }
        return true;
    }

    public mVector setSkillLan(int i, MainObject mainObject) {
        mVector mvector = new mVector("Player vec7");
        mvector.addElement(new Object_Effect_Skill((short) mainObject.ID, mainObject.typeObject));
        byte b = mainObject.typeObject;
        Skill skillFormId = MainListSkill.getSkillFormId(i);
        if (mCurentLvSkill[skillFormId.Id] == 0) {
            return mvector;
        }
        if (skillFormId.typeSkill == 1) {
            this.ListKillNow.classbuff = this.clazz;
            this.ListKillNow.typebuff = skillFormId.typeBuff;
            if (skillFormId.mLvSkill[(mCurentLvSkill[skillFormId.Id] + mPlusLvSkill[skillFormId.Id]) - 1].nTarget > 0 && (b == 0 || skillFormId.typeBuff == 3)) {
                int i2 = 1;
                for (int i3 = 0; i3 < GameScreen.Vecplayers.size(); i3++) {
                    MainObject mainObject2 = (MainObject) GameScreen.Vecplayers.elementAt(i3);
                    if (mainObject2.ID != mainObject.ID && mainObject2 != GameScreen.player && b == mainObject2.typeObject && mainObject2.Action != 4 && (b != 0 || ((skillFormId.typeBuff != 3 || setFirePlayer(mainObject2)) && (skillFormId.typeBuff != 2 || !setFirePlayer(mainObject2))))) {
                        if (MainObject.getDistance(mainObject.x, mainObject.y, mainObject2.x, mainObject2.y) <= skillFormId.mLvSkill[(mCurentLvSkill[skillFormId.Id] + mPlusLvSkill[skillFormId.Id]) - 1].range_lan) {
                            mvector.addElement(new Object_Effect_Skill((short) mainObject2.ID, mainObject2.typeObject));
                            i2++;
                        }
                        if (i2 >= skillFormId.mLvSkill[(mCurentLvSkill[skillFormId.Id] + mPlusLvSkill[skillFormId.Id]) - 1].nTarget) {
                            return mvector;
                        }
                    }
                }
            }
        } else if (skillFormId.mLvSkill[(mCurentLvSkill[skillFormId.Id] + mPlusLvSkill[skillFormId.Id]) - 1].nTarget > 1) {
            int i4 = 1;
            for (int i5 = 0; i5 < GameScreen.Vecplayers.size(); i5++) {
                MainObject mainObject3 = (MainObject) GameScreen.Vecplayers.elementAt(i5);
                if (mainObject3.ID != mainObject.ID && mainObject3 != GameScreen.player && b == mainObject3.typeObject && mainObject3.Action != 4 && (b != 0 || setFirePlayer(mainObject3))) {
                    if (MainObject.getDistance(mainObject.x, mainObject.y, mainObject3.x, mainObject3.y) <= skillFormId.mLvSkill[(mCurentLvSkill[skillFormId.Id] + mPlusLvSkill[skillFormId.Id]) - 1].range_lan) {
                        mvector.addElement(new Object_Effect_Skill((short) mainObject3.ID, mainObject3.typeObject));
                        i4++;
                    }
                    if (i4 >= skillFormId.mLvSkill[(mCurentLvSkill[skillFormId.Id] + mPlusLvSkill[skillFormId.Id]) - 1].nTarget) {
                        return mvector;
                    }
                }
            }
        }
        return mvector;
    }

    public void setAddDelaySkill(int i, int i2) {
        if (i == 35) {
            for (int i3 = 0; i3 < mKillPlayer.length; i3++) {
                timeDelaySkill[i3].value = ObjectData.ITEM;
                timeDelaySkill[i3].limit = ObjectData.ITEM;
                timeDelaySkill[i3].timebegin = mSystem.currentTimeMillis();
            }
            return;
        }
        if (mKillPlayer[i2] == i) {
            LvSkill lvSkill = MainListSkill.getSkillFormId(i2).mLvSkill[(mCurentLvSkill[i2] + mPlusLvSkill[i2]) - 1];
            timeDelaySkill[i2].value = lvSkill.getdelay();
            timeDelaySkill[i2].limit = lvSkill.getdelay();
            timeDelaySkill[i2].typeSkill = HotKey.SKILL;
            timeDelaySkill[i2].timebegin = mSystem.currentTimeMillis();
            if (lvSkill.mpLost > 0) {
                this.mp -= lvSkill.mpLost;
            }
            Skill skillFormId = MainListSkill.getSkillFormId(i2);
            if (skillFormId != null && skillFormId.typeSkill == 0) {
                timeDelaySkill[i2].isSkillAttack = true;
            }
        }
    }

    public void resetCoolDown() {
        for (int i = 0; i < timeDelaySkill.length; i++) {
            if (timeDelaySkill[i].isSkillAttack) {
                timeDelaySkill[i].value = 0;
            }
        }
    }

    public void updateDelaySkill() {
        for (int i = 0; i < timeDelaySkill.length; i++) {
            if (timeDelaySkill[i] != null && timeDelaySkill[i].value > -150) {
                timeDelaySkill[i].value -= (int) (mSystem.currentTimeMillis() - timeDelaySkill[i].timebegin);
                timeDelaySkill[i].timebegin = mSystem.currentTimeMillis();
            }
        }
        for (int i2 = 0; i2 < timeDelayPotion.length; i2++) {
            if (timeDelayPotion[i2].value > -150) {
                timeDelayPotion[i2].value -= (int) (mSystem.currentTimeMillis() - timeDelayPotion[i2].timebegin);
                timeDelayPotion[i2].timebegin = mSystem.currentTimeMillis();
            }
        }
    }

    public void resetMove() {
        if (this.KillFire != -1) {
            this.KillFire = -1;
            this.posTransRoad = null;
            this.toX = this.x;
            this.toY = this.y;
            this.xStopMove = 0;
            this.yStopMove = 0;
        }
    }

    public boolean setFirePlayer(MainObject mainObject) {
        if (mainObject.canfire()) {
            return true;
        }
        if (mainObject.isLuaThieng() || GameCanvas.loadmap.mapLang() || mainObject == null || this.isStun || this.isDongBang) {
            return false;
        }
        if (mainObject.isSleep || mainObject.isMoveOut) {
            return true;
        }
        if (mainObject.typeObject == 0 && mainObject.typeSpec == 1) {
            if (mainObject.typePk >= 1 && mainObject.typePk <= 9 && this.typePk >= 1 && this.typePk <= 9 && mainObject.typePk == this.typePk) {
                return false;
            }
            if (mainObject.myClan == null || GameScreen.player.myClan == null || mainObject.myClan.IdClan != GameScreen.player.myClan.IdClan) {
                return true;
            }
            return false;
        }
        if (this.Action == 4 || mainObject.Action == 4) {
            return false;
        }
        if (mainObject.typeObject == 0) {
            if ((mainObject.typePk >= 1 && mainObject.typePk <= 9 && this.typePk >= 1 && this.typePk <= 9 && mainObject.typePk == this.typePk) || this.pointPk >= 2000) {
                return false;
            }
            if (this.typePk == 12 && (mainObject.typePk == 13 || mainObject.typePk == 11)) {
                return true;
            }
            if (this.typePk == 12 && mainObject.typePk == 12) {
                return false;
            }
            if (this.typePk == 13 && mainObject.typePk == 12) {
                return true;
            }
            if (this.typePk == 13 && (mainObject.typePk == 13 || mainObject.typePk == 11)) {
                return false;
            }
            if (this.typePk == 11 && mainObject.typePk == 12) {
                return true;
            }
            if (this.typePk == 11 && (mainObject.typePk == 11 || mainObject.typePk == 13)) {
                return false;
            }
            if ((this.typePk == 10 && setPlayerPk((short) mainObject.ID)) || mainObject.typePk == 0 || mainObject.typePk == 10) {
                return true;
            }
            if (mainObject.typePk != -1 && this.typePk != -1 && this.typePk != mainObject.typePk && this.typePk != 10) {
                return true;
            }
            if (mainObject.Lv > 10 && this.typePk == 0) {
                return true;
            }
            return false;
        }
        if (PointSucKhoe <= 0) {
            GameCanvas.addInfoChar(T.yeusuckhoe);
            return false;
        }
        if (mainObject.typeObject == 1 && mainObject.typeMonster == 7 && this.myClan != null && mainObject.myClan != null && this.myClan.IdClan == mainObject.myClan.IdClan) {
            return false;
        }
        return true;
    }

    public boolean setPlayerPk(short s) {
        for (int i = 0; i < vecPlayerPk.length; i++) {
            if (s == vecPlayerPk[i]) {
                return true;
            }
        }
        return false;
    }

    public void dofire() {
        if (isAutoFire == 0) {
            isAutoFire = (byte) 1;
            xBeginAutoFire = this.x;
            yBeginAutofire = this.y;
            timeResetAuto = 60;
            isCurAutoFire = true;
            return;
        }
        if (isAutoFire == -1) {
            GameCanvas.keyMyPressed[25] = true;
            GameCanvas.keyMyPressed[5] = true;
            isAutoFire = (byte) 1;
            xBeginAutoFire = this.x;
            yBeginAutofire = this.y;
            timeResetAuto = 60;
            isCurAutoFire = false;
        }
    }

    public void dofire_() {
        isAutoFire = (byte) 1;
        xBeginAutoFire = this.x;
        yBeginAutofire = this.y;
        timeResetAuto = 60;
        isCurAutoFire = true;
    }

    public void setPointFocus() {
        if (GameScreen.ObjFocus == null || GameScreen.ObjFocus.typeBoss == 2) {
            return;
        }
        if (GameScreen.ObjFocus.isNPC()) {
            GameScreen.gI().cmdGiaotiep.perform();
            if (isAutoFire == 1) {
                setCurAutoFire();
            }
            GameCanvas.isPointerSelect = false;
            return;
        }
        if (GameScreen.ObjFocus.typeObject == 0) {
            if (setFirePlayer(GameScreen.ObjFocus)) {
                if (mhotkey[levelTab][IndexFire].type != HotKey.SKILL) {
                    int i = 0;
                    while (true) {
                        if (i >= mhotkey[levelTab].length) {
                            break;
                        }
                        if (mhotkey[levelTab][i].type != HotKey.SKILL) {
                            i++;
                        } else {
                            IndexFire = (byte) i;
                            if (isAutoFire == 1) {
                                setCurAutoFire();
                            }
                        }
                    }
                }
                setActionHotKey(IndexFire, false);
                return;
            }
            if (GameScreen.ObjFocus.isSelling()) {
                GameScreen.gI().cmdplayerStore.perform();
                return;
            }
            if (this.typePk == -1 || this.typePk == 10 || GameCanvas.loadmap.mapLang()) {
                GameScreen.gI().cmdGiaotiep.setPos(GameScreen.ObjFocus.x - MainScreen.cameraMain.xCam, ((GameScreen.ObjFocus.y - MainScreen.cameraMain.yCam) - GameScreen.ObjFocus.hOne) - 30, PaintInfoGameScreen.fraContact, "");
                GameScreen.gI().cmdGiaotiep.IdGiaotiep = GameScreen.ObjFocus.ID;
                GameScreen.timePaintCmdGiaotiep = (byte) 60;
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus.typeObject == 4 || GameScreen.ObjFocus.typeObject == 3 || GameScreen.ObjFocus.typeObject == 5 || GameScreen.ObjFocus.typeObject == 7) {
            if (!GameScreen.ObjFocus.isSend) {
                GlobalService.gI().Get_Item_Map((short) GameScreen.ObjFocus.ID, GameScreen.ObjFocus.typeObject);
                GameScreen.ObjFocus.isSend = true;
                return;
            }
            return;
        }
        if (GameScreen.ObjFocus.typeObject != 1 || GameScreen.ObjFocus.Action == 4) {
            return;
        }
        if (GameScreen.ObjFocus.isMiNuong()) {
            GameScreen.gI().cmdinfoMiNuong.perform();
            return;
        }
        if (!GameScreen.ObjFocus.isMonstervantieu()) {
            dofire();
            return;
        }
        if (GameScreen.ObjFocus.isMonstervantieu()) {
            if (GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                GameScreen.gI().cmdInfoVantieu.perform();
                return;
            }
            if (!GameScreen.ObjFocus.getnameOwner().equals(this.name)) {
                if (this.typePk == 0) {
                    dofire();
                    return;
                }
                if (this.typePk == 11 || this.typePk == 13) {
                    if (GameScreen.ObjFocus.isMonsterVantieuDen()) {
                        dofire();
                    }
                } else if (this.typePk == 12 && GameScreen.ObjFocus.isMonsterVantieuTrang()) {
                    dofire();
                }
            }
        }
    }

    public void resetPlayer() {
        party = null;
        this.currentQuest = null;
        this.chat = null;
    }

    public void setValueAuto(int i) {
        if (isAutoFire != -1) {
            if (isCurAutoFire) {
                isAutoFire = (byte) i;
            } else {
                isAutoFire = (byte) -1;
            }
            if (isAutoFire == 0) {
                xBeginAutoFire = -1;
                yBeginAutofire = -1;
            }
        }
    }

    public boolean setAutoBuff(int i, int i2, MainObject mainObject) {
        Object_Effect_Skill object_Effect_Skill;
        if (mainObject == null) {
            this.posTransRoad = null;
            this.IDAttack = this.ID;
            mVector mvector = new mVector("Player vec3");
            mvector.addElement(new Object_Effect_Skill((short) this.ID, this.typeObject));
            this.ListKillNow.setFireSkill(this, mvector, i2, (byte) -1);
            return true;
        }
        if (i == 2) {
            this.posTransRoad = null;
            this.IDAttack = this.ID;
            mVector mvector2 = new mVector("Player vec4");
            if (mainObject.Action == 4 || mainObject.typeObject == 2 || this.typePk == 0 || mainObject.typePk == 0 || mainObject.typePk == 10 || (mainObject.typePk != -1 && this.typePk != -1 && this.typePk != mainObject.typePk)) {
                object_Effect_Skill = new Object_Effect_Skill((short) this.ID, this.typeObject);
            } else {
                object_Effect_Skill = new Object_Effect_Skill((short) mainObject.ID, mainObject.typeObject);
            }
            mvector2.addElement(object_Effect_Skill);
            this.ListKillNow.setFireSkill(this, mvector2, i2, (byte) -1);
            return true;
        }
        if (i == 1) {
            this.posTransRoad = null;
            this.IDAttack = this.ID;
            mVector mvector3 = new mVector("Player vec5");
            mvector3.addElement(new Object_Effect_Skill((short) this.ID, this.typeObject));
            this.ListKillNow.setFireSkill(this, mvector3, i2, (byte) -1);
            return true;
        }
        if (i == 3 && mainObject.Action != 4 && mainObject.typeObject != 2 && setFirePlayer(mainObject)) {
            this.posTransRoad = null;
            this.IDAttack = mainObject.ID;
            mVector mvector4 = new mVector("Player vec6");
            mvector4.addElement(new Object_Effect_Skill((short) mainObject.ID, mainObject.typeObject));
            this.ListKillNow.setFireSkill(this, mvector4, i2, (byte) -1);
            return true;
        }
        return false;
    }

    public static boolean setFirePlayerSpec(MainObject mainObject) {
        if (mainObject != null && mainObject.typeObject == 0 && mainObject.typeSpec == 1) {
            if (mainObject.myClan == null || GameScreen.player.myClan == null || mainObject.myClan.IdClan != GameScreen.player.myClan.IdClan) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean checkGiaoTiep() {
        if (GameScreen.ObjFocus == null) {
            return false;
        }
        if (GameScreen.ObjFocus.typeObject == 2) {
            return true;
        }
        if ((GameScreen.ObjFocus.typeObject != 1 || (GameScreen.ObjFocus.myClan != null && GameScreen.player.myClan != null && GameScreen.ObjFocus.myClan.IdClan == GameScreen.player.myClan.IdClan)) && !setFirePlayer(GameScreen.ObjFocus)) {
            return true;
        }
        return false;
    }

    @Override // GameObjects.MainObject
    public void move_to_XY() {
        if (isSelling()) {
            this.toX = this.x;
            this.toY = this.y;
            this.posTransRoad = null;
            mVector mvector = new mVector();
            mvector.addElement(new iCommand(T.yes, 4, this));
            mvector.addElement(new iCommand(T.cancel, 5, this));
            GameCanvas.start_Select_Dialog(T.can_not_move, mvector);
            return;
        }
        if (!Canmove() || this.isBinded) {
            this.toX = this.x;
            this.toY = this.y;
            return;
        }
        if (this.isMoveOut) {
            return;
        }
        if (this.x != this.toX) {
            this.vy = 0;
            this.Action = 1;
            if (CRes.abs(this.x - this.toX) > this.vMax + getVmount()) {
                if (this.x > this.toX) {
                    this.vx = -(this.vMax + getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 2;
                    return;
                } else {
                    this.vx = this.vMax + getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 3;
                    return;
                }
            }
            if (CRes.abs(this.x - this.toX) < this.vMax + getVmount()) {
                if (this.x > this.toX) {
                    this.vx = -CRes.abs(this.x - this.toX);
                    this.PrevDir = this.Direction;
                    this.Direction = 2;
                    return;
                } else {
                    this.vx = CRes.abs(this.x - this.toX);
                    this.PrevDir = this.Direction;
                    this.Direction = 3;
                    return;
                }
            }
            if (this.x > this.toX) {
                this.vx = -(this.vMax + getVmount());
                this.PrevDir = this.Direction;
                this.Direction = 2;
                return;
            } else {
                this.vx = this.vMax + getVmount();
                this.PrevDir = this.Direction;
                this.Direction = 3;
                return;
            }
        }
        if (this.y != this.toY) {
            this.vx = 0;
            this.Action = 1;
            if (CRes.abs(this.y - this.toY) > this.vMax + getVmount()) {
                if (this.y > this.toY) {
                    this.vy = -(this.vMax + getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 1;
                    return;
                } else {
                    this.vy = this.vMax + getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 0;
                    return;
                }
            }
            if (CRes.abs(this.y - this.toY) < this.vMax + getVmount()) {
                if (this.y > this.toY) {
                    this.vy = -CRes.abs(this.y - this.toY);
                    this.PrevDir = this.Direction;
                    this.Direction = 1;
                    return;
                } else {
                    this.vy = CRes.abs(this.y - this.toY);
                    this.PrevDir = this.Direction;
                    this.Direction = 0;
                    return;
                }
            }
            if (this.y > this.toY) {
                this.vy = -(this.vMax + getVmount());
                this.PrevDir = this.Direction;
                this.Direction = 1;
                return;
            } else {
                this.vy = this.vMax + getVmount();
                this.PrevDir = this.Direction;
                this.Direction = 0;
                return;
            }
        }
        this.vx = 0;
        this.vy = 0;
        this.Action = 0;
    }

    @Override // GameObjects.MainObject
    public void addEffectCharWearing(int i, int i2) {
        this.vecEffectCharWearing.addElement(new EffectCharWearing((byte) i, i2));
    }

    public static void setCurAutoFire() {
        if (isCurAutoFire) {
            isAutoFire = (byte) 0;
        } else {
            isAutoFire = (byte) -1;
        }
    }

    @Override // GameObjects.MainObject
    public void removeNameStore() {
        this.nameStore = null;
    }

    @Override // GameObjects.MainObject
    public void setNameStore(String str) {
        if (this.nameStore == null) {
            this.nameStore = new PopupChat();
        }
        this.nameStore.setChat(str, this.isStop);
        this.nameStore.updatePos(this.x, (this.y - this.hOne) - 30);
    }

    @Override // GameObjects.MainObject
    public boolean isSelling() {
        return this.nameStore != null;
    }
}
