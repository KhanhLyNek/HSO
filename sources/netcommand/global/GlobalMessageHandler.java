package netcommand.global;

import CLib.mSystem;
import GameEffect.EffectSkill;
import Main.GameCanvas;
import com.silverknight.TemMidlet;
import net.IMessageHandler;
import net.Message;
import netcommand.Cmd_Message;
import netcommand.IMiniGameMsgHandler;

/* loaded from: KnightOnline_v403.jar:netcommand/global/GlobalMessageHandler.class */
public class GlobalMessageHandler extends Cmd_Message implements IMessageHandler {
    public GlobalLogicHandler globalLogicHandler = new GlobalLogicHandler();
    public static GlobalMessageHandler me;
    public IMiniGameMsgHandler miniGameMessageHandler;

    public static GlobalMessageHandler gI() {
        if (me == null) {
            me = new GlobalMessageHandler();
        }
        return me;
    }

    public void setGameLogicHandler(GlobalLogicHandler globalLogicHandler) {
        this.globalLogicHandler = globalLogicHandler;
    }

    @Override // net.IMessageHandler
    public void onConnectOK() {
        this.globalLogicHandler.onConnectOK();
    }

    @Override // net.IMessageHandler
    public void onConnectionFail() {
        this.globalLogicHandler.onConnectFail();
    }

    @Override // net.IMessageHandler
    public void onDisconnected() {
        GlobalLogicHandler.onDisconnect();
    }

    @Override // net.IMessageHandler
    public void onMessage(Message message) {
        switch (message.command) {
            case Cmd_Message.SHOW_INFO /* -110 */:
                GameCanvas.readMessenge.onInfoMount(message);
                return;
            case Cmd_Message.GET_NAP_STORE_APPLE /* -109 */:
            case Cmd_Message.USE_MATERIAL /* -107 */:
            case -89:
            case -88:
            case -87:
            case -86:
            case -85:
            case -84:
            case -83:
            case -82:
            case -81:
            case -80:
            case -79:
            case -78:
            case -77:
            case -74:
            case -73:
            case -72:
            case -71:
            case -70:
            case -69:
            case -68:
            case -67:
            case -66:
            case -65:
            case -64:
            case -63:
            case -62:
            case -61:
            case -60:
            case -59:
            case -58:
            case Cmd_Message.NAP_DIAMOND /* -56 */:
            case -55:
            case -48:
            case -47:
            case -46:
            case -45:
            case -43:
            case -42:
            case -41:
            case -40:
            case -39:
            case -38:
            case -37:
            case -36:
            case -35:
            case -34:
            case -33:
            case -29:
            case Cmd_Message.MONSTER_CAPCHAR /* -28 */:
            case -27:
            case -26:
            case -25:
            case -24:
            case -23:
            case -22:
            case -21:
            case -20:
            case -19:
            case -18:
            case -17:
            case -16:
            case -15:
            case -14:
            case -13:
            case -12:
            case -11:
            case -10:
            case -9:
            case -8:
            case -7:
            case -6:
            case -5:
            case -4:
            case -3:
            case -2:
            case -1:
            case 0:
            case 11:
            case 14:
            case 18:
            case 24:
            case 38:
            case 43:
            case 45:
            case 46:
            case 47:
            case 58:
            case 66:
            case 71:
            case 72:
            case 79:
            case EffectSkill.EFF_KILL_GHOST /* 80 */:
            case EffectSkill.EFF_PHO_BANG /* 81 */:
            case EffectSkill.EFF_SUNG_SET_NEW /* 82 */:
            case 83:
            case 87:
            case EffectSkill.EFF_DAN_FOCUS /* 88 */:
            case 89:
            default:
                return;
            case Cmd_Message.FILL_REC_UPDATE_TIME /* -108 */:
                GameCanvas.readMessenge.onFillRec_Time(message);
                return;
            case Cmd_Message.GET_MATERITAL_TEMPLATE /* -106 */:
                GameCanvas.readMessenge.Material_Template(message);
                return;
            case Cmd_Message.HOP_RAC /* -105 */:
                GameCanvas.readMessenge.onHopRac(message);
                return;
            case Cmd_Message.UPDATE_INFO_CLAN_HOLD /* -104 */:
                GameCanvas.readMessenge.infoclanChiemthanh(message);
                return;
            case Cmd_Message.INFO_MI_NUONG /* -103 */:
                GameCanvas.readMessenge.MiNuongInfo(message);
                return;
            case Cmd_Message.MUA_BAN /* -102 */:
                GameCanvas.readMessenge.StoreInfo(message);
                return;
            case Cmd_Message.COMPETITION /* -101 */:
                GameCanvas.readMessenge.ThachDau(message);
                return;
            case Cmd_Message.KHAM_NGOC /* -100 */:
                GameCanvas.readMessenge.khamNgoc(message);
                return;
            case Cmd_Message.LAST_LOGIN /* -99 */:
                GameCanvas.readMessenge.lastlogout(message);
                return;
            case Cmd_Message.USE_SHIP /* -98 */:
                GameCanvas.readMessenge.useShip(message);
                return;
            case Cmd_Message.USE_MOUNT /* -97 */:
                GameCanvas.readMessenge.useMount(message);
                return;
            case Cmd_Message.SERVER_ADD_NPC /* -96 */:
                GameCanvas.readMessenge.npcServer(message);
                return;
            case Cmd_Message.MARKKILLER /* -95 */:
                GameCanvas.readMessenge.updateMarkKiller(message);
                return;
            case Cmd_Message.STATUS_ARENA /* -94 */:
                GameCanvas.readMessenge.UpdateInfoArena(message);
                return;
            case Cmd_Message.IN_APP_PURCHASE /* -93 */:
                mSystem.println("--Nap winphone ");
                return;
            case Cmd_Message.USE_ITEM_ARENA /* -92 */:
                GameCanvas.readMessenge.useItemArena(message);
                return;
            case Cmd_Message.MINI_GAME /* -91 */:
                GameCanvas.readMessenge.receiveLotteryReward(message);
                return;
            case -90:
            case 90:
                GameCanvas.readMessenge.remove_Actor(message);
                return;
            case Cmd_Message.NOKIA_PURCHASE_MESSAGE /* -76 */:
                TemMidlet.handleMessage(message);
                return;
            case Cmd_Message.GOOGLE_PURCHASE_MESSAGE /* -75 */:
                TemMidlet.handleMessage(message);
                return;
            case Cmd_Message.UPDATE_DATA /* -57 */:
                GameCanvas.readMessenge.UpdateDataAndroid(message);
                return;
            case Cmd_Message.CHECK_UPDATE_DATA /* -54 */:
                GameCanvas.readMessenge.SoSanhDataAndroid(message);
                return;
            case Cmd_Message.NAP_TIEN /* -53 */:
                GameCanvas.readMessenge.nap_tien(message);
                return;
            case Cmd_Message.LOAD_IMAGE_DATA_PART_CHAR /* -52 */:
                GameCanvas.readMessenge.loadImageDataCharPart(message);
                return;
            case Cmd_Message.LOAD_IMAGE /* -51 */:
                GameCanvas.readMessenge.loadImage(message);
                return;
            case Cmd_Message.NPC_BIG /* -50 */:
                GameCanvas.readMessenge.npcBig(message);
                return;
            case Cmd_Message.LOAD_IMAGE_DATA_AUTO_EFF /* -49 */:
                GameCanvas.readMessenge.loadImageDataAutoEff(message);
                return;
            case Cmd_Message.NEW_NPC_INFO /* -44 */:
                GameCanvas.readMessenge.newNPCInfo(message);
                return;
            case Cmd_Message.DIALOG_SERVER /* -32 */:
                GameCanvas.readMessenge.Dialog_server(message);
                return;
            case Cmd_Message.DIALOG_MORE_OPTION_SERVER /* -31 */:
                GameCanvas.readMessenge.Dialog_More_server(message);
                return;
            case Cmd_Message.DYNAMIC_MENU /* -30 */:
                GameCanvas.readMessenge.Dynamic_Menu(message);
                return;
            case 1:
                GameCanvas.readMessenge.Login_Ok(message);
                if (TemMidlet.currentIAPStore == 2 || mSystem.isIpAppstore()) {
                    TemMidlet.handleAllMessage(message);
                    return;
                }
                return;
            case 2:
                GameCanvas.readMessenge.Login_Fail(message);
                return;
            case 3:
                GameCanvas.readMessenge.mainCharInfo(message);
                return;
            case 4:
                GameCanvas.readMessenge.objectMove(message);
                return;
            case 5:
                GameCanvas.readMessenge.charInfo(message);
                return;
            case 6:
                GameCanvas.readMessenge.firePK(message);
                return;
            case 7:
                GameCanvas.readMessenge.monsterInfo(message);
                return;
            case 8:
                GameCanvas.readMessenge.playerExit(message);
                return;
            case 9:
                GameCanvas.readMessenge.fireMonster(message);
                return;
            case 10:
                GameCanvas.readMessenge.monsterFire(message);
                return;
            case 12:
                GameCanvas.readMessenge.changeMap(message);
                return;
            case 13:
                GameCanvas.readMessenge.listChar(message);
                return;
            case 15:
                GameCanvas.readMessenge.charWearing(message);
                return;
            case 16:
                GameCanvas.readMessenge.charInventory(message);
                return;
            case 17:
                GameCanvas.readMessenge.dieMonster(message);
                return;
            case 19:
                GameCanvas.readMessenge.ItemDrop(message);
                return;
            case 20:
                GameCanvas.readMessenge.GetItemMap(message);
                return;
            case 21:
                GameCanvas.readMessenge.Item_More_Info(message);
                return;
            case 22:
                GameCanvas.readMessenge.onUpSkill(message);
                return;
            case 23:
                GameCanvas.readMessenge.npcInfo(message);
                return;
            case 25:
                GameCanvas.readMessenge.itemTemplate(message);
                return;
            case 26:
                GameCanvas.readMessenge.catalogyMonster(message);
                return;
            case 27:
                GameCanvas.readMessenge.chatPopup(message);
                return;
            case 28:
                GameCanvas.readMessenge.get_Item_Tem(message);
                return;
            case 29:
                GameCanvas.readMessenge.Skill_List(message);
                return;
            case 30:
                GameCanvas.readMessenge.Set_XP(message);
                return;
            case 31:
                GameCanvas.readMessenge.writeUserAccountInfoToRMS(message);
                return;
            case 32:
                GameCanvas.readMessenge.use_Potion(message);
                return;
            case 33:
                GameCanvas.readMessenge.Level_Up(message);
                return;
            case 34:
                GameCanvas.readMessenge.chatTab(message);
                return;
            case 35:
                GameCanvas.readMessenge.Friend(message);
                return;
            case 36:
                GameCanvas.readMessenge.Buy_Sell(message);
                return;
            case 37:
                GameCanvas.readMessenge.InfoServer_Download(message);
                return;
            case 39:
                GameCanvas.readMessenge.Register(message);
                return;
            case 40:
                GameCanvas.readMessenge.Buff(message);
                return;
            case 41:
                GameCanvas.readMessenge.diePlayer(message);
                return;
            case 42:
                GameCanvas.readMessenge.pk(message);
                return;
            case 44:
                GameCanvas.readMessenge.UpdatePetContainer(message);
                return;
            case 48:
                GameCanvas.readMessenge.Party(message);
                return;
            case 49:
                GameCanvas.readMessenge.other_player_info(message);
                return;
            case 50:
                GameCanvas.readMessenge.eff_plus_time(message);
                return;
            case 51:
                GameCanvas.readMessenge.changeArea(message);
                return;
            case 52:
                GameCanvas.readMessenge.onReceiveInfoQuest(message);
                return;
            case 53:
                GameCanvas.readMessenge.InfoEasyFromServer(message);
                return;
            case 54:
                GameCanvas.readMessenge.update_Status_Area(message);
                return;
            case 55:
                GameCanvas.readMessenge.Save_RMS_Server(message);
                return;
            case 56:
                GameCanvas.readMessenge.List_Server(message);
                return;
            case 57:
                GameCanvas.readMessenge.List_Pk(message);
                return;
            case 59:
                GameCanvas.readMessenge.suckhoe(message);
                return;
            case 60:
                GameCanvas.readMessenge.chat_npc(message);
                return;
            case 61:
                GameCanvas.readMessenge.name_server(message);
                return;
            case 62:
                GameCanvas.readMessenge.x2_Xp(message);
                return;
            case 63:
                GameCanvas.readMessenge.delete_rms(message);
                return;
            case 64:
                GameCanvas.readMessenge.Help_From_Server(message);
                return;
            case 65:
                GameCanvas.readMessenge.CharChest(message);
                return;
            case 67:
                GameCanvas.readMessenge.Rebuild_Item(message);
                return;
            case 68:
                GameCanvas.readMessenge.Thach_Dau(message);
                return;
            case 69:
                GameCanvas.readMessenge.Clan(message);
                return;
            case 70:
                GameCanvas.readMessenge.updateHpNPC(message);
                return;
            case 73:
                GameCanvas.readMessenge.ReplacePlusItem(message);
                return;
            case 74:
                GameCanvas.readMessenge.Num_Eff(message);
                return;
            case 75:
                GameCanvas.readMessenge.EffFormServer(message);
                return;
            case 76:
                GameCanvas.readMessenge.EffWeather(message);
                return;
            case 77:
                GameCanvas.readMessenge.Rebuild_Wing(message);
                return;
            case 78:
                GameCanvas.readMessenge.Open_Box(message);
                return;
            case 84:
                GameCanvas.readMessenge.petAttack(message);
                return;
            case 85:
                GameCanvas.readMessenge.monsterDetonate(message);
                return;
            case 86:
                GameCanvas.readMessenge.monsterSkillInfo(message);
                return;
        }
    }
}
