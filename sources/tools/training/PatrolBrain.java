package tools.training;

/**
 * Tiny adaptive waypoint learner for CLDC/MIDP (fixed arrays, no allocations
 * during scoring). Values reflect monsters actually seen near a visited spot;
 * unknown spots receive a modest exploration bonus.
 */
public final class PatrolBrain {
    private final int[] density;
    private final boolean[] known;
    private final int[] obstructionPenalty;
    private final long[] lastVisit;

    public PatrolBrain(int capacity) {
        if (capacity < 2 || capacity > 16)
            throw new IllegalArgumentException("capacity");
        density = new int[capacity];
        known = new boolean[capacity];
        obstructionPenalty = new int[capacity];
        lastVisit = new long[capacity];
    }

    public void clear() {
        for (int i = 0; i < density.length; i++) {
            density[i] = 0;
            known[i] = false;
            obstructionPenalty[i] = 0;
            lastVisit[i] = 0L;
        }
    }

    public void observe(int index, int nearbyMonsters) {
        if (index < 0 || index >= density.length) return;
        if (nearbyMonsters < 0) nearbyMonsters = 0;
        if (nearbyMonsters > 8) nearbyMonsters = 8;
        int raw = nearbyMonsters * 80;
        density[index] = known[index] ? (density[index] * 3 + raw) / 4 : raw;
        known[index] = true;
        if (obstructionPenalty[index] > 0)
            obstructionPenalty[index] /= 2;
    }

    public void markVisit(int index, long now) {
        if (index >= 0 && index < lastVisit.length) lastVisit[index] = now;
    }

    public void markObstacle(int index) {
        if (index >= 0 && index < obstructionPenalty.length)
            obstructionPenalty[index] = Math.min(900, obstructionPenalty[index] + 300);
    }

    public int next(int current, int count, long now) {
        if (count <= 1 || count > density.length) return 0;
        if (current < 0 || current >= count) current = 0;
        int selected = (current + 1) % count;
        int highest = Integer.MIN_VALUE;
        for (int step = 1; step < count; step++) {
            int index = (current + step) % count;
            int score = (known[index] ? density[index] : 125)
                    - obstructionPenalty[index] - step * 6;
            if (lastVisit[index] > 0 && now >= lastVisit[index] &&
                    now - lastVisit[index] < 8000L) {
                score -= 160;
            }
            if (score > highest) {
                highest = score;
                selected = index;
            }
        }
        return selected;
    }

    public int getDensity(int index) {
        return index < 0 || index >= density.length ? 0 : density[index];
    }
}
