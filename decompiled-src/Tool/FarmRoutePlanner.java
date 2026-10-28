/* Map routing over verified normal gates and verified teleport NPC menu links.
 * Objective: minimum number of map transfers, with the closest current-map
 * entrance breaking ties. This is NOT proof of shortest travel time in pixels:
 * other maps' collision maps are not available until entered.
 */
package Tool;

import GameObjects.MainObject;
import GameScreen.GameScreen;
import Model.Point;
import Thread_More.LoadMap;

public final class FarmRoutePlanner {
    public static final int GATE = 0;
    public static final int STONE = 1;
    private static final int MAX_MAPS = 128;

    private FarmRoutePlanner() {}

    /** Minimum directed transfers from each map to destination. */
    public static int[] distancesTo(int destMap, boolean[] blockedStones) {
        int[] d = new int[MAX_MAPS];
        int[] q = new int[MAX_MAPS];
        for (int i = 0; i < d.length; i++) d[i] = -1;
        if (destMap < 0 || destMap >= MAX_MAPS) return d;
        int head = 0, tail = 1;
        d[destMap] = 0;
        q[0] = destMap;
        while (head < tail) {
            int cur = q[head++];
            for (int i = 0; i < FarmWorldData.EXITS.length; i++) {
                int[] a = FarmWorldData.EXITS[i];
                if (a[1] != cur || !valid(a[0]) || d[a[0]] != -1) continue;
                d[a[0]] = d[cur] + 1;
                q[tail++] = a[0];
            }
            for (int i = 0; i < FarmTeleportData.ROUTES.length; i++) {
                int[] a = FarmTeleportData.ROUTES[i];
                if (blockedStones != null && blockedStones[i]) continue;
                if (a[5] != cur || !valid(a[0]) || d[a[0]] != -1) continue;
                d[a[0]] = d[cur] + 1;
                q[tail++] = a[0];
            }
        }
        return d;
    }

    public static int[] distancesFrom(int from, boolean[] blockedStones) {
        int[] d = new int[MAX_MAPS];
        int[] q = new int[MAX_MAPS];
        for (int i = 0; i < d.length; i++) d[i] = -1;
        if (!valid(from)) return d;
        d[from] = 0;
        int head = 0, tail = 1;
        q[0] = from;
        while (head < tail) {
            int cur = q[head++];
            for (int i = 0; i < FarmWorldData.EXITS.length; i++) {
                int[] a = FarmWorldData.EXITS[i];
                if (a[0] != cur || !valid(a[1]) || d[a[1]] != -1) continue;
                d[a[1]] = d[cur] + 1;
                q[tail++] = a[1];
            }
            for (int i = 0; i < FarmTeleportData.ROUTES.length; i++) {
                int[] a = FarmTeleportData.ROUTES[i];
                if (blockedStones != null && blockedStones[i]) continue;
                if (a[0] != cur || !valid(a[5]) || d[a[5]] != -1) continue;
                d[a[5]] = d[cur] + 1;
                q[tail++] = a[5];
            }
        }
        return d;
    }

    /** Returns [kind, rowIndex] or null. */
    public static int[] next(int current, int target, int playerX, int playerY,
                             boolean[] blockedStones) {
        int[] d = distancesTo(target, blockedStones);
        if (!valid(current) || d[current] < 1) return null;
        int bestKind = -1, bestIndex = -1;
        int bestHops = Integer.MAX_VALUE, bestApproach = Integer.MAX_VALUE;
        for (int i = 0; i < FarmWorldData.EXITS.length; i++) {
            int[] a = FarmWorldData.EXITS[i];
            if (a[0] != current || !valid(a[1]) || d[a[1]] < 0 ||
                    !portalVisible(a[2], a[3])) continue;
            int hops = d[a[1]] + 1;
            int walk = abs(playerX - a[2]) + abs(playerY - a[3]);
            if (hops < bestHops || hops == bestHops && walk < bestApproach) {
                bestKind = GATE; bestIndex = i;
                bestHops = hops; bestApproach = walk;
            }
        }
        for (int i = 0; i < FarmTeleportData.ROUTES.length; i++) {
            int[] a = FarmTeleportData.ROUTES[i];
            if (a[0] != current || !valid(a[5]) || d[a[5]] < 0 ||
                    blockedStones != null && blockedStones[i] ||
                    !npcVisible(a[1], a[2], a[3])) continue;
            int hops = d[a[5]] + 1;
            int walk = abs(playerX - a[2]) + abs(playerY - a[3]);
            if (hops < bestHops || hops == bestHops && walk < bestApproach) {
                bestKind = STONE; bestIndex = i;
                bestHops = hops; bestApproach = walk;
            }
        }
        return bestKind == -1 ? null : new int[]{bestKind, bestIndex};
    }

    public static boolean npcVisible(int npcId, int x, int y) {
        if (GameScreen.Vecplayers == null) return false;
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject o = (MainObject)GameScreen.Vecplayers.elementAt(i);
            if (o != null && !o.isRemove && o.typeObject == 2 && o.ID == npcId &&
                    abs(o.x - x) <= 48 && abs(o.y - y) <= 48) return true;
        }
        return false;
    }

    public static boolean portalVisible(int x, int y) {
        if (LoadMap.vecPointChange == null) return false;
        for (int i = 0; i < LoadMap.vecPointChange.size(); i++) {
            Point p = (Point)LoadMap.vecPointChange.elementAt(i);
            if (p != null && abs(p.x - x) <= 48 && abs(p.y - y) <= 48) return true;
        }
        return false;
    }

    private static boolean valid(int n) {
        return n >= 0 && n < MAX_MAPS;
    }
    private static int abs(int n) { return n < 0 ? -n : n; }
}
