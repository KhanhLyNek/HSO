package Main;

import CLib.mImage;
import CLib.mSystem;
import GameEffect.EffectSkill;
import GameObjects.Item;
import GameObjects.MainObject;
import GameScreen.LoginScreen;
import GameScreen.PaintInfoGameScreen;
import GameScreen.WorldMapScreen;
import InterfaceComponents.MainTabNew;
import InterfaceComponents.MsgDialog;
import InterfaceComponents.PopupChat;
import Map.BackGround;
import Map.ItemMap;
import Model.AvMain;
import Model.FrameImage;
import Model.Point;

/* loaded from: KnightOnline_v403.jar:Main/CreateImageStatic.class */
public class CreateImageStatic {
    public static void LoadImage() {
        PaintInfoGameScreen.loadPaintInfo();
        AvMain.imgLock = mImage.createImage("/interface/lock.img");
        AvMain.imgGlass = mImage.createImage("/interface/glass.img");
        AvMain.imghpmp = mImage.createImage("/interface/hp_mp.img");
        AvMain.imgInfo = mImage.createImage("/interface/infochar.img");
        AvMain.imgBackInfo = mImage.createImage("/interface/backinfo.img");
        AvMain.fraQuest = new FrameImage(mImage.createImage("/interface/quest.img"), 12, 19);
        AvMain.imgFocusMap = mImage.createImage("/interface/focusmap.img");
        AvMain.imgcolorhpmp = mImage.createImage("/interface/color_hp_mp.png");
        AvMain.imgcolorhpmp_back = mImage.createImage("/interface/color_hp_mpback.png");
        AvMain.imgDelaySkill = mImage.createImage("/interface/delayskill.img");
        AvMain.imgLoadImg = mImage.createImage("/interface/loadimg.img");
        AvMain.imgEyeDie = mImage.createImage("/interface/eyedie.img");
        AvMain.imgHotKey = mImage.createImage("/interface/hotkey.img");
        AvMain.imgHotKey2 = mImage.createImage("/interface/hotkey2.img");
        AvMain.imgMess = mImage.createImage("/interface/mess.img");
        AvMain.imgicongt = mImage.createImage("/interface/gt.img");
        AvMain.imgColorItem = mImage.createImage("/interface/coloritem.img");
        AvMain.fraPk = new FrameImage(mImage.createImage("/interface/iconpk.png"), 12, 12);
        AvMain.fraStar = new FrameImage(mImage.createImage("/interface/ar.img"), 15, 15);
        AvMain.fraObjMiniMap = new FrameImage(mImage.createImage("/interface/objminimap.png"), 7, 7);
        AvMain.fraDiamond = new FrameImage(mImage.createImage("/interface/screentab4.img"), 14, 14);
        AvMain.fraPlayerDie = new FrameImage(mImage.createImage("/interface/die_char.img"), 28, 42);
        for (int i = 0; i < AvMain.tab.length; i++) {
            AvMain.tab[i] = mImage.createImage(new StringBuffer().append("/interface/tab").append(i).append(".img").toString());
        }
        AvMain.wimg = mImage.getImageWidth(AvMain.tab[0].image);
        AvMain.imgtextfield = mImage.createImage("/interface/textf.img");
        MainObject.focus = mImage.createImage("/interface/focus.img");
        MainObject.newfocus = mImage.createImage("/interface/newfocus.png");
        MainObject.shadow = mImage.createImage("/shadow.png");
        MainObject.shadow1 = mImage.createImage("/shadow1.png");
        MainObject.water = mImage.createImage("/water.img");
        BackGround.LoadBackGround();
        AvMain.imgSelect = mImage.createImage("/interface/selected_hand.img");
        MsgDialog.fraWaiting = new FrameImage(mImage.createImage("/interface/waiting.img"), 18, 18);
        if (mSystem.isIP_TrucTiep) {
            LoginScreen.logo = mImage.createImage("/interface/logoip.png");
        } else {
            LoginScreen.logo = mImage.createImage("/interface/logo.png");
        }
        for (int i2 = 0; i2 < MainTabNew.imgTab.length; i2++) {
            MainTabNew.imgTab[i2] = mImage.createImage(new StringBuffer().append("/interface/screentab").append(i2).append(".img").toString());
        }
        AvMain.imgPopup = mImage.createImage("/interface/popup.img");
        for (int i3 = 0; i3 < PopupChat.mPopup.length; i3++) {
            PopupChat.mPopup[i3] = mImage.createImage(new StringBuffer().append("/interface/chatpopup").append(i3).append(".img").toString());
        }
        PaintInfoGameScreen.imgInfoFocus = mImage.createImage("/interface/infofocus.img");
        PaintInfoGameScreen.imgauto = mImage.createImage("/interface/iconauto.img");
        PaintInfoGameScreen.imgxp = mImage.createImage("/interface/iconxp.img");
        PaintInfoGameScreen.fraStatusArea = new FrameImage(mImage.createImage("/interface/statusarea.png"), 11, 11);
        PaintInfoGameScreen.fralevelup = new FrameImage(mImage.createImage("/interface/levelup.img"), 11, 11);
        WorldMapScreen.fraMyPos = new FrameImage(AvMain.imgFocusMap, 10, 10);
        PaintInfoGameScreen.fraFocusIngame = new FrameImage(mImage.createImage("/interface/focusingame.img"), 32, 11);
        PaintInfoGameScreen.fraEvent = new FrameImage(mImage.createImage("/interface/event.img"), 22, 22);
        Item.fraeffitemdrop = new FrameImage(mImage.createImage("/interface/effitemdrop.img"), 15, 15);
        AvMain.fraMonSample = new FrameImage(mImage.createImage("/interface/monsample.img"), 36, 36);
        Point.FraEffInMap = new FrameImage[1];
        Point.FraEffInMap[0] = new FrameImage(mImage.createImage("/interface/eff_inmap0.png"), 16, 16);
        MainTabNew.img_skIcn = mImage.createImage("/interface/sk_icn.png");
        MainTabNew.img_pkIcn = mImage.createImage("/interface/pk_icn.png");
        MainTabNew.img_arenaIcn = mImage.createImage("/interface/arena_icn.png");
        PaintInfoGameScreen.imgArenaIcon = new FrameImage(mImage.createImage("/interface/iconarena.png"), 18, 18);
        if (!mSystem.isj2me) {
            for (int i4 = 0; i4 < AvMain.imghitScr.length; i4++) {
                AvMain.imghitScr[i4] = mImage.createImage(new StringBuffer().append("/interface/hitscr").append(i4).append(".png").toString());
            }
            for (int i5 = 0; i5 < PaintInfoGameScreen.imgHitWidth.length; i5++) {
                PaintInfoGameScreen.imgHitWidth[i5] = mImage.getImageWidth(AvMain.imghitScr[i5].image);
                PaintInfoGameScreen.imgHitHeight[i5] = mImage.getImageHeight(AvMain.imghitScr[i5].image);
            }
        }
        AvMain.imgRect = mImage.createImage("/interface/rect.png");
        ItemMap.img_HouseArena_Die = new FrameImage[4];
        int[] iArr = {72, EffectSkill.EFF_KIEM_NHO_TROI, 94, 105};
        int[] iArr2 = {74, 57, 54, 63};
        for (int i6 = 0; i6 < 4; i6++) {
            ItemMap.img_HouseArena_Die[i6] = new FrameImage(mImage.createImage(new StringBuffer().append("/bg/nhabe").append(i6 + 1).append(".png").toString()), iArr[i6], iArr2[i6]);
        }
        AvMain.imgStun = new FrameImage(143);
        AvMain.imgSleep = new FrameImage(142);
        AvMain.fraPlayerNo = new FrameImage(139);
        AvMain.imgSelect_1 = mImage.createImage("/interface/imgselect_1.png");
        AvMain.fraFogetPass = new FrameImage(mImage.createImage("/point/fgpass.png"), 14, 14);
        AvMain.imgcolorhpSmall_back = mImage.createImage("/interface/imghpsmall_back.png");
        AvMain.imgcolorhpSmall = mImage.createImage("/interface/imghpsmall.png");
        PaintInfoGameScreen.WBlackclolor = mImage.getImageWidth(AvMain.imgcolorhpmp_back.image);
        PaintInfoGameScreen.WRedclor = mImage.getImageWidth(AvMain.imgcolorhpmp.image);
        MainObject.weaponEff_Gem = new FrameImage[2];
        MainObject.weaponEff_Gem[0] = new FrameImage(150);
        MainObject.weaponEff_Gem[1] = new FrameImage(151);
        AvMain.img18Plus = mImage.createImage("/interface/18.png");
    }
}
