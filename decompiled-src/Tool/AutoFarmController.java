/*
 * Level-aware automatic farming for KnightOnline/HSO v4.0.3 (Java ME).
 * Targets and map exits: FarmWorldData, extracted from the server SQL snapshot.
 *
 * This class does not forge packets. The original Player.updatePlayer() sends
 * command 4 through GlobalService -> Session_ME with the active session key.
 * Server-issued map transitions are triggered by walking to a real portal.
 */
package Tool;

import CLib.Session_ME;
import CLib.mGraphics;
import CLib.mSystem;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.LoadMapScreen;
import Main.GameCanvas;
import Model.Point;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;

public final class AutoFarmController {
    private static final int TILE = 24;
    private static final int SEARCH_RADIUS_TILES = 4;
    private static final int MAX_ROUTE_TILES = 19;
    private static final int ARRIVAL_TOLERANCE = 19;
    private static final int RING_RADIUS = 110;
    private static final int MAX_LEVEL_BELOW_PLAYER = 2;
    private static final int MAP_LIMIT = 128;
    private static final int MAX_STALL_RETRIES = 4;
    private static final long STALL_TIMEOUT_MS = 3500L;
    private static final long PORTAL_TIMEOUT_MS = 12000L;
    private static final long STONE_TIMEOUT_MS = 8000L;
    private static final long STONE_MENU_DELAY_MS = 350L;
    private static final long ROUTE_REPLAN_DELAY_MS = 400L;

    private static boolean enabled;
    private static boolean ownsRoute;
    private static boolean farming;
    private static boolean waitingForPortal;
    private static int targetRow = -1;
    private static int selectedLevel = -1;
    private static int observedMap = -1;
    private static int routeGoalX;
    private static int routeGoalY;
    private static int lastX;
    private static int lastY;
    private static int retryCount;
    private static long lastProgressAt;
    private static long lastPlanAt;
    private static long portalWaitingSince;
    private static String status = "OFF";
    private static boolean waitingForRevive;
    private static int farmAnchorX = -1;
    private static int farmAnchorY = -1;
    private static int stoneStage;
    private static int stoneRouteIndex = -1;
    private static long stoneStageAt;
    private static final boolean[] disabledStoneRoutes =
            new boolean[FarmTeleportData.ROUTES.length];

    private AutoFarmController() {
    }

    public static void toggle() {
        if (enabled) {
            stop("Auto Farm: OFF");
        } else {
            start();
        }
    }

    public static void start() {
        Player p = GameScreen.player;
        if (p == null || GameCanvas.loadmap == null ||
                !LoadMapScreen.isNextMap ||
                Session_ME.gI() == null || !Session_ME.gI().isConnected()) {
            info("Auto Farm: can dang nhap va tai xong map");
            return;
        }
        if (p.Lv < 1) {
            info("Auto Farm: level nhan vat khong hop le");
            return;
        }
        enabled = true;
        ownsRoute = false;
        farming = false;
        waitingForPortal = false;
        waitingForRevive = false;
        stoneStage = 0;
        stoneRouteIndex = -1;
        targetRow = -1;
        farmAnchorX = -1;
        farmAnchorY = -1;
        selectedLevel = p.Lv;
        observedMap = GameCanvas.loadmap.idMap;
        for (int i = 0; i < disabledStoneRoutes.length; i++) disabledStoneRoutes[i] = false;
        int[] remembered = FarmMemory.load(p.ID);
        if (remembered != null && remembered[0] == p.Lv) {
            int[] reachable = FarmRoutePlanner.distancesFrom(observedMap, disabledStoneRoutes);
            for (int i = 0; i < FarmWorldData.MOBS.length; i++) {
                int[] row = FarmWorldData.MOBS[i];
                if (row[0] == remembered[1] && row[1] == remembered[2] &&
                        row[2] == remembered[3] &&
                        row[0] >= 0 && row[0] < reachable.length &&
                        reachable[row[0]] >= 0) {
                    targetRow = i;
                    farmAnchorX = -1;
                    farmAnchorY = -1;
                    // Only restore a genuine recorded static spawn center.
                    for (int c = 3; c + 1 < row.length; c += 2) {
                        if (row[c] == remembered[4] && row[c + 1] == remembered[5]) {
                            farmAnchorX = remembered[4];
                            farmAnchorY = remembered[5];
                            break;
                        }
                    }
                    break;
                }
            }
        }
        lastPlanAt = 0L;
        lastProgressAt = mSystem.currentTimeMillis();
        retryCount = 0;
        disableAttack();
        status = "SELECT_TARGET";
        info("Auto Farm ON: chon quai theo level " + p.Lv);
    }

    public static void stop(String reason) {
        if (ownsRoute) {
            clearMovement();
        }
        disableAttack();
        ownsRoute = false;
        enabled = false;
        farming = false;
        waitingForPortal = false;
        waitingForRevive = false;
        stoneStage = 0;
        stoneRouteIndex = -1;
        targetRow = -1;
        farmAnchorX = -1;
        farmAnchorY = -1;
        status = "OFF";
        info(reason);
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static String getStatus() {
        return status;
    }

    public static int getTargetMapId() {
        return targetRow < 0 ? -1 : FarmWorldData.MOBS[targetRow][0];
    }

    public static int getTargetMonsterId() {
        return targetRow < 0 ? -1 : FarmWorldData.MOBS[targetRow][1];
    }

    public static int getTargetMonsterLevel() {
        return targetRow < 0 ? -1 : FarmWorldData.MOBS[targetRow][2];
    }

    public static int getFarmAnchorX() { return farmAnchorX; }
    public static int getFarmAnchorY() { return farmAnchorY; }

    /** Draw world-space white spawn perimeter, not a moving monster marker. */
    public static void paintFarmRing(mGraphics g) {
        if (!enabled || farmAnchorX < 0 || farmAnchorY < 0 ||
                GameCanvas.loadmap == null ||
                GameCanvas.loadmap.idMap != getTargetMapId()) return;
        g.setColor(0xFFFFFF);
        g.drawArc(farmAnchorX - RING_RADIUS, farmAnchorY - RING_RADIUS,
                RING_RADIUS * 2, RING_RADIUS * 2, 0, 360, false);
        g.drawArc(farmAnchorX - RING_RADIUS - 2, farmAnchorY - RING_RADIUS - 2,
                RING_RADIUS * 2 + 4, RING_RADIUS * 2 + 4, 0, 360, false);
    }

    /** Only allow the selected monster template into native auto-attack. */
    public static boolean acceptsTarget(MainObject candidate) {
        if (!enabled || !farming) return true;
        return candidate instanceof MainMonster && targetRow >= 0 &&
                ((MainMonster)candidate).catalogyMonster == getTargetMonsterId() &&
                candidate.typeBoss != 2;
    }

    /** Run on the GameScreen update thread, never the socket receive thread. */
    public static void tick() {
        if (!enabled) return;
        Player p = GameScreen.player;
        LoadMap map = GameCanvas.loadmap;
        if (Session_ME.gI() == null || !Session_ME.gI().isConnected()) {
            stop("Auto Farm: mat ket noi");
            return;
        }
        // Keep the saved target during map loading or a temporary missing
        // player object in the death/respawn transition.
        if (p == null || map == null || !LoadMapScreen.isNextMap) return;
        if (p.Action == 4 || p.hp <= 0) {
            if (!waitingForRevive) {
                clearMovement();
                disableAttack();
                farming = false;
                waitingForPortal = false;
                stoneStage = 0;
                waitingForRevive = true;
                status = "WAIT_REVIVE";
                info("Auto Farm: da guc, giu bai farm va cho hoi sinh");
            }
            return;
        }
        if (waitingForRevive) {
            waitingForRevive = false;
            lastPlanAt = 0L;
            retryCount = 0;
            status = "RETURN_TO_FARM";
            info("Auto Farm: da hoi sinh, quay lai bai farm da luu");
        }
        if (p.Lv != selectedLevel) {
            clearMovement();
            disableAttack();
            targetRow = -1;
            farmAnchorX = -1;
            farmAnchorY = -1;
            FarmMemory.clear(p.ID);
            selectedLevel = p.Lv;
            retryCount = 0;
            waitingForPortal = false;
            farming = false;
            status = "LEVEL_CHANGED";
            info("Auto Farm: level moi " + p.Lv + ", tim bai quai moi");
        }

        int currentMap = map.idMap;
        if (currentMap != observedMap) {
            // A real server map-change packet has already been processed.
            clearMovement();
            disableAttack();
            observedMap = currentMap;
            waitingForPortal = false;
            stoneStage = 0;
            stoneRouteIndex = -1;
            farming = false;
            retryCount = 0;
            status = "MAP_CHANGED";
        }
        if (targetRow < 0 && !chooseTarget(p.Lv, currentMap)) {
            stop("Auto Farm: chua co quai phu hop/duong map hop le");
            return;
        }
        if (Player.isLockKey || p.isBinded) return;

        if (farming) {
            if (getTargetMapId() != currentMap) {
                farming = false;
                disableAttack();
            } else {
                if (Player.PointSucKhoe <= 0) {
                    stop("Auto Farm: khong du suc khoe");
                    return;
                }
                if (Player.isAutoFire != 1) {
                    p.dofire_();
                }
                status = "FARMING";
                return;
            }
        }

        long now = mSystem.currentTimeMillis();
        if (stoneStage != 0) {
            if (stoneRouteIndex < 0 ||
                    stoneRouteIndex >= FarmTeleportData.ROUTES.length ||
                    now - stoneStageAt > STONE_TIMEOUT_MS) {
                if (stoneRouteIndex >= 0 && stoneRouteIndex < disabledStoneRoutes.length)
                    disabledStoneRoutes[stoneRouteIndex] = true;
                stoneStage = 0;
                stoneRouteIndex = -1;
                lastPlanAt = 0L;
                status = "STONE_REPLAN";
                info("Auto Farm: da khong phan hoi, tim duong khac");
            } else {
                if (stoneStage == 1 && now - stoneStageAt >= STONE_MENU_DELAY_MS) {
                    int[] stone = FarmTeleportData.ROUTES[stoneRouteIndex];
                    if (!FarmRoutePlanner.npcVisible(stone[1], stone[2], stone[3])) {
                        disabledStoneRoutes[stoneRouteIndex] = true;
                        stoneStage = 0;
                        stoneRouteIndex = -1;
                    } else {
                        GlobalService.gI().Dynamic_Menu((short)stone[1], (byte)0,
                                (byte)stone[4]);
                        stoneStage = 2;
                        stoneStageAt = now;
                        status = "STONE_WARP_WAIT";
                    }
                }
                return;
            }
        }
        if (waitingForPortal) {
            if (now - portalWaitingSince > PORTAL_TIMEOUT_MS) {
                stop("Auto Farm: cong map khong phan hoi");
            }
            return;
        }

        if (ownsRoute) {
            if (p.x != lastX || p.y != lastY) {
                lastX = p.x;
                lastY = p.y;
                lastProgressAt = now;
                retryCount = 0;
            }
            if (reached(p, routeGoalX, routeGoalY)) {
                clearMovement();
            } else if (p.posTransRoad != null &&
                    now - lastProgressAt < STALL_TIMEOUT_MS) {
                return;
            } else if (now - lastPlanAt < ROUTE_REPLAN_DELAY_MS) {
                return;
            } else {
                if (++retryCount > MAX_STALL_RETRIES) {
                    stop("Auto Farm: di chuyen bi ket");
                    return;
                }
                clearMovement();
            }
        }
        if (now - lastPlanAt < ROUTE_REPLAN_DELAY_MS) return;
        lastPlanAt = now;

        int targetMap = getTargetMapId();
        if (currentMap != targetMap) {
            int[] next = FarmRoutePlanner.next(currentMap, targetMap, p.x, p.y,
                    disabledStoneRoutes);
            if (next == null) {
                stop("Auto Farm: khong co cong/da hop le tren map nay");
                return;
            }
            if (next[0] == FarmRoutePlanner.STONE) {
                if (!navigateToStone(p, map, next[1])) {
                    disabledStoneRoutes[next[1]] = true;
                    lastPlanAt = 0L;
                    info("Auto Farm: da khong tiep can duoc, tim duong thay the");
                }
            } else {
                int[] gate = FarmWorldData.EXITS[next[1]];
                if (!navigateToPortal(p, map, gate[1])) {
                    stop("Auto Farm: khong tim duoc duong toi cong map");
                }
            }
        } else {
            if (!navigateToSpawn(p, map)) {
                stop("Auto Farm: khong tim duoc bai quai co the den");
            }
        }
    }

    private static boolean chooseTarget(int level, int currentMap) {
        int[] hops = FarmRoutePlanner.distancesFrom(currentMap, disabledStoneRoutes);
        int best = -1;
        int bestGap = 99999;
        int bestHops = 99999;
        for (int i = 0; i < FarmWorldData.MOBS.length; ++i) {
            int[] row = FarmWorldData.MOBS[i];
            int gap = level - row[2];
            if (gap < 0 || gap > MAX_LEVEL_BELOW_PLAYER) continue;
            int mapId = row[0];
            if (mapId < 0 || mapId >= hops.length || hops[mapId] < 0) continue;
            if (gap < bestGap || gap == bestGap && hops[mapId] < bestHops) {
                best = i;
                bestGap = gap;
                bestHops = hops[mapId];
            }
        }
        if (best < 0) return false;
        targetRow = best;
        int[] target = FarmWorldData.MOBS[best];
        status = "TARGET_SELECTED";
        info("Auto Farm: " + FarmWorldData.MOB_NAMES[best] + " Lv" + target[2] +
                " - " + FarmWorldData.mapName(target[0]) + " (map " + target[0] + ")");
        return true;
    }

    /** Move toward a source-verified stone, then use the real NPC menu. */
    private static boolean navigateToStone(Player p, LoadMap map, int rowIndex) {
        if (rowIndex < 0 || rowIndex >= FarmTeleportData.ROUTES.length) return false;
        int[] stone = FarmTeleportData.ROUTES[rowIndex];
        if (stone[0] != map.idMap ||
                !FarmRoutePlanner.npcVisible(stone[1], stone[2], stone[3])) return false;
        stoneRouteIndex = rowIndex;
        status = "TO_STONE";
        if (abs(p.x - stone[2]) <= 28 && abs(p.y - stone[3]) <= 28) {
            return beginStoneRequest();
        }
        return makeRoute(p, map, new int[][]{{stone[2], stone[3]}}, 2);
    }

    private static boolean beginStoneRequest() {
        if (stoneRouteIndex < 0 || stoneRouteIndex >= FarmTeleportData.ROUTES.length)
            return false;
        int[] stone = FarmTeleportData.ROUTES[stoneRouteIndex];
        Player p = GameScreen.player;
        if (p == null || abs(p.x - stone[2]) > 40 || abs(p.y - stone[3]) > 40 ||
                !FarmRoutePlanner.npcVisible(stone[1], stone[2], stone[3]))
            return false;
        clearMovement();
        disableAttack();
        GlobalService.gI().getlist_from_npc((byte)stone[1]);
        stoneStage = 1;
        stoneStageAt = mSystem.currentTimeMillis();
        status = "STONE_MENU";
        return true;
    }

    private static boolean navigateToPortal(Player p, LoadMap map, int nextMap) {
        int portalIndex = -1;
        int bestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < FarmWorldData.EXITS.length; ++i) {
            int[] exit = FarmWorldData.EXITS[i];
            if (exit[0] != map.idMap || exit[1] != nextMap) continue;
            if (!portalPresent(exit[2], exit[3])) continue;
            int d = abs(p.x - exit[2]) + abs(p.y - exit[3]);
            if (d < bestDistance) {
                bestDistance = d;
                portalIndex = i;
            }
        }
        if (portalIndex < 0) return false;
        int[] portal = FarmWorldData.EXITS[portalIndex];
        status = "TO_PORTAL";
        if (abs(p.x - portal[2]) < 38 && abs(p.y - portal[3]) < 38) {
            // Trigger the ordinary server-side portal check using the actual
            // current player position, not a fabricated destination.
            GlobalService.gI().player_move((short)p.x, (short)p.y);
            waitingForPortal = true;
            portalWaitingSince = mSystem.currentTimeMillis();
            return true;
        }
        return makeRoute(p, map, new int[][] {{portal[2], portal[3]}}, 1);
    }

    private static boolean portalPresent(int x, int y) {
        if (LoadMap.vecPointChange == null) return false;
        for (int i = 0; i < LoadMap.vecPointChange.size(); ++i) {
            Point point = (Point)LoadMap.vecPointChange.elementAt(i);
            if (point != null && abs(point.x - x) <= 48 && abs(point.y - y) <= 48) return true;
        }
        return false;
    }

    private static boolean navigateToSpawn(Player p, LoadMap map) {
        int[] row = FarmWorldData.MOBS[targetRow];
        int n = (row.length - 3) / 2;
        int[][] centers = new int[n][2];
        for (int i = 0; i < n; ++i) {
            centers[i][0] = row[3 + 2 * i];
            centers[i][1] = row[4 + 2 * i];
        }
        status = "TO_SPAWN";
        if (farmAnchorX >= 0 && farmAnchorY >= 0)
            return makeRoute(p, map, new int[][]{{farmAnchorX, farmAnchorY}}, 0);
        return makeRoute(p, map, centers, 0);
    }

    /** Find a reachable center-adjacent walkable tile and route in short chunks. */
    private static boolean makeRoute(Player p, LoadMap map, int[][] desiredPoints, int mode) {
        int w = map.mapW;
        int h = map.mapH;
        if (w < 1 || h < 1 || w > 127 || h > 127 ||
                map.mapType == null || map.mapType.length < w * h) return false;
        int px = p.x / TILE, py = p.y / TILE;
        if (px < 0 || py < 0 || px >= w || py >= h) return false;
        int size = w * h;
        int[] parent = new int[size];
        int[] dist = new int[size];
        int[] queue = new int[size];
        for (int i = 0; i < size; ++i) {
            parent[i] = -1;
            dist[i] = -1;
        }
        int start = py * w + px;
        dist[start] = 0;
        parent[start] = start;
        int head = 0, tail = 1;
        queue[0] = start;
        while (head < tail) {
            int curr = queue[head++];
            int x = curr % w, y = curr / w;
            if (x > 0) tail = visit(curr - 1, curr, map.mapType, parent, dist, queue, tail);
            if (x + 1 < w) tail = visit(curr + 1, curr, map.mapType, parent, dist, queue, tail);
            if (y > 0) tail = visit(curr - w, curr, map.mapType, parent, dist, queue, tail);
            if (y + 1 < h) tail = visit(curr + w, curr, map.mapType, parent, dist, queue, tail);
        }

        int bestArea = -1;
        int bestIndex = -1;
        int bestDistance = Integer.MAX_VALUE;
        int bestCenterError = Integer.MAX_VALUE;
        for (int a = 0; a < desiredPoints.length; ++a) {
            int gx = desiredPoints[a][0], gy = desiredPoints[a][1];
            int tx = gx / TILE, ty = gy / TILE;
            int areaIndex = -1;
            int areaError = Integer.MAX_VALUE;
            int areaDistance = Integer.MAX_VALUE;
            for (int dy = -SEARCH_RADIUS_TILES; dy <= SEARCH_RADIUS_TILES; ++dy) {
                for (int dx = -SEARCH_RADIUS_TILES; dx <= SEARCH_RADIUS_TILES; ++dx) {
                    int xx = tx + dx, yy = ty + dy;
                    if (xx < 0 || yy < 0 || xx >= w || yy >= h) continue;
                    int idx = yy * w + xx;
                    if (dist[idx] < 0 || map.mapType[idx] == 1 || map.mapType[idx] == -1) continue;
                    int cx = xx * TILE + TILE / 2;
                    int cy = yy * TILE + TILE / 2;
                    int err = abs(cx - gx) + abs(cy - gy);
                    if (mode != 0 && (abs(cx - gx) >= 36 || abs(cy - gy) >= 36)) continue;
                    // In each cluster, prefer the tile closest to its spawn center.
                    if (err < areaError || err == areaError && dist[idx] < areaDistance) {
                        areaIndex = idx;
                        areaDistance = dist[idx];
                        areaError = err;
                    }
                }
            }
            // Between valid spawn clusters choose the shortest reachable route.
            if (areaIndex >= 0 && (areaDistance < bestDistance ||
                    areaDistance == bestDistance && areaError < bestCenterError)) {
                bestArea = a;
                bestIndex = areaIndex;
                bestDistance = areaDistance;
                bestCenterError = areaError;
            }
        }
        if (bestIndex < 0) return false;
        if (mode == 0 && farmAnchorX < 0 && bestArea >= 0) {
            farmAnchorX = desiredPoints[bestArea][0];
            farmAnchorY = desiredPoints[bestArea][1];
            FarmMemory.save(p.ID, new int[]{
                    p.Lv, getTargetMapId(), getTargetMonsterId(), getTargetMonsterLevel(),
                    farmAnchorX, farmAnchorY});
        }
        int gx = (bestIndex % w) * TILE + TILE / 2;
        int gy = (bestIndex / w) * TILE + TILE / 2;
        if (reached(p, gx, gy)) {
            if (mode == 1) {
                if (bestCenterError <= 60) {
                    GlobalService.gI().player_move((short)p.x, (short)p.y);
                    waitingForPortal = true;
                    portalWaitingSince = mSystem.currentTimeMillis();
                    return true;
                }
                return false;
            }
            if (mode == 2) return beginStoneRequest();
            enterFarming(p);
            return true;
        }
        int[] all = new int[bestDistance + 1];
        int index = bestIndex;
        for (int i = bestDistance; i >= 0; --i) {
            all[i] = index;
            if (index == start) break;
            index = parent[index];
            if (index < 0) return false;
        }
        int count = all.length < MAX_ROUTE_TILES ? all.length : MAX_ROUTE_TILES;
        short[] waypoints = new short[count];
        for (int i = 0; i < count; ++i) {
            int tile = all[i];
            waypoints[i] = (short)(((tile % w) << 8) | (tile / w));
        }
        int last = all[count - 1];
        routeGoalX = (last % w) * TILE + TILE / 2;
        routeGoalY = (last / w) * TILE + TILE / 2;
        p.posTransRoad = waypoints;
        p.countAutoMove = 0;
        p.toX = p.x;
        p.toY = p.y;
        p.xStopMove = routeGoalX;
        p.yStopMove = routeGoalY;
        ownsRoute = true;
        lastX = p.x;
        lastY = p.y;
        lastProgressAt = mSystem.currentTimeMillis();
        return true;
    }

    private static int visit(int next, int from, int[] types, int[] parent,
                             int[] dist, int[] queue, int tail) {
        if (dist[next] >= 0 || types[next] == 1 || types[next] == -1) return tail;
        dist[next] = dist[from] + 1;
        parent[next] = from;
        queue[tail++] = next;
        return tail;
    }

    private static void enterFarming(Player p) {
        clearMovement();
        farming = true;
        waitingForPortal = false;
        retryCount = 0;
        Player.xBeginAutoFire = p.x;
        Player.yBeginAutofire = p.y;
        p.dofire_();
        status = "FARMING";
        info("Auto Farm: den bai " + FarmWorldData.MOB_NAMES[targetRow] +
                " Lv" + getTargetMonsterLevel() + ", bat dau auto danh");
    }

    private static void disableAttack() {
        Player.isAutoFire = (byte)-1;
        Player.isCurAutoFire = false;
        GameScreen.ObjFocus = null;
    }

    private static void clearMovement() {
        if (ownsRoute && GameScreen.player != null) {
            Player p = GameScreen.player;
            p.posTransRoad = null;
            p.countAutoMove = 0;
            p.xStopMove = 0;
            p.yStopMove = 0;
            p.toX = p.x;
            p.toY = p.y;
            p.vx = 0;
            p.vy = 0;
            if (p.Action == 1) p.Action = 0;
        }
        ownsRoute = false;
    }

    private static boolean reached(Player p, int x, int y) {
        return abs(p.x - x) <= ARRIVAL_TOLERANCE &&
                abs(p.y - y) <= ARRIVAL_TOLERANCE;
    }

    private static int abs(int value) {
        return value < 0 ? -value : value;
    }

    private static void info(String message) {
        if (message != null && GameScreen.player != null) {
            GameCanvas.addInfoChar(message);
        }
    }
}
