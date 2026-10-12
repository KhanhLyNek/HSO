package Main;

import GameScreen.GameScreen;
import Thread_More.LoadMap;

/** Read-only observations of map and zone values already decoded by the client. */
public final class ChildLocationDiagnostics {
    private static int lastMap = -1;
    private static int lastArea = -1;
    private static String lastMapName = "";
    private static String notice = "Chua co thong tin dua be";
    private ChildLocationDiagnostics() {}

    public static void onMapChanged(int mapId, String mapName, int area) {
        lastMap = mapId;
        lastMapName = mapName == null ? "" : mapName;
        lastArea = area;
        notice = "Map " + lastMap + " / khu " + lastArea;
    }

    public static void onAreaChanged(int area) {
        lastArea = area;
        notice = "Map " + lastMap + " / khu " + lastArea;
    }

    /** Only call this when an actual child-spawn announcement has been verified. */
    public static void onVerifiedChildNotice(int mapId, int area) {
        notice = "Dua be: map " + mapId + " / khu " + area;
    }

    public static String getNotice() { return notice; }
    public static int getMapId() { return lastMap; }
    public static int getArea() { return lastArea; }
    public static String getMapName() { return lastMapName; }
}
