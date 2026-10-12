package Skill;

/* loaded from: KnightOnline_v403.jar:Skill/HotKey.class */
public class HotKey {
    public short id;
    public short idIconPotion;
    public byte type;
    public byte typePotion;
    public static byte NULL = -1;
    public static byte SKILL = 0;
    public static byte POTION = 1;

    public void setHotKey(int i, int i2, byte b) {
        this.id = (short) i;
        this.type = (byte) i2;
        this.typePotion = b;
    }

    public void setIdIcon(short s) {
        this.idIconPotion = s;
    }
}
