package tools.training;

/** Pure Java policy tests; no emulator or game runtime needed. */
public final class TargetPolicyTest {
    private static void check(boolean value, String message) {
        if (!value) throw new AssertionError(message);
    }

    private static int score(int mode, int myLv, int lv, int playerDistance,
                             int campDistance, int hp, int maxHp, boolean normal, boolean dead) {
        return TargetPolicy.score(mode, myLv, lv, playerDistance, campDistance,
                180, hp, maxHp, normal, dead);
    }

    public static void main(String[] args) {
        check(score(TargetPolicy.SMART, 50, 50, 65, 60, 100, 100, true, false)
                > score(TargetPolicy.SMART, 50, 50, 135, 130, 100, 100, true, false),
                "nearby enemy first");
        check(score(TargetPolicy.SMART, 50, 50, 65, 60, 10, 100, true, false)
                > score(TargetPolicy.SMART, 50, 50, 65, 60, 100, 100, true, false),
                "weak enemy finish bonus");
        check(score(TargetPolicy.SAFE, 50, 54, 65, 60, 100, 100, true, false)
                == TargetPolicy.INVALID, "avoid stronger enemies");
        check(score(TargetPolicy.SMART, 50, 60, 65, 60, 100, 100, true, false)
                == TargetPolicy.INVALID, "smart mode danger cutoff");
        check(score(TargetPolicy.SMART, 50, 30, 65, 60, 100, 100, true, false)
                == TargetPolicy.INVALID, "no too-low-level farm");
        check(score(TargetPolicy.SMART, 50, 50, 65, 181, 100, 100, true, false)
                == TargetPolicy.INVALID, "camp boundary");
        check(score(TargetPolicy.SMART, 50, 50, 181, 65, 100, 100, true, false)
                == TargetPolicy.INVALID, "attack range boundary");
        check(score(TargetPolicy.SMART, 50, 50, 65, 60, 100, 100, false, false)
                == TargetPolicy.INVALID, "reject boss trader NPC or quest");
        check(score(TargetPolicy.SMART, 50, 50, 65, 60, 100, 100, true, true)
                == TargetPolicy.INVALID, "reject dead and captcha");
        check(score(TargetPolicy.SMART, 50, 50, 65, 60, 0, 100, true, false)
                == TargetPolicy.INVALID, "reject uninitialized monster info");
        check(score(TargetPolicy.CLASSIC, 50, 100, 65, 60, 100, 100, true, false)
                != TargetPolicy.INVALID, "classic mode nearest living normal");
        check(!TargetPolicy.shouldSwitch(1000, 200, 900L), "avoid target thrashing");
        check(TargetPolicy.shouldSwitch(1000, 200, 3000L), "switch when worth it");
        check(!TargetPolicy.shouldSwitch(1100, 1000, 3000L), "require margin");
        check(TargetPolicy.shouldSwitch(700, TargetPolicy.INVALID, 0), "replace dead target");
        System.out.println("TargetPolicyTest: ALL PASS");
    }
}
