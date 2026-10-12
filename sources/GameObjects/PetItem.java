package GameObjects;

import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;

/* loaded from: KnightOnline_v403.jar:GameObjects/PetItem.class */
public class PetItem extends Item {
    public byte petImageId;
    public byte nFrameImgPet;
    public int type;
    public short experience;
    public int age;
    public int frame;
    public short growpoint;
    public short maxgrow;
    public short spirit;
    public short health;
    public short agility;
    public short strength;
    public short maxtiemnang;
    public MainInfoItem petAttack;
    public short[] mvaluetiemnang;
    public boolean isPetTool;

    public PetItem() {
    }

    public PetItem(int i, String str, int i2, int i3, int i4, int i5, MainInfoItem[] mainInfoItemArr, int i6, short s, int i7, byte b, byte b2) {
        this.itemNameExcludeLv = str;
        this.itemName = str;
        this.imageId = i2;
        this.Id = i;
        this.tier = (byte) 0;
        if (this.tier > 0) {
            this.itemName = new StringBuffer().append(this.itemName).append(" +").append((int) this.tier).toString();
        }
        this.colorNameItem = i3;
        this.classcharItem = i4;
        this.ItemCatagory = i5;
        this.mInfo = mainInfoItemArr;
        this.type_Only_Item = i6;
        this.IdTem = (short) -1;
        this.priceItem = 0L;
        this.LvItem = s;
        this.canSell = (byte) 0;
        this.canTrade = (byte) 0;
        this.timeUse = 0;
        if (this.timeUse > 0) {
            this.timeDem = GameCanvas.timeNow;
        } else {
            this.timeDem = 0L;
        }
        this.priceType = (byte) 0;
        this.type = i7;
        if (((mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) b2).toString())) != null) {
            this.isPetTool = true;
        }
        this.nFrameImgPet = b;
        this.petImageId = b2;
        if ((this.mInfo != null && this.mInfo.length > 0) || this.priceItem > 0 || this.timeDem > 0) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                CRes.selectionSort(mainInfoItemArr);
            }
            setContentItem();
        }
        this.IndexSort = (byte) 10;
        if (this.mInfo != null && this.mInfo.length > 0) {
            for (int i8 = 0; i8 < this.mInfo.length; i8++) {
                MainInfoItem mainInfoItem = this.mInfo[i8];
                if (mainInfoItem.id <= 6 && mainInfoItem.value > 0) {
                    this.petAttack = mainInfoItem;
                }
            }
        }
    }

    public void setInfoPetOld(int i, String str, int i2, int i3, int i4, int i5, MainInfoItem[] mainInfoItemArr, int i6, short s, int i7, byte b, byte b2) {
        this.itemNameExcludeLv = str;
        this.itemName = str;
        this.imageId = i2;
        this.Id = i;
        this.tier = (byte) 0;
        if (this.tier > 0) {
            this.itemName = new StringBuffer().append(this.itemName).append(" +").append((int) this.tier).toString();
        }
        this.colorNameItem = i3;
        this.classcharItem = i4;
        this.ItemCatagory = i5;
        this.mInfo = mainInfoItemArr;
        this.type_Only_Item = i6;
        this.IdTem = (short) -1;
        this.priceItem = 0L;
        this.LvItem = s;
        this.canSell = (byte) 0;
        this.canTrade = (byte) 0;
        this.timeUse = 0;
        if (this.timeUse > 0) {
            this.timeDem = GameCanvas.timeNow;
        } else {
            this.timeDem = 0L;
        }
        this.priceType = (byte) 0;
        this.type = i7;
        this.nFrameImgPet = b;
        this.petImageId = b2;
        if ((this.mInfo != null && this.mInfo.length > 0) || this.priceItem > 0 || this.timeDem > 0) {
            if (this.mInfo != null && this.mInfo.length > 0) {
                CRes.selectionSort(mainInfoItemArr);
            }
            setContentItem();
        }
        this.IndexSort = (byte) 10;
        if (this.mInfo != null && this.mInfo.length > 0) {
            for (int i8 = 0; i8 < this.mInfo.length; i8++) {
                MainInfoItem mainInfoItem = this.mInfo[i8];
                if (mainInfoItem.id <= 6 && mainInfoItem.value > 0) {
                    this.petAttack = mainInfoItem;
                }
            }
        }
    }

    public void setTimeUser(int i, long j) {
        this.timeDefaultItemFashion = i;
        this.currentTimeItemFashion = j;
    }

    public void setInfoPet(int i, short s, short s2, short s3, short s4, short s5, short s6, short s7, short s8) {
        this.age = i;
        this.growpoint = s;
        this.maxgrow = s6;
        this.strength = s2;
        this.agility = s3;
        this.health = s4;
        this.spirit = s5;
        this.maxtiemnang = s7;
        this.experience = s8;
        this.mvaluetiemnang = new short[4];
        this.mvaluetiemnang[0] = this.strength;
        this.mvaluetiemnang[1] = this.agility;
        this.mvaluetiemnang[2] = this.health;
        this.mvaluetiemnang[3] = this.spirit;
    }

    @Override // GameObjects.Item
    public void paintItem(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        super.paintItem(mgraphics, i, i2, i3, i4, i5);
    }

    public void paintShowPet(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5, int i6) {
        MainImage imagePet = ObjectData.getImagePet(this.petImageId);
        if (this.isPetTool) {
            if (imagePet.img != null) {
                int i7 = 0;
                if (isFly()) {
                    i7 = 15;
                }
                mgraphics.drawImage(MainObject.shadow, i, i2 + 10, 3, true);
                paintPetTool(mgraphics, imagePet, i, i2 + i7);
                doChangeFrmaeBoss();
                return;
            }
            return;
        }
        if (this.type != 2) {
            mgraphics.drawImage(MainObject.shadow, i, i2 + 10, 3, true);
            i2 -= 10;
        }
        if (imagePet.img != null) {
            int imageHeight = mImage.getImageHeight(imagePet.img.image) / this.nFrameImgPet;
            int imageWidth = mImage.getImageWidth(imagePet.img.image) / 2;
            mgraphics.drawRegion(imagePet.img, imageWidth * (getframe(i6) / 3), imageHeight * (getframe(i6) % 3), imageWidth, imageHeight, 0, i, i2 + i4, 33, true);
        }
    }

    public void doChangeFrmaeBoss() {
        mVector mvector = new mVector();
        mVector mvector2 = (mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) this.petImageId).toString());
        if (mvector2 != null) {
            mvector = mvector2;
        }
        if (mvector.size() > 0) {
            this.frame = (byte) ((this.frame + 1) % ((DataEffect) mvector.elementAt(0)).getAnim(0, 0).frame.length);
        }
    }

    public void paintPetTool(mGraphics mgraphics, MainImage mainImage, int i, int i2) {
        mVector mvector = new mVector();
        mVector mvector2 = (mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) this.petImageId).toString());
        if (mvector2 != null) {
            mvector = mvector2;
        }
        if (mvector.size() == 0) {
            return;
        }
        try {
            DataEffect dataEffect = (DataEffect) mvector.elementAt(0);
            if (dataEffect != null && ((mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) this.petImageId).toString())) != null && mainImage != null && mainImage.img != null) {
                dataEffect.paintPet(mgraphics, dataEffect.getFrame(this.frame, 0, 0), i, i2 - 0, 0, mainImage.img);
            }
        } catch (Exception e) {
        }
    }

    public int getframe(int i) {
        switch (this.type) {
            case 0:
                return Pet.mOwl[i][0][GameCanvas.gameTick % Pet.mOwl[i][0].length];
            case 1:
                return Pet.mBat[i][0][GameCanvas.gameTick % Pet.mBat[i][0].length];
            case 2:
                return Pet.mWoftAnimFrame[i][0][GameCanvas.gameTick % Pet.mWoftAnimFrame[i][0].length];
            case 3:
                return Pet.mEagle[i][0][GameCanvas.gameTick % Pet.mEagle[i][0].length];
            default:
                return Pet.mElfAnimFrame[i][0][GameCanvas.gameTick % Pet.mElfAnimFrame[i][0].length];
        }
    }

    public boolean isFly() {
        mVector mvector = new mVector();
        mVector mvector2 = (mVector) Pet.PET_DATA.get(new StringBuffer().append("").append((int) this.petImageId).toString());
        if (mvector2 != null) {
            mvector = mvector2;
        }
        DataEffect dataEffect = (DataEffect) mvector.elementAt(0);
        if (dataEffect != null && dataEffect.isFly <= -5) {
            return true;
        }
        return false;
    }
}
