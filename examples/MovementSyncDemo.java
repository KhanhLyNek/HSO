package dev.lymeowz.hso.training;

/**
 * Offline client/server movement reconciliation demo.
 * No connection to HSO or any external game server.
 * Run: javac -d out examples/MovementSyncDemo.java
 *      java -cp out dev.lymeowz.hso.training.MovementSyncDemo
 */
public final class MovementSyncDemo {
    static final double DT = 0.05;
    static final double MAX_SPEED = 3.0;
    static final double WALL_X = 4.0;

    static final class State {
        double x;
        int sequence;
        State(double x, int sequence) { this.x = x; this.sequence = sequence; }
    }

    static final class Command {
        final int sequence;
        final double direction;
        Command(int sequence, double direction) {
            this.sequence = sequence;
            this.direction = direction;
        }
    }

    static double advance(double x, double direction) {
        double next = x + Math.max(-1, Math.min(1, direction)) * MAX_SPEED * DT;
        // The authoritative rules block movement through the demo wall.
        return Math.max(0, Math.min(WALL_X - 0.01, next));
    }

    static State serverStep(State server, Command input) {
        if (input.sequence <= server.sequence) return server;
        return new State(advance(server.x, input.direction), input.sequence);
    }

    public static void main(String[] args) {
        State server = new State(2, 0);
        State predicted = new State(2, 0);
        for (int sequence = 1; sequence <= 40; sequence++) {
            Command input = new Command(sequence, 1);
            predicted = new State(advance(predicted.x, input.direction), sequence);
            State authoritative = serverStep(server, input);
            server = authoritative;
            // Acknowledged inputs are removed; replay any unacknowledged inputs here.
            predicted = new State(authoritative.x, authoritative.sequence);
            if (Math.abs(predicted.x - server.x) > 1e-9)
                throw new AssertionError("client/server desynchronized");
        }
        if (server.x >= WALL_X) throw new AssertionError("wall collision failed");
        if (server.sequence != 40) throw new AssertionError("sequence mismatch");
        System.out.printf("PASS: client=%.2f server=%.2f ack=%d%n",
                predicted.x, server.x, server.sequence);
    }
}
