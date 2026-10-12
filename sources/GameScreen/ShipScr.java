package GameScreen;

import CLib.mGraphics;
import CLib.mSystem;
import GameEffect.EffectAuto;
import GameEffect.EffectSkill;
import GameObjects.ObjectData;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Map.BackGround;
import Model.T;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/ShipScr.class */
public class ShipScr extends MainScreen {
    public static ShipScr instance;
    public EffectAuto eff;
    public short maxTime;
    public long last;
    public long curr;
    public long timeDone;
    public long timeStart;
    public boolean isSpeed;
    iCommand cmdFaster;
    String timeC;
    public int xShip;
    public int yShip;
    public short timeSpeed;
    public byte ideffect = -1;
    public byte typeMap = 1;
    public int speed = 1;
    long timeMove = 0;

    public static ShipScr gI() {
        if (instance == null) {
            instance = new ShipScr();
        }
        return instance;
    }

    public void setShipScr(short s) {
        this.isSpeed = false;
        this.left = null;
        this.right = null;
        this.cmdFaster = new iCommand(T.speedUp, -1, this);
        this.center = this.cmdFaster;
        this.timeStart = mSystem.currentTimeMillis();
        this.maxTime = s;
        this.timeDone = this.timeStart + (s * ObjectData.MONSTER);
        this.xShip = 0;
        this.yShip = (GameCanvas.h * 2) / 3;
        this.speed = 0;
        this.timeSpeed = (short) 0;
        this.ideffect = (byte) 50;
        this.eff = new EffectAuto(this.ideffect, this.xShip, this.yShip, 0, 0, 1, 0);
        this.timeMove = (s * ObjectData.MONSTER) / (GameCanvas.w + EffectSkill.EFF_BIG_SWORD_115_2);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        this.timeC = LoadMap.getTimeCountDown(this.timeStart, this.maxTime);
        BackGround.updateSky();
        updateShip();
        if (this.isSpeed || this.timeDone - mSystem.currentTimeMillis() <= 10000) {
            this.center = null;
        }
        GameScreen.player.dyWater = 0;
        GameScreen.player.Action = 0;
        GameScreen.player.x = this.xShip + this.speed + 30;
        GameScreen.player.y = this.yShip - 40;
        GameScreen.player.Direction = 3;
        GameScreen.player.updateActionPerson();
        super.update();
    }

    public void updateShip() {
        this.curr = mSystem.currentTimeMillis();
        if (this.isSpeed) {
            this.speed += 2;
            if (this.xShip + this.speed >= GameCanvas.w) {
                this.timeDone = mSystem.currentTimeMillis() + 20000;
                GlobalService.gI().useShip(this.typeMap);
            }
        } else {
            if (this.curr - this.last >= this.timeMove) {
                this.speed++;
                this.last = this.curr;
            }
            if (this.timeDone - mSystem.currentTimeMillis() <= 0) {
                this.timeDone = mSystem.currentTimeMillis() + 20000;
                GlobalService.gI().useShip(this.typeMap);
            }
        }
        if (this.eff != null) {
            this.eff.update();
            this.eff.x = this.speed;
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (GameCanvas.currentScreen != this) {
            return;
        }
        mgraphics.setColor(-13286542);
        mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, true);
        BackGround.paint_SeaAuto(mgraphics);
        paintShip(mgraphics);
        GameScreen.player.paintPlayer(mgraphics, -1);
        BackGround.paint_SeaCloud(mgraphics);
        super.paint(mgraphics);
    }

    public void paintShip(mGraphics mgraphics) {
        if (this.eff != null) {
            this.eff.paint(mgraphics);
        }
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        this.lastScreen = GameCanvas.game;
        super.Show();
        GameCanvas.clearAll();
        GameScreen.infoGame.resetShip();
    }

    @Override // Model.AvMain
    public void updatekey() {
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        super.updatePointer();
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                GlobalService.gI().useShip((byte) 2);
                return;
            default:
                return;
        }
    }
}
