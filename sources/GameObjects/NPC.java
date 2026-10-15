package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mVector;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.MainImage;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/NPC.class */
public class NPC extends MainObject {
    public short idImage;
    public short wBlock;
    public short hBlock;
    int nFrame;
    int wAvatar;
    int hAvatar;
    int indexSound = -1;
    int timePlaySound = -1;

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                if (i2 >= 0 && i2 < MainQuest.vecQuestList.size()) {
                    ((MainQuest) MainQuest.vecQuestList.elementAt(i2)).beginQuest();
                    break;
                }
                break;
            case 1:
                if (i2 >= 0 && i2 < MainQuest.vecQuestFinish.size()) {
                    ((MainQuest) MainQuest.vecQuestFinish.elementAt(i2)).beginQuest();
                    break;
                }
                break;
            case 2:
                if (i2 >= 0 && i2 < MainQuest.vecQuestDoing_Main.size()) {
                    ((MainQuest) MainQuest.vecQuestDoing_Main.elementAt(i2)).show_Info_Quest_Doing();
                    break;
                }
                break;
            case 3:
                if (i2 >= 0 && i2 < MainQuest.vecQuestDoing_Sub.size()) {
                    ((MainQuest) MainQuest.vecQuestDoing_Sub.elementAt(i2)).show_Info_Quest_Doing();
                    break;
                }
                break;
            case 4:
                GlobalService.gI().getlist_from_npc((byte) this.ID);
                break;
            case 5:
                NhiemVu();
                break;
        }
        super.commandPointer(i, i2);
    }

    public NPC(String str, String str2, byte b, byte b2, short s, short s2, byte b3, byte b4, byte b5) {
        this.name = str;
        this.nameGiaotiep = str2;
        this.ID = b;
        this.idImage = b2;
        this.x = s + 12;
        this.y = s2;
        this.wBlock = b3;
        this.hBlock = b4;
        this.nFrame = b5;
        this.hp = 100;
        this.maxHp = 100;
        this.ysai = 0;
        GameCanvas.loadmap.setBlockNPC(s, s2 - 24, b3, b4);
        setPlaySound();
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (this.idImage != -1) {
            MainImage imagePartNPC = ObjectData.getImagePartNPC(this.idImage);
            if (imagePartNPC.img != null) {
                if (this.wOne == 0) {
                    this.hOne = mImage.getImageHeight(imagePartNPC.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(imagePartNPC.img.image);
                }
                mgraphics.drawRegion(imagePartNPC.img, 0, ((GameCanvas.gameTick / 7) % this.nFrame) * this.hOne, this.wOne, this.hOne, 0, this.x, this.y, 33, false);
            }
        }
        if (GameScreen.ObjFocus == null || ((GameScreen.ObjFocus != null && this != GameScreen.ObjFocus) || PaintInfoGameScreen.isLevelPoint)) {
            paintName(mgraphics, 0);
        }
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void update() {
        if (this.strChatPopup != null) {
            addChat(this.strChatPopup, true);
            this.strChatPopup = null;
        }
        if (this.chat != null) {
            this.chat.updatePos(this.x, (this.y - this.hOne) - 30);
            if (this.chat.setOff()) {
                this.chat = null;
            }
        }
        if (this.isMonPhoBangDie && this.timeFreeMove < 70) {
            this.timeFreeMove++;
            if (CRes.random(3) == 1) {
                if (CRes.random(2) == 1) {
                    LoadMap.timeVibrateScreen = 103;
                }
                int random = CRes.random(1, 3);
                for (int i = 0; i < random; i++) {
                    int random_Am_0 = CRes.random_Am_0(25);
                    int random_Am_02 = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + random_Am_0, (this.y + random_Am_02) - (this.hOne / 2));
                    if (CRes.random(3) == 1) {
                        GameScreen.addEffectEndKill(9, this.x + random_Am_0, (this.y + random_Am_02) - (this.hOne / 2));
                    }
                }
            }
            if (this.timeFreeMove >= 70) {
                for (int i2 = 0; i2 < 6; i2++) {
                    int random_Am_03 = CRes.random_Am_0(25);
                    int random_Am_04 = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + random_Am_03, (this.y + random_Am_04) - (this.hOne / 2));
                    if (CRes.random(3) == 1) {
                        GameScreen.addEffectEndKill(9, this.x + random_Am_03, (this.y + random_Am_04) - (this.hOne / 2));
                    }
                }
                GameScreen.addEffectKill(81, this.x, this.y - 20, 200, (short) 0, (byte) 0);
                this.isRemove = true;
                this.isMonPhoBangDie = false;
            }
        }
        if (this.indexSound >= 0 && GameCanvas.gameTick % this.timePlaySound == 0 && MainObject.isInScreen(this)) {
            if (this.indexSound == 44) {
                if (getCountPlayerINearMe() >= 5) {
                    mSound.playSound(this.indexSound, mSound.volumeSound);
                    return;
                }
                return;
            }
            mSound.playSound(this.indexSound, mSound.volumeSound);
        }
    }

    @Override // GameObjects.MainObject
    public void paintAvatarFocus(mGraphics mgraphics, int i, int i2) {
        MainImage imagePartNPC = ObjectData.getImagePartNPC(this.idImage);
        if (imagePartNPC.img != null) {
            if (this.wAvatar <= 0) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(imagePartNPC.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(imagePartNPC.img.image);
                }
                this.wAvatar = this.wOne;
                this.hAvatar = this.hOne;
                if (this.wAvatar > 26) {
                    this.wAvatar = 26;
                }
                if (this.hAvatar > 26) {
                    this.hAvatar = 26;
                }
            }
            mgraphics.drawRegion(imagePartNPC.img, (this.wOne / 2) - (this.wAvatar / 2), 0, this.wAvatar, this.hAvatar, 0, i, i2, 3, false);
        }
    }

    @Override // GameObjects.MainObject
    public void paintBigAvatar(mGraphics mgraphics, int i, int i2) {
        MainImage imagePartNPC = ObjectData.getImagePartNPC((short) this.IdBigAvatar);
        if (imagePartNPC.img != null) {
            mgraphics.drawImage(imagePartNPC.img, i, i2, 40, false);
        }
    }

    @Override // GameObjects.MainObject
    public boolean isNPC() {
        return true;
    }

    @Override // GameObjects.MainObject
    public void GiaoTiep() {
        MainObject.resetDirection(GameScreen.player, this);
        if (this.isPerson == 0) {
            GlobalService.gI().getlist_from_npc((byte) this.ID);
            return;
        }
        mVector mvector = new mVector("NPC menu");
        if (this.nameGiaotiep.length() > 0) {
            mvector.addElement(new iCommand(this.nameGiaotiep, 4, this));
        }
        if (checkNV()) {
            mvector.addElement(new iCommand(T.quest, 5, this));
        }
        GameCanvas.menu2.startAt_NPC(mvector, this.infoObject, this.ID, (byte) 2, false, 0);
        mSound.playSound(39, mSound.volumeSound);
    }

    public boolean checkNV() {
        for (int i = 0; i < MainQuest.vecQuestList.size(); i++) {
            if (((MainQuest) MainQuest.vecQuestList.elementAt(i)).idNPC_From == this.ID) {
                return true;
            }
        }
        for (int i2 = 0; i2 < MainQuest.vecQuestFinish.size(); i2++) {
            if (((MainQuest) MainQuest.vecQuestFinish.elementAt(i2)).idNPC_To == this.ID) {
                return true;
            }
        }
        for (int i3 = 0; i3 < MainQuest.vecQuestDoing_Main.size(); i3++) {
            if (((MainQuest) MainQuest.vecQuestDoing_Main.elementAt(i3)).idNPC_To == this.ID) {
                return true;
            }
        }
        for (int i4 = 0; i4 < MainQuest.vecQuestDoing_Sub.size(); i4++) {
            if (((MainQuest) MainQuest.vecQuestDoing_Sub.elementAt(i4)).idNPC_To == this.ID) {
                return true;
            }
        }
        return false;
    }

    public void NhiemVu() {
        mVector mvector = new mVector("NPC menu2");
        for (int i = 0; i < MainQuest.vecQuestList.size(); i++) {
            MainQuest mainQuest = (MainQuest) MainQuest.vecQuestList.elementAt(i);
            if (mainQuest.idNPC_From == this.ID) {
                iCommand icommand = new iCommand(mainQuest.name, 0, i, this);
                icommand.setFraCaption(AvMain.fraQuest, 1, 1);
                mvector.addElement(icommand);
            }
        }
        for (int i2 = 0; i2 < MainQuest.vecQuestFinish.size(); i2++) {
            MainQuest mainQuest2 = (MainQuest) MainQuest.vecQuestFinish.elementAt(i2);
            if (mainQuest2.idNPC_To == this.ID) {
                iCommand icommand2 = new iCommand(mainQuest2.name, 1, i2, this);
                icommand2.setFraCaption(AvMain.fraQuest, 1, 3);
                mvector.addElement(icommand2);
            }
        }
        for (int i3 = 0; i3 < MainQuest.vecQuestDoing_Main.size(); i3++) {
            MainQuest mainQuest3 = (MainQuest) MainQuest.vecQuestDoing_Main.elementAt(i3);
            if (mainQuest3.idNPC_To == this.ID) {
                iCommand icommand3 = new iCommand(mainQuest3.name, 2, i3, this);
                icommand3.setFraCaption(AvMain.fraQuest, 1, 2);
                mvector.addElement(icommand3);
            }
        }
        for (int i4 = 0; i4 < MainQuest.vecQuestDoing_Sub.size(); i4++) {
            MainQuest mainQuest4 = (MainQuest) MainQuest.vecQuestDoing_Sub.elementAt(i4);
            if (mainQuest4.idNPC_To == this.ID) {
                iCommand icommand4 = new iCommand(mainQuest4.name, 3, i4, this);
                icommand4.setFraCaption(AvMain.fraQuest, 1, 2);
                mvector.addElement(icommand4);
            }
        }
        if (mvector.size() == 0) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            GameCanvas.menu2.doCloseMenu();
        } else {
            GameCanvas.menu2.doCloseMenu();
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            GameCanvas.menu2.startAt(mvector, 2, T.quest, false, null);
        }
    }

    public void setPlaySound() {
        switch (this.ID) {
            case -36:
            case -20:
            case -3:
                this.indexSound = 44;
                this.timePlaySound = 150;
                return;
            case -21:
            case -5:
                this.indexSound = 43;
                this.timePlaySound = 150;
                return;
            default:
                return;
        }
    }

    public int getCountPlayerINearMe() {
        int i = 0;
        for (int i2 = 0; i2 < GameScreen.Vecplayers.size(); i2++) {
            MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i2);
            if (mainObject.typeObject == 0 && MainObject.getDistance(this.x, this.y, mainObject.x, mainObject.y) <= 120) {
                i++;
            }
        }
        return i;
    }
}
