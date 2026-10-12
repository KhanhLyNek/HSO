package InterfaceComponents;

import CLib.mFont;
import CLib.mGraphics;
import GameObjects.Item;
import GameObjects.MainBuff;
import GameObjects.MainInfoItem;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import Main.GameCanvas;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabInfoChar.class */
public class TabInfoChar extends MainTabNew {
    int w4;
    int idSelect;
    int hmax;
    int xCamInfo;
    int hitem;
    int plusTouch;
    iCommand cmdSetPoint;
    iCommand cmdSendSetPoint;
    iCommand cmdSendSetPointOne;
    iCommand cmdHoiSendSetPoint;
    InputDialog inputDialog;
    ListNew list;
    int xplus = 30;
    int timePaintFocus = 0;
    String strInfoSmall = "";

    public TabInfoChar(String str) {
        this.hitem = 20;
        this.plusTouch = 0;
        this.typeTab = (byte) 2;
        this.nameTab = str;
        if (GameCanvas.isTouch) {
            this.hitem = 22;
        }
        if (GameCanvas.h <= 200) {
            this.hitem = 18;
        }
        this.plusTouch = this.hitem - 20;
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        this.w4 = MainTabNew.wblack / 4;
        init();
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        this.cmdSetPoint = new iCommand(T.setPoint, 0, this);
        this.cmdHoiSendSetPoint = new iCommand(T.cong, 3, this);
        this.cmdSendSetPoint = new iCommand(T.cong, 1, this);
        this.cmdSendSetPointOne = new iCommand(T.cong, 2, this);
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        int length = this.yBegin + 4 + GameCanvas.hText + (this.hitem * T.mKyNang.length) + 2;
        int length2 = MainTabNew.hblack - ((GameCanvas.hText + 2) + (this.hitem * T.mKyNang.length));
        this.hmax = ((GameScreen.player.mInfoChar.length * GameCanvas.hText) - length2) + 5;
        this.list = new ListNew(this.xBegin, length, MainTabNew.wblack, length2, 0, 0, this.hmax);
        if (MainTabNew.longwidth > 0) {
            this.list.x = MainTabNew.xlongwidth;
            this.list.y = MainTabNew.ylongwidth + MainTabNew.wOneItem;
            this.list.maxW = MainTabNew.longwidth;
            this.list.maxH = MainTabNew.hblack - MainTabNew.wOneItem;
            this.list.cmxLim = ((GameScreen.player.mInfoChar.length * GameCanvas.hText) - this.hSmall) + MainTabNew.wOneItem + 10;
        }
        if (this.hmax < 0) {
            this.hmax = 0;
        }
        MainScreen.cameraSub.setAll(0, this.list.cmxLim, 0, 0);
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            if (Player.diemTiemNang > 0) {
                this.center = this.cmdSetPoint;
            }
        }
        this.timePaintFocus = 0;
        super.init();
        if (GameCanvas.isSmallScreen) {
            for (int i = 0; i < GameScreen.player.mInfoChar.length; i++) {
                MainInfoItem mainInfoItem = GameScreen.player.mInfoChar[i];
                if (mainInfoItem.id < 23 || mainInfoItem.id > 26) {
                    this.strInfoSmall = new StringBuffer().append(this.strInfoSmall).append(Item.nameInfoItem[mainInfoItem.id]).append(": ").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value)).append("\n").toString();
                }
            }
        }
    }

    @Override // InterfaceComponents.MainTabNew
    public void backTab() {
        MainTabNew.Focus = (byte) 0;
        this.idSelect = 0;
        super.backTab();
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        short s;
        short s2;
        switch (i) {
            case -1:
                backTab();
                break;
            case 0:
                if (Player.diemTiemNang == 1) {
                    GameCanvas.start_Left_Dialog(new StringBuffer().append(T.cong1diem).append(T.mKyNang[this.idSelect]).append(T.nhandc).append(infoShow(1)).toString(), this.cmdSendSetPointOne);
                    break;
                } else if (Player.diemTiemNang > 0) {
                    this.inputDialog = new InputDialog();
                    this.inputDialog.setinfo(new StringBuffer().append(T.nhapsodiem).append(T.mKyNang[this.idSelect]).append(" (").append(T.nhohonhoacbang).append(Player.diemTiemNang).append(") ").toString(), this.cmdHoiSendSetPoint, true, T.kynang);
                    GameCanvas.currentDialog = this.inputDialog;
                    break;
                }
                break;
            case 1:
                try {
                    s2 = Short.parseShort(this.inputDialog.tfInput.getText());
                } catch (Exception e) {
                    s2 = 0;
                }
                if (s2 < 1) {
                    return;
                }
                if (s2 > Player.diemTiemNang) {
                    GameCanvas.start_Ok_Dialog(new StringBuffer().append(T.khongthecong).append(Player.diemTiemNang).toString());
                    break;
                } else {
                    GlobalService.gI().Add_Base_Skill_Point((byte) 0, (byte) this.idSelect, s2);
                    GameCanvas.end_Dialog();
                    if (GameScreen.help.setStep_Next(7, 6)) {
                        GameScreen.help.Next++;
                        GameScreen.help.setNext();
                        break;
                    }
                }
                break;
            case 2:
                GlobalService.gI().Add_Base_Skill_Point((byte) 0, (byte) this.idSelect, (short) 1);
                if (GameScreen.help.setStep_Next(7, 6)) {
                    GameScreen.help.Next++;
                }
                GameCanvas.end_Dialog();
                break;
            case 3:
                try {
                    s = Short.parseShort(this.inputDialog.tfInput.getText());
                } catch (Exception e2) {
                    s = 0;
                }
                if (s < 1) {
                    return;
                }
                if (s > Player.diemTiemNang) {
                    GameCanvas.start_Ok_Dialog(new StringBuffer().append(T.khongthecong).append(Player.diemTiemNang).toString());
                    break;
                } else {
                    GameCanvas.start_Left_Dialog(new StringBuffer().append((int) s).append(T.diemcongvao).append(T.mKyNang[this.idSelect]).append(T.nhandc).append(infoShow(s)).toString(), this.cmdSendSetPoint);
                    break;
                }
        }
        super.commandPointer(i, i2);
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        String stringBuffer;
        int i = this.yBegin + 4;
        int i2 = this.xBegin + 4;
        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.diemtiemnang).append(Player.diemTiemNang).toString(), i2, i, 0, false);
        int i3 = i + GameCanvas.hText + 2;
        for (int i4 = 0; i4 < T.mKyNang.length; i4++) {
            int i5 = ((this.xBegin + this.w4) - (this.w4 / 4)) + this.xplus;
            int i6 = i3 + (this.hitem * i4) + this.plusTouch;
            if (GameCanvas.lowGraphic) {
                MainTabNew.paintRectLowGraphic(mgraphics, (i5 + 2) - (this.plusTouch / 2), (i6 - 1) - (this.plusTouch / 2), 24 + this.plusTouch, 13 + this.plusTouch, 4);
            } else {
                mgraphics.drawRegion(MainTabNew.imgTab[12], 0, 0, 24 + this.plusTouch, 13 + this.plusTouch, 0, (i5 + 2) - (this.plusTouch / 2), (i6 - 1) - (this.plusTouch / 2), 0, false);
            }
            mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.mKyNang[i4]).append(":").toString(), (((i5 - (this.plusTouch / 2)) - this.xplus) - (this.w4 / 2)) - (GameCanvas.isSmallScreen ? 4 : 0), i6, 0, true);
            mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(Player.mTiemnang[0][i4]).append("").toString(), i5 + 14, i6, 2, true);
            if (Player.mTiemnang[1][i4] > 0) {
                mFont.tahoma_7b_blue.drawString(mgraphics, new StringBuffer().append("+").append(Player.mTiemnang[1][i4]).toString(), i5 + 26 + this.plusTouch, i6, 0, false);
            }
        }
        mgraphics.setColor(MainTabNew.color[3]);
        if (this.idSelect != 4 && ((this.timePaintFocus > 0 || !GameCanvas.isTouch) && MainTabNew.Focus == 1)) {
            mgraphics.drawRect(((((this.xBegin + this.w4) - (this.w4 / 4)) + this.xplus) + 2) - (this.plusTouch / 2), ((i3 + (this.hitem * this.idSelect)) + (this.plusTouch / 2)) - 1, 24 + this.plusTouch, 13 + this.plusTouch, false);
        }
        int length = i3 + (this.hitem * T.mKyNang.length);
        int i7 = MainTabNew.xlongwidth;
        int i8 = MainTabNew.ylongwidth + MainTabNew.wOneItem + 4;
        int i9 = MainTabNew.longwidth;
        int i10 = (this.hSmall - MainTabNew.wOneItem) - 6;
        if (MainTabNew.longwidth > 0) {
            mFont.tahoma_7b_white.drawString(mgraphics, T.info, MainTabNew.xlongwidth + (MainTabNew.longwidth / 2), MainTabNew.ylongwidth + (MainTabNew.wOneItem / 4), 2, false);
        }
        mgraphics.saveCanvas();
        mgraphics.ClipRec(i7, i8, i9, i10);
        if (MainTabNew.longwidth > 0) {
            GameCanvas.resetTrans(mgraphics);
            mgraphics.setClip(MainTabNew.xlongwidth, MainTabNew.ylongwidth + MainTabNew.wOneItem + 4, MainTabNew.longwidth, (this.hSmall - MainTabNew.wOneItem) - 6);
            mgraphics.translate(MainTabNew.xlongwidth, MainTabNew.ylongwidth + MainTabNew.wOneItem);
            mgraphics.translate(0, -MainScreen.cameraSub.yCam);
            i2 = 4;
            length = 4;
        } else {
            if (!GameCanvas.isSmallScreen && this.idSelect == 4) {
                if (MainScreen.cameraSub.yCam > 0) {
                    mgraphics.drawRegion(MainTabNew.imgTab[7], 0, 0, 13, 8, 0, (this.xBegin + MainTabNew.wblack) - 16, (length - 2) + (GameCanvas.gameTick % 3), 0, false);
                }
                if (MainScreen.cameraSub.yCam < MainScreen.cameraSub.yLimit) {
                    mgraphics.drawRegion(MainTabNew.imgTab[7], 0, 8, 13, 8, 0, (this.xBegin + MainTabNew.wblack) - 16, ((this.yBegin + MainTabNew.hblack) - 10) - (GameCanvas.gameTick % 3), 0, false);
                }
            }
            if (!GameCanvas.isSmallScreen) {
                mgraphics.setClip(this.xBegin, length - 5, MainTabNew.wblack, ((this.yBegin + MainTabNew.hblack) - length) + 3);
                int i11 = this.xBegin;
                int i12 = length - 5;
                int i13 = MainTabNew.wblack;
                int i14 = ((this.yBegin + MainTabNew.hblack) - length) + 3;
                mgraphics.translate(0, -MainScreen.cameraSub.yCam);
            }
        }
        if (!GameCanvas.isSmallScreen) {
            for (int i15 = 0; i15 < GameScreen.player.mInfoChar.length; i15++) {
                MainInfoItem mainInfoItem = GameScreen.player.mInfoChar[i15];
                if (mainInfoItem.id < 23 || mainInfoItem.id > 26) {
                    mFont mfont = mFont.tahoma_7_white;
                    mFont textColor = MainTabNew.setTextColor(Item.colorInfoItem[mainInfoItem.id]);
                    String stringBuffer2 = new StringBuffer().append(Item.nameInfoItem[mainInfoItem.id]).append(": ").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], mainInfoItem.value)).toString();
                    textColor.drawString(mgraphics, stringBuffer2, i2, length, 0, true);
                    int i16 = 0;
                    if (GameScreen.player.vecBuff != null) {
                        for (int i17 = 0; i17 < GameScreen.player.vecBuff.size(); i17++) {
                            MainBuff mainBuff = (MainBuff) GameScreen.player.vecBuff.elementAt(i17);
                            if (mainBuff.minfo != null) {
                                for (int i18 = 0; i18 < mainBuff.minfo.length; i18++) {
                                    if (mainInfoItem.id == mainBuff.minfo[i18].id) {
                                        i16 += mainBuff.minfo[i18].value;
                                    }
                                }
                            }
                        }
                    }
                    if (i16 != 0) {
                        mFont mfont2 = mFont.tahoma_7_green;
                        if (i16 > 0) {
                            stringBuffer = new StringBuffer().append(" +").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], i16)).toString();
                        } else {
                            stringBuffer = new StringBuffer().append(" ").append(Item.getPercent(Item.isPercentInfoItem[mainInfoItem.id], i16)).toString();
                            mfont2 = mFont.tahoma_7_red;
                        }
                        mfont2.drawString(mgraphics, new StringBuffer().append(" ").append(stringBuffer).toString(), i2 + mFont.tahoma_7_white.getWidth(stringBuffer2), length, 0, true);
                    }
                    length += GameCanvas.hText;
                }
            }
        } else {
            mFont.tahoma_7b_white.drawString(mgraphics, T.info, i2 + (MainTabNew.wblack / 2), length - (GameCanvas.hText / 2), 2, true);
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        if (GameCanvas.isTouch) {
            this.list.moveCamera();
            this.list.update_Pos_UP_DOWN();
            MainScreen.cameraSub.yCam = this.list.cmx;
        } else {
            MainScreen.cameraSub.UpdateCamera();
        }
        if (!GameCanvas.isTouch) {
            if (Player.diemTiemNang > 0 && this.idSelect != 4) {
                if (this.center == null) {
                    this.center = this.cmdSetPoint;
                }
            } else if (this.center != null) {
                this.center = null;
            }
        }
        if (this.timePaintFocus > 0 && GameCanvas.currentDialog == null) {
            this.timePaintFocus--;
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (MainTabNew.Focus == 1) {
            if (this.idSelect == 4) {
                if (GameCanvas.isSmallScreen) {
                    this.idSelect = 3;
                    GameCanvas.start_Show_Dialog(this.strInfoSmall, T.info);
                } else if (GameCanvas.keyMyHold[2]) {
                    this.xCamInfo -= GameCanvas.hText;
                    if (this.xCamInfo < 0) {
                        this.idSelect = 3;
                    }
                    GameCanvas.clearKeyHold(2);
                    MainScreen.cameraSub.moveCamera(0, this.xCamInfo);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.xCamInfo += GameCanvas.hText;
                    if (this.xCamInfo > this.hmax) {
                        this.xCamInfo = this.hmax;
                    }
                    GameCanvas.clearKeyHold(8);
                    MainScreen.cameraSub.moveCamera(0, this.xCamInfo);
                }
            } else {
                if (GameCanvas.keyMyHold[2]) {
                    this.idSelect--;
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    this.idSelect++;
                    GameCanvas.clearKeyHold(8);
                } else if (GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
                    MainTabNew.Focus = (byte) 0;
                    GameCanvas.clearKeyHold(4);
                    GameCanvas.clearKeyHold(6);
                }
                int length = T.mKyNang.length - 1;
                if (this.hmax > 0) {
                    length++;
                }
                this.idSelect = resetSelect(this.idSelect, length, false);
                this.xCamInfo = 0;
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (GameCanvas.isPointerSelect) {
            int i = 0;
            while (true) {
                if (i >= 4) {
                    break;
                }
                if (!GameCanvas.isPoint(((((this.xBegin + this.w4) - (this.w4 / 4)) + this.xplus) - 2) - (this.plusTouch / 2), ((((((this.yBegin + GameCanvas.hText) + 4) + (this.hitem * i)) + this.plusTouch) + 2) - 5) - (this.plusTouch / 2), 32 + this.plusTouch, 21 + this.plusTouch)) {
                    i++;
                } else {
                    this.idSelect = i;
                    this.cmdSetPoint.perform();
                    GameCanvas.isPointerSelect = false;
                    this.timePaintFocus = 10;
                    break;
                }
            }
        }
        super.updatePointer();
    }

    public String infoShow(int i) {
        String str = "";
        switch (this.idSelect) {
            case 0:
                str = new StringBuffer().append(new StringBuffer().append(str).append("\n").append(T.chimang).append(setpercent(2, i)).toString()).append("\n").append(T.tatcasatthuong).append(setpercent(20, i)).toString();
                if (GameScreen.player.clazz == 0) {
                    str = new StringBuffer().append(new StringBuffer().append(str).append("\n").append(T.satthuongvatly).append("+").append(4 * i).toString()).append("\n").append(T.satthuonglua).append("+").append(4 * i).toString();
                    break;
                } else if (GameScreen.player.clazz == 1) {
                    str = new StringBuffer().append(new StringBuffer().append(str).append("\n").append(T.satthuongvatly).append("+").append(4 * i).toString()).append("\n").append(T.satthuongdoc).append("+").append(4 * i).toString();
                    break;
                }
                break;
            case 1:
                String stringBuffer = new StringBuffer().append(new StringBuffer().append(str).append("\n").append(T.nedon).append(setpercent(2, i)).toString()).append("\n").append(T.phongthu).append(setpercent(10, i)).toString();
                if (GameScreen.player.clazz == 3) {
                    str = new StringBuffer().append(stringBuffer).append("\n").append(T.phongthu).append("+").append(22 * i).toString();
                    break;
                } else if (GameScreen.player.clazz == 1) {
                    str = new StringBuffer().append(stringBuffer).append("\n").append(T.phongthu).append("+").append(22 * i).toString();
                    break;
                } else {
                    str = new StringBuffer().append(stringBuffer).append("\n").append(T.phongthu).append("+").append(20 * i).toString();
                    break;
                }
            case 2:
                String stringBuffer2 = new StringBuffer().append(str).append("\n").append(T.phansatthuong).append(setpercent(2, i)).toString();
                if (GameScreen.player.clazz == 0) {
                    str = new StringBuffer().append(stringBuffer2).append("\n").append(T.mau).append("+").append(320 * i).toString();
                    break;
                } else if (GameScreen.player.clazz == 2) {
                    str = new StringBuffer().append(new StringBuffer().append(stringBuffer2).append("\n").append(T.mau).append("+").append(310 * i).toString()).append("\n").append(T.nangluong).append("+").append(1 * i).toString();
                    break;
                } else {
                    str = new StringBuffer().append(stringBuffer2).append("\n").append(T.mau).append("+").append(300 * i).toString();
                    if (GameScreen.player.clazz == 2) {
                        str = new StringBuffer().append(str).append("\n").append(T.khangtatcast).append(setpercent(5, i)).toString();
                        break;
                    }
                }
                break;
            case 3:
                String stringBuffer3 = new StringBuffer().append(str).append("\n").append(T.xuyengiap).append(setpercent(2, i)).toString();
                if (GameScreen.player.clazz == 2) {
                    str = new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(stringBuffer3).append("\n").append(T.nangluong).append("+").append(11 * i).toString()).append("\n").append(T.satthuongvatly).append("+").append(4 * i).toString()).append("\n").append(T.satthuongbang).append("+").append(4 * i).toString()).append("\n").append(T.satthuongvatly).append(setpercent(18, i)).toString()).append("\n").append(T.satthuongbang).append(setpercent(18, i)).toString();
                    break;
                } else if (GameScreen.player.clazz == 3) {
                    str = new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(stringBuffer3).append("\n").append(T.nangluong).append("+").append(11 * i).toString()).append("\n").append(T.satthuongvatly).append("+").append(4 * i).toString()).append("\n").append(T.satthuongdien).append("+").append(4 * i).toString()).append("\n").append(T.satthuongvatly).append(setpercent(18, i)).toString()).append("\n").append(T.satthuongdien).append(setpercent(18, i)).toString();
                    break;
                } else {
                    str = new StringBuffer().append(stringBuffer3).append("\n").append(T.nangluong).append("+").append(10 * i).toString();
                    break;
                }
        }
        return str;
    }

    public String setpercent(int i, int i2) {
        String stringBuffer;
        int i3 = i * i2;
        if (i3 % 100 == 0) {
            stringBuffer = new StringBuffer().append("+").append(i3 / 100).append("%").toString();
        } else if (i3 % 10 == 0) {
            stringBuffer = new StringBuffer().append("+").append(i3 / 100).append(".").append((i3 % 100) / 10).append("%").toString();
        } else {
            stringBuffer = new StringBuffer().append("+").append(i3 / 100).append(".").append((i3 % 100) / 10).append("").append(i3 % 10).append("%").toString();
        }
        return stringBuffer;
    }
}
