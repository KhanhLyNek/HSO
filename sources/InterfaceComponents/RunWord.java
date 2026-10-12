package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import Main.GameCanvas;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/RunWord.class */
public class RunWord {
    public boolean showDlg = false;
    public int nStepToShow;
    public int currentDlgStep;
    public int dlgActionType;
    public String[] dlgChainInfo;
    public int dlgFocusX;
    public int dlgFocusY;
    public int dlgRunX;
    public int dlgRunY;
    public int dlgW;
    public int dlgX;
    mFont f;

    public void startDialogChain(String str, int i, int i2, int i3, int i4, mFont mfont) {
        this.f = mfont;
        this.dlgActionType = i;
        this.dlgFocusX = i2;
        this.dlgFocusY = i3;
        this.currentDlgStep = 0;
        this.dlgW = i4;
        if (this.dlgW > GameCanvas.w - 10) {
            this.dlgW = GameCanvas.w - 10;
        }
        this.dlgChainInfo = mfont.splitFontArray(str, this.dlgW);
        this.dlgX = this.dlgFocusX;
        this.showDlg = true;
        this.dlgRunX = 0;
        this.dlgRunY = 0;
    }

    public boolean checkDlgStep() {
        if (this.dlgRunY < this.dlgChainInfo.length) {
            return false;
        }
        return true;
    }

    public boolean nextDlgStep() {
        if (this.dlgRunY < this.dlgChainInfo.length) {
            this.dlgRunY = this.dlgChainInfo.length;
            this.dlgRunX = 0;
            return false;
        }
        this.dlgRunY = 0;
        this.dlgRunX = 0;
        return true;
    }

    public void updateDlg() {
        if (this.showDlg && this.dlgRunY < this.dlgChainInfo.length) {
            this.dlgRunX += 2;
            if (this.dlgRunX >= this.dlgChainInfo[this.dlgRunY].length()) {
                this.dlgRunX = 0;
                this.dlgRunY++;
            }
        }
    }

    public void paintText(mGraphics mgraphics) {
        int i = this.dlgFocusY;
        for (int i2 = 0; i2 < this.dlgRunY; i2++) {
            this.f.drawString(mgraphics, this.dlgChainInfo[i2], this.dlgX, i + (i2 * GameCanvas.hText), 0, false);
        }
        if (this.dlgRunY < this.dlgChainInfo.length) {
            this.f.drawString(mgraphics, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX), this.dlgX, i + (this.dlgRunY * GameCanvas.hText), 0, false);
        }
    }

    public void paintText(mGraphics mgraphics, int i) {
        int i2 = this.dlgFocusY;
        if (i == 2) {
            for (int i3 = 0; i3 < this.dlgRunY; i3++) {
                this.f.drawString(mgraphics, this.dlgChainInfo[i3], this.dlgX + (this.dlgW / 2), i2 + (i3 * GameCanvas.hText), 2, false);
            }
            if (this.dlgRunY < this.dlgChainInfo.length) {
                this.f.drawString(mgraphics, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX), this.dlgX + (this.dlgW / 2), i2 + (this.dlgRunY * GameCanvas.hText), 2, false);
                return;
            }
            return;
        }
        for (int i4 = 0; i4 < this.dlgRunY; i4++) {
            this.f.drawString(mgraphics, this.dlgChainInfo[i4], this.dlgX, i2 + (i4 * GameCanvas.hText), 0, false);
        }
        if (this.dlgRunY < this.dlgChainInfo.length) {
            this.f.drawString(mgraphics, this.dlgChainInfo[this.dlgRunY].substring(0, this.dlgRunX), this.dlgX, i2 + (this.dlgRunY * GameCanvas.hText), 0, false);
        }
    }
}
