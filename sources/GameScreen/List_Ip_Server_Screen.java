package GameScreen;

import Build_Indo.IndoServer;
import Build_Usa.Usa_Server;
import CLib.Session_ME;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSystem;
import CLib.mVector;
import InterfaceComponents.MainList;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Map.BackGround;
import Model.AvMain;
import Model.T;
import Model.TE;
import Model.TIndo;
import Model.TSpain;

/* loaded from: KnightOnline_v403.jar:GameScreen/List_Ip_Server_Screen.class */
public class List_Ip_Server_Screen extends MainScreen {
    int x;
    int y;
    int w;
    int h;
    int idSelect;
    int hItem;
    MainList list;
    String serverListStatus;
    mVector vecChoice = new mVector("List_Ip_Server_Screen vecchoice");

    public List_Ip_Server_Screen() {
        this.serverListStatus = new StringBuffer().append(LogoScreen.serverListSource).append(":").append(mSystem.listServer.length).append(LogoScreen.refreshServerList() ? "" : "!").toString();
        this.hItem = GameCanvas.hCommand;
        this.w = GameCanvas.w - 20;
        if (this.w > 180) {
            this.w = 180;
        }
        this.h = mSystem.listServer.length * this.hItem;
        int i = 0;
        if (this.h > (GameCanvas.h / 5) * 4) {
            i = this.h - ((GameCanvas.h / 5) * 4);
            this.h = (GameCanvas.h / 5) * 4;
        }
        this.x = GameCanvas.hw - (this.w / 2);
        this.y = (GameCanvas.hh - (this.h / 2)) + (this.hItem / 2);
        this.vecChoice.removeAllElements();
        for (int i2 = 0; i2 < mSystem.listServer.length; i2++) {
            this.vecChoice.addElement(new iCommand(mSystem.listServer[i2][0], 0, i2, this));
        }
        if (GameCanvas.isTouch) {
            this.idSelect = -1;
        }
        this.list = new MainList(this.x, this.y, this.w, this.h, this.hItem, mSystem.listServer.length, i, this.idSelect, this.vecChoice);
    }

    public void checkIPSV() {
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                if (Session_ME.gI().connected) {
                    Session_ME.gI().close();
                }
                GameCanvas.IndexServer = (byte) i2;
                LoginScreen.saveIndexServer();
                GameCanvas.login.Show();
                boolean z = GameCanvas.isVNLanguage;
                GameCanvas.isVNLanguage = GameCanvas.langServer[GameCanvas.IndexServer] == 0;
                if (GameCanvas.isVNLanguage != z) {
                    LogoScreen.setChangeLang();
                    GameCanvas.start_Ok_Dialog(T.TchangSv);
                }
                if (GameCanvas.isVNLanguage) {
                    LoginScreen.isPaintHotLine = true;
                } else {
                    LoginScreen.isPaintHotLine = false;
                }
                if (IndoServer.isIndoSv) {
                    LoginScreen.isPaintHotLine = false;
                    GameCanvas.t = new TIndo();
                    LogoScreen.setChangeLang();
                    if (mSystem.isIphone) {
                    }
                }
                if (Usa_Server.isUsa_server) {
                    LoginScreen.isPaintHotLine = false;
                    if (GameCanvas.IndexServer == 0) {
                        GameCanvas.t = new TE();
                    } else if (GameCanvas.IndexServer == 1) {
                        GameCanvas.t = new TSpain();
                    }
                    LogoScreen.setChangeLang();
                }
                GameCanvas.login.checkLoginAgain(GameCanvas.IndexServer);
                return;
            default:
                return;
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        BackGround.paint(mgraphics);
        if (PaintInfoGameScreen.paint18plush == 0) {
            if (!mSystem.isIphone) {
                mgraphics.drawImage(AvMain.img18Plus, 0, 0, 0, false);
            }
        } else if (PaintInfoGameScreen.paint18plush == 1) {
            PaintInfoGameScreen.paintinfo18plush(mgraphics);
        }
        BackGround.paintLight(mgraphics);
        paintFormList(mgraphics, this.x, this.y - this.hItem, this.w, this.h + this.hItem, new StringBuffer().append(T.listserver).append(" [").append(this.serverListStatus).append("]").toString());
        mgraphics.setClip(this.x, this.y, this.w, this.h);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(this.x, this.y, this.w, this.h);
        mgraphics.translate(0, -this.list.cmx);
        if (this.idSelect > -1) {
            paintFocus(mgraphics);
        }
        for (int i = 0; i < this.vecChoice.size(); i++) {
            mFont.tahoma_7b_white.drawString(mgraphics, ((iCommand) this.vecChoice.elementAt(i)).caption, this.x + (this.w / 2), ((this.y + (this.hItem / 2)) + (i * this.hItem)) - 6, 2, true);
            if (i < this.vecChoice.size() - 1) {
                mgraphics.setColor(AvMain.color[4]);
                mgraphics.fillRect(this.x + 8, (this.y + ((i + 1) * this.hItem)) - 1, this.w - 16, 1, true);
            }
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
    }

    public void paintFocus(mGraphics mgraphics) {
        mgraphics.setColor(-3689060);
        mgraphics.fillRect(this.x + 5, ((this.y + (this.idSelect * this.hItem)) + (this.hItem / 2)) - 11, this.w - 10, 22, false);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        this.list.updateMenu();
        this.idSelect = this.list.value;
        if (this.idSelect >= this.vecChoice.size()) {
            this.idSelect = -1;
            this.list.value = -1;
        }
        BackGround.updateSky();
    }
}
