package tools.quest;

import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainMonster;
import GameObjects.MainObject;
import GameObjects.MainQuest;
import GameObjects.Player;
import GameScreen.GameScreen;
import Main.GameCanvas;
import netcommand.global.GlobalService;
import tools.navigation.SmartNavigator;

/**
 * Optional mission assist. Uses the existing server's active quest lists and
 * registered monster catalogue IDs; never guesses a quest ID or claims rewards
 * without the original server having marked the quest complete.
 */
public final class QuestAssistant {
    private static boolean enabled;
    private static int lastSubmitted = -1;
    private static long lastSubmitAt, lastStatusAt;
    private QuestAssistant() {}

    public static boolean isEnabled() { return enabled; }
    public static void toggle() {
        enabled = !enabled;
        lastSubmitted = -1;
        GameCanvas.addInfoChar(enabled ? "[Nhiệm vụ] Đã bật hỗ trợ nhiệm vụ." :
                "[Nhiệm vụ] Đã tắt hỗ trợ nhiệm vụ.");
    }

    private static MainQuest questAt(mVector quests, int i) {
        return quests == null || i < 0 || i >= quests.size() ?
                null : (MainQuest) quests.elementAt(i);
    }
    private static MainQuest activeAt(int n) {
        int count = MainQuest.vecQuestDoing_Main.size();
        return n < count ? questAt(MainQuest.vecQuestDoing_Main,n) :
                questAt(MainQuest.vecQuestDoing_Sub,n-count);
    }

    public static int activeCount() {
        return MainQuest.vecQuestDoing_Main.size() + MainQuest.vecQuestDoing_Sub.size();
    }

    public static String status() {
        int active = activeCount(), finished = MainQuest.vecQuestFinish.size();
        return "Đang làm " + active + " - Chờ trả " + finished +
                (enabled ? " - BẬT" : " - TẮT");
    }

    /** Bonus only for monster IDs explicitly listed in active kill quests. */
    public static int bonus(MainObject mob) {
        if (!enabled || !(mob instanceof MainMonster)) return 0;
        int templateId = ((MainMonster) mob).catalogyMonster;
        for (int k = 0; k < activeCount(); k++) {
            MainQuest quest = activeAt(k);
            if (quest == null || quest.typeQuest != MainQuest.Q_KILL_MONSTER ||
                    quest.isComplete || quest.mIdQuest == null ||
                    quest.mQuestGot == null || quest.mtotalQuest == null) continue;
            int n = Math.min(quest.mIdQuest.length,
                    Math.min(quest.mQuestGot.length,quest.mtotalQuest.length));
            for (int i = 0; i < n; i++) {
                if (quest.mIdQuest[i] == templateId &&
                    quest.mQuestGot[i] < quest.mtotalQuest[i]) return 2400;
            }
        }
        return 0;
    }

    /** True only after the server has listed the quest as ready to finish. */
    public static boolean submitIfReady(Player p) {
        if (!enabled || p == null || p.Action == 4 ||
                GameCanvas.currentScreen != GameCanvas.game ||
                GameCanvas.menu2.isShowMenu ||
                GameCanvas.currentDialog != null ||
                GameCanvas.subDialog != null) return false;
        long now = mSystem.currentTimeMillis();
        for (int i = 0; i < MainQuest.vecQuestFinish.size(); i++) {
            MainQuest quest = questAt(MainQuest.vecQuestFinish,i);
            if (quest == null || !quest.isComplete) continue;
            MainObject npc = MainObject.get_Object(quest.idNPC_To, (byte) 2);
            if (npc == null || npc.isRemove) continue;
            if (MainObject.getDistance(p.x,p.y,npc.x,npc.y) > p.wFocus - 16) {
                SmartNavigator.approach(p,npc.x,npc.y,p.wFocus-24);
                return true;
            }
            if (lastSubmitted == quest.ID && now - lastSubmitAt < 60000L) return true;
            lastSubmitted = quest.ID;
            lastSubmitAt = now;
            GlobalService.gI().quest((short) quest.ID,
                    (byte) (quest.isMain ? 0 : 1), (byte) 1);
            GameCanvas.addInfoChar("[Nhiệm vụ] Đã gửi yêu cầu trả: " + quest.name);
            return true;
        }
        return false;
    }
}
