package GameEffect;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import GameObjects.Part;
import Model.MainPartImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

/* loaded from: KnightOnline_v403.jar:GameEffect/MainEffectAuto.class */
public class MainEffectAuto {
    public static mHashtable hashTemEffAuto = new mHashtable();
    public mHashtable hashImage = new mHashtable();
    public MainFrameEff[] mFrame;
    short[] mRunFrame;

    public MainEffectAuto(int i, byte[] bArr) {
        setEffAuto(i, bArr);
    }

    public void setEffAuto(int i, byte[] bArr) {
        InputStream openFile;
        DataInputStream dataInputStream;
        try {
            if (bArr != null) {
                openFile = new ByteArrayInputStream(bArr);
            } else {
                openFile = mImage.openFile(new StringBuffer().append("/eff_").append(i).toString());
            }
            if (openFile != null) {
                if (i < 34) {
                    if (!mSystem.isMaHoa) {
                        dataInputStream = new DataInputStream(openFile);
                    } else {
                        DataInputStream dataInputStream2 = new DataInputStream(openFile);
                        byte[] bArr2 = new byte[dataInputStream2.available()];
                        dataInputStream2.read(bArr2);
                        for (int i2 = 0; i2 < bArr2.length; i2++) {
                            bArr2[i2] = (byte) (bArr2[i2] ^ 3);
                        }
                        dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr2));
                    }
                } else {
                    dataInputStream = new DataInputStream(openFile);
                }
                int readUnsignedByte = dataInputStream.readUnsignedByte();
                for (int i3 = 0; i3 < readUnsignedByte; i3++) {
                    MainPartImage mainPartImage = new MainPartImage(dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte(), dataInputStream.readUnsignedByte());
                    this.hashImage.put(new StringBuffer().append("").append((int) mainPartImage.ID).toString(), mainPartImage);
                }
                int readShort = dataInputStream.readShort();
                this.mFrame = new MainFrameEff[readShort];
                for (int i4 = 0; i4 < readShort; i4++) {
                    int readByte = dataInputStream.readByte();
                    this.mFrame[i4] = new MainFrameEff();
                    this.mFrame[i4].mpart = new Part[readByte];
                    for (int i5 = 0; i5 < readByte; i5++) {
                        this.mFrame[i4].mpart[i5] = new Part();
                        this.mFrame[i4].mpart[i5].x = dataInputStream.readShort();
                        this.mFrame[i4].mpart[i5].y = dataInputStream.readShort();
                        this.mFrame[i4].mpart[i5].idPartImage = dataInputStream.readByte();
                    }
                }
                int readShort2 = dataInputStream.readShort();
                this.mRunFrame = new short[readShort2];
                for (int i6 = 0; i6 < readShort2; i6++) {
                    this.mRunFrame[i6] = dataInputStream.readShort();
                }
                dataInputStream.readByte();
                dataInputStream.readByte();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mSystem.println(new StringBuffer().append("err effauto 2 id=").append(i).toString());
        }
    }
}
