package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mVector;
import GameObjects.MainEvent;
import GameObjects.MainTextChat;
import GameScreen.EventScreen;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import Model.mCamera;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/MsgChat.class */
public class MsgChat extends MainDialog {
    int wMainTab;
    int maxTab;
    int min;
    int max;
    int xdich;
    byte maxText;
    int xselect;
    static String[] strName;
    public static ChatDetail curentfocus;
    public static iCommand cmdChat;
    public static iCommand cmdDelete;
    public static iCommand cmdClose;
    public static iCommand cmdMenu;
    public static iCommand cmdOkAdd;
    public static iCommand cmdCancelAdd;
    boolean isLeft;
    boolean isRight;
    ListNew list;
    int minchat;
    int maxchat;
    int hchat;
    static int idSelect = 0;
    public static mVector vecChatTab = new mVector("MsgChat vecChatTab");
    Scroll scroll = new Scroll();
    mCamera cam = new mCamera();
    int wOneItem = MainTabNew.wOneItem;
    int wOne5 = MainTabNew.wOne5;

    public MsgChat() {
        this.maxText = (byte) 5;
        this.wDia = (GameCanvas.w / 4) * 3;
        if (this.wDia < 160) {
            this.wDia = 160;
        } else if (this.wDia > 240) {
            this.wDia = 240;
        }
        this.hDia = (GameCanvas.h - (iCommand.hButtonCmd * 2)) - 16;
        if (this.hDia > 240) {
            this.hDia = 240;
        }
        this.hchat = ((this.hDia - (3 * this.wOneItem)) / GameCanvas.hText) + 5;
        this.xDia = GameCanvas.hw - (this.wDia / 2);
        this.yDia = GameCanvas.hh - (this.hDia / 2);
        this.cam.setAll(0, 0, 0, 0);
        this.wMainTab = this.wOneItem * 3;
        if (this.wMainTab > 70) {
            this.wMainTab = 70;
        }
        if (this.wOneItem < 20) {
            this.maxText = (byte) 4;
        }
        this.maxTab = (((this.wDia - this.wMainTab) - 20) / (this.wOneItem - 1)) + 1;
        this.min = idSelect - (this.maxTab / 2);
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
        }
        this.xdich = ((this.wDia - ((this.maxTab - 1) * (this.wOneItem - 1))) - this.wMainTab) / 2;
        cmdChat = new iCommand(T.chat, 0, this);
        cmdDelete = new iCommand(T.delTabChat, 1, this);
        cmdClose = new iCommand(T.trora, -1, this);
        cmdMenu = new iCommand(T.menu, 2, this);
        cmdOkAdd = new iCommand(T.chapnhan, 3, this);
        cmdCancelAdd = new iCommand(T.tuchoi, 4, this);
        this.list = new ListNew(this.xDia, this.yDia + this.wOneItem, this.wDia, this.hDia - (3 * this.wOneItem), 0, 0, 0);
        this.scroll.setInfo((this.xDia + this.wDia) - 6, this.yDia + this.wOneItem + 10, ((this.hDia - this.wOneItem) - TField.getHeight()) - 25, MainTabNew.color[9]);
        init();
    }

    public void setCaptionCmd() {
        cmdChat.caption = T.chat;
        cmdDelete.caption = T.delTabChat;
        cmdClose.caption = T.trora;
        cmdMenu.caption = T.menu;
        cmdOkAdd.caption = T.chapnhan;
        cmdCancelAdd.caption = T.tuchoi;
    }

    public void init() {
        if (vecChatTab.size() != 0) {
            if (curentfocus == null && vecChatTab.size() > 0) {
                if (idSelect < 0 || idSelect >= vecChatTab.size()) {
                    idSelect = 0;
                }
                curentfocus = (ChatDetail) vecChatTab.elementAt(idSelect);
                updateSelect();
            }
            if (curentfocus != null) {
                updateSelect();
            }
            if (GameCanvas.isTouch) {
                cmdMenu.setPos((this.xDia + this.wDia) - 12, this.yDia - 14, PaintInfoGameScreen.fraCloseMenu, "");
            }
            this.left = cmdMenu;
        }
    }

    public void backTab() {
        idSelect = 0;
        this.cam.setAll(0, 0, 0, 0);
        this.list = new ListNew(this.xDia, this.yDia + this.wOneItem, this.wMainTab, this.hDia - (3 * this.wOneItem), 0, 0, 0);
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                GameCanvas.end_Dialog();
                break;
            case 0:
                curentfocus.addStartChat(GameScreen.player.name);
                break;
            case 1:
                if (idSelect < 0 || idSelect >= vecChatTab.size()) {
                    return;
                }
                ChatDetail chatDetail = (ChatDetail) vecChatTab.elementAt(idSelect);
                if (chatDetail != null) {
                    MainEvent event = EventScreen.setEvent(chatDetail.name, (byte) 0);
                    if (event != null) {
                        event.isRemove = true;
                    }
                    vecChatTab.removeElementAt(idSelect);
                    if (idSelect > 0) {
                        idSelect--;
                    }
                    if (vecChatTab.size() == 0) {
                        curentfocus = null;
                        this.left = null;
                        this.center = null;
                        this.right = cmdClose;
                        break;
                    } else {
                        updateSelect();
                        break;
                    }
                }
                break;
            case 2:
                if (GameCanvas.isTouch && vecChatTab.size() == 1) {
                    GameCanvas.end_Dialog();
                    return;
                }
                mVector mvector = new mVector("MsgChat menu");
                if (idSelect > 0) {
                    mvector.addElement(cmdDelete);
                }
                mvector.addElement(cmdClose);
                GameCanvas.menu2.startAt(mvector, 2, T.trochuyen, false, null);
                break;
            case 3:
                GlobalService.gI().Friend((byte) 1, curentfocus.friend);
                cmdDelete.perform();
                break;
            case 4:
                GlobalService.gI().Friend((byte) 2, curentfocus.friend);
                cmdDelete.perform();
                break;
        }
        super.commandPointer(i, i2);
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        String stringBuffer;
        GameCanvas.resetTrans(mgraphics);
        AvMain.paintDialogNew(mgraphics, this.xDia - 10, (this.yDia - GameCanvas.hCommand) - 6, this.wDia + 20, this.hDia + GameCanvas.hCommand + 12, 0);
        paintBack(mgraphics);
        AvMain.FontBorderColor(mgraphics, T.trochuyen, this.xDia + (this.wDia / 2), (this.yDia - GameCanvas.hCommand) + (GameCanvas.hCommand / 4), 2, 0);
        int i = this.xDia + this.xdich;
        int i2 = this.yDia + this.wOne5;
        if (vecChatTab.size() > 0 && curentfocus != null) {
            if (this.min > 0) {
                mgraphics.drawRegion(MainTabNew.imgTab[13], 0, (!this.isLeft || GameCanvas.gameTick % 6 >= 3) ? 0 : 16, 13, 8, 6, i - 6, i2 + (this.wOne5 * 2), 3, true);
            }
            mgraphics.setColor(MainTabNew.color[7]);
            for (int i3 = this.min; i3 < idSelect; i3++) {
                ChatDetail chatDetail = (ChatDetail) vecChatTab.elementAt(i3);
                paintRectNew(mgraphics, i, i2, this.wOneItem, this.wOneItem, chatDetail.isNew, chatDetail.timeNew);
                mFont.tahoma_7_black.drawString(mgraphics, strName[i3], i + (this.wOneItem / 2), (i2 + (this.wOneItem / 2)) - 7, 2, true);
                i += this.wOneItem - 1;
            }
            this.xselect = i;
            int i4 = i + (this.wMainTab - 1);
            for (int i5 = idSelect + 1; i5 < this.max; i5++) {
                ChatDetail chatDetail2 = (ChatDetail) vecChatTab.elementAt(i5);
                paintRectNew(mgraphics, i4, i2, this.wOneItem, this.wOneItem, chatDetail2.isNew, chatDetail2.timeNew);
                mFont.tahoma_7_black.drawString(mgraphics, strName[i5], i4 + (this.wOneItem / 2), (i2 + (this.wOneItem / 2)) - 7, 2, true);
                i4 += this.wOneItem - 1;
            }
            if (this.max < vecChatTab.size()) {
                mgraphics.drawRegion(MainTabNew.imgTab[13], 0, (!this.isRight || GameCanvas.gameTick % 6 >= 3) ? 8 : 24, 13, 8, 6, i4 + 7, i2 + (this.wOne5 * 2), 3, true);
            }
            paintTabFocus(mgraphics, this.xselect, i2 - 1, this.wMainTab);
            if (mFont.tahoma_7b_white.getWidth(curentfocus.name) < this.wMainTab - 4) {
                stringBuffer = curentfocus.name;
            } else {
                stringBuffer = new StringBuffer().append(curentfocus.name.substring(0, (this.maxText - 1) * 2)).append("..").toString();
            }
            mFont.tahoma_7b_white.drawString(mgraphics, stringBuffer, this.xselect + (this.wMainTab / 2), (i2 + (this.wOneItem / 2)) - 7, 2, true);
            int i6 = i2 + this.wOneItem;
            mgraphics.setClip(this.xDia, i6, this.wDia, ((this.hDia - this.wOneItem) - 7) - (curentfocus.typeChat == 0 ? 17 : 0));
            mgraphics.saveCanvas();
            mgraphics.ClipRec(this.xDia, i6, this.wDia, ((this.hDia - this.wOneItem) - 7) - (curentfocus.typeChat == 0 ? 17 : 0));
            mgraphics.translate(0, -this.cam.yCam);
            this.minchat = (this.cam.yCam / GameCanvas.hText) - 2;
            if (this.minchat < 0) {
                this.minchat = 0;
            }
            this.maxchat = this.minchat + this.hchat;
            for (int i7 = this.minchat; i7 <= this.maxchat; i7++) {
                if (i7 < curentfocus.vecDetail.size() && i7 >= 0) {
                    MainTextChat mainTextChat = (MainTextChat) curentfocus.vecDetail.elementAt(i7);
                    MainTabNew.setTextColor(mainTextChat.color).drawString(mgraphics, mainTextChat.text, this.xDia + this.wOne5, i6 + 2 + (i7 * GameCanvas.hText), 0, true);
                }
            }
            mGraphics.resetTransAndroid(mgraphics);
            mgraphics.restoreCanvas();
            mgraphics.endClip();
            GameCanvas.resetTrans(mgraphics);
            if (this.cam.yLimit > 0) {
                this.scroll.paint(mgraphics);
            }
            if (curentfocus.typeChat == 0) {
                curentfocus.tfchat.paint(mgraphics);
            }
        } else {
            mFont.tahoma_7_white.drawString(mgraphics, T.noMessage, this.xDia + (this.wDia / 2), i2 + this.wOneItem + 2, 2, true);
        }
        super.paint(mgraphics);
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (vecChatTab.size() > 0 && curentfocus != null) {
            int i = idSelect;
            if (GameCanvas.keyMyHold[4]) {
                if (idSelect != 0) {
                    idSelect--;
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                idSelect++;
                GameCanvas.clearKeyHold(6);
            }
            idSelect = resetSelect(idSelect, vecChatTab.size() - 1, true);
            if (GameCanvas.keyMyHold[2]) {
                this.cam.yTo -= GameCanvas.hText;
                if (this.cam.yTo < 0) {
                    this.cam.yTo = 0;
                }
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.cam.yTo += GameCanvas.hText;
                if (this.cam.yTo > this.cam.yLimit) {
                    this.cam.yTo = this.cam.yLimit;
                }
                GameCanvas.clearKeyHold(8);
            }
            if (i != idSelect) {
                updateSelect();
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void update() {
        if (GameCanvas.isTouch) {
            this.list.update_Pos_UP_DOWN();
            this.cam.yCam = this.list.cmx;
        }
        this.isLeft = false;
        this.isRight = false;
        if (vecChatTab.size() > 0 && curentfocus != null) {
            for (int i = 0; i < vecChatTab.size(); i++) {
                if ((i < this.min || i >= this.max) && ((ChatDetail) vecChatTab.elementAt(i)).isNew) {
                    if (i < this.min) {
                        this.isLeft = true;
                    } else {
                        this.isRight = true;
                    }
                }
            }
            if (vecChatTab.size() == 0) {
                idSelect = 0;
            }
            if (this.cam.yLimit > 0) {
                this.scroll.setYScrool(this.cam.yCam, this.cam.yLimit);
            }
            if (GameCanvas.isTouch) {
                this.list.moveCamera();
            } else {
                this.cam.UpdateCamera();
                this.cam.UpdateCamera();
            }
            if (curentfocus.typeChat == 0) {
                curentfocus.tfchat.update();
            }
        } else if (curentfocus == null && vecChatTab.size() > 0) {
            idSelect = 0;
            curentfocus = (ChatDetail) vecChatTab.elementAt(idSelect);
            updateSelect();
        }
        updatekey();
        updatePointer();
        super.update();
    }

    @Override // InterfaceComponents.MainDialog, Model.AvMain
    public void keypress(int i) {
        if (vecChatTab.size() > 0 && curentfocus != null && curentfocus.typeChat == 0) {
            curentfocus.tfchat.keyPressed(i);
        }
        super.keypress(i);
    }

    public static void getStrName() {
        strName = new String[vecChatTab.size()];
        for (int i = 0; i < strName.length; i++) {
            ChatDetail chatDetail = (ChatDetail) vecChatTab.elementAt(i);
            if (chatDetail.name.length() <= 4) {
                strName[i] = chatDetail.name;
            } else {
                strName[i] = chatDetail.name.substring(0, 4);
            }
        }
    }

    public void updateCameraNew(int i) {
        if (GameCanvas.isTouch) {
            this.list.cmxLim = curentfocus.limY;
            if (this.list.cmx + (this.hDia / 4) > this.list.cmxLim && this.list.cmxLim > 0) {
                this.list.cmtoX += GameCanvas.hText * i;
                return;
            }
            return;
        }
        this.cam.yLimit = curentfocus.limY;
        if (this.cam.yCam + (this.hDia / 4) > this.cam.yLimit) {
            this.cam.yTo += GameCanvas.hText * i;
        }
    }

    public void updateSelect() {
        curentfocus = (ChatDetail) vecChatTab.elementAt(idSelect);
        if (this.left == null) {
            this.left = cmdMenu;
        }
        if (curentfocus.typeChat == 0) {
            curentfocus.tfchat.setText("");
            if (!GameCanvas.isTouch) {
                curentfocus.tfchat.setFocus(true);
                this.center = cmdChat;
                this.right = curentfocus.tfchat.setCmdClear();
            }
        } else if (curentfocus.typeChat == 2) {
            this.center = cmdOkAdd;
            this.right = cmdCancelAdd;
        } else {
            this.center = null;
            this.right = null;
        }
        if (curentfocus.timeNew > 0) {
            curentfocus.timeNew = (byte) -1;
            curentfocus.isNew = false;
        }
        if (curentfocus.limY > 0) {
            int i = curentfocus.limY - (this.hDia / 4);
            if (i < 0) {
                i = 0;
            }
            if (GameCanvas.isTouch) {
                this.list.cmxLim = curentfocus.limY;
                this.list.cmtoX = curentfocus.limY;
            } else {
                this.cam.setAll(0, curentfocus.limY, 0, i);
                this.cam.yTo = curentfocus.limY;
            }
        } else if (!GameCanvas.isTouch) {
            this.cam.setAll(0, 0, 0, 0);
        } else {
            this.list.cmxLim = 0;
            this.list.cmtoX = 0;
        }
        this.min = idSelect - (this.maxTab / 2);
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
            this.min = this.max - this.maxTab;
            if (this.min < 0) {
                this.min = 0;
            }
        }
        getStrName();
        TabScreenNew.timeRepaint = 10;
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (vecChatTab.size() > 0 && curentfocus != null) {
            if (curentfocus.typeChat == 0) {
                curentfocus.tfchat.updatePoiter();
            }
            if (GameCanvas.isPointerSelect) {
                int i = this.xDia;
                int i2 = this.yDia;
                int i3 = i + this.xdich;
                int i4 = i3 + ((this.maxTab - 1) * (this.wOneItem - 1)) + this.wMainTab;
                int i5 = idSelect;
                if (GameCanvas.isPoint(this.xDia, i2, this.wDia, this.wOneItem)) {
                    if (GameCanvas.px < this.xselect && GameCanvas.px > i3) {
                        i5 = (idSelect + ((GameCanvas.px - this.xselect) / (this.wOneItem - 1))) - 1;
                        TabScreenNew.timeRepaint = 10;
                    } else if (GameCanvas.px > this.xselect + this.wMainTab && GameCanvas.px < i4) {
                        i5 = idSelect + (((GameCanvas.px - this.xselect) - this.wMainTab) / (this.wOneItem - 1)) + 1;
                    } else if (GameCanvas.px < i3) {
                        i5 = idSelect - this.maxTab;
                    } else if (GameCanvas.px > i4) {
                        i5 = idSelect + this.maxTab;
                    }
                }
                int resetSelect = resetSelect(i5, vecChatTab.size() - 1, false);
                if (resetSelect != idSelect) {
                    idSelect = resetSelect;
                    updateSelect();
                    GameCanvas.isPointerSelect = false;
                    TabScreenNew.timeRepaint = 10;
                }
            }
        }
        super.updatePointer();
    }

    public void addNewChat(String str, String str2, String str3, byte b, boolean z) {
        for (int i = 0; i < vecChatTab.size(); i++) {
            ChatDetail chatDetail = (ChatDetail) vecChatTab.elementAt(i);
            if (chatDetail.name.compareTo(str) == 0) {
                if (z) {
                    idSelect = i;
                }
                if (str3.length() > 0) {
                    chatDetail.addString(new StringBuffer().append(str2).append(str3).toString(), str);
                    GameCanvas.mevent.addEvent(str, (byte) 0, mFont.tahoma_7_white.splitFontArray(str3, (GameCanvas.mevent.wDia / 5) * 2)[0], 0);
                    return;
                }
                return;
            }
        }
        ChatDetail chatDetail2 = new ChatDetail(str, b);
        if (b == 0) {
            chatDetail2.addString(new StringBuffer().append(T.beginChat).append(str).toString(), str);
        }
        if (str3.length() > 0) {
            chatDetail2.addString(new StringBuffer().append(str2).append(str3).toString(), str);
            GameCanvas.mevent.addEvent(str, (byte) 0, mFont.tahoma_7_white.splitFontArray(str3, GameCanvas.mevent.wDia / 2)[0], 0);
        }
        vecChatTab.addElement(chatDetail2);
        set_text_min_max();
        if (z) {
            idSelect = vecChatTab.size() - 1;
        }
    }

    public void set_text_min_max() {
        getStrName();
        this.min = idSelect - (this.maxTab / 2);
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxTab;
        if (this.max > vecChatTab.size()) {
            this.max = vecChatTab.size();
            this.min = this.max - this.maxTab;
            if (this.min < 0) {
                this.min = 0;
            }
        }
    }

    public void paintRectNew(mGraphics mgraphics, int i, int i2, int i3, int i4, boolean z, int i5) {
        if (z && (i5 + GameCanvas.gameTick) % 8 < 4) {
            TabScreenNew.timeRepaint = 10;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mgraphics, i + 1, i2 + 1, i3 - 2, (i4 - this.wOne5) - 1, 3);
            } else {
                mgraphics.drawRegion(MainTabNew.imgTab[10], 0, 0, i3 - 2, (i4 - this.wOne5) - 1, 0, i + 1, i2 + 1, 0, true);
            }
        }
        mgraphics.fillRect(i, i2 + 1, 1, i4 - 1, true);
        mgraphics.fillRect(i + 1, i2, i3 - 2, 1, true);
        mgraphics.fillRect((i + i3) - 1, i2 + 1, 1, i4 - 1, true);
    }

    public void paintTabFocus(mGraphics mgraphics, int i, int i2, int i3) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mgraphics, i, i2, i3, 32, 2);
            return;
        }
        for (int i4 = 0; i4 <= i3 / 32; i4++) {
            if (i4 == 0) {
                mgraphics.drawImage(MainTabNew.imgTab[9], i, i2, 0, true);
            } else if (i4 == i3 / 32) {
                mgraphics.drawRegion(MainTabNew.imgTab[9], 0, 0, 32, 32, 2, (i + i3) - 32, i2, 0, true);
            } else {
                mgraphics.drawImage(MainTabNew.imgTab[2], i + (i4 * 32), i2, 0, true);
            }
        }
    }

    public void paintBack(mGraphics mgraphics) {
        if (GameCanvas.lowGraphic) {
            MainTabNew.paintRectLowGraphic(mgraphics, this.xDia, this.yDia + this.wOneItem, this.wDia, this.hDia - this.wOneItem, 2);
            return;
        }
        int i = this.hDia - this.wOneItem;
        int i2 = this.wDia / 32;
        int i3 = i / 32;
        for (int i4 = 0; i4 <= i2; i4++) {
            for (int i5 = 0; i5 <= i3; i5++) {
                if (i4 == i2) {
                    if (i5 == i3) {
                        mgraphics.drawImage(MainTabNew.imgTab[2], (this.xDia + this.wDia) - 32, (this.yDia + this.hDia) - 32, 0, true);
                    } else {
                        mgraphics.drawImage(MainTabNew.imgTab[2], (this.xDia + this.wDia) - 32, this.yDia + this.wOneItem + (i5 * 32), 0, true);
                    }
                } else if (i5 == i3) {
                    mgraphics.drawImage(MainTabNew.imgTab[2], this.xDia + (i4 * 32), (this.yDia + this.hDia) - 32, 0, true);
                } else {
                    mgraphics.drawImage(MainTabNew.imgTab[2], this.xDia + (i4 * 32), this.yDia + this.wOneItem + (i5 * 32), 0, true);
                }
            }
        }
    }

    public boolean setAddFriend(String str) {
        for (int i = 0; i < vecChatTab.size(); i++) {
            ChatDetail chatDetail = (ChatDetail) vecChatTab.elementAt(i);
            if (chatDetail.typeChat == 2 && chatDetail.friend.compareTo(str) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void setFocus(int i) {
        if (GameCanvas.subDialog != GameCanvas.msgchat && vecChatTab != null && i >= 0 && i < vecChatTab.size()) {
            idSelect = i;
        }
    }

    public static void setIndexFocus(String str) {
        for (int i = 0; i < vecChatTab.size(); i++) {
            if (((ChatDetail) vecChatTab.elementAt(i)).name.compareTo(str) == 0) {
                idSelect = i;
                return;
            }
        }
    }

    public void checkRemoveText() {
        for (int i = 0; i < vecChatTab.size(); i++) {
            ChatDetail chatDetail = (ChatDetail) vecChatTab.elementAt(i);
            int size = chatDetail.vecDetail.size();
            if (size > 80) {
                for (int i2 = 0; i2 < size - 80; i2++) {
                    chatDetail.vecDetail.removeElementAt(i);
                }
                chatDetail.setLim();
            }
        }
    }
}
