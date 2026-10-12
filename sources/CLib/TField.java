package CLib;

import GameEffect.EffectSkill;
import GameObjects.Monstervantieu;
import GameScreen.GameScreen;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.MsgChat;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import com.silverknight.TemMidlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:CLib/TField.class */
public class TField extends AvMain {
    public static final byte KEY_LEFT = 14;
    public static final byte KEY_RIGHT = 15;
    public static final byte KEY_CLEAR = 19;
    public int x;
    public int y;
    public int width;
    public int widthTouch;
    public int height;
    public boolean isFocus;
    public boolean lockArrow;
    public boolean paintFocus;
    public boolean isChangeFocus;
    public static final int CARET_WIDTH = 1;
    public static final int CARET_SHOWING_TIME = 5;
    public static final int TEXT_GAP_X = 5;
    public static final int MAX_SHOW_CARET_COUNER = 10;
    public static final int INPUT_TYPE_ANY = 0;
    public static final int INPUT_TYPE_NUMERIC = 1;
    public static final int INPUT_TYPE_PASSWORD = 2;
    public static final int INPUT_ALPHA_NUMBER_ONLY = 3;
    private String text;
    private String passwordText;
    public String paintedText;
    public int caretPos;
    public int counter;
    private int maxTextLenght;
    public int offsetX;
    public int keyInActiveState;
    private int indexOfActiveChar;
    public int showCaretCounter;
    private int inputType;
    public static boolean isQwerty;
    public static int typingModeAreaWidth;
    public static final byte abc = 0;
    public static final byte Abc = 1;
    public static final byte ABC = 2;
    public static final byte number123 = 3;
    public static int timeChangeMode;
    public static final int NOKIA = 0;
    public static final int MOTO = 1;
    public static final int ORTHER = 2;
    public static final int BB = 3;
    public static int changeDau;
    public iCommand cmdClear;
    public String sDefaust;
    public String title;
    public String strnull;
    public static int xDu;
    public static int yDu;
    int xCamText;
    public boolean isCloseKey;
    private int indexDau;
    private int indexTemplate;
    private int indexCong;
    private long timeDau;
    int timeFocus;
    boolean openVirtual;
    public static int typeXpeed = 1;
    public static final int[] MAX_TIME_TO_CONFIRM_KEY = {18, 14, 11, 9, 6, 4, 2};
    public static int CARET_HEIGHT = 0;
    public static String[] print = {" 0", ".,@?!_1\"/$-():*+<=>;%&~#%^&*{}[];'/1", "abc2âă", "def3đê", "ghi4", "jkl5", "mno6ôơ", "pqrs7", "tuv8ư", "wxyz9", "*", "#"};
    private static String[] printA = {"0", "1", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9", "0", "0"};
    private static String[] printBB = {" 0", "er1", "ty2", "ui3", "df4", "gh5", "jk6", "cv7", "bn8", "m9", "0", "0", "qw!", "as?", "zx", "op.", "l,"};
    private static int lastKey = -1984;
    public static int mode = 0;
    public static final String[] modeNotify = {"abc", "Abc", "ABC", "123"};
    public static int changeModeKey = 11;
    public static boolean isOpenTextBox = false;
    private static String printDau = "aáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵ";
    public static int[][] BBKEY = {new int[]{32, 48}, new int[]{49, 69}, new int[]{50, 84}, new int[]{51, 85}, new int[]{52, 68}, new int[]{53, 71}, new int[]{54, 74}, new int[]{55, 67}, new int[]{56, 66}, new int[]{57, 77}, new int[]{42, Monstervantieu.ID_TEMPLATE_LUA_DEN}, new int[]{35, 137}, new int[]{33, 113}, new int[]{63, 97}, new int[]{64, 121, 122}, new int[]{46, EffectSkill.EFF_SUNG_NGU}, new int[]{44, 108}};

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case 0:
                if (this.isFocus) {
                    clear();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void setVendorTypeMode(int i) {
        if (i == 1) {
            print[0] = "0";
            print[10] = " *";
            print[11] = "#";
            changeModeKey = 35;
            changeDau = 42;
            return;
        }
        if (i == 0) {
            print[0] = " 0";
            print[10] = "*";
            print[11] = "#";
            changeModeKey = 35;
            changeDau = 42;
            return;
        }
        if (i == 2) {
            print[0] = "0";
            print[10] = "*";
            print[11] = " #";
            changeModeKey = 42;
            changeDau = 35;
        }
    }

    public void setFocus(boolean z) {
        if (this.isFocus != z) {
            mode = 0;
        }
        lastKey = -1984;
        timeChangeMode = GameCanvas.getSecond();
        this.isFocus = z;
    }

    public iCommand setCmdClear() {
        return this.cmdClear;
    }

    public static int getHeight() {
        if (GameCanvas.isTouch) {
            return 28;
        }
        return 20;
    }

    private void init() {
        CARET_HEIGHT = mFont.tahoma_7_black.getHeight() + 1;
        this.cmdClear = new iCommand(T.del, 0, this);
    }

    public static void setQwerty(boolean z) {
        isQwerty = z;
        typingModeAreaWidth = mFont.tahoma_7_black.getWidth("ABC") + 5;
        if (z) {
            typingModeAreaWidth = 0;
        }
    }

    public TField() {
        this.widthTouch = 0;
        this.lockArrow = false;
        this.paintFocus = true;
        this.isChangeFocus = true;
        this.text = "";
        this.passwordText = "";
        this.paintedText = "";
        this.caretPos = 0;
        this.counter = 0;
        this.maxTextLenght = 500;
        this.offsetX = 0;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.showCaretCounter = 10;
        this.inputType = 0;
        this.sDefaust = "";
        this.title = "";
        this.strnull = "";
        this.xCamText = 0;
        this.isCloseKey = true;
        this.indexDau = -1;
        this.indexTemplate = 0;
        this.indexCong = 0;
        this.timeDau = 0L;
        this.timeFocus = 0;
        this.openVirtual = false;
        this.text = "";
        init();
        setFocus(false);
        setheightText();
    }

    public TField(int i, int i2) {
        this.widthTouch = 0;
        this.lockArrow = false;
        this.paintFocus = true;
        this.isChangeFocus = true;
        this.text = "";
        this.passwordText = "";
        this.paintedText = "";
        this.caretPos = 0;
        this.counter = 0;
        this.maxTextLenght = 500;
        this.offsetX = 0;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.showCaretCounter = 10;
        this.inputType = 0;
        this.sDefaust = "";
        this.title = "";
        this.strnull = "";
        this.xCamText = 0;
        this.isCloseKey = true;
        this.indexDau = -1;
        this.indexTemplate = 0;
        this.indexCong = 0;
        this.timeDau = 0L;
        this.timeFocus = 0;
        this.openVirtual = false;
        this.text = "";
        this.x = i;
        this.y = i2;
        init();
        setFocus(false);
        setheightText();
    }

    public TField(int i, int i2, int i3) {
        this.widthTouch = 0;
        this.lockArrow = false;
        this.paintFocus = true;
        this.isChangeFocus = true;
        this.text = "";
        this.passwordText = "";
        this.paintedText = "";
        this.caretPos = 0;
        this.counter = 0;
        this.maxTextLenght = 500;
        this.offsetX = 0;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.showCaretCounter = 10;
        this.inputType = 0;
        this.sDefaust = "";
        this.title = "";
        this.strnull = "";
        this.xCamText = 0;
        this.isCloseKey = true;
        this.indexDau = -1;
        this.indexTemplate = 0;
        this.indexCong = 0;
        this.timeDau = 0L;
        this.timeFocus = 0;
        this.openVirtual = false;
        this.text = "";
        this.x = i;
        this.y = i2;
        this.width = i3;
        this.widthTouch = 0;
        init();
        setFocus(false);
        setheightText();
    }

    public TField(int i, int i2, int i3, int i4) {
        this.widthTouch = 0;
        this.lockArrow = false;
        this.paintFocus = true;
        this.isChangeFocus = true;
        this.text = "";
        this.passwordText = "";
        this.paintedText = "";
        this.caretPos = 0;
        this.counter = 0;
        this.maxTextLenght = 500;
        this.offsetX = 0;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.showCaretCounter = 10;
        this.inputType = 0;
        this.sDefaust = "";
        this.title = "";
        this.strnull = "";
        this.xCamText = 0;
        this.isCloseKey = true;
        this.indexDau = -1;
        this.indexTemplate = 0;
        this.indexCong = 0;
        this.timeDau = 0L;
        this.timeFocus = 0;
        this.openVirtual = false;
        this.text = "";
        this.x = i;
        this.y = i2;
        this.width = i3;
        this.widthTouch = i4;
        init();
        setFocus(false);
        setheightText();
    }

    public void setheightText() {
        this.height = 20;
        if (GameCanvas.isTouch) {
            this.height = 28;
        }
    }

    public void setStringNull(String str) {
        this.strnull = str;
    }

    public void clear() {
        if (this.caretPos > 0 && this.text.length() > 0) {
            this.text = new StringBuffer().append(this.text.substring(0, this.caretPos - 1)).append(this.text.substring(this.caretPos, this.text.length())).toString();
            this.caretPos--;
            setOffset(0);
            setPasswordTest();
        }
    }

    public void setOffset(int i) {
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }
        int width = mFont.tahoma_7_black.getWidth(this.paintedText.substring(0, this.caretPos));
        if (i == -1) {
            if (width + this.offsetX < 15 && this.caretPos > 0 && this.caretPos < this.paintedText.length()) {
                this.offsetX += mFont.tahoma_7_black.getWidth(this.paintedText.substring(this.caretPos, this.caretPos + 1));
            }
        } else if (i == 1) {
            if (width + this.offsetX > this.width - 25 && this.caretPos < this.paintedText.length() && this.caretPos > 0) {
                this.offsetX -= mFont.tahoma_7_black.getWidth(this.paintedText.substring(this.caretPos - 1, this.caretPos));
            }
        } else {
            this.offsetX = -(width - (this.width - 12));
        }
        if (this.offsetX > 0) {
            this.offsetX = 0;
        } else if (this.offsetX < 0) {
            int width2 = mFont.tahoma_7_black.getWidth(this.paintedText) - (this.width - 12);
            if (this.offsetX < (-width2)) {
                this.offsetX = -width2;
            }
        }
    }

    private void keyPressedAny(int i) {
        String[] strArr;
        char charAt;
        char charAt2;
        if (GameCanvas.isBB) {
            strArr = printBB;
        } else if (this.inputType == 2 || this.inputType == 3) {
            strArr = printA;
        } else {
            strArr = print;
        }
        if (GameCanvas.isBB) {
            i = getBBKeyCode(i);
            if (i == -1) {
                return;
            }
        }
        if (i == lastKey) {
            this.indexOfActiveChar = (this.indexOfActiveChar + 1) % strArr[i - 48].length();
            char charAt3 = strArr[i - 48].charAt(this.indexOfActiveChar);
            if (mode == 0) {
                charAt2 = Character.toLowerCase(charAt3);
            } else if (mode == 1) {
                charAt2 = Character.toUpperCase(charAt3);
            } else if (mode == 2) {
                charAt2 = Character.toUpperCase(charAt3);
            } else {
                charAt2 = strArr[i - 48].charAt(strArr[i - 48].length() - 1);
            }
            String stringBuffer = new StringBuffer().append(this.text.substring(0, this.caretPos - 1)).append(charAt2).toString();
            if (this.caretPos < this.text.length()) {
                stringBuffer = new StringBuffer().append(stringBuffer).append(this.text.substring(this.caretPos, this.text.length())).toString();
            }
            this.text = stringBuffer;
            this.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
            setPasswordTest();
        } else if (this.text.length() < this.maxTextLenght) {
            if (mode == 1 && lastKey != -1984) {
                mode = 0;
            }
            this.indexOfActiveChar = 0;
            char charAt4 = strArr[i - 48].charAt(this.indexOfActiveChar);
            if (mode == 0) {
                charAt = Character.toLowerCase(charAt4);
            } else if (mode == 1) {
                charAt = Character.toUpperCase(charAt4);
            } else if (mode == 2) {
                charAt = Character.toUpperCase(charAt4);
            } else {
                charAt = strArr[i - 48].charAt(strArr[i - 48].length() - 1);
            }
            String stringBuffer2 = new StringBuffer().append(this.text.substring(0, this.caretPos)).append(charAt).toString();
            if (this.caretPos < this.text.length()) {
                stringBuffer2 = new StringBuffer().append(stringBuffer2).append(this.text.substring(this.caretPos, this.text.length())).toString();
            }
            this.text = stringBuffer2;
            this.keyInActiveState = MAX_TIME_TO_CONFIRM_KEY[typeXpeed];
            this.caretPos++;
            setPasswordTest();
            setOffset(0);
        }
        lastKey = i;
    }

    private void keyPressedAscii(int i) {
        if (((this.inputType != 2 && this.inputType != 3) || ((i >= 48 && i <= 57) || ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)))) && this.text.length() < this.maxTextLenght) {
            String stringBuffer = new StringBuffer().append(this.text.substring(0, this.caretPos)).append((char) i).toString();
            if (this.caretPos < this.text.length()) {
                stringBuffer = new StringBuffer().append(stringBuffer).append(this.text.substring(this.caretPos, this.text.length())).toString();
            }
            this.text = stringBuffer;
            this.caretPos++;
            setPasswordTest();
            setOffset(0);
        }
    }

    public static void setMode() {
        mode++;
        if (mode > 3) {
            mode = 0;
        }
        lastKey = changeModeKey;
        timeChangeMode = GameCanvas.getSecond();
    }

    public void setTypeKey(int i) {
        if (this.inputType == 0 || this.inputType == 2 || this.inputType == 3) {
            keyPressedAny(i);
        } else if (this.inputType == 1) {
            keyPressedAscii(i);
            this.keyInActiveState = 1;
        }
    }

    public boolean keyPressed(int i) {
        if (GameCanvas.isBB) {
            if (i == 8 || i == 127) {
                clear();
            }
        } else if (i == 8 || i == -8 || i == 204) {
            clear();
            return true;
        }
        if (!GameCanvas.isTouch && !GameCanvas.isBB && i >= 65 && i <= 122) {
            isQwerty = true;
            typingModeAreaWidth = 0;
            try {
                CRes.saveRMS("isQty", new byte[]{1});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (isQwerty && !GameCanvas.isBB) {
            if (i == 45) {
                if (i == lastKey && this.keyInActiveState < MAX_TIME_TO_CONFIRM_KEY[typeXpeed]) {
                    this.text = new StringBuffer().append(this.text.substring(0, this.caretPos - 1)).append('_').toString();
                    this.paintedText = this.text;
                    setPasswordTest();
                    setOffset(0);
                    lastKey = -1984;
                    return false;
                }
                lastKey = 45;
            }
            if (i >= 32) {
                keyPressedAscii(i);
                return false;
            }
        }
        if (!isQwerty && i == changeModeKey) {
            setMode();
            this.keyInActiveState = 1;
            lastKey = i;
            return false;
        }
        if (i == changeDau && this.inputType == 0) {
            setDau();
            return false;
        }
        if (i == 42) {
            i = 58;
        }
        if (i == 35) {
            i = 59;
        }
        if (GameCanvas.isBB && i >= 48) {
            if (isQwerty) {
                keyPressedAscii(i);
                this.keyInActiveState = 1;
                return true;
            }
            setTypeKey(i);
            return true;
        }
        if (i >= 48 && i <= 59) {
            setTypeKey(i);
            return true;
        }
        this.indexOfActiveChar = 0;
        lastKey = -1984;
        if (i == 14 && !this.lockArrow) {
            if (this.caretPos > 0) {
                this.caretPos--;
                setOffset(0);
                this.showCaretCounter = 10;
                return false;
            }
            return true;
        }
        if (i == 15 && !this.lockArrow) {
            if (this.caretPos < this.text.length()) {
                this.caretPos++;
                setOffset(0);
                this.showCaretCounter = 10;
                return false;
            }
            return true;
        }
        if (i == 19) {
            clear();
            return false;
        }
        lastKey = i;
        return true;
    }

    private void setDau() {
        this.timeDau = mSystem.currentTimeMillis() / 100;
        if (this.indexDau == -1) {
            for (int i = this.caretPos; i > 0; i--) {
                char charAt = this.text.charAt(i - 1);
                for (int i2 = 0; i2 < printDau.length(); i2++) {
                    if (charAt == printDau.charAt(i2)) {
                        this.indexTemplate = i2;
                        this.indexCong = 0;
                        this.indexDau = i - 1;
                        return;
                    }
                }
            }
            this.indexDau = -1;
            return;
        }
        this.indexCong++;
        if (this.indexCong >= 6) {
            this.indexCong = 0;
        }
        this.text = new StringBuffer().append(this.text.substring(0, this.indexDau)).append(printDau.substring(this.indexTemplate + this.indexCong, this.indexTemplate + this.indexCong + 1)).append(this.text.substring(this.indexDau + 1)).toString();
    }

    public void paintByList(mGraphics mgraphics) {
        boolean isFocused = isFocused();
        mFont mfont = mFont.tahoma_7_black;
        int i = 0;
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
            i = 3;
        } else {
            this.paintedText = this.text;
        }
        int i2 = 0;
        mgraphics.setColor(12621920);
        this.timeFocus++;
        if (isFocused && this.paintedText.length() > 0 && this.caretPos > 0) {
            i2 = mFont.tahoma_7_black.getWidth(this.paintedText.substring(0, this.caretPos));
        }
        AvMain.paintRectText(mgraphics, this.x, this.y, this.width, this.height + 2, isFocused);
        if (this.paintedText.length() == 0) {
            i = 0;
            this.paintedText = this.strnull;
            mfont = mFont.tahoma_7_white;
        }
        mfont.drawString(mgraphics, this.paintedText, this.x + 4, ((this.y + (this.height / 2)) - 5) + i, 0, true);
        if (isFocused && this.timeFocus % 16 > 12) {
            mgraphics.setColor(0);
            mgraphics.fillRect(this.x + 3 + i2, (this.y + (this.height / 2)) - 7, 1, 14, false);
        }
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        boolean isFocused = isFocused();
        mFont mfont = mFont.tahoma_7_black;
        int i = 0;
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
            i = 3;
        } else {
            this.paintedText = this.text;
        }
        int i2 = 0;
        mgraphics.setColor(12621920);
        this.timeFocus++;
        if (isFocused && this.paintedText.length() > 0 && this.caretPos > 0) {
            i2 = mFont.tahoma_7_black.getWidth(this.paintedText.substring(0, this.caretPos));
        }
        AvMain.paintRectText(mgraphics, this.x, this.y, this.width, this.height + 2, isFocused);
        mgraphics.setClip(this.x + 2, this.y + 2, this.width - 4, this.height - 3);
        int translateX = mgraphics.getTranslateX();
        int translateY = mgraphics.getTranslateY();
        mgraphics.translate(-this.xCamText, 0);
        if (this.paintedText.length() == 0) {
            i = 0;
            this.paintedText = this.strnull;
            mfont = mFont.tahoma_7_white;
        }
        mfont.drawString(mgraphics, this.paintedText, this.x + 4, ((this.y + (this.height / 2)) - 5) + i, 0, true);
        if (isFocused && this.timeFocus % 16 > 12) {
            mgraphics.setColor(0);
            mgraphics.fillRect(this.x + 3 + i2, (this.y + (this.height / 2)) - 7, 1, 14, false);
        }
        mgraphics.endClip();
        mgraphics.setClip(0, 0, GameCanvas.w, GameCanvas.h);
        mgraphics.endClip();
        GameCanvas.resetTrans(mgraphics);
        mgraphics.translate(translateX, translateY);
    }

    public boolean isFocused() {
        return this.isFocus;
    }

    private void setPasswordTest() {
        if (this.inputType == 2) {
            this.passwordText = "";
            for (int i = 0; i < this.text.length(); i++) {
                this.passwordText = new StringBuffer().append(this.passwordText).append("*").toString();
            }
            if (this.keyInActiveState > 0 && this.caretPos > 0) {
                this.passwordText = new StringBuffer().append(this.passwordText.substring(0, this.caretPos - 1)).append(this.text.charAt(this.caretPos - 1)).append(this.passwordText.substring(this.caretPos, this.passwordText.length())).toString();
            }
        }
    }

    public void updatePoiter() {
        setTextBox();
    }

    @Override // Model.AvMain
    public void update() {
        String str;
        if (this.isFocus) {
            if (this.inputType == 2) {
                str = this.passwordText;
            } else {
                str = this.text;
            }
            this.xCamText = ((-this.width) / 2) + (this.caretPos * 5) + 4;
            int width = (mFont.tahoma_7_black.getWidth(str) - this.width) + 8;
            if (this.xCamText > width) {
                this.xCamText = width;
            }
            if (this.xCamText < 0) {
                this.xCamText = 0;
            }
        } else {
            this.xCamText = 0;
        }
        this.counter++;
        if (this.keyInActiveState > 0) {
            this.keyInActiveState--;
            if (this.keyInActiveState == 0 || mode > 2) {
                this.indexOfActiveChar = 0;
                if (this.isFocus && mode == 1 && lastKey != changeModeKey) {
                    mode = 0;
                }
                lastKey = -1984;
                setPasswordTest();
            }
        }
        if (this.showCaretCounter > 0) {
            this.showCaretCounter--;
        }
        if (this.indexDau != -1 && (mSystem.currentTimeMillis() / 100) - this.timeDau > 5) {
            this.indexDau = -1;
        }
        if (this.isFocus) {
            if (GameCanvas.keyMyPressed[4]) {
                this.caretPos--;
                if (this.caretPos < 0) {
                    this.caretPos = 0;
                }
                setOffset(-1);
                GameCanvas.keyMyPressed[4] = false;
                return;
            }
            if (GameCanvas.keyMyPressed[6]) {
                this.caretPos++;
                if (this.caretPos > getText().length()) {
                    this.caretPos = getText().length();
                }
                setOffset(1);
                GameCanvas.keyMyPressed[6] = false;
            }
        }
    }

    public void updatepointerByList() {
        if (!this.isFocus) {
            this.isFocus = true;
            if (!isOpenTextBox) {
                isOpenTextBox = true;
                GameCanvas.isPointerSelect = false;
                doChangeToTextBox();
                return;
            }
            return;
        }
        if (!isOpenTextBox) {
            isOpenTextBox = true;
            GameCanvas.isPointerSelect = false;
            doChangeToTextBox();
        }
    }

    public void setTextBox() {
        if (GameCanvas.isPointerSelect && GameCanvas.isPoint(0, 0, GameCanvas.w, GameCanvas.h - (GameCanvas.hCommand / 2))) {
            if (GameCanvas.isPoint(this.x, this.y - 6, this.width - this.widthTouch, this.height + 12)) {
                if (!this.isFocus) {
                    this.isFocus = true;
                    if (!isOpenTextBox) {
                        isOpenTextBox = true;
                        GameCanvas.isPointerSelect = false;
                        doChangeToTextBox();
                        return;
                    }
                    return;
                }
                if (!isOpenTextBox) {
                    isOpenTextBox = true;
                    GameCanvas.isPointerSelect = false;
                    doChangeToTextBox();
                    return;
                }
                return;
            }
            if (this.isChangeFocus) {
                this.isFocus = false;
            }
        }
    }

    public void setPoiter() {
        this.isFocus = true;
        isOpenTextBox = true;
        GameCanvas.isPointerSelect = false;
        doChangeToTextBox();
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        if (str == null) {
            return;
        }
        lastKey = -1984;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.text = str;
        this.paintedText = str;
        setPasswordTest();
        this.caretPos = str.length();
        setOffset(0);
    }

    public void setMaxTextLenght(int i) {
        this.maxTextLenght = i;
    }

    public void setIputType(int i) {
        this.inputType = i;
    }

    public int getBBKeyCode(int i) {
        for (int i2 = 0; i2 < BBKEY.length; i2++) {
            for (int i3 = 0; i3 < BBKEY[i2].length; i3++) {
                if (BBKEY[i2][i3] == i) {
                    return i2 + 48;
                }
            }
        }
        return -1;
    }

    public void doChangeToTextBox() {
        TextBox textBox = new TextBox(this.title, this.sDefaust, 200, 0);
        textBox.addCommand(new Command("OK", 4, 0));
        textBox.addCommand(new Command("Cancel", 3, 0));
        textBox.setCommandListener(new CommandListener(this, textBox) { // from class: CLib.TField.1
            private final TextBox val$tb;
            private final TField this$0;

            {
                this.this$0 = this;
                this.val$tb = textBox;
            }

            public void commandAction(Command command, Displayable displayable) {
                TField.isOpenTextBox = false;
                this.this$0.isFocus = false;
                if (command.getLabel().equals("OK")) {
                    this.this$0.setText(this.val$tb.getString());
                    if (ChatTextField.isShow) {
                        if (ChatTextField.gI().tfChat.getText().length() > 0) {
                            GameScreen.player.strChatPopup = ChatTextField.gI().tfChat.getText();
                            GlobalService.gI().chatPopup(ChatTextField.gI().tfChat.getText());
                            ChatTextField.gI().tfChat.setText("");
                        }
                        ChatTextField.isShow = false;
                    } else if (GameCanvas.subDialog != null && GameCanvas.subDialog == GameCanvas.msgchat) {
                        MsgChat.cmdChat.perform();
                    }
                }
                if (command.getLabel().equals("Cancel") && ChatTextField.isShow) {
                    ChatTextField.isShow = false;
                }
                Display.getDisplay(TemMidlet.instance).setCurrent(TemMidlet.temCanvas);
                TemMidlet.temCanvas.setFullScreenMode(true);
            }
        });
        try {
            if (this.inputType == 2) {
                textBox.setConstraints(65536);
            } else if (this.inputType == 1) {
                textBox.setConstraints(2);
            } else {
                textBox.setConstraints(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textBox.setString(getText());
        textBox.setMaxSize(this.maxTextLenght);
        Display.getDisplay(TemMidlet.instance).setCurrent(textBox);
    }
}
