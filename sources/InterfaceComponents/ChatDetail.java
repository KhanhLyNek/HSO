package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mVector;
import GameObjects.MainTextChat;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/ChatDetail.class */
public class ChatDetail {
    String name;
    String friend;
    public TField tfchat;
    public byte typeChat;
    public static final byte TYPE_CHAT = 0;
    public static final byte TYPE_SERVER = 1;
    public static final byte TYPE_ADDFRIEND = 2;
    byte typeColorChat;
    public static final byte TYPE_TROCHUYEN = 0;
    public static final byte TYPE_BANGHOI_NHOM = 1;
    public mVector vecDetail = new mVector("ChatDetail vecDetail");
    byte timeNew = -1;
    boolean isNew = false;
    int limY = 0;
    int indexColor = 0;

    public ChatDetail(String str, byte b) {
        this.typeChat = (byte) 0;
        this.typeColorChat = (byte) 0;
        this.name = str;
        this.typeChat = b;
        if (this.typeChat == 0) {
            this.tfchat = new TField(GameCanvas.msgchat.xDia + 5, (GameCanvas.msgchat.yDia + GameCanvas.msgchat.hDia) - (TField.getHeight() + 5), GameCanvas.msgchat.wDia - 10);
            this.tfchat.isCloseKey = false;
        } else if (this.typeChat == 2) {
            this.friend = str;
            this.name = T.addFriend;
        }
        if (str.compareTo(T.tabBangHoi) == 0 || str.compareTo(T.tinden) == 0 || str.compareTo(T.tabThuLinh) == 0) {
            this.typeColorChat = (byte) 1;
        } else {
            this.typeColorChat = (byte) 0;
        }
    }

    public void addString(String str, String str2) {
        if (str.length() > 0) {
            String[] splitFontArray = mFont.tahoma_7_white.splitFontArray(str, (GameCanvas.msgchat.wDia - (GameCanvas.msgchat.wOne5 * 2)) - 8);
            MainTextChat[] addChatNew = addChatNew(splitFontArray, setColorText(str2));
            if (addChatNew != null) {
                for (MainTextChat mainTextChat : addChatNew) {
                    this.vecDetail.addElement(mainTextChat);
                }
            }
            setLim();
            if (this.limY > 0 && GameCanvas.subDialog != null && GameCanvas.subDialog == GameCanvas.msgchat && MsgChat.curentfocus != null && MsgChat.curentfocus == this) {
                GameCanvas.msgchat.updateCameraNew(splitFontArray.length);
            }
            if (MsgChat.curentfocus != null && MsgChat.curentfocus != this && this.name.compareTo(T.tinden) != 0) {
                this.isNew = true;
                this.timeNew = (byte) CRes.random(1, 11);
            }
        }
    }

    public void addStartChat(String str) {
        String str2 = "";
        if (this.tfchat != null) {
            str2 = this.tfchat.getText();
        }
        if (str2.length() > 0) {
            String[] splitFontArray = mFont.tahoma_7_white.splitFontArray(new StringBuffer().append(GameScreen.player.name).append(": ").append(str2).toString(), (GameCanvas.msgchat.wDia - (GameCanvas.msgchat.wOne5 * 2)) - 8);
            MainTextChat[] addChatNew = addChatNew(splitFontArray, setColorText(str));
            if (addChatNew != null) {
                for (MainTextChat mainTextChat : addChatNew) {
                    this.vecDetail.addElement(mainTextChat);
                }
            }
            setLim();
            if (MsgChat.curentfocus != null && MsgChat.curentfocus == this) {
                GameCanvas.msgchat.updateCameraNew(splitFontArray.length);
            }
            GlobalService.gI().chatTab(this.name, str2);
        }
        if (this.tfchat != null) {
            this.tfchat.setText("");
        }
    }

    public void setLim() {
        this.limY = (this.vecDetail.size() * GameCanvas.hText) - (((GameCanvas.msgchat.hDia - MainTabNew.wOneItem) - 10) - (this.typeChat == 0 ? TField.getHeight() + 2 : 0));
        if (this.limY < 0) {
            this.limY = 0;
        }
    }

    public MainTextChat[] addChatNew(String[] strArr, byte b) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        MainTextChat[] mainTextChatArr = new MainTextChat[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            mainTextChatArr[i] = new MainTextChat(strArr[i], b);
        }
        return mainTextChatArr;
    }

    private byte setColorText(String str) {
        byte b;
        if (this.typeColorChat == 1) {
            b = this.indexColor % 2 == 0 ? (byte) 0 : (byte) 5;
            this.indexColor++;
        } else if (str.compareTo(GameScreen.player.name) == 0) {
            b = 5;
        } else {
            b = 0;
        }
        return b;
    }
}
