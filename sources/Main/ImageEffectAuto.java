package Main;

import CLib.mHashtable;
import CLib.mImage;
import GameEffect.EffectAuto;
import java.util.Enumeration;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:Main/ImageEffectAuto.class */
public class ImageEffectAuto {
    public static mHashtable hashImageEffAuto = new mHashtable();
    public long timeRemove;
    int imageId;
    public mImage img;

    public ImageEffectAuto(int i) {
        this.imageId = i;
        this.img = mImage.createImage(new StringBuffer().append("/effauto/eff_").append(i).append(".png").toString());
        if (this.img == null || this.img.image == null) {
            this.img = EffectAuto.getEffectAutoImgFromRMS(i);
        }
        this.timeRemove = GameCanvas.timeNow;
    }

    public static void setRemove() {
        Enumeration keys = hashImageEffAuto.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            ImageEffectAuto imageEffectAuto = (ImageEffectAuto) hashImageEffAuto.get(str);
            if ((GameCanvas.timeNow - imageEffectAuto.timeRemove) / 1000 > 60) {
                if (imageEffectAuto.img != null) {
                    imageEffectAuto.img.clearData();
                }
                hashImageEffAuto.remove(str);
            }
        }
    }

    public static mImage setImage(int i) {
        ImageEffectAuto imageEffectAuto = (ImageEffectAuto) hashImageEffAuto.get(new StringBuffer().append("").append(i).toString());
        if (imageEffectAuto == null) {
            imageEffectAuto = new ImageEffectAuto(i);
            hashImageEffAuto.put(new StringBuffer().append("").append(i).toString(), imageEffectAuto);
            if (imageEffectAuto == null || imageEffectAuto.img == null) {
                GlobalService.gI().load_image_data_part_char((byte) 111, (short) i);
            }
        } else {
            imageEffectAuto.timeRemove = GameCanvas.timeNow;
        }
        return imageEffectAuto.img;
    }

    public static mImage setImageMatna(int i) {
        ImageEffectAuto imageEffectAuto = (ImageEffectAuto) hashImageEffAuto.get(new StringBuffer().append("").append(i).toString());
        if (imageEffectAuto == null) {
            imageEffectAuto = new ImageEffectAuto(i);
            hashImageEffAuto.put(new StringBuffer().append("").append(i).toString(), imageEffectAuto);
            if (imageEffectAuto == null || imageEffectAuto.img == null) {
                GlobalService.gI().load_image_data_part_char((byte) 7, (short) i);
            }
        } else {
            imageEffectAuto.timeRemove = GameCanvas.timeNow;
        }
        return imageEffectAuto.img;
    }

    public static void SetRemoveAll() {
        Enumeration keys = hashImageEffAuto.keys();
        while (keys.hasMoreElements()) {
            Object obj = hashImageEffAuto.get((String) keys.nextElement());
            ImageEffectAuto imageEffectAuto = null;
            if (obj != null) {
                imageEffectAuto = (ImageEffectAuto) obj;
            }
            if (imageEffectAuto != null && imageEffectAuto.img != null) {
                imageEffectAuto.img.clearData();
            }
        }
        hashImageEffAuto.clear();
    }
}
