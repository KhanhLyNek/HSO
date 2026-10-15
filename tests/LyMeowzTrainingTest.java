import GameScreen.LyMeowzTrainingPlanner;

public final class LyMeowzTrainingTest {
    private static void ok(boolean v,String message) {
        if(!v) throw new AssertionError(message);
    }
    public static void main(String[] args) {
        ok(LyMeowzTrainingPlanner.healthPercent(1,100) == 1, "hp ratio");
        ok(LyMeowzTrainingPlanner.healthPercent(100,100) == 100, "full health");
        ok(LyMeowzTrainingPlanner.healthPercent(2000000000,2000000001) == 99,
                "long-safe health arithmetic");
        ok(LyMeowzTrainingPlanner.needsRecovery(20,100,100,100),
                "critical HP pauses target selection");
        ok(LyMeowzTrainingPlanner.needsRecovery(90,100,5,100),
                "critical MP pauses target selection");
        ok(!LyMeowzTrainingPlanner.needsRecovery(90,100,90,100),
                "healthy character resumes");
        ok(LyMeowzTrainingPlanner.score(80,30,31,100,100) == Integer.MAX_VALUE,
                "unsafe overlevel mob refused");
        ok(LyMeowzTrainingPlanner.score(30,30,30,100,100) <
                LyMeowzTrainingPlanner.score(70,30,27,100,100),
                "prefer nearby appropriate-level monster");
        ok(LyMeowzTrainingPlanner.crowdPenalty(240, 2) == 120,
                "bounded crowd penalty");
        System.out.println("LyMeowz training safety tests: PASS");
    }
}
