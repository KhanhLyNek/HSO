package Main;

import CLib.mVector;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameScreen.GameScreen;

/** Read-only target selection diagnostics. Never attacks, moves or sends messages. */
public final class AutoHuntDiagnostics {
    private AutoHuntDiagnostics() {}
    public static int selectedId = -1;
    public static int selectedDistance = -1;
    public static int nearbyCount = 0;

    public static void scan() {
        selectedId = -1;
        selectedDistance = -1;
        nearbyCount = 0;
        if (GameScreen.player == null) return;
        mVector objects = GameScreen.Vecplayers;
        if (objects == null) return;
        int px = GameScreen.player.x;
        int py = GameScreen.player.y;
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < objects.size(); i++) {
            Object value = objects.elementAt(i);
            if (!(value instanceof MainMonster)) continue;
            MainObject monster = (MainObject) value;
            if (monster.hp <= 0) continue;
            nearbyCount++;
            long dx = (long) monster.x - px;
            long dy = (long) monster.y - py;
            long d2 = dx * dx + dy * dy;
            if (d2 < best) {
                best = (int) Math.min(d2, Integer.MAX_VALUE);
                selectedId = i;
                selectedDistance = (int) Math.sqrt(d2);
            }
        }
    }
}
