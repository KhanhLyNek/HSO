package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/MonsterWalk.class */
public class MonsterWalk extends MainMonster {
    static byte[][][] mMon0102 = {new byte[]{new byte[]{0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{0, 0, 1, 1, 0, 0, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2, 0, 0}, new byte[]{2, 2, 0, 0, 1, 1, 0, 0}}, new byte[]{new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMon012 = {new byte[]{new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{0, 0, 1, 1, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2}, new byte[]{2, 2, 0, 0, 1, 1}}, new byte[]{new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMon01 = {new byte[]{new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{1, 1, 1, 0, 0, 0}, new byte[]{1, 1, 1, 0, 0, 0}, new byte[]{1, 1, 1, 0, 0, 0}}, new byte[]{new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonBossMesdusa = {new byte[]{new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, new byte[]{1, 1, 1, 1, 0, 0, 0, 0, 2, 2, 2, 2}, new byte[]{2, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 1}}, new byte[]{new byte[]{2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, new byte[]{2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, new byte[]{2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonschiemthanh = {new byte[]{new byte[]{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}}, new byte[]{new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 5, 5, 5, 5, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}}};

    @Override // GameObjects.MainObject
    public boolean isMonsterHouse() {
        return this.typeMonster == 12;
    }

    public boolean isMonsterGate() {
        return this.typeMonster == 16;
    }

    @Override // GameObjects.MainObject
    public boolean isItemBox() {
        return this.typeMonster == 13;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v27, types: [byte[][], byte[][][]] */
    public MonsterWalk(int i, int i2, int i3, String str, int i4, int i5, int i6, int i7) {
        this.typeObject = (byte) 1;
        this.typeMonster = i3;
        this.ID = i;
        this.catalogyMonster = i2;
        this.xAnchor = i4;
        this.yAnchor = i5;
        this.x = i4;
        this.y = i5;
        this.name = str;
        this.maxHp = i6;
        this.hp = i6;
        this.Lv = (short) i7;
        this.MonWater = 1;
        this.Direction = 0;
        this.nFrame = 5;
        this.hOne = -1;
        this.wOne = -1;
        this.vMax = 3;
        this.limitMove = 60;
        this.timeAutoAction = CRes.random(50, 70);
        this.limitAttack = 50;
        this.xsai = 0;
        this.ysai = -2;
        switch (i3) {
            case 0:
                if (!isMoveSlow(this.catalogyMonster)) {
                    ((MainMonster) this).mAction = mMon012;
                    break;
                } else {
                    ((MainMonster) this).mAction = mMonBossMesdusa;
                    break;
                }
            case 1:
                ((MainMonster) this).mAction = mMon01;
                break;
            case 2:
                ((MainMonster) this).mAction = mMon0102;
                break;
            case 4:
                this.MonWater = 0;
                ((MainMonster) this).mAction = mMon012;
                break;
            case 6:
                this.limitAttack = 80;
                ((MainMonster) this).mAction = mMon012;
                break;
            case 9:
                this.MonWater = 0;
                ((MainMonster) this).mAction = mMon012;
                this.limitAttack = 80;
                break;
            case 12:
            case 16:
            case 18:
                ((MainMonster) this).mAction = new byte[][]{new byte[]{new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}}, new byte[]{new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}}};
                this.nFrame = 1;
                break;
            case 17:
                ((MainMonster) this).mAction = mMonschiemthanh;
                break;
        }
        this.timeLoadInfo = mSystem.currentTimeMillis();
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if ((this.StepMovebocap != 1 || (this.catalogyMonster != 103 && this.catalogyMonster != 149)) && this.typeMonster != 18 && !this.isDie) {
            paintDataEff_Top(mgraphics, this.x, this.y);
            paintBuffFirst(mgraphics);
            MainImage imagePartMonster = ObjectData.getImagePartMonster((short) this.catalogyMonster);
            int i = this.Action;
            if (i > ((MainMonster) this).mAction.length - 1) {
                i = 0;
            }
            if (this.f > ((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction].length - 1) {
                this.f = 0;
            }
            if (imagePartMonster.img != null) {
                if (this.wOne < 0) {
                    if (this.catalogyMonster <= 92 || this.typeMonster == 16) {
                        this.hOne = mImage.getImageHeight(imagePartMonster.img.image) / this.nFrame;
                        this.wOne = mImage.getImageWidth(imagePartMonster.img.image);
                    } else {
                        this.hOne = mImage.getImageHeight(imagePartMonster.img.image) / 3;
                        this.wOne = mImage.getImageWidth(imagePartMonster.img.image) / 2;
                    }
                }
                int i2 = 0;
                int i3 = ((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction][this.f] * this.hOne;
                if (this.catalogyMonster > 92) {
                    i2 = (((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction][this.f] / 3) * this.wOne;
                    i3 = (((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction][this.f] % 3) * this.hOne;
                }
                paintEffauto_Low(mgraphics, this.x, this.y);
                if (isMonsterHouse()) {
                    this.Direction = 0;
                    mgraphics.drawRegion(imagePartMonster.img, i2, i3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, ((this.y - this.dy) + this.dyWater) - this.vyStyleDie, 33, false);
                } else {
                    mgraphics.drawRegion(imagePartMonster.img, i2, i3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, ((this.y - this.dy) + this.dyWater) - this.vyStyleDie, 33, false);
                }
                if (this.isWater && this.dy == 0) {
                    mgraphics.drawRegion(MainObject.water, 0, ((i != 0 ? 2 : 0) * 17) + (((GameCanvas.gameTick / 2) % 2) * 17), 28, 17, 0, this.x + 1, (this.y + this.dyWater) - 4, 3, false);
                }
                this.canFocusMon = true;
            } else {
                this.canFocusMon = false;
            }
            paintIconClan(mgraphics, this.x - 1, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - 20, 2);
            paintDataEff_Bot(mgraphics, this.x, this.y);
            paintBuffLast(mgraphics);
            super.paint(mgraphics);
        }
    }

    public void checkcollide() {
        if (!isBossNew() || ((GameScreen.player.vx == 0 && GameScreen.player.vy == 0) || GameScreen.player.moveToBoss)) {
            GameScreen.player.moveToBoss = false;
            return;
        }
        if (CRes.ktvc(this.x - (this.wOne / 3), this.x + (this.wOne / 3), GameScreen.player.x - (GameScreen.player.wOne / 3), GameScreen.player.x + (GameScreen.player.wOne / 3), this.y - this.hOne, this.y - (this.hOne / 3), GameScreen.player.y - GameScreen.player.hOne, GameScreen.player.y - (GameScreen.player.hOne / 3))) {
            if (GameScreen.player.Action == 1 && !GameScreen.player.moveToBoss) {
                GameScreen.player.moveToBoss = true;
                return;
            }
            return;
        }
        GameScreen.player.moveToBoss = false;
    }

    @Override // GameObjects.MainMonster
    public void Move_to_Focus() {
        if ((this.isMove && this.catalogyMonster == 103) || this.typeBoss == 5 || this.isBinded || this.isDongBang) {
            return;
        }
        if (this.isServerControl) {
            MainObject mainObject = MainObject.get_Object(this.IDAttack, (byte) 0);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            if (mainObject.Action != 4) {
                this.toX = mainObject.x;
                this.toY = mainObject.y;
                this.vx = 0;
                this.vy = 0;
                this.Action = 0;
                this.toX = this.x;
                this.toY = this.y;
                if (CRes.random(30) == 0) {
                    this.timeFreeFire = 20;
                }
                if (this.x > mainObject.x) {
                    this.Direction = 2;
                    return;
                } else {
                    this.Direction = 3;
                    return;
                }
            }
            return;
        }
        if (this.vecObjskill != null && this.vecObjskill.size() > 0 && !this.isServerControl) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjskill.elementAt(0);
            if (GameCanvas.timeNow - this.timeBeginMoveAttack > this.timeMaxMoveAttack) {
                this.IDAttack = object_Effect_Skill.ID;
                object_Effect_Skill.skillMonster = this.skillDefault;
                beginFire();
                beginSkill();
                return;
            }
            MainObject mainObject2 = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject2 == null) {
                this.isRunAttack = false;
                return;
            }
            if (mainObject2.Action != 4) {
                this.toX = mainObject2.x;
                this.toY = mainObject2.y;
                if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject2.x, mainObject2.y) <= object_Effect_Skill.skillMonster.range) {
                    this.IDAttack = object_Effect_Skill.ID;
                    beginFire();
                    beginSkill();
                    return;
                } else {
                    if (CRes.abs(this.x - this.toX) >= 4 || CRes.abs(this.y - this.toY) >= 4) {
                        move_to_XY_Normal();
                        return;
                    }
                    return;
                }
            }
            move_to_XY_Normal();
            return;
        }
        MainObject mainObject3 = MainObject.get_Object(this.IDAttack, (byte) 0);
        if (mainObject3 == null) {
            this.isRunAttack = false;
            return;
        }
        if (mainObject3.Action != 4) {
            this.toX = mainObject3.x;
            this.toY = mainObject3.y;
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject3.x, mainObject3.y) <= this.limitAttack) {
                this.vx = 0;
                this.vy = 0;
                this.Action = 0;
                this.toX = this.x;
                this.toY = this.y;
                if (CRes.random(30) == 0) {
                    this.timeFreeFire = 20;
                }
                if (this.x > mainObject3.x) {
                    this.Direction = 2;
                    return;
                } else {
                    this.Direction = 3;
                    return;
                }
            }
            if (CRes.abs(this.x - this.toX) >= 4 || CRes.abs(this.y - this.toY) >= 4) {
                move_to_XY_Normal();
            }
        }
    }

    @Override // GameObjects.MainObject
    public void move_to_XY() {
        if (!Canmove() || this.isBinded) {
            this.toX = this.x;
            this.toY = this.y;
            return;
        }
        if (this.isMoveOut) {
            return;
        }
        if (CRes.abs(this.x - this.toX) > this.vMax + getVmount()) {
            this.vy = 0;
            this.Action = 1;
            if (CRes.abs(this.x - this.toX) > this.vMax + getVmount()) {
                if (this.x > this.toX) {
                    this.vx = -(this.vMax + getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 2;
                    return;
                } else {
                    this.vx = this.vMax + getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 3;
                    return;
                }
            }
            this.vx = this.toX - this.x;
            return;
        }
        if (CRes.abs(this.y - this.toY) > this.vMax + getVmount()) {
            this.vx = 0;
            this.Action = 1;
            if (CRes.abs(this.y - this.toY) > this.vMax + getVmount()) {
                if (this.y > this.toY) {
                    this.vy = -(this.vMax + getVmount());
                    this.PrevDir = this.Direction;
                    this.Direction = 1;
                    return;
                } else {
                    this.vy = this.vMax + getVmount();
                    this.PrevDir = this.Direction;
                    this.Direction = 0;
                    return;
                }
            }
            this.vy = this.toY - this.y;
            return;
        }
        if (this.isDetonateInDest) {
            GameScreen.addEffectEndKill(43, this.x, this.y);
            LoadMap.timeVibrateScreen = 10;
            this.isStop = true;
        }
        this.vx = 0;
        this.vy = 0;
        if ((this.catalogyMonster == 103 || this.catalogyMonster == 149) && this.StepMovebocap == 0) {
            GameScreen.addEffectEndKill(54, this.x, this.y - 20);
            this.StepMovebocap = (byte) 1;
        }
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void update() {
        super.update();
        if (!this.isInfo) {
            long currentTimeMillis = mSystem.currentTimeMillis();
            if (currentTimeMillis - this.timeLoadInfo >= 5000) {
                this.timeLoadInfo = currentTimeMillis;
                GlobalService.gI().monster_info((short) this.ID);
            }
        }
        setDie();
        if (!Canmove()) {
            return;
        }
        updateAction();
        if (this.Action != 4 && this.Action != 3 && this.Action != 2) {
            if (this.isRunAttack && !this.isMonPhoBangDie) {
                if (this.timeFreeFire > 0) {
                    if (!this.isServerControl && (!isMonsterHouse() || !isItemBox() || !isMonsterGate())) {
                        autoMoveFire();
                    }
                } else if (!isMonsterHouse() && !isItemBox() && !canNotMove() && !isMonsterGate()) {
                    Move_to_Focus();
                }
            } else if (!this.isServerControl && !isMonsterHouse() && !isItemBox() && !canNotMove() && !isMonsterGate()) {
                auto_Move();
            }
        }
        int tile = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        if (!this.isServerControl && !isMonsterHouse() && !isItemBox() && !canNotMove()) {
            setMove(this.MonWater, tile);
        }
    }

    public boolean isMoveSlow(int i) {
        if (i == 101 || i == 83 || i == 84) {
            return true;
        }
        return false;
    }
}
