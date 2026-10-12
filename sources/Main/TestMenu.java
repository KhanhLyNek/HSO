package Main;

import javax.microedition.lcdui.Graphics;

/** Local-only in-game diagnostics menu. No network or save modifications. */
public final class TestMenu {
    private static boolean open;
    private static boolean testMode;
    private static int selected;
    private TestMenu() {}

    public static boolean handleKey(int key) {
        if (key == 42) { open = !open; return true; }
        if (!open) return false;
        if (key == 50 || key == -1) selected = (selected + 1) % 2;
        else if (key == 56 || key == -2) selected = (selected + 1) % 2;
        else if (key == 53 || key == -5) {
            if (selected == 0) testMode = !testMode;
            else open = false;
        } else if (key == 48 || key == -7) open = false;
        return true;
    }

    public static void draw(Graphics g, int width, int height) {
        if (!open || g == null) return;
        int oldColor = g.getColor();
        int x = Math.max(2, (width - 190) / 2);
        int w = Math.min(190, width - 4);
        int y = Math.max(2, (height - 108) / 2);
        g.setColor(0x142032);
        g.fillRect(x, y, w, 108);
        g.setColor(0x57D5FF);
        g.drawRect(x, y, w - 1, 107);
        g.drawString("HSO TEST MENU", x + 8, y + 6, Graphics.TOP | Graphics.LEFT);
        g.setColor(selected == 0 ? 0x57D5FF : 0xFFFFFF);
        g.drawString((selected == 0 ? "> " : "  ") + "Test mode: " + (testMode ? "ON" : "OFF"),
                x + 8, y + 32, Graphics.TOP | Graphics.LEFT);
        g.setColor(selected == 1 ? 0x57D5FF : 0xFFFFFF);
        g.drawString((selected == 1 ? "> " : "  ") + "Close", x + 8, y + 53, Graphics.TOP | Graphics.LEFT);
        g.setColor(0xB8C6D6);
        g.drawString("2/8 Move  5 Select", x + 8, y + 77, Graphics.TOP | Graphics.LEFT);
        g.setColor(oldColor);
    }

    public static boolean isTestMode() { return testMode; }
}
