package Skill;

import CLib.mVector;
import GameObjects.MainBuff;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameObjects.Object_Effect_Skill;
import GameObjects.Pet;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:Skill/ListSkill.class */
public class ListSkill extends MainListSkill {
    MainObject obj;
    MainObject objBeKill;
    int skill;
    int plash;
    int frame;
    int index;
    mVector vecObjBeKill;
    public byte classbuff = -1;
    public byte typebuff = 0;
    public static long lastTimeAttack;
    public static short GlobalCountdown = 0;
    public static long lastTime = 0;
    public static int limitTimeAtt = 600;

    public void setFireSkill(MainObject mainObject, mVector mvector, int i, byte b) {
        if (mvector == null || mvector.size() <= 0 || i > MainListSkill.mSkillAllClasses[mainObject.clazz].length - 1) {
            return;
        }
        this.classbuff = b;
        this.obj = mainObject;
        Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) mvector.elementAt(0);
        this.objBeKill = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
        this.vecObjBeKill = mvector;
        if (this.obj == null || this.obj.Action == 4 || this.objBeKill == null || this.objBeKill.Action == 4) {
            mainObject.KillFire = -1;
            mainObject.IDAttack = -1;
            mainObject.vecObjFocusSkill = null;
            return;
        }
        if (mainObject.typeObject == 9) {
            this.skill = MainListSkill.mSkillAllClasses[6][i];
        } else {
            this.skill = MainListSkill.mSkillAllClasses[mainObject.clazz][i];
        }
        this.index = i;
        mainObject.KillFire = i;
        this.plash = MainListSkill.mPlash[this.skill];
        this.frame = MainListSkill.mFramePlash[this.skill];
        if (mainObject.clazz == 3) {
            this.plash /= 2;
            this.frame /= 2;
        }
        if (mainObject == GameScreen.player) {
            FireSkill(MainListSkill.getRange(i));
        } else {
            FireSkill(MainListSkill.mRange[this.skill]);
        }
    }

    public void setVecBeKill(mVector mvector) {
        this.vecObjBeKill = mvector;
    }

    public void FireSkill(int i) {
        if (MainObject.getDistance(this.obj.x, this.obj.y, this.objBeKill.x, this.objBeKill.y - (this.objBeKill.hOne / 4)) <= i || this.obj.typeObject == 9) {
            if (this.obj != GameScreen.player || setMove_Skill()) {
                this.obj.toX = this.obj.x;
                this.obj.toY = this.obj.y;
                this.obj.vx = 0;
                this.obj.vy = 0;
                if (this.obj.typeObject != 9) {
                    this.obj.beginFire();
                    this.obj.PlashNow.setPlash(this.plash);
                    this.obj.KillFire = -1;
                    if (this.skill == 53) {
                        this.obj.Direction = 1;
                    } else {
                        this.obj.Direction = setDirection(this.obj, this.objBeKill);
                    }
                    setEff_More();
                    return;
                }
                return;
            }
            return;
        }
        int angle = CRes.angle(this.obj.x - this.objBeKill.x, this.obj.y - this.objBeKill.y);
        int i2 = 0;
        while (true) {
            int cos = this.objBeKill.x + ((CRes.cos(CRes.fixangle(angle % 360)) * (i - 5)) / ObjectData.MONSTER);
            int sin = this.objBeKill.y + ((CRes.sin(CRes.fixangle(angle % 360)) * (i - 5)) / ObjectData.MONSTER);
            int tile = GameCanvas.loadmap.getTile(cos, sin);
            angle += CRes.random_Am_0(90);
            i2++;
            if (i2 > 12) {
                this.obj.posTransRoad = null;
                GameCanvas.addInfoChar(T.farfocus);
                this.obj.KillFire = -1;
                this.obj.IDAttack = -1;
                this.obj.vecObjFocusSkill = null;
                setGoBack();
                return;
            }
            if (tile != -1 && tile != 1) {
                if (this.obj == GameScreen.player) {
                    this.obj.toX = this.obj.x;
                    this.obj.toY = this.obj.y;
                    GameScreen.player.xStopMove = 0;
                    GameScreen.player.yStopMove = 0;
                    this.obj.posTransRoad = GameCanvas.game.updateFindRoad(cos / LoadMap.wTile, sin / LoadMap.wTile, this.obj.x / LoadMap.wTile, this.obj.y / LoadMap.wTile, 12);
                    Player.xFocus = -1;
                    Player.yFocus = -1;
                    if (this.obj.posTransRoad == null) {
                        this.obj.toX = cos;
                        this.obj.toY = sin;
                        this.obj.xFire = cos;
                        this.obj.yFire = sin;
                        this.obj.posTransRoad = null;
                    } else if (this.obj.posTransRoad.length > 12) {
                        this.obj.posTransRoad = null;
                        GameCanvas.addInfoChar(T.farfocus);
                        setGoBack();
                        this.obj.KillFire = -1;
                        this.obj.IDAttack = -1;
                        this.obj.vecObjFocusSkill = null;
                    } else {
                        this.obj.xFire = cos;
                        this.obj.yFire = sin;
                        GameScreen.player.xStopMove = this.obj.xFire;
                        GameScreen.player.yStopMove = this.obj.yFire;
                    }
                    this.obj.countAutoMove = 0;
                } else {
                    this.obj.toX = cos;
                    this.obj.xFire = cos;
                    this.obj.toY = sin;
                    this.obj.yFire = sin;
                }
                if (this.obj.typeObject == 1) {
                    this.obj.IDAttack = this.objBeKill.ID;
                    return;
                } else {
                    this.obj.vecObjFocusSkill = this.vecObjBeKill;
                    return;
                }
            }
        }
    }

    public void setGoBack() {
        if (this.obj == GameScreen.player && Player.isAutoFire == 1 && Player.isCurAutoFire) {
            this.objBeKill.isSend = true;
            this.obj.toX = this.obj.x;
            this.obj.toY = this.obj.y;
            this.obj.xStopMove = Player.xBeginAutoFire;
            this.obj.yStopMove = Player.yBeginAutofire;
            this.obj.posTransRoad = GameCanvas.game.updateFindRoad(this.obj.xStopMove / LoadMap.wTile, this.obj.yStopMove / LoadMap.wTile, this.obj.x / LoadMap.wTile, this.obj.y / LoadMap.wTile, 20);
            Player.xFocus = -1;
            Player.yFocus = -1;
            if (this.obj.posTransRoad != null && this.obj.posTransRoad.length > 20) {
                this.obj.posTransRoad = null;
            }
        }
    }

    public void fireSkillFree() {
        if (this.obj == null || this.objBeKill == null) {
            this.obj.KillFire = -1;
            this.obj.IDAttack = -1;
            this.obj.vecObjFocusSkill = null;
            return;
        }
        if (this.obj != GameScreen.player || setMove_Skill()) {
            this.obj.toX = this.obj.x;
            this.obj.toY = this.obj.y;
            this.obj.vx = 0;
            this.obj.vy = 0;
            this.obj.beginFire();
            this.obj.PlashNow.setPlash(this.plash);
            this.obj.KillFire = -1;
            if (this.skill == 53) {
                this.obj.Direction = 1;
            } else {
                this.obj.Direction = setDirection(this.obj, this.objBeKill);
            }
            setEff_More();
        }
    }

    public void updateEffSkill() {
        if (this.obj == null || this.objBeKill == null) {
            this.obj.KillFire = -1;
            this.obj.IDAttack = -1;
            this.obj.vecObjFocusSkill = null;
            return;
        }
        if (this.obj.typeObject == 9) {
            if (this.classbuff > -1) {
                GameScreen.addEffectKillSubTime(MainListSkill.mEffectKill[this.skill], this.obj.ID, this.obj.typeObject, this.vecObjBeKill, (byte) MainListSkill.mBuffAllClasses[this.classbuff][this.index], 500);
                ((Pet) this.obj).setState((byte) 4);
                return;
            }
            return;
        }
        if (this.obj.fplash == this.frame) {
            if (this.obj == GameScreen.player) {
                Skill skillFormId = MainListSkill.getSkillFormId(this.index);
                if (GameCanvas.timeNow - lastTime > GlobalCountdown) {
                    GlobalCountdown = skillFormId.performDur;
                    lastTime = GameCanvas.timeNow;
                    sendSkill();
                }
            }
            if (this.classbuff > -1) {
                GameScreen.addEffectKillSubTime(MainListSkill.mEffectKill[this.skill], this.obj.ID, this.obj.typeObject, this.vecObjBeKill, (byte) MainListSkill.mBuffAllClasses[this.classbuff][this.index], 1250);
            } else {
                GameScreen.addEffectKill(MainListSkill.mEffectKill[this.skill], this.obj.ID, this.obj.typeObject, this.vecObjBeKill);
            }
        }
    }

    public void setEff_More() {
        if (this.obj == GameScreen.player) {
            GameScreen.player.setAddDelaySkill(this.skill, this.index);
        }
        switch (this.skill) {
            case 5:
            case 21:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 400, 0);
                return;
            case 6:
            case 47:
            case 48:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 600, 4);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
            case 17:
            case 25:
            case 26:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 35:
            case 38:
            case 45:
            default:
                return;
            case 14:
            case 34:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 600, 3);
                return;
            case 18:
            case 19:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 300, 1);
                return;
            case 20:
            case 46:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 300, 4);
                return;
            case 22:
            case 36:
            case 44:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 300, 3);
                return;
            case 23:
            case 41:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 300, 0);
                return;
            case 24:
            case 40:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 400, 1);
                return;
            case 27:
            case 37:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 400, 3);
                return;
            case 39:
            case 51:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 400, 4);
                return;
            case 42:
            case 43:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 600, 0);
                return;
            case 49:
            case 50:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 400, 1);
                return;
            case 52:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 600, 1);
                return;
            case 53:
                GameScreen.addEffectKillTime(30, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 600, 1);
                return;
            case 54:
            case 58:
            case 64:
            case 68:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 600, 0);
                return;
            case 55:
            case 59:
            case 65:
            case 69:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 600, 3);
                return;
            case 56:
            case 61:
            case 62:
            case 66:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 300, 4);
                return;
            case 57:
            case 60:
            case 63:
            case 67:
                GameScreen.addEffectKillTime(29, this.obj.ID, this.obj.typeObject, this.vecObjBeKill, 400, 1);
                return;
        }
    }

    public boolean setMove_Skill() {
        int i = this.skill;
        return true;
    }

    public boolean setMove(int i, int i2, int i3, int i4) {
        short[] updateFindRoad = GameScreen.gI().updateFindRoad(i2 / LoadMap.wTile, i4 / LoadMap.wTile, i / LoadMap.wTile, i3 / LoadMap.wTile, 12);
        if (updateFindRoad == null) {
            int tile = GameCanvas.loadmap.getTile(i2, i4);
            if (tile != -1 && tile != 1) {
                return setSendMove(i, i2, i3, i4);
            }
            GameCanvas.addInfoChar(T.farfocus);
            setGoBack();
            this.obj.KillFire = -1;
            this.obj.IDAttack = -1;
            this.obj.vecObjFocusSkill = null;
            return false;
        }
        if (updateFindRoad.length > 12) {
            GameCanvas.addInfoChar(T.farfocus);
            setGoBack();
            this.obj.KillFire = -1;
            this.obj.IDAttack = -1;
            this.obj.vecObjFocusSkill = null;
            return false;
        }
        return true;
    }

    public boolean setSendMove(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = 0;
        int i7 = 0;
        int abs = CRes.abs(i - i2);
        int abs2 = CRes.abs(i3 - i4);
        if (abs >= 4) {
            if (i > i2) {
                i6 = -6;
            } else {
                i6 = 6;
            }
        }
        if (abs2 >= 4) {
            if (i3 > i4) {
                i7 = -6;
            } else {
                i7 = 6;
            }
        }
        int i8 = abs / 6;
        int i9 = abs2 / 6;
        if (abs > abs2) {
            i5 = i8;
        } else {
            i5 = i9;
        }
        for (int i10 = 0; i10 < i5; i10++) {
            if (i10 < i8) {
                i += i6;
            }
            if (i10 < i9) {
                i3 += i7;
            }
            int tile = GameCanvas.loadmap.getTile(i, i3);
            if (tile == 1 || tile == -1) {
                GameCanvas.addInfoChar(T.farfocus);
                setGoBack();
                this.obj.KillFire = -1;
                this.obj.IDAttack = -1;
                return false;
            }
        }
        return true;
    }

    public static void doSetTimeAtt() {
        lastTimeAttack = System.currentTimeMillis();
    }

    public static boolean canAttack() {
        return System.currentTimeMillis() - lastTimeAttack > ((long) limitTimeAtt);
    }

    public void sendSkill() {
        mVector skillLan = GameScreen.player.setSkillLan(this.index, this.objBeKill);
        this.vecObjBeKill = skillLan;
        if (this.classbuff > -1) {
            setSendBuff(skillLan);
        } else if (this.objBeKill.typeObject == 1) {
            GlobalService.gI().fire_monster(skillLan, (byte) this.index);
        } else if (this.objBeKill.typeObject == 0) {
            GlobalService.gI().fire_Pk(skillLan, (byte) this.index);
        }
    }

    public void setSendBuff(mVector mvector) {
        GlobalService.gI().BuffMore((byte) this.index, this.objBeKill.typeObject, mvector);
    }

    public boolean setBuff(int i) {
        for (int i2 = 0; i2 < this.obj.vecBuff.size(); i2++) {
            if (((MainBuff) this.obj.vecBuff.elementAt(i2)).typeBuff == i) {
                return false;
            }
        }
        return true;
    }
}
