package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import Main.GameCanvas;

/* loaded from: KnightOnline_v403.jar:GameObjects/ThacNuoc.class */
public class ThacNuoc {
    public byte idImgThac;
    int x;
    int y;
    public static mImage[] allImgThac = null;
    int indexTile = 0;

    public ThacNuoc() {
    }

    public ThacNuoc(int i, int i2, int i3) {
        this.x = i2;
        this.y = i3;
        this.idImgThac = (byte) i;
        if (allImgThac == null) {
            allImgThac = new mImage[9];
            for (int i4 = 0; i4 < allImgThac.length; i4++) {
                allImgThac[i4] = mImage.createImage(new StringBuffer().append("/tilethac").append(i4).append(".png").toString());
            }
        }
    }

    public void paint(mGraphics mgraphics) {
        if (allImgThac != null && this.idImgThac < allImgThac.length && allImgThac[this.idImgThac] != null) {
            mgraphics.drawRegion(allImgThac[this.idImgThac], 0, this.indexTile * 24, 24, 24, this.idImgThac > 4 ? 0 : 0, this.x, this.y, 0, false);
        }
    }

    public void update() {
        if (GameCanvas.gameTick % 4 == 0) {
            this.indexTile = (this.indexTile + 1) % 4;
        }
    }

    public boolean isThacNuoc() {
        return true;
    }
}
