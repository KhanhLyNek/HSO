package tools.battlefield;

/** Tests for battlefield map recognition and route/target policy only. */
public final class BattlefieldPolicyTest {
    private static void check(boolean ok, String label) {
        if (!ok) throw new AssertionError(label);
    }

    public static void main(String[] args) {
        int[] arenas = {54, 56, 58, 60, 61};
        for (int i = 0; i < arenas.length; i++) {
            check(BattlefieldPolicy.isBattlefieldMap(arenas[i]),
                    "recognize active battlefield map");
        }
        for (int i = 0; i < 4; i++) {
            check(BattlefieldPolicy.isBattlefieldWaitingMap(53 + i * 2),
                    "recognize staging map");
        }
        check(!BattlefieldPolicy.isBattlefieldMap(53),
                "do not enter battle mode in waiting room");
        check(!BattlefieldPolicy.isBattlefieldMap(1), "ordinary village excluded");
        check(BattlefieldPolicy.isValidMonster(true,false,false,false,false),
                "normal monster eligible");
        check(!BattlefieldPolicy.isValidMonster(true,false,true,false,false),
                "trade cargo excluded");
        check(!BattlefieldPolicy.isValidMonster(true,false,false,true,false),
                "verification event excluded");
        check(!BattlefieldPolicy.isValidMonster(true,false,false,false,true),
                "friendly battlefield object excluded");
        int near = BattlefieldPolicy.score(60,60,80,100,100,false,false);
        int distant = BattlefieldPolicy.score(60,60,220,100,100,false,false);
        check(near > distant, "prefer shorter valid route");
        check(BattlefieldPolicy.score(60,75,80,100,100,false,false)
                 == Integer.MIN_VALUE, "reject strong opponent");
        check(BattlefieldPolicy.nextArea(0,3)==1, "next area");
        check(BattlefieldPolicy.nextArea(2,3)==0, "wrap area index");
        check(BattlefieldPolicy.nextArea(0,1)==-1, "cannot change single area");
        check(BattlefieldPolicy.shouldStopAreaRetries(3),
                "stop on repeated server non-acknowledgment");
        System.out.println("BattlefieldPolicyTest: ALL PASS");
    }
}
