package tools.dungeon;

public final class DefenseTargetPolicyTest {
    private static void check(boolean condition) {
        if (!condition) throw new RuntimeException("Defense target policy failed");
    }
    public static void main(String[] args) {
        int attacker = DefenseTargetPolicy.score(true,true,true,70,100,500,90,100,40);
        int distant = DefenseTargetPolicy.score(true,true,false,350,40,500,90,100,40);
        check(attacker > distant);
        check(DefenseTargetPolicy.score(false,true,true,10,10,500,10,100,100) == DefenseTargetPolicy.INVALID);
        check(DefenseTargetPolicy.score(true,true,true,10,900,500,10,100,100) == DefenseTargetPolicy.INVALID);
        check(DefenseTargetPolicy.emergency(30,100));
        check(!DefenseTargetPolicy.emergency(70,100));
        check(DefenseTargetPolicy.shouldSwitch(attacker,distant,true));
        System.out.println("DefenseTargetPolicyTest OK");
    }
}
