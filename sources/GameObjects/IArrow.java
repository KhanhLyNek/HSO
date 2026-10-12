package GameObjects;

import CLib.mGraphics;

/* loaded from: KnightOnline_v403.jar:GameObjects/IArrow.class */
public abstract class IArrow {
    public boolean wantDestroy;

    public abstract void set(int i, int i2, int i3, int i4, short s, MainObject mainObject, MainObject mainObject2);

    public abstract void setAngle(int i);

    public abstract void update();

    public abstract void paint(mGraphics mgraphics);

    public abstract void SetEffFollow(int i);

    public abstract void onArrowTouchTarget();
}
