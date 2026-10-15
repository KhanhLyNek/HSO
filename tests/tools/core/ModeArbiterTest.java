package tools.core;

/** Standalone Java 8 regression tests for mode ownership and priorities. */
public final class ModeArbiterTest {
    private static void check(boolean value, String message) {
        if (!value) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        check(ModeArbiter.choose(false,false,false,false) == ModeArbiter.IDLE,
                "idle means no tool controls the character");
        check(ModeArbiter.choose(true,false,false,false) == ModeArbiter.TRAINING,
                "normal training");
        check(ModeArbiter.choose(false,true,false,false) == ModeArbiter.BOSS,
                "boss only");
        check(ModeArbiter.choose(false,false,false,true) == ModeArbiter.RECORDING,
                "recording only");
        check(ModeArbiter.choose(false,false,true,false) == ModeArbiter.TRADING,
                "trading only");
        check(ModeArbiter.choose(true,true,false,false) == ModeArbiter.BOSS,
                "boss takes priority over training");
        check(ModeArbiter.choose(true,true,true,false) == ModeArbiter.TRADING,
                "cargo safety takes priority over combat");
        check(ModeArbiter.choose(true,true,false,true) == ModeArbiter.RECORDING,
                "route recording takes priority over combat");
        check(ModeArbiter.mustStopTraining(ModeArbiter.RECORDING),
                "recording stops auto farm");
        check(ModeArbiter.mustStopTraining(ModeArbiter.BOSS),
                "boss stops auto farm");
        check(!ModeArbiter.mustStopTraining(ModeArbiter.TRAINING),
                "active training is retained");
        check(ModeArbiter.mustStopBoss(ModeArbiter.TRADING),
                "trade stops boss hunting");
        check(!ModeArbiter.mustStopBoss(ModeArbiter.BOSS),
                "boss does not cancel itself");
        System.out.println("ModeArbiterTest: ALL PASS");
    }
}
