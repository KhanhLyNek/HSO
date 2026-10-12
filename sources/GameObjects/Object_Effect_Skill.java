package GameObjects;

import Skill.Monster_Skill;

/* loaded from: KnightOnline_v403.jar:GameObjects/Object_Effect_Skill.class */
public class Object_Effect_Skill {
    public short ID;
    public byte tem;
    public int hpShow;
    public int hpLast;
    public int hpPlus;
    public int[] mEffTypePlus;
    public int[] mEff_HP_Plus;
    public Monster_Skill skillMonster;

    public Object_Effect_Skill(short s, byte b) {
        this.mEffTypePlus = new int[0];
        this.mEff_HP_Plus = new int[0];
        this.ID = s;
        this.tem = b;
    }

    public Object_Effect_Skill(short s, byte b, Monster_Skill monster_Skill) {
        this.mEffTypePlus = new int[0];
        this.mEff_HP_Plus = new int[0];
        this.ID = s;
        this.tem = b;
        this.skillMonster = monster_Skill;
    }

    public void setHP(int i, int i2) {
        this.hpShow = i;
        this.hpLast = i2;
    }
}
