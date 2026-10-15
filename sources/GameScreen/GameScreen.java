package GameScreen;

import Build_Indo.IndoServer;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.AnimateEffect;
import GameEffect.EffectAuto;
import GameEffect.EffectEnd;
import GameEffect.EffectMonster;
import GameEffect.EffectNum;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.DataSkillEff;
import GameObjects.GameData;
import GameObjects.IArrow;
import GameObjects.Item;
import GameObjects.MagicBeam;
import GameObjects.MainHelp;
import GameObjects.MainItem;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.MainRMS;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameObjects.Pet;
import GameObjects.Player;
import GameObjects.ThacNuoc;
import GameObjects.TimecountDown;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Main.ImageEffect;
import Main.ImageEffectAuto;
import Map.ItemMap;
import Map.MainItemMap;
import Model.AvMain;
import Model.CRes;
import Model.CharCountDown;
import Model.EffectManager;
import Model.Menu2;
import Model.Point;
import Model.T;
import Thread_More.LoadMap;
import Thread_More.MiniMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/GameScreen.class */
public class GameScreen extends MainScreen {
    public static final byte CHIEN_BINH = 0;
    public static final byte SAT_THU = 1;
    public static final byte PHAP_SU = 2;
    public static final byte XA_THU = 3;
    public static short[] idmap18;
    public iCommand cmdMyseft;
    public static long timeCheckDelHash;
    public static int xMoveCam;
    public static int yMoveCam;
    public static int xCur;
    public static int yCur;
    public static int timeResetCam;
    public static String[] textServer;
    public int xRec;
    public int yRec;
    public int wRec;
    public int hRec;
    public int colorRec;
    public mImage imgCombo;
    public static CharCountDown charcountdonw;
    int p;
    int o;
    MainObject ob;
    public static MainItemMap tr;
    int iii;
    int xStart;
    int yStart;
    int cmxMini;
    int cmyMini;
    public static int ID_DLG_BUY_KC = -32059;
    public static Player player = new Player(0, (byte) 0, "unname", 0, 0);
    public static Pet pet = null;
    public static MainObject ObjFocus = null;
    public static mVector VecNum = new mVector("GameScr VecNum");
    public static mVector Vecplayers = new mVector("GameScr Vecplayers");
    public static mVector VecInfoServer = new mVector("GameScr VecInfoServer");
    public static mVector VecInfoChar = new mVector("GameScr menu");
    public static mVector vecEffInMap = new mVector("GameScr vecEffInMap");
    public static mVector vecStep = new mVector("GameScr vecStep");
    public static mHashtable HashImageItemMap = new mHashtable();
    public static mHashtable HashImageNPC = new mHashtable();
    public static mVector vecWeather = new mVector("GameScr vecWeather");
    public static MainHelp help = new MainHelp();
    public static PaintInfoGameScreen infoGame = new PaintInfoGameScreen();
    public static byte timePaintCmdGiaotiep = 0;
    public static boolean isMoveCamera = false;
    public static boolean isFinishHelp = false;
    public static int demNumSoundEff = 0;
    public static String nameSpecialRegion = "";
    public static long timeSpRegion = 0;
    public static long timeArena = -1;
    public static mVector vecHightEffAuto = new mVector("Hight EffectAuto");
    public static boolean isInPetArea = false;
    public static mVector arrowsUp = new mVector();
    public static mVector vecTimecountDown = new mVector("Time");
    public static boolean isHideOderPlayer = false;
    public static boolean isHideFullOderPlayer = false;
    public static boolean isShowHoiSinh = false;
    public static boolean isMapLang = false;
    public static mVector vecDataeff = new mVector();
    public static boolean isReArea = false;
    public static MainItemMap maxtr = new MainItemMap();
    public static int dx = 0;
    public static int dy = 0;
    public static String goiMes = "";
    public static short[][] posSkill = {new short[]{-72, 0, 120}, new short[]{144, 192, 120}};
    public boolean isFullScreen = false;
    public boolean isCombo = false;
    MainObject maxob = new MainObject();
    public mVector menuNgua = new mVector("GameScr menungua");
    public iCommand cmdAddfriend = new iCommand(T.addFriend, 0);
    public iCommand cmdInfoChar = new iCommand(T.info, 1);
    public iCommand cmdChat = new iCommand(T.trochuyen, 2);
    public iCommand cmdMoiParty = new iCommand(T.moiParty, 3);
    public iCommand cmdNewParty = new iCommand(T.newParty, 5);
    public iCommand cmdBuy_Sell = new iCommand(T.buySell, 7);
    public iCommand cmdSetWeather = new iCommand("mua", 25, this);
    public iCommand cmdChucNang = new iCommand(T.chucnang, 14);
    public iCommand cmdAddMemClan = new iCommand(T.addmemclan, 15);
    public iCommand cmdGiaotiep = new iCommand(T.giaotiep, 0, this);
    public iCommand cmdMenu = new iCommand(T.menu, 1, this);
    public iCommand cmdShowChat = new iCommand(T.tinnhan, 4, this);
    public iCommand cmdParty = new iCommand(T.party, 6, this);
    public iCommand cmdSetPk = new iCommand(T.setPk, 8, this);
    public iCommand cmdSetDoSat = new iCommand(T.dosat, 10, this);
    public iCommand cmdChangeMap = new iCommand(T.changeArea, 12, this);
    public iCommand cmdListFriend = new iCommand(T.listFriend, 15, this);
    public iCommand cmdLyMeowz = new iCommand("LyMeowz", 80, this);
    private boolean lyMeowzIntelligenceEnabled;
    private boolean lyMeowzQuestTravelEnabled;
    private int lyMeowzQuestTravelNpcId = -1;
    private int lyMeowzQuestTravelMap = -1;
    private int lyMeowzQuestTravelArea = -1;
    private int lyMeowzQuestTravelWaitTicks;
    private int lyMeowzQuestTravelNpcWaitTicks;
    private int lyMeowzQuestTravelStallTicks;
    private int lyMeowzQuestTravelLastX;
    private int lyMeowzQuestTravelLastY;
    private boolean lyMeowzQuestTravelWaitingForMap;
    private boolean lyMeowzQuestTravelOwnsPath;
    private byte lyMeowzQuestTravelPreviousAutoFire;
    private boolean lyMeowzQuestTravelPreviousCurAutoFire;

    private boolean lyMeowzPreviousAutoHp;
    private byte lyMeowzPreviousAutoFire;
    private boolean lyMeowzPreviousCurAutoFire;
    public iCommand cmdAutoFire = new iCommand(T.autoFire, 16, this);
    public iCommand cmdEndHelp = new iCommand(T.endHelp, 17, this);
    public iCommand cmdAutoItem = new iCommand(T.autoItem, 18, this);
    public iCommand cmdAutoMPHP = new iCommand(T.autoHP, 19, this);
    public iCommand cmdThachDau = new iCommand(T.thachdau, 20, this);
    public iCommand cmdAutoBuff = new iCommand(T.autoBuff, 21, this);
    public iCommand cmdShowAuto = new iCommand(T.info, 22, this);
    public iCommand cmdInfoVantieu = new iCommand(T.info, 27, this);
    public iCommand cmdQuickChat = new iCommand(T.chat, 28, this);
    public iCommand cmdplayerStore = new iCommand(T.gianHang, 30, this);
    public iCommand cmdinfoMiNuong = new iCommand(T.info, 31, this);

    public static GameScreen gI() {
        if (GameCanvas.game == null) {
            GameCanvas.game = new GameScreen();
        }
        return GameCanvas.game;
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        this.left = null;
        this.right = null;
        this.center = null;
        if (!GameCanvas.isTouch) {
            this.left = this.cmdMenu;
        }
        super.Show();
        isMoveCamera = false;
        GameCanvas.clearAll();
    }

    public void checkRemoveImage() {
        if (!GameCanvas.isTouch) {
            checkClear();
        }
        mSystem.freeDataChangeMap();
    }

    public void setCaptionCmd() {
        this.cmdAddfriend.caption = T.addFriend;
        this.cmdInfoChar.caption = T.info;
        this.cmdChat.caption = T.trochuyen;
        this.cmdMoiParty.caption = T.moiParty;
        this.cmdNewParty.caption = T.newParty;
        this.cmdBuy_Sell.caption = T.buySell;
        this.cmdChucNang.caption = T.chucnang;
        this.cmdAddMemClan.caption = T.addmemclan;
        this.cmdGiaotiep.caption = T.giaotiep;
        this.cmdMenu.caption = T.menu;
        this.cmdShowChat.caption = T.tinnhan;
        this.cmdParty.caption = T.party;
        this.cmdSetPk.caption = T.setPk;
        this.cmdSetDoSat.caption = T.dosat;
        this.cmdChangeMap.caption = T.changeArea;
        this.cmdListFriend.caption = T.listFriend;
        this.cmdAutoFire.caption = T.autoFire;
        this.cmdEndHelp.caption = T.endHelp;
        this.cmdAutoItem.caption = T.autoItem;
        this.cmdAutoMPHP.caption = T.autoHP;
        this.cmdThachDau.caption = T.thachdau;
        this.cmdAutoBuff.caption = T.autoBuff;
        this.cmdShowAuto.caption = T.info;
        this.cmdInfoVantieu.caption = T.info;
        this.cmdQuickChat.caption = T.chat;
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case 2:
                GameCanvas.AllInfo.Show(this);
                break;
        }
        super.commandTab(i, i2);
    }

    @Override // Model.AvMain
    public void commandMenu(int i, int i2) {
        switch (i) {
            case 0:
                if (Menu2.objSelect != null) {
                    GlobalService.gI().Friend((byte) 0, Menu2.objSelect.name);
                    break;
                }
                break;
            case 1:
                if (Menu2.objSelect != null) {
                    GlobalService.gI().Re_Info_Other_Object(Menu2.objSelect.name, Info_Other_Player.VIEW);
                    break;
                }
                break;
            case 2:
                if (Menu2.objSelect != null) {
                    GameCanvas.msgchat.addNewChat(Menu2.objSelect.name, "", "", (byte) 0, true);
                }
                GameCanvas.start_Chat_Dialog();
                break;
            case 3:
                if (Menu2.objSelect != null) {
                    GlobalService.gI().Party((byte) 1, Menu2.objSelect.name);
                    break;
                }
                break;
            case 5:
                GlobalService.gI().Party((byte) 0, "");
                break;
            case 7:
                if (Menu2.objSelect != null) {
                    GlobalService.gI().Buy_Sell((byte) 0, Menu2.objSelect.name, (byte) 0, (short) 0, 0);
                    break;
                }
                break;
            case 14:
                MenuChucNang();
                break;
            case 15:
                if (Menu2.objSelect != null) {
                    GlobalService.gI().Add_And_AnS_MemClan((byte) 10, Menu2.objSelect.name);
                    break;
                }
                break;
        }
        super.commandMenu(i, i2);
    }

    public void MenuChucNang() {
        mVector mvector = new mVector("GameScr menu3");
        mvector.addElement(this.cmdListFriend);
        mvector.addElement(this.cmdLyMeowz);
        String stringBuffer = new StringBuffer().append(T.off).append(" ").append(T.dosat).toString();
        if (player.typePk != 0) {
            mvector.addElement(this.cmdSetPk);
            stringBuffer = new StringBuffer().append(T.on).append(" ").append(T.dosat).toString();
        }
        this.cmdSetDoSat.caption = stringBuffer;
        mvector.addElement(this.cmdSetDoSat);
        if (Player.isAutoFire > -1) {
            this.cmdAutoFire.caption = new StringBuffer().append(T.off).append(T.autoFire).toString();
        } else {
            this.cmdAutoFire.caption = new StringBuffer().append(T.on).append(T.autoFire).toString();
        }
        mvector.addElement(this.cmdAutoFire);
        if (Player.autoItem != null) {
            this.cmdAutoItem.caption = new StringBuffer().append(T.off).append(T.autoItem).toString();
        } else {
            this.cmdAutoItem.caption = new StringBuffer().append(T.on).append(T.autoItem).toString();
        }
        mvector.addElement(this.cmdAutoItem);
        if (Player.isAutoHPMP) {
            this.cmdAutoMPHP.caption = new StringBuffer().append(T.off).append(T.autoHP).toString();
        } else {
            this.cmdAutoMPHP.caption = new StringBuffer().append(T.on).append(T.autoHP).toString();
        }
        mvector.addElement(this.cmdAutoMPHP);
        if (help.Step >= 0) {
            mvector.addElement(this.cmdEndHelp);
        }
        GameCanvas.menu2.startAt(mvector, 2, T.chucnang, false, null);
    }

    /** One master switch; advanced recovery/navigation remains disabled until integrated. */
    public boolean isLyMeowzIntelligenceEnabled() {
        return lyMeowzIntelligenceEnabled;
    }

    public void toggleLyMeowzIntelligence() {
        if (!lyMeowzIntelligenceEnabled) {
            if (player == null || player.hp <= 0) {
                GameCanvas.addInfoChar("[LyMeowz] Can nhan vat con song de bat.");
                return;
            }
            lyMeowzPreviousAutoHp = Player.isAutoHPMP;
            lyMeowzPreviousAutoFire = Player.isAutoFire;
            lyMeowzPreviousCurAutoFire = Player.isCurAutoFire;
            Player.isAutoHPMP = true;
            Player.xBeginAutoFire = player.x;
            Player.yBeginAutofire = player.y;
            Player.isCurAutoFire = true;
            Player.isAutoFire = (byte) 1;
            lyMeowzIntelligenceEnabled = true;
            GameCanvas.addInfoChar("[LyMeowz] Da bat tu danh va HP/MP.");
        } else {
            Player.isAutoHPMP = lyMeowzPreviousAutoHp;
            Player.isAutoFire = lyMeowzPreviousAutoFire;
            Player.isCurAutoFire = lyMeowzPreviousCurAutoFire;
            lyMeowzIntelligenceEnabled = false;
            GameCanvas.addInfoChar("[LyMeowz] Da tat che do.");
        }
    }

    public void openLyMeowzMenu() {
        mVector items = new mVector("LyMeowz menu");
        items.addElement(new iCommand((lyMeowzIntelligenceEnabled ? "Tat " : "Bat ") +
                "Level up your intelligence", 82, this));
        items.addElement(new iCommand("Tien do nhiem vu diet quai", 83, this));
        items.addElement(new iCommand("Tim NPC tra nhiem vu", 84, this));
        items.addElement(new iCommand("Mo menu tra nhiem vu", 85, this));
        items.addElement(new iCommand("Di den NPC tra nhiem vu", 86, this));
        items.addElement(new iCommand((lyMeowzQuestTravelEnabled ? "Dung" : "Bat") +
                " tu dong tim NPC qua map", 87, this));
        items.addElement(new iCommand("Kiem tra tuyen NPC da hoc", 88, this));
        items.addElement(new iCommand("Luu ban do LyMeowz", 89, this));
        items.addElement(new iCommand("Quay lai", 81, this));
        GameCanvas.menu2.startAt(items, 2, "LyMeowz", false, null);
    }

    /** Explicitly opt-in quest NPC navigation; leaves quest confirmation to the native menu. */
    private void toggleLyMeowzQuestTravel() {
        if (lyMeowzQuestTravelEnabled) {
            stopLyMeowzQuestTravel("LyMeowz: Da dung tim duong NPC.");
            return;
        }
        if (player == null || player.hp <= 0 || GameCanvas.loadmap == null) {
            GameCanvas.addInfoChar("LyMeowz: Can nhan vat con song tren map.");
            return;
        }
        int npcId = -1;
        for (int q = 0; q < GameObjects.MainQuest.vecQuestFinish.size(); q++) {
            GameObjects.MainQuest quest = (GameObjects.MainQuest) GameObjects.MainQuest.vecQuestFinish.elementAt(q);
            if (quest != null && quest.idNPC_To >= 0) {
                npcId = quest.idNPC_To;
                break;
            }
        }
        if (npcId < 0) {
            GameCanvas.addInfoChar("LyMeowz: Chua co nhiem vu da hoan thanh.");
            return;
        }
        lyMeowzQuestTravelNpcId = npcId;
        lyMeowzQuestTravelMap = GameCanvas.loadmap.idMap;
        lyMeowzQuestTravelArea = LoadMap.Area & 255;
        lyMeowzQuestTravelWaitTicks = 0;
        lyMeowzQuestTravelNpcWaitTicks = 0;
        lyMeowzQuestTravelStallTicks = 0;
        lyMeowzQuestTravelWaitingForMap = false;
        lyMeowzQuestTravelOwnsPath = false;
        lyMeowzQuestTravelLastX = player.x;
        lyMeowzQuestTravelLastY = player.y;
        lyMeowzQuestTravelPreviousAutoFire = Player.isAutoFire;
        lyMeowzQuestTravelPreviousCurAutoFire = Player.isCurAutoFire;
        Player.isAutoFire = (byte) 0;
        Player.isCurAutoFire = false;
        lyMeowzQuestTravelEnabled = true;
        GameCanvas.addInfoChar("LyMeowz: Tu tim NPC " + npcId + ". Chi su dung cac cong da hoc.");
    }

    private void stopLyMeowzQuestTravel(String reason) {
        if (!lyMeowzQuestTravelEnabled) return;
        lyMeowzQuestTravelEnabled = false;
        if (lyMeowzQuestTravelOwnsPath && player != null) {
            player.posTransRoad = null;
            player.countAutoMove = 0;
            player.xStopMove = 0;
            player.yStopMove = 0;
        }
        lyMeowzQuestTravelOwnsPath = false;
        lyMeowzQuestTravelWaitingForMap = false;
        if (Player.isAutoFire == 0) Player.isAutoFire = lyMeowzQuestTravelPreviousAutoFire;
        if (!Player.isCurAutoFire) Player.isCurAutoFire = lyMeowzQuestTravelPreviousCurAutoFire;
        if (reason != null) GameCanvas.addInfoChar(reason);
    }

    /**
     * Follow the native Player.posTransRoad consumer in small forward segments.
     * Map collision comes from actual server-loaded tiles, not guessed map adjacency.
     */
    private boolean lyMeowzQuestMoveTo(int destX, int destY, boolean allowNearby) {
        if (GameCanvas.loadmap == null || GameCanvas.loadmap.mapType == null ||
                LoadMap.wTile <= 0 || player == null || !player.Canmove()) return false;
        int sx = player.x / LoadMap.wTile;
        int sy = player.y / LoadMap.wTile;
        int dx = destX / LoadMap.wTile;
        int dy = destY / LoadMap.wTile;
        short[] route = LyMeowzMapReader.route(GameCanvas.loadmap.mapType,
                GameCanvas.loadmap.mapW, GameCanvas.loadmap.mapH,
                sx, sy, dx, dy, 512);
        if (route.length == 0 && (sx != dx || sy != dy) && allowNearby) {
            // NPCs can occupy collision-blocked tiles. Reach a walkable adjacent
            // tile within interaction distance instead of walking into the NPC.
            int[] ox = {0, 1, -1, 0, 1, -1, 1, -1};
            int[] oy = {-1, 0, 0, 1, -1, -1, 1, 1};
            for (int radius = 1; radius <= 2 && route.length == 0; radius++) {
                for (int j = 0; j < ox.length && route.length == 0; j++) {
                    int candidateX = dx + ox[j] * radius;
                    int candidateY = dy + oy[j] * radius;
                    if (sx == candidateX && sy == candidateY) {
                        // Already within a tile of the NPC; next update handles interaction.
                        return false;
                    }
                    route = LyMeowzMapReader.route(GameCanvas.loadmap.mapType,
                            GameCanvas.loadmap.mapW, GameCanvas.loadmap.mapH,
                            sx, sy, candidateX, candidateY, 512);
                    if (route.length > 0) {
                        destX = candidateX * LoadMap.wTile + LoadMap.wTile / 2;
                        destY = candidateY * LoadMap.wTile + LoadMap.wTile / 2;
                    }
                }
            }
        }
        if (route.length == 0) {
            // A destination in the current tile still needs an exact-position move.
            if (sx != dx || sy != dy) return false;
            route = new short[] {(short) ((sx << 8) | sy)};
        }
        int steps = route.length > 16 ? 16 : route.length;
        short[] segment = new short[steps];
        for (int i = 0; i < steps; i++) segment[i] = route[i];
        player.posTransRoad = segment;
        player.countAutoMove = 0;
        player.toX = player.x;
        player.toY = player.y;
        // For a long route, finish this segment before computing the next one.
        player.xStopMove = steps == route.length ? destX : 0;
        player.yStopMove = steps == route.length ? destY : 0;
        lyMeowzQuestTravelOwnsPath = true;
        lyMeowzQuestTravelStallTicks = 0;
        lyMeowzQuestTravelLastX = player.x;
        lyMeowzQuestTravelLastY = player.y;
        return true;
    }

    private void updateLyMeowzQuestTravel() {
        if (!lyMeowzQuestTravelEnabled || GameCanvas.loadmap == null || player == null ||
                !LoadMapScreen.isNextMap || GameCanvas.currentScreen != GameCanvas.game ||
                GameCanvas.gameTick % 12 != 0) return;
        if (player.hp <= 0 || player.Action == 4) {
            stopLyMeowzQuestTravel("LyMeowz: Tam dung tim NPC vi nhan vat da chet.");
            return;
        }
        boolean unfinishedTurnIn = false;
        for (int q = 0; q < GameObjects.MainQuest.vecQuestFinish.size(); q++) {
            GameObjects.MainQuest quest = (GameObjects.MainQuest) GameObjects.MainQuest.vecQuestFinish.elementAt(q);
            if (quest != null && quest.idNPC_To == lyMeowzQuestTravelNpcId) {
                unfinishedTurnIn = true;
                break;
            }
        }
        if (!unfinishedTurnIn) {
            stopLyMeowzQuestTravel("LyMeowz: Nhiem vu khong con trong danh sach tra.");
            return;
        }
        int mapId = GameCanvas.loadmap.idMap;
        int areaId = LoadMap.Area & 255;
        if (lyMeowzQuestTravelMap != mapId || lyMeowzQuestTravelArea != areaId) {
            lyMeowzQuestTravelMap = mapId;
            lyMeowzQuestTravelArea = areaId;
            lyMeowzQuestTravelWaitTicks = 0;
            lyMeowzQuestTravelNpcWaitTicks = 0;
            lyMeowzQuestTravelWaitingForMap = false;
            lyMeowzQuestTravelOwnsPath = false;
            lyMeowzQuestTravelStallTicks = 0;
            GameCanvas.addInfoChar("LyMeowz: Da qua map " + mapId + ", dang tim cong/NPC tiep theo.");
        }
        if (player.posTransRoad != null) {
            if (lyMeowzQuestTravelOwnsPath) {
                if (player.x == lyMeowzQuestTravelLastX && player.y == lyMeowzQuestTravelLastY) {
                    if (++lyMeowzQuestTravelStallTicks > 20) {
                        stopLyMeowzQuestTravel("LyMeowz: Bi ket duong, da dung tu dong.");
                    }
                } else {
                    lyMeowzQuestTravelStallTicks = 0;
                    lyMeowzQuestTravelLastX = player.x;
                    lyMeowzQuestTravelLastY = player.y;
                }
            }
            return;
        }
        lyMeowzQuestTravelOwnsPath = false;
        MainObject npc = MainObject.get_Object(lyMeowzQuestTravelNpcId, (byte) 2);
        if (npc instanceof GameObjects.NPC) {
            LyMeowzNpcMapRegistry.remember(lyMeowzQuestTravelNpcId, mapId, areaId);
            if (MainObject.getDistance(player.x, player.y, npc.x, npc.y) <= 90) {
                stopLyMeowzQuestTravel(null);
                ((GameObjects.NPC) npc).NhiemVu();
                GameCanvas.addInfoChar("LyMeowz: Da den NPC, chon nhiem vu de tra.");
            } else if (!lyMeowzQuestMoveTo(npc.x, npc.y, true)) {
                stopLyMeowzQuestTravel("LyMeowz: Khong tim duoc duong den NPC tren map nay.");
            }
            return;
        }
        int destMap = LyMeowzNpcMapRegistry.findMap(lyMeowzQuestTravelNpcId);
        int destArea = LyMeowzNpcMapRegistry.findArea(lyMeowzQuestTravelNpcId);
        if (destMap == mapId && destArea == areaId) {
            // NPCs may stream in after the map-change packet; allow time to spawn.
            if (++lyMeowzQuestTravelNpcWaitTicks > 30)
                stopLyMeowzQuestTravel("LyMeowz: NPC chua duoc tai tren map dich.");
            return;
        }
        if (destMap < 0) {
            stopLyMeowzQuestTravel("LyMeowz: Chua biet map cua NPC, can quan sat NPC truoc.");
            return;
        }
        int[] next = LyMeowzMapTransitions.nextGate(mapId, areaId, destMap, destArea);
        if (next == null) {
            stopLyMeowzQuestTravel("LyMeowz: Chua hoc tuyen cong den map " + destMap + ".");
            return;
        }
        Model.Point gate = null;
        int tolerance = LoadMap.wTile * 3;
        int best = tolerance * tolerance;
        for (int i = 0; i < LoadMap.vecPointChange.size(); i++) {
            Model.Point candidate = (Model.Point) LoadMap.vecPointChange.elementAt(i);
            int dx = candidate.x - next[0];
            int dy = candidate.y - next[1];
            int d2 = dx * dx + dy * dy;
            if (d2 < best) { best = d2; gate = candidate; }
        }
        if (gate == null) {
            stopLyMeowzQuestTravel("LyMeowz: Khong con cong da hoc tren map nay.");
            return;
        }
        int gateDistance = MainObject.getDistance(player.x, player.y, gate.x, gate.y);
        if (gateDistance <= 16) {
            // The native movement/server transition is authoritative; no invented packet.
            lyMeowzQuestTravelWaitingForMap = true;
            if (++lyMeowzQuestTravelWaitTicks > 50)
                stopLyMeowzQuestTravel("LyMeowz: Dung tai cong nhung server chua chuyen map.");
            return;
        }
        if (lyMeowzQuestTravelWaitingForMap && ++lyMeowzQuestTravelWaitTicks > 50) {
            stopLyMeowzQuestTravel("LyMeowz: Cong khong chuyen map, dung de tranh lap.");
            return;
        }
        if (!lyMeowzQuestMoveTo(gate.x, gate.y, false)) {
            stopLyMeowzQuestTravel("LyMeowz: Khong tim duoc duong den cong.");
        }
    }

    public void doArea() {
        GlobalService.gI().Request_Area();
        isReArea = true;
        GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        MainItem mainItem;
        switch (i) {
            case 80:
                openLyMeowzMenu();
                break;
            case 81:
                MenuChucNang();
                break;
            case 82:
                toggleLyMeowzIntelligence();
                break;
            case 83:
                int remaining = Player.lyMeowzQuestKillsRemaining();
                GameCanvas.addInfoChar("LyMeowz - Con " + remaining + " quai can diet cho nhiem vu.");
                openLyMeowzMenu();
                break;
            case 87:
                toggleLyMeowzQuestTravel();
                openLyMeowzMenu();
                break;
            case 89:
                boolean savedWorld = LyMeowzWorldStore.flushIfDirty();
                GameCanvas.addInfoChar(savedWorld ? "LyMeowz: Da luu ban do da hoc." :
                        "LyMeowz: Khong co thay doi hoac khong luu duoc RMS.");
                openLyMeowzMenu();
                break;
            case 88:
                int statusNpc = -1;
                for (int q = 0; q < GameObjects.MainQuest.vecQuestFinish.size(); q++) {
                    GameObjects.MainQuest quest = (GameObjects.MainQuest) GameObjects.MainQuest.vecQuestFinish.elementAt(q);
                    if (quest != null) { statusNpc = quest.idNPC_To; break; }
                }
                if (statusNpc < 0) {
                    GameCanvas.addInfoChar("LyMeowz: Khong co NPC cho nhiem vu da xong.");
                } else {
                    int statusMap = LyMeowzNpcMapRegistry.findMap(statusNpc);
                    if (statusMap < 0) {
                        GameCanvas.addInfoChar("LyMeowz: NPC " + statusNpc + " chua co map da hoc.");
                    } else if (GameCanvas.loadmap.idMap == statusMap &&
                            (LoadMap.Area & 255) == LyMeowzNpcMapRegistry.findArea(statusNpc)) {
                        GameCanvas.addInfoChar("LyMeowz: NPC " + statusNpc + " o map hien tai " + statusMap + ".");
                    } else {
                        int[] statusGate = LyMeowzMapTransitions.nextGate(GameCanvas.loadmap.idMap,
                                LoadMap.Area & 255, statusMap,
                                LyMeowzNpcMapRegistry.findArea(statusNpc));
                        if (statusGate == null)
                            GameCanvas.addInfoChar("LyMeowz: Biet map " + statusMap + " nhung chua hoc du cong.");
                        else
                            GameCanvas.addInfoChar("LyMeowz: Map dich " + statusMap + ", cong " +
                                    statusGate[0] + "," + statusGate[1] + " sang map " + statusGate[2] + "/khu " + statusGate[3] + ".");
                    }
                }
                openLyMeowzMenu();
                break;

            case 86:
                boolean routeFound = false;
                for (int q = 0; q < GameObjects.MainQuest.vecQuestFinish.size(); q++) {
                    GameObjects.MainQuest quest = (GameObjects.MainQuest) GameObjects.MainQuest.vecQuestFinish.elementAt(q);
                    if (quest == null) continue;
                    MainObject npc = MainObject.get_Object(quest.idNPC_To, (byte) 2);
                    if (npc == null) continue;
                    if (MainObject.getDistance(player.x, player.y, npc.x, npc.y) <= 100) {
                        if (npc instanceof GameObjects.NPC) {
                            ((GameObjects.NPC) npc).NhiemVu();
                            routeFound = true;
                        }
                    } else if (Thread_More.LoadMap.wTile > 0) {
                        short[] route = updateFindRoad(npc.x / Thread_More.LoadMap.wTile,
                                npc.y / Thread_More.LoadMap.wTile,
                                player.x / Thread_More.LoadMap.wTile,
                                player.y / Thread_More.LoadMap.wTile, 20);
                        if (route != null && route.length > 0 && route.length <= 20) {
                            player.posTransRoad = route;
                            routeFound = true;
                            GameCanvas.addInfoChar("LyMeowz: Dang di den NPC, bam mo menu khi den gan.");
                        }
                    }
                    break;
                }
                if (!routeFound) {
                    GameCanvas.addInfoChar("LyMeowz: Khong tim duoc duong an toan den NPC tren map nay.");
                    openLyMeowzMenu();
                }
                break;
            case 85:
                boolean openedQuest = false;
                for (int q = 0; q < GameObjects.MainQuest.vecQuestFinish.size(); q++) {
                    GameObjects.MainQuest quest = (GameObjects.MainQuest) GameObjects.MainQuest.vecQuestFinish.elementAt(q);
                    if (quest == null) continue;
                    MainObject npc = MainObject.get_Object(quest.idNPC_To, (byte) 2);
                    if (npc instanceof GameObjects.NPC &&
                            MainObject.getDistance(player.x, player.y, npc.x, npc.y) <= 100) {
                        ((GameObjects.NPC) npc).NhiemVu();
                        openedQuest = true;
                        break;
                    }
                }
                if (!openedQuest) {
                    GameCanvas.addInfoChar("LyMeowz: Can den gan NPC co nhiem vu da hoan thanh.");
                    openLyMeowzMenu();
                }
                break;
            case 84:
                boolean lyMeowzNpcMenuOpened = false;
                int questNpcId = -1;
                for (int q = 0; q < GameObjects.MainQuest.vecQuestFinish.size(); q++) {
                    GameObjects.MainQuest quest = (GameObjects.MainQuest) GameObjects.MainQuest.vecQuestFinish.elementAt(q);
                    if (quest != null) {
                        questNpcId = quest.idNPC_To;
                        break;
                    }
                }
                if (questNpcId < 0) {
                    GameCanvas.addInfoChar("LyMeowz: Chua co nhiem vu san sang tra.");
                } else {
                    MainObject npc = MainObject.get_Object(questNpcId, (byte) 2);
                    if (npc != null) {
                        ObjFocus = npc;
                        PaintInfoGameScreen.isPaintInfoFocus = true;
                        if (MainObject.getDistance(player.x, player.y, npc.x, npc.y) <= 100 && npc instanceof GameObjects.NPC) {
                            ((GameObjects.NPC) npc).GiaoTiep();
                            lyMeowzNpcMenuOpened = true;
                            GameCanvas.addInfoChar("LyMeowz: Da mo tuong tac NPC. Chon nhiem vu de tra.");
                        } else {
                            GameCanvas.addInfoChar("LyMeowz: Da danh dau NPC. Den gan NPC roi bam lai de tuong tac.");
                        }
                    } else {
                        int knownMap = LyMeowzNpcMapRegistry.findMap(questNpcId);
                        if (knownMap >= 0) {
                            int[] gate = LyMeowzMapTransitions.nextGate(GameCanvas.loadmap.idMap,
                                    LoadMap.Area & 255, knownMap,
                                    LyMeowzNpcMapRegistry.findArea(questNpcId));
                            if (gate != null)
                                GameCanvas.addInfoChar("LyMeowz: Cong tiep theo den map " + knownMap + " tai " + gate[0] + "," + gate[1] + ".");
                            else
                                GameCanvas.addInfoChar("LyMeowz: NPC " + questNpcId + " tung thay o map " + knownMap + ". Chua hoc tuyen duong.");
                        }
                        else
                            GameCanvas.addInfoChar("LyMeowz: Chua biet map cua NPC " + questNpcId + ".");
                    }
                }
                if (!lyMeowzNpcMenuOpened) openLyMeowzMenu();
                break;
            case 0:
                if (infoGame.isMapThachdau()) {
                    return;
                }
                if (ObjFocus != null) {
                    if (ObjFocus.isSelling() && !GameCanvas.isTouch) {
                        this.cmdplayerStore.perform();
                        break;
                    } else if (!player.setFirePlayer(ObjFocus) || ObjFocus.typeObject != 0) {
                        ObjFocus.GiaoTiep();
                        if (Player.isAutoFire == 1) {
                            Player.setCurAutoFire();
                        }
                        player.resetAction();
                        timePaintCmdGiaotiep = (byte) 0;
                        break;
                    } else if (this.center == this.cmdGiaotiep) {
                        this.center = null;
                        break;
                    }
                }
                break;
            case 1:
                GameCanvas.end_Dialog();
                if (player.isSelling()) {
                    do_Show_SellItem();
                    break;
                } else {
                    GameCanvas.AllInfo.Show(GameCanvas.game);
                    GlobalService.gI().send_cmd_server((byte) 59);
                    if (help.setStep_Next(1, 9)) {
                        GameCanvas.AllInfo.selectTab = 0;
                        help.NextStep();
                        help.setNext();
                        break;
                    } else if (help.setStep_Next(6, 2)) {
                        help.NextStep();
                        help.setNext();
                        break;
                    }
                }
                break;
            case 4:
                GameCanvas.start_Chat_Dialog();
                break;
            case 6:
                if (Player.party != null) {
                    GameCanvas.start_Party_Dialog();
                    break;
                }
                break;
            case 8:
                mVector mvector = new mVector("GameScr menu");
                int i3 = 0;
                while (i3 < T.mColorPk.length) {
                    if (i3 > 0 || player.typePk != -1) {
                        iCommand icommand = new iCommand(T.mColorPk[i3], 9, i3 == 0 ? -1 : i3, this);
                        if (i3 > 0) {
                            icommand.setFraCaption(AvMain.fraPk, 3, i3 * 3);
                        }
                        mvector.addElement(icommand);
                    }
                    i3++;
                }
                GameCanvas.menu2.startAt(mvector, 4, T.setPk, false, null);
                break;
            case 9:
                GlobalService.gI().set_Pk((byte) i2);
                break;
            case 10:
                if (player.typePk != 0) {
                    GameCanvas.start_Left_Dialog(T.hoibatdosat, new iCommand(T.on, 24, this));
                    break;
                } else {
                    GlobalService.gI().set_Pk((byte) -1);
                    break;
                }
            case 13:
                GlobalService.gI().Change_Area((byte) i2);
                break;
            case 15:
                if (!List_Server.isLoadFriend) {
                    GlobalService.gI().Friend((byte) 4, "");
                    GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.cancel, -1));
                    break;
                } else {
                    List_Server.gI().vecListServer = List_Server.vecMyFriend;
                    List_Server.gI().typeList = (byte) 0;
                    List_Server.gI().updateList();
                    List_Server.gI().setMinMax();
                    List_Server.gI().setXmove();
                    List_Server.gI().setCmd();
                    List_Server.gI().nameList = T.listFriend;
                    List_Server.gI().page = (byte) 99;
                    List_Server.gI().Show(this);
                    break;
                }
            case 16:
                if (Player.isAutoFire > -1) {
                    Player.isAutoFire = (byte) -1;
                    Player.isCurAutoFire = false;
                    break;
                } else {
                    Player.isAutoFire = (byte) 0;
                    Player.isCurAutoFire = true;
                    break;
                }
            case 17:
                help.p = null;
                help.Step = -1;
                help.Next = 0;
                help.setNext();
                help.SaveStep();
                break;
            case 18:
                if (Player.autoItem != null) {
                    Player.autoItem.isremove = true;
                    break;
                } else {
                    GameCanvas.start_Auto_Item_Dialog();
                    break;
                }
            case 19:
                if (Player.isAutoHPMP) {
                    Player.isAutoHPMP = false;
                    MainRMS.setSaveAuto();
                    break;
                } else {
                    GameCanvas.start_Auto_HPMP_Dialog();
                    break;
                }
            case 20:
                if (Menu2.objSelect != null) {
                    GlobalService.gI().Thach_Dau((byte) 0, Menu2.objSelect.name);
                    break;
                }
                break;
            case 21:
                GameCanvas.start_Auto_Buff();
                break;
            case 22:
                PaintInfoGameScreen.isShowInfoAuto = !PaintInfoGameScreen.isShowInfoAuto;
                MainRMS.setSaveTouch();
                break;
            case 24:
                GlobalService.gI().set_Pk((byte) 0);
                GameCanvas.end_Dialog();
                break;
            case 25:
                AddEffWeather((byte) 0, false, -4, 300);
                break;
            case 26:
                if (i2 >= 0 && i2 <= this.menuNgua.size() - 1 && (mainItem = (MainItem) this.menuNgua.elementAt(i2)) != null) {
                    GlobalService.gI().Use_Potion((short) mainItem.Id);
                    break;
                }
                break;
            case 27:
                GlobalService.gI().Dynamic_Menu((short) -56, (byte) 0, (byte) 0);
                break;
            case 28:
                mVector mvector2 = new mVector("GameScr menuchat");
                for (int i4 = 0; i4 < T.mQuickChat.length; i4++) {
                    mvector2.addElement(new iCommand(T.mQuickChat[i4], 29, i4, this));
                }
                GameCanvas.menu2.startAt(mvector2, 4, T.chat, false, null);
                break;
            case 29:
                player.strChatPopup = T.mQuickChat[i2];
                GlobalService.gI().chatPopup(T.mQuickChat[i2]);
                break;
            case 30:
                if (ObjFocus != null && ObjFocus.isSelling()) {
                    GlobalService.gI().do_Buy_Sell_Item(1, null, "", (short) ObjFocus.ID, 0, (byte) 0);
                    break;
                }
                break;
            case 31:
                if (ObjFocus != null && ObjFocus.isMiNuong()) {
                    GlobalService.gI().RequestInfo_MiNuong((byte) 0, (short) (ObjFocus.ID + ObjectData.MONSTER));
                    break;
                }
                break;
        }
        super.commandPointer(i, i2);
    }

    public void doSellItem() {
        GameCanvas.end_Dialog();
        Item.VecItem_Sell_in_store.removeAllElements();
        Item.VecItemSell.removeAllElements();
        mVector mvector = new mVector("Sell");
        mvector.addElement(new TabShopNew(Item.VecInvetoryPlayer, (byte) 16, T.tabhanhtrang, -1, (byte) 0));
        mvector.addElement(new TabShopNew(new mVector("info"), (byte) 15, T.gianHang, -1, (byte) 0));
        GameCanvas.shopNpc = new TabScreenNew();
        GameCanvas.shopNpc.selectTab = 0;
        GameCanvas.shopNpc.addMoreTab(mvector);
        GameCanvas.shopNpc.Show(gI());
    }

    public void do_Show_SellItem() {
        GameCanvas.end_Dialog();
        mVector mvector = new mVector("Sell");
        mvector.addElement(new TabShopNew(Item.VecInvetoryPlayer, (byte) 16, T.tabhanhtrang, -1, (byte) 0));
        mvector.addElement(new TabShopNew(new mVector("info"), (byte) 15, T.gianHang, -1, (byte) 0));
        GameCanvas.shopNpc = new TabScreenNew();
        GameCanvas.shopNpc.selectTab = 0;
        GameCanvas.shopNpc.addMoreTab(mvector);
        GameCanvas.shopNpc.Show(gI());
    }

    public void paintMonsterEffect(mGraphics mgraphics) {
        for (int i = 0; i < EffectMonster.listEffectMonster.size(); i++) {
            ((EffectMonster) EffectMonster.listEffectMonster.elementAt(i)).paint(mgraphics);
        }
    }

    public void paintMonsterDieEffect(mGraphics mgraphics) {
        for (int i = 0; i < EffectMonster.listEffectMonster.size(); i++) {
            ((EffectMonster) EffectMonster.listEffectMonster.elementAt(i)).paintDie(mgraphics);
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        try {
            dx = 0;
            dy = 0;
            if (LoadMap.timeVibrateScreen > 0) {
                if (LoadMap.timeVibrateScreen > 100) {
                    dy = CRes.random_Am_0(3);
                    if (LoadMap.timeVibrateScreen == 101) {
                        LoadMap.timeVibrateScreen = 0;
                    }
                } else {
                    dy = CRes.random_Am_0(3);
                    dx = CRes.random_Am(0, 2);
                }
                LoadMap.timeVibrateScreen--;
            }
            mgraphics.translate(dx, dy);
            mgraphics.translate(-MainScreen.cameraMain.xCam, -MainScreen.cameraMain.yCam);
            if (GameCanvas.mapBack != null) {
                GameCanvas.mapBack.paint(mgraphics);
            }
            if (LoadMap.idTile == 9) {
                mgraphics.setColor(367554);
                mgraphics.fillRect(MainScreen.cameraMain.xCam, MainScreen.cameraMain.yCam, GameCanvas.w, GameCanvas.h, false);
            }
            GameCanvas.loadmap.paint(mgraphics);
            infoGame.paintShip(mgraphics);
            if (this.isFullScreen) {
                mgraphics.drawRecAlpa(0, 0, GameCanvas.loadmap.mapW * 24, GameCanvas.loadmap.mapH * 24, this.colorRec);
            } else if (this.wRec > 0) {
                mgraphics.fillRecAlpla(this.xRec, this.yRec, this.wRec, this.hRec, this.colorRec);
            }
            for (int i = 0; i < vecDataeff.size(); i++) {
                DataSkillEff dataSkillEff = (DataSkillEff) vecDataeff.elementAt(i);
                if (dataSkillEff != null) {
                    dataSkillEff.paintTop(mgraphics, dataSkillEff.x, dataSkillEff.y);
                }
            }
            for (int i2 = 0; i2 < LoadMap.Thacnuoc.size(); i2++) {
                ((ThacNuoc) LoadMap.Thacnuoc.elementAt(i2)).paint(mgraphics);
            }
            for (int i3 = 0; i3 < vecStep.size(); i3++) {
                ((Point) vecStep.elementAt(i3)).paint(mgraphics);
            }
            for (int i4 = 0; i4 < vecEffInMap.size(); i4++) {
                ((Point) vecEffInMap.elementAt(i4)).paint(mgraphics);
            }
            if (help.Step >= 0 && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.currentScreen == this && !ChatTextField.isShow && GameCanvas.subDialog == null && GameCanvas.currentScreen == GameCanvas.game) {
                help.paintHelpFrist(mgraphics);
            }
            if (GameCanvas.isTouch && Player.timeFocus > 0 && player.posTransRoad != null && Player.xFocus >= 0) {
                PaintInfoGameScreen.fraFocusIngame.drawFrame((4 - (Player.timeFocus / 2)) % PaintInfoGameScreen.fraFocusIngame.nFrame, Player.xFocus, Player.yFocus, 0, 3, mgraphics);
            }
            EffectManager.lowEffects.paintAll(mgraphics);
            paintMonsterDieEffect(mgraphics);
            ItemMap.paintDieHouseArena(mgraphics);
            try {
                this.p = 0;
                this.o = 0;
                this.maxob.y = ObjectData.ICON_PET;
                maxtr.y = ObjectData.ICON_PET;
                while (true) {
                    if (this.p >= Vecplayers.size() && this.o >= LoadMap.mItemMap.size()) {
                        break;
                    }
                    this.ob = this.maxob;
                    tr = maxtr;
                    if (this.p < Vecplayers.size()) {
                        this.ob = (MainObject) Vecplayers.elementAt(this.p);
                    }
                    if (this.o < LoadMap.mItemMap.size()) {
                        tr = (MainItemMap) LoadMap.mItemMap.elementAt(this.o);
                    }
                    if (tr == null || this.ob.y + this.ob.ysai < tr.y + LoadMap.wTile) {
                        if (MainObject.isInScreen(this.ob) && !this.ob.isStop && !this.ob.isRemove) {
                            this.ob.paint(mgraphics);
                            if (this.ob.chat != null && this.ob.chat.strChat != null) {
                                this.ob.chat.paint(mgraphics);
                            }
                        }
                        this.p++;
                        if (tr == null) {
                            this.o++;
                        }
                    } else {
                        if (tr.isInScreen() && tr != null) {
                            tr.paint(mgraphics);
                        }
                        this.o++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                mSystem.outloi("loi gameScreen paint Doi tuong");
            }
            for (int i5 = 0; i5 < arrowsUp.size(); i5++) {
                IArrow iArrow = (IArrow) arrowsUp.elementAt(i5);
                if (iArrow != null) {
                    iArrow.paint(mgraphics);
                }
            }
            for (int i6 = 0; i6 < vecDataeff.size(); i6++) {
                DataSkillEff dataSkillEff2 = (DataSkillEff) vecDataeff.elementAt(i6);
                if (dataSkillEff2 != null) {
                    dataSkillEff2.paintBottom(mgraphics, dataSkillEff2.x, dataSkillEff2.y);
                }
            }
            paintMonsterEffect(mgraphics);
            for (int i7 = 0; i7 < LoadMap.vecPointChange.size(); i7++) {
                Point point = (Point) LoadMap.vecPointChange.elementAt(i7);
                mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, LoadMap.mTranPointChangeMap[point.dis], point.x + ((GameCanvas.gameTick % 6) * point.vx), point.y + ((GameCanvas.gameTick % 6) * point.vy), 3, false);
                AvMain.Font3dWhite(mgraphics, point.name, point.x2 + ((GameCanvas.gameTick % 6) * point.vx), point.y2 + ((GameCanvas.gameTick % 6) * point.vy), point.f);
            }
            if (ObjFocus != null && ObjFocus.typeObject != 1) {
                ObjFocus.paintNameFocus(mgraphics);
            }
            int size = VecNum.size();
            EffectManager.hiEffects.paintAll(mgraphics);
            if (vecHightEffAuto.size() > 0) {
                for (int i8 = 0; i8 < vecHightEffAuto.size(); i8++) {
                    EffectAuto effectAuto = (EffectAuto) vecHightEffAuto.elementAt(i8);
                    if (effectAuto != null) {
                        effectAuto.paint(mgraphics);
                    }
                }
            }
            for (int i9 = 0; i9 < size; i9++) {
                MainEffect mainEffect = (MainEffect) VecNum.elementAt(i9);
                if (MainEffect.isInScreen(mainEffect.x, mainEffect.y, 10, 10) && !mainEffect.isRemove && !mainEffect.isStop) {
                    mainEffect.paint(mgraphics);
                }
            }
            for (int i10 = 0; i10 < vecWeather.size(); i10++) {
                ((AnimateEffect) vecWeather.elementAt(i10)).paint(mgraphics);
            }
            MainObject.paintFocus(mgraphics);
            GameCanvas.resetTrans(mgraphics);
            if (timePaintCmdGiaotiep > 0 && this.cmdGiaotiep != null) {
                this.cmdGiaotiep.paint(mgraphics, this.cmdGiaotiep.xCmd, this.cmdGiaotiep.yCmd);
            }
            if ((!GameCanvas.menu2.isShowMenu || Menu2.isNPCMenu == 2) && ((GameCanvas.currentDialog == null || ((help.Step == 5 && help.Next < 8) || (GameCanvas.isTouch && (help.Step == 0 || help.Step == 1)))) && GameCanvas.currentScreen == this && !ChatTextField.isShow && GameCanvas.subDialog == null && GameCanvas.currentScreen == GameCanvas.game && (PaintInfoGameScreen.isShowInGame || PaintInfoGameScreen.hShowInGame < 100))) {
                if (player.currentQuest == null) {
                    if (GameCanvas.isTouch) {
                        mgraphics.translate(GameCanvas.w - (GameCanvas.minimap.maxX * MiniMap.wMini), 0 - PaintInfoGameScreen.hShowInGame);
                    } else {
                        mgraphics.translate((GameCanvas.w - (GameCanvas.minimap.maxX * MiniMap.wMini)) - 3, ((GameCanvas.h - 23) - (GameCanvas.minimap.maxY * MiniMap.wMini)) + PaintInfoGameScreen.hShowInGame);
                    }
                    if (GameCanvas.isTouch) {
                        if (!infoGame.isMapThachdau() && !infoGame.isMapchienthanh()) {
                            AvMain.paintDialog(mgraphics, (GameCanvas.minimap.maxX * MiniMap.wMini) - 40, (GameCanvas.minimap.maxY * MiniMap.wMini) - 19, 40, 35, 1);
                            mFont.tahoma_7b_black.drawString(mgraphics, new StringBuffer().append(T.Area).append((int) LoadMap.getAreaPaint()).toString(), ((GameCanvas.minimap.maxX * MiniMap.wMini) - 37) + 17, (GameCanvas.minimap.maxY * MiniMap.wMini) + 3, 2, false);
                        }
                        if (infoGame.isMapArena(GameCanvas.loadmap.idMap)) {
                            infoGame.paintTimeHS(mgraphics);
                            String timeArena2 = LoadMap.getTimeArena(timeArena);
                            if (!timeArena2.equals("")) {
                                mgraphics.fillRect(((GameCanvas.minimap.maxX * MiniMap.wMini) - (GameCanvas.w / 2)) - 18, (GameCanvas.minimap.maxY * MiniMap.wMini) - 38, 36, 17, 0, 60, false);
                                AvMain.Font3dWhite(mgraphics, timeArena2, (GameCanvas.minimap.maxX * MiniMap.wMini) - (GameCanvas.w / 2), (GameCanvas.minimap.maxY * MiniMap.wMini) - 35, 2);
                            }
                        } else {
                            String timeSpecialRegion = LoadMap.getTimeSpecialRegion();
                            if (!timeSpecialRegion.equals("")) {
                                mFont.tahoma_7_white.drawString(mgraphics, timeSpecialRegion, (GameCanvas.minimap.maxX * MiniMap.wMini) - 42, (GameCanvas.minimap.maxY * MiniMap.wMini) + 3, 1, false);
                            }
                        }
                    } else if (GameCanvas.isSmallScreen) {
                        mFont.tahoma_7_black.drawString(mgraphics, new StringBuffer().append(T.Area).append((int) LoadMap.getAreaPaint()).toString(), 14, -13, 2, false);
                        if (infoGame.isMapArena(GameCanvas.loadmap.idMap)) {
                            infoGame.paintTimeHS(mgraphics);
                            String timeArena3 = LoadMap.getTimeArena(timeArena);
                            if (!timeArena3.equals("")) {
                                AvMain.Font3dWhite(mgraphics, timeArena3, 14, -30, 1);
                            }
                        } else {
                            String timeSpecialRegion2 = LoadMap.getTimeSpecialRegion();
                            if (!timeSpecialRegion2.equals("")) {
                                mFont.tahoma_7_white.drawString(mgraphics, timeSpecialRegion2, 14, -30, 1, false);
                            }
                        }
                    } else {
                        AvMain.paintDialog(mgraphics, (GameCanvas.minimap.maxX * MiniMap.wMini) - 37, -17, 40, 35, 1);
                        mFont.tahoma_7b_black.drawString(mgraphics, new StringBuffer().append(T.Area).append((int) LoadMap.getAreaPaint()).toString(), ((GameCanvas.minimap.maxX * MiniMap.wMini) - 34) + 17, -14, 2, false);
                        if (infoGame.isMapArena(GameCanvas.loadmap.idMap)) {
                            infoGame.paintTimeHS(mgraphics);
                            String timeArena4 = LoadMap.getTimeArena(timeArena);
                            if (!timeArena4.equals("")) {
                                AvMain.Font3dWhite(mgraphics, timeArena4, (GameCanvas.minimap.maxX * MiniMap.wMini) - 39, -14, 1);
                            }
                        } else {
                            String timeSpecialRegion3 = LoadMap.getTimeSpecialRegion();
                            if (!timeSpecialRegion3.equals("")) {
                                mFont.tahoma_7_white.drawString(mgraphics, timeSpecialRegion3, (GameCanvas.minimap.maxX * MiniMap.wMini) - 39, -14, 1, false);
                            }
                        }
                    }
                    GameCanvas.minimap.paint(mgraphics);
                    GameCanvas.resetTrans(mgraphics);
                    PaintInfoGameScreen paintInfoGameScreen = infoGame;
                    int i11 = GameCanvas.w;
                    int i12 = GameCanvas.minimap.maxX;
                    MiniMap miniMap = GameCanvas.minimap;
                    paintInfoGameScreen.paintPos_minimap(mgraphics, (i11 - (i12 * MiniMap.wMini)) + 21, 0);
                    if (player != null && !mSystem.isj2me) {
                        if (player.Action == 4) {
                            mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
                        } else if (player.ispaintHit) {
                            PaintInfoGameScreen.paintHitscr(mgraphics, player.isMaxdame);
                        }
                    }
                    infoGame.paintInfoPlayer(mgraphics, 0, 0 - PaintInfoGameScreen.hShowInGame, !GameCanvas.isSmallScreen, mFont.tahoma_7_white);
                    infoGame.paintInfoThachDau(mgraphics, 0, 0 - PaintInfoGameScreen.hShowInGame);
                    infoGame.paintInfoThachDauOtherPlayer(mgraphics, (GameCanvas.w / 2) + 20, 0 - PaintInfoGameScreen.hShowInGame);
                    infoGame.paintInfoFocus(mgraphics);
                    if (!GameCanvas.isTouch && player.Action != 4) {
                        infoGame.paintKillPlayer(mgraphics);
                    }
                    infoGame.PaintIconPlayer(mgraphics);
                    if (!GameCanvas.menu2.isShowMenu) {
                        infoGame.paintPoiterAll(mgraphics);
                    }
                    if (PaintInfoGameScreen.numMess > 0) {
                        mgraphics.drawImage(AvMain.imgMess, PaintInfoGameScreen.xMess, PaintInfoGameScreen.yMess - PaintInfoGameScreen.hShowInGame, 0, false);
                        String stringBuffer = new StringBuffer().append(PaintInfoGameScreen.numMess).append("").toString();
                        if (PaintInfoGameScreen.numMess > 20) {
                            stringBuffer = "20+";
                        }
                        AvMain.Font3dWhite(mgraphics, stringBuffer, PaintInfoGameScreen.xMess + 17, (PaintInfoGameScreen.yMess - 1) - PaintInfoGameScreen.hShowInGame, 0);
                    }
                }
                GameCanvas.resetTrans(mgraphics);
                if (infoGame.strInfoServer == null && PaintInfoGameScreen.isShowInGame && !GameCanvas.menu2.isShowMenu) {
                    if (GameCanvas.isTouch) {
                        paintCmd(mgraphics);
                    } else {
                        paintCmd_OnlyText(mgraphics);
                    }
                }
                if (this.imgCombo != null) {
                    mgraphics.drawImage(this.imgCombo, 5, 46, 0, false);
                }
            }
            GameCanvas.resetTrans(mgraphics);
            if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                if (!isFinishHelp && help.Step >= 0 && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && !ChatTextField.isShow && GameCanvas.subDialog == null && GameCanvas.currentScreen == GameCanvas.game) {
                    help.paintHelpLast(mgraphics);
                }
            } else if (help.Step >= 0 && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && !ChatTextField.isShow && GameCanvas.subDialog == null && GameCanvas.currentScreen == GameCanvas.game) {
                help.paintHelpLast(mgraphics);
            }
            infoGame.paintNameMap(mgraphics);
            infoGame.paintIconClan(mgraphics);
            if (PaintInfoGameScreen.paint18plush == 1 && canpaint18plus()) {
                PaintInfoGameScreen.paintinfo18plush(mgraphics);
            }
            for (int i13 = 0; i13 < vecTimecountDown.size(); i13++) {
                TimecountDown timecountDown = (TimecountDown) vecTimecountDown.elementAt(i13);
                if (timecountDown != null) {
                    timecountDown.paint(mgraphics);
                }
            }
            if (charcountdonw != null) {
                charcountdonw.paint(mgraphics, (GameCanvas.w / 2) - 31, GameCanvas.h - 30);
            }
            GameCanvas.resetTrans(mgraphics);
            if (textServer != null && textServer.length > 0) {
                for (int i14 = 0; i14 < textServer.length; i14++) {
                    AvMain.Font3dWhite(mgraphics, textServer[i14], GameCanvas.w - 10, (GameCanvas.minimap.maxY * MiniMap.wMini) + 15 + (i14 * GameCanvas.hText), 1);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean canpaint18plus() {
        if (idmap18 != null) {
            for (int i = 0; i < idmap18.length; i++) {
                if (idmap18[i] == GameCanvas.loadmap.idMap) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public void checkPaintNamearena() {
        if (GameCanvas.gameTick % 50 == 0) {
            for (int i = 0; i < Vecplayers.size() - 1; i++) {
                ((MainObject) Vecplayers.elementAt(i)).Namearena = false;
            }
            for (int i2 = 0; i2 < Vecplayers.size() - 1; i2++) {
                MainObject mainObject = (MainObject) Vecplayers.elementAt(i2);
                if (mainObject != null && mainObject.typeObject == 0) {
                    for (int i3 = i2 + 1; i3 < Vecplayers.size(); i3++) {
                        MainObject mainObject2 = (MainObject) Vecplayers.elementAt(i3);
                        if (mainObject2.typeObject == 0 && mainObject2 != null && CRes.abs(mainObject.x - mainObject2.x) <= 20 && CRes.abs(mainObject.y - mainObject2.y) <= 20) {
                            mainObject.Namearena = true;
                            mainObject2.Namearena = true;
                        }
                    }
                }
            }
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (GameCanvas.loadmap != null && player != null) {
            LyMeowzWorldStore.ensureLoaded(player.name);
            if (GameCanvas.gameTick % 4 == 0 && player.hp > 0 &&
                    GameCanvas.currentScreen == GameCanvas.game && LoadMapScreen.isNextMap &&
                    (player.vx != 0 || player.vy != 0 || player.posTransRoad != null ||
                     player.toX != player.x || player.toY != player.y)) {
                for (int ly = 0; ly < LoadMap.vecPointChange.size(); ly++) {
                    Model.Point gate = (Model.Point) LoadMap.vecPointChange.elementAt(ly);
                    if (MainObject.getDistance(player.x, player.y, gate.x, gate.y) <= 36) {
                        LyMeowzMapTransitions.armGate(GameCanvas.loadmap.idMap,
                                LoadMap.Area & 255, gate.x, gate.y, System.currentTimeMillis());
                        break;
                    }
                }
            }
            if (GameCanvas.gameTick % 120 == 0) LyMeowzWorldStore.flushIfDirty();
        }
        if (GameCanvas.loadmap != null) {
            for (int ly = 0; ly < GameObjects.MainQuest.vecQuestFinish.size(); ly++) {
                GameObjects.MainQuest lyQuest = (GameObjects.MainQuest) GameObjects.MainQuest.vecQuestFinish.elementAt(ly);
                if (lyQuest != null && MainObject.get_Object(lyQuest.idNPC_To, (byte) 2) != null)
                    LyMeowzNpcMapRegistry.remember(lyQuest.idNPC_To, GameCanvas.loadmap.idMap, LoadMap.Area & 255);
            }
        }
        updateLyMeowzQuestTravel();
        int i = 0;
        while (i < vecDataeff.size()) {
            try {
                DataSkillEff dataSkillEff = (DataSkillEff) vecDataeff.elementAt(i);
                if (dataSkillEff != null) {
                    dataSkillEff.update();
                    if (dataSkillEff.wantDetroy) {
                        vecDataeff.removeElement(dataSkillEff);
                        i--;
                    }
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        int i2 = 0;
        while (i2 < vecEffInMap.size()) {
            Point point = (Point) vecEffInMap.elementAt(i2);
            point.updateInMap();
            if (point.isRemove) {
                vecEffInMap.removeElement(point);
                i2--;
            }
            i2++;
        }
        int i3 = 0;
        while (i3 < vecStep.size()) {
            Point point2 = (Point) vecStep.elementAt(i3);
            point2.updateInMap();
            if (point2.isRemove) {
                vecStep.removeElement(point2);
                i3--;
            }
            i3++;
        }
        for (int i4 = 0; i4 < vecTimecountDown.size(); i4++) {
            TimecountDown timecountDown = (TimecountDown) vecTimecountDown.elementAt(i4);
            timecountDown.update();
            if (timecountDown.wantdestroy) {
                vecTimecountDown.removeElement(timecountDown);
            }
        }
        if (charcountdonw != null) {
            charcountdonw.update();
            if (charcountdonw.WantDestroy) {
                charcountdonw = null;
            }
        }
        for (int i5 = 0; i5 < LoadMap.Thacnuoc.size(); i5++) {
            ((ThacNuoc) LoadMap.Thacnuoc.elementAt(i5)).update();
        }
        if (help.Step >= 0) {
            help.updateHelp();
        }
        if (Player.timeFocus > 0) {
            Player.timeFocus--;
        }
        if (GameCanvas.mapBack != null) {
            GameCanvas.mapBack.updateCloud();
        }
        for (int i6 = 0; i6 < LoadMap.mItemMap.size(); i6++) {
            MainItemMap mainItemMap = (MainItemMap) LoadMap.mItemMap.elementAt(i6);
            if (mainItemMap.TypeItem == 1) {
                mainItemMap.update();
            }
        }
        int i7 = 0;
        while (i7 < Vecplayers.size()) {
            MainObject mainObject = (MainObject) Vecplayers.elementAt(i7);
            if (mainObject.isStop && !mainObject.isRemove) {
                mainObject.timeStop++;
                if (mainObject.timeStop >= 5) {
                    mainObject.isRemove = true;
                }
            } else if (mainObject == null || mainObject.isRemove) {
                Vecplayers.removeElementAt(i7);
                i7--;
            } else {
                mainObject.update();
                mainObject.ySort = mainObject.y + mainObject.ysai;
            }
            i7++;
        }
        for (int size = arrowsUp.size() - 1; size >= 0; size--) {
            IArrow iArrow = (IArrow) arrowsUp.elementAt(size);
            iArrow.update();
            if (iArrow.wantDestroy) {
                arrowsUp.removeElementAt(size);
            }
        }
        CRes.quickSort(Vecplayers);
        checkPaintNamearena();
        int i8 = 0;
        while (i8 < VecNum.size()) {
            MainEffect mainEffect = (MainEffect) VecNum.elementAt(i8);
            if (mainEffect == null || mainEffect.isRemove) {
                VecNum.removeElement(mainEffect);
                i8--;
            } else if (!mainEffect.isStop) {
                mainEffect.update();
            }
            i8++;
        }
        if (LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) {
            MainScreen.cameraMain.moveCamera(MainScreen.cameraMain.xLimit / 2, MainScreen.cameraMain.yLimit / 2);
        } else if (player.currentQuest == null) {
            if (isMoveCamera) {
                MainScreen.cameraMain.setCameraWithLim(xCur - xMoveCam, yCur - yMoveCam);
            } else {
                MainScreen.cameraMain.moveCamera(player.x - GameCanvas.hw, player.y - GameCanvas.hh);
            }
        } else {
            MainObject mainObject2 = MainObject.get_Object(player.currentQuest.idNPCChat, (byte) 2);
            if (mainObject2 != null) {
                MainScreen.cameraMain.moveCamera(player.x - GameCanvas.hw, (mainObject2.y - GameCanvas.hh) - (GameCanvas.hh / 4));
            } else {
                MainScreen.cameraMain.moveCamera(player.x - GameCanvas.hw, player.y - GameCanvas.hh);
            }
        }
        MainScreen.cameraMain.UpdateCamera();
        GameCanvas.minimap.miniCamera.moveCamera(((player.x / LoadMap.wTile) - (GameCanvas.minimap.maxX / 2)) * MiniMap.wMini, ((player.y / LoadMap.wTile) - (GameCanvas.minimap.maxY / 2)) * MiniMap.wMini);
        GameCanvas.minimap.miniCamera.UpdateCamera();
        boolean z = false;
        int i9 = 0;
        while (i9 < vecWeather.size()) {
            z = true;
            AnimateEffect animateEffect = (AnimateEffect) vecWeather.elementAt(i9);
            animateEffect.update();
            if (animateEffect.type == 4) {
                if (animateEffect.isStop) {
                    vecWeather.removeElement(animateEffect);
                    i9--;
                }
            } else if (animateEffect.type == 5) {
                if (animateEffect.isStop && animateEffect.list.size() == 0) {
                    vecWeather.removeElement(animateEffect);
                    i9--;
                }
            } else if (animateEffect.list.size() == 0) {
                vecWeather.removeElement(animateEffect);
                i9--;
            }
            i9++;
        }
        if (z) {
            AnimateEffect.updateWind();
        }
        if (GameCanvas.gameTick % 200 == 0) {
            ImageEffect.SetRemove();
            ImageEffectAuto.setRemove();
            DataSkillEff.SetRemove();
            CRes.setRemoveCharPartInfo();
            GameData.SetRemove();
            MainObject.SetRemove();
            EffectAuto.SetRemove();
        }
        if (ObjFocus != null && ObjFocus.isRemove) {
            ObjFocus = null;
        }
        if (Player.autoItem != null && Player.autoItem.isremove) {
            Player.autoItem = null;
            MainRMS.setSaveAuto();
        }
        if (timePaintCmdGiaotiep > 0) {
            if (this.cmdGiaotiep != null) {
                MainObject mainObject3 = MainObject.get_Object(this.cmdGiaotiep.IdGiaotiep, (byte) 0);
                if (mainObject3 == null) {
                    timePaintCmdGiaotiep = (byte) 0;
                } else {
                    this.cmdGiaotiep.setPosXY(mainObject3.x - MainScreen.cameraMain.xCam, ((mainObject3.y - MainScreen.cameraMain.yCam) - mainObject3.hOne) - 30);
                }
            }
            timePaintCmdGiaotiep = (byte) (timePaintCmdGiaotiep - 1);
        }
        if (!GameCanvas.isTouch && (GameCanvas.timeNow - timeCheckDelHash) / 1000 > 300) {
            checkClear();
        }
        if (demNumSoundEff > 0 && GameCanvas.gameTick % 30 == 0) {
            demNumSoundEff--;
        }
        EffectManager.hiEffects.updateAll();
        EffectManager.lowEffects.updateAll();
        if (vecHightEffAuto.size() > 0) {
            for (int i10 = 0; i10 < vecHightEffAuto.size(); i10++) {
                EffectAuto effectAuto = (EffectAuto) vecHightEffAuto.elementAt(i10);
                if (effectAuto.wantdestroy) {
                    vecHightEffAuto.removeElement(effectAuto);
                }
                if (effectAuto != null) {
                    effectAuto.update();
                }
            }
        }
        infoGame.countTimeHS();
        for (int i11 = 0; i11 < EffectMonster.listEffectMonster.size(); i11++) {
            ((EffectMonster) EffectMonster.listEffectMonster.elementAt(i11)).update();
        }
    }

    public void checkClear() {
        timeCheckDelHash = GameCanvas.timeNow;
        ObjectData.checkDelHash(MainMonster.HashImageMonster);
        ObjectData.checkDelHash(HashImageItemMap);
        ObjectData.checkDelHash(Item.HashImageIconClan);
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (!Player.isLockKey) {
            super.updatekey();
        }
        if (player != null) {
            player.updateKey();
        }
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (GameCanvas.isTouch) {
            if (timePaintCmdGiaotiep > 0 && this.cmdGiaotiep != null) {
                this.cmdGiaotiep.updatePointer();
            }
            super.updatePointer();
            infoGame.updatePoiterAll();
        }
    }

    public void doMenu() {
        if (player.Action == 1) {
            player.resetMove();
            player.posTransRoad = null;
            player.resetAction();
        }
        mVector mvector = new mVector("GameScr menu2");
        mvector.addElement(this.cmdMyseft);
        mvector.addElement(this.cmdChucNang);
        if (Player.party != null) {
            mvector.addElement(this.cmdParty);
        }
        if (player.typePk == 0 || (ObjFocus != null && ObjFocus.typePk == 0)) {
            mvector.addElement(this.cmdGiaotiep);
        }
        mvector.addElement(this.cmdChangeMap);
        GameCanvas.menu2.startAt(mvector, 2, T.menuChinh, false, null);
        if (help.setStep_Next(1, 9) || help.setStep_Next(6, 2)) {
            Menu2.isHelp = true;
        }
        player.resetAction();
    }

    public void doMenuUseNgua(mVector mvector) {
        this.menuNgua = new mVector("GameScr menungua2");
        this.menuNgua = mvector;
        mVector mvector2 = new mVector("GameScr newvec");
        for (int i = 0; i < mvector.size(); i++) {
            MainItem mainItem = (MainItem) mvector.elementAt(i);
            if (mainItem != null) {
                mvector2.addElement(new iCommand(mainItem.itemName, 26, i, this));
                GameCanvas.menu2.startAt(mvector2, 2, T.TuseNgua, false, null);
            }
        }
    }

    public void doMenuAuto() {
        mVector mvector = new mVector("GameScr menu3");
        if (Player.isAutoFire > -1) {
            gI().cmdAutoFire.caption = new StringBuffer().append(T.off).append(T.autoFire).toString();
        } else {
            gI().cmdAutoFire.caption = new StringBuffer().append(T.on).append(T.autoFire).toString();
        }
        mvector.addElement(gI().cmdAutoFire);
        if (Player.autoItem != null) {
            gI().cmdAutoItem.caption = new StringBuffer().append(T.off).append(T.autoItem).toString();
        } else {
            gI().cmdAutoItem.caption = new StringBuffer().append(T.on).append(T.autoItem).toString();
        }
        mvector.addElement(gI().cmdAutoItem);
        if (Player.isAutoHPMP) {
            gI().cmdAutoMPHP.caption = new StringBuffer().append(T.off).append(T.autoHP).toString();
        } else {
            gI().cmdAutoMPHP.caption = new StringBuffer().append(T.on).append(T.autoHP).toString();
        }
        mvector.addElement(gI().cmdAutoMPHP);
        mvector.addElement(gI().cmdAutoBuff);
        this.cmdShowAuto.caption = new StringBuffer().append(T.on).append(T.showAuto).toString();
        if (PaintInfoGameScreen.isShowInfoAuto) {
            this.cmdShowAuto.caption = new StringBuffer().append(T.off).append(T.showAuto).toString();
        }
        mvector.addElement(gI().cmdShowAuto);
        GameCanvas.menu2.startAt(mvector, 2, T.auto, false, null);
    }

    public static void addEffectKill(int i, int i2, byte b, mVector mvector) {
        Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short) i2, b);
        if (mSystem.isj2me && infoGame.ismapHouse(GameCanvas.loadmap.idMap)) {
            if (i2 != player.ID && EffectSkill.countSkillArena <= 3) {
                EffectSkill.countSkillArena = (byte) (EffectSkill.countSkillArena + 1);
            }
            StartAddEffectSkill(i, object_Effect_Skill, mvector);
            return;
        }
        StartAddEffectSkill(i, object_Effect_Skill, mvector);
    }

    public static void StartAddEffectSkill(int i, Object_Effect_Skill object_Effect_Skill, mVector mvector) {
        if (i == 119) {
            addEffect2Vector(new EffectSkill(114, object_Effect_Skill, mvector, 0));
            addEffect2Vector(new EffectSkill(115, object_Effect_Skill, mvector, 0));
            return;
        }
        if (i == 118) {
            addEffect2Vector(new EffectSkill(65, object_Effect_Skill, mvector, 0));
            mVector mvector2 = new mVector();
            for (int i2 = 0; i2 < mvector.size(); i2++) {
                Object_Effect_Skill object_Effect_Skill2 = (Object_Effect_Skill) mvector.elementAt(i2);
                if (object_Effect_Skill2 != null) {
                    mvector2.addElement(object_Effect_Skill2);
                    addEffect2Vector(new EffectSkill(62, object_Effect_Skill, mvector2, 0));
                    mvector2.removeAllElements();
                }
            }
            return;
        }
        if (i == 117) {
            addEffect2Vector(new EffectSkill(20, object_Effect_Skill, mvector, 0));
            addEffect2Vector(new EffectSkill(91, object_Effect_Skill, mvector, 0));
            return;
        }
        if (i == 116) {
            addEffect2Vector(new EffectSkill(53, object_Effect_Skill, mvector, 0));
            addEffect2Vector(new EffectSkill(77, object_Effect_Skill, mvector, 0));
            return;
        }
        if (i == 123) {
            addEffect2Vector(new EffectSkill(60, object_Effect_Skill, mvector, 0));
            mVector mvector3 = new mVector();
            for (int i3 = 0; i3 < mvector.size(); i3++) {
                Object_Effect_Skill object_Effect_Skill3 = (Object_Effect_Skill) mvector.elementAt(i3);
                if (object_Effect_Skill3 != null) {
                    mvector3.addElement(object_Effect_Skill3);
                    addEffect2Vector(new EffectSkill(49, object_Effect_Skill, mvector3, 0));
                    mvector3.removeAllElements();
                }
            }
            return;
        }
        if (i == 122) {
            mVector mvector4 = new mVector();
            for (int i4 = 0; i4 < mvector.size(); i4++) {
                Object_Effect_Skill object_Effect_Skill4 = (Object_Effect_Skill) mvector.elementAt(i4);
                if (object_Effect_Skill4 != null) {
                    mvector4.addElement(object_Effect_Skill4);
                    addEffect2Vector(new EffectSkill(51, object_Effect_Skill, mvector4, 0));
                    mvector4.removeAllElements();
                }
            }
            addEffect2Vector(new EffectSkill(66, object_Effect_Skill, mvector, 0));
            return;
        }
        if (i == 121) {
            addEffect2Vector(new EffectSkill(34, object_Effect_Skill, mvector, 0));
            mVector mvector5 = new mVector();
            for (int i5 = 0; i5 < mvector.size(); i5++) {
                Object_Effect_Skill object_Effect_Skill5 = (Object_Effect_Skill) mvector.elementAt(i5);
                if (object_Effect_Skill5 != null) {
                    mvector5.addElement(object_Effect_Skill5);
                    addEffect2Vector(new EffectSkill(55, object_Effect_Skill, mvector5, 0));
                    mvector5.removeAllElements();
                }
            }
            return;
        }
        if (i == 120) {
            mVector mvector6 = new mVector();
            for (int i6 = 0; i6 < mvector.size(); i6++) {
                Object_Effect_Skill object_Effect_Skill6 = (Object_Effect_Skill) mvector.elementAt(i6);
                if (object_Effect_Skill6 != null) {
                    mvector6.addElement(object_Effect_Skill6);
                    addEffect2Vector(new EffectSkill(54, object_Effect_Skill, mvector6, 0));
                    mvector6.removeAllElements();
                }
            }
            addEffect2Vector(new EffectSkill(11, object_Effect_Skill, mvector, 0));
            return;
        }
        if (infoGame.isMapchienthanh() || infoGame.ismapHouse(GameCanvas.loadmap.idMap)) {
            if (object_Effect_Skill.ID == player.ID) {
                addEffect2Vector(new EffectSkill(i, object_Effect_Skill, mvector, 0));
                return;
            }
            EffectSkill effectSkill = new EffectSkill(i, object_Effect_Skill, mvector, 0);
            if (effectSkill.levelPaint == -1) {
                if (EffectManager.hiEffects.size() <= 20) {
                    addEffect2Vector(effectSkill);
                    return;
                }
                return;
            } else {
                if (EffectManager.lowEffects.size() <= 20) {
                    addEffect2Vector(effectSkill);
                    return;
                }
                return;
            }
        }
        addEffect2Vector(new EffectSkill(i, object_Effect_Skill, mvector, 0));
    }

    public static void addEffectKill(int i, int i2, byte b, int i3, byte b2, int i4, int i5) {
        mVector mvector = new mVector("GameScr vec3");
        Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short) i3, b2);
        object_Effect_Skill.setHP(i4, i5);
        mvector.addElement(object_Effect_Skill);
        addEffect2Vector(new EffectSkill(i, new Object_Effect_Skill((short) i2, b), mvector, 0));
    }

    public static void addEffectKill(int i, int i2, byte b, int i3, byte b2, int i4, int i5, byte b3) {
        mVector mvector = new mVector("GameScr vec");
        Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short) i3, b2);
        object_Effect_Skill.setHP(i4, i5);
        mvector.addElement(object_Effect_Skill);
        addEffect2Vector(new EffectSkill(i, new Object_Effect_Skill((short) i2, b), mvector, b3));
    }

    public static void addEffectKill(int i, int i2, byte b, mVector mvector, byte b2) {
        Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short) i2, b);
        if (!EffectSkill.isMultiTarget(i)) {
            addEffect2Vector(new EffectSkill(i, object_Effect_Skill, mvector, b2));
            return;
        }
        for (int i3 = 0; i3 < mvector.size(); i3++) {
            mVector mvector2 = new mVector("GameScr vector");
            mvector2.addElement((Object_Effect_Skill) mvector.elementAt(i3));
            addEffect2Vector(new EffectSkill(i, object_Effect_Skill, mvector2, b2));
        }
    }

    public static void StartEffect_Chiemthanh(int i, int i2, byte b, mVector mvector, byte b2) {
        MainObject mainObject = MainObject.get_Object(i2, b);
        if (mainObject.x <= 216) {
            for (int i3 = 0; i3 < 3; i3++) {
                EffectManager.addHiEffect(new EffectSkill(mainObject.x, mainObject.y + 12, mainObject.x + posSkill[0][i3], mainObject.y + posSkill[1][i3], (byte) 0));
            }
            return;
        }
        if (mainObject.x >= 384) {
            for (int i4 = 0; i4 < 3; i4++) {
                EffectManager.addHiEffect(new EffectSkill(mainObject.x, mainObject.y + 12, mainObject.x + posSkill[0][i4], mainObject.y + posSkill[1][i4], (byte) 1));
            }
        }
    }

    public static void addEffectKillSubTime(int i, int i2, byte b, mVector mvector, byte b2, int i3) {
        EffectSkill effectSkill = new EffectSkill(i, new Object_Effect_Skill((short) i2, b), mvector, b2);
        effectSkill.timeRemove = i3;
        addEffect2Vector(effectSkill);
    }

    public static void addEffectKillSubTime(int i, int i2, byte b, int i3, byte b2, int i4, int i5, byte b3, int i6) {
        mVector mvector = new mVector("GameScr vec2");
        Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill((short) i3, b2);
        object_Effect_Skill.setHP(i4, i5);
        mvector.addElement(object_Effect_Skill);
        addEffect2Vector(new EffectSkill(i, new Object_Effect_Skill((short) i2, b), mvector, b3));
    }

    public static MainItemMap addEffectAuto(String str, String str2) {
        return new EffectAuto(str, str2);
    }

    public static void addEffectKillTime(int i, int i2, byte b, mVector mvector, int i3, int i4) {
        EffectSkill effectSkill = new EffectSkill(i, new Object_Effect_Skill((short) i2, b), mvector, i4);
        effectSkill.timeRemove = i3;
        addEffect2Vector(effectSkill);
    }

    public static void addEffect2Vector(MainEffect mainEffect) {
        if (mainEffect.levelPaint != -1) {
            EffectManager.addHiEffect(mainEffect);
        } else {
            EffectManager.addLowEffect(mainEffect);
        }
    }

    public static void addEffectEndKill(int i, int i2, int i3) {
        addEffect2Vector(new EffectEnd(i, i2, i3));
    }

    public static void addEffectEndFromSv(int i, int i2, int i3, int i4) {
        addEffect2Vector(new EffectEnd(i, i2, i3, i4));
    }

    public static void addEffectEndKill_Direction(int i, int i2, int i3, int i4, byte b) {
        addEffect2Vector(new EffectEnd(i, i2, i3, i4, b));
    }

    public static void addEffectEndKill_Time(int i, int i2, int i3, long j) {
        addEffect2Vector(new EffectEnd(i, i2, i3, j));
    }

    public static void addEffectKill(int i, int i2, int i3, int i4, short s, byte b) {
        addEffect2Vector(new EffectSkill(i, i2, i3, i4, s, b));
    }

    public static void addEffectNew(int i, int i2, int i3, int i4, short s, byte b, short s2, byte b2, boolean z) {
        EffectSkill effectSkill = new EffectSkill(i, i2, i3, i4, s, b);
        effectSkill.setObjFrom(s2, b2);
        if (z) {
            EffectManager.addLowEffect(effectSkill);
        } else {
            EffectManager.addHiEffect(effectSkill);
        }
    }

    public static void addEffectNum(String str, int i, int i2, int i3, int i4) {
        if (infoGame.isMapchienthanh() && i4 != player.ID) {
            return;
        }
        EffectNum effectNum = new EffectNum(str, i, i2, i3);
        int find_Index_Stop = find_Index_Stop(VecNum);
        if (find_Index_Stop == VecNum.size()) {
            VecNum.addElement(effectNum);
        } else {
            VecNum.setElementAt(effectNum, find_Index_Stop);
        }
    }

    public static void addEffectNum(String str, int i, int i2, int i3) {
        EffectNum effectNum = new EffectNum(str, i, i2, i3);
        int find_Index_Stop = find_Index_Stop(VecNum);
        if (find_Index_Stop == VecNum.size()) {
            VecNum.addElement(effectNum);
        } else {
            VecNum.setElementAt(effectNum, find_Index_Stop);
        }
    }

    public static void addEffectNum(String str, int i, int i2, int i3, int i4, int i5) {
        if (infoGame.isMapchienthanh() && i5 != player.ID) {
            return;
        }
        EffectNum effectNum = new EffectNum(str, i, i2, i3, i4);
        int find_Index_Stop = find_Index_Stop(VecNum);
        if (find_Index_Stop == VecNum.size()) {
            VecNum.addElement(effectNum);
        } else {
            VecNum.setElementAt(effectNum, find_Index_Stop);
        }
    }

    public static void addPlayer(MainObject mainObject) {
        Vecplayers.addElement(mainObject);
    }

    public short[] updateFindRoad(int i, int i2, int i3, int i4, int i5) {
        if (MainObject.getDistance(i * LoadMap.wTile, i2 * LoadMap.wTile, i3 * LoadMap.wTile, i4 * LoadMap.wTile) <= LoadMap.wTile) {
            return null;
        }
        this.xStart = (byte) this.cmxMini;
        this.yStart = (byte) this.cmyMini;
        int i6 = i - this.xStart;
        int i7 = i2 - this.yStart;
        int i8 = i3 - this.xStart;
        int i9 = i4 - this.yStart;
        // The pathfinder operates on a minimap window, not the whole world.
        // Reject out-of-window coordinates before indexing the collision grid.
        if (GameCanvas.loadmap == null || GameCanvas.loadmap.mapFind == null ||
                GameCanvas.loadmap.mapFind.length == 0 ||
                GameCanvas.loadmap.mapFind[0] == null ||
                GameCanvas.loadmap.mapType == null ||
                i6 < 0 || i6 >= GameCanvas.loadmap.mapFind.length ||
                i7 < 0 || i7 >= GameCanvas.loadmap.mapFind[0].length ||
                i8 < 0 || i8 >= GameCanvas.loadmap.mapFind.length ||
                i9 < 0 || i9 >= GameCanvas.loadmap.mapFind[0].length) {
            return new short[i5 + 1];
        }
        for (int i10 = 0; i10 < GameCanvas.loadmap.mapFind.length; i10++) {
            for (int i11 = 0; i11 < GameCanvas.loadmap.mapFind[i10].length; i11++) {
                int i12 = ((this.yStart + i11) * GameCanvas.loadmap.mapW) + this.xStart + i10;
                if (i12 < 0 || i12 >= GameCanvas.loadmap.mapType.length ||
                        GameCanvas.loadmap.mapType[i12] == 1 || GameCanvas.loadmap.mapType[i12] == -1) {
                    // Unloaded tiles must never be treated as walkable.
                    GameCanvas.loadmap.mapFind[i10][i11] = -1;
                } else {
                    GameCanvas.loadmap.mapFind[i10][i11] = 0;
                }
            }
        }
        int i13 = i6;
        int i14 = i7;
        short s = (short) i13;
        short s2 = (short) i14;
        // Use bounded BFS first. Keep the original reverse-path encoding used by
        // the game's movement consumer, falling back to native routing if needed.
        int[] lyTiles = new int[GameCanvas.loadmap.mapFind.length * GameCanvas.loadmap.mapFind[0].length];
        int lyWidth = GameCanvas.loadmap.mapFind.length;
        int lyHeight = GameCanvas.loadmap.mapFind[0].length;
        for (int lyX = 0; lyX < lyWidth; lyX++) {
            for (int lyY = 0; lyY < lyHeight; lyY++) {
                lyTiles[lyY * lyWidth + lyX] = GameCanvas.loadmap.mapFind[lyX][lyY] == -1 ? 1 : 0;
            }
        }
        short[] lyRoute = LyMeowzMapReader.route(lyTiles, lyWidth, lyHeight,
                i6, i7, i8, i9, i5);
        if (lyRoute.length > 0) {
            short[] nativeRoute = new short[lyRoute.length + 1];
            for (int lyStep = 0; lyStep < lyRoute.length; lyStep++) {
                nativeRoute[lyStep] = lyRoute[lyRoute.length - 1 - lyStep];
            }
            nativeRoute[lyRoute.length] = (short) ((i6 << 8) | i7);
            return nativeRoute;
        }
        if (GameCanvas.loadmap.mapFind[i13][i14] == -1 ||
                GameCanvas.loadmap.mapFind[i8][i9] == -1) {
            return new short[i5 + 1];
        }
        GameCanvas.loadmap.mapFind[i13][i14] = 1;
        int i15 = 2;
        int length = GameCanvas.loadmap.mapFind.length;
        int length2 = GameCanvas.loadmap.mapFind[0].length;
        int i16 = 0;
        do {
            i16++;
            if (i16 > 1000 || i15 >= 127) {
                return new short[i5 + 1];
            }
            int i17 = -1;
            int i18 = -1;
            if (i13 + 1 < length && GameCanvas.loadmap.mapFind[i13 + 1][i14] == 0) {
                GameCanvas.loadmap.mapFind[i13 + 1][i14] = (byte) i15;
                i17 = i13 + 1;
                i18 = i14;
            }
            if (i13 - 1 >= 0 && GameCanvas.loadmap.mapFind[i13 - 1][i14] == 0) {
                GameCanvas.loadmap.mapFind[i13 - 1][i14] = (byte) i15;
                if (i17 != -1) {
                    if (CRes.setDis(i17, i18, i8, i9) > CRes.setDis(i13 - 1, i14, i8, i9)) {
                        i17 = i13 - 1;
                        i18 = i14;
                    }
                } else {
                    i17 = i13 - 1;
                    i18 = i14;
                }
            }
            if (i14 + 1 < length2 && GameCanvas.loadmap.mapFind[i13][i14 + 1] == 0) {
                GameCanvas.loadmap.mapFind[i13][i14 + 1] = (byte) i15;
                if (i17 != -1) {
                    if (CRes.setDis(i17, i18, i8, i9) > CRes.setDis(i13, i14 + 1, i8, i9)) {
                        i17 = i13;
                        i18 = i14 + 1;
                    }
                } else {
                    i17 = i13;
                    i18 = i14 + 1;
                }
            }
            if (i14 - 1 >= 0 && GameCanvas.loadmap.mapFind[i13][i14 - 1] == 0) {
                GameCanvas.loadmap.mapFind[i13][i14 - 1] = (byte) i15;
                if (i17 != -1) {
                    if (CRes.setDis(i17, i18, i8, i9) > CRes.setDis(i13, i14 - 1, i8, i9)) {
                        i17 = i13;
                        i18 = i14 - 1;
                    }
                } else {
                    i17 = i13;
                    i18 = i14 - 1;
                }
            }
            boolean z = true;
            if (i17 != -1) {
                z = false;
                i13 = i17;
                i14 = i18;
            } else {
                i14 = 1000;
                i13 = 1000;
            }
            short s3 = 0;
            while (true) {
                short s4 = s3;
                if (s4 >= length) {
                    break;
                }
                short s5 = 0;
                while (s5 < length2) {
                    short s6 = s5;
                    if (GameCanvas.loadmap.mapFind[s4][s6] > 1 && setContinue(s4, s6, GameCanvas.loadmap.mapFind) && GameCanvas.loadmap.mapFind[s4][s6] + CRes.setDis(s4, s6, i8, i9) < i15 + CRes.setDis(i13, i14, i8, i9)) {
                        i13 = s4;
                        i14 = s6;
                        i15 = GameCanvas.loadmap.mapFind[s4][s6];
                        z = false;
                    }
                    s5 = (short) (s6 + 1);
                }
                s3 = (short) (s4 + 1);
            }
            if (i13 != i8 || i14 != i9) {
                if (!z) {
                    i15 = (short) (i15 + 1);
                } else {
                    return new short[i5 + 1];
                }
            } else {
                if (i15 >= 127) {
                    return new short[i5 + 1];
                }
                int i19 = 0;
                short[] sArr = new short[i15];
                while (true) {
                    sArr[i19] = (short) ((i13 << 8) + i14);
                    if (i13 + 1 < length && GameCanvas.loadmap.mapFind[i13 + 1][i14] == GameCanvas.loadmap.mapFind[i13][i14] - 1) {
                        i13 = (short) (i13 + 1);
                    } else if (i13 - 1 >= 0 && GameCanvas.loadmap.mapFind[i13 - 1][i14] == GameCanvas.loadmap.mapFind[i13][i14] - 1) {
                        i13 = (short) (i13 - 1);
                    } else if (i14 + 1 < length2 && GameCanvas.loadmap.mapFind[i13][i14 + 1] == GameCanvas.loadmap.mapFind[i13][i14] - 1) {
                        i14 = (short) (i14 + 1);
                    } else if (i14 - 1 >= 0 && GameCanvas.loadmap.mapFind[i13][i14 - 1] == GameCanvas.loadmap.mapFind[i13][i14] - 1) {
                        i14 = (short) (i14 - 1);
                    }
                    if (i13 != s || i14 != s2) {
                        i19++;
                    } else {
                        sArr[i15 - 1] = (short) ((i6 << 8) + i7);
                        return sArr;
                    }
                }
            }
        } while (i15 <= i5);
        return new short[i15];
    }

    private boolean setContinue(int i, int i2, byte[][] bArr) {
        if (i + 1 < bArr.length && bArr[i + 1][i2] == 0) {
            return true;
        }
        if (i - 1 >= 0 && bArr[i - 1][i2] == 0) {
            return true;
        }
        if (i2 + 1 < bArr[i].length && bArr[i][i2 + 1] == 0) {
            return true;
        }
        if (i2 - 1 >= 0 && bArr[i][i2 - 1] == 0) {
            return true;
        }
        return false;
    }

    public static int find_Index_Stop(mVector mvector) {
        int size = mvector.size();
        for (int i = 0; i < mvector.size(); i++) {
            MainEffect mainEffect = (MainEffect) mvector.elementAt(i);
            if (mainEffect.isStop && !mainEffect.isRemove) {
                return i;
            }
        }
        return size;
    }

    public static void Remove_ChangeMap() {
        LoadMap.mItemMap.removeAllElements();
        vecStep.removeAllElements();
        vecDataeff.removeAllElements();
    }

    public static void Remove_ChangeArea() {
    }

    public static void RemoveLoadMap() {
        for (int i = 0; i < Vecplayers.size(); i++) {
            MainObject mainObject = (MainObject) Vecplayers.elementAt(i);
            if (mainObject != player && mainObject != pet) {
                mainObject.isRemove = true;
                mainObject.isBinded = false;
                mainObject.isDongBang = false;
                mainObject.isSleep = false;
                mainObject.isStun = false;
                mainObject.isMoveOut = false;
                mainObject.isno = false;
                mainObject.isnoBoss84 = false;
            }
        }
        VecNum.removeAllElements();
        vecStep.removeAllElements();
        vecDataeff.removeAllElements();
        EffectManager.hiEffects.reMoveAll();
        EffectManager.lowEffects.reMoveAll();
        player.effAuto = null;
        MiniMap.vecNPC_Map.removeAllElements();
        vecHightEffAuto.removeAllElements();
        player.isBinded = false;
        player.isSleep = false;
        player.isStun = false;
        player.isDongBang = false;
        player.isMoveOut = false;
        player.isno = false;
        player.isnoBoss84 = false;
        ItemMap.isPaintDieHouseArena = false;
    }

    public static boolean isWater(int i, int i2) {
        return GameCanvas.loadmap.getTile(i, i2) == 2;
    }

    public static void AddEffWeather(byte b, boolean z, int i, int i2) {
        vecWeather.addElement(new AnimateEffect(b, z, i, i2));
    }

    public static boolean setIsInScreen(int i, int i2) {
        if (i < MainScreen.cameraMain.xCam || i > MainScreen.cameraMain.xCam + GameCanvas.w || i2 < MainScreen.cameraMain.yCam || i2 > MainScreen.cameraMain.yCam + GameCanvas.h) {
            return false;
        }
        return true;
    }

    public static void addSoundEff(int i) {
        if (demNumSoundEff <= 3) {
            mSound.playSound(i, mSound.volumeSound);
            demNumSoundEff++;
        }
    }

    public static void addEffInMap(int i, int i2, int i3, int i4) {
        if (GameCanvas.lowGraphic) {
            return;
        }
        if (i3 == 0) {
            if (GameCanvas.gameTick % 4 == 0) {
                mSound.playSound(46, mSound.volumeSound);
            }
        } else if (i3 == 1 && GameCanvas.gameTick % 4 == 0) {
            mSound.playSound(51, mSound.volumeSound);
        }
        if (LoadMap.idTile != 3 && LoadMap.idTile != 8) {
            return;
        }
        Point point = new Point();
        point.x = i;
        point.y = i2;
        point.color = i3;
        point.fRe = 40;
        if (point.color == 0) {
            point.fRe = CRes.random(28, 43);
            point.maxframe = 1;
            point.isSmall = true;
            point.fSmall = CRes.random((point.fRe / 2) - 5, (point.fRe / 2) + 6);
        } else if (point.color == 1) {
            return;
        }
        if (i4 == 1) {
            point.dis = 0;
        } else if (i4 == 0) {
            point.dis = 3;
        } else if (i4 == 2) {
            point.dis = 6;
        } else if (i4 == 3) {
            point.dis = 5;
        }
        if (point.color == 0) {
            vecStep.addElement(point);
        } else {
            vecEffInMap.addElement(point);
        }
    }

    public static void checkAddEff(int i, int i2, int i3, int i4, short s, byte b, short s2, byte b2, boolean z) {
        if (z) {
            for (int i5 = 0; i5 < EffectManager.lowEffects.size(); i5++) {
                MainEffect mainEffect = (MainEffect) EffectManager.lowEffects.elementAt(i5);
                if (mainEffect != null && !mainEffect.isRemove && mainEffect.typeEffect == i && mainEffect.objBeKillMain != null && mainEffect.objBeKillMain.ID == s) {
                    mainEffect.setTimeRemove((short) i4);
                    mainEffect.objBeKillMain.vx = 0;
                    mainEffect.objBeKillMain.vy = 0;
                    mainEffect.objBeKillMain.toX = i2;
                    mainEffect.objBeKillMain.toY = i3;
                    if (i == 101) {
                        mainEffect.objBeKillMain.isSleep = true;
                        return;
                    }
                    if (i == 102) {
                        mainEffect.objBeKillMain.isStun = true;
                        return;
                    }
                    if (i == 107) {
                        mainEffect.objBeKillMain.isno = true;
                        return;
                    }
                    if (i == 103) {
                        mainEffect.objBeKillMain.isnoBoss84 = true;
                        return;
                    } else if (i == 100) {
                        mainEffect.objBeKillMain.isDongBang = true;
                        return;
                    } else {
                        mainEffect.objBeKillMain.isBinded = true;
                        return;
                    }
                }
            }
            addEffectNew(i, i2, i3, i4, s, b, s2, (byte) 1, i != 100);
            return;
        }
        for (int i6 = 0; i6 < EffectManager.hiEffects.size(); i6++) {
            MainEffect mainEffect2 = (MainEffect) EffectManager.hiEffects.elementAt(i6);
            if (mainEffect2 != null && !mainEffect2.isRemove && mainEffect2.typeEffect == i && mainEffect2.objBeKillMain != null && mainEffect2.objBeKillMain.ID == s) {
                mainEffect2.setTimeRemove((short) i4);
                mainEffect2.objBeKillMain.vx = 0;
                mainEffect2.objBeKillMain.vy = 0;
                mainEffect2.objBeKillMain.toX = i2;
                mainEffect2.objBeKillMain.toY = i3;
                if (i == 101) {
                    mainEffect2.objBeKillMain.isSleep = true;
                    return;
                }
                if (i == 102) {
                    mainEffect2.objBeKillMain.isStun = true;
                    return;
                }
                if (i == 107) {
                    mainEffect2.objBeKillMain.isno = true;
                    return;
                } else if (i == 103) {
                    mainEffect2.objBeKillMain.isnoBoss84 = true;
                    return;
                } else {
                    mainEffect2.objBeKillMain.isBinded = true;
                    return;
                }
            }
        }
        addEffectNew(i, i2, i3, i4, s, b, s2, (byte) 1, false);
    }

    public static MainObject findOwner(MainObject mainObject) {
        for (int i = 0; i < Vecplayers.size(); i++) {
            MainObject mainObject2 = (MainObject) Vecplayers.elementAt(i);
            if (mainObject2 != null && mainObject2.findOwner(mainObject)) {
                return mainObject2;
            }
        }
        return null;
    }

    public static MainObject findChar(short s) {
        for (int i = 0; i < Vecplayers.size(); i++) {
            MainObject mainObject = (MainObject) Vecplayers.elementAt(i);
            if (mainObject != null && mainObject.typeObject == 0 && mainObject.ID == s) {
                return mainObject;
            }
        }
        return null;
    }

    public static MainObject findMonster(short s) {
        for (int i = 0; i < Vecplayers.size(); i++) {
            MainObject mainObject = (MainObject) Vecplayers.elementAt(i);
            if (mainObject != null && mainObject.typeObject == 1 && mainObject.ID == s) {
                return mainObject;
            }
        }
        return null;
    }

    public static MainObject findObjByteCat(short s, byte b) {
        for (int i = 0; i < Vecplayers.size(); i++) {
            MainObject mainObject = (MainObject) Vecplayers.elementAt(i);
            if (mainObject != null && mainObject.typeObject == b && mainObject.ID == s) {
                return mainObject;
            }
        }
        return null;
    }

    public static MainObject findObj(short s) {
        for (int i = 0; i < Vecplayers.size(); i++) {
            MainObject mainObject = (MainObject) Vecplayers.elementAt(i);
            if (mainObject != null && mainObject.ID == s) {
                return mainObject;
            }
        }
        return null;
    }

    public static String converSecon2minutes(int i) {
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        if (i3 <= 0) {
            return new StringBuffer().append(i2).append("").toString();
        }
        return new StringBuffer().append(i3).append(":").append(i2).toString();
    }

    public static void startNewMagicBeam(int i, MainObject mainObject, MainObject mainObject2, int i2, int i3, int i4, short s, int i5, int i6) {
        MagicBeam magicBeam = new MagicBeam();
        magicBeam.set(i, i2, i3, i4, s, mainObject, mainObject2);
        magicBeam.setEff(i5, i6);
        arrowsUp.addElement(magicBeam);
    }

    @Override // GameScreen.MainScreen
    public boolean isGameScr() {
        return true;
    }
}
