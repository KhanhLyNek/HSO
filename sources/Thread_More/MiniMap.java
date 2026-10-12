package Thread_More;

import CLib.TemGraphics;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.MainQuest;
import GameObjects.NPCMini;
import GameObjects.ObjectParty;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.WorldMapScreen;
import Main.GameCanvas;
import Model.AvMain;
import Model.Point;
import Model.mCamera;

/* loaded from: KnightOnline_v403.jar:Thread_More/MiniMap.class */
public class MiniMap {
    public int maxX;
    public int maxY;
    public static mImage imgMiniMap;
    public static Point pHelp;
    public static int wMini = 1;
    public static mVector vecNPC_Map = new mVector("MiniMap vecNPC_Map");
    public mCamera miniCamera = new mCamera();
    int[] color = {-15731219, -661753, -4539997, -1173963};

    public void setSize() {
        if (GameCanvas.w > 300 && GameCanvas.h > 300) {
            wMini = 3;
        } else if (GameCanvas.w > 200 && GameCanvas.h > 200) {
            wMini = 2;
        }
        this.maxX = 25;
        this.maxY = 20;
        if (this.maxX > GameCanvas.loadmap.mapW) {
            this.maxX = GameCanvas.loadmap.mapW;
        }
        if (this.maxY > GameCanvas.loadmap.mapH) {
            this.maxY = GameCanvas.loadmap.mapH;
        }
        if (GameCanvas.isTouch) {
            int i = GameCanvas.w;
            int i2 = GameCanvas.minimap.maxX;
            MiniMap miniMap = GameCanvas.minimap;
            PaintInfoGameScreen.xFocus = (i - (i2 * wMini)) - 55;
        }
        this.miniCamera.setAll((GameCanvas.loadmap.mapW - this.maxX) * wMini, (GameCanvas.loadmap.mapH - this.maxY) * wMini, ((GameScreen.player.x / LoadMap.wTile) - (this.maxX / 2)) * wMini, ((GameScreen.player.y / LoadMap.wTile) - (this.maxY / 2)) * wMini);
        imgMiniMap = CreateMiniMap(wMini);
    }

    public void paint(mGraphics mgraphics) {
        if (GameScreen.infoGame.isMapThachdau() || GameScreen.infoGame.isMapchienthanh()) {
            return;
        }
        mgraphics.setColor(-9164782);
        mgraphics.fillRect(-3, -3, (this.maxX * wMini) + 6, (this.maxY * wMini) + 6, false);
        mgraphics.setColor(-470164);
        mgraphics.fillRect(-2, -2, (this.maxX * wMini) + 4, (this.maxY * wMini) + 4, false);
        mgraphics.setColor(-12052464);
        mgraphics.fillRect(-1, -1, (this.maxX * wMini) + 2, (this.maxY * wMini) + 2, false);
        mgraphics.setClip(0, 0, this.maxX * wMini, this.maxY * wMini);
        mgraphics.translate(-this.miniCamera.xCam, -this.miniCamera.yCam);
        if (imgMiniMap != null && imgMiniMap.image != null) {
            mgraphics.drawImage(imgMiniMap, 0, 0, 0, true);
        }
        if (LoadMap.typeMap == 2) {
            for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
                MainObject mainObject = (MainObject) GameScreen.Vecplayers.elementAt(i);
                if (mainObject.typeObject == 1) {
                    AvMain.fraObjMiniMap.drawFrame(11, (mainObject.x / LoadMap.wTile) * wMini, (mainObject.y / LoadMap.wTile) * wMini, 0, 3, mgraphics);
                }
            }
        }
        for (int i2 = 0; i2 < vecNPC_Map.size(); i2++) {
            NPCMini nPCMini = (NPCMini) vecNPC_Map.elementAt(i2);
            AvMain.fraObjMiniMap.drawFrame(nPCMini.type + 4, (nPCMini.x / LoadMap.wTile) * wMini, (nPCMini.y / LoadMap.wTile) * wMini, 0, 3, mgraphics);
        }
        AvMain.fraObjMiniMap.drawFrame(GameScreen.player.Action == 4 ? 9 : GameScreen.player.Direction, (GameScreen.player.x / LoadMap.wTile) * wMini, (GameScreen.player.y / LoadMap.wTile) * wMini, 0, 3, mgraphics);
        if (LoadMap.idTile == 9) {
            mgraphics.setColor(367554);
        } else {
            mgraphics.setColor(-16776961);
        }
        if (Player.party != null) {
            for (int i3 = 0; i3 < Player.party.vecPartys.size(); i3++) {
                ObjectParty objectParty = (ObjectParty) Player.party.vecPartys.elementAt(i3);
                if (objectParty.name.compareTo(GameScreen.player.name) != 0 && objectParty.idMap == GameCanvas.loadmap.idMap) {
                    AvMain.fraObjMiniMap.drawFrame(10, (objectParty.x / LoadMap.wTile) * wMini, (objectParty.y / LoadMap.wTile) * wMini, 0, 3, mgraphics);
                }
            }
        }
        if (pHelp != null && pHelp.frame == GameCanvas.loadmap.idMap) {
            int i4 = pHelp.x;
            int i5 = pHelp.y;
            if (i4 < this.miniCamera.xCam + 3) {
                i4 = this.miniCamera.xCam + 3;
            }
            if (i4 > (this.miniCamera.xCam + (this.maxX * wMini)) - 3) {
                i4 = (this.miniCamera.xCam + (this.maxX * wMini)) - 3;
            }
            if (i5 < this.miniCamera.yCam + 3) {
                i5 = this.miniCamera.yCam + 3;
            }
            if (i5 > (this.miniCamera.yCam + (this.maxY * wMini)) - 3) {
                i5 = (this.miniCamera.yCam + (this.maxY * wMini)) - 3;
            }
            WorldMapScreen.fraMyPos.drawFrame((GameCanvas.gameTick / 2) % WorldMapScreen.fraMyPos.nFrame, i4, i5, 0, 3, mgraphics);
        }
        mgraphics.endClip();
    }

    public static void addNPCMini(NPCMini nPCMini) {
        MainObject mainObject = MainObject.get_Object(nPCMini.ID, (byte) 2);
        if (mainObject != null) {
            SetTypeNPC(mainObject);
            nPCMini.type = mainObject.typeNPC;
            vecNPC_Map.addElement(nPCMini);
        }
    }

    public static void addMonMini(int i, byte b) {
        MainObject mainObject = MainObject.get_Object(i, b);
        if (mainObject != null) {
            NPCMini nPCMini = new NPCMini(i, mainObject.x, mainObject.y);
            nPCMini.type = 8;
            for (int i2 = 0; i2 < vecNPC_Map.size(); i2++) {
                NPCMini nPCMini2 = (NPCMini) vecNPC_Map.elementAt(i2);
                if (nPCMini2.ID == i && nPCMini2.type == nPCMini.type) {
                    nPCMini2.x = nPCMini.x;
                    nPCMini2.y = nPCMini.y;
                    return;
                }
            }
            vecNPC_Map.addElement(nPCMini);
        }
    }

    public static void SetTypeNPC(MainObject mainObject) {
        for (int i = 0; i < MainQuest.vecQuestList.size(); i++) {
            if (((MainQuest) MainQuest.vecQuestList.elementAt(i)).idNPC_From == mainObject.ID && (mainObject.typeNPC == 0 || mainObject.typeNPC == 2)) {
                mainObject.typeNPC = 1;
            }
        }
        for (int i2 = 0; i2 < MainQuest.vecQuestFinish.size(); i2++) {
            if (((MainQuest) MainQuest.vecQuestFinish.elementAt(i2)).idNPC_To == mainObject.ID) {
                mainObject.typeNPC = 3;
            }
        }
        for (int i3 = 0; i3 < MainQuest.vecQuestDoing_Main.size(); i3++) {
            if (((MainQuest) MainQuest.vecQuestDoing_Main.elementAt(i3)).idNPC_To == mainObject.ID && mainObject.typeNPC == 0) {
                mainObject.typeNPC = 2;
            }
        }
        for (int i4 = 0; i4 < MainQuest.vecQuestDoing_Sub.size(); i4++) {
            if (((MainQuest) MainQuest.vecQuestDoing_Sub.elementAt(i4)).idNPC_To == mainObject.ID && mainObject.typeNPC == 0) {
                mainObject.typeNPC = 2;
            }
        }
    }

    public void setPoint(int i, int i2, int i3) {
        if (pHelp == null) {
            pHelp = new Point();
        }
        pHelp.x = (i * wMini) - (wMini / 2);
        pHelp.y = (i2 * wMini) - (wMini / 2);
        pHelp.frame = i3;
    }

    public static mImage CreateMiniMap(int i) {
        try {
            mImage createImage = mImage.createImage(GameCanvas.loadmap.mapW * i, GameCanvas.loadmap.mapH * i);
            TemGraphics graphics = createImage.getGraphics();
            if (GameCanvas.mapBack != null) {
                graphics.g.setColor(GameCanvas.mapBack.colorMini);
                graphics.g.fillRect(0, 0, createImage.image.getWidth(), createImage.image.getHeight());
            }
            for (int i2 = 0; i2 < GameCanvas.loadmap.mapW; i2++) {
                for (int i3 = 0; i3 < GameCanvas.loadmap.mapH; i3++) {
                    int i4 = GameCanvas.loadmap.mapPaint[(i3 * GameCanvas.loadmap.mapW) + i2] - 1;
                    if (i4 > -1 && LoadMap.imgTileSmall.image != null) {
                        graphics.g.drawRegion(LoadMap.imgTileSmall.image, 0 * mGraphics.zoomLevel, i4 * 3 * mGraphics.zoomLevel, i * mGraphics.zoomLevel, i * mGraphics.zoomLevel, 0, i2 * i * mGraphics.zoomLevel, i3 * i * mGraphics.zoomLevel, 0);
                    }
                }
            }
            for (int i5 = 0; i5 < LoadMap.vecPointChange.size(); i5++) {
                Point point = (Point) LoadMap.vecPointChange.elementAt(i5);
                graphics.g.setColor(-10621185);
                switch (point.f) {
                    case 0:
                        graphics.g.fillRect((((point.x * i) / LoadMap.wTile) - i) * mGraphics.zoomLevel, (((point.y * i) / LoadMap.wTile) - (2 * i)) * mGraphics.zoomLevel, i * mGraphics.zoomLevel, i * 4 * mGraphics.zoomLevel);
                        break;
                    case 1:
                        graphics.g.fillRect(((point.x * i) / LoadMap.wTile) * mGraphics.zoomLevel, (((point.y * i) / LoadMap.wTile) - (2 * i)) * mGraphics.zoomLevel, i * mGraphics.zoomLevel, i * 4 * mGraphics.zoomLevel);
                        break;
                    case 2:
                        graphics.g.fillRect((((point.x * i) / LoadMap.wTile) - (2 * i)) * mGraphics.zoomLevel, ((point.y * i) / LoadMap.wTile) * mGraphics.zoomLevel, 4 * i * mGraphics.zoomLevel, i * mGraphics.zoomLevel);
                        break;
                    case 3:
                        graphics.g.fillRect((((point.x * i) / LoadMap.wTile) - (2 * i)) * mGraphics.zoomLevel, ((point.y * i) / LoadMap.wTile) * mGraphics.zoomLevel, 4 * i * mGraphics.zoomLevel, i * mGraphics.zoomLevel);
                        break;
                }
            }
            return createImage;
        } catch (Exception e) {
            mSystem.println(new StringBuffer().append("--CreateMiniMap: ").append(e.toString()).toString());
            return null;
        }
    }
}
