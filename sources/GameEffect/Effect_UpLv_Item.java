package GameEffect;

import CLib.mGraphics;
import Main.GameCanvas;

/* loaded from: KnightOnline_v403.jar:GameEffect/Effect_UpLv_Item.class */
public class Effect_UpLv_Item {
    int wnew = 1;
    int[][] colorBorder = {new int[]{-1, -1, -1052689, -1052689, -4079167, -4079167, -6250336}, new int[]{-14097665, -14097665, -14104585, -14104585, -14114080, -14114080, -14249063}, new int[]{-1280, -1280, -1251840, -1251840, -3949821, -3949821, -5594621}, new int[]{-423937, -423937, -1473281, -1473281, -3768833, -3768833, -5934642}, new int[]{-20158, -20158, -26313, -26313, -34787, -34787, -3179218}};
    int[] size = {1, 1, 1, 1, 1};

    public void paintUpgradeEffect(int i, int i2, int i3, int i4, mGraphics mgraphics, int i5) {
        if (i3 <= 0) {
            return;
        }
        int i6 = (i3 - 1) % 5;
        mgraphics.setColor(this.colorBorder[i6][6]);
        mgraphics.drawRect((i - (i4 / 2)) - i5, (i2 - (i4 / 2)) - i5, i4, i4, true);
        for (int i7 = 0; i7 < this.size.length; i7++) {
            int upgradeEffectX = (i - (i4 / 2)) + upgradeEffectX((GameCanvas.gameTick * 1) - (i7 * this.wnew), i4);
            int upgradeEffectY = (i2 - (i4 / 2)) + upgradeEffectY((GameCanvas.gameTick * 1) - (i7 * this.wnew), i4);
            mgraphics.setColor(this.colorBorder[i6][i7]);
            mgraphics.fillRect((upgradeEffectX - (this.size[i7] / 2)) - i5, (upgradeEffectY - (this.size[i7] / 2)) - i5, this.size[i7], this.size[i7], true);
        }
        if (i3 >= 6 && i3 < 11) {
            for (int i8 = 0; i8 < this.size.length; i8++) {
                int upgradeEffectX2 = (i - (i4 / 2)) + upgradeEffectX(((GameCanvas.gameTick - (i4 * 2)) * 1) - (i8 * this.wnew), i4);
                int upgradeEffectY2 = (i2 - (i4 / 2)) + upgradeEffectY(((GameCanvas.gameTick - (i4 * 2)) * 1) - (i8 * this.wnew), i4);
                mgraphics.setColor(this.colorBorder[i6][i8]);
                mgraphics.fillRect((upgradeEffectX2 - (this.size[i8] / 2)) - i5, (upgradeEffectY2 - (this.size[i8] / 2)) - i5, this.size[i8], this.size[i8], true);
            }
        }
        if (i3 >= 11) {
            for (int i9 = 0; i9 < this.size.length; i9++) {
                int upgradeEffectX3 = (i - (i4 / 2)) + upgradeEffectX(((GameCanvas.gameTick - ((i4 * 13) / 10)) * 1) - (i9 * this.wnew), i4);
                int upgradeEffectY3 = (i2 - (i4 / 2)) + upgradeEffectY(((GameCanvas.gameTick - ((i4 * 13) / 10)) * 1) - (i9 * this.wnew), i4);
                mgraphics.setColor(this.colorBorder[i6][i9]);
                mgraphics.fillRect((upgradeEffectX3 - (this.size[i9] / 2)) - i5, (upgradeEffectY3 - (this.size[i9] / 2)) - i5, this.size[i9], this.size[i9], true);
            }
            for (int i10 = 0; i10 < this.size.length; i10++) {
                int upgradeEffectX4 = (i - (i4 / 2)) + upgradeEffectX(((GameCanvas.gameTick - ((i4 * 13) / 5)) * 1) - (i10 * this.wnew), i4);
                int upgradeEffectY4 = (i2 - (i4 / 2)) + upgradeEffectY(((GameCanvas.gameTick - ((i4 * 13) / 5)) * 1) - (i10 * this.wnew), i4);
                mgraphics.setColor(this.colorBorder[i6][i10]);
                mgraphics.fillRect((upgradeEffectX4 - (this.size[i10] / 2)) - i5, (upgradeEffectY4 - (this.size[i10] / 2)) - i5, this.size[i10], this.size[i10], true);
            }
        }
    }

    public int upgradeEffectY(int i, int i2) {
        int i3 = i % (4 * i2);
        if (0 <= i3 && i3 < i2) {
            return 0;
        }
        if (i2 <= i3 && i3 < i2 * 2) {
            return i3 % i2;
        }
        if (i2 * 2 <= i3 && i3 < i2 * 3) {
            return i2;
        }
        return i2 - (i3 % i2);
    }

    public int upgradeEffectX(int i, int i2) {
        int i3 = i % (4 * i2);
        if (0 <= i3 && i3 < i2) {
            return i3 % i2;
        }
        if (i2 <= i3 && i3 < i2 * 2) {
            return i2;
        }
        if (i2 * 2 <= i3 && i3 < i2 * 3) {
            return i2 - (i3 % i2);
        }
        return 0;
    }
}
