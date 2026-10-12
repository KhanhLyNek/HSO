package GameObjects;

import CLib.mGraphics;
import Model.FrameImage;

/* loaded from: KnightOnline_v403.jar:GameObjects/Mount.class */
public class Mount extends MainObject {
    public static byte[][] Dir = {new byte[]{5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6}, new byte[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, new byte[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, new byte[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}};

    public Mount(byte b, byte b2, int i, int i2) {
        this.typeMount = b;
        this.x = i;
        this.y = i2;
        this.Direction = b2;
        this.Action = 0;
        this.typeObject = (byte) 10;
    }

    @Override // GameObjects.MainObject
    public void updateMount() {
        this.frameMount = (byte) (this.frameMount + 1);
        if (this.frameMount > Dir[this.Direction].length - 1) {
            this.frameMount = (byte) 0;
        }
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        FrameImage frameImageMount;
        if (this.typeMount != -1 && (frameImageMount = FrameImage.getFrameImageMount(this.typeMount, 3, 5, 0)) != null) {
            mgraphics.drawImage(MainObject.shadow, this.x, this.y - 8, 3, false);
            frameImageMount.drawFrameNew(Dir[this.Direction][this.frameMount], this.x + this.xMount, ((this.y - this.ysai) - this.dy) + this.yMount, this.Direction > 2 ? 2 : 0, 33, mgraphics);
        }
    }
}
