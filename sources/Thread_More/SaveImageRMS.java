package Thread_More;

import CLib.mVector;
import GameObjects.ObjectData;
import GameObjects.idSaveImage;
import Main.GameCanvas;
import Model.CRes;
import Model.MainImageDataPartChar;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/* loaded from: KnightOnline_v403.jar:Thread_More/SaveImageRMS.class */
public class SaveImageRMS implements Runnable {
    public static mVector vecSaveImage = new mVector("SaveImageRMS vecSaveImage");
    public static mVector vecSaveCharPart = new mVector("SaveImageRMS vecSaveCharPart");

    @Override // java.lang.Runnable
    public void run() {
        while (true) {
            boolean z = false;
            while (vecSaveCharPart.size() > 0) {
                z = true;
                MainImageDataPartChar mainImageDataPartChar = (MainImageDataPartChar) vecSaveCharPart.elementAt(0);
                try {
                    CRes.saveRMS(new StringBuffer().append("img_data_char_").append((int) mainImageDataPartChar.type).append("_").append((int) mainImageDataPartChar.id).toString(), mainImageDataPartChar.getSaveData().toByteArray());
                } catch (Exception e) {
                }
                vecSaveCharPart.removeElementAt(0);
            }
            if (z) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeShort(GameCanvas.IndexCharPar);
                    CRes.saveRMS("isIndexPart", byteArrayOutputStream.toByteArray());
                    dataOutputStream.close();
                } catch (Exception e2) {
                }
            }
            if (vecSaveImage.size() > 0) {
                try {
                    idSaveImage idsaveimage = (idSaveImage) vecSaveImage.elementAt(0);
                    ObjectData.setToRms(idsaveimage.mbytImage, idsaveimage.id);
                    vecSaveImage.removeElementAt(0);
                } catch (Exception e3) {
                }
            } else {
                return;
            }
        }
    }

    public void start() {
        new Thread(this).start();
    }
}
