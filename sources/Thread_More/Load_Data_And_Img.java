package Thread_More;

import CLib.mSystem;
import InterfaceComponents.IAction;
import Model.CRes;

/* loaded from: KnightOnline_v403.jar:Thread_More/Load_Data_And_Img.class */
public class Load_Data_And_Img {
    public static LoadData load = new LoadData();

    /* loaded from: KnightOnline_v403.jar:Thread_More/Load_Data_And_Img$LoadData.class */
    public static class LoadData implements Runnable {
        IAction iaLoadMap;
        int iWpsPlash = -1;
        int jWpsPlash = -1;
        int iImgWeaPone = -1;
        int jImgWeaPone = -1;
        int indexWeapone = -1;
        int indexImgEffect = -1;
        int indexTreeImg = -1;
        int indexMap = -1;
        int indexArrow = -1;
        public boolean isInitGame = false;
        public byte[] byteMap = null;

        public void loadWpsPlash(int i, int i2) {
            if (this.iWpsPlash == -1 && this.jWpsPlash == -1) {
                this.iWpsPlash = i;
                this.jWpsPlash = i2;
            }
        }

        public void loadImgWeaPone(int i, int i2, int i3) {
            if (this.iImgWeaPone == -1 && this.jImgWeaPone == -1) {
                this.iImgWeaPone = i;
                this.jImgWeaPone = i2;
                this.indexWeapone = i3;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    mSystem.println("loi Cres 6");
                }
                if (this.iWpsPlash != -1 && this.jWpsPlash != -1) {
                    CRes.GetWPSplashInfo(0, this.iWpsPlash, this.jWpsPlash);
                    this.iWpsPlash = -1;
                    this.jWpsPlash = -1;
                }
                if (this.iImgWeaPone != -1 && this.jImgWeaPone != -1) {
                    CRes.getImgWeaPone(this.iImgWeaPone, this.jImgWeaPone, this.indexWeapone);
                    this.iImgWeaPone = -1;
                    this.jImgWeaPone = -1;
                }
            }
        }
    }
}
