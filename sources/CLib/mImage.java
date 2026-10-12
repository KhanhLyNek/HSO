package CLib;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Image;

/* loaded from: KnightOnline_v403.jar:CLib/mImage.class */
public class mImage {
    public Image image;

    public static String getLink(String str) {
        return str;
    }

    public void clearData() {
    }

    public static mImage createEncodedImage(String str) {
        mImage mimage = new mImage();
        String stringBuffer = new StringBuffer().append("/x").append(mGraphics.zoomLevel).append(str).toString();
        try {
            DataInputStream dataInputStream = new DataInputStream("".getClass().getResourceAsStream(stringBuffer));
            int available = dataInputStream.available();
            byte[] bArr = new byte[available];
            dataInputStream.read(bArr, 0, available);
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (bArr[i] ^ 3);
            }
            mimage.image = Image.createImage(bArr, 0, available);
        } catch (IOException e) {
            mSystem.outz(new StringBuffer().append("IOException :").append(e.getMessage()).toString());
        } catch (IllegalArgumentException e2) {
            mSystem.outz(new StringBuffer().append(stringBuffer).append(" IllegalArgumentException :").append(e2.getMessage()).toString());
        } catch (NullPointerException e3) {
            mSystem.outz(new StringBuffer().append(stringBuffer).append(" NullPointerException :").append(e3.getMessage()).toString());
        }
        if (mimage.image == null) {
            return null;
        }
        return mimage;
    }

    public static String replaceIMGtoPNG(String str, String str2, String str3) {
        String str4 = "";
        String str5 = "";
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf > 0) {
            str4 = str.substring(lastIndexOf + 1, str.length());
            str5 = str.substring(0, lastIndexOf + 1);
        }
        if (str4.compareTo(str2) == 0) {
            StringBuffer stringBuffer = new StringBuffer(str5);
            stringBuffer.append(str3);
            return stringBuffer.toString();
        }
        return str;
    }

    public static mImage createImage(String str) {
        String replaceIMGtoPNG = replaceIMGtoPNG(str, "img", "png");
        String str2 = "";
        int lastIndexOf = replaceIMGtoPNG.lastIndexOf(46);
        if (lastIndexOf > 0) {
            str2 = replaceIMGtoPNG.substring(lastIndexOf + 1, replaceIMGtoPNG.length());
        }
        if (str2.compareTo("img") == 0) {
            return createEncodedImage(replaceIMGtoPNG);
        }
        mImage mimage = new mImage();
        try {
            mimage.image = Image.createImage(new StringBuffer().append("/x").append(mGraphics.zoomLevel).append(replaceIMGtoPNG).toString());
        } catch (Exception e) {
        }
        if (mimage.image == null) {
            return null;
        }
        return mimage;
    }

    public static mImage createImageAll(String str) {
        mImage mimage = new mImage();
        try {
            mimage.image = Image.createImage(str);
        } catch (Exception e) {
        }
        if (mimage.image == null) {
            return null;
        }
        return mimage;
    }

    public static DataInputStream openFile(String str) {
        return new DataInputStream("".getClass().getResourceAsStream(getLink(str)));
    }

    public static mImage createImage(int i, int i2) {
        mImage mimage = new mImage();
        mimage.image = Image.createImage(i * mGraphics.zoomLevel, i2 * mGraphics.zoomLevel);
        return mimage;
    }

    public static mImage createImage(byte[] bArr, int i, int i2, String str) {
        mImage mimage = new mImage();
        mimage.image = Image.createImage(bArr, 0, bArr.length);
        return mimage;
    }

    public TemGraphics getGraphics() {
        TemGraphics temGraphics = new TemGraphics();
        temGraphics.g = this.image.getGraphics();
        return temGraphics;
    }

    public static int getImageWidth(Image image) {
        return image.getWidth() / mGraphics.zoomLevel;
    }

    public int getWith() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public static int getImageHeight(Image image) {
        return image.getHeight() / mGraphics.zoomLevel;
    }

    public void getRGB(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        this.image.getRGB(iArr, i, i2, i3, i4, i5, i6);
    }

    public static mImage createRGBImage(int[] iArr, int i, int i2, boolean z) {
        mImage mimage = new mImage();
        mimage.image = Image.createRGBImage(iArr, i, i2, z);
        return mimage;
    }
}
