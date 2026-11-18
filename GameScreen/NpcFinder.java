package GameScreen;

import CLib.mVector;
import GameObjects.MainObject;
import GameObjects.Player;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.Point;
import netcommand.global.GlobalService;
import Thread_More.LoadMap;

/**
 * Menu tich hop "Tim NPC": liet ke NPC trong map hien tai (gan nhat truoc),
 * chon 1 NPC de danh dau tren minimap va tu dong chay toi.
 */
public class NpcFinder {
    public static final int CMD_OPEN = 32;   // mo danh sach NPC
    public static final int CMD_SELECT = 33; // chon 1 NPC (subIndex = vi tri trong list)
    public static final int CMD_AUTO = 34;   // bat dau tu dong tim NPC theo ten
    public static final int CMD_STOP = 35;   // dung tu dong tim

    public static final String AUTO_TARGET = "Đứa bé";
    private static final int MAX_MAPS = 25;       // gioi han so lan sang map moi
    private static final int WAIT_LOAD = 40;      // so tick doi NPC tai xong khi vao map
    private static final int WAIT_MOVE = 25;      // so tick cho nhan vat bat dau di

    private static final int MAX_ITEMS = 30;
    private static MainObject[] list = new MainObject[0];

    /** Mo menu danh sach NPC trong map. */
    public static void show() {
        mVector all = GameScreen.Vecplayers;
        int n = 0;
        MainObject[] tmp = new MainObject[all.size()];
        for (int i = 0; i < all.size(); i++) {
            MainObject o = (MainObject) all.elementAt(i);
            if (o == null || o == GameScreen.player || o.isRemove) continue;
            if (!o.isNPC()) continue;
            if (o.name == null || o.name.length() == 0) continue;
            tmp[n++] = o;
        }
        // sap xep theo khoang cach tang dan (insertion sort, n nho)
        for (int i = 1; i < n; i++) {
            MainObject k = tmp[i];
            int dk = dist(k);
            int j = i - 1;
            while (j >= 0 && dist(tmp[j]) > dk) {
                tmp[j + 1] = tmp[j];
                j--;
            }
            tmp[j + 1] = k;
        }
        if (n > MAX_ITEMS) n = MAX_ITEMS;
        list = new MainObject[n];
        mVector menu = new mVector("NpcFinder menu");
        if (searching) {
            menu.addElement(new iCommand("Dừng tìm", CMD_STOP, (Model.AvMain) GameCanvas.game));
        } else {
            menu.addElement(new iCommand("Tự động tìm " + AUTO_TARGET, CMD_AUTO, (Model.AvMain) GameCanvas.game));
        }
        for (int i = 0; i < n; i++) {
            list[i] = tmp[i];
            int tiles = dist(tmp[i]) / LoadMap.wTile;
            menu.addElement(new iCommand(tmp[i].name + " (" + tiles + ")", CMD_SELECT, i, (Model.AvMain) GameCanvas.game));
        }
        GameCanvas.menu2.startAt(menu, 2, "Tìm NPC", false, null);
    }

    /** Chon NPC thu index trong danh sach: danh dau minimap + tu chay toi. */
    public static void select(int index) {
        if (index < 0 || index >= list.length) return;
        MainObject o = list[index];
        if (o == null || o.isRemove) {
            GameCanvas.addInfoChar("NPC không còn trong map");
            return;
        }
        Player p = GameScreen.player;
        GameScreen.ObjFocus = o;
        try {
            GameCanvas.minimap.setPoint(o.x / LoadMap.wTile, o.y / LoadMap.wTile, GameCanvas.loadmap.idMap);
        } catch (Exception e) {
            // minimap chua san sang - bo qua
        }
        if (dist(o) <= LoadMap.wTile * 2) {
            GameCanvas.addInfoChar(o.name + " ở ngay đây");
            return;
        }
        try {
            p.toX = p.x;
            p.toY = p.y;
            // dung cach NPC 1 chut (phia duoi) de khong dam vao NPC
            p.xStopMove = o.x;
            p.yStopMove = o.y + LoadMap.wTile;
            p.posTransRoad = GameCanvas.game.updateFindRoad(
                    p.xStopMove / LoadMap.wTile, p.yStopMove / LoadMap.wTile,
                    p.x / LoadMap.wTile, p.y / LoadMap.wTile, 60);
            p.countAutoMove = 0;
            if (p.posTransRoad == null) {
                p.toX = p.xStopMove;
                p.toY = p.yStopMove;
            }
            GameCanvas.addInfoChar("Đang đi tới " + o.name);
        } catch (Exception e) {
            p.posTransRoad = null;
            p.xStopMove = 0;
            p.yStopMove = 0;
            GameCanvas.addInfoChar("Không tìm được đường tới " + o.name);
        }
    }

    // ===================== TU DONG TIM THEO THU TU =====================
    // Moi map: vao map -> hoi so khu -> do tung khu -> di qua cong chua thu -> map moi.
    private static final int P_ENTER = 0, P_REQ = 1, P_WAIT_REQ = 2, P_AREA_NEXT = 3, P_WAIT_AREA = 4, P_PORTAL = 5;

    private static boolean searching = false;
    private static String target = "";
    private static int phase = P_ENTER;
    private static int lastMap = -1;
    private static int timer = 0;
    private static int moveGrace = 0;
    private static int mapsVisited = 0;
    private static int reqWait = 0;
    private static int areaWait = 0;
    private static int areaTarget = -1;
    private static int[] areaList = new int[0];
    private static int areaQ = 0;
    private static Point[] portals = new Point[0]; // snapshot cong cua map hien tai
    private static int[] triedKeys = new int[256];  // map*100+idxCong da di
    private static int triedCount = 0;
    private static int[] scannedMaps = new int[64]; // map da do het cac khu
    private static int scannedCount = 0;
    private static int lastSnapMap = -1;

    /** Bat dau: tim NPC ten AUTO_TARGET o moi khu cua moi map. */
    public static void startAuto() {
        searching = true;
        target = norm(AUTO_TARGET);
        lastMap = -1;
        phase = P_ENTER;
        timer = 0;
        moveGrace = 0;
        mapsVisited = 0;
        triedCount = 0;
        scannedCount = 0;
        GameCanvas.addInfoChar("Bắt đầu tìm " + AUTO_TARGET);
    }

    public static void stopAuto() {
        if (searching) GameCanvas.addInfoChar("Đã dừng tìm");
        searching = false;
        GameScreen.player.posTransRoad = null;
    }

    private static void finish(String msg) {
        searching = false;
        GameCanvas.addInfoChar(msg);
    }

    /** Neu thay NPC thi dung lai va chay toi. */
    private static boolean check() {
        MainObject found = findByName(target);
        if (found == null) return false;
        searching = false;
        GameCanvas.addInfoChar("Đã thấy " + found.name + "!");
        list = new MainObject[]{found};
        select(0);
        return true;
    }

    private static void enterMap(int idMap) {
        lastMap = idMap;
        if (++mapsVisited > MAX_MAPS) {
            finish("Dừng: đã đi quá nhiều map");
            return;
        }
        phase = P_ENTER;
        timer = WAIT_LOAD;
        moveGrace = 0;
    }

    /** Goi moi khung hinh tu GameScreen.update(). */
    public static void tick() {
        if (!searching) return;
        try {
            if (GameCanvas.currentScreen != GameCanvas.game || GameScreen.player == null
                    || GameCanvas.loadmap == null) {
                return;
            }
            Player p = GameScreen.player;
            int idMap = GameCanvas.loadmap.idMap;
            if (idMap != lastMap) { enterMap(idMap); return; }
            // menu/hop thoai nguoi choi mo -> tam dung (tru luc cho bang khu vuc cua game)
            if (phase != P_WAIT_REQ && (GameCanvas.menu2.isShowMenu || GameCanvas.currentDialog != null)) return;
            if (timer > 0) { timer--; return; }

            switch (phase) {
                case P_ENTER: {
                    snapshotPortals();
                    // cong gan cho vua xuat hien nhat la cong quay lai -> coi nhu da thu
                    int back = nearestPortal(p.x, p.y, true);
                    if (back >= 0) markTried(idMap, back);
                    if (check()) return;
                    if (isScanned(idMap)) { phase = P_PORTAL; return; }
                    phase = P_REQ;
                    return;
                }
                case P_REQ: {
                    GameScreen.isReArea = true;
                    GlobalService.gI().Request_Area();
                    reqWait = 120;
                    phase = P_WAIT_REQ;
                    return;
                }
                case P_WAIT_REQ: {
                    if (GameScreen.isReArea && --reqWait > 0) return;
                    GameScreen.isReArea = false;
                    // dong bang chon khu vuc ma game tu mo ra
                    GameCanvas.menu2.doCloseMenu();
                    GameCanvas.end_Dialog();
                    int max = LoadMap.MaxArea;
                    if (max < 1) max = 1;
                    if (max > 30) max = 30;
                    int cur = LoadMap.Area;
                    areaList = new int[max];
                    int c = 0;
                    for (int i = 0; i < max; i++) if (i != cur) areaList[c++] = i;
                    int[] t = new int[c];
                    System.arraycopy(areaList, 0, t, 0, c);
                    areaList = t;
                    areaQ = 0;
                    phase = P_AREA_NEXT;
                    return;
                }
                case P_AREA_NEXT: {
                    if (check()) return;
                    if (areaQ >= areaList.length) {
                        markScanned(idMap);
                        phase = P_PORTAL;
                        return;
                    }
                    areaTarget = areaList[areaQ++];
                    GameCanvas.addInfoChar("Dò khu " + (areaTarget + 1));
                    GlobalService.gI().Change_Area((byte) areaTarget);
                    areaWait = 150;
                    phase = P_WAIT_AREA;
                    return;
                }
                case P_WAIT_AREA: {
                    if (LoadMap.Area == areaTarget) {   // da chuyen xong, doi NPC tai
                        timer = WAIT_LOAD;
                        phase = P_AREA_NEXT;
                    } else if (--areaWait <= 0) {       // khu day / bi tu choi -> bo qua
                        phase = P_AREA_NEXT;
                    }
                    return;
                }
                default: { // P_PORTAL
                    if (check()) return;
                    if (moveGrace > 0) { moveGrace--; return; }
                    if (p.posTransRoad != null || p.Action == 1 || p.vx != 0 || p.vy != 0) return; // dang di
                    int ni = nearestPortal(p.x, p.y, false);
                    if (ni < 0) {
                        finish("Không thấy " + AUTO_TARGET + " ở các map và khu đã dò");
                        return;
                    }
                    Point next = portals[ni];
                    markTried(idMap, ni);
                    p.toX = p.x;
                    p.toY = p.y;
                    p.xStopMove = next.x;
                    p.yStopMove = next.y;
                    p.posTransRoad = GameCanvas.game.updateFindRoad(
                            next.x / LoadMap.wTile, next.y / LoadMap.wTile,
                            p.x / LoadMap.wTile, p.y / LoadMap.wTile, 80);
                    p.countAutoMove = 0;
                    if (p.posTransRoad == null) {
                        p.toX = next.x;
                        p.toY = next.y;
                    }
                    moveGrace = WAIT_MOVE;
                    GameCanvas.addInfoChar("Đi tới: " + next.name);
                }
            }
        } catch (Exception e) {
            GameScreen.player.posTransRoad = null;
            moveGrace = WAIT_MOVE; // thu lai o lan sau
        }
    }

    private static void snapshotPortals() {
        mVector v = LoadMap.vecPointChange;
        // chi chup lai neu map co cong (sau doi khu danh sach co the bi xoa)
        if (v.size() == 0 && portals.length > 0 && lastSnapMap == GameCanvas.loadmap.idMap) return;
        portals = new Point[v.size()];
        for (int i = 0; i < portals.length; i++) portals[i] = (Point) v.elementAt(i);
        lastSnapMap = GameCanvas.loadmap.idMap;
    }

    private static MainObject findByName(String normName) {
        mVector all = GameScreen.Vecplayers;
        for (int i = 0; i < all.size(); i++) {
            MainObject o = (MainObject) all.elementAt(i);
            if (o == null || o == GameScreen.player || o.isRemove || o.name == null) continue;
            if (norm(o.name).indexOf(normName) >= 0) return o;
        }
        return null;
    }

    /** Chi so cong gan nhat; any=true: ke ca cong da thu, false: chi cong chua thu. -1 neu khong co. */
    private static int nearestPortal(int x, int y, boolean any) {
        int best = -1;
        int bd = Integer.MAX_VALUE;
        for (int i = 0; i < portals.length; i++) {
            Point q = portals[i];
            if (q == null) continue;
            if (!any && isTried(GameCanvas.loadmap.idMap, i)) continue;
            int d = MainObject.getDistance(x, y, q.x, q.y);
            if (d < bd) { bd = d; best = i; }
        }
        return best;
    }

    private static boolean isTried(int map, int idx) {
        for (int i = 0; i < triedCount; i++) if (triedKeys[i] == map * 100 + idx) return true;
        return false;
    }

    private static void markTried(int map, int idx) {
        if (idx < 0 || triedCount >= triedKeys.length || isTried(map, idx)) return;
        triedKeys[triedCount++] = map * 100 + idx;
    }

    private static boolean isScanned(int map) {
        for (int i = 0; i < scannedCount; i++) if (scannedMaps[i] == map) return true;
        return false;
    }

    private static void markScanned(int map) {
        if (scannedCount < scannedMaps.length && !isScanned(map)) scannedMaps[scannedCount++] = map;
    }

    /** Bo dau tieng Viet + ha chu thuong de so ten khong phan biet dau. */
    private static String norm(String s) {
        String from = "àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ";
        String to   = "aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyyd";
        StringBuffer sb = new StringBuffer();
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int k = from.indexOf(c);
            sb.append(k >= 0 ? to.charAt(k) : c);
        }
        return sb.toString();
    }

    private static int dist(MainObject o) {
        return MainObject.getDistance(GameScreen.player.x, GameScreen.player.y, o.x, o.y);
    }
}
