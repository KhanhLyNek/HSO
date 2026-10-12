package InterfaceComponents;

import CLib.TField;
import CLib.mGraphics;
import CLib.mSystem;
import GameScreen.GameScreen;
import Main.GameCanvas;
import Model.AvMain;
import Model.T;
import netcommand.global.GlobalService;

/* loaded from: KnightOnline_v403.jar:InterfaceComponents/ChatTextField.class */
public class ChatTextField extends AvMain {
    public TField tfChat = new TField();
    public static ChatTextField instance;
    public static boolean isShow = false;

    public static ChatTextField gI() {
        if (instance != null) {
            return instance;
        }
        ChatTextField chatTextField = new ChatTextField();
        instance = chatTextField;
        return chatTextField;
    }

    public void setChat() {
        isShow = !isShow;
        if (isShow) {
            this.tfChat.setPoiter();
        }
    }

    @Override // Model.AvMain
    public void commandTab(int i, int i2) {
        switch (i) {
            case 0:
                this.tfChat.setText("");
                isShow = false;
                if (!GameCanvas.isTouch) {
                    this.tfChat.setFocus(true);
                    return;
                }
                return;
            case 1:
                sendChat();
                return;
            default:
                return;
        }
    }

    protected ChatTextField() {
        this.tfChat.isChangeFocus = false;
        this.tfChat.setFocus(true);
        init();
        this.tfChat.x = (GameCanvas.w - this.tfChat.width) / 2;
        this.tfChat.setMaxTextLenght(70);
        this.tfChat.setStringNull(T.chat);
        if (!GameCanvas.isTouch) {
            this.left = new iCommand(T.close, 0);
            this.center = new iCommand(T.chat, 1);
            this.right = this.tfChat.setCmdClear();
        }
    }

    public void init() {
        this.tfChat.y = ((GameCanvas.h - iCommand.hButtonCmd) - this.tfChat.height) - 5;
        this.tfChat.width = (GameCanvas.w - (TField.xDu * 2)) - 10;
    }

    public void keyPressed(int i) {
        this.tfChat.keyPressed(i);
    }

    @Override // Model.AvMain
    public void updatekey() {
        this.tfChat.update();
        super.updatekey();
    }

    @Override // Model.AvMain
    public void paint(mGraphics mgraphics) {
        super.paint(mgraphics);
        if (!mSystem.isIP_GDX) {
            this.tfChat.paint(mgraphics);
        }
    }

    @Override // Model.AvMain
    public void updatePointer() {
        this.tfChat.updatePoiter();
        super.updatePointer();
    }

    public void sendChat() {
        if (this.tfChat.getText().length() > 0) {
            GameScreen.player.strChatPopup = this.tfChat.getText();
            GlobalService.gI().chatPopup(this.tfChat.getText());
            this.tfChat.setText("");
        }
        if (GameCanvas.isTouch) {
            isShow = false;
        }
    }
}
