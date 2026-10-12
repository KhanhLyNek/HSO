package GameScreen;

import CLib.mFont;
import CLib.mGraphics;
import CLib.mImage;
import CLib.mSystem;
import CLib.mVector;
import GameObjects.MainClan;
import GameObjects.MainObject;
import GameObjects.ObjectData;
import InterfaceComponents.ChatTextField;
import InterfaceComponents.ListNew;
import InterfaceComponents.iCommand;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.MainImage;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:GameScreen/List_Server.class */
public class List_Server extends MainScreen {
    public static List_Server me;
    public static mVector vecMyFriend = new mVector("List_server vecMyFriend");
    public static mVector vecArche = new mVector("List_server vecMyFriend");
    public static boolean isLoadFriend = false;
    public static final byte FRIEND = 0;
    public static final byte LIST_TOP = 1;
    public static final byte LIST_ARCHE = 2;
    public static final byte LIST_CLAN = 3;
    public static final byte LIST_MEM_CLAN = 4;
    public static final byte LIST_MEM_OTHER_CLAN = 5;
    public static final byte LIST_THI_DAU = 6;
    int idSelect;
    int xMove;
    int limitMove;
    int min;
    int max;
    int w;
    int h;
    ListNew list;
    public String nameList;
    public byte page;
    public byte typeList;
    public mVector vecListServer = new mVector("List_server vecListServer");
    int hItem = 50;
    int maxpaint = 0;
    int x = 0;
    int y = 0;
    int[][] mPosStar = {new int[]{6, 20}, new int[]{22, 18}, new int[]{38, 20}, new int[]{14, 8}, new int[]{30, 8}};
    public byte[] frameicon = {0, 1, 2, 1};
    public byte frameClan = 0;
    boolean isTran = false;
    int yCamBegin = 0;
    iCommand cmdClose = new iCommand(T.close, -1);
    iCommand cmdchat = new iCommand(T.trochuyen, 1, this);
    iCommand cmdMenuPoiter = new iCommand(T.menu, 3, this);
    iCommand cmdInfo = new iCommand(T.info, 4, this);
    iCommand prePage = new iCommand(T.vetruoc, 5, this);
    iCommand nextPage = new iCommand(T.toitruoc, 6, this);
    iCommand cmddelete = new iCommand(T.del, 0, this);
    iCommand cmdhoidelete = new iCommand(T.del, 2, this);
    iCommand cmdPhongchuc = new iCommand(T.phonghacap, 7, this);
    iCommand cmdDuoiMem = new iCommand(T.moiroiclan, 8, this);
    iCommand cmdInfoCLan = new iCommand(T.info, 10, this);
    iCommand cmdListMemClan = new iCommand(T.xemdanhsach, 11, this);
    iCommand cmdDongGopClan = new iCommand(T.donggopclan, 12, this);
    iCommand cmdUpdateFriendList = new iCommand(T.update, 13, this);
    iCommand cmdThidau = new iCommand(T.thachdau, 14, this);

    public static List_Server gI() {
        if (me == null) {
            List_Server list_Server = new List_Server();
            me = list_Server;
            return list_Server;
        }
        return me;
    }

    public void doSetCaption() {
        this.cmdClose.caption = T.close;
        this.cmdchat.caption = T.trochuyen;
        this.cmdMenuPoiter.caption = T.menu;
        this.cmdInfo.caption = T.info;
        this.prePage.caption = T.vetruoc;
        this.nextPage.caption = T.toitruoc;
        this.cmddelete.caption = T.del;
        this.cmdhoidelete.caption = T.del;
        this.cmdPhongchuc.caption = T.phonghacap;
        this.cmdDuoiMem.caption = T.moiroiclan;
        this.cmdInfoCLan.caption = T.info;
        this.cmdListMemClan.caption = T.xemdanhsach;
        this.cmdDongGopClan.caption = T.donggopclan;
        this.cmdUpdateFriendList.caption = T.update;
        this.cmdThidau.caption = T.thachdau;
    }

    @Override // GameScreen.MainScreen
    public void Show() {
        mSystem.outloi("goi ham show kia");
        Show(GameCanvas.currentScreen);
    }

    public void setSize(byte b) {
        this.typeList = b;
        this.hItem = 50;
        if (this.typeList == 2) {
            this.hItem = 70;
        }
        this.w = (GameCanvas.w / 4) * 3;
        this.h = (GameCanvas.h / 5) * 4;
        if (this.w < 160) {
            this.w = 160;
        } else if (this.w > 280) {
            this.w = 280;
        }
        if (this.h < 210) {
            this.h = 210;
        } else if (this.h > 280) {
            this.h = 280;
        }
        this.x = GameCanvas.hw - (this.w / 2);
        this.y = GameCanvas.hh - (this.h / 2);
        this.idSelect = 0;
        this.maxpaint = ((this.h - GameCanvas.hCommand) / this.hItem) + 3;
        if (this.vecListServer.size() > 0) {
            setXmove();
            MainScreen.cameraSub.setAll(0, ((this.vecListServer.size() * this.hItem) - this.h) + GameCanvas.hCommand + 10, 0, 0);
            this.list = new ListNew(this.x, this.y, this.w, this.h, this.hItem, 0, MainScreen.cameraSub.yLimit);
            this.min = 0;
            this.max = this.maxpaint;
            if (this.max > this.vecListServer.size()) {
                this.max = this.vecListServer.size();
            }
            setCmd();
        }
        if (GameCanvas.isTouch) {
            this.cmdClose.setPos((this.x + this.w) - 12, this.y + 10, PaintInfoGameScreen.fraCloseMenu, "");
        }
        this.right = this.cmdClose;
    }

    private void doMenuPhongChuc() {
        MainObject mainObject;
        if (this.idSelect < 0 || this.idSelect >= this.vecListServer.size() || (mainObject = (MainObject) this.vecListServer.elementAt(this.idSelect)) == null) {
            return;
        }
        mVector mvector = new mVector("List_server menu4");
        if (GameScreen.player.myClan.chucvu == Byte.MAX_VALUE) {
            mvector.addElement(new iCommand(T.mChucVuClan[1], 9, 126, this));
        }
        if (GameScreen.player.myClan.chucvu >= 126) {
            mvector.addElement(new iCommand(T.mChucVuClan[2], 9, 125, this));
        }
        if (GameScreen.player.myClan.chucvu >= 125) {
            mvector.addElement(new iCommand(T.mChucVuClan[3], 9, 124, this));
            mvector.addElement(new iCommand(T.mChucVuClan[4], 9, 123, this));
            mvector.addElement(new iCommand(T.mChucVuClan[5], 9, 122, this));
        }
        GameCanvas.menu2.startAt(mvector, 2, mainObject.name, false, null);
    }

    private void doMenuListClan() {
        if (this.idSelect < 0 || this.idSelect >= this.vecListServer.size() || ((MainClan) this.vecListServer.elementAt(this.idSelect)).hang == -1) {
            return;
        }
        mVector mvector = new mVector("List_server menu2");
        mvector.addElement(this.cmdInfoCLan);
        mvector.addElement(this.cmdListMemClan);
        GameCanvas.menu2.startAt(mvector, 2, T.clan, false, null);
    }

    private void doMenuListTop() {
        if (this.idSelect >= 0 && this.idSelect < this.vecListServer.size()) {
            MainObject mainObject = (MainObject) this.vecListServer.elementAt(this.idSelect);
            if (mainObject.name.compareTo(GameScreen.player.name) == 0) {
                return;
            }
            mVector mvector = new mVector("List_server menu3");
            if (this.typeList == 4) {
                if (GameScreen.player.myClan.chucvu >= 125) {
                    mvector.addElement(this.cmdPhongchuc);
                    if (GameScreen.player.myClan.chucvu >= 126) {
                        mvector.addElement(this.cmdDuoiMem);
                    }
                }
                mvector.addElement(this.cmdDongGopClan);
            }
            if (mainObject != null && mainObject.typeOnline == 1 && mainObject.hang != -1 && mainObject.name.compareTo(GameScreen.player.name) != 0) {
                mvector.addElement(this.cmdInfo);
                mvector.addElement(this.cmdchat);
            }
            GameCanvas.menu2.startAt(mvector, 2, T.chucnang, false, null);
        }
    }

    public void doMenu() {
        MainObject mainObject;
        MainObject mainObject2;
        mVector mvector = new mVector("List_server menu");
        if (this.typeList == 0) {
            mvector.addElement(this.cmdchat);
            mvector.addElement(this.cmdhoidelete);
            mvector.addElement(this.cmdUpdateFriendList);
        }
        if (this.typeList != 6 && this.idSelect >= 0 && this.idSelect < this.vecListServer.size() && (mainObject2 = (MainObject) this.vecListServer.elementAt(this.idSelect)) != null && mainObject2.typeOnline == 1 && mainObject2.hang != -1 && mainObject2.name.compareTo(GameScreen.player.name) != 0) {
            mvector.addElement(this.cmdInfo);
        }
        if (this.typeList == 6 && (mainObject = (MainObject) this.vecListServer.elementAt(this.idSelect)) != null && mainObject.hang != -1 && mainObject.name.compareTo(GameScreen.player.name) != 0) {
            mvector.addElement(this.cmdInfo);
            mvector.addElement(this.cmdThidau);
        }
        if (this.page != 99) {
            if (this.page != 0) {
                this.prePage.caption = new StringBuffer().append(T.vetruoc).append(CRes.abs(this.page)).toString();
                mvector.addElement(this.prePage);
            }
            if (this.page >= 0) {
                this.nextPage.caption = new StringBuffer().append(T.toitruoc).append(this.page + 2).toString();
                mvector.addElement(this.nextPage);
            }
        }
        GameCanvas.menu2.startAt(mvector, 2, T.friend, false, null);
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case -1:
                if (this.lastScreen == GameCanvas.AllInfo) {
                    this.lastScreen.Show(this.lastScreen.lastScreen);
                    break;
                } else {
                    this.lastScreen.Show();
                    break;
                }
        }
        super.commandTab(i, i2);
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        MainObject mainObject;
        MainObject mainObject2;
        switch (i) {
            case 0:
                GlobalService.gI().Friend((byte) 3, ((MainObject) vecMyFriend.elementAt(this.idSelect)).name);
                vecMyFriend.removeElementAt(this.idSelect);
                if (this.idSelect > 0) {
                    this.idSelect--;
                }
                if (vecMyFriend.size() == 0) {
                    this.left = null;
                    this.center = null;
                }
                GameCanvas.end_Dialog();
                return;
            case 1:
                MainObject mainObject3 = (MainObject) this.vecListServer.elementAt(this.idSelect);
                if (mainObject3 != null && mainObject3.hang != -1) {
                    GameCanvas.msgchat.addNewChat(mainObject3.name, "", "", (byte) 0, true);
                    GameCanvas.start_Chat_Dialog();
                    return;
                }
                return;
            case 2:
                GameCanvas.start_Left_Dialog(T.deleteFriend, this.cmddelete);
                return;
            case 3:
                if (this.typeList == 1 || this.typeList == 0 || this.typeList == 6) {
                    doMenu();
                    return;
                }
                if (this.typeList == 5 || this.typeList == 4) {
                    doMenuListTop();
                    return;
                } else {
                    if (this.typeList == 3) {
                        doMenuListClan();
                        return;
                    }
                    return;
                }
            case 4:
                if (this.idSelect >= 0 && this.idSelect < this.vecListServer.size() && (mainObject2 = (MainObject) this.vecListServer.elementAt(this.idSelect)) != null && mainObject2.hang != -1) {
                    if (this.typeList == 6) {
                        GlobalService.gI().Re_Info_Other_Object(mainObject2.name, Info_Other_Player.THACH_DAU_INFO);
                        return;
                    } else {
                        GlobalService.gI().Re_Info_Other_Object(mainObject2.name, Info_Other_Player.VIEW);
                        return;
                    }
                }
                return;
            case 5:
                GlobalService.gI().set_Page((byte) (CRes.abs(this.page) - 1));
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.cancel, -1));
                return;
            case 6:
                GlobalService.gI().set_Page((byte) CRes.abs(this.page + 1));
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.cancel, -1));
                return;
            case 7:
                doMenuPhongChuc();
                return;
            case 8:
                MainObject mainObject4 = (MainObject) this.vecListServer.elementAt(this.idSelect);
                if (mainObject4 != null) {
                    GlobalService.gI().Delete_Mem_Clan((byte) 18, mainObject4.name);
                    return;
                }
                return;
            case 9:
                MainObject mainObject5 = (MainObject) this.vecListServer.elementAt(this.idSelect);
                if (mainObject5 != null) {
                    GlobalService.gI().PhongCap_Clan((byte) 4, (byte) i2, mainObject5.name);
                }
                GameCanvas.start_Ok_Dialog(T.pleaseWait);
                return;
            case 10:
                MainClan mainClan = (MainClan) this.vecListServer.elementAt(this.idSelect);
                if (mainClan != null && mainClan.hang != -1) {
                    GlobalService.gI().ChucNang_Clan((byte) 15, mainClan.IdClan);
                    GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                    return;
                }
                return;
            case 11:
                MainClan mainClan2 = (MainClan) this.vecListServer.elementAt(this.idSelect);
                if (mainClan2 != null && mainClan2.hang != -1) {
                    GlobalService.gI().ChucNang_Clan((byte) 13, mainClan2.IdClan);
                    GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                    return;
                }
                return;
            case 12:
                MainObject mainObject6 = (MainObject) this.vecListServer.elementAt(this.idSelect);
                if (mainObject6 != null) {
                    GlobalService.gI().info_Mem_Clan((byte) 14, mainObject6.name);
                    GameCanvas.start_Wait_Dialog(T.danglaydulieu, new iCommand(T.close, -1));
                    return;
                }
                return;
            case 13:
                GlobalService.gI().Friend((byte) 4, "");
                GameCanvas.start_Wait_Dialog(T.pleaseWait, new iCommand(T.cancel, -1));
                return;
            case 14:
                if (this.idSelect >= 0 && this.idSelect < this.vecListServer.size() && (mainObject = (MainObject) this.vecListServer.elementAt(this.idSelect)) != null && mainObject.hang != -1) {
                    GlobalService.gI().doSendThachDau((byte) 0, mainObject.name);
                    return;
                }
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
        paintFormList(mgraphics, this.x, this.y, this.w, this.h, this.nameList);
        mgraphics.translate(this.x, this.y + GameCanvas.hCommand);
        mgraphics.setClip(3, 0, this.w, this.h - GameCanvas.hCommand);
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.saveCanvas();
        mgraphics.translateAndroid(this.x, this.y + GameCanvas.hCommand);
        mgraphics.ClipRec(3, 0, this.w, this.h - GameCanvas.hCommand);
        if (this.vecListServer == null) {
            return;
        }
        if (this.vecListServer.size() > 0) {
            if (this.typeList == 3) {
                paintTopClan(mgraphics, 5);
            } else {
                paintTopNormal(mgraphics, 5);
            }
        } else if (this.typeList == 0) {
            mFont.tahoma_7_white.drawString(mgraphics, T.nullFriend, this.w / 2, 5, 2, true);
        } else {
            mFont.tahoma_7_white.drawString(mgraphics, T.listnull, this.w / 2, 5, 2, true);
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        if (!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null && GameCanvas.currentScreen == this && !ChatTextField.isShow && GameCanvas.subDialog == null) {
            super.paint(mgraphics);
        }
    }

    private void paintTopClan(mGraphics mgraphics, int i) {
        mgraphics.setColor(AvMain.SELECTED_COLOR);
        mgraphics.fillRect(3, (-MainScreen.cameraSub.yCam) + i + (this.idSelect * this.hItem) + 1, this.w - 6, this.hItem - 1, true);
        mgraphics.translate(0, -MainScreen.cameraSub.yCam);
        int i2 = i + (this.min * this.hItem);
        for (int i3 = this.min; i3 < this.max; i3++) {
            MainClan mainClan = (MainClan) this.vecListServer.elementAt(i3);
            if (mainClan.hang == -1) {
                mFont.tahoma_7b_white.drawString(mgraphics, mainClan.shortName, 20, i2 + 5, 0, true);
                i2 += this.hItem;
            } else {
                MainImage imageIconClan = ObjectData.getImageIconClan(mainClan.IdIcon);
                if (imageIconClan.img != null) {
                    if (mImage.getImageHeight(imageIconClan.img.image) / 18 == 3) {
                        if (GameCanvas.gameTick % 6 == 0) {
                            int length = this.frameicon.length;
                            if (length == 0) {
                                length = 1;
                            }
                            this.frameClan = (byte) ((this.frameClan + 1) % length);
                        }
                        mgraphics.drawRegion(imageIconClan.img, 0, this.frameicon[this.frameClan] * 18, 18, 18, 0, 9, i2 + 11, 3, true);
                    } else {
                        mgraphics.drawImage(imageIconClan.img, 9, i2 + 11, 3, true);
                    }
                }
                if (GameScreen.player.myClan != null && GameScreen.player.myClan.shortName.compareTo(mainClan.shortName) == 0) {
                    AvMain.Font3dWhite(mgraphics, new StringBuffer().append(mainClan.shortName).append(" - ").append(mainClan.name).toString(), 20, i2 + 5, 0);
                } else {
                    mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(mainClan.shortName).append(" - ").append(mainClan.name).toString(), 20, i2 + 5, 0, true);
                }
                mFont.tahoma_7_white.drawString(mgraphics, mainClan.slogan, 10, 20 + i2, 0, true);
                if (mainClan.hang < 4) {
                    mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.hang).append(" ").append(T.mhang[mainClan.hang]).toString(), 10, 35 + i2, 0, true);
                } else {
                    mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.hang).append(" ").append(mainClan.hang + 1).toString(), 10, 35 + i2, 0, true);
                }
                i2 += this.hItem;
                if (i3 < this.vecListServer.size() - 1) {
                    mgraphics.setColor(AvMain.color[4]);
                    mgraphics.fillRect(4, i2, this.w - 8, 1, true);
                }
            }
        }
    }

    public void paintTopNormal(mGraphics mgraphics, int i) {
        mgraphics.setColor(AvMain.SELECTED_COLOR);
        mgraphics.fillRect(3, (-MainScreen.cameraSub.yCam) + i + (this.idSelect * this.hItem) + 1, this.w - 6, this.hItem - 1, true);
        mgraphics.translate(0, -MainScreen.cameraSub.yCam);
        int i2 = i + (this.min * this.hItem);
        for (int i3 = this.min; i3 < this.max; i3++) {
            if (i3 >= 0 && i3 < this.vecListServer.size()) {
                MainObject mainObject = (MainObject) this.vecListServer.elementAt(i3);
                if (mainObject.hang == -1) {
                    mainObject.paintNameShow(mgraphics, 50, 5 + i2, true);
                    i2 += this.hItem;
                    if (i3 < this.vecListServer.size() - 1) {
                        mgraphics.setColor(AvMain.color[4]);
                        mgraphics.fillRect(4, i2, this.w - 8, 1, true);
                    }
                } else {
                    mainObject.paintShowPlayer(mgraphics, 20, 40 + i2, 0);
                    paintIconOnline(mgraphics, mainObject.typeOnline, 40, 10 + i2);
                    if (mainObject.name.compareTo(GameScreen.player.name) == 0) {
                        String nameAndClan = mainObject.getNameAndClan(" - ");
                        int i4 = 0;
                        if (mainObject.myClan != null) {
                            i4 = 16;
                            mainObject.paintIconClan(mgraphics, (50 + 16) - 7, 5 + i2 + 6, -1);
                        }
                        AvMain.FontBorderColor(mgraphics, nameAndClan, 50 + i4, 5 + i2, 0, 0);
                    } else {
                        mainObject.paintNameShow(mgraphics, 50, 5 + i2, true);
                    }
                    int i5 = 40;
                    if (i3 == this.idSelect) {
                        mgraphics.setClip(35, MainScreen.cameraSub.yCam, this.w - 40, this.h - GameCanvas.hCommand);
                        i5 = 40 - this.xMove;
                    }
                    if (this.typeList == 6) {
                        if (mainObject.hang < 4) {
                            mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.hang).append(" ").append(T.mhang[mainObject.hang]).toString(), i5, 35 + i2, 0, true);
                        } else {
                            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.hang).append(" ").append(mainObject.hang + 1).toString(), i5, 35 + i2, 0, true);
                        }
                        mFont.tahoma_7_white.drawString(mgraphics, mainObject.infoObject, i5, 20 + i2, 0, true);
                    } else {
                        if (this.typeList == 4 || this.typeList == 5) {
                            mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.level).append((int) mainObject.Lv).toString(), i5, 20 + i2, 0, true);
                            mFont.tahoma_7_white.drawString(mgraphics, MainClan.getNameChucVu(mainObject.myClan.chucvu), i5, 35 + i2, 0, true);
                        } else {
                            mFont.tahoma_7_white.drawString(mgraphics, mainObject.infoObject, i5, 20 + i2, 0, true);
                        }
                        if (this.typeList == 1) {
                            if (mainObject.hang < 4) {
                                mFont.tahoma_7b_white.drawString(mgraphics, new StringBuffer().append(T.hang).append(" ").append(T.mhang[mainObject.hang]).toString(), i5, 35 + i2, 0, true);
                            } else {
                                mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.hang).append(" ").append(mainObject.hang + 1).toString(), i5, 35 + i2, 0, true);
                            }
                        }
                    }
                    mgraphics.endClip();
                    if (i3 == this.idSelect) {
                        mgraphics.setClip(5, MainScreen.cameraSub.yCam, this.w - 10, this.h - GameCanvas.hCommand);
                    }
                    i2 += this.hItem;
                    if (i3 < this.vecListServer.size() - 1) {
                        mgraphics.setColor(AvMain.color[4]);
                        mgraphics.fillRect(4, i2, this.w - 8, 1, true);
                    }
                    mgraphics.endClip();
                }
            }
        }
    }

    public void paintAr(mGraphics mgraphics, int i, int i2, int i3, int i4) {
        mgraphics.setColor(0);
        mgraphics.fillRect(i3, i4 + 1, 62, 7, true);
        mgraphics.fillRect(i3 + 1, i4, 60, 1, true);
        mgraphics.fillRect(i3 + 1, i4 + 8, 60, 1, true);
        if (i2 > 0) {
            int i5 = (i2 * 60) / i;
            if (i5 <= 0) {
                i5 = 1;
            } else if (i5 > 60) {
                i5 = 60;
            }
            mgraphics.setColor(-14436849);
            mgraphics.fillRect(i3 + 1, i4, i5, 1, true);
        }
        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(i2).append("/").append(i).toString(), i3 + 31, i4 + 4, 2, true);
    }

    private void paintIconOnline(mGraphics mgraphics, byte b, int i, int i2) {
        int i3 = 0;
        if (b == 0) {
            i3 = 2;
        }
        PaintInfoGameScreen.fraStatusArea.drawFrame(i3, i, i2, 0, 3, mgraphics);
    }

    @Override // GameScreen.MainScreen, Model.AvMain
    public void update() {
        this.lastScreen.update();
        if (this.limitMove > 0) {
            this.xMove += 2;
            if (this.xMove > this.limitMove) {
                this.xMove = 0;
            }
        }
        if (this.vecListServer.size() > 0) {
            if (GameCanvas.isTouch && this.list != null) {
                this.list.moveCamera();
            } else {
                MainScreen.cameraSub.UpdateCamera();
            }
            if (MainScreen.cameraSub.yCam != MainScreen.cameraSub.yTo) {
                setMinMax();
                return;
            }
            return;
        }
        if (this.center != null || this.left != null) {
            this.center = null;
            this.left = null;
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        if (this.vecListServer.size() > 0) {
            int i = this.idSelect;
            if (GameCanvas.keyMyHold[2]) {
                this.idSelect--;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                this.idSelect++;
                GameCanvas.clearKeyHold(8);
            }
            this.idSelect = resetSelect(this.idSelect, this.vecListServer.size() - 1, false);
            if (i != this.idSelect) {
                setXmove();
                MainScreen.cameraSub.moveCamera(0, ((this.idSelect * this.hItem) - (this.h / 2)) + 40 + GameCanvas.hCommand);
            }
        }
        super.updatekey();
    }

    @Override // Model.AvMain
    public void updatePointer() {
        if (this.vecListServer.size() > 0) {
            if (GameCanvas.isPointSelect(this.x, this.y + GameCanvas.hCommand, this.w, this.h - GameCanvas.hCommand)) {
                int i = (((MainScreen.cameraSub.yCam + GameCanvas.py) - this.y) - GameCanvas.hCommand) / this.hItem;
                if (i >= 0 && i < this.vecListServer.size()) {
                    GameCanvas.isPointerSelect = false;
                    if (i == this.idSelect) {
                        this.cmdMenuPoiter.perform();
                    } else {
                        this.idSelect = i;
                        setXmove();
                    }
                } else {
                    this.idSelect = 0;
                }
                GameCanvas.isPointerSelect = false;
            }
            if (GameCanvas.isTouch && this.list != null) {
                this.list.update_Pos_UP_DOWN();
                MainScreen.cameraSub.yCam = this.list.cmx;
            }
        }
        super.updatePointer();
    }

    public void setMinMax() {
        this.min = (MainScreen.cameraSub.yCam / this.hItem) - 1;
        if (this.min < 0) {
            this.min = 0;
        }
        this.max = this.min + this.maxpaint;
        if (this.max > this.vecListServer.size()) {
            this.max = this.vecListServer.size();
            this.min = this.max - this.maxpaint;
            if (this.min < 0) {
                this.min = 0;
            }
        }
    }

    public void setXmove() {
        if (this.vecListServer == null || this.idSelect == -1 || this.idSelect > this.vecListServer.size() - 1) {
            return;
        }
        this.xMove = 0;
        String str = "";
        int i = 40;
        if (this.typeList == 3) {
            MainClan mainClan = (MainClan) this.vecListServer.elementAt(this.idSelect);
            if (mainClan.hang != -1) {
                str = mainClan.slogan;
            }
            i = 20;
        } else {
            str = ((MainObject) this.vecListServer.elementAt(this.idSelect)).infoObject;
        }
        if (this.typeList == 2) {
            i = 50;
        }
        this.limitMove = (mFont.tahoma_7_black.getWidth(str) - (this.w - i)) + 5;
        if (this.limitMove > 0) {
            this.limitMove += 25;
        }
    }

    public void setCmd() {
        if (this.vecListServer.size() > 0 && !GameCanvas.isTouch) {
            this.left = this.cmdMenuPoiter;
            if (this.typeList == 0) {
                this.center = this.cmdchat;
            }
        }
    }

    public void updateList() {
        MainScreen.cameraSub.setAll(0, ((this.vecListServer.size() * this.hItem) - this.h) + GameCanvas.hCommand + 10, 0, 0);
        this.list = new ListNew(this.x, this.y, this.w, this.h, this.hItem, 0, MainScreen.cameraSub.yLimit);
    }
}
