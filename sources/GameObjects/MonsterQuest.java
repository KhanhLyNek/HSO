package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Thread_More.LoadMap;

/* loaded from: KnightOnline_v403.jar:GameObjects/MonsterQuest.class */
public class MonsterQuest extends MainMonster {
    static byte[] mFrameImg = {0, 1, 2, 3, 4, 5, 5, 5, 5, 4, 3, 2, 1, 0};
    static byte[][] mPosImg = {new byte[]{0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{0, 0, -1, -1, 0, 0, 1, 1}};
    int hRe;
    int fpos = 0;
    boolean isReveiceQuest = false;
    int test = 0;

    public MonsterQuest(int i, int i2, int i3, String str, int i4, int i5, int i6, int i7) {
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
        this.nFrame = 6;
        this.hOne = -1;
        this.wOne = -1;
        this.ysai = -2;
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (!this.isDie) {
            MainImage imagePartMonster = ObjectData.getImagePartMonster((short) this.catalogyMonster);
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
                int i = 0;
                int i2 = mFrameImg[this.f / 3] * this.hOne;
                if (this.catalogyMonster > 92) {
                    i = (mFrameImg[this.f / 3] / 3) * this.wOne;
                    i2 = (mFrameImg[this.f / 3] % 3) * this.hOne;
                }
                if (this.isReveiceQuest) {
                    if (this.timeTanHinh > this.test) {
                        mgraphics.drawRegion(imagePartMonster.img, i, i2, this.wOne, this.hRe, 0, this.x, this.y - (this.hOne / 2), 33, false);
                        mgraphics.drawRegion(imagePartMonster.img, i, (i2 + this.hOne) - this.hRe, this.wOne, this.hRe, 0, this.x, (this.y - (this.hOne / 2)) + this.hRe, 33, false);
                    }
                } else {
                    mgraphics.drawRegion(imagePartMonster.img, i, i2, this.wOne, this.hOne, 0, this.x + mPosImg[0][this.fpos / 3], this.y + mPosImg[1][this.fpos / 3], 33, false);
                }
            }
            if (this.Action != 4 && !this.isReveiceQuest) {
                paintName(mgraphics, 0);
            }
        }
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void update() {
        setDie();
        if (this.Action != 4) {
            if (this.isReveiceQuest) {
                this.timeTanHinh++;
                this.hRe += 2;
                if (this.hRe >= (this.hOne - 1) / 2) {
                    this.isReveiceQuest = false;
                    this.timeTanHinh = 0;
                    this.hRe = 10;
                }
            }
            this.f++;
            if (this.f / 3 > mFrameImg.length - 1) {
                this.f = 0;
            }
            this.fpos++;
            if (this.fpos / 3 > mPosImg[0].length - 1) {
                this.fpos = 0;
            }
        }
        super.update();
    }

    @Override // GameObjects.MainMonster
    public void setDie() {
        if (this.Action == 4) {
            if (CRes.random(3) == 1) {
                if (CRes.random(2) == 1) {
                    LoadMap.timeVibrateScreen = 103;
                }
                int random = CRes.random(1, 3);
                for (int i = 0; i < random; i++) {
                    int random_Am_0 = CRes.random_Am_0(20);
                    int random_Am_02 = CRes.random_Am_0(30);
                    GameScreen.addEffectEndKill(36, this.x + random_Am_0, (this.y + random_Am_02) - (this.hOne / 2));
                    if (CRes.random(3) == 1) {
                        GameScreen.addEffectEndKill(9, this.x + random_Am_0, (this.y + random_Am_02) - (this.hOne / 2));
                    }
                }
            }
            if (this.timeReveice >= 0 && (GameCanvas.timeNow - this.timedie) / 1000 > this.timeReveice - 1) {
                Reveive();
                this.isReveiceQuest = true;
                this.timeTanHinh = 0;
                this.hRe = 10;
                GameScreen.addEffectKill(81, this.x, this.y - 20, 200, (short) 0, (byte) 0);
                GameScreen.addEffectEndKill(39, this.x, this.y - (this.hOne / 2));
                return;
            }
            return;
        }
        if (this.hp <= 0) {
            this.hp = 0;
            this.Action = 4;
            resetXY();
            this.timedie = GameCanvas.timeNow;
        }
    }
}
