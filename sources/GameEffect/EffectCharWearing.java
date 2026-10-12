package GameEffect;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameObjects.ObjectData;
import Main.GameCanvas;
import Main.ImageEffect;
import Model.CRes;

/* loaded from: KnightOnline_v403.jar:GameEffect/EffectCharWearing.class */
public class EffectCharWearing {
    private byte f;
    private byte f1;
    private byte FrameEffect;
    private byte FrameEffect1;
    public int idImage;
    public byte type;
    private int frameWidth;
    private int frameHeight;
    public static final byte TYPE_HAT_WEARING = 0;
    public static final byte TYPE_ARMOR_WEARING = 1;
    public static final byte TYPE_TROUSERS_WEARING = 2;
    private long timepaint = mSystem.currentTimeMillis();
    private int timerepaint;
    private int dx;
    private int dy;
    public static byte[][] Frame = {new byte[]{0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4}, new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3}, new byte[]{0, 0, 1, 1, 2, 2, 1, 3, 3, 3, 0, 0, 1, 1, 2, 2, 1, 3, 3, 3}};
    int pospaint;

    public EffectCharWearing(byte b, int i) {
        this.idImage = i;
        this.type = b;
        switch (this.type) {
            case 0:
                this.dx = 5;
                this.dy = -25;
                this.timerepaint = 1;
                return;
            case 1:
                this.dx = 0;
                this.dy = -12;
                this.timerepaint = 2;
                return;
            case 2:
                this.dx = 5;
                this.dy = 0;
                this.timerepaint = 3;
                return;
            default:
                return;
        }
    }

    public void paint(mGraphics mgraphics, int i, int i2) {
        mImage image = ImageEffect.setImage(this.idImage);
        if (image != null && image.image != null) {
            if (this.frameWidth == 0 || this.frameHeight == 0) {
                this.frameWidth = EffectSkill.arrInfoEff[this.idImage][0];
                this.frameHeight = EffectSkill.arrInfoEff[this.idImage][1];
            }
            if (this.timepaint - mSystem.currentTimeMillis() < 0) {
                switch (this.type) {
                    case 0:
                        if (this.pospaint == 0) {
                            mgraphics.drawRegion(image, 0, this.FrameEffect * this.frameHeight, this.frameWidth, this.frameHeight, 0, i + this.dx, i2 + this.dy, 3, false);
                            return;
                        } else {
                            mgraphics.drawRegion(image, 0, this.FrameEffect1 * this.frameHeight, this.frameWidth, this.frameHeight, 0, i - this.dx, i2 + this.dy, 3, false);
                            return;
                        }
                    case 1:
                        mgraphics.drawRegion(image, 0, this.FrameEffect * this.frameHeight, this.frameWidth, this.frameHeight, 0, i + this.dx, i2 + this.dy, 3, false);
                        return;
                    case 2:
                        mgraphics.drawRegion(image, 0, this.FrameEffect * this.frameHeight, this.frameWidth, this.frameHeight, 0, i + this.dx, i2 + this.dy, 3, false);
                        mgraphics.drawRegion(image, 0, this.FrameEffect1 * this.frameHeight, this.frameWidth, this.frameHeight, 0, i - this.dx, i2 + this.dy, 3, false);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void update() {
        if (this.timepaint - mSystem.currentTimeMillis() < 0) {
            switch (this.type) {
                case 0:
                    if (GameCanvas.gameTick % 2 == 0) {
                        this.f = (byte) (this.f + 1);
                    }
                    if (this.f > Frame[this.type].length - 1) {
                        this.f = (byte) 0;
                        this.timerepaint = CRes.random(10);
                        this.timepaint = (this.timerepaint * ObjectData.MONSTER) + mSystem.currentTimeMillis();
                        if (this.timerepaint % 2 == 0) {
                            this.pospaint = 0;
                        } else {
                            this.pospaint = 1;
                        }
                    }
                    this.FrameEffect = Frame[this.type][this.f];
                    if (GameCanvas.gameTick % 4 == 0) {
                        this.f1 = (byte) (this.f1 + 1);
                    }
                    if (this.f1 > Frame[this.type].length - 1) {
                        this.f1 = (byte) 0;
                    }
                    this.FrameEffect1 = Frame[this.type][this.f1];
                    return;
                case 1:
                    this.f = (byte) (this.f + 1);
                    if (this.f > Frame[this.type].length - 1) {
                        this.f = (byte) 0;
                        this.timerepaint = CRes.random(10);
                        this.timepaint = (this.timerepaint * ObjectData.MONSTER) + mSystem.currentTimeMillis();
                    }
                    this.FrameEffect = Frame[this.type][this.f];
                    return;
                case 2:
                    if (GameCanvas.gameTick % 2 == 0) {
                        this.f = (byte) (this.f + 1);
                    }
                    if (this.f > Frame[this.type].length - 1) {
                        this.f = (byte) 0;
                        this.timerepaint = CRes.random(10);
                        this.timepaint = (this.timerepaint * ObjectData.MONSTER) + mSystem.currentTimeMillis();
                    }
                    this.FrameEffect = Frame[this.type][this.f];
                    if (GameCanvas.gameTick % 3 == 0) {
                        this.f1 = (byte) (this.f1 + 1);
                    }
                    if (this.f1 > Frame[this.type].length - 1) {
                        this.f1 = (byte) 0;
                    }
                    this.FrameEffect1 = Frame[this.type][this.f1];
                    return;
                default:
                    return;
            }
        }
    }
}
