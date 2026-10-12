package Skill;

import GameObjects.MainObject;

/* loaded from: KnightOnline_v403.jar:Skill/SplashSkill.class */
public class SplashSkill {
    public int min;
    public static final byte PLASH_MIN_4 = 0;
    public static final byte PLASH_MIN_6 = 1;
    public static final byte PLASH_MIN_12 = 2;
    public static final byte PLASH_SUNG_0 = 3;
    public static final byte PLASH_2KIEM_LV2_LV4 = 4;
    public static final byte PLASH_MIN_8 = 5;
    public static final byte PLASH_SUNG_LASER = 6;
    public static final byte PLASH_MIN_16 = 7;
    public static final byte PLASH_MIN_10 = 8;
    public static final byte PLASH_BUFF = 9;
    public static final byte PLASH_SUNG_LV4 = 10;
    public static final byte PLASH_SUNG_MIN_8 = 11;
    public static final byte PLASH_KIEM_LV5 = 12;
    public static final byte PLASH_SUNG_BAODAN = 13;
    public static final byte PLASH_NULL = 14;
    public static final byte PLASH_SUNG_DAYDAN = 15;
    public static final byte PLASH_SUNG_MIN_6 = 16;
    public int typePlash = 0;
    boolean isSung = false;

    public void setPlash(int i) {
        this.typePlash = i;
        this.isSung = false;
        switch (this.typePlash) {
            case 0:
                this.min = 4;
                return;
            case 1:
                this.min = 6;
                return;
            case 2:
                this.min = 12;
                return;
            case 3:
            case 4:
            case 9:
            case 12:
            case 13:
            case 14:
            case 15:
            default:
                return;
            case 5:
                this.min = 8;
                return;
            case 6:
                this.min = 30;
                this.isSung = true;
                return;
            case 7:
                this.min = 16;
                return;
            case 8:
                this.min = 10;
                return;
            case 10:
                this.min = 22;
                this.isSung = true;
                return;
            case 11:
                this.min = 11;
                this.isSung = true;
                return;
            case 16:
                this.min = 6;
                this.isSung = true;
                return;
        }
    }

    public void update(MainObject mainObject) {
        switch (this.typePlash) {
            case 3:
                if (mainObject.fplash < 2) {
                    mainObject.frame = 5;
                    mainObject.weapon_frame = 4;
                    break;
                } else {
                    mainObject.setEye(1);
                    mainObject.weapon_frame = mainObject.fplash + 2;
                    if (mainObject.fplash < 6) {
                        mainObject.frame = 4;
                        break;
                    } else {
                        mainObject.Action = 0;
                        mainObject.frame = 0;
                        mainObject.f = 0;
                        mainObject.weapon_frame = 0;
                        break;
                    }
                }
            case 4:
                if (mainObject.fplash < 4) {
                    mainObject.frame = 4;
                    mainObject.weapon_frame = 4;
                    break;
                } else {
                    mainObject.setEye(1);
                    mainObject.weapon_frame = mainObject.fplash;
                    if (mainObject.fplash < 10) {
                        mainObject.frame = 5;
                        break;
                    } else {
                        mainObject.Action = 0;
                        mainObject.frame = 0;
                        mainObject.f = 0;
                        mainObject.weapon_frame = 0;
                        break;
                    }
                }
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            default:
                if (mainObject.fplash < this.min) {
                    mainObject.frame = 4;
                    if (this.isSung) {
                        mainObject.frame = 5;
                    }
                    mainObject.weapon_frame = 4;
                    if (mainObject.weapon_frame > 4) {
                        mainObject.weapon_frame = 4;
                        break;
                    }
                } else {
                    mainObject.setEye(1);
                    mainObject.weapon_frame = mainObject.fplash - (this.min - 4);
                    if (mainObject.fplash < this.min + 4) {
                        mainObject.frame = 5;
                        if (this.isSung) {
                            mainObject.frame = 4;
                            break;
                        }
                    } else {
                        mainObject.Action = 0;
                        mainObject.frame = 0;
                        mainObject.f = 0;
                        mainObject.weapon_frame = 0;
                        break;
                    }
                }
                break;
            case 9:
                if (mainObject.fplash < 30) {
                    mainObject.frame = 4;
                    if (mainObject.clazz == 3) {
                        mainObject.frame = 5;
                    }
                    mainObject.weapon_frame = 4;
                    break;
                } else {
                    mainObject.Action = 0;
                    mainObject.frame = 0;
                    mainObject.f = 0;
                    mainObject.weapon_frame = 0;
                    break;
                }
            case 12:
                if (mainObject.fplash >= 30) {
                    mainObject.Action = 0;
                    mainObject.frame = 0;
                    mainObject.f = 0;
                    mainObject.weapon_frame = 0;
                    break;
                }
                break;
            case 13:
                if (mainObject.fplash < 12) {
                    mainObject.frame = 5;
                    mainObject.weapon_frame = 4;
                    break;
                } else if (mainObject.fplash < 30) {
                    if (mainObject.fplash < 14) {
                        mainObject.weapon_frame = mainObject.fplash - 8;
                        mainObject.frame = 4;
                        break;
                    } else if (mainObject.fplash % 2 == 0) {
                        mainObject.setEye(1);
                        mainObject.weapon_frame = 4;
                        mainObject.frame = 5;
                        break;
                    } else {
                        mainObject.weapon_frame = 7;
                        mainObject.frame = 4;
                        break;
                    }
                } else {
                    mainObject.Action = 0;
                    mainObject.frame = 0;
                    mainObject.f = 0;
                    mainObject.weapon_frame = 0;
                    break;
                }
            case 14:
                if (mainObject.clazz == 3) {
                    if (mainObject.fplash < 2) {
                        mainObject.frame = 5;
                        mainObject.weapon_frame = 4;
                        break;
                    } else {
                        mainObject.setEye(1);
                        mainObject.weapon_frame = mainObject.fplash + 2;
                        if (mainObject.fplash < 6) {
                            mainObject.frame = 4;
                            break;
                        } else {
                            mainObject.Action = 0;
                            mainObject.frame = 0;
                            mainObject.f = 0;
                            mainObject.weapon_frame = 0;
                            break;
                        }
                    }
                } else if (mainObject.fplash < 4) {
                    mainObject.frame = 4;
                    mainObject.weapon_frame = 4;
                    break;
                } else {
                    mainObject.setEye(1);
                    mainObject.weapon_frame = mainObject.fplash;
                    if (mainObject.fplash < 8) {
                        mainObject.frame = 5;
                        break;
                    } else {
                        mainObject.Action = 0;
                        mainObject.frame = 0;
                        mainObject.f = 0;
                        mainObject.weapon_frame = 0;
                        break;
                    }
                }
            case 15:
                if (mainObject.fplash < 26) {
                    mainObject.frame = 5;
                    mainObject.weapon_frame = 4;
                    if (mainObject.fplash >= 12) {
                        mainObject.setEye(1);
                        break;
                    }
                } else if (mainObject.fplash < 30) {
                    mainObject.weapon_frame = mainObject.fplash - 22;
                    mainObject.frame = 4;
                    if (mainObject.weapon_frame > 7) {
                        mainObject.weapon_frame = 7;
                        break;
                    }
                } else {
                    mainObject.Action = 0;
                    mainObject.frame = 0;
                    mainObject.f = 0;
                    mainObject.weapon_frame = 0;
                    break;
                }
                break;
        }
        mainObject.fplash++;
    }
}
