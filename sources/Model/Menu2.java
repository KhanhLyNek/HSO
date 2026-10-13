package Model;

import Build_Usa.Usa_Server;
import CLib.Main;
import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.Item;
import GameObjects.MainHelp;
import GameObjects.MainItem;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.RunWord;
import InterfaceComponents.TabConfig;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import com.silverknight.TemMidlet;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:Model/Menu2.class */
public class Menu2 extends AvMain {
    public boolean isShowMenu;
    public mVector menuItems;
    public int menuSelectedItem;
    public int SelectFocus;
    public int menuX;
    public int menuY;
    public int menuW;
    public int menuH;
    public int menuTemY;
    public int hPlus;
    public static int cmtoX;
    public static int cmx;
    public static int cmdy;
    public static int cmvy;
    public static int cmxLim;
    public static int xc;
    int pos;
    int sizeMenu;
    String[] mStrTalk;
    public RunWord runText;
    public static byte isNPCMenu;
    public static final byte NORMAL = 0;
    public static final byte NPC_MENU = 1;
    public static final byte QUICK_MENU = 2;
    mVector vecFocus;
    int IdMenu;
    int IdNpc;
    public static int IdNPCLast;
    byte typeO;
    int hShow;
    int maxShow;
    boolean disableClose;
    int archorRunText;
    iCommand cmdChangeScreen;
    iCommand cmdTouch;
    public iCommand cmdNgua;
    int xBegin;
    int w2cmd;
    public int w;
    private int pointerDownTime;
    private int pointerDownFirstX;
    private boolean pointerIsDowning;
    private boolean isDownWhenRunning;
    private int waitToPerform;
    private int cmRun;
    int cmvx;
    int cmdx;
    public static boolean isHelp = false;
    public static boolean isGiaotiep = false;
    public static boolean isPaint = true;
    public static boolean isLoadData = true;
    public static MainObject objSelect = null;
    String nameMenu = "";
    byte timeShow = 0;
    public boolean isSv = false;
    boolean isPosPoint = false;
    int pa = 0;
    boolean trans = false;
    private int[] pointerDownLastX = new int[3];

    public void startAt(mVector mvector, int i, String str, boolean z, mVector mvector2) {
        iCommand icommand;
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.runText = null;
        this.right = null;
        isNPCMenu = (byte) 0;
        isGiaotiep = z;
        this.vecFocus = mvector2;
        this.SelectFocus = 0;
        if (isGiaotiep && (this.vecFocus == null || this.vecFocus.size() == 0)) {
            return;
        }
        this.nameMenu = str;
        isHelp = false;
        this.disableClose = false;
        this.menuSelectedItem = 0;
        this.pos = i;
        if (mvector == null || mvector.size() == 0) {
            return;
        }
        this.menuItems = mvector;
        this.isShowMenu = true;
        if (i == -1) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
            this.hPlus = 0;
            this.menuW = 60;
            this.menuH = 60;
            for (int i2 = 0; i2 < mvector.size(); i2++) {
                iCommand icommand2 = (iCommand) mvector.elementAt(i2);
                if (mFont.tahoma_7_yellow.getWidth(icommand2.caption) > this.menuW - 8) {
                    icommand2.subCaption = mFont.tahoma_7b_yellow.splitFontArray(icommand2.caption, this.menuW - 8);
                }
            }
            this.w = (mvector.size() * this.menuW) - 1;
            if (this.w > GameCanvas.w - 2) {
                this.w = GameCanvas.w - 2;
            }
            this.menuX = GameCanvas.hw - (this.w / 2);
            if (this.menuX < 1) {
                this.menuX = 1;
            }
            this.menuY = (GameCanvas.h - this.menuH) - (GameCanvas.hCommand + 1);
            if (GameCanvas.isTouch) {
                this.menuY -= 3;
            }
            this.menuY += 27;
            this.menuTemY = this.menuY;
            cmxLim = (this.menuItems.size() * this.menuW) - GameCanvas.w;
            if (cmxLim < 0) {
                cmxLim = 0;
            }
            cmtoX = 0;
            cmx = 0;
            xc = 50;
        } else {
            if (isGiaotiep) {
                objSelect = (MainObject) this.vecFocus.elementAt(0);
            }
            this.menuW = GameCanvas.hCommand;
            if (GameCanvas.isTouch) {
                this.menuW = 32;
            }
            this.sizeMenu = (((GameCanvas.h / 4) * 3) / this.menuW) - (z ? 2 : 1);
            if (GameCanvas.isTouch) {
                this.sizeMenu++;
            }
            this.w = GameCanvas.w / 3;
            if (this.w < mFont.tahoma_7b_white.getWidth(str) + 30) {
                this.w = mFont.tahoma_7b_white.getWidth(str) + 30;
            }
            this.hPlus = GameCanvas.hCommand;
            if (z) {
                this.hPlus += this.menuW;
            }
            int i3 = 120;
            int i4 = 30;
            if (z) {
                i4 = 50;
            }
            for (int i5 = 0; i5 < mvector.size(); i5++) {
                int width = mFont.tahoma_7b_white.getWidth(((iCommand) mvector.elementAt(i5)).caption) + i4;
                if (width > i3) {
                    i3 = width;
                }
            }
            if (this.w < i3) {
                this.w = i3;
            }
            if (this.w > GameCanvas.w) {
                this.w = GameCanvas.w;
            }
            cmtoX = 0;
            cmx = 0;
            if (GameCanvas.isTouch) {
                icommand = new iCommand(T.close, 1, this);
            } else {
                icommand = new iCommand(T.back, 1, this);
            }
            if (mvector.size() > this.sizeMenu) {
                this.menuH = (this.sizeMenu * this.menuW) + 8;
                cmxLim = (mvector.size() - this.sizeMenu) * this.menuW;
            } else {
                this.menuH = (mvector.size() * this.menuW) + 8;
                cmxLim = 0;
            }
            setPos();
            this.menuTemY = this.menuY;
            if (icommand != null) {
                if (GameCanvas.isTouch) {
                    icommand.setPos((this.menuX + this.w) - 11, ((this.menuY - this.hPlus) + (GameCanvas.hCommand / 2)) - 2, PaintInfoGameScreen.fraCloseMenu, "");
                }
                this.right = icommand;
            }
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        resetBegin();
    }

    public void startAt_Sv(mVector mvector, int i, String str, boolean z, mVector mvector2) {
        iCommand icommand;
        this.isSv = true;
        isLoadData = false;
        this.waitToPerform = 0;
        this.runText = null;
        this.right = null;
        isNPCMenu = (byte) 0;
        isGiaotiep = z;
        this.vecFocus = mvector2;
        this.SelectFocus = 0;
        if (isGiaotiep && (this.vecFocus == null || this.vecFocus.size() == 0)) {
            return;
        }
        this.nameMenu = str;
        isHelp = false;
        this.disableClose = false;
        this.menuSelectedItem = 0;
        this.pos = i;
        if (mvector == null || mvector.size() == 0) {
            return;
        }
        this.menuItems = mvector;
        this.isShowMenu = true;
        if (i == -1) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
            this.hPlus = 0;
            this.menuW = 60;
            this.menuH = 60;
            for (int i2 = 0; i2 < mvector.size(); i2++) {
                iCommand icommand2 = (iCommand) mvector.elementAt(i2);
                if (mFont.tahoma_7_yellow.getWidth(icommand2.caption) > this.menuW - 8) {
                    icommand2.subCaption = mFont.tahoma_7b_yellow.splitFontArray(icommand2.caption, this.menuW - 8);
                }
            }
            this.w = (mvector.size() * this.menuW) - 1;
            if (this.w > GameCanvas.w - 2) {
                this.w = GameCanvas.w - 2;
            }
            this.menuX = GameCanvas.hw - (this.w / 2);
            if (this.menuX < 1) {
                this.menuX = 1;
            }
            this.menuY = (GameCanvas.h - this.menuH) - (GameCanvas.hCommand + 1);
            if (GameCanvas.isTouch) {
                this.menuY -= 3;
            }
            this.menuY += 27;
            this.menuTemY = this.menuY;
            cmxLim = (this.menuItems.size() * this.menuW) - GameCanvas.w;
            if (cmxLim < 0) {
                cmxLim = 0;
            }
            cmtoX = 0;
            cmx = 0;
            xc = 50;
        } else {
            if (isGiaotiep) {
                objSelect = (MainObject) this.vecFocus.elementAt(0);
            }
            this.menuW = GameCanvas.hCommand;
            if (GameCanvas.isTouch) {
                this.menuW = 32;
            }
            this.sizeMenu = (((GameCanvas.h / 4) * 3) / this.menuW) - (z ? 2 : 1);
            if (GameCanvas.isTouch) {
                this.sizeMenu++;
            }
            this.w = GameCanvas.w / 3;
            if (this.w < mFont.tahoma_7b_white.getWidth(str) + 30) {
                this.w = mFont.tahoma_7b_white.getWidth(str) + 30;
            }
            this.hPlus = GameCanvas.hCommand;
            if (z) {
                this.hPlus += this.menuW;
            }
            int i3 = 120;
            int i4 = 30;
            if (z) {
                i4 = 50;
            }
            for (int i5 = 0; i5 < mvector.size(); i5++) {
                int width = mFont.tahoma_7b_white.getWidth(((iCommand) mvector.elementAt(i5)).caption) + i4;
                if (width > i3) {
                    i3 = width;
                }
            }
            if (this.w < i3) {
                this.w = i3;
            }
            if (this.w > GameCanvas.w) {
                this.w = GameCanvas.w;
            }
            cmtoX = 0;
            cmx = 0;
            if (GameCanvas.isTouch) {
                icommand = new iCommand(T.close, 1, this);
            } else {
                icommand = new iCommand(T.back, 1, this);
            }
            if (mvector.size() > this.sizeMenu) {
                this.menuH = (this.sizeMenu * this.menuW) + 8;
                cmxLim = (mvector.size() - this.sizeMenu) * this.menuW;
            } else {
                this.menuH = (mvector.size() * this.menuW) + 8;
                cmxLim = 0;
            }
            setPos();
            this.menuTemY = this.menuY;
            if (icommand != null) {
                if (GameCanvas.isTouch) {
                    icommand.setPos((this.menuX + this.w) - 11, ((this.menuY - this.hPlus) + (GameCanvas.hCommand / 2)) - 2, PaintInfoGameScreen.fraCloseMenu, "");
                }
                this.right = icommand;
            }
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        resetBegin();
    }

    public void startAt_Map_World(mVector mvector, int i, String str, boolean z, mVector mvector2) {
        iCommand icommand;
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.runText = null;
        this.right = null;
        isNPCMenu = (byte) 0;
        isGiaotiep = z;
        this.vecFocus = mvector2;
        this.SelectFocus = 0;
        if (isGiaotiep && (this.vecFocus == null || this.vecFocus.size() == 0)) {
            return;
        }
        this.nameMenu = str;
        isHelp = false;
        this.disableClose = false;
        this.menuSelectedItem = 0;
        this.pos = i;
        if (mvector == null || mvector.size() == 0) {
            return;
        }
        this.menuItems = mvector;
        this.isShowMenu = true;
        if (i == -1) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
            this.hPlus = 0;
            this.menuW = 60;
            this.menuH = 60;
            for (int i2 = 0; i2 < mvector.size(); i2++) {
                iCommand icommand2 = (iCommand) mvector.elementAt(i2);
                if (mFont.tahoma_7_yellow.getWidth(icommand2.caption) > this.menuW - 8) {
                    icommand2.subCaption = mFont.tahoma_7b_yellow.splitFontArray(icommand2.caption, this.menuW - 8);
                }
            }
            this.w = (mvector.size() * this.menuW) - 1;
            if (this.w > GameCanvas.w - 2) {
                this.w = GameCanvas.w - 2;
            }
            this.menuX = GameCanvas.hw - (this.w / 2);
            if (this.menuX < 1) {
                this.menuX = 1;
            }
            this.menuY = (GameCanvas.h - this.menuH) - (GameCanvas.hCommand + 1);
            if (GameCanvas.isTouch) {
                this.menuY -= 3;
            }
            this.menuY += 27;
            this.menuTemY = this.menuY;
            cmxLim = (this.menuItems.size() * this.menuW) - GameCanvas.w;
            if (cmxLim < 0) {
                cmxLim = 0;
            }
            cmtoX = 0;
            cmx = 0;
            xc = 50;
        } else {
            if (isGiaotiep) {
                objSelect = (MainObject) this.vecFocus.elementAt(0);
            }
            this.menuW = GameCanvas.hCommand;
            if (GameCanvas.isTouch) {
                this.menuW = 32;
            }
            this.sizeMenu = (((GameCanvas.h / 4) * 3) / this.menuW) - (z ? 2 : 1);
            if (GameCanvas.isTouch) {
                this.sizeMenu++;
            }
            this.w = GameCanvas.w / 3;
            if (this.w < mFont.tahoma_7b_white.getWidth(str) + 30) {
                this.w = mFont.tahoma_7b_white.getWidth(str) + 30;
            }
            this.hPlus = GameCanvas.hCommand;
            if (z) {
                this.hPlus += this.menuW;
            }
            int i3 = 120;
            int i4 = 30;
            if (z) {
                i4 = 50;
            }
            for (int i5 = 0; i5 < mvector.size(); i5++) {
                int width = mFont.tahoma_7b_white.getWidth(((iCommand) mvector.elementAt(i5)).caption) + i4;
                if (width > i3) {
                    i3 = width;
                }
            }
            if (this.w < i3) {
                this.w = i3;
            }
            if (this.w > GameCanvas.w) {
                this.w = GameCanvas.w;
            }
            cmtoX = 0;
            cmx = 0;
            if (GameCanvas.isTouch) {
                icommand = new iCommand(T.close, 1, this);
            } else {
                icommand = new iCommand(T.back, 1, this);
            }
            if (mvector.size() > this.sizeMenu) {
                this.menuH = (this.sizeMenu * this.menuW) + 8;
                cmxLim = (mvector.size() - this.sizeMenu) * this.menuW;
            } else {
                this.menuH = (mvector.size() * this.menuW) + 8;
                cmxLim = 0;
            }
            setPos();
            this.menuTemY = this.menuY;
            if (icommand != null) {
                if (GameCanvas.isTouch) {
                    icommand.setPos((this.menuX + this.w) - 11, ((this.menuY - this.hPlus) + (GameCanvas.hCommand / 2)) - 2, PaintInfoGameScreen.fraCloseMenu, "");
                }
                this.right = icommand;
            }
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        resetBegin();
    }

    public void setinfoDynamic(mVector mvector, int i, int i2, int i3, String str) {
        iCommand icommand;
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.right = null;
        this.runText = null;
        isGiaotiep = false;
        this.vecFocus = null;
        if (mvector == null) {
            return;
        }
        this.nameMenu = str;
        isHelp = false;
        isNPCMenu = (byte) 0;
        this.menuSelectedItem = 0;
        this.IdMenu = i2;
        this.IdNpc = i3;
        this.pos = i;
        this.disableClose = false;
        this.isShowMenu = true;
        this.menuItems = new mVector("Menu2 menuItem2");
        this.menuW = GameCanvas.hCommand;
        if (GameCanvas.isTouch) {
            this.menuW = 32;
        }
        this.sizeMenu = (((GameCanvas.h / 4) * 3) / this.menuW) - 1;
        if (GameCanvas.isTouch) {
            this.sizeMenu++;
        }
        this.w = GameCanvas.w / 3;
        this.hPlus = this.menuW;
        int i4 = 120;
        if (120 < mFont.tahoma_7b_white.getWidth(str) + 30) {
            i4 = mFont.tahoma_7b_white.getWidth(str) + 30;
        }
        for (int i5 = 0; i5 < mvector.size(); i5++) {
            iCommand icommand2 = (iCommand) mvector.elementAt(i5);
            iCommand icommand3 = new iCommand(icommand2.caption, 2, this);
            int width = mFont.tahoma_7b_white.getWidth(icommand2.caption) + 20;
            if (width > i4) {
                i4 = width;
            }
            this.menuItems.addElement(icommand3);
        }
        if (GameCanvas.isTouch) {
            icommand = new iCommand(T.close, 1, this);
        } else {
            icommand = new iCommand(T.back, 1, this);
        }
        this.w = i4;
        if (this.w > GameCanvas.w) {
            this.w = GameCanvas.w;
        }
        if (this.menuItems.size() > this.sizeMenu) {
            this.menuH = (this.sizeMenu * this.menuW) + 8;
            cmxLim = (this.menuItems.size() - this.sizeMenu) * this.menuW;
        } else {
            this.menuH = (this.menuItems.size() * this.menuW) + 8;
            cmxLim = 0;
        }
        cmtoX = 0;
        cmx = 0;
        setPos();
        this.menuTemY = this.menuY;
        if (icommand != null) {
            if (GameCanvas.isTouch) {
                icommand.setPos((this.menuX + this.w) - 11, ((this.menuY - this.hPlus) + (GameCanvas.hCommand / 2)) - 2, PaintInfoGameScreen.fraCloseMenu, "");
            }
            this.right = icommand;
        }
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        resetBegin();
    }

    public void startAt_NPC(mVector mvector, String str, int i, byte b, boolean z, int i2) {
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.right = null;
        isNPCMenu = (byte) 1;
        this.SelectFocus = 0;
        this.nameMenu = str;
        isHelp = false;
        this.disableClose = false;
        isGiaotiep = false;
        this.IdNpc = i;
        IdNPCLast = i;
        this.typeO = b;
        this.archorRunText = i2;
        this.menuSelectedItem = 0;
        if (mvector == null || mvector.size() == 0) {
            this.menuItems = new mVector("Menu2 menuItem3");
        } else {
            this.menuItems = mvector;
        }
        this.isShowMenu = true;
        this.menuW = GameCanvas.hCommand;
        if (GameCanvas.isTouch) {
            this.menuW = 32;
        }
        this.sizeMenu = 0;
        this.w = GameCanvas.w - 10;
        if (this.w > 300) {
            this.w = 300;
        }
        this.mStrTalk = mFont.tahoma_7_black.splitFontArray(str, this.w - 20);
        this.hPlus = GameCanvas.hCommand;
        cmtoX = 0;
        cmx = 0;
        int length = this.mStrTalk.length;
        if (!z) {
            this.menuItems.addElement(new iCommand(T.close, 1, this));
        } else if (length == 1) {
            length = 2;
        }
        this.menuH = ((length + 2) * GameCanvas.hText) + ((((this.menuItems.size() - 1) / 2) + 1) * (iCommand.hButtonCmd + 5)) + 5;
        cmxLim = 0;
        this.menuX = GameCanvas.hw - (this.w / 2);
        this.menuY = (GameCanvas.h - this.menuH) - 10;
        this.menuTemY = this.menuY;
        this.runText = new RunWord();
        this.runText.startDialogChain(str, 0, this.menuX + 10, this.menuY + 10 + GameCanvas.hText, this.w - 20, mFont.tahoma_7_white);
        setPosNPC();
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        resetBegin();
    }

    public void setAt_Quick() {
        this.isPosPoint = PaintInfoGameScreen.isLevelPoint;
        this.timeShow = (byte) 1;
        isLoadData = false;
        this.isSv = false;
        this.waitToPerform = 0;
        this.right = null;
        isNPCMenu = (byte) 2;
        this.SelectFocus = 0;
        this.nameMenu = "";
        isHelp = false;
        this.disableClose = false;
        isGiaotiep = false;
        this.menuSelectedItem = 0;
        this.w = GameCanvas.w - 40;
        this.isShowMenu = true;
        this.menuW = 40;
        cmtoX = 0;
        cmx = 0;
        this.hShow = 0;
        this.menuH = 40;
        this.maxShow = (byte) (this.w / this.menuH);
        this.menuItems = new mVector("Menu2 menuItems");
        iCommand icommand = new iCommand(T.auto, 3, 2, this);
        iCommand icommand2 = new iCommand(T.dosat, 4, 4, this);
        iCommand icommand3 = new iCommand(T.setPk, 5, 3, this);
        iCommand icommand4 = new iCommand(T.mevent, 6, 0, this);
        iCommand icommand5 = new iCommand(T.listFriend, 7, 1, this);
        this.cmdNgua = new iCommand(T.TuseNgua, 14, 10, this);
        if (Main.isPC) {
            this.cmdChangeScreen = new iCommand(T.changeScrennSmall, 8, 5, this);
            if (Main.level == 1) {
                this.cmdChangeScreen.caption = T.normalScreen;
            } else {
                this.cmdChangeScreen.caption = mGraphics.zoomLevel == 1 ? T.normalScreen : T.changeScrennSmall;
            }
        } else {
            this.cmdTouch = new iCommand(new StringBuffer().append(T.touch).append("/").append(T.keypad).toString(), 8, 5, this);
        }
        iCommand icommand6 = new iCommand(T.naptien, 11, 9, this);
        iCommand icommand7 = new iCommand(T.logout, 12, 8, this);
        this.menuItems.addElement(this.cmdNgua);
        this.menuItems.addElement(icommand4);
        this.menuItems.addElement(icommand5);
        if (GameScreen.player.myClan != null) {
            this.menuItems.addElement(new iCommand(T.clan, 9, 6, this));
        }
        if (Player.party != null) {
            this.menuItems.addElement(new iCommand(T.party, 10, 7, this));
        }
        this.menuItems.addElement(icommand);
        // Reuse the existing Auto icon frame; no extra sprites required.
        this.menuItems.addElement(new iCommand("Auto UP Level", 15, 2, this));
        this.menuItems.addElement(new iCommand("Auto Di Buon", 16, 2, this));
        this.menuItems.addElement(icommand3);
        if (!mSystem.isHideNaptien()) {
            this.menuItems.addElement(icommand6);
        }
        this.menuItems.addElement(icommand2);
        if (Main.isPC) {
            this.menuItems.addElement(this.cmdChangeScreen);
        } else {
            this.menuItems.addElement(this.cmdTouch);
        }
        this.menuItems.addElement(icommand7);
        if (this.maxShow > this.menuItems.size()) {
            this.maxShow = this.menuItems.size();
        }
        this.w = this.maxShow * this.menuH;
        cmxLim = 0;
        if (!this.isPosPoint) {
            this.menuX = GameCanvas.hw - (this.w / 2);
        } else {
            this.menuX = 20;
        }
        this.menuY = GameCanvas.h - 40;
        this.menuTemY = this.menuY;
        if (GameCanvas.isTouch) {
            this.menuSelectedItem = -1;
        }
        isLoadData = true;
        for (int i = 0; i < this.menuItems.size(); i++) {
            iCommand icommand8 = (iCommand) this.menuItems.elementAt(i);
            icommand8.setPos_ShowName(this.menuX + (this.menuH / 2) + (i * this.menuH), this.menuY + (this.menuH / 2), PaintInfoGameScreen.mfraIconQuick[icommand8.subIndex], icommand8.caption, 0, -32);
        }
        cmxLim = (this.menuItems.size() - this.maxShow) * this.menuH;
        this.menuSelectedItem = -1;
        resetBegin();
    }

    public void resetBegin() {
        for (int i = 0; i < this.pointerDownLastX.length; i++) {
            this.pointerDownLastX[i] = 0;
        }
        this.pointerDownTime = 0;
        this.pointerDownFirstX = 0;
        this.pointerIsDowning = false;
        this.isDownWhenRunning = false;
        this.waitToPerform = 0;
        this.cmRun = 0;
    }

    public void setPosNPC() {
        int size = this.menuItems.size();
        if (size == 1) {
            this.xBegin = this.menuX + (this.w / 2);
            this.w2cmd = 0;
        } else if (size == 2) {
            this.w2cmd = 20;
            this.xBegin = ((this.menuX + (this.w / 2)) - (this.w2cmd / 2)) - (iCommand.wButtonCmd / 2);
        } else {
            this.w2cmd = 20;
            this.xBegin = ((this.menuX + (this.w / 2)) - (this.w2cmd / 2)) - (iCommand.wButtonCmd / 2);
        }
        for (int i = 0; i < size; i++) {
            iCommand icommand = (iCommand) this.menuItems.elementAt(i);
            if (size == 3 && i == 2) {
                icommand.setPos(this.menuX + (this.w / 2), (((this.menuY + this.menuH) - iCommand.hButtonCmd) - (((size - 1) / 2) * (iCommand.hButtonCmd + 5))) + 7 + ((i / 2) * (iCommand.hButtonCmd + 5)), null, icommand.caption);
            } else {
                icommand.setPos(this.xBegin + ((i % 2) * (iCommand.wButtonCmd + this.w2cmd)), (((this.menuY + this.menuH) - iCommand.hButtonCmd) - (((size - 1) / 2) * (iCommand.hButtonCmd + 5))) + 7 + ((i / 2) * (iCommand.hButtonCmd + 5)), null, icommand.caption);
            }
            if (i == 0) {
                icommand.isSelect = true;
            }
        }
    }

    public void setPos() {
        switch (this.pos) {
            case 0:
                this.menuX = 2;
                this.menuY = ((GameCanvas.h - GameCanvas.hCommand) - this.menuH) - 2;
                if (GameCanvas.isTouch) {
                    this.menuY += GameCanvas.hCommand;
                    return;
                }
                return;
            case 1:
                this.menuX = (GameCanvas.w - this.w) - 2;
                this.menuY = ((GameCanvas.h - GameCanvas.hCommand) - this.menuH) - 2;
                if (GameCanvas.isTouch) {
                    this.menuY += GameCanvas.hCommand;
                    return;
                }
                return;
            case 2:
            case 4:
                this.menuX = GameCanvas.hw - (this.w / 2);
                this.menuY = (((GameCanvas.h / 2) - (this.menuH / 2)) - 2) + (this.menuW / 2) + 6;
                return;
            case 3:
                this.menuX = 2;
                this.menuY = 2;
                return;
            default:
                return;
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        mVector mvector = Item.VecInvetoryPlayer;
        switch (i) {
            case 0:
                this.isShowMenu = false;
                perform((iCommand) this.menuItems.elementAt(this.menuSelectedItem));
                break;
            case 1:
                doCloseMenu();
                break;
            case 2:
                GlobalService.gI().Dynamic_Menu((short) this.IdNpc, (byte) this.IdMenu, (byte) this.menuSelectedItem);
                this.isShowMenu = false;
                GameCanvas.isPointerSelect = false;
                break;
            case 3:
                this.isShowMenu = false;
                GameScreen.gI().doMenuAuto();
                break;
            case 4:
                this.isShowMenu = false;
                GameScreen.gI().cmdSetDoSat.perform();
                break;
            case 5:
                this.isShowMenu = false;
                GameScreen.gI().cmdSetPk.perform();
                break;
            case 6:
                this.isShowMenu = false;
                TabConfig.cmdEvent.perform();
                break;
            case 7:
                GameScreen.gI().cmdListFriend.perform();
                break;
            case 8:
                if (Main.isPC) {
                    GameCanvas.start_Left_Dialog(T.changeSizeScreen, new iCommand(T.select, 13, this));
                    break;
                } else {
                    TabConfig.cmdKeypad.perform();
                    break;
                }
            case 9:
                TabConfig.cmdShowClan.perform();
                break;
            case 10:
                GameScreen.gI().cmdParty.perform();
                break;
            case 11:
                if (mSystem.isIpAppstore()) {
                    mVector mvector2 = new mVector("Menu2 vec2");
                    for (int i3 = 0; i3 < TemMidlet.google_listGems.length; i3++) {
                        iCommand icommand = new iCommand(TemMidlet.google_listGems[i3], 20, i3, this);
                        icommand.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mvector2.addElement(icommand);
                    }
                    GameCanvas.menu2.startAt(mvector2, 2, T.naptien, false, null);
                    break;
                } else if (TemMidlet.currentIAPStore == 1) {
                    mVector mvector3 = new mVector("Menu2 vec");
                    for (int i4 = 0; i4 < TemMidlet.listGems.length; i4++) {
                        iCommand icommand2 = new iCommand(TemMidlet.listGems[i4], 20, i4, this);
                        icommand2.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mvector3.addElement(icommand2);
                    }
                    GameCanvas.menu2.startAt(mvector3, 2, T.naptien, false, null);
                    break;
                } else if (TemMidlet.currentIAPStore == 2) {
                    if (Usa_Server.isUsa_server) {
                        if (GameCanvas.naptien > 0) {
                            mVector mvector4 = new mVector("Menu2 vec2");
                            for (int i5 = 0; i5 < TemMidlet.google_listGems.length; i5++) {
                                iCommand icommand3 = new iCommand(TemMidlet.google_listGems[i5], 20, i5, this);
                                icommand3.setFraCaption(AvMain.fraDiamond, 1, 1);
                                mvector4.addElement(icommand3);
                            }
                            GameCanvas.menu2.startAt(mvector4, 2, T.naptien, false, null);
                            return;
                        }
                        if (GameCanvas.naptien <= 0) {
                            GameCanvas.start_Ok_Dialog(T.hetlannap);
                            return;
                        }
                    }
                    mVector mvector5 = new mVector("Menu2 vec2");
                    for (int i6 = 0; i6 < TemMidlet.google_listGems.length; i6++) {
                        iCommand icommand4 = new iCommand(TemMidlet.google_listGems[i6], 20, i6, this);
                        icommand4.setFraCaption(AvMain.fraDiamond, 1, 1);
                        mvector5.addElement(icommand4);
                    }
                    GameCanvas.menu2.startAt(mvector5, 2, T.naptien, false, null);
                    break;
                } else {
                    GlobalService.gI().send_cmd_server((byte) -56);
                    GameCanvas.start_Ok_Dialog(T.pleaseWait);
                    break;
                }
            case 12:
                GameScreen.player.resetAction();
                Session_ME.gI().close();
                GameCanvas.login.Show();
                GameScreen.player = new Player(0, (byte) 0, "unname", 0, 0);
                break;
            case 13:
                if (mGraphics.zoomLevel > 1) {
                }
                Main.exit();
                break;
            case 15:
                this.isShowMenu = false;
                GameScreen.gI().openTrainingMenu();
                break;
            case 16:
                this.isShowMenu = false;
                GameScreen.gI().openTradeMenu();
                break;
            case 14:
                mVector mvector6 = new mVector("Menu2 vecngua");
                this.isShowMenu = false;
                if (GameScreen.player.typeMount != -1) {
                    TabConfig.cmdXuongNgua.perform();
                    break;
                } else {
                    for (int i7 = 0; i7 < mvector.size(); i7++) {
                        MainItem mainItem = (MainItem) mvector.elementAt(i7);
                        if (mainItem != null && mainItem.ItemCatagory == 4 && isNgua((short) mainItem.Id)) {
                            mvector6.addElement(mainItem);
                        }
                    }
                    if (mvector6.size() > 0) {
                        GameScreen.gI().doMenuUseNgua(mvector6);
                        break;
                    } else {
                        GameCanvas.start_Ok_Dialog(T.khongcongua);
                        break;
                    }
                }
                break;
            case 20:
                if (mSystem.isIpAppstore()) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[i2]);
                    break;
                } else if (TemMidlet.currentIAPStore == 1) {
                    if (TemMidlet.isBlockNOKIAStore) {
                        GameCanvas.start_Ok_Dialog("Comming soon !");
                        break;
                    } else if (!TemMidlet.isBlockNOKIAStore) {
                        GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.close, -1));
                        TemMidlet.makePurchase(TemMidlet.productIds[0]);
                        break;
                    }
                } else if (TemMidlet.currentIAPStore == 2) {
                    TemMidlet.makePurchase(TemMidlet.google_productIds[i2]);
                    break;
                }
                break;
        }
        super.commandPointer(i, i2);
    }

    public boolean isNgua(short s) {
        return s == 62 || s == 63 || s == 64 || s == 65 || s == 66;
    }

    public void updateMenuKey() {
        if (!isPaint || !this.isShowMenu) {
            return;
        }
        boolean z = false;
        if (isGiaotiep) {
            if (GameCanvas.keyMyPressed[2]) {
                z = true;
                this.menuSelectedItem--;
                if (this.menuSelectedItem < 0) {
                    this.menuSelectedItem = this.menuItems.size() - 1;
                }
                GameCanvas.clearKeyPressed(2);
            } else if (GameCanvas.keyMyPressed[8]) {
                z = true;
                this.menuSelectedItem++;
                if (this.menuSelectedItem > this.menuItems.size() - 1) {
                    this.menuSelectedItem = 0;
                }
                GameCanvas.clearKeyPressed(8);
            }
            int i = this.SelectFocus;
            if (GameCanvas.keyMyPressed[4]) {
                this.SelectFocus--;
                GameCanvas.clearKeyPressed(4);
            }
            if (GameCanvas.keyMyPressed[6]) {
                this.SelectFocus++;
                GameCanvas.clearKeyPressed(6);
            }
            this.SelectFocus = resetSelect(this.SelectFocus, this.vecFocus.size() - 1, true);
            if (this.SelectFocus != i) {
                objSelect = (MainObject) this.vecFocus.elementAt(this.SelectFocus);
            }
        } else if (isNPCMenu == 1) {
            int i2 = this.menuSelectedItem;
            if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[2]) {
                this.menuSelectedItem--;
                GameCanvas.clearKeyHold(4);
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[6] || GameCanvas.keyMyHold[8]) {
                this.menuSelectedItem++;
                GameCanvas.clearKeyHold(6);
                GameCanvas.clearKeyHold(8);
            }
            this.menuSelectedItem = resetSelect(this.menuSelectedItem, this.menuItems.size() - 1, false);
            if (i2 != this.menuSelectedItem) {
                for (int i3 = 0; i3 < this.menuItems.size(); i3++) {
                    iCommand icommand = (iCommand) this.menuItems.elementAt(i3);
                    if (i3 == this.menuSelectedItem) {
                        icommand.isSelect = true;
                    } else {
                        icommand.isSelect = false;
                    }
                }
            }
            if (GameCanvas.keyMyHold[5]) {
                GameCanvas.clearKeyHold(5);
                if (this.menuSelectedItem < this.menuItems.size() && this.menuSelectedItem >= 0) {
                    ((iCommand) this.menuItems.elementAt(this.menuSelectedItem)).perform();
                }
            }
        } else if (isNPCMenu == 0) {
            if (GameCanvas.keyMyPressed[2] || GameCanvas.keyMyPressed[4]) {
                z = true;
                this.menuSelectedItem--;
                if (this.menuSelectedItem < 0) {
                    this.menuSelectedItem = this.menuItems.size() - 1;
                }
                GameCanvas.clearKeyPressed(2);
                GameCanvas.clearKeyPressed(4);
            } else if (GameCanvas.keyMyPressed[8] || GameCanvas.keyMyPressed[6]) {
                z = true;
                this.menuSelectedItem++;
                if (this.menuSelectedItem > this.menuItems.size() - 1) {
                    this.menuSelectedItem = 0;
                }
                GameCanvas.clearKeyPressed(6);
                GameCanvas.clearKeyPressed(8);
            }
        }
        if (z) {
            if (this.pos == -1) {
                cmtoX = ((this.menuSelectedItem * this.menuW) + this.menuW) - (GameCanvas.w / 2);
            } else {
                cmtoX = ((this.menuSelectedItem + 1) * this.menuW) - (this.menuH / 2);
            }
            if (cmtoX > cmxLim) {
                cmtoX = cmxLim;
            }
            if (cmtoX < 0) {
                cmtoX = 0;
            }
            if (this.menuSelectedItem == this.menuItems.size() - 1 || this.menuSelectedItem == 0) {
                cmx = cmtoX;
            }
        }
        if (isNPCMenu == 0) {
            if (this.pos == -1) {
                updatePos_LEFT_RIGHT();
            } else {
                update_Pos_UP_DOWN();
            }
            if (GameCanvas.isPointerSelect && !GameCanvas.isPoint(this.menuX - 5, (this.menuTemY - 5) - this.hPlus, this.w + 10, this.menuH + 10 + this.hPlus) && !this.isSv) {
                doCloseMenu();
            }
        } else if (isNPCMenu == 2) {
            updatePos_LEFT_RIGHT();
            if (GameCanvas.isPointerSelect && !GameCanvas.isPoint(this.menuX - 5, this.menuY - 5, this.w + 10, this.menuH + 10)) {
                this.timeShow = (byte) -1;
            }
        }
        super.updatekey();
    }

    public void updatePos_LEFT_RIGHT() {
        int i;
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH)) {
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
                    this.menuSelectedItem = ((cmtoX + GameCanvas.px) - this.menuX) / this.menuW;
                }
                int i3 = GameCanvas.px - this.pointerDownLastX[0];
                if (i3 != 0 && this.menuSelectedItem != -1) {
                    this.menuSelectedItem = -1;
                }
                for (int length = this.pointerDownLastX.length - 1; length > 0; length--) {
                    this.pointerDownLastX[length] = this.pointerDownLastX[length - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.px;
                cmtoX -= i3;
                if (cmtoX < 0) {
                    cmtoX = 0;
                }
                if (cmtoX > cmxLim) {
                    cmtoX = cmxLim;
                }
                if (cmx < 0 || cmx > cmxLim) {
                    i3 /= 2;
                }
                cmx -= i3;
            }
        }
        if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            int i4 = GameCanvas.px - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(i4) < 20 && CRes.abs(GameCanvas.px - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                cmtoX = cmx;
                this.pointerDownFirstX = -1000;
                this.menuSelectedItem = ((cmtoX + GameCanvas.px) - this.menuX) / this.menuW;
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem == -1 && !this.isDownWhenRunning) {
                if (cmx < 0) {
                    cmtoX = 0;
                } else if (cmx > cmxLim) {
                    cmtoX = cmxLim;
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
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            this.pointerIsDowning = false;
        }
    }

    private void update_Pos_UP_DOWN() {
        int i;
        if (GameCanvas.keyMyPressed[5]) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            doCloseMenu();
            perform((iCommand) this.menuItems.elementAt(this.menuSelectedItem));
        } else if (GameCanvas.keyMyPressed[12]) {
            GameCanvas.clearKeyHold();
            GameCanvas.clearKeyPressed();
            doCloseMenu();
            perform((iCommand) this.menuItems.elementAt(this.menuSelectedItem));
        }
        if (GameCanvas.isPointerSelect && isGiaotiep) {
            int i2 = this.SelectFocus;
            if (GameCanvas.isPoint((this.menuX + 13) - 14, (((this.menuTemY - this.hPlus) + GameCanvas.hCommand) + (this.menuW / 2)) - 14, 28, 28)) {
                this.SelectFocus--;
                GameCanvas.isPointerSelect = false;
            }
            if (GameCanvas.isPoint(((this.menuX + this.w) - 13) - 14, (((this.menuTemY - this.hPlus) + GameCanvas.hCommand) + (this.menuW / 2)) - 14, 28, 28)) {
                this.SelectFocus++;
                GameCanvas.isPointerSelect = false;
            }
            this.SelectFocus = resetSelect(this.SelectFocus, this.vecFocus.size() - 1, true);
            if (this.SelectFocus != i2) {
                objSelect = (MainObject) this.vecFocus.elementAt(this.SelectFocus);
            }
        }
        if (GameCanvas.isPointerDown) {
            if (!this.pointerIsDowning && GameCanvas.isPointer(this.menuX, this.menuY, this.w, this.menuH)) {
                for (int i3 = 0; i3 < this.pointerDownLastX.length; i3++) {
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
                    this.menuSelectedItem = ((cmtoX + GameCanvas.py) - this.menuY) / this.menuW;
                }
                int i4 = GameCanvas.py - this.pointerDownLastX[0];
                if (i4 != 0 && this.menuSelectedItem != -1) {
                    this.menuSelectedItem = -1;
                }
                for (int length = this.pointerDownLastX.length - 1; length > 0; length--) {
                    this.pointerDownLastX[length] = this.pointerDownLastX[length - 1];
                }
                this.pointerDownLastX[0] = GameCanvas.py;
                cmtoX -= i4;
                if (cmtoX < 0) {
                    cmtoX = 0;
                }
                if (cmtoX > cmxLim) {
                    cmtoX = cmxLim;
                }
                if (cmx < 0 || cmx > cmxLim) {
                    i4 /= 2;
                }
                cmx -= i4;
            }
        }
        if (GameCanvas.isPointerClick && this.pointerIsDowning) {
            int i5 = GameCanvas.py - this.pointerDownLastX[0];
            GameCanvas.isPointerClick = false;
            if (CRes.abs(i5) < 20 && CRes.abs(GameCanvas.py - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning && GameCanvas.isPointerSelect) {
                this.cmRun = 0;
                cmtoX = cmx;
                this.pointerDownFirstX = -1000;
                this.menuSelectedItem = ((cmtoX + GameCanvas.py) - this.menuY) / this.menuW;
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                this.waitToPerform = 1;
            } else if (this.menuSelectedItem == -1 && !this.isDownWhenRunning) {
                if (cmx < 0) {
                    cmtoX = 0;
                } else if (cmx > cmxLim) {
                    cmtoX = cmxLim;
                } else {
                    int i6 = (GameCanvas.py - this.pointerDownLastX[0]) + (this.pointerDownLastX[0] - this.pointerDownLastX[1]) + (this.pointerDownLastX[1] - this.pointerDownLastX[2]);
                    if (i6 > 10) {
                        i = 10;
                    } else if (i6 < -10) {
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
        if (GameCanvas.isPointerRelease && this.pointerIsDowning) {
            this.pointerIsDowning = false;
        }
    }

    public void moveCamera() {
        if (this.cmRun != 0 && !this.pointerIsDowning) {
            cmtoX += this.cmRun / 100;
            if (cmtoX < 0) {
                cmtoX = 0;
            } else if (cmtoX > cmxLim) {
                cmtoX = cmxLim;
            } else {
                cmx = cmtoX;
            }
            this.cmRun = (this.cmRun * 9) / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }
        if (cmx != cmtoX && !this.pointerIsDowning) {
            this.cmvx = (cmtoX - cmx) << 2;
            this.cmdx += this.cmvx;
            cmx += this.cmdx >> 4;
            this.cmdx &= 15;
        }
    }

    public void paintMenu(mGraphics mgraphics) {
        int itemMenuHelp;
        GameCanvas.resetTrans(mgraphics);
        if (!isLoadData) {
            return;
        }
        if (isNPCMenu == 1) {
            paint_NPC_MENU(mgraphics);
            return;
        }
        if (isNPCMenu == 0) {
            AvMain.paintDialog(mgraphics, this.menuX - 6, (this.menuTemY - this.hPlus) - 6, this.w + 12, this.menuH + this.hPlus + 12, 0);
            AvMain.paintRectNice(mgraphics, this.menuX, this.menuTemY, this.w, this.menuH, (byte) 2);
            mFont.tahoma_7b_black.drawString(mgraphics, this.nameMenu, this.menuX + (this.w / 2), (this.menuTemY - this.hPlus) + (GameCanvas.hCommand / 4), 2, true);
            if (isGiaotiep) {
                if (GameCanvas.lowGraphic) {
                    MainTabNew.paintRectLowGraphic(mgraphics, this.menuX, (this.menuY - this.hPlus) + GameCanvas.hCommand + 2, this.w, this.menuW - 4, 1);
                } else {
                    for (int i = 0; i <= this.w / 32; i++) {
                        if (i < this.w / 32) {
                            mgraphics.drawRegion(MainTabNew.imgTab[1], 0, 0, 32, this.menuW - 4, 0, this.menuX + (i * 32), (this.menuTemY - this.hPlus) + GameCanvas.hCommand + 2, 0, true);
                        } else {
                            mgraphics.drawRegion(MainTabNew.imgTab[1], 0, 0, 32, this.menuW - 4, 0, (this.menuX + this.w) - 32, (this.menuTemY - this.hPlus) + GameCanvas.hCommand + 2, 0, true);
                        }
                    }
                }
                if (this.vecFocus.size() > 1) {
                    mgraphics.drawRegion(MainTabNew.imgTab[7], 0, 16, 13, 8, 6, (this.menuX + 8) - (GameCanvas.gameTick % 3), (this.menuTemY - this.hPlus) + GameCanvas.hCommand + (this.menuW / 2), 3, true);
                    mgraphics.drawRegion(MainTabNew.imgTab[7], 0, 24, 13, 8, 6, ((this.menuX + this.w) - 8) + (GameCanvas.gameTick % 3), (this.menuTemY - this.hPlus) + GameCanvas.hCommand + (this.menuW / 2), 3, true);
                }
                AvMain.FontBorderColor(mgraphics, objSelect.name, this.menuX + (this.w / 2) + 13, (this.menuTemY - this.hPlus) + GameCanvas.hCommand + (this.menuW / 4) + 3, 2, 2);
                objSelect.paintAvatarFocus(mgraphics, ((this.menuX + (this.w / 2)) - ((mFont.tahoma_7b_black.getWidth(objSelect.name) / 2) + 1)) - 2, (this.menuTemY - this.hPlus) + GameCanvas.hCommand + (this.menuW / 4) + 8);
            }
            if (!isPaint) {
                return;
            }
            if (this.pos == -1) {
                mgraphics.setClip(this.menuX + 2, this.menuTemY + 2, this.w - 4, this.menuH - 4);
                mgraphics.saveCanvas();
                mgraphics.ClipRec(this.menuX + 2, this.menuTemY + 2, this.w - 4, this.menuH - 4);
                mgraphics.translate(-cmx, 0);
                for (int i2 = 0; i2 < this.menuItems.size(); i2++) {
                    if (i2 == this.menuSelectedItem) {
                        mgraphics.setColor(AvMain.color[3]);
                        mgraphics.fillRoundRect(this.menuX + (i2 * this.menuW) + 3, this.menuTemY + 3, this.menuW - 8, this.menuH - 6, 6, 6, true);
                    }
                    String[] strArr = ((iCommand) this.menuItems.elementAt(i2)).subCaption;
                    if (strArr == null) {
                        strArr = new String[]{((iCommand) this.menuItems.elementAt(i2)).caption};
                    }
                    int length = this.menuTemY + ((this.menuH - (strArr.length * 14)) / 2) + 1;
                    for (int i3 = 0; i3 < strArr.length; i3++) {
                        if (i2 == this.menuSelectedItem) {
                            mFont.tahoma_7b_white.drawString(mgraphics, strArr[i3], ((this.menuX + (i2 * this.menuW)) + (this.menuW / 2)) - 1, length + (i3 * 14), 2, true);
                        } else {
                            mFont.tahoma_7b_black.drawString(mgraphics, strArr[i3], ((this.menuX + (i2 * this.menuW)) + (this.menuW / 2)) - 1, length + (i3 * 14), 2, true);
                        }
                    }
                }
                mGraphics.resetTransAndroid(mgraphics);
                mgraphics.restoreCanvas();
                mgraphics.endClip();
                return;
            }
            mgraphics.setClip(this.menuX + 3, this.menuY + 3, this.w - 6, this.menuH - 6);
            mgraphics.saveCanvas();
            mgraphics.ClipRec(this.menuX + 3, this.menuY + 3, this.w - 6, this.menuH - 6);
            mgraphics.translate(0, -cmx);
            mgraphics.setColor(AvMain.color[4]);
            if (this.pos == 2 || this.pos == 4) {
                for (int i4 = 0; i4 < this.menuItems.size() - 1; i4++) {
                    mgraphics.setColor(AvMain.color[4]);
                    mgraphics.fillRect(this.menuX + 8, this.menuY + 3 + this.menuW + (i4 * this.menuW), this.w - 16, 1, true);
                }
            }
            int i5 = (cmx / this.menuW) - 1;
            if (i5 < 0) {
                i5 = 0;
            }
            int i6 = i5 + this.sizeMenu + 2;
            if (i6 > this.menuItems.size()) {
                i6 = this.menuItems.size();
                i5 = (i6 - this.sizeMenu) - 2;
                if (i5 < 0) {
                    i5 = 0;
                }
            }
            if (this.menuSelectedItem > -1) {
                paintSelect(mgraphics, this.menuX + 3, this.menuY + 3 + (this.menuSelectedItem * this.menuW), this.w - 6, this.menuW + 1);
            }
            for (int i7 = i5; i7 < i6; i7++) {
                iCommand icommand = (iCommand) this.menuItems.elementAt(i7);
                if (this.pos == 2) {
                    icommand.paintCaptionImageMenu(mgraphics, this.menuX + (this.w / 2), this.menuY + 6 + (this.menuW / 4) + (i7 * this.menuW), 2);
                } else if (this.pos == 0 || this.pos == 3) {
                    icommand.paintCaptionImageMenu(mgraphics, this.menuX + 6, this.menuY + 6 + (this.menuW / 4) + (i7 * this.menuW), 0);
                } else if (this.pos == 1) {
                    icommand.paintCaptionImageMenu(mgraphics, (this.menuX + this.w) - 6, this.menuY + 6 + (this.menuW / 4) + (i7 * this.menuW), 1);
                } else if (this.pos == 4) {
                    icommand.paintCaptionImageMenu(mgraphics, this.menuX + 10, this.menuY + 6 + (this.menuW / 4) + (i7 * this.menuW), 0);
                }
            }
            mGraphics.resetTransAndroid(mgraphics);
            mgraphics.restoreCanvas();
            mgraphics.endClip();
            GameCanvas.resetTrans(mgraphics);
            if (GameScreen.help.Step >= 0 && isHelp && (itemMenuHelp = GameScreen.help.itemMenuHelp()) >= 0) {
                MainHelp.paintPopup(mgraphics, (this.menuX - 6) - 70, ((this.menuY + 16) + (itemMenuHelp * this.menuW)) - GameCanvas.hText, 70, GameCanvas.hText, -1, T.helpMenu);
                mgraphics.drawRegion(AvMain.imgSelect, 0, 0, 12, 16, 4, this.menuX + 4 + ((GameCanvas.gameTick / 2) % 4), this.menuY + 14 + (itemMenuHelp * this.menuW), 10, true);
            }
            if (!this.isSv) {
                paintCmd(mgraphics);
                return;
            }
            return;
        }
        if (isNPCMenu == 2) {
            if (this.isPosPoint) {
                mgraphics.drawRegion(PaintInfoGameScreen.imgOther[4], 0, 0, 20, 16, 0, this.menuX - 20, (this.menuY + this.menuH) - 16, 0, true);
                mgraphics.drawRegion(PaintInfoGameScreen.imgOther[4], 20, 0, 20, 16, 0, this.menuX + this.hShow, (this.menuY + this.menuH) - 16, 0, true);
                if (this.hShow == this.menuH) {
                    mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 2, this.menuX, this.menuY, 0, true);
                    mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 0, this.menuX + 20, this.menuY, 0, true);
                } else {
                    int i8 = 0;
                    while (true) {
                        int i9 = i8;
                        if (i9 >= this.hShow) {
                            break;
                        }
                        if (i9 == 0) {
                            mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 2, this.menuX, this.menuY, 0, true);
                        } else if (i9 + this.menuH >= this.hShow) {
                            mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 0, this.menuX + i9, this.menuY, 0, true);
                        } else {
                            mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 20, 40, 40, 0, this.menuX + i9, this.menuY, 0, true);
                        }
                        i8 = i9 + this.menuH;
                    }
                }
                mgraphics.setClip(this.menuX + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
                mgraphics.saveCanvas();
                mgraphics.ClipRec(this.menuX + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
            } else {
                mgraphics.drawRegion(PaintInfoGameScreen.imgOther[4], 0, 0, 20, 16, 0, ((this.menuX + (this.w / 2)) - (this.hShow / 2)) - 20, (this.menuY + this.menuH) - 16, 0, true);
                mgraphics.drawRegion(PaintInfoGameScreen.imgOther[4], 20, 0, 20, 16, 0, this.menuX + (this.w / 2) + (this.hShow / 2), (this.menuY + this.menuH) - 16, 0, true);
                if (this.hShow == this.menuH) {
                    mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 2, (this.menuX + (this.w / 2)) - 20, this.menuY, 0, true);
                    mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 20, 0, 20, 40, 0, this.menuX + (this.w / 2), this.menuY, 0, true);
                } else {
                    int i10 = 0;
                    while (true) {
                        int i11 = i10;
                        if (i11 >= this.hShow) {
                            break;
                        }
                        if (i11 == 0) {
                            mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 2, ((this.menuX + (this.w / 2)) - (this.hShow / 2)) + i11, this.menuY, 0, true);
                        } else if (i11 + this.menuH >= this.hShow) {
                            mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 0, 40, 40, 0, ((this.menuX + (this.w / 2)) - (this.hShow / 2)) + i11, this.menuY, 0, true);
                        } else {
                            mgraphics.drawRegion(PaintInfoGameScreen.imgBackQuick, 0, 20, 40, 40, 0, ((this.menuX + (this.w / 2)) - (this.hShow / 2)) + i11, this.menuY, 0, true);
                        }
                        i10 = i11 + this.menuH;
                    }
                }
                mgraphics.saveCanvas();
                mgraphics.setClip(((this.menuX + (this.w / 2)) - (this.hShow / 2)) + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
                mgraphics.ClipRec(((this.menuX + (this.w / 2)) - (this.hShow / 2)) + 5, this.menuY - 20, this.hShow - 10, this.menuH + 20);
            }
            mgraphics.translate(-cmx, 0);
            for (int i12 = 0; i12 < this.menuItems.size(); i12++) {
                iCommand icommand2 = (iCommand) this.menuItems.elementAt(i12);
                icommand2.paint(mgraphics, icommand2.xCmd, icommand2.yCmd);
            }
            GameCanvas.resetTrans(mgraphics);
            mgraphics.translate(-cmx, 0);
            for (int i13 = 0; i13 < this.menuItems.size(); i13++) {
                iCommand icommand3 = (iCommand) this.menuItems.elementAt(i13);
                icommand3.paintClickCaption(mgraphics, icommand3.xCmd, icommand3.yCmd, 2);
            }
            mGraphics.resetTransAndroid(mgraphics);
            mgraphics.restoreCanvas();
            mgraphics.endClip();
        }
    }

    private void paint_NPC_MENU(mGraphics mgraphics) {
        int i = this.menuX + 6;
        int i2 = this.menuY + 8;
        AvMain.paintDialog(mgraphics, this.menuX, this.menuTemY, this.w, this.menuH, 12);
        MainObject mainObject = MainObject.get_Object(this.IdNpc, this.typeO);
        if (mainObject == null) {
            return;
        }
        mainObject.paintBigAvatar(mgraphics, (this.menuX + this.w) - 10, this.menuY);
        AvMain.Font3dWhite(mgraphics, mainObject.name, i + 10, i2, 0);
        if (this.runText != null) {
            this.runText.paintText(mgraphics, this.archorRunText);
        }
        GameCanvas.resetTrans(mgraphics);
        for (int i3 = 0; i3 < this.menuItems.size(); i3++) {
            iCommand icommand = (iCommand) this.menuItems.elementAt(i3);
            icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
        }
    }

    public void doCloseMenu() {
        this.isShowMenu = false;
        this.isSv = false;
        GameCanvas.isPointerSelect = false;
        GameCanvas.isPointerClick = false;
        GameCanvas.isPointerEnd = true;
    }

    public void updateMenu() {
        if (this.timeShow > 0) {
            this.timeShow = (byte) (this.timeShow + 1);
            if (this.hShow < this.w) {
                this.hShow += this.menuH;
                if (this.hShow >= this.w) {
                    this.hShow = this.w;
                    this.timeShow = (byte) 0;
                }
            }
        } else if (this.timeShow < 0) {
            this.timeShow = (byte) (this.timeShow - 1);
            if (this.hShow > 0) {
                this.hShow -= this.menuH;
                if (this.hShow <= 0) {
                    this.hShow = 0;
                    this.timeShow = (byte) 0;
                    doCloseMenu();
                }
            }
        }
        if (!isLoadData) {
            return;
        }
        moveCamera();
        if (isNPCMenu == 1) {
            if (this.runText != null) {
                this.runText.updateDlg();
            }
            for (int i = 0; i < this.menuItems.size(); i++) {
                ((iCommand) this.menuItems.elementAt(i)).updatePointer();
            }
        } else if (isNPCMenu == 2 && !GameCanvas.isPointerMove && this.timeShow == 0) {
            for (int i2 = 0; i2 < this.menuItems.size(); i2++) {
                ((iCommand) this.menuItems.elementAt(i2)).updatePointerShow(cmx, 0);
            }
        }
        if (this.menuTemY > this.menuY) {
            int i3 = (this.menuTemY - this.menuY) >> 1;
            if (i3 < 1) {
                i3 = 1;
            }
            this.menuTemY -= i3;
        }
        if (xc != 0) {
            xc >>= 1;
            if (xc < 0) {
                xc = 0;
            }
        }
        if (this.waitToPerform > 0) {
            this.waitToPerform--;
            if (this.waitToPerform == 0) {
                this.isShowMenu = false;
                if (this.menuSelectedItem >= 0) {
                    perform((iCommand) this.menuItems.elementAt(this.menuSelectedItem));
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                    GameCanvas.isPointerEnd = true;
                    GameCanvas.isPointerSelect = false;
                }
            }
        }
        super.updatePointer();
    }

    public void perform(iCommand icommand) {
        if (icommand != null) {
            if (icommand.action != null) {
                icommand.action.perform();
            } else if (icommand.Pointer != null) {
                icommand.Pointer.commandPointer(icommand.indexMenu, icommand.subIndex);
            } else {
                GameCanvas.currentScreen.commandMenu(icommand.indexMenu, icommand.subIndex);
            }
            GameCanvas.isPointerSelect = false;
            mSound.playSound(42, mSound.volumeSound);
        }
    }
}
