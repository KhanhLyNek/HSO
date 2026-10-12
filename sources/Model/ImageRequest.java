package Model;

import CLib.mVector;
import Main.GameCanvas;

/* loaded from: KnightOnline_v403.jar:Model/ImageRequest.class */
public class ImageRequest {
    public static mVector vecImageRequest = new mVector("ImageRequest vecImageRequest");
    public long timeRequest = GameCanvas.timeNow;
    public int idRequest;

    public ImageRequest(int i) {
        this.idRequest = i;
    }
}
