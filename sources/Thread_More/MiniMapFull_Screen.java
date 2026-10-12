package Thread_More;

import CLib.mGraphics;
import CLib.mImage;
import GameObjects.MainObject;
import GameObjects.NPCMini;
import GameObjects.ObjectParty;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.WorldMapScreen;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import Model.mCamera;

/* loaded from: KnightOnline_v403.jar:Thread_More/MiniMapFull_Screen.class */
public class MiniMapFull_Screen extends MainScreen {
    int maxX;
    int maxY;
    int LimitX;
    int LimitY;
    int speedShowX;
    int speedShowY;
    int wMini;
    iCommand cmdBack;
    public mImage imgtest;
    mCamera miniCamera = new mCamera();
    byte typeEnd = -1;
    boolean canShowMiniMap = true;

    public static MiniMapFull_Screen gI() {
        if (GameCanvas.fullMiniMap == null) {
            GameCanvas.fullMiniMap = new MiniMapFull_Screen();
        }
        return GameCanvas.fullMiniMap;
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        this.canShowMiniMap = true;
        this.lastScreen = GameCanvas.game;
        init();
        super.Show();
        if (this.maxX == this.LimitX && this.maxY == this.LimitY) {
            this.canShowMiniMap = false;
        }
    }

    public void init() {
        this.cmdBack = new iCommand(T.back, -1, this);
        this.maxX = GameCanvas.minimap.maxX;
        this.maxY = GameCanvas.minimap.maxY;
        this.LimitX = GameCanvas.loadmap.mapW;
        this.LimitY = GameCanvas.loadmap.mapH;
        if (this.LimitX > GameCanvas.w - GameCanvas.hCommand) {
            this.LimitX = GameCanvas.w - GameCanvas.hCommand;
        }
        if (this.LimitY > GameCanvas.h - GameCanvas.hCommand) {
            this.LimitY = GameCanvas.h - GameCanvas.hCommand;
        }
        this.speedShowX = (this.LimitX - this.maxX) / 5;
        this.speedShowY = (this.LimitY - this.maxY) / 5;
        if (GameCanvas.isTouch) {
            this.wMini = 3;
            this.imgtest = MiniMap.CreateMiniMap(this.wMini);
        } else {
            this.wMini = MiniMap.wMini;
            this.imgtest = MiniMap.imgMiniMap;
        }
        this.typeEnd = (byte) -1;
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                if (!this.canShowMiniMap) {
                    this.typeEnd = (byte) 2;
                    return;
                } else {
                    if (this.typeEnd == 0) {
                        this.typeEnd = (byte) 1;
                        return;
                    }
                    return;
                }
            default:
                return;
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        this.lastScreen.paint(mgraphics);
        if (GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap)) {
            GameCanvas.resetTrans(mgraphics);
            mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
        }
        if (GameCanvas.isTouch) {
            mgraphics.translate(GameCanvas.w - (this.maxX * this.wMini), 0);
        } else {
            mgraphics.translate((GameCanvas.w - (this.maxX * this.wMini)) - 3, (GameCanvas.h - 23) - (this.maxY * this.wMini));
        }
        mgraphics.setColor(-9164782);
        mgraphics.fillRect(-3, -3, (this.maxX * this.wMini) + 6, (this.maxY * this.wMini) + 6, false);
        mgraphics.setColor(-470164);
        mgraphics.fillRect(-2, -2, (this.maxX * this.wMini) + 4, (this.maxY * this.wMini) + 4, false);
        mgraphics.setColor(-12052464);
        mgraphics.fillRect(-1, -1, (this.maxX * this.wMini) + 2, (this.maxY * this.wMini) + 2, false);
        mgraphics.setClip(0, 0, this.maxX * this.wMini, this.maxY * this.wMini);
        if (this.imgtest != null) {
            mgraphics.drawImage(this.imgtest, 0, 0, 0, false);
        }
        if (LoadMap.typeMap == 2) {
            for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
                MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i);
                if (mainObject.typeObject == 1) {
                    AvMain.fraObjMiniMap.drawFrame(11, (mainObject.x / LoadMap.wTile) * this.wMini, (mainObject.y / LoadMap.wTile) * this.wMini, 0, 3, mgraphics);
                }
            }
        }
        for (int i2 = 0; i2 < MiniMap.vecNPC_Map.size(); i2++) {
            NPCMini nPCMini = (NPCMini) MiniMap.vecNPC_Map.elementAt(i2);
            AvMain.fraObjMiniMap.drawFrame(nPCMini.type + 4, (nPCMini.x / LoadMap.wTile) * this.wMini, (nPCMini.y / LoadMap.wTile) * this.wMini, 0, 3, mgraphics);
        }
        AvMain.fraObjMiniMap.drawFrame(GameScreen.player.Action == 4 ? 9 : GameScreen.player.Direction, (GameScreen.player.x / LoadMap.wTile) * this.wMini, (GameScreen.player.y / LoadMap.wTile) * this.wMini, 0, 3, mgraphics);
        mgraphics.setColor(-16776961);
        if (Player.party != null) {
            for (int i3 = 0; i3 < Player.party.vecPartys.size(); i3++) {
                ObjectParty objectParty = (ObjectParty) Player.party.vecPartys.elementAt(i3);
                if (objectParty.name.compareTo(GameScreen.player.name) != 0 && objectParty.idMap == GameCanvas.loadmap.idMap) {
                    AvMain.fraObjMiniMap.drawFrame(10, (objectParty.x / LoadMap.wTile) * this.wMini, (objectParty.y / LoadMap.wTile) * this.wMini, 0, 3, mgraphics);
                }
            }
        }
        if (MiniMap.pHelp != null && MiniMap.pHelp.frame == GameCanvas.loadmap.idMap) {
            int i4 = MiniMap.pHelp.x;
            int i5 = MiniMap.pHelp.y;
            if (i4 < this.miniCamera.xCam + 3) {
                i4 = this.miniCamera.xCam + 3;
            }
            if (i4 > (this.miniCamera.xCam + (this.maxX * this.wMini)) - 3) {
                i4 = (this.miniCamera.xCam + (this.maxX * this.wMini)) - 3;
            }
            if (i5 < this.miniCamera.yCam + 3) {
                i5 = this.miniCamera.yCam + 3;
            }
            if (i5 > (this.miniCamera.yCam + (this.maxY * this.wMini)) - 3) {
                i5 = (this.miniCamera.yCam + (this.maxY * this.wMini)) - 3;
            }
            WorldMapScreen.fraMyPos.drawFrame((GameCanvas.gameTick / 2) % WorldMapScreen.fraMyPos.nFrame, i4, i5, 0, 3, mgraphics);
        }
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
        if (GameScreen.infoGame.isMapArena(GameCanvas.loadmap.idMap)) {
            GameScreen.infoGame.paintSttArena(mgraphics, 10, 40, 20, 20);
        }
        GameScreen.infoGame.paintPos_minimap(mgraphics, GameCanvas.w, 0);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (this.typeEnd == 2) {
            this.imgtest = null;
            this.lastScreen.Show();
        } else if (this.typeEnd == 1) {
            if (this.maxX > GameCanvas.minimap.maxX) {
                this.maxX -= this.speedShowX * 2;
                if (this.maxX <= GameCanvas.minimap.maxX) {
                    this.maxX = GameCanvas.minimap.maxX;
                    this.typeEnd = (byte) 2;
                }
            }
            if (this.maxY > GameCanvas.minimap.maxY) {
                this.maxY -= this.speedShowY * 2;
                if (this.maxY <= GameCanvas.minimap.maxY) {
                    this.maxY = GameCanvas.minimap.maxY;
                    this.typeEnd = (byte) 2;
                }
            }
        } else {
            if (this.maxX < this.LimitX) {
                this.maxX += this.speedShowX;
                if (this.maxX >= this.LimitX) {
                    this.maxX = this.LimitX;
                    this.typeEnd = (byte) 0;
                }
            }
            if (this.maxY < this.LimitY) {
                this.maxY += this.speedShowY;
                if (this.maxY >= this.LimitY) {
                    this.maxY = this.LimitY;
                    this.typeEnd = (byte) 0;
                }
            }
        }
        this.lastScreen.update();
        for (int i = 0; i < GameCanvas.keyMyHold.length; i++) {
            if (GameCanvas.keyMyHold[i]) {
                this.cmdBack.perform();
                GameCanvas.clearKeyHold(i);
                return;
            }
        }
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (GameCanvas.isPointerDown) {
            this.cmdBack.perform();
        }
    }
}
