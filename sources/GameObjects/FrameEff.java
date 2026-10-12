package GameObjects;

import CLib.mVector;

/* loaded from: KnightOnline_v403.jar:GameObjects/FrameEff.class */
public class FrameEff {
    public mVector listPart;
    public mVector listPartTop;
    public mVector listPartBottom;
    public byte xShadow;
    public int yShadow;

    public FrameEff(mVector mvector) {
        this.listPart = new mVector();
        this.listPartTop = new mVector();
        this.listPartBottom = new mVector();
        this.listPart = mvector;
    }

    public FrameEff(mVector mvector, mVector mvector2) {
        this.listPart = new mVector();
        this.listPartTop = new mVector();
        this.listPartBottom = new mVector();
        this.listPartTop = mvector;
        this.listPartBottom = mvector2;
    }
}
