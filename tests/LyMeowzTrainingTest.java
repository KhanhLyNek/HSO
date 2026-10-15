import GameScreen.LyMeowzTrainingPlanner;
import GameScreen.LyMeowzTrainingSpotRegistry;

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
        LyMeowzTrainingSpotRegistry.clear();
        ok(LyMeowzTrainingSpotRegistry.best(30, -1, 10, 0) == null,
                "unobserved maps not guessed");
        LyMeowzTrainingSpotRegistry.observe(15,30,10,0,240,120,4);
        LyMeowzTrainingSpotRegistry.observe(15,29,11,2,360,150,0);
        int[] recommended=LyMeowzTrainingSpotRegistry.best(30,15,10,0);
        ok(recommended != null && recommended[0] == 11 && recommended[1] == 2,
                "quiet area is preferred over crowded local spawn");
        ok(LyMeowzTrainingSpotRegistry.best(30,999,10,0) == null,
                "quest category filter blocks unrelated mobs");
        LyMeowzTrainingSpotRegistry.observe(15,40,11,2,360,150,0);
        recommended=LyMeowzTrainingSpotRegistry.best(30,15,10,0);
        ok(recommended != null && recommended[0] == 10,
                "unsafe overlevel hunting area not selected");
        System.out.println("LyMeowz training safety tests: PASS");
    }
}
