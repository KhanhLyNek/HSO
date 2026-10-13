package netcommand.global;

import CLib.Session_ME;
import CLib.mFont;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.AnimateEffect;
import GameEffect.DataEffAuto;
import GameEffect.EffectAuto;
import GameEffect.EffectMonster;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.CatalogyMonster;
import GameObjects.CharPartInfo;
import GameObjects.DataEffect;
import GameObjects.DataSkillEff;
import GameObjects.EffectData;
import GameObjects.GameData;
import GameObjects.ImageIcon;
import GameObjects.Item;
import GameObjects.Item_Drop;
import GameObjects.MainBuff;
import GameObjects.MainClan;
import GameObjects.MainInfoItem;
import GameObjects.MainItem;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.MainParty;
import GameObjects.MainQuest;
import GameObjects.MainRMS;
import GameObjects.MainThanhTichClan;
import GameObjects.Mount;
import GameObjects.MountTemplate;
import GameObjects.NPC;
import GameObjects.NPCMini;
import GameObjects.NPCserver;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameObjects.Other_Players;
import GameObjects.Pet;
import GameObjects.PetItem;
import GameObjects.Player;
import GameObjects.TimecountDown;
import GameObjects.idSaveImage;
import GameScreen.Buy_Sell_Screen;
import GameScreen.Clan_Screen;
import GameScreen.CreateChar;
import GameScreen.EventScreen;
import GameScreen.GameScreen;
import GameScreen.Info_Other_Player;
import GameScreen.List_Server;
import GameScreen.LoadMapScreen;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.SelectCharScreen;
import GameScreen.ShipScr;
import GameScreen.TabScreenNew;
import GameScreen.WorldMapScreen;
import InterfaceComponents.DataRebuildItem;
import InterfaceComponents.IAction;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgChat;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabLottery;
import InterfaceComponents.TabMySeftNew;
import InterfaceComponents.TabQuest;
import InterfaceComponents.TabRebuildItem;
import InterfaceComponents.TabShopNew;
import InterfaceComponents.TabSkillsNew;
import InterfaceComponents.iCommand;
import InterfaceComponents.infoShow;
import Main.GameCanvas;
import Main.ChildLocationDiagnostics;
import Main.GameMidlet;
import Main.ImageEffect;
import Main.ImageEffectAuto;
import Map.ItemMap;
import Map.MainItemMap;
import Map.MapBackGround;
import Model.AvMain;
import Model.CRes;
import Model.CharCountDown;
import Model.EffectManager;
import Model.InfocontenNew;
import Model.MainImage;
import Model.MainImageDataPartChar;
import Model.MainTemplateItem;
import Model.Menu2;
import Model.Point;
import Model.T;
import Skill.HotKey;
import Skill.LvSkill;
import Skill.MainListSkill;
import Skill.Monster_Skill;
import Skill.Skill;
import Thread_More.LoadMap;
import Thread_More.MiniMap;
import Thread_More.SaveImageRMS;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.Message;
import tools.training.TrainingController;
import tools.trading.TradeController;

/* loaded from: KnightOnline_v403.jar:netcommand/global/ReadMessenge.class */
public class ReadMessenge extends AvMain {
    short idDialog;
    byte typeDialog;
    public static final byte UPDATE_ALL = 0;
    public static final byte ADD_REPLACE = 1;
    public static final byte DELETE = 2;
    public static final byte UPDATE_SIZECHEST = 3;
    public static byte SHOP_ITEM_EVENT = 100;
    public static byte SHOP_POTION = 0;
    public static byte SHOP_ITEM = 1;
    public static byte SHOP_HAIR = 2;
    public static byte CHEST = 3;
    public static byte SHOP_MATERIAL = 4;
    public static byte REBUILD = 5;
    public static byte SHOP_ICONCLAN_FREE = 6;
    public static byte SHOP_ICONCLAN_VIP = 7;
    public static byte SHOP_POTION_CLAN = 8;
    public static byte REPLACE = 9;
    public static byte WING = 10;
    public static byte PET_KEEPER = 11;
    public static byte TAB_HOP_NGUYEN_LIEU = 12;
    public static byte SHOP_VANTIEU = 13;
    public static byte SHOP_KHAM_NGOC = 14;
    public static byte SHOP_GHEP_NGOC = 15;
    public static byte SHOP_DUC_LO = 16;
    public static byte SHOP_OTHER_PLAYER = 17;
    public static byte SHOP_ANY_NGUYEN_LIEU = 18;
    public static byte SHOP_HOP_AN = 19;
    public static byte SHOP_NANG_CAP_MEDAL = 20;
    String nameParty = "";
    String nameBuySell = "";

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                GlobalService.gI().Buy_Sell((byte) 1, this.nameBuySell, (byte) 0, (short) 0, 0);
                GameCanvas.end_Dialog();
                return;
            case 1:
                GlobalService.gI().Party((byte) 2, this.nameParty);
                GameCanvas.end_Dialog();
                return;
            case 2:
                GameCanvas.end_Dialog();
                if (i2 >= 0) {
                    GlobalService.gI().dialog_Server(this.idDialog, this.typeDialog, (byte) i2);
                    return;
                }
                return;
            case 3:
                MainObject.StepHelpServer = (byte) (MainObject.StepHelpServer + 1);
                if (MainObject.StepHelpServer >= MainObject.strHelpNPC.length - 1) {
                    GameCanvas.menu2.startAt_NPC(null, MainObject.strHelpNPC[MainObject.strHelpNPC.length - 1], i2, (byte) 2, false, 2);
                    return;
                }
                mVector mvector = new mVector("ReadMessenge menu");
                mvector.addElement(new iCommand(T.next, 3, i2, this));
                GameCanvas.menu2.startAt_NPC(mvector, MainObject.strHelpNPC[MainObject.StepHelpServer], i2, (byte) 2, false, 2);
                return;
            case 4:
                GameCanvas.menu2.doCloseMenu();
                GlobalService.gI().Replace_Item((byte) 2, (short) 0);
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                return;
            case 20:
                if (mSystem.isIpAppstore()) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[i2]);
                    return;
                }
                if (TemMidlet.currentIAPStore == 2) {
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
                return;
            default:
                return;
        }
    }

    public void khamNgoc(Message message) {
        try {
            GameCanvas.end_Dialog();
            TabRebuildItem.isBeginEff = (byte) 1;
            TabRebuildItem.isNextRebuild = message.reader().readByte();
            TabRebuildItem.contentShow = message.reader().readUTF();
            TabRebuildItem.vecGem.removeAllElements();
            short readShort = message.reader().readShort();
            byte readByte = message.reader().readByte();
            if (readShort != -1 && readByte == 7) {
                MainItem material = Item.getMaterial(readShort);
                if (material != null) {
                    TabRebuildItem.itemRe = material;
                } else {
                    TabRebuildItem.itemRe = new MainItem();
                    TabRebuildItem.itemRe.Id = readShort;
                    TabRebuildItem.itemRe.ItemCatagory = 7;
                }
            }
        } catch (Exception e) {
        }
    }

    public void mainCharInfo(Message message) {
        short s;
        short s2;
        short s3;
        short s4;
        short s5;
        short s6;
        try {
            short readShort = message.reader().readShort();
            String lowerCase = message.reader().readUTF().toLowerCase();
            int readInt = message.reader().readInt();
            int readInt2 = message.reader().readInt();
            int readInt3 = message.reader().readInt();
            int readInt4 = message.reader().readInt();
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            byte readByte4 = message.reader().readByte();
            if (GameScreen.player == null) {
                GameScreen.player = new Player(readShort, (byte) 0, lowerCase.toLowerCase(), 50, 50);
            }
            GameScreen.player.setInfo(readByte, readByte3, readByte4);
            int readByte5 = message.reader().readByte();
            MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte5];
            int i = 0;
            for (int i2 = 0; i2 < GameScreen.player.mKhangChar.length; i2++) {
                GameScreen.player.mKhangChar[i2] = "0";
            }
            for (int i3 = 0; i3 < readByte5; i3++) {
                if (GameMidlet.version.equals("1.1.0")) {
                    mainInfoItemArr[i3] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readUnsignedShort());
                } else {
                    mainInfoItemArr[i3] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                }
                if (mainInfoItemArr[i3].id >= 16 && mainInfoItemArr[i3].id <= 20) {
                    GameScreen.player.mKhangChar[mainInfoItemArr[i3].id - 16] = MainItem.getNopercent(mainInfoItemArr[i3].value);
                }
                if (mainInfoItemArr[i3].id >= 23 && mainInfoItemArr[i3].id <= 26) {
                    i++;
                }
            }
            if (i == 0) {
                GameScreen.player.mInfoChar = mainInfoItemArr;
            } else {
                GameScreen.player.mInfoChar = new MainInfoItem[readByte5 - i];
                int i4 = 0;
                for (int i5 = 0; i5 < mainInfoItemArr.length; i5++) {
                    if (mainInfoItemArr[i5].id < 23 || mainInfoItemArr[i5].id > 26) {
                        GameScreen.player.mInfoChar[i4] = new MainInfoItem(mainInfoItemArr[i5].id, mainInfoItemArr[i5].value);
                        i4++;
                    }
                }
            }
            GameScreen.player.Lv = message.reader().readShort();
            GameScreen.player.phantramLv = message.reader().readShort();
            Player.diemTiemNang = message.reader().readShort();
            Player.diemKyNang = message.reader().readShort();
            for (int i6 = 0; i6 < Player.mTiemnang[0].length; i6++) {
                Player.mTiemnang[0][i6] = message.reader().readShort();
            }
            for (int i7 = 0; i7 < Player.mTiemnang[0].length; i7++) {
                Player.mTiemnang[1][i7] = message.reader().readShort();
            }
            Player.mCurentLvSkill = new int[Player.MaxSkill];
            for (int i8 = 0; i8 < Player.MaxSkill; i8++) {
                Player.mCurentLvSkill[i8] = message.reader().readByte();
            }
            for (int i9 = 0; i9 < Player.MaxSkill; i9++) {
                Player.mPlusLvSkill[i9] = message.reader().readByte();
            }
            for (int i10 = 0; i10 < Player.MaxSkill; i10++) {
                int i11 = -1;
                boolean z = true;
                if (Player.mCurentLvSkill[i10] > 0) {
                    if (i10 < 9) {
                        int i12 = 0;
                        while (true) {
                            if (i12 >= 3) {
                                break;
                            }
                            HotKey hotKey = Player.mhotkey[Player.levelTab][i12];
                            if (hotKey != null) {
                                if (i11 == -1) {
                                    if (hotKey.type == HotKey.NULL) {
                                        i11 = i12;
                                        z = false;
                                    }
                                } else if (hotKey.type == HotKey.SKILL && hotKey.id == i10) {
                                    i11 = -1;
                                    break;
                                }
                            }
                            i12++;
                        }
                    }
                } else {
                    z = false;
                }
                if (i11 != -1) {
                    Player.mhotkey[Player.levelTab][i11].setHotKey(i10, HotKey.SKILL, (byte) 0);
                }
                if (z) {
                    break;
                }
            }
            GameScreen.player.typePk = message.reader().readByte();
            GameScreen.player.pointPk = message.reader().readShort();
            Player.maxInven = message.reader().readByte();
            TabShopNew.maxTab = (byte) (Player.maxInven / 42);
            short readShort2 = message.reader().readShort();
            if (readShort2 >= 0) {
                GameScreen.player.myClan = new MainClan(message.reader().readInt(), readShort2, message.reader().readUTF(), message.reader().readByte());
            }
            GameScreen.nameSpecialRegion = message.reader().readUTF();
            GameScreen.timeSpRegion = message.reader().readLong();
            ChildLocationDiagnostics.onRegionNotice(GameScreen.nameSpecialRegion, GameScreen.timeSpRegion);
            Skill.resetContent();
            GameScreen.player.typeObject = (byte) 0;
            GameScreen.player.name = lowerCase.toLowerCase();
            GameScreen.player.ID = readShort;
            GameScreen.player.hp = readInt;
            if (GameScreen.player.hp > 0 && GameScreen.player.Action == 4) {
                GameScreen.player.resetAction();
                GameScreen.player.weapon_frame = 0;
                GameScreen.player.Action = 0;
                GameScreen.player.vecBuff.removeAllElements();
                GlobalService.gI().player_move((short) GameScreen.player.x, (short) GameScreen.player.y);
            }
            GameScreen.player.maxHp = readInt2;
            GameScreen.player.hpEffect = readInt2 / 10;
            GameScreen.player.mp = readInt3;
            GameScreen.player.maxMp = readInt4;
            GameScreen.player.clazz = readByte2;
            GameScreen.player.weapon_frame = 0;
            if (!Player.isNewPlayer) {
                EventScreen.vecListEvent.removeAllElements();
                EventScreen.vecEventShow.removeAllElements();
                if (!GameScreen.Vecplayers.equals(GameScreen.player)) {
                    GameScreen.addPlayer(GameScreen.player);
                }
                GameScreen.player.init();
                Player.isNewPlayer = true;
                MsgChat.vecChatTab.removeAllElements();
                GameCanvas.msgchat.addNewChat(T.tinden, "", T.thongbaotuserver, (byte) 1, false);
                Player.vecPlayerPk = new short[0];
                MainRMS.RequietRMS();
                GlobalService.gI().send_cmd_server((byte) 59);
                PaintInfoGameScreen.numMess = 0;
                GameScreen.VecInfoServer.removeAllElements();
                GameScreen.VecInfoChar.removeAllElements();
                GameScreen.infoGame.strInfoServer = null;
                GameScreen.infoGame.strInfoCharCline = null;
                GameScreen.infoGame.strInfoCharServer = null;
                Player.typeX2 = (byte) 0;
                if (GameScreen.player.Lv == 1) {
                    Player.isAutoFire = (byte) -1;
                }
                GlobalService.gI().Save_RMS_Server((byte) 1, (byte) 4, null);
                GlobalService.gI().Save_RMS_Server((byte) 1, (byte) 3, null);
                TemMidlet.submitPurchase();
            }
            MainTabNew.timePaintInfo = 0;
            int readByte6 = message.reader().readByte();
            short[] sArr = new short[readByte6];
            for (int i13 = 0; i13 < readByte6; i13++) {
                sArr[i13] = -1;
                sArr[i13] = message.reader().readShort();
            }
            GameScreen.player.idPartFashion = sArr;
            GameCanvas.naptien = message.reader().readByte();
            try {
                s = message.reader().readShort();
            } catch (Exception e) {
                s = -1;
                e.printStackTrace();
            }
            GameScreen.player.idMatna = s;
            try {
                if (message.reader().readByte() == 1) {
                    GameScreen.player.paintMatnaTruocNon = true;
                } else {
                    GameScreen.player.paintMatnaTruocNon = false;
                }
            } catch (Exception e2) {
            }
            try {
                s2 = message.reader().readShort();
            } catch (Exception e3) {
                s2 = -1;
            }
            GameScreen.player.idPhiPhong = s2;
            try {
                s3 = message.reader().readShort();
            } catch (Exception e4) {
                s3 = -1;
            }
            GameScreen.player.idWeaPon = s3;
            try {
                s4 = message.reader().readShort();
            } catch (Exception e5) {
                s4 = -1;
            }
            GameScreen.player.idHorse = s4;
            try {
                s5 = message.reader().readShort();
            } catch (Exception e6) {
                s5 = -1;
            }
            GameScreen.player.idHair = s5;
            short s7 = -1;
            try {
                s7 = message.reader().readShort();
            } catch (Exception e7) {
            }
            GameScreen.player.idWing = s7;
            try {
                s6 = message.reader().readShort();
            } catch (Exception e8) {
                s6 = -1;
            }
            GameScreen.player.idName = s6;
            short s8 = -1;
            try {
                s8 = message.reader().readShort();
            } catch (Exception e9) {
            }
            GameScreen.player.idBody = s8;
            short s9 = -1;
            try {
                s9 = message.reader().readShort();
            } catch (Exception e10) {
            }
            GameScreen.player.idLeg = s9;
            short s10 = -1;
            try {
                s10 = message.reader().readShort();
            } catch (Exception e11) {
            }
            GameScreen.player.idBienhinh = s10;
        } catch (IOException e12) {
            e12.printStackTrace();
        }
    }

    public void objectMove(Message message) {
        while (message.reader().available() > 0) {
            try {
                byte readByte = message.reader().readByte();
                short readShort = message.reader().readShort();
                short readShort2 = message.reader().readShort();
                short readShort3 = message.reader().readShort();
                short readShort4 = message.reader().readShort();
                byte readByte2 = message.reader().readByte();
                if (LoadMapScreen.isNextMap) {
                    if (readByte2 != Byte.MAX_VALUE) {
                        MainObject mainObject = MainObject.get_Object(readShort2, readByte);
                        if (mainObject == null) {
                            if (readByte == 1) {
                                MainMonster.createMonster(readShort2, readShort3, readShort4, readShort);
                                GlobalService.gI().monster_info(readShort2);
                            } else if (readByte == 0) {
                                Other_Players other_Players = new Other_Players(readShort2, readByte, "", readShort3, readShort4);
                                other_Players.Direction = CRes.random(4);
                                other_Players.setWearingListChar(null);
                                GameScreen.addPlayer(other_Players);
                                GlobalService.gI().char_info(readShort2);
                                other_Players.timeRePlayerInfo = GameCanvas.timeNow;
                            } else if (readByte == 2) {
                                Other_Players other_Players2 = new Other_Players(readShort2, readByte, "", readShort3, readShort4);
                                other_Players2.isObject = true;
                                GameScreen.addPlayer(other_Players2);
                                GlobalService.gI().new_npc_info(readShort2);
                            }
                        } else {
                            if (mainObject.isRemove) {
                                return;
                            }
                            if (mainObject == GameScreen.player) {
                                if (CRes.abs(GameScreen.player.x - readShort3) > 24 || CRes.abs(GameScreen.player.y - readShort4) > 24) {
                                    Player.isLockKey = true;
                                    Player.isSendMove = false;
                                    GameScreen.player.toX = GameScreen.player.x;
                                    GameScreen.player.toY = GameScreen.player.y;
                                    GameScreen.player.xStopMove = readShort3;
                                    GameScreen.player.yStopMove = readShort4;
                                    try {
                                        GameScreen.player.posTransRoad = GameCanvas.game.updateFindRoad(readShort3 / LoadMap.wTile, readShort4 / LoadMap.wTile, GameScreen.player.x / LoadMap.wTile, GameScreen.player.y / LoadMap.wTile, 20);
                                    } catch (Exception e) {
                                        mainObject.x = readShort3;
                                        mainObject.y = readShort4;
                                        GameScreen.player.posTransRoad = null;
                                        Player.isLockKey = false;
                                        Player.isSendMove = true;
                                    }
                                }
                                if (GameScreen.player.posTransRoad == null || GameScreen.player.posTransRoad.length >= 20) {
                                    mainObject.x = readShort3;
                                    mainObject.y = readShort4;
                                    GameScreen.player.posTransRoad = null;
                                    Player.isLockKey = false;
                                    Player.isSendMove = true;
                                }
                            } else if (mainObject.typeObject == 1) {
                                mainObject.toX = readShort3;
                                mainObject.toY = readShort4;
                                mainObject.isMove = true;
                                if (readByte2 == 126) {
                                    if (mainObject.StepMovebocap != 1) {
                                        GameScreen.addEffectEndKill(54, mainObject.x, mainObject.y - 20);
                                    }
                                    mainObject.x = mainObject.toX;
                                    mainObject.y = mainObject.toY;
                                    mainObject.isMove = false;
                                    mainObject.StepMovebocap = (byte) -1;
                                } else if (readByte2 == 125) {
                                    mainObject.StepMovebocap = (byte) 0;
                                }
                            } else if (mainObject.Action != 2 && mainObject.Action != 4 && mainObject.KillFire == -1) {
                                mainObject.toX = readShort3;
                                mainObject.toY = readShort4;
                                mainObject.countCharStand = (short) 0;
                                if (Player.party != null) {
                                    Player.party.setPos(mainObject.name, readShort3, readShort4, mainObject.hp, mainObject.maxHp);
                                }
                            }
                        }
                    } else if (readByte2 == Byte.MAX_VALUE) {
                        MainObject mainObject2 = MainObject.get_Object(readShort2, readByte);
                        if (mainObject2 != null) {
                            if (mainObject2.isRemove) {
                                return;
                            }
                            mainObject2.isMoveOut = true;
                            mainObject2.xMoveOut = readShort3;
                            mainObject2.yMoveOut = readShort4;
                        } else if (readByte == 0) {
                            Other_Players other_Players3 = new Other_Players(readShort2, readByte, "", readShort3, readShort4);
                            other_Players3.Direction = CRes.random(4);
                            other_Players3.setWearingListChar(null);
                            GameScreen.addPlayer(other_Players3);
                            GlobalService.gI().char_info(readShort2);
                            other_Players3.timeRePlayerInfo = GameCanvas.timeNow;
                            mainObject2.isMoveOut = true;
                            mainObject2.xMoveOut = readShort3;
                            mainObject2.yMoveOut = readShort4;
                        }
                    }
                }
            } catch (Exception e2) {
                return;
            }
        }
    }

    public void newNPCInfo(Message message) {
        try {
            short readShort = message.reader().readShort();
            String readUTF = message.reader().readUTF();
            int readInt = message.reader().readInt();
            int readInt2 = message.reader().readInt();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            short readShort4 = message.reader().readShort();
            message.reader().readByte();
            message.reader().readByte();
            message.reader().readUTF();
            short readShort5 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 2);
            if (mainObject == null || mainObject.isRemove) {
                mSystem.outz("New npc is null");
                return;
            }
            mainObject.name = readUTF;
            mainObject.x = readShort3;
            mainObject.y = readShort4;
            mainObject.toX = readShort3;
            mainObject.toY = readShort4;
            mainObject.Lv = readShort2;
            mainObject.maxHp = readInt;
            mainObject.hp = readInt2;
            mainObject.hpEffect = readInt / 10;
            mainObject.imageId = readShort5;
            mainObject.typeObject = (byte) 2;
            if (readShort5 == -1) {
                mainObject.setInfo(message.reader().readByte(), message.reader().readByte(), message.reader().readByte());
                byte[] bArr = new byte[12];
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = -1;
                }
                int readByte = message.reader().readByte();
                for (int i2 = 0; i2 < readByte; i2++) {
                    bArr[message.reader().readByte()] = message.reader().readByte();
                }
                mainObject.setWearingListChar(bArr);
                short readShort6 = message.reader().readShort();
                if (readShort6 != -1) {
                    mainObject.myClan = new MainClan(message.reader().readInt(), readShort6, message.reader().readUTF(), message.reader().readByte());
                } else {
                    mainObject.myClan = null;
                }
            } else {
                byte readByte2 = message.reader().readByte();
                short readShort7 = message.reader().readShort();
                mainObject.numFrame = readByte2;
                mainObject.IdBigAvatar = readShort7;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v73, types: [short[], short[][]] */
    public void charInfo(Message message) {
        short s;
        short s2;
        short s3;
        short s4;
        short s5;
        short s6;
        short s7;
        try {
            short readShort = message.reader().readShort();
            String lowerCase = message.reader().readUTF().toLowerCase();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            byte readByte4 = message.reader().readByte();
            byte readByte5 = message.reader().readByte();
            short readShort4 = message.reader().readShort();
            int readInt = message.reader().readInt();
            int readInt2 = message.reader().readInt();
            byte readByte6 = message.reader().readByte();
            short readShort5 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
            if (mainObject == 0 || mainObject.isRemove) {
                return;
            }
            if (readByte2 == -126 && lowerCase.endsWith(",")) {
                mainObject.iscuop = true;
                lowerCase = lowerCase.substring(0, lowerCase.length() - 1);
            }
            mainObject.name = lowerCase;
            mainObject.toX = readShort2;
            mainObject.toY = readShort3;
            mainObject.clazz = readByte;
            mainObject.weapon_frame = 0;
            mainObject.Lv = readShort4;
            mainObject.maxHp = readInt2;
            mainObject.hp = readInt;
            mainObject.typePk = readByte6;
            mainObject.hpEffect = readInt2 / 10;
            mainObject.pointPk = readShort5;
            mainObject.isBot = readByte2;
            if (mainObject.isNPC()) {
                mainObject.nameGiaotiep = mainObject.name;
                mainObject.infoObject = T.tuchoi;
                MiniMap.addNPCMini(new NPCMini(mainObject.isBot, readShort2, readShort3));
            }
            if (readByte2 == -126) {
                mainObject.typeSpec = (byte) 1;
            }
            mainObject.setInfo(readByte3, readByte4, readByte5);
            if (Player.party != null) {
                Player.party.setObjParty(mainObject);
            }
            if (mainObject.hp <= 0) {
                mainObject.hp = 0;
                mainObject.mp = 0;
                mainObject.resetAction();
                mainObject.Action = 4;
                mainObject.timedie = GameCanvas.timeNow;
            }
            byte[] bArr = new byte[12];
            ?? r0 = new short[12];
            for (int i = 0; i < r0.length; i++) {
                r0[i] = new short[3];
            }
            for (int i2 = 0; i2 < r0.length; i2++) {
                for (int i3 = 0; i3 < r0[i2].length; i3++) {
                    r0[i2][i3] = -1;
                }
            }
            for (int i4 = 0; i4 < bArr.length; i4++) {
                bArr[i4] = -1;
            }
            int readByte7 = message.reader().readByte();
            mainObject.removeAllEffCharWearing();
            for (int i5 = 0; i5 < readByte7; i5++) {
                byte readByte8 = message.reader().readByte();
                bArr[readByte8] = message.reader().readByte();
                int readByte9 = message.reader().readByte();
                for (int i6 = 0; i6 < readByte9; i6++) {
                    r0[readByte8][i6] = message.reader().readShort();
                }
                short readShort6 = message.reader().readShort();
                if (readShort6 != -1) {
                    if (readByte8 == 0) {
                        mainObject.addEffectCharWearing(1, readShort6);
                    } else if (readByte8 == 2) {
                        mainObject.addEffectCharWearing(0, readShort6);
                    } else if (readByte8 == 1) {
                        mainObject.addEffectCharWearing(2, readShort6);
                    }
                }
            }
            mainObject.setWearingListChar(bArr);
            mainObject.setArrayGemKham(r0);
            short readShort7 = message.reader().readShort();
            if (readShort7 >= 0) {
                mainObject.myClan = new MainClan(message.reader().readInt(), readShort7, message.reader().readUTF(), message.reader().readByte());
            } else {
                mainObject.myClan = null;
            }
            byte readByte10 = message.reader().readByte();
            if (readByte10 >= 0) {
                byte readByte11 = message.reader().readByte();
                byte readByte12 = message.reader().readByte();
                MainObject findOwner = GameScreen.findOwner(mainObject);
                if (findOwner != null) {
                    GameScreen.Vecplayers.removeElement(findOwner);
                }
                Pet createPet = Pet.createPet(mainObject, readByte10, readByte12, readByte11);
                if (createPet != null) {
                    GameScreen.addPlayer(createPet);
                }
            }
            int readByte13 = message.reader().readByte();
            short[] sArr = new short[readByte13];
            for (int i7 = 0; i7 < readByte13; i7++) {
                sArr[i7] = -1;
                sArr[i7] = message.reader().readShort();
            }
            mainObject.idPartFashion = sArr;
            try {
                mainObject.idImageHenshin = message.reader().readShort();
            } catch (Exception e) {
            }
            byte readByte14 = message.reader().readByte();
            mainObject.isFootSnow = message.reader().readBoolean();
            mainObject.typeMount = readByte14;
            byte readByte15 = message.reader().readByte();
            byte readByte16 = message.reader().readByte();
            mainObject.typefocus = readByte15;
            mainObject.typefire = readByte16;
            try {
                s = message.reader().readShort();
            } catch (Exception e2) {
                s = -1;
            }
            mainObject.idMatna = s;
            try {
                if (message.reader().readByte() == 1) {
                    mainObject.paintMatnaTruocNon = true;
                } else {
                    mainObject.paintMatnaTruocNon = false;
                }
            } catch (Exception e3) {
            }
            try {
                s2 = message.reader().readShort();
            } catch (Exception e4) {
                s2 = -1;
            }
            mainObject.idPhiPhong = s2;
            try {
                s3 = message.reader().readShort();
            } catch (Exception e5) {
                s3 = -1;
            }
            mainObject.idWeaPon = s3;
            try {
                s4 = message.reader().readShort();
            } catch (Exception e6) {
                s4 = -1;
            }
            mainObject.idHorse = s4;
            try {
                s5 = message.reader().readShort();
            } catch (Exception e7) {
                s5 = -1;
            }
            mainObject.idHair = s5;
            try {
                s6 = message.reader().readShort();
            } catch (Exception e8) {
                s6 = -1;
            }
            mainObject.idWing = s6;
            try {
                s7 = message.reader().readShort();
            } catch (Exception e9) {
                s7 = -1;
            }
            mainObject.idName = s7;
            short s8 = -1;
            try {
                s8 = message.reader().readShort();
            } catch (Exception e10) {
            }
            mainObject.idBody = s8;
            short s9 = -1;
            try {
                s9 = message.reader().readShort();
            } catch (Exception e11) {
            }
            mainObject.idLeg = s9;
            short s10 = -1;
            try {
                s10 = message.reader().readShort();
            } catch (Exception e12) {
            }
            mainObject.idBienhinh = s10;
        } catch (Exception e13) {
            e13.printStackTrace();
        }
    }

    public void monsterInfo(Message message) {
        try {
            short readShort = message.reader().readShort();
            int readUnsignedByte = message.reader().readUnsignedByte();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            int readInt = message.reader().readInt();
            int readInt2 = message.reader().readInt();
            byte readByte = message.reader().readByte();
            int readInt3 = message.reader().readInt();
            MainMonster mainMonster = (MainMonster) MainObject.get_Object(readShort, (byte) 1);
            if (mainMonster == null || mainMonster.isRemove) {
                return;
            }
            mainMonster.Lv = (short) readUnsignedByte;
            mainMonster.toX = readShort2;
            mainMonster.toY = readShort3;
            mainMonster.hp = readInt;
            mainMonster.maxHp = readInt2;
            mainMonster.isInfo = true;
            mainMonster.setLvmonster(readUnsignedByte);
            mainMonster.timeReveice = readInt3;
            if (readInt3 == -2) {
                mainMonster.typeBoss = (byte) 1;
            }
            if (readInt3 == -3) {
                message.reader().readUTF();
                mainMonster.typeBoss = (byte) 2;
                MainObject.vecCapchar.removeAllElements();
                MainObject.strCapchar = "";
                GameScreen.ObjFocus = mainMonster;
                GameCanvas.addInfoChar(T.helpCapchar);
            }
            if (readInt3 == -4) {
                mainMonster.typeBoss = (byte) 3;
                mainMonster.xPhoBang = message.reader().readShort();
                mainMonster.yPhoBang = message.reader().readShort();
            }
            if (readInt3 == -5) {
                mainMonster.typeBoss = (byte) 4;
                mainMonster.xPhoBang = message.reader().readShort();
                mainMonster.yPhoBang = message.reader().readShort();
            }
            short readShort4 = message.reader().readShort();
            if (readShort4 >= 0) {
                mainMonster.myClan = new MainClan(message.reader().readInt(), readShort4, message.reader().readUTF(), message.reader().readByte());
            } else {
                mainMonster.myClan = null;
            }
            mainMonster.skillDefault = new Monster_Skill(readByte);
            if (mainMonster.hp <= 0) {
                mainMonster.hp = 0;
                mainMonster.mp = 0;
                if (mainMonster.Action != 4) {
                    MainObject.startDeadFly(mainMonster, GameScreen.player.ID, CRes.random(3));
                }
            }
            if (mainMonster.typeMonster == 7) {
                MiniMap.addMonMini(mainMonster.ID, (byte) 1);
                mainMonster.name = message.reader().readUTF();
            }
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            byte readByte4 = message.reader().readByte();
            mainMonster.isServerControl = readByte2 == 1;
            mainMonster.vMax = readByte3;
            mainMonster.Direction = readByte4;
            String readUTF = message.reader().readUTF();
            mainMonster.nameowner = readUTF;
            if (!readUTF.equals("") || mainMonster.isMiNuong()) {
                mainMonster.setspeedVantieu(readByte3);
            }
            long readLong = message.reader().readLong();
            mainMonster.colorName = message.reader().readByte();
            mainMonster.setTimelive(readLong);
            EffectMonster.addEffectMonster(mainMonster);
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("loi cho nay ne monsterinfo ").append(e.toString()).toString());
            e.printStackTrace();
        }
    }

    public void playerExit(Message message) {
        try {
            short readShort = message.reader().readShort();
            if (readShort == GameScreen.player.ID) {
                return;
            }
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
            if (mainObject != null && !mainObject.isRemove) {
                Pet pet = (Pet) MainObject.get_Object(mainObject.ID, (byte) 9);
                if (pet != null) {
                    GameScreen.addEffectEndKill(35, pet.x, pet.y - 20);
                    pet.isStop = true;
                }
                GameScreen.addEffectEndKill(35, mainObject.x, mainObject.y - 20);
                mainObject.isStop = true;
            }
            MainObject mainObject2 = MainObject.get_Object(readShort, (byte) 1);
            if (mainObject2 != null && !mainObject2.isRemove) {
                GameScreen.addEffectEndKill(35, mainObject2.x, mainObject2.y - 20);
                mainObject2.isStop = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeMap(Message message) {
        TradeController.beforeMapChange();
        boolean z = false;
        try {
            GameCanvas.game.wRec = 0;
            GameCanvas.game.isFullScreen = false;
            GameScreen.infoGame.idCharLoiDai1 = (short) -1;
            GameScreen.infoGame.idCharLoiDai2 = (short) -1;
            LoadMapScreen.isNextMap = false;
            if (Player.isAutoFire > -1) {
                Player.setCurAutoFire();
            }
            if (GameCanvas.currentScreen != GameCanvas.load) {
                GameCanvas.load.Show();
            }
            GameScreen.Remove_ChangeMap();
            GameScreen.RemoveLoadMap();
            GameCanvas.loadmap.idMap = message.reader().readShort();
            GameCanvas.worldmap.setPosPlayer(GameCanvas.loadmap.idMap);
            GameScreen.infoGame.setCountTimeHS(GameCanvas.loadmap.idMap, mSystem.currentTimeMillis(), 30);
            GameScreen.player.x = message.reader().readShort() * 24;
            GameScreen.player.y = message.reader().readShort() * 24;
            GameScreen.player.xStand = GameScreen.player.x;
            GameScreen.player.yStand = GameScreen.player.y;
            GameScreen.player.resetMove();
            GameScreen.player.posTransRoad = null;
            GameScreen.player.resetAction();
            if (GameScreen.pet != null) {
                GameScreen.pet.x = GameScreen.player.x;
                GameScreen.pet.y = GameScreen.player.y;
                GameScreen.pet.clearWayPoints();
            }
            GameCanvas.loadmap.idMapMini = message.reader().readShort();
            GameCanvas.loadmap.nameMap = message.reader().readUTF();
            int readShort = message.reader().readShort();
            byte[] bArr = null;
            if (readShort > 0) {
                bArr = new byte[readShort];
                message.reader().read(bArr);
            }
            GameCanvas.loadmap.loadmap(bArr);
            byte readByte = message.reader().readByte();
            if (readByte >= 0) {
                short readShort2 = message.reader().readShort();
                GameCanvas.mapBack = new MapBackGround();
                GameCanvas.mapBack.setBackGround(readByte, readShort2);
            } else {
                GameCanvas.mapBack = null;
            }
            int readShort3 = message.reader().readShort();
            GameCanvas.load.mItemMap = null;
            if (readShort3 > 0) {
                GameCanvas.load.mItemMap = new byte[readShort3];
                message.reader().read(GameCanvas.load.mItemMap);
                GameCanvas.loadmap.load_ItemMap(GameCanvas.load.mItemMap);
            }
            GameScreen.player.useShip = false;
            if (GameCanvas.loadmap.idMap == 19) {
                GameScreen.infoGame.eff = new EffectAuto(50, 1080, 96, 0, 0, 1, 0);
            } else if (GameCanvas.loadmap.idMap == 67) {
                GameScreen.infoGame.eff = new EffectAuto(50, 360, 672, 0, 0, 1, 0);
            }
            z = true;
            LoadMap.vecPointChange.removeAllElements();
            int readByte2 = message.reader().readByte();
            for (int i = 0; i < readByte2; i++) {
                Point point = new Point();
                point.x = message.reader().readShort();
                point.y = message.reader().readShort();
                point.name = message.reader().readUTF();
                if (point.x >= 100) {
                    if (point.x > (GameCanvas.loadmap.mapW * LoadMap.wTile) - 100) {
                        point.dis = 1;
                        point.f = 1;
                        point.x2 = point.x + 8;
                        point.y2 = point.y - 18;
                        point.vx = 1;
                    } else if (point.y < (GameCanvas.loadmap.mapW * LoadMap.wTile) / 2) {
                        point.y -= 10;
                        point.dis = 2;
                        point.f = 2;
                        point.x2 = point.x;
                        point.y2 = point.y + 10;
                        point.vy = -1;
                    } else {
                        point.dis = 3;
                        point.f = 2;
                        point.x2 = point.x;
                        point.y2 = point.y - 20;
                        point.vy = 1;
                    }
                } else {
                    point.dis = 0;
                    point.x2 = point.x - 8;
                    point.y2 = point.y - 18;
                    point.f = 0;
                    point.vx = -1;
                }
                LoadMap.vecPointChange.addElement(point);
            }
            try {
                GameCanvas.minimap.setSize();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte readByte3 = message.reader().readByte();
            LoadMap.Area = message.reader().readByte();
            TradeController.afterMapChange();
            ChildLocationDiagnostics.onMapChanged(GameCanvas.loadmap.idMap, GameCanvas.loadmap.nameMap, LoadMap.Area);
            GameCanvas.end_Dialog();
            GameCanvas.clearKeyHold();
            GameCanvas.load.isTele = false;
            if (readByte3 == 1) {
                GameCanvas.load.isTele = true;
            }
            LoadMap.typeMap = message.reader().readByte();
            if (LoadMap.typeMap == 3) {
                GameScreen.isInPetArea = true;
            } else {
                GameScreen.isInPetArea = false;
            }
            LoadMapScreen.isNextMap = true;
            GameCanvas.timenextLogin = 0L;
            GameCanvas.countLogin = 0L;
            try {
                GameScreen.isMapLang = message.reader().readBoolean();
                GameScreen.isShowHoiSinh = message.reader().readBoolean();
            } catch (Exception e2) {
                GameScreen.isShowHoiSinh = false;
                GameScreen.isMapLang = false;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            if (z) {
                LoadMapScreen.isNextMap = true;
            }
        }
    }

    public void fireMonster(Message message) {
        try {
            Fire_Object(message, (byte) 0, (byte) 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void charWearing(Message message) {
        MainObject mainObject = null;
        try {
            mainObject = MainObject.get_Object(message.reader().readShort(), (byte) 0);
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            int readByte = message.reader().readByte();
            byte[] bArr = new byte[readByte];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = -1;
            }
            if (mainObject == GameScreen.player) {
                Item.VecEquipPlayer.clear();
                if (GameScreen.pet != null) {
                    GameScreen.Vecplayers.removeElement(GameScreen.pet);
                }
                for (int i2 = 0; i2 < mainObject.slotGem.length; i2++) {
                    mainObject.slotGem[i2] = -1;
                }
            }
            boolean z = false;
            mainObject.removeAllEffCharWearing();
            for (int i3 = 0; i3 < readByte; i3++) {
                byte readByte2 = message.reader().readByte();
                if (readByte2 > -1) {
                    String readUTF = message.reader().readUTF();
                    byte readByte3 = message.reader().readByte();
                    byte readByte4 = message.reader().readByte();
                    short readShort = message.reader().readShort();
                    byte readByte5 = message.reader().readByte();
                    byte readByte6 = message.reader().readByte();
                    short readShort2 = message.reader().readShort();
                    byte readByte7 = message.reader().readByte();
                    bArr[i3] = readByte5;
                    int readByte8 = message.reader().readByte();
                    MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte8];
                    for (int i4 = 0; i4 < readByte8; i4++) {
                        int readUnsignedByte = message.reader().readUnsignedByte();
                        mainInfoItemArr[i4] = new MainInfoItem(readUnsignedByte, message.reader().readInt());
                        if (readByte2 == 8 && readUnsignedByte == 67) {
                            z = true;
                        }
                    }
                    MainItem MainItem_Item = MainItem.MainItem_Item(readByte2, readUTF, readShort, readByte6, readByte7, readByte3, 3, mainInfoItemArr, readByte4, false, (short) -1, 0L, readShort2, (byte) 0, (byte) 0, 0, (byte) 0, message.reader().readByte());
                    if (mainObject == GameScreen.player) {
                        Item.VecEquipPlayer.put(new StringBuffer().append("").append((int) readByte2).toString(), MainItem_Item);
                        if (MainItem_Item.moreContenGem.size() > 0) {
                            for (int i5 = 0; i5 < MainItem_Item.moreContenGem.size(); i5++) {
                                InfocontenNew infocontenNew = (InfocontenNew) MainItem_Item.moreContenGem.elementAt(i5);
                                if (infocontenNew.idimage >= 23 && infocontenNew.idimage <= 27) {
                                    mainObject.slotGem[i5] = 0;
                                } else if (infocontenNew.idimage >= 28 && infocontenNew.idimage <= 32) {
                                    mainObject.slotGem[i5] = 1;
                                } else if (infocontenNew.idimage == -1) {
                                    mainObject.slotGem[i5] = -1;
                                }
                            }
                        }
                    }
                    if (readByte4 == 1) {
                        for (MainInfoItem mainInfoItem : mainInfoItemArr) {
                            if (mainInfoItem.id == 71) {
                                mainObject.addEffectCharWearing(2, mainInfoItem.value);
                            }
                        }
                    }
                    if (readByte4 == 0) {
                        for (MainInfoItem mainInfoItem2 : mainInfoItemArr) {
                            if (mainInfoItem2.id == 71) {
                                mainObject.addEffectCharWearing(1, mainInfoItem2.value);
                            }
                        }
                    }
                    if (readByte4 == 2) {
                        for (MainInfoItem mainInfoItem3 : mainInfoItemArr) {
                            if (mainInfoItem3.id == 71) {
                                mainObject.addEffectCharWearing(0, mainInfoItem3.value);
                            }
                        }
                    }
                } else {
                    bArr[i3] = -1;
                }
            }
            mainObject.isFootSnow = z;
            byte readByte9 = message.reader().readByte();
            mSystem.outz(new StringBuffer().append("petId=").append((int) readByte9).toString());
            byte b = 0;
            byte b2 = 6;
            if (readByte9 > -1) {
                String readUTF2 = message.reader().readUTF();
                byte readByte10 = message.reader().readByte();
                short readShort3 = message.reader().readShort();
                short readShort4 = message.reader().readShort();
                byte readByte11 = message.reader().readByte();
                byte readByte12 = message.reader().readByte();
                b = readByte12;
                byte readByte13 = message.reader().readByte();
                b2 = readByte13;
                byte readByte14 = message.reader().readByte();
                int readInt = message.reader().readInt();
                short readShort5 = message.reader().readShort();
                short readShort6 = message.reader().readShort();
                short readShort7 = message.reader().readShort();
                short readShort8 = message.reader().readShort();
                short readShort9 = message.reader().readShort();
                short readShort10 = message.reader().readShort();
                short readShort11 = message.reader().readShort();
                int readByte15 = message.reader().readByte();
                MainInfoItem[] mainInfoItemArr2 = new MainInfoItem[readByte15];
                for (int i6 = 0; i6 < readByte15; i6++) {
                    mainInfoItemArr2[i6] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt(), message.reader().readInt());
                }
                PetItem petItem = new PetItem(readByte9, readUTF2, readByte11, readByte14, readByte10, 9, mainInfoItemArr2, 14, readShort3, readByte11, readByte13, readByte12);
                petItem.setInfoPet(readInt, readShort5, readShort7, readShort8, readShort9, readShort10, readShort6, readShort11, readShort4);
                if (message.reader().readByte() == 1) {
                    petItem.setTimeUser(message.reader().readInt(), Long.parseLong(message.reader().readUTF()));
                }
                if (mainObject == GameScreen.player) {
                    Item.VecEquipPlayer.put(new StringBuffer().append("").append((int) readByte9).toString(), petItem);
                }
                bArr[5] = readByte11;
                if (GameCanvas.subDialog != null && GameCanvas.subDialog.type == 14 && MsgDialog.isInven_Equip == 1) {
                    MsgDialog.pet = petItem;
                }
            }
            mainObject.setWearingEquip(bArr);
            if (mainObject.equals(GameScreen.player)) {
                Pet pet = null;
                if (GameScreen.player.pet != -1) {
                    pet = Pet.createPet(GameScreen.player, (short) GameScreen.player.pet, b2, b);
                }
                GameScreen.pet = pet;
                if (GameScreen.pet != null) {
                    GameScreen.addPlayer(GameScreen.pet);
                }
            }
            int readByte16 = message.reader().readByte();
            short[] sArr = new short[readByte16];
            for (int i7 = 0; i7 < readByte16; i7++) {
                sArr[i7] = -1;
                sArr[i7] = message.reader().readShort();
            }
            mainObject.idPartFashion = sArr;
        } catch (Exception e) {
            mSystem.println("loi char wearing");
            mSystem.outloi(">>>>>>>>>>>> wearing ");
            if (GameScreen.player != null && mainObject != GameScreen.player) {
                mainObject.isRemove = true;
            }
        }
    }

    public void dieMonster(Message message) {
        try {
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort2, (byte) 1);
            if (GameScreen.help.setStep_Next(1, 2) && readShort == GameScreen.player.ID) {
                GameScreen.help.Next++;
                GameScreen.help.setNext();
                Player.isAutoFire = (byte) -1;
            }
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            if (mainObject.hp != 0) {
                mainObject.hp = 0;
            }
            mainObject.coutEff = 100;
            if (mainObject.Action == 4 || mainObject.isDie) {
                if (mainObject.typeBoss != 0) {
                    mainObject.isRemove = true;
                    if (mainObject.typeBoss == 2) {
                        MainObject.imgCapchar = null;
                    }
                }
                mainObject.timedie = GameCanvas.timeNow;
            } else {
                mainObject.hp = 0;
                if (mainObject.typeBoss != 0) {
                    mainObject.isRemove = true;
                    if (mainObject.typeBoss == 2) {
                        MainObject.imgCapchar = null;
                    }
                } else {
                    MainObject.startDeadFly((MainMonster) mainObject, readShort, CRes.random(3));
                }
                if (readShort == GameScreen.player.ID && mainObject.timeReveice >= 0) {
                    MainQuest.updateQuestKillMonster(readShort2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void petAttack(Message message) {
        try {
            mSystem.outz("--- Pet Attack Message --- ");
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            byte readByte2 = message.reader().readByte();
            int readByte3 = message.reader().readByte();
            mVector mvector = new mVector("ReadMessenge vecObjectBeAttacked");
            for (int i = 0; i < readByte3; i++) {
                short readShort2 = message.reader().readShort();
                int readInt = message.reader().readInt();
                int readInt2 = message.reader().readInt();
                Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill(readShort2, readByte2, new Monster_Skill((byte) 14));
                object_Effect_Skill.setHP(readInt, readInt2);
                MainObject mainObject = MainObject.get_Object(readShort2, readByte2);
                if (mainObject != null && !mainObject.isRemove) {
                    mvector.addElement(object_Effect_Skill);
                }
            }
            Pet pet = (Pet) MainObject.get_Object(readShort, (byte) 9);
            MainObject mainObject2 = MainObject.get_Object(readShort, (byte) 0);
            int readInt3 = message.reader().readInt();
            int readInt4 = message.reader().readInt();
            if (mainObject2 != null) {
                mainObject2.hp = readInt3;
                mainObject2.mp = readInt4;
            }
            if (pet != null) {
                pet.vecObjskill = mvector;
                pet.setState((byte) 2);
                pet.initAttackState(readByte);
            }
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("loi ham pet attack ").append(e.toString()).toString());
        }
    }

    public void monsterFire(Message message) {
        try {
            byte readByte = message.reader().readByte();
            MainMonster mainMonster = (MainMonster) MainObject.get_Object(message.reader().readShort(), (byte) 1);
            if (mainMonster == null || mainMonster.isRemove) {
                return;
            }
            boolean isBossNew = mainMonster.isBossNew();
            if (mainMonster.Action == 4) {
                if (mainMonster.timeReveice < 0) {
                    return;
                } else {
                    mainMonster.Reveive();
                }
            }
            mainMonster.isMove = false;
            mainMonster.vx = 0;
            mainMonster.vy = 0;
            if (readByte == 1) {
                int readInt = message.reader().readInt();
                byte readByte2 = message.reader().readByte();
                int readByte3 = message.reader().readByte();
                mainMonster.hp = readInt;
                if (readByte3 <= 0) {
                    return;
                }
                if (mainMonster.vecObjskill != null && mainMonster.vecObjskill.size() > 0) {
                    Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) mainMonster.vecObjskill.elementAt(0);
                    mainMonster.IDAttack = object_Effect_Skill.ID;
                    if (mainMonster.skillDefault == null) {
                        mainMonster.skillDefault = new Monster_Skill((byte) 14);
                    }
                    if (!isBossNew) {
                        object_Effect_Skill.skillMonster = mainMonster.skillDefault;
                    }
                    mainMonster.beginFire();
                    mainMonster.beginSkill();
                }
                mVector mvector = new mVector("ReadMessenge vecBe");
                boolean z = false;
                mVector mvector2 = new mVector("ReadMessenge allPlayerDie");
                for (int i = 0; i < readByte3; i++) {
                    short readShort = message.reader().readShort();
                    int readInt2 = message.reader().readInt();
                    int readInt3 = message.reader().readInt();
                    Object_Effect_Skill object_Effect_Skill2 = new Object_Effect_Skill(readShort, readByte2, new Monster_Skill(message.reader().readByte()));
                    object_Effect_Skill2.setHP(readInt2, readInt3);
                    int readByte4 = message.reader().readByte();
                    object_Effect_Skill2.mEffTypePlus = new int[readByte4];
                    object_Effect_Skill2.mEff_HP_Plus = new int[readByte4];
                    for (int i2 = 0; i2 < readByte4; i2++) {
                        object_Effect_Skill2.mEffTypePlus[i2] = message.reader().readByte();
                        object_Effect_Skill2.mEff_HP_Plus[i2] = message.reader().readInt();
                    }
                    MainObject mainObject = MainObject.get_Object(readShort, readByte2);
                    if (mainObject != null && !mainObject.isRemove) {
                        mvector.addElement(object_Effect_Skill2);
                        if (mainObject.hp > 0) {
                            mainObject.hp = readInt3;
                        }
                        if (mainObject.ID == GameScreen.player.ID) {
                            GameScreen.player.setPainthit((byte) 4, (readInt3 / GameScreen.player.maxHp) * 100 > 20);
                        }
                        if (mainObject.hp <= 0) {
                            z = true;
                            mvector2.addElement(mainObject);
                        }
                    }
                }
                mainMonster.vecObjskill = mvector;
                mainMonster.hp = readInt;
                mainMonster.isRunAttack = true;
                mainMonster.timeMaxMoveAttack = ObjectData.NPC;
                mainMonster.timeBeginMoveAttack = GameCanvas.timeNow;
                if (z || mainMonster.typeBoss == 1 || mainMonster.typeBoss == 2) {
                    Object_Effect_Skill object_Effect_Skill3 = (Object_Effect_Skill) mainMonster.vecObjskill.elementAt(0);
                    mainMonster.IDAttack = object_Effect_Skill3.ID;
                    if (mainMonster.skillDefault == null) {
                        mainMonster.skillDefault = new Monster_Skill((byte) 14);
                    }
                    if (!isBossNew) {
                        object_Effect_Skill3.skillMonster = mainMonster.skillDefault;
                    }
                    mainMonster.beginFire();
                    mainMonster.beginSkill();
                }
                for (int i3 = 0; i3 < mvector2.size(); i3++) {
                    MainObject mainObject2 = (MainObject) mvector2.elementAt(i3);
                    mainObject2.resetAction();
                    mainObject2.Action = 4;
                    mainObject2.timedie = GameCanvas.timeNow;
                    mainObject2.f = 0;
                    GameScreen.addEffectEndKill(11, mainObject2.x, mainObject2.y);
                }
            } else if (readByte == 0) {
                if (mainMonster.vecObjskill != null && mainMonster.vecObjskill.size() > 0) {
                    Object_Effect_Skill object_Effect_Skill4 = (Object_Effect_Skill) mainMonster.vecObjskill.elementAt(0);
                    mainMonster.IDAttack = object_Effect_Skill4.ID;
                    if (mainMonster.skillDefault == null) {
                        mainMonster.skillDefault = new Monster_Skill((byte) 14);
                    }
                    if (!isBossNew) {
                        object_Effect_Skill4.skillMonster = mainMonster.skillDefault;
                    }
                    mainMonster.beginFire();
                    mainMonster.beginSkill();
                }
                mainMonster.isRunAttack = false;
                mainMonster.posTransRoad = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            mSystem.outloi(new StringBuffer().append(-1).append(" istarget").toString());
            mSystem.outloi(new StringBuffer().append(-1).append(" size").toString());
        }
    }

    public void loadImage(Message message) {
        try {
            short readShort = message.reader().readShort();
            String stringBuffer = new StringBuffer().append("").append((int) readShort).toString();
            byte[] bArr = new byte[message.reader().available()];
            message.reader().read(bArr);
            if (readShort == 9999) {
                MainObject.imgCapchar = mImage.createImage(bArr, 0, bArr.length, stringBuffer);
                return;
            }
            if (ObjectData.setIdOK(readShort)) {
                SaveImageRMS.vecSaveImage.addElement(new idSaveImage(readShort, bArr));
            }
            mImage createImage = mImage.createImage(bArr, 0, bArr.length, stringBuffer);
            if (readShort >= 13000) {
                Item.HashImageItem.put(new StringBuffer().append("").append(readShort - ObjectData.ITEM).toString(), new MainImage(createImage));
            } else if (readShort >= 10700) {
                Item.HashImageMount.put(new StringBuffer().append("").append(readShort - ObjectData.IMAGE_MOUNT).toString(), new MainImage(createImage));
            } else if (readShort >= 10600) {
                LoadMap.imgTileSmall = createImage;
                GameCanvas.minimap.setSize();
            } else if (readShort >= 10500) {
                LoadMap.imgTileWater = createImage;
            } else if (readShort >= 10400) {
                LoadMap.imgTile = createImage;
            } else if (readShort >= 10200) {
                Pet.HashImagePet.put(new StringBuffer().append("").append(readShort - ObjectData.IMAGE_PET).toString(), new MainImage(createImage));
            } else if (readShort >= 10000) {
                Item.HashImagePetIcon.put(new StringBuffer().append("").append(readShort - ObjectData.ICON_PET).toString(), new MainImage(createImage));
            } else if (readShort >= 9500) {
                Item.HashImageIconArcheClan.put(new StringBuffer().append("").append(readShort - ObjectData.ICON_ARCHE_CLAN).toString(), new MainImage(createImage));
            } else if (readShort >= 7000) {
                Item.HashImageIconClan.put(new StringBuffer().append("").append(readShort - ObjectData.ICON_CLAN).toString(), new MainImage(createImage));
            } else if (readShort >= 6000) {
                Skill.hashImageSkill.put(new StringBuffer().append("").append(readShort - ObjectData.SKILL).toString(), new MainImage(createImage));
            } else if (readShort >= 5500) {
                Item.HashImageMaterial.put(new StringBuffer().append("").append(readShort - ObjectData.MATERIAL).toString(), new MainImage(createImage));
            } else if (readShort >= 5000) {
                Item.HashImageQuestItem.put(new StringBuffer().append("").append(readShort - ObjectData.QUEST_ITEM).toString(), new MainImage(createImage));
            } else if (readShort >= 4000) {
                Item.HashImagePotion.put(new StringBuffer().append("").append(readShort - ObjectData.POTION).toString(), new MainImage(createImage));
            } else if (readShort >= 3000) {
                GameScreen.HashImageNPC.put(new StringBuffer().append("").append(readShort - ObjectData.NPC).toString(), new MainImage(createImage));
            } else if (readShort >= 2000) {
                Item.HashImageItem.put(new StringBuffer().append("").append(readShort - ObjectData.ITEM).toString(), new MainImage(createImage));
            } else if (readShort >= 1000) {
                MainMonster.HashImageMonster.put(new StringBuffer().append("").append(readShort - ObjectData.MONSTER).toString(), new MainImage(createImage));
            } else {
                GameScreen.HashImageItemMap.put(new StringBuffer().append("").append((int) readShort).toString(), new MainImage(createImage));
            }
        } catch (Exception e) {
            mSystem.println("loi ham load img ");
            e.printStackTrace();
        }
    }

    public void charInventory(Message message) {
        update_InVen_Or_Chest(message, Item.VecInvetoryPlayer, (byte) 0);
    }

    public static byte getColorNum(short s, int i) {
        for (int i2 = 0; i2 < Item.VecInvetoryPlayer.size(); i2++) {
            Item item = (Item) Item.VecInvetoryPlayer.elementAt(i2);
            if (item != null && item.ItemCatagory == 7 && item.Id == s && item.numPotion >= i) {
                return (byte) 1;
            }
        }
        return (byte) 0;
    }

    public static String getInfoNum(short s, int i) {
        for (int i2 = 0; i2 < Item.VecInvetoryPlayer.size(); i2++) {
            Item item = (Item) Item.VecInvetoryPlayer.elementAt(i2);
            if (item != null && item.ItemCatagory == 7 && item.Id == s) {
                return new StringBuffer().append(item.numPotion).append("/").append(i).toString();
            }
        }
        return new StringBuffer().append("0/").append(i).toString();
    }

    public void npcInfo(Message message) {
        MainTabNew tabRebuildItem;
        Item itemInventory;
        Item itemInventory2;
        Item itemInventory3;
        Item itemInventory4;
        Item itemInventory5;
        Item itemInventory6;
        Item itemInventory7;
        try {
            byte b = 8;
            String str = "";
            boolean z = false;
            String readUTF = message.reader().readUTF();
            byte readByte = message.reader().readByte();
            TabShopNew.isTabHopNL = false;
            if (readByte < 0) {
                MainObject mainObject = MainObject.get_Object(readByte, (byte) 2);
                if (mainObject != null && !mainObject.isRemove) {
                    mainObject.strChatPopup = readUTF;
                }
            } else {
                GameCanvas.start_Ok_Dialog(T.danglaydulieu);
                int readShort = message.reader().readShort();
                mVector mvector = new mVector("ReadMessenge vectorInfoNPC");
                byte b2 = TabShopNew.NORMAL;
                byte b3 = TabShopNew.NORMAL;
                if (readByte == SHOP_OTHER_PLAYER) {
                    z = true;
                    b3 = TabShopNew.SHOP_STORE_OTHER_PLAYER;
                    for (int i = 0; i < readShort; i++) {
                        byte readByte2 = message.reader().readByte();
                        if (readByte2 == 3) {
                            short readShort2 = message.reader().readShort();
                            String readUTF2 = message.reader().readUTF();
                            byte readByte3 = message.reader().readByte();
                            byte readByte4 = message.reader().readByte();
                            short readShort3 = message.reader().readShort();
                            byte readByte5 = message.reader().readByte();
                            short readShort4 = message.reader().readShort();
                            byte readByte6 = message.reader().readByte();
                            int readByte7 = message.reader().readByte();
                            MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte7];
                            for (int i2 = 0; i2 < readByte7; i2++) {
                                mainInfoItemArr[i2] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                            }
                            mvector.addElement(MainItem.MainItem_Item(readShort2, readUTF2, readShort3, readByte5, readByte6, readByte3, 3, mainInfoItemArr, readByte4, false, readShort2, 0L, readShort4, (byte) 0, (byte) 0, 0, message.reader().readByte(), (byte) 0));
                        }
                        if (readByte2 == 7) {
                            short readShort5 = message.reader().readShort();
                            short readShort6 = message.reader().readShort();
                            byte readByte8 = message.reader().readByte();
                            byte readByte9 = message.reader().readByte();
                            long readLong = message.reader().readLong();
                            MainItem material = Item.getMaterial(readShort5);
                            if (material != null) {
                                mvector.addElement(MainItem.MainItem_Material(readShort5, material.itemName, material.imageId, 7, readLong, (byte) 0, material.content, material.value, material.typeMaterial, readShort6, readByte8, readByte9));
                            } else {
                                MainItem mainItem = new MainItem();
                                mainItem.Id = readShort5;
                                mainItem.numPotion = readShort6;
                                mainItem.canSell = readByte8;
                                mainItem.canTrade = readByte9;
                                mainItem.priceItem = readLong;
                                mainItem.priceType = (byte) 0;
                                mainItem.ItemCatagory = 7;
                                Item.put_Material(readShort5);
                                mvector.addElement(mainItem);
                            }
                        }
                        if (readByte2 == 4) {
                            short readShort7 = message.reader().readShort();
                            short readShort8 = message.reader().readShort();
                            long readLong2 = message.reader().readLong();
                            MainTemplateItem potionTem = MainTemplateItem.getPotionTem(readShort7);
                            if (potionTem != null) {
                                mvector.addElement(new MainItem(potionTem.ID, potionTem.IconId, potionTem.name, potionTem.contentPotion, readShort8, 4, readLong2, potionTem.typePotion, (byte) 0, (byte) 0, (byte) 0));
                            }
                        }
                    }
                    TabShopNew.idSeller = message.reader().readShort();
                } else if (readByte == SHOP_VANTIEU) {
                    b3 = TabShopNew.SHOP_VANTIEU;
                    for (int i3 = 0; i3 < readShort; i3++) {
                        short readShort9 = message.reader().readShort();
                        String readUTF3 = message.reader().readUTF();
                        byte readByte10 = message.reader().readByte();
                        byte readByte11 = message.reader().readByte();
                        short readShort10 = message.reader().readShort();
                        long readLong3 = message.reader().readLong();
                        short readShort11 = message.reader().readShort();
                        byte readByte12 = message.reader().readByte();
                        int readByte13 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArr2 = new MainInfoItem[readByte13];
                        for (int i4 = 0; i4 < readByte13; i4++) {
                            mainInfoItemArr2[i4] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                        }
                        mvector.addElement(MainItem.MainItem_Item(readShort9, readUTF3, readShort10, (byte) 0, readByte12, readByte10, 3, mainInfoItemArr2, readByte11, false, readShort9, readLong3, readShort11, (byte) 0, (byte) 0, 0, message.reader().readByte(), (byte) 0));
                    }
                } else if (readByte == SHOP_POTION) {
                    for (int i5 = 0; i5 < readShort; i5++) {
                        MainTemplateItem potionTem2 = MainTemplateItem.getPotionTem(message.reader().readShort());
                        if (potionTem2 != null) {
                            mvector.addElement(new MainItem(potionTem2.ID, potionTem2.IconId, potionTem2.name, potionTem2.contentPotion, 1, 4, potionTem2.PricePoition, potionTem2.typePotion, potionTem2.moneyType, (byte) 0, (byte) 0));
                        }
                    }
                } else if (readByte == SHOP_ITEM || readByte == WING || readByte == SHOP_ITEM_EVENT) {
                    if (readByte == WING) {
                        b3 = TabShopNew.WING;
                    }
                    for (int i6 = 0; i6 < readShort; i6++) {
                        short readShort12 = message.reader().readShort();
                        String readUTF4 = message.reader().readUTF();
                        byte readByte14 = message.reader().readByte();
                        byte readByte15 = message.reader().readByte();
                        short readShort13 = message.reader().readShort();
                        long readLong4 = message.reader().readLong();
                        short readShort14 = message.reader().readShort();
                        byte readByte16 = message.reader().readByte();
                        int readByte17 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArr3 = new MainInfoItem[readByte17];
                        for (int i7 = 0; i7 < readByte17; i7++) {
                            mainInfoItemArr3[i7] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                        }
                        MainItem MainItem_Item = MainItem.MainItem_Item(readShort12, readUTF4, readShort13, (byte) 0, readByte16, readByte14, 3, mainInfoItemArr3, readByte15, false, readShort12, readLong4, readShort14, (byte) 0, (byte) 0, 0, message.reader().readByte(), (byte) 0);
                        if (readByte == SHOP_ITEM_EVENT) {
                            MainItem_Item.diaHoiEvent = message.reader().readUTF();
                        }
                        mvector.addElement(MainItem_Item);
                    }
                    if (readByte == SHOP_ITEM_EVENT) {
                        str = message.reader().readUTF();
                    }
                } else if (readByte == SHOP_HAIR) {
                    b3 = TabShopNew.TOC;
                    for (int i8 = 0; i8 < readShort; i8++) {
                        short readShort15 = message.reader().readShort();
                        String readUTF5 = message.reader().readUTF();
                        short readShort16 = message.reader().readShort();
                        long readLong5 = message.reader().readLong();
                        byte readByte18 = message.reader().readByte();
                        int readByte19 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArr4 = new MainInfoItem[readByte19];
                        for (int i9 = 0; i9 < readByte19; i9++) {
                            mainInfoItemArr4[i9] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                        }
                        mvector.addElement(MainItem.MainItem_Toc(readShort15, readUTF5, readShort16, (byte) 0, 6, mainInfoItemArr4, false, readShort15, readLong5, readByte18, (byte) 0, (byte) 0));
                    }
                } else if (readByte == CHEST) {
                    b2 = TabShopNew.INVEN_AND_CHEST;
                    b = 9;
                    if (Item.VecChestPlayer.size() == 0) {
                        GlobalService.gI().Update_Char_Chest((byte) -1, (short) 0, (byte) 0, (short) 0);
                    }
                    mvector = Item.VecChestPlayer;
                } else if (readByte == PET_KEEPER) {
                    b2 = TabShopNew.INVEN_AND_PET_KEEPER;
                    b = 13;
                    b3 = TabShopNew.INVEN_AND_PET_KEEPER;
                } else if (readByte == SHOP_MATERIAL) {
                    b = 8;
                    for (int i10 = 0; i10 < readShort; i10++) {
                        short readShort17 = message.reader().readShort();
                        MainItem material2 = Item.getMaterial(readShort17);
                        if (material2 != null) {
                            mvector.addElement(MainItem.MainItem_Material(readShort17, material2.itemName, material2.imageId, 7, material2.priceItem, material2.priceType, material2.content, (short) material2.value, material2.typeMaterial, (short) 1, material2.canSell, material2.canTrade));
                        } else {
                            MainItem mainItem2 = new MainItem();
                            mainItem2.Id = readShort17;
                            mainItem2.ItemCatagory = 7;
                            mvector.addElement(mainItem2);
                        }
                    }
                } else if (readByte == REBUILD || readByte == TAB_HOP_NGUYEN_LIEU) {
                    b2 = TabShopNew.INVEN_AND_REBUILD;
                    b = 10;
                    if (readByte == TAB_HOP_NGUYEN_LIEU) {
                        TabShopNew.isTabHopNL = true;
                    }
                } else if (readByte == SHOP_HOP_AN) {
                    b2 = TabShopNew.SHOP_HOP_AN;
                    b = 10;
                } else if (readByte == SHOP_NANG_CAP_MEDAL) {
                    b2 = TabShopNew.SHOP_NANG_CAP_MEDEL;
                    b = 10;
                } else if (readByte == SHOP_ANY_NGUYEN_LIEU) {
                    b2 = TabShopNew.SHOP_ANY_NGUYEN_LIEU;
                    b = 10;
                } else if (readByte == SHOP_GHEP_NGOC) {
                    b2 = TabShopNew.SHOP_GHEP_NGOC;
                    b = 10;
                } else if (readByte == SHOP_DUC_LO) {
                    b2 = TabShopNew.SHOP_DUC_LO;
                    b = 10;
                } else if (readByte == SHOP_KHAM_NGOC) {
                    b2 = TabShopNew.SHOP_KHAM_NGOC;
                    b = 10;
                } else if (readByte == SHOP_ICONCLAN_FREE || readByte == SHOP_ICONCLAN_VIP) {
                    b = 8;
                    b3 = TabShopNew.SHOP_BANG;
                    for (int i11 = 0; i11 < readShort; i11++) {
                        short s = 0;
                        if (readByte == SHOP_ICONCLAN_VIP) {
                            s = TabShopNew.PriceIconClan;
                        }
                        mvector.addElement(new MainItem(message.reader().readShort(), s));
                    }
                } else if (readByte == SHOP_POTION_CLAN) {
                    b = 8;
                    b3 = TabShopNew.NORMAL;
                    for (int i12 = 0; i12 < readShort; i12++) {
                        MainTemplateItem potionTem3 = MainTemplateItem.getPotionTem(message.reader().readShort());
                        if (potionTem3 != null) {
                            mvector.addElement(new MainItem(potionTem3.ID, potionTem3.IconId, potionTem3.name, potionTem3.contentPotion, 1, 4, potionTem3.PricePoition, potionTem3.typePotion, potionTem3.moneyType, (byte) 0, (byte) 0));
                        }
                    }
                } else if (readByte == REPLACE) {
                    b2 = TabShopNew.INVEN_AND_REPLACE;
                    b = 10;
                }
                mVector mvector2 = new mVector("ReadMessenge vec11");
                if (readByte != SHOP_ICONCLAN_FREE && readByte != SHOP_ICONCLAN_VIP && readByte != SHOP_POTION_CLAN) {
                    TabShopNew tabShopNew = new TabShopNew(Item.VecInvetoryPlayer, (byte) 0, T.tabhanhtrang, -1, b2);
                    tabShopNew.isShop_Other_Player = z;
                    mvector2.addElement(tabShopNew);
                }
                if (readByte == REBUILD || readByte == TAB_HOP_NGUYEN_LIEU) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_REBUILD);
                    tabRebuildItem.isTabHopNguyenLieu = readByte == TAB_HOP_NGUYEN_LIEU;
                    if (readByte == TAB_HOP_NGUYEN_LIEU) {
                        TabShopNew.isTabHopNL = true;
                    }
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i13 = 0; i13 < TabRebuildItem.idMaterial.length; i13++) {
                        MainItem material3 = Item.getMaterial(TabRebuildItem.idMaterial[i13]);
                        int i14 = 0;
                        if (material3 != null && (itemInventory = Item.getItemInventory(material3.ItemCatagory, (short) material3.Id)) != null) {
                            i14 = itemInventory.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i13] = i14;
                    }
                } else if (readByte == SHOP_KHAM_NGOC) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_KHAM_NGOC);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i15 = 0; i15 < TabRebuildItem.idMaterial.length; i15++) {
                        MainItem material4 = Item.getMaterial(TabRebuildItem.idMaterial[i15]);
                        int i16 = 0;
                        if (material4 != null && (itemInventory7 = Item.getItemInventory(material4.ItemCatagory, (short) material4.Id)) != null) {
                            i16 = itemInventory7.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i15] = i16;
                    }
                } else if (readByte == SHOP_DUC_LO) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_DUC_LO);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i17 = 0; i17 < TabRebuildItem.idMaterial.length; i17++) {
                        MainItem material5 = Item.getMaterial(TabRebuildItem.idMaterial[i17]);
                        int i18 = 0;
                        if (material5 != null && (itemInventory6 = Item.getItemInventory(material5.ItemCatagory, (short) material5.Id)) != null) {
                            i18 = itemInventory6.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i17] = i18;
                    }
                } else if (readByte == SHOP_HOP_AN) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_ANY);
                    tabRebuildItem.isCreate_medal = true;
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i19 = 0; i19 < TabRebuildItem.idMaterial.length; i19++) {
                        MainItem material6 = Item.getMaterial(TabRebuildItem.idMaterial[i19]);
                        int i20 = 0;
                        if (material6 != null && (itemInventory5 = Item.getItemInventory(material6.ItemCatagory, (short) material6.Id)) != null) {
                            i20 = itemInventory5.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i19] = i20;
                    }
                    int readByte20 = message.reader().readByte();
                    TabRebuildItem.vecGem.removeAllElements();
                    for (int i21 = 0; i21 < readByte20; i21++) {
                        short readShort18 = message.reader().readShort();
                        short readShort19 = message.reader().readShort();
                        if (readShort18 != -1 && 7 == 7) {
                            TabRebuildItem.numColor[i21] = getColorNum(readShort18, readShort19);
                            TabRebuildItem.numofGem[i21] = getInfoNum(readShort18, readShort19);
                            MainItem material7 = Item.getMaterial(readShort18);
                            if (material7 != null) {
                                TabRebuildItem.vecGem.addElement(material7);
                            }
                            if (material7 == null) {
                                MainItem mainItem3 = new MainItem();
                                mainItem3.Id = readShort18;
                                mainItem3.ItemCatagory = 7;
                                TabRebuildItem.vecGem.addElement(mainItem3);
                            }
                        }
                    }
                } else if (readByte == SHOP_NANG_CAP_MEDAL) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_ANY);
                    tabRebuildItem.isUPgradeMedal = true;
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i22 = 0; i22 < TabRebuildItem.idMaterial.length; i22++) {
                        MainItem material8 = Item.getMaterial(TabRebuildItem.idMaterial[i22]);
                        int i23 = 0;
                        if (material8 != null && (itemInventory4 = Item.getItemInventory(material8.ItemCatagory, (short) material8.Id)) != null) {
                            i23 = itemInventory4.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i22] = i23;
                    }
                } else if (readByte == SHOP_ANY_NGUYEN_LIEU) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_ANY);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i24 = 0; i24 < TabRebuildItem.idMaterial.length; i24++) {
                        MainItem material9 = Item.getMaterial(TabRebuildItem.idMaterial[i24]);
                        int i25 = 0;
                        if (material9 != null && (itemInventory3 = Item.getItemInventory(material9.ItemCatagory, (short) material9.Id)) != null) {
                            i25 = itemInventory3.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i24] = i25;
                    }
                } else if (readByte == SHOP_GHEP_NGOC) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_GHEP_NGOC);
                    TabRebuildItem.vecGem.removeAllElements();
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                    for (int i26 = 0; i26 < TabRebuildItem.idMaterial.length; i26++) {
                        MainItem material10 = Item.getMaterial(TabRebuildItem.idMaterial[i26]);
                        int i27 = 0;
                        if (material10 != null && (itemInventory2 = Item.getItemInventory(material10.ItemCatagory, (short) material10.Id)) != null) {
                            i27 = itemInventory2.numPotion;
                        }
                        TabRebuildItem.numMaterialInven[i26] = i27;
                    }
                } else if (readByte == REPLACE) {
                    tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_REPLACE_PLUS);
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemFree = null;
                } else {
                    tabRebuildItem = new TabShopNew(mvector, b, readUTF, readByte, b3);
                    if (readByte == SHOP_ITEM_EVENT) {
                        tabRebuildItem.setNameCmd(str);
                    }
                }
                mvector2.addElement(tabRebuildItem);
                GameCanvas.shopNpc = new TabScreenNew();
                if (readByte != SHOP_ICONCLAN_FREE && readByte != SHOP_ICONCLAN_VIP && readByte != SHOP_POTION_CLAN) {
                    GameCanvas.shopNpc.selectTab = 1;
                } else {
                    GameCanvas.shopNpc.selectTab = 0;
                }
                GameCanvas.shopNpc.addMoreTab(mvector2);
                GameCanvas.shopNpc.Show(GameScreen.gI());
                GameCanvas.end_Dialog();
                if (readByte == REBUILD || readByte == REPLACE || readByte == TAB_HOP_NGUYEN_LIEU) {
                    tabRebuildItem.init();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void itemTemplate(Message message) {
        try {
            int readShort = message.reader().readShort();
            for (int i = 0; i < readShort; i++) {
                short readShort2 = message.reader().readShort();
                MainTemplateItem.hashPotionTem.put(new StringBuffer().append("").append((int) readShort2).toString(), new MainTemplateItem(readShort2, message.reader().readShort(), message.reader().readLong(), message.reader().readUTF(), message.reader().readUTF(), message.reader().readByte(), message.reader().readByte(), message.reader().readByte(), message.reader().readShort(), message.reader().readBoolean()));
            }
            int readUnsignedByte = message.reader().readUnsignedByte();
            Item.nameInfoItem = new String[readUnsignedByte];
            Item.colorInfoItem = new byte[readUnsignedByte];
            Item.isPercentInfoItem = new byte[readUnsignedByte];
            for (int i2 = 0; i2 < readUnsignedByte; i2++) {
                Item.nameInfoItem[i2] = message.reader().readUTF();
                Item.colorInfoItem[i2] = message.reader().readByte();
                Item.isPercentInfoItem[i2] = message.reader().readByte();
            }
            int readShort3 = message.reader().readShort();
            for (int i3 = 0; i3 < readShort3; i3++) {
                short readShort4 = message.reader().readShort();
                short readShort5 = message.reader().readShort();
                long readLong = message.reader().readLong();
                String readUTF = message.reader().readUTF();
                String readUTF2 = message.reader().readUTF();
                byte readByte = message.reader().readByte();
                byte readByte2 = message.reader().readByte();
                byte readByte3 = message.reader().readByte();
                short readShort6 = message.reader().readShort();
                byte readByte4 = message.reader().readByte();
                byte readByte5 = message.reader().readByte();
                MainItem MainItem_Material = MainItem.MainItem_Material(readShort4, readUTF, readShort5, 7, readLong, readByte2, readUTF2, readShort6, readByte, (short) 1, readByte3, readByte4);
                MainItem_Material.setColorName(readByte5);
                MainTemplateItem.hashMaterialTem.put(new StringBuffer().append("").append((int) readShort4).toString(), MainItem_Material);
            }
            TabShopNew.priceSellPotion = message.reader().readShort();
            TabShopNew.priceSellItem = message.reader().readShort();
            TabShopNew.hesoLv = message.reader().readShort();
            TabShopNew.hesoColor = message.reader().readShort();
            TabShopNew.priceSellQuest = message.reader().readShort();
            TabShopNew.maxPriceItem = message.reader().readShort();
            TabShopNew.PriceIconClan = message.reader().readShort();
            TabShopNew.PriceChatWorld = message.reader().readByte();
            LoginScreen.indexInfoLogin = (byte) 0;
            int readByte6 = message.reader().readByte();
            mSystem.outz(new StringBuffer().append("size pet template = ").append(readByte6).toString());
            for (int i4 = 0; i4 < readByte6; i4++) {
                MainTemplateItem.hashPetTem.put(new StringBuffer().append("").append((int) message.reader().readShort()).toString(), new Byte(message.reader().readByte()));
            }
            try {
                int readByte7 = message.reader().readByte();
                MainObject.idMaterialHopNguyenLieu = new short[readByte7];
                for (int i5 = 0; i5 < readByte7; i5++) {
                    MainObject.idMaterialHopNguyenLieu[i5] = message.reader().readShort();
                }
            } catch (Exception e) {
                mSystem.println("----Err Readmess:-- itemTemplate");
            }
            try {
                PaintInfoGameScreen.paint18plush = message.reader().readByte();
            } catch (Exception e2) {
                PaintInfoGameScreen.paint18plush = (byte) 0;
            }
            try {
                int readByte8 = message.reader().readByte();
                GameScreen.idmap18 = new short[readByte8];
                for (int i6 = 0; i6 < readByte8; i6++) {
                    GameScreen.idmap18[i6] = (short) message.reader().readUnsignedByte();
                }
            } catch (Exception e3) {
                GameScreen.idmap18 = new short[0];
            }
        } catch (Exception e4) {
            Session_ME.gI().close();
            mVector mvector = new mVector("ReadMessenge vec7");
            if (SelectCharScreen.isSelectOk && GameCanvas.currentScreen != GameCanvas.login) {
                mvector.addElement(new iCommand(T.again, 0));
            }
            mvector.addElement(new iCommand(T.exit, 6));
            GameCanvas.start_Select_Dialog(T.disconnect, mvector);
            e4.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v106, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v17, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v24, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v31, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v38, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v45, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v52, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r0v59, types: [byte[], byte[][]] */
    /* JADX WARN: Type inference failed for: r11v11, types: [int] */
    /* JADX WARN: Type inference failed for: r14v7, types: [int] */
    public void catalogyMonster(Message message) {
        byte[][] bArr;
        byte[] bArr2;
        try {
            short readShort = message.reader().readShort();
            for (short s = 0; s < readShort; s++) {
                MainMonster.VecCatalogyMonSter.addElement(new CatalogyMonster(message.reader().readShort(), (short) message.reader().readUnsignedByte(), message.reader().readInt(), message.reader().readByte(), message.reader().readUTF()));
            }
            int readByte = message.reader().readByte();
            MountTemplate.FRAME_VE_TRUOC = new byte[readByte];
            for (int i = 0; i < readByte; i++) {
                int readByte2 = message.reader().readByte();
                MountTemplate.FRAME_VE_TRUOC[i] = new byte[readByte2];
                for (int i2 = 0; i2 < readByte2; i2++) {
                    MountTemplate.FRAME_VE_TRUOC[i][i2] = message.reader().readByte();
                }
            }
            int readByte3 = message.reader().readByte();
            MountTemplate.DY_CHAR_STAND = new byte[readByte3];
            for (int i3 = 0; i3 < readByte3; i3++) {
                int readByte4 = message.reader().readByte();
                MountTemplate.DY_CHAR_STAND[i3] = new byte[readByte4];
                for (int i4 = 0; i4 < readByte4; i4++) {
                    MountTemplate.DY_CHAR_STAND[i3][i4] = message.reader().readByte();
                }
            }
            int readByte5 = message.reader().readByte();
            MountTemplate.DY_CHAR_MOVE = new byte[readByte5];
            for (int i5 = 0; i5 < readByte5; i5++) {
                int readByte6 = message.reader().readByte();
                MountTemplate.DY_CHAR_MOVE[i5] = new byte[readByte6];
                for (int i6 = 0; i6 < readByte6; i6++) {
                    MountTemplate.DY_CHAR_MOVE[i5][i6] = message.reader().readByte();
                }
            }
            int readByte7 = message.reader().readByte();
            MountTemplate.dx = new byte[readByte7];
            for (int i7 = 0; i7 < readByte7; i7++) {
                int readByte8 = message.reader().readByte();
                MountTemplate.dx[i7] = new byte[readByte8];
                for (int i8 = 0; i8 < readByte8; i8++) {
                    MountTemplate.dx[i7][i8] = message.reader().readByte();
                }
            }
            int readByte9 = message.reader().readByte();
            MountTemplate.dy = new byte[readByte9];
            for (int i9 = 0; i9 < readByte9; i9++) {
                int readByte10 = message.reader().readByte();
                MountTemplate.dy[i9] = new byte[readByte10];
                for (int i10 = 0; i10 < readByte10; i10++) {
                    MountTemplate.dy[i9][i10] = message.reader().readByte();
                }
            }
            int readByte11 = message.reader().readByte();
            MountTemplate.FRAME_MOVE_LR = new byte[readByte11];
            for (int i11 = 0; i11 < readByte11; i11++) {
                int readByte12 = message.reader().readByte();
                MountTemplate.FRAME_MOVE_LR[i11] = new byte[readByte12];
                for (int i12 = 0; i12 < readByte12; i12++) {
                    MountTemplate.FRAME_MOVE_LR[i11][i12] = message.reader().readByte();
                }
            }
            int readByte13 = message.reader().readByte();
            MountTemplate.FRAME_MOVE_DOWN = new byte[readByte13];
            for (int i13 = 0; i13 < readByte13; i13++) {
                int readByte14 = message.reader().readByte();
                MountTemplate.FRAME_MOVE_DOWN[i13] = new byte[readByte14];
                for (int i14 = 0; i14 < readByte14; i14++) {
                    MountTemplate.FRAME_MOVE_DOWN[i13][i14] = message.reader().readByte();
                }
            }
            int readByte15 = message.reader().readByte();
            MountTemplate.FRAME_MOVE_UP = new byte[readByte15];
            for (int i15 = 0; i15 < readByte15; i15++) {
                int readByte16 = message.reader().readByte();
                MountTemplate.FRAME_MOVE_UP[i15] = new byte[readByte16];
                for (int i16 = 0; i16 < readByte16; i16++) {
                    MountTemplate.FRAME_MOVE_UP[i15][i16] = message.reader().readByte();
                }
            }
            int readByte17 = message.reader().readByte();
            MountTemplate.speed = new byte[readByte17];
            for (int i17 = 0; i17 < readByte17; i17++) {
                MountTemplate.speed[i17] = message.reader().readByte();
            }
            int readByte18 = message.reader().readByte();
            Item.ATB_Can_Not_Paint = new byte[readByte18];
            for (int i18 = 0; i18 < readByte18; i18++) {
                Item.ATB_Can_Not_Paint[i18] = message.reader().readByte();
            }
            try {
                int readByte19 = message.reader().readByte();
                Pet.mTypemove = new byte[readByte19];
                for (int i19 = 0; i19 < readByte19; i19++) {
                    Pet.mTypemove[i19] = message.reader().readByte();
                }
            } catch (Exception e) {
                Pet.mTypemove = new byte[]{2, 1, 0, 2, 0, 1};
            }
            try {
                int readByte20 = message.reader().readByte();
                MainTemplateItem.mItem_Equip_Tem = new int[readByte20];
                for (int i20 = 0; i20 < readByte20; i20++) {
                    MainTemplateItem.mItem_Equip_Tem[i20] = message.reader().readByte();
                }
            } catch (Exception e2) {
                MainTemplateItem.mItem_Equip_Tem = new int[]{-1, 0, 3, 4, 5, 12, 2, 1, 6, 4, 7, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2, -2};
            }
            try {
                int readByte21 = message.reader().readByte();
                MainTemplateItem.mItem_Rotate_Tem_Equip = new int[readByte21];
                for (int i21 = 0; i21 < readByte21; i21++) {
                    MainTemplateItem.mItem_Rotate_Tem_Equip[i21] = message.reader().readByte();
                }
            } catch (Exception e3) {
                MainTemplateItem.mItem_Rotate_Tem_Equip = new int[]{1, 7, 6, 2, -1, 4, 8, 10, 0, 0, 0, 0, 5};
            }
            try {
                int readByte22 = message.reader().readByte();
                MainMonster.idBossNew = new short[readByte22];
                for (int i22 = 0; i22 < readByte22; i22++) {
                    MainMonster.idBossNew[i22] = message.reader().readShort();
                }
            } catch (Exception e4) {
                MainMonster.idBossNew = new short[]{104, 103, 105, 106, 135};
            }
            try {
                int readByte23 = message.reader().readByte();
                Player.ID_HAIR_NO_HAT = new byte[readByte23];
                for (int i23 = 0; i23 < readByte23; i23++) {
                    Player.ID_HAIR_NO_HAT[i23] = message.reader().readByte();
                }
            } catch (Exception e5) {
                Player.ID_HAIR_NO_HAT = null;
            }
            try {
                byte readByte24 = message.reader().readByte();
                for (byte b = 0; b < readByte24; b++) {
                    byte readByte25 = message.reader().readByte();
                    int readShort2 = message.reader().readShort();
                    if (readShort2 > 0) {
                        byte[] bArr3 = new byte[readShort2];
                        for (int i24 = 0; i24 < readShort2; i24++) {
                            bArr3[i24] = message.reader().readByte();
                        }
                        mVector mvector = new mVector();
                        mvector.addElement(new DataEffect(bArr3, readByte25, true));
                        Pet.PET_DATA.put(new StringBuffer().append("").append((int) readByte25).toString(), mvector);
                    }
                }
            } catch (Exception e6) {
            }
            try {
                int readShort3 = message.reader().readShort();
                bArr = new byte[readShort3];
                for (int i25 = 0; i25 < readShort3; i25++) {
                    int readByte26 = message.reader().readByte();
                    bArr[i25] = new byte[readByte26];
                    for (int i26 = 0; i26 < readByte26; i26++) {
                        bArr[i25][i26] = message.reader().readByte();
                    }
                }
            } catch (Exception e7) {
                bArr = (byte[][]) null;
            }
            if (bArr != null) {
                EffectSkill.arrInfoEff = bArr;
            }
            try {
                int readByte27 = message.reader().readByte();
                bArr2 = new byte[readByte27];
                for (int i27 = 0; i27 < readByte27; i27++) {
                    bArr2[i27] = message.reader().readByte();
                }
            } catch (Exception e8) {
                bArr2 = null;
            }
            if (bArr2 != null) {
                MountTemplate.Arr_Fly = bArr2;
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    public void listChar(Message message) {
        try {
            LoginScreen.saveUser_Pass();
            LoginScreen.saveIndexServer();
            GameScreen.player = new Player(0, (byte) 0, "unname", 0, 0);
            Item.VecInvetoryPlayer.removeAllElements();
            Item.VecChestPlayer.removeAllElements();
            SelectCharScreen.VecSelectChar.removeAllElements();
            int readByte = message.reader().readByte();
            mVector mvector = new mVector("ReadMessenge vList");
            int i = (GameCanvas.w - 150) / 4;
            int i2 = i;
            for (int i3 = 0; i3 < readByte; i3++) {
                int i4 = (GameCanvas.hh - 25) - (20 * ((i3 + 1) % 2));
                int readInt = message.reader().readInt();
                String readUTF = message.reader().readUTF();
                byte readByte2 = message.reader().readByte();
                byte readByte3 = message.reader().readByte();
                byte readByte4 = message.reader().readByte();
                int readByte5 = message.reader().readByte();
                byte[] bArr = new byte[12];
                for (int i5 = 0; i5 < bArr.length; i5++) {
                    bArr[i5] = -1;
                }
                for (int i6 = 0; i6 < readByte5; i6++) {
                    bArr[message.reader().readByte()] = message.reader().readByte();
                }
                short readShort = message.reader().readShort();
                byte readByte6 = message.reader().readByte();
                message.reader().readByte();
                message.reader().readByte();
                Other_Players other_Players = new Other_Players(readInt, (byte) 0, readUTF, 0, 0);
                short readShort2 = message.reader().readShort();
                if (readShort2 >= 0) {
                    other_Players.myClan = new MainClan(0, readShort2, message.reader().readUTF(), message.reader().readByte());
                }
                other_Players.clazz = readByte6;
                other_Players.setInfo(readByte2, readByte4, readByte3);
                other_Players.setWearingListChar(bArr);
                other_Players.Lv = readShort;
                other_Players.x = i2 + 25;
                other_Players.y = i4 + 50;
                i2 += 50 + i;
                mvector.addElement(other_Players);
            }
            GameCanvas.selectChar.setPosPaint(mvector);
            if (readByte == 0) {
                GameCanvas.createChar = new CreateChar();
                GameCanvas.createChar.Show((byte) 0);
            } else {
                GameCanvas.selectChar.Show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void npcBig(Message message) {
        try {
            int readByte = message.reader().readByte();
            for (int i = 0; i < readByte; i++) {
                String readUTF = message.reader().readUTF();
                String readUTF2 = message.reader().readUTF();
                byte readByte2 = message.reader().readByte();
                byte readByte3 = message.reader().readByte();
                short readShort = message.reader().readShort();
                short readShort2 = message.reader().readShort();
                byte readByte4 = message.reader().readByte();
                byte readByte5 = message.reader().readByte();
                byte readByte6 = message.reader().readByte();
                MainObject mainObject = MainObject.get_Object(readByte2, (byte) 2);
                if (mainObject == null) {
                    mainObject = new NPC(readUTF, readUTF2, readByte2, readByte3, readShort, readShort2, readByte4, readByte5, readByte6);
                } else {
                    mainObject.name = readUTF;
                    mainObject.nameGiaotiep = readUTF2;
                    mainObject.imageId = readByte3;
                    mainObject.x = readShort;
                    mainObject.y = readShort2;
                }
                mainObject.isRemove = false;
                mainObject.IdBigAvatar = message.reader().readByte() + 500;
                mainObject.infoObject = message.reader().readUTF();
                mainObject.isPerson = message.reader().readByte();
                mainObject.isShowHP = message.reader().readByte() == 1;
                mainObject.typeObject = (byte) 2;
                GameScreen.addPlayer(mainObject);
                MiniMap.addNPCMini(new NPCMini(readByte2, readShort, readShort2));
                if (readByte2 == -52) {
                    Mount mount = new Mount((byte) 0, (byte) 2, readShort + 48, readShort2 + 10);
                    if (!GameScreen.Vecplayers.contains(mount)) {
                        GameScreen.addPlayer(mount);
                    }
                    Mount mount2 = new Mount((byte) 2, (byte) 0, readShort + 43, readShort2 - 4);
                    if (!GameScreen.Vecplayers.contains(mount2)) {
                        GameScreen.addPlayer(mount2);
                    }
                    Mount mount3 = new Mount((byte) 3, (byte) 3, readShort - 18, readShort2 + 7);
                    if (!GameScreen.Vecplayers.contains(mount3)) {
                        GameScreen.addPlayer(mount3);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void onReceiveInfoQuest(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte < 3) {
                byte readByte2 = message.reader().readByte();
                MainQuest mainQuest = null;
                if (readByte == 0) {
                    MainQuest.vecQuestList.removeAllElements();
                } else if (readByte == 1) {
                    MainQuest.vecQuestFinish.removeAllElements();
                } else if (readByte == 2) {
                    MainQuest.vecQuestDoing_Main.removeAllElements();
                    MainQuest.vecQuestDoing_Sub.removeAllElements();
                }
                for (byte b = 0; b < readByte2; b = (byte) (b + 1)) {
                    short readShort = message.reader().readShort();
                    boolean readBoolean = message.reader().readBoolean();
                    String readUTF = message.reader().readUTF();
                    switch (readByte) {
                        case 0:
                            mainQuest = new MainQuest(readShort, readBoolean, readUTF, message.reader().readByte(), message.reader().readUTF(), message.reader().readByte(), message.reader().readUTF());
                            MainQuest.vecQuestList.addElement(mainQuest);
                            break;
                        case 1:
                            mainQuest = new MainQuest(readShort, readBoolean, readUTF, message.reader().readByte(), message.reader().readUTF(), message.reader().readUTF());
                            MainQuest.vecQuestFinish.addElement(mainQuest);
                            break;
                        case 2:
                            byte readByte3 = message.reader().readByte();
                            String readUTF2 = message.reader().readUTF();
                            String readUTF3 = message.reader().readUTF();
                            byte readByte4 = message.reader().readByte();
                            if (readByte3 == 0) {
                                int readByte5 = message.reader().readByte();
                                short[] sArr = new short[readByte5];
                                short[] sArr2 = new short[readByte5];
                                short[] sArr3 = new short[readByte5];
                                for (byte b2 = 0; b2 < readByte5; b2 = (byte) (b2 + 1)) {
                                    sArr[b2] = message.reader().readShort();
                                    sArr3[b2] = message.reader().readShort();
                                    sArr2[b2] = message.reader().readShort();
                                }
                                mainQuest = new MainQuest(readShort, readBoolean, readUTF, readUTF3, readByte3, readUTF2, readByte4, sArr, sArr2, sArr3, 0);
                            } else if (readByte3 == 1) {
                                int readByte6 = message.reader().readByte();
                                short[] sArr4 = new short[readByte6];
                                short[] sArr5 = new short[readByte6];
                                short[] sArr6 = new short[readByte6];
                                for (byte b3 = 0; b3 < readByte6; b3 = (byte) (b3 + 1)) {
                                    sArr4[b3] = message.reader().readShort();
                                    sArr6[b3] = message.reader().readShort();
                                    sArr5[b3] = message.reader().readShort();
                                }
                                mainQuest = new MainQuest(readShort, readBoolean, readUTF, readUTF3, readByte3, readUTF2, readByte4, sArr4, sArr5, sArr6, 1);
                            } else if (readByte3 == 2) {
                                mainQuest = new MainQuest(readShort, readBoolean, readUTF, readUTF3, message.reader().readByte(), message.reader().readUTF());
                            }
                            if (mainQuest == null) {
                                break;
                            } else if (readBoolean) {
                                MainQuest.vecQuestDoing_Main.addElement(mainQuest);
                                break;
                            } else {
                                MainQuest.vecQuestDoing_Sub.addElement(mainQuest);
                                break;
                            }
                    }
                }
                if (readByte != 0 && (readByte == 1 || readByte == 2)) {
                    ((MainTabNew) GameCanvas.AllInfo.VecTabScreen.elementAt(4)).init();
                }
            } else {
                message.reader().readByte();
                message.reader().readByte();
            }
            for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
                MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i);
                if (mainObject.typeObject == 2) {
                    if (readByte == 0) {
                        if (mainObject.typeNPC == 1) {
                            mainObject.typeNPC = 0;
                        }
                    } else if (readByte == 1) {
                        if (mainObject.typeNPC == 3) {
                            mainObject.typeNPC = 0;
                        }
                    } else if (readByte == 2 && mainObject.typeNPC == 2) {
                        mainObject.typeNPC = 0;
                    }
                    for (int i2 = 0; i2 < MainQuest.vecQuestList.size(); i2++) {
                        if (((MainQuest) MainQuest.vecQuestList.elementAt(i2)).idNPC_From == mainObject.ID && mainObject.typeNPC != 3) {
                            mainObject.typeNPC = 1;
                        }
                    }
                    for (int i3 = 0; i3 < MainQuest.vecQuestFinish.size(); i3++) {
                        if (((MainQuest) MainQuest.vecQuestFinish.elementAt(i3)).idNPC_To == mainObject.ID) {
                            mainObject.typeNPC = 3;
                        }
                    }
                    for (int i4 = 0; i4 < MainQuest.vecQuestDoing_Main.size(); i4++) {
                        if (((MainQuest) MainQuest.vecQuestDoing_Main.elementAt(i4)).idNPC_To == mainObject.ID && mainObject.typeNPC == 0) {
                            mainObject.typeNPC = 2;
                        }
                    }
                    for (int i5 = 0; i5 < MainQuest.vecQuestDoing_Sub.size(); i5++) {
                        if (((MainQuest) MainQuest.vecQuestDoing_Sub.elementAt(i5)).idNPC_To == mainObject.ID && mainObject.typeNPC == 0) {
                            mainObject.typeNPC = 2;
                        }
                    }
                }
            }
            for (int i6 = 0; i6 < MiniMap.vecNPC_Map.size(); i6++) {
                NPCMini nPCMini = (NPCMini) MiniMap.vecNPC_Map.elementAt(i6);
                if (readByte == 0) {
                    if (nPCMini.type == 1) {
                        nPCMini.type = 0;
                    }
                } else if (readByte == 1) {
                    if (nPCMini.type == 3) {
                        nPCMini.type = 0;
                    }
                } else if (readByte == 2 && nPCMini.type == 2) {
                    nPCMini.type = 0;
                }
                MainObject mainObject2 = MainObject.get_Object(nPCMini.ID, (byte) 2);
                if (mainObject2 != null && !mainObject2.isRemove) {
                    nPCMini.type = mainObject2.typeNPC;
                }
            }
        } catch (Exception e) {
        }
    }

    public void Dynamic_Menu(Message message) {
        try {
            short readShort = message.reader().readShort();
            byte readByte = message.reader().readByte();
            int readByte2 = message.reader().readByte();
            mVector mvector = new mVector("ReadMessenge cmd");
            for (int i = 0; i < readByte2; i++) {
                mvector.addElement(new iCommand(message.reader().readUTF(), (IAction) null));
            }
            String menuText = message.reader().readUTF();
            if (!TrainingController.onDynamicMenu(readShort, readByte, mvector)) {
                GameCanvas.menu2.setinfoDynamic(mvector, 2, readByte, readShort, menuText);
            }
        } catch (Exception e) {
        }
    }

    public void Item_More_Info(Message message) {
        try {
            int readByte = message.reader().readByte();
            TabMySeftNew.mItemInfo = new MainInfoItem[readByte];
            TabMySeftNew.mItemInfoShow = new MainInfoItem[readByte];
            for (int i = 0; i < readByte; i++) {
                MainInfoItem mainInfoItem = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                if (mainInfoItem.id >= 16 && mainInfoItem.id <= 20) {
                    TabMySeftNew.meffskill[mainInfoItem.id - 16] = MainItem.getNopercent(mainInfoItem.value);
                }
                TabMySeftNew.mItemInfo[i] = mainInfoItem;
                TabMySeftNew.mItemInfoShow[i] = mainInfoItem;
            }
        } catch (Exception e) {
        }
    }

    public void Set_XP(Message message) {
        try {
            short readShort = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.phantramLv = message.reader().readShort();
                int readInt = message.reader().readInt();
                if (!GameCanvas.lowGraphic || mainObject == GameScreen.player) {
                    GameScreen.addEffectNum(new StringBuffer().append(readInt).append("xp").toString(), mainObject.x, mainObject.y - mainObject.hOne, 1, readShort);
                }
            }
        } catch (Exception e) {
        }
    }

    public void Level_Up(Message message) {
        try {
            short readShort = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.Lv = message.reader().readByte();
                GameScreen.addEffectKill(42, readShort, (byte) 0, readShort, (byte) 0, 0, 0);
                if (mainObject == GameScreen.player && GameScreen.help.setStep_Next(5, 8)) {
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                    if (Player.isAutoFire == -1) {
                        Player.isAutoFire = (byte) 0;
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void Login_Ok(Message message) {
        try {
            byte[] bArr = new byte[message.reader().readShort()];
            message.reader().read(bArr);
            LoadMap.load_Table_Map(bArr);
            GameScreen.vecTimecountDown.removeAllElements();
            PaintInfoGameScreen.idicon = -1;
        } catch (Exception e) {
        }
    }

    public void ItemDrop(Message message) {
        try {
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            String readUTF = message.reader().readUTF();
            byte b = 0;
            if (readByte == 3 || readByte == 4 || readByte == 7) {
                b = message.reader().readByte();
            }
            short readShort4 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 1);
            if (mainObject != null && !mainObject.isRemove) {
                GameScreen.addPlayer(new Item_Drop(readShort3, readByte, readUTF, mainObject.x, mainObject.y, readShort2, b));
                return;
            }
            MainObject mainObject2 = MainObject.get_Object(readShort4, (byte) 0);
            if (mainObject2 != null) {
                GameScreen.addPlayer(new Item_Drop(readShort3, readByte, readUTF, mainObject2.x, mainObject2.y, readShort2, b));
            }
        } catch (Exception e) {
        }
    }

    public void GetItemMap(Message message) {
        try {
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Item_Object(readShort, readByte);
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            if (readByte == 5 && readShort2 == GameScreen.player.ID) {
                MainQuest.updateQuestGetItem(mainObject.imageId, mainObject.name);
            }
            MainObject mainObject2 = MainObject.get_Object(readShort2, (byte) 0);
            if (mainObject2 != null && !mainObject2.isRemove) {
                if (!GameCanvas.lowGraphic || mainObject == GameScreen.player) {
                    if (mainObject.typeObject == 3 && mainObject.colorName > 0) {
                        GameScreen.addEffectNum(mainObject.name, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), -mainObject.colorName, readShort2);
                    } else {
                        GameScreen.addEffectNum(mainObject.name, mainObject2.x, mainObject2.y - (mainObject2.hOne / 2), 5, readShort2);
                    }
                }
                mainObject.setVx_Vy_Item(mainObject2.x, mainObject2.y - (mainObject2.hOne / 2));
                mainObject.isRunAttack = true;
            } else {
                mainObject.isRemove = true;
            }
        } catch (Exception e) {
        }
    }

    public void get_Item_Tem(Message message) {
        try {
            short readShort = message.reader().readShort();
            String readUTF = message.reader().readUTF();
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            short readShort2 = message.reader().readShort();
            int readByte4 = message.reader().readByte();
            int[] iArr = new int[readByte4];
            byte[] bArr = new byte[readByte4];
            for (int i = 0; i < readByte4; i++) {
                bArr[i] = message.reader().readByte();
                iArr[i] = message.reader().readInt();
            }
            MainTemplateItem.hashItemTem.put(new StringBuffer().append("").append((int) readShort).toString(), new MainTemplateItem(readShort, readUTF, readByte, readByte2, readByte3, readShort2, iArr, bArr));
        } catch (Exception e) {
        }
    }

    public void Skill_List(Message message) {
        try {
            MsgDialog.MaxSkillBuff = (byte) 0;
            mVector mvector = new mVector("ReadMessenge vec14");
            int readByte = message.reader().readByte();
            for (int i = 0; i < readByte; i++) {
                Skill skill = new Skill();
                skill.Id = message.reader().readByte();
                skill.iconId = message.reader().readByte();
                skill.name = message.reader().readUTF();
                skill.typeSkill = message.reader().readByte();
                if (skill.typeSkill == 1) {
                    MsgDialog.MaxSkillBuff = (byte) (MsgDialog.MaxSkillBuff + 1);
                }
                skill.range = message.reader().readShort();
                skill.detail = message.reader().readUTF();
                skill.typeBuff = message.reader().readByte();
                skill.subEff = message.reader().readByte();
                int readByte2 = message.reader().readByte();
                skill.mLvSkill = new LvSkill[readByte2];
                for (int i2 = 0; i2 < readByte2; i2++) {
                    skill.mLvSkill[i2] = new LvSkill();
                    skill.mLvSkill[i2].mpLost = message.reader().readShort();
                    skill.mLvSkill[i2].LvRe = message.reader().readShort();
                    if (i2 == 0) {
                        skill.lvMin = skill.mLvSkill[i2].LvRe;
                    }
                    skill.mLvSkill[i2].delay = message.reader().readInt();
                    skill.mLvSkill[i2].timeBuff = message.reader().readInt();
                    skill.mLvSkill[i2].per_Sub_Eff = message.reader().readByte();
                    skill.mLvSkill[i2].time_Sub_Eff = message.reader().readShort();
                    skill.mLvSkill[i2].plus_Hp = message.reader().readShort();
                    skill.mLvSkill[i2].plus_Mp = message.reader().readShort();
                    int readByte3 = message.reader().readByte();
                    skill.mLvSkill[i2].minfo = new MainInfoItem[readByte3];
                    for (int i3 = 0; i3 < readByte3; i3++) {
                        skill.mLvSkill[i2].minfo[i3] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                    }
                    skill.mLvSkill[i2].nTarget = message.reader().readByte();
                    skill.mLvSkill[i2].range_lan = message.reader().readShort();
                }
                skill.performDur = message.reader().readShort();
                skill.typePaint = message.reader().readByte();
                mvector.addElement(skill);
            }
            TabSkillsNew.vecPaintSkill = CRes.selectionSortSkill(CRes.selectionSortIDSkill(mvector));
            MainListSkill.setKill(GameScreen.player.clazz);
            GameScreen.player.tabskills.setXYSkill(TabSkillsNew.vecPaintSkill);
            GameCanvas.load.isLoadSkill = true;
            MsgDialog.Autobuff = mSystem.new_M_Int(MsgDialog.MaxSkillBuff, 3);
            int i4 = 0;
            for (int i5 = 0; i5 < TabSkillsNew.vecPaintSkill.size(); i5++) {
                Skill skill2 = (Skill) TabSkillsNew.vecPaintSkill.elementAt(i5);
                if (skill2.typeSkill == 1) {
                    MsgDialog.Autobuff[i4][0] = skill2.Id;
                    MsgDialog.Autobuff[i4][1] = 0;
                    MsgDialog.Autobuff[i4][2] = i5;
                    i4++;
                }
            }
        } catch (Exception e) {
        }
    }

    public void use_Potion(Message message) {
        Item itemInventory;
        try {
            TabScreenNew.timeRepaint = 5;
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort, readByte);
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            if (readShort2 != -1 && mainObject == GameScreen.player && (itemInventory = Item.getItemInventory(4, readShort2)) != null) {
                itemInventory.numPotion--;
                if (itemInventory.numPotion <= 0) {
                    Item.VecInvetoryPlayer.removeElement(itemInventory);
                }
            }
            byte readByte2 = message.reader().readByte();
            if (readByte2 == 0) {
                mainObject.maxHp = message.reader().readInt();
                mainObject.hp = message.reader().readInt();
                int readInt = message.reader().readInt();
                if (mainObject.hp > 0) {
                    if (mainObject.Action == 4) {
                        mainObject.resetAction();
                        mainObject.Action = 0;
                        mainObject.vecBuff.removeAllElements();
                        if (mainObject == GameScreen.player) {
                            GlobalService.gI().player_move((short) GameScreen.player.x, (short) GameScreen.player.y);
                        }
                    }
                } else {
                    mainObject.hp = 0;
                    mainObject.mp = 0;
                    if (mainObject.Action != 4) {
                        if (readByte == 1) {
                            MainObject.startDeadFly((MainMonster) mainObject, GameScreen.player.ID, CRes.random(3));
                        } else {
                            mainObject.resetAction();
                            mainObject.Action = 4;
                            mainObject.timedie = GameCanvas.timeNow;
                            mainObject.f = 0;
                            GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
                        }
                    }
                }
                if (!GameCanvas.lowGraphic || mainObject == GameScreen.player) {
                    if (readInt > 0) {
                        GameScreen.addEffectNum(new StringBuffer().append("+").append(readInt).toString(), mainObject.x, mainObject.y - (mainObject.hOne / 2), 3, readShort);
                    } else if (readInt < 0) {
                        GameScreen.addEffectNum(new StringBuffer().append("").append(readInt).toString(), mainObject.x, mainObject.y - (mainObject.hOne / 2), 3, readShort);
                    }
                }
            } else if (readByte2 == 1) {
                mainObject.maxMp = message.reader().readInt();
                mainObject.mp = message.reader().readInt();
                int readInt2 = message.reader().readInt();
                if (!GameCanvas.lowGraphic || mainObject == GameScreen.player) {
                    if (readInt2 > 0) {
                        GameScreen.addEffectNum(new StringBuffer().append("+").append(readInt2).toString(), mainObject.x, mainObject.y - (mainObject.hOne / 2), 4, readShort);
                    } else if (readInt2 < 0) {
                        GameScreen.addEffectNum(new StringBuffer().append("").append(readInt2).toString(), mainObject.x, mainObject.y - (mainObject.hOne / 2), 3, readShort);
                    }
                }
            } else if (readByte2 == 2) {
                mainObject.maxHp = message.reader().readInt();
                mainObject.hp = message.reader().readInt();
                int readInt3 = message.reader().readInt();
                if (!GameCanvas.lowGraphic) {
                    if (readInt3 > 0) {
                        mSystem.outz("dzo day ---");
                        GameScreen.addEffectNum(new StringBuffer().append("+").append(readInt3).toString(), mainObject.x, mainObject.y - (mainObject.hOne / 2), 3, readShort);
                    } else if (readInt3 < 0) {
                        mSystem.outz("hoac dzo day ---");
                        GameScreen.addEffectNum(new StringBuffer().append("").append(readInt3).toString(), mainObject.x, mainObject.y - (mainObject.hOne / 2), 6, readShort);
                    }
                }
            } else if (readByte2 == 3) {
                mainObject.hp = message.reader().readInt();
                GameScreen.addEffectNum(new StringBuffer().append("").append(message.reader().readInt()).toString(), mainObject.x, mainObject.y - (mainObject.hOne / 2), 9, readShort);
                GameScreen.addEffectEndKill(0, mainObject.x, (mainObject.y + ((mainObject.vy * 2) / ObjectData.MONSTER)) - 5);
                GameScreen.addEffectEndKill(9, mainObject.x, mainObject.y + ((mainObject.vy * 2) / ObjectData.MONSTER) + 5);
                GameScreen.addEffectEndKill(4, mainObject.x, mainObject.y);
            }
            if (Player.party != null) {
                Player.party.setPos(mainObject.name, mainObject.x, mainObject.y, mainObject.hp, mainObject.maxHp);
            }
        } catch (Exception e) {
        }
    }

    public void chatPopup(Message message) {
        try {
            MainObject mainObject = MainObject.get_Object(message.reader().readShort(), message.reader().readByte());
            if (mainObject != null && !mainObject.isRemove) {
                String readUTF = message.reader().readUTF();
                if (readUTF == null || readUTF.trim().length() == 0) {
                    return;
                }
                mainObject.strChatPopup = readUTF;
                GameCanvas.msgchat.addNewChat(T.tinden, new StringBuffer().append(mainObject.name).append(": ").toString(), readUTF, (byte) 1, false);
            }
        } catch (Exception e) {
        }
    }

    public void chatTab(Message message) {
        try {
            String readUTF = message.reader().readUTF();
            String readUTF2 = message.reader().readUTF();
            if (readUTF2 == null || readUTF2.trim().length() == 0) {
                return;
            }
            if (readUTF.compareTo(T.tinden) == 0) {
                GameCanvas.msgchat.addNewChat(readUTF, new StringBuffer().append(T.thongbao).append(": ").toString(), readUTF2, (byte) 1, false);
            } else if (readUTF.compareTo(T.tabThuLinh) == 0) {
                GameCanvas.msgchat.addNewChat(readUTF, "", readUTF2, (byte) 1, false);
            } else if (readUTF.compareTo(T.tabBangHoi) == 0) {
                GameCanvas.msgchat.addNewChat(readUTF, "", readUTF2, (byte) 0, false);
            } else if (readUTF.compareTo(T.party) == 0) {
                GameCanvas.msgchat.addNewChat(readUTF, "", readUTF2, (byte) 0, false);
            } else {
                GameCanvas.msgchat.addNewChat(readUTF, new StringBuffer().append(readUTF).append(": ").toString(), readUTF2, (byte) 0, false);
            }
        } catch (Exception e) {
        }
    }

    public void Friend(Message message) {
        try {
            mSystem.outz("friend list");
            String str = T.listFriend;
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                GameCanvas.mevent.addEvent(message.reader().readUTF(), (byte) 1, T.loimoikb, 0);
            } else if (readByte == 4 || readByte == 1) {
                if (readByte == 4) {
                    str = message.reader().readUTF();
                    message.reader().readByte();
                }
                int i = 1;
                if (readByte == 4) {
                    i = message.reader().readUnsignedByte();
                }
                mSystem.outz(new StringBuffer().append("friend sizelist= ").append(i).append(" type=").append((int) readByte).toString());
                mVector mvector = new mVector("ReadMessenge vec6");
                for (int i2 = 0; i2 < i; i2++) {
                    mSystem.outz(new StringBuffer().append("friend list i=").append(i2).toString());
                    String readUTF = message.reader().readUTF();
                    mSystem.outz(new StringBuffer().append("friendname list i=").append(readUTF).toString());
                    byte readByte2 = message.reader().readByte();
                    byte readByte3 = message.reader().readByte();
                    byte readByte4 = message.reader().readByte();
                    mSystem.outz(new StringBuffer().append("friendhair list i=").append((int) readByte4).toString());
                    short readShort = message.reader().readShort();
                    int readByte5 = message.reader().readByte();
                    mSystem.outz(new StringBuffer().append("frienditemsize list i=").append(readByte5).toString());
                    byte[] bArr = new byte[12];
                    mSystem.outz(new StringBuffer().append("friend11 list i=").append(i2).toString());
                    for (int i3 = 0; i3 < bArr.length; i3++) {
                        bArr[i3] = -1;
                    }
                    for (int i4 = 0; i4 < readByte5; i4++) {
                        bArr[message.reader().readByte()] = message.reader().readByte();
                    }
                    mSystem.outz(new StringBuffer().append("friend22 list i=").append(i2).toString());
                    byte readByte6 = message.reader().readByte();
                    MainObject mainObject = new MainObject(0, (byte) 0, readUTF, 0, 0);
                    short readShort2 = message.reader().readShort();
                    if (readShort2 >= 0) {
                        mainObject.myClan = new MainClan(0, readShort2, message.reader().readUTF(), message.reader().readByte());
                    }
                    mSystem.outz(new StringBuffer().append("friend33 list i=").append(i2).toString());
                    mainObject.setInfo(readByte2, readByte3, readByte4);
                    mainObject.typeOnline = readByte6;
                    mainObject.Lv = readShort;
                    mainObject.setWearingListChar(bArr);
                    mainObject.infoObject = new StringBuffer().append(T.level).append((int) readShort).toString();
                    mSystem.outz(new StringBuffer().append("friend44 list i=").append(i2).toString());
                    if (readByte == 1) {
                        GameCanvas.addInfoChar(new StringBuffer().append(mainObject.name).append(T.chapnhanketban).toString());
                        List_Server.vecMyFriend.addElement(mainObject);
                        if (List_Server.gI().typeList == 0) {
                            List_Server.gI().setMinMax();
                            List_Server.gI().setXmove();
                            List_Server.gI().setCmd();
                        }
                    } else {
                        mvector.addElement(mainObject);
                    }
                    mSystem.outz(new StringBuffer().append("ok i roi i=").append(i2).toString());
                }
                if (readByte == 4) {
                    List_Server.vecMyFriend.removeAllElements();
                    List_Server.gI().typeList = (byte) 0;
                    List_Server.vecMyFriend = mvector;
                    List_Server.gI().vecListServer = mvector;
                    List_Server.gI().setSize((byte) 0);
                    List_Server.gI().updateList();
                    List_Server.gI().setMinMax();
                    List_Server.gI().setXmove();
                    List_Server.gI().setCmd();
                    List_Server.gI().nameList = str;
                    List_Server.gI().page = (byte) 99;
                    List_Server.isLoadFriend = true;
                    if (GameCanvas.currentScreen != List_Server.gI()) {
                        if (GameCanvas.currentScreen.lastScreen != List_Server.gI()) {
                            List_Server.gI().Show(GameCanvas.currentScreen);
                        } else {
                            List_Server.gI().Show(GameCanvas.game);
                        }
                    } else {
                        GameCanvas.end_Dialog();
                    }
                    mSystem.outz("ok load");
                }
                GameCanvas.end_Dialog();
            }
        } catch (Exception e) {
        }
    }

    public void lastlogout(Message message) {
        try {
            GameCanvas.timenextLogin = mSystem.currentTimeMillis() + (message.reader().readByte() * ObjectData.MONSTER);
            GameScreen.player.resetAction();
            Session_ME.gI().close();
            GameScreen.player = new Player(0, (byte) 0, "unname", 0, 0);
        } catch (Exception e) {
        }
    }

    public void Login_Fail(Message message) {
        byte b;
        try {
            GameCanvas.start_Ok_Dialog(message.reader().readUTF());
            try {
                b = message.reader().readByte();
            } catch (Exception e) {
                b = 0;
            }
            if (b == 0) {
                GlobalLogicHandler.isMelogin = false;
            }
            if (b == 1) {
                GlobalLogicHandler.isMelogin = true;
            }
        } catch (Exception e2) {
        }
    }

    public void Register(Message message) {
        try {
            boolean readBoolean = message.reader().readBoolean();
            String readUTF = message.reader().readUTF();
            if (readBoolean) {
                GameCanvas.login.section = 0;
                GameCanvas.login.setScreen();
            }
            GameCanvas.start_Ok_Dialog(readUTF);
        } catch (Exception e) {
        }
    }

    public void Party(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                Player.party = new MainParty(GameScreen.player.name, GameScreen.player.Lv, GameCanvas.loadmap.idMap, LoadMap.getAreaPaint());
                GameCanvas.start_Ok_Dialog(T.vuataonhom);
            } else if (readByte == 1) {
                this.nameParty = message.reader().readUTF();
                GameCanvas.mevent.addEvent(this.nameParty, (byte) 2, T.moivaoParty, 0);
            } else if (readByte == 2) {
                MainParty mainParty = null;
                int readByte2 = message.reader().readByte();
                for (int i = 0; i < readByte2; i++) {
                    if (i == false) {
                        mainParty = new MainParty(message.reader().readUTF(), message.reader().readShort(), message.reader().readUnsignedByte(), message.reader().readByte());
                    } else {
                        mainParty.addObjParty(message.reader().readUTF(), message.reader().readShort(), message.reader().readUnsignedByte(), message.reader().readByte());
                    }
                }
                Player.party = mainParty;
                MsgDialog.maxSizeParty = -1;
            } else if (readByte == 3) {
                GameCanvas.start_Ok_Dialog(T.moikhoinhom);
                Player.party = null;
            } else if (readByte == 4) {
                GameCanvas.start_Ok_Dialog(T.giaitannhom);
                Player.party = null;
            } else if (readByte == 5) {
                GameCanvas.start_Ok_Dialog(T.roikhoinhom);
                Player.party = null;
            }
            if (Player.party != null) {
                Player.party.setIsParty();
            }
        } catch (Exception e) {
        }
    }

    public void Buy_Sell(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                this.nameBuySell = message.reader().readUTF();
                GameCanvas.mevent.addEvent(this.nameBuySell, (byte) 3, T.loimoigiaodich, 0);
            } else if (readByte == 1) {
                String readUTF = message.reader().readUTF();
                GameCanvas.buy_sell = new Buy_Sell_Screen();
                GameCanvas.buy_sell.setinfoBuySell(readUTF);
                GameCanvas.buy_sell.Show(GameCanvas.currentScreen);
            } else if (readByte == 2 || readByte == 3) {
                byte readByte2 = message.reader().readByte();
                byte readByte3 = message.reader().readByte();
                short readShort = message.reader().readShort();
                short readShort2 = message.reader().readShort();
                short s = 1;
                byte b = 0;
                byte b2 = 0;
                short s2 = 0;
                byte b3 = 0;
                String str = "";
                String str2 = null;
                MainInfoItem[] mainInfoItemArr = null;
                if (readByte3 == 4) {
                    s = message.reader().readShort();
                    MainTemplateItem mainTemplateItem = (MainTemplateItem) MainTemplateItem.hashPotionTem.get(new StringBuffer().append("").append((int) readShort).toString());
                    str2 = mainTemplateItem.contentPotion;
                    str = mainTemplateItem.name;
                } else if (readByte3 == 3) {
                    str = message.reader().readUTF();
                    b = message.reader().readByte();
                    b2 = message.reader().readByte();
                    s2 = message.reader().readShort();
                    b3 = message.reader().readByte();
                    int readByte4 = message.reader().readByte();
                    mainInfoItemArr = new MainInfoItem[readByte4];
                    for (int i = 0; i < readByte4; i++) {
                        mainInfoItemArr[i] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                    }
                } else if (readByte3 == 7) {
                    s = message.reader().readShort();
                    MainItem material = Item.getMaterial(readShort);
                    if (material != null) {
                        str2 = material.content;
                        str = material.itemName;
                    } else {
                        str2 = "";
                        str = "";
                    }
                }
                Buy_Sell_Screen.setmItemOther(new MainItem(readShort, str, s, readShort2, readByte3, str2, mainInfoItemArr, b, b2, s2, b3, (byte) 0, (byte) 0), readByte, readByte2);
            } else if (readByte == 4) {
                GameCanvas.buy_sell.typeLock = message.reader().readByte();
                if (GameCanvas.buy_sell.typeLock == 1) {
                    GameCanvas.buy_sell.center = null;
                    GameCanvas.buy_sell.left = null;
                }
                if (GameCanvas.buy_sell.typeLock == 2) {
                    GameCanvas.buy_sell.center = null;
                    GameCanvas.buy_sell.left = GameCanvas.buy_sell.cmdGiaodich;
                    GameCanvas.buy_sell.setPosCmd();
                }
            } else if (readByte == 5) {
                byte readByte5 = message.reader().readByte();
                if (readByte5 == 0) {
                    GameCanvas.buy_sell.left = null;
                    GameCanvas.buy_sell.right = null;
                    GameCanvas.buy_sell.center = new iCommand(T.cancel, 8);
                    GameCanvas.buy_sell.setPosCmd();
                    GameCanvas.buy_sell.typeActionBuySell = 0;
                    GameCanvas.start_Ok_Dialog(new StringBuffer().append(T.xinchogiaodich).append(GameCanvas.buy_sell.nameBuy).append(T.xacnhangiaodich).toString());
                } else if (readByte5 == 1) {
                    GameCanvas.buy_sell.typeActionBuySell = 1;
                } else if (readByte5 == 2) {
                    if (GameCanvas.buy_sell.lastScreen.lastScreen != null) {
                        GameCanvas.buy_sell.lastScreen.Show(GameCanvas.buy_sell.lastScreen.lastScreen);
                    } else {
                        GameCanvas.buy_sell.lastScreen.Show();
                    }
                    GameCanvas.buy_sell = null;
                    GameCanvas.start_Ok_Dialog(T.giaodichthanhcong);
                }
            } else if (readByte == 6) {
                if (GameCanvas.buy_sell.lastScreen.lastScreen != null) {
                    GameCanvas.buy_sell.lastScreen.Show(GameCanvas.buy_sell.lastScreen.lastScreen);
                } else {
                    GameCanvas.buy_sell.lastScreen.Show();
                }
                GameCanvas.buy_sell = null;
                GameCanvas.start_Ok_Dialog(T.huygiaodich);
            } else if (readByte == 7) {
                GameCanvas.buy_sell.moneyBuySell[1] = message.reader().readInt();
            } else if (readByte == 8) {
                GameCanvas.buy_sell.moneyBuySell[0] = message.reader().readInt();
            } else if (readByte == 9) {
                GameCanvas.buy_sell.strchat[1] = message.reader().readUTF();
            }
        } catch (Exception e) {
        }
    }

    public void Buff(Message message) {
        MainObject mainObject;
        MainBuff buff;
        try {
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            short readShort = message.reader().readShort();
            byte readByte3 = message.reader().readByte();
            int readInt = message.reader().readInt();
            short readShort2 = message.reader().readShort();
            byte readByte4 = message.reader().readByte();
            if (readByte2 == 6) {
                mainObject = MainObject.get_Object(readShort, (byte) 9);
            } else if (readByte2 == 4) {
                mainObject = MainObject.get_Object(readShort, (byte) 1);
            } else {
                mainObject = MainObject.get_Object(readShort, (byte) 0);
            }
            MainObject mainObject2 = MainObject.get_Object(readShort2, readByte4);
            if (mainObject != null && !mainObject.isRemove && mainObject != GameScreen.player && readByte == 0 && mainObject.Action != 4 && mainObject.Action != 2) {
                if (mainObject2 != null) {
                    if (readByte2 == 4 && mainObject2 == mainObject && !mainObject2.isRemove) {
                        GameScreen.addEffectKillSubTime(MainListSkill.mSkillAllClasses[readByte2][readByte3], mainObject.ID, mainObject.typeObject, mainObject.ID, mainObject.typeObject, 0, mainObject.hp, (byte) MainListSkill.mBuffAllClasses[readByte2][readByte3], 850);
                    }
                } else {
                    mVector mvector = new mVector("ReadMessenge vec");
                    mvector.addElement(new Object_Effect_Skill((short) mainObject.ID, mainObject.typeObject));
                    if (readByte2 == 6) {
                        ((Pet) mainObject).setState((byte) 2);
                    }
                    mainObject.ListKillNow.setFireSkill(mainObject, mvector, readByte3, readByte2);
                }
            }
            if (mainObject2 != null && !mainObject2.isRemove) {
                mainObject2.addBuff(MainListSkill.mBuffAllClasses[readByte2][readByte3], readInt, 0);
                if (mainObject2 == GameScreen.player && (buff = MainBuff.getBuff(MainListSkill.mBuffAllClasses[readByte2][readByte3], 0)) != null) {
                    buff.idIcon = message.reader().readByte();
                    int readByte5 = message.reader().readByte();
                    buff.minfotam = new MainInfoItem[readByte5];
                    MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte5];
                    for (int i = 0; i < mainInfoItemArr.length; i++) {
                        mainInfoItemArr[i] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                    }
                    buff.minfotam = mainInfoItemArr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firePK(Message message) {
        try {
            Fire_Object(message, (byte) 0, (byte) 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void diePlayer(Message message) {
        try {
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            byte readByte = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
            MainObject mainObject2 = MainObject.get_Object(readShort2, readByte);
            if (mainObject2 != null && !mainObject2.isRemove && readByte == 0) {
                mainObject2.pointPk = readShort3;
            }
            if (mainObject == null || mainObject.isRemove || mainObject.Action == 4) {
                return;
            }
            mainObject.hp = 0;
            mainObject.resetAction();
            mainObject.Action = 4;
            if (GameScreen.player == mainObject) {
                TrainingController.onDeath(GameScreen.player);
                TradeController.onDeath();
            }
            mainObject.timedie = GameCanvas.timeNow;
            GameScreen.addEffectEndKill(11, mainObject.x, mainObject.y);
            if (GameScreen.player == mainObject && !GameScreen.infoGame.isMapChienTruong(GameCanvas.loadmap.idMap)) {
                if (LoadMap.typeMap == 1) {
                    GameScreen.gI().center = Player.cmdVeLang;
                    Player.cmdVeLang.setPos(GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, Player.cmdVeLang.caption);
                } else if (LoadMap.typeMap == 2) {
                    GameScreen.gI().center = Player.cmdRevice;
                    Player.cmdRevice.setPos(GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, Player.cmdRevice.caption);
                } else if (!GameScreen.infoGame.isMapThachdau()) {
                    GameScreen.gI().left = Player.cmdRevice;
                    GameScreen.gI().right = Player.cmdVeLang;
                    Player.cmdRevice.setPos(iCommand.wButtonCmd / 2, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, Player.cmdRevice.caption);
                    Player.cmdVeLang.setPos(GameCanvas.w - (iCommand.wButtonCmd / 2), (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 2, null, Player.cmdVeLang.caption);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pk(Message message) {
        try {
            short readShort = message.reader().readShort();
            byte readByte = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.typePk = readByte;
                if (mainObject == GameScreen.player) {
                    GameScreen.player.pointPk = message.reader().readShort();
                    String stringBuffer = new StringBuffer().append(T.off).append(" ").append(T.dosat).toString();
                    if (GameScreen.player.typePk != 0) {
                        stringBuffer = new StringBuffer().append(T.on).append(" ").append(T.dosat).toString();
                    }
                    GameScreen.gI().cmdSetDoSat.caption = stringBuffer;
                    if (readByte > 0) {
                        GlobalService.gI().Thach_Dau((byte) 2, "");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void Fire_Object(Message message, byte b, byte b2) {
        short readShort;
        MainObject mainObject;
        int addEffKillPlus;
        try {
            if (!LoadMapScreen.isNextMap || (mainObject = MainObject.get_Object((readShort = message.reader().readShort()), b)) == null || mainObject.isRemove) {
                return;
            }
            byte readByte = message.reader().readByte();
            int readByte2 = message.reader().readByte();
            if (readByte2 <= 0) {
                return;
            }
            mVector mvector = new mVector("ReadMessenge vec5");
            for (int i = 0; i < readByte2; i++) {
                short readShort2 = message.reader().readShort();
                Object_Effect_Skill object_Effect_Skill = new Object_Effect_Skill(readShort2, b2);
                int readInt = message.reader().readInt();
                object_Effect_Skill.setHP(readInt, message.reader().readInt());
                if (readShort2 == GameScreen.player.ID) {
                    GameScreen.player.setPainthit((byte) 4, (readInt / GameScreen.player.maxHp) * 100 > 20);
                }
                int readByte3 = message.reader().readByte();
                object_Effect_Skill.mEffTypePlus = new int[readByte3];
                object_Effect_Skill.mEff_HP_Plus = new int[readByte3];
                for (int i2 = 0; i2 < readByte3; i2++) {
                    object_Effect_Skill.mEffTypePlus[i2] = message.reader().readByte();
                    object_Effect_Skill.mEff_HP_Plus[i2] = message.reader().readInt();
                }
                MainObject mainObject2 = MainObject.get_Object(object_Effect_Skill.ID, b2);
                if (mainObject2 != null && !mainObject2.isRemove) {
                    mvector.addElement(object_Effect_Skill);
                }
            }
            int readInt2 = message.reader().readInt();
            int readInt3 = message.reader().readInt();
            byte readByte4 = message.reader().readByte();
            int readInt4 = message.reader().readInt();
            if (readShort == GameScreen.player.ID) {
                for (int i3 = 0; i3 < mvector.size(); i3++) {
                    Object_Effect_Skill object_Effect_Skill2 = (Object_Effect_Skill) mvector.elementAt(i3);
                    MainObject mainObject3 = MainObject.get_Object(object_Effect_Skill2.ID, b2);
                    if (mainObject3 != null && !mainObject3.isRemove) {
                        int i4 = 2;
                        if (mainObject != null && !mainObject.isRemove && (addEffKillPlus = EffectSkill.setAddEffKillPlus(object_Effect_Skill2.mEffTypePlus, mainObject, mainObject3, object_Effect_Skill2.mEff_HP_Plus)) != -1) {
                            i4 = addEffKillPlus;
                        }
                        if (mainObject3.ID != GameScreen.player.ID) {
                            if (object_Effect_Skill2.hpShow == 0) {
                                GameScreen.addEffectNum(T.hut, mainObject3.x, mainObject3.y - mainObject3.hOne, 6, mainObject3.ID);
                            } else {
                                GameScreen.addEffectNum(new StringBuffer().append("-").append(object_Effect_Skill2.hpShow).toString(), mainObject3.x, mainObject3.y - mainObject3.hOne, i4, mainObject3.ID);
                            }
                            if (readInt4 > 0) {
                                GameScreen.addEffectNum(new StringBuffer().append("-").append(readInt4).toString(), mainObject3.x, mainObject3.y - mainObject3.hOne, readByte4, mainObject3.ID);
                            }
                        }
                        mainObject3.hp = object_Effect_Skill2.hpLast;
                        if (mainObject3.typeObject == 1 && mainObject3.hp <= 0) {
                            MainObject.startDeadFly((MainMonster) mainObject3, readShort, CRes.random(3));
                            if (mainObject3.timeReveice >= 0) {
                                MainQuest.updateQuestKillMonster(mainObject3.ID);
                            }
                        }
                    }
                }
            } else if (mainObject != null && !mainObject.isRemove && mvector.size() > 0) {
                mainObject.posTransRoad = null;
                mainObject.vecObjFocusSkill = mvector;
                mainObject.ListKillNow.setFireSkill(mainObject, mvector, readByte, (byte) -1);
            }
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.hp = readInt2;
                mainObject.mp = readInt3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0009. Please report as an issue. */
    public void onInfoMount(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    String readUTF = message.reader().readUTF();
                    short readShort = message.reader().readShort();
                    String readUTF2 = message.reader().readUTF();
                    int readByte = message.reader().readByte();
                    byte[] bArr = new byte[readByte];
                    for (int i = 0; i < bArr.length; i++) {
                        bArr[i] = -1;
                    }
                    infoShow.vecEquipShow.clear();
                    for (int i2 = 0; i2 < readByte; i2++) {
                        byte readByte2 = message.reader().readByte();
                        if (readByte2 > -1) {
                            String readUTF3 = message.reader().readUTF();
                            byte readByte3 = message.reader().readByte();
                            byte readByte4 = message.reader().readByte();
                            short readShort2 = message.reader().readShort();
                            byte readByte5 = message.reader().readByte();
                            byte readByte6 = message.reader().readByte();
                            short readShort3 = message.reader().readShort();
                            byte readByte7 = message.reader().readByte();
                            bArr[i2] = readByte5;
                            int readByte8 = message.reader().readByte();
                            MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte8];
                            for (int i3 = 0; i3 < readByte8; i3++) {
                                mainInfoItemArr[i3] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                            }
                            byte readByte9 = message.reader().readByte();
                            MainItem MainItem_Item = MainItem.MainItem_Item(readByte2, readUTF3, readShort2, readByte6, readByte7, readByte3, 3, mainInfoItemArr, readByte4, false, (short) -1, 0L, readShort3, (byte) 0, (byte) 0, 0, (byte) 0, (byte) 0);
                            MainItem_Item.set_can_Sell(readByte9);
                            infoShow.vecEquipShow.put(new StringBuffer().append("").append((int) readByte2).toString(), MainItem_Item);
                        } else {
                            bArr[i2] = -1;
                        }
                    }
                    byte readByte10 = message.reader().readByte();
                    int readByte11 = message.reader().readByte();
                    String[] strArr = new String[readByte11];
                    for (int i4 = 0; i4 < readByte11; i4++) {
                        strArr[i4] = message.reader().readUTF();
                    }
                    if (GameCanvas.showMount == null) {
                        GameCanvas.showMount = new infoShow();
                    }
                    GameCanvas.showMount.init(readByte10);
                    GameCanvas.showMount.name = readUTF;
                    GameCanvas.showMount.lv = readShort;
                    GameCanvas.showMount.exp = readUTF2;
                    GameCanvas.showMount.info = strArr;
                    GameCanvas.showMount.Show(GameCanvas.currentScreen);
                default:
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void other_player_info(Message message) {
        try {
            MainObject mainObject = new MainObject();
            mainObject.ID = message.reader().readShort();
            mainObject.name = message.reader().readUTF();
            mainObject.clazz = message.reader().readByte();
            mainObject.head = message.reader().readByte();
            mainObject.eye = message.reader().readByte();
            mainObject.hair = message.reader().readByte();
            mainObject.Lv = message.reader().readShort();
            mainObject.hp = message.reader().readInt();
            mainObject.maxHp = message.reader().readInt();
            mainObject.typePk = message.reader().readByte();
            mainObject.pointPk = message.reader().readShort();
            int readByte = message.reader().readByte();
            byte[] bArr = new byte[readByte];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = -1;
            }
            Info_Other_Player.vecEquipShow.clear();
            for (int i2 = 0; i2 < readByte; i2++) {
                byte readByte2 = message.reader().readByte();
                if (readByte2 > -1) {
                    String readUTF = message.reader().readUTF();
                    byte readByte3 = message.reader().readByte();
                    byte readByte4 = message.reader().readByte();
                    short readShort = message.reader().readShort();
                    byte readByte5 = message.reader().readByte();
                    byte readByte6 = message.reader().readByte();
                    short readShort2 = message.reader().readShort();
                    byte readByte7 = message.reader().readByte();
                    bArr[i2] = readByte5;
                    int readByte8 = message.reader().readByte();
                    MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte8];
                    for (int i3 = 0; i3 < readByte8; i3++) {
                        mainInfoItemArr[i3] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                    }
                    byte readByte9 = message.reader().readByte();
                    MainItem MainItem_Item = MainItem.MainItem_Item(readByte2, readUTF, readShort, readByte6, readByte7, readByte3, 3, mainInfoItemArr, readByte4, false, (short) -1, 0L, readShort2, (byte) 0, (byte) 0, 0, (byte) 0, (byte) 0);
                    MainItem_Item.set_can_Sell(readByte9);
                    Info_Other_Player.vecEquipShow.put(new StringBuffer().append("").append((int) readByte2).toString(), MainItem_Item);
                } else {
                    bArr[i2] = -1;
                }
            }
            mainObject.setWearingEquip(bArr);
            short readShort3 = message.reader().readShort();
            if (readShort3 >= 0) {
                mainObject.myClan = new MainClan(0, readShort3, message.reader().readUTF(), message.reader().readByte());
                mainObject.myClan.name = message.reader().readUTF();
            }
            byte readByte10 = message.reader().readByte();
            if (readByte10 > -1) {
                String readUTF2 = message.reader().readUTF();
                byte readByte11 = message.reader().readByte();
                short readShort4 = message.reader().readShort();
                short readShort5 = message.reader().readShort();
                byte readByte12 = message.reader().readByte();
                byte readByte13 = message.reader().readByte();
                byte readByte14 = message.reader().readByte();
                byte readByte15 = message.reader().readByte();
                int readInt = message.reader().readInt();
                short readShort6 = message.reader().readShort();
                short readShort7 = message.reader().readShort();
                short readShort8 = message.reader().readShort();
                short readShort9 = message.reader().readShort();
                short readShort10 = message.reader().readShort();
                short readShort11 = message.reader().readShort();
                short readShort12 = message.reader().readShort();
                int readByte16 = message.reader().readByte();
                MainInfoItem[] mainInfoItemArr2 = new MainInfoItem[readByte16];
                for (int i4 = 0; i4 < readByte16; i4++) {
                    mainInfoItemArr2[i4] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt(), message.reader().readInt());
                }
                PetItem petItem = new PetItem(readByte10, readUTF2, readByte12, readByte15, readByte11, 9, mainInfoItemArr2, 14, readShort4, readByte12, readByte14, readByte13);
                petItem.setInfoPet(readInt, readShort6, readShort8, readShort9, readShort10, readShort11, readShort7, readShort12, readShort5);
                Info_Other_Player.vecEquipShow.put(new StringBuffer().append("").append((int) readByte10).toString(), petItem);
                bArr[5] = readByte12;
                Info_Other_Player.showPet = petItem;
            } else {
                Info_Other_Player.showPet = null;
            }
            Info_Other_Player.showObject = mainObject;
            byte readByte17 = message.reader().readByte();
            if (GameCanvas.info_other_player == null) {
                GameCanvas.info_other_player = new Info_Other_Player();
            }
            GameCanvas.info_other_player.init(readByte17);
            GameCanvas.info_other_player.Show(GameCanvas.currentScreen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eff_plus_time(Message message) {
        try {
            MainObject mainObject = MainObject.get_Object(message.reader().readShort(), message.reader().readByte());
            if (mainObject != null && !mainObject.isRemove) {
                MainBuff.setEffHead(message.reader().readByte(), message.reader().readByte(), mainObject);
            }
        } catch (Exception e) {
        }
    }

    public void changeArea(Message message) {
        try {
            mSystem.outz("nhan change Area");
            LoadMapScreen.isNextMap = false;
            if (GameCanvas.currentScreen != GameCanvas.load) {
                GameCanvas.load.Show();
            }
            GameScreen.RemoveLoadMap();
            LoadMapScreen.isNextMap = true;
            LoadMap.Area = message.reader().readByte();
            ChildLocationDiagnostics.onAreaChanged(LoadMap.Area);
            GameScreen.player.x = message.reader().readShort() * 24;
            GameScreen.player.y = message.reader().readShort() * 24;
            GameScreen.player.resetMove();
            GameScreen.player.posTransRoad = null;
            GameScreen.player.resetAction();
            if (GameScreen.pet != null) {
                GameScreen.pet.x = GameScreen.player.x;
                GameScreen.pet.y = GameScreen.player.y;
                GameScreen.pet.clearWayPoints();
            }
            GameCanvas.end_Dialog();
            GameCanvas.clearKeyHold();
            GameCanvas.load.isTele = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update_Status_Area(Message message) {
        try {
            LoadMap.MaxArea = message.reader().readByte();
            LoadMap.mStatusArea = new int[LoadMap.MaxArea];
            String[] strArr = new String[LoadMap.MaxArea];
            for (int i = 0; i < LoadMap.MaxArea; i++) {
                LoadMap.mStatusArea[i] = message.reader().readByte();
                byte readByte = message.reader().readByte();
                if (readByte != 0) {
                    LoadMap.mStatusArea[i] = readByte;
                }
            }
            for (int i2 = 0; i2 < LoadMap.MaxArea; i2++) {
                strArr[i2] = message.reader().readUTF();
            }
            GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
            GameScreen.isReArea = false;
            mVector mvector = new mVector("ReadMessenge menu4");
            for (int i3 = 0; i3 < LoadMap.MaxArea; i3++) {
                iCommand icommand = new iCommand(strArr[i3], 13, i3, GameScreen.gI());
                icommand.setFraCaption(PaintInfoGameScreen.fraStatusArea, 1, LoadMap.mStatusArea[i3]);
                mvector.addElement(icommand);
            }
            GameCanvas.menu2.startAt(mvector, 2, T.khuvuc, false, null);
            GameCanvas.end_Dialog();
        } catch (Exception e) {
        }
    }

    public void remove_Actor(Message message) {
        try {
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            MainObject mainObject = MainObject.get_Item_Object(readShort, readByte);
            if (mainObject != null && !mainObject.isRemove) {
                if (readByte == 1) {
                    if (mainObject.typeBoss == 4) {
                        if (message.reader().readByte() == 0) {
                            mainObject.isMonPhoBangDie = true;
                            mainObject.vMax = 6;
                        } else {
                            mainObject.isStop = true;
                        }
                    } else if (mainObject.typeBoss == 3) {
                        if (message.reader().readByte() == 0) {
                            mainObject.isMonPhoBangDie = true;
                            mainObject.vMax = 6;
                        } else {
                            mainObject.isStop = true;
                        }
                    } else if (mainObject.typeBoss == 2) {
                        if (message.reader().readByte() == 1) {
                            GameScreen.addEffectKill(80, mainObject.x, mainObject.y - 70, 200, readShort, (byte) 1);
                            mainObject.isStop = true;
                        } else {
                            mainObject.isStop = true;
                            MainObject.imgCapchar = null;
                        }
                    } else {
                        mainObject.isStop = true;
                    }
                } else if (readByte == 2) {
                    if (message.reader().readByte() == 1) {
                        mainObject.isMonPhoBangDie = true;
                        LoadMap.isShowEffAuto = LoadMap.EFF_PHOBANG_END;
                    } else {
                        GameScreen.addEffectEndKill(35, mainObject.x, mainObject.y - 20);
                        mainObject.isStop = true;
                    }
                } else {
                    if (readByte == 0) {
                        GameScreen.addEffectEndKill(35, mainObject.x, mainObject.y - 20);
                    }
                    mainObject.isStop = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Save_RMS_Server(Message message) {
        try {
            byte readByte = message.reader().readByte();
            int readShort = message.reader().readShort();
            byte[] bArr = null;
            if (readShort > 0) {
                bArr = new byte[readShort];
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = message.reader().readByte();
                }
            }
            mSystem.outz(new StringBuffer().append("nhan k=").append((int) readByte).append("   di=").append(readShort).toString());
            MainRMS.setLoadRMS(readByte, bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void List_Pk(Message message) {
        try {
            int readByte = message.reader().readByte();
            Player.vecPlayerPk = new short[readByte];
            for (int i = 0; i < readByte; i++) {
                Player.vecPlayerPk[i] = message.reader().readShort();
            }
        } catch (Exception e) {
        }
    }

    public void List_Server(Message message) {
        try {
            mSystem.outz("list server");
            byte readByte = message.reader().readByte();
            String readUTF = message.reader().readUTF();
            byte readByte2 = message.reader().readByte();
            int readInt = message.reader().readInt();
            int readUnsignedByte = message.reader().readUnsignedByte();
            mVector mvector = new mVector("ReadMessenge vec8");
            if (readByte == 3) {
                for (int i = 0; i < readUnsignedByte; i++) {
                    String readUTF2 = message.reader().readUTF();
                    int readInt2 = message.reader().readInt();
                    short readShort = message.reader().readShort();
                    String readUTF3 = message.reader().readUTF();
                    String readUTF4 = message.reader().readUTF();
                    MainClan mainClan = new MainClan(readInt2, readShort, readUTF3, (byte) 122);
                    mainClan.name = readUTF2;
                    mainClan.slogan = readUTF4;
                    if (readInt > readUnsignedByte && i == readUnsignedByte - 1) {
                        mainClan.hang = readInt;
                    } else {
                        mainClan.hang = i;
                    }
                    mvector.addElement(mainClan);
                }
            } else {
                for (int i2 = 0; i2 < readUnsignedByte; i2++) {
                    String readUTF5 = message.reader().readUTF();
                    byte readByte3 = message.reader().readByte();
                    byte readByte4 = message.reader().readByte();
                    byte readByte5 = message.reader().readByte();
                    short readShort2 = message.reader().readShort();
                    int readByte6 = message.reader().readByte();
                    byte[] bArr = new byte[12];
                    for (int i3 = 0; i3 < bArr.length; i3++) {
                        bArr[i3] = -1;
                    }
                    for (int i4 = 0; i4 < readByte6; i4++) {
                        bArr[message.reader().readByte()] = message.reader().readByte();
                    }
                    byte readByte7 = message.reader().readByte();
                    String readUTF6 = message.reader().readUTF();
                    MainObject mainObject = new MainObject(0, (byte) 0, readUTF5, 0, 0);
                    short readShort3 = message.reader().readShort();
                    if (readShort3 >= 0) {
                        mainObject.myClan = new MainClan(0, readShort3, message.reader().readUTF(), message.reader().readByte());
                    }
                    mainObject.setInfo(readByte3, readByte4, readByte5);
                    mainObject.typeOnline = readByte7;
                    mainObject.Lv = readShort2;
                    mainObject.setWearingListChar(bArr);
                    mainObject.infoObject = readUTF6;
                    if (readInt > readUnsignedByte && i2 == readUnsignedByte - 1) {
                        mainObject.hang = readInt;
                    } else {
                        mainObject.hang = i2;
                    }
                    mvector.addElement(mainObject);
                }
            }
            if (mvector.size() == 0) {
                return;
            }
            if (readByte == 1) {
                if (readInt > readUnsignedByte) {
                    MainObject mainObject2 = new MainObject(0, (byte) 0, "...", 0, 0);
                    mainObject2.hang = -1;
                    mvector.insertElementAt(mainObject2, readUnsignedByte - 1);
                }
            } else if (readByte == 3) {
                if (readInt > readUnsignedByte) {
                    MainClan mainClan2 = new MainClan(0, (short) -1, "...", (byte) 122);
                    mainClan2.hang = -1;
                    mvector.insertElementAt(mainClan2, readUnsignedByte - 1);
                }
            } else if (readByte == 6 && readInt > readUnsignedByte) {
                MainObject mainObject3 = new MainObject(0, (byte) 0, "...", 0, 0);
                mainObject3.hang = -1;
                mvector.insertElementAt(mainObject3, readUnsignedByte - 1);
            }
            List_Server.gI().vecListServer = mvector;
            List_Server.gI().setSize(readByte);
            List_Server.gI().updateList();
            List_Server.gI().setMinMax();
            List_Server.gI().setXmove();
            List_Server.gI().setCmd();
            List_Server.gI().nameList = readUTF;
            List_Server.gI().page = readByte2;
            if (GameCanvas.currentScreen != List_Server.gI()) {
                if (GameCanvas.currentScreen.lastScreen != List_Server.gI()) {
                    List_Server.gI().Show(GameCanvas.currentScreen);
                } else {
                    List_Server.gI().Show(GameCanvas.game);
                }
            } else {
                GameCanvas.end_Dialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void suckhoe(Message message) {
        try {
            Player.PointSucKhoe = message.reader().readInt();
            Player.PointArena = message.reader().readInt();
        } catch (Exception e) {
        }
    }

    public void chat_npc(Message message) {
        try {
            MainObject mainObject = MainObject.get_Object(message.reader().readByte(), (byte) 2);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.strChatPopup = message.reader().readUTF();
            }
        } catch (Exception e) {
        }
    }

    public void InfoServer_Download(Message message) {
        try {
            String readUTF = message.reader().readUTF();
            String readUTF2 = message.reader().readUTF();
            byte readByte = message.reader().readByte();
            if (GameScreen.player != null && GameScreen.player.Action != 2 && GameScreen.player.Action != 4) {
                GameScreen.player.resetAction();
                GameScreen.player.posTransRoad = null;
            }
            if (readByte == 15) {
                GameCanvas.start_Ok_Dialog(readUTF, readByte);
            } else if (readUTF2.length() == 0) {
                GameCanvas.start_Ok_Dialog(readUTF);
            } else {
                GameCanvas.start_Download_Dialog(readUTF, readUTF2);
            }
        } catch (Exception e) {
        }
    }

    public void name_server(Message message) {
        try {
            int readUnsignedByte = message.reader().readUnsignedByte();
            String[] strArr = new String[readUnsignedByte];
            for (int i = 0; i < readUnsignedByte; i++) {
                strArr[i] = message.reader().readUTF();
            }
            WorldMapScreen.namePos = strArr;
            int readByte = message.reader().readByte();
            String[] strArr2 = new String[readByte];
            for (int i2 = 0; i2 < readByte; i2++) {
                strArr2[i2] = message.reader().readUTF();
            }
            TabQuest.nameItemQuest = strArr2;
            int readByte2 = message.reader().readByte();
            TabRebuildItem.mNameMaterial = new String[readByte2];
            TabRebuildItem.idMaterial = new short[readByte2];
            TabRebuildItem.numMaterialInven = new int[readByte2];
            for (int i3 = 0; i3 < readByte2; i3++) {
                TabRebuildItem.idMaterial[i3] = message.reader().readShort();
                MainItem material = Item.getMaterial(TabRebuildItem.idMaterial[i3]);
                if (material != null) {
                    TabRebuildItem.mNameMaterial[i3] = material.itemName;
                }
            }
            int readByte3 = message.reader().readByte();
            TabRebuildItem.dataRebuild = new DataRebuildItem[readByte3];
            for (int i4 = 0; i4 < readByte3; i4++) {
                TabRebuildItem.dataRebuild[i4] = new DataRebuildItem();
                TabRebuildItem.dataRebuild[i4].lv = message.reader().readByte();
                TabRebuildItem.dataRebuild[i4].priceCoin = message.reader().readInt();
                TabRebuildItem.dataRebuild[i4].priceGold = message.reader().readShort();
                TabRebuildItem.dataRebuild[i4].mValue = new byte[4];
                for (int i5 = 0; i5 < 4; i5++) {
                    TabRebuildItem.dataRebuild[i4].mValue[i5] = message.reader().readByte();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void x2_Xp(Message message) {
        try {
            Player.typeX2 = message.reader().readByte();
            Player.timeSetX2 = GameCanvas.timeNow;
            Player.timeX2 = message.reader().readShort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImageDataCharPart(Message message) {
        try {
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            String stringBuffer = new StringBuffer().append("").append((int) readShort).toString();
            byte[] bArr = new byte[message.reader().readInt()];
            message.reader().read(bArr);
            int i = 0;
            if (message.reader().available() > 0) {
                i = message.reader().available();
            }
            byte[] bArr2 = new byte[1];
            if (i > 0) {
                bArr2 = new byte[i];
                message.reader().read(bArr2);
            }
            if (readByte < 100) {
                mImage createImage = mImage.createImage(bArr, 0, 0, stringBuffer);
                if (readByte < 50) {
                    MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar) CharPartInfo.hashImagePartChar.get(new StringBuffer().append((int) readByte).append("_").append((int) readShort).toString());
                    if (mainImageDataPartChar == null) {
                        CharPartInfo.hashImagePartChar.put(new StringBuffer().append((int) readByte).append("_").append((int) readShort).toString(), new MainImageDataPartChar(createImage, bArr2));
                    } else {
                        mainImageDataPartChar.img = createImage;
                        mainImageDataPartChar.isData = bArr2;
                    }
                } else {
                    MainImageDataPartChar mainImageDataPartChar2 = (MainImageDataPartChar) CRes.hashWeapon.get(new StringBuffer().append(readByte - 50).append("_").append((int) readShort).toString());
                    if (mainImageDataPartChar2 == null) {
                        CRes.hashWeapon.put(new StringBuffer().append(readByte - 50).append("_").append((int) readShort).toString(), new MainImageDataPartChar(createImage, bArr2));
                    } else {
                        mainImageDataPartChar2.img = createImage;
                        mainImageDataPartChar2.isData = bArr2;
                    }
                }
            } else if (readByte == 110) {
                ImageEffect imageEffect = (ImageEffect) ImageEffect.hashImageEff.get(new StringBuffer().append("").append((int) readShort).toString());
                if (imageEffect != null) {
                    imageEffect.timeRemove = GameCanvas.timeNow;
                    imageEffect.img = mImage.createImage(bArr, 0, 0, stringBuffer);
                }
            } else if (readByte == 111) {
                ImageEffectAuto imageEffectAuto = (ImageEffectAuto) ImageEffectAuto.hashImageEffAuto.get(new StringBuffer().append("").append((int) readShort).toString());
                if (imageEffectAuto != null) {
                    imageEffectAuto.timeRemove = GameCanvas.timeNow;
                    imageEffectAuto.img = mImage.createImage(bArr, 0, 0, stringBuffer);
                }
                DataEffAuto dataEffAuto = (DataEffAuto) EffectAuto.ALL_DATA_EFF_AUTO.get(new StringBuffer().append("").append((int) readShort).toString());
                if (dataEffAuto != null) {
                    CRes.saveRMS(new StringBuffer().append("data_eff").append((int) readShort).toString(), bArr2);
                    dataEffAuto.timeremove = GameCanvas.timeNow;
                    dataEffAuto.setdata(bArr2);
                }
            } else if (readByte == 112) {
                ImageIcon imageIcon = (ImageIcon) GameData.listImgIcon.get(new StringBuffer().append("").append(readShort + GameData.ID_START_SKILL).toString());
                if (imageIcon != null) {
                    imageIcon.timeRemove = GameCanvas.timeNow;
                    imageIcon.img = mImage.createImage(bArr, 0, 0, stringBuffer);
                }
                EffectData effectData = (EffectData) DataSkillEff.ALL_DATA_EFFECT.get(new StringBuffer().append("").append(readShort + GameData.ID_START_SKILL).toString());
                if (effectData != null) {
                    effectData.timeRemove = GameCanvas.timeNow;
                    effectData.setdata(bArr2);
                }
            } else if (readByte == 113) {
                ImageIcon imageIcon2 = (ImageIcon) GameData.listImgIcon.get(new StringBuffer().append("").append(readShort + GameData.ID_START_SKILL).toString());
                if (imageIcon2 != null) {
                    imageIcon2.timeRemove = GameCanvas.timeNow;
                    imageIcon2.img = mImage.createImage(bArr, 0, 0, stringBuffer);
                }
                DataSkillEff effMatNa = MainObject.getEffMatNa(readShort);
                if (effMatNa != null) {
                    effMatNa.loadData(bArr2);
                }
            }
            MsgDialog.curupdate++;
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("loi ne ").append(e.toString()).toString());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:53:0x0165. Please report as an issue. */
    public void loadImageDataAutoEff(Message message) {
        MainObject mainObject;
        MainObject mainObject2;
        try {
            byte readByte = message.reader().readByte();
            if (readByte != 100) {
                if (readByte == 4) {
                    byte[] bArr = new byte[message.reader().readShort()];
                    for (int i = 0; i < bArr.length; i++) {
                        bArr[i] = message.reader().readByte();
                    }
                    short readShort = message.reader().readShort();
                    int readByte2 = message.reader().readByte();
                    for (int i2 = 0; i2 < readByte2; i2++) {
                        MainObject findObjByteCat = GameScreen.findObjByteCat(message.reader().readShort(), message.reader().readByte());
                        if (findObjByteCat != null) {
                            GameScreen.vecHightEffAuto.addElement(new EffectAuto(readShort, findObjByteCat.x, findObjByteCat.y, bArr));
                        }
                    }
                } else if (readByte == 3) {
                    short readShort2 = message.reader().readShort();
                    byte readByte3 = message.reader().readByte();
                    int readUnsignedByte = message.reader().readUnsignedByte();
                    MainObject mainObject3 = MainObject.get_Object(readShort2, readByte3);
                    if (mainObject3 != null) {
                        mainObject3.removeDataSkillEff(readUnsignedByte);
                    }
                } else if (readByte == 101) {
                    short readShort3 = message.reader().readShort();
                    for (int i3 = 0; i3 < LoadMap.mItemMap.size(); i3++) {
                        MainItemMap mainItemMap = (MainItemMap) LoadMap.mItemMap.elementAt(i3);
                        if (mainItemMap != null && mainItemMap.idActor == readShort3) {
                            LoadMap.mItemMap.removeElement(mainItemMap);
                        }
                    }
                } else {
                    byte[] bArr2 = new byte[message.reader().readShort()];
                    for (int i4 = 0; i4 < bArr2.length; i4++) {
                        bArr2[i4] = message.reader().readByte();
                    }
                    byte readByte4 = message.reader().readByte();
                    byte readByte5 = message.reader().readByte();
                    int readUnsignedByte2 = message.reader().readUnsignedByte();
                    switch (readByte) {
                        case 0:
                            short readShort4 = message.reader().readShort();
                            byte readByte6 = message.reader().readByte();
                            byte readByte7 = message.reader().readByte();
                            short readShort5 = message.reader().readShort();
                            byte readByte8 = message.reader().readByte();
                            long currentTimeMillis = (readShort5 * ObjectData.MONSTER) + mSystem.currentTimeMillis();
                            MainObject mainObject4 = MainObject.get_Object(readShort4, readByte6);
                            if (mainObject4 != null) {
                                mainObject4.addEffAutoFromsv(readUnsignedByte2, mainObject4.x, mainObject4.y, 0, 0, 0, 0, bArr2, readByte7, currentTimeMillis, readByte8, readByte4, readByte5);
                            }
                            break;
                        case 1:
                            short readShort6 = message.reader().readShort();
                            short readShort7 = message.reader().readShort();
                            byte readByte9 = message.reader().readByte();
                            byte readByte10 = message.reader().readByte();
                            short readShort8 = message.reader().readShort();
                            short readShort9 = message.reader().readShort();
                            byte readByte11 = message.reader().readByte();
                            if (readByte10 != 0 && readShort8 != 0 && (mainObject2 = MainObject.get_Object(readShort8, readByte11)) != null) {
                                mainObject2.hOne = readByte10;
                            }
                            if (readByte9 == 3) {
                                GameScreen.vecHightEffAuto.addElement(new EffectAuto(readUnsignedByte2, readShort6, readShort7, 0, 0, readByte9, 5, bArr2, readShort9));
                            } else if (readByte11 == 1) {
                                for (int i5 = 0; i5 < LoadMap.mItemMap.size(); i5++) {
                                    MainItemMap mainItemMap2 = (MainItemMap) LoadMap.mItemMap.elementAt(i5);
                                    if (mainItemMap2 != null && mainItemMap2.idActor == readShort8) {
                                        return;
                                    }
                                }
                                EffectAuto effectAuto = new EffectAuto(readUnsignedByte2, readShort6, readShort7, 0, 0, readByte9, 0, bArr2);
                                effectAuto.setidActor(readShort8);
                                LoadMap.mItemMap.addElement(effectAuto);
                                GameCanvas.loadmap.setBlockNPC(readShort6, readShort7, readByte4, readByte5);
                                CRes.quickSort1(LoadMap.mItemMap);
                            } else {
                                EffectAuto effectAuto2 = new EffectAuto(readUnsignedByte2, readShort6, readShort7, 0, 0, readByte9, 0, bArr2);
                                effectAuto2.setidActor(readShort8);
                                LoadMap.mItemMap.addElement(effectAuto2);
                                GameCanvas.loadmap.setBlockNPC(readShort6, readShort7, readByte4, readByte5);
                                CRes.quickSort1(LoadMap.mItemMap);
                            }
                            break;
                        case 2:
                            short readShort10 = message.reader().readShort();
                            byte readByte12 = message.reader().readByte();
                            byte readByte13 = message.reader().readByte();
                            int readInt = message.reader().readInt();
                            if (readByte13 == 17) {
                                if (readInt < 0) {
                                    GameScreen.player.addDataEff(readUnsignedByte2, bArr2, readByte4, readByte5);
                                } else if (readInt > 0) {
                                    GameScreen.player.addDataEff(readUnsignedByte2, bArr2, readByte4, readByte5, readInt + mSystem.currentTimeMillis(), readByte13);
                                }
                            } else {
                                MainObject mainObject5 = MainObject.get_Object(readShort10, readByte12);
                                if (mainObject5 != null) {
                                    if (readInt < 0) {
                                        mainObject5.addDataEff(readUnsignedByte2, bArr2, readByte4, readByte5);
                                    } else if (readInt > 0) {
                                        mainObject5.addDataEff(readUnsignedByte2, bArr2, readByte4, readByte5, readInt + mSystem.currentTimeMillis(), readByte13);
                                    } else {
                                        mainObject5.addDataEff2(readUnsignedByte2, bArr2, readByte4, readByte5, 0L, readByte13);
                                    }
                                }
                            }
                            break;
                    }
                }
            } else if (readByte == 100) {
                short readShort11 = message.reader().readShort();
                int readUnsignedByte3 = message.reader().readUnsignedByte();
                byte readByte14 = message.reader().readByte();
                byte readByte15 = message.reader().readByte();
                if ((readByte14 == 0 || readByte14 == 1) && (mainObject = MainObject.get_Object(readShort11, readByte14)) != null) {
                    if (readByte15 == 0) {
                        for (int i6 = 0; i6 < mainObject.vecEffauto.size(); i6++) {
                            EffectAuto effectAuto3 = (EffectAuto) mainObject.vecEffauto.elementAt(i6);
                            if (effectAuto3 != null && effectAuto3.IDImage == readUnsignedByte3) {
                                mainObject.vecEffauto.removeElement(effectAuto3);
                            }
                        }
                    } else if (readByte15 == 1) {
                        for (int i7 = 0; i7 < mainObject.veclowEffauto.size(); i7++) {
                            EffectAuto effectAuto4 = (EffectAuto) mainObject.veclowEffauto.elementAt(i7);
                            if (effectAuto4 != null && effectAuto4.IDImage == readUnsignedByte3) {
                                mainObject.veclowEffauto.removeElement(effectAuto4);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("loadImageDataAutoEff loi  ").append(e.toString()).toString());
        }
    }

    public void nap_tien(Message message) {
        try {
            short readShort = message.reader().readShort();
            int readByte = message.reader().readByte();
            String[] strArr = new String[readByte];
            for (int i = 0; i < readByte; i++) {
                strArr[i] = message.reader().readUTF();
            }
            String readUTF = message.reader().readUTF();
            String readUTF2 = message.reader().readUTF();
            if (readShort == 1) {
                GameCanvas.end_Dialog();
                GameCanvas.start_More_Input_Dialog(strArr, new iCommand(readUTF, 0), readShort, (short) -1, readUTF2);
            } else if (mSystem.isIpAppstore()) {
                mVector mvector = new mVector("ReadMessenge vec9");
                for (int i2 = 0; i2 < TemMidlet.google_listGems.length; i2++) {
                    iCommand icommand = new iCommand(TemMidlet.google_listGems[i2], 20, i2, this);
                    icommand.setFraCaption(AvMain.fraDiamond, 1, 1);
                    mvector.addElement(icommand);
                }
                GameCanvas.menu2.startAt(mvector, 2, T.naptien, false, null);
            } else if (TemMidlet.currentIAPStore == 2) {
                mVector mvector2 = new mVector("ReadMessenge vec9");
                for (int i3 = 0; i3 < TemMidlet.google_listGems.length; i3++) {
                    iCommand icommand2 = new iCommand(TemMidlet.google_listGems[i3], 20, i3, this);
                    icommand2.setFraCaption(AvMain.fraDiamond, 1, 1);
                    mvector2.addElement(icommand2);
                }
                GameCanvas.menu2.startAt(mvector2, 2, T.naptien, false, null);
            } else if (TemMidlet.currentIAPStore == 1) {
                mVector mvector3 = new mVector("ReadMessenge vec10");
                for (int i4 = 0; i4 < TemMidlet.listGems.length; i4++) {
                    iCommand icommand3 = new iCommand(TemMidlet.listGems[i4], 20, i4, this);
                    icommand3.setFraCaption(AvMain.fraDiamond, 1, 1);
                    mvector3.addElement(icommand3);
                }
                GameCanvas.menu2.startAt(mvector3, 2, T.naptien, false, null);
            } else if (TemMidlet.currentIAPStore == 0) {
                GameCanvas.end_Dialog();
                GameCanvas.start_More_Input_Dialog(strArr, new iCommand(readUTF, 0), readShort, (short) -1, readUTF2);
            }
        } catch (Exception e) {
        }
    }

    public void delete_rms(Message message) {
        try {
            if (GameCanvas.IndexRes != -1) {
                TemMidlet.delRMS();
            }
            CRes.saveRMS("isIndexRes", new byte[]{message.reader().readByte()});
        } catch (Exception e) {
        }
    }

    public void CharChest(Message message) {
        update_InVen_Or_Chest(message, Item.VecChestPlayer, (byte) 9);
    }

    private PetItem createPetItemFromMessage(Message message) {
        try {
            String readUTF = message.reader().readUTF();
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            byte readByte4 = message.reader().readByte();
            byte readByte5 = message.reader().readByte();
            int readInt = message.reader().readInt();
            short readShort4 = message.reader().readShort();
            short readShort5 = message.reader().readShort();
            short readShort6 = message.reader().readShort();
            short readShort7 = message.reader().readShort();
            short readShort8 = message.reader().readShort();
            short readShort9 = message.reader().readShort();
            short readShort10 = message.reader().readShort();
            int readByte6 = message.reader().readByte();
            MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte6];
            for (int i = 0; i < readByte6; i++) {
                mainInfoItemArr[i] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt(), message.reader().readInt());
            }
            PetItem petItem = new PetItem(readShort, readUTF, readByte2, readByte5, readByte, 9, mainInfoItemArr, 14, readShort2, readByte2, readByte4, readByte3);
            petItem.setInfoPet(readInt, readShort4, readShort6, readShort7, readShort8, readShort9, readShort5, readShort10, readShort3);
            return petItem;
        } catch (Exception e) {
            return null;
        }
    }

    private MainItem createItemFromMessage(Message message) {
        try {
            String readUTF = message.reader().readUTF();
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            byte readByte2 = message.reader().readByte();
            short readShort2 = message.reader().readShort();
            byte readByte3 = message.reader().readByte();
            short readShort3 = message.reader().readShort();
            byte readByte4 = message.reader().readByte();
            byte readByte5 = message.reader().readByte();
            byte readByte6 = message.reader().readByte();
            int readByte7 = message.reader().readByte();
            MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte7];
            for (int i = 0; i < readByte7; i++) {
                mainInfoItemArr[i] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
            }
            return new MainItem(readShort, readUTF, readShort2, readByte3, readByte4, readByte, 3, mainInfoItemArr, readByte2, false, (short) -1, 0L, readShort3, readByte5, readByte6, message.reader().readInt(), (byte) 0, message.reader().readByte());
        } catch (Exception e) {
            return null;
        }
    }

    public void UpdatePetContainer(Message message) {
        Pet pet;
        try {
            message.reader().readByte();
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            mVector mvector = Item.VecPetContainer;
            if (readByte == 0) {
                MainTemplateItem.removeUpdateItemVec(readByte2, mvector);
                if (readByte2 == 3) {
                    message.reader().readByte();
                    int readByte3 = message.reader().readByte();
                    for (int i = 0; i < readByte3; i++) {
                        mvector.addElement(createItemFromMessage(message));
                    }
                } else if (readByte2 == 9) {
                    message.reader().readByte();
                    int readByte4 = message.reader().readByte();
                    for (int i2 = 0; i2 < readByte4; i2++) {
                        mvector.addElement(createPetItemFromMessage(message));
                    }
                    if (GameScreen.isInPetArea) {
                        for (int i3 = 0; i3 < Item.VecPetContainer.size(); i3++) {
                            PetItem petItem = (PetItem) mvector.elementAt(i3);
                            Pet pet2 = (Pet) MainObject.get_Object(petItem.Id, (byte) 9);
                            if (GameCanvas.subDialog != null && GameCanvas.subDialog.type == 14 && MsgDialog.isInven_Equip == 0) {
                                mSystem.outz(new StringBuffer().append(" Id item pet in MsgDialog = ").append(MsgDialog.pet.Id).toString());
                                if (MsgDialog.pet.Id == petItem.Id) {
                                    MsgDialog.pet = petItem;
                                }
                            }
                            if (pet2 == null) {
                                GameScreen.addPlayer(Pet.createPet((short) petItem.type, petItem.Id, petItem.nFrameImgPet, petItem.petImageId));
                            }
                        }
                    }
                }
            } else if (readByte == 1) {
                if (readByte2 == 3) {
                    byte readByte5 = message.reader().readByte();
                    MainItem createItemFromMessage = createItemFromMessage(message);
                    Item itemVec = Item.getItemVec(readByte5, (short) createItemFromMessage.Id, mvector);
                    if (itemVec == null) {
                        mvector.addElement(createItemFromMessage);
                    } else {
                        mvector.setElementAt(createItemFromMessage, mvector.indexOf(itemVec));
                    }
                } else if (readByte2 == 9) {
                    byte readByte6 = message.reader().readByte();
                    PetItem createPetItemFromMessage = createPetItemFromMessage(message);
                    Item itemVec2 = Item.getItemVec(readByte6, (short) createPetItemFromMessage.Id, mvector);
                    if (itemVec2 == null) {
                        mvector.addElement(createPetItemFromMessage);
                    } else {
                        mvector.setElementAt(createPetItemFromMessage, mvector.indexOf(itemVec2));
                    }
                    if (GameScreen.isInPetArea) {
                        GameScreen.addPlayer(Pet.createPet((short) createPetItemFromMessage.type, createPetItemFromMessage.Id, createPetItemFromMessage.nFrameImgPet, createPetItemFromMessage.petImageId));
                    }
                }
            } else if (readByte == 2) {
                Item itemVec3 = Item.getItemVec(message.reader().readByte(), message.reader().readShort(), mvector);
                if (itemVec3 != null) {
                    mvector.removeElement(itemVec3);
                }
                PetItem petItem2 = (PetItem) itemVec3;
                if (petItem2 != null && petItem2.type >= 0 && (pet = (Pet) MainObject.get_Item_Object(petItem2.Id, 9)) != null) {
                    GameScreen.Vecplayers.removeElement(pet);
                }
            }
        } catch (Exception e) {
        }
    }

    public void Dialog_server(Message message) {
        try {
            GameCanvas.end_Dialog();
            this.idDialog = message.reader().readShort();
            this.typeDialog = message.reader().readByte();
            String readUTF = message.reader().readUTF();
            mVector mvector = new mVector("ReadMessenge vec4");
            mvector.addElement(new iCommand("Ok", 2, 1, this));
            mvector.addElement(new iCommand(T.cancel, 2, 0, this));
            GameCanvas.start_Select_Dialog(readUTF, mvector);
        } catch (Exception e) {
        }
    }

    public void update_InVen_Or_Chest(Message message, mVector mvector, byte b) {
        Item itemInventory;
        Item itemInventory2;
        try {
            mSystem.outz("<<<<<<<<< update inventory >>>>>>>>>>>>");
            MainTemplateItem.isload = false;
            if (b == 9) {
                Player.maxChest = message.reader().readByte();
            }
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            if (b == 0) {
                long readLong = message.reader().readLong();
                int readInt = message.reader().readInt();
                GameScreen.player.coin = readLong;
                GameScreen.player.gold = readInt;
            }
            if (readByte == 0) {
                MainTemplateItem.removeUpdateItemVec(readByte2, mvector);
                if (readByte2 == 4) {
                    byte readByte3 = message.reader().readByte();
                    int readByte4 = message.reader().readByte();
                    for (int i = 0; i < readByte4; i++) {
                        short readShort = message.reader().readShort();
                        short readShort2 = message.reader().readShort();
                        byte readByte5 = message.reader().readByte();
                        byte readByte6 = message.reader().readByte();
                        MainTemplateItem mainTemplateItem = (MainTemplateItem) MainTemplateItem.hashPotionTem.get(new StringBuffer().append("").append((int) readShort).toString());
                        mSystem.outz(new StringBuffer().append("content=").append(mainTemplateItem.contentPotion).toString());
                        mvector.addElement(new MainItem(readShort, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, readShort2, readByte3, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, readByte5, readByte6));
                    }
                    if (b == 0) {
                        MainItem.setAddHotKey((byte) 1, false);
                        MainItem.setAddHotKey((byte) 0, false);
                    }
                } else if (readByte2 == 3) {
                    byte readByte7 = message.reader().readByte();
                    int readByte8 = message.reader().readByte();
                    mSystem.outz(new StringBuffer().append("size item=").append(readByte8).toString());
                    for (int i2 = 0; i2 < readByte8; i2++) {
                        String readUTF = message.reader().readUTF();
                        byte readByte9 = message.reader().readByte();
                        short readShort3 = message.reader().readShort();
                        byte readByte10 = message.reader().readByte();
                        short readShort4 = message.reader().readShort();
                        byte readByte11 = message.reader().readByte();
                        short readShort5 = message.reader().readShort();
                        byte readByte12 = message.reader().readByte();
                        byte readByte13 = message.reader().readByte();
                        byte readByte14 = message.reader().readByte();
                        int readByte15 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte15];
                        for (int i3 = 0; i3 < readByte15; i3++) {
                            mainInfoItemArr[i3] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                        }
                        int readInt2 = message.reader().readInt();
                        byte readByte16 = message.reader().readByte();
                        int i4 = -1;
                        String str = "-1";
                        if (message.reader().readByte() == 1) {
                            i4 = message.reader().readInt();
                            str = message.reader().readUTF();
                        }
                        byte readByte17 = message.reader().readByte();
                        MainItem mainItem = new MainItem(readShort3, readUTF, readShort4, readByte11, readByte12, readByte9, readByte7, mainInfoItemArr, readByte10, false, (short) -1, 0L, readShort5, readByte13, readByte14, readInt2, (byte) 0, readByte16, i4, Long.parseLong(str));
                        mainItem.setCanShell_notCanTrade(readByte17);
                        mvector.addElement(mainItem);
                    }
                } else if (readByte2 == 5) {
                    message.reader().readByte();
                    int readByte18 = message.reader().readByte();
                    mSystem.outz(new StringBuffer().append("size item quest=").append(readByte18).toString());
                    for (int i5 = 0; i5 < readByte18; i5++) {
                        mvector.addElement(new MainItem(message.reader().readShort(), message.reader().readUTF(), message.reader().readShort(), T.questitem, message.reader().readByte(), message.reader().readByte()));
                    }
                } else if (readByte2 == 7) {
                    message.reader().readByte();
                    int readByte19 = message.reader().readByte();
                    mSystem.outz(new StringBuffer().append("size material=").append(readByte19).toString());
                    for (int i6 = 0; i6 < readByte19; i6++) {
                        short readShort6 = message.reader().readShort();
                        short readShort7 = message.reader().readShort();
                        byte readByte20 = message.reader().readByte();
                        byte readByte21 = message.reader().readByte();
                        MainItem material = Item.getMaterial(readShort6);
                        if (material != null) {
                            mvector.addElement(MainItem.MainItem_Material(readShort6, material.itemName, material.imageId, 7, material.priceItem, material.priceType, material.content, material.value, material.typeMaterial, readShort7, readByte20, readByte21));
                        } else {
                            MainItem mainItem2 = new MainItem();
                            mainItem2.Id = readShort6;
                            mainItem2.numPotion = readShort7;
                            mainItem2.canSell = readByte20;
                            mainItem2.canTrade = readByte21;
                            mainItem2.ItemCatagory = 7;
                            Item.put_Material(readShort6);
                            mvector.addElement(mainItem2);
                        }
                    }
                }
                if (b == 0 && readByte2 != 3) {
                    TabShopNew.isSortInven = true;
                }
            } else if (readByte == 1) {
                if (readByte2 == 4) {
                    byte readByte22 = message.reader().readByte();
                    short readShort8 = message.reader().readShort();
                    short readShort9 = message.reader().readShort();
                    byte readByte23 = message.reader().readByte();
                    byte readByte24 = message.reader().readByte();
                    Item itemVec = Item.getItemVec(readByte22, readShort8, mvector);
                    if (itemVec != null) {
                        itemVec.numPotion = readShort9;
                        itemVec.canSell = readByte23;
                        itemVec.canTrade = readByte24;
                    } else {
                        MainTemplateItem mainTemplateItem2 = (MainTemplateItem) MainTemplateItem.hashPotionTem.get(new StringBuffer().append("").append((int) readShort8).toString());
                        mvector.addElement(new MainItem(readShort8, mainTemplateItem2.IconId, mainTemplateItem2.name, mainTemplateItem2.contentPotion, readShort9, readByte22, mainTemplateItem2.PricePoition, mainTemplateItem2.typePotion, mainTemplateItem2.moneyType, readByte23, readByte24));
                    }
                    if (b == 0) {
                        MainItem.setAddHotKey((byte) 1, false);
                        MainItem.setAddHotKey((byte) 0, false);
                        if (GameScreen.help.setStep_Next(1, 5)) {
                            GameScreen.help.Next++;
                            GameScreen.help.setNext();
                        }
                    }
                } else if (readByte2 == 3) {
                    byte readByte25 = message.reader().readByte();
                    String readUTF2 = message.reader().readUTF();
                    byte readByte26 = message.reader().readByte();
                    short readShort10 = message.reader().readShort();
                    Object itemVec2 = Item.getItemVec(readByte25, readShort10, mvector);
                    byte readByte27 = message.reader().readByte();
                    short readShort11 = message.reader().readShort();
                    byte readByte28 = message.reader().readByte();
                    short readShort12 = message.reader().readShort();
                    byte readByte29 = message.reader().readByte();
                    byte readByte30 = message.reader().readByte();
                    byte readByte31 = message.reader().readByte();
                    int readByte32 = message.reader().readByte();
                    MainInfoItem[] mainInfoItemArr2 = new MainInfoItem[readByte32];
                    for (int i7 = 0; i7 < readByte32; i7++) {
                        mainInfoItemArr2[i7] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                    }
                    int readInt3 = message.reader().readInt();
                    byte readByte33 = message.reader().readByte();
                    int i8 = -1;
                    String str2 = "-1";
                    if (message.reader().readByte() == 1) {
                        i8 = message.reader().readInt();
                        str2 = message.reader().readUTF();
                    }
                    byte readByte34 = message.reader().readByte();
                    MainItem MainItem_Item = MainItem.MainItem_Item(readShort10, readUTF2, readShort11, readByte28, readByte29, readByte26, readByte25, mainInfoItemArr2, readByte27, false, (short) -1, 0L, readShort12, readByte30, readByte31, readInt3, (byte) 0, readByte33, i8, Long.parseLong(str2));
                    MainItem_Item.setCanShell_notCanTrade(readByte34);
                    if (itemVec2 == null) {
                        mvector.addElement(MainItem_Item);
                    } else {
                        mvector.setElementAt(MainItem_Item, mvector.indexOf(itemVec2));
                    }
                } else if (readByte2 == 9) {
                    byte readByte35 = message.reader().readByte();
                    PetItem createPetItemFromMessage = createPetItemFromMessage(message);
                    Object itemVec3 = Item.getItemVec(readByte35, (short) createPetItemFromMessage.Id, mvector);
                    if (itemVec3 == null) {
                        mvector.addElement(createPetItemFromMessage);
                    } else {
                        mSystem.outz("replace pet");
                        mvector.setElementAt(createPetItemFromMessage, mvector.indexOf(itemVec3));
                    }
                } else if (readByte2 == 5) {
                    byte readByte36 = message.reader().readByte();
                    short readShort13 = message.reader().readShort();
                    String readUTF3 = message.reader().readUTF();
                    short readShort14 = message.reader().readShort();
                    String str3 = T.questitem;
                    byte readByte37 = message.reader().readByte();
                    byte readByte38 = message.reader().readByte();
                    Item itemVec4 = Item.getItemVec(readByte36, readShort13, mvector);
                    if (itemVec4 == null) {
                        mvector.addElement(new MainItem(readShort13, readUTF3, readShort14, str3, readByte37, readByte38));
                    } else {
                        itemVec4.numPotion = readShort14;
                        itemVec4.canSell = readByte37;
                        itemVec4.canTrade = readByte38;
                    }
                } else if (readByte2 == 7) {
                    byte readByte39 = message.reader().readByte();
                    short readShort15 = message.reader().readShort();
                    short readShort16 = message.reader().readShort();
                    byte readByte40 = message.reader().readByte();
                    byte readByte41 = message.reader().readByte();
                    Item itemVec5 = Item.getItemVec(readByte39, readShort15, mvector);
                    if (itemVec5 != null) {
                        itemVec5.numPotion = readShort16;
                        itemVec5.canSell = readByte40;
                        itemVec5.canTrade = readByte41;
                    } else {
                        MainItem material2 = Item.getMaterial(readShort15);
                        if (material2 != null) {
                            mvector.addElement(MainItem.MainItem_Material(readShort15, material2.itemName, material2.imageId, 7, material2.priceItem, material2.priceType, material2.content, material2.canTrade, material2.typeMaterial, readShort16, readByte40, readByte41));
                        } else {
                            MainItem mainItem3 = new MainItem();
                            mainItem3.Id = readShort15;
                            mainItem3.numPotion = readShort16;
                            mainItem3.canSell = readByte40;
                            mainItem3.canTrade = readByte41;
                            mainItem3.ItemCatagory = 7;
                            Item.put_Material(readShort15);
                            mvector.addElement(mainItem3);
                        }
                    }
                    if (b == 0) {
                        for (int i9 = 0; i9 < TabRebuildItem.idMaterial.length; i9++) {
                            MainItem material3 = Item.getMaterial(TabRebuildItem.idMaterial[i9]);
                            int i10 = 0;
                            if (material3 != null && (itemInventory2 = Item.getItemInventory(material3.ItemCatagory, (short) material3.Id)) != null) {
                                i10 = itemInventory2.numPotion;
                            }
                            TabRebuildItem.numMaterialInven[i9] = i10;
                        }
                    }
                }
                if (b == 0) {
                    if (GameScreen.help.Step == 1 && GameScreen.help.Next == 5) {
                        GameScreen.help.Next++;
                        GameScreen.help.setNext();
                    }
                    if (readByte2 != 3) {
                        TabShopNew.isSortInven = true;
                    }
                }
            } else if (readByte == 2) {
                Object itemVec6 = Item.getItemVec(message.reader().readByte(), message.reader().readShort(), mvector);
                if (itemVec6 != null) {
                    mvector.removeElement(itemVec6);
                }
                if (b == 0) {
                    if (readByte2 == 4) {
                        MainItem.setAddHotKey((byte) 1, false);
                        MainItem.setAddHotKey((byte) 0, false);
                    } else if (readByte2 == 7 && b == 0) {
                        for (int i11 = 0; i11 < TabRebuildItem.idMaterial.length; i11++) {
                            MainItem material4 = Item.getMaterial(TabRebuildItem.idMaterial[i11]);
                            int i12 = 0;
                            if (material4 != null && (itemInventory = Item.getItemInventory(material4.ItemCatagory, (short) material4.Id)) != null) {
                                i12 = itemInventory.numPotion;
                            }
                            TabRebuildItem.numMaterialInven[i11] = i12;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainTemplateItem.isload = true;
        MainTabNew.timePaintInfo = 0;
    }

    public void Rebuild_Item(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                short readShort = message.reader().readShort();
                byte readByte2 = message.reader().readByte();
                if (readByte2 == 3) {
                    Item itemInventory = Item.getItemInventory(readByte2, readShort);
                    if (itemInventory != null && TabRebuildItem.isBeginEff == 0) {
                        TabRebuildItem.itemRe = MainItem.MainItem_Item(readShort, itemInventory.itemNameExcludeLv, itemInventory.imageId, itemInventory.tier, itemInventory.colorNameItem, itemInventory.classcharItem, readByte2, itemInventory.mInfo, itemInventory.type_Only_Item, false, itemInventory.IdTem, itemInventory.priceItem, (short) itemInventory.LvItem, itemInventory.canSell, itemInventory.canTrade, 0, (byte) 0, (byte) 0);
                    }
                } else {
                    TabRebuildItem.isLucky = true;
                }
                if (TabRebuildItem.isBeginEff == 0) {
                    TabRebuildItem.tilemayman = message.reader().readUTF();
                    TabRebuildItem.tilemaymanafter = TabRebuildItem.tilemayman;
                } else {
                    TabRebuildItem.tilemaymanafter = message.reader().readUTF();
                }
            } else if (readByte == 1) {
                if (message.reader().readByte() == 3) {
                    TabRebuildItem.itemRe = null;
                } else {
                    TabRebuildItem.isLucky = false;
                }
                if (TabRebuildItem.isBeginEff == 0) {
                    TabRebuildItem.tilemayman = message.reader().readUTF();
                    TabRebuildItem.tilemaymanafter = TabRebuildItem.tilemayman;
                } else {
                    TabRebuildItem.tilemaymanafter = message.reader().readUTF();
                }
            } else if (readByte == 2) {
                GameCanvas.end_Dialog();
                TabRebuildItem.isBeginEff = (byte) 1;
                TabRebuildItem.isNextRebuild = message.reader().readByte();
                TabRebuildItem.contentShow = message.reader().readUTF();
                mSystem.outloi(new StringBuffer().append("thanh cong hay that bai=").append((int) TabRebuildItem.isNextRebuild).toString());
            } else if (readByte == 3) {
                GameCanvas.end_Dialog();
                GameCanvas.menu2.startAt_NPC(null, message.reader().readUTF(), Menu2.IdNPCLast, (byte) 2, false, 0);
            } else if (readByte == 4) {
                short readShort2 = message.reader().readShort();
                short readShort3 = message.reader().readShort();
                String readUTF = message.reader().readUTF();
                byte readByte3 = message.reader().readByte();
                Item itemInventory2 = Item.getItemInventory(readByte3, readShort2);
                if (itemInventory2 != null) {
                    TabRebuildItem.itemRe = MainItem.MainItem_Item(readShort2, itemInventory2.itemNameExcludeLv, itemInventory2.imageId, itemInventory2.tier, itemInventory2.colorNameItem, itemInventory2.classcharItem, readByte3, itemInventory2.mInfo, itemInventory2.type_Only_Item, false, itemInventory2.IdTem, itemInventory2.priceItem, (short) itemInventory2.LvItem, itemInventory2.canSell, itemInventory2.canTrade, 0, (byte) 0, (byte) 0);
                    TabRebuildItem.itemRe.itemClone = null;
                    TabRebuildItem.itemRe.infoHop = mSystem.split(readUTF, "|");
                    MainItem material = Item.getMaterial(TabRebuildItem.itemRe.Id);
                    if (material != null) {
                        TabRebuildItem.itemRe.itemName = material.itemName;
                    }
                    MainItem material2 = Item.getMaterial(readShort3);
                    if (material2 != null) {
                        if (material2.isMaterialHopNguyenLieu()) {
                            TabRebuildItem.itemRe.itemClone = material2.clone();
                        }
                    } else {
                        MainItem mainItem = new MainItem();
                        mainItem.Id = readShort3;
                        mainItem.ItemCatagory = 7;
                        TabRebuildItem.itemRe.itemClone = mainItem;
                    }
                }
            } else if (readByte == 5) {
                GameCanvas.end_Dialog();
                TabRebuildItem.isBeginEff = (byte) 1;
                TabRebuildItem.isNextRebuild = message.reader().readByte();
                TabRebuildItem.contentShow = message.reader().readUTF();
            } else if (readByte == 6) {
                TabRebuildItem.itemRe = null;
            }
        } catch (Exception e) {
        }
    }

    public void ReplacePlusItem(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                short readShort = message.reader().readShort();
                byte readByte2 = message.reader().readByte();
                Item itemInventory = Item.getItemInventory(3, readShort);
                if (itemInventory != null) {
                    if (readByte2 == 1) {
                        TabRebuildItem.itemPlus = MainItem.MainItem_Item(readShort, itemInventory.itemNameExcludeLv, itemInventory.imageId, itemInventory.tier, itemInventory.colorNameItem, itemInventory.classcharItem, 3, itemInventory.mInfo, itemInventory.type_Only_Item, false, itemInventory.IdTem, itemInventory.priceItem, (short) itemInventory.LvItem, itemInventory.canSell, itemInventory.canTrade, 0, (byte) 0, (byte) 0);
                    } else if (readByte2 == 0) {
                        TabRebuildItem.itemFree = MainItem.MainItem_Item(readShort, itemInventory.itemNameExcludeLv, itemInventory.imageId, itemInventory.tier, itemInventory.colorNameItem, itemInventory.classcharItem, 3, itemInventory.mInfo, itemInventory.type_Only_Item, false, itemInventory.IdTem, itemInventory.priceItem, (short) itemInventory.LvItem, itemInventory.canSell, itemInventory.canTrade, 0, (byte) 0, (byte) 0);
                    }
                } else {
                    mSystem.outz("k le no null=");
                }
            } else if (readByte == 1) {
                GameCanvas.end_Dialog();
                short readShort2 = message.reader().readShort();
                if (TabRebuildItem.itemPlus == null || TabRebuildItem.itemFree == null) {
                    return;
                }
                mVector mvector = new mVector("ReadMessenge menu3");
                mvector.addElement(new iCommand(T.replace, 4, this));
                GameCanvas.menu2.startAt_NPC(mvector, new StringBuffer().append(T.hoichuyendo).append(TabRebuildItem.itemPlus.itemName).append(T.qua).append(TabRebuildItem.itemFree.itemName).append("? ").append(T.phi).append(": ").append((int) readShort2).append(" ").append(T.gem).append(".").toString(), Menu2.IdNPCLast, (byte) 2, false, 0);
            } else if (readByte == 2) {
                GameCanvas.end_Dialog();
                TabRebuildItem.contentShow = message.reader().readUTF();
                TabRebuildItem.isBeginEff = (byte) 1;
            } else if (readByte == 3) {
                GameCanvas.end_Dialog();
                GameCanvas.menu2.startAt_NPC(null, message.reader().readUTF(), Menu2.IdNPCLast, (byte) 2, false, 0);
            }
        } catch (Exception e) {
        }
    }

    public void Thach_Dau(Message message) {
        try {
            if (message.reader().readByte() == 0) {
                GameCanvas.mevent.addEvent(message.reader().readUTF(), (byte) 4, T.loimoithachdau, message.reader().readShort());
            }
        } catch (Exception e) {
        }
    }

    public void Help_From_Server(Message message) {
        try {
            byte readByte = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(readByte, (byte) 2);
            if (mainObject == null || mainObject.isRemove) {
                return;
            }
            MainObject.strHelpNPC = mFont.split(message.reader().readUTF(), "\b");
            MainObject.StepHelpServer = (byte) 0;
            mVector mvector = new mVector("ReadMessenge menu2");
            mvector.addElement(new iCommand(T.next, 3, readByte, this));
            GameCanvas.menu2.startAt_NPC(mvector, MainObject.strHelpNPC[0], readByte, (byte) 2, false, 2);
        } catch (Exception e) {
        }
    }

    public void Clan(Message message) {
        MainObject mainObject;
        try {
            String str = "";
            byte b = 122;
            short s = 0;
            switch (message.reader().readByte()) {
                case 10:
                    GameCanvas.mevent.addEvent(message.reader().readUTF(), (byte) 5, T.loimoivaoclan, 0);
                    break;
                case 14:
                    GameCanvas.start_Ok_Dialog(new StringBuffer().append(T.thanhvien).append(": ").append(message.reader().readUTF()).append(" ").append(T.level).append((int) message.reader().readShort()).append("\n").append(T.chucvu).append(MainClan.getNameChucVu(message.reader().readByte())).append("\n").append(T.gop).append(T.coin).append(": ").append(message.reader().readLong()).append("\n").append(T.gop).append(T.gem).append(": ").append(message.reader().readInt()).toString());
                    break;
                case 15:
                    byte readByte = message.reader().readByte();
                    byte readByte2 = message.reader().readByte();
                    if (readByte2 == 0) {
                        int readInt = message.reader().readInt();
                        short readShort = message.reader().readShort();
                        String readUTF = message.reader().readUTF();
                        String readUTF2 = message.reader().readUTF();
                        short readShort2 = message.reader().readShort();
                        short readShort3 = message.reader().readShort();
                        short readShort4 = message.reader().readShort();
                        short readShort5 = message.reader().readShort();
                        short readShort6 = message.reader().readShort();
                        String readUTF3 = message.reader().readUTF();
                        String readUTF4 = message.reader().readUTF();
                        String readUTF5 = message.reader().readUTF();
                        long readLong = message.reader().readLong();
                        int readInt2 = message.reader().readInt();
                        int readByte3 = message.reader().readByte();
                        MainThanhTichClan[] mainThanhTichClanArr = null;
                        if (readByte3 > 0) {
                            mainThanhTichClanArr = new MainThanhTichClan[readByte3];
                            for (int i = 0; i < mainThanhTichClanArr.length; i++) {
                                mainThanhTichClanArr[i] = new MainThanhTichClan(message.reader().readByte(), message.reader().readByte(), message.reader().readUTF());
                            }
                        }
                        if (readByte == 0) {
                            if (GameScreen.player.myClan == null) {
                                GameScreen.player.myClan = new MainClan(readInt, readShort, readUTF, (byte) 122);
                            }
                            GameScreen.player.myClan.setInfoClan(readUTF2, readShort2, readShort3, readShort4, readShort5, readShort6, readUTF4, readUTF5, readUTF3, readLong, readInt2, mainThanhTichClanArr);
                            if (GameCanvas.clan == null) {
                                GameCanvas.clan = new Clan_Screen();
                            }
                            GameCanvas.clan.setInfoClan(GameScreen.player.myClan, readByte);
                            GameCanvas.clan.Show(GameCanvas.currentScreen);
                        } else {
                            MainClan mainClan = new MainClan(readInt, readShort, readUTF, (byte) 122);
                            mainClan.setInfoClan(readUTF2, readShort2, readShort3, readShort4, readShort5, readShort6, readUTF4, readUTF5, readUTF3, readLong, readInt2, mainThanhTichClanArr);
                            if (GameCanvas.clan == null) {
                                GameCanvas.clan = new Clan_Screen();
                            }
                            GameCanvas.clan.setInfoClan(mainClan, readByte);
                            GameCanvas.clan.Show(GameCanvas.currentScreen);
                        }
                        GameCanvas.end_Dialog();
                        break;
                    } else if (readByte2 == 1) {
                        if (GameScreen.player.myClan != null) {
                            GameScreen.player.myClan.coin = message.reader().readLong();
                            GameScreen.player.myClan.gold = message.reader().readInt();
                            break;
                        }
                    } else if (readByte2 == 2) {
                        if (GameScreen.player.myClan != null) {
                            GameScreen.player.myClan.noiquy = message.reader().readUTF();
                            Clan_Screen.isUpdateThongTin = true;
                            break;
                        }
                    } else if (readByte2 == 3 && GameScreen.player.myClan != null) {
                        GameScreen.player.myClan.slogan = message.reader().readUTF();
                        Clan_Screen.isUpdateThongTin = true;
                        break;
                    }
                    break;
                case 19:
                    short readShort7 = message.reader().readShort();
                    String readUTF6 = message.reader().readUTF();
                    int readInt3 = message.reader().readInt();
                    if (readInt3 != -1) {
                        message.reader().readUTF();
                        str = message.reader().readUTF();
                        s = message.reader().readShort();
                        b = message.reader().readByte();
                    }
                    if (readShort7 != 32000 && (mainObject = MainObject.get_Object(readShort7, (byte) 0)) != null) {
                        if (readInt3 == -1) {
                            if (mainObject.myClan != null) {
                                mainObject.myClan.isRemove = true;
                            }
                        } else if (mainObject.myClan == null) {
                            mainObject.myClan = new MainClan(readInt3, s, str, b);
                        } else {
                            mainObject.myClan.IdClan = readInt3;
                            mainObject.myClan.IdIcon = s;
                            mainObject.myClan.shortName = str.toUpperCase();
                            mainObject.myClan.chucvu = b;
                        }
                    }
                    if (GameCanvas.currentScreen == List_Server.gI() && List_Server.gI().typeList == 4) {
                        int i2 = 0;
                        while (true) {
                            if (i2 < List_Server.gI().vecListServer.size()) {
                                MainObject mainObject2 = (MainObject) List_Server.gI().vecListServer.elementAt(i2);
                                if (mainObject2.name.compareTo(readUTF6) != 0) {
                                    i2++;
                                } else if (mainObject2.myClan != null) {
                                    if (readInt3 == -1) {
                                        mainObject2.myClan.chucvu = (byte) 121;
                                    } else {
                                        mainObject2.myClan.chucvu = b;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    break;
                case 20:
                    GameCanvas.start_Ok_Dialog(message.reader().readUTF());
                    break;
                case 21:
                    byte readByte4 = message.reader().readByte();
                    if (readByte4 == 3) {
                        Item.VecClanInvetory.removeAllElements();
                        int readShort8 = message.reader().readShort();
                        for (int i3 = 0; i3 < readShort8; i3++) {
                            short readShort9 = message.reader().readShort();
                            short readShort10 = message.reader().readShort();
                            MainTemplateItem mainTemplateItem = (MainTemplateItem) MainTemplateItem.hashPotionTem.get(new StringBuffer().append("").append((int) readShort9).toString());
                            Item.VecClanInvetory.addElement(new MainItem(readShort9, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, readShort10, 4, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, (byte) 0, (byte) 0));
                        }
                        mVector mvector = new mVector("ReadMessenge vec3");
                        mvector.addElement(new TabShopNew(Item.VecClanInvetory, (byte) 12, T.tabBangHoi, -1, TabShopNew.NORMAL));
                        GameCanvas.shopNpc = new TabScreenNew();
                        GameCanvas.shopNpc.selectTab = 0;
                        GameCanvas.shopNpc.addMoreTab(mvector);
                        GameCanvas.shopNpc.Show(GameScreen.gI());
                        GameCanvas.end_Dialog();
                        break;
                    } else if (readByte4 == 2) {
                        Item itemVec = Item.getItemVec(4, message.reader().readShort(), Item.VecClanInvetory);
                        if (itemVec != null) {
                            Item.VecClanInvetory.removeElement(itemVec);
                        }
                        break;
                    } else if (readByte4 == 0) {
                        short readShort11 = message.reader().readShort();
                        short readShort12 = message.reader().readShort();
                        Item itemVec2 = Item.getItemVec(4, readShort11, Item.VecClanInvetory);
                        if (itemVec2 != null) {
                            itemVec2.numPotion = readShort12;
                        } else {
                            MainTemplateItem mainTemplateItem2 = (MainTemplateItem) MainTemplateItem.hashPotionTem.get(new StringBuffer().append("").append((int) readShort11).toString());
                            Item.VecClanInvetory.addElement(new MainItem(readShort11, mainTemplateItem2.IconId, mainTemplateItem2.name, mainTemplateItem2.contentPotion, readShort12, 4, mainTemplateItem2.PricePoition, mainTemplateItem2.typePotion, mainTemplateItem2.moneyType, (byte) 0, (byte) 0));
                        }
                        break;
                    }
                    break;
                case 22:
                    if (GameScreen.player.myClan != null) {
                        GameScreen.player.myClan.typeX2 = message.reader().readByte();
                        GameScreen.player.myClan.timeX2 = message.reader().readShort();
                        break;
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void updateHpNPC(Message message) {
        try {
            message.reader().readByte();
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(message.reader().readByte(), (byte) 2);
            if (mainObject != null && !mainObject.isRemove) {
                mainObject.hp = readShort;
                mainObject.maxHp = readShort2;
            }
        } catch (Exception e) {
        }
    }

    public void Dialog_More_server(Message message) {
        try {
            GameCanvas.end_Dialog();
            this.idDialog = message.reader().readShort();
            this.typeDialog = message.reader().readByte();
            String readUTF = message.reader().readUTF();
            int readByte = message.reader().readByte();
            String[] strArr = new String[readByte];
            String[] strArr2 = new String[readByte];
            byte b = 1;
            byte[] bArr = new byte[readByte];
            for (int i = 0; i < readByte; i++) {
                strArr[i] = message.reader().readUTF();
                bArr[i] = message.reader().readByte();
                strArr2[i] = "";
            }
            for (int i2 = 0; i2 < readByte; i2++) {
                try {
                    strArr2[i2] = message.reader().readUTF();
                } catch (Exception e) {
                }
            }
            b = message.reader().readByte();
            GameCanvas.start_More_Input_Dialog(strArr, new iCommand("OK", 1), this.typeDialog, this.idDialog, readUTF, strArr2, b);
        } catch (Exception e2) {
        }
    }

    public void InfoEasyFromServer(Message message) {
        try {
            String readUTF = message.reader().readUTF();
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                GameCanvas.addInfoChar(readUTF);
            } else if (readByte == 1) {
                GameCanvas.addInfoCharServer(readUTF);
            }
        } catch (Exception e) {
        }
    }

    public void EffFormServer(Message message) {
        try {
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            byte readByte3 = message.reader().readByte();
            short readShort3 = message.reader().readShort();
            MainObject mainObject = MainObject.get_Object(readShort, readByte2);
            mSystem.outz(new StringBuffer().append("Effect from server type = ").append(mainObject.name).toString());
            if (readByte >= 0 && mainObject != null && !mainObject.isRemove) {
                switch (readByte) {
                    case 0:
                        GameScreen.addEffectEndKill(38, mainObject.x, mainObject.y - 20);
                        break;
                    case 1:
                        for (int i = 0; i < EffectManager.hiEffects.size(); i++) {
                            MainEffect mainEffect = (MainEffect) EffectManager.hiEffects.elementAt(i);
                            if (mainEffect.typeEffect == 85 && mainEffect.objBeKillMain != null && mainEffect.objBeKillMain.ID == readShort) {
                                if (readShort2 == 0) {
                                    mainEffect.isStop = true;
                                    return;
                                }
                                return;
                            }
                        }
                        GameScreen.addEffectKill(85, mainObject.x, mainObject.y, readShort2, readShort, readByte2);
                        break;
                    case 2:
                        for (int i2 = 0; i2 < EffectManager.hiEffects.size(); i2++) {
                            MainEffect mainEffect2 = (MainEffect) EffectManager.hiEffects.elementAt(i2);
                            if (mainEffect2.typeEffect == 87 && mainEffect2.objBeKillMain != null && mainEffect2.objBeKillMain.ID == readShort && readShort2 == 0) {
                                mainEffect2.isStop = true;
                                return;
                            }
                        }
                        GameScreen.addEffectKill(87, mainObject.x, mainObject.y, readShort2, readShort, readByte2);
                        break;
                    case 3:
                        GameScreen.addEffectEndKill(43, mainObject.x, mainObject.y);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 4:
                        GameScreen.checkAddEff(94, mainObject.x, mainObject.y, readShort2, readShort, readByte2, readShort3, (byte) 1, false);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 5:
                        GameScreen.checkAddEff(100, mainObject.x, mainObject.y, readShort2, readShort, readByte2, readShort3, (byte) 1, false);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 6:
                        GameScreen.checkAddEff(101, mainObject.x, mainObject.y, readShort2, readShort, readByte2, readShort3, (byte) 1, false);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 7:
                        GameScreen.checkAddEff(EffectSkill.EFF_PET_EAGLE, mainObject.x, mainObject.y, readShort2, readShort, readByte2, readShort3, (byte) 1, false);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 8:
                        GameScreen.checkAddEff(EffectSkill.EFF_BOSS_NO, mainObject.x, mainObject.y, readShort2, readShort, readByte2, readShort3, (byte) 1, false);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 9:
                        mainObject.beginFire();
                        GameScreen.checkAddEff(103, mainObject.x, mainObject.y, readShort2, readShort, readByte2, readShort3, (byte) 1, false);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 10:
                        mainObject.addnewBuff(12, readShort2);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 11:
                        mainObject.addnewBuff(13, readShort2);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 12:
                        mainObject.addnewBuff(11, readShort2);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                    case 13:
                        mainObject.addnewBuff(14, readShort2);
                        if (readByte3 > 0) {
                            LoadMap.timeVibrateScreen = readByte3;
                            break;
                        }
                        break;
                }
            }
        } catch (Exception e) {
        }
    }

    public void EffWeather(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte == -1) {
                for (int i = 0; i < GameScreen.vecWeather.size(); i++) {
                    AnimateEffect animateEffect = (AnimateEffect) GameScreen.vecWeather.elementAt(i);
                    if (animateEffect.type == 0 || animateEffect.type == 2 || animateEffect.type == 1 || animateEffect.type == 4) {
                        animateEffect.stop();
                    }
                }
            } else {
                short readShort = message.reader().readShort();
                short readShort2 = message.reader().readShort();
                if (readShort2 == 0) {
                    for (int i2 = 0; i2 < GameScreen.vecWeather.size(); i2++) {
                        AnimateEffect animateEffect2 = (AnimateEffect) GameScreen.vecWeather.elementAt(i2);
                        if (animateEffect2.type == readByte) {
                            animateEffect2.isStop = true;
                            return;
                        }
                    }
                } else if (readByte == 0 || readByte == 2 || readByte == 1 || readByte == 4) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= GameScreen.vecWeather.size()) {
                            break;
                        }
                        AnimateEffect animateEffect3 = (AnimateEffect) GameScreen.vecWeather.elementAt(i3);
                        if (animateEffect3.type != readByte) {
                            i3++;
                        } else {
                            animateEffect3.isStop = true;
                            break;
                        }
                    }
                    GameScreen.AddEffWeather(readByte, true, readShort, readShort2);
                }
            }
        } catch (Exception e) {
        }
    }

    public void Num_Eff(Message message) {
        try {
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            String readUTF = message.reader().readUTF();
            byte readByte2 = message.reader().readByte();
            MainObject mainObject = MainObject.get_Object(readShort, readByte);
            if (mainObject != null && !mainObject.isRemove) {
                GameScreen.addEffectNum(readUTF, mainObject.x, mainObject.y - (mainObject.hOne / 2), 8, readByte2, readShort);
            }
        } catch (Exception e) {
        }
    }

    public void Rebuild_Wing(Message message) {
        Item itemInventory;
        try {
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                TabRebuildItem.isChetao = message.reader().readInt();
                TabRebuildItem.nameWing = message.reader().readUTF();
                TabRebuildItem.priceWing = message.reader().readInt();
                TabRebuildItem.lvReWing = message.reader().readShort();
                TabRebuildItem.timeUseWing = message.reader().readInt();
                int readByte2 = message.reader().readByte();
                TabRebuildItem.dataWing = new DataRebuildItem[readByte2];
                for (int i = 0; i < readByte2; i++) {
                    TabRebuildItem.dataWing[i] = new DataRebuildItem(message.reader().readShort(), message.reader().readShort());
                }
                TabRebuildItem.numWingMaterialInven = new int[readByte2];
                for (int i2 = 0; i2 < TabRebuildItem.dataWing.length; i2++) {
                    MainItem material = Item.getMaterial(TabRebuildItem.dataWing[i2].id);
                    int i3 = 0;
                    if (material != null && (itemInventory = Item.getItemInventory(material.ItemCatagory, (short) material.Id)) != null) {
                        i3 = itemInventory.numPotion;
                    }
                    TabRebuildItem.numWingMaterialInven[i2] = i3;
                }
            } else if (readByte == 1) {
                String readUTF = message.reader().readUTF();
                TabRebuildItem.itemWing = null;
                mVector mvector = new mVector("ReadMessenge vec12");
                mvector.addElement(new TabShopNew(Item.VecInvetoryPlayer, (byte) 0, T.tabhanhtrang, -1, TabShopNew.INVEN_AND_WING));
                TabRebuildItem tabRebuildItem = new TabRebuildItem(readUTF, TabRebuildItem.TYPE_REBUILD_WING);
                mvector.addElement(tabRebuildItem);
                GameCanvas.shopNpc = new TabScreenNew();
                GameCanvas.shopNpc.selectTab = 1;
                GameCanvas.shopNpc.addMoreTab(mvector);
                GameCanvas.shopNpc.Show(GameScreen.gI());
                GameCanvas.end_Dialog();
                tabRebuildItem.init();
            } else if (readByte == 2) {
                short readShort = message.reader().readShort();
                if (readShort == -1) {
                    TabRebuildItem.itemWing = null;
                } else {
                    Item itemInventory2 = Item.getItemInventory(3, readShort);
                    if (itemInventory2 != null && TabRebuildItem.isBeginEff == 0) {
                        TabRebuildItem.itemWing = MainItem.MainItem_Item(readShort, itemInventory2.itemNameExcludeLv, itemInventory2.imageId, itemInventory2.tier, itemInventory2.colorNameItem, itemInventory2.classcharItem, 3, itemInventory2.mInfo, itemInventory2.type_Only_Item, false, itemInventory2.IdTem, itemInventory2.priceItem, (short) itemInventory2.LvItem, itemInventory2.canSell, itemInventory2.canTrade, 0, (byte) 0, (byte) 0);
                    }
                }
            } else if (readByte == 5) {
                GameCanvas.end_Dialog();
                TabRebuildItem.contentShow = message.reader().readUTF();
                TabRebuildItem.idWingOk = message.reader().readShort();
                TabRebuildItem.isBeginEff = (byte) 1;
            } else if (readByte == 4) {
                GameCanvas.end_Dialog();
                GameCanvas.menu2.startAt_NPC(null, message.reader().readUTF(), Menu2.IdNPCLast, (byte) 2, false, 0);
            } else if (readByte == 6) {
                TabRebuildItem.itemWing = null;
                TabRebuildItem.dataWing = null;
                TabRebuildItem.priceWing = 0;
                TabRebuildItem.timeUseWing = 0;
                TabRebuildItem.nameWing = "";
                TabRebuildItem.isChetao = 0;
                TabRebuildItem.lvReWing = (short) 0;
                TabRebuildItem.idWingOk = (short) 0;
            }
        } catch (Exception e) {
        }
    }

    public void Open_Box(Message message) {
        mSystem.outz("<<<<<<<<< mo ruong >>>>>>>>>>>>");
        try {
            String readUTF = message.reader().readUTF();
            int readByte = message.reader().readByte();
            MainItem[] mainItemArr = new MainItem[readByte];
            for (int i = 0; i < readByte; i++) {
                mainItemArr[i] = new MainItem(message.reader().readUTF(), message.reader().readShort(), message.reader().readInt(), message.reader().readByte(), message.reader().readByte(), message.reader().readByte());
            }
            String readUTF2 = message.reader().readUTF();
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            if (readByte == 0) {
                return;
            }
            GameCanvas.start_Open_Box(readUTF, readUTF2, mainItemArr, readByte2, readByte3);
        } catch (Exception e) {
        }
    }

    public void UpdateDataAndroid(Message message) {
        mSystem.outz("--------- Nhan message -57");
        try {
            GameCanvas.IndexCharPar = message.reader().readShort();
            short readShort = message.reader().readShort();
            GameCanvas.start_Update_Data();
            MsgDialog.curupdate = 0;
            MsgDialog.maxupdate = readShort;
            mSystem.outz(new StringBuffer().append("==========").append((int) GameCanvas.IndexCharPar).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SoSanhDataAndroid(Message message) {
        mSystem.outz(" -------------------- Nhan message - 54");
        try {
            GameCanvas.IndexCharPar = message.reader().readShort();
            int readShort = message.reader().readShort();
            int i = 0;
            for (int i2 = 0; i2 < readShort; i2++) {
                byte readByte = message.reader().readByte();
                short readShort2 = message.reader().readShort();
                int readInt = message.reader().readInt();
                try {
                    boolean z = false;
                    byte[] loadRMS = CRes.loadRMS(new StringBuffer().append("img_data_char_").append((int) readByte).append("_").append((int) readShort2).toString());
                    if (loadRMS == null) {
                        z = true;
                    } else if (readInt != new DataInputStream(new ByteArrayInputStream(loadRMS)).readShort()) {
                        z = true;
                    }
                    if (z) {
                        GlobalService.gI().load_image_data_part_char(readByte, readShort2);
                        i++;
                    }
                } catch (Exception e) {
                }
            }
            if (i > 0) {
                GameCanvas.start_Update_Data();
                MsgDialog.curupdate = 0;
                MsgDialog.maxupdate = i;
            }
            mSystem.outz(new StringBuffer().append("==========").append((int) GameCanvas.IndexCharPar).toString());
        } catch (Exception e2) {
        }
    }

    public void useMount(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    byte readByte = message.reader().readByte();
                    MainObject mainObject = MainObject.get_Object(message.reader().readShort(), (byte) 0);
                    if (mainObject != null) {
                        mainObject.typeMount = readByte;
                        break;
                    }
                    break;
                case 1:
                    byte readByte2 = message.reader().readByte();
                    byte readByte3 = message.reader().readByte();
                    short readShort = message.reader().readShort();
                    short readShort2 = message.reader().readShort();
                    MainObject mainObject2 = MainObject.get_Object(readShort, (byte) 1);
                    if (mainObject2 != null) {
                        mainObject2.setEffectauto(readByte3, readShort2, readByte2);
                        break;
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int] */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    public void monsterSkillInfo(Message message) {
        try {
            byte readByte = message.reader().readByte();
            mSystem.outz(new StringBuffer().append("size = ").append((int) readByte).toString());
            for (byte b = 0; b < readByte; b++) {
                byte readByte2 = message.reader().readByte();
                message.reader().readByte();
                Monster_Skill.hashMonsterSkillInfo.put(new StringBuffer().append("").append((int) readByte2).toString(), new Short(message.reader().readShort()));
            }
        } catch (Exception e) {
        }
    }

    public void monsterDetonate(Message message) {
        try {
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            byte readByte3 = message.reader().readByte();
            byte readByte4 = message.reader().readByte();
            byte readByte5 = message.reader().readByte();
            byte readByte6 = message.reader().readByte();
            message.reader().readByte();
            message.reader().readByte();
            MainMonster mainMonster = (MainMonster) MainObject.get_Object(readShort, readByte2);
            switch (readByte) {
                case 0:
                    mainMonster.vMax = 8;
                    mainMonster.toX = readShort2;
                    mainMonster.toY = readShort3;
                    mainMonster.isDetonateInDest = true;
                    break;
                case 1:
                    switch (readByte3) {
                        case 0:
                            GameScreen.addEffectEndKill(38, mainMonster.x, mainMonster.y - 20);
                            break;
                        case 1:
                            GameScreen.addEffectKill(85, mainMonster.x, mainMonster.y, readByte4, readShort, readByte2);
                            break;
                        case 2:
                            GameScreen.addEffectKill(87, mainMonster.x, mainMonster.y, readByte4, readShort, readByte2);
                            break;
                        case 3:
                            GameScreen.addEffectEndKill(36, mainMonster.x, mainMonster.y);
                            break;
                    }
                    mainMonster.x = readShort2;
                    mainMonster.y = readShort3;
                    switch (readByte5) {
                        case 0:
                            GameScreen.addEffectEndKill(38, mainMonster.x, mainMonster.y - 20);
                            break;
                        case 1:
                            GameScreen.addEffectKill(85, mainMonster.x, mainMonster.y, readByte6, readShort, readByte2);
                            break;
                        case 2:
                            GameScreen.addEffectKill(87, mainMonster.x, mainMonster.y, readByte6, readShort, readByte2);
                            break;
                        case 3:
                            mainMonster.isDetonateInDest = true;
                            break;
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void petGainXP(Message message) {
        try {
            message.reader().readShort();
            message.reader().readShort();
            message.reader().readShort();
            message.reader().readInt();
        } catch (Exception e) {
        }
    }

    public void receiveLotteryReward(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                Item.VecLotteryReward.removeAllElements();
                int readByte2 = message.reader().readByte();
                for (int i = 0; i < readByte2; i++) {
                    byte readByte3 = message.reader().readByte();
                    if (readByte3 == 3) {
                        String readUTF = message.reader().readUTF();
                        byte readByte4 = message.reader().readByte();
                        short readShort = message.reader().readShort();
                        byte readByte5 = message.reader().readByte();
                        short readShort2 = message.reader().readShort();
                        byte readByte6 = message.reader().readByte();
                        short readShort3 = message.reader().readShort();
                        byte readByte7 = message.reader().readByte();
                        int readByte8 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte8];
                        for (int i2 = 0; i2 < readByte8; i2++) {
                            mainInfoItemArr[i2] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                        }
                        Item.VecLotteryReward.addElement(new MainItem(readShort, readUTF, readShort2, readByte6, readByte7, readByte4, readByte3, mainInfoItemArr, readByte5, false, (short) -1, 0L, readShort3, (byte) 0, (byte) 0, -1, (byte) 0, (byte) 0));
                    } else if (readByte3 == 4) {
                        short readShort4 = message.reader().readShort();
                        short readShort5 = message.reader().readShort();
                        MainTemplateItem mainTemplateItem = (MainTemplateItem) MainTemplateItem.hashPotionTem.get(new StringBuffer().append("").append((int) readShort4).toString());
                        Item.VecLotteryReward.addElement(new MainItem(readShort4, mainTemplateItem.IconId, mainTemplateItem.name, mainTemplateItem.contentPotion, readShort5, 4, mainTemplateItem.PricePoition, mainTemplateItem.typePotion, mainTemplateItem.moneyType, (byte) 0, (byte) 0));
                    } else if (readByte3 == 7) {
                        short readShort6 = message.reader().readShort();
                        short readShort7 = message.reader().readShort();
                        MainItem material = Item.getMaterial(readShort6);
                        if (material != null) {
                            Item.VecLotteryReward.addElement(MainItem.MainItem_Material(readShort6, material.itemName, material.imageId, 7, material.priceItem, material.priceType, material.content, material.value, material.typeMaterial, readShort7, (byte) 0, (byte) 0));
                        } else {
                            MainItem mainItem = new MainItem();
                            mainItem.Id = readShort6;
                            mainItem.ItemCatagory = 7;
                            mainItem.numPotion = readShort7;
                            Item.VecLotteryReward.addElement(mainItem);
                        }
                    }
                }
                mVector mvector = new mVector("ReadMessenge vec13");
                mvector.addElement(new TabLottery(T.moly, Item.VecLotteryReward, (byte) 0));
                GameCanvas.shopNpc = new TabScreenNew();
                GameCanvas.shopNpc.selectTab = 0;
                GameCanvas.shopNpc.addMoreTab(mvector);
                GameCanvas.shopNpc.Show(GameScreen.gI());
                GameCanvas.end_Dialog();
            } else if (readByte == 1) {
                byte readByte9 = message.reader().readByte();
                byte readByte10 = message.reader().readByte();
                TabLottery.changeSection((byte) 1);
                TabLottery.setLuckyNumber(readByte10);
                TabLottery.isReady = false;
                mSystem.outz(new StringBuffer().append("itemIndex = ").append((int) readByte9).toString());
                mSystem.outz(new StringBuffer().append("posIndex = ").append((int) readByte10).toString());
            } else if (readByte == 2) {
                if (message.reader().readByte() == 1) {
                    message.reader().readByte();
                    TabLottery.setLuckyNumber(message.reader().readByte());
                    TabLottery.isWin = true;
                } else {
                    byte readByte11 = message.reader().readByte();
                    message.reader().readByte();
                    TabLottery.setLuckyNumber(readByte11);
                    TabLottery.isWin = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeUserAccountInfoToRMS(Message message) {
        try {
            String readUTF = message.reader().readUTF();
            String readUTF2 = message.reader().readUTF();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeUTF(readUTF);
            dataOutputStream.writeUTF(readUTF2);
            CRes.saveRMS("user_pass", byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
            LoginScreen.userSv = readUTF;
            LoginScreen.tfusername.setText(readUTF);
            LoginScreen.tfpassword.setText(readUTF2);
            LoginScreen.username = readUTF;
            LoginScreen.password = readUTF2;
        } catch (Exception e) {
        }
    }

    public void UpdateInfoArena(Message message) {
        try {
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            short readShort = message.reader().readShort();
            byte readByte3 = message.reader().readByte();
            GameScreen.infoGame.setSttArena(readByte, readByte2, readShort);
            if (readByte3 == 0) {
                GameScreen.timeArena = message.reader().readLong();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMarkKiller(Message message) {
        try {
            byte readByte = message.reader().readByte();
            if (readByte == 0) {
                short readShort = message.reader().readShort();
                short readShort2 = message.reader().readShort();
                MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
                if (mainObject != null) {
                    mainObject.markKiller = readShort2;
                }
            } else if (readByte == 1) {
                ItemMap.isPaintDieHouseArena = true;
            } else if (readByte == 2) {
                short readShort3 = message.reader().readShort();
                byte readByte2 = message.reader().readByte();
                MainObject mainObject2 = MainObject.get_Object(readShort3, (byte) 1);
                if (mainObject2 != null && readByte2 != 0) {
                    mainObject2.addeffAuto(readByte2, mainObject2.x, mainObject2.y, 0, 0, readByte2 == 57 ? 2 : 0, 0);
                }
            }
        } catch (Exception e) {
        }
    }

    public void useItemArena(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    if (message.reader().readByte() == 1) {
                        EffectMonster.buffDame = true;
                        break;
                    } else {
                        EffectMonster.buffDame = false;
                        break;
                    }
                case 1:
                    short readShort = message.reader().readShort();
                    MainObject mainObject = MainObject.get_Object(message.reader().readShort(), (byte) 0);
                    if (mainObject != null) {
                        mainObject.idImageHenshin = readShort;
                        break;
                    }
                    break;
                case 2:
                    byte readByte = message.reader().readByte();
                    MainObject mainObject2 = MainObject.get_Object(message.reader().readShort(), (byte) 0);
                    if (mainObject2 != null) {
                        if (readByte == 1) {
                            mainObject2.isPaint_No = true;
                            break;
                        } else {
                            mainObject2.isPaint_No = false;
                            break;
                        }
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void npcServer(Message message) {
        try {
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            short readShort3 = message.reader().readShort();
            short readShort4 = message.reader().readShort();
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            byte readByte4 = message.reader().readByte();
            byte readByte5 = message.reader().readByte();
            String readUTF = message.reader().readUTF();
            String readUTF2 = message.reader().readUTF();
            byte readByte6 = message.reader().readByte();
            byte[] bArr = new byte[message.reader().readByte()];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) message.reader().readShort();
            }
            byte[] bArr2 = new byte[message.reader().readShort()];
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr2[i2] = message.reader().readByte();
            }
            byte[] bArr3 = new byte[message.reader().readByte()];
            for (int i3 = 0; i3 < bArr3.length; i3++) {
                bArr3[i3] = message.reader().readByte();
            }
            byte readByte7 = message.reader().readByte();
            for (int i4 = 0; i4 < GameScreen.Vecplayers.size(); i4++) {
                MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i4);
                if (mainObject.isNPC_server() && mainObject.ID == readByte7) {
                    mainObject.setInfo(readByte6, readShort, readShort2, readByte3, readByte4, readByte5, readUTF, readUTF2, readShort3, readShort4, readByte, readByte2, bArr, bArr2, bArr3);
                    return;
                }
            }
            NPCserver nPCserver = new NPCserver(readByte6, readShort, readShort2, readByte3, readByte4, readByte5, readUTF, readUTF2, readShort3, readShort4, readByte, readByte2, bArr, bArr2, bArr3);
            nPCserver.ID = readByte7;
            GameScreen.Vecplayers.addElement(nPCserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void useShip(Message message) {
        try {
            byte readByte = message.reader().readByte();
            short readShort = message.reader().readShort();
            GameScreen.player.useShip = true;
            if (readByte == 0) {
                GameScreen.infoGame.isShipMove = true;
                ShipScr.gI().setShipScr(readShort);
            } else if (readByte == 2) {
                ShipScr.gI().isSpeed = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ThachDau(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    short readShort = message.reader().readShort();
                    int readInt = message.reader().readInt();
                    int readInt2 = message.reader().readInt();
                    MainObject mainObject = MainObject.get_Object(readShort, (byte) 0);
                    if (mainObject != null) {
                        mainObject.mp = readInt;
                        mainObject.maxMp = readInt2;
                        break;
                    }
                    break;
                case 1:
                    PaintInfoGameScreen.timeThachdau = (mSystem.currentTimeMillis() / 1000) + message.reader().readByte();
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void StoreInfo(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 1:
                    short readShort = message.reader().readShort();
                    String readUTF = message.reader().readUTF();
                    MainObject findChar = GameScreen.findChar(readShort);
                    if (findChar != null) {
                        findChar.setNameStore(readUTF);
                        break;
                    }
                    break;
                case 2:
                    short readShort2 = message.reader().readShort();
                    MainObject findChar2 = GameScreen.findChar(readShort2);
                    if (GameScreen.player != null && readShort2 == GameScreen.player.ID) {
                        Item.VecItem_Sell_in_store.removeAllElements();
                        Item.VecItemSell.removeAllElements();
                    }
                    if (findChar2 != null) {
                        findChar2.removeNameStore();
                        break;
                    }
                    break;
                case 3:
                    Item.VecItemSell.removeAllElements();
                    int readByte = message.reader().readByte();
                    for (int i = 0; i < readByte; i++) {
                        byte readByte2 = message.reader().readByte();
                        if (readByte2 == 3) {
                            short readShort3 = message.reader().readShort();
                            String readUTF2 = message.reader().readUTF();
                            byte readByte3 = message.reader().readByte();
                            byte readByte4 = message.reader().readByte();
                            short readShort4 = message.reader().readShort();
                            byte readByte5 = message.reader().readByte();
                            short readShort5 = message.reader().readShort();
                            byte readByte6 = message.reader().readByte();
                            int readByte7 = message.reader().readByte();
                            MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte7];
                            for (int i2 = 0; i2 < readByte7; i2++) {
                                mainInfoItemArr[i2] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                            }
                            MainItem MainItem_Item = MainItem.MainItem_Item(readShort3, readUTF2, readShort4, readByte5, readByte6, readByte3, 3, mainInfoItemArr, readByte4, false, readShort3, 0L, readShort5, (byte) 0, (byte) 0, 0, message.reader().readByte(), (byte) 0);
                            MainItem_Item.isSell = true;
                            Item.VecItemSell.addElement(MainItem_Item);
                        }
                        if (readByte2 == 7) {
                            short readShort6 = message.reader().readShort();
                            short readShort7 = message.reader().readShort();
                            byte readByte8 = message.reader().readByte();
                            byte readByte9 = message.reader().readByte();
                            long readLong = message.reader().readLong();
                            MainItem material = Item.getMaterial(readShort6);
                            if (material != null) {
                                MainItem MainItem_Material = MainItem.MainItem_Material(readShort6, material.itemName, material.imageId, 7, readLong, (byte) 0, material.content, material.value, material.typeMaterial, readShort7, readByte8, readByte9);
                                MainItem_Material.isSell = true;
                                Item.VecItemSell.addElement(MainItem_Material);
                            } else {
                                MainItem mainItem = new MainItem();
                                mainItem.Id = readShort6;
                                mainItem.numPotion = readShort7;
                                mainItem.canSell = readByte8;
                                mainItem.canTrade = readByte9;
                                mainItem.priceItem = readLong;
                                mainItem.priceType = (byte) 0;
                                mainItem.isSell = true;
                                mainItem.ItemCatagory = 7;
                                Item.put_Material(readShort6);
                                Item.VecItemSell.addElement(mainItem);
                            }
                        }
                        if (readByte2 == 4) {
                            short readShort8 = message.reader().readShort();
                            short readShort9 = message.reader().readShort();
                            long readInt = message.reader().readInt();
                            MainTemplateItem potionTem = MainTemplateItem.getPotionTem(readShort8);
                            if (potionTem != null) {
                                Item.VecItemSell.addElement(new MainItem(potionTem.ID, potionTem.IconId, potionTem.name, new StringBuffer().append(potionTem.contentPotion).append(". ").append(T.price).append(" ").append(MainItem.getDotNumber(readInt)).append(" ").append(T.coin).toString(), readShort9, 4, readInt, potionTem.typePotion, potionTem.moneyType, (byte) 0, (byte) 0));
                            }
                        }
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void MiNuongInfo(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    short readShort = message.reader().readShort();
                    String readUTF = message.reader().readUTF();
                    MainObject findMonster = GameScreen.findMonster(readShort);
                    if (findMonster != null) {
                        findMonster.SetnameOwner(readUTF);
                        break;
                    }
                    break;
                case 1:
                    short readShort2 = message.reader().readShort();
                    short readShort3 = message.reader().readShort();
                    GameScreen.infoGame.idCharLoiDai1 = readShort2;
                    GameScreen.infoGame.idCharLoiDai2 = readShort3;
                    break;
                case 2:
                    short readShort4 = message.reader().readShort();
                    short readShort5 = message.reader().readShort();
                    short readShort6 = message.reader().readShort();
                    short readShort7 = message.reader().readShort();
                    int readByte = message.reader().readByte();
                    MainObject findObj = GameScreen.findObj(readShort4);
                    if (findObj != null) {
                        for (int i = 0; i < readByte; i++) {
                            short readShort8 = message.reader().readShort();
                            int readInt = message.reader().readInt();
                            MainObject findObj2 = GameScreen.findObj(readShort8);
                            if (findObj2 != null) {
                                GameScreen.startNewMagicBeam(15, findObj, findObj2, findObj.x, findObj.y, readInt, readShort5, readShort6, readShort7);
                            }
                        }
                        break;
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void infoclanChiemthanh(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    int readInt = message.reader().readInt();
                    String readUTF = message.reader().readUTF();
                    PaintInfoGameScreen.idicon = readInt;
                    PaintInfoGameScreen.nameclan = readUTF;
                    break;
                case 1:
                    GameScreen.vecTimecountDown.removeAllElements();
                    int readByte = message.reader().readByte();
                    for (int i = 0; i < readByte; i++) {
                        short readShort = message.reader().readShort();
                        String readUTF2 = message.reader().readUTF();
                        if (readShort > 0) {
                            GameScreen.vecTimecountDown.addElement(new TimecountDown(readShort, readUTF2, GameCanvas.w / 2, GameCanvas.hText + 10 + (i * GameCanvas.hText)));
                        }
                    }
                    break;
                case 2:
                    int readByte2 = message.reader().readByte();
                    if (readByte2 > 0) {
                        GameScreen.textServer = new String[readByte2];
                        for (int i2 = 0; i2 < readByte2; i2++) {
                            GameScreen.textServer[i2] = message.reader().readUTF();
                        }
                        break;
                    } else {
                        GameScreen.textServer = null;
                        break;
                    }
            }
        } catch (Exception e) {
        }
    }

    public void onHopRac(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    TabRebuildItem.vecGem.removeAllElements();
                    int readByte = message.reader().readByte();
                    short readShort = message.reader().readShort();
                    for (int i = 0; i < readByte; i++) {
                        MainItem material = Item.getMaterial(readShort);
                        if (material != null) {
                            TabRebuildItem.vecGem.addElement(material);
                        } else {
                            MainItem mainItem = new MainItem();
                            mainItem.Id = readShort;
                            TabRebuildItem.itemRe.ItemCatagory = 7;
                            TabRebuildItem.vecGem.addElement(mainItem);
                        }
                    }
                    break;
                case 1:
                    TabRebuildItem.vecGem.removeAllElements();
                    break;
                case 2:
                    GameCanvas.end_Dialog();
                    TabRebuildItem.isBeginEff = (byte) 1;
                    TabRebuildItem.isNextRebuild = message.reader().readByte();
                    TabRebuildItem.contentShow = message.reader().readUTF();
                    TabRebuildItem.vecGem.removeAllElements();
                    short readShort2 = message.reader().readShort();
                    byte readByte2 = message.reader().readByte();
                    if (readShort2 != -1 && readByte2 == 7) {
                        MainItem material2 = Item.getMaterial(readShort2);
                        if (material2 != null) {
                            TabRebuildItem.itemRe = material2;
                        } else {
                            TabRebuildItem.itemRe = new MainItem();
                            TabRebuildItem.itemRe.Id = readShort2;
                            TabRebuildItem.itemRe.ItemCatagory = 7;
                        }
                        break;
                    }
                    break;
                case 3:
                    GameCanvas.end_Dialog();
                    TabRebuildItem.isBeginEff = (byte) 1;
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.isNextRebuild = message.reader().readByte();
                    TabRebuildItem.contentShow = message.reader().readUTF();
                    TabRebuildItem.vecGem.removeAllElements();
                    if (TabRebuildItem.isNextRebuild == 3) {
                        byte readByte3 = message.reader().readByte();
                        String readUTF = message.reader().readUTF();
                        byte readByte4 = message.reader().readByte();
                        short readShort3 = message.reader().readShort();
                        byte readByte5 = message.reader().readByte();
                        short readShort4 = message.reader().readShort();
                        byte readByte6 = message.reader().readByte();
                        short readShort5 = message.reader().readShort();
                        byte readByte7 = message.reader().readByte();
                        byte readByte8 = message.reader().readByte();
                        byte readByte9 = message.reader().readByte();
                        int readByte10 = message.reader().readByte();
                        MainInfoItem[] mainInfoItemArr = new MainInfoItem[readByte10];
                        for (int i2 = 0; i2 < readByte10; i2++) {
                            mainInfoItemArr[i2] = new MainInfoItem(message.reader().readUnsignedByte(), message.reader().readInt());
                        }
                        int readInt = message.reader().readInt();
                        byte readByte11 = message.reader().readByte();
                        int i3 = -1;
                        String str = "-1";
                        if (message.reader().readByte() == 1) {
                            i3 = message.reader().readInt();
                            str = message.reader().readUTF();
                        }
                        byte readByte12 = message.reader().readByte();
                        MainItem mainItem2 = new MainItem(readShort3, readUTF, readShort4, readByte6, readByte7, readByte4, readByte3, mainInfoItemArr, readByte5, false, (short) -1, 0L, readShort5, readByte8, readByte9, readInt, (byte) 0, readByte11, i3, Long.parseLong(str));
                        mainItem2.setCanShell_notCanTrade(readByte12);
                        TabRebuildItem.itemRe = mainItem2;
                        break;
                    }
                    break;
                case 4:
                    int readByte13 = message.reader().readByte();
                    TabRebuildItem.vecGem.removeAllElements();
                    for (int i4 = 0; i4 < readByte13; i4++) {
                        short readShort6 = message.reader().readShort();
                        short readShort7 = message.reader().readShort();
                        if (readShort6 != -1 && 7 == 7) {
                            TabRebuildItem.numColor[i4] = getColorNum(readShort6, readShort7);
                            TabRebuildItem.numofGem[i4] = getInfoNum(readShort6, readShort7);
                            MainItem material3 = Item.getMaterial(readShort6);
                            if (material3 != null) {
                                TabRebuildItem.vecGem.addElement(material3);
                            } else {
                                MainItem mainItem3 = new MainItem();
                                mainItem3.Id = readShort6;
                                mainItem3.ItemCatagory = 7;
                                TabRebuildItem.vecGem.addElement(mainItem3);
                            }
                        }
                    }
                    break;
                case 5:
                    GameCanvas.end_Dialog();
                    TabRebuildItem.isBeginEff = (byte) 1;
                    TabRebuildItem.itemRe = null;
                    TabRebuildItem.isNextRebuild = message.reader().readByte();
                    TabRebuildItem.contentShow = message.reader().readUTF();
                    TabRebuildItem.vecGem.removeAllElements();
                    short readShort8 = message.reader().readShort();
                    Item itemInventory = Item.getItemInventory(3, readShort8);
                    if (itemInventory != null) {
                        TabRebuildItem.itemRe = MainItem.MainItem_Item(readShort8, itemInventory.itemNameExcludeLv, itemInventory.imageId, itemInventory.tier, itemInventory.colorNameItem, itemInventory.classcharItem, 3, itemInventory.mInfo, itemInventory.type_Only_Item, false, itemInventory.IdTem, itemInventory.priceItem, (short) itemInventory.LvItem, itemInventory.canSell, itemInventory.canTrade, 0, (byte) 0, (byte) 0);
                        break;
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void Material_Template(Message message) {
        try {
            short readShort = message.reader().readShort();
            short readShort2 = message.reader().readShort();
            long readLong = message.reader().readLong();
            String readUTF = message.reader().readUTF();
            String readUTF2 = message.reader().readUTF();
            byte readByte = message.reader().readByte();
            byte readByte2 = message.reader().readByte();
            byte readByte3 = message.reader().readByte();
            short readShort3 = message.reader().readShort();
            byte readByte4 = message.reader().readByte();
            byte readByte5 = message.reader().readByte();
            MainItem mainItem = (MainItem) MainTemplateItem.hashMaterialTem.get(new StringBuffer().append((int) readShort).append("").toString());
            if (mainItem != null) {
                mainItem.setinfo(readShort, readUTF, readShort2, 7, readLong, readByte2, readUTF2, readShort3, readByte, (short) 1, readByte3, readByte4);
                mainItem.setColorName(readByte5);
            }
            for (int i = 0; i < Item.VecInvetoryPlayer.size(); i++) {
                MainItem mainItem2 = (MainItem) Item.VecInvetoryPlayer.elementAt(i);
                if (mainItem2 != null && mainItem2.ItemCatagory == 7 && mainItem2.Id == readShort) {
                    mainItem2.setinfo(readShort, readUTF, readShort2, 7, readLong, readByte2, readUTF2, readShort3, readByte, (short) 1, readByte3, readByte4);
                    mainItem2.setColorName(readByte5);
                }
            }
        } catch (Exception e) {
        }
    }

    public void onFillRec_Time(Message message) {
        try {
            switch (message.reader().readByte()) {
                case 0:
                    GameCanvas.game.isFullScreen = false;
                    GameCanvas.game.colorRec = message.reader().readInt();
                    GameCanvas.game.xRec = message.reader().readShort();
                    GameCanvas.game.yRec = message.reader().readShort();
                    GameCanvas.game.wRec = message.reader().readShort();
                    GameCanvas.game.hRec = message.reader().readShort();
                    break;
                case 1:
                    GameCanvas.game.colorRec = message.reader().readInt();
                    GameCanvas.game.isFullScreen = true;
                    break;
                case 2:
                    byte readByte = message.reader().readByte();
                    short readShort = message.reader().readShort();
                    short readShort2 = message.reader().readShort();
                    String readUTF = message.reader().readUTF();
                    byte b = 0;
                    if (readByte == 3 || readByte == 4 || readByte == 7) {
                        b = message.reader().readByte();
                    }
                    GameScreen.addPlayer(new Item_Drop(readShort2, readByte, readUTF, message.reader().readShort(), message.reader().readShort(), readShort, b));
                    break;
                case 3:
                    int readInt = message.reader().readInt();
                    GameCanvas.game.imgCombo = null;
                    if (readInt > 0) {
                        byte[] bArr = new byte[readInt];
                        if (readInt > 0) {
                            for (int i = 0; i < readInt; i++) {
                                bArr[i] = message.reader().readByte();
                            }
                            GameCanvas.game.imgCombo = mImage.createImage(bArr, 0, bArr.length, "");
                            GameCanvas.game.isCombo = true;
                        }
                        break;
                    } else {
                        GameCanvas.game.imgCombo = null;
                        GameScreen.player.timeCombo = -1;
                        break;
                    }
                case 4:
                    GameScreen.player.resetCoolDown();
                    GameScreen.player.timeCombo = message.reader().readInt();
                    break;
                case 5:
                    if (message.reader().readByte() == 1) {
                        onContinueFire();
                        break;
                    }
                    break;
                case 6:
                    int readInt2 = message.reader().readInt();
                    String readUTF2 = message.reader().readUTF();
                    if (readInt2 > 0) {
                        GameScreen.charcountdonw = new CharCountDown(mSystem.currentTimeMillis() + (readInt2 * ObjectData.MONSTER), readUTF2);
                    }
                    if (readInt2 < 0) {
                        GameScreen.charcountdonw = null;
                        break;
                    }
                    break;
                case 7:
                    GameScreen.vecDataeff.addElement(new DataSkillEff(message.reader().readShort(), message.reader().readShort(), message.reader().readShort(), (byte[]) null));
                    break;
                case 8:
                    short readShort3 = message.reader().readShort();
                    byte readByte2 = message.reader().readByte();
                    short readShort4 = message.reader().readShort();
                    if (readByte2 == 0) {
                        MainObject findChar = GameScreen.findChar(readShort4);
                        if (findChar != null) {
                            GameScreen.vecDataeff.addElement(new DataSkillEff(readShort3, findChar.x, findChar.y, (byte[]) null));
                        }
                        break;
                    } else {
                        MainObject findMonster = GameScreen.findMonster(readShort4);
                        if (findMonster != null) {
                            GameScreen.vecDataeff.addElement(new DataSkillEff(readShort3, findMonster.x, findMonster.y, (byte[]) null));
                        }
                        break;
                    }
            }
        } catch (Exception e) {
        }
    }

    public void onUpSkill(Message message) {
    }

    public void onContinueFire() {
        try {
            GameScreen.ObjFocus = null;
            GameScreen.player.nextMonster();
            GameScreen.player.dofire_();
        } catch (Exception e) {
        }
    }
}
