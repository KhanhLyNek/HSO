package GameObjects;

import CLib.mVector;
import GameScreen.GameScreen;

/* loaded from: KnightOnline_v403.jar:GameObjects/MainParty.class */
public class MainParty {
    public String nameMain;
    public mVector vecPartys = new mVector("MainParty vecPartys");
    public boolean isClear;

    public MainParty(String str, short s, int i, byte b) {
        this.isClear = false;
        this.nameMain = str;
        this.vecPartys.removeAllElements();
        this.isClear = false;
        addObjParty(str, s, i, b);
    }

    public void addObjParty(String str, short s, int i, byte b) {
        ObjectParty objectParty = new ObjectParty(str, s, i, b);
        objectParty.maxhp = 10;
        objectParty.hp = 10;
        this.vecPartys.addElement(objectParty);
    }

    public void removeObj(String str) {
        for (int i = 0; i < this.vecPartys.size(); i++) {
            ObjectParty objectParty = (ObjectParty) this.vecPartys.elementAt(i);
            if (objectParty.name.compareTo(str) == 0) {
                objectParty.isRemove = true;
                return;
            }
        }
    }

    public void setPos(String str, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < this.vecPartys.size(); i5++) {
            ObjectParty objectParty = (ObjectParty) this.vecPartys.elementAt(i5);
            if (objectParty.name.compareTo(str) == 0) {
                objectParty.x = i;
                objectParty.y = i2;
                if (i3 > i4) {
                    i3 = i4;
                }
                objectParty.hp = i3;
                objectParty.maxhp = i4;
                return;
            }
        }
    }

    public boolean update() {
        if (this.isClear) {
            this.vecPartys.removeAllElements();
            return true;
        }
        int i = 0;
        while (i < this.vecPartys.size()) {
            ObjectParty objectParty = (ObjectParty) this.vecPartys.elementAt(i);
            if (objectParty.isRemove) {
                this.vecPartys.removeElement(objectParty);
                i--;
            }
            i++;
        }
        return false;
    }

    public void setIsParty() {
        for (int i = 0; i < this.vecPartys.size(); i++) {
            ((ObjectParty) this.vecPartys.elementAt(i)).ischeck = false;
        }
        for (int i2 = 0; i2 < GameScreen.Vecplayers.size(); i2++) {
            MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i2);
            if (mainObject.typeObject == 0 && mainObject != GameScreen.player) {
                mainObject.isParty = false;
                int i3 = 0;
                while (true) {
                    if (i3 < this.vecPartys.size()) {
                        ObjectParty objectParty = (ObjectParty) this.vecPartys.elementAt(i3);
                        if (objectParty.ischeck || objectParty.name.compareTo(mainObject.name) != 0) {
                            i3++;
                        } else {
                            mainObject.isParty = true;
                            objectParty.ischeck = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void setObjParty(MainObject mainObject) {
        for (int i = 0; i < this.vecPartys.size(); i++) {
            if (((ObjectParty) this.vecPartys.elementAt(i)).name.compareTo(mainObject.name) == 0) {
                mainObject.isParty = true;
                return;
            }
        }
    }
}
