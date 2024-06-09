package com.grupo.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyboardManager class handles keyboard input by implementing the KeyListener interface.
 * It keeps track of the state of various keys such as up, down, left, right, fire, and jump.
 */
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

    /**
     * Constructs a KeyboardManager with specified key mappings.
     *
     * @param upKey    The key for the up action.
     * @param downKey  The key for the down action.
     * @param leftKey  The key for the left action.
     * @param rightKey The key for the right action.
     * @param fireKey  The key for the fire action.
     * @param jumpKey  The key for the jump action.
     */
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

    /**
     * Invoked when a key has been pressed. Sets the corresponding flag to indicate that
     * the associated key is currently being held down.
     *
     * @param e The KeyEvent object containing information about the key event.
     */
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


    /**
     * Invoked when a key has been released. Clears the corresponding flag to indicate
     * that the associated key is no longer being held down.
     *
     * @param e The KeyEvent object containing information about the key event.
     */
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
