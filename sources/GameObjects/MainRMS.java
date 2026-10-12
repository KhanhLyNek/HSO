package GameObjects;

import Build_Indo.IndoServer;
import GameScreen.GameScreen;
import GameScreen.PaintInfoGameScreen;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.TabSkillsNew;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Skill.Skill;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameObjects/MainRMS.class */
public class MainRMS {
    public static final byte SKILL = 0;
    public static final byte HELP = 1;
    public static final byte BEGIN_GAME = 2;
    public static final byte AUTO = 3;
    public static final byte TOUCH = 4;
    public static boolean isLoadBegin = false;
    public static boolean isLoadShowAuto = true;
    public static String showAuto = "";

    public static void RequietRMS() {
        GlobalService.gI().Save_RMS_Server((byte) 1, (byte) 0, null);
        setLoadRMS((byte) 0, null);
    }

    public static void setLoadRMS(byte b, byte[] bArr) {
        switch (b) {
            case 0:
                if (GameScreen.player != null) {
                    TabSkillsNew.loadSkill(bArr);
                    return;
                }
                return;
            case 1:
                GameScreen.help.LoadStep(bArr);
                GameCanvas.load.isLoadHelp = true;
                if (!GameCanvas.isVNLanguage && !IndoServer.isIndoSv) {
                    if (GameScreen.help.Step > 0 || GameScreen.help.Next > -5) {
                        GameScreen.isFinishHelp = true;
                        GameCanvas.end_Dialog();
                        Player player = GameScreen.player;
                        Player.isLockKey = false;
                        return;
                    }
                    return;
                }
                return;
            case 2:
                GameScreen.help.loadBeginGame(bArr);
                isLoadBegin = true;
                return;
            case 3:
                if (bArr != null) {
                    setLoadAuto(bArr);
                    return;
                }
                return;
            case 4:
                if (bArr != null) {
                    if (GameCanvas.isTouch) {
                        PaintInfoGameScreen.isLevelPoint = bArr[0] == 1;
                        PaintInfoGameScreen.setPosTouch();
                    }
                    if (bArr.length > 1) {
                        PaintInfoGameScreen.isShowInfoAuto = bArr[1] == 1;
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v61, types: [int] */
    /* JADX WARN: Type inference failed for: r11v4, types: [int] */
    /* JADX WARN: Type inference failed for: r11v5, types: [int] */
    public static void setLoadAuto(byte[] bArr) {
        showAuto = "";
        boolean z = false;
        Player.isAutoHPMP = bArr[0] == 1;
        MsgDialog.mHPMP[0] = bArr[1];
        MsgDialog.mHPMP[1] = bArr[2];
        if (Player.isAutoHPMP && PaintInfoGameScreen.isShowInfoAuto) {
            z = true;
            showAuto = new StringBuffer().append(showAuto).append(T.autoHP).append("\n  +").append(T.mAuto[0]).append(MsgDialog.mHPMP[0]).append("%\n  +").append(T.mAuto[1]).append(MsgDialog.mHPMP[1]).append("%").toString();
        }
        boolean z2 = bArr[3] == 1;
        if (z2) {
            Player.autoItem = new AutoGetItem(bArr[4], bArr[5], bArr[6]);
        }
        if (z2 && PaintInfoGameScreen.isShowInfoAuto) {
            if (z) {
                showAuto = new StringBuffer().append(showAuto).append("\n").toString();
            } else {
                z = true;
            }
            showAuto = new StringBuffer().append(showAuto).append(T.autoItem).append("\n  +").append(T.mAutoItem[0]).append(": ").append(T.mValueAutoItem[0][Player.autoItem.valueColorItem]).toString();
            showAuto = new StringBuffer().append(showAuto).append("\n  +").append(T.mAutoItem[1]).append(": ").append(T.mValueAutoItem[1][Player.autoItem.isGetMoney]).toString();
            showAuto = new StringBuffer().append(showAuto).append("\n  +").append(T.mAutoItem[2]).append(": ").append(T.mValueAutoItem[2][Player.autoItem.isGetPotion]).toString();
        }
        Player.isAutoBuff = bArr[7];
        byte b = MsgDialog.MaxSkillBuff;
        if (bArr.length - 7 >= b) {
            b -= CRes.abs(b - (bArr.length - 7)) + 1;
        }
        for (byte b2 = 0; b2 < b; b2++) {
            MsgDialog.Autobuff[b2][1] = bArr[8 + b2];
        }
        if (Player.isAutoBuff == 1 && PaintInfoGameScreen.isShowInfoAuto) {
            if (z) {
                showAuto = new StringBuffer().append(showAuto).append("\n").toString();
            }
            showAuto = new StringBuffer().append(showAuto).append(T.autoBuff).toString();
            for (byte b3 = 0; b3 < b; b3++) {
                if (MsgDialog.Autobuff[b3][1] == 1) {
                    showAuto = new StringBuffer().append(showAuto).append("\n  ").append(((Skill) TabSkillsNew.vecPaintSkill.elementAt(MsgDialog.Autobuff[b3][2])).name).toString();
                }
            }
        }
        if (showAuto.length() > 0 && isLoadShowAuto && GameCanvas.currentScreen == GameCanvas.game) {
            isLoadShowAuto = false;
            GameCanvas.start_Show_Dialog(new StringBuffer().append(T.autoFire).append("\n  ").append(showAuto).toString(), T.auto);
        }
    }

    public static void setSaveAuto() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(Player.isAutoHPMP ? 1 : 0);
            dataOutputStream.writeByte(MsgDialog.mHPMP[0]);
            dataOutputStream.writeByte(MsgDialog.mHPMP[1]);
            dataOutputStream.writeByte(Player.autoItem == null ? 0 : 1);
            if (Player.autoItem == null) {
                dataOutputStream.writeByte(0);
                dataOutputStream.writeByte(0);
                dataOutputStream.writeByte(0);
            } else {
                dataOutputStream.writeByte(Player.autoItem.valueColorItem);
                dataOutputStream.writeByte(Player.autoItem.isGetMoney);
                dataOutputStream.writeByte(Player.autoItem.isGetPotion);
            }
            dataOutputStream.writeByte(Player.isAutoBuff);
            for (int i = 0; i < MsgDialog.MaxSkillBuff; i++) {
                dataOutputStream.writeByte(MsgDialog.Autobuff[i][1]);
            }
            CRes.saveRMSName((byte) 3, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        } catch (Exception e) {
        }
    }

    public static void setSaveTouch() {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) (PaintInfoGameScreen.isLevelPoint ? 1 : 0);
        bArr[1] = (byte) (PaintInfoGameScreen.isShowInfoAuto ? 1 : 0);
        try {
            CRes.saveRMSName((byte) 4, bArr);
        } catch (Exception e) {
        }
    }
}
