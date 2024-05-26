package com.denniseckerskorn.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean fire;
    private boolean jump;

    private final char upKey;
    private final char downKey;
    private final char leftKey;
    private final char rightKey;
    private final char fireKey;
    private final char jumpKey;

    public KeyboardManager(char upKey, char downKey, char leftKey, char rightKey, char fireKey, char jumpKey) {
        this.upKey = Character.toLowerCase(upKey);
        this.downKey = Character.toLowerCase(downKey);
        this.leftKey = Character.toLowerCase(leftKey);
        this.rightKey = Character.toLowerCase(rightKey);
        this.fireKey = Character.toLowerCase(fireKey);
        this.jumpKey = Character.toLowerCase(jumpKey);
        up = false;
        down = false;
        left = false;
        right = false;
        fire = false;
        jump = false;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isJump() {
        return jump;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == upKey) {
            up = true;
        } else if (c == downKey) {
            down = true;
        }
        if (c == leftKey) {
            left = true;
        } else if (c == rightKey) {
            right = true;
        }
        if (c == fireKey) {
            fire = true;
        }
        if (c == jumpKey) {
            jump = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == upKey) {
            up = false;
        } else if (c == downKey) {
            down = false;
        }
        if (c == leftKey) {
            left = false;
        } else if (c == rightKey) {
            right = false;
        }
        if (c == fireKey) {
            fire = false;
        }
        if (c == jumpKey) {
            jump = false;
        }
    }
}
