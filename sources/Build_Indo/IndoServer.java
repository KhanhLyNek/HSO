package Build_Indo;

import CLib.mSystem;
import Main.GameCanvas;
import Model.TIndo;

/* loaded from: KnightOnline_v403.jar:Build_Indo/IndoServer.class */
public class IndoServer {
    public static boolean isIndoSv = false;

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String[], java.lang.String[][]] */
    public static void setLinkIp() {
        if (isIndoSv) {
            GameCanvas.t = new TIndo();
            GameCanvas.linkIP = "http://knightageonline.com/srvip/indo.php";
            mSystem.listServer = new String[]{new String[]{"Indo Naga", "54.151.177.35"}};
        }
    }
}
