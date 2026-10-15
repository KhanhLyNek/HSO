package GameScreen;

/**
 * Bounded CLDC-safe decision controller. Game actions remain in GameScreen;
 * the Director only selects state transitions and emits one-shot intents.
 */
public final class LyMeowzAIDirector {
    public static final byte OFF = 0;
    public static final byte TRAIN = 1;
    public static final byte TURN_IN = 2;
    public static final byte RECOVER = 3;
    public static final byte PAUSED = 4;
    public static final int NONE = 0;
    public static final int START_TURN_IN = 1;

    private boolean enabled;
    private byte state = OFF;
    private int attemptedQuestId = -1;
    private int lastErrorCode;
    private int transitionCount;

    public void setEnabled(boolean value) {
        enabled = value;
        if (!value) {
            state = OFF;
            attemptedQuestId = -1;
        }
    }

    /** Called from the main game update thread, never from networking. */
    public int update(boolean dead, boolean navigating, boolean recoveryActive,
                      int readyQuestId) {
        if (!enabled) {
            state = OFF;
            return NONE;
        }
        if (dead || recoveryActive) {
            moveTo(RECOVER);
            return NONE;
        }
        if (navigating) {
            moveTo(TURN_IN);
            return NONE;
        }
        if (readyQuestId >= 0) {
            moveTo(TURN_IN);
            if (readyQuestId != attemptedQuestId) {
                attemptedQuestId = readyQuestId;
                return START_TURN_IN;
            }
            return NONE; // do not reopen the quest menu every frame
        }
        attemptedQuestId = -1;
        moveTo(TRAIN);
        return NONE;
    }

    public void reportError(int code) {
        lastErrorCode = code;
        moveTo(PAUSED);
    }

    private void moveTo(byte next) {
        if (state != next) {
            state = next;
            transitionCount++;
        }
    }

    public byte state() { return state; }
    public int lastErrorCode() { return lastErrorCode; }
    public int transitionCount() { return transitionCount; }
}
