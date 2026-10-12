package Main;

import javax.microedition.lcdui.Graphics;
import Main.ChildLocationDiagnostics;

/** Offline UI diagnostics only: never sends packets or changes gameplay. */
public final class TestMenu {
    private static boolean open;
    private static boolean testMode;
    private static boolean autoDemo;
    private static int selected;
    private static long lastStep;
    private static int demoStep;
    private TestMenu() {}

    public static boolean isOpen() { return open; }
    public static boolean isTestMode() { return testMode; }

    public static boolean handleKey(int key) {
        if (key == 42) { open = !open; return true; }
        if (!open) return false;
        if (key == 50 || key == -1) selected = (selected + 2) % 3;
        else if (key == 56 || key == -2) selected = (selected + 1) % 3;
        else if (key == 53 || key == -5) {
            if (selected == 0) testMode = !testMode;
            else if (selected == 1) { autoDemo = !autoDemo; lastStep = 0; demoStep = 0; }
            else open = false;
        } else if (key == 48 || key == -7) open = false;
        return true;
    }

    public static void draw(Graphics g, int width, int height) {
        if (!open || g == null) return;
        if (autoDemo) {
            long now = System.currentTimeMillis();
            if (lastStep == 0 || now - lastStep >= 1000L) {
                demoStep = (demoStep + 1) % 10;
                lastStep = now;
            }
        }
        int oldColor = g.getColor();
        int oldX = g.getClipX(), oldY = g.getClipY();
        int oldW = g.getClipWidth(), oldH = g.getClipHeight();
        g.setClip(0, 0, width, height);
        int w = Math.min(194, width - 4);
        int h = 168;
        int x = (width - w) / 2;
        int y = Math.max(2, (height - h) / 2);
        g.setColor(0x142032);
        g.fillRect(x, y, w, h);
        g.setColor(0x57D5FF);
        g.drawRect(x, y, w - 1, h - 1);
        g.drawString("HSO TEST MENU", x + 8, y + 5, Graphics.TOP | Graphics.LEFT);
        String[] labels = {
            "Test: " + (testMode ? "ON" : "OFF"),
            "Auto UI demo: " + (autoDemo ? "ON" : "OFF"),
            "Close"
        };
        for (int i = 0; i < labels.length; i++) {
            g.setColor(selected == i ? 0x57D5FF : 0xFFFFFF);
            g.drawString((selected == i ? "> " : "  ") + labels[i],
                    x + 8, y + 25 + i * 20, Graphics.TOP | Graphics.LEFT);
        }
        g.setColor(0xB8C6D6);
        g.drawString(autoDemo ? "UI tick: " + demoStep : "2/8 Move  5 Select",
                x + 8, y + 92, Graphics.TOP | Graphics.LEFT);
        g.setColor(0xB8C6D6);
        g.drawString("Map: " + ChildLocationDiagnostics.getMapId() + " Khu: " + ChildLocationDiagnostics.getArea(),
                x + 8, y + 110, Graphics.TOP | Graphics.LEFT);
        g.drawString("Region notices: " + ChildLocationDiagnostics.getRegionNoticeCount(),
                x + 8, y + 129, Graphics.TOP | Graphics.LEFT);
        g.drawString("Child location: unverified",
                x + 8, y + 146, Graphics.TOP | Graphics.LEFT);
        g.setClip(oldX, oldY, oldW, oldH);
        g.setColor(oldColor);
    }
}
