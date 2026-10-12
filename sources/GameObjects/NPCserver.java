package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mVector;
import GameScreen.GameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.FrameImage;
import Model.MainImage;
import Model.T;
import Thread_More.MiniMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/NPCserver.class */
public class NPCserver extends MainObject {
    public short idImage;
    public short wBlock;
    public short hBlock;
    public short xBlock;
    public short yBlock;
    int nFrame;
    int wAvatar;
    int hAvatar;
    int dx;
    int dy;
    Other_Players p;
    mImage imgSub;
    FrameImage imgSubFrame;
    byte[] frameArray;
    byte f;
    int indexSound = -1;
    int timePlaySound = -1;
    byte idnotFocus = Byte.MAX_VALUE;

    public NPCserver(byte b, short s, short s2, byte b2, byte b3, byte b4, String str, String str2, short s3, short s4, byte b5, byte b6, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.p = null;
        this.idImage = b;
        this.x = s;
        this.y = s2;
        this.dx = b2;
        this.dy = b3;
        this.nFrame = b4;
        this.name = str2;
        this.nameGiaotiep = str;
        this.wBlock = b5;
        this.hBlock = b6;
        this.xBlock = s3;
        this.yBlock = s4;
        this.isStop = false;
        this.isRemove = false;
        this.imgSub = mImage.createImage(bArr2, 0, bArr2.length, new StringBuffer().append("npc_server").append((int) this.idImage).toString());
        this.imgSubFrame = new FrameImage(this.imgSub, mImage.getImageWidth(this.imgSub.image), mImage.getImageHeight(this.imgSub.image) / this.nFrame);
        this.frameArray = bArr3;
        GameCanvas.loadmap.setBlockNPC_Server(this.xBlock - 12, this.yBlock, this.wBlock, this.hBlock);
        if (this.idImage != this.idnotFocus) {
            this.p = new Other_Players(this.idImage, (byte) 0, this.name, this.x, this.y);
            this.p.clazz = (byte) 1;
            this.p.Direction = 0;
            this.p.setInfo(bArr[12], bArr[13], bArr[14]);
            this.p.setWearingEquip(bArr);
            MiniMap.addNPCMini(new NPCMini(b, this.x, this.y));
            setPlaySound();
            this.p.x = this.x + this.dx;
            this.p.y = this.y + this.dy;
            this.p.toX = this.p.x;
            this.p.toY = this.p.y;
            this.hOne = (mImage.getImageHeight(this.imgSub.image) + this.p.hOne) - 10;
            this.hp = 100;
            this.maxHp = 100;
            this.Lv = (short) 1;
        }
    }

    @Override // GameObjects.MainObject
    public void setInfo(byte b, short s, short s2, byte b2, byte b3, byte b4, String str, String str2, short s3, short s4, byte b5, byte b6, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.p = null;
        this.imgSub = null;
        this.imgSubFrame = null;
        this.idImage = b;
        this.x = s;
        this.y = s2;
        this.dx = b2;
        this.dy = b3;
        this.nFrame = b4;
        this.name = str2;
        this.nameGiaotiep = str;
        this.wBlock = b5;
        this.hBlock = b6;
        this.xBlock = s3;
        this.yBlock = s4;
        this.isStop = false;
        this.isRemove = false;
        this.imgSub = mImage.createImage(bArr2, 0, bArr2.length, new StringBuffer().append("npc_server").append((int) this.idImage).toString());
        this.imgSubFrame = new FrameImage(this.imgSub, mImage.getImageWidth(this.imgSub.image), mImage.getImageHeight(this.imgSub.image) / this.nFrame);
        this.frameArray = bArr3;
        GameCanvas.loadmap.setBlockNPC_Server(this.xBlock - 12, this.yBlock, this.wBlock, this.hBlock);
        if (this.idImage != this.idnotFocus) {
            this.p = new Other_Players(this.idImage, (byte) 0, this.name, this.x, this.y);
            this.p.clazz = (byte) 1;
            this.p.Direction = 0;
            this.p.setInfo(bArr[12], bArr[13], bArr[14]);
            this.p.setWearingEquip(bArr);
            MiniMap.addNPCMini(new NPCMini(b, this.x, this.y));
            setPlaySound();
            this.p.x = this.x + this.dx;
            this.p.y = this.y + this.dy;
            this.p.toX = this.p.x;
            this.p.toY = this.p.y;
            this.hOne = (mImage.getImageHeight(this.imgSub.image) + this.p.hOne) - 10;
        }
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (this.imgSubFrame != null) {
            this.imgSubFrame.drawFrame(this.frameArray[this.f], this.x, this.y, 0, 33, mgraphics);
        }
        if (this.p != null) {
            this.p.paint(mgraphics);
            paintName(mgraphics, 2);
        }
    }

    @Override // GameObjects.MainObject
    public void paintName(mGraphics mgraphics, int i) {
        mFont mfont = mFont.tahoma_7_white;
        MainTabNew.setTextColor(i).drawString(mgraphics, this.nameGiaotiep, this.p.x, this.p.y - 70, 2, false);
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void update() {
        this.f = (byte) (this.f + 1);
        if (this.f > this.frameArray.length - 1) {
            this.f = (byte) 0;
        }
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
    public boolean canFocus() {
        return this.idImage != this.idnotFocus;
    }

    @Override // GameObjects.MainObject
    public boolean isNPC() {
        return this.idImage != this.idnotFocus;
    }

    @Override // GameObjects.MainObject
    public void paintNameFocus(mGraphics mgraphics) {
    }

    @Override // GameObjects.MainObject
    public boolean isNPC_server() {
        return true;
    }

    @Override // GameObjects.MainObject
    public int getIDnpc() {
        return this.idImage;
    }

    @Override // GameObjects.MainObject
    public void GiaoTiep() {
        if (isNPC()) {
            MainObject.resetDirection(GameScreen.player, this);
            if (this.isPerson == 0) {
                GlobalService.gI().getlist_from_npc((byte) getIDnpc());
                return;
            }
            mVector mvector = new mVector("NPCserver menu");
            if (this.nameGiaotiep.length() > 0) {
                mvector.addElement(new iCommand(this.nameGiaotiep, 4, this));
            }
            if (checkNV()) {
                mvector.addElement(new iCommand(T.quest, 5, this));
            }
            GameCanvas.menu2.startAt_NPC(mvector, this.infoObject, this.ID, (byte) 2, false, 0);
            mSound.playSound(39, mSound.volumeSound);
        }
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
        mVector mvector = new mVector("NPCserver menu2");
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

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                if (i2 >= 0 && i2 <= MainQuest.vecQuestList.size()) {
                    ((MainQuest) MainQuest.vecQuestList.elementAt(i2)).beginQuest();
                    break;
                }
                break;
            case 1:
                if (i2 >= 0 && i2 <= MainQuest.vecQuestFinish.size()) {
                    ((MainQuest) MainQuest.vecQuestFinish.elementAt(i2)).beginQuest();
                    break;
                }
                break;
            case 2:
                if (i2 >= 0 && i2 <= MainQuest.vecQuestDoing_Main.size()) {
                    ((MainQuest) MainQuest.vecQuestDoing_Main.elementAt(i2)).show_Info_Quest_Doing();
                    break;
                }
                break;
            case 3:
                if (i2 >= 0 && i2 <= MainQuest.vecQuestDoing_Sub.size()) {
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
}
