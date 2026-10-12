package GameObjects;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import Main.GameCanvas;
import Model.MainImage;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/Monstervantieu.class */
public class Monstervantieu extends MainMonster {
    public static final int ID_TEMPLATE_LUA_TRANG = 127;
    public static final int ID_TEMPLATE_LUA_DEN = 128;
    public static final int ID_TEMPLATE_LAC_DA_TRANG = 129;
    public static final int ID_TEMPLATE_LAC_DA_DEN = 130;
    public static final int ID_TEMPLATE_BO_TRANG = 131;
    public static final int ID_TEMPLATE_BO_DEN = 132;
    public static final int ID_TEMPLATE_VOI_TRANG = 177;
    public static final int ID_TEMPLATE_VOI_DEN = 176;
    public byte Maxspeed;
    byte typemove;
    byte numW;
    byte numH;
    public byte[][][] FRAMEMOVE = {new byte[]{new byte[]{7, 7, 7, 8, 8, 8, 9, 9, 9, 6, 6, 6, 6}, new byte[]{12, 12, 12, 13, 13, 13, 14, 14, 14, 11, 11, 11, 11}, new byte[]{2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1, 1, 1}, new byte[]{2, 2, 2, 3, 3, 3, 4, 4, 4, 1, 1, 1, 1}}, new byte[]{new byte[]{7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 6, 6, 6, 6}, new byte[]{12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 11, 11, 11, 11}, new byte[]{2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}, new byte[]{2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}}, new byte[]{new byte[]{7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 6, 6, 6, 6}, new byte[]{12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 11, 11, 11, 11}, new byte[]{2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}, new byte[]{2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 1, 1, 1, 1}}};
    public byte[][] FRAMESTAND = {new byte[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, new byte[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11}, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    public byte[] dysai = {-11, -7, -7};
    byte vTam = 0;
    byte xwater = 0;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [byte[][], byte[][][]] */
    /* JADX WARN: Type inference failed for: r1v4, types: [byte[], byte[][]] */
    public Monstervantieu(int i, int i2, int i3, String str, int i4, int i5, int i6, int i7) {
        this.typemove = (byte) 0;
        this.typeMonster = i3;
        this.ID = i;
        this.catalogyMonster = i2;
        this.typeObject = (byte) 1;
        this.name = str;
        this.x = i4;
        this.y = i5;
        this.hOne = -1;
        this.wOne = -1;
        this.maxHp = i6;
        this.Lv = (short) i7;
        this.typemove = getTypemove();
        this.Action = 0;
        this.MonWater = 0;
        this.numW = (byte) 3;
        this.numH = (byte) 5;
        this.nFrame = this.numW * this.numH;
        this.timeLoadInfo = mSystem.currentTimeMillis();
    }

    @Override // GameObjects.MainObject
    public boolean isMonsterVantieuTrang() {
        return this.catalogyMonster == 129 || this.catalogyMonster == 131 || this.catalogyMonster == 127 || this.catalogyMonster == 177;
    }

    @Override // GameObjects.MainObject
    public boolean isMonsterVantieuDen() {
        return this.catalogyMonster == 128 || this.catalogyMonster == 130 || this.catalogyMonster == 132 || this.catalogyMonster == 176;
    }

    @Override // GameObjects.MainObject
    public boolean isMonstervantieu() {
        return true;
    }

    @Override // GameObjects.MainObject
    public void setspeedVantieu(int i) {
        this.vMax = i;
        this.Maxspeed = (byte) i;
        this.vTam = (byte) (i + 1);
    }

    @Override // GameObjects.MainObject
    public String getnameOwner() {
        return this.nameowner;
    }

    protected byte getTypemove() {
        switch (this.catalogyMonster) {
            case 127:
            case ID_TEMPLATE_LUA_DEN /* 128 */:
                return (byte) 0;
            case ID_TEMPLATE_LAC_DA_TRANG /* 129 */:
            case ID_TEMPLATE_LAC_DA_DEN /* 130 */:
                return (byte) 1;
            case ID_TEMPLATE_BO_TRANG /* 131 */:
            case ID_TEMPLATE_BO_DEN /* 132 */:
            case ID_TEMPLATE_VOI_DEN /* 176 */:
            case ID_TEMPLATE_VOI_TRANG /* 177 */:
                return (byte) 2;
            default:
                return (byte) 0;
        }
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void paint(mGraphics mgraphics) {
        MainImage imagePartMonster = ObjectData.getImagePartMonster((short) this.catalogyMonster);
        if (imagePartMonster != null && !this.isDongBang && imagePartMonster.img != null) {
            if (this.Direction == 3) {
                this.xwater = (byte) -2;
            } else if (this.Direction == 2) {
                this.xwater = (byte) 2;
            } else {
                this.xwater = (byte) 0;
            }
            if (this.wOne < 0) {
                this.wOne = mImage.getImageWidth(imagePartMonster.img.image) / this.numW;
                this.hOne = mImage.getImageHeight(imagePartMonster.img.image) / this.numH;
                this.yEffAuto = this.hOne / 2;
            }
            paintDataEff_Top(mgraphics, this.x, this.y);
            if (this.frameMount >= 0 && this.frameMount < this.nFrame) {
                int i = (this.frameMount / this.numH) * this.wOne;
                int i2 = (this.frameMount % this.numH) * this.hOne;
                if (this.isWater) {
                    mgraphics.drawRegion(imagePartMonster.img, i, i2, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, ((this.y - this.ysai) - this.dy) + this.dyWater, 33, true);
                    mgraphics.drawRegion(MainObject.water, 0, ((this.Action != 0 ? 2 : 0) * 17) + (((GameCanvas.gameTick / 2) % 2) * 17), 28, 17, 0, this.x + this.xwater, (this.y + this.dyWater) - 8, 3, false);
                } else {
                    MainObject.paintShadow(mgraphics, this.Direction, this.x + this.xwater, (this.y - this.ysai) + this.dysai[this.typemove]);
                    mgraphics.drawRegion(imagePartMonster.img, i, i2, this.wOne, this.hOne, this.Direction > 2 ? 2 : 0, this.x, ((this.y - this.ysai) - this.dy) + this.dyWater, 33, true);
                }
            }
        }
        paintDataEff_Bot(mgraphics, this.x, this.y);
        mFont.tahoma_7_white.drawString(mgraphics, this.nameowner, this.x, ((this.y - this.hOne) - 3) - (this.isDongBang ? 5 : 0), 2, false);
        super.paint(mgraphics);
    }

    public void updateMonsterAction() {
        this.fMount = (byte) (this.fMount + 1);
        if (this.Action == 0) {
            if (this.fMount > this.FRAMESTAND[this.Direction].length - 1) {
                this.fMount = (byte) 0;
            }
            this.frameMount = this.FRAMESTAND[this.Direction][this.fMount];
        } else if (this.Action == 1) {
            if (this.fMount > this.FRAMEMOVE[this.typemove][this.Direction].length - 1) {
                this.fMount = (byte) 0;
            }
            this.frameMount = this.FRAMEMOVE[this.typemove][this.Direction][this.fMount];
        }
    }

    @Override // GameObjects.MainMonster, GameObjects.MainObject, Model.AvMain
    public void update() {
        super.update();
        updateMonsterAction();
        updateDataEffect();
        setMove(this.MonWater, GameCanvas.loadmap.getTile(this.x + this.vx, this.y + this.vy));
        if (!Canmove() || this.isBinded) {
            return;
        }
        if (!this.isInfo) {
            long currentTimeMillis = mSystem.currentTimeMillis();
            if (currentTimeMillis - this.timeLoadInfo >= 5000) {
                this.timeLoadInfo = currentTimeMillis;
                GlobalService.gI().monster_info((short) this.ID);
            }
        }
        if (this.vx == 0 && this.vy == 0) {
            this.Action = 0;
        }
        if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) >= LoadMap.wTile * 3 && MainObject.getDistance(this.x, this.y, this.toX, this.toY) <= LoadMap.wTile * 4) {
            this.Maxspeed = this.vTam;
        }
        if (MainObject.getDistance(this.x, this.y, this.toX, this.toY) < LoadMap.wTile * 3) {
            this.vMax = (byte) (this.vTam - 1);
        }
        if (this.Maxspeed > 1) {
            if (this.frameMount == 3 || this.frameMount == 8 || this.frameMount == 13) {
                this.vMax = this.Maxspeed - 1;
            } else {
                this.vMax = this.Maxspeed;
            }
        }
    }
}
