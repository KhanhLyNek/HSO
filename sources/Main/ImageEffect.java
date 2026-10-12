package Main;

import CLib.mHashtable;
import CLib.mImage;
import java.util.Enumeration;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:Main/ImageEffect.class */
public class ImageEffect {
    public static mHashtable hashImageEff = new mHashtable();
    public static long lastTime = 0;
    public long timeRemove;
    public int imageId;
    public mImage img;

    public ImageEffect(int i) {
        this.imageId = i;
        this.img = mImage.createImage(new StringBuffer().append("/eff/g").append(i).append(".png").toString());
        if (this.img == null || this.img.image == null) {
            this.img = getEffectImgFromRMS(i);
        }
        this.timeRemove = GameCanvas.timeNow;
    }

    public ImageEffect() {
    }

    public ImageEffect(int i, mImage mimage) {
        this.imageId = i;
        this.img = mimage;
        this.timeRemove = GameCanvas.timeNow;
    }

    public static mImage setImage(int i) {
        ImageEffect imageEffect = (ImageEffect) hashImageEff.get(new StringBuffer().append("").append(i).toString());
        if (imageEffect == null) {
            imageEffect = new ImageEffect(i);
            hashImageEff.put(new StringBuffer().append("").append(i).toString(), imageEffect);
        } else {
            imageEffect.timeRemove = GameCanvas.timeNow;
        }
        return imageEffect.img;
    }

    public static void SetRemove() {
        Enumeration keys = hashImageEff.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            ImageEffect imageEffect = (ImageEffect) hashImageEff.get(str);
            if ((GameCanvas.timeNow - imageEffect.timeRemove) / 1000 > 60) {
                if (imageEffect.img != null) {
                    imageEffect.img.clearData();
                }
                hashImageEff.remove(str);
            }
        }
    }

    public static void SetRemoveAll() {
        Enumeration keys = hashImageEff.keys();
        while (keys.hasMoreElements()) {
            Object obj = hashImageEff.get((String) keys.nextElement());
            ImageEffect imageEffect = null;
            if (obj != null) {
                imageEffect = (ImageEffect) obj;
            }
            if (imageEffect != null && imageEffect.img != null) {
                imageEffect.img.clearData();
            }
        }
        hashImageEff.clear();
    }

    public static mImage getEffectImgFromRMS(int i) {
        mImage mimage = new mImage();
        lastTime = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char((byte) 110, (short) i);
        return mimage;
    }
}
