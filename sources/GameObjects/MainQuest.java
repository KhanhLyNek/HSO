package GameObjects;

import CLib.mFont;
import CLib.mVector;
import GameScreen.GameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/MainQuest.class */
public class MainQuest extends AvMain {
    public static final int Q_NHAT_ITEM = 0;
    public static final int Q_KILL_MONSTER = 1;
    public static final int Q_CHUYEN_DO = 2;
    public static final int Q_TALK = 3;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_MONSTER = 1;
    public static final int NHAN = 0;
    public static final int TRA = 1;
    public static mVector vecQuestList = new mVector("MainQuest vecQuestList");
    public static mVector vecQuestFinish = new mVector("MainQuest vecQuestFinish");
    public static mVector vecQuestDoing_Main = new mVector("MainQuest vecQuestDoing_Main");
    public static mVector vecQuestDoing_Sub = new mVector("MainQuest vecQuestDoing_Sub");
    public byte typeQuest;
    public int ID;
    public int idNPC_To;
    public int idNPC_From;
    public int idNPCChat;
    public int nhantra;
    public boolean isComplete;
    public boolean isMain;
    public String name;
    public String strDetail;
    public String strShortDetail;
    public String[] mDetail;
    public String[] mstrTalk;
    public String[] mstrHelp;
    public String strDetailTalk;
    public String strDetailHelp;
    public String strShowDialog;
    public short[] mIdQuest;
    public short[] mtotalQuest;
    public short[] mQuestGot;
    int step;

    public MainQuest(int i, boolean z, String str, int i2, String str2, byte b, String str3) {
        this.isComplete = false;
        this.step = 0;
        this.ID = i;
        this.isMain = z;
        this.idNPC_From = i2;
        this.idNPCChat = i2;
        this.name = str;
        this.strDetailTalk = str2;
        this.strDetailHelp = str3;
        this.typeQuest = b;
        this.nhantra = 0;
        this.mstrTalk = mFont.split(str2, ">");
    }

    public MainQuest(int i, boolean z, String str, int i2, String str2, String str3) {
        this.isComplete = false;
        this.step = 0;
        this.ID = i;
        this.isMain = z;
        this.idNPC_To = i2;
        this.idNPCChat = i2;
        this.name = str;
        this.strDetailTalk = str2;
        this.strDetailHelp = str3;
        this.mstrHelp = mFont.tahoma_7_white.splitFontArray(str3, MainTabNew.wblack - 35);
        this.nhantra = 1;
        this.mstrTalk = mFont.split(str2, ">");
    }

    public MainQuest(int i, boolean z, String str, String str2, byte b, String str3, int i2, short[] sArr, short[] sArr2, short[] sArr3, int i3) {
        this.isComplete = false;
        this.step = 0;
        this.ID = i;
        this.isMain = z;
        this.idNPC_To = i2;
        this.idNPCChat = i2;
        this.name = str;
        this.typeQuest = b;
        this.strShortDetail = str3;
        this.strDetailHelp = str2;
        this.mIdQuest = sArr;
        this.mtotalQuest = sArr2;
        this.mQuestGot = sArr3;
    }

    public MainQuest(int i, boolean z, String str, String str2, int i2, String str3) {
        this.isComplete = false;
        this.step = 0;
        this.ID = i;
        this.isMain = z;
        this.name = str;
        this.strShortDetail = str3;
        this.strDetailHelp = str2;
        this.idNPC_To = i2;
        this.idNPCChat = i2;
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                if (GameCanvas.menu2.runText != null && !GameCanvas.menu2.runText.nextDlgStep()) {
                    return;
                }
                nextStep();
                return;
            case 1:
                if (GameCanvas.menu2.runText != null && !GameCanvas.menu2.runText.nextDlgStep()) {
                    return;
                }
                MainObject mainObject = MainObject.get_Object(this.idNPCChat, (byte) 2);
                if (mainObject != null) {
                    mainObject.chat = null;
                }
                String str = this.strDetailHelp;
                if (str == null) {
                    str = "sai roi";
                }
                GameScreen.player.chat = null;
                if (this.typeQuest == 3) {
                    GlobalService.gI().quest((short) this.ID, (byte) (this.isMain ? 0 : 1), (byte) 1);
                } else {
                    GameCanvas.start_Quest_Dialog(str, this.name, this.ID, this.nhantra, (byte) (this.isMain ? 0 : 1));
                }
                GameScreen.player.currentQuest = null;
                GameScreen.gI().center = null;
                GameCanvas.clearKeyHold();
                GameCanvas.isPointerSelect = false;
                return;
            default:
                return;
        }
    }

    public void beginQuest() {
        this.step = 0;
        GameScreen.player.currentQuest = this;
        nextStep();
    }

    public void show_Info_Quest_Doing() {
        if (this.strShortDetail == null || MainObject.get_Object(this.idNPCChat, (byte) 2) == null) {
            return;
        }
        MainObject.get_Object(this.idNPCChat, (byte) 2).strChatPopup = this.strDetailHelp;
    }

    public iCommand setCmd() {
        iCommand icommand;
        if (this.step < this.mstrTalk.length - 1) {
            icommand = new iCommand(T.next, 0, this);
        } else {
            icommand = new iCommand(T.next, 1, this);
        }
        return icommand;
    }

    public void nextStep() {
        if (MainObject.get_Object(this.idNPCChat, (byte) 2) == null) {
            GameScreen.player.currentQuest = null;
            return;
        }
        if (this.mstrTalk[this.step].trim().startsWith("0")) {
            MainObject.get_Object(this.idNPCChat, (byte) 2).chat = null;
            mVector mvector = new mVector("MainQuest menu");
            mvector.addElement(setCmd());
            GameCanvas.menu2.startAt_NPC(mvector, this.mstrTalk[this.step].substring(1, this.mstrTalk[this.step].length()), GameScreen.player.ID, (byte) 0, true, 0);
        } else {
            GameScreen.player.chat = null;
            mVector mvector2 = new mVector("MainQuest menu2");
            mvector2.addElement(setCmd());
            GameCanvas.menu2.startAt_NPC(mvector2, this.mstrTalk[this.step].substring(1, this.mstrTalk[this.step].length()), this.idNPCChat, (byte) 2, true, 0);
        }
        this.step++;
    }

    public static void updateQuestKillMonster(int i) {
        MainMonster mainMonster = (MainMonster) MainObject.get_Object(i, (byte) 1);
        if (mainMonster == null) {
            return;
        }
        String str = "";
        for (int i2 = 0; i2 < vecQuestDoing_Main.size(); i2++) {
            MainQuest mainQuest = (MainQuest) vecQuestDoing_Main.elementAt(i2);
            if (mainQuest.typeQuest == 1) {
                for (int i3 = 0; i3 < mainQuest.mIdQuest.length; i3++) {
                    if (mainQuest.mIdQuest[i3] == mainMonster.catalogyMonster && mainQuest.mQuestGot[i3] < mainQuest.mtotalQuest[i3]) {
                        short[] sArr = mainQuest.mQuestGot;
                        int i4 = i3;
                        sArr[i4] = (short) (sArr[i4] + 1);
                        if (str.length() > 0) {
                            str = new StringBuffer().append(str).append(" , ").toString();
                        }
                        str = new StringBuffer().append(str).append((int) mainQuest.mQuestGot[i3]).append("/").append((int) mainQuest.mtotalQuest[i3]).toString();
                    }
                }
            }
        }
        for (int i5 = 0; i5 < vecQuestDoing_Sub.size(); i5++) {
            MainQuest mainQuest2 = (MainQuest) vecQuestDoing_Sub.elementAt(i5);
            if (mainQuest2.typeQuest == 1) {
                for (int i6 = 0; i6 < mainQuest2.mIdQuest.length; i6++) {
                    if (mainQuest2.mIdQuest[i6] == mainMonster.catalogyMonster && mainQuest2.mQuestGot[i6] < mainQuest2.mtotalQuest[i6]) {
                        short[] sArr2 = mainQuest2.mQuestGot;
                        int i7 = i6;
                        sArr2[i7] = (short) (sArr2[i7] + 1);
                        if (str.length() > 0) {
                            str = new StringBuffer().append(str).append(" , ").toString();
                        }
                        str = new StringBuffer().append(str).append((int) mainQuest2.mQuestGot[i6]).append("/").append((int) mainQuest2.mtotalQuest[i6]).toString();
                    }
                }
            }
        }
        if (str.length() > 0) {
            if (GameCanvas.game != null) GameCanvas.game.lyMeowzRecordQuestKill();
            GameCanvas.addInfoChar(new StringBuffer().append(T.giet).append(mainMonster.name).append(": ").append(str).toString());
        }
    }

    public static void updateQuestGetItem(int i, String str) {
        String str2 = "";
        for (int i2 = 0; i2 < vecQuestDoing_Main.size(); i2++) {
            MainQuest mainQuest = (MainQuest) vecQuestDoing_Main.elementAt(i2);
            if (mainQuest.typeQuest == 0) {
                for (int i3 = 0; i3 < mainQuest.mIdQuest.length; i3++) {
                    if (mainQuest.mIdQuest[i3] == i && mainQuest.mQuestGot[i3] < mainQuest.mtotalQuest[i3]) {
                        short[] sArr = mainQuest.mQuestGot;
                        int i4 = i3;
                        sArr[i4] = (short) (sArr[i4] + 1);
                        if (str2.length() > 0) {
                            str2 = new StringBuffer().append(str2).append(" , ").toString();
                        }
                        str2 = new StringBuffer().append(str2).append((int) mainQuest.mQuestGot[i3]).append("/").append((int) mainQuest.mtotalQuest[i3]).toString();
                    }
                }
            }
        }
        for (int i5 = 0; i5 < vecQuestDoing_Sub.size(); i5++) {
            MainQuest mainQuest2 = (MainQuest) vecQuestDoing_Sub.elementAt(i5);
            if (mainQuest2.typeQuest == 0) {
                for (int i6 = 0; i6 < mainQuest2.mIdQuest.length; i6++) {
                    System.out.println(new StringBuffer().append((int) mainQuest2.mIdQuest[i6]).append("  ").append(i).toString());
                    if (mainQuest2.mIdQuest[i6] == i && mainQuest2.mQuestGot[i6] < mainQuest2.mtotalQuest[i6]) {
                        short[] sArr2 = mainQuest2.mQuestGot;
                        int i7 = i6;
                        sArr2[i7] = (short) (sArr2[i7] + 1);
                        if (str2.length() > 0) {
                            str2 = new StringBuffer().append(str2).append(" , ").toString();
                        }
                        str2 = new StringBuffer().append(str2).append((int) mainQuest2.mQuestGot[i6]).append("/").append((int) mainQuest2.mtotalQuest[i6]).toString();
                    }
                }
            }
        }
        if (str2.length() > 0) {
            GameCanvas.addInfoChar(new StringBuffer().append(T.nhat).append(str).append(": ").append(str2).toString());
        }
    }
}
