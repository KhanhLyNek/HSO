package GameScreen;

/**
 * Low-overhead event log and level-progress statistics.
 * phantramLv is server-reported level progress, NOT absolute EXP;
 * therefore this module never mislabels derived progress as exact EXP/hour.
 */
public final class LyMeowzDiagnostics {
    private static final int LOG_LIMIT = 24;
    private final String[] events = new String[LOG_LIMIT];
    private int nextEvent, eventCount, failures, mapHops, questKills, deaths;
    private long startedAt, lastObserved;
    private int startLevel, startProgress, currentLevel, currentProgress;

    public void observe(int level, int progress, long now) {
        if (now <= 0 || level < 0) return;
        if (startedAt == 0 || now < lastObserved) {
            startedAt = now;
            startLevel = level;
            startProgress = progress;
        }
        currentLevel = level;
        currentProgress = progress;
        lastObserved = now;
    }

    public void event(String message) {
        if (message == null) return;
        if (message.length() > 90) message = message.substring(0, 90);
        events[nextEvent] = message;
        nextEvent = (nextEvent + 1) % LOG_LIMIT;
        if (eventCount < LOG_LIMIT) eventCount++;
    }

    public void failure(String message) {
        failures++;
        event("ERR " + message);
    }

    public void mapHop(int mapId, int areaId) {
        mapHops++;
        event("Map " + mapId + "/khu " + areaId);
    }

    public void questKill() { questKills++; }
    public void death() { deaths++; event("Nhan vat da chet"); }

    public String rate(long now) {
        if (startedAt <= 0 || now <= startedAt || now - startedAt < 10000L)
            return "Chua du du lieu";
        if (currentLevel != startLevel)
            return "+" + (currentLevel - startLevel) + " cap, khong co EXP tuyet doi";
        long delta = (long) currentProgress - startProgress;
        long rate = delta * 3600000L / (now - startedAt);
        return rate + " don vi tien do cap/gio (uoc tinh)";
    }

    public int failures() { return failures; }
    public int mapHops() { return mapHops; }
    public int questKills() { return questKills; }
    public int deaths() { return deaths; }

    public String latestEvent() {
        if (eventCount == 0) return "Chua co su kien";
        return events[(nextEvent + LOG_LIMIT - 1) % LOG_LIMIT];
    }

    public String summary(long now) {
        return "LyMeowz: " + rate(now) + "; quai NV " + questKills +
                "; qua map " + mapHops + "; chet " + deaths +
                "; loi " + failures;
    }
}
