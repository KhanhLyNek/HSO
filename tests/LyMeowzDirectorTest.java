import GameScreen.LyMeowzAIDirector;
import GameScreen.LyMeowzRecovery;
import GameScreen.LyMeowzDiagnostics;

public final class LyMeowzDirectorTest {
    private static void check(boolean v, String message) {
        if (!v) throw new AssertionError(message);
    }

    public static void main(String[] args) {
        LyMeowzAIDirector director = new LyMeowzAIDirector();
        check(director.update(false,false,false,100) == LyMeowzAIDirector.NONE,
                "off does not launch");
        director.setEnabled(true);
        check(director.update(false,false,false,100) == LyMeowzAIDirector.START_TURN_IN,
                "first finished quest starts navigation");
        check(director.update(false,false,false,100) == LyMeowzAIDirector.NONE,
                "no repeated menu opening");
        check(director.update(false,true,false,100) == LyMeowzAIDirector.NONE,
                "navigating waits");
        check(director.update(true,false,false,100) == LyMeowzAIDirector.NONE,
                "dead never navigates");
        check(director.update(false,false,true,100) == LyMeowzAIDirector.NONE,
                "recovery owns movement");
        check(director.update(false,false,false,-1) == LyMeowzAIDirector.NONE,
                "idle resets completed quest marker");
        check(director.update(false,false,false,101) == LyMeowzAIDirector.START_TURN_IN,
                "new quest allowed");

        LyMeowzRecovery recover = new LyMeowzRecovery();
        recover.setEnabled(true);
        check(recover.update(true,2,3,125,240,1000) == LyMeowzRecovery.NONE,
                "remember live checkpoint");
        check(recover.update(false,2,3,125,240,1200) == LyMeowzRecovery.REQUEST_REVIVE,
                "single explicit revive");
        check(recover.update(false,2,3,125,240,1300) == LyMeowzRecovery.NONE,
                "no repeated revive packets");
        check(recover.update(true,7,0,30,30,2200) == LyMeowzRecovery.RETURN_TO_CHECKPOINT,
                "return after server revive");
        check(recover.targetMap() == 2 && recover.targetArea() == 3 &&
                recover.targetX() == 125 && recover.targetY() == 240,
                "preserve pre-death map/area/spot");
        recover.completed();
        check(recover.state() == LyMeowzRecovery.COMPLETE, "recovery complete");

        LyMeowzDiagnostics log = new LyMeowzDiagnostics();
        log.observe(20,50,100000);
        log.observe(20,60,160000);
        check(log.rate(160000).indexOf("tien do cap/gio") >= 0, "honest relative rate");
        log.questKill();
        log.mapHop(3,2);
        log.failure("path failure");
        check(log.questKills() == 1 && log.mapHops() == 1 && log.failures() == 1,
                "event counts");
        check(log.latestEvent().indexOf("path failure") >= 0, "error journal");
        System.out.println("LyMeowz Director / recovery / diagnostics tests: PASS");
    }
}
