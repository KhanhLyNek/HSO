package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import Model.CRes;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* loaded from: KnightOnline_v403.jar:GameObjects/DataEffect.class */
public class DataEffect {
    public SmallImage[] smallImage;
    public byte[] sequence;
    public short idimg;
    private short FrameWith;
    private short FrameHeight;
    public static byte[][] indexAction2 = {new byte[]{0, 0, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new byte[]{4, 4, 5, 6, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
    public static byte[] indexAction = {0, 0, 1, 2, 3, 1, 1, 1, 1};
    public mVector listFrame = new mVector();
    public mVector listAnima = new mVector();
    public String name = "";
    public byte isFly = 0;
    public byte idShadow = 0;

    public DataEffect(byte[] bArr, int i, boolean z) {
        this.idimg = (short) i;
        setDataType1(bArr);
    }

    public DataEffect(byte[] bArr) {
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            int readByte = dataInputStream.readByte();
            this.smallImage = new SmallImage[readByte];
            for (int i = 0; i < readByte; i++) {
                this.smallImage[i] = new SmallImage(dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte());
            }
            int readShort = dataInputStream.readShort();
            for (int i2 = 0; i2 < readShort; i2++) {
                int readByte2 = dataInputStream.readByte();
                mVector mvector = new mVector();
                for (int i3 = 0; i3 < readByte2; i3++) {
                    mvector.addElement(new PartFrame(dataInputStream.readShort(), dataInputStream.readShort(), dataInputStream.readByte()));
                }
                this.listFrame.addElement(new FrameEff(mvector));
            }
            int readShort2 = dataInputStream.readShort();
            this.sequence = new byte[readShort2];
            for (int i4 = 0; i4 < readShort2; i4++) {
                this.sequence[i4] = (byte) dataInputStream.readShort();
            }
            dataInputStream.readByte();
            dataInputStream.readByte();
            byte[] bArr2 = new byte[dataInputStream.readByte()];
            dataInputStream.read(bArr2);
            this.listAnima.addElement(new Animation(bArr2));
            byte[] bArr3 = new byte[dataInputStream.readByte()];
            dataInputStream.read(bArr3);
            this.listAnima.addElement(new Animation(bArr3));
            byte[] bArr4 = new byte[dataInputStream.readByte()];
            dataInputStream.read(bArr4);
            this.listAnima.addElement(new Animation(bArr4));
            byte[] bArr5 = new byte[dataInputStream.readByte()];
            dataInputStream.read(bArr5);
            this.listAnima.addElement(new Animation(bArr5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v13, types: [int] */
    /* JADX WARN: Type inference failed for: r22v0 */
    /* JADX WARN: Type inference failed for: r22v1, types: [int] */
    /* JADX WARN: Type inference failed for: r22v2, types: [int] */
    /* JADX WARN: Type inference failed for: r23v3, types: [int] */
    public void setDataType1(byte[] bArr) {
        DataInputStream dataInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        this.listFrame.removeAllElements();
        this.listAnima.removeAllElements();
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
                dataInputStream = new DataInputStream(byteArrayInputStream);
                int readByte = dataInputStream.readByte();
                this.smallImage = new SmallImage[readByte];
                for (int i = 0; i < readByte; i++) {
                    this.smallImage[i] = new SmallImage(dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte());
                }
                int i2 = 0;
                int i3 = -1000000;
                short readShort = dataInputStream.readShort();
                for (short s = 0; s < readShort; s++) {
                    byte readByte2 = dataInputStream.readByte();
                    mVector mvector = new mVector();
                    for (byte b = 0; b < readByte2; b++) {
                        PartFrame partFrame = new PartFrame(dataInputStream.readShort(), dataInputStream.readShort(), dataInputStream.readByte());
                        partFrame.flip = dataInputStream.readByte();
                        partFrame.onTop = dataInputStream.readByte();
                        mvector.addElement(partFrame);
                        if (s == false) {
                            if (i3 < partFrame.dy + this.smallImage[partFrame.idSmallImg].h) {
                                i3 = partFrame.dy + this.smallImage[partFrame.idSmallImg].h;
                            }
                            if (i2 < CRes.abs(partFrame.dy)) {
                                i2 = CRes.abs(partFrame.dy);
                            }
                        }
                    }
                    if (s == false && i3 <= -5) {
                        this.isFly = (byte) i3;
                    }
                    this.listFrame.addElement(new FrameEff(mvector, null));
                }
                this.FrameWith = this.smallImage[0].w;
                this.FrameHeight = (short) i2;
                int readShort2 = dataInputStream.readShort();
                this.sequence = new byte[readShort2];
                for (int i4 = 0; i4 < readShort2; i4++) {
                    this.sequence[i4] = (byte) dataInputStream.readShort();
                }
                byte[] bArr2 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr2);
                this.listAnima.addElement(new Animation(bArr2));
                byte[] bArr3 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr3);
                this.listAnima.addElement(new Animation(bArr3));
                byte[] bArr4 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr4);
                this.listAnima.addElement(new Animation(bArr4));
                byte[] bArr5 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr5);
                this.listAnima.addElement(new Animation(bArr5));
                byte[] bArr6 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr6);
                this.listAnima.addElement(new Animation(bArr6));
                byte[] bArr7 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr7);
                this.listAnima.addElement(new Animation(bArr7));
                byte[] bArr8 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr8);
                this.listAnima.addElement(new Animation(bArr8));
                byte[] bArr9 = new byte[dataInputStream.readByte()];
                dataInputStream.read(bArr9);
                this.listAnima.addElement(new Animation(bArr9));
                if (dataInputStream.available() > 0) {
                    this.idShadow = dataInputStream.readByte();
                    for (short s2 = 0; s2 < readShort; s2++) {
                        FrameEff frameEff = (FrameEff) this.listFrame.elementAt(s2);
                        frameEff.xShadow = dataInputStream.readByte();
                        frameEff.yShadow = dataInputStream.readByte();
                    }
                }
                try {
                    dataInputStream.close();
                } catch (Exception e) {
                }
                try {
                    byteArrayInputStream.close();
                } catch (Exception e2) {
                }
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Exception e3) {
                }
                try {
                    byteArrayInputStream.close();
                } catch (Exception e4) {
                }
                throw th;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
            try {
                dataInputStream.close();
            } catch (Exception e6) {
            }
            try {
                byteArrayInputStream.close();
            } catch (Exception e7) {
            }
        }
    }

    public Animation getAnim(int i) {
        return (Animation) this.listAnima.elementAt(indexAction[i]);
    }

    public int getFrame(int i, int i2, int i3) {
        Animation animation = (Animation) this.listAnima.elementAt(indexAction2[i3][i2]);
        if (i < animation.frame.length) {
            return animation.frame[i];
        }
        return 0;
    }

    public int getFrame(int i, int i2) {
        Animation animation = (Animation) this.listAnima.elementAt(indexAction[i2]);
        if (i < animation.frame.length) {
            return animation.frame[i];
        }
        return 0;
    }

    public void paint(mGraphics mgraphics, int i, int i2, int i3, int i4, mImage mimage) {
        if (mimage == null) {
            return;
        }
        FrameEff frameEff = (FrameEff) this.listFrame.elementAt(0);
        for (int i5 = 0; i5 < frameEff.listPart.size(); i5++) {
            try {
                PartFrame partFrame = (PartFrame) frameEff.listPart.elementAt(i5);
                SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                int i6 = partFrame.dx;
                if (i4 == 2) {
                    i6 = (-i6) - smallImage.w;
                }
                mgraphics.drawRegion(mimage, smallImage.x, smallImage.y, smallImage.w, smallImage.h, i4, i2 + i6, i3 + partFrame.dy, 0, false);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void paintPet(mGraphics mgraphics, int i, int i2, int i3, int i4, mImage mimage) {
        if (mimage == null) {
            return;
        }
        FrameEff frameEff = (FrameEff) this.listFrame.elementAt(i);
        if (frameEff != null && frameEff.listPartTop == null) {
        }
        if (frameEff != null) {
            for (int i5 = 0; i5 < frameEff.listPartTop.size(); i5++) {
                try {
                    PartFrame partFrame = (PartFrame) frameEff.listPartTop.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    int i6 = partFrame.dx;
                    int i7 = smallImage.w;
                    int i8 = smallImage.h;
                    short s = smallImage.x;
                    short s2 = smallImage.y;
                    if (s > mImage.getImageWidth(mimage.image)) {
                        s = 0;
                    }
                    if (s2 > mImage.getImageHeight(mimage.image)) {
                        s2 = 0;
                    }
                    if (s + i7 > mImage.getImageWidth(mimage.image)) {
                        i7 = mImage.getImageWidth(mimage.image) - s;
                    }
                    if (s2 + i8 > mImage.getImageHeight(mimage.image)) {
                        i8 = mImage.getImageHeight(mimage.image) - s2;
                    }
                    if (i4 == 2) {
                        i6 = (-i6) - i7;
                    }
                    if (partFrame.flip != 1) {
                        mgraphics.drawRegion(mimage, s, s2, i7, i8, i4, i2 + i6, i3 + partFrame.dy, 0, false);
                    } else {
                        mgraphics.drawRegion(mimage, s, s2, i7, i8, i4 == 2 ? 0 : 2, i2 + i6, i3 + partFrame.dy, 0, false);
                    }
                } catch (Exception e) {
                    System.out.println(new StringBuffer().append("loi dataeff: ").append(this.name).toString());
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public mVector getDataPet() {
        mVector mvector = new mVector();
        mVector mvector2 = (mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) this.idimg).toString());
        if (mvector2 != null) {
            mvector = mvector2;
        }
        return mvector;
    }

    public Animation getAnim(int i, int i2) {
        return (Animation) this.listAnima.elementAt(indexAction2[i2][i]);
    }
}
