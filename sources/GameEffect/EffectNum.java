package GameEffect;

import CLib.mFont;
import CLib.mGraphics;
import InterfaceComponents.MainTabNew;
import Model.AvMain;
import Model.CRes;

/* loaded from: KnightOnline_v403.jar:GameEffect/EffectNum.class */
public class EffectNum extends MainEffect {
    public static final byte COLOR_NORMAL = 0;
    public static final byte COLOR_XP = 1;
    public static final byte COLOR_FIRE = 2;
    public static final byte COLOR_PLUS_HP = 3;
    public static final byte COLOR_PLUS_MP = 4;
    public static final byte COLOR_PUT_ITEM = 5;
    public static final byte COLOR_FIRE_PERSON = 6;
    public static final byte COLOR_EFF_CRI = 7;
    public static final byte COLOR_OPTION = 8;
    public static final byte COLOR_EFF_MON_FIRE = 9;
    public static final byte COLOR_DAME_LIGHT = 10;
    public static final byte COLOR_DAME_DRAK = 11;
    String strContent;
    int typeNum;
    int fRemove;
    mFont fontPaint;
    boolean isGravity;

    public EffectNum(String str, int i, int i2, int i3) {
        this.isGravity = false;
        this.isGravity = false;
        this.strContent = str;
        this.x = i;
        this.y = i2 + CRes.random_Am_0(5);
        this.typeNum = i3;
        this.fontPaint = mFont.tahoma_7b_white;
        if (this.typeNum < 0) {
            this.fontPaint = MainTabNew.setTextColorName(-i3);
        } else {
            switch (i3) {
                case 1:
                    this.fontPaint = mFont.tahoma_7_green;
                    break;
                case 2:
                case 6:
                case 7:
                case 9:
                    this.isGravity = true;
                    break;
                case 5:
                    this.fontPaint = mFont.tahoma_7_white;
                    break;
                case 8:
                    this.fontPaint = MainTabNew.setTextColor(i3);
                    break;
                case 10:
                    this.fontPaint = mFont.tahoma_7b_yellow;
                    break;
                case 11:
                    this.fontPaint = mFont.tahoma_7b_violet;
                    break;
            }
        }
        if (this.isGravity) {
            this.vy = -CRes.random(11, 14);
            this.fRemove = CRes.random(20, 25);
        } else {
            this.vy = -CRes.random(2, 4);
            this.fRemove = CRes.random(24, 32);
        }
    }

    public EffectNum(String str, int i, int i2, int i3, int i4) {
        this.isGravity = false;
        this.strContent = str;
        this.x = i;
        this.y = i2 + CRes.random_Am_0(5);
        this.typeNum = i3;
        this.fontPaint = mFont.tahoma_7b_white;
        this.vy = -CRes.random(11, 14);
        this.isGravity = true;
        this.fRemove = CRes.random(20, 25);
        if (this.typeNum < 0) {
            this.fontPaint = MainTabNew.setTextColorName(-i4);
            return;
        }
        switch (i3) {
            case 8:
                this.fontPaint = MainTabNew.setTextColor(i4);
                return;
            default:
                return;
        }
    }

    @Override // GameEffect.MainEffect
    public void paint(mGraphics mgraphics) {
        switch (this.typeNum) {
            case 1:
            case 5:
                this.fontPaint.drawString(mgraphics, this.strContent, this.x, this.y, 2, false);
                return;
            case 2:
                AvMain.Font3dWhite(mgraphics, this.strContent, this.x, this.y, 2);
                return;
            case 3:
                AvMain.Font3dColor(mgraphics, this.strContent, this.x, this.y, 2, (byte) 5);
                return;
            case 4:
                AvMain.Font3dColor(mgraphics, this.strContent, this.x, this.y, 2, (byte) 1);
                return;
            case 6:
                AvMain.Font3dColor(mgraphics, this.strContent, this.x, this.y, 2, (byte) 4);
                return;
            case 7:
                AvMain.Font3dColor(mgraphics, this.strContent, this.x, this.y, 2, (byte) 2);
                return;
            case 8:
            default:
                this.fontPaint.drawString(mgraphics, this.strContent, this.x, this.y, 2, false);
                return;
            case 9:
                AvMain.Font3dColor(mgraphics, this.strContent, this.x, this.y, 2, (byte) 6);
                return;
        }
    }

    @Override // GameEffect.MainEffect
    public void update() {
        this.f++;
        if (this.isGravity) {
            this.vy++;
        }
        super.update();
        if (this.f >= this.fRemove) {
            this.isStop = true;
        }
    }
}
