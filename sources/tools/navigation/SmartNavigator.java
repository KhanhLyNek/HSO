package tools.navigation;

import CLib.mSystem;
import GameObjects.MainObject;
import GameObjects.Player;
import Main.GameCanvas;
import Thread_More.LoadMap;

/** One shared, paced movement owner for automatic farming and recovery. */
public final class SmartNavigator {
    public static final int FAILED = -1;
    public static final int MOVING = 0;
    public static final int ARRIVED = 1;
    public static final int BUSY = 2;
    private static int mapId = -1, targetX = -1, targetY = -1, failures;
    private static long lastPlan, lastProgress;
    private static int oldX, oldY;
    private static boolean owned;

    private SmartNavigator() {}

    public static void reset(Player p) {
        if (owned && p != null) p.posTransRoad = null;
        owned = false;
        targetX = -1;
        targetY = -1;
        mapId = -1;
        failures = 0;
        lastPlan = 0;
        lastProgress = 0;
    }

    public static String status() {
        return "Điểm " + targetX + "," + targetY + " - Lỗi " + failures;
    }

    /**
     * Recalculates only after the current segment finishes; handles full-map
     * targets far beyond the native viewport-limited pathfinder.
     */
    public static int approach(Player p, int x, int y, int tolerance) {
        if (p == null || GameCanvas.loadmap == null || p.Action == 4 ||
                p.isBinded || p.isStun || p.isDongBang) return BUSY;
        if (MainObject.getDistance(p.x, p.y, x, y) <= tolerance) {
            reset(p);
            return ARRIVED;
        }
        long now = mSystem.currentTimeMillis();
        int currentMap = GameCanvas.loadmap.idMap;
        if (targetX != x || targetY != y || mapId != currentMap) {
            reset(p);
            targetX = x;
            targetY = y;
            mapId = currentMap;
            oldX = p.x;
            oldY = p.y;
            lastProgress = now;
        }
        if (MainObject.getDistance(oldX, oldY, p.x, p.y) > 12) {
            oldX = p.x;
            oldY = p.y;
            lastProgress = now;
            failures = 0;
        }
        if (now - lastProgress > 9000L) {
            if (owned) p.posTransRoad = null;
            owned = false;
            failures++;
            lastProgress = now;
        }
        if (failures >= 4) { reset(p); return FAILED; }
        if (p.posTransRoad != null) {
            // Do not replace paths made by player touch or a different tool.
            return MOVING;
        }
        if (now - lastPlan < 950L) return MOVING;
        lastPlan = now;
        int tile = LoadMap.wTile;
        int destX = x / tile, destY = y / tile;
        int hereX = p.x / tile, hereY = p.y / tile;
        TilePathfinder.Route route = TilePathfinder.shared().route(
                GameCanvas.loadmap.mapType, GameCanvas.loadmap.mapW,
                GameCanvas.loadmap.mapH, hereX, hereY, destX, destY, 45,
                tolerance >= tile * 2 ? 2 : tolerance >= tile ? 1 : 0);
        if (route == null || route.steps == null || route.steps.length < 2) {
            failures++;
            return failures >= 4 ? FAILED : MOVING;
        }
        p.toX = p.x;
        p.toY = p.y;
        p.countAutoMove = 1;
        p.xStopMove = route.goalX * tile + tile / 2;
        p.yStopMove = route.goalY * tile + tile / 2;
        p.posTransRoad = route.steps;
        owned = true;
        return MOVING;
    }
}
