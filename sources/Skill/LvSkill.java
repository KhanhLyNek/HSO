package Skill;

import GameObjects.MainInfoItem;
import GameObjects.ObjectData;
import GameScreen.GameScreen;
import Model.T;

/* loaded from: KnightOnline_v403.jar:Skill/LvSkill.class */
public class LvSkill {
    public short mpLost;
    public short time_Sub_Eff;
    public short plus_Hp;
    public short plus_Mp;
    public short LvRe;
    public short range_lan;
    public int delay;
    public int timeBuff;
    public byte per_Sub_Eff;
    public byte nTarget;
    public MainInfoItem[] minfo;

    public int getdelay() {
        if (GameScreen.player.timeCombo > 0) {
            return GameScreen.player.timeCombo;
        }
        return this.delay;
    }

    public String[] getinfo() {
        int i = 0;
        if (this.mpLost > 0) {
            i = 0 + 1;
        }
        if (this.timeBuff > 0) {
            i++;
        }
        if (this.per_Sub_Eff > 0) {
            i++;
        }
        if (this.time_Sub_Eff > 0) {
            i++;
        }
        if (this.plus_Hp > 0) {
            i++;
        }
        if (this.plus_Mp > 0) {
            i++;
        }
        if (this.delay > 0) {
            i++;
        }
        String[] strArr = new String[i];
        int i2 = 0;
        if (this.mpLost > 0) {
            i2 = 0 + 1;
            strArr[0] = new StringBuffer().append(T.nangluong).append((int) this.mpLost).toString();
        }
        if (this.delay > 0) {
            int i3 = i2;
            i2++;
            strArr[i3] = new StringBuffer().append(T.delay).append(this.delay / ObjectData.MONSTER).append("s").toString();
        }
        if (this.timeBuff > 0) {
            int i4 = i2;
            i2++;
            strArr[i4] = new StringBuffer().append(T.timebuff).append(this.timeBuff / ObjectData.MONSTER).append("s").toString();
        }
        if (this.per_Sub_Eff > 0) {
            int i5 = i2;
            i2++;
            strArr[i5] = new StringBuffer().append(T.hieuung).append((int) this.per_Sub_Eff).toString();
        }
        if (this.time_Sub_Eff > 0) {
            int i6 = i2;
            i2++;
            strArr[i6] = new StringBuffer().append(T.timehieuung).append(this.time_Sub_Eff / ObjectData.MONSTER).append("s").toString();
        }
        if (this.plus_Hp > 0) {
            int i7 = i2;
            i2++;
            strArr[i7] = new StringBuffer().append(T.tanghpdung).append(this.plus_Hp / 10).append("%").toString();
        }
        if (this.plus_Mp > 0) {
            int i8 = i2;
            int i9 = i2 + 1;
            strArr[i8] = new StringBuffer().append(T.tangmpdung).append(this.plus_Mp / 10).append("%").toString();
        }
        return strArr;
    }
}
