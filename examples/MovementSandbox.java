package dev.lymeowz.hso.training;

/**
 * Standalone movement sandbox. No hooks into a live game or network.
 * Compile: javac -d out examples/MovementSandbox.java
 * Run: java -cp out dev.lymeowz.hso.training.MovementSandbox
 */
public final class MovementSandbox {
    private static final int WIDTH = 10, HEIGHT = 6;
    private static final boolean[][] WALLS = new boolean[HEIGHT][WIDTH];

    static {
        for (int y = 1; y < 5; y++) WALLS[y][4] = true;
    }

    private static final class Actor {
        double x = 2, y = 2;
        double speed = 2; // tiles/second
        boolean noclip;

        void tick(double dx, double dy, double dt) {
            if (!Double.isFinite(dt) || dt < 0 || dt > 0.1) {
                throw new IllegalArgumentException("dt must be between 0 and 0.1 seconds");
            }
            double length = Math.hypot(dx, dy);
            if (length == 0) return;
            double nx = x + dx / length * speed * dt;
            double ny = y + dy / length * speed * dt;
            if (canEnter(nx, y)) x = nx;
            if (canEnter(x, ny)) y = ny;
        }

        boolean canEnter(double nx, double ny) {
            if (nx < 0 || ny < 0 || nx >= WIDTH || ny >= HEIGHT) return false;
            return noclip || !WALLS[(int) ny][(int) nx];
        }
    }

    public static void main(String[] args) {
        Actor actor = new Actor();
        for (int i = 0; i < 40; i++) actor.tick(1, 0, 0.05);
        System.out.printf("Collision enabled: (%.2f, %.2f)%n", actor.x, actor.y);
        actor.noclip = true;
        actor.speed = 4;
        for (int i = 0; i < 20; i++) actor.tick(1, 0, 0.05);
        System.out.printf("Sandbox noclip: (%.2f, %.2f)%n", actor.x, actor.y);
    }
}
