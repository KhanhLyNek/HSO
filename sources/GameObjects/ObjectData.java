package GameObjects;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mVector;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImage;
import Skill.Skill;
import java.util.Enumeration;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/ObjectData.class */
public class ObjectData {
    public static final short ITEMMAP = 0;
    public static final short MONSTER = 1000;
    public static final short ITEM = 2000;
    public static final short NPC = 3000;
    public static final short POTION = 4000;
    public static final short QUEST_ITEM = 5000;
    public static final short MATERIAL = 5500;
    public static final short SKILL = 6000;
    public static final short ICON_CLAN = 7000;
    public static final short ICON_ARCHE_CLAN = 9500;
    public static final short CAPCHAR = 9999;
    public static final short ICON_PET = 10000;
    public static final short IMAGE_PET = 10200;
    public static final short IMAGE_TILE = 10400;
    public static final short IMAGE_TILE_WATER = 10500;
    public static final short IMAGE_TILE_SMALL = 10600;
    public static final short IMAGE_MOUNT = 10700;
    public static final short ITEM_2 = 13000;
    public static mVector vecSaveImage = new mVector("ObjectData vecSaveImage");

    public static MainImage getImagePartNPC(short s) {
        MainImage mainImage = (MainImage) GameScreen.HashImageNPC.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            GameScreen.HashImageNPC.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            String stringBuffer = new StringBuffer().append("/resLocal/npc/").append((int) s).append(".png").toString();
            if (s >= 500) {
                stringBuffer = new StringBuffer().append("/resLocal/npc/icon/").append(s - 500).append(".png").toString();
            }
            mImage createImage = mImage.createImage(stringBuffer);
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + NPC));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + NPC));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImagePartItemMap(short s) {
        MainImage mainImage = (MainImage) GameScreen.HashImageItemMap.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            GameScreen.HashImageItemMap.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/tree/").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + 0));
            } else {
                mainImage.img = createImage;
            }
        } else if (!GameCanvas.isTouch) {
            mainImage.count = (int) (GameCanvas.timeNow / 1000);
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + 0));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImagePartMonster(short s) {
        MainImage mainImage = (MainImage) MainMonster.HashImageMonster.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            MainMonster.HashImageMonster.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/mons/").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + MONSTER));
            } else {
                mainImage.img = createImage;
            }
        } else if (!GameCanvas.isTouch) {
            mainImage.count = (int) (GameCanvas.timeNow / 1000);
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + MONSTER));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageItem(short s) {
        MainImage mainImage = (MainImage) Item.HashImageItem.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageItem.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/icon/icon_").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + ITEM));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + ITEM));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImagePotion(short s) {
        MainImage mainImage = (MainImage) Item.HashImagePotion.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImagePotion.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/potion/potionIcon_").append(s + 1 >= 10 ? new StringBuffer().append(s + 1).append("").toString() : new StringBuffer().append("0").append(s + 1).toString()).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + POTION));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + POTION));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageQuestItem(short s) {
        MainImage mainImage = (MainImage) Item.HashImageQuestItem.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageQuestItem.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/iconq/").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + QUEST_ITEM));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + QUEST_ITEM));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageMaterial(short s) {
        MainImage mainImage = (MainImage) Item.HashImageMaterial.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageMaterial.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/material/").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + MATERIAL));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + MATERIAL));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageSkill(short s) {
        MainImage mainImage = (MainImage) Skill.hashImageSkill.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Skill.hashImageSkill.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(s < 10 ? new StringBuffer().append("/resLocal/skill/iconSkill_0").append((int) s).append(".png").toString() : new StringBuffer().append("/resLocal/skill/iconSkill_").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + SKILL));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + SKILL));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageIconClan(short s) {
        MainImage mainImage = (MainImage) Item.HashImageIconClan.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageIconClan.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            String stringBuffer = new StringBuffer().append("/resLocal/iconclan/").append((int) s).append(".png").toString();
            if (s >= 500) {
                stringBuffer = new StringBuffer().append("/resLocal/iconclan/shop/").append(s - 500).append(".png").toString();
            }
            mImage createImage = mImage.createImage(stringBuffer);
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + ICON_CLAN));
            } else {
                mainImage.img = createImage;
            }
        } else if (!GameCanvas.isTouch) {
            mainImage.count = (int) (GameCanvas.timeNow / 1000);
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + ICON_CLAN));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageIconArCheClan(short s) {
        MainImage mainImage = (MainImage) Item.HashImageIconArcheClan.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageIconArcheClan.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/iconclan/huyhieu/").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + ICON_ARCHE_CLAN));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + ICON_ARCHE_CLAN));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static MainImage getImageIconPet(short s) {
        MainImage mainImage = (MainImage) Item.HashImagePetIcon.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImagePetIcon.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/pet/icon/icon_pet_").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + ICON_PET));
            } else {
                mainImage.img = createImage;
            }
        }
        return mainImage;
    }

    public static MainImage getImagePet(short s) {
        MainImage mainImage = (MainImage) Pet.HashImagePet.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Pet.HashImagePet.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/pet/").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + IMAGE_PET));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + IMAGE_PET));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }

    public static mImage getFromRms(short s) {
        String stringBuffer = new StringBuffer().append("image").append((int) s).toString();
        byte[] bArr = null;
        if (setIdOK(s)) {
            bArr = CRes.loadRMS(new StringBuffer().append("image").append((int) s).toString());
        }
        if (bArr == null) {
            GlobalService.gI().load_image(s);
            return null;
        }
        try {
            return mImage.createImage(bArr, 0, bArr.length, stringBuffer);
        } catch (Exception e) {
            GlobalService.gI().load_image(s);
            return null;
        }
    }

    public static boolean setIdOK(short s) {
        if (s >= 4000 && s < 5000) {
            return true;
        }
        if (s >= 3000 && s < 3500) {
            return true;
        }
        if (s >= 6000 && s < 7000) {
            return true;
        }
        return false;
    }

    public static void setToRms(byte[] bArr, short s) {
        try {
            CRes.saveRMS(new StringBuffer().append("image").append((int) s).toString(), bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkDelHash(mHashtable mhashtable) {
        Enumeration keys = mhashtable.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            MainImage mainImage = (MainImage) mhashtable.get(str);
            if ((GameCanvas.timeNow / 1000) - mainImage.count > 300) {
                if (mainImage.img != null) {
                    mainImage.img.clearData();
                }
                mhashtable.remove(str);
            }
        }
    }

    public static MainImage getImageMount(short s) {
        MainImage mainImage = (MainImage) Item.HashImageMount.get(new StringBuffer().append("").append((int) s).toString());
        if (mainImage == null) {
            mainImage = new MainImage();
            Item.HashImageMount.put(new StringBuffer().append("").append((int) s).toString(), mainImage);
            mImage createImage = mImage.createImage(new StringBuffer().append("/resLocal/mount/").append((int) s).append(".png").toString());
            if (createImage == null) {
                mainImage.img = getFromRms((short) (s + IMAGE_MOUNT));
            } else {
                mainImage.img = createImage;
            }
        }
        if (mainImage.img == null) {
            mainImage.timeImageNull++;
            if (mainImage.timeImageNull >= 200) {
                GlobalService.gI().load_image((short) (s + IMAGE_MOUNT));
                mainImage.timeImageNull = 0;
            }
        }
        return mainImage;
    }
}
