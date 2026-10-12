package GameObjects;

import CLib.mImage;
import CLib.mSystem;

/* loaded from: KnightOnline_v403.jar:GameObjects/WeaponInfo.class */
public class WeaponInfo {
    public mImage img;
    public byte[][][] mPos = mSystem.new_M_Byte(4, 3, 2);
    public byte[][] mRegion = mSystem.new_M_Byte(4, 2);
    public int himg;
}
