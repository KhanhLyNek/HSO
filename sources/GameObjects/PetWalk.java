package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import GameEffect.EffectSkill;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Skill.Monster_Skill;

/* loaded from: KnightOnline_v403.jar:GameObjects/PetWalk.class */
public class PetWalk extends Pet {
    public PetWalk(MainObject mainObject, short s, byte b, byte b2, byte b3) {
        super(s, b, b2, b3);
        this.owner = mainObject;
        this.typeObject = (byte) 9;
        this.ID = mainObject.ID;
        this.xAnchor = mainObject.x;
        this.yAnchor = mainObject.y;
        this.x = mainObject.x;
        this.y = mainObject.y;
        this.oldPosX = mainObject.x;
        this.oldPosY = mainObject.y;
        this.wOne = -1;
        this.hOne = -1;
        this.limitMove = 60;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = (byte) 0;
        this.timeAutoAction = CRes.random(50, 70);
        this.MonWater = 0;
        this.limitAttack = 30;
        this.timeMaxMoveAttack = 0;
    }

    public PetWalk(short s, int i, byte b, byte b2, byte b3) {
        super(s, b, b2, b3);
        this.owner = null;
        this.typeObject = (byte) 9;
        this.ID = i;
        int i2 = this.posArray[CRes.random(this.posArray.length)].x;
        int i3 = this.posArray[CRes.random(this.posArray.length)].y;
        this.xAnchor = i2;
        this.yAnchor = i3;
        this.x = i2;
        this.y = i3;
        this.oldPosX = i2;
        this.oldPosY = i3;
        this.wOne = -1;
        this.hOne = -1;
        this.limitMove = EffectSkill.EFF_BIG_SWORD_115_2;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = (byte) 0;
        this.timeAutoAction = CRes.random(50, 70);
        this.MonWater = 3;
        this.limitAttack = 200;
        this.timeMaxMoveAttack = 0;
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        try {
            paintDataEff_Top(mgraphics, this.x, this.y);
            paintBuffFirst(mgraphics);
            MainImage imagePet = ObjectData.getImagePet(((Pet) this).imageId);
            if (this.state == 1 && (this.Direction == 1 || this.Direction == 0)) {
                this.Direction = this.PrevDir;
            }
            int i = this.Action;
            if (i > ((MainMonster) this).mAction.length - 1) {
                i = 0;
            }
            if (this.f > ((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction].length - 1) {
                this.f = 0;
            }
            if (imagePet.img != null) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(imagePet.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(imagePet.img.image) / 2;
                }
                int i2 = (((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction][this.f] / 3) * this.wOne;
                int i3 = (((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction][this.f] % 3) * this.hOne;
                if (this.isWater) {
                    mgraphics.drawRegion(imagePet.img, i2, i3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y + this.dyWater + 4, 33, false);
                    mgraphics.drawRegion(MainObject.water, 0, ((i != 0 ? 2 : 0) * 17) + (((GameCanvas.gameTick / 2) % 2) * 17), 28, 17, 0, this.x + 1 + this.xtam, (this.y + this.dyWater) - 4, 3, false);
                } else {
                    mgraphics.drawRegion(imagePet.img, i2, i3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y, 33, false);
                }
            }
            paintBuffLast(mgraphics);
            paintDataEff_Bot(mgraphics, this.x, this.y);
        } catch (Exception e) {
            e.printStackTrace();
            e.toString();
        }
    }

    @Override // GameObjects.Pet
    protected void attack() {
        if (this.skillDefault == null) {
            this.skillDefault = new Monster_Skill((byte) 2);
        }
        this.vMax = 9;
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjskill.elementAt(this.attackCount);
            MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject == null) {
                this.isRunAttack = false;
                return;
            }
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) > this.limitAttack && mainObject.hp > 0) {
                this.toX = mainObject.x;
                this.toY = mainObject.y;
                move_to_XY();
                return;
            }
            if (GameCanvas.timeNow - this.timeBeginMoveAttack > this.timeMaxMoveAttack) {
                this.IDAttack = object_Effect_Skill.ID;
                object_Effect_Skill.skillMonster = this.skillDefault;
                beginFire();
                addAttackEffect();
                this.timeMaxMoveAttack = 200;
                this.timeBeginMoveAttack = GameCanvas.timeNow;
                if (this.attackType == 2) {
                    if (this.attackCount >= this.vecObjskill.size() - 1) {
                        this.isDoneAttack = false;
                        setState((byte) 4);
                        this.attackCount = 0;
                        return;
                    }
                    return;
                }
                if (this.attackType == 5 && this.attackCount == 3) {
                    this.isDoneAttack = false;
                    setState((byte) 4);
                    this.attackCount = 0;
                }
            }
        }
    }
}
