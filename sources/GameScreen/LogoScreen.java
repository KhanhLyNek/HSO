package GameScreen;

import Build_Indo.IndoServer;
import Build_Usa.Usa_Server;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import GameObjects.MainRMS;
import Main.GameCanvas;
import Model.CRes;
import Model.T;
import Model.TE;
import com.silverknight.TemMidlet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/* loaded from: KnightOnline_v403.jar:GameScreen/LogoScreen.class */
public class LogoScreen extends MainScreen {
    int dem;
    public static String strListserver = "";
    public static boolean isLoadServer = false;
    public static String serverListSource = "DEFAULT";
    private static String savedServerHost;

    public LogoScreen() {
        try {
            byte[] loadRMS = CRes.loadRMS("listServer");
            byte[] loadRMS2 = CRes.loadRMS("isIndexServer");
            byte[] loadRMS3 = CRes.loadRMS("selectedServerHost");
            if (loadRMS3 != null) {
                savedServerHost = new String(loadRMS3, "UTF-8");
            }
            boolean z = loadRMS2 == null && savedServerHost == null;
            if (loadRMS != null) {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(loadRMS));
                if (applyServerList(dataInputStream.readUTF(), z)) {
                    serverListSource = "RMS";
                }
                dataInputStream.close();
            }
            strListserver = TemMidlet.connectHTTP(GameCanvas.linkIP);
            if (applyServerList(strListserver, z)) {
                serverListSource = "HTTP";
            } else if (loadRMS != null) {
                strListserver = new DataInputStream(new ByteArrayInputStream(loadRMS)).readUTF();
                if (applyServerList(strListserver, z)) {
                    serverListSource = "RMS";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        mgraphics.setColor(0);
        mgraphics.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
    }

    public void checkIPSV() {
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        this.dem++;
        if (this.dem > 60) {
            this.dem = 0;
            GameCanvas.login.Show();
        }
    }

    public static void getServerList(String str, boolean z) {
        applyServerList(str, z);
    }

    public static boolean refreshServerList() {
        strListserver = TemMidlet.connectHTTP(GameCanvas.linkIP);
        if (!applyServerList(strListserver, false)) {
            return false;
        }
        serverListSource = "HTTP";
        return true;
    }

    public static boolean applyServerList(String str, boolean z) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String str2 = savedServerHost;
        if (!z && GameCanvas.IndexServer >= 0 && GameCanvas.IndexServer < mSystem.listServer.length && str2 == null) {
            str2 = mSystem.listServer[GameCanvas.IndexServer][1];
        }
        String[] split = mFont.split(str.trim(), ",");
        int i = 0;
        for (String str3 : split) {
            if (mFont.split(str3.trim(), ":").length >= 4) {
                i++;
            }
        }
        if (i == 0) {
            return false;
        }
        String[][] strArr = new String[i][2];
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        int i2 = 0;
        for (String str4 : split) {
            try {
                String[] split2 = mFont.split(str4.trim(), ":");
                if (split2.length >= 4) {
                    strArr[i2][0] = split2[0].trim();
                    strArr[i2][1] = split2[1].trim();
                    iArr[i2] = Integer.parseInt(split2[2].trim());
                    iArr2[i2] = Byte.parseByte(split2[3].trim());
                    i2++;
                }
            } catch (Exception e) {
                return false;
            }
        }
        mSystem.listServer = strArr;
        GameCanvas.portServer = iArr;
        GameCanvas.langServer = iArr2;
        int i3 = -1;
        if (str2 != null) {
            int i4 = 0;
            while (true) {
                if (i4 >= strArr.length) {
                    break;
                }
                if (!str2.equals(strArr[i4][1])) {
                    i4++;
                } else {
                    i3 = i4;
                    break;
                }
            }
        }
        if (i3 < 0) {
            i3 = 0;
        }
        GameCanvas.IndexServer = (byte) i3;
        GameCanvas.isVNLanguage = GameCanvas.langServer[GameCanvas.IndexServer] == 0;
        saveListServer(str);
        setChangeLang();
        return true;
    }

    public static void saveSelectedServer() {
        try {
            savedServerHost = mSystem.listServer[GameCanvas.IndexServer][1];
            CRes.saveRMS("selectedServerHost", savedServerHost.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setChangeLang() {
        MainRMS.showAuto = "";
        LoginScreen.indexInfoLogin = (byte) 1;
        if (GameCanvas.w < 200 || GameCanvas.h < 200) {
            LoginScreen.indexInfoLogin = (byte) 2;
        } else if (GameCanvas.isTouch && GameCanvas.w < 380 && GameCanvas.w > 315 && GameCanvas.w < 380) {
            LoginScreen.indexInfoLogin = (byte) 2;
        }
        GameCanvas.start_Wait_Dialog(T.pleaseWait, null);
        if (!GameCanvas.isVNLanguage) {
            GameCanvas.t = new TE();
            if (mSystem.isIP_TrucTiep) {
                LoginScreen.logo = mImage.createImage("/interface/logoeip.png");
            } else {
                LoginScreen.logo = mImage.createImage("/interface/logoe.png");
            }
        } else {
            GameCanvas.t = new T();
            if (mSystem.isIP_TrucTiep) {
                LoginScreen.logo = mImage.createImage("/interface/logoip.png");
            } else {
                LoginScreen.logo = mImage.createImage("/interface/logo.png");
            }
        }
        IndoServer.setLinkIp();
        Usa_Server.setLinkIp();
        List_Server.gI().doSetCaption();
        GameCanvas.loadCaptionCmd();
        WorldMapScreen.namePos = null;
        GameCanvas.end_Dialog();
    }

    public static void saveListServer(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF(str);
            CRes.saveRMS("listServer", byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
        } catch (Exception e) {
        }
    }
}
