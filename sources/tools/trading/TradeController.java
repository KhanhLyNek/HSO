package tools.trading;

import CLib.mSystem;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Thread_More.LoadMap;
import tools.training.TrainingController;
import tools.training.TrainingSettings;

/**
 * Merchant convoy route recorder/replayer.
 *
 * Transactions are always performed by the player in the original NPC UI;
 * the tool does not fabricate buying/selling commands, NPC choices, or map IDs.
 * Cross-map gateways are explicitly user-assisted until gateway semantics are
 * verified on the target server.
 */
public final class TradeController {
    private static final int OFF = 0;
    private static final int RECORD = 1;
    private static final int READY = 2;
    private static final int RUN = 3;
    private static final int WAIT_GATE = 4;
    private static final int ARRIVED = 5;

    private static final TradeRoute route = new TradeRoute();
    private static int state = OFF;
    private static int index;
    private static long enteredAt, missingPackSince, lastMove, lastPackWarning;
    private static long journeyStart, awaitingInfoSince;
    private static int pathErrors, lastMap = -1, delayDistance = 144;
    private static String character = "";
    private static String originName = "";
    private static String destinationName = "";
    private static int sourceNpc = -1, destinationNpc = -1;
    private static boolean hasPack, warnedTime;

    private TradeController() {}

    private static void say(String message) {
        GameCanvas.addInfoChar("[Di buon] " + message);
    }

    private static long now() {
        return mSystem.currentTimeMillis();
    }

    private static void state(int next) {
        state = next;
        enteredAt = now();
        lastMove = 0;
        pathErrors = 0;
    }

    public static boolean isRecording() { return state == RECORD; }
    public static boolean isRunning() { return state == RUN || state == WAIT_GATE; }
    public static int getDistanceLimit() { return delayDistance; }

    public static void cycleDistanceLimit() {
        delayDistance = delayDistance == 96 ? 144 : delayDistance == 144 ? 192 : 96;
        say("Gioi han cach thu hang: " + delayDistance);
    }

    public static String status() {
        String s = state == RECORD ? "Ghi tuyen" :
                state == RUN ? "Dang dan hang" :
                state == WAIT_GATE ? "Cho qua cong" :
                state == READY ? "San sang" :
                state == ARRIVED ? "Da den noi" : "Tat";
        return s + " | moc " + index + "/" + route.size()
                + " | con vat " + (hasPack ? "co" : "chua thay");
    }

    public static void startRecording() {
        Player p = GameScreen.player;
        if (p == null || GameCanvas.loadmap == null || p.hp <= 0
                || p.Action == 4 || p.Lv < 40) {
            say("Can nhan vat song va cap 40+.");
            return;
        }
        if (isRunning()) {
            say("Hay dung chay tuyen truoc khi ghi moi.");
            return;
        }
        if (TrainingSettings.isEnabled()) TrainingController.stop("Chuyen sang ghi tuyen di buon.");
        route.clear();
        character = p.name;
        originName = GameCanvas.loadmap.nameMap;
        destinationName = "";
        sourceNpc = -1;
        destinationNpc = -1;
        index = 0;
        lastMap = GameCanvas.loadmap.idMap;
        hasPack = false;
        warnedTime = false;
        route.add(lastMap, LoadMap.Area, p.x, p.y, false);
        state(RECORD);
        say("Bat dau ghi tuyen. Tu mua hang va dan thu di den NPC Graham.");
    }

    public static void finishRecording() {
        if (state != RECORD) {
            say("Chua bat ghi tuyen.");
            return;
        }
        Player p = GameScreen.player;
        if (p != null && GameCanvas.loadmap != null) {
            route.add(GameCanvas.loadmap.idMap, LoadMap.Area, p.x, p.y, false);
            destinationName = GameCanvas.loadmap.nameMap;
        }
        if (route.size() < 2) {
            say("Tuyen qua ngan. Hay di chuyen them.");
            return;
        }
        state(READY);
        say("Da luu " + route.size() + " moc. Khong luu qua lan thoat game.");
    }

    public static void beginEscort() {
        Player p = GameScreen.player;
        if (state != READY && state != ARRIVED) {
            say("Hay ghi va ket thuc ghi tuyen truoc.");
            return;
        }
        if (p == null || p.hp <= 0 || p.Action == 4
                || GameCanvas.loadmap == null || !p.name.equals(character)) {
            say("Khong dung nhan vat hoac nhan vat da chet.");
            return;
        }
        TradeRoute.Step first = route.get(0);
        if (first == null || GameCanvas.loadmap.idMap != first.mapId
                || LoadMap.Area != first.area ||
                MainObject.getDistance(p.x, p.y, first.x, first.y) > 144) {
            say("Hay quay lai diem xuat phat cua tuyen " + originName);
            return;
        }
        if (findOwnedAnimal(p) == null) {
            say("Chua tim thay con vat thuong nhan cua ban.");
            return;
        }
        if (p.Lv < 40) {
            say("Can cap 40+ de di buon.");
            return;
        }
        if (TrainingSettings.isEnabled()) TrainingController.stop("Chuyen sang Auto Di Buon.");
        // No Auto Fire while transporting goods.
        Player.isAutoFire = (byte) -1;
        Player.isCurAutoFire = false;
        index = 0;
        hasPack = true;
        missingPackSince = 0;
        warnedTime = false;
        journeyStart = now();
        awaitingInfoSince = 0;
        lastMap = GameCanvas.loadmap.idMap;
        state(RUN);
        say("Bat dau dan hang. Se dung tai cong chuyen map.");
    }

    public static void stop() {
        if (state == OFF) return;
        if (GameScreen.player != null && isRunning()) {
            GameScreen.player.posTransRoad = null;
        }
        state(route.size() >= 2 ? READY : OFF);
        hasPack = false;
        say("Da dung Auto Di Buon.");
    }

    public static void captureSourceNpc() { captureNpc(true); }
    public static void captureDestinationNpc() { captureNpc(false); }

    public static void openSourceNpc() { openRecordedNpc(sourceNpc); }
    public static void openDestinationNpc() { openRecordedNpc(destinationNpc); }

    private static void openRecordedNpc(int id) {
        Player p = GameScreen.player;
        if (id < 0 || p == null) {
            say("Chua luu NPC o cho giao dich.");
            return;
        }
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject n = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (n == null || n.isRemove || n.typeObject != 2 || n.getIDnpc() != id)
                continue;
            if (MainObject.getDistance(p.x, p.y, n.x, n.y) > p.wFocus) {
                say("Hay den gan NPC truoc khi giao dich.");
                return;
            }
            GameScreen.ObjFocus = n;
            n.GiaoTiep();
            return;
        }
        say("NPC khong hien dien o map nay.");
    }

    private static void captureNpc(boolean source) {
        MainObject npc = GameScreen.ObjFocus;
        if (npc == null || npc.typeObject != 2) {
            say("Hay chon NPC Graham hoac Dylan tren man hinh.");
            return;
        }
        if (source) {
            sourceNpc = npc.getIDnpc();
            say("Luu NPC mua hang ID " + sourceNpc);
        } else {
            destinationNpc = npc.getIDnpc();
            say("Luu NPC ban hang ID " + destinationNpc);
        }
    }

    /** Called before the original map is disposed. */
    public static void beforeMapChange() {
        if (state != RECORD || GameCanvas.loadmap == null || GameScreen.player == null)
            return;
        Player p = GameScreen.player;
        if (!route.add(GameCanvas.loadmap.idMap, LoadMap.Area, p.x, p.y, true)) {
            stop();
            say("Da dat gioi han 400 moc.");
        }
    }

    /** Called after the server has provided the new map and area. */
    public static void afterMapChange() {
        if (GameCanvas.loadmap == null || GameScreen.player == null) return;
        int map = GameCanvas.loadmap.idMap;
        Player p = GameScreen.player;
        if (state == RECORD) {
            if (!route.add(map, LoadMap.Area, p.x, p.y, false)) {
                stop();
                say("Da dat gioi han 400 moc.");
            }
        } else if (state == WAIT_GATE || state == RUN) {
            TradeRoute.Step following = route.get(index + 1);
            if (following == null || following.mapId != map ||
                    following.area != LoadMap.Area) {
                stop();
                say("Khong dung map/khu cua tuyen ghi.");
                return;
            }
            p.posTransRoad = null;
            index++;
            missingPackSince = now();
            state(RUN);
            say("Da qua map " + map + ", cho thu hang bat kip.");
        }
        lastMap = map;
    }

    public static void onDeath() {
        if (state == RECORD || isRunning()) {
            stop();
            say("Nhan vat chet; dung tuyen de tranh mat hang.");
        }
    }

    public static void tick(Player p) {
        if (state != RECORD && !isRunning()) return;
        if (p == null || GameCanvas.loadmap == null || character == null ||
                !character.equals(p.name) || p.hp <= 0 || p.Action == 4) {
            onDeath();
            return;
        }
        if (state == RECORD) {
            if (GameCanvas.currentScreen != GameCanvas.game) return;
            if (!route.add(GameCanvas.loadmap.idMap, LoadMap.Area, p.x, p.y, false)) {
                finishRecording();
                say("Tu dong ket thuc khi du 400 moc.");
            }
            return;
        }
        if (GameCanvas.currentScreen != GameCanvas.game ||
                GameCanvas.currentDialog != null || GameCanvas.subDialog != null ||
                GameCanvas.menu2.isShowMenu) {
            return;
        }
        MainObject animal = findOwnedAnimal(p);
        hasPack = animal != null;
        if (animal == null) {
            if (missingPackSince == 0) missingPackSince = now();
            p.posTransRoad = null;
            if (now() - missingPackSince > 10000L) {
                stop();
                say("Khong thay con vat thuong nhan trong 10 giay.");
            }
            return;
        }
        missingPackSince = 0;
        if (!animal.isInfo) {
            p.posTransRoad = null;
            if (awaitingInfoSince == 0) awaitingInfoSince = now();
            if (now() - awaitingInfoSince > 12000L) {
                stop();
                say("Server chua gui thong tin thu hang.");
            }
            return;
        }
        awaitingInfoSince = 0;
        if (animal.Action == 4 || animal.hp <= 0) {
            stop();
            say("Con vat cho hang da chet.");
            return;
        }
        if (animal.maxHp > 0 && animal.hp * 100L / animal.maxHp < 25 &&
                now() - lastPackWarning > 10000L) {
            lastPackWarning = now();
            say("Canh bao: thu hang duoi 25% mau!");
        }
        if (!warnedTime && now() - journeyStart > 23L * 60L * 1000L) {
            warnedTime = true;
            say("Da di buon hon 23 phut - kiem tra thoi gian thu hang.");
        }
        int gap = MainObject.getDistance(p.x, p.y, animal.x, animal.y);
        if (gap > delayDistance) {
            p.posTransRoad = null;
            if (now() - lastPackWarning > 7000L) {
                lastPackWarning = now();
                say("Dung lai cho thu hang, khoang cach " + gap);
            }
            return;
        }
        if (state == WAIT_GATE) {
            if (now() - enteredAt > 45000L) {
                stop();
                say("Khong qua cong sau 45 giay. Hay kiem tra lai.");
            }
            return;
        }
        TradeRoute.Step step = route.get(index);
        if (step == null) {
            finishEscort();
            return;
        }
        if (step.mapId != GameCanvas.loadmap.idMap || step.area != LoadMap.Area) {
            stop();
            say("Sai ban do hoac khu vuc.");
            return;
        }
        int distance = MainObject.getDistance(p.x, p.y, step.x, step.y);
        if (distance <= (step.exit ? LoadMap.wTile * 2 : LoadMap.wTile)) {
            if (step.exit) {
                p.posTransRoad = null;
                state(WAIT_GATE);
                say("Den cong! Hay qua cong thu cong de tiep tuc tuyen.");
                return;
            }
            if (index + 1 >= route.size()) {
                finishEscort();
                return;
            }
            index++;
            return;
        }
        if (p.posTransRoad == null && now() - lastMove > 900L) {
            lastMove = now();
            if (!moveToward(p, step.x, step.y)) {
                pathErrors++;
                if (pathErrors >= 4) {
                    stop();
                    say("Khong tim duoc duong. Hay dan thu hang thu cong.");
                }
            } else {
                pathErrors = 0;
            }
        }
    }

    private static void finishEscort() {
        if (GameScreen.player != null) GameScreen.player.posTransRoad = null;
        state(ARRIVED);
        say("Da den noi ban hang " + destinationName + ". Kiem tra NPC de ban hang.");
        if (destinationNpc >= 0) openDestinationNpc();
    }

    private static MainObject findOwnedAnimal(Player p) {
        MainObject nearest = null;
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject obj = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (obj == null || obj.isRemove || !obj.isMonstervantieu() ||
                    !obj.isMonsterVantieuTrang() || obj.getnameOwner() == null ||
                    !obj.getnameOwner().equals(p.name)) continue;
            int d = MainObject.getDistance(p.x, p.y, obj.x, obj.y);
            if (d < best) { nearest = obj; best = d; }
        }
        return nearest;
    }

    private static boolean moveToward(Player p, int x, int y) {
        int tile = LoadMap.wTile;
        int tx = x / tile, ty = y / tile;
        int sx = p.x / tile, sy = p.y / tile;
        if (!walkable(tx, ty) || !walkable(sx, sy)) return false;
        short[] path;
        try {
            path = GameScreen.gI().updateFindRoad(tx, ty, sx, sy, 80);
        } catch (Exception e) {
            return false;
        }
        int expected = ((tx & 255) << 8) | (ty & 255);
        if (path == null || path.length < 2 ||
                (path[path.length - 1] & 65535) != expected) return false;
        p.countAutoMove = 0;
        p.toX = p.x;
        p.toY = p.y;
        p.xStopMove = tx * tile + tile / 2;
        p.yStopMove = ty * tile + tile / 2;
        p.posTransRoad = path;
        return true;
    }

    private static boolean walkable(int x, int y) {
        if (GameCanvas.loadmap.mapType == null || x < 0 || y < 0 ||
                x >= GameCanvas.loadmap.mapW || y >= GameCanvas.loadmap.mapH)
            return false;
        int t = GameCanvas.loadmap.mapType[y * GameCanvas.loadmap.mapW + x];
        return t != 1 && t != -1;
    }
}
