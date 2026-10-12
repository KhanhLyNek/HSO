package GameEffect;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Main.ImageEffectAuto;
import Map.MainItemMap;
import Model.CRes;
import Model.MainPartImage;
import Thread_More.LoadMap;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Enumeration;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameEffect/EffectAuto.class */
public class EffectAuto extends MainItemMap {
    MainEffectAuto eff;
    int nCountReplay;
    boolean isPaint;
    boolean isUpdate;
    int typeEffect;
    int valueEffect;
    int f;
    long timeBegin;
    public boolean wantdestroy;
    int indexSound;
    int timePlaySound;
    public static long lastTime;
    public long timelive;
    public short Loop;
    byte typedata;
    public int dxx;
    public int dyy;
    public byte Typemove;
    public static long timeSoundFIRE = 0;
    public static long timeSoundWATER = 0;
    public static long timeSoundPHUNNUOC = 0;
    public static long timeSoundGIOTNUOC = 0;
    public static mHashtable ALL_DATA_EFF_AUTO = new mHashtable();

    public EffectAuto(String str, String str2) {
        this.nCountReplay = 0;
        this.isPaint = true;
        this.isUpdate = false;
        this.indexSound = -1;
        this.timePlaySound = -1;
        this.typedata = (byte) 0;
        this.TypeItem = (byte) 1;
        String[] split = mFont.split(str2, ";");
        this.IDItem = Short.parseShort(split[0]);
        this.IDImage = Short.parseShort(split[1]);
        this.x = Integer.parseInt(split[2]) * LoadMap.wTile;
        this.y = Integer.parseInt(split[3]) * LoadMap.wTile;
        this.dx = Integer.parseInt(split[4]);
        this.dy = Integer.parseInt(split[5]);
        this.typeEffect = Integer.parseInt(split[6]);
        this.valueEffect = Integer.parseInt(split[7]);
        this.wOne = 70;
        this.hOne = 70;
        setDataEff(null);
    }

    public EffectAuto(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.nCountReplay = 0;
        this.isPaint = true;
        this.isUpdate = false;
        this.indexSound = -1;
        this.timePlaySound = -1;
        this.typedata = (byte) 0;
        this.TypeItem = (byte) 1;
        this.IDItem = (short) i;
        this.IDImage = (short) i;
        this.x = i2;
        this.y = i3;
        this.dx = i4;
        this.dy = i5;
        this.typeEffect = i6;
        this.valueEffect = i7;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        setDataEff(null);
    }

    public EffectAuto(int i, int i2, int i3, int i4, int i5, int i6, int i7, byte[] bArr, long j, byte b, int i8, int i9) {
        this.nCountReplay = 0;
        this.isPaint = true;
        this.isUpdate = false;
        this.indexSound = -1;
        this.timePlaySound = -1;
        this.typedata = (byte) 0;
        this.typedata = (byte) 1;
        this.TypeItem = (byte) 2;
        this.IDItem = (short) i;
        this.IDImage = (short) i;
        this.x = i2;
        this.y = i3;
        this.dx = i4;
        this.dy = i5;
        this.typeEffect = i6;
        this.valueEffect = i7;
        this.wOne = 70;
        this.hOne = 70;
        this.timelive = j;
        this.Typemove = b;
        this.dxx = i8;
        this.dyy = i9;
        setdataFromSV(bArr);
    }

    public EffectAuto(int i, int i2, int i3, int i4, int i5, int i6, int i7, byte[] bArr) {
        this.nCountReplay = 0;
        this.isPaint = true;
        this.isUpdate = false;
        this.indexSound = -1;
        this.timePlaySound = -1;
        this.typedata = (byte) 0;
        this.typedata = (byte) 1;
        this.TypeItem = (byte) 1;
        this.IDItem = (short) i;
        this.IDImage = (short) i;
        this.x = i2;
        this.y = i3;
        this.dx = i4;
        this.dy = i5;
        this.typeEffect = i6;
        this.valueEffect = i7;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        setdataFromSV(bArr);
    }

    public EffectAuto(int i, int i2, int i3, byte[] bArr) {
        this.nCountReplay = 0;
        this.isPaint = true;
        this.isUpdate = false;
        this.indexSound = -1;
        this.timePlaySound = -1;
        this.typedata = (byte) 0;
        this.typedata = (byte) 1;
        this.TypeItem = (byte) 3;
        this.IDItem = (short) i;
        this.IDImage = (short) i;
        this.x = i2;
        this.y = i3;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        setdataFromSV(bArr);
    }

    public byte[] getdataFromRMS() {
        byte[] bArr = null;
        try {
            byte[] loadRMS = CRes.loadRMS(new StringBuffer().append("data_eff").append((int) this.IDImage).toString());
            if (loadRMS != null) {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(loadRMS));
                bArr = new byte[dataInputStream.readShort()];
                dataInputStream.read(bArr);
                dataInputStream.close();
            }
        } catch (Exception e) {
        }
        return bArr;
    }

    public EffectAuto(int i, int i2, int i3, int i4, int i5, int i6, int i7, byte[] bArr, short s) {
        this.nCountReplay = 0;
        this.isPaint = true;
        this.isUpdate = false;
        this.indexSound = -1;
        this.timePlaySound = -1;
        this.typedata = (byte) 0;
        this.typedata = (byte) 1;
        this.TypeItem = (byte) 1;
        this.IDItem = (short) i;
        this.IDImage = (short) i;
        this.x = i2;
        this.y = i3;
        this.dx = i4;
        this.dy = i5;
        this.typeEffect = i6;
        this.valueEffect = i7;
        this.wOne = 70;
        this.hOne = 70;
        this.dxx = 0;
        this.dyy = 0;
        this.Loop = s;
        setdataFromSV(bArr);
    }

    public void setdataFromSV(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            this.eff = loadTemEff(this.IDImage, bArr);
            this.timeBegin = GameCanvas.timeNow;
            this.isPaint = true;
            this.isUpdate = true;
            setPlaySound();
            return;
        }
        DataEffAuto dataEffAuto = (DataEffAuto) ALL_DATA_EFF_AUTO.get(new StringBuffer().append("").append((int) this.IDImage).toString());
        if (dataEffAuto == null) {
            byte[] bArr2 = getdataFromRMS();
            if (bArr2 != null && bArr2.length > 0) {
                dataEffAuto = new DataEffAuto(this.IDImage);
                dataEffAuto.setdata(bArr2);
                ALL_DATA_EFF_AUTO.put(new StringBuffer().append((int) this.IDImage).append("").toString(), dataEffAuto);
                this.eff = loadTemEff(this.IDImage, dataEffAuto.data);
                this.timeBegin = GameCanvas.timeNow;
                this.isPaint = true;
                this.isUpdate = true;
                dataEffAuto.timeremove = (int) (mSystem.currentTimeMillis() / 1000);
                setPlaySound();
            } else {
                dataEffAuto = new DataEffAuto(this.IDImage);
                ALL_DATA_EFF_AUTO.put(new StringBuffer().append((int) this.IDImage).append("").toString(), dataEffAuto);
                ImageEffectAuto.setImage(this.IDImage);
                dataEffAuto.timeremove = (int) (mSystem.currentTimeMillis() / 1000);
            }
        }
        if (dataEffAuto != null && dataEffAuto.data != null && dataEffAuto.data.length > 0) {
            this.eff = loadTemEff(this.IDImage, dataEffAuto.data);
            this.timeBegin = GameCanvas.timeNow;
            this.isPaint = true;
            this.isUpdate = true;
            setPlaySound();
        }
    }

    @Override // Map.MainItemMap
    public void setDataEff(byte[] bArr) {
        this.eff = loadTemEff(this.IDImage, bArr);
        this.timeBegin = GameCanvas.timeNow;
        this.isPaint = true;
        this.isUpdate = true;
        setPlaySound();
    }

    public void paintMatna(mGraphics mgraphics) {
        if (this.typedata == 1 && !this.isUpdate) {
            setdataFromSV(null);
            return;
        }
        try {
            if (this.eff != null && this.isPaint) {
                short s = this.eff.mRunFrame[this.f];
                int length = this.eff.mFrame[s].mpart.length;
                for (int i = 0; i < length; i++) {
                    MainPartImage mainPartImage = (MainPartImage) this.eff.hashImage.get(new StringBuffer().append("").append((int) this.eff.mFrame[s].mpart[i].idPartImage).toString());
                    mImage image = ImageEffectAuto.setImage(this.IDImage);
                    if (mainPartImage != null && image != null && image.image != null) {
                        mgraphics.drawRegion(image, mainPartImage.x, mainPartImage.y, mainPartImage.w, mainPartImage.h, 0, this.x + this.dx + this.eff.mFrame[s].mpart[i].x, this.y + this.dy + this.eff.mFrame[s].mpart[i].y, 0, false);
                    }
                }
            }
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("paint effauto null img1: ").append(e.toString()).toString());
            this.isPaint = false;
            this.isUpdate = false;
        }
    }

    @Override // Map.MainItemMap
    public void paint(mGraphics mgraphics) {
        if (this.typedata == 1 && !this.isUpdate) {
            setdataFromSV(null);
            return;
        }
        if (this.TypeItem == 3) {
            try {
                if (this.eff != null && this.isPaint) {
                    short s = this.eff.mRunFrame[this.f];
                    int length = this.eff.mFrame[s].mpart.length;
                    for (int i = 0; i < length; i++) {
                        MainPartImage mainPartImage = (MainPartImage) this.eff.hashImage.get(new StringBuffer().append("").append((int) this.eff.mFrame[s].mpart[i].idPartImage).toString());
                        mImage image = ImageEffectAuto.setImage(this.IDImage);
                        if (mainPartImage != null && image != null && image.image != null) {
                            mgraphics.drawRegion(image, mainPartImage.x, mainPartImage.y, mainPartImage.w, mainPartImage.h, 0, this.x + this.dx + this.eff.mFrame[s].mpart[i].x, this.y + this.dy + this.eff.mFrame[s].mpart[i].y, 0, false);
                        }
                    }
                }
                return;
            } catch (Exception e) {
                mSystem.println(new StringBuffer().append("paint effauto null img1: ").append(e.toString()).toString());
                this.isPaint = false;
                this.isUpdate = false;
                return;
            }
        }
        if (this.TypeItem == 4) {
            paintMatna(mgraphics);
            return;
        }
        if ((GameCanvas.lowGraphic || !this.isUpdate || LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) && !GameScreen.infoGame.isMapchienthanh()) {
            return;
        }
        try {
            if (this.eff != null && this.isPaint) {
                short s2 = this.eff.mRunFrame[this.f];
                int length2 = this.eff.mFrame[s2].mpart.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    MainPartImage mainPartImage2 = (MainPartImage) this.eff.hashImage.get(new StringBuffer().append("").append((int) this.eff.mFrame[s2].mpart[i2].idPartImage).toString());
                    mImage image2 = ImageEffectAuto.setImage(this.IDImage);
                    if (mainPartImage2 != null && image2 != null && image2.image != null) {
                        mgraphics.drawRegion(image2, mainPartImage2.x, mainPartImage2.y, mainPartImage2.w, mainPartImage2.h, 0, this.x + this.dx + this.eff.mFrame[s2].mpart[i2].x, this.y + this.dy + this.eff.mFrame[s2].mpart[i2].y, 0, false);
                    }
                }
            }
        } catch (Exception e2) {
            mSystem.println(new StringBuffer().append("paint effauto null img1: ").append((int) this.IDImage).append(" ").append(e2.toString()).append(" ").append(this.eff.mRunFrame.length).toString());
            this.isPaint = false;
            this.isUpdate = false;
        }
    }

    public void paint(mGraphics mgraphics, int i, int i2) {
        if (this.typedata == 1 && !this.isUpdate) {
            setdataFromSV(null);
            return;
        }
        if (GameCanvas.lowGraphic || !this.isUpdate || LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) {
            return;
        }
        try {
            if (this.eff != null && this.isPaint) {
                short s = this.eff.mRunFrame[this.f];
                int length = this.eff.mFrame[s].mpart.length;
                for (int i3 = 0; i3 < length; i3++) {
                    MainPartImage mainPartImage = (MainPartImage) this.eff.hashImage.get(new StringBuffer().append("").append((int) this.eff.mFrame[s].mpart[i3].idPartImage).toString());
                    mImage image = ImageEffectAuto.setImage(this.IDImage);
                    if (mainPartImage != null && image != null && image.image != null) {
                        mgraphics.drawRegion(image, mainPartImage.x, mainPartImage.y, mainPartImage.w, mainPartImage.h, 0, i + this.dx + this.eff.mFrame[s].mpart[i3].x + this.dxx, i2 + this.dy + this.eff.mFrame[s].mpart[i3].y + this.dyy, 0, false);
                    }
                }
            }
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("paint effauto null img ").append((int) this.IDImage).append(" ").append(e.toString()).toString());
            this.isPaint = false;
            this.isUpdate = false;
        }
    }

    public boolean lockmoveByeffAuto() {
        return this.timelive - mSystem.currentTimeMillis() > 0 && this.Typemove == 1;
    }

    public boolean CanpaintByeffauto() {
        return this.timelive - mSystem.currentTimeMillis() > 0 && this.Typemove == 2;
    }

    @Override // Map.MainItemMap
    public void update() {
        if (this.typedata == 1 && !this.isUpdate) {
            return;
        }
        try {
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("eff=").append((int) this.IDImage).append(e.toString()).toString());
            this.isPaint = false;
            this.isUpdate = false;
        }
        if (this.TypeItem == 3) {
            this.f++;
            if (this.f >= this.eff.mRunFrame.length - 1) {
                this.f = 0;
                this.wantdestroy = true;
                return;
            }
            return;
        }
        if ((GameCanvas.lowGraphic || !this.isUpdate || LoadMap.isShowEffAuto == LoadMap.EFF_PHOBANG_END) && !GameScreen.infoGame.isMapchienthanh()) {
            return;
        }
        if (this.TypeItem == 2 && this.timelive - mSystem.currentTimeMillis() < 0) {
            this.wantdestroy = true;
        }
        if (this.IDImage == 51) {
            this.f++;
            if (this.f >= this.eff.mRunFrame.length - 1) {
                this.f = this.eff.mRunFrame.length - 1;
            }
        } else if (this.f >= this.eff.mRunFrame.length - 1) {
            switch (this.typeEffect) {
                case 0:
                    this.nCountReplay++;
                    this.isPaint = false;
                    if (this.nCountReplay >= this.valueEffect) {
                        this.nCountReplay = 0;
                        this.isPaint = true;
                        this.f = 0;
                        break;
                    }
                    break;
                case 1:
                    this.f = 0;
                    break;
                case 2:
                    this.isPaint = false;
                    if (GameCanvas.gameTick % 5 == 0 && (GameCanvas.timeNow - this.timeBegin) / 1000 > this.valueEffect) {
                        this.timeBegin = GameCanvas.timeNow;
                        this.f = 0;
                        this.isPaint = true;
                        break;
                    }
                    break;
                case 3:
                    if (this.Loop > 0 && CRes.random(this.valueEffect) == 0) {
                        this.Loop = (short) (this.Loop - 1);
                        this.f = 0;
                        break;
                    } else if (this.Loop <= 0) {
                        this.wantdestroy = true;
                        this.isPaint = false;
                        this.isUpdate = false;
                        break;
                    }
                    break;
                case 4:
                    if (CRes.random(this.valueEffect) == 0) {
                        this.f = 0;
                        if (this.indexSound >= 0 && CRes.random(this.timePlaySound) == 0 && isInScreen()) {
                            mSound.playSound(this.indexSound, mSound.volumeSound);
                            break;
                        }
                    }
                    break;
            }
        } else {
            this.f++;
        }
        setBeginSound();
    }

    private void setBeginSound() {
        if (this.indexSound == 48) {
            if (CRes.random(this.timePlaySound) == 0 && isInScreen()) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                return;
            }
            return;
        }
        if (this.indexSound == 49) {
            if (this.f == 35 || this.f == 43) {
                if (isInScreen()) {
                    mSound.playSound(49, mSound.volumeSound);
                    return;
                }
                return;
            } else {
                if (this.f == 125 && isInScreen()) {
                    mSound.playSound(50, mSound.volumeSound);
                    return;
                }
                return;
            }
        }
        if (this.indexSound == 52) {
            if (this.f == 50 && isInScreen()) {
                mSound.playSound(52, mSound.volumeSound);
                return;
            }
            return;
        }
        if (this.indexSound == 54) {
            if (this.f % 5 == 0 && isInScreen() && (GameCanvas.timeNow - timeSoundPHUNNUOC) / 100 > 48) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                timeSoundPHUNNUOC = GameCanvas.timeNow;
                return;
            }
            return;
        }
        if (this.indexSound == 53) {
            if (this.f % 5 == 0 && isInScreen() && (GameCanvas.timeNow - timeSoundFIRE) / 100 > 68 && GameCanvas.currentScreen == GameCanvas.game) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                timeSoundFIRE = GameCanvas.timeNow;
                return;
            }
            return;
        }
        if (this.indexSound == 55) {
            if (this.f % 5 == 0 && isInScreen() && (GameCanvas.timeNow - timeSoundWATER) / 100 > 28) {
                mSound.playSound(this.indexSound, mSound.volumeSound);
                timeSoundWATER = GameCanvas.timeNow;
                return;
            }
            return;
        }
        if (this.indexSound == 50) {
            if (this.f == 30 && isInScreen()) {
                mSound.playSound(50, mSound.volumeSound);
                return;
            }
            return;
        }
        if (this.indexSound == 56 && this.f % 5 == 0 && isInScreen() && (GameCanvas.timeNow - timeSoundGIOTNUOC) / 100 > 48) {
            mSound.playSound(this.indexSound, mSound.volumeSound);
            timeSoundGIOTNUOC = GameCanvas.timeNow;
        }
    }

    public MainEffectAuto loadTemEff(int i, byte[] bArr) {
        MainEffectAuto mainEffectAuto = (MainEffectAuto) MainEffectAuto.hashTemEffAuto.get(new StringBuffer().append("").append(i).toString());
        if (mainEffectAuto == null) {
            mainEffectAuto = new MainEffectAuto(i, bArr);
            MainEffectAuto.hashTemEffAuto.put(new StringBuffer().append(i).append("").toString(), mainEffectAuto);
        }
        return mainEffectAuto;
    }

    public static mImage getEffectAutoImgFromRMS(int i) {
        mImage mimage = new mImage();
        lastTime = GameCanvas.timeNow;
        GlobalService.gI().load_image_data_part_char((byte) 111, (short) i);
        return mimage;
    }

    public void setPlaySound() {
        switch (this.IDImage) {
            case 0:
            case 15:
            case 17:
            case 18:
            case 30:
            case 32:
                this.indexSound = 53;
                this.timePlaySound = 2;
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 10:
            case 13:
            case 14:
            case 16:
            case 21:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            default:
                return;
            case 6:
                this.indexSound = 50;
                this.timePlaySound = 2;
                return;
            case 7:
                this.indexSound = 48;
                this.timePlaySound = 200;
                return;
            case 9:
            case 12:
                this.indexSound = 52;
                this.timePlaySound = 2;
                return;
            case 11:
                this.indexSound = 49;
                this.timePlaySound = 2;
                return;
            case 19:
            case 20:
            case 31:
                this.indexSound = 55;
                this.timePlaySound = 2;
                return;
            case 22:
                this.indexSound = 56;
                this.timePlaySound = 2;
                return;
            case 28:
                this.indexSound = 54;
                this.timePlaySound = 2;
                return;
            case 29:
                this.indexSound = 47;
                this.timePlaySound = 3;
                return;
        }
    }

    public void setidActor(int i) {
        this.idActor = (short) i;
    }

    public static void SetRemove() {
        Enumeration keys = ALL_DATA_EFF_AUTO.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            if ((GameCanvas.timeNow - ((DataEffAuto) ALL_DATA_EFF_AUTO.get(str)).timeremove) / 1000 > 60) {
                ALL_DATA_EFF_AUTO.remove(str);
            }
        }
    }
}
