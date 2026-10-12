package net;

/* loaded from: KnightOnline_v403.jar:net/ISession.class */
public interface ISession {
    boolean isConnected();

    void setHandler(IMessageHandler iMessageHandler);

    void connect(String str, String str2);

    void sendMessage(Message message);

    void close();
}
