/*
 * HSO v4.0.3 client-integrated mob-spawn navigation (Java ME compatible).
 *
 * Packet #4 is already emitted by Player.updatePlayer() through
 * GlobalService.player_move(x, y) and Session_ME. This class intentionally
 * does not create another socket or inject raw, unencrypted packets.
 *
 * First milestone: level-11 player -> a reachable Dog (mob 6) spawn cluster
 * on map 7 (Canh Dong Soi). There is no cross-map teleporting here.
 */
package Tool;

import CLib.Session_ME;
import CLib.mSystem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import Main.GameCanvas;
import Thread_More.LoadMap;

public final class AutoMobNavigator {
    public static final int DOG_MAP_ID = 7;
    public static final int DOG_MOB_ID = 6;
    public static final int DOG_LEVEL = 11;

    // Pixel/world positions from hso-server/hso.sql, grouped by spawn area.
    // Do not average all points: the three packs are far apart.
    private static final short[][] DOG_AREAS = {
        {828, 594, 750, 582, 702, 642},
        {240, 660, 240, 726, 150, 762, 108, 768, 312, 618},
        {288, 408, 312, 486, 204, 486, 186, 402, 186, 324, 126, 366}
    };

    private static final int CENTER_SEARCH_TILES = 4;
    private static final long STALL_TIMEOUT_MS = 2500L;
    private static final long RETRY_DELAY_MS = 400L;
    private static final int MAX_RETRIES = 3;
    private static final int ARRIVAL_TOLERANCE = 18;

    private static boolean enabled;
    private static boolean ownsRoute;
    private static int destinationX;
    private static int destinationY;
    private static int lastX;
    private static int lastY;
    private static int retries;
    private static long lastProgressAt;
    private static long lastPlanAt;
    private static String status = "OFF";

    private AutoMobNavigator() {
    }

    /** Toggle from the GameScreen menu; never moves a character in other maps. */
    public static void toggle() {
        if (enabled) {
            stop("Da tat tu di chuyen", true);
        } else {
            start();
        }
    }

    /** Start only when the session is in the level-11 dog map. */
    public static void start() {
        Player player = GameScreen.player;
        LoadMap map = GameCanvas.loadmap;
        if (player == null || map == null || player.Lv != DOG_LEVEL) {
            notifyPlayer("Auto Cho dien: chi ho tro nhan vat level 11");
            return;
        }
        if (map.idMap != DOG_MAP_ID) {
            notifyPlayer("Auto Cho dien: can den map 7 - Canh Dong Soi truoc");
            return;
        }
        if (!LoadMapScreen.isNextMap || Session_ME.gI() == null || !Session_ME.gI().isConnected()) {
            notifyPlayer("Auto Cho dien: chua san sang/ket noi server");
            return;
        }
        enabled = true;
        ownsRoute = false;
        retries = 0;
        lastPlanAt = 0L;
        lastProgressAt = mSystem.currentTimeMillis();
        lastX = player.x;
        lastY = player.y;
        status = "PLANNING";
        notifyPlayer("Auto Cho dien: dang tim duong toi bai quai");
        // Initial route is prepared on the next GameScreen update.
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static String getStatus() {
        return status;
    }

    public static int getDestinationX() {
        return destinationX;
    }

    public static int getDestinationY() {
        return destinationY;
    }

    /** Called from GameScreen.update(), not from a separate network thread. */
    public static void tick() {
        if (!enabled) {
            return;
        }
        Player player = GameScreen.player;
        LoadMap map = GameCanvas.loadmap;
        if (player == null || map == null || player.Lv != DOG_LEVEL ||
                map.idMap != DOG_MAP_ID || !LoadMapScreen.isNextMap) {
            stop("Auto Cho dien: da doi level hoac map", false);
            return;
        }
        if (Session_ME.gI() == null || !Session_ME.gI().isConnected()) {
            stop("Auto Cho dien: mat ket noi", false);
            return;
        }
        if (player.Action == 4 || player.hp <= 0) {
            stop("Auto Cho dien: nhan vat da guc", false);
            return;
        }
        if (Player.isLockKey) {
            // Wait for server-authoritative position correction to finish.
            return;
        }
        long now = mSystem.currentTimeMillis();
        if (ownsRoute) {
            if (abs(player.x - destinationX) <= ARRIVAL_TOLERANCE &&
                    abs(player.y - destinationY) <= ARRIVAL_TOLERANCE) {
                stop("Da den bai Cho dien", true);
                status = "ARRIVED";
                return;
            }
            if (player.x != lastX || player.y != lastY) {
                lastX = player.x;
                lastY = player.y;
                lastProgressAt = now;
            }
            if (player.posTransRoad != null && now - lastProgressAt < STALL_TIMEOUT_MS) {
                return;
            }
            if (now - lastPlanAt < RETRY_DELAY_MS) {
                return;
            }
            if (++retries > MAX_RETRIES) {
                stop("Auto Cho dien: khong the di den, da dung", true);
                status = "STUCK";
                return;
            }
            player.posTransRoad = null;
            ownsRoute = false;
        }
        if (now - lastPlanAt < RETRY_DELAY_MS) {
            return;
        }
        lastPlanAt = now;
        if (!planToNearestSpawn(player, map)) {
            stop("Auto Cho dien: khong tim thay o co duong di", true);
            status = "NO_PATH";
        }
    }

    private static boolean planToNearestSpawn(Player player, LoadMap map) {
        final int width = map.mapW;
        final int height = map.mapH;
        if (width <= 0 || height <= 0 || width > 127 || height > 127 ||
                map.mapType == null || map.mapType.length < width * height) {
            return false;
        }
        int startX = player.x / LoadMap.wTile;
        int startY = player.y / LoadMap.wTile;
        if (startX < 0 || startY < 0 || startX >= width || startY >= height) {
            return false;
        }
        final int size = width * height;
        final int[] parent = new int[size];
        final int[] distance = new int[size];
        final int[] queue = new int[size];
        int i;
        for (i = 0; i < size; ++i) {
            parent[i] = -1;
            distance[i] = -1;
        }
        int start = startY * width + startX;
        int head = 0;
        int tail = 1;
        queue[0] = start;
        distance[start] = 0;
        parent[start] = start;

        // BFS on the actual server-supplied collision map. No teleporting.
        while (head < tail) {
            int current = queue[head++];
            int x = current % width;
            int y = current / width;
            if (x > 0) tail = visit(current - 1, current, map.mapType, parent, distance, queue, tail);
            if (x + 1 < width) tail = visit(current + 1, current, map.mapType, parent, distance, queue, tail);
            if (y > 0) tail = visit(current - width, current, map.mapType, parent, distance, queue, tail);
            if (y + 1 < height) tail = visit(current + width, current, map.mapType, parent, distance, queue, tail);
        }

        int chosen = -1;
        int shortest = Integer.MAX_VALUE;
        for (int a = 0; a < DOG_AREAS.length; ++a) {
            short[] points = DOG_AREAS[a];
            int sx = 0;
            int sy = 0;
            for (i = 0; i < points.length; i += 2) {
                sx += points[i];
                sy += points[i + 1];
            }
            int centerX = sx / (points.length / 2) / LoadMap.wTile;
            int centerY = sy / (points.length / 2) / LoadMap.wTile;
            int bestInArea = -1;
            int centerError = Integer.MAX_VALUE;
            for (int dy = -CENTER_SEARCH_TILES; dy <= CENTER_SEARCH_TILES; ++dy) {
                for (int dx = -CENTER_SEARCH_TILES; dx <= CENTER_SEARCH_TILES; ++dx) {
                    int tx = centerX + dx;
                    int ty = centerY + dy;
                    if (tx < 0 || ty < 0 || tx >= width || ty >= height) continue;
                    int index = ty * width + tx;
                    if (distance[index] < 0 || map.mapType[index] == 1 || map.mapType[index] == -1) continue;
                    int error = abs(dx) + abs(dy);
                    if (error < centerError || error == centerError &&
                            (bestInArea < 0 || distance[index] < distance[bestInArea])) {
                        centerError = error;
                        bestInArea = index;
                    }
                }
            }
            if (bestInArea >= 0 && distance[bestInArea] < shortest) {
                chosen = bestInArea;
                shortest = distance[bestInArea];
            }
        }
        if (chosen < 0) {
            return false;
        }

        int targetTileX = chosen % width;
        int targetTileY = chosen / width;
        destinationX = targetTileX * LoadMap.wTile + LoadMap.wTile / 2;
        destinationY = targetTileY * LoadMap.wTile + LoadMap.wTile / 2;
        if (abs(player.x - destinationX) <= ARRIVAL_TOLERANCE &&
                abs(player.y - destinationY) <= ARRIVAL_TOLERANCE) {
            stop("Da o gan trung tam bai Cho dien", true);
            status = "ARRIVED";
            return true;
        }

        // Player.posTransRoad expects tile waypoints encoded as (x << 8) | y.
        // It will move locally with collision/speed checks and send packet #4
        // through the game's existing GlobalService/Session_ME connection.
        short[] path = new short[shortest + 1];
        int index = chosen;
        for (i = shortest; i >= 0; --i) {
            path[i] = (short)(((index % width) << 8) | (index / width));
            if (index == start) break;
            index = parent[index];
            if (index < 0) return false;
        }
        player.posTransRoad = path;
        player.countAutoMove = 0;
        player.toX = player.x;
        player.toY = player.y;
        player.xStopMove = destinationX;
        player.yStopMove = destinationY;
        lastX = player.x;
        lastY = player.y;
        lastProgressAt = mSystem.currentTimeMillis();
        ownsRoute = true;
        status = "MOVING";
        return true;
    }

    private static int visit(int next, int from, int[] tileTypes, int[] parent,
                             int[] distance, int[] queue, int tail) {
        if (distance[next] != -1 || tileTypes[next] == 1 || tileTypes[next] == -1) {
            return tail;
        }
        distance[next] = distance[from] + 1;
        parent[next] = from;
        queue[tail++] = next;
        return tail;
    }

    private static int abs(int n) {
        return n < 0 ? -n : n;
    }

    public static void stop(String message, boolean cancelRoute) {
        if (cancelRoute && ownsRoute && GameScreen.player != null) {
            Player p = GameScreen.player;
            p.posTransRoad = null;
            p.countAutoMove = 0;
            p.xStopMove = 0;
            p.yStopMove = 0;
            p.resetMove();
        }
        enabled = false;
        ownsRoute = false;
        status = "OFF";
        notifyPlayer(message);
    }

    private static void notifyPlayer(String message) {
        if (message != null && GameScreen.player != null) {
            GameCanvas.addInfoChar(message);
        }
    }
}
