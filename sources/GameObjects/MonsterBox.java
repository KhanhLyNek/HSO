package GameObjects;

import CLib.mGraphics;
import CLib.mSystem;
import Model.CRes;
import Model.MainImage;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/MonsterBox.class */
public class MonsterBox extends MainMonster {
    public MonsterBox(int i, int i2, int i3, String str, int i4, int i5, int i6, int i7) {
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
        this.Lv = (byte) i7;
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
        this.timeLoadInfo = mSystem.currentTimeMillis();
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
    }

    @Override // GameObjects.MainObject
    public boolean isItemBox() {
        return true;
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        MainImage imagePartMonster;
        if (!this.isDie && (imagePartMonster = ObjectData.getImagePartMonster((short) this.catalogyMonster)) != null && imagePartMonster.img != null) {
            mgraphics.drawImage(imagePartMonster.img, this.x, this.y, 33, false);
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
