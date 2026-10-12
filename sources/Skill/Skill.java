package Skill;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mHashtable;
import GameObjects.Item;
import GameObjects.ObjectData;
import GameObjects.Player;
import GameScreen.GameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.TabSkillsNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.MainImage;
import Model.T;

/* loaded from: KnightOnline_v403.jar:Skill/Skill.class */
public class Skill {
    public static mHashtable hashImageSkill = new mHashtable();
    public int Id;
    public String name;
    public String detail;
    public byte typeSkill;
    public byte iconId;
    public byte typeBuff;
    public byte subEff;
    public short range;
    public short lvMin;
    public short performDur;
    public LvSkill[] mLvSkill;
    public static final byte ACTIVE = 0;
    public static final byte BUFF = 1;
    public static final byte PASSIVE = 2;
    public static final byte BUFF_ALL = 0;
    public static final byte BUFF_ME = 1;
    public static final byte BUFF_TEAM = 2;
    public static final byte BUFF_ENEMY = 3;
    public byte typePaint;
    public String[] mcontent;

    public void setContent() {
    }

    public void paint(mGraphics mgraphics, int i, int i2, int i3) {
        MainImage imageSkill = ObjectData.getImageSkill(this.iconId);
        if (imageSkill.img != null) {
            mgraphics.drawImage(imageSkill.img, i, i2, i3, true);
        } else {
            mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, i, i2, i3, true);
        }
    }

    public void paintSmall(mGraphics mgraphics, int i, int i2, int i3) {
        MainImage imageSkill = ObjectData.getImageSkill(this.iconId);
        if (imageSkill.img != null) {
            mgraphics.drawRegion(imageSkill.img, 1, 1, 18, 18, 0, i, i2, i3, false);
        } else {
            mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, i, i2, i3, false);
        }
    }

    public String[] getContent() {
        if (this.mcontent == null) {
            if (GameScreen.player.Lv < this.lvMin) {
                String[] splitFontArray = mFont.tahoma_7_white.splitFontArray(this.detail, MainTabNew.longwidth > 0 ? MainTabNew.longwidth - 10 : 126);
                this.mcontent = new String[splitFontArray.length + 1];
                if (GameScreen.player.Lv < this.lvMin) {
                    this.mcontent[0] = new StringBuffer().append(T.yeucau).append(T.level).append((int) this.lvMin).toString();
                }
                for (int i = 1; i < this.mcontent.length; i++) {
                    this.mcontent[i] = splitFontArray[i - 1];
                }
            } else {
                int i2 = Player.mCurentLvSkill[this.Id];
                if (i2 > 0) {
                    i2 += Player.mPlusLvSkill[this.Id];
                }
                int i3 = i2 - 1;
                if (i3 < 0) {
                    i3 = 0;
                }
                if (this.Id == 0 || i3 >= 15) {
                    i3 = 0;
                }
                LvSkill lvSkill = this.mLvSkill[i3];
                String[] strArr = lvSkill.getinfo();
                String[] mInfoSkill = mInfoSkill(i3, Player.mCurentLvSkill[this.Id] == 0);
                this.mcontent = new String[strArr.length + mInfoSkill.length + lvSkill.minfo.length];
                int i4 = 0;
                for (String str : mInfoSkill) {
                    this.mcontent[i4] = str;
                    i4++;
                }
                for (String str2 : strArr) {
                    this.mcontent[i4] = str2;
                    i4++;
                }
                for (int i5 = 0; i5 < lvSkill.minfo.length; i5++) {
                    this.mcontent[i4] = new StringBuffer().append(Item.nameInfoItem[lvSkill.minfo[i5].id]).append(": ").append(Item.getPercent(Item.isPercentInfoItem[lvSkill.minfo[i5].id], lvSkill.minfo[i5].value)).toString();
                    i4++;
                }
            }
        }
        return this.mcontent;
    }

    public String[] mInfoSkill(int i, boolean z) {
        String[] splitFontArray = mFont.tahoma_7_white.splitFontArray(this.detail, MainTabNew.longwidth > 0 ? MainTabNew.longwidth - 10 : 126);
        int length = 0 + splitFontArray.length + 1;
        if (z) {
            length++;
        } else if (Player.mCurentLvSkill[this.Id] < 10 && this.Id != 0) {
            length++;
        }
        if (this.typeSkill == 1) {
            length++;
        }
        if (this.mLvSkill[i].range_lan > 0) {
            length++;
        }
        String[] strArr = new String[length];
        int i2 = 0;
        if (z) {
            strArr[0] = T.chuahoc;
            i2 = 0 + 1;
        } else if (Player.mCurentLvSkill[this.Id] < 10 && this.Id != 0) {
            strArr[0] = new StringBuffer().append(T.nangcapyeucau).append((int) this.mLvSkill[i].LvRe).toString();
            i2 = 0 + 1;
        }
        String stringBuffer = new StringBuffer().append("[").append(T.kynang).append(T.active).append("]").toString();
        if (this.typeSkill == 1) {
            stringBuffer = new StringBuffer().append("[").append(T.kynang).append(T.buff).append("]").toString();
        } else if (this.typeSkill == 2) {
            stringBuffer = new StringBuffer().append("[").append(T.kynang).append(T.passive).append("]").toString();
        }
        strArr[i2] = stringBuffer;
        int i3 = i2 + 1;
        for (String str : splitFontArray) {
            strArr[i3] = str;
            i3++;
        }
        if (this.typeSkill == 1) {
            String stringBuffer2 = new StringBuffer().append(T.tacdunglen).append(" : ").toString();
            switch (this.typeBuff) {
                case 0:
                    stringBuffer2 = new StringBuffer().append(stringBuffer2).append(T.moinguoi).toString();
                    break;
                case 1:
                    stringBuffer2 = new StringBuffer().append(stringBuffer2).append(T.banthan).toString();
                    break;
                case 2:
                    stringBuffer2 = new StringBuffer().append(stringBuffer2).append(T.trongdoi).toString();
                    break;
                case 3:
                    stringBuffer2 = new StringBuffer().append(stringBuffer2).append(T.kethu).toString();
                    break;
            }
            int i4 = i3;
            i3++;
            strArr[i4] = stringBuffer2;
        }
        if (this.mLvSkill[i].range_lan > 0) {
            int i5 = i3;
            int i6 = i3 + 1;
            strArr[i5] = new StringBuffer().append(T.phamvilan).append((int) this.mLvSkill[i].range_lan).toString();
        }
        return strArr;
    }

    public static void resetContent() {
        for (int i = 0; i < TabSkillsNew.vecPaintSkill.size(); i++) {
            ((Skill) TabSkillsNew.vecPaintSkill.elementAt(i)).mcontent = null;
        }
    }
}
