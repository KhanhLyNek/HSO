package tools.training;

import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Thread_More.LoadMap;
import InterfaceComponents.iCommand;
import netcommand.global.GlobalService;
import tools.trading.TradeController;
import tools.boss.BossController;

/**
 * Client-side training / death-recovery state machine.
 * Uses only existing HSO movement, revive and NPC menu commands.
 * Never guesses an NPC menu index, never resends unacknowledged actions.
 */
public final class TrainingController {
    private static final int IDLE = 0;
    private static final int TRAIN = 1;
    private static final int HOME = 2;
    private static final int STONE = 3;
    private static final int STONE_MENU = 4;
    private static final int MAP_WAIT = 5;
    private static final int SPOT = 6;
    private static int state = IDLE;
    private static int mapId = -1;
    private static byte area;
    private static int spotX, spotY;
    private static String mapName = "";
    private static String charName = "";
    private static int selectedStone = 1000;
    private static int activeStone = 1000;
    private static long enteredAt, lastMove;
    private static int pathFailures, menuDepth, recoveryRetries;
    private static boolean homeSent, areaSent, previousAutoHp;

    private static final int MAX_WAYPOINTS = 6;
    private static final int[] patrolX = new int[MAX_WAYPOINTS];
    private static final int[] patrolY = new int[MAX_WAYPOINTS];
    private static int patrolCount, patrolIndex, patrolMap = -1;
    private static byte patrolArea;
    private static long startedAt, lastTargetCheck, lastMonsterFound, selectedAt;
    private static long lastProgressAt, lastPatrolMove;
    private static int lastPatrolX, lastPatrolY, stuckCount;
    private static int selectedTargetId = -1, candidates, retargets;
    private static int startingLevel, startingProgress;

    private TrainingController() {}

    private static void enter(int next) {
        state = next;
        enteredAt = mSystem.currentTimeMillis();
        lastMove = 0;
        pathFailures = 0;
    }

    private static boolean timedOut(long ms) {
        return mSystem.currentTimeMillis() - enteredAt > ms;
    }

    private static void notice(String text) {
        GameCanvas.addInfoChar("[Auto UP] " + text);
    }

    public static String status() {
        String[] labels = {"Tắt", "Đang luyện", "Về làng", "Tìm đá dịch chuyển",
                "Chờ menu dịch chuyển", "Chờ vào bản đồ", "Trở lại vị trí"};
        return labels[state] + " - Bản đồ " + mapId + " (" + spotX + "," + spotY +
                ") - Quái " + candidates + " - Mốc " + patrolCount;
    }

    public static String statistics() {
        Player p = GameScreen.player;
        long minutes = startedAt > 0 ? (mSystem.currentTimeMillis() - startedAt) / 60000L : 0;
        if (p == null) return "Chưa có dữ liệu nhân vật.";
        return "Luyện " + minutes + " phút - Cấp " + startingLevel + " đến " + p.Lv +
                " - Tiến trình " + startingProgress + " đến " + p.phantramLv +
                " - Đổi quái " + retargets + " lần.";
    }

    public static int getPatrolCount() { return patrolCount; }

    public static void recordWaypoint() {
        Player p = GameScreen.player;
        if (p == null || GameCanvas.loadmap == null || p.Action == 4) {
            notice("Cần đứng ở vị trí muốn lưu.");
            return;
        }
        int here = GameCanvas.loadmap.idMap;
        if (patrolCount != 0 && (here != patrolMap || LoadMap.Area != patrolArea)) {
            notice("Các mốc phải thuộc cùng bản đồ và khu.");
            return;
        }
        if (patrolCount >= MAX_WAYPOINTS) {
            notice("Đã đủ 6 mốc, hãy xoá trước khi ghi lại.");
            return;
        }
        for (int i = 0; i < patrolCount; i++) {
            if (MainObject.getDistance(patrolX[i], patrolY[i], p.x, p.y) < 48) {
                notice("Mốc này quá gần mốc đã lưu.");
                return;
            }
        }
        if (patrolCount == 0) { patrolMap = here; patrolArea = LoadMap.Area; }
        patrolX[patrolCount] = p.x;
        patrolY[patrolCount] = p.y;
        patrolCount++;
        notice("Đã lưu mốc tuần tra " + patrolCount + "/" + MAX_WAYPOINTS);
    }

    public static void clearWaypoints() {
        patrolCount = 0;
        patrolIndex = 0;
        patrolMap = -1;
        notice("Đã xoá tuyến tuần tra.");
    }

    public static void toggle() {
        if (TrainingSettings.isEnabled()) {
            stop("Da tat.");
            return;
        }
        if (TradeController.isRecording() || TradeController.isRunning()) {
            notice("Hay dung Auto Di Buon truoc khi bat luyen cap.");
            return;
        }
        Player p = GameScreen.player;
        if (p == null || GameCanvas.loadmap == null || p.Action == 4 || p.hp <= 0) {
            notice("Hay bat khi nhan vat con song trong bai luyen.");
            return;
        }
        if (BossController.isEnabled()) BossController.stop("Chuyển sang Luyện cấp.");
        TrainingSettings.toggle();
        charName = p.name;
        mapId = GameCanvas.loadmap.idMap;
        mapName = GameCanvas.loadmap.nameMap;
        area = LoadMap.Area;
        spotX = p.x;
        spotY = p.y;
        homeSent = false;
        areaSent = false;
        recoveryRetries = 0;
        previousAutoHp = Player.isAutoHPMP;
        Player.isAutoHPMP = true;
        startedAt = mSystem.currentTimeMillis();
        startingLevel = p.Lv;
        startingProgress = p.phantramLv;
        lastTargetCheck = 0;
        lastMonsterFound = startedAt;
        lastPatrolMove = startedAt;
        lastProgressAt = startedAt;
        lastPatrolX = p.x;
        lastPatrolY = p.y;
        stuckCount = retargets = candidates = 0;
        selectedAt = startedAt;
        selectedTargetId = -1;
        patrolIndex = 0;
        enter(TRAIN);
        p.dofire_();
        notice("Bat luyen cap, ghi diem " + mapId + ":" + spotX + "," + spotY);
    }

    public static void stop(String why) {
        if (!TrainingSettings.isEnabled()) {
            return;
        }
        TrainingSettings.stop();
        state = IDLE;
        Player.isAutoFire = (byte) -1;
        Player.isCurAutoFire = false;
        Player.isAutoHPMP = previousAutoHp;
        if (GameScreen.player != null) {
            GameScreen.player.posTransRoad = null;
        }
        notice(why);
    }

    public static void selectFocusedStone() {
        MainObject focus = GameScreen.ObjFocus;
        if (focus == null || focus.typeObject != 2) {
            notice("Hay chon da dich chuyen/NPC truoc.");
            return;
        }
        selectedStone = focus.getIDnpc();
        notice("Da luu NPC dich chuyen ID " + selectedStone);
    }

    public static boolean allowMonster(MainObject target) {
        if (!TrainingSettings.isEnabled() || state != TRAIN) {
            return true;
        }
        return target != null && target.typeObject == 1 && target.typeBoss == 0
                && !target.isMonstervantieu() && !target.isMiNuong() &&
                !target.isLuaThieng() && target.typeMonster != 7 &&
                target.Action != 4 && !target.isDie && !target.isSend
                && MainObject.getDistance(target.x, target.y, spotX, spotY)
                   <= TrainingSettings.getMaximumRange();
    }

    /** Prevents the original nearest-monster routine selecting trade animals,
     * quest targets, bosses or CAPTCHA when an automatic training mode is on. */
    public static boolean selectNextMonster(Player p) {
        if (!TrainingSettings.isEnabled() || state != TRAIN) return false;
        smartTargetTick(p);
        return true;
    }

    /**
     * One scan per second (not per game frame). Keeps a target long enough to
     * finish an attack, and never selects boss, NPC, CAPTCHA or trader cargo.
     */
    private static void smartTargetTick(Player p) {
        long now = mSystem.currentTimeMillis();
        if (now - lastTargetCheck < 950L) return;
        lastTargetCheck = now;
        MainObject best = null;
        int bestScore = TargetPolicy.INVALID;
        MainObject current = GameScreen.ObjFocus;
        int currentScore = TargetPolicy.INVALID;
        int detected = 0;
        int maxRange = TrainingSettings.getMaximumRange();
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject mob = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (mob == null || !allowMonster(mob)) continue;
            int distance = MainObject.getDistance(p.x, p.y, mob.x, mob.y);
            int campDistance = MainObject.getDistance(spotX, spotY, mob.x, mob.y);
            int score = TargetPolicy.score(TrainingSettings.getMode(), p.Lv, mob.Lv,
                    distance, campDistance, maxRange, mob.hp, mob.maxHp,
                    true, mob.typeBoss != 0 || mob.isDie || mob.isSend);
            if (score == TargetPolicy.INVALID) continue;
            detected++;
            if (mob == current) currentScore = score;
            if (score > bestScore) { best = mob; bestScore = score; }
        }
        candidates = detected;
        if (best == null) {
            if (Player.isAutoFire == 1) Player.isAutoFire = (byte) 0;
            selectedTargetId = -1;
            if (current != null && current.typeObject == 1) GameScreen.ObjFocus = null;
            if (TrainingSettings.isPatrolEnabled() && patrolCount > 1 &&
                    now - lastMonsterFound > 4500L) {
                patrolTick(p, now);
            }
            return;
        }
        lastMonsterFound = now;
        stuckCount = 0;
        if (p.posTransRoad != null && patrolCount > 1) p.posTransRoad = null;
        if (current != best && (currentScore == TargetPolicy.INVALID ||
                TargetPolicy.shouldSwitch(bestScore, currentScore, now - selectedAt))) {
            GameScreen.ObjFocus = best;
            selectedTargetId = best.ID;
            selectedAt = now;
            retargets++;
        } else if (current == best) {
            selectedTargetId = best.ID;
        }
        if (GameScreen.ObjFocus == null ||
                !allowMonster(GameScreen.ObjFocus)) {
            GameScreen.ObjFocus = best;
            selectedTargetId = best.ID;
            selectedAt = now;
        }
        if (Player.isAutoFire != 1 && p.posTransRoad == null) p.dofire_();
    }

    /** Only replays user-recorded waypoints on the same map / zone. */
    private static void patrolTick(Player p, long now) {
        if (patrolMap != GameCanvas.loadmap.idMap || patrolArea != LoadMap.Area) {
            notice("Tuyến tuần tra khác bản đồ hoặc khu hiện tại.");
            TrainingSettings.togglePatrol();
            return;
        }
        int destinationX = patrolX[patrolIndex];
        int destinationY = patrolY[patrolIndex];
        if (MainObject.getDistance(p.x, p.y, destinationX, destinationY) <
                LoadMap.wTile * 2) {
            patrolIndex = (patrolIndex + 1) % patrolCount;
            destinationX = patrolX[patrolIndex];
            destinationY = patrolY[patrolIndex];
            lastPatrolMove = now;
            lastPatrolX = p.x;
            lastPatrolY = p.y;
        }
        if (MainObject.getDistance(p.x, p.y, lastPatrolX, lastPatrolY) > 15) {
            lastPatrolX = p.x;
            lastPatrolY = p.y;
            lastPatrolMove = now;
            stuckCount = 0;
        } else if (now - lastPatrolMove > 8000L) {
            p.posTransRoad = null;
            stuckCount++;
            lastPatrolMove = now;
            if (stuckCount >= 3) {
                stop("Tuần tra bị kẹt, đã dừng an toàn.");
                return;
            }
        }
        Player.isAutoFire = (byte) 0;
        if (p.posTransRoad == null) moveNear(p, destinationX, destinationY, false);
        if (pathFailures >= 4) {
            stop("Không tìm được đường tuần tra.");
        }
    }

    public static void onDeath(Player p) {
        if (!TrainingSettings.isEnabled() || state != TRAIN || p == null) {
            return;
        }
        if (!TrainingSettings.isReturnAfterDeath()) {
            stop("Da chet - tu quay lai dang tat.");
            return;
        }
        mapId = GameCanvas.loadmap.idMap;
        mapName = GameCanvas.loadmap.nameMap;
        area = LoadMap.Area;
        spotX = p.x;
        spotY = p.y;
        homeSent = false;
        areaSent = false;
        recoveryRetries = 0;
        menuDepth = 0;
        Player.isAutoFire = (byte) -1;
        Player.isCurAutoFire = false;
        p.posTransRoad = null;
        enter(HOME);
        notice("Ghi toa do chet: " + spotX + "," + spotY + ". Ve lang...");
    }

    public static void tick(Player p) {
        if (!TrainingSettings.isEnabled() || p == null || GameCanvas.loadmap == null) {
            return;
        }
        if (charName == null || !charName.equals(p.name)) {
            stop("Nhan vat da thay doi.");
            return;
        }
        if (state == TRAIN) {
            if (p.hp <= 0 || p.Action == 4) {
                onDeath(p);
                return;
            }
            if (GameCanvas.currentScreen != GameCanvas.game ||
                    GameCanvas.currentDialog != null || GameCanvas.subDialog != null ||
                    GameCanvas.menu2.isShowMenu) {
                if (Player.isAutoFire == 1) Player.isAutoFire = (byte) 0;
                return;
            }
            if (p.maxHp <= 0 || p.hp * 100L / p.maxHp < TrainingSettings.getMinimumHp()) {
                if (Player.isAutoFire == 1) Player.isAutoFire = (byte) 0;
                return;
            }
            if (TrainingSettings.getMode() == TargetPolicy.CLASSIC) {
                if (Player.isAutoFire != 1 && p.posTransRoad == null) p.dofire_();
            } else {
                smartTargetTick(p);
            }
            return;
        }
        if (state != HOME && p.Action == 4) {
            if (recoveryRetries >= 2) {
                stop("Chet qua nhieu lan trong luc quay lai bai.");
            } else {
                recoveryRetries++;
                homeSent = false;
                Player.isAutoFire = (byte) -1;
                p.posTransRoad = null;
                enter(HOME);
            }
            return;
        }
        if (state == HOME) {
            if (p.Action != 4 && GameScreen.isMapLang) {
                enter(STONE);
                return;
            }
            if (p.Action == 4 && !homeSent) {
                if (LoadMap.typeMap == 2 || GameScreen.infoGame.isMapThachdau()) {
                    stop("Map khong cho ve lang tu dong.");
                    return;
                }
                homeSent = true;
                GlobalService.gI().gohome((byte) 0);
            }
            if (timedOut(20000L)) {
                stop("Khong nhan duoc xac nhan ve lang.");
            }
            return;
        }
        if (GameCanvas.currentScreen != GameCanvas.game || p.Action == 4) {
            if (timedOut(25000L)) {
                stop("Dang cho nhan vat san sang qua lau.");
            }
            return;
        }
        if (state == STONE) {
            if (GameCanvas.loadmap.idMap == mapId) {
                enter(SPOT);
                return;
            }
            MainObject npc = findStone(p);
            if (npc == null) {
                if (timedOut(15000L)) {
                    stop("Khong tim thay da dich chuyen. Hay chon NPC trong menu.");
                }
                return;
            }
            if (MainObject.getDistance(p.x, p.y, npc.x, npc.y) <= p.wFocus - 25) {
                activeStone = npc.getIDnpc();
                menuDepth = 0;
                p.posTransRoad = null;
                GlobalService.gI().getlist_from_npc((byte) activeStone);
                enter(STONE_MENU);
                return;
            }
            moveNear(p, npc.x, npc.y, true);
            if (pathFailures >= 4 || timedOut(25000L)) {
                stop("Khong tim duoc duong den da dich chuyen.");
            }
            return;
        }
        if (state == STONE_MENU || state == MAP_WAIT) {
            if (GameCanvas.loadmap.idMap == mapId) {
                enter(SPOT);
                return;
            }
            if (timedOut(18000L)) {
                stop("Da dich chuyen khong tra ve map da luu.");
            }
            return;
        }
        if (state == SPOT) {
            if (GameCanvas.loadmap.idMap != mapId) {
                stop("Map dich chuyen khong dung.");
                return;
            }
            if (LoadMap.Area != area) {
                if (!areaSent) {
                    areaSent = true;
                    GlobalService.gI().Change_Area(area);
                    enteredAt = mSystem.currentTimeMillis();
                } else if (timedOut(15000L)) {
                    stop("Khong doi duoc dung khu vuc.");
                }
                return;
            }
            if (MainObject.getDistance(p.x, p.y, spotX, spotY) <= LoadMap.wTile * 2) {
                p.posTransRoad = null;
                enter(TRAIN);
                selectedTargetId = -1;
                lastMonsterFound = mSystem.currentTimeMillis();
                p.dofire_();
                notice("Da ve bai luyen, tiep tuc Auto UP.");
                return;
            }
            moveNear(p, spotX, spotY, false);
            if (pathFailures >= 4 || timedOut(30000L)) {
                stop("Khong tim duoc duong ve toa do chet.");
            }
        }
    }

    private static boolean stoneName(String s) {
        if (s == null) return false;
        String n = s.toLowerCase();
        return n.indexOf("dịch chuyển") >= 0 || n.indexOf("dich chuyen") >= 0
                || n.indexOf("teleport") >= 0;
    }

    private static MainObject findStone(Player p) {
        MainObject preferred = null;
        MainObject nearest = null;
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject n = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (n == null || n.isRemove || n.typeObject != 2) continue;
            if (n.getIDnpc() == selectedStone) preferred = n;
            if (stoneName(n.name) || stoneName(n.nameGiaotiep)) {
                int d = MainObject.getDistance(p.x, p.y, n.x, n.y);
                if (d < best) { best = d; nearest = n; }
            }
        }
        return preferred != null ? preferred : nearest;
    }

    private static void moveNear(Player p, int x, int y, boolean npc) {
        if (p.posTransRoad != null) return;
        long now = mSystem.currentTimeMillis();
        if (now - lastMove < 1100L) return;
        lastMove = now;
        int tile = LoadMap.wTile;
        int tx = x / tile, ty = y / tile;
        if (npc) {
            int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int best = Integer.MAX_VALUE, bx = tx, by = ty;
            for (int i = 0; i < offsets.length; i++) {
                int cx = tx + offsets[i][0], cy = ty + offsets[i][1];
                if (!walkable(cx, cy)) continue;
                int d = MainObject.getDistance(p.x, p.y, cx * tile, cy * tile);
                if (d < best) { best = d; bx = cx; by = cy; }
            }
            tx = bx; ty = by;
        }
        if (!walkable(tx, ty)) { pathFailures++; return; }
        int sx = p.x / tile, sy = p.y / tile;
        if (!walkable(sx, sy)) { pathFailures++; return; }
        short[] route;
        try {
            route = GameScreen.gI().updateFindRoad(tx, ty, sx, sy, 80);
        } catch (Exception ex) {
            pathFailures++;
            return;
        }
        int end = ((tx & 255) << 8) - (ty & 255);
        if (route == null || route.length < 2
                || (route[route.length - 1] & 65535) != end) {
            pathFailures++;
            return;
        }
        p.countAutoMove = 0;
        p.toX = p.x;
        p.toY = p.y;
        p.xStopMove = tx * tile + tile / 2;
        p.yStopMove = ty * tile + tile / 2;
        p.posTransRoad = route;
        pathFailures = 0;
    }

    private static boolean walkable(int x, int y) {
        if (GameCanvas.loadmap.mapType == null || x < 0 || y < 0 ||
                x >= GameCanvas.loadmap.mapW || y >= GameCanvas.loadmap.mapH)
            return false;
        int type = GameCanvas.loadmap.mapType[y * GameCanvas.loadmap.mapW + x];
        return type != 1 && type != -1;
    }

    public static boolean onDynamicMenu(short npcId, byte menuId, mVector entries) {
        if (!TrainingSettings.isEnabled() ||
                (state != STONE_MENU && state != MAP_WAIT) || npcId != activeStone) {
            return false;
        }
        int selected = -1, count = 0;
        String destination = mapName == null ? "" : mapName.toLowerCase().trim();
        for (int i = 0; i < entries.size(); i++) {
            iCommand cmd = (iCommand) entries.elementAt(i);
            String label = cmd.caption == null ? "" : cmd.caption.toLowerCase();
            if (destination.length() > 0 && label.indexOf(destination) >= 0) {
                selected = i;
                count++;
            }
        }
        boolean exactDestination = count == 1;
        if (!exactDestination && menuDepth == 0) {
            selected = -1;
            count = 0;
            for (int i = 0; i < entries.size(); i++) {
                iCommand cmd = (iCommand) entries.elementAt(i);
                if (stoneName(cmd.caption)) { selected = i; count++; }
            }
        }
        if (count != 1 || menuDepth >= 3) {
            stop("Menu dich chuyen khong ro. Hay thao tac thu cong.");
            return false;
        }
        menuDepth++;
        GlobalService.gI().Dynamic_Menu(npcId, menuId, (byte) selected);
        enter(exactDestination ? MAP_WAIT : STONE_MENU);
        return true;
    }
}
