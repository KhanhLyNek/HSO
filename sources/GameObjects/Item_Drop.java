package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;

/* loaded from: KnightOnline_v403.jar:GameObjects/Item_Drop.class */
public class Item_Drop extends MainObject {
    long timeAp;
    int timeRemove;
    int timefly;

    public Item_Drop(int i, byte b, String str, int i2, int i3, short s, byte b2) {
        this.ID = i;
        this.typeObject = b;
        this.name = str;
        i2 = i2 < 48 ? 48 : i2;
        i2 = i2 > GameCanvas.loadmap.maxWMap - 48 ? GameCanvas.loadmap.maxWMap - 48 : i2;
        i3 = i3 < 48 ? 48 : i3;
        i3 = i3 > GameCanvas.loadmap.maxHMap - 48 ? GameCanvas.loadmap.maxHMap - 48 : i3;
        this.x = i2;
        this.y = i3;
        this.imageId = s;
        this.colorName = b2;
        this.vx = CRes.random_Am(1, 5);
        this.vy = -CRes.random(3, 10);
        this.vMax = 16;
        this.time = CRes.random(3, 9);
        this.timeAp = GameCanvas.timeNow;
        this.timeRemove = 60;
        this.isSend = false;
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        MainImage mainImage = null;
        switch (this.typeObject) {
            case 3:
                mainImage = ObjectData.getImageItem((short) this.imageId);
                break;
            case 4:
                mainImage = ObjectData.getImagePotion((short) this.imageId);
                break;
            case 5:
                mainImage = ObjectData.getImageQuestItem((short) this.imageId);
                break;
            case 7:
                mainImage = ObjectData.getImageMaterial((short) this.imageId);
                break;
        }
        if (mainImage.img != null) {
            if (this.hOne == 0) {
                this.hOne = mImage.getImageHeight(mainImage.img.image);
            }
            mgraphics.drawImage(mainImage.img, this.x, this.y, 33, false);
            if (this.typeObject == 3 && this.colorName > 1) {
                Item.fraeffitemdrop.drawFrame(((this.colorName - 1) * 3) + ((GameCanvas.gameTick / 3) % 3), this.x + 6, this.y - 14, 0, 3, mgraphics);
            }
        }
        if (this.isWater) {
            mgraphics.drawRegion(MainObject.water, 0, ((GameCanvas.gameTick / 2) % 2) * 17, 28, 17, 0, this.x, (this.y - 2) + this.dyWater, 3, false);
        }
        if (PaintInfoGameScreen.isLevelPoint) {
            byte b = 0;
            if (this.typeObject == 3) {
                b = this.colorName;
            }
            paintName(mgraphics, b);
        }
    }

    @Override // GameObjects.MainObject
    public void paintAvatarFocus(mGraphics mgraphics, int i, int i2) {
        MainImage mainImage = null;
        switch (this.typeObject) {
            case 3:
                mainImage = ObjectData.getImageItem((short) this.imageId);
                break;
            case 4:
                mainImage = ObjectData.getImagePotion((short) this.imageId);
                break;
            case 5:
                mainImage = ObjectData.getImageQuestItem((short) this.imageId);
                break;
            case 7:
                mainImage = ObjectData.getImageMaterial((short) this.imageId);
                break;
        }
        if (mainImage.img != null) {
            if (this.hOne == 0) {
                this.hOne = mImage.getImageHeight(mainImage.img.image);
            }
            mgraphics.drawImage(mainImage.img, i - 1, i2, 3, false);
        }
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void update() {
        if (this.time > 0) {
            this.x += this.vx;
            this.y += this.vy;
            this.vy += 2;
            this.time--;
        }
        if (this.time == 0) {
            if (GameCanvas.loadmap.getTile(this.x, this.y) == 2) {
                this.isWater = true;
            }
            this.isRunAttack = false;
            this.time = -1;
        }
        if (this.isRunAttack) {
            this.timefly++;
            this.x += this.vx;
            this.y += this.vy;
            if (this.timefly >= this.timeHuyKill) {
                this.isRemove = true;
                this.isRunAttack = false;
            }
        }
        if (this.isSend) {
            this.timeGet++;
            if (this.timeGet > 40) {
                this.isSend = false;
                this.timeGet = 0;
            }
        }
        if ((GameCanvas.timeNow - this.timeAp) / 1000 >= this.timeRemove) {
            this.isRemove = true;
        }
    }
}
