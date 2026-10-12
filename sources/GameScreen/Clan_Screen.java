package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mVector;
import GameObjects.MainClan;
import GameObjects.MainItem;
import GameObjects.ObjectData;
import InterfaceComponents.InputDialog;
import InterfaceComponents.ListNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.MainImage;
import Model.T;
import Model.mCamera;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/Clan_Screen.class */
public class Clan_Screen extends MainScreen {
    public static boolean isUpdateThongTin = true;
    int xbegin;
    int ybegin;
    int wScreen;
    int hScreen;
    int hTouch;
    MainClan clanShow;
    String[] mslogan;
    String[] mnoiquy;
    byte type;
    public static final byte MY_CLAN = 0;
    public static final byte VIEW_CLAN = 1;
    iCommand cmdMenu;
    iCommand cmdXinvao;
    iCommand cmdXemList;
    iCommand cmdGopXu;
    iCommand cmdGopLuong;
    iCommand cmdInfoMySeft;
    iCommand cmdChucNang;
    iCommand cmdChangeSlogan;
    iCommand cmdChangeNoiQuy;
    iCommand cmdPhongcap;
    iCommand cmdClose;
    iCommand cmdRoiClan;
    iCommand cmdChatBang;
    iCommand cmdThongBao;
    iCommand cmdInvenClan;
    InputDialog inputDialog;
    ListNew list;
    mCamera cam = new mCamera();
    public byte[] frameicon = {0, 1, 2, 1};
    public byte frameClan = 0;

    public Clan_Screen() {
        this.hTouch = 0;
        if (GameCanvas.isTouch) {
            this.hTouch = iCommand.hButtonCmd;
        }
        init(180);
    }

    public void init(int i) {
        this.wScreen = i;
        this.hScreen = i;
        if (this.hScreen > GameCanvas.h - 20) {
            this.hScreen = GameCanvas.h - 20;
        }
        if (this.wScreen > GameCanvas.w - 20) {
            this.wScreen = GameCanvas.w - 20;
        }
        this.xbegin = GameCanvas.hw - (this.wScreen / 2);
        this.ybegin = GameCanvas.hh - (this.hScreen / 2);
    }

    @Override // GameScreen.MainScreen
    public void Show(MainScreen mainScreen) {
        super.Show(mainScreen);
        GameCanvas.end_Dialog();
    }

    public void setInfoClan(MainClan mainClan, byte b) {
        this.type = b;
        this.clanShow = mainClan;
        if (this.clanShow == null) {
            this.lastScreen.Show(this.lastScreen.lastScreen);
            return;
        }
        updateShow();
        this.cmdClose = new iCommand(T.close, -1, this);
        this.cmdXemList = new iCommand(T.xemdanhsach, 2, this);
        if (b == 1) {
            this.cmdXemList.caption = T.thanhvien;
            this.left = this.cmdXemList;
        } else if (b == 0) {
            this.cmdMenu = new iCommand(T.menu, 0, this);
            this.cmdGopXu = new iCommand(new StringBuffer().append(T.gop).append(T.coin).toString(), 3, this);
            this.cmdGopLuong = new iCommand(new StringBuffer().append(T.gop).append(T.gem).toString(), 4, this);
            this.cmdInfoMySeft = new iCommand(new StringBuffer().append(T.info).append(" ").append(T.banthan).toString(), 6, this);
            this.cmdChucNang = new iCommand(T.chucnang, 7, this);
            this.cmdChangeSlogan = new iCommand(T.doiSlogan, 8, this);
            this.cmdChangeNoiQuy = new iCommand(T.doiNoiquy, 9, this);
            this.cmdPhongcap = new iCommand(T.phonghacap, 2, this);
            this.cmdRoiClan = new iCommand(T.roiclan, 11, this);
            this.cmdChatBang = new iCommand(T.chattoanbang, 12, this);
            this.cmdThongBao = new iCommand(T.doithongbao, 13, this);
            this.cmdInvenClan = new iCommand(new StringBuffer().append(T.tabhanhtrang).append(" ").append(T.clan).toString(), 14, this);
            this.left = this.cmdMenu;
        }
        this.right = this.cmdClose;
        if (GameCanvas.isTouch) {
            this.left.setPos(this.xbegin + (this.wScreen / 2), ((this.ybegin + this.wScreen) - (iCommand.hButtonCmd / 2)) - 4, PaintInfoGameScreen.fraButton, this.left.caption);
            this.right.setPos((this.xbegin + this.wScreen) - 12, this.ybegin + 10, PaintInfoGameScreen.fraCloseMenu, "");
        }
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        switch (i) {
            case -1:
                this.lastScreen.Show(this.lastScreen.lastScreen);
                return;
            case 0:
                mVector mvector = new mVector("Clan_Screen menu");
                if (this.type == 1) {
                    mvector.addElement(this.cmdXemList);
                    mvector.addElement(this.cmdXinvao);
                } else if (this.type == 0) {
                    if (GameScreen.player.myClan == null || GameScreen.player.myClan.isRemove) {
                        this.lastScreen.Show(this.lastScreen.lastScreen);
                        GameCanvas.start_Ok_Dialog(T.bankhongconclan);
                        return;
                    }
                    mvector.addElement(this.cmdXemList);
                    mvector.addElement(this.cmdInfoMySeft);
                    mvector.addElement(this.cmdChatBang);
                    if (GameScreen.player.myClan.getChucNang()) {
                        mvector.addElement(this.cmdChucNang);
                        mvector.addElement(this.cmdInvenClan);
                    }
                    mvector.addElement(this.cmdGopXu);
                    mvector.addElement(this.cmdGopLuong);
                    if (GameScreen.player.myClan.chucvu != Byte.MAX_VALUE) {
                        mvector.addElement(this.cmdRoiClan);
                    }
                }
                GameCanvas.menu2.startAt(mvector, 2, T.chucnang, false, null);
                return;
            case 1:
                GlobalService.gI().ChucNang_Clan((byte) 0, this.clanShow.IdClan);
                GameCanvas.start_Ok_Dialog(T.dagoidangky);
                return;
            case 2:
                GlobalService.gI().ChucNang_Clan((byte) 13, this.clanShow.IdClan);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                return;
            case 3:
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsoxumuongop, new iCommand(T.gop, 5, 6, this), true, new StringBuffer().append(T.gop).append(T.coin).toString());
                GameCanvas.currentDialog = this.inputDialog;
                return;
            case 4:
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapsoluongmuongop, new iCommand(T.gop, 5, 7, this), true, new StringBuffer().append(T.gop).append(T.gem).toString());
                GameCanvas.currentDialog = this.inputDialog;
                return;
            case 5:
                try {
                    int parseInt = Integer.parseInt(this.inputDialog.tfInput.getText());
                    if (parseInt > 0) {
                        GlobalService.gI().gop_Xu_Luong_Clan((byte) i2, parseInt);
                        GameCanvas.start_Ok_Dialog(T.pleaseWait);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            case 6:
                GlobalService.gI().info_Mem_Clan((byte) 14, GameScreen.player.name);
                GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                return;
            case 7:
                if (GameScreen.player.myClan == null || GameScreen.player.myClan.isRemove) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    GameCanvas.start_Ok_Dialog(T.bankhongconclan);
                    return;
                } else {
                    if (GameScreen.player.myClan.chucvu == Byte.MAX_VALUE) {
                        mVector mvector2 = new mVector("Clan_Screen menu2");
                        mvector2.addElement(this.cmdPhongcap);
                        mvector2.addElement(this.cmdChangeSlogan);
                        mvector2.addElement(this.cmdChangeNoiQuy);
                        mvector2.addElement(this.cmdThongBao);
                        GameCanvas.menu2.startAt(mvector2, 2, T.chucnang, false, null);
                        return;
                    }
                    this.cmdPhongcap.perform();
                    return;
                }
            case 8:
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapthongtindoi, new iCommand(T.change, 10, 16, this), false, new StringBuffer().append(T.change).append(" ").append(T.slogan).toString());
                GameCanvas.currentDialog = this.inputDialog;
                return;
            case 9:
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapthongtindoi, new iCommand(T.change, 10, 17, this), false, new StringBuffer().append(T.change).append(" ").append(T.noiquy).toString());
                GameCanvas.currentDialog = this.inputDialog;
                return;
            case 10:
                String text = this.inputDialog.tfInput.getText();
                if (text != null) {
                    GlobalService.gI().Change_Slo_NoiQuy_Clan((byte) i2, text);
                    GameCanvas.start_Ok_Dialog(T.pleaseWait);
                    return;
                }
                return;
            case 11:
                GameCanvas.start_Left_Dialog(T.hoiroiClan, new iCommand(T.roiclan, 15, this));
                return;
            case 12:
                GameCanvas.msgchat.addNewChat(T.tabBangHoi, "", "", (byte) 0, true);
                GameCanvas.start_Chat_Dialog();
                return;
            case 13:
                this.inputDialog = new InputDialog();
                this.inputDialog.setinfo(T.nhapthongtindoi, new iCommand(T.change, 10, 2, this), false, new StringBuffer().append(T.change).append(" ").append(T.thongbao).toString());
                GameCanvas.currentDialog = this.inputDialog;
                return;
            case 14:
                GlobalService.gI().InvenClan((byte) 21);
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                return;
            case 15:
                GlobalService.gI().Delete_Mem_Clan((byte) 18, GameScreen.player.name);
                this.lastScreen.Show(this.lastScreen.lastScreen);
                return;
            default:
                return;
        }
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void paint(mGraphics mgraphics) {
        if (this.lastScreen != null) {
            this.lastScreen.paint(mgraphics);
        }
        if (GameCanvas.currentScreen != this) {
            return;
        }
        GameCanvas.resetTrans(mgraphics);
        int i = this.ybegin;
        int i2 = this.xbegin;
        paintFormList(mgraphics, i2, i, this.wScreen, this.hScreen, T.clan);
        int i3 = i2 + 5;
        int i4 = i + GameCanvas.hCommand + 2;
        mgraphics.setClip(this.xbegin, i4, this.wScreen, ((this.hScreen - GameCanvas.hCommand) - this.hTouch) - 5);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(this.xbegin, i4, this.wScreen, ((this.hScreen - GameCanvas.hCommand) - this.hTouch) - 5);
        mgraphics.translate(0, -this.cam.yCam);
        AvMain.Font3dWhite(mgraphics, this.clanShow.name, this.xbegin + (this.wScreen / 2), i4 + 4, 2);
        int i5 = i4 + GameCanvas.hText + (GameCanvas.hText / 2);
        mFont.tahoma_7b_white.drawString(mgraphics, T.bieutuong, i3, i5, 0, true);
        MainImage imageIconClan = ObjectData.getImageIconClan(this.clanShow.IdIcon);
        if (imageIconClan.img != null) {
            if (mImage.getImageHeight(imageIconClan.img.image) / 18 == 3) {
                if (GameCanvas.gameTick % 6 == 0) {
                    int length = this.frameicon.length;
                    if (length == 0) {
                        length = 1;
                    }
                    this.frameClan = (byte) ((this.frameClan + 1) % length);
                }
                mgraphics.drawRegion(imageIconClan.img, 0, this.frameicon[this.frameClan] * 18, 18, 18, 0, i3 + 70, i5 + 6, 3, true);
            } else {
                mgraphics.drawImage(imageIconClan.img, i3 + 70, i5 + 6, 3, true);
            }
        }
        mFont.tahoma_7b_white.drawString(mgraphics, this.clanShow.shortName, i3 + 78, i5, 0, true);
        int i6 = i5 + GameCanvas.hText;
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.level).append(this.clanShow.Lv).append(" +").append(this.clanShow.ptLv / 10).append(",").append(this.clanShow.ptLv % 10).append("%").append("    ").append(T.hang).append(": ").append(this.clanShow.hang).toString(), i3, i6, 0, true);
        int i7 = i6 + GameCanvas.hText;
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.members).append(this.clanShow.numMem).append("/").append(this.clanShow.maxMem).toString(), i3, i7, 0, true);
        int i8 = i7 + GameCanvas.hText;
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.mChucVuClan[0]).append(": ").append(this.clanShow.nameThuLinh).toString(), i3, i8, 0, true);
        int i9 = i8 + GameCanvas.hText;
        for (int i10 = 0; i10 < this.mslogan.length; i10++) {
            mFont.tahoma_7_white.drawString(mgraphics, this.mslogan[i10], i3, i9, 0, true);
            i9 += GameCanvas.hText;
        }
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.quyxu).append(MainItem.getDotNumber(this.clanShow.coin)).toString(), i3, i9, 0, true);
        int i11 = i9 + GameCanvas.hText;
        mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.quyngoc).append(MainItem.getDotNumber(this.clanShow.gold)).toString(), i3, i11, 0, true);
        if (this.clanShow.mthanhtich != null) {
            for (int i12 = 0; i12 < this.clanShow.mthanhtich.length; i12++) {
                i11 += 20;
                MainImage imageIconArCheClan = ObjectData.getImageIconArCheClan(this.clanShow.mthanhtich[i12].id);
                if (imageIconArCheClan.img != null) {
                    mgraphics.drawImage(imageIconArCheClan.img, i3 + 9, i11 + 5, 3, true);
                }
                mFont.tahoma_7_yellow.drawString(mgraphics, new StringBuffer().append((int) this.clanShow.mthanhtich[i12].num).append("").toString(), i3 + 16, i11 + 4, 2, true);
                mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(": ").append(this.clanShow.mthanhtich[i12].nameThanhTich).toString(), i3 + 22, i11, 0, true);
            }
            i11 += 5;
        }
        int i13 = i11 + GameCanvas.hText;
        for (int i14 = 0; i14 < this.mnoiquy.length; i14++) {
            mFont.tahoma_7_white.drawString(mgraphics, this.mnoiquy[i14], i3, i13, 0, true);
            i13 += GameCanvas.hText;
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        if (GameCanvas.currentScreen == this && !GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.subDialog == null) {
            super.paint(mgraphics);
        }
    }

    public void updateShow() {
        if (this.clanShow.slogan.length() > 100) {
            init(220);
        } else if (this.clanShow.noiquy.length() > 100) {
            init(220);
        }
        this.mslogan = mFont.tahoma_7_white.splitFontArray(this.clanShow.slogan, this.wScreen - 10);
        this.mnoiquy = mFont.tahoma_7_white.splitFontArray(this.clanShow.noiquy, this.wScreen - 10);
        int length = GameCanvas.hCommand + 2 + ((6 + this.mnoiquy.length + this.mslogan.length) * GameCanvas.hText);
        if (this.clanShow.mthanhtich != null) {
            length += (20 * this.clanShow.mthanhtich.length) + 5;
        }
        if (length > ((this.hScreen - GameCanvas.hCommand) - this.hTouch) - 5) {
            this.list = new ListNew(this.xbegin, this.ybegin, this.wScreen, this.hScreen, 0, 0, length - (((this.hScreen - GameCanvas.hCommand) - this.hTouch) - 5));
        } else {
            this.list = new ListNew(this.xbegin, this.ybegin, this.wScreen, this.hScreen, 0, 0, 0);
        }
        this.cam.setAll(0, this.list.cmxLim, 0, 0);
        isUpdateThongTin = false;
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        if (this.lastScreen != null) {
            this.lastScreen.update();
        }
        if (isUpdateThongTin) {
            updateShow();
            isUpdateThongTin = false;
        }
        if (GameCanvas.isTouch) {
            this.list.moveCamera();
            this.cam.yCam = this.list.cmx;
        } else {
            this.cam.UpdateCamera();
        }
        super.update();
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (this.cam.yLimit > 0) {
            if (GameCanvas.keyMyHold[2]) {
                if (this.cam.yTo > 0) {
                    this.cam.yTo -= GameCanvas.hText;
                }
                if (this.cam.yTo < 0) {
                    this.cam.yTo = 0;
                }
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                if (this.cam.yTo < this.cam.yLimit) {
                    this.cam.yTo += GameCanvas.hText;
                }
                if (this.cam.yTo > this.cam.yLimit) {
                    this.cam.yTo = this.cam.yLimit;
                }
                GameCanvas.clearKeyHold(2);
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (GameCanvas.isTouch) {
            this.list.update_Pos_UP_DOWN();
        }
        super.updatePointer();
    }
}
