package com.silverknight;

import CLib.Session_ME;
import CLib.mSystem;
import Model.CRes;
import Thread_More.Load_Data_And_Img;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import net.Message;
import netcommand.global.GlobalMessageHandler;

/* loaded from: KnightOnline_v403.jar:com/silverknight/TemMidlet.class */
public class TemMidlet extends MIDlet {
    public static TemCanvas temCanvas;
    public static TemMidlet instance;
    public static final byte DIVICE = 0;
    public static final byte NONE = 0;
    public static final byte NOKIA_STORE = 1;
    public static final byte GOOGLE_STORE = 2;
    public static byte currentIAPStore = 0;
    public static boolean isBlockNOKIAStore = true;
    public static byte langServer = 0;
    public static String[] google_productIds = {"hs_gold_10", "hs_gold_30", "hs_gold_70", "hs_gold_180", "hs_gold_380", "hs_gold_800"};
    public static String[] google_listGems = {"24 Gems ($0.99)", "84 Gems ($2.99)", "150 Gems ($4.99)", "350 Gems ($9.99)", "1.000 Gems ($24.99)", "2.500 Gems ($49.99)"};
    public static String[] google_productIds_US = {"hs_gold_10", "hs_gold_30", "hs_gold_70", "hs_gold_180", "hs_gold_380", "hs_gold_800"};
    public static String[] google_listGems_US = {"24 Gems ($0.99)", "84 Gems ($2.99)", "150 Gems ($4.99)", "350 Gems ($9.99)", "1.000 Gems ($24.99)", "2.500 Gems ($49.99)"};
    public static String[] google_productIds_GB = {"hs_gold_10", "hs_gold_30", "hs_gold_70", "hs_gold_180", "hs_gold_380", "hs_gold_800"};
    public static String[] google_listGems_GB = {"24 Gems ($0.99)", "84 Gems ($2.99)", "150 Gems ($4.99)", "350 Gems ($9.99)", "1.000 Gems ($24.99)", "2.500 Gems ($49.99)"};
    public static final String[] productIds = new String[1];
    public static String[] listGems = {"24 Gems"};

    public TemMidlet() {
        temCanvas = new TemCanvas();
        instance = this;
        Session_ME.gI().setHandler(GlobalMessageHandler.gI());
        Display.getDisplay(this).setCurrent(temCanvas);
        temCanvas.start();
        Load_Data_And_Img.load.isInitGame = true;
    }

    protected void destroyApp(boolean z) throws MIDletStateChangeException {
        instance.notifyDestroyed();
    }

    protected void pauseApp() {
    }

    protected void startApp() throws MIDletStateChangeException {
    }

    public void destroy() {
        try {
            instance.destroyApp(true);
        } catch (MIDletStateChangeException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encoding(byte[] bArr) {
        if (bArr != null) {
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (bArr[i] ^ (-1));
            }
        }
        return bArr;
    }

    public static void saveRMS(String str, byte[] bArr) throws Exception {
        byte[] encoding = encoding(bArr);
        RecordStore openRecordStore = RecordStore.openRecordStore(str, true);
        if (openRecordStore.getNumRecords() > 0) {
            openRecordStore.setRecord(1, encoding, 0, encoding.length);
        } else {
            openRecordStore.addRecord(encoding, 0, encoding.length);
        }
        openRecordStore.closeRecordStore();
    }

    public static byte[] loadRMS(String str) {
        try {
            RecordStore openRecordStore = RecordStore.openRecordStore(str, false);
            byte[] record = openRecordStore.getRecord(1);
            openRecordStore.closeRecordStore();
            return encoding(record);
        } catch (Exception e) {
            return null;
        }
    }

    public static void openUrl(String str) {
        try {
            instance.platformRequest(str);
            instance.notifyDestroyed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delRMS() {
        try {
            String[] listRecordStores = RecordStore.listRecordStores();
            if (listRecordStores != null) {
                for (int i = 0; i < listRecordStores.length; i++) {
                    if (CRes.CheckDelRMS(listRecordStores[i])) {
                        RecordStore.deleteRecordStore(listRecordStores[i]);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static String connectHTTP(String str) {
        HttpConnection httpConnection = null;
        InputStream inputStream = null;
        try {
            httpConnection = Connector.open(str);
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type", "//text plain");
            httpConnection.setRequestProperty("Connection", "close");
            if (httpConnection.getResponseCode() != 200) {
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        return null;
                    }
                }
                if (httpConnection != null) {
                    httpConnection.close();
                }
                return null;
            }
            inputStream = httpConnection.openInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[256];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String str2 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            if (str2.length() > 0 && str2.charAt(0) == 65279) {
                str2 = str2.substring(1);
            }
            String str3 = str2;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
            if (httpConnection != null) {
                httpConnection.close();
            }
            return str3;
        } catch (IOException e3) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    return null;
                }
            }
            if (httpConnection != null) {
                httpConnection.close();
            }
            return null;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    throw th;
                }
            }
            if (httpConnection != null) {
                httpConnection.close();
            }
            throw th;
        }
    }

    public static void setProductIds() {
        productIds[0] = "1270462";
    }

    public static void makePurchase(String str) {
    }

    public static void submitPurchase() {
    }

    public static void handleMessage(Message message) {
    }

    public void call(String str) {
        try {
            platformRequest(new StringBuffer().append("tel:").append(str).toString());
        } catch (ConnectionNotFoundException e) {
            mSystem.println(new StringBuffer().append("loi roi ").append(e.toString()).toString());
        }
    }

    public static void handleAllMessage(Message message) {
    }
}
