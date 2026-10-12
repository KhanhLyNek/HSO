package netcommand.global;

import CLib.TField;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.Object_Effect_Skill;
import GameObjects.item_sell;
import GameScreen.GameScreen;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.T;
import Skill.ListSkill;
import netcommand.Cmd_Message;

/* loaded from: KnightOnline_v403.jar:netcommand/global/GlobalService.class */
public class GlobalService extends Cmd_Message {
    protected static GlobalService instance;
    long timeCheck;

    public static GlobalService gI() {
        if (instance == null) {
            instance = new GlobalService();
        }
        return instance;
    }

    public void login(String str, String str2, String str3, String str4, String str5, String str6, int i, byte b) {
        init((byte) 1);
        try {
            this.m.writer().writeUTF(str);
            this.m.writer().writeUTF(str2);
            this.m.writer().writeUTF(str3);
            this.m.writer().writeUTF(str4);
            this.m.writer().writeUTF(str5);
            this.m.writer().writeUTF(str6);
            this.m.writer().writeByte(mGraphics.zoomLevel);
            this.m.writer().writeByte(GameCanvas.device);
            this.m.writer().writeInt(i);
            this.m.writer().writeByte(b);
            this.m.writer().writeByte((byte) (TField.isQwerty ? 1 : 0));
            this.m.writer().writeByte(GameCanvas.IndexRes);
            this.m.writer().writeByte(LoginScreen.indexInfoLogin);
            this.m.writer().writeByte(GameCanvas.lowGraphic ? 1 : 0);
            this.m.writer().writeShort(GameCanvas.IndexCharPar);
            this.m.writer().writeUTF(GameCanvas.stringPackageName);
            GlobalLogicHandler.isDisConect = false;
            GlobalLogicHandler.timeReconnect = 0L;
            GlobalLogicHandler.isMelogin = false;
        } catch (Exception e) {
        }
        send();
        if (i == -1) {
            GameCanvas.start_Wait_Dialog(T.dangdangnhap, new iCommand(T.close, 7));
            GameCanvas.countLogin = mSystem.currentTimeMillis();
        } else {
            GameCanvas.start_Wait_Dialog(T.dangketnoilai, new iCommand(T.close, 7));
        }
    }

    public void infoTranpost() {
    }

    public void player_move(short s, short s2) {
        init((byte) 4);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
        } catch (Exception e) {
        }
        send();
    }

    public void char_info(short s) {
        init((byte) 5);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void requestInapPurchare(byte b, String str, byte b2) {
        init((byte) -93);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void new_npc_info(short s) {
        init((byte) -44);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void monster_info(short s) {
        init((byte) 7);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void Ok_Change_Map() {
        init((byte) 12);
        send();
    }

    public void fire_monster(mVector mvector, byte b) {
        init((byte) 9);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(mvector.size());
            for (int i = 0; i < mvector.size(); i++) {
                this.m.writer().writeShort(((Object_Effect_Skill) mvector.elementAt(i)).ID);
            }
            ListSkill.doSetTimeAtt();
        } catch (Exception e) {
        }
        send();
    }

    public void nap_tien(short s, String[] strArr) {
        init((byte) -53);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(strArr.length);
            for (String str : strArr) {
                this.m.writer().writeUTF(str);
            }
        } catch (Exception e) {
        }
        send();
    }

    public void load_image_data_part_char(byte b, short s) {
        if (GameCanvas.currentScreen == GameCanvas.selectChar) {
        }
        init((byte) -52);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void load_image_data_auto_eff(short s) {
        init((byte) -49);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void load_image(short s) {
        init((byte) -51);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void create_char(byte b, String str, byte b2, byte b3, byte b4, byte b5) {
        init((byte) 14);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
            this.m.writer().writeByte(b2);
            this.m.writer().writeByte(b3);
            this.m.writer().writeByte(b4);
        } catch (Exception e) {
        }
        send();
    }

    public void select_char(byte b, int i) {
        init((byte) 13);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeInt(i);
        } catch (Exception e) {
        }
        send();
        GameCanvas.start_Wait_Dialog(T.pleaseWait, null);
    }

    public void getlist_from_npc(byte b) {
        init((byte) 23);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void buy_item(byte b, short s, short s2) {
        init((byte) 24);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
        } catch (Exception e) {
        }
        send();
    }

    public void quest(short s, byte b, byte b2) {
        init((byte) 52);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b2);
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
        if (GameScreen.help.setStep_Next(0, -5)) {
            GameScreen.help.Next++;
            GameScreen.help.SaveStep((byte) 0, (byte) 0);
            GameScreen.help.setNext();
            return;
        }
        if (GameScreen.help.setStep_Next(8, 10)) {
            GameCanvas.end_Dialog();
            GameScreen.help.p = null;
            GameScreen.help.NextStep();
            GameScreen.help.setNext();
            GameScreen.help.SaveStep();
        }
    }

    public void Dynamic_Menu(short s, byte b, byte b2) {
        mSystem.outz(new StringBuffer().append("Gui command -30 idNPC = ").append((int) s).append(" , idMenu = ").append((int) b).append(" , index = ").append((int) b2).toString());
        init((byte) -30);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void Item_More_Info(byte b, byte b2) {
        init((byte) 21);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void Use_Item(byte b, byte b2) {
        init((byte) 11);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void Use_Potion(short s) {
        init((byte) 32);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void delete_Item(byte b, short s, byte b2) {
        init((byte) 18);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void Get_Item_Map(short s, byte b) {
        init((byte) 20);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void Add_Base_Skill_Point(byte b, byte b2, short s) {
        init((byte) 22);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void Add_Base_Skill_Point(byte b, byte b2) {
        init((byte) 22);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void getItemTem(short s) {
        init((byte) 28);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void gohome(byte b) {
        init((byte) 31);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void chatPopup(String str) {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        init((byte) 27);
        try {
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
        GameCanvas.msgchat.addNewChat(T.tinden, new StringBuffer().append(GameScreen.player.name).append(": ").toString(), str, (byte) 1, false);
    }

    public void chatTab(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        init((byte) 34);
        try {
            this.m.writer().writeUTF(str);
            this.m.writer().writeUTF(str2);
        } catch (Exception e) {
        }
        send();
    }

    public void Friend(byte b, String str) {
        init((byte) 35);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void Register(String str, String str2) {
        init((byte) 39);
        try {
            this.m.writer().writeUTF(str);
            this.m.writer().writeUTF(str2);
        } catch (Exception e) {
        }
        send();
    }

    public void Party(byte b, String str) {
        init((byte) 48);
        try {
            this.m.writer().writeByte(b);
            if (b != 0 && b != 5 && b != 4) {
                this.m.writer().writeUTF(str);
            }
        } catch (Exception e) {
        }
        send();
    }

    public void Buy_Sell(byte b, String str, byte b2, short s, int i) {
        init((byte) 36);
        try {
            this.m.writer().writeByte(b);
            switch (b) {
                case 0:
                case 1:
                    this.m.writer().writeUTF(str);
                    break;
                case 2:
                case 3:
                    this.m.writer().writeByte(b2);
                    this.m.writer().writeShort(s);
                    break;
                case 7:
                    this.m.writer().writeInt(i);
                    break;
                case 9:
                    this.m.writer().writeUTF(str);
                    break;
            }
        } catch (Exception e) {
        }
        send();
    }

    public void BuffMore(byte b, byte b2, mVector mvector) {
        init((byte) 40);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
            this.m.writer().writeByte(mvector.size());
            for (int i = 0; i < mvector.size(); i++) {
                this.m.writer().writeShort(((Object_Effect_Skill) mvector.elementAt(i)).ID);
            }
        } catch (Exception e) {
        }
        send();
    }

    public void fire_Pk(mVector mvector, byte b) {
        init((byte) 6);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(mvector.size());
            for (int i = 0; i < mvector.size(); i++) {
                this.m.writer().writeShort(((Object_Effect_Skill) mvector.elementAt(i)).ID);
            }
        } catch (Exception e) {
        }
        send();
    }

    public void set_Pk(byte b) {
        init((byte) 42);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void Re_Info_Other_Object(String str, byte b) {
        init((byte) 49);
        try {
            this.m.writer().writeUTF(str);
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void Change_Area(byte b) {
        init((byte) 51);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void Request_Area() {
        init((byte) 54);
        send();
    }

    public void Save_RMS_Server(byte b, byte b2, byte[] bArr) {
        init((byte) 55);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
            int i = 0;
            if (bArr != null) {
                i = bArr.length;
            }
            this.m.writer().writeShort(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.m.writer().writeByte(bArr[i2]);
            }
        } catch (Exception e) {
        }
        send();
    }

    public void set_Page(byte b) {
        init((byte) 56);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void chat_npc(byte b) {
        init((byte) 60);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void Mon_Capchar(byte b) {
        init((byte) -28);
        try {
            this.m.writer().writeByte(PaintInfoGameScreen.mValueHotKey[b]);
            mSystem.outloi(new StringBuffer().append("so goi len").append((int) b).toString());
        } catch (Exception e) {
        }
        send();
        if (MainObject.vecCapchar.size() == 5) {
            MainObject.vecCapchar.removeElementAt(0);
        }
        if (!GameCanvas.isTouch && TField.isQwerty) {
            MainObject.vecCapchar.addElement(new StringBuffer().append("").append(PaintInfoGameScreen.mValueChar[b]).toString());
        } else {
            MainObject.vecCapchar.addElement(new StringBuffer().append("").append(PaintInfoGameScreen.mValueHotKey[b]).toString());
        }
        MainObject.strCapchar = "";
        for (int i = 0; i < MainObject.vecCapchar.size(); i++) {
            MainObject.strCapchar = new StringBuffer().append(MainObject.strCapchar).append(MainObject.vecCapchar.elementAt(i)).toString();
        }
    }

    public void Update_Char_Chest(byte b, short s, byte b2, short s2) {
        init((byte) 65);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b2);
            this.m.writer().writeShort(s2);
        } catch (Exception e) {
        }
        send();
    }

    public void Update_Pet_Container(byte b, short s, byte b2, short s2) {
        mSystem.outz("Send desposit/withdraw pet message");
        init((byte) 44);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b2);
            this.m.writer().writeShort(s2);
        } catch (Exception e) {
        }
        send();
    }

    public void dialog_Server(short s, byte b, byte b2) {
        init((byte) -32);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void send_cmd_server(byte b) {
        init(b);
        send();
    }

    public void Rebuild_Item(byte b, short s, byte b2) {
        init((byte) 67);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void KhamNgoc(byte b, int i, int i2, int i3, int i4) {
        init((byte) -100);
        try {
            switch (b) {
                case 0:
                    this.m.writer().writeByte(b);
                    this.m.writer().writeShort(i);
                    this.m.writer().writeShort(i2);
                    this.m.writer().writeShort(i3);
                    this.m.writer().writeShort(i4);
                    break;
                case 1:
                    this.m.writer().writeByte(b);
                    this.m.writer().writeShort(i);
                    break;
                case 2:
                    this.m.writer().writeByte(b);
                    this.m.writer().writeShort(i);
                    this.m.writer().writeShort(i2);
                    break;
            }
        } catch (Exception e) {
        }
        send();
    }

    public void Replace_Item(byte b, short s) {
        init((byte) 73);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void useMount(byte b) {
        init((byte) -97);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void Thach_Dau(byte b, String str) {
        init((byte) 68);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void NextClan(byte b) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void InvenClan(byte b) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void Add_And_AnS_MemClan(byte b, String str) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void ChucNang_Clan(byte b, int i) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeInt(i);
        } catch (Exception e) {
        }
        send();
    }

    public void gop_Xu_Luong_Clan(byte b, int i) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeInt(i);
        } catch (Exception e) {
        }
        send();
    }

    public void info_Mem_Clan(byte b, String str) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void PhongCap_Clan(byte b, byte b2, String str) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void Delete_Mem_Clan(byte b, String str) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void Change_Slo_NoiQuy_Clan(byte b, String str) {
        init((byte) 69);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void Chat_World(String str) {
        init((byte) 71);
        try {
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void Rebuild_Wing(byte b, int i, short s) {
        init((byte) 77);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeInt(i);
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void UpdateData() {
        init((byte) -57);
        try {
            this.m.writer().writeByte(GameCanvas.IndexCharPar);
        } catch (Exception e) {
        }
        send();
    }

    public void Pet_Eat(short s, short s2, byte b, byte b2) {
        init((byte) 45);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void Update_Pet_Eat(byte b, short s) {
        init((byte) 44);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
        mSystem.outz("cho an pet");
    }

    public void doPaymentNokia(String str) {
        init((byte) -76);
        try {
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void request_LotteryItems(byte b, byte b2) {
        init((byte) -91);
        try {
            this.m.writer().writeByte(b);
            if (b == 1) {
                this.m.writer().writeByte(b2);
            } else if (b == 2) {
                this.m.writer().writeByte(b2);
            }
        } catch (Exception e) {
        }
        send();
    }

    public void DoKhacItem(byte b, byte b2, short s) {
        init((byte) -91);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int] */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    public void sendMoreServerInfo(short s, short s2, String[] strArr) {
        init((byte) -31);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
            byte length = (byte) strArr.length;
            this.m.writer().writeByte(length);
            for (byte b = 0; b < length; b++) {
                this.m.writer().writeUTF(strArr[b]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        send();
    }

    public void sendMoreServerInfo(short s, short s2, mVector mvector) {
        init((byte) -31);
        try {
            this.m.writer().writeShort(s);
            this.m.writer().writeShort(s2);
            byte size = (byte) mvector.size();
            this.m.writer().writeByte(size);
            for (int i = 0; i < size; i++) {
                this.m.writer().writeUTF((String) mvector.elementAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        send();
    }

    public void arena(byte b) {
        init((byte) 37);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void useShip(byte b) {
        init((byte) -98);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void doSendThachDau(byte b, String str) {
        init((byte) -101);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
        } catch (Exception e) {
        }
        send();
    }

    public void do_Buy_Sell_Item(int i, mVector mvector, String str, short s, int i2, byte b) {
        init((byte) -102);
        try {
            this.m.writer().writeByte(i);
            switch (i) {
                case 0:
                    this.m.writer().writeByte(mvector.size());
                    for (int i3 = 0; i3 < mvector.size(); i3++) {
                        item_sell item_sellVar = (item_sell) mvector.elementAt(i3);
                        if (item_sellVar != null) {
                            this.m.writer().writeShort(item_sellVar.id);
                            this.m.writer().writeInt(item_sellVar.price);
                            this.m.writer().writeShort(item_sellVar.soluuong);
                            this.m.writer().writeByte(item_sellVar.cat);
                        }
                    }
                    this.m.writer().writeUTF(str);
                    break;
                case 1:
                    this.m.writer().writeShort(s);
                    break;
                case 2:
                    this.m.writer().writeShort(i2);
                    this.m.writer().writeShort(s);
                    this.m.writer().writeByte(b);
                    break;
                case 5:
                    this.m.writer().writeShort(s);
                    this.m.writer().writeShort(i2);
                    break;
            }
            send();
        } catch (Exception e) {
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void RequestInfo_MiNuong(byte b, short s) {
        init((byte) -103);
        try {
            this.m.writer().writeByte(b);
            switch (b) {
                case 0:
                    this.m.writer().writeShort(s);
                    break;
                case 1:
                    break;
            }
        } catch (Exception e) {
        }
        send();
    }

    public void RequestMaterialTemplate(short s) {
        init((byte) -106);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void Hop_rac(byte b, short s, byte b2) {
        init((byte) -105);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeShort(s);
            this.m.writer().writeByte(b2);
        } catch (Exception e) {
        }
        send();
    }

    public void Hop_rac(byte b) {
        init((byte) -105);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void doUseMaterial(short s) {
        init((byte) -107);
        try {
            this.m.writer().writeShort(s);
        } catch (Exception e) {
        }
        send();
    }

    public void doNapple(byte b, String str, String str2) {
        init((byte) -109);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeUTF(str);
            this.m.writer().writeUTF(str2);
        } catch (Exception e) {
        }
        send();
    }

    public void requestinfoMount(byte b) {
        init((byte) -110);
        try {
            this.m.writer().writeByte(b);
        } catch (Exception e) {
        }
        send();
    }

    public void doSendNewInapp(String str, String str2, String str3) {
        init((byte) -75);
        try {
            this.m.writer().writeUTF(str);
            this.m.writer().writeUTF(str2);
            this.m.writer().writeUTF(str3);
        } catch (Exception e) {
        }
        send();
    }
}
