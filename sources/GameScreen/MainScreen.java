package GameScreen;

import CLib.mGraphics;
import Main.GameCanvas;
import Model.AvMain;
import Model.mCamera;

/* loaded from: KnightOnline_v403.jar:GameScreen/MainScreen.class */
public class MainScreen extends AvMain {
    MainScreen mainScreen;
    public MainScreen lastScreen;
    public static mCamera cameraMain;
    public static mCamera cameraSub;
    public String name = "";

    public void Show() {
        GameCanvas.clearKeyPressed();
        GameCanvas.currentScreen = this;
        GameCanvas.end_Dialog();
        GameCanvas.menu2.isShowMenu = false;
    }

    public void Show(MainScreen mainScreen) {
        if (mainScreen != null) {
            this.lastScreen = mainScreen;
        }
        GameCanvas.clearKeyPressed();
        GameCanvas.currentScreen = this;
        GameCanvas.end_Dialog();
        GameCanvas.menu2.isShowMenu = false;
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        super.paint(mgraphics);
    }

    @Override // Model.AvMain
    public void update() {
    }

    public void keyPress(int i) {
    }

    public void keyBack() {
    }

    public boolean isGameScr() {
        return false;
    }
}
