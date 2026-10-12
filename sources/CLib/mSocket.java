package CLib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/* loaded from: KnightOnline_v403.jar:CLib/mSocket.class */
public class mSocket {
    SocketConnection s;

    public mSocket(String str, int i) {
        try {
            this.s = Connector.open(new StringBuffer().append("socket://").append(str).append(":").append(i).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (this.s != null) {
                this.s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setKeepAlive(boolean z) {
    }

    public DataOutputStream getOutputStream() {
        try {
            return this.s.openDataOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataInputStream getInputStream() {
        try {
            return this.s.openDataInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
