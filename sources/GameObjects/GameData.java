package GameObjects;

import CLib.mHashtable;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import Main.GameCanvas;
import Model.FrameImage;
import java.util.Enumeration;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/GameData.class */
public class GameData {
    private static GameData me;
    public static FrameImage imgSkillIcon;
    private boolean loadData = false;
    public static int ID_START_SKILL = 0;
    public static mVector imgHorse = new mVector();
    public static mHashtable listImgIcon = new mHashtable();
    static byte idWait = 0;

    public static GameData gI() {
        if (me != null) {
            return me;
        }
        GameData gameData = new GameData();
        me = gameData;
        return gameData;
    }

    public void getImage() {
        if (!this.loadData) {
            loadImgPotion();
            loadImgGemItem();
            this.loadData = true;
        }
    }

    public static void loadImgPotion() {
    }

    private static void createImgHorse(byte[] bArr, byte[][] bArr2) {
    }

    public static void saveImgSkill(int i, byte[] bArr) {
    }

    public static void saveImgGem(byte b, byte[] bArr) {
    }

    public static void loadImgGemItem() {
    }

    public static void setIndexWait() {
    }

    public static ImageIcon getImgIcon(short s, int i) {
        ImageIcon imageIcon = (ImageIcon) listImgIcon.get(new StringBuffer().append((int) s).append("").toString());
        int i2 = s - ID_START_SKILL;
        if (imageIcon == null) {
            imageIcon = new ImageIcon();
            imageIcon.id = s;
            imageIcon.isLoad = true;
            listImgIcon.put(new StringBuffer().append((int) s).append("").toString(), imageIcon);
            if (imageIcon.img == null && s >= ID_START_SKILL) {
                try {
                    imageIcon.img = mImage.createImage(new StringBuffer().append("/imgDataEff/").append(i2).append(".png").toString());
                } catch (Exception e) {
                }
            }
            if (imageIcon.img == null && (mSystem.currentTimeMillis() / 1000) - imageIcon.timeGetBack > 30) {
                GlobalService.gI().load_image_data_part_char((byte) i, (short) i2);
            }
        } else {
            imageIcon.timeRemove = GameCanvas.timeNow;
        }
        return imageIcon;
    }

    public static void RequestImgandData(short s, int i) {
        ImageIcon imageIcon = (ImageIcon) listImgIcon.get(new StringBuffer().append((int) s).append("").toString());
        int i2 = s - ID_START_SKILL;
        if (imageIcon == null) {
            ImageIcon imageIcon2 = new ImageIcon();
            imageIcon2.id = s;
            imageIcon2.isLoad = true;
            listImgIcon.put(new StringBuffer().append((int) s).append("").toString(), imageIcon2);
            if (imageIcon2.img == null && s >= ID_START_SKILL) {
                try {
                    imageIcon2.img = mImage.createImage(new StringBuffer().append("/imgDataEff/").append(i2).append(".png").toString());
                } catch (Exception e) {
                }
            }
            if (imageIcon2.img == null && (mSystem.currentTimeMillis() / 1000) - imageIcon2.timeGetBack > 30) {
                GlobalService.gI().load_image_data_part_char((byte) i, (short) i2);
            }
            imageIcon2.timeRemove = (int) (mSystem.currentTimeMillis() / 1000);
        }
    }

    public static void SetRemove() {
        Enumeration keys = listImgIcon.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            ImageIcon imageIcon = (ImageIcon) listImgIcon.get(str);
            if ((GameCanvas.timeNow - imageIcon.timeRemove) / 1000 > 60) {
                if (imageIcon.img != null) {
                    imageIcon.img.clearData();
                }
                listImgIcon.remove(str);
            }
        }
    }
}
