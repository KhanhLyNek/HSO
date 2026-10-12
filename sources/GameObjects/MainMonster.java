package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectSkill;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Thread_More.LoadMap;

/* loaded from: KnightOnline_v403.jar:GameObjects/MainMonster.class */
public class MainMonster extends MainObject {
    public static final int MONSTER_MOVE_012 = 0;
    public static final int MONSTER_MOVE_01 = 1;
    public static final int MONSTER_MOVE_0102 = 2;
    public static final int MONSTER_MOVE_FLY_012 = 3;
    public static final int MONSTER_MOVE_WATER = 4;
    public static final int MONSTER_MOVE_FLY_0102 = 5;
    public static final int MONSTER_SEN_CHUA = 6;
    public static final int MONSTER_QUEST = 7;
    public static final int MONSTER_BO_CHUA = 8;
    public static final int MONSTER_BACH_TUOC_TRANG = 9;
    public static final int MONSTER_MOVE_FLY_012_SLOW = 10;
    public static final int MONSTER_HOUSE = 12;
    public static final int MONSTER_BOX = 13;
    public static final int MONSTER_VANTIEU = 14;
    public static final int MONSTER_MI_NUONG = 15;
    public static final int MONSTER_Boss_Medusa = 101;
    public static final int MONSTER_Boss_DeVang = 83;
    public static final int MONSTER_Boss_DeBac = 84;
    public static final int MONSTER_GATE = 16;
    public static final int MONSTER_WALK_CAN_NOT_MOVE = 17;
    public static final int MONSTER_PAINT_BY_EFFECT_AUTO = 18;
    public static final int MONSTER_FLY_CAN_NOT_MOVE = 19;
    byte[][][] mAction;
    public int timeAutoAction;
    public int catalogyMonster;
    public int timeRemove;
    public int limitMove;
    public int limitAttack;
    public int timeFreeFire;
    public int xAnchor;
    public int yAnchor;
    public int xPhoBang;
    public int yPhoBang;
    int vxDie;
    int vyDie;
    int vyStyleDie;
    int vyStyleStand;
    int imageId;
    public int MonWater;
    public int frameDie;
    public int timeBienmat;
    int wAvatar;
    int hAvatar;
    int nFrame;
    int hRegion;
    public int timeMaxMoveAttack;
    public long timeBeginMoveAttack;
    public byte loopAtack;
    int count;
    public static mHashtable HashImageMonster = new mHashtable();
    public static mVector VecCatalogyMonSter = new mVector("MainMonster VecCatalogyMonSter");
    public static short[] idBossNew = {104, 103, 105, 106, 135};
    public int timeRemoveGhost = 0;
    public mVector vecObjskill = null;
    public String nameowner = "";

    public void setIDeffect(byte b) {
    }

    public void setLvmonster(int i) {
    }

    public void setPaintCicle(boolean z) {
    }

    @Override // GameObjects.MainObject
    public boolean isBossNew() {
        for (int i = 0; i < idBossNew.length; i++) {
            if (this.catalogyMonster == idBossNew[i]) {
                return true;
            }
        }
        return false;
    }

    public void setTimelive(long j) {
    }

    public static void createMonster(int i, int i2, int i3, int i4) {
        CatalogyMonster catalogyMonster = getCatalogyMonster(i4);
        if (catalogyMonster == null) {
            mSystem.println(new StringBuffer().append("khong lay dc catalogy ").append(i4).toString());
        }
        switch (catalogyMonster.typeMove) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 6:
            case 9:
                GameScreen.addPlayer(new MonsterWalk(i, catalogyMonster.id, catalogyMonster.typeMove, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 3:
            case 5:
            case 8:
            case 10:
                GameScreen.addPlayer(new MonsterFly(i, catalogyMonster.id, catalogyMonster.typeMove, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 7:
                GameScreen.addPlayer(new MonsterQuest(i, catalogyMonster.id, catalogyMonster.typeMove, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 11:
            default:
                GameScreen.addPlayer(new MonsterWalk(i, catalogyMonster.id, 0, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 12:
                GameScreen.addPlayer(new MonsterWalk(i, catalogyMonster.id, 12, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 13:
                if (catalogyMonster.id == 110) {
                    GameScreen.addPlayer(new Monsterplus(i, catalogyMonster.id, 13, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                    return;
                } else {
                    GameScreen.addPlayer(new MonsterBox(i, catalogyMonster.id, 13, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                    return;
                }
            case 14:
                GameScreen.addPlayer(new Monstervantieu(i, catalogyMonster.id, 14, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 15:
                GameScreen.addPlayer(new Minuong(i, catalogyMonster.id, 14, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 16:
                GameScreen.addPlayer(new MonsterWalk(i, catalogyMonster.id, 16, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 17:
                GameScreen.addPlayer(new MonsterWalk(i, catalogyMonster.id, 17, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 18:
                GameScreen.addPlayer(new MonsterWalk(i, catalogyMonster.id, 18, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
            case 19:
                GameScreen.addPlayer(new MonsterFly(i, catalogyMonster.id, 19, catalogyMonster.name, i2, i3, catalogyMonster.maxHp, catalogyMonster.lv));
                return;
        }
    }

    @Override // GameObjects.MainObject
    public void paintAvatarFocus(mGraphics mgraphics, int i, int i2) {
        MainImage imagePartMonster = ObjectData.getImagePartMonster((short) this.catalogyMonster);
        if (imagePartMonster.img != null) {
            if (this.wAvatar <= 0) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(imagePartMonster.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(imagePartMonster.img.image);
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
            mgraphics.drawRegion(imagePartMonster.img, (this.wOne / 2) - (this.wAvatar / 2), 0, this.wAvatar, this.hAvatar, 0, i, i2, 3, false);
        }
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void update() {
        if (this.isDie && this.Action != 4) {
            this.Action = 4;
            this.timedie = GameCanvas.timeNow;
        }
        if (this.isServerControl && this.isMove) {
            move_to_XY();
        }
        updateDataEffect();
        super.update();
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        paintDongBang(mgraphics);
        paintEffauto(mgraphics, this.x, this.y);
    }

    public void updateAction() {
        if (this.isMoveOut) {
            return;
        }
        this.f++;
        if (this.f > this.mAction[this.Action][this.Direction > 2 ? 2 : this.Direction].length - 1) {
            this.f = 0;
            if (this.Action == 3) {
                this.Action = 0;
                this.vx = 0;
                this.vy = 0;
            }
            if (this.Action == 2) {
                if (!isBossNew()) {
                    this.Action = 0;
                    this.vx = 0;
                    this.vy = 0;
                } else {
                    if (this.loopAtack >= 0) {
                        this.loopAtack = (byte) (this.loopAtack - 1);
                    }
                    if (this.loopAtack <= 0) {
                        this.Action = 0;
                        this.vx = 0;
                        this.vy = 0;
                    }
                }
            }
        }
        if (this.Action == 1 && this.vx == 0 && this.vy == 0) {
            this.Action = 0;
            this.f = 0;
        }
        if (this.timeFreeFire > 0) {
            this.timeFreeFire--;
        }
        if (this.isDie || this.Action == 4) {
            if (this.typeBoss == 3 || this.typeBoss == 4) {
                this.timeRemove++;
                if (this.timeRemove > 600) {
                    this.isRemove = true;
                }
            }
        }
    }

    public static CatalogyMonster getCatalogyMonster(int i) {
        for (int i2 = 0; i2 < VecCatalogyMonSter.size(); i2++) {
            CatalogyMonster catalogyMonster = (CatalogyMonster) VecCatalogyMonSter.elementAt(i2);
            if (catalogyMonster.id == i) {
                return catalogyMonster;
            }
        }
        return null;
    }

    public void auto_Move() {
        if (this.typeBoss == 5 || isMonsterHouse() || isItemBox() || this.isStun || this.isBinded || this.isDongBang || this.isSleep || this.isno || this.isMoveOut) {
            return;
        }
        if (this.typeBoss == 3 || this.typeBoss == 4) {
            this.toX = this.xPhoBang;
            this.toY = this.yPhoBang;
            move_to_XY();
            if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) <= LoadMap.wTile / 2 && this.isMonPhoBangDie) {
                if (this.typeBoss == 3) {
                    GameScreen.addEffectEndKill(36, this.x, this.y - (this.hOne / 2));
                    LoadMap.timeVibrateScreen = 103;
                } else if (this.typeBoss == 4) {
                    for (int i = 0; i < 3; i++) {
                        int i2 = this.hOne;
                        int i3 = this.wOne;
                        if (this.hOne <= 1) {
                            i2 = 40;
                        }
                        if (i3 <= 1) {
                            i3 = 40;
                        }
                        GameScreen.addEffectEndKill(36, this.x + CRes.random_Am_0(i3 / 2), (this.y - (this.hOne / 2)) + CRes.random_Am_0(i2 / 2));
                    }
                    LoadMap.timeVibrateScreen = EffectSkill.EFF_KIEM_LON_NGU;
                }
                this.isRemove = true;
                this.isMonPhoBangDie = false;
                return;
            }
            return;
        }
        if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) > this.limitMove + (this.limitMove / 2)) {
            if (!MainObject.isInScreen(this) && !setIsInScreen(this.xAnchor, this.yAnchor, this.wOne, this.hOne)) {
                this.x = this.xAnchor;
                this.y = this.yAnchor;
                this.toX = this.xAnchor;
                this.toY = this.yAnchor;
                return;
            }
            this.toX = this.xAnchor;
            this.toY = this.yAnchor;
            move_to_XY();
            return;
        }
        if (!MainObject.isInScreen(this) && !setIsInScreen(this.xAnchor, this.yAnchor, this.wOne, this.hOne)) {
            this.x = this.xAnchor;
            this.y = this.yAnchor;
            this.toX = this.xAnchor;
            this.toY = this.yAnchor;
            return;
        }
        this.time++;
        if (this.Action != 4) {
            if (this.timeStand > 0) {
                this.time = 0;
                this.Action = 0;
                this.vx = 0;
                this.vy = 0;
                this.timeStand--;
            } else if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, GameScreen.player.x, GameScreen.player.y) < 50) {
                if (this.Action == 1) {
                    if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove - this.vMax) {
                        this.time = 0;
                        this.Action = 0;
                        this.vx = 0;
                        this.vy = 0;
                        if (this.x > GameScreen.player.x) {
                            this.Direction = 2;
                        } else {
                            this.Direction = 3;
                        }
                    }
                } else if (this.Action == 0 || CRes.random(30) == 0) {
                    this.vx = 0;
                    this.vy = 0;
                    if (this.time > this.timeAutoAction) {
                        this.time = 0;
                        this.Action = 1;
                        this.Direction = CRes.random(4);
                        setSpeedInDirection(this.vMax - 2);
                    }
                    if (this.x > GameScreen.player.x) {
                        this.Direction = 2;
                    } else {
                        this.Direction = 3;
                    }
                }
            } else if (this.Action == 1) {
                if (this.time > this.timeAutoAction || CRes.random(16) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove - this.vMax) {
                    this.time = 0;
                    this.Action = 0;
                    this.vx = 0;
                    this.vy = 0;
                }
            } else if (this.Action == 0) {
                this.vx = 0;
                this.vy = 0;
                if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0) {
                    this.time = 0;
                    this.Action = 1;
                    this.Direction = CRes.random(4);
                    setSpeedInDirection(this.vMax);
                }
            }
            if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) > this.limitMove) {
                if (CRes.abs(this.x - this.xAnchor) > CRes.abs(this.y - this.yAnchor)) {
                    if (this.x > this.xAnchor) {
                        this.Direction = 2;
                    } else {
                        this.Direction = 3;
                    }
                } else if (this.y > this.yAnchor) {
                    this.Direction = 1;
                } else {
                    this.Direction = 0;
                }
                setSpeedInDirection(this.vMax);
            }
        }
    }

    public void autoMoveFire() {
        if (this.typeBoss == 5) {
            return;
        }
        this.time++;
        if (this.Action != 4) {
            if (this.Action == 1) {
                if (this.time > this.timeAutoAction || CRes.random(16) == 0) {
                    this.time = 0;
                    this.Action = 0;
                    this.vx = 0;
                    this.vy = 0;
                    MainObject mainObject = MainObject.get_Object(this.IDAttack, (byte) 0);
                    if (mainObject != null) {
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
                return;
            }
            if (this.Action == 0) {
                this.vx = 0;
                this.vy = 0;
                if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0) {
                    this.time = 0;
                    this.Action = 1;
                    this.Direction = CRes.random(4);
                    setSpeed(this.vMax);
                }
            }
        }
    }

    public void Move_to_Focus() {
        if (this.typeBoss == 5 || this.isBinded || this.isDongBang) {
            return;
        }
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjskill.elementAt(0);
            if (GameCanvas.timeNow - this.timeBeginMoveAttack > this.timeMaxMoveAttack) {
                this.IDAttack = object_Effect_Skill.ID;
                object_Effect_Skill.skillMonster = this.skillDefault;
                beginFire();
                beginSkill();
                return;
            }
            MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            this.toX = mainObject.x;
            this.toY = mainObject.y;
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) <= object_Effect_Skill.skillMonster.range) {
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
        MainObject mainObject2 = MainObject.get_Object(this.IDAttack, (byte) 0);
        if (mainObject2 == null) {
            this.isRunAttack = false;
            return;
        }
        this.toX = mainObject2.x;
        this.toY = mainObject2.y;
        if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject2.x, mainObject2.y) <= this.limitAttack) {
            this.vx = 0;
            this.vy = 0;
            this.Action = 0;
            this.toX = this.x;
            this.toY = this.y;
            if (CRes.random(30) == 0) {
                this.timeFreeFire = 20;
            }
            if (this.x > mainObject2.x) {
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

    public void Move_To_Player_Skill() {
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjskill.elementAt(0);
            if (GameCanvas.timeNow - this.timeBeginMoveAttack > this.timeMaxMoveAttack) {
                this.IDAttack = object_Effect_Skill.ID;
                object_Effect_Skill.skillMonster = this.skillDefault;
                beginFire();
                beginSkill();
                return;
            }
            MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            this.toX = mainObject.x;
            this.toY = mainObject.y;
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) <= object_Effect_Skill.skillMonster.range) {
                this.IDAttack = object_Effect_Skill.ID;
                beginFire();
                beginSkill();
            } else if (CRes.abs(this.x - this.toX) >= 4 || CRes.abs(this.y - this.toY) >= 4) {
                move_to_XY_Normal();
            }
        }
    }

    public void Reveive() {
        ReveiceMonster();
        this.isRunAttack = false;
        resetXY();
        this.frameDie = 0;
        this.timeFreeFire = 0;
        this.timeBienmat = 5;
        this.f = 0;
        this.Action = 0;
        this.time = 0;
        this.isDie = false;
        this.vxDie = 0;
        this.vyDie = 0;
        this.hp = this.maxHp;
        this.vecBuff.removeAllElements();
        this.vyStyleStand = 0;
        this.vyStyleDie = 0;
    }

    public void setDie() {
        if (this.Action == 4) {
            this.frameDie++;
            if (GameCanvas.gameTick % 10 == 0 && this.timeReveice >= 0 && this.isDie && (GameCanvas.timeNow - this.timedie) / 1000 > this.timeReveice) {
                Reveive();
            }
            if (!this.isDie) {
                this.x += this.vxDie;
                this.y += this.vyDie;
                if (this.vyStyleDie > 0) {
                    this.vyStyleDie -= 3;
                    if (this.vyStyleDie <= 0 && this.vyStyleStand > 2) {
                        this.vyStyleStand -= 2;
                        this.vyStyleDie = this.vyStyleStand;
                        if (CRes.abs(this.vxDie) > 1) {
                            this.vxDie -= this.vxDie / CRes.abs(this.vxDie);
                        }
                        if (CRes.abs(this.vyDie) > 1) {
                            this.vyDie -= this.vyDie / CRes.abs(this.vyDie);
                        }
                    }
                } else {
                    this.vxDie >>= 1;
                    this.vyDie >>= 1;
                }
                if (this.frameDie >= this.timeBienmat) {
                    GameScreen.addEffectEndKill(11, this.x, this.y - (this.hOne / 4));
                    if (!isMonsterHouse() || !isItemBox()) {
                        this.isDie = true;
                    } else if (this.coutEff < -10) {
                        this.isDie = true;
                    }
                }
            }
            if (isMonsterHouse() || isItemBox() || this.typeMonster == 16) {
                this.coutEff--;
                if (this.coutEff > 0 && this.timeFreeMove < 70) {
                    this.timeFreeMove++;
                    if (CRes.random(3) == 1) {
                        if (CRes.random(2) == 1) {
                            LoadMap.timeVibrateScreen = 103;
                        }
                        int random = CRes.random(1, 3);
                        for (int i = 0; i < random; i++) {
                            int random_Am_0 = CRes.random_Am_0(25);
                            int random_Am_02 = CRes.random_Am_0(30);
                            GameScreen.addEffectEndKill(36, this.x + random_Am_0, ((this.y + random_Am_02) - (this.hOne / 3)) + 10);
                            if (CRes.random(3) == 1) {
                                GameScreen.addEffectEndKill(9, this.x + random_Am_0, ((this.y + random_Am_02) - (this.hOne / 3)) + 10);
                            }
                        }
                    }
                    if (this.timeFreeMove >= 70) {
                        for (int i2 = 0; i2 < 6; i2++) {
                            int random_Am_03 = CRes.random_Am_0(25);
                            int random_Am_04 = CRes.random_Am_0(30);
                            GameScreen.addEffectEndKill(36, this.x + random_Am_03, ((this.y + random_Am_04) - (this.hOne / 3)) + 10);
                            if (CRes.random(3) == 1) {
                                GameScreen.addEffectEndKill(9, this.x + random_Am_03, ((this.y + random_Am_04) - (this.hOne / 2)) + 10);
                            }
                        }
                        this.isRemove = true;
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        if (this.hp <= 0) {
            this.hp = 0;
            this.Action = 4;
            this.timedie = GameCanvas.timeNow;
            resetXY();
        }
    }

    public void ReveiceMonster() {
        boolean z;
        int i = 0;
        if (this.typeMonster == 7) {
            this.x = this.xAnchor;
            this.y = this.yAnchor;
            return;
        }
        do {
            this.x = this.xAnchor + CRes.random_Am_0(48);
            this.y = this.yAnchor + CRes.random_Am_0(48);
            int tile = GameCanvas.loadmap.getTile(this.x, this.y);
            z = (tile == 1 || tile == -1) ? false : true;
            i++;
            if (i > 15) {
                z = true;
                this.x = this.xAnchor;
                this.y = this.yAnchor;
            }
        } while (!z);
    }

    public void startDeadFly(int i, int i2, int i3, int i4) {
        this.timedie = GameCanvas.timeNow;
        this.Action = 4;
        this.vx = 0;
        this.vy = 0;
        this.vxDie = i;
        this.vyDie = i2;
        this.vyStyleDie = i4;
        this.vyStyleStand = i4;
        this.timeBienmat = i3;
        this.isDie = false;
        if (isMonsterHouse() || isItemBox() || this.typeMonster == 16) {
            this.vxDie = 0;
            this.vyDie = 0;
            this.vyStyleDie = 0;
            this.vyStyleStand = 0;
        }
    }

    public void resetV() {
        this.vx = 0;
        this.vy = 0;
        this.toX = this.x;
        this.toY = this.y;
    }

    @Override // GameObjects.MainObject
    public void startDie() {
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            ((Object_Effect_Skill) this.vecObjskill.elementAt(0)).skillMonster = this.skillDefault;
        }
        beginSkill();
    }

    public void beginSkill() {
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjskill.elementAt(0);
            if (object_Effect_Skill != null && object_Effect_Skill.skillMonster != null) {
                if (object_Effect_Skill.skillMonster.typeSkill == 124) {
                    GameScreen.StartEffect_Chiemthanh(object_Effect_Skill.skillMonster.typeSkill, this.ID, (byte) 1, this.vecObjskill, object_Effect_Skill.skillMonster.typeSub);
                } else {
                    GameScreen.addEffectKill(object_Effect_Skill.skillMonster.typeSkill, this.ID, (byte) 1, this.vecObjskill, object_Effect_Skill.skillMonster.typeSub);
                }
            }
            this.vecObjskill = null;
        }
    }

    @Override // GameObjects.MainObject
    public void beginFire() {
        this.Action = 2;
        this.loopAtack = (byte) CRes.random(3, 5);
        resetAction();
        this.f = 0;
    }

    @Override // GameObjects.MainObject
    public void addiconClan() {
    }

    @Override // GameObjects.MainObject
    public boolean canNotMove() {
        return this.typeMonster == 17 || this.typeMonster == 19 || this.typeMonster == 18;
    }
}
