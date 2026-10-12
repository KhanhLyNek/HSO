package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import GameEffect.EffectAuto;
import GameEffect.EffectSkill;
import GameScreen.GameScreen;
import Model.CRes;
import Model.FrameImage;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/Monsterplus.class */
public class Monsterplus extends MainMonster {
    public byte ideffect;
    public EffectAuto eff;
    public int r;
    public FrameImage fraImgEff;
    public byte fireFrame = 0;
    public int[] arr_radian = {0, 30, 60, 90, EffectSkill.EFF_BIG_SWORD_115_2, 150, 180, 210, 240, 270, 300, 330};
    public long timeLive = 0;

    public Monsterplus(int i, int i2, int i3, String str, int i4, int i5, int i6, int i7) {
        this.ideffect = (byte) -1;
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
        this.fraImgEff = new FrameImage(1);
        this.r = EffectSkill.EFF_BIG_SWORD_115_2;
        this.limitMove = 60;
        this.timeAutoAction = CRes.random(50, 70);
        this.limitAttack = 50;
        this.xsai = 0;
        this.ysai = -2;
        this.timeLoadInfo = mSystem.currentTimeMillis();
        if (this.catalogyMonster == 110) {
            this.ideffect = (byte) 52;
            this.r = EffectSkill.EFF_BIG_SWORD_115_2;
            this.eff = new EffectAuto(this.ideffect, i4, i5, 0, 0, 1, 0);
        }
    }

    @Override // GameObjects.MainObject
    public void setEffectauto(int i, int i2, short s) {
        this.ideffect = (byte) i;
        this.eff = new EffectAuto(this.ideffect, this.x, this.y, 0, 0, 1, 0);
        this.r = i2;
        this.Lv = s;
    }

    @Override // GameObjects.MainMonster
    public void setTimelive(long j) {
        this.timeLive = j;
    }

    @Override // GameObjects.MainObject
    public boolean isLuaThieng() {
        return true;
    }

    @Override // GameObjects.MainMonster
    public void setLvmonster(int i) {
        this.Lv = (byte) i;
        if (this.catalogyMonster == 110) {
            if (this.Lv == 1) {
                this.ideffect = (byte) 52;
                this.r = EffectSkill.EFF_BIG_SWORD_115_2;
            }
            if (this.Lv == 2) {
                this.ideffect = (byte) 53;
                this.r = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
            } else if (this.Lv == 3) {
                this.ideffect = (byte) 54;
                this.r = 140;
            } else if (this.Lv == 4) {
                this.ideffect = (byte) 55;
                this.r = 150;
            }
            this.eff = new EffectAuto(this.ideffect, this.x, this.y, 0, 0, 1, 0);
        }
    }

    public void setIdeffect(byte b) {
        this.ideffect = b;
    }

    @Override // GameObjects.MainMonster
    public void updateAction() {
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
        updateAction();
        this.fireFrame = (byte) (this.fireFrame + 1);
        if (this.fireFrame > 10) {
            this.fireFrame = (byte) 0;
        }
        if (this.eff != null) {
            this.eff.update();
        }
        boolean z = false;
        if (this.myClan != null) {
            if (GameScreen.player.myClan != null && this.myClan.IdClan == GameScreen.player.myClan.IdClan) {
                z = true;
            }
        } else if (this.nameowner.equals(GameScreen.player.name)) {
            z = true;
        }
        if (z) {
            this.ysai = 10;
            for (int i = 0; i < this.arr_radian.length; i++) {
                int[] iArr = this.arr_radian;
                int i2 = i;
                iArr[i2] = iArr[i2] + 1;
                if (this.arr_radian[i] >= 360) {
                    this.arr_radian[i] = 0;
                }
            }
        }
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (this.eff != null) {
            this.eff.paint(mgraphics);
        }
        boolean z = false;
        if (this.myClan != null) {
            if (GameScreen.player.myClan != null && this.myClan.IdClan == GameScreen.player.myClan.IdClan) {
                z = true;
            }
        } else if (this.nameowner.equals(GameScreen.player.name)) {
            z = true;
        }
        if (z) {
            paintIconClan(mgraphics, this.x - 1, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - 20, 2);
            String convertSecondsToHMmSs = LoadMap.convertSecondsToHMmSs(this.timeLive - mSystem.currentTimeMillis());
            if (!convertSecondsToHMmSs.equals("")) {
                mFont.tahoma_7_yellow.drawString(mgraphics, convertSecondsToHMmSs, this.x, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - 40, 3, false);
            }
            mFont.tahoma_7_yellow.drawString(mgraphics, new StringBuffer().append(T.level).append((int) this.Lv).toString(), this.x, ((((this.y - this.ysai) - this.dy) + this.dyWater) - this.hOne) - 55, 3, false);
            for (int i = 0; i < this.arr_radian.length; i++) {
                if (this.fraImgEff != null) {
                    this.fraImgEff.drawFrameEffectSkill((this.fireFrame / 2) % this.fraImgEff.nFrame, ((CRes.cos(this.arr_radian[i]) * this.r) / 1024) + this.x + 2, ((CRes.sin(this.arr_radian[i]) * this.r) / 1024) + this.y, 0, 3, mgraphics);
                }
            }
        }
    }

    @Override // GameObjects.MainObject
    public void move_to_XY() {
    }

    @Override // GameObjects.MainObject
    public void move_to_XY_Normal() {
    }

    @Override // GameObjects.MainObject
    public void moveX() {
    }

    @Override // GameObjects.MainObject
    public void moveY() {
    }
}
