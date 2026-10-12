package Map;

import CLib.mGraphics;
import GameObjects.ObjectData;
import Main.GameCanvas;
import Model.FrameImage;
import Model.MainImage;

/* loaded from: KnightOnline_v403.jar:Map/ItemMap.class */
public class ItemMap extends MainItemMap {
    public static FrameImage[] img_HouseArena_Die;
    public static boolean isPaintDieHouseArena = false;
    public static int[] dx_imgDie = {420, 376, 290, 310};
    public static int[] dy_imgDie = {188, 447, 426, 105};

    public ItemMap(short s, short s2, int i, int i2, int[][] iArr) {
        super(s, s2, i, i2, iArr);
        this.TypeItem = (byte) 0;
    }

    public void setInfoItem(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    @Override // Map.MainItemMap
    public void paint(mGraphics mgraphics) {
        MainImage imagePartItemMap = ObjectData.getImagePartItemMap(this.IDImage);
        if (imagePartItemMap.img != null) {
            mgraphics.drawImage(imagePartItemMap.img, this.x + this.dx, this.y + this.dy, 0, false);
        }
    }

    public static void paintDieHouseArena(mGraphics mgraphics) {
        if (isPaintDieHouseArena) {
            if (GameCanvas.loadmap.idMap == 58) {
                img_HouseArena_Die[0].drawFrame(0, dx_imgDie[0], dy_imgDie[0], 0, mgraphics);
                return;
            }
            if (GameCanvas.loadmap.idMap == 56) {
                img_HouseArena_Die[1].drawFrame(0, dx_imgDie[1], dy_imgDie[1], 0, mgraphics);
            } else if (GameCanvas.loadmap.idMap == 54) {
                img_HouseArena_Die[2].drawFrame(0, dx_imgDie[2], dy_imgDie[2], 0, mgraphics);
            } else if (GameCanvas.loadmap.idMap == 60) {
                img_HouseArena_Die[3].drawFrame(0, dx_imgDie[3], dy_imgDie[3], 0, mgraphics);
            }
        }
    }
}
