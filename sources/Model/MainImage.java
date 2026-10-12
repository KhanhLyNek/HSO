package Model;

import CLib.mImage;

/* loaded from: KnightOnline_v403.jar:Model/MainImage.class */
public class MainImage {
    public mImage img;
    public short w;
    public short h;
    public int count;
    public int timeImageNull;

    public MainImage() {
        this.count = -1;
        this.timeImageNull = 0;
    }

    public MainImage(mImage mimage) {
        this.count = -1;
        this.timeImageNull = 0;
        this.img = mimage;
        this.count = 0;
        this.w = (short) mImage.getImageWidth(mimage.image);
        this.h = (short) mImage.getImageWidth(mimage.image);
    }
}
