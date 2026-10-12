package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectSkill;
import GameEffect.Effect_UpLv_Item;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.FrameImage;
import Model.InfocontenNew;
import Model.MainImage;
import Model.MainTemplateItem;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/Item.class */
public class Item {
    public static String[] nameInfoItem;
    public static byte[] colorInfoItem;
    public static byte[] isPercentInfoItem;
    public String itemName;
    public String content;
    public String itemNameExcludeLv;
    public MainInfoItem[] mInfo;
    public String[] mcontent;
    public String[] mPlusContent;
    public int[] mColor;
    public int[] mPlusColor;
    public int timeupdateMore;
    public int timeUse;
    public long priceItem;
    public int imageId;
    public int ItemCatagory;
    public short IdTem;
    public static FrameImage fraeffitemdrop;
    public byte typeMaterial;
    public int numPotion;
    public byte typePotion;
    public static final byte HP = 0;
    public static final byte MP = 1;
    public static final byte COIN = 2;
    public static final byte GOLD = 6;
    public int classcharItem;
    public int Id;
    public int idTab;
    public int sell;
    public int value;
    public byte priceType;
    public int idPart;
    public int type_Only_Item;
    public int LvItem;
    public Item itemClone;
    public static byte[] ATB_Can_Not_Paint;
    public static byte TYPE_NGOC_KHAM = 49;
    public static byte TYPE_BUA_HUYEN_BI = 50;
    public static int ID_TEM_VE_MUA_BAN = 51;
    public static Effect_UpLv_Item eff_UpLv = new Effect_UpLv_Item();
    public static mHashtable VecEquipPlayer = new mHashtable();
    public static mVector VecInvetoryPlayer = new mVector("Item VecInvetoryPlayer");
    public static mVector VecChestPlayer = new mVector("Item VecChestPlayer");
    public static mVector VecPetContainer = new mVector("Item VecPetContainer");
    public static mVector VecClanInvetory = new mVector("Item VecClanInvetory");
    public static mVector VecLotteryReward = new mVector("Item VecLotteryReward");
    public static mVector VecItemSell = new mVector("Item Sell");
    public static mVector VecItem_Sell_in_store = new mVector("Item Sell in store");
    public static mHashtable HashImageItem = new mHashtable();
    public static mHashtable HashImagePotion = new mHashtable();
    public static mHashtable HashImageQuestItem = new mHashtable();
    public static mHashtable HashImageMaterial = new mHashtable();
    public static mHashtable HashImageIconClan = new mHashtable();
    public static mHashtable HashImageIconArcheClan = new mHashtable();
    public static mHashtable HashImagePetIcon = new mHashtable();
    public static mHashtable HashImageMount = new mHashtable();
    public static byte[] frameicon = {0, 1, 2, 1};
    public int sizeW = 60;
    int wImage = 16;
    public byte canSell = 0;
    public byte canTrade = 0;
    public byte tier = 0;
    public byte isLock = 0;
    public byte IndexSort = 0;
    public long timeDem = 0;
    public byte Selling = 0;
    public byte can_sell_for_other_player = 0;
    public int timeDefaultItemFashion = -1;
    public long currentTimeItemFashion = -1;
    public byte CanShell_CanNotTrade = 0;
    public boolean isSell = false;
    public String[] infoHop = {"", ""};
    public mVector moreContenGem = new mVector("EffectSkill moreContenGem");
    public int colorNameItem = 0;
    public String diaHoiEvent = "";
    public byte frame = 0;

    public boolean isOutTime() {
        if ((mSystem.currentTimeMillis() / 1000) - this.currentTimeItemFashion > this.timeDefaultItemFashion) {
            return true;
        }
        return false;
    }

    public boolean isFashionItem() {
        return this.timeDefaultItemFashion != -1;
    }

    public long getTimeItemFashion() {
        return mSystem.currentTimeMillis() - this.currentTimeItemFashion;
    }

    public static boolean isWeapone(int i) {
        return i >= 8 && i <= 11;
    }

    public void addhole(short[] sArr) {
    }

    public static boolean isMaterial_Light_Drak(int i) {
        return i > 22 && i < 33;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v52, types: [int] */
    public void paintItem(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        MainImage mainImage = null;
        if (this.itemName == null) {
            if (setNameNull()) {
                return;
            }
        } else {
            if (this.colorNameItem > 0) {
                int i6 = this.colorNameItem;
                if (this.colorNameItem >= 20 && this.colorNameItem < 30) {
                    i6 = 6;
                } else if (this.colorNameItem >= 30 && this.colorNameItem < 40) {
                    i6 = 7;
                } else if (this.colorNameItem >= 40 && this.colorNameItem < 50) {
                    i6 = 8;
                }
                mgraphics.drawRegion(AvMain.imgColorItem, 0, (i6 - 1) * 32, i3 - 1, i3 - 1, 2, i - ((i3 - 1) / 2), i2 - ((i3 - 1) / 2), 0, true);
            }
            if (this.ItemCatagory == 6) {
                if (this.priceItem <= 0) {
                    mgraphics.drawRegion(AvMain.imgColorItem, 0, 0, i3 - 1, i3 - 1, 2, i - ((i3 - 1) / 2), i2 - ((i3 - 1) / 2), 0, true);
                }
                CRes.getCharPartInfo(5, this.imageId).paintShow(mgraphics, i, i2, 0, 0);
                return;
            }
            if (this.ItemCatagory == 5) {
                mainImage = ObjectData.getImageQuestItem((short) this.imageId);
            } else if (this.ItemCatagory == 4) {
                mainImage = ObjectData.getImagePotion((short) this.imageId);
            } else if (this.ItemCatagory == 3) {
                mainImage = ObjectData.getImageItem((short) this.imageId);
            } else if (this.ItemCatagory == 7) {
                mainImage = ObjectData.getImageMaterial((short) this.imageId);
            } else if (this.ItemCatagory == 8) {
                mainImage = ObjectData.getImageIconClan((short) this.Id);
            } else if (this.ItemCatagory == 9) {
                mainImage = ObjectData.getImageIconPet((short) this.imageId);
            }
            if (this.ItemCatagory != 6) {
                if (mainImage != null && mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length = frameicon.length;
                            if (length == 0) {
                                length = 1;
                            }
                            this.frame = (byte) ((this.frame + 1) % length);
                        }
                        mgraphics.drawRegion(mainImage.img, 0, frameicon[this.frame] * 18, 18, 18, 0, i, i2, 3, true);
                    } else {
                        mgraphics.drawImage(mainImage.img, i, i2, 3, true);
                    }
                } else {
                    mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, i, i2, 3, true);
                }
            }
            if (this.ItemCatagory == 3 && this.tier > 0) {
                byte b = this.tier;
                if (this.type_Only_Item == 7) {
                    b = this.tier / 2;
                }
                eff_UpLv.paintUpgradeEffect(i, i2, b, i3 - 4, mgraphics, i4);
            }
        }
        if (this.numPotion > 1) {
            mFont.number_Yellow_Small.drawString(mgraphics, new StringBuffer().append(this.numPotion).append("").toString(), ((i + (i3 / 2)) - 1) - i5, ((i2 + (i3 / 2)) - 9) - i5, 1, true);
        }
        if (this.isLock > 0) {
            mgraphics.drawImage(AvMain.imgLock, i + 4, i2 + 3, 0, true);
        }
        if (this.ItemCatagory == 3) {
            if (this.timeUse > 0) {
                updateTime();
            }
            if (isFashionItem()) {
                updateFashion();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v52, types: [int] */
    public void paintItem_notnum(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        MainImage mainImage = null;
        if (this.itemName == null) {
            if (setNameNull()) {
                return;
            }
        } else {
            if (this.colorNameItem > 0) {
                int i6 = this.colorNameItem;
                if (this.colorNameItem >= 20 && this.colorNameItem < 30) {
                    i6 = 6;
                } else if (this.colorNameItem >= 30 && this.colorNameItem < 40) {
                    i6 = 7;
                } else if (this.colorNameItem >= 40 && this.colorNameItem < 50) {
                    i6 = 8;
                }
                mgraphics.drawRegion(AvMain.imgColorItem, 0, (i6 - 1) * 32, i3 - 1, i3 - 1, 2, i - ((i3 - 1) / 2), i2 - ((i3 - 1) / 2), 0, true);
            }
            if (this.ItemCatagory == 6) {
                if (this.priceItem <= 0) {
                    mgraphics.drawRegion(AvMain.imgColorItem, 0, 0, i3 - 1, i3 - 1, 2, i - ((i3 - 1) / 2), i2 - ((i3 - 1) / 2), 0, true);
                }
                CRes.getCharPartInfo(5, this.imageId).paintShow(mgraphics, i, i2, 0, 0);
                return;
            }
            if (this.ItemCatagory == 5) {
                mainImage = ObjectData.getImageQuestItem((short) this.imageId);
            } else if (this.ItemCatagory == 4) {
                mainImage = ObjectData.getImagePotion((short) this.imageId);
            } else if (this.ItemCatagory == 3) {
                mainImage = ObjectData.getImageItem((short) this.imageId);
            } else if (this.ItemCatagory == 7) {
                mainImage = ObjectData.getImageMaterial((short) this.imageId);
            } else if (this.ItemCatagory == 8) {
                mainImage = ObjectData.getImageIconClan((short) this.Id);
            } else if (this.ItemCatagory == 9) {
                mainImage = ObjectData.getImageIconPet((short) this.imageId);
            }
            if (this.ItemCatagory != 6) {
                if (mainImage != null && mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length = frameicon.length;
                            if (length == 0) {
                                length = 1;
                            }
                            this.frame = (byte) ((this.frame + 1) % length);
                        }
                        mgraphics.drawRegion(mainImage.img, 0, frameicon[this.frame] * 18, 18, 18, 0, i, i2, 3, true);
                    } else {
                        mgraphics.drawImage(mainImage.img, i, i2, 3, true);
                    }
                } else {
                    mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, i, i2, 3, true);
                }
            }
            if (this.ItemCatagory == 3 && this.tier > 0) {
                byte b = this.tier;
                if (this.type_Only_Item == 7) {
                    b = this.tier / 2;
                }
                eff_UpLv.paintUpgradeEffect(i, i2, b, i3 - 4, mgraphics, i4);
            }
        }
        if (this.isLock > 0) {
            mgraphics.drawImage(AvMain.imgLock, i + 4, i2 + 3, 0, true);
        }
        if (this.ItemCatagory == 3) {
            if (this.timeUse > 0) {
                updateTime();
            }
            if (isFashionItem()) {
                updateFashion();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v52, types: [int] */
    public void paintItemNew(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        MainImage mainImage = null;
        if (this.itemName == null) {
            if (setNameNull()) {
                return;
            }
        } else {
            if (this.colorNameItem > 0) {
                int i6 = this.colorNameItem;
                if (this.colorNameItem >= 20 && this.colorNameItem < 30) {
                    i6 = 6;
                } else if (this.colorNameItem >= 30 && this.colorNameItem < 40) {
                    i6 = 7;
                } else if (this.colorNameItem >= 40 && this.colorNameItem < 50) {
                    i6 = 8;
                }
                mgraphics.drawRegion(AvMain.imgColorItem, 0, (i6 - 1) * 32, i3 - 1, i3 - 1, 2, i - ((i3 - 1) / 2), i2 - ((i3 - 1) / 2), 0, true);
            }
            if (this.ItemCatagory == 6) {
                if (this.priceItem <= 0) {
                    mgraphics.drawRegion(AvMain.imgColorItem, 0, 0, i3 - 1, i3 - 1, 2, i - ((i3 - 1) / 2), i2 - ((i3 - 1) / 2), 0, true);
                }
                CRes.getCharPartInfo(5, this.imageId).paintShow(mgraphics, i, i2, 0, 0);
                return;
            }
            if (this.ItemCatagory == 5) {
                mainImage = ObjectData.getImageQuestItem((short) this.imageId);
            } else if (this.ItemCatagory == 4) {
                mainImage = ObjectData.getImagePotion((short) this.imageId);
            } else if (this.ItemCatagory == 3) {
                mainImage = ObjectData.getImageItem((short) this.imageId);
            } else if (this.ItemCatagory == 7) {
                mainImage = ObjectData.getImageMaterial((short) this.imageId);
            } else if (this.ItemCatagory == 8) {
                mainImage = ObjectData.getImageIconClan((short) this.Id);
            } else if (this.ItemCatagory == 9) {
                mainImage = ObjectData.getImageIconPet((short) this.imageId);
            }
            if (this.ItemCatagory != 6) {
                if (mainImage != null && mainImage.img != null) {
                    if (mImage.getImageHeight(mainImage.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 10 == 0) {
                            int length = frameicon.length;
                            if (length == 0) {
                                length = 1;
                            }
                            this.frame = (byte) ((this.frame + 1) % length);
                        }
                        mgraphics.drawRegion(mainImage.img, 0, frameicon[this.frame] * 18, 18, 18, 0, i, i2, 3, true);
                    } else {
                        mgraphics.drawImage(mainImage.img, i, i2, 3, true);
                    }
                } else {
                    mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, i, i2, 3, true);
                }
            }
            if (this.ItemCatagory == 3 && this.tier > 0) {
                byte b = this.tier;
                if (this.type_Only_Item == 7) {
                    b = this.tier / 2;
                }
                eff_UpLv.paintUpgradeEffect(i, i2, b, i3 - 4, mgraphics, i4);
            }
        }
        if (this.isLock > 0) {
            mgraphics.drawImage(AvMain.imgLock, i + 4, i2 + 3, 0, true);
        }
        if (this.ItemCatagory == 3) {
            if (this.timeUse > 0) {
                updateTime();
            }
            if (isFashionItem()) {
                updateFashion();
            }
        }
    }

    public void updateTime() {
        if ((GameCanvas.timeNow - this.timeDem) / 1000 >= 60 && !isFashionItem()) {
            this.timeDem += 60000;
            this.timeUse--;
            if (this.mPlusContent != null) {
                if (this.timeUse > 0) {
                    if (this.type_Only_Item == 14) {
                        this.mPlusContent[0] = new StringBuffer().append(T.thoigianaptrung).append(PaintInfoGameScreen.getStringTime(this.timeUse)).toString();
                        return;
                    } else {
                        this.mPlusContent[0] = new StringBuffer().append(T.thoigianconlai).append(PaintInfoGameScreen.getStringTime(this.timeUse)).toString();
                        return;
                    }
                }
                this.mPlusContent[0] = "";
            }
        }
    }

    public void updateFashion() {
        if ((GameCanvas.timeNow - this.timeDem) / 1000 >= 60) {
            this.timeDem += 60000;
            int timeItemFashion = (int) (this.timeDefaultItemFashion - (getTimeItemFashion() / 60000));
            if (this.mPlusContent != null) {
                if (timeItemFashion > 0) {
                    if (this.type_Only_Item == 14) {
                        this.mPlusContent[0] = new StringBuffer().append(T.thoigianaptrung).append(PaintInfoGameScreen.getStringTime(timeItemFashion)).toString();
                        return;
                    } else {
                        this.mPlusContent[0] = new StringBuffer().append(T.thoigianconlai).append(PaintInfoGameScreen.getStringTime(timeItemFashion)).toString();
                        return;
                    }
                }
                this.mPlusContent[0] = "";
            }
        }
    }

    public boolean setNameNull() {
        if (this.itemName == null) {
            MainTemplateItem itemTem = MainTemplateItem.getItemTem(this.IdTem);
            if (itemTem != null && itemTem.name != null) {
                setValueItem(itemTem);
                return false;
            }
            MainTemplateItem.getItemTem(this.IdTem);
            GameCanvas.start_Ok_Dialog(T.danglaydulieu);
            return true;
        }
        return false;
    }

    public void setValueItem(MainTemplateItem mainTemplateItem) {
        this.itemName = mainTemplateItem.name;
        this.imageId = mainTemplateItem.IconId;
        this.type_Only_Item = mainTemplateItem.typeItem;
        if (this.mInfo == null) {
            MainInfoItem[] mainInfoItemArr = new MainInfoItem[mainTemplateItem.mValueItem.length];
            for (int i = 0; i < mainTemplateItem.mValueItem.length; i++) {
                mainInfoItemArr[i] = new MainInfoItem(i, mainTemplateItem.mValueItem[i]);
            }
            this.mInfo = mainInfoItemArr;
            setContentTem();
        }
    }

    public void setContentTem() {
        int i = 0;
        for (int i2 = 0; i2 < this.mInfo.length; i2++) {
            if (this.mInfo[i2].value > 0) {
                i++;
            }
        }
        this.mcontent = new String[i];
        this.mColor = new int[i];
        int i3 = 0;
        int width = mFont.tahoma_7b_white.getWidth(this.itemName);
        if (width > this.sizeW - 10) {
            this.sizeW = width + 10;
        }
        for (int i4 = 0; i4 < this.mInfo.length; i4++) {
            MainInfoItem mainInfoItem = this.mInfo[i4];
            if (mainInfoItem.value > 0) {
                this.mcontent[i3] = new StringBuffer().append(nameInfoItem[i4]).append(": ").append(mainInfoItem.value).toString();
                if (isPercentInfoItem[i4] == 1) {
                    StringBuffer stringBuffer = new StringBuffer();
                    String[] strArr = this.mcontent;
                    int i5 = i3;
                    strArr[i5] = stringBuffer.append(strArr[i5]).append("%").toString();
                }
                this.mColor[i3] = colorInfoItem[i4];
                int width2 = mFont.tahoma_7_black.getWidth(this.mcontent[i3]);
                if (width2 > this.sizeW - 10) {
                    this.sizeW = width2 + 10;
                }
                i3++;
            }
        }
        if (this.sizeW > 200 && GameCanvas.isTouch) {
            this.sizeW = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
        }
        setSliptConten();
    }

    public void setContentItem() {
        if (this.itemName == null) {
            this.itemName = null;
            return;
        }
        int length = this.mInfo.length;
        mVector mvector = new mVector("EffectSkill tem");
        for (int i = 0; i < this.mInfo.length; i++) {
            MainInfoItem mainInfoItem = this.mInfo[i];
            if (isPercentInfoItem[mainInfoItem.id] == 4) {
                this.moreContenGem.addElement(new InfocontenNew(mainInfoItem.value, i));
            } else if (isATB_Can_paint(isPercentInfoItem[mainInfoItem.id])) {
                mvector.addElement(mainInfoItem);
            }
        }
        int size = mvector.size();
        this.mcontent = new String[size];
        this.mColor = new int[size];
        int width = mFont.tahoma_7b_white.getWidth(this.itemName);
        if (width > this.sizeW - 10) {
            this.sizeW = width + 10;
        }
        for (int i2 = 0; i2 < size; i2++) {
            MainInfoItem mainInfoItem2 = (MainInfoItem) mvector.elementAt(i2);
            if (mainInfoItem2.value == 0) {
                this.mcontent[i2] = nameInfoItem[mainInfoItem2.id];
            } else if (this.type_Only_Item == 14) {
                if (mainInfoItem2.maxDam > 0) {
                    this.mcontent[i2] = new StringBuffer().append(nameInfoItem[mainInfoItem2.id]).append(": ").append(getPercent(isPercentInfoItem[mainInfoItem2.id], mainInfoItem2.value)).append("-").append(mainInfoItem2.maxDam).toString();
                } else {
                    this.mcontent[i2] = new StringBuffer().append(nameInfoItem[mainInfoItem2.id]).append(": ").append(getPercent(isPercentInfoItem[mainInfoItem2.id], mainInfoItem2.value)).toString();
                }
            } else {
                this.mcontent[i2] = new StringBuffer().append(nameInfoItem[mainInfoItem2.id]).append(": ").append(mainInfoItem2.id == 70 ? MainItem.getDotNumber(mainInfoItem2.value) : getPercent(isPercentInfoItem[mainInfoItem2.id], mainInfoItem2.value)).toString();
                if (mainInfoItem2.id == 70) {
                    this.Selling = (byte) 1;
                }
            }
            this.mColor[i2] = colorInfoItem[mainInfoItem2.id];
            int width2 = mFont.tahoma_7_black.getWidth(this.mcontent[i2]);
            if (width2 > this.sizeW - 10) {
                this.sizeW = width2 + 10;
            }
        }
        int i3 = 0;
        if (this.timeUse > 0) {
            i3 = 0 + 1;
        }
        boolean isFashionItem = isFashionItem();
        int timeItemFashion = (int) (this.timeDefaultItemFashion - (getTimeItemFashion() / 60000));
        if (timeItemFashion <= 0) {
            isFashionItem = false;
        }
        if (isFashionItem) {
            i3++;
        }
        if (this.priceItem > 0) {
            i3++;
        }
        if (this.ItemCatagory == 3 && this.classcharItem < 4 && this.classcharItem > -1) {
            i3++;
        }
        if (this.type_Only_Item != 14 && this.LvItem > 0) {
            i3++;
        }
        if (i3 > 0) {
            this.mPlusContent = new String[i3];
            this.mPlusColor = new int[i3];
            int i4 = 0;
            if (this.timeUse > 0) {
                this.mPlusColor[0] = 6;
                if (this.type_Only_Item == 14) {
                    this.mPlusContent[0] = new StringBuffer().append(T.thoigianaptrung).append(PaintInfoGameScreen.getStringTime(this.timeUse)).toString();
                } else {
                    this.mPlusContent[0] = new StringBuffer().append(T.thoigianconlai).append(PaintInfoGameScreen.getStringTime(this.timeUse)).toString();
                }
                int width3 = mFont.tahoma_7_black.getWidth(this.mPlusContent[0]);
                if (width3 > this.sizeW - 10) {
                    this.sizeW = width3 + 10;
                }
                i4 = 0 + 1;
            }
            if (isFashionItem) {
                this.mPlusColor[i4] = 6;
                this.mPlusContent[i4] = new StringBuffer().append(T.thoigianconlai).append(PaintInfoGameScreen.getStringTime(timeItemFashion)).toString();
                i4++;
            }
            if (this.ItemCatagory == 3 && this.classcharItem < 4 && this.classcharItem > -1) {
                this.mPlusContent[i4] = new StringBuffer().append("[").append(T.mClass[this.classcharItem]).append("]").toString();
                if (this.classcharItem == GameScreen.player.clazz) {
                    this.mPlusColor[i4] = 0;
                } else {
                    this.mPlusColor[i4] = 6;
                }
                i4++;
            }
            if (this.priceItem > 0) {
                this.mPlusContent[i4] = new StringBuffer().append(T.price).append(": ").append(this.priceItem).append(getPriceType()).toString();
                this.mPlusColor[i4] = 2;
                i4++;
            }
            if (this.type_Only_Item != 14 && this.LvItem > 0) {
                this.mPlusContent[i4] = new StringBuffer().append(T.LVyeucau).append(this.LvItem).toString();
                this.mPlusColor[i4] = 0;
                int width4 = mFont.tahoma_7_black.getWidth(this.mPlusContent[i4]);
                if (width4 > this.sizeW - 10) {
                    this.sizeW = width4 + 10;
                }
            }
        } else {
            this.mPlusContent = null;
            this.mPlusColor = null;
        }
        if (this.sizeW > 200 && GameCanvas.isTouch) {
            this.sizeW = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
        }
        setSliptConten();
    }

    public void setSliptConten() {
        if (this.mcontent == null) {
            return;
        }
        if ((this.mcontent != null && this.mcontent.length == 1) || this.mcontent.length == 0 || this.ItemCatagory != 3) {
            return;
        }
        int i = this.sizeW;
        mVector mvector = new mVector();
        String str = this.mcontent[0];
        int i2 = this.mColor[0];
        for (int i3 = 1; i3 < this.mcontent.length; i3++) {
            for (String str2 : mFont.tahoma_7_black.splitFontArray(this.mcontent[i3], i)) {
                mvector.addElement(new Atb_info(str2, (byte) this.mColor[i3]));
            }
        }
        this.mcontent = new String[mvector.size() + 1];
        this.mColor = new int[mvector.size() + 1];
        this.mcontent[0] = str;
        this.mColor[0] = i2;
        for (int i4 = 0; i4 < mvector.size(); i4++) {
            Atb_info atb_info = (Atb_info) mvector.elementAt(i4);
            this.mcontent[i4 + 1] = atb_info.info;
            this.mColor[i4 + 1] = atb_info.id_color;
        }
    }

    public static String getPercent(int i, int i2) {
        if (i == 1) {
            if (i2 % 100 == 0) {
                return new StringBuffer().append(i2 / 100).append("%").toString();
            }
            if (i2 % 10 == 0) {
                return new StringBuffer().append(i2 / 100).append(".").append((i2 % 100) / 10).append("%").toString();
            }
            return new StringBuffer().append(i2 / 100).append(".").append((i2 % 100) / 10).append("").append(i2 % 10).append("%").toString();
        }
        if (i == 2) {
            return new StringBuffer().append(i2 / ObjectData.MONSTER).append(",").append((i2 % ObjectData.MONSTER) / 100).append("s").toString();
        }
        if (i == 3) {
            return new StringBuffer().append(i2).append("$").toString();
        }
        if (i == 4) {
            return " ";
        }
        if (i == 6) {
            return new StringBuffer().append(i2).append(" ").append(T.coin).toString();
        }
        if (i == 7) {
            return "";
        }
        return new StringBuffer().append("").append(i2).toString();
    }

    public String getPriceType() {
        String stringBuffer = new StringBuffer().append(" ").append(T.coin).toString();
        if (this.priceType == 1) {
            stringBuffer = new StringBuffer().append(" ").append(T.gem).toString();
        }
        return stringBuffer;
    }

    public static Item getItemVec(int i, short s, mVector mvector) {
        for (int i2 = 0; i2 < mvector.size(); i2++) {
            Item item = (Item) mvector.elementAt(i2);
            if (item.Id == s && item.ItemCatagory == i) {
                return item;
            }
        }
        return null;
    }

    public static Item getItemInventory(int i, short s) {
        for (int i2 = 0; i2 < VecInvetoryPlayer.size(); i2++) {
            Item item = (Item) VecInvetoryPlayer.elementAt(i2);
            if (item.Id == s && item.ItemCatagory == i) {
                return item;
            }
        }
        return null;
    }

    public static Item getItemChest(int i, short s) {
        for (int i2 = 0; i2 < VecChestPlayer.size(); i2++) {
            Item item = (Item) VecChestPlayer.elementAt(i2);
            if (item.Id == s && item.ItemCatagory == i) {
                return item;
            }
        }
        return null;
    }

    public static MainItem getMaterial(int i) {
        MainItem mainItem = (MainItem) MainTemplateItem.hashMaterialTem.get(new StringBuffer().append(i).append("").toString());
        if (mainItem != null) {
            return mainItem;
        }
        return null;
    }

    public static void put_Material(int i) {
        MainTemplateItem.hashMaterialTem.put(new StringBuffer().append("").append(i).toString(), new MainItem());
        GlobalService.gI().RequestMaterialTemplate((short) i);
    }

    public boolean isMaterialHopNguyenLieu() {
        return false;
    }

    public void setinfo(int i, String str, int i2, int i3, long j, byte b, String str2, int i4, byte b2, short s, byte b3, byte b4) {
        this.itemName = str;
        this.imageId = i2;
        this.Id = i;
        this.ItemCatagory = i3;
        this.priceItem = j;
        this.priceType = b;
        this.value = i4;
        this.canSell = b3;
        this.canTrade = b4;
        this.content = str2;
        this.typeMaterial = b2;
        if (str2 != null) {
            mMoreContent(str2);
        }
        this.IndexSort = (byte) 2;
    }

    public void setinfo(String str, int i, int i2, long j, byte b, String str2, int i3, byte b2, short s, byte b3, byte b4) {
        this.itemName = str;
        this.imageId = i;
        this.ItemCatagory = i2;
        this.priceItem = j;
        this.priceType = b;
        this.value = i3;
        this.canSell = b3;
        this.canTrade = b4;
        this.content = str2;
        this.typeMaterial = b2;
        if (str2 != null) {
            mMoreContent(str2);
        }
        this.IndexSort = (byte) 2;
    }

    public void mMoreContent(String str) {
        this.sizeW = 86;
        if (MainTabNew.longwidth > 0) {
            this.sizeW = MainTabNew.longwidth - 5;
        }
        this.mcontent = mFont.tahoma_7_black.splitFontArray(str, this.sizeW - 5);
        if (MainTabNew.longwidth == 0 && ((this.mcontent.length + 1) * GameCanvas.hText > (GameCanvas.h / 4) * 3 || mFont.tahoma_7b_black.getWidth(this.itemName) > 70)) {
            this.sizeW = EffectSkill.EFF_BIG_SWORD_115_2;
            this.mcontent = mFont.tahoma_7_black.splitFontArray(str, this.sizeW - 5);
        }
        this.mColor = new int[this.mcontent.length];
        for (int i = 0; i < this.mColor.length; i++) {
            this.mColor[i] = 0;
        }
        int i2 = 0;
        if (this.priceItem > 0) {
            i2 = 0 + 1;
        }
        if (i2 > 0) {
            this.mPlusContent = new String[i2];
            this.mPlusColor = new int[i2];
            if (this.priceItem > 0) {
                this.mPlusContent[i2 - 1] = new StringBuffer().append(T.price).append(": ").append(MainItem.getDotNumber(this.priceItem)).append(getPriceType()).toString();
                this.mPlusColor[i2 - 1] = 2;
            }
        } else {
            this.mPlusContent = null;
            this.mPlusColor = null;
        }
        if (this.sizeW > 200 && GameCanvas.isTouch) {
            this.sizeW = EffectSkill.EFF_BIG_SWORD_115_2;
        }
        setSliptConten();
    }

    public static boolean isATB_Can_paint(int i) {
        for (int i2 = 0; i2 < ATB_Can_Not_Paint.length; i2++) {
            if (i == ATB_Can_Not_Paint[i2]) {
                return false;
            }
        }
        return true;
    }

    public void setColorName(int i) {
        this.colorNameItem = i;
    }

    public void addATBitem(int i, int i2, int i3) {
        this.mInfo = new MainInfoItem[i];
        for (int i4 = 0; i4 < i; i4++) {
            this.mInfo[i4] = new MainInfoItem(i3, i2);
        }
    }
}
