package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FilePack;
import Model.MainImageDataPartChar;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/CharPartInfo.class */
public class CharPartInfo {
    public byte type;
    public short id;
    public int[][] x;
    public int[][] y;
    public int[][] w;
    public int[][] h;
    public byte[] arrData;
    int avxf;
    int avyf;
    int avx0;
    int avy0;
    int avw0;
    int avh0;
    public static mHashtable hashImagePartChar = new mHashtable();
    public static final byte[][] PART_OF_FRAME = {new byte[]{0, 0, 1, 2, 3, 4}, new byte[]{0, 0, 1, 2, 3, 4}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 0, 0, 0, 0, 0}, new byte[]{0, 1, 0, 1, 0, 1}};
    public long timeRemove = 0;
    public long timeRe = 0;
    public mImage image = null;
    public int[][] dx = mSystem.new_M_Int(4, 6);
    public int[][] dy = mSystem.new_M_Int(4, 6);

    public void load(byte[] bArr, int i, int i2) {
        FilePack.initByArray(bArr);
        this.image = FilePack.getImg(new StringBuffer().append(i2).append("").toString());
        InputStream loadData = FilePack.instance.loadData(new StringBuffer().append(i2).append(".d").toString());
        FilePack.reset();
        this.avx0 = CRes.readSignByte(loadData);
        this.avy0 = CRes.readSignByte(loadData);
        this.avw0 = CRes.readSignByte(loadData);
        this.avh0 = CRes.readSignByte(loadData);
        this.avxf = CRes.readSignByte(loadData);
        this.avyf = CRes.readSignByte(loadData);
        for (int i3 = 0; i3 < 4; i3++) {
            for (int i4 = 0; i4 < this.x[i3].length; i4++) {
                try {
                    this.x[i3][i4] = loadData.read();
                    this.y[i3][i4] = loadData.read();
                    this.w[i3][i4] = loadData.read();
                    this.h[i3][i4] = loadData.read();
                } catch (Exception e) {
                    e.printStackTrace();
                    mSystem.println("loi CharPart 1");
                    return;
                }
            }
            for (int i5 = 0; i5 < 6; i5++) {
                this.dx[i3][i5] = CRes.readSignByte(loadData);
                this.dy[i3][i5] = CRes.readSignByte(loadData);
            }
        }
    }

    public void loadNew(byte b, short s) {
        mImage mimage;
        if (mSystem.isImgLocal && this.timeRe == 0) {
            this.timeRe = GameCanvas.timeNow;
            try {
                mimage = mImage.createImage(new StringBuffer().append("/c/").append(FilePack.charAvatar[b]).append("/b").append((int) s).append("_").append(FilePack.charAvatar[b]).append(".png").toString());
            } catch (Exception e) {
                mimage = null;
            }
            if (mimage != null && mimage.image != null) {
                DataInputStream dataInputStream = null;
                try {
                    dataInputStream = mImage.openFile(new StringBuffer().append("/c/").append(FilePack.charAvatar[b]).append("/b").append((int) s).append("_").append(FilePack.charAvatar[b]).append("_data").toString());
                } catch (Exception e2) {
                }
                if (dataInputStream != null) {
                    this.image = mimage;
                    FilePack.reset();
                    for (int i = 0; i < 3; i++) {
                        for (int i2 = 0; i2 < this.x[i].length; i2++) {
                            try {
                                this.x[i][i2] = dataInputStream.read();
                                this.y[i][i2] = dataInputStream.read();
                                this.w[i][i2] = dataInputStream.read();
                                this.h[i][i2] = dataInputStream.read();
                            } catch (Exception e3) {
                                mSystem.println(new StringBuffer().append("loi ham char par").append(e3.toString()).toString());
                                e3.printStackTrace();
                                return;
                            }
                        }
                    }
                    for (int i3 = 0; i3 < 4; i3++) {
                        for (int i4 = 0; i4 < 6; i4++) {
                            this.dx[i3][i4] = CRes.readSignByte(dataInputStream);
                            this.dy[i3][i4] = CRes.readSignByte(dataInputStream);
                        }
                    }
                    return;
                }
            }
        }
        MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar) hashImagePartChar.get(new StringBuffer().append((int) b).append("_").append((int) s).toString());
        if (mainImageDataPartChar == null) {
            this.timeRemove = GameCanvas.timeNow;
            hashImagePartChar.put(new StringBuffer().append((int) b).append("_").append((int) s).toString(), new MainImageDataPartChar());
            getFromRms(b, s);
            return;
        }
        if (mainImageDataPartChar.img == null) {
            if ((GameCanvas.timeNow - this.timeRemove) / 1000 >= 15) {
                getFromRms(b, s);
                return;
            }
            return;
        }
        this.image = mainImageDataPartChar.img;
        DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream(mainImageDataPartChar.isData));
        FilePack.reset();
        for (int i5 = 0; i5 < 3; i5++) {
            for (int i6 = 0; i6 < this.x[i5].length; i6++) {
                try {
                    this.x[i5][i6] = dataInputStream2.read();
                    this.y[i5][i6] = dataInputStream2.read();
                    this.w[i5][i6] = dataInputStream2.read();
                    this.h[i5][i6] = dataInputStream2.read();
                } catch (Exception e4) {
                    e4.printStackTrace();
                    mSystem.outloi("loi CharPart 3");
                }
            }
        }
        for (int i7 = 0; i7 < 4; i7++) {
            for (int i8 = 0; i8 < 6; i8++) {
                this.dx[i7][i8] = CRes.readSignByte(dataInputStream2);
                this.dy[i7][i8] = CRes.readSignByte(dataInputStream2);
            }
        }
        hashImagePartChar.remove(mainImageDataPartChar);
    }

    public CharPartInfo(byte b, short s) {
        this.type = b;
        this.id = s;
        int i = 0;
        switch (b) {
            case 0:
            case 1:
                i = 5;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                i = 1;
                break;
            case 6:
                i = 2;
                break;
        }
        this.x = mSystem.new_M_Int(4, i);
        this.y = mSystem.new_M_Int(4, i);
        this.w = mSystem.new_M_Int(4, i);
        this.h = mSystem.new_M_Int(4, i);
        loadNew(b, s);
    }

    public void paint(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        try {
            if (this.type < 0) {
                return;
            }
            int i5 = i3;
            if (i5 > 2) {
                i5 = 2;
            }
            if (this.image != null && this.image.image != null) {
                int i6 = this.dx[i3][i4];
                int i7 = this.dy[i3][i4];
                if (i3 > 2) {
                    i6 = (-this.dx[i5][i4]) - this.w[i5][PART_OF_FRAME[this.type][i4]];
                    i7 = this.dy[i5][i4];
                }
                mgraphics.drawRegion(this.image, this.x[i5][PART_OF_FRAME[this.type][i4]], this.y[i5][PART_OF_FRAME[this.type][i4]], this.w[i5][PART_OF_FRAME[this.type][i4]], this.h[i5][PART_OF_FRAME[this.type][i4]], i3 > 2 ? 2 : 0, i + i6, i2 + i7, 0, true);
                this.timeRemove = GameCanvas.timeNow;
            } else {
                loadNew(this.type, this.id);
            }
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("loi day ne ").append((int) this.id).toString());
        }
    }

    public void paintShow(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (this.type < 0) {
            return;
        }
        int i5 = i3;
        if (i5 > 2) {
            i5 = 2;
        }
        if (this.image != null && this.image.image != null) {
            mgraphics.drawRegion(this.image, this.x[i5][PART_OF_FRAME[this.type][i4]], this.y[i5][PART_OF_FRAME[this.type][i4]], this.w[i5][PART_OF_FRAME[this.type][i4]], this.h[i5][PART_OF_FRAME[this.type][i4]], i3 > 2 ? 2 : 0, i + this.dx[i3][i4], i2 - (this.h[i5][PART_OF_FRAME[this.type][i4]] / 2), 0, true);
            this.timeRemove = GameCanvas.timeNow;
        } else {
            loadNew(this.type, this.id);
            mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, i, i2, 3, true);
        }
    }

    public void paintAvatar(mGraphics mgraphics, short s, short s2, int i) {
        if (this.image != null && this.image.image != null) {
            mgraphics.drawRegion(this.image, this.avx0, this.avy0, this.avw0, this.avh0, 0, s + this.avxf, s2 + this.avyf, 0, true);
        }
    }

    public void getFromRms(byte b, short s) {
        this.timeRemove = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char(b, s);
    }
}
