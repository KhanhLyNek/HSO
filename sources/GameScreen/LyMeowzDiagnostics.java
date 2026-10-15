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
        long minutes = startedAt > 0 ? (System.currentTimeMillis() - startedAt) / 60000L : 0L;
        events[nextEvent] = "[" + minutes + "m] " + message;
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

    /** Offset 0 is newest; recent events survive only for the active session. */
    public String recentEvent(int offset) {
        if (offset < 0 || offset >= eventCount) return null;
        return events[(nextEvent + LOG_LIMIT - 1 - offset) % LOG_LIMIT];
    }

    public String latestEvent() {
        String latest = recentEvent(0);
        return latest == null ? "Chua co su kien" : latest;
    }

    public String summary(long now) {
        return "LyMeowz: " + rate(now) + "; quai NV " + questKills +
                "; qua map " + mapHops + "; chet " + deaths +
                "; loi " + failures;
    }
}
