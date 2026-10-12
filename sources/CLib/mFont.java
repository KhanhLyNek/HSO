package CLib;

import java.io.DataInputStream;
import java.io.IOException;

/* loaded from: KnightOnline_v403.jar:CLib/mFont.class */
public class mFont {
    public static final int LEFT = 0;
    public static final byte RIGHT = 1;
    public static final byte CENTER = 2;
    public static final byte RED = 0;
    public static final byte YELLOW = 1;
    public static final byte GREEN = 2;
    public static final byte FATAL = 3;
    public static final byte MISS = 4;
    public static final byte ORANGE = 5;
    public static final byte ADDMONEY = 6;
    public static final byte MISS_ME = 7;
    public static final byte FATAL_ME = 8;
    private int space;
    private int height;
    private mImage imgFont;
    private String strFont;
    private int[][] fImages;
    public static String str = " 0123456789+-*='_?.,<>/[]{}!@#$%^&*():aáàảãạâấầẩẫậăắằẳẵặbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvxyýỳỷỹỵzwAÁÀẢÃẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÉÈẺẼẸÊẾỀỂỄỆFGHIÍÌỈĨỊJKLMNOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢPQRSTUÚÙỦŨỤƯỨỪỬỮỰVXYÝỲỶỸỴZW";
    public static mFont tahoma_7b_orange;
    public static mFont tahoma_7b_blue;
    public static mFont tahoma_7b_black;
    public static mFont tahoma_7b_yellow;
    public static mFont tahoma_7b_violet;
    public static mFont tahoma_7b_white;
    public static mFont tahoma_7b_green;
    public static mFont tahoma_7b_red;
    public static mFont tahoma_7b_brown;
    public static mFont tahoma_7_black;
    public static mFont tahoma_7_white;
    public static mFont tahoma_7_yellow;
    public static mFont tahoma_7_orange;
    public static mFont tahoma_7_red;
    public static mFont tahoma_7_blue;
    public static mFont tahoma_7_green;
    public static mFont tahoma_7_violet;
    public static mFont number_yellow;
    public static mFont number_red;
    public static mFont number_green;
    public static mFont number_white;
    public static mFont number_orange;
    public static mFont number_Yellow_Small;
    public static mFont tahoma_7_gray;
    String pathImage;

    public static void loadmFont() {
        tahoma_7b_orange = new mFont(str, "/mfont/tahoma_7b_orange.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_blue = new mFont(str, "/mfont/tahoma_7b_blue.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_black = new mFont(str, "/mfont/tahoma_7b_black.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_yellow = new mFont(str, "/mfont/tahoma_7b_yellow.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_violet = new mFont(str, "/mfont/tahoma_7b_violet.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_white = new mFont(str, "/mfont/tahoma_7b_white.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_green = new mFont(str, "/mfont/tahoma_7b_green.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_brown = new mFont(str, "/mfont/tahoma_7b_brown.png", "/mfont/tahoma_7b", 0);
        tahoma_7b_red = new mFont(str, "/mfont/tahoma_7b_red.png", "/mfont/tahoma_7b", 0);
        tahoma_7_black = new mFont(str, "/mfont/tahoma_7_black.png", "/mfont/tahoma_7", 0);
        tahoma_7_white = new mFont(str, "/mfont/tahoma_7_white.png", "/mfont/tahoma_7", 0);
        tahoma_7_yellow = new mFont(str, "/mfont/tahoma_7_yellow.png", "/mfont/tahoma_7", 0);
        tahoma_7_orange = new mFont(str, "/mfont/tahoma_7_orange.png", "/mfont/tahoma_7", 0);
        tahoma_7_red = new mFont(str, "/mfont/tahoma_7_red.png", "/mfont/tahoma_7", 0);
        tahoma_7_blue = new mFont(str, "/mfont/tahoma_7_blue.png", "/mfont/tahoma_7", 0);
        tahoma_7_green = new mFont(str, "/mfont/tahoma_7_green.png", "/mfont/tahoma_7", 0);
        tahoma_7_violet = new mFont(str, "/mfont/tahoma_7_violet.png", "/mfont/tahoma_7", 0);
        tahoma_7_gray = new mFont(str, "/mfont/tahoma_7_gray.png", "/mfont/tahoma_7", 0);
        number_yellow = new mFont(" 0123456789+-", "/mfont/number_yellow.png", "/mfont/number", 0);
        number_red = new mFont(" 0123456789+-", "/mfont/number_red.png", "/mfont/number", 0);
        number_green = new mFont(" 0123456789+-", "/mfont/number_green.png", "/mfont/number", 0);
        number_white = new mFont(" 0123456789+-", "/mfont/number_white.png", "/mfont/number", 0);
        number_orange = new mFont(" 0123456789+-", "/mfont/number_orange.png", "/mfont/number", 0);
        number_Yellow_Small = new mFont(" 0123456789+-", "/mfont/number_yellow.png", "/mfont/number", 0);
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [int[], int[][]] */
    public mFont(String str2, String str3, String str4, int i) {
        try {
            this.strFont = str2;
            this.space = i;
            this.pathImage = str3;
            DataInputStream dataInputStream = null;
            reloadImage();
            try {
                dataInputStream = mImage.openFile(str4);
                this.fImages = new int[dataInputStream.readShort()];
                for (int i2 = 0; i2 < this.fImages.length; i2++) {
                    this.fImages[i2] = new int[4];
                    this.fImages[i2][0] = dataInputStream.readShort();
                    this.fImages[i2][1] = dataInputStream.readShort();
                    this.fImages[i2][2] = dataInputStream.readShort();
                    this.fImages[i2][3] = dataInputStream.readShort();
                }
                setHeight(this.fImages[this.fImages.length - 1][3]);
            } catch (Exception e) {
                try {
                    dataInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            mSystem.println(new StringBuffer().append("paht data:").append(str3).toString());
        }
    }

    public void reloadImage() {
        this.imgFont = mImage.createImage(this.pathImage);
    }

    public void freeImage() {
        this.imgFont = null;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getWidth(String str2) {
        int i = 0;
        for (int i2 = 0; i2 < str2.length(); i2++) {
            int indexOf = this.strFont.indexOf(str2.charAt(i2));
            if (indexOf == -1) {
                indexOf = 0;
            }
            i += this.fImages[indexOf][2] + this.space;
        }
        return i;
    }

    public void drawString(mGraphics mgraphics, String str2, int i, int i2, int i3, boolean z) {
        int width;
        int length = str2.length();
        if (i3 == 0) {
            width = i;
        } else if (i3 == 1) {
            width = i - getWidth(str2);
        } else {
            width = i - (getWidth(str2) >> 1);
        }
        for (int i4 = 0; i4 < length; i4++) {
            int indexOf = this.strFont.indexOf(str2.charAt(i4));
            if (indexOf == -1) {
                indexOf = 0;
            }
            if (indexOf > -1) {
                mgraphics.drawRegion(this.imgFont, this.fImages[indexOf][0], this.fImages[indexOf][1], this.fImages[indexOf][2], this.fImages[indexOf][3], 0, width, i2, 20, z);
            }
            width += this.fImages[indexOf][2] + this.space;
        }
    }

    public void drawString(mGraphics mgraphics, String str2, int i, int i2, int i3, mFont mfont, boolean z) {
        int width;
        int length = str2.length();
        if (i3 == 0) {
            width = i;
        } else if (i3 == 1) {
            width = i - getWidth(str2);
        } else {
            width = i - (getWidth(str2) >> 1);
        }
        for (int i4 = 0; i4 < length; i4++) {
            int indexOf = this.strFont.indexOf(str2.charAt(i4));
            if (indexOf == -1) {
                indexOf = 0;
            }
            if (indexOf > -1) {
                mgraphics.drawRegion(mfont.imgFont, this.fImages[indexOf][0], this.fImages[indexOf][1], this.fImages[indexOf][2], this.fImages[indexOf][3], 0, width + 1, i2, 20, z);
                mgraphics.drawRegion(mfont.imgFont, this.fImages[indexOf][0], this.fImages[indexOf][1], this.fImages[indexOf][2], this.fImages[indexOf][3], 0, width, i2 + 1, 20, z);
                mgraphics.drawRegion(this.imgFont, this.fImages[indexOf][0], this.fImages[indexOf][1], this.fImages[indexOf][2], this.fImages[indexOf][3], 0, width, i2, 20, z);
            }
            width += this.fImages[indexOf][2] + this.space;
        }
    }

    public void drawString(mGraphics mgraphics, String str2, int i, int i2, int i3, mFont mfont, mFont mfont2, boolean z) {
        int width;
        int length = str2.length();
        if (i3 == 0) {
            width = i;
        } else if (i3 == 1) {
            width = i - getWidth(str2);
        } else {
            width = i - (getWidth(str2) >> 1);
        }
        for (int i4 = 0; i4 < length; i4++) {
            int indexOf = this.strFont.indexOf(str2.charAt(i4));
            if (indexOf == -1) {
                indexOf = 0;
            }
            if (indexOf > -1) {
                mgraphics.drawRegion(mfont.imgFont, this.fImages[indexOf][0], this.fImages[indexOf][1], this.fImages[indexOf][2], this.fImages[indexOf][3], 0, width + 1, i2, 20, z);
                mgraphics.drawRegion(mfont2.imgFont, this.fImages[indexOf][0], this.fImages[indexOf][1], this.fImages[indexOf][2], this.fImages[indexOf][3], 0, width, i2 + 1, 20, z);
                mgraphics.drawRegion(this.imgFont, this.fImages[indexOf][0], this.fImages[indexOf][1], this.fImages[indexOf][2], this.fImages[indexOf][3], 0, width, i2, 20, z);
            }
            width += this.fImages[indexOf][2] + this.space;
        }
    }

    public mVector splitFontVector(String str2, int i) {
        mVector mvector = new mVector("mFont lines");
        String str3 = "";
        int i2 = 0;
        while (i2 < str2.length()) {
            if (str2.charAt(i2) == '\n') {
                mvector.addElement(str3);
                str3 = "";
            } else {
                str3 = new StringBuffer().append(str3).append(str2.charAt(i2)).toString();
                if (getWidth(str3) > i) {
                    int length = str3.length() - 1;
                    while (length >= 0 && str3.charAt(length) != ' ') {
                        length--;
                    }
                    if (length < 0) {
                        length = str3.length() - 1;
                    }
                    mvector.addElement(str3.substring(0, length));
                    i2 = (i2 - (str3.length() - length)) + 1;
                    str3 = "";
                }
                if (i2 == str2.length() - 1 && !str3.trim().equals("")) {
                    mvector.addElement(str3);
                }
            }
            i2++;
        }
        return mvector;
    }

    public static String[] split(String str2, String str3) {
        mVector mvector = new mVector("mFont nodes");
        int indexOf = str2.indexOf(str3);
        while (true) {
            int i = indexOf;
            if (i < 0) {
                break;
            }
            mvector.addElement(str2.substring(0, i));
            str2 = str2.substring(i + str3.length());
            indexOf = str2.indexOf(str3);
        }
        mvector.addElement(str2);
        String[] strArr = new String[mvector.size()];
        if (mvector.size() > 0) {
            for (int i2 = 0; i2 < mvector.size(); i2++) {
                strArr[i2] = (String) mvector.elementAt(i2);
            }
        }
        return strArr;
    }

    public String splitFirst(String str2) {
        String str3 = "";
        boolean z = false;
        for (int i = 0; i < str2.length(); i++) {
            if (!z) {
                String substring = str2.substring(i, str2.length());
                if (compare(substring, " ")) {
                    str3 = new StringBuffer().append(str3).append(str2.charAt(i)).append("-").toString();
                } else {
                    str3 = new StringBuffer().append(str3).append(substring).toString();
                }
                z = true;
            } else if (str2.charAt(i) == ' ') {
                z = false;
            }
        }
        return str3;
    }

    public String[] splitFontArray(String str2, int i) {
        mVector splitFontVector = splitFontVector(str2, i);
        String[] strArr = new String[splitFontVector.size()];
        for (int i2 = 0; i2 < splitFontVector.size(); i2++) {
            strArr[i2] = splitFontVector.elementAt(i2).toString();
        }
        return strArr;
    }

    public boolean compare(String str2, String str3) {
        for (int i = 0; i < str2.length(); i++) {
            if (String.valueOf(str2.charAt(i)).equals(str3)) {
                return true;
            }
        }
        return false;
    }
}
