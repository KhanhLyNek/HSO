package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectSkill;
import Main.GameCanvas;
import Model.CRes;
import java.io.DataInputStream;
import java.util.Enumeration;

/* loaded from: KnightOnline_v403.jar:GameObjects/DataSkillEff.class */
public class DataSkillEff {
    public boolean isMatna;
    public mVector listFrame;
    public mVector listAnima;
    public SmallImage[] smallImage;
    public byte[][] frameChar;
    public byte[] sequence;
    public byte Frame;
    public byte f;
    public boolean wantDetroy;
    public short x;
    public short y;
    public long timeRemove;
    public byte indexStartSkill;
    int idEff;
    int dxx;
    int dyy;
    public int typRequestImg;
    public byte Typemove;
    public boolean isremovebyTime;
    public boolean isremovebyFrame;
    private boolean isLoadData;
    int transform;
    mImage img_pp;
    public static final int TRANS_MIRROR = 2;
    public static final int TRANS_MIRROR_ROT180 = 1;
    public static final int TRANS_MIRROR_ROT270 = 4;
    public static final int TRANS_MIRROR_ROT90 = 7;
    public static final int TRANS_NONE = 0;
    public static final int TRANS_ROT180 = 3;
    public static final int TRANS_ROT270 = 6;
    public static final int TRANS_ROT90 = 5;
    private byte loop;
    private long lasttime;
    public long timelive;
    public static byte TYPE_EFFECT_END = 0;
    public static byte TYPE_EFFECT_STARTSKILL = 1;
    public static byte TYPE_EFFECT_BUFF = 2;
    public static mHashtable ALL_DATA_EFFECT = new mHashtable();

    /* JADX WARN: Type inference failed for: r1v3, types: [byte[], byte[][]] */
    public DataSkillEff(byte[] bArr, short s, int i, int i2) {
        this.listFrame = new mVector();
        this.listAnima = new mVector();
        this.frameChar = new byte[]{new byte[]{0}, new byte[]{0}, new byte[]{0}, new byte[]{0}};
        this.idEff = 0;
        this.typRequestImg = EffectSkill.EFF_KIEM_NHO_TROI;
        this.idEff = s;
        this.dxx = i;
        this.dyy = i2;
        loadData(bArr);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [byte[], byte[][]] */
    public DataSkillEff(byte[] bArr, short s, int i, int i2, long j, byte b) {
        this.listFrame = new mVector();
        this.listAnima = new mVector();
        this.frameChar = new byte[]{new byte[]{0}, new byte[]{0}, new byte[]{0}, new byte[]{0}};
        this.idEff = 0;
        this.typRequestImg = EffectSkill.EFF_KIEM_NHO_TROI;
        this.idEff = s;
        this.dxx = i;
        this.dyy = i2;
        this.Typemove = b;
        this.timelive = j;
        this.isremovebyTime = true;
        loadData(bArr);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [byte[], byte[][]] */
    public DataSkillEff(short s, int i, int i2, byte[] bArr) {
        this.listFrame = new mVector();
        this.listAnima = new mVector();
        this.frameChar = new byte[]{new byte[]{0}, new byte[]{0}, new byte[]{0}, new byte[]{0}};
        this.idEff = 0;
        this.typRequestImg = EffectSkill.EFF_KIEM_NHO_TROI;
        this.idEff = s;
        this.x = (short) i;
        this.y = (short) i2;
        loadData(bArr);
        this.isremovebyFrame = true;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [byte[], byte[][]] */
    public DataSkillEff(int i) {
        this.listFrame = new mVector();
        this.listAnima = new mVector();
        this.frameChar = new byte[]{new byte[]{0}, new byte[]{0}, new byte[]{0}, new byte[]{0}};
        this.idEff = 0;
        this.typRequestImg = EffectSkill.EFF_KIEM_NHO_TROI;
        this.idEff = i;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [byte[], byte[][]] */
    public DataSkillEff(int i, int i2) {
        this.listFrame = new mVector();
        this.listAnima = new mVector();
        this.frameChar = new byte[]{new byte[]{0}, new byte[]{0}, new byte[]{0}, new byte[]{0}};
        this.idEff = 0;
        this.typRequestImg = EffectSkill.EFF_KIEM_NHO_TROI;
        this.idEff = i;
        try {
            DataInputStream dataInputStream = new DataInputStream(mImage.openFile(new StringBuffer().append("/x1/wp/e").append(i).toString()));
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            loadData(bArr);
            this.img_pp = mImage.createImage(new StringBuffer().append("/wp/e").append(i).append(".png").toString());
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("loi load pp ").append(e.toString()).toString());
        }
        this.transform = i2;
    }

    public boolean isHavedata() {
        if (this.isLoadData) {
            return true;
        }
        loadData(null);
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0006, code lost:
    
        if (r11.length == 0) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void loadData(byte[] r11) {
        /*
            Method dump skipped, instructions count: 585
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: GameObjects.DataSkillEff.loadData(byte[]):void");
    }

    public static int[] getTransform(int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i3;
        int i7 = i4;
        if (i5 == 0) {
            return new int[]{i2, i3, i4};
        }
        if (i2 == 0) {
            i5 = 2;
            i6 = i3;
            i7 = i4;
        } else if (i2 == 2) {
            i5 = 0;
            i6 = -i3;
        } else if (i2 == 1) {
            i5 = 3;
            i7 = -i4;
        } else if (i2 == 4) {
            i5 = 5;
            i6 = -i3;
            i7 = -i4;
        } else if (i2 == 7) {
            i5 = 2;
            i6 = i3;
            i7 = i4;
        } else if (i2 == 3) {
            i5 = 5;
            i7 = -i4;
            i6 = -i3;
        } else if (i2 == 6) {
            i5 = 0;
            i6 = -i3;
        } else if (i2 == 5) {
            i5 = 3;
            i7 = -i4;
        }
        return new int[]{i5, i6, i7};
    }

    public static int getWidthRotate(int i, int i2, int i3) {
        switch (i) {
            case 0:
                return i2;
            case 1:
                return i2;
            case 2:
                return i2;
            case 3:
                return i2;
            case 4:
                return i3;
            case 5:
                return i3;
            case 6:
                return i3;
            case 7:
                return i3;
            default:
                return i2;
        }
    }

    public static int getHeightRotate(int i, int i2, int i3) {
        switch (i) {
            case 0:
                return i3;
            case 1:
                return i3;
            case 2:
                return i3;
            case 3:
                return i3;
            case 4:
                return i2;
            case 5:
                return i2;
            case 6:
                return i2;
            case 7:
                return i2;
            default:
                return i3;
        }
    }

    public void paintTopArrow(mGraphics mgraphics, int i, int i2) {
        if (isHavedata() && this.Frame < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff) this.listFrame.elementAt(this.Frame);
            try {
                mVector mvector = frameEff.listPartTop;
                mVector mvector2 = frameEff.listPartBottom;
                for (int i3 = 0; i3 < mvector2.size(); i3++) {
                    PartFrame partFrame = (PartFrame) mvector2.elementAt(i3);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    if (this.img_pp != null) {
                        short s = partFrame.dx;
                        short s2 = smallImage.w;
                        short s3 = smallImage.h;
                        short s4 = smallImage.x;
                        short s5 = smallImage.y;
                        int[] transform = getTransform(partFrame.flip, this.transform, partFrame.dx, partFrame.dy);
                        int widthRotate = getWidthRotate(transform[0], smallImage.w, smallImage.h);
                        int heightRotate = getHeightRotate(transform[0], smallImage.w, smallImage.h);
                        int imageWidth = mImage.getImageWidth(this.img_pp.image);
                        int imageHeight = mImage.getImageHeight(this.img_pp.image);
                        if (s4 > imageWidth) {
                            s4 = 0;
                        }
                        if (s5 > imageHeight) {
                            s5 = 0;
                        }
                        if (s4 + widthRotate > imageWidth) {
                            widthRotate = imageWidth - s4;
                        }
                        if (s5 + heightRotate > imageHeight) {
                            heightRotate = imageHeight - s5;
                        }
                        mgraphics.drawRegion(this.img_pp, s4, s5, widthRotate, heightRotate, transform[0], i + transform[1] + this.dxx, i2 + transform[2] + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTop(mGraphics mgraphics, int i, int i2) {
        if (isHavedata() && this.Frame < this.listFrame.size()) {
            FrameEff frameEff = (FrameEff) this.listFrame.elementAt(this.Frame);
            try {
                mVector mvector = frameEff.listPartTop;
                mVector mvector2 = frameEff.listPartBottom;
                for (int i3 = 0; i3 < mvector2.size(); i3++) {
                    PartFrame partFrame = (PartFrame) mvector2.elementAt(i3);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        short s = partFrame.dx;
                        int i4 = smallImage.w;
                        int i5 = smallImage.h;
                        short s2 = smallImage.x;
                        short s3 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s2 > imageWidth) {
                            s2 = 0;
                        }
                        if (s3 > imageHeight) {
                            s3 = 0;
                        }
                        if (s2 + i4 > imageWidth) {
                            i4 = imageWidth - s2;
                        }
                        if (s3 + i5 > imageHeight) {
                            i5 = imageHeight - s3;
                        }
                        mgraphics.drawRegion(imgIcon.img, s2, s3, i4, i5, partFrame.flip == 1 ? 2 : 0, i + s + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomArrow(mGraphics mgraphics, int i, int i2) {
        if (isHavedata() && this.Frame < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(this.Frame)).listPartTop;
                for (int i3 = 0; i3 < mvector.size(); i3++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i3);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (this.img_pp != null) {
                        short s = partFrame.dx;
                        short s2 = smallImage.w;
                        short s3 = smallImage.h;
                        short s4 = smallImage.x;
                        short s5 = smallImage.y;
                        int[] transform = getTransform(partFrame.flip, this.transform, partFrame.dx, partFrame.dy);
                        int widthRotate = getWidthRotate(transform[0], smallImage.w, smallImage.h);
                        int heightRotate = getHeightRotate(transform[0], smallImage.w, smallImage.h);
                        int imageWidth = mImage.getImageWidth(this.img_pp.image);
                        int imageHeight = mImage.getImageHeight(this.img_pp.image);
                        if (s4 > imageWidth) {
                            s4 = 0;
                        }
                        if (s5 > imageHeight) {
                            s5 = 0;
                        }
                        if (s4 + widthRotate > imageWidth) {
                            widthRotate = imageWidth - s4;
                        }
                        if (s5 + heightRotate > imageHeight) {
                            heightRotate = imageHeight - s5;
                        }
                        mgraphics.drawRegion(this.img_pp, s4, s5, widthRotate, heightRotate, transform[0], i + transform[1] + this.dxx, i2 + transform[2] + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottom(mGraphics mgraphics, int i, int i2) {
        if (isHavedata() && this.Frame < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(this.Frame)).listPartTop;
                for (int i3 = 0; i3 < mvector.size(); i3++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i3);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        short s = partFrame.dx;
                        int i4 = smallImage.w;
                        int i5 = smallImage.h;
                        short s2 = smallImage.x;
                        short s3 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s2 > imageWidth) {
                            s2 = 0;
                        }
                        if (s3 > imageHeight) {
                            s3 = 0;
                        }
                        if (s2 + i4 > imageWidth) {
                            i4 = imageWidth - s2;
                        }
                        if (s3 + i5 > imageHeight) {
                            i5 = imageHeight - s3;
                        }
                        mgraphics.drawRegion(imgIcon.img, s2, s3, i4, i5, partFrame.flip == 1 ? 2 : 0, i + s + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopWeaPon(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopHorse(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopPP(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTop(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopAll(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopHair(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopName(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopWing(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartTop;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomWeaPon(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomHorse(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomPP(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottom(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomAll(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomHair(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomName(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomWing(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (isHavedata() && i3 < this.listFrame.size()) {
            try {
                mVector mvector = ((FrameEff) this.listFrame.elementAt(i3)).listPartBottom;
                for (int i5 = 0; i5 < mvector.size(); i5++) {
                    PartFrame partFrame = (PartFrame) mvector.elementAt(i5);
                    SmallImage smallImage = this.smallImage[partFrame.idSmallImg];
                    ImageIcon imgIcon = GameData.getImgIcon((short) (this.idEff + GameData.ID_START_SKILL), this.typRequestImg);
                    if (imgIcon != null && imgIcon.img != null) {
                        int i6 = partFrame.dx;
                        int i7 = smallImage.w;
                        int i8 = smallImage.h;
                        short s = smallImage.x;
                        short s2 = smallImage.y;
                        int imageWidth = mImage.getImageWidth(imgIcon.img.image);
                        int imageHeight = mImage.getImageHeight(imgIcon.img.image);
                        if (s > imageWidth) {
                            s = 0;
                        }
                        if (s2 > imageHeight) {
                            s2 = 0;
                        }
                        if (s + i7 > imageWidth) {
                            i7 = imageWidth - s;
                        }
                        if (s2 + i8 > imageHeight) {
                            i8 = imageHeight - s2;
                        }
                        int i9 = partFrame.flip == 1 ? 2 : 0;
                        if (i4 == 2 || i4 == 6) {
                            if (i9 == 2) {
                                i9 = 0;
                            } else {
                                i9 = 2;
                            }
                            i6 = -(i6 + i7);
                        }
                        mgraphics.drawRegion(imgIcon.img, s, s2, i7, i8, i9, i + i6 + this.dxx, i2 + partFrame.dy + this.dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean lockmoveByeffAuto() {
        return this.Typemove == 1;
    }

    public boolean CanpaintByeffauto() {
        return this.Typemove == 2;
    }

    public boolean isTanghinhbyEffauto() {
        return this.Typemove == 17;
    }

    public void updateArrow() {
        if (!isHavedata()) {
            return;
        }
        this.f = (byte) ((this.f + 1) % this.sequence.length);
        this.Frame = this.sequence[this.f];
    }

    public void update() {
        try {
            if (!isHavedata()) {
                return;
            }
            if (this.isremovebyTime && this.timelive - mSystem.currentTimeMillis() < 0) {
                this.wantDetroy = true;
            }
            this.f = (byte) (this.f + 1);
            if (this.f >= this.sequence.length) {
                if (this.isremovebyFrame) {
                    this.wantDetroy = true;
                }
                if (this.isremovebyTime) {
                    this.f = (byte) 0;
                }
                if (!this.isremovebyTime && mSystem.currentTimeMillis() - this.lasttime > this.loop * ObjectData.MONSTER) {
                    this.f = (byte) 0;
                    this.lasttime = mSystem.currentTimeMillis();
                    this.loop = (byte) CRes.random(1, 8);
                }
            }
            if (this.f > 0 && this.f < this.sequence.length) {
                this.Frame = this.sequence[this.f];
            }
        } catch (Exception e) {
            mSystem.println("loi update me day");
            e.printStackTrace();
        }
    }

    public static void SetRemove() {
        Enumeration keys = ALL_DATA_EFFECT.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            if ((GameCanvas.timeNow - ((EffectData) ALL_DATA_EFFECT.get(str)).timeRemove) / 1000 > 60) {
                ALL_DATA_EFFECT.remove(str);
            }
        }
    }
}
