package tools.recovery;

public final class RecoverySnapshotTest {
    private static void check(boolean yes, String message) {
        if (!yes) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        RecoverySnapshot snapshot = new RecoverySnapshot();
        snapshot.recordAlive(41, 2, 432, 672, "Thung lũng", 1000L);
        snapshot.recordAlive(41, 2, 480, 672, "Thung lũng", 2000L);
        // Server had already moved the corpse to town before the death hook.
        snapshot.captureDeath(1, 0, 48, 48, "Làng", 3000L);
        check(snapshot.hasDeathLocation(), "death stored");
        check(snapshot.mapId() == 41 && snapshot.area() == 2,
                "use confirmed last alive map/area, not teleport destination");
        check(snapshot.x() == 480 && snapshot.y() == 672,
                "use latest observed alive coordinates");
        check("Thung lũng".equals(snapshot.mapName()), "preserve original map label");
        snapshot.captureDeath(1, 0, 0, 0, "Làng", 4000L);
        check(snapshot.mapId() == 41 && snapshot.x() == 480,
                "duplicate server death packet cannot overwrite location");

        snapshot.clear();
        snapshot.recordAlive(50, 1, 900, 900, "Hang", 1000L);
        snapshot.captureDeath(3, 0, 300, 300, "Thành", 40001L);
        check(snapshot.mapId() == 3 && snapshot.x() == 300,
                "too-old live position cannot become a wrong recovery destination");

        snapshot.clear();
        snapshot.recordAlive(-1, 0, 0, 0, "Invalid", 1000L);
        snapshot.captureDeath(4, 1, 72, 96, "Đường núi", 1500L);
        check(snapshot.mapId() == 4 && snapshot.area() == 1,
                "fall back to server death packet without an observed alive location");

        snapshot.clear();
        check(!snapshot.hasDeathLocation(), "recovered session clears snapshot");
        System.out.println("RecoverySnapshotTest: ALL PASS");
    }
}
