package Build_Usa;

import CLib.mSystem;
import Main.GameCanvas;
import Model.TE;

/* loaded from: KnightOnline_v403.jar:Build_Usa/Usa_Server.class */
public class Usa_Server {
    public static boolean isUsa_server = false;

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String[], java.lang.String[][]] */
    public static void setLinkIp() {
        if (isUsa_server) {
            GameCanvas.t = new TE();
            GameCanvas.linkIP = "http://knightageonline.com/srvip/us.php";
            mSystem.listServer = new String[]{new String[]{"Fire Dragon", "46.137.254.172"}, new String[]{"Sky Dragon", "54.254.156.202"}};
        }
    }
}
