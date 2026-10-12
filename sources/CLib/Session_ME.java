package CLib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import net.IMessageHandler;
import net.ISession;
import net.Message;

/* loaded from: KnightOnline_v403.jar:CLib/Session_ME.class */
public class Session_ME implements ISession {
    private DataOutputStream dos;
    public DataInputStream dis;
    public IMessageHandler messageHandler;
    private mSocket sc;
    public boolean connected;
    public boolean connecting;
    public Thread initThread;
    public Thread collectorThread;
    public Thread sendThread;
    public int sendByteCount;
    public int recvByteCount;
    boolean getKeyComplete;
    private byte curR;
    private byte curW;
    long timeConnected;
    public static boolean isCancel;
    protected static Session_ME instance = new Session_ME();
    public static int countRead = 0;
    private final Sender sender = new Sender(this);
    public byte[] key = null;
    public String strRecvByteCount = "";
    int countMsg = 0;

    public static Session_ME gI() {
        return instance;
    }

    @Override // net.ISession
    public boolean isConnected() {
        return this.connected;
    }

    @Override // net.ISession
    public void setHandler(IMessageHandler iMessageHandler) {
        this.messageHandler = iMessageHandler;
    }

    public void connect(String str, int i) {
        System.out.println(new StringBuffer().append("------------IP host:").append(str).append("--port:").append(i).toString());
        if (this.connected || this.connecting) {
            return;
        }
        this.sender.removeAllMessage();
        this.getKeyComplete = false;
        this.sc = null;
        this.initThread = new Thread(new NetworkInit(this, str, i));
        this.initThread.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: KnightOnline_v403.jar:CLib/Session_ME$NetworkInit.class */
    public class NetworkInit implements Runnable {
        private final String host;
        private final int port;
        private final Session_ME this$0;

        NetworkInit(Session_ME session_ME, String str, int i) {
            this.this$0 = session_ME;
            this.host = str;
            this.port = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Session_ME.isCancel = false;
            new Thread(new Runnable(this) { // from class: CLib.Session_ME.NetworkInit.1
                private final NetworkInit this$1;

                {
                    this.this$1 = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(20000L);
                    } catch (InterruptedException e) {
                    }
                    if (this.this$1.this$0.connecting) {
                        try {
                            this.this$1.this$0.sc.close();
                        } catch (Exception e2) {
                        }
                        Session_ME.isCancel = true;
                        this.this$1.this$0.connecting = false;
                        this.this$1.this$0.connected = false;
                        this.this$1.this$0.messageHandler.onConnectionFail();
                    }
                }
            }).start();
            this.this$0.connecting = true;
            Thread.currentThread().setPriority(1);
            this.this$0.connected = true;
            try {
                doConnect(this.host, this.port);
                this.this$0.messageHandler.onConnectOK();
            } catch (Exception e) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e2) {
                }
                if (!Session_ME.isCancel && this.this$0.messageHandler != null) {
                    this.this$0.close();
                    this.this$0.messageHandler.onConnectionFail();
                }
            }
        }

        public void doConnect(String str, int i) throws Exception {
            this.this$0.sc = new mSocket(str, i);
            this.this$0.sc.setKeepAlive(true);
            this.this$0.dos = this.this$0.sc.getOutputStream();
            this.this$0.dis = this.this$0.sc.getInputStream();
            new Thread(this.this$0.sender).start();
            this.this$0.collectorThread = new Thread(new MessageCollector(this.this$0));
            this.this$0.collectorThread.start();
            this.this$0.timeConnected = mSystem.currentTimeMillis();
            this.this$0.doSendMessage(new Message((byte) -40));
            this.this$0.connecting = false;
        }
    }

    @Override // net.ISession
    public void sendMessage(Message message) {
        this.sender.AddMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void doSendMessage(Message message) throws IOException {
        byte[] data = message.getData();
        try {
            if (this.getKeyComplete) {
                this.dos.writeByte(writeKey(message.command));
            } else {
                this.dos.writeByte(message.command);
            }
            if (data != null) {
                int length = data.length;
                if (this.getKeyComplete) {
                    this.dos.writeByte(writeKey((byte) (length >> 8)));
                    this.dos.writeByte(writeKey((byte) (length & 255)));
                } else {
                    this.dos.writeShort(length);
                }
                if (this.getKeyComplete) {
                    for (int i = 0; i < data.length; i++) {
                        data[i] = writeKey(data[i]);
                    }
                }
                this.dos.write(data);
                this.sendByteCount += 5 + data.length;
            } else {
                this.dos.writeShort(0);
                this.sendByteCount += 5;
            }
            this.dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte readKey(byte b) {
        byte[] bArr = this.key;
        byte b2 = this.curR;
        this.curR = (byte) (b2 + 1);
        byte b3 = (byte) ((bArr[b2] & 255) ^ (b & 255));
        if (this.curR >= this.key.length) {
            this.curR = (byte) (this.curR % this.key.length);
        }
        return b3;
    }

    private byte writeKey(byte b) {
        byte[] bArr = this.key;
        byte b2 = this.curW;
        this.curW = (byte) (b2 + 1);
        byte b3 = (byte) ((bArr[b2] & 255) ^ (b & 255));
        if (this.curW >= this.key.length) {
            this.curW = (byte) (this.curW % this.key.length);
        }
        return b3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: KnightOnline_v403.jar:CLib/Session_ME$Sender.class */
    public class Sender implements Runnable {
        public final Vector sendingMessage = new Vector();
        private final Session_ME this$0;

        public Sender(Session_ME session_ME) {
            this.this$0 = session_ME;
        }

        public void AddMessage(Message message) {
            this.sendingMessage.addElement(message);
        }

        public void removeAllMessage() {
            if (this.sendingMessage != null) {
                this.sendingMessage.removeAllElements();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            while (this.this$0.connected) {
                try {
                    if (this.this$0.getKeyComplete) {
                        while (this.sendingMessage.size() > 0) {
                            Message message = (Message) this.sendingMessage.elementAt(0);
                            this.sendingMessage.removeElementAt(0);
                            this.this$0.doSendMessage(message);
                        }
                    }
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: KnightOnline_v403.jar:CLib/Session_ME$MessageCollector.class */
    public class MessageCollector implements Runnable {
        private final Session_ME this$0;

        MessageCollector(Session_ME session_ME) {
            this.this$0 = session_ME;
        }

        @Override // java.lang.Runnable
        public void run() {
            Message readMessage;
            while (this.this$0.isConnected() && (readMessage = readMessage()) != null) {
                try {
                    try {
                        if (readMessage.command == -40) {
                            getKey(readMessage);
                        } else {
                            this.this$0.messageHandler.onMessage(readMessage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                }
            }
            if (this.this$0.connected) {
                if (this.this$0.messageHandler != null) {
                    if (mSystem.currentTimeMillis() - this.this$0.timeConnected > 500) {
                        this.this$0.messageHandler.onDisconnected();
                    } else {
                        this.this$0.messageHandler.onConnectionFail();
                    }
                }
                if (this.this$0.sc != null) {
                    this.this$0.cleanNetwork();
                }
            }
        }

        private void getKey(Message message) throws IOException {
            int readByte = message.reader().readByte();
            this.this$0.key = new byte[readByte];
            for (int i = 0; i < readByte; i++) {
                this.this$0.key[i] = message.reader().readByte();
            }
            for (int i2 = 0; i2 < this.this$0.key.length - 1; i2++) {
                byte[] bArr = this.this$0.key;
                int i3 = i2 + 1;
                bArr[i3] = (byte) (bArr[i3] ^ this.this$0.key[i2]);
            }
            this.this$0.getKeyComplete = true;
        }

        private Message readMessage() throws Exception {
            int readUnsignedShort;
            Session_ME.countRead++;
            byte readByte = this.this$0.dis.readByte();
            if (this.this$0.getKeyComplete) {
                readByte = this.this$0.readKey(readByte);
            }
            if (this.this$0.getKeyComplete) {
                if (readByte != -51 && readByte != -52 && readByte != -54 && readByte != 126) {
                    readUnsignedShort = ((this.this$0.readKey(this.this$0.dis.readByte()) & 255) << 8) | (this.this$0.readKey(this.this$0.dis.readByte()) & 255);
                } else {
                    if (readByte == 126) {
                        readByte = this.this$0.readKey(this.this$0.dis.readByte());
                    }
                    byte[] bArr = {this.this$0.dis.readByte(), this.this$0.dis.readByte(), this.this$0.dis.readByte(), this.this$0.dis.readByte()};
                    readUnsignedShort = (this.this$0.readKey(bArr[3]) & 255) | ((this.this$0.readKey(bArr[2]) & 255) << 8) | ((this.this$0.readKey(bArr[1]) & 255) << 16) | ((this.this$0.readKey(bArr[0]) & 255) << 24);
                }
            } else {
                readUnsignedShort = this.this$0.dis.readUnsignedShort();
            }
            byte[] bArr2 = new byte[readUnsignedShort];
            int i = 0;
            int i2 = 0;
            while (i != -1 && i2 < readUnsignedShort) {
                i = this.this$0.dis.read(bArr2, i2, readUnsignedShort - i2);
                if (i > 0) {
                    i2 += i;
                    this.this$0.recvByteCount += 5 + i2;
                }
            }
            if (this.this$0.getKeyComplete) {
                for (int i3 = 0; i3 < bArr2.length; i3++) {
                    bArr2[i3] = this.this$0.readKey(bArr2[i3]);
                }
            }
            return new Message(readByte, bArr2);
        }
    }

    @Override // net.ISession
    public void close() {
        cleanNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanNetwork() {
        this.key = null;
        this.curR = (byte) 0;
        this.curW = (byte) 0;
        try {
            this.connected = false;
            this.connecting = false;
            if (this.sc != null) {
                this.sc.close();
                this.sc = null;
            }
            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }
            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }
            this.sendThread = null;
            this.collectorThread = null;
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // net.ISession
    public void connect(String str, String str2) {
    }
}
