package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import Main.GameCanvas;
import Model.AvMain;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/PopupChat.class */
public class PopupChat extends AvMain {
    int dy;
    public int h;
    public int w;
    public int timeOff;
    public String[] strChat;
    public String name;
    public static mImage[] mPopup = new mImage[2];
    int x = GameCanvas.hw;
    int y = GameCanvas.hh;
    public int indexpaint = 0;
    boolean isStop = true;
    int[] color = {-13427660, -16788};

    public void setChat(String str, boolean z) {
        this.isStop = z;
        String trim = str.trim();
        this.w = mFont.tahoma_7_black.getWidth(trim);
        if (this.w > 100) {
            this.w = 100;
        } else if (this.w < 20) {
            this.w = 20;
        }
        this.dy = 8;
        this.name = trim;
        this.strChat = mFont.tahoma_7_black.splitFontArray(trim, this.w);
        this.h = this.strChat.length * GameCanvas.hText;
        if (this.strChat.length <= 2) {
            this.timeOff = 80;
        } else {
            this.timeOff = 140;
        }
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (this.dy > 0) {
            this.dy -= 2;
        }
        paintPopup(mgraphics);
    }

    public void updatePos(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public boolean setOff() {
        this.timeOff--;
        if (this.timeOff <= 0 && this.isStop) {
            return true;
        }
        return false;
    }

    public void paintPopup(mGraphics mgraphics) {
        try {
            int i = (this.y - this.h) + this.dy;
            int i2 = this.x - (this.w / 2);
            mgraphics.setColor(this.color[0]);
            mgraphics.fillRect(i2 - 3, i, this.w + 6, this.h, false);
            mgraphics.fillRect(i2, i - 3, this.w, this.h + 6, false);
            mgraphics.setColor(this.color[1]);
            mgraphics.fillRect(i2, i - 2, this.w, this.h + 4, false);
            mgraphics.fillRect(i2 - 2, i, this.w + 4, this.h, false);
            mgraphics.drawRegion(mPopup[0], 0, 0, 3, 3, 0, i2 - 3, i - 3, 0, false);
            mgraphics.drawRegion(mPopup[0], 0, 3, 3, 3, 0, i2 + this.w, i - 3, 0, false);
            mgraphics.drawRegion(mPopup[0], 0, 9, 3, 3, 0, i2 - 3, i + this.h, 0, false);
            mgraphics.drawRegion(mPopup[0], 0, 6, 3, 3, 0, i2 + this.w, i + this.h, 0, false);
            if (this.indexpaint == 1) {
                mgraphics.drawRegion(mPopup[1], 0, 0, 7, 7, 3, (i2 + (this.w / 2)) - 3, i - 9, 0, false);
            } else {
                mgraphics.drawImage(mPopup[1], (i2 + (this.w / 2)) - 3, i + this.h + 2, 0, false);
            }
            if (this.strChat != null) {
                for (int i3 = 0; i3 < this.strChat.length; i3++) {
                    mFont.tahoma_7_black.drawString(mgraphics, this.strChat[i3], i2 + (this.w / 2), i + 1 + (i3 * GameCanvas.hText), 2, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
