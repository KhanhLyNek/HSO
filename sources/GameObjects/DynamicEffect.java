package GameObjects;

import CLib.mGraphics;
import CLib.mHashtable;
import GameEffect.EffectSkill;

/* loaded from: KnightOnline_v403.jar:GameObjects/DynamicEffect.class */
public class DynamicEffect extends MainObject {
    public static mHashtable ALL_DATA_DYNAMIC_EFFECT = new mHashtable();
    public short id;
    public long timeExist = -1;
    byte index = 0;
    public byte stop = 0;

    public DynamicEffect(int i) {
        this.id = (short) i;
    }

    public void paint(mGraphics mgraphics, int i, int i2) {
        ImageIcon imgIcon;
        DataEffect dataEffect = (DataEffect) ALL_DATA_DYNAMIC_EFFECT.get(new StringBuffer().append((int) this.id).append("").toString());
        if (dataEffect != null && (imgIcon = GameData.getImgIcon((short) (this.id + 8700), EffectSkill.EFF_KIEM_NHO_TROI)) != null && imgIcon.img != null) {
            dataEffect.paint(mgraphics, getSequence(this.id)[this.index], i, i2, 0, imgIcon.img);
        }
    }

    public static void setDataEffect(byte[][] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            ALL_DATA_DYNAMIC_EFFECT.put(new StringBuffer().append(i).append("").toString(), new DataEffect(bArr[i]));
        }
    }

    public static byte[] getSequence(int i) {
        DataEffect dataEffect = (DataEffect) ALL_DATA_DYNAMIC_EFFECT.get(new StringBuffer().append(i).append("").toString());
        if (dataEffect != null) {
            return dataEffect.sequence;
        }
        return null;
    }

    public boolean canDestroy() {
        return this.timeExist != -1 && System.currentTimeMillis() - this.timeExist >= 0 && this.index == 0;
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void update() {
        try {
            byte[] sequence = getSequence(this.id);
            if (sequence != null) {
                this.index = (byte) ((this.index + 1) % sequence.length);
            }
        } catch (Exception e) {
        }
        if (canDestroy()) {
            this.isRemove = true;
        }
    }

    @Override // GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        ImageIcon imgIcon;
        DataEffect dataEffect = (DataEffect) ALL_DATA_DYNAMIC_EFFECT.get(new StringBuffer().append((int) this.id).append("").toString());
        if (dataEffect != null && (imgIcon = GameData.getImgIcon((short) (this.id + 8700), EffectSkill.EFF_KIEM_NHO_TROI)) != null && imgIcon.img != null) {
            dataEffect.paint(mgraphics, getSequence(this.id)[this.index], this.x, this.y, 0, imgIcon.img);
        }
    }

    public void setPosTo(short s, short s2) {
    }
}
