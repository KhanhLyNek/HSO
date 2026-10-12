package Model;

import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import GameEffect.EffectSkill;
import GameObjects.ObjectData;
import Main.ImageEffect;

/* loaded from: KnightOnline_v403.jar:Model/FrameImage.class */
public class FrameImage {
    public int frameWidth;
    public int frameHeight;
    public int nFrame;
    public mImage imgFrame;
    public int Id;
    public int numWidth;
    public int numHeight;
    public static mHashtable HashFrameImageMount = new mHashtable();
    boolean isNgua;

    public FrameImage(mImage mimage, int i, int i2) {
        this.Id = -1;
        this.isNgua = false;
        if (mimage != null) {
            this.imgFrame = mimage;
            this.frameWidth = i;
            this.frameHeight = i2;
            this.nFrame = mImage.getImageHeight(mimage.image) / i2;
        }
    }

    public void clearData() {
        if (this.imgFrame != null) {
            this.imgFrame.clearData();
        }
    }

    public int getNFrame() {
        return this.nFrame;
    }

    public FrameImage(int i) {
        this.Id = -1;
        this.isNgua = false;
        this.Id = i;
        this.frameWidth = EffectSkill.arrInfoEff[i][0];
        this.frameHeight = EffectSkill.arrInfoEff[i][1];
        this.nFrame = EffectSkill.arrInfoEff[i][2];
    }

    public static FrameImage init(String str, int i, int i2) {
        return new FrameImage(FilePack.getImg(str), i, i2);
    }

    public FrameImage(mImage mimage, int i, int i2, int i3) {
        this.Id = -1;
        this.isNgua = false;
        this.numWidth = i;
        this.numHeight = i2;
        this.frameWidth = mImage.getImageWidth(mimage.image) / i;
        this.frameHeight = mImage.getImageHeight(mimage.image) / i2;
        this.nFrame = (i * i2) - i3;
    }

    public static FrameImage getFrameImageMount(short s, int i, int i2, int i3) {
        FrameImage frameImage = (FrameImage) HashFrameImageMount.get(new StringBuffer().append("").append((int) s).toString());
        if (frameImage == null) {
            frameImage = new FrameImage(s, i, i2, i3);
            HashFrameImageMount.put(new StringBuffer().append("").append((int) s).toString(), frameImage);
        } else if (frameImage.imgFrame == null) {
            MainImage imageMount = ObjectData.getImageMount(s);
            if (imageMount != null && imageMount.img != null) {
                frameImage.imgFrame = imageMount.img;
                frameImage.numWidth = i;
                frameImage.numHeight = i2;
                frameImage.frameWidth = mImage.getImageWidth(frameImage.imgFrame.image) / i;
                frameImage.frameHeight = mImage.getImageHeight(frameImage.imgFrame.image) / i2;
                if (frameImage.imgFrame != null || frameImage.imgFrame.image != null) {
                    frameImage.nFrame = (i * i2) - i3;
                }
            }
        } else {
            frameImage.numWidth = i;
            frameImage.numHeight = i2;
            frameImage.frameWidth = mImage.getImageWidth(frameImage.imgFrame.image) / i;
            frameImage.frameHeight = mImage.getImageHeight(frameImage.imgFrame.image) / i2;
            if (frameImage.imgFrame != null || frameImage.imgFrame.image != null) {
                frameImage.nFrame = (i * i2) - i3;
            }
        }
        return frameImage;
    }

    public FrameImage(int i, int i2, int i3, int i4, int i5) {
        this.Id = -1;
        this.isNgua = false;
        this.Id = i;
        this.isNgua = true;
        this.numWidth = i2;
        this.numHeight = i3;
        MainImage mainImage = new MainImage(mImage.createImage("/vantieu/bo.png"));
        if (mainImage != null && mainImage.img != null) {
            this.imgFrame = mainImage.img;
            this.frameWidth = mImage.getImageWidth(this.imgFrame.image) / i2;
            this.frameHeight = mImage.getImageHeight(this.imgFrame.image) / i3;
            if (this.imgFrame != null || this.imgFrame.image != null) {
                this.nFrame = (i2 * i3) - i4;
            }
        }
    }

    public FrameImage(int i, int i2, int i3, int i4) {
        this.Id = -1;
        this.isNgua = false;
        this.Id = i;
        this.isNgua = true;
        this.numWidth = i2;
        this.numHeight = i3;
        MainImage imageMount = ObjectData.getImageMount((short) i);
        if (imageMount != null && imageMount.img != null) {
            this.imgFrame = imageMount.img;
            this.frameWidth = mImage.getImageWidth(this.imgFrame.image) / i2;
            this.frameHeight = mImage.getImageHeight(this.imgFrame.image) / i3;
            if (this.imgFrame != null || this.imgFrame.image != null) {
                this.nFrame = (i2 * i3) - i4;
            }
        }
    }

    public void drawFrameNew(int i, int i2, int i3, int i4, int i5, mGraphics mgraphics) {
        if (i >= 0 && i < this.nFrame) {
            try {
                int i6 = i * this.frameHeight;
                mgraphics.drawRegion(this.imgFrame, (i / this.numHeight) * this.frameWidth, (i % this.numHeight) * this.frameHeight, this.frameWidth, this.frameHeight, i4, i2, i3, i5, true);
            } catch (Exception e) {
            }
        }
    }

    public void drawFrame(int i, int i2, int i3, int i4, int i5, mGraphics mgraphics) {
        if (this.imgFrame == null) {
            mImage image = ImageEffect.setImage(this.Id);
            if (image != null) {
                this.imgFrame = image;
            }
            if (image.image != null) {
                this.nFrame = mImage.getImageHeight(this.imgFrame.image) / this.frameHeight;
                return;
            }
            return;
        }
        if (i >= 0 && i < this.nFrame && this.imgFrame != null && this.imgFrame.image != null) {
            mgraphics.drawRegion(this.imgFrame, 0, i * this.frameHeight, this.frameWidth, this.frameHeight, i4, i2, i3, i5, true);
        }
    }

    public void drawFrameEffectSkill(int i, int i2, int i3, int i4, int i5, mGraphics mgraphics) {
        mImage image = ImageEffect.setImage(this.Id);
        if (image != null && image.image != null) {
            if (i > this.nFrame) {
                i = this.nFrame;
            }
            int i6 = i * this.frameHeight;
            if (i6 > this.frameHeight * (this.nFrame - 1) || i6 < 0) {
                i6 = this.frameHeight * (this.nFrame - 1);
            }
            mgraphics.drawRegion(image, 0, i6, this.frameWidth, this.frameHeight, i4, i2, i3, i5, true);
        }
    }

    public void drawFrameEffectSkill1(int i, int i2, int i3, int i4, mGraphics mgraphics) {
        mImage image = ImageEffect.setImage(this.Id);
        if (image != null && image.image != null) {
            if (i > this.nFrame) {
                i = this.nFrame;
            }
            int i5 = i * this.frameHeight;
            if (i5 > this.frameHeight * (this.nFrame - 1) || i5 < 0) {
                i5 = this.frameHeight * (this.nFrame - 1);
            }
            mgraphics.drawRegion(image, 0, i5, this.frameWidth, this.frameHeight, i4, i2, i3, 0, true);
        }
    }

    public void drawFrame(int i, int i2, int i3, int i4, mGraphics mgraphics) {
        if (this.imgFrame != null) {
            if (i >= 0 && i < this.nFrame && this.imgFrame.image != null) {
                mgraphics.drawRegion(this.imgFrame, 0, i * this.frameHeight, this.frameWidth, this.frameHeight, i4, i2, i3, 0, true);
                return;
            }
            return;
        }
        mImage image = ImageEffect.setImage(this.Id);
        if (image != null) {
            this.imgFrame = image;
        }
        if (this.imgFrame.image != null) {
            this.nFrame = mImage.getImageHeight(this.imgFrame.image) / this.frameHeight;
        }
    }

    public void drawFrameXY(int i, int i2, int i3, int i4, mGraphics mgraphics) {
        if (this.imgFrame != null) {
            if (i >= 0 && i < this.nFrame && this.imgFrame.image != null) {
                mgraphics.drawRegion(this.imgFrame, i * this.frameWidth, i2 * this.frameHeight, this.frameWidth, this.frameHeight, 0, i3, i4, 0, true);
                return;
            }
            return;
        }
        mImage image = ImageEffect.setImage(this.Id);
        if (image != null) {
            this.imgFrame = image;
        }
        if (this.imgFrame.image != null) {
            this.nFrame = mImage.getImageHeight(this.imgFrame.image) / this.frameHeight;
        }
    }
}
