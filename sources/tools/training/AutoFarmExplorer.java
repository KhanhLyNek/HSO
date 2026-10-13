package tools.training;

import CLib.mSystem;
import GameObjects.MainObject;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Thread_More.LoadMap;
import netcommand.global.GlobalService;
import tools.navigation.LocalSurvey;
import tools.navigation.SmartNavigator;
import tools.navigation.WorldTraveler;
import tools.quest.QuestAssistant;

/** Progressive exploration when a training location has no suitable targets. */
public final class AutoFarmExplorer {
    private static long noTargetsSince;
    private static long areaRequestedAt, lastMapAttempt, lastInfo;
    private static int areasTried, mapAttempts, lastArea = -1;
    private static int unreachableId = -1;
    private static long unreachableUntil;
    private static boolean waitingArea;

    private AutoFarmExplorer() {}

    public static void reset() {
        noTargetsSince = 0;
        areaRequestedAt = 0;
        areasTried = 0;
        mapAttempts = 0;
        lastArea = -1;
        waitingArea = false;
        unreachableId = -1;
        LocalSurvey.reset();
        SmartNavigator.reset(GameScreen.player);
        WorldTraveler.resetJourney();
    }

    public static void onFoundMonsters() {
        noTargetsSince = 0;
        areasTried = 0;
        mapAttempts = 0;
        waitingArea = false;
        LocalSurvey.reset();
        SmartNavigator.reset(GameScreen.player);
    }

    public static void onMapChanged() {
        noTargetsSince = mSystem.currentTimeMillis();
        waitingArea = false;
        lastArea = LoadMap.Area;
        LocalSurvey.reset();
        SmartNavigator.reset(GameScreen.player);
    }

    public static String status() {
        return "Đã thử " + areasTried + " khu, " + mapAttempts +
                " cổng. " + WorldTraveler.status();
    }

    private static boolean suitable(MainObject mob, Player p) {
        if (mob == null || mob.typeObject != 1 || mob.typeBoss != 0 ||
                mob.isDie || mob.isSend || mob.isRemove ||
                mob.Action == 4 || mob.hp <= 0 ||
                mob.isMonstervantieu() || mob.isMiNuong() ||
                mob.isLuaThieng()) return false;
        int difference = mob.Lv - p.Lv;
        boolean quest = QuestAssistant.bonus(mob) > 0;
        return quest || (difference >= -12 && difference <=
                (TrainingSettings.getMode() == TargetPolicy.SAFE ? 2 : 7));
    }

    private static MainObject bestDistant(Player p) {
        MainObject best = null;
        int highest = Integer.MIN_VALUE;
        for (int i = 0; i < GameScreen.Vecplayers.size(); i++) {
            MainObject mob = (MainObject) GameScreen.Vecplayers.elementAt(i);
            if (!suitable(mob,p)) continue;
            if (mob.ID == unreachableId && mSystem.currentTimeMillis() < unreachableUntil)
                continue;
            int d = MainObject.getDistance(p.x, p.y, mob.x, mob.y);
            if (d > 2000) continue;
            int score = 9000 - d * 3 -
                    Math.abs(p.Lv - mob.Lv) * 90 + QuestAssistant.bonus(mob);
            if (score > highest) { highest = score; best = mob; }
        }
        return best;
    }

    public static void tick(Player p) {
        if (!TrainingSettings.isAutoFindFarm() || p == null ||
                GameCanvas.loadmap == null || p.Action == 4) return;
        long now = mSystem.currentTimeMillis();
        if (noTargetsSince == 0) noTargetsSince = now;
        if (now - noTargetsSince < 3500L) return;

        // 1) Seek appropriate monsters anywhere in the current server map.
        MainObject distant = bestDistant(p);
        if (distant != null) {
            Player.isAutoFire = (byte) 0;
            Player.isCurAutoFire = false;
            int r = SmartNavigator.approach(p, distant.x, distant.y,
                    Math.max(72, p.wFocus - 40));
            if (r == SmartNavigator.ARRIVED) {
                TrainingController.setNewTrainingSpot(p.x, p.y);
                noTargetsSince = now;
            } else if (r == SmartNavigator.FAILED) {
                unreachableId = distant.ID;
                unreachableUntil = now + 60000L;
            }
            return;
        }

        // 2) Explore safe, reachable sectors of the map rather than idling.
        if (LocalSurvey.attempts() < 12 && now - noTargetsSince < 90000L) {
            Player.isAutoFire = (byte) 0;
            int r = LocalSurvey.seek(p);
            if (r != SmartNavigator.FAILED) return;
        }

        // 3) Ask the server for a new zone only once per attempt.
        if (lastArea != LoadMap.Area) {
            waitingArea = false;
            lastArea = LoadMap.Area;
            LocalSurvey.reset();
            noTargetsSince = now;
            return;
        }
        if (waitingArea) {
            if (now - areaRequestedAt < 12000L) return;
            waitingArea = false;
        }
        if (TrainingSettings.isAutoChangeArea() && areasTried < 2 &&
                LoadMap.MaxArea > 1 && !GameScreen.isMapLang &&
                LoadMap.typeMap == LoadMap.MAP_NORMAL) {
            int next = (LoadMap.Area + 1) % LoadMap.MaxArea;
            if (next != LoadMap.Area) {
                areasTried++;
                areaRequestedAt = now;
                waitingArea = true;
                GlobalService.gI().Change_Area((byte) next);
                if (now - lastInfo > 5000L) {
                    GameCanvas.addInfoChar("[Tìm bãi] Đang chuyển khu " + next);
                    lastInfo = now;
                }
                return;
            }
        }

        // 4) Move toward server-confirmed worlds with compatible monster levels.
        if (TrainingSettings.isAutoExploreMap() && mapAttempts < 6) {
            if (now - lastMapAttempt < 1000L) return;
            lastMapAttempt = now;
            int wantedMap = WorldTraveler.suggestedMap(p.Lv);
            int r = wantedMap >= 0 ?
                    WorldTraveler.moveToMap(p, wantedMap, "") :
                    WorldTraveler.exploreNextMap(p);
            if (r == WorldTraveler.MOVING) return;
            mapAttempts++;
            if (r == WorldTraveler.FAILED) {
                GameCanvas.addInfoChar("[Tìm bãi] Cổng không phản hồi, thử hướng khác.");
                SmartNavigator.reset(p);
            }
            return;
        }
        if (now - lastInfo > 18000L) {
            lastInfo = now;
            GameCanvas.addInfoChar("[Tìm bãi] Chưa thấy quái phù hợp, hãy mở thêm bản đồ.");
        }
    }
}
