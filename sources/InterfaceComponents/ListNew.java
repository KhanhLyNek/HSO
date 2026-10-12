package InterfaceComponents;

import CLib.mVector;
import Main.GameCanvas;
import Model.CRes;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/ListNew.class */
public class ListNew {
    public int maxW;
    public int itemH;
    public int maxH;
    public int maxSize;
    public int x;
    public int y;
    public int value;
    public int cmtoX;
    public int cmx;
    public int cmdy;
    public int cmvy;
    public int cmxLim;
    private int pointerDownTime;
    private int pointerDownFirstX;
    private boolean pointerIsDowning;
    private boolean isDownWhenRunning;
    private int cmRun;
    mVector vecCmd;
    public int w;
    int cmvx;
    int cmdx;
    private int[] pointerDownLastX = new int[3];
    iCommand cmdCenter = null;
    int pa = 0;
    boolean trans = false;

    public ListNew(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.x = i;
        this.y = i2;
        this.maxW = i3;
        this.maxH = i4;
        this.itemH = i5;
        this.maxSize = i6;
        this.cmxLim = i7;
    }

    public void updateMenuKey() {
        boolean z = false;
        if (GameCanvas.keyMyPressed[2]) {
            z = true;
            this.value--;
            if (this.value < 0) {
                this.value = this.maxSize - 1;
            }
            GameCanvas.clearKeyPressed(2);
            GameCanvas.clearKeyPressed(4);
        } else if (GameCanvas.keyMyPressed[8]) {
            z = true;
            this.value++;
            if (this.value > this.maxSize - 1) {
                this.value = this.maxSize - 1;
            }
            GameCanvas.clearKeyPressed(6);
            GameCanvas.clearKeyPressed(8);
        }
        if (z) {
            this.cmtoX = ((this.value + 1) * this.itemH) - (this.maxH / 2);
            if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            }
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            }
            if (this.value == this.maxSize - 1 || this.value == 0) {
                this.cmx = this.cmtoX;
            }
        }
        update_Pos_UP_DOWN();
    }

    public void update_Pos_UP_DOWN() {
        int i;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.x, this.y, this.maxW, this.maxH)) {
                for (int i2 = 0; i2 < this.pointerDownLastX.length; i2++) {
                    this.pointerDownLastX[0] = GameCanvas.py;
                }
                this.pointerDownFirstX = GameCanvas.py;
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                this.pointerDownTime++;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                }
                int i3 = GameCanvas.py - this.pointerDownLastX[0];
                if (i3 != 0 && this.value != -1) {
                    this.value = -1;
                }
                for (int length = this.pointerDownLastX.length - 1; length > 0; length--) {
                    this.pointerDownLastX[length] = this.pointerDownLastX[length - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.py;
                this.cmtoX -= i3;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                }
                if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                }
                if (this.cmx < 0 || this.cmx > this.cmxLim) {
                    i3 /= 2;
                }
                this.cmx -= i3;
            }
        }
        if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            int i4 = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(i4) < 20 && CRes.abs(GameCanvas.py - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerSelect) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.pointerDownTime = 0;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
            } else if (this.value == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    int i5 = (GameCanvas.py - this.pointerDownLastX[0]) + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    if (i5 > 10) {
                        i = 10;
                    } else if (i5 < -10) {
                        i = -10;
                    } else {
                        i = 0;
                    }
                    this.cmRun = (-i) * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick = false;
        }
    }

    public void updatePos_LEFT_RIGHT() {
        int i;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.x, this.y, this.maxW, this.maxH)) {
                for (int i2 = 0; i2 < this.pointerDownLastX.length; i2++) {
                    this.pointerDownLastX[0] = GameCanvas.px;
                }
                this.pointerDownFirstX = GameCanvas.px;
                this.pointerIsDowning = true;
                this.isDownWhenRunning = this.cmRun != 0;
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                this.pointerDownTime++;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                }
                int i3 = GameCanvas.px - this.pointerDownLastX[0];
                if (i3 != 0 && this.value != -1) {
                    this.value = -1;
                }
                for (int length = this.pointerDownLastX.length - 1; length > 0; length--) {
                    this.pointerDownLastX[length] = this.pointerDownLastX[length - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.px;
                this.cmtoX -= i3;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                }
                if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                }
                if (this.cmx < 0 || this.cmx > this.cmxLim) {
                    i3 /= 2;
                }
                this.cmx -= i3;
            }
        }
        if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            int i4 = GameCanvas.px - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(i4) < 20 && CRes.abs(GameCanvas.px - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerSelect) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.pointerDownTime = 0;
            } else if (this.value != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
            } else if (this.value == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    int i5 = (GameCanvas.px - this.pointerDownLastX[0]) + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    if (i5 > 10) {
                        i = 10;
                    } else if (i5 < -10) {
                        i = -10;
                    } else {
                        i = 0;
                    }
                    this.cmRun = (-i) * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerClick = false;
        }
    }

    public void moveCamera() {
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            this.cmtoX += this.cmRun / 100;
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            } else if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            } else {
                this.cmx = this.cmtoX;
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
    }

    public void updateMenu() {
        moveCamera();
        updateMenuKey();
    }
}
