package GameScreen;

import Build_Indo.IndoServer;
import CLib.mFont;
import CLib.mGraphics;
import GameObjects.MainQuest;
import InterfaceComponents.RunWord;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;

/* loaded from: KnightOnline_v403.jar:GameScreen/StoryScreen.class */
public class StoryScreen extends MainScreen {
    String[] strcontent;
    int maxH;
    int ypaint;
    int speed;
    public static int[] mTypeStory = null;
    private iCommand cmdNext;
    int indexType = 0;
    RunWord run = new RunWord();

    public static void setTypeStory() {
        if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
            mTypeStory = new int[]{0, 0, 0};
        } else {
            mTypeStory = new int[]{0, 0};
        }
    }

    public void setCmdnext() {
        if (this.cmdNext == null) {
            this.cmdNext = new iCommand(T.next, -1, this);
            this.cmdNext.caption = T.next;
            this.cmdNext.setPos(GameCanvas.hw, GameCanvas.h - iCommand.hButtonCmd, null, this.cmdNext.caption);
            this.center = this.cmdNext;
        }
    }

    public void setContent() {
        setCmdnext();
        if (this.indexType >= mTypeStory.length) {
            GameCanvas.game.Show();
            GameCanvas.hLoad = GameCanvas.hh;
            this.indexType = 0;
            if (MainQuest.vecQuestList != null) {
                for (int i = 0; i < MainQuest.vecQuestList.size(); i++) {
                    ((MainQuest) MainQuest.vecQuestList.elementAt(i)).beginQuest();
                    GameScreen.player.Direction = 1;
                }
                return;
            }
            return;
        }
        int i2 = 220;
        if (220 > GameCanvas.w) {
            i2 = GameCanvas.w - 10;
        }
        if (mTypeStory[this.indexType] == 0) {
            this.strcontent = mFont.tahoma_7b_white.splitFontArray(T.story[this.indexType], i2);
            this.maxH = 180 + (this.strcontent.length * GameCanvas.hText);
            this.speed = 1;
            if (this.strcontent.length < 4) {
                this.speed = 2;
            }
        } else {
            this.run.startDialogChain(T.story[this.indexType], 0, GameCanvas.hw - (i2 / 2), GameCanvas.hh, 220, mFont.tahoma_7b_white);
        }
        this.indexType++;
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        mgraphics.setColor(0);
        mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        mgraphics.setClip(0, GameCanvas.hh - 90, GameCanvas.w, 180);
        if (this.strcontent != null) {
            for (int i = 0; i < this.strcontent.length; i++) {
                mFont.tahoma_7b_white.drawString(mgraphics, this.strcontent[i], GameCanvas.hw, ((GameCanvas.hh + 80) + (i * GameCanvas.hText)) - this.ypaint, 2, true);
            }
        } else if (this.run.dlgChainInfo != null) {
            this.run.paintText(mgraphics);
            GameCanvas.resetTrans(mgraphics);
            AvMain.Font3dWhite(mgraphics, T.next, GameCanvas.hw, (GameCanvas.h - (GameCanvas.hCommand / 2)) - 4, 2);
        }
        mgraphics.endClip();
        super.paint(mgraphics);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (this.strcontent != null) {
            this.ypaint += this.speed;
            if (this.ypaint > this.maxH) {
                this.strcontent = null;
                setContent();
                this.ypaint = 0;
                return;
            }
            return;
        }
        if (this.run.dlgChainInfo != null) {
            this.run.updateDlg();
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                this.indexType = mTypeStory.length;
                setContent();
                return;
            default:
                return;
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (GameCanvas.keyMyPressed[5]) {
            this.indexType = mTypeStory.length;
            setContent();
            GameCanvas.clearKeyPressed(5);
        }
        if (this.run.dlgChainInfo != null) {
            if (GameCanvas.keyMyHold[5] && this.run.nextDlgStep()) {
                this.run.dlgChainInfo = null;
                setContent();
                GameCanvas.clearKeyHold(5);
            }
            super.updatekey();
        }
    }
}
