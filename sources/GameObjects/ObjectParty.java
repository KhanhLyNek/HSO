package GameObjects;

/* loaded from: KnightOnline_v403.jar:GameObjects/ObjectParty.class */
public class ObjectParty {
    public short Lv;
    public String name;
    public byte idArea;
    public int idMap;
    public int x;
    public int y;
    public int hp;
    public int maxhp;
    public boolean isRemove = false;
    public boolean ischeck = true;

    public ObjectParty(String str, short s, int i, byte b) {
        this.idArea = b;
        this.idMap = i;
        this.name = str;
        this.Lv = s;
    }
}
