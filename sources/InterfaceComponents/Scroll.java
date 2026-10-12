package InterfaceComponents;

import CLib.mGraphics;
import Main.GameCanvas;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/Scroll.class */
public class Scroll {
    int x;
    int y;
    int h;
    int color;
    int yScroll;
    int hScroll;
    public int cmtoX;
    public int cmtoY;
    public int cmx;
    public int cmy;
    public int cmvx;
    public int cmvy;
    public int cmdx;
    public int cmdy;
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public int cmxLim;
    public int cmyLim;
    public static Scroll me;
    private int pointerDownTime;
    private int pointerDownFirstX;
    public boolean pointerIsDowning;
    public boolean isDownWhenRunning;
    private int cmRun;
    public int selectedItem;
    public int ITEM_SIZE;
    public int nITEM;
    public int ITEM_PER_LINE;
    public boolean FocusNew;
    public int[] pointerDownLastX = new int[3];
    public boolean styleUPDOWN = true;

    public void clear() {
        this.cmtoX = 0;
        this.cmtoY = 0;
        this.cmx = 0;
        this.cmy = 0;
        this.cmvx = 0;
        this.cmvy = 0;
        this.cmdx = 0;
        this.cmdy = 0;
        this.cmxLim = 0;
        this.cmyLim = 0;
        this.width = 0;
        this.height = 0;
    }

    public void setClip(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        mgraphics.setClip(i, i2, i3, i4 - 1);
        mgraphics.translate(-mgraphics.getTranslateX(), -mgraphics.getTranslateY());
        mgraphics.translate(-this.cmx, -this.cmy);
    }

    public void setClip(mGraphics mgraphics) {
        mgraphics.setClip(this.xPos + 1, this.yPos + 1, this.width - 2, this.height - 2);
        mgraphics.translate(-mgraphics.getTranslateX(), -mgraphics.getTranslateY());
        mgraphics.translate(-this.cmx, -this.cmy);
    }

    public ScrollResult updateKey() {
        if (this.styleUPDOWN) {
            return updateKeyScrollUpDown();
        }
        return updateKeyScrollLeftRight();
    }

    private ScrollResult updateKeyScrollUpDown() {
        int i;
        int i2 = this.xPos;
        int i3 = this.yPos;
        int i4 = this.width;
        int i5 = this.height;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(i2, i3, i4, i5)) {
                for (int i6 = 0; i6 < this.pointerDownLastX.length; i6++) {
                    this.pointerDownLastX[0] = GameCanvas.py;
                }
                this.pointerDownFirstX = GameCanvas.py;
                this.pointerIsDowning = true;
                this.selectedItem = -1;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                this.pointerDownTime++;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    if (this.ITEM_PER_LINE > 1) {
                        this.selectedItem = ((((this.cmtoY + GameCanvas.py) - i3) / this.ITEM_SIZE) * this.ITEM_PER_LINE) + (((this.cmtoX + GameCanvas.px) - i2) / this.ITEM_SIZE);
                    } else {
                        this.selectedItem = ((this.cmtoY + GameCanvas.py) - i3) / this.ITEM_SIZE;
                    }
                }
                int i7 = GameCanvas.py - this.pointerDownLastX[0];
                if (i7 != 0 && this.selectedItem != -1) {
                    this.selectedItem = -1;
                }
                for (int length = this.pointerDownLastX.length - 1; length > 0; length--) {
                    this.pointerDownLastX[length] = this.pointerDownLastX[length - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.py;
                this.cmtoY -= i7;
                if (this.cmtoY < 0) {
                    this.cmtoY = 0;
                }
                if (this.cmtoY > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                }
                if (this.cmy < 0 || this.cmy > this.cmyLim) {
                    i7 /= 2;
                }
                this.cmy -= i7;
            }
        }
        boolean z = false;
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            int i8 = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerRelease = false;
            if (Math.abs(i8) < 20 && Math.abs(GameCanvas.py - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                this.cmtoY = this.cmy;
                this.pointerDownFirstX = -1000;
                if (this.ITEM_PER_LINE > 1) {
                    this.selectedItem = ((((this.cmtoY + GameCanvas.py) - i3) / this.ITEM_SIZE) * this.ITEM_PER_LINE) + (((this.cmtoX + GameCanvas.px) - i2) / this.ITEM_SIZE);
                } else {
                    this.selectedItem = ((this.cmtoY + GameCanvas.py) - i3) / this.ITEM_SIZE;
                }
                this.pointerDownTime = 0;
                z = true;
            } else if (this.selectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                z = true;
            } else if (this.selectedItem == -1 && !this.isDownWhenRunning) {
                if (this.cmy < 0) {
                    this.cmtoY = 0;
                } else if (this.cmy > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                } else {
                    int i9 = (GameCanvas.py - this.pointerDownLastX[0]) + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    if (i9 > 10) {
                        i = 10;
                    } else if (i9 < -10) {
                        i = -10;
                    } else {
                        i = 0;
                    }
                    this.cmRun = (-i) * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerRelease = false;
        }
        ScrollResult scrollResult = new ScrollResult();
        scrollResult.selected = this.selectedItem;
        scrollResult.isFinish = z;
        scrollResult.isDowning = this.pointerIsDowning;
        return scrollResult;
    }

    private ScrollResult updateKeyScrollLeftRight() {
        int i;
        int i2 = this.xPos;
        int i3 = this.yPos;
        int i4 = this.width;
        int i5 = this.height;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(i2, i3, i4, i5)) {
                for (int i6 = 0; i6 < this.pointerDownLastX.length; i6++) {
                    this.pointerDownLastX[0] = GameCanvas.px;
                }
                this.pointerDownFirstX = GameCanvas.px;
                this.pointerIsDowning = true;
                this.selectedItem = -1;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                this.pointerDownTime++;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    this.selectedItem = ((this.cmtoX + GameCanvas.px) - i2) / this.ITEM_SIZE;
                }
                int i7 = GameCanvas.px - this.pointerDownLastX[0];
                if (i7 != 0 && this.selectedItem != -1) {
                    this.selectedItem = -1;
                }
                for (int length = this.pointerDownLastX.length - 1; length > 0; length--) {
                    this.pointerDownLastX[length] = this.pointerDownLastX[length - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.px;
                this.cmtoX -= i7;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                }
                if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                }
                if (this.cmx < 0 || this.cmx > this.cmxLim) {
                    i7 /= 2;
                }
                this.cmx -= i7;
            }
        }
        boolean z = false;
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            int i8 = GameCanvas.px - this.pointerDownLastX[0];
            GameCanvas.isPointerRelease = false;
            if (Math.abs(i8) < 20 && Math.abs(GameCanvas.px - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.selectedItem = ((this.cmtoX + GameCanvas.px) - i2) / this.ITEM_SIZE;
                this.pointerDownTime = 0;
                z = true;
            } else if (this.selectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                z = true;
            } else if (this.selectedItem == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    int i9 = (GameCanvas.px - this.pointerDownLastX[0]) + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    if (i9 > 10) {
                        i = 10;
                    } else if (i9 < -10) {
                        i = -10;
                    } else {
                        i = 0;
                    }
                    this.cmRun = (-i) * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerRelease = false;
        }
        ScrollResult scrollResult = new ScrollResult();
        scrollResult.selected = this.selectedItem;
        scrollResult.isFinish = z;
        scrollResult.isDowning = this.pointerIsDowning;
        return scrollResult;
    }

    public void updatecm() {
        if (GameCanvas.isPointer(this.xPos, this.yPos, this.width, this.height) && GameCanvas.isTouch && !this.FocusNew) {
            this.FocusNew = true;
        }
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            if (this.styleUPDOWN) {
                this.cmtoY += this.cmRun / 100;
                if (this.cmtoY < 0) {
                    this.cmtoY = 0;
                } else if (this.cmtoY > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                } else {
                    this.cmy = this.cmtoY;
                }
            } else {
                this.cmtoX += this.cmRun / 100;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                } else if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    this.cmx = this.cmtoX;
                }
            }
            this.cmRun = (this.cmRun * 9) / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }
        if (this.cmx != this.cmtoX && !this.pointerIsDowning) {
            this.cmvx = (this.cmtoX - this.cmx) << 2;
            this.cmdx += this.cmvx;
            this.cmx += this.cmdx >> 4;
            this.cmdx &= 15;
        }
        if (this.cmy != this.cmtoY && !this.pointerIsDowning) {
            this.cmvy = (this.cmtoY - this.cmy) << 2;
            this.cmdy += this.cmvy;
            this.cmy += this.cmdy >> 4;
            this.cmdy &= 15;
        }
    }

    public void setStyle(int i, int i2, int i3, int i4, int i5, int i6, boolean z, int i7) {
        this.xPos = i3;
        this.yPos = i4;
        this.ITEM_SIZE = i2;
        this.nITEM = i;
        this.width = i5;
        this.height = i6;
        this.styleUPDOWN = z;
        this.ITEM_PER_LINE = i7;
        if (z) {
            this.cmyLim = (i * i2) - i6;
        } else {
            this.cmxLim = (i * i2) - i5;
        }
        if (this.cmyLim < 0) {
            this.cmyLim = 0;
        }
        if (this.cmxLim < 0) {
            this.cmxLim = 0;
        }
    }

    public void moveTo(int i) {
        if (this.styleUPDOWN) {
            this.cmtoY = i - ((this.height - (this.ITEM_SIZE * 2)) / 2);
            if (this.cmtoY < 0) {
                this.cmtoY = 0;
            }
            if (this.cmtoY > this.cmyLim) {
                this.cmtoY = this.cmyLim;
                return;
            }
            return;
        }
        this.cmtoX = i - ((this.width - this.ITEM_SIZE) / 2);
        if (this.cmtoX < 0) {
            this.cmtoX = 0;
        }
        if (this.cmtoX > this.cmxLim) {
            this.cmtoX = this.cmxLim;
        }
    }

    public void moveCmrTo(int i) {
        if (i > 0) {
            this.cmtoY += this.ITEM_SIZE;
        } else if (i < 0) {
            this.cmtoY -= 20;
        }
        if (this.cmtoY < 0) {
            this.cmtoY = 0;
        }
        if (this.cmtoY > this.cmyLim) {
            this.cmtoY = this.cmyLim;
        }
    }

    public static Scroll gI() {
        if (me == null) {
            me = new Scroll();
        }
        return me;
    }

    public void setInfo(int i, int i2, int i3, int i4) {
        this.x = i;
        this.y = i2;
        this.h = i3;
        this.color = i4;
        this.hScroll = i3 - MainTabNew.wOneItem;
    }

    public void paint(mGraphics mgraphics) {
        mgraphics.setColor(this.color);
        mgraphics.fillRect(this.x - 2, this.y - 1, 3, 1, true);
        mgraphics.fillRect(this.x - 3, this.y, 1, this.h - 1, true);
        mgraphics.fillRect(this.x + 1, this.y, 1, this.h - 1, true);
        mgraphics.fillRect(this.x - 2, (this.y + this.h) - 1, 3, 1, true);
        mgraphics.fillRect(this.x - 2, this.y + this.yScroll, 3, MainTabNew.wOneItem, true);
    }

    public void setYScrool(int i, int i2) {
        this.yScroll = (i * this.hScroll) / i2;
    }
}
