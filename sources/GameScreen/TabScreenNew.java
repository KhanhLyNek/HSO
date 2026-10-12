package GameScreen;

import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.TabRebuildItem;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.T;

/* loaded from: KnightOnline_v403.jar:GameScreen/TabScreenNew.class */
public class TabScreenNew extends MainScreen {
    public int selectTab = 0;
    public mVector VecTabScreen = new mVector("TabScreenNew VecTabScreen");
    public static iCommand cmdTab;
    public static iCommand cmdBack;
    public static final int CMD_TAB_CLOSE = 0;
    public static final int CMD_BACK_TAB = 1;
    public static int timeRepaint = 0;
    public static int xback;
    public static int yback;

    public TabScreenNew() {
        cmdBack = new iCommand(T.close, 0, this);
        xback = GameCanvas.w - 20;
        yback = 17;
        if (GameCanvas.isTouch) {
            cmdBack.setPos(xback, yback, PaintInfoGameScreen.fraCloseMenu, "");
        }
    }

    public void setCaptionCmd() {
        if (!GameCanvas.isTouch) {
            cmdBack.caption = T.close;
        }
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        mSystem.outloi("goi ham co truyen lastScreen");
    }

    @Override // GameScreen.MainScreen
    public void Show(MainScreen mainScreen) {
        timeRepaint = 10;
        if (GameScreen.help.setStep_Next(6, 2)) {
            this.selectTab = 2;
        }
        if (GameCanvas.isTouch) {
            MainTabNew.Focus = (byte) 1;
            getCurrentTab().init();
            MainTabNew.timePaintInfo = 0;
            this.right = cmdBack;
        } else {
            MainTabNew.Focus = (byte) 0;
        }
        super.Show();
        GameCanvas.currentScreen.lastScreen = mainScreen;
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                if (this.lastScreen == null || this.lastScreen == GameCanvas.currentScreen || GameCanvas.currentScreen == GameCanvas.AllInfo) {
                    GameCanvas.game.Show();
                } else {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                }
                if (GameScreen.help.setStep_Next(4, 9)) {
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void addMoreTab(mVector mvector) {
        this.VecTabScreen.removeAllElements();
        this.VecTabScreen = mvector;
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        MainTabNew currentTab = getCurrentTab();
        GameCanvas.resetTrans(mgraphics);
        MainTabNew.gI().paintTab(mgraphics, currentTab.nameTab, this.selectTab, this.VecTabScreen, currentTab.isClan);
        currentTab.paint(mgraphics);
        GameCanvas.resetTrans(mgraphics);
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && GameCanvas.currentScreen == this) {
            if (GameCanvas.isTouch) {
                super.paintCmd(mgraphics);
            } else if (MainTabNew.Focus == 0) {
                paintCmd_OnlyText(mgraphics);
            } else {
                currentTab.paintCmd_OnlyText(mgraphics);
            }
        } else {
            timeRepaint = 10;
        }
        if (GameScreen.help.Step >= 0 && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null && currentTab.typeTab != 0) {
            GameScreen.help.itemTabHelp(mgraphics, null, currentTab.typeTab);
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (this.lastScreen == GameCanvas.game) {
            this.lastScreen.update();
        }
        MainTabNew currentTab = getCurrentTab();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.currentScreen == this) {
            currentTab.update();
        }
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null) {
            timeRepaint = 10;
        } else if (timeRepaint > 0) {
            timeRepaint--;
        }
    }

    private MainTabNew getCurrentTab() {
        return (MainTabNew) this.VecTabScreen.elementAt(this.selectTab);
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null) {
            return;
        }
        if (MainTabNew.Focus == 0) {
            timeRepaint = 10;
            this.left = null;
            this.center = null;
            this.right = cmdBack;
            int i = this.selectTab;
            if (GameCanvas.keyMyHold[2]) {
                this.selectTab--;
                GameCanvas.clearKeyHold();
                if (TabRebuildItem.resetItemReplace) {
                    TabRebuildItem.itemFree = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemWing = null;
                    TabRebuildItem.resetItemReplace = false;
                }
            } else if (GameCanvas.keyMyHold[8]) {
                this.selectTab++;
                GameCanvas.clearKeyHold();
                if (TabRebuildItem.resetItemReplace) {
                    TabRebuildItem.itemFree = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemWing = null;
                    TabRebuildItem.resetItemReplace = false;
                }
            } else if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
                GameCanvas.clearKeyHold();
                GameCanvas.clearKeyPressed();
                SetInit();
            }
            this.selectTab = resetSelect(this.selectTab, this.VecTabScreen.size() - 1, true);
            if (i != this.selectTab) {
                MainScreen.cameraSub.yCam = 0;
            }
            MainTabNew currentTab = getCurrentTab();
            if (currentTab.typeTab == 7 || currentTab.typeTab == 11) {
                currentTab.init();
            }
            if (GameScreen.help.setStep_Next(3, 8)) {
                if (currentTab.typeTab == 1) {
                    currentTab.init();
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                }
            } else if (GameScreen.help.setStep_Next(7, 9)) {
                if (currentTab.typeTab == 3) {
                    currentTab.init();
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                }
            } else if (GameScreen.help.setStep_Next(9, 1) && currentTab.typeTab == 4) {
                currentTab.init();
                GameScreen.help.Next++;
                GameScreen.help.setNext();
            }
            super.updatekey();
            return;
        }
        MainTabNew currentTab2 = getCurrentTab();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) {
            currentTab2.updatekey();
        }
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null || GameCanvas.subDialog != null) {
            return;
        }
        MainTabNew currentTab = getCurrentTab();
        if (currentTab.typeTab == 10 && TabRebuildItem.isBeginEff != 0) {
            return;
        }
        if (GameCanvas.isPointSelect(MainTabNew.gI().xTab, MainTabNew.gI().yTab + (GameCanvas.h / 5), MainTabNew.wOneItem + (MainTabNew.wOne5 * 2), MainTabNew.wOneItem * this.VecTabScreen.size())) {
            int resetSelect = resetSelect((GameCanvas.py - (MainTabNew.gI().yTab + (GameCanvas.h / 5))) / MainTabNew.wOneItem, this.VecTabScreen.size() - 1, false);
            if (resetSelect != this.selectTab) {
                if (TabRebuildItem.resetItemReplace) {
                    TabRebuildItem.itemFree = null;
                    TabRebuildItem.itemPlus = null;
                    TabRebuildItem.itemWing = null;
                    TabRebuildItem.resetItemReplace = false;
                }
                timeRepaint = 10;
                this.selectTab = resetSelect;
                SetInit();
                currentTab = getCurrentTab();
                if (GameScreen.help.setStep_Next(3, 8)) {
                    if (currentTab.typeTab == 1) {
                        GameScreen.help.NextStep();
                        GameScreen.help.setNext();
                    }
                } else if (GameScreen.help.setStep_Next(7, 9)) {
                    if (currentTab.typeTab == 3) {
                        GameScreen.help.NextStep();
                        GameScreen.help.setNext();
                    }
                } else if (GameScreen.help.setStep_Next(9, 1) && currentTab.typeTab == 4) {
                    GameScreen.help.Next++;
                    GameScreen.help.setNext();
                }
                mSound.playSound(41, mSound.volumeSound);
            }
            GameCanvas.isPointerSelect = false;
        }
        currentTab.updatePointer();
        super.updatePointer();
    }

    public void SetInit() {
        MainTabNew.Focus = (byte) 1;
        getCurrentTab().init();
    }

    @Override // GameScreen.MainScreen
    public void keyPress(int i) {
        getCurrentTab().keypress(i);
        super.keyPress(i);
    }

    public void setvecShop(mVector mvector) {
    }
}
