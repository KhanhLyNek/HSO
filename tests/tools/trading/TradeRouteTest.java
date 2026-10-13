package tools.trading;

/** Run with: javac -d /tmp/trade-build sources/tools/trading/TradeRoute.java tests/tools/trading/TradeRouteTest.java && java -cp /tmp/trade-build tools.trading.TradeRouteTest */
public final class TradeRouteTest {
    private static void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(label);
    }

    public static void main(String[] args) {
        TradeRoute route = new TradeRoute();
        check(route.size() == 0, "starts empty");
        check(route.get(0) == null, "out of range");
        check(route.add(19, (byte) 6, 240, 320, false), "first point");
        check(route.add(19, (byte) 6, 255, 330, false), "near point");
        check(route.size() == 1, "near duplicates collapsed");
        check(route.add(19, (byte) 6, 310, 320, false), "next point");
        check(route.size() == 2, "far point stored");
        check(route.add(19, (byte) 6, 312, 321, true), "exit marker");
        check(route.size() == 2, "exit replaces last point");
        check(route.get(1).exit, "last point marked as gateway");
        check(route.add(20, (byte) 6, 60, 72, false), "map transition");
        check(route.size() == 3, "map entry stored");
        check(route.get(2).mapId == 20, "destination map id");
        check(route.get(2).area == 6, "destination zone");
        check(route.add(20, (byte) 5, 60, 72, false), "area transition");
        check(route.size() == 4, "new zone preserved");
        route.clear();
        check(route.size() == 0 && route.get(0) == null, "clear");
        for (int i = 0; i < TradeRoute.MAX_STEPS; i++) {
            check(route.add(40 + i, (byte) 6, i * 60, 50, false), "fill route");
        }
        check(route.size() == TradeRoute.MAX_STEPS, "max capacity");
        check(!route.add(999, (byte) 6, 99, 99, false), "bound enforced");
        System.out.println("TradeRouteTest: ALL PASS");
    }
}
