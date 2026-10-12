package Model;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;

/* loaded from: KnightOnline_v403.jar:Model/AvMain.class */
public class AvMain {
    public iCommand left;
    public iCommand right;
    public iCommand center;
    public static int wimg;
    public static mImage imgtextfield;
    public static mImage imgInfo;
    public static mImage imghpmp;
    public static mImage imgcolorhpmp;
    public static mImage imgPopup;
    public static mImage imgBackInfo;
    public static mImage imgWorldMap;
    public static mImage imgSelect;
    public static mImage imgFocusMap;
    public static mImage imgDelaySkill;
    public static mImage imgLoadImg;
    public static mImage imgEyeDie;
    public static mImage imgHotKey;
    public static mImage imgHotKey2;
    public static mImage imgMess;
    public static mImage imgColorItem;
    public static mImage imgicongt;
    public static mImage imgGlass;
    public static mImage imgLock;
    public static mImage imgRect;
    public static mImage imgSelect_1;
    public static mImage imgcolorhpmp_back;
    public static mImage imgcolorhpSmall_back;
    public static mImage imgcolorhpSmall;
    public static mImage img18Plus;
    public static FrameImage fraPlayerDie;
    public static FrameImage fraObjMiniMap;
    public static FrameImage fraPk;
    public static FrameImage fraStar;
    public static FrameImage fraQuest;
    public static FrameImage fraMonSample;
    public static FrameImage fraDiamond;
    public static FrameImage fraPlayerNo;
    public static FrameImage imgStun;
    public static FrameImage imgSleep;
    public static FrameImage fraFogetPass;
    public static final int SELECTED_COLOR = -6517641;
    public static mImage[] tab = new mImage[4];
    public static mImage[] imghitScr = new mImage[3];
    public static mImage[] textf = new mImage[2];
    public static int[] color = {-12052464, -477112, -6778760, -2043720, -6649744};
    static int[] colorDia = {-8902126, -460632, -11467264, -477112, -477112};

    public void paint(mGraphics mgraphics) {
        GameCanvas.resetTrans(mgraphics);
        if (GameCanvas.currentDialog == null && !GameCanvas.menu2.isShowMenu) {
            paintCmd(mgraphics);
        }
    }

    public void update() {
    }

    public void keypress(int i) {
    }

    public void paintCmd(mGraphics mgraphics) {
        if (GameCanvas.isSmallScreen) {
            paintCmdSmall(mgraphics);
            return;
        }
        if (this.left != null) {
            this.left.paint(mgraphics, GameCanvas.wCommand, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 1);
        }
        if (this.right != null) {
            this.right.paint(mgraphics, GameCanvas.w - GameCanvas.wCommand, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 1);
        }
        if (this.center != null) {
            this.center.paint(mgraphics, GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 1);
        }
    }

    public void paintCmd_OnlyText(mGraphics mgraphics) {
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null) {
            return;
        }
        GameCanvas.resetTrans(mgraphics);
        if (GameCanvas.isSmallScreen) {
            paintCmd_OnlyText_Small(mgraphics);
            return;
        }
        if (this.left != null) {
            Font3dWhite(mgraphics, this.left.caption, 30, (GameCanvas.h - (GameCanvas.hCommand / 2)) - 4, 2);
        }
        if (this.right != null) {
            Font3dWhite(mgraphics, this.right.caption, GameCanvas.w - 30, (GameCanvas.h - (GameCanvas.hCommand / 2)) - 4, 2);
        }
        if (this.center != null) {
            Font3dWhite(mgraphics, this.center.caption, GameCanvas.hw, (GameCanvas.h - (GameCanvas.hCommand / 2)) - 4, 2);
        }
    }

    public void paintCmdSmall(mGraphics mgraphics) {
        GameCanvas.resetTrans(mgraphics);
        if (this.left != null) {
            this.left.paint(mgraphics, GameCanvas.wCommand, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 1);
        }
        if (this.right != null) {
            this.right.paint(mgraphics, GameCanvas.w - GameCanvas.wCommand, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 1);
        }
        if (this.center != null) {
            this.center.paint(mgraphics, GameCanvas.hw, (GameCanvas.h - (iCommand.hButtonCmd / 2)) - 1);
        }
    }

    public void paintCmd_OnlyText_Small(mGraphics mgraphics) {
        if (this.left != null) {
            mFont.tahoma_7b_white.drawString(mgraphics, this.left.caption, 27, (GameCanvas.h - (GameCanvas.hCommand / 2)) + 1, 2, false);
        }
        if (this.right != null) {
            mFont.tahoma_7b_white.drawString(mgraphics, this.right.caption, GameCanvas.w - 27, (GameCanvas.h - (GameCanvas.hCommand / 2)) + 1, 2, false);
        }
        if (this.center != null) {
            mFont.tahoma_7b_white.drawString(mgraphics, this.center.caption, GameCanvas.hw, (GameCanvas.h - (GameCanvas.hCommand / 2)) + 1, 2, false);
        }
    }

    public void commandTab(int i, int i2) {
    }

    public void commandMenu(int i, int i2) {
    }

    public void commandPointer(int i, int i2) {
    }

    public void commandPointer(int i, Object obj) {
    }

    public void paintRect(mGraphics mgraphics, int i, int i2, int i3, int i4, boolean z) {
        mgraphics.setColor(0);
        mgraphics.fillRect(i, i2, i3, i4, false);
        mgraphics.setColor(-15703219);
        if (z) {
            mgraphics.setColor(-1);
        }
        mgraphics.fillRect(i + 2, i2 + 2, i3 - 4, i4 - 4, false);
    }

    public void paintRect(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        mgraphics.setColor(0);
        mgraphics.fillRect(i, i2, i3, i4, false);
        mgraphics.setColor(-15703219);
        mgraphics.fillRect(i + 2, i2 + 2, i3 - 4, i4 - 4, false);
    }

    public void paintRect(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5, int i6) {
        mgraphics.setColor(i5);
        mgraphics.fillRect(i, i2, i3, i4, false);
        mgraphics.setColor(i6);
        mgraphics.fillRect(i + 2, i2 + 2, i3 - 4, i4 - 4, false);
    }

    public static void paintDialog(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        if (i3 < 35) {
            i3 = 35;
        }
        int i6 = (i3 - 6) / 32;
        int i7 = (i4 - 6) / 32;
        if (i4 % 2 != 0) {
            i4++;
        }
        if (GameCanvas.lowGraphic) {
            if (i5 > 2) {
                if (i5 == 8 || i5 == 12) {
                    i5 = 4;
                } else {
                    i5 = 3;
                }
            }
            mgraphics.setColor(colorDia[0]);
            mgraphics.fillRect(i, i2, i3, i4, false);
            mgraphics.setColor(colorDia[4]);
            mgraphics.drawRect(i + 1, i2 + 1, i3 - 3, i4 - 3, false);
            MainTabNew.paintRectLowGraphic(mgraphics, i + 3, i2 + 3, i3 - 6, i4 - 6, i5);
            return;
        }
        if (i4 < 32) {
            for (int i8 = 0; i8 <= i6; i8++) {
                for (int i9 = 0; i9 <= i7; i9++) {
                    if (i8 == i6) {
                        if (i9 == i7) {
                            mgraphics.drawRegion(MainTabNew.imgTab[i5], 0, 0, 32, i4, 0, ((i - 3) + i3) - 32, i2, 0, false);
                        } else {
                            mgraphics.drawRegion(MainTabNew.imgTab[i5], 0, 0, 32, i4, 0, ((i - 3) + i3) - 32, i2 + 3 + (32 * i9), 0, false);
                        }
                    } else if (i9 == i7) {
                        mgraphics.drawRegion(MainTabNew.imgTab[i5], 0, 0, 32, i4, 0, i + 3 + (i8 * 32), i2, 0, false);
                    } else {
                        mgraphics.drawRegion(MainTabNew.imgTab[i5], 0, 0, 32, i4, 0, i + 3 + (i8 * 32), i2 + 3 + (32 * i9), 0, false);
                    }
                }
            }
        } else {
            for (int i10 = 0; i10 <= i6; i10++) {
                for (int i11 = 0; i11 <= i7; i11++) {
                    if (i10 == i6) {
                        if (i11 == i7) {
                            mgraphics.drawImage(MainTabNew.imgTab[i5], ((i - 3) + i3) - 32, ((i2 - 3) + i4) - 32, 0, false);
                        } else {
                            mgraphics.drawImage(MainTabNew.imgTab[i5], ((i - 3) + i3) - 32, i2 + 3 + (32 * i11), 0, false);
                        }
                    } else if (i11 == i7) {
                        mgraphics.drawImage(MainTabNew.imgTab[i5], i + 3 + (i10 * 32), ((i2 - 3) + i4) - 32, 0, false);
                    } else {
                        mgraphics.drawImage(MainTabNew.imgTab[i5], i + 3 + (i10 * 32), i2 + 3 + (32 * i11), 0, false);
                    }
                }
            }
        }
        mgraphics.drawRegion(imgPopup, 0, 0, 5, 5, 0, i, i2, 0, false);
        mgraphics.drawRegion(imgPopup, 0, 5, 5, 5, 0, (i + i3) - 5, i2, 0, false);
        mgraphics.drawRegion(imgPopup, 0, 15, 5, 5, 0, i, (i2 + i4) - 5, 0, false);
        mgraphics.drawRegion(imgPopup, 0, 10, 5, 5, 0, (i + i3) - 5, (i2 + i4) - 5, 0, false);
        mgraphics.setColor(colorDia[0]);
        mgraphics.fillRect(i + 3, i2, i3 - 6, 1, false);
        mgraphics.fillRect(i, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[1]);
        mgraphics.fillRect(i + 3, i2 + 1, i3 - 6, 1, false);
        mgraphics.fillRect(i + 1, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[2]);
        mgraphics.fillRect(i + 3, i2 + 2, i3 - 6, 1, false);
        mgraphics.fillRect(i + 2, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[2]);
        mgraphics.fillRect(i + 3, (i2 + i4) - 1, i3 - 6, 1, false);
        mgraphics.fillRect((i + i3) - 1, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[4]);
        mgraphics.fillRect(i + 3, (i2 + i4) - 2, i3 - 6, 1, false);
        mgraphics.fillRect((i + i3) - 2, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[0]);
        mgraphics.fillRect(i + 3, (i2 + i4) - 3, i3 - 6, 1, false);
        mgraphics.fillRect((i + i3) - 3, i2 + 3, 1, i4 - 6, false);
    }

    public static void paintDialogNew(mGraphics mgraphics, int i, int i2, int i3, int i4, int i5) {
        if (GameCanvas.lowGraphic) {
            paintDialog(mgraphics, i, i2, i3, i4, i5);
            return;
        }
        int i6 = (i3 - 6) / 32;
        int i7 = (i4 - 6) / 32;
        if (i4 % 2 != 0) {
            i4++;
        }
        for (int i8 = 0; i8 <= i6; i8++) {
            for (int i9 = 0; i9 <= i7; i9++) {
                if (i9 <= 1 || i9 >= i7 - 1 || i8 == 0 || i8 == i6) {
                    if (i8 == i6) {
                        if (i9 == i7) {
                            mgraphics.drawImage(MainTabNew.imgTab[i5], ((i - 3) + i3) - 32, ((i2 - 3) + i4) - 32, 0, false);
                        } else {
                            mgraphics.drawImage(MainTabNew.imgTab[i5], ((i - 3) + i3) - 32, i2 + 3 + (32 * i9), 0, false);
                        }
                    } else if (i9 == i7) {
                        mgraphics.drawImage(MainTabNew.imgTab[i5], i + 3 + (i8 * 32), ((i2 - 3) + i4) - 32, 0, false);
                    } else {
                        mgraphics.drawImage(MainTabNew.imgTab[i5], i + 3 + (i8 * 32), i2 + 3 + (32 * i9), 0, false);
                    }
                }
            }
        }
        mgraphics.drawRegion(imgPopup, 0, 0, 5, 5, 0, i, i2, 0, false);
        mgraphics.drawRegion(imgPopup, 0, 5, 5, 5, 0, (i + i3) - 5, i2, 0, false);
        mgraphics.drawRegion(imgPopup, 0, 15, 5, 5, 0, i, (i2 + i4) - 5, 0, false);
        mgraphics.drawRegion(imgPopup, 0, 10, 5, 5, 0, (i + i3) - 5, (i2 + i4) - 5, 0, false);
        mgraphics.setColor(colorDia[0]);
        mgraphics.fillRect(i + 3, i2, i3 - 6, 1, false);
        mgraphics.fillRect(i, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[1]);
        mgraphics.fillRect(i + 3, i2 + 1, i3 - 6, 1, false);
        mgraphics.fillRect(i + 1, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[2]);
        mgraphics.fillRect(i + 3, i2 + 2, i3 - 6, 1, false);
        mgraphics.fillRect(i + 2, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[2]);
        mgraphics.fillRect(i + 3, (i2 + i4) - 1, i3 - 6, 1, false);
        mgraphics.fillRect((i + i3) - 1, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[4]);
        mgraphics.fillRect(i + 3, (i2 + i4) - 2, i3 - 6, 1, false);
        mgraphics.fillRect((i + i3) - 2, i2 + 3, 1, i4 - 6, false);
        mgraphics.setColor(colorDia[0]);
        mgraphics.fillRect(i + 3, (i2 + i4) - 3, i3 - 6, 1, false);
        mgraphics.fillRect((i + i3) - 3, i2 + 3, 1, i4 - 6, false);
    }

    public static void paintTabNew(mGraphics mgraphics, int i, int i2, int i3, int i4, boolean z, byte b) {
        if (GameCanvas.lowGraphic) {
            paintDialog(mgraphics, i, i2, i3, i4, b);
            return;
        }
        if (i4 < 32) {
            i4 = 32;
        }
        mgraphics.setColor(color[0]);
        mgraphics.fillRect((i + wimg) - 2, i2 + 3, (i3 - (2 * wimg)) + 4, i4 - 5, false);
        mgraphics.fillRect(i + 4, (i2 + wimg) - 2, i3 - 8, (i4 - (2 * wimg)) + 4, false);
        mgraphics.setColor(color[1]);
        mgraphics.fillRect((i + wimg) - 2, i2 + 4, (i3 - (2 * wimg)) + 4, i4 - 7, false);
        mgraphics.fillRect(i + 5, (i2 + wimg) - 2, i3 - 10, (i4 - (2 * wimg)) + 4, false);
        mgraphics.setColor(color[0]);
        mgraphics.fillRect((i + wimg) - 2, i2 + 5, (i3 - (2 * wimg)) + 4, i4 - 9, false);
        mgraphics.fillRect(i + 6, (i2 + wimg) - 2, i3 - 12, (i4 - (2 * wimg)) + 4, false);
        mgraphics.setColor(color[2]);
        mgraphics.fillRect(i + 7, i2 + 6, i3 - 14, i4 - 12, false);
        for (int i5 = 0; i5 <= (i3 - 15) / 32; i5++) {
            for (int i6 = 0; i6 <= (i4 - 11) / 32; i6++) {
                if (i5 == (i3 - 15) / 32) {
                    if (i6 == (i4 - 11) / 32) {
                        mgraphics.drawImage(MainTabNew.imgTab[b], (i + i3) - 39, (i2 + i4) - 37, 0, false);
                    } else {
                        mgraphics.drawImage(MainTabNew.imgTab[b], (i + i3) - 39, i2 + 7 + (i6 * 32), 0, false);
                    }
                } else if (i6 == (i4 - 11) / 32) {
                    mgraphics.drawImage(MainTabNew.imgTab[b], i + 8 + (i5 * 32), (i2 + i4) - 37, 0, false);
                } else {
                    mgraphics.drawImage(MainTabNew.imgTab[b], i + 8 + (i5 * 32), i2 + 7 + (i6 * 32), 0, false);
                }
            }
        }
        mgraphics.drawImage(tab[0], i, i2, 0, false);
        mgraphics.drawRegion(tab[0], 0, 0, wimg, wimg, 2, (i + i3) - wimg, i2, 0, false);
        mgraphics.drawImage(tab[1], i + 2, (i2 + i4) - wimg, 0, false);
        mgraphics.drawRegion(tab[1], 0, 0, 30, 30, 2, (i + i3) - 32, (i2 + i4) - wimg, 0, false);
        if (z) {
            mgraphics.drawImage(tab[2], i + (i3 / 2), i2 + 2, 3, false);
        }
    }

    public static void paintRectNice(mGraphics mgraphics, int i, int i2, int i3, int i4, byte b) {
        if (i4 % 2 == 1) {
            i4++;
        }
        if (GameCanvas.lowGraphic) {
            if (b > 2) {
                if (b == 8 || b == 12) {
                    b = 4;
                } else {
                    b = 3;
                }
            }
            MainTabNew.paintRectLowGraphic(mgraphics, i, i2, i3, i4, b);
            return;
        }
        for (int i5 = 0; i5 <= i3 / 32; i5++) {
            for (int i6 = 0; i6 <= i4 / 32; i6++) {
                if (i5 == i3 / 32) {
                    if (i6 == i4 / 32) {
                        mgraphics.drawImage(MainTabNew.imgTab[b], (i + i3) - 32, (i2 + i4) - 32, 0, false);
                    } else {
                        mgraphics.drawImage(MainTabNew.imgTab[b], (i + i3) - 32, i2 + (i6 * 32), 0, false);
                    }
                } else if (i6 == i4 / 32) {
                    mgraphics.drawImage(MainTabNew.imgTab[b], i + (i5 * 32), (i2 + i4) - 32, 0, false);
                } else {
                    mgraphics.drawImage(MainTabNew.imgTab[b], i + (i5 * 32), i2 + (i6 * 32), 0, false);
                }
            }
        }
    }

    public static void fill(int i, int i2, int i3, int i4, int i5, mGraphics mgraphics) {
        mgraphics.setColor(i5);
        mgraphics.fillRect(i, i2, i3, i4, false);
    }

    public static void paintRectText(mGraphics mgraphics, int i, int i2, int i3, int i4, boolean z) {
        mgraphics.setColor(-4155296);
        if (z) {
            mgraphics.setColor(-132648);
        }
        mgraphics.drawRegion(imgtextfield, 0, z ? 30 : 0, 4, 15, 0, i, i2, 0, true);
        mgraphics.drawRegion(imgtextfield, 0, (z ? 30 : 0) + 15, 4, 15, 0, (i + i3) - 4, (i2 + i4) - 15, 0, true);
        mgraphics.drawRegion(imgtextfield, 0, (z ? 30 : 0) + 11, 4, 4, 0, i, (i2 + i4) - 4, 0, true);
        mgraphics.drawRegion(imgtextfield, 0, (z ? 30 : 0) + 15, 4, 4, 0, (i + i3) - 4, i2, 0, true);
        mgraphics.fillRect(i + 4, i2, i3 - 8, i4, true);
        mgraphics.fillRect(i, i2 + 4, i3, i4 - 8, true);
    }

    public void paintSelect(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        mgraphics.setColor(SELECTED_COLOR);
        mgraphics.fillRect(i, i2, i3, i4, false);
    }

    public void paintFormList(mGraphics mgraphics, int i, int i2, int i3, int i4, String str) {
        paintDialogNew(mgraphics, i - 6, i2 - 6, i3 + 12, i4 + 12, 0);
        paintRectNice(mgraphics, i, i2 + GameCanvas.hCommand, i3, i4 - GameCanvas.hCommand, (byte) 2);
        MainTabNew.paintNameItem(mgraphics, i + (i3 / 2), i2 + (GameCanvas.hCommand / 4), i3, str, 7);
    }

    public int resetSelect(int i, int i2, boolean z) {
        if (i < 0) {
            if (z) {
                i = i2;
            } else {
                i = 0;
            }
        } else if (i > i2) {
            if (z) {
                i = 0;
            } else {
                i = i2;
            }
        }
        return i;
    }

    public void updatekey() {
        if (GameCanvas.keyMyHold[5]) {
            if (this.center != null) {
                GameCanvas.clearKeyPressed(5);
                GameCanvas.clearKeyHold(5);
                this.center.perform();
                return;
            }
            return;
        }
        if (GameCanvas.keyMyHold[12]) {
            if (this.left != null) {
                GameCanvas.clearKeyPressed(12);
                GameCanvas.clearKeyHold(12);
                this.left.perform();
                return;
            }
            return;
        }
        if (GameCanvas.keyMyHold[13] && this.right != null) {
            GameCanvas.clearKeyPressed(13);
            GameCanvas.clearKeyHold(13);
            this.right.perform();
        }
    }

    public void updatePointer() {
        if (GameCanvas.isTouch) {
            if (this.left != null) {
                if (this.left.isPosCmd()) {
                    this.left.updatePointer();
                } else if (GameCanvas.isPointSelect(0, (GameCanvas.h - GameCanvas.hCommand) - 5, GameCanvas.wCommand * 2, GameCanvas.hCommand + 10)) {
                    this.left.perform();
                }
            }
            if (this.right != null) {
                if (this.right.isPosCmd()) {
                    this.right.updatePointer();
                } else if (GameCanvas.isPointSelect(GameCanvas.w - (GameCanvas.wCommand * 2), (GameCanvas.h - GameCanvas.hCommand) - 5, GameCanvas.wCommand * 2, GameCanvas.hCommand + 10)) {
                    this.right.perform();
                }
            }
            if (this.center != null) {
                if (this.center.isPosCmd()) {
                    this.center.updatePointer();
                } else if (GameCanvas.isPointSelect(GameCanvas.hw - GameCanvas.wCommand, (GameCanvas.h - GameCanvas.hCommand) - 5, GameCanvas.wCommand * 2, GameCanvas.hCommand + 10)) {
                    this.center.perform();
                }
            }
        }
    }

    public static void Font3dWhite(mGraphics mgraphics, String str, int i, int i2, int i3) {
        mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 + 1, i3, true);
        mFont.tahoma_7b_white.drawString(mgraphics, str, i, i2, i3, true);
    }

    public static void Font3dColor(mGraphics mgraphics, String str, int i, int i2, int i3, byte b) {
        mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 + 1, i3, true);
        MainTabNew.setTextColorName(b).drawString(mgraphics, str, i, i2, i3, true);
    }

    public static void Font3dColorAndColor(mGraphics mgraphics, String str, int i, int i2, int i3, byte b, byte b2) {
        MainTabNew.setTextColorName(b).drawString(mgraphics, str, i + 1, i2 + 1, i3, true);
        MainTabNew.setTextColorName(b2).drawString(mgraphics, str, i, i2, i3, true);
    }

    public static void FontBorderColor(mGraphics mgraphics, String str, int i, int i2, int i3, int i4) {
        if (i4 == 2) {
            mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 + 1, i3, true);
        } else {
            mFont.tahoma_7b_black.drawString(mgraphics, str, i - 1, i2 - 1, i3, true);
            mFont.tahoma_7b_black.drawString(mgraphics, str, i - 1, i2 + 1, i3, true);
            mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 - 1, i3, true);
            mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2 + 1, i3, true);
            mFont.tahoma_7b_black.drawString(mgraphics, str, i - 1, i2, i3, true);
            mFont.tahoma_7b_black.drawString(mgraphics, str, i + 1, i2, i3, true);
            mFont.tahoma_7b_black.drawString(mgraphics, str, i, i2 - 1, i3, true);
            mFont.tahoma_7b_black.drawString(mgraphics, str, i, i2 + 1, i3, true);
        }
        MainTabNew.setTextColorName(i4).drawString(mgraphics, str, i, i2, i3, false);
    }
}
