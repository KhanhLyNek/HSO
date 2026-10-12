package GameObjects;

import CLib.mGraphics;
import CLib.mVector;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Model.Point;

/* loaded from: KnightOnline_v403.jar:GameObjects/PetTool.class */
public class PetTool extends Pet {
    public byte mAction;
    public int huongY;
    public int flip;
    public boolean isBeginAttack;
    public static final byte M_STAND = 0;
    public static final byte M_DEAD = 1;
    public static final byte M_WALK = 2;
    public static final byte M_ATTACK = 3;
    public int p1;
    public int maxAttack;

    public PetTool(MainObject mainObject, short s, byte b, byte b2, byte b3) {
        super(s, b, b2, b3);
        this.maxAttack = 0;
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
        this.isPetTool = true;
    }

    public PetTool(short s, int i, byte b, byte b2, byte b3) {
        super(s, b, b2, b3);
        this.maxAttack = 0;
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
        this.mAction = (byte) 0;
        this.state = (byte) 0;
        this.timeAutoAction = CRes.random(200, 250);
        this.MonWater = 3;
        this.limitAttack = 10;
        this.timeMaxMoveAttack = 0;
        this.isPetTool = true;
    }

    public PetTool() {
        this.maxAttack = 0;
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        mVector mvector = new mVector();
        mVector mvector2 = (mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) ((Pet) this).imageId).toString());
        if (mvector2 != null) {
            mvector = mvector2;
        }
        if (mvector.size() == 0) {
            return;
        }
        try {
            DataEffect dataEffect = (DataEffect) mvector.elementAt(0);
            if (dataEffect != null) {
                paintDataEff_Top(mgraphics, this.x, this.y);
                byte b = this.mAction;
                if (this.state == 2 && this.isBeginAttack) {
                    b = 3;
                }
                int i = 0;
                int i2 = this.Action;
                if (isFly()) {
                    i = 35;
                    mgraphics.drawImage(MainObject.shadow, this.x, this.y - 6, 3, false);
                } else {
                    if (!this.isWater) {
                        mgraphics.drawImage(MainObject.shadow, this.x, this.y, 3, false);
                    }
                    if (this.isWater) {
                        mgraphics.drawRegion(MainObject.water, 0, ((i2 != 0 ? 2 : 0) * 17) + (((GameCanvas.gameTick / 2) % 2) * 17), 28, 17, 0, this.x + 1 + this.xtam, (this.y + this.dyWater) - 4, 3, false);
                    }
                }
                MainImage imagePet = ObjectData.getImagePet(((Pet) this).imageId);
                if (((mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) ((Pet) this).imageId).toString())) != null && imagePet != null && imagePet.img != null) {
                    dataEffect.paintPet(mgraphics, dataEffect.getFrame(this.frame, b, this.huongY), this.x, this.y - i, this.flip, imagePet.img);
                }
                paintDataEff_Bot(mgraphics, this.x, this.y);
            }
        } catch (Exception e) {
        }
    }

    public void doChangeFrmaeBoss() {
        mVector mvector = new mVector();
        mVector mvector2 = (mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) ((Pet) this).imageId).toString());
        if (mvector2 != null) {
            mvector = mvector2;
        }
        if (mvector.size() > 0) {
            DataEffect dataEffect = (DataEffect) mvector.elementAt(0);
            byte b = this.mAction;
            if (this.state == 2 && this.isBeginAttack) {
                b = 3;
            }
            this.frame = (byte) ((this.frame + 1) % dataEffect.getAnim(b, this.huongY).frame.length);
            if (this.maxAttack == 0) {
                this.maxAttack = dataEffect.getAnim(3, this.huongY).frame.length;
            }
        }
    }

    @Override // GameObjects.Pet, GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void update() {
        doChangeFrmaeBoss();
        try {
            super.update();
            setMove(this.MonWater, GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy));
            if (this.owner != null) {
                if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) <= 10) {
                    this.wayPoint.removeElementAt(0);
                }
                this.xAnchor = this.owner.x;
                this.yAnchor = this.owner.y;
                this.ownerX = this.owner.x;
                this.ownerY = this.owner.y;
            }
            this.time++;
            switch (this.state) {
                case 0:
                    roam();
                    break;
                case 1:
                    this.mAction = (byte) 2;
                    follow();
                    break;
                case 2:
                    attack();
                    break;
                case 3:
                    this.mAction = (byte) 2;
                    returnToPlayer();
                    break;
                case 4:
                    this.mAction = (byte) 0;
                    waitForCommand();
                    break;
            }
            if (this.state != this.preState) {
                this.preState = this.state;
            }
        } catch (Exception e) {
        }
    }

    @Override // GameObjects.Pet
    protected void returnToPlayer() {
        this.vMax = this.owner.vMax;
        this.toX = this.owner.x;
        this.toY = this.owner.y;
        setHuong(this.toX, this.toY);
        move_to_XY();
        if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) < 40 && this.owner.Action != 2) {
            setState((byte) 0);
        }
    }

    @Override // GameObjects.Pet
    protected void attack() {
        MainObject mainObject;
        MainObject mainObject2;
        this.vMax = 12;
        if (this.vecObjskill != null && this.vecObjskill.size() > 0) {
            Object_Effect_Skill object_Effect_Skill = (Object_Effect_Skill) this.vecObjskill.elementAt(0);
            MainObject mainObject3 = MainObject.get_Object(object_Effect_Skill.ID, object_Effect_Skill.tem);
            if (mainObject3 == null) {
                this.isRunAttack = false;
                setState((byte) 4);
                return;
            }
            if (MainObject.getDistance(this.x + this.vx, this.y + this.vy, mainObject3.x, mainObject3.y) > 30 && !this.isBeginAttack && mainObject3.hp > 0) {
                this.toX = mainObject3.x;
                this.toY = mainObject3.y;
                setHuong(this.toX, this.toY);
                move_to_XY();
                this.mAction = (byte) 2;
            } else {
                this.isBeginAttack = true;
            }
            this.p1++;
            if (this.p1 > this.maxAttack && this.isBeginAttack) {
                try {
                    setHuong(mainObject3.x, mainObject3.y);
                    switch (this.attackType) {
                        case 11:
                            for (int i = 0; i < this.vecObjskill.size(); i++) {
                                Object_Effect_Skill object_Effect_Skill2 = (Object_Effect_Skill) this.vecObjskill.elementAt(i);
                                if (object_Effect_Skill2 != null && (mainObject2 = MainObject.get_Object(object_Effect_Skill2.ID, object_Effect_Skill2.tem)) != null) {
                                    GameScreen.startNewMagicBeam(15, this, mainObject2, this.x, this.y, (-1) * object_Effect_Skill2.hpShow, (short) 163, 163, 24);
                                }
                            }
                            break;
                        case 12:
                            for (int i2 = 0; i2 < this.vecObjskill.size(); i2++) {
                                Object_Effect_Skill object_Effect_Skill3 = (Object_Effect_Skill) this.vecObjskill.elementAt(i2);
                                if (object_Effect_Skill3 != null && (mainObject = MainObject.get_Object(object_Effect_Skill3.ID, object_Effect_Skill3.tem)) != null) {
                                    GameScreen.addEffectEndFromSv(57, 21, mainObject.x, mainObject.y);
                                    GameScreen.addEffectNum(new StringBuffer().append("-").append(object_Effect_Skill3.hpShow).toString(), mainObject.x, mainObject.y - mainObject.hOne, 2, mainObject.ID);
                                }
                            }
                            break;
                    }
                } catch (Exception e) {
                }
                this.p1 = 0;
                this.isBeginAttack = false;
                setState((byte) 4);
            }
        }
    }

    @Override // GameObjects.Pet
    protected void roam() {
        this.vMax = 1;
        if (this.Action == 1) {
            if (this.time > this.timeAutoAction || CRes.random(16) == 0 || MainObject.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove) {
                this.time = 0;
                this.Action = 0;
                this.mAction = (byte) 0;
                this.vx = 0;
                this.vy = 0;
            }
        } else if (this.Action == 0) {
            this.vx = 0;
            this.vy = 0;
            if (this.time > this.timeAutoAction / 2 || CRes.random(12) == 0) {
                this.time = 0;
                this.Action = 1;
                this.mAction = (byte) 2;
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
        }
    }

    @Override // GameObjects.MainObject
    public void setSpeedInDirection(int i) {
        int random_Am_0 = CRes.random_Am_0(3);
        if (CRes.abs(random_Am_0) > 1) {
            i--;
        }
        switch (this.Direction) {
            case 0:
                this.vy = i;
                this.vx = random_Am_0;
                break;
            case 1:
                this.vy = -i;
                this.vx = random_Am_0;
                break;
            case 2:
                this.vy = random_Am_0;
                this.vx = -i;
                break;
            case 3:
                this.vy = random_Am_0;
                this.vx = i;
                break;
        }
        if (this.vx == 0 && CRes.random(3) == 0) {
            this.time = 0;
            this.Action = 0;
            this.vx = 0;
            this.vy = 0;
            this.mAction = (byte) 0;
        }
        if (this.vx > 0) {
            this.Direction = 3;
        } else {
            this.Direction = 2;
        }
        this.huongY = (byte) (this.y > this.toY ? 1 : 0);
        this.flip = (byte) (this.x - this.toX > 0 ? 0 : 2);
        this.flip = 0;
        if (this.Direction == 3) {
            this.flip = 2;
            return;
        }
        if (this.Direction != 2) {
            if (this.Direction == 0) {
                this.huongY = 0;
            } else if (this.Direction == 1) {
                this.huongY = 1;
            }
        }
    }

    @Override // GameObjects.Pet
    protected void follow() {
        this.vMax = this.owner.vMax;
        this.Action = 1;
        if (MainObject.getDistance(this.oldPosX, this.oldPosY, this.ownerX, this.ownerY) > 40) {
            Point point = new Point(this.ownerX, this.ownerY);
            this.oldPosX = this.ownerX;
            this.oldPosY = this.ownerY;
            this.wayPoint.addElement(point);
        } else if (MainObject.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) < 40) {
            this.wayPoint.removeAllElements();
            setState((byte) 4);
        }
        if (this.wayPoint.elementAt(0) != null) {
            this.toX = ((Point) this.wayPoint.elementAt(0)).x;
            this.toY = ((Point) this.wayPoint.elementAt(0)).y;
            setHuong(this.toX, this.toY);
            move_to_XY();
        }
    }

    public void setHuong(int i, int i2) {
        this.huongY = (byte) (this.y > i2 ? 1 : 0);
        this.flip = (byte) (this.x - i > 0 ? 0 : 2);
        this.flip = 0;
        if (this.Direction == 3) {
            this.flip = 2;
            return;
        }
        if (this.Direction != 2) {
            if (this.Direction == 0) {
                this.huongY = 0;
            } else if (this.Direction == 1) {
                this.huongY = 1;
            }
        }
    }

    public boolean isFly() {
        mVector mvector = new mVector();
        mVector mvector2 = (mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) ((Pet) this).imageId).toString());
        if (mvector2 != null) {
            mvector = mvector2;
        }
        DataEffect dataEffect = (DataEffect) mvector.elementAt(0);
        if (dataEffect != null && dataEffect.isFly <= -5) {
            return true;
        }
        return false;
    }
}
