package GameEffect;

import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import GameScreen.GameScreen;
import InterfaceComponents.TabRebuildItem;
import Main.GameCanvas;
import Model.CRes;
import Model.FrameImage;
import Model.Point;

/* loaded from: KnightOnline_v403.jar:GameEffect/EffectEnd.class */
public class EffectEnd extends MainEffect {
    public static final byte END_NUC_DAT = 0;
    public static final byte END_EFF_WATER_BIG = 1;
    public static final byte END_FAR_NEAR = 2;
    public static final byte END_THIEN_THACH = 3;
    public static final byte END_KILL_PS_LV2 = 4;
    public static final byte END_KILL_2KIEM_LV2 = 5;
    public static final byte END_KILL_2KIEM_LV2_BEGIN = 6;
    public static final byte END_KILL_SUNG_LV1 = 7;
    public static final byte END_ROCK = 9;
    public static final byte END_KILL_PS_LV1 = 10;
    public static final byte END_DIE_MONSTER = 11;
    public static final byte END_KILL_SUNG_LV2 = 12;
    public static final byte END_KILL_SUNG_LV2_BEGIN = 13;
    public static final byte END_PHAO_HOA = 14;
    public static final byte END_PHAO_BANG = 15;
    public static final byte END_WATER_ROCK = 19;
    public static final byte END_EFF_LV_UP = 21;
    public static final byte END_EFF_2KIEM_DOC = 24;
    public static final byte END_EFF_KIEM_LV6 = 25;
    public static final byte END_KIEM_NUC_DAT_2 = 26;
    public static final byte END_ICE_BIG = 27;
    public static final byte END_ICE_UP = 28;
    public static final byte END_XUYEN_GIAP = 29;
    public static final byte END_ROCK_FIRE_BIG = 30;
    public static final byte END_REBUILD = 31;
    public static final byte END_LEVEL_UP_REBUILD = 32;
    public static final byte END_EFF_LV_UP_REBUILD = 33;
    public static final byte END_EFF_FINISH_REBUILD = 34;
    public static final byte END_EFF_REMOVE_OBJ = 35;
    public static final byte END_EFF_REMOVE_MON_PHO_BANG = 36;
    public static final byte END_REPLACE_PLUS = 37;
    public static final byte END_NHANBAN_REVEICE = 38;
    public static final byte END_HUT_HP_MON_PHO_BANG = 39;
    public static final byte END_EFF_NAM_DAT = 40;
    public static final byte END_EFF_A_DEN_B = 41;
    public static final byte END_EFF_OPEN_BOX = 42;
    public static final byte END_EFF_DETONATE = 43;
    public static final byte END_EFF_POISON_NOVA = 44;
    public static final byte END_EFF_TORNADO = 45;
    public static final byte END_EFF_CAY_DAT = 46;
    public static final byte END_EFF_NUT_DAT_BIND = 47;
    public static final byte END_EFF_Medusa_ATK = 48;
    public static final byte END_EFF_DONG_BANG = 49;
    public static final byte END_EFF_Medusa_VongTron = 50;
    public static final byte END_PET_BAT = 51;
    public static final byte END_PET_EAGLE = 52;
    public static final byte END_PET_OWL = 53;
    public static final byte END_EFF_MOVE_BOSS = 54;
    public static final byte END_MOUNT_DUST = 55;
    public static final byte END_FOOT_SNOW = 56;
    public static final byte END_ENY = 57;
    public static final byte END_MO_TO = 58;
    mVector VecEffEnd;
    mVector VecSubEffEnd;
    int x1000;
    int y1000;
    int lT_Arc;
    int gocT_Arc;
    Point pRebuild;
    public byte[] nFrame;
    int trans;
    public long timeRemove;
    public int timeDeley;
    public EffectAuto effAuto;
    byte typeSub;
    int numEffReplace;
    int plusGoc;

    public EffectEnd(int i, int i2, int i3) {
        int i4;
        int random;
        this.VecEffEnd = new mVector("EffectEnd VecEffEnd");
        this.VecSubEffEnd = new mVector("EffectEnd VecSubEffEnd");
        this.trans = 0;
        this.timeDeley = ObjectData.ITEM;
        this.numEffReplace = 0;
        this.f = -1;
        this.typeEffect = i;
        this.x = i2;
        this.y = i3;
        switch (i) {
            case 0:
                this.fraImgEff = new FrameImage(53);
                this.fRemove = 6;
                return;
            case 1:
                this.fraImgEff = new FrameImage(29);
                this.fRemove = 3;
                return;
            case 2:
                this.fraImgEff = new FrameImage(18);
                this.fRemove = 4;
                return;
            case 3:
                this.fraImgEff = new FrameImage(27);
                this.fRemove = 15;
                for (int i5 = 0; i5 < 7; i5++) {
                    Point point = new Point();
                    point.x = i2 + CRes.random_Am_0(22);
                    point.y = i3 + CRes.random_Am_0(8);
                    point.dis = CRes.random(this.fraImgEff.nFrame);
                    this.VecEffEnd.addElement(point);
                }
                return;
            case 4:
                this.fraImgEff = new FrameImage(4);
                this.fRemove = 5;
                return;
            case 5:
                this.fraImgEff = new FrameImage(51);
                this.fRemove = 6;
                return;
            case 6:
                this.fraImgEff = new FrameImage(52);
                this.fRemove = 4;
                return;
            case 7:
                this.fraImgEff = new FrameImage(54);
                this.fRemove = 4;
                return;
            case 8:
            case 16:
            case 17:
            case 18:
            case 20:
            case 22:
            case 23:
            case 31:
            case 37:
            case 41:
            case 47:
            case 49:
            case 50:
            case 52:
            case 53:
            default:
                return;
            case 9:
                if (GameCanvas.lowGraphic) {
                    removeEff();
                    return;
                }
                this.fraImgEff = new FrameImage(56);
                this.fRemove = CRes.random(11, 15);
                int random2 = CRes.random(3, 7);
                for (int i6 = 0; i6 < random2; i6++) {
                    Point point2 = new Point();
                    point2.x = i2 + CRes.random_Am_0(5);
                    point2.y = i3 + CRes.random_Am_0(7);
                    point2.frame = CRes.random(this.fraImgEff.nFrame);
                    point2.dis = CRes.random(2);
                    point2.vy = -CRes.random(6, 9);
                    point2.vx = CRes.random(1, 4);
                    if (i6 % 2 == 0) {
                        point2.vx = -point2.vx;
                    }
                    this.VecEffEnd.addElement(point2);
                }
                return;
            case 10:
                this.fraImgEff = new FrameImage(58);
                this.fRemove = 4;
                return;
            case 11:
                this.fraImgEff = new FrameImage(42);
                this.fRemove = 9;
                return;
            case 12:
                this.fraImgEff = new FrameImage(60);
                this.fRemove = 4;
                return;
            case 13:
                this.fraImgEff = new FrameImage(9);
                this.fRemove = 4;
                return;
            case 14:
            case 15:
                if (GameCanvas.lowGraphic) {
                    removeEff();
                    return;
                }
                if (i == 14) {
                    this.fraImgEff = new FrameImage(1);
                    random = CRes.random(7, 12);
                } else {
                    this.fraImgEff = new FrameImage(5);
                    random = CRes.random(7, 12);
                }
                this.fRemove = 12;
                for (int i7 = 0; i7 < random; i7++) {
                    Point point3 = new Point();
                    point3.x = i2 + CRes.random_Am_0(4);
                    point3.y = i3 + CRes.random_Am_0(5);
                    point3.frame = CRes.random(this.fraImgEff.nFrame);
                    point3.vy = -CRes.random(5, 9);
                    point3.vx = CRes.random(0, 3);
                    if (i7 % 2 == 0) {
                        point3.vx = -point3.vx;
                    }
                    this.VecEffEnd.addElement(point3);
                }
                return;
            case 19:
                if (GameCanvas.lowGraphic) {
                    removeEff();
                    return;
                }
                this.fraImgEff = new FrameImage(28);
                this.frame = CRes.random(2) == 0 ? 0 : 2;
                this.fRemove = 2;
                return;
            case 21:
            case 33:
                if (this.typeEffect == 33) {
                    this.fraImgEff = new FrameImage(2);
                    i4 = 30;
                    this.fRemove = 30;
                } else {
                    this.fraImgEff = new FrameImage(66);
                    i4 = 40;
                    this.fRemove = 12;
                }
                for (int i8 = 0; i8 < i4; i8++) {
                    Point point4 = new Point();
                    point4.x = i2 + CRes.random_Am_0(10);
                    point4.y = i3 - CRes.random_Am_0(8);
                    point4.frame = CRes.random(this.fraImgEff.nFrame);
                    point4.vy = -CRes.random(3, 11);
                    if (this.typeEffect == 33) {
                        point4.vx = CRes.random(0, 4);
                    } else {
                        point4.vx = CRes.random(0, 3);
                    }
                    if (i8 % 2 == 0) {
                        point4.vx = -point4.vx;
                    }
                    this.VecEffEnd.addElement(point4);
                }
                return;
            case 24:
                this.fraImgEff = new FrameImage(12);
                this.fRemove = CRes.random(18, 23);
                this.levelPaint = -1;
                return;
            case 25:
            case 28:
            case 40:
                if (this.typeEffect == 25) {
                    this.fraImgEff = new FrameImage(86);
                } else if (this.typeEffect == 28) {
                    this.fraImgEff = new FrameImage(96);
                } else if (this.typeEffect == 40) {
                    int tile = GameCanvas.loadmap.getTile(i2, i3);
                    if (tile == 2 || tile == -1) {
                        return;
                    } else {
                        this.fraImgEff = new FrameImage(19);
                    }
                }
                this.levelPaint = -1;
                this.fRemove = CRes.random(74, 85);
                fRemove_Low(20);
                return;
            case 26:
                this.levelPaint = -1;
                if (CRes.random(2) == 0) {
                    this.fraImgEff = new FrameImage(90);
                } else {
                    this.fraImgEff = new FrameImage(13);
                }
                this.fRemove = CRes.random(74, 85);
                fRemove_Low(20);
                return;
            case 27:
            case 30:
                this.fRemove = 20;
                this.levelPaint = -1;
                if (this.typeEffect == 27) {
                    this.fraImgEff = new FrameImage(92);
                } else if (this.typeEffect == 30) {
                    this.fraImgEff = new FrameImage(115);
                }
                for (int i9 = 0; i9 < 3; i9++) {
                    Point point5 = new Point();
                    point5.x = i2 + CRes.random_Am_0(3);
                    point5.y = i3 + CRes.random_Am_0(3);
                    if (CRes.random(2) == 0) {
                        if (i9 % 2 == 0) {
                            point5.vx = CRes.random(3);
                        } else {
                            point5.vx = -CRes.random(3);
                        }
                    } else {
                        point5.vx = CRes.random_Am_0(3);
                    }
                    point5.vy = -5;
                    point5.fRe = CRes.random(6, 12);
                    point5.frame = i9;
                    this.VecEffEnd.addElement(point5);
                }
                return;
            case 29:
                this.fraImgEff = new FrameImage(105);
                this.fRemove = CRes.random(11, 15);
                int random3 = CRes.random(3, 6);
                for (int i10 = 0; i10 < random3; i10++) {
                    Point point6 = new Point();
                    point6.x = i2 + CRes.random_Am_0(5);
                    point6.y = i3 + CRes.random_Am_0(7);
                    point6.frame = CRes.random(this.fraImgEff.nFrame);
                    point6.dis = CRes.random(2);
                    point6.vy = -CRes.random(6, 9);
                    point6.vx = CRes.random(1, 4);
                    if (i10 % 2 == 0) {
                        point6.vx = -point6.vx;
                    }
                    this.VecEffEnd.addElement(point6);
                }
                return;
            case 32:
                create_Level_up();
                return;
            case 34:
                this.fraImgEff = new FrameImage(14);
                this.fRemove = 18;
                return;
            case 35:
                this.fraImgEff = new FrameImage(122);
                this.fRemove = 6;
                return;
            case 36:
                this.fraImgEff = new FrameImage(4);
                this.fRemove = 10;
                return;
            case 38:
                this.fraImgEff = new FrameImage(65);
                this.fraImgSubEff = new FrameImage(122);
                this.fraImgSub2Eff = new FrameImage(14);
                this.fRemove = 12;
                return;
            case 39:
                this.fRemove = 55;
                return;
            case 42:
                this.fraImgEff = new FrameImage(7);
                this.fraImgSubEff = new FrameImage(2);
                this.fRemove = 20;
                this.gocT_Arc = 0;
                for (int i11 = 0; i11 < 8; i11++) {
                    int random4 = CRes.random(4, 6);
                    Point point7 = new Point();
                    point7.x = i2 * ObjectData.MONSTER;
                    point7.y = i3 * ObjectData.MONSTER;
                    point7.vx = CRes.cos(CRes.fixangle(this.gocT_Arc)) * random4;
                    point7.vy = CRes.sin(CRes.fixangle(this.gocT_Arc)) * random4;
                    this.gocT_Arc += 45;
                    this.VecEffEnd.addElement(point7);
                }
                return;
            case 43:
                this.fraImgEff = new FrameImage(124);
                this.fRemove = 10;
                return;
            case 44:
                this.fraImgEff = new FrameImage(70);
                this.fRemove = 4;
                return;
            case 45:
                this.fraImgEff = new FrameImage(63);
                this.fRemove = 8;
                return;
            case 46:
                this.fraImgEff = new FrameImage(86);
                this.levelPaint = -1;
                this.fRemove = 6;
                return;
            case 48:
                this.fraImgEff = new FrameImage(134);
                this.fRemove = 8;
                return;
            case 51:
                this.fraImgEff = new FrameImage(140);
                this.fRemove = 6;
                return;
            case 54:
                this.fraImgEff = new FrameImage(144);
                this.fRemove = 10;
                return;
        }
    }

    public EffectEnd(int i, int i2, int i3, int i4, byte b) {
        this.VecEffEnd = new mVector("EffectEnd VecEffEnd");
        this.VecSubEffEnd = new mVector("EffectEnd VecSubEffEnd");
        this.trans = 0;
        this.timeDeley = ObjectData.ITEM;
        this.numEffReplace = 0;
        this.f = -1;
        this.typeEffect = i;
        this.x = i2;
        this.y = i3;
        this.Direction = i4;
        switch (i) {
            case 55:
                this.nFrame = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2};
                int tile = GameCanvas.loadmap.getTile(i2, i3);
                if (tile == 2 || tile == -1) {
                    return;
                }
                this.fraImgEff = new FrameImage(145);
                this.levelPaint = b;
                return;
            case 56:
                this.fraImgEff = new FrameImage(152);
                this.levelPaint = b;
                this.fRemove = CRes.random(20, 30);
                fRemove_Low(20);
                return;
            case 57:
            default:
                return;
            case 58:
                this.fraImgEff = new FrameImage(164);
                this.levelPaint = b;
                this.fRemove = CRes.random(20, 30);
                fRemove_Low(20);
                return;
        }
    }

    public EffectEnd(int i, int i2, int i3, int i4) {
        this.VecEffEnd = new mVector("EffectEnd VecEffEnd");
        this.VecSubEffEnd = new mVector("EffectEnd VecSubEffEnd");
        this.trans = 0;
        this.timeDeley = ObjectData.ITEM;
        this.numEffReplace = 0;
        this.f = -1;
        this.typeEffect = i;
        this.x = i3;
        this.y = i4;
        this.fraImgEff = new FrameImage(i2);
        this.fRemove = this.fraImgEff.getNFrame();
    }

    public EffectEnd(int i, int i2, int i3, long j) {
        this.VecEffEnd = new mVector("EffectEnd VecEffEnd");
        this.VecSubEffEnd = new mVector("EffectEnd VecSubEffEnd");
        this.trans = 0;
        this.timeDeley = ObjectData.ITEM;
        this.numEffReplace = 0;
        this.f = -1;
        this.typeEffect = i;
        this.x = i2;
        this.y = i3;
        switch (i) {
            case 47:
                this.timeRemove = j + this.timeDeley;
                this.fraImgEff = new FrameImage(86);
                this.levelPaint = -1;
                this.fRemove = CRes.random(74, 85);
                fRemove_Low(20);
                return;
            case 48:
            default:
                return;
            case 49:
                this.timeRemove = j;
                this.effAuto = new EffectAuto(51, i2, i3, 0, 0, 0, 0);
                this.levelPaint = -1;
                this.fRemove = 6;
                return;
            case 50:
                this.timeRemove = j;
                this.fraImgEff = new FrameImage(137);
                this.levelPaint = -1;
                this.fRemove = 12;
                return;
        }
    }

    public EffectEnd(int i, int i2, int i3, byte b) {
        this.VecEffEnd = new mVector("EffectEnd VecEffEnd");
        this.VecSubEffEnd = new mVector("EffectEnd VecSubEffEnd");
        this.trans = 0;
        this.timeDeley = ObjectData.ITEM;
        this.numEffReplace = 0;
        this.f = -1;
        this.typeEffect = i;
        this.typeSub = b;
        this.x = i2;
        this.y = i3;
    }

    public EffectEnd(int i, int i2, int i3, int i4, int i5, int i6) {
        this.VecEffEnd = new mVector("EffectEnd VecEffEnd");
        this.VecSubEffEnd = new mVector("EffectEnd VecSubEffEnd");
        this.trans = 0;
        this.timeDeley = ObjectData.ITEM;
        this.numEffReplace = 0;
        this.typeEffect = i;
        this.x = i2;
        this.y = i3;
        this.toX = i4;
        this.toY = i5;
        switch (i) {
            case 31:
                create_Arc_Big_Small();
                return;
            case 37:
                this.fraImgEff = new FrameImage(7);
                this.fraImgSubEff = new FrameImage(2);
                this.fRemove = 100;
                this.lT_Arc = 40;
                this.gocT_Arc = 0;
                this.numEffReplace = i6;
                this.plusGoc = 360 / i6;
                return;
            case 41:
                this.fraImgEff = new FrameImage(7);
                this.fraImgSubEff = new FrameImage(2);
                this.fRemove = 18;
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0004. Please report as an issue. */
    @Override // GameEffect.MainEffect
    public void paint(mGraphics mgraphics) {
        int i;
        int i2;
        int i3;
        try {
            switch (this.typeEffect) {
                case 0:
                case 2:
                case 5:
                case 11:
                case 35:
                case 36:
                case 51:
                case 54:
                case 57:
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill((this.f / 2) % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mgraphics);
                    }
                    return;
                case 1:
                case 4:
                case 6:
                case 7:
                case 10:
                case 12:
                case 13:
                case 44:
                case 45:
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(this.f % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mgraphics);
                    }
                    return;
                case 3:
                    for (int i4 = 0; i4 < this.VecEffEnd.size(); i4++) {
                        Point point = (Point) this.VecEffEnd.elementAt(i4);
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(((point.f + this.f) / 2) % this.fraImgEff.nFrame, point.x, point.y, 0, 3, mgraphics);
                        }
                    }
                    return;
                case 8:
                case 16:
                case 17:
                case 18:
                case 20:
                case 22:
                case 23:
                case 39:
                case 52:
                case 53:
                default:
                    return;
                case 9:
                case 27:
                case 29:
                case 30:
                    for (int i5 = 0; i5 < this.VecEffEnd.size(); i5++) {
                        Point point2 = (Point) this.VecEffEnd.elementAt(i5);
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(point2.frame, point2.x, point2.y, 0, 3, mgraphics);
                        }
                    }
                    return;
                case 14:
                case 15:
                case 21:
                    for (int i6 = 0; i6 < this.VecEffEnd.size(); i6++) {
                        Point point3 = (Point) this.VecEffEnd.elementAt(i6);
                        if (this.f < (this.fRemove / 3) * 2) {
                            if (this.fraImgEff != null) {
                                this.fraImgEff.drawFrameEffectSkill(point3.frame, point3.x, point3.y, 0, 3, mgraphics);
                            }
                        } else if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(point3.frame == 0 ? 3 : point3.frame, point3.x, point3.y, 0, 3, mgraphics);
                        }
                    }
                    return;
                case 19:
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(this.frame + (this.f % this.fraImgEff.nFrame), this.x - (this.fraImgEff.frameWidth / 2), this.y - (this.fraImgEff.frameHeight / 2), 0, 0, mgraphics);
                    }
                    return;
                case 24:
                    int i7 = 0;
                    if (this.f > this.fRemove - 5) {
                        i7 = (this.f - (this.fRemove - 4)) / 2;
                    }
                    if (i7 < 3 && this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(i7, this.x, this.y, 0, 3, mgraphics);
                    }
                    return;
                case 25:
                case 28:
                case 40:
                case 46:
                    if (this.f <= this.fRemove) {
                        if (this.f < 2) {
                            int i8 = this.f;
                        }
                        if (this.f > this.fRemove - 5) {
                            i3 = ((this.fRemove - 1) - this.f) / 2;
                        } else {
                            i3 = 2;
                        }
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(i3, this.x, this.y, 0, 3, mgraphics);
                        }
                    }
                    return;
                case 26:
                    if (this.f < 2) {
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(this.f, this.x, this.y, 0, 3, mgraphics);
                        }
                    } else if (this.f < this.fRemove - 4) {
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(2, this.x, this.y, 0, 3, mgraphics);
                        }
                    } else if (this.f < this.fRemove && this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(((this.fRemove - 1) - this.f) / 2, this.x, this.y, 0, 3, mgraphics);
                    }
                    return;
                case 31:
                    for (int i9 = 0; i9 < this.VecEffEnd.size(); i9++) {
                        Point point4 = (Point) this.VecEffEnd.elementAt(i9);
                        if (this.fraImgSubEff != null) {
                            this.fraImgSubEff.drawFrameEffectSkill(point4.f / 2, point4.x, point4.y, 0, 3, mgraphics);
                        }
                    }
                    if (this.lT_Arc > 5 && this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill(this.pRebuild.v, (this.pRebuild.x2 + this.pRebuild.vx) / ObjectData.MONSTER, (this.pRebuild.y2 + this.pRebuild.vy) / ObjectData.MONSTER, 0, 3, mgraphics);
                    }
                    return;
                case 32:
                    if (this.fraImgSubEff != null) {
                        this.fraImgSubEff.drawFrameEffectSkill(0, this.x, this.y - ((this.f - (this.fRemove / 2)) * 2), 0, 3, mgraphics);
                    }
                    return;
                case 33:
                    for (int i10 = 0; i10 < this.VecEffEnd.size(); i10++) {
                        Point point5 = (Point) this.VecEffEnd.elementAt(i10);
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill((point5.frame + (point5.f / 2)) % this.fraImgEff.nFrame, point5.x, point5.y, 0, 3, mgraphics);
                        }
                    }
                    return;
                case 34:
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill((this.f / 3) % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mgraphics);
                    }
                    return;
                case 37:
                case 41:
                case 42:
                    for (int i11 = 0; i11 < this.VecEffEnd.size(); i11++) {
                        Point point6 = (Point) this.VecEffEnd.elementAt(i11);
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill((point6.f / 2) % this.fraImgEff.nFrame, point6.x / ObjectData.MONSTER, point6.y / ObjectData.MONSTER, 0, 3, mgraphics);
                        }
                    }
                    for (int i12 = 0; i12 < this.VecSubEffEnd.size(); i12++) {
                        Point point7 = (Point) this.VecSubEffEnd.elementAt(i12);
                        if (this.fraImgSubEff != null) {
                            this.fraImgSubEff.drawFrameEffectSkill(point7.f / 2, point7.x, point7.y, 0, 3, mgraphics);
                        }
                    }
                    return;
                case 38:
                    if (this.f < 6) {
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(this.f, this.x, this.y, 0, 3, mgraphics);
                        }
                        if (this.fraImgSubEff != null) {
                            this.fraImgSubEff.drawFrameEffectSkill(2 - (this.f / 2), this.x, this.y, 0, 33, mgraphics);
                        }
                    } else if (this.fraImgSub2Eff != null) {
                        this.fraImgSub2Eff.drawFrameEffectSkill(this.f - 6, this.x, this.y - 20, 0, 3, mgraphics);
                    }
                    return;
                case 43:
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill((this.f / 2) % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mgraphics);
                    }
                    return;
                case 47:
                    if (this.timeRemove - mSystem.currentTimeMillis() >= 0) {
                        long currentTimeMillis = (this.timeRemove - mSystem.currentTimeMillis()) / 1000;
                        if (currentTimeMillis >= 2) {
                            i2 = 2;
                        } else if (currentTimeMillis >= 1) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(i2, this.x, this.y, 0, 3, mgraphics);
                        }
                    }
                    return;
                case 48:
                case 50:
                    if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrameEffectSkill((this.f / 2) % this.fraImgEff.nFrame, this.x, this.y, 0, 3, mgraphics);
                    }
                    return;
                case 49:
                    if (this.effAuto != null) {
                        this.effAuto.paint(mgraphics);
                    }
                    return;
                case 55:
                    try {
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(this.nFrame[this.f], this.x, this.y, this.trans, 3, mgraphics);
                        }
                    } catch (Exception e) {
                        mSystem.println(new StringBuffer().append("err mountdust f:").append(this.nFrame.length).append("  ").append(this.f).toString());
                    }
                    return;
                case 56:
                    if (this.f <= this.fRemove) {
                        if (this.f < 2) {
                            int i13 = this.f;
                        }
                        if (this.f > this.fRemove - 5) {
                            i = ((this.fRemove - 1) - this.f) / 2;
                        } else {
                            i = 2;
                        }
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(i, this.x, this.y, this.trans, 3, mgraphics);
                        }
                    }
                    return;
                case 58:
                    if (this.f <= this.fRemove) {
                        if (this.f < 2) {
                            int i14 = this.f;
                        }
                        if (this.f > this.fRemove - 5) {
                            int i15 = ((this.fRemove - 1) - this.f) / 2;
                        }
                        if (this.fraImgEff != null) {
                            this.fraImgEff.drawFrameEffectSkill(0, this.x, this.y, this.trans, 3, mgraphics);
                        }
                    }
                    return;
            }
        } catch (Exception e2) {
            mSystem.println(new StringBuffer().append("/ ").append(this.typeEffect).append(e2.toString()).toString());
        }
    }

    @Override // GameEffect.MainEffect
    public void update() {
        this.f++;
        this.subf++;
        switch (this.typeEffect) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
            case 7:
            case 10:
            case 11:
            case 12:
            case 13:
            case 19:
            case 24:
            case 25:
            case 26:
            case 28:
            case 34:
            case 35:
            case 40:
            case 44:
            case 46:
            case 51:
            case 54:
            case 57:
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 3:
                for (int i = 0; i < this.VecEffEnd.size(); i++) {
                    Point point = (Point) this.VecEffEnd.elementAt(i);
                    if (CRes.random(3) == 0) {
                        point.x = this.x + CRes.random_Am_0(22);
                        point.y = this.y + CRes.random_Am_0(8);
                        point.f = CRes.random(this.fraImgEff.nFrame);
                    }
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 8:
            case 16:
            case 17:
            case 18:
            case 20:
            case 22:
            case 23:
            case 52:
            case 53:
            default:
                return;
            case 9:
            case 14:
            case 15:
            case 29:
                for (int i2 = 0; i2 < this.VecEffEnd.size(); i2++) {
                    Point point2 = (Point) this.VecEffEnd.elementAt(i2);
                    point2.update();
                    point2.vy++;
                    if (this.f == this.fRemove && GameScreen.isWater(point2.x, point2.y)) {
                        GameScreen.addEffectEndKill(19, point2.x, point2.y);
                    }
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 21:
                for (int i3 = 0; i3 < this.VecEffEnd.size(); i3++) {
                    Point point3 = (Point) this.VecEffEnd.elementAt(i3);
                    point3.update();
                    if (point3.vy < 0) {
                        point3.vy++;
                    } else {
                        point3.vy = 0;
                        point3.vx = 0;
                    }
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 27:
            case 30:
                for (int i4 = 0; i4 < this.VecEffEnd.size(); i4++) {
                    Point point4 = (Point) this.VecEffEnd.elementAt(i4);
                    point4.f++;
                    if (point4.f < point4.fRe) {
                        point4.x += point4.vx;
                        point4.y += point4.vy;
                    }
                    point4.vy++;
                }
                if (this.f > this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 31:
                int i5 = 0;
                while (i5 < this.VecEffEnd.size()) {
                    Point point5 = (Point) this.VecEffEnd.elementAt(i5);
                    point5.f++;
                    if (point5.f / 2 >= 4) {
                        this.VecEffEnd.removeElement(point5);
                        i5--;
                    }
                    i5++;
                }
                this.pRebuild.v = (this.f / 2) % 2;
                if (this.f > 4) {
                    if (this.lT_Arc <= 5) {
                        if (this.VecEffEnd.size() == 0) {
                            removeEff();
                            return;
                        }
                        return;
                    }
                    this.pRebuild.f += 14;
                    this.pRebuild.vy = CRes.sin(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
                    this.pRebuild.vx = CRes.cos(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
                    if (this.f % 2 == 0) {
                        this.lT_Arc--;
                        this.pRebuild.f += 14;
                    }
                    Point point6 = new Point();
                    point6.x = ((this.pRebuild.x2 + this.pRebuild.vx) / ObjectData.MONSTER) + CRes.random_Am(-1, 2);
                    point6.y = ((this.pRebuild.y2 + this.pRebuild.vy) / ObjectData.MONSTER) + CRes.random_Am(-1, 2);
                    this.VecEffEnd.addElement(point6);
                    return;
                }
                return;
            case 32:
                if (this.f >= this.fRemove) {
                    removeEff();
                }
                if (this.f < this.fRemove / 2) {
                    for (int i6 = 0; i6 < this.VecEffEnd.size(); i6++) {
                        Point point7 = (Point) this.VecEffEnd.elementAt(i6);
                        if (CRes.random(3) == 0) {
                            if (point7.fRe == 1) {
                                point7.fRe = 0;
                            } else {
                                point7.fRe = 1;
                                point7.frame = CRes.random(4);
                            }
                        }
                    }
                    return;
                }
                return;
            case 33:
                for (int i7 = 0; i7 < this.VecEffEnd.size(); i7++) {
                    Point point8 = (Point) this.VecEffEnd.elementAt(i7);
                    point8.update();
                    point8.vy++;
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 36:
                if (this.f == 1) {
                    GameScreen.addEffectEndKill(14, this.x, this.y);
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 37:
                if (this.f % 3 == 0 && this.VecEffEnd.size() < this.numEffReplace && this.f < 50) {
                    Point point9 = new Point();
                    point9.x = this.x * ObjectData.MONSTER;
                    point9.y = this.y * ObjectData.MONSTER;
                    point9.vx = CRes.cos(CRes.fixangle(this.gocT_Arc)) * 4;
                    point9.vy = CRes.sin(CRes.fixangle(this.gocT_Arc)) * 4;
                    point9.x2 = this.toX * ObjectData.MONSTER;
                    point9.y2 = this.toY * ObjectData.MONSTER;
                    this.gocT_Arc += this.plusGoc;
                    this.VecEffEnd.addElement(point9);
                    if (this.VecEffEnd.size() == this.numEffReplace) {
                        this.f = 50;
                    }
                }
                if (this.f == 80) {
                    for (int i8 = 0; i8 < this.VecEffEnd.size(); i8++) {
                        Point point10 = (Point) this.VecEffEnd.elementAt(i8);
                        point10.vx = (point10.x2 - point10.x) / 8;
                        point10.vy = (point10.y2 - point10.y) / 8;
                        point10.f = 100;
                    }
                }
                int i9 = 0;
                while (i9 < this.VecEffEnd.size()) {
                    Point point11 = (Point) this.VecEffEnd.elementAt(i9);
                    point11.update();
                    if (point11.f % 3 == 0) {
                        Point point12 = new Point();
                        point12.x = (point11.x / ObjectData.MONSTER) + CRes.random_Am(5, 12);
                        point12.y = (point11.y / ObjectData.MONSTER) + CRes.random_Am(5, 12);
                        this.VecSubEffEnd.addElement(point12);
                    }
                    if (point11.f == 10) {
                        point11.vx = 0;
                        point11.vy = 0;
                    }
                    if (point11.f > 108) {
                        this.VecEffEnd.removeElement(point11);
                        i9--;
                    }
                    i9++;
                }
                int i10 = 0;
                while (i10 < this.VecSubEffEnd.size()) {
                    Point point13 = (Point) this.VecSubEffEnd.elementAt(i10);
                    point13.f++;
                    if (point13.f / 2 >= 4) {
                        this.VecSubEffEnd.removeElement(point13);
                        i10--;
                    }
                    i10++;
                }
                if (this.f > 80 && this.VecEffEnd.size() == 0) {
                    TabRebuildItem.addEffectEnd_ReBuild_ss(32, this.toX, this.toY - 11);
                    TabRebuildItem.addEffectEnd_ReBuild_ss(33, this.toX, this.toY);
                    TabRebuildItem.addEffectEnd_ReBuild_ss(34, this.toX, this.toY);
                    removeEff();
                    return;
                }
                return;
            case 38:
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 39:
                if (CRes.random(2) == 0) {
                    GameScreen.addEffectKill(83, this.x, this.y, 400, (short) -1, (byte) 0);
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 41:
                if (this.f == 1) {
                    Point point14 = new Point();
                    point14.x = this.x * ObjectData.MONSTER;
                    point14.y = this.y * ObjectData.MONSTER;
                    point14.x2 = this.toX * ObjectData.MONSTER;
                    point14.y2 = this.toY * ObjectData.MONSTER;
                    point14.vx = 0;
                    point14.vy = 0;
                    this.VecEffEnd.addElement(point14);
                }
                int i11 = 0;
                while (i11 < this.VecEffEnd.size()) {
                    Point point15 = (Point) this.VecEffEnd.elementAt(i11);
                    point15.update();
                    if (point15.f == 4) {
                        point15.vx = (point15.x2 - point15.x) / 12;
                        point15.vy = (point15.y2 - point15.y) / 12;
                    }
                    if (point15.f % 3 == 0) {
                        Point point16 = new Point();
                        point16.x = (point15.x / ObjectData.MONSTER) + CRes.random_Am(5, 12);
                        point16.y = (point15.y / ObjectData.MONSTER) + CRes.random_Am(5, 12);
                        this.VecSubEffEnd.addElement(point16);
                    }
                    if (point15.f == 16) {
                        point15.vx = 0;
                        point15.vy = 0;
                        this.VecEffEnd.removeElement(point15);
                        i11--;
                    }
                    i11++;
                }
                int i12 = 0;
                while (i12 < this.VecSubEffEnd.size()) {
                    Point point17 = (Point) this.VecSubEffEnd.elementAt(i12);
                    point17.f++;
                    if (point17.f / 2 >= 4) {
                        this.VecSubEffEnd.removeElement(point17);
                        i12--;
                    }
                    i12++;
                }
                if (this.f >= this.fRemove && this.VecSubEffEnd.size() == 0 && this.VecEffEnd.size() == 0) {
                    removeEff();
                    return;
                }
                return;
            case 42:
                int i13 = 0;
                while (i13 < this.VecEffEnd.size()) {
                    Point point18 = (Point) this.VecEffEnd.elementAt(i13);
                    point18.update();
                    if (point18.f % 3 == 0) {
                        Point point19 = new Point();
                        point19.x = (point18.x / ObjectData.MONSTER) + CRes.random_Am(5, 12);
                        point19.y = (point18.y / ObjectData.MONSTER) + CRes.random_Am(5, 12);
                        this.VecSubEffEnd.addElement(point19);
                    }
                    if (point18.f == this.fRemove) {
                        point18.vx = 0;
                        point18.vy = 0;
                        this.VecEffEnd.removeElement(point18);
                        i13--;
                    }
                    i13++;
                }
                int i14 = 0;
                while (i14 < this.VecSubEffEnd.size()) {
                    Point point20 = (Point) this.VecSubEffEnd.elementAt(i14);
                    point20.f++;
                    if (point20.f / 2 >= 4) {
                        this.VecSubEffEnd.removeElement(point20);
                        i14--;
                    }
                    i14++;
                }
                if (this.f >= this.fRemove && this.VecEffEnd.size() == 0 && this.VecSubEffEnd.size() == 0) {
                    removeEff();
                    return;
                }
                return;
            case 43:
                if (this.f == 1) {
                    GameScreen.addEffectEndKill(14, this.x, this.y);
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 45:
                if (this.f == 1) {
                    GameScreen.addEffectEndKill(9, this.x, this.y + 20);
                    GameScreen.addEffectEndKill(46, this.x, this.y + 20);
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 47:
            case 50:
                if (this.timeRemove - mSystem.currentTimeMillis() < 0) {
                    removeEff();
                    return;
                }
                return;
            case 48:
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 49:
                if (this.effAuto != null) {
                    this.effAuto.update();
                }
                if (this.timeRemove - mSystem.currentTimeMillis() < 0) {
                    removeEff();
                    GameScreen.addEffectEndKill(15, this.x, this.y);
                    return;
                }
                return;
            case 55:
                if (this.f < 0) {
                    this.f = 0;
                }
                if (this.Direction == 2) {
                    this.trans = 0;
                } else if (this.Direction == 3) {
                    this.trans = 2;
                }
                if (this.Direction == 1) {
                    this.trans = 3;
                }
                if (this.Direction == 0) {
                    this.trans = 0;
                }
                if (this.f > this.nFrame.length - 1) {
                    this.f = 0;
                    removeEff();
                    return;
                }
                return;
            case 56:
                if (this.Direction == 2) {
                    this.trans = 4;
                } else if (this.Direction == 3) {
                    this.trans = 7;
                }
                if (this.Direction == 1) {
                    this.trans = 0;
                }
                if (this.Direction == 0) {
                    this.trans = 3;
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
            case 58:
                if (this.Direction == 2) {
                    this.trans = 4;
                } else if (this.Direction == 3) {
                    this.trans = 7;
                }
                if (this.Direction == 1) {
                    this.trans = 0;
                }
                if (this.Direction == 0) {
                    this.trans = 3;
                }
                if (this.f >= this.fRemove) {
                    removeEff();
                    return;
                }
                return;
        }
    }

    public void removeEff() {
        this.isStop = true;
        this.isRemove = true;
    }

    private void create_Arc_Big_Small() {
        this.fraImgEff = new FrameImage(7);
        this.fraImgSubEff = new FrameImage(2);
        this.x1000 = this.x * ObjectData.MONSTER;
        this.y1000 = this.y * ObjectData.MONSTER;
        this.fRemove = 15;
        this.lT_Arc = MainObject.getDistance(this.toX, this.toY, this.x, this.y);
        this.gocT_Arc = CRes.angle(this.x - this.toX, this.y - this.toY);
        this.pRebuild = new Point(this.toX * ObjectData.MONSTER, this.toY * ObjectData.MONSTER);
        this.pRebuild.x2 = this.toX * ObjectData.MONSTER;
        this.pRebuild.y2 = this.toY * ObjectData.MONSTER;
        this.pRebuild.f = this.gocT_Arc;
        this.pRebuild.vy = CRes.sin(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
        this.pRebuild.vx = CRes.cos(CRes.fixangle(this.pRebuild.f % 360)) * this.lT_Arc;
        this.pRebuild.v = 0;
    }

    private void create_Level_up() {
        this.fraImgSubEff = new FrameImage(67);
        this.fRemove = 23;
    }

    public void fRemove_Low(int i) {
        if (GameCanvas.lowGraphic) {
            this.fRemove = CRes.random(i - 4, i + 5);
        }
    }
}
