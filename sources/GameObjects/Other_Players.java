package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectCharWearing;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.PopupChat;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Skill.ListSkill;
import Skill.SplashSkill;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/Other_Players.class */
public class Other_Players extends MainObject {
    private PopupChat nameStore;
    byte[] vmove;

    public Other_Players(int i, byte b, String str, int i2, int i3) {
        super(i, b, str, i2, i3);
        this.vmove = new byte[]{-1, 1};
        this.vMax = 6;
        this.hOne = 40;
        this.wOne = 30;
        this.hp = 0;
        this.maxHp = 0;
        this.mp = 0;
        this.maxMp = 0;
        this.xsai = 1;
        this.ysai = 2;
        this.PlashNow = new SplashSkill();
        this.ListKillNow = new ListSkill();
        this.countCharStand = (short) 0;
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

    @Override // GameObjects.MainObject
    public void paintName(mGraphics mgraphics, int i) {
        if (GameScreen.infoGame.isMapThachdau()) {
            return;
        }
        byte b = GameScreen.player.typePk;
        boolean z = true;
        if (GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap) && this.typePk == b) {
            z = false;
        }
        String str = this.name;
        mFont mfont = mFont.tahoma_7_white;
        mFont textColor = MainTabNew.setTextColor(i);
        boolean z2 = true;
        int i2 = 0;
        if (this.typeSpec == 1) {
            if (this.typePk > 0) {
                z2 = false;
            }
            i2 = 5;
        }
        int i3 = 18;
        if (PaintInfoGameScreen.isLevelPoint) {
            i3 = 12;
        }
        if (this.typeMonster == 7) {
            i3 += 8;
        }
        if (this.isObject && PaintInfoGameScreen.isLevelPoint) {
            i3 += 6;
        }
        if (this.idName != -1) {
            z2 = false;
        }
        if (z2 && !this.Namearena) {
            textColor.drawString(mgraphics, str, this.x, (((((((this.y - this.ysai) - this.dy) + this.dyWater) - (this.isDongBang ? 5 : 0)) - this.hOne) - i3) - this.dyMount) - this.yjum, 2, false);
        }
        if (this.typeObject == 0 && this.typeSpec == 1 && z2 && !this.iscuop) {
            i3 += 10;
            textColor.drawString(mgraphics, T.nhanban, this.x, (((((((this.y - this.ysai) - this.dy) + this.dyWater) - (this.isDongBang ? 5 : 0)) - this.hOne) - i3) - this.dyMount) - this.yjum, 2, false);
        }
        if (this.typeObject == 2 && this.chat == null) {
            AvMain.fraQuest.drawFrame(this.typeNPC, this.x - 6, (((((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - i3) - 18) - 4) + ((GameCanvas.gameTick / 2) % 4), 0, mgraphics);
        }
        int i4 = 0;
        if ((Player.party != null && this.isParty) || this.isShowHP || this.typeMonster == 7) {
            int i5 = 44;
            if (this.typeObject == 2 || this.typeMonster == 7) {
                i5 = this.hOne + 5;
            }
            mgraphics.setColor(-8714722);
            mgraphics.fillRect(this.x - 20, ((((((this.y - this.ysai) - this.dy) + this.dyWater) - i5) - i3) - this.dyMount) - this.yjum, 40, 3, false);
            mgraphics.setColor(-579511);
            mgraphics.fillRect(this.x - 20, ((((((this.y - this.ysai) - this.dy) + this.dyWater) - i5) - i3) - this.dyMount) - this.yjum, (40 * this.hp) / this.maxHp, 3, false);
            i4 = 0 + 5;
        }
        if (this.myClan != null && this.typeSpec != 1 && !this.Namearena) {
            paintIconClan(mgraphics, this.x - 1, ((((((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - i3) - 8) - i4) - this.dyMount) - this.yjum, 2);
            i4 += 16;
        }
        if (this.typePk >= 0 && this.typeObject == 0 && z && !isPkVantieu()) {
            AvMain.fraPk.drawFrame((this.typePk * 3) + ((GameCanvas.gameTick / 3) % 3), this.x, ((((((((this.y - this.dy) + this.dyWater) - this.ysai) - (i4 + 59)) + 18) - i3) + i2) - this.dyMount) - this.yjum, 0, 33, mgraphics);
        }
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (mSystem.isj2me) {
            if (this.typeObject == 0) {
                if (GameScreen.isHideOderPlayer) {
                    painthidePlayer(mgraphics, 0);
                    paintNameStore(mgraphics, this.x, this.y);
                    return;
                } else if (GameScreen.isHideFullOderPlayer) {
                    paintName(mgraphics, 0);
                    paintNameStore(mgraphics, this.x, this.y);
                    return;
                }
            }
        } else if (this.typeObject == 0 && GameScreen.isHideOderPlayer) {
            painthidePlayer(mgraphics, 0);
            paintNameStore(mgraphics, this.x, this.y);
            return;
        }
        if (this.typeObject == 2) {
            if (this.imageId == -1) {
                paintPlayer(mgraphics, 0);
            } else {
                paintObject(mgraphics, 0);
            }
        } else {
            paintPlayer(mgraphics, 0);
        }
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
    public void paintAvatarFocus(mGraphics mgraphics, int i, int i2) {
        if (GameScreen.infoGame.isMapThachdau()) {
            return;
        }
        super.paintAvatarFocus(mgraphics, i, i2);
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void update() {
        updateDataEffect();
        updateEffectCharWearing();
        updateActionPerson();
        if (this.nameStore != null) {
            this.nameStore.updatePos(this.x, (this.y - this.hOne) - 30);
        }
        if (this.KillFire != -1) {
            if (CRes.abs(this.x - this.xFire) <= this.vMax + getVmount() && CRes.abs(this.y - this.yFire) <= this.vMax + getVmount()) {
                this.timeHuyKill++;
                this.posTransRoad = null;
                this.ListKillNow.setFireSkill(this, this.vecObjFocusSkill, this.KillFire, (byte) -1);
                if (this.timeHuyKill > 5) {
                    this.ListKillNow.fireSkillFree();
                    this.timeHuyKill = 0;
                    this.KillFire = -1;
                }
            }
        } else {
            this.timeHuyKill = 0;
        }
        if (!MainObject.isInScreen(this) && !setIsInScreen(this.toX, this.toY, this.wOne, this.hOne)) {
            this.x = this.toX;
            this.y = this.toY;
            this.vx = 0;
            this.vy = 0;
            if (this.Action != 4) {
                this.Action = 0;
                return;
            }
            return;
        }
        Move_to_Focus_Person();
        setMove(1, GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy));
        updateEye();
        updateoverHP_MP();
        super.update();
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
    }

    public void move_to_XY(int i, int i2) {
        if (CRes.abs(this.x - i) > 2) {
            this.vy = 0;
            this.Action = 1;
            if (CRes.abs(this.x - i) > this.vMax + getVmount()) {
                if (this.x > i) {
                    this.vx = -(this.vMax + getVmount());
                    this.Direction = 2;
                    return;
                } else {
                    this.vx = this.vMax + getVmount();
                    this.Direction = 3;
                    return;
                }
            }
            this.vx = i - this.x;
            return;
        }
        if (CRes.abs(this.y - i2) > 2) {
            this.vx = 0;
            this.Action = 1;
            if (CRes.abs(this.y - i2) > this.vMax + getVmount()) {
                if (this.y > i2) {
                    this.vy = -(this.vMax + getVmount());
                    this.Direction = 1;
                    return;
                } else {
                    this.vy = this.vMax + getVmount();
                    this.Direction = 0;
                    return;
                }
            }
            this.vy = i2 - this.y;
            return;
        }
        this.vx = 0;
        this.vy = 0;
    }

    @Override // GameObjects.MainObject
    public int getIDnpc() {
        return this.isBot;
    }

    @Override // GameObjects.MainObject
    public void GiaoTiep() {
        if (isNPC()) {
            if (this.isObject) {
                return;
            }
            MainObject.resetDirection(GameScreen.player, this);
            if (this.isPerson == 0) {
                GlobalService.gI().getlist_from_npc((byte) getIDnpc());
                return;
            }
            mVector mvector = new mVector("Other_Player menu");
            if (this.nameGiaotiep.length() > 0) {
                mvector.addElement(new iCommand(this.nameGiaotiep, 4, this));
            }
            if (checkNV()) {
                mvector.addElement(new iCommand(T.quest, 5, this));
            }
            GameCanvas.menu2.startAt(mvector, 2, T.giaotiep, false, null);
            return;
        }
        if (this.typeObject == 0 && this.typeSpec == 0) {
            mVector mvector2 = new mVector("Other_Player menu2");
            mvector2.addElement(GameScreen.gI().cmdChat);
            mvector2.addElement(GameScreen.gI().cmdAddfriend);
            mvector2.addElement(GameScreen.gI().cmdInfoChar);
            if (Player.party == null || Player.party.vecPartys.size() < 5) {
                mvector2.addElement(GameScreen.gI().cmdMoiParty);
            }
            mvector2.addElement(GameScreen.gI().cmdBuy_Sell);
            if (LoadMap.typeMap != 1 && LoadMap.typeMap != 2) {
                mvector2.addElement(GameScreen.gI().cmdThachDau);
            }
            if (GameScreen.player.myClan != null && GameScreen.player.myClan.setAddMem()) {
                mvector2.addElement(GameScreen.gI().cmdAddMemClan);
            }
            mVector vectorObjectNear = vectorObjectNear();
            vectorObjectNear.insertElementAt(this, 0);
            GameCanvas.menu2.startAt(mvector2, 2, T.giaotiep, true, vectorObjectNear);
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
        mVector mvector = new mVector("Other_Player menu3");
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

    @Override // GameObjects.MainObject
    public void addEffectCharWearing(int i, int i2) {
        this.vecEffectCharWearing.addElement(new EffectCharWearing((byte) i, i2));
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

    @Override // GameObjects.MainObject
    public boolean canFocus() {
        return this.typefocus == 1;
    }

    @Override // GameObjects.MainObject
    public boolean canfire() {
        return this.typefire == 1;
    }
}
