package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameEffect.EffectSkill;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.MainImage;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/MonsterFly.class */
public class MonsterFly extends MainMonster {
    mImage img;
    int ydieFly = 0;
    static byte[][][] mMonFly_012 = {new byte[]{new byte[]{0, 0, 1, 1, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2}, new byte[]{2, 2, 0, 0, 1, 1}}, new byte[]{new byte[]{0, 0, 1, 1, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2}, new byte[]{2, 2, 0, 0, 1, 1}}, new byte[]{new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonFly_0102 = {new byte[]{new byte[]{0, 0, 1, 1, 0, 0, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2, 0, 0}, new byte[]{2, 2, 0, 0, 1, 1, 0, 0}}, new byte[]{new byte[]{0, 0, 1, 1, 0, 0, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2, 0, 0}, new byte[]{2, 2, 0, 0, 1, 1, 0, 0}}, new byte[]{new byte[]{2, 2, 2, 2, 3, 3, 3, 3}, new byte[]{2, 2, 2, 2, 3, 3, 3, 3}, new byte[]{2, 2, 2, 2, 3, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4, 4, 4}}};
    static byte[][][] mMonFly_012_slow = {new byte[]{new byte[]{0, 0, 1, 1, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2}, new byte[]{2, 2, 0, 0, 1, 1}}, new byte[]{new byte[]{0, 0, 1, 1, 2, 2}, new byte[]{1, 1, 0, 0, 2, 2}, new byte[]{2, 2, 0, 0, 1, 1}}, new byte[]{new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}, new byte[]{2, 2, 2, 3, 3, 3}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}, new byte[]{new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}, new byte[]{4, 4, 4, 4, 4, 4}}};

    public MonsterFly(int i, int i2, int i3, String str, int i4, int i5, int i6, int i7) {
        this.coutEff = 0;
        this.typeMonster = i3;
        this.typeObject = (byte) 1;
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
        this.Direction = 0;
        this.MonWater = 0;
        this.hOne = -1;
        this.wOne = -1;
        this.nFrame = 5;
        this.vMax = 3;
        this.limitMove = 70;
        this.xsai = 0;
        this.ysai = -2;
        this.timeAutoAction = CRes.random(60, 85);
        this.limitAttack = 50;
        switch (i3) {
            case 3:
                ((MainMonster) this).mAction = mMonFly_012;
                break;
            case 5:
            case 19:
                ((MainMonster) this).mAction = mMonFly_0102;
                break;
            case 8:
                ((MainMonster) this).mAction = mMonFly_012;
                this.limitAttack = 80;
                break;
            case 10:
                ((MainMonster) this).mAction = mMonFly_012_slow;
                break;
        }
        this.timeLoadInfo = mSystem.currentTimeMillis();
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (!this.isDie) {
            if (this.typeBoss == 2 && MainObject.imgCapchar != null) {
                AvMain.Font3dWhite(mgraphics, MainObject.strCapchar, this.x, ((((this.y - this.dy) - this.ydieFly) - this.hOne) - 20) - 30, 2);
                mgraphics.drawImage(MainObject.imgCapchar, this.x, (((this.y - this.dy) - this.vyStyleDie) - this.hOne) - 20, 33, false);
            }
            paintDataEff_Top(mgraphics, this.x, this.y);
            paintEffauto_Low(mgraphics, this.x, this.y);
            paintBuffFirst(mgraphics);
            MainImage imagePartMonster = ObjectData.getImagePartMonster((short) this.catalogyMonster);
            int i = this.Action;
            if (i > ((MainMonster) this).mAction.length - 1) {
                i = 0;
            }
            if (this.f > ((MainMonster) this).mAction[i][this.Direction > 2 ? 2 : this.Direction].length - 1) {
                this.f = 0;
            }
            mgraphics.drawImage(MainObject.shadow, this.x, (this.y - this.dy) - this.ydieFly, 3, false);
            if (imagePartMonster.img != null) {
                if (this.wOne < 0) {
                    if (this.catalogyMonster <= 92) {
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
                mgraphics.drawRegion(imagePartMonster.img, i2, i3, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, ((this.y - 8) - this.dy) - this.vyStyleDie, 33, false);
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
        updateDataEffect();
        setDie();
        if (this.Action == 4) {
            this.ydieFly += 3;
            if (this.ydieFly > 11) {
                this.ydieFly = 11;
            }
        } else {
            this.ydieFly = 0;
        }
        if (!Canmove() || this.isBinded) {
            return;
        }
        updateAction();
        if (this.Action != 4 && this.Action != 3 && this.Action != 2) {
            if (this.isRunAttack && !this.isMonPhoBangDie) {
                if (this.timeFreeFire > 0) {
                    if (!this.isServerControl) {
                        autoMoveFire();
                    }
                } else if (!canNotMove()) {
                    Move_to_Focus();
                }
            } else if (!this.isServerControl) {
                auto_Move();
            }
        }
        int tile = GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy);
        if (!this.isServerControl) {
            setMove(this.MonWater, tile);
        }
        if (this.typeBoss == 2) {
            updateImgCapchar();
        }
    }

    public void updateImgCapchar() {
        if (MainObject.imgCapchar == null) {
            if (this.timeCapchar > 0) {
                this.timeCapchar--;
            }
            if (this.timeCapchar <= 0) {
                mSystem.outz("lay hinh capchar");
                GlobalService.gI().load_image((short) 9999);
                this.timeCapchar = EffectSkill.EFF_BIG_SWORD_115_2;
            }
        }
    }
}
