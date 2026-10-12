package Model;

import CLib.mGraphics;
import CLib.mVector;
import GameEffect.MainEffect;

/* loaded from: KnightOnline_v403.jar:Model/EffectManager.class */
public class EffectManager extends mVector {
    public static EffectManager lowEffects = new EffectManager();
    public static EffectManager hiEffects = new EffectManager();

    public void updateAll() {
        for (int size = size() - 1; size >= 0; size--) {
            MainEffect mainEffect = (MainEffect) elementAt(size);
            if (mainEffect != null) {
                mainEffect.update();
                if (mainEffect.isRemove) {
                    mainEffect.clearData();
                    removeElementAt(size);
                }
            }
        }
    }

    public void paintAll(mGraphics mgraphics) {
        for (int i = 0; i < size(); i++) {
            MainEffect mainEffect = (MainEffect) elementAt(i);
            if (mainEffect != null && !mainEffect.isRemove) {
                mainEffect.paint(mgraphics);
            }
        }
    }

    public void reMoveAll() {
        for (int size = size() - 1; size >= 0; size--) {
            MainEffect mainEffect = (MainEffect) elementAt(size);
            if (mainEffect != null) {
                mainEffect.isRemove = true;
                mainEffect.clearData();
                removeElementAt(size);
            }
        }
    }

    public static void addHiEffect(MainEffect mainEffect) {
        hiEffects.addElement(mainEffect);
    }

    public static void addLowEffect(MainEffect mainEffect) {
        lowEffects.addElement(mainEffect);
    }
}
