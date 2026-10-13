package tools.navigation;

import GameObjects.MainObject;
import GameObjects.Player;
import Main.GameCanvas;
import Thread_More.LoadMap;

/**
 * Bounded patrol over reachable tiles when server has no suitable monsters in
 * the current field of view. No GPS, cheating or knowledge of unseen maps.
 */
public final class LocalSurvey {
    private static int index, mapId = -1, attempts;
    private static long lastSwitch;
    private LocalSurvey() {}

    public static void reset() {
        index = 0;
        attempts = 0;
        mapId = -1;
        lastSwitch = 0;
    }
    public static int attempts() { return attempts; }

    /** 5x3 grid, visited in deterministic order, centered in current map. */
    public static int seek(Player p) {
        if (GameCanvas.loadmap == null || p == null) return SmartNavigator.FAILED;
        if (mapId != GameCanvas.loadmap.idMap) {
            index = 0;
            attempts = 0;
            mapId = GameCanvas.loadmap.idMap;
            lastSwitch = 0;
        }
        int width = GameCanvas.loadmap.mapW, height = GameCanvas.loadmap.mapH;
        if (width < 8 || height < 8) return SmartNavigator.FAILED;
        int total = 15;
        for (int test = 0; test < total; test++) {
            int selected = (index + test) % total;
            int gx = selected % 5, gy = selected / 5;
            int x = (gx + 1) * (width - 2) / 6 + 1;
            int y = (gy + 1) * (height - 2) / 4 + 1;
            int key = y * width + x;
            if (x >= 128 || y >= 128 || GameCanvas.loadmap.mapType == null ||
                    key >= GameCanvas.loadmap.mapType.length ||
                    GameCanvas.loadmap.mapType[key] == 1 ||
                    GameCanvas.loadmap.mapType[key] == -1) continue;
            int px = x * LoadMap.wTile + LoadMap.wTile / 2;
            int py = y * LoadMap.wTile + LoadMap.wTile / 2;
            int r = SmartNavigator.approach(p, px, py, LoadMap.wTile * 2);
            if (r == SmartNavigator.ARRIVED || r == SmartNavigator.FAILED) {
                index = (selected + 1) % total;
                attempts++;
                continue;
            }
            index = selected;
            return SmartNavigator.MOVING;
        }
        return SmartNavigator.FAILED;
    }
}
