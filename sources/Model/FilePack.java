package Model;

import CLib.mImage;
import CLib.mSystem;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: KnightOnline_v403.jar:Model/FilePack.class */
public class FilePack {
    public static FilePack instance;
    public String[] fname;
    private int[] fpos;
    private int[] flen;
    private byte[] fullData;
    private int nFile;
    private String name;
    private byte[] code = {78, 103, 117, 121, 101, 110, 86, 97, 110, 77, 105, 110, 104};
    private int codeLen = this.code.length;
    public static final String[] charAvatar = {"leg", "body", "head", "hat", "eye", "hair", "wing"};
    public static final String wps = "/wps/";
    public static final String weapon = "/weapon/";
    private DataInputStream file;

    public FilePack() {
    }

    public static void reset() {
        if (instance != null) {
            instance.close();
        }
        instance = null;
        System.gc();
    }

    public FilePack(String str, byte[] bArr) {
        int i = 0;
        int i2 = 0;
        this.name = str;
        if (bArr == null) {
            open();
        } else {
            openbyArray(bArr);
        }
        if (this.file == null) {
            instance = null;
            return;
        }
        try {
            this.nFile = encode(this.file.readUnsignedByte());
            this.fname = new String[this.nFile];
            this.fpos = new int[this.nFile];
            this.flen = new int[this.nFile];
            for (int i3 = 0; i3 < this.nFile; i3++) {
                byte[] bArr2 = new byte[encode(this.file.readByte())];
                this.file.read(bArr2);
                encode(bArr2);
                this.fname[i3] = new String(bArr2);
                this.fpos[i3] = i;
                this.flen[i3] = encode(this.file.readUnsignedShort());
                i += this.flen[i3];
                i2 += this.flen[i3];
            }
            this.fullData = new byte[i2];
            this.file.readFully(this.fullData);
            encode(this.fullData);
        } catch (Exception e) {
            mSystem.outloi("loi FilePack 1");
            e.printStackTrace();
        }
        close();
    }

    public static mImage getImg(String str) {
        return instance.loadImage(new StringBuffer().append(str).append(".png").toString());
    }

    public static void init(String str) {
        instance = new FilePack(str, null);
    }

    public static void initByArray(byte[] bArr) {
        instance = new FilePack("", bArr);
    }

    private int encode(int i) {
        return i;
    }

    private void encode(byte[] bArr) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (bArr[i] ^ this.code[i % this.codeLen]);
        }
    }

    private void open() {
        this.file = new DataInputStream(getClass().getResourceAsStream(this.name));
    }

    private void openbyArray(byte[] bArr) {
        this.file = new DataInputStream(new ByteArrayInputStream(bArr));
    }

    public void close() {
        try {
            if (this.file != null) {
                this.file.close();
            }
        } catch (IOException e) {
            mSystem.outloi("loi FilePack 1");
            e.printStackTrace();
        }
    }

    public byte[] loadFile(String str) throws Exception {
        for (int i = 0; i < this.nFile; i++) {
            if (this.fname[i].compareTo(str) == 0) {
                byte[] bArr = new byte[this.flen[i]];
                System.arraycopy(this.fullData, this.fpos[i], bArr, 0, this.flen[i]);
                return bArr;
            }
        }
        throw new Exception(new StringBuffer().append("File '").append(str).append("' not found!").toString());
    }

    public mImage loadImage(String str) {
        for (int i = 0; i < this.nFile; i++) {
            if (this.fname[i].compareTo(str) == 0) {
                return mImage.createImage(this.fullData, this.fpos[i], this.flen[i], str);
            }
        }
        return null;
    }

    public InputStream loadData(String str) {
        for (int i = 0; i < this.nFile; i++) {
            if (this.fname[i].compareTo(str) == 0) {
                byte[] bArr = new byte[this.flen[i]];
                System.arraycopy(this.fullData, this.fpos[i], bArr, 0, this.flen[i]);
                return new ByteArrayInputStream(bArr);
            }
        }
        return null;
    }

    public byte[] getBinaryFile(String str) {
        for (int i = 0; i < this.nFile; i++) {
            if (this.fname[i].compareTo(str) == 0) {
                byte[] bArr = new byte[this.flen[i]];
                System.arraycopy(this.fullData, this.fpos[i], bArr, 0, this.flen[i]);
                return bArr;
            }
        }
        mSystem.outloi(new StringBuffer().append("File '").append(str).append("' not found!").toString());
        return null;
    }
}
