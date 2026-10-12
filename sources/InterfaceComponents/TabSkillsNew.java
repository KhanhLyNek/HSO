package InterfaceComponents;

import CLib.TField;
import CLib.mFont;
import CLib.mGraphics;
import CLib.mSound;
import CLib.mSystem;
import CLib.mVector;
import GameEffect.EffectEnd;
import GameEffect.EffectSkill;
import GameEffect.MainEffect;
import GameObjects.MainItem;
import GameObjects.Monstervantieu;
import GameObjects.Player;
import GameScreen.GameScreen;
import GameScreen.MainScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.TabScreenNew;
import Main.GameCanvas;
import Model.AvMain;
import Model.CRes;
import Model.T;
import Skill.HotKey;
import Skill.Skill;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/TabSkillsNew.class */
public class TabSkillsNew extends MainTabNew {
    public static int[][] XYpaint;
    int idSelect;
    int hcmd;
    iCommand cmdSetPoint;
    iCommand cmdSendSetPoint;
    iCommand cmdSendSetPointOne;
    iCommand cmdSetKey;
    iCommand cmdMenu;
    iCommand cmdupskill;
    InputDialog inputDialog;
    ListNew list;
    mVector vecListCmd;
    public static mVector vecPaintSkill = new mVector("TabSkillsNew vecPaintSkill");
    int w8;
    int sizeKill = 22;
    boolean isSaveSkill = false;
    public mVector vecEffRe = new mVector();
    int[] stylePaint = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1};
    int hKill = 4;

    public TabSkillsNew(String str) {
        this.typeTab = (byte) 3;
        this.nameTab = str;
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3) + ((MainTabNew.wblack % 8) / 2);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        XYpaint = mSystem.new_M_Int(Player.MaxSkill, 2);
        int i = this.yBegin + (MainTabNew.wOneItem / 2) + GameCanvas.hText;
        for (int i2 = 0; i2 < Player.MaxSkill; i2++) {
            if (i2 == 0) {
                XYpaint[i2][0] = this.xBegin + (MainTabNew.wblack / 2);
                XYpaint[i2][1] = i;
                i += this.sizeKill * 2;
            } else {
                XYpaint[i2][1] = i;
                if (i2 % 2 == 1) {
                    XYpaint[i2][0] = this.xBegin + (MainTabNew.wblack / 4);
                } else {
                    XYpaint[i2][0] = this.xBegin + ((MainTabNew.wblack / 4) * 3);
                    i += this.sizeKill * 2;
                }
            }
        }
        init();
        this.cmdBack = new iCommand(T.back, -1, this);
        if (GameCanvas.isTouch) {
            this.cmdBack.caption = T.close;
        }
        this.cmdSetPoint = new iCommand(T.setPoint, 0, this);
        this.cmdSendSetPoint = new iCommand(T.cong, 1, this);
        this.cmdSendSetPointOne = new iCommand(T.cong, 2, this);
        this.cmdSetKey = new iCommand(T.setKey, 3, this);
        this.cmdMenu = new iCommand(T.select, 5, this);
        this.cmdupskill = new iCommand(T.nangcap, 6, this);
        this.w8 = MainTabNew.wblack / 8;
        this.vecEffRe.removeAllElements();
    }

    @Override // InterfaceComponents.MainTabNew
    public void init() {
        MainTabNew.timePaintInfo = 0;
        MainScreen.cameraSub.setAll(0, (((XYpaint[Player.MaxSkill - 1][1] - this.yBegin) + this.sizeKill) - MainTabNew.hblack) + 5, 0, 0);
        this.list = new ListNew(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, (MainTabNew.hblack - GameCanvas.hText) - 2, 0, 0, MainScreen.cameraSub.yLimit);
        if (!GameCanvas.isTouch) {
            this.right = this.cmdBack;
            this.center = this.cmdMenu;
        }
        if (GameCanvas.isTouch) {
            this.idSelect = -1;
        } else {
            this.idSelect = 0;
        }
        this.listContent = null;
    }

    @Override // InterfaceComponents.MainTabNew
    public void backTab() {
        if (this.isSaveSkill) {
            saveSkill();
            this.isSaveSkill = false;
        }
        MainTabNew.timePaintInfo = 0;
        MainTabNew.Focus = (byte) 0;
        if (GameCanvas.isTouch) {
            this.idSelect = -1;
        } else {
            this.idSelect = 0;
        }
        super.backTab();
    }

    @Override // Model.AvMain
    public void commandPointer(int i, int i2) {
        iCommand icommand;
        short s;
        if ((this.idSelect == -1 || this.idSelect > vecPaintSkill.size() - 1) && i != -1) {
            return;
        }
        switch (i) {
            case -1:
                backTab();
                break;
            case 0:
                Skill skill = (Skill) vecPaintSkill.elementAt(this.idSelect);
                if (skill.lvMin > GameScreen.player.Lv) {
                    GameCanvas.start_Ok_Dialog(T.capdochuadu);
                    return;
                }
                if (Player.diemKyNang == 1) {
                    GameCanvas.start_Left_Dialog(new StringBuffer().append(T.cong1kynang).append(skill.name).toString(), this.cmdSendSetPointOne);
                    break;
                } else if (Player.diemKyNang > 0) {
                    int i3 = Player.diemKyNang;
                    if (i3 > 10 - Player.mCurentLvSkill[skill.Id]) {
                        i3 = 10 - Player.mCurentLvSkill[skill.Id];
                    }
                    this.inputDialog = new InputDialog();
                    this.inputDialog.setinfo(new StringBuffer().append(T.nhapsodiem).append(skill.name).append(" (").append(T.nhohonhoacbang).append(i3).append(")").toString(), this.cmdSendSetPoint, true, T.tabkynang);
                    GameCanvas.currentDialog = this.inputDialog;
                    break;
                }
                break;
            case 1:
                try {
                    s = Short.parseShort(this.inputDialog.tfInput.getText());
                } catch (Exception e) {
                    s = 0;
                }
                if (s < 1) {
                    return;
                }
                GlobalService.gI().Add_Base_Skill_Point((byte) 1, (byte) ((Skill) vecPaintSkill.elementAt(this.idSelect)).Id, s);
                if (GameScreen.help.setStep_Next(8, 7)) {
                    GameScreen.help.Next++;
                    GameScreen.help.setNext();
                    break;
                } else {
                    GameCanvas.end_Dialog();
                    break;
                }
            case 2:
                GameCanvas.end_Dialog();
                GlobalService.gI().Add_Base_Skill_Point((byte) 1, (byte) ((Skill) vecPaintSkill.elementAt(this.idSelect)).Id, (short) 1);
                if (GameScreen.help.setStep_Next(8, 7)) {
                    GameScreen.help.Next++;
                    GameScreen.help.setNext();
                }
                MainTabNew.timePaintInfo = 0;
                break;
            case 3:
                mVector mvector = new mVector("TabSkillsNew menu");
                for (int i4 = 0; i4 < 5; i4++) {
                    if (!GameCanvas.isTouch) {
                        if (TField.isQwerty) {
                            icommand = new iCommand(new StringBuffer().append(T.phim).append(PaintInfoGameScreen.mValueChar[i4]).toString(), 4, i4, this);
                        } else {
                            icommand = new iCommand(new StringBuffer().append(T.phim).append(PaintInfoGameScreen.mValueHotKey[i4]).toString(), 4, i4, this);
                        }
                    } else {
                        icommand = new iCommand(new StringBuffer().append(T.oso).append(i4 + 1).toString(), 4, i4, this);
                    }
                    mvector.addElement(icommand);
                }
                GameCanvas.menu2.startAt(mvector, 2, T.setKey, false, null);
                break;
            case 4:
                Player.mhotkey[Player.levelTab][i2].setHotKey(((Skill) vecPaintSkill.elementAt(this.idSelect)).Id, HotKey.SKILL, (byte) 0);
                if (GameCanvas.isTouch) {
                    saveSkill();
                    break;
                } else {
                    this.isSaveSkill = true;
                    break;
                }
            case 5:
                if (this.idSelect == -1) {
                    return;
                }
                new mVector("TabSkillsNew vecmenu");
                mVector doMenu = doMenu();
                if (doMenu != null && doMenu.size() > 0) {
                    GameCanvas.menu2.startAt(doMenu, 2, T.kynang, false, null);
                    break;
                }
                break;
            case 6:
                GlobalService.gI().Add_Base_Skill_Point((byte) 2, (byte) ((Skill) vecPaintSkill.elementAt(this.idSelect)).Id);
                this.vecEffRe.removeAllElements();
                break;
        }
        super.commandPointer(i, i2);
    }

    public void upgraderesult(int i) {
        if (i == 0) {
            mSound.playSound(27, mSound.volumeSound);
            this.vecEffRe.removeAllElements();
            addEffectEnd_ReBuild_ss(11, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + (this.sizeKill / 2));
            addEffectRebuild(69, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + (this.sizeKill / 2), 300);
            addEffectRebuild(69, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + (this.sizeKill / 2), 300);
            GameCanvas.addInfoChar(T.thatbai);
            return;
        }
        mSound.playSound(26, mSound.volumeSound);
        this.vecEffRe.removeAllElements();
        addEffectEnd_ReBuild_ss(32, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + (this.sizeKill / 2));
        addEffectEnd_ReBuild_ss(33, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + (this.sizeKill / 2));
        addEffectEnd_ReBuild_ss(34, XYpaint[this.idSelect][0], XYpaint[this.idSelect][1] + (this.sizeKill / 2));
        GameCanvas.addInfoChar(T.thanhcong);
    }

    public void addEffectEnd_ReBuild_ss(int i, int i2, int i3) {
        this.vecEffRe.addElement(new EffectEnd(i, i2, i3));
    }

    public void addEffectRebuild(int i, int i2, int i3, int i4) {
        this.vecEffRe.addElement(new EffectSkill(i, i2, i3, i4, (short) 0, (byte) 0));
    }

    public void setXYSkill(mVector mvector) {
        XYpaint = (int[][]) null;
        this.xBegin = this.xTab + MainTabNew.wOneItem + (MainTabNew.wOne5 * 3) + ((MainTabNew.wblack % 8) / 2);
        this.yBegin = this.yTab + (GameCanvas.h / 5) + MainTabNew.wOneItem;
        XYpaint = mSystem.new_M_Int(Player.MaxSkill, 2);
        int i = this.yBegin + (MainTabNew.wOneItem / 2) + GameCanvas.hText;
        for (int i2 = 0; i2 < mvector.size(); i2++) {
            Skill skill = (Skill) mvector.elementAt(i2);
            if (skill != null) {
                if (i2 == 0) {
                    XYpaint[i2][0] = this.xBegin + (MainTabNew.wblack / 2);
                    XYpaint[i2][1] = i;
                    i += this.sizeKill * 2;
                } else {
                    XYpaint[i2][1] = i;
                    if (skill.typePaint == 0) {
                        XYpaint[i2][0] = this.xBegin + (MainTabNew.wblack / 4);
                    } else if (skill.typePaint == 1) {
                        XYpaint[i2][0] = this.xBegin + ((MainTabNew.wblack / 4) * 3);
                        if (i2 < mvector.size() - 2) {
                            i += this.sizeKill * 2;
                        }
                    }
                }
            }
        }
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        mFont.tahoma_7_white.drawString(mgraphics, new StringBuffer().append(T.diemkynang).append(Player.diemKyNang).toString(), this.xBegin + 2, this.yBegin + 3, 0, true);
        mgraphics.setClip(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, (MainTabNew.hblack - 2) - GameCanvas.hText);
        mgraphics.saveCanvas();
        mgraphics.ClipRec(this.xBegin, this.yBegin + GameCanvas.hText + 2, MainTabNew.wblack, (MainTabNew.hblack - 2) - GameCanvas.hText);
        mgraphics.translate(0, -MainScreen.cameraSub.yCam);
        for (int i = 0; i < vecPaintSkill.size(); i++) {
            paintLine(mgraphics, i);
            Skill skill = (Skill) vecPaintSkill.elementAt(i);
            if (skill != null) {
                skill.paint(mgraphics, XYpaint[i][0], XYpaint[i][1] + (this.sizeKill / 2), 3);
                if (i == this.idSelect && MainTabNew.Focus == 1) {
                    mgraphics.drawImage(AvMain.imgSelect_1, (XYpaint[i][0] - (this.sizeKill / 2)) - 2, XYpaint[i][1] - 2, 0, true);
                }
                if (skill.lvMin > GameScreen.player.Lv) {
                    mgraphics.drawRegion(AvMain.imgDelaySkill, 0, 0, 20, 20, 0, XYpaint[i][0], XYpaint[i][1] + (this.sizeKill / 2), 3, true);
                }
                if (i != 0) {
                    AvMain.Font3dWhite(mgraphics, new StringBuffer().append(Player.mCurentLvSkill[skill.Id]).append("").toString(), XYpaint[i][0] + (this.sizeKill / 2) + 4, (XYpaint[i][1] + (this.sizeKill / 2)) - 12, 0);
                    if (Player.mPlusLvSkill[skill.Id] > 0) {
                        AvMain.Font3dColor(mgraphics, new StringBuffer().append("+").append(Player.mPlusLvSkill[skill.Id]).toString(), XYpaint[i][0] + (this.sizeKill / 2) + 4, (XYpaint[i][1] + this.sizeKill) - 12, 0, (byte) 1);
                    }
                }
            }
        }
        for (int i2 = 0; i2 < this.vecEffRe.size(); i2++) {
            ((MainEffect) this.vecEffRe.elementAt(i2)).paint(mgraphics);
        }
        mGraphics.resetTransAndroid(mgraphics);
        mgraphics.restoreCanvas();
        mgraphics.endClip();
        if (((!GameCanvas.menu2.isShowMenu && GameCanvas.currentDialog == null) || MainTabNew.longwidth > 0) && MainTabNew.Focus == 1 && MainTabNew.timePaintInfo > MainTabNew.timeRequest) {
            paintPopupContent(mgraphics, false);
            if (this.vecListCmd != null) {
                for (int i3 = 0; i3 < this.vecListCmd.size(); i3++) {
                    iCommand icommand = (iCommand) this.vecListCmd.elementAt(i3);
                    icommand.paint(mgraphics, icommand.xCmd, icommand.yCmd);
                }
            }
        }
    }

    public void paintLine(mGraphics mgraphics, int i) {
        mgraphics.setColor(MainTabNew.color[6]);
        if (this.stylePaint[i] == 0) {
            mgraphics.fillRect(XYpaint[i][0], XYpaint[i][1] + this.sizeKill, 1, (this.hKill * 2) + this.sizeKill, true);
            return;
        }
        if (this.stylePaint[i] == 1) {
            mgraphics.fillRect(XYpaint[i][0], XYpaint[i][1] + this.sizeKill, 1, this.sizeKill / 2, true);
            mgraphics.fillRect(XYpaint[i][0] - (2 * this.w8), XYpaint[i][1] + this.sizeKill + (this.sizeKill / 2), (this.w8 * 4) + 1, 1, true);
            mgraphics.fillRect(XYpaint[i][0] - (2 * this.w8), XYpaint[i][1] + this.sizeKill + (this.sizeKill / 2) + 1, 1, this.sizeKill / 2, true);
            mgraphics.fillRect(XYpaint[i][0] + (2 * this.w8), XYpaint[i][1] + this.sizeKill + (this.sizeKill / 2) + 1, 1, this.sizeKill / 2, true);
            return;
        }
        if (this.stylePaint[i] == 2) {
            mgraphics.fillRect(XYpaint[i][0], XYpaint[i][1] + this.sizeKill, 1, this.hKill / 2, true);
            mgraphics.fillRect(XYpaint[i][0] - this.w8, XYpaint[i][1] + this.sizeKill + (this.hKill / 2), (this.w8 * 2) + 1, 1, true);
            mgraphics.fillRect(XYpaint[i][0] - this.w8, XYpaint[i][1] + this.sizeKill + (this.hKill / 2) + 1, 1, (this.hKill / 2) + 4, true);
            mgraphics.fillRect(XYpaint[i][0] + this.w8, XYpaint[i][1] + this.sizeKill + (this.hKill / 2) + 1, 1, (this.hKill / 2) + 4, true);
        }
    }

    @Override // InterfaceComponents.MainTabNew, Model.AvMain
    public void update() {
        for (int i = 0; i < this.vecEffRe.size(); i++) {
            try {
                MainEffect mainEffect = (MainEffect) this.vecEffRe.elementAt(i);
                mainEffect.update();
                if (mainEffect.isStop) {
                    this.vecEffRe.removeElement(mainEffect);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (MainTabNew.Focus == 1) {
            if (this.listContent != null) {
                this.listContent.moveCamera();
            }
            if (MainTabNew.timePaintInfo < MainTabNew.timeRequest + 2) {
                MainTabNew.timePaintInfo++;
                if (MainTabNew.timePaintInfo == MainTabNew.timeRequest) {
                    setPaintInfo();
                }
            }
            if (GameCanvas.isTouch) {
                this.list.moveCamera();
            } else {
                MainScreen.cameraSub.UpdateCamera();
                if (this.yCon < MainScreen.cameraSub.yCam + 4) {
                    this.yCon = MainScreen.cameraSub.yCam + 4;
                }
            }
        } else {
            MainTabNew.timePaintInfo = 0;
        }
    }

    @Override // Model.AvMain
    public void updatekey() {
        super.updatekey();
        if (MainTabNew.Focus == 1) {
            int i = this.idSelect;
            if (GameCanvas.keyMyHold[2] || GameCanvas.keyMyHold[8] || GameCanvas.keyMyHold[4] || GameCanvas.keyMyHold[6]) {
                TabScreenNew.timeRepaint = 10;
                if (this.idSelect == -1) {
                    GameCanvas.clearKeyHold();
                    this.idSelect = 0;
                    return;
                }
            }
            if (this.listContent != null) {
                if (GameCanvas.keyMyHold[2]) {
                    if (this.listContent.cmtoX > 0) {
                        this.listContent.cmtoX -= GameCanvas.hText;
                    } else {
                        this.listContent.cmtoX = 0;
                    }
                    GameCanvas.clearKeyHold(2);
                } else if (GameCanvas.keyMyHold[8]) {
                    if (this.listContent.cmtoX < this.listContent.cmxLim) {
                        this.listContent.cmtoX += GameCanvas.hText;
                    } else {
                        this.listContent.cmtoX = this.listContent.cmxLim;
                    }
                    GameCanvas.clearKeyHold(8);
                }
            } else if (GameCanvas.keyMyHold[2]) {
                this.idSelect -= 2;
                if (this.idSelect < 0) {
                    this.idSelect = 0;
                }
                TabScreenNew.timeRepaint = 10;
                GameCanvas.clearKeyHold(2);
            } else if (GameCanvas.keyMyHold[8]) {
                TabScreenNew.timeRepaint = 10;
                this.idSelect += 2;
                if (this.idSelect > vecPaintSkill.size() - 1) {
                    this.idSelect = vecPaintSkill.size() - 1;
                }
                GameCanvas.clearKeyHold(8);
            }
            if (GameCanvas.keyMyHold[4]) {
                TabScreenNew.timeRepaint = 10;
                if (this.idSelect % 2 == 1 || this.idSelect == 0) {
                    MainTabNew.Focus = (byte) 0;
                    this.idSelect = 0;
                } else {
                    this.idSelect--;
                }
                GameCanvas.clearKeyHold(4);
            } else if (GameCanvas.keyMyHold[6]) {
                TabScreenNew.timeRepaint = 10;
                if (this.idSelect < vecPaintSkill.size() - 1) {
                    this.idSelect++;
                }
                GameCanvas.clearKeyHold(6);
            }
            if (this.idSelect >= 0) {
                this.idSelect = resetSelect(this.idSelect, vecPaintSkill.size() - 1, false);
                Skill skill = null;
                if (this.idSelect != i && this.idSelect >= 0 && this.idSelect <= vecPaintSkill.size() - 1) {
                    this.listContent = null;
                    cmdListBig();
                    MainTabNew.timePaintInfo = 0;
                    MainScreen.cameraSub.moveCamera(0, (XYpaint[this.idSelect][1] - this.yBegin) - (MainTabNew.hblack / 2));
                    skill = (Skill) vecPaintSkill.elementAt(this.idSelect);
                }
                if (!GameCanvas.isTouch && skill != null) {
                    if (this.center == null) {
                        if (skill.lvMin <= GameScreen.player.Lv) {
                            if (Player.mCurentLvSkill[skill.Id] != 0 || Player.diemKyNang > 0) {
                                this.center = this.cmdMenu;
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (skill.lvMin > GameScreen.player.Lv || (Player.mCurentLvSkill[skill.Id] == 0 && Player.diemKyNang == 0)) {
                        this.center = null;
                        return;
                    }
                    return;
                }
                return;
            }
            this.idSelect = -1;
        }
    }

    @Override // InterfaceComponents.MainTabNew
    public void setPaintInfo() {
        if (this.idSelect >= Player.mKillPlayer.length || this.idSelect == -1) {
            MainTabNew.timePaintInfo = 0;
            return;
        }
        Skill skill = (Skill) vecPaintSkill.elementAt(this.idSelect);
        if (skill == null) {
            this.mContent = null;
            this.mPlusContent = null;
            this.listContent = null;
            return;
        }
        this.name = skill.name;
        if (Player.mCurentLvSkill[skill.Id] > 0) {
            this.name = new StringBuffer().append(this.name).append(" Lv").append(Player.mCurentLvSkill[skill.Id] + Player.mPlusLvSkill[skill.Id]).toString();
        }
        this.mContent = skill.getContent();
        this.mPlusContent = null;
        this.listContent = null;
        if (MainTabNew.longwidth > 0) {
            int length = this.mContent.length;
            if (length * GameCanvas.hText > MainTabNew.hMaxContent - this.hcmd) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent, 0, 0, (length * GameCanvas.hText) - (MainTabNew.hMaxContent - this.hcmd));
            } else if (GameCanvas.isTouch) {
                this.listContent = new ListNew(MainTabNew.xlongwidth, MainTabNew.ylongwidth, MainTabNew.longwidth, MainTabNew.hMaxContent - this.hcmd, 0, 0, 0);
            }
            cmdListBig();
            return;
        }
        this.xCon = XYpaint[this.idSelect][0] - 45;
        if (this.xCon < 2) {
            this.xCon = 2;
        } else if (this.xCon + 92 > GameCanvas.w) {
            this.xCon = GameCanvas.w - 92;
        }
        this.wContent = Monstervantieu.ID_TEMPLATE_LAC_DA_DEN;
        int i = XYpaint[this.idSelect][1] - MainScreen.cameraSub.yCam;
        if (i < this.yBegin + (MainTabNew.hblack / 2)) {
            this.yCon = i + this.sizeKill + 2 + MainScreen.cameraSub.yCam;
        } else {
            this.yCon = (((i + 1) - (GameCanvas.hText * (this.mContent.length + 1))) - (this.sizeKill / 2)) + MainScreen.cameraSub.yCam;
        }
        if ((this.yCon - MainScreen.cameraSub.yCam) + ((this.mContent.length + 1) * GameCanvas.hText) + 8 > GameCanvas.h - GameCanvas.hCommand) {
            this.yCon = (GameCanvas.h - GameCanvas.hCommand) - ((((this.mContent.length + 1) * GameCanvas.hText) + 8) - MainScreen.cameraSub.yCam);
        }
        if (this.yCon < MainScreen.cameraSub.yCam) {
            this.yCon = MainScreen.cameraSub.yCam;
        }
        int length2 = this.mContent.length;
        if (length2 * GameCanvas.hText > MainTabNew.hMaxContent) {
            this.listContent = new ListNew(this.xCon, this.yCon, this.wContent, MainTabNew.hMaxContent, 0, 0, (length2 * GameCanvas.hText) - MainTabNew.hMaxContent);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0109, code lost:
    
        if (InterfaceComponents.MainTabNew.longwidth != 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x010c, code lost:
    
        InterfaceComponents.MainTabNew.timePaintInfo = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0110, code lost:
    
        Main.GameCanvas.isPointerSelect = false;
     */
    @Override // Model.AvMain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updatePointer() {
        /*
            Method dump skipped, instructions count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: InterfaceComponents.TabSkillsNew.updatePointer():void");
    }

    public mVector doMenu() {
        mVector mvector = new mVector("TabSkillsNew menu2");
        Skill skill = (Skill) vecPaintSkill.elementAt(this.idSelect);
        if (this.idSelect < 0 && this.idSelect >= vecPaintSkill.size()) {
            return mvector;
        }
        if (Player.diemKyNang > 0 && this.idSelect > 0 && Player.mCurentLvSkill[skill.Id] < 10 && GameScreen.player.Lv >= skill.lvMin) {
            mvector.addElement(this.cmdSetPoint);
        }
        if (skill.typeSkill != 2 && Player.mCurentLvSkill[skill.Id] > 0) {
            if (mvector.size() == 0 && MainTabNew.longwidth == 0) {
                this.cmdSetKey.perform();
                return null;
            }
            mvector.addElement(this.cmdSetKey);
        }
        mvector.addElement(this.cmdupskill);
        this.hcmd = ((mvector.size() + 1) / 2) * iCommand.hButtonCmd;
        return mvector;
    }

    public static void saveSkill() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = 0; i < Player.mhotkey.length; i++) {
            try {
                for (int i2 = 0; i2 < Player.mhotkey[0].length; i2++) {
                    HotKey hotKey = Player.mhotkey[i][i2];
                    dataOutputStream.writeShort(hotKey.id);
                    dataOutputStream.writeByte(hotKey.type);
                    dataOutputStream.writeByte(hotKey.typePotion);
                }
            } catch (Exception e) {
                return;
            }
        }
        CRes.saveRMSName((byte) 0, byteArrayOutputStream.toByteArray());
        dataOutputStream.close();
    }

    public void cmdListBig() {
        if (MainTabNew.longwidth > 0 && GameCanvas.isTouch && this.idSelect >= 0) {
            this.vecListCmd = doMenu();
            setPosCmd(this.vecListCmd);
        }
    }

    public static void loadSkill(byte[] bArr) {
        if (bArr == null) {
            for (int i = 0; i < Player.mhotkey[0].length; i++) {
                Player.mhotkey[0][i] = new HotKey();
                Player.mhotkey[1][i] = new HotKey();
                Player.mhotkey[2][i] = new HotKey();
                if (i == 2) {
                    Player.mhotkey[0][i].setHotKey(0, HotKey.SKILL, (byte) 0);
                    Player.mhotkey[1][i].setHotKey(0, HotKey.SKILL, (byte) 0);
                    Player.mhotkey[2][i].setHotKey(0, HotKey.SKILL, (byte) 0);
                } else {
                    Player.mhotkey[0][i].setHotKey(0, HotKey.NULL, (byte) 0);
                    Player.mhotkey[1][i].setHotKey(0, HotKey.NULL, (byte) 0);
                    Player.mhotkey[2][i].setHotKey(0, HotKey.NULL, (byte) 0);
                }
            }
            if (GameScreen.player.Lv > 1) {
                MainItem.setAddHotKey((byte) 1, false);
                MainItem.setAddHotKey((byte) 0, false);
                return;
            }
            return;
        }
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        for (int i2 = 0; i2 < Player.mhotkey.length; i2++) {
            try {
                for (int i3 = 0; i3 < Player.mhotkey[0].length; i3++) {
                    Player.mhotkey[i2][i3] = new HotKey();
                    Player.mhotkey[i2][i3].setHotKey(dataInputStream.readShort(), dataInputStream.readByte(), dataInputStream.readByte());
                }
            } catch (Exception e) {
                for (int i4 = 0; i4 < Player.mhotkey[0].length; i4++) {
                    Player.mhotkey[0][i4] = new HotKey();
                    Player.mhotkey[1][i4] = new HotKey();
                    Player.mhotkey[2][i4] = new HotKey();
                    if (i4 == 2) {
                        Player.mhotkey[0][i4].setHotKey(0, HotKey.SKILL, (byte) 0);
                        Player.mhotkey[1][i4].setHotKey(0, HotKey.SKILL, (byte) 0);
                        Player.mhotkey[2][i4].setHotKey(0, HotKey.SKILL, (byte) 0);
                    } else {
                        Player.mhotkey[0][i4].setHotKey(0, HotKey.NULL, (byte) 0);
                        Player.mhotkey[1][i4].setHotKey(0, HotKey.NULL, (byte) 0);
                        Player.mhotkey[2][i4].setHotKey(0, HotKey.NULL, (byte) 0);
                    }
                }
                if (GameScreen.player.Lv > 1) {
                    MainItem.setAddHotKey((byte) 1, false);
                    MainItem.setAddHotKey((byte) 0, false);
                    return;
                }
                return;
            }
        }
    }
}
