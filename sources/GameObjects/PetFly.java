package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;

/* loaded from: KnightOnline_v403.jar:GameObjects/PetFly.class */
public class PetFly extends Pet {
    public byte[] dypet;
    public byte dyshadow;
    int xs;
    int ys;
    int act;
    int fr;

    public PetFly(MainObject mainObject, short s, byte b, byte b2, byte b3) {
        super(s, b, b2, b3);
        this.dypet = new byte[]{0, 5, 5};
        this.dyshadow = (byte) 0;
        this.xs = 0;
        this.ys = 0;
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
        this.limitMove = 48;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = (byte) 0;
        this.timeAutoAction = CRes.random(200, 250);
        this.MonWater = 0;
        this.limitAttack = 10;
        this.timeMaxMoveAttack = 0;
        this.yfly = (short) 50;
        if (b2 == 3 || b2 == 9) {
            this.dyshadow = (byte) 0;
        } else {
            this.dyshadow = (byte) 6;
        }
    }

    public PetFly(short s, int i, byte b, byte b2, byte b3) {
        super(s, b, b2, b3);
        this.dypet = new byte[]{0, 5, 5};
        this.dyshadow = (byte) 0;
        this.xs = 0;
        this.ys = 0;
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
        this.limitMove = 48;
        this.Direction = 0;
        this.vMax = 4;
        this.Action = 0;
        this.state = (byte) 0;
        this.timeAutoAction = CRes.random(200, 250);
        this.MonWater = 3;
        this.limitAttack = 10;
        this.timeMaxMoveAttack = 0;
        this.yfly = (short) 50;
        if (b2 == 3 || b2 == 9) {
            this.dyshadow = (byte) 0;
        } else {
            this.dyshadow = (byte) 6;
        }
    }

    @Override // GameObjects.Pet
    protected void roam() {
        switch (this.typemove) {
            case 1:
                this.vMax = 1;
                if (this.Action == 1) {
                    if (this.time > this.timeAutoAction || CRes.random(16) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove) {
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
                if (this.owner != null) {
                    if (this.owner.Action == 1 && MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) > 40) {
                        setState((byte) 1);
                    }
                    if (this.owner.Action == 0 && MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) > this.limitMove * 2) {
                        setState((byte) 3);
                        return;
                    }
                    return;
                }
                int distance = MainObject.getDistance(this.x, this.y, GameScreen.player.x, GameScreen.player.y);
                if (distance < 80 && distance > 40 && CRes.random(6) == 0) {
                    setState((byte) 6);
                    return;
                }
                return;
            case 2:
                this.vMax = 1;
                if (this.Action == 1) {
                    if ((this.time > this.timeAutoAction || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove) && this.yfly >= 50) {
                        if (this.count > 20 && !this.isWater) {
                            this.time = 0;
                            this.vx = 0;
                            this.vy = 0;
                            this.Action = 0;
                            this.count = 0;
                        }
                        if (this.count <= 20) {
                            this.count++;
                        }
                    }
                } else if (this.Action == 0) {
                    this.vx = 0;
                    this.vy = 0;
                    if (this.yfly >= 0) {
                        this.yfly = (short) (this.yfly - 5);
                    }
                    if (GameCanvas.gameTick % 30 == 0) {
                        this.Direction = CRes.random(4);
                    }
                    if (this.time > this.timeAutoAction && this.yfly <= 0) {
                        this.time = 0;
                        setSpeedInDirection(this.vMax);
                        this.Action = 1;
                    }
                }
                if (this.owner != null) {
                    if (this.owner.Action == 1) {
                        if (MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) > 40) {
                            setState((byte) 1);
                            return;
                        }
                        return;
                    } else {
                        if (this.owner.Action == 0 && MainObject.getDistance(this.x, this.y, this.ownerX, this.ownerY) > this.limitMove * 2) {
                            setState((byte) 3);
                            return;
                        }
                        return;
                    }
                }
                int distance2 = MainObject.getDistance(this.x, this.y, GameScreen.player.x, GameScreen.player.y);
                if (distance2 < 80 && distance2 > 40 && CRes.random(6) == 0) {
                    setState((byte) 6);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        try {
            paintDataEff_Top(mgraphics, this.x, this.y);
            paintBuffFirst(mgraphics);
            if (this.state == 1 && (this.Direction == 1 || this.Direction == 0)) {
                this.Direction = this.PrevDir;
            }
            MainImage imagePet = ObjectData.getImagePet(((Pet) this).imageId);
            if (imagePet.img != null) {
                if (this.wOne < 0) {
                    this.hOne = mImage.getImageHeight(imagePet.img.image) / this.nFrame;
                    this.wOne = mImage.getImageWidth(imagePet.img.image) / 2;
                }
                mgraphics.drawImage(MainObject.shadow, this.x + this.xtam, this.y - this.dyshadow, 3, false);
                if (this.isWater && this.yfly <= 0) {
                    mgraphics.drawRegion(imagePet.img, this.xs, this.ys, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y + this.dyWater + 8, 33, false);
                    mgraphics.drawRegion(MainObject.water, 0, ((this.Action != 0 ? 2 : 0) * 17) + (((GameCanvas.gameTick / 2) % 2) * 17), 28, 17, 0, this.x + this.xtam, (this.y + this.dyWater) - 8, 3, false);
                } else {
                    mgraphics.drawRegion(imagePet.img, this.xs, this.ys, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x + this.xtam, this.y - this.yfly, 33, false);
                }
            }
            paintBuffLast(mgraphics);
            paintDataEff_Bot(mgraphics, this.x, this.y);
        } catch (Exception e) {
        }
    }

    @Override // GameObjects.Pet
    protected void attack() {
        this.vMax = 12;
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjskill.elementAt(this.attackCount);
            MainObject mainObject = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject == null || mainObject.hp <= 0) {
                this.isRunAttack = false;
                setState((byte) 4);
                return;
            }
            this.toX = mainObject.x;
            this.toY = mainObject.y;
            move_to_XY();
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject.x, mainObject.y) < 30) {
                if (this.yfly >= 15) {
                    this.yfly = (short) (this.yfly - 15);
                }
                this.f = 4;
                if (this.yfly <= 15) {
                    addAttackEffect();
                    setState((byte) 4);
                    return;
                }
                return;
            }
            if (this.yfly <= 50) {
                this.yfly = (short) (this.yfly + 5);
            }
        }
    }

    @Override // GameObjects.Pet, GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void update() {
        this.act = this.Action;
        if (this.act > ((MainMonster) this).mAction.length - 1) {
            this.act = 0;
        }
        if (this.f > ((MainMonster) this).mAction[this.act][this.Direction > 2 ? 2 : this.Direction].length - 1) {
            this.f = 0;
        }
        this.fr = ((MainMonster) this).mAction[this.act][this.Direction > 2 ? 2 : this.Direction][this.f] / 3;
        this.xs = (this.fr > 1 ? 1 : this.fr) * this.wOne;
        this.fr = ((MainMonster) this).mAction[this.act][this.Direction > 2 ? 2 : this.Direction][this.f];
        this.ys = (this.fr % 3) * this.hOne;
        if (this.xs == -1) {
            this.xs = 0;
        }
        if (this.ys == -1) {
            this.ys = 0;
        }
        if (this.yfly <= 0) {
            this.ys = 0;
            this.xs = 0;
        }
        if (((this.vx < 0 && this.vy > 0) || (this.vx > 0 && this.vy > 0)) && this.state == 0 && this.yfly >= 30) {
            this.f = 3;
            if (this.vx < 0) {
                this.vx = -3;
            } else {
                this.vx = 3;
            }
            if (this.vy < 0) {
                this.vy = -3;
            } else {
                this.vy = 3;
            }
        }
        super.update();
    }
}
