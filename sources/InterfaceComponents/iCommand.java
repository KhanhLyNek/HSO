package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import GameScreen.PaintInfoGameScreen;
import Main.GameCanvas;
import Model.AvMain;
import Model.FrameImage;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/iCommand.class */
public class iCommand {
    public String caption;
    public String clickCaption;
    public String[] subCaption;
    public IAction action;
    public AvMain Pointer;
    public byte indexMenu;
    public byte subIndex;
    public boolean isSelect;
    int nframe;
    int beginframe;
    FrameImage fraImgCaption;
    FrameImage fraImageCmd;
    int wimgCaption;
    int himgCaption;
    int wstr;
    int wimgCmd;
    int himgCmd;
    public int xCmd;
    public int yCmd;
    public int frameCmd;
    public int xdich;
    public int ydich;
    public static int wButtonCmd = 70;
    public static int hButtonCmd = 24;
    public int IdGiaotiep;
    public Object obj;
    public boolean isButtonDai;

    public iCommand(String str, IAction iAction) {
        this.clickCaption = "";
        this.subIndex = (byte) -1;
        this.isSelect = false;
        this.xCmd = -1;
        this.yCmd = -1;
        this.IdGiaotiep = 0;
        this.isButtonDai = false;
        this.caption = str;
        this.action = iAction;
    }

    public iCommand(String str, int i) {
        this.clickCaption = "";
        this.subIndex = (byte) -1;
        this.isSelect = false;
        this.xCmd = -1;
        this.yCmd = -1;
        this.IdGiaotiep = 0;
        this.isButtonDai = false;
        this.caption = str;
        this.indexMenu = (byte) i;
    }

    public iCommand(String str, int i, AvMain avMain) {
        this.clickCaption = "";
        this.subIndex = (byte) -1;
        this.isSelect = false;
        this.xCmd = -1;
        this.yCmd = -1;
        this.IdGiaotiep = 0;
        this.isButtonDai = false;
        this.caption = str;
        this.indexMenu = (byte) i;
        this.Pointer = avMain;
    }

    public iCommand(String str, int i, int i2, AvMain avMain) {
        this.clickCaption = "";
        this.subIndex = (byte) -1;
        this.isSelect = false;
        this.xCmd = -1;
        this.yCmd = -1;
        this.IdGiaotiep = 0;
        this.isButtonDai = false;
        this.caption = str;
        this.indexMenu = (byte) i;
        this.subIndex = (byte) i2;
        this.Pointer = avMain;
    }

    public iCommand(String str, int i, int i2) {
        this.clickCaption = "";
        this.subIndex = (byte) -1;
        this.isSelect = false;
        this.xCmd = -1;
        this.yCmd = -1;
        this.IdGiaotiep = 0;
        this.isButtonDai = false;
        this.caption = str;
        this.indexMenu = (byte) i;
        this.subIndex = (byte) i2;
    }

    public iCommand(String str, int i, Object obj, AvMain avMain) {
        this.clickCaption = "";
        this.subIndex = (byte) -1;
        this.isSelect = false;
        this.xCmd = -1;
        this.yCmd = -1;
        this.IdGiaotiep = 0;
        this.isButtonDai = false;
        this.caption = str;
        this.indexMenu = (byte) i;
        this.Pointer = avMain;
        this.obj = obj;
    }

    public void setFraCaption(FrameImage frameImage) {
        this.fraImgCaption = frameImage;
        this.wimgCaption = this.fraImgCaption.frameWidth;
        this.himgCaption = this.fraImgCaption.frameHeight;
        this.nframe = this.fraImgCaption.nFrame;
        this.beginframe = 0;
        if (GameCanvas.isSmallScreen) {
            this.wstr = mFont.tahoma_7_white.getWidth(this.caption);
        } else {
            this.wstr = mFont.tahoma_7b_white.getWidth(this.caption);
        }
    }

    public void setFraCaption(FrameImage frameImage, int i, int i2) {
        this.fraImgCaption = frameImage;
        this.wimgCaption = this.fraImgCaption.frameWidth;
        this.himgCaption = this.fraImgCaption.frameHeight;
        this.nframe = i;
        this.beginframe = i2;
        if (GameCanvas.isSmallScreen) {
            this.wstr = mFont.tahoma_7_white.getWidth(this.caption);
        } else {
            this.wstr = mFont.tahoma_7b_white.getWidth(this.caption);
        }
    }

    public void setPosImg(int i, int i2, FrameImage frameImage, int i3, int i4) {
        this.xCmd = i;
        this.yCmd = i2;
        this.fraImgCaption = frameImage;
        this.wimgCaption = this.fraImgCaption.frameWidth;
        this.himgCaption = this.fraImgCaption.frameHeight;
        this.nframe = i3;
        this.beginframe = i4;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
                return;
            }
            return;
        }
        this.wimgCmd = 70;
        this.himgCmd = hButtonCmd;
    }

    public void setPos(int i, int i2, FrameImage frameImage, String str) {
        this.caption = str;
        this.xCmd = i;
        this.yCmd = i2;
        this.fraImageCmd = frameImage;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
                return;
            }
            return;
        }
        this.wimgCmd = 70;
        this.himgCmd = hButtonCmd;
    }

    public void setPos_BoxText(int i, int i2, FrameImage frameImage, String str, int i3, int i4) {
        this.isButtonDai = true;
        this.caption = str;
        this.xCmd = i;
        this.yCmd = i2;
        this.fraImageCmd = frameImage;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
                return;
            }
            return;
        }
        this.wimgCmd = i3;
        this.himgCmd = i4;
    }

    public void setPos_ShowName(int i, int i2, FrameImage frameImage, String str, int i3, int i4) {
        this.caption = "";
        this.clickCaption = str;
        this.xCmd = i;
        this.yCmd = i2;
        this.xdich = i3;
        this.ydich = i4;
        this.fraImageCmd = frameImage;
        if (this.fraImageCmd != null) {
            this.wimgCmd = this.fraImageCmd.frameWidth;
            this.himgCmd = this.fraImageCmd.frameHeight;
            if (this.wimgCmd < 28) {
                this.wimgCmd = 28;
            }
            if (this.himgCmd < 28) {
                this.himgCmd = 28;
                return;
            }
            return;
        }
        this.wimgCmd = 70;
        this.himgCmd = hButtonCmd;
    }

    public void setPosXY(int i, int i2) {
        this.xCmd = i;
        this.yCmd = i2;
    }

    public void perform() {
        if (GameCanvas.menu2.isShowMenu && (GameCanvas.menu2.runText == null || GameCanvas.menu2.runText.checkDlgStep())) {
            GameCanvas.menu2.doCloseMenu();
        }
        if (this.action != null) {
            this.action.perform();
            GameCanvas.isPointerSelect = false;
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            mSound.playSound(41, mSound.volumeSound);
            return;
        }
        mSound.playSound(41, mSound.volumeSound);
        if (this.Pointer != null) {
            if (this.obj != null) {
                this.Pointer.commandPointer(this.indexMenu, this.obj);
            } else {
                this.Pointer.commandPointer(this.indexMenu, this.subIndex);
            }
            GameCanvas.isPointerSelect = false;
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            return;
        }
        if (GameCanvas.currentDialog != null) {
            GameCanvas.currentDialog.commandTab(this.indexMenu, this.subIndex);
            GameCanvas.isPointerSelect = false;
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            return;
        }
        if (ChatTextField.isShow) {
            ChatTextField.gI().commandTab(this.indexMenu, this.subIndex);
            return;
        }
        if (GameCanvas.menu2.isShowMenu) {
            GameCanvas.currentScreen.commandMenu(this.indexMenu, this.subIndex);
        } else if (GameCanvas.subDialog != null) {
            GameCanvas.subDialog.commandTab(this.indexMenu, this.subIndex);
        } else {
            GameCanvas.currentScreen.commandTab(this.indexMenu, this.subIndex);
        }
    }

    public void update() {
    }

    public void paint(mGraphics mgraphics, int i, int i2) {
        if (isPosCmd()) {
            if (this.fraImageCmd != null) {
                this.fraImageCmd.drawFrame(this.frameCmd, this.xCmd, this.yCmd, 0, 3, mgraphics);
            } else {
                paintbutton(mgraphics, this.xCmd, this.yCmd);
            }
            paintCaptionImage(mgraphics, this.xCmd, this.yCmd - 6, 2);
            return;
        }
        if (this.fraImageCmd != null) {
            this.fraImageCmd.drawFrame(this.frameCmd, i, i2, 0, 3, mgraphics);
        } else {
            paintbutton(mgraphics, i, i2);
        }
        paintCaptionImage(mgraphics, i, i2 - 6, 2);
    }

    public void paintCaptionImage(mGraphics mgraphics, int i, int i2, int i3) {
        if (this.caption == null) {
            return;
        }
        int i4 = 0;
        if (this.fraImgCaption != null) {
            if (i3 == 2) {
                this.fraImgCaption.drawFrame(this.beginframe + ((GameCanvas.gameTick / 2) % this.nframe), (i - (this.wimgCaption / 2)) - (this.wstr / 2), (i2 + (this.himgCaption / 2)) - 3, 0, 3, mgraphics);
                i4 = this.wimgCaption / 2;
            } else if (i3 == 0) {
                this.fraImgCaption.drawFrame(this.beginframe + ((GameCanvas.gameTick / 2) % this.nframe), i + (this.wimgCaption / 2), (i2 + (this.himgCaption / 2)) - 4, 0, 3, mgraphics);
                i4 = this.wimgCaption + 6;
            }
        }
        if (!this.isButtonDai) {
            if (GameCanvas.isSmallScreen) {
                mFont.tahoma_7_white.drawString(mgraphics, this.caption, i + i4, i2, i3, false);
                return;
            } else {
                AvMain.Font3dColor(mgraphics, this.caption, i + i4, i2, i3, (byte) 0);
                return;
            }
        }
        if (GameCanvas.isSmallScreen) {
            mFont.tahoma_7_white.drawString(mgraphics, this.caption, i + i4 + (this.wimgCmd / 2), i2 + (this.himgCmd / 2) + 1, i3, false);
        } else {
            AvMain.Font3dColor(mgraphics, this.caption, i + i4 + (this.wimgCmd / 2), i2 + (this.himgCmd / 2) + 1, i3, (byte) 0);
        }
    }

    public void paintClickCaption(mGraphics mgraphics, int i, int i2, int i3) {
        if (this.frameCmd == 1 && this.clickCaption.length() > 0) {
            AvMain.Font3dColor(mgraphics, this.clickCaption, i + this.xdich, i2 + this.ydich, i3, (byte) 0);
        }
    }

    public void paintCaptionImageMenu(mGraphics mgraphics, int i, int i2, int i3) {
        int i4 = 0;
        if (this.fraImgCaption != null) {
            if (i3 == 2) {
                this.fraImgCaption.drawFrame(this.beginframe + ((GameCanvas.gameTick / 2) % this.nframe), (i - (this.wimgCaption / 2)) - (this.wstr / 2), i2 + (this.himgCaption / 2), 0, 3, mgraphics);
                i4 = this.wimgCaption / 2;
            } else if (i3 == 0) {
                this.fraImgCaption.drawFrame(this.beginframe + ((GameCanvas.gameTick / 2) % this.nframe), i + (this.wimgCaption / 2), i2 + (this.himgCaption / 2), 0, 3, mgraphics);
                i4 = this.wimgCaption + 6;
            }
        }
        if (GameCanvas.isSmallScreen) {
            mFont.tahoma_7_white.drawString(mgraphics, this.caption, i + i4, i2, i3, false);
        } else {
            AvMain.Font3dColorAndColor(mgraphics, this.caption, i + i4, i2, i3, (byte) 7, (byte) 0);
        }
    }

    public void paintImage(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        if (this.fraImgCaption != null) {
            if (i4 == 0) {
                this.fraImgCaption.drawFrame(this.beginframe + ((GameCanvas.gameTick / 2) % this.nframe), i, i2 + (this.himgCaption / 2), 0, i3, mgraphics);
                return;
            }
            if (i4 == 1) {
                int i5 = this.beginframe + this.frameCmd == 1 ? 1 : 0;
                if (i5 > this.nframe) {
                    i5 = this.beginframe - 1;
                }
                this.fraImgCaption.drawFrame(i5, i, i2 + (this.himgCaption / 2), 0, i3, mgraphics);
                return;
            }
            this.fraImgCaption.drawFrame(this.beginframe, i, i2 + (this.himgCaption / 2), 0, i3, mgraphics);
        }
    }

    public void updatePointer() {
        if (!this.isButtonDai) {
            if (isPosCmd()) {
                if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
                    if (GameCanvas.isPoint((this.xCmd - (this.wimgCmd / 2)) - 5, (this.yCmd - (this.himgCmd / 2)) - 5, this.wimgCmd + 10, this.himgCmd + 10)) {
                        this.frameCmd = 1;
                    } else {
                        this.frameCmd = 0;
                    }
                } else {
                    this.frameCmd = 0;
                }
                if (GameCanvas.isPointSelect((this.xCmd - (this.wimgCmd / 2)) - 5, (this.yCmd - (this.himgCmd / 2)) - 5, this.wimgCmd + 10, this.himgCmd + 10)) {
                    perform();
                    GameCanvas.isPointerSelect = false;
                    this.frameCmd = 0;
                    return;
                }
                return;
            }
            return;
        }
        if (isPosCmd()) {
            if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
                if (GameCanvas.isPoint(this.xCmd - 5, this.yCmd - 5, this.wimgCmd + 10, this.himgCmd + 10)) {
                    this.isSelect = true;
                    this.frameCmd = 1;
                } else {
                    this.frameCmd = 0;
                    this.isSelect = false;
                }
            } else {
                this.frameCmd = 0;
                this.isSelect = false;
            }
            if (GameCanvas.isPointSelect(this.xCmd - 5, this.yCmd - 5, this.wimgCmd + 10, this.himgCmd + 10)) {
                perform();
                GameCanvas.isPointerSelect = false;
                this.frameCmd = 0;
                this.isSelect = false;
            }
        }
    }

    public void updatePointer(int i, int i2) {
        if (isPosCmd()) {
            if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
                if (GameCanvas.isPoint(((this.xCmd - (this.wimgCmd / 2)) - 3) - i, ((this.yCmd - (this.himgCmd / 2)) - 3) - i2, this.wimgCmd + 6, this.himgCmd + 6)) {
                    this.frameCmd = 1;
                } else {
                    this.frameCmd = 0;
                }
            } else {
                this.frameCmd = 0;
            }
            if (GameCanvas.isPointSelect(((this.xCmd - (this.wimgCmd / 2)) - 3) - i, ((this.yCmd - (this.himgCmd / 2)) - 3) - i2, this.wimgCmd + 6, this.himgCmd + 6)) {
                perform();
                GameCanvas.isPointerSelect = false;
                this.frameCmd = 0;
            }
        }
    }

    public void updatePointerShow(int i, int i2) {
        if (isPosCmd()) {
            if (GameCanvas.isPointerDown || GameCanvas.isPointerMove) {
                if (GameCanvas.isPoint(((this.xCmd - (this.wimgCmd / 2)) - 3) - i, ((this.yCmd - (this.himgCmd / 2)) - 3) - i2, this.wimgCmd + 6, this.himgCmd + 6)) {
                    this.frameCmd = 1;
                    return;
                } else {
                    this.frameCmd = 0;
                    return;
                }
            }
            this.frameCmd = 0;
        }
    }

    public void paintbutton(mGraphics mgraphics, int i, int i2) {
        if (GameCanvas.isTouch) {
            if (!this.isButtonDai) {
                PaintInfoGameScreen.fraButton.drawFrame(this.frameCmd, i, i2, 0, 3, mgraphics);
                return;
            } else {
                AvMain.paintDialog(mgraphics, i, i2, this.wimgCmd, this.himgCmd, this.isSelect ? 1 : 2);
                return;
            }
        }
        if (!this.isButtonDai) {
            AvMain.paintDialog(mgraphics, i - (wButtonCmd / 2), i2 - (hButtonCmd / 2), wButtonCmd, hButtonCmd, this.isSelect ? 1 : 2);
        } else {
            AvMain.paintDialog(mgraphics, i, i2, this.wimgCmd, this.himgCmd, this.isSelect ? 1 : 2);
        }
    }

    public boolean isPosCmd() {
        if (this.xCmd > 0 && this.yCmd > 0) {
            return true;
        }
        return false;
    }
}
