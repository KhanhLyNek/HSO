package GameScreen;

import Build_Indo.IndoServer;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSound;
import CLib.mSystem;
import GameEffect.EffectMonster;
import GameObjects.MainRMS;
import GameObjects.NPC;
import GameObjects.NPCMini;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import Thread_More.MiniMap;
import Thread_More.SaveImageRMS;
import net.Message;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/LoadMapScreen.class */
public class LoadMapScreen extends MainScreen {
    public boolean isLoadSkill = false;
    public boolean isTele = false;
    public boolean isLoadHelp = false;
    long time;
    long timeWait;
    public byte[] mItemMap;
    public byte[] mEffMap;
    public byte[] mNPCMap;
    public static boolean isNextMap = false;
    public static byte[] mMusic = {2, 0, 3, 2, 3, 2, 6, 2, 2, 2, 6, 5, 5, 5, 6, 4, 4, 3, 6, 8, 8, 8, 8, 8, 8, 4, 4, 4, 6, 3, 3, 3, 6, 1, 1, 1, 5, 5, 5, 5, 5, 5, 8, 8, 8, 8, 5, 6, 5, 5, 5, 2, 5, 0};

    @Override // GameScreen.MainScreen
    public void Show() {
        mSystem.my_Gc();
        super.Show();
        this.time = GameCanvas.timeNow;
        GameCanvas.saveImage.start();
        PaintInfoGameScreen.timeChange = 0;
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        mgraphics.setColor(0);
        mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        if (LoginScreen.logo != null) {
            mgraphics.drawImage(LoginScreen.logo, GameCanvas.hw, GameCanvas.hh - 16, 3, false);
            mgraphics.drawRegion(AvMain.imgLoadImg, 0, (GameCanvas.gameTick % 12) * 16, 16, 16, 0, GameCanvas.hw, (GameCanvas.hh + (mImage.getImageHeight(LoginScreen.logo.image) / 2)) - 9, 3, false);
            if (this.timeWait > 0) {
                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(this.timeWait).append("").toString(), GameCanvas.hw, (GameCanvas.hh + (mImage.getImageHeight(LoginScreen.logo.image) / 2)) - 15, 2, false);
            }
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if ((GameCanvas.timeNow - this.time) / 1000 > 180 && GameCanvas.currentDialog != null) {
            GameCanvas.start_Center_Dialog_Only(T.disconnect, new iCommand(T.exit, 0));
            if (GameScreen.player != null) {
                GameScreen.player.resetAction();
            }
        } else if (GameCanvas.currentDialog == null) {
        }
        if (isNextMap && this.isLoadSkill && MainRMS.isLoadBegin && (SaveImageRMS.vecSaveImage.size() <= 5 || (GameCanvas.timeNow - this.time) / 1000 > 45)) {
            if (GameScreen.help.setStep_Next(0, -5)) {
                GameCanvas.story = new StoryScreen();
                GameCanvas.story.setContent();
                GameCanvas.story.Show();
            } else {
                GameScreen.player.resetMove();
                GameScreen.player.posTransRoad = null;
                GameScreen.player.resetAction();
                GameCanvas.game.Show();
                GameCanvas.game.checkRemoveImage();
                if (MainRMS.showAuto.length() > 0 && MainRMS.isLoadShowAuto) {
                    MainRMS.isLoadShowAuto = false;
                    GameCanvas.start_Show_Dialog(new StringBuffer().append(T.autoFire).append("\n").append(MainRMS.showAuto).toString(), T.auto);
                }
                GameCanvas.hLoad = (GameCanvas.h / 4) * 3;
                PaintInfoGameScreen.setNameMap();
                if (this.isTele) {
                    GameScreen.addEffectKill(58, GameScreen.player.ID, GameScreen.player.typeObject, GameScreen.player.ID, GameScreen.player.typeObject, 0, GameScreen.player.hp);
                    GameScreen.player.isTanHinh = true;
                }
                if (GameCanvas.loadmap.idMap == 0 && GameScreen.help.setStep_Next(5, 8) && (GameCanvas.isVNLanguage || IndoServer.isIndoSv)) {
                    GameScreen.help.NextStep();
                    GameScreen.help.setNext();
                }
            }
            if (this.isLoadHelp) {
                GameScreen.help.setNext();
                this.isLoadHelp = false;
            }
            SelectCharScreen.isSelectOk = true;
            GlobalService.gI().Ok_Change_Map();
            GlobalService.gI().send_cmd_server((byte) 59);
            EffectMonster.listEffectMonster.removeAllElements();
            PlayMusicLang();
        }
        boolean z = false;
        if (GameCanvas.timenextLogin - mSystem.currentTimeMillis() > 0) {
            this.timeWait = (GameCanvas.timenextLogin - mSystem.currentTimeMillis()) / 1000;
        }
        if (GameCanvas.timenextLogin - mSystem.currentTimeMillis() <= 0 && GameCanvas.timenextLogin > 0) {
            z = true;
        }
        if (z) {
            this.timeWait = 0L;
            GameCanvas.timenextLogin = 0L;
            GameCanvas.login.checkLoginAgain(GameCanvas.IndexServer);
            SelectCharScreen.reSelect = true;
            SelectCharScreen.Canselect = true;
        }
        if (GameCanvas.countLogin > 0 && (mSystem.currentTimeMillis() - GameCanvas.countLogin) / 1000 > 15) {
            GameCanvas.countLogin = 0L;
            GameCanvas.start_Wait_Dialog(T.Logifail, new iCommand(T.close, 16));
        }
    }

    private void PlayMusicLang() {
        mSound.stopSoundAll();
        if (GameCanvas.loadmap.idMap >= mMusic.length - 1 || mMusic[GameCanvas.loadmap.idMap] < 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        } else {
            mSound.playMus(mMusic[GameCanvas.loadmap.idMap], mSound.volumeMusic, true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v2, types: [int] */
    public static void NPCBig(Message message) {
        try {
            byte readByte = message.reader().readByte();
            for (byte b = 0; b < readByte; b++) {
                String readUTF = message.reader().readUTF();
                String readUTF2 = message.reader().readUTF();
                byte readByte2 = message.reader().readByte();
                byte readByte3 = message.reader().readByte();
                short readShort = message.reader().readShort();
                short readShort2 = message.reader().readShort();
                NPC npc = new NPC(readUTF, readUTF2, readByte2, readByte3, readShort, readShort2, message.reader().readByte(), message.reader().readByte(), message.reader().readByte());
                npc.IdBigAvatar = message.reader().readByte() + 500;
                npc.infoObject = message.reader().readUTF();
                npc.isPerson = message.reader().readByte();
                npc.typeObject = (byte) 2;
                GameScreen.addPlayer(npc);
                MiniMap.addNPCMini(new NPCMini(readByte2, readShort, readShort2));
            }
        } catch (Exception e) {
        }
    }
}
