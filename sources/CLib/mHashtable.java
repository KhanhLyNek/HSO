package CLib;

import java.util.Enumeration;
import java.util.Hashtable;

/* loaded from: KnightOnline_v403.jar:CLib/mHashtable.class */
public class mHashtable {
    public Hashtable htb = new Hashtable();

    public void clear() {
        this.htb.clear();
    }

    public boolean contains(Object obj) {
        return this.htb.contains(obj);
    }

    public boolean containsKey(Object obj) {
        return this.htb.containsKey(obj);
    }

    public Object get(String str) {
        return this.htb.get(str);
    }

    public int size() {
        return this.htb.size();
    }

    public void put(String str, Object obj) {
        if (this.htb.containsKey(str)) {
            this.htb.remove(str);
        }
        this.htb.put(str, obj);
    }

    public void remove(Object obj) {
        this.htb.remove(obj);
    }

    public Enumeration keys() {
        return this.htb.keys();
    }
}
