package CLib;

import java.util.Vector;

/* loaded from: KnightOnline_v403.jar:CLib/mVector.class */
public class mVector {
    private Vector a;
    private String name;

    public mVector() {
        this.a = new Vector();
        this.name = "EffectManager";
    }

    public mVector(String str) {
        this.a = new Vector();
        this.name = str;
    }

    public mVector(Vector vector) {
        this.a = vector;
        this.name = "No Name";
    }

    public void addElement(Object obj) {
        this.a.addElement(obj);
    }

    public boolean contains(Object obj) {
        if (this.a.contains(obj)) {
            return true;
        }
        return false;
    }

    public int size() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object elementAt(int i) {
        if (i > -1 && i < this.a.size()) {
            return this.a.elementAt(i);
        }
        return null;
    }

    public void setElementAt(Object obj, int i) {
        if (i > -1 && i < this.a.size()) {
            this.a.setElementAt(obj, i);
        }
    }

    public int indexOf(Object obj) {
        return this.a.indexOf(obj);
    }

    public void removeElementAt(int i) {
        if (i > -1 && i < this.a.size()) {
            this.a.removeElementAt(i);
        }
    }

    public void removeElement(Object obj) {
        this.a.removeElement(obj);
    }

    public void removeAllElements() {
        this.a.removeAllElements();
    }

    public void insertElementAt(Object obj, int i) {
        this.a.insertElementAt(obj, i);
    }

    public Object firstElement() {
        return this.a.firstElement();
    }

    public Object lastElement() {
        return this.a.lastElement();
    }
}
