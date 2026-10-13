package dev.lymeowz.hso.training;

import java.io.*;
import java.util.Arrays;

/**
 * Offline packet interceptor for a toy protocol, NOT the HSO wire protocol.
 * Frame: unsigned byte type, unsigned short payload length, payload bytes.
 * Uses in-memory streams only; never connects to a live server.
 */
public final class PacketLab {
    static final int MAX_PAYLOAD = 1024;
    static final int MOVE = 1;

    static byte[] encode(int type, byte[] payload) throws IOException {
        if (type < 0 || type > 255 || payload.length > MAX_PAYLOAD)
            throw new IllegalArgumentException("invalid packet");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bytes);
        out.writeByte(type);
        out.writeShort(payload.length);
        out.write(payload);
        out.flush();
        return bytes.toByteArray();
    }

    static final class Packet {
        final int type;
        final byte[] payload;
        Packet(int type, byte[] payload) { this.type = type; this.payload = payload; }
    }

    static Packet decode(byte[] frame) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(frame));
        int type = in.readUnsignedByte();
        int length = in.readUnsignedShort();
        if (length > MAX_PAYLOAD || length != in.available())
            throw new IOException("invalid frame length");
        byte[] payload = new byte[length];
        in.readFully(payload);
        return new Packet(type, payload);
    }

    interface Interceptor {
        Packet inspect(Packet packet) throws IOException;
    }

    static final class MovementValidator implements Interceptor {
        public Packet inspect(Packet packet) throws IOException {
            if (packet.type == MOVE) {
                if (packet.payload.length != 4) throw new IOException("invalid movement payload");
                DataInputStream in = new DataInputStream(new ByteArrayInputStream(packet.payload));
                int x = in.readUnsignedShort();
                int y = in.readUnsignedShort();
                if (x > 100 || y > 100) throw new IOException("outside test map");
            }
            return packet;
        }
    }

    public static void main(String[] args) throws Exception {
        byte[] movement = {0, 20, 0, 30};
        byte[] frame = encode(MOVE, movement);
        Packet decoded = decode(frame);
        Packet checked = new MovementValidator().inspect(decoded);
        if (!Arrays.equals(movement, checked.payload)) throw new AssertionError();
        try {
            new MovementValidator().inspect(new Packet(MOVE, new byte[] {0, 101, 0, 30}));
            throw new AssertionError("invalid movement was accepted");
        } catch (IOException expected) {
            System.out.println("PASS: invalid movement rejected");
        }
        try {
            decode(new byte[] {1, 0, 10, 0});
            throw new AssertionError("truncated packet accepted");
        } catch (IOException expected) {
            System.out.println("PASS: truncated frame rejected");
        }
        System.out.println("PASS: offline packet decode/intercept/validate");
    }
}
