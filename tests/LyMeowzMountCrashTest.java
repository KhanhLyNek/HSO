import GameObjects.MountTemplate;
import GameObjects.MainObject;

/** Regression test for Android's null-array crash in MainObject.updateMount(). */
public final class LyMeowzMountCrashTest {
    private static void check(boolean valid, String description) {
        if (!valid) throw new AssertionError(description);
    }

    private static byte[][] table(int rows, int width) {
        byte[][] matrix = new byte[rows][];
        for (int i = 0; i < rows; i++) matrix[i] = new byte[width];
        return matrix;
    }

    public static void main(String[] args) {
        MountTemplate.dx = null;
        MountTemplate.dy = null;
        MountTemplate.DY_CHAR_STAND = null;
        MountTemplate.DY_CHAR_MOVE = null;
        MountTemplate.FRAME_VE_TRUOC = null;
        MountTemplate.FRAME_MOVE_LR = null;
        MountTemplate.FRAME_MOVE_DOWN = null;
        MountTemplate.FRAME_MOVE_UP = null;
        MountTemplate.speed = null;
        MountTemplate.Arr_Fly = null;

        check(!MountTemplate.hasAnimationData(0), "missing entire catalogue");
        check(!MountTemplate.canFly(0), "missing flying metadata");
        check(MountTemplate.safeSpeed(0) == 0, "missing speed must not crash");
        check(!MountTemplate.hasAnimationData(-1), "unmounted index");
        check(!MountTemplate.hasAnimationData(255), "out-of-range signed type");

        // This was the actual reported failure: remote player with a mount
        // while the server catalogue has not yet arrived.
        MainObject remotePlayer = new MainObject();
        remotePlayer.typeMount = (byte) 0;
        remotePlayer.frameMount = (byte) 6;
        remotePlayer.updateMount();
        check(remotePlayer.typeMount == 0,
                "missing animation must NOT permanently unequip mount");
        check(remotePlayer.frameMount == (byte) -1,
                "incomplete animation must be hidden until ready");
        check(!remotePlayer.isFramePaintMount_Truoc(),
                "missing front-frame data must be safe");
        check(remotePlayer.getVmount() == 0, "safe speed on missing catalogue");
        check(!remotePlayer.isMountFly(), "safe flying flag on missing catalogue");

        MountTemplate.dx = table(2,8);
        MountTemplate.dy = table(2,8);
        MountTemplate.DY_CHAR_STAND = table(2,4);
        MountTemplate.DY_CHAR_MOVE = table(2,12);
        MountTemplate.FRAME_VE_TRUOC = table(2,1);
        MountTemplate.FRAME_MOVE_LR = table(2,5);
        MountTemplate.FRAME_MOVE_DOWN = table(2,5);
        MountTemplate.FRAME_MOVE_UP = table(2,5);
        MountTemplate.speed = new byte[]{2,3};
        MountTemplate.Arr_Fly = new byte[]{0,1};
        check(MountTemplate.hasAnimationData(1), "complete animation rows accepted");
        check(MountTemplate.canFly(1), "flight metadata restored");
        check(MountTemplate.safeSpeed(1) == 3, "server speed restored");
        // Simulate data arriving AFTER a previous update skipped the mount.
        // It should recover automatically without resetting the mount ID.
        remotePlayer.typeMount = (byte) 1;
        remotePlayer.Action = 0;
        remotePlayer.Direction = 2;
        remotePlayer.frame = 1;
        remotePlayer.updateMount();
        check(remotePlayer.typeMount == 1 && remotePlayer.frameMount == 1,
                "mount animation resumes when server data becomes complete");
        MountTemplate.dx[1] = null;
        remotePlayer.updateMount();
        check(remotePlayer.frameMount == (byte) -1,
                "disappearing or partially loaded animation is safely skipped");
        check(!MountTemplate.hasAnimationData(1), "missing inner row safely rejected");
        MountTemplate.dx[1] = new byte[2];
        check(!MountTemplate.hasAnimationData(1), "short row safely rejected");
        check(!MountTemplate.hasAnimationData(2), "out-of-bounds mount index");
        System.out.println("LyMeowz mount null-array crash regression: PASS");
    }
}
