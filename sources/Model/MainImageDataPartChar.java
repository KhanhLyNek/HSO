package Model;

import CLib.mImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/* loaded from: KnightOnline_v403.jar:Model/MainImageDataPartChar.class */
public class MainImageDataPartChar {
    public mImage img;
    public int count;
    public byte type;
    public short id;
    public short version;
    public long timeImageNull;
    public byte[] isData;
    public byte[] isDataImg;

    public MainImageDataPartChar() {
        this.count = -1;
        this.timeImageNull = 0L;
        this.isData = null;
        this.isDataImg = null;
    }

    public MainImageDataPartChar(byte[] bArr, byte[] bArr2, byte b, short s, short s2) {
        this.count = -1;
        this.timeImageNull = 0L;
        this.isData = null;
        this.isDataImg = null;
        this.isDataImg = bArr;
        this.count = 0;
        this.isData = bArr2;
        this.type = b;
        this.id = s;
        this.version = s2;
    }

    public MainImageDataPartChar(mImage mimage, byte[] bArr) {
        this.count = -1;
        this.timeImageNull = 0L;
        this.isData = null;
        this.isDataImg = null;
        this.img = mimage;
        this.count = 0;
        this.isData = bArr;
    }

    public ByteArrayOutputStream getSaveData() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort(this.version);
            dataOutputStream.writeInt(this.isDataImg.length);
            dataOutputStream.write(this.isDataImg);
            dataOutputStream.writeShort(this.isData.length);
            dataOutputStream.write(this.isData);
        } catch (Exception e) {
        }
        return byteArrayOutputStream;
    }
}
