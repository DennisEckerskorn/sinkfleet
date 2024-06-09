package com.grupo.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.grupo.engine.input.KeyboardManager;

/**
 * A specialized keyboard manager for handling numeric input.
 */
public class NumericKeyboardManager extends KeyboardManager {
    private String posX;
    private String posY;
    private boolean isHorizontal;
    private boolean enterPressed;
    private boolean next; // Represents the space bar

    /**
     * Constructs a NumericKeyboardManager with specified control keys.
     *
     * @param upKey    The key for moving up.
     * @param downKey  The key for moving down.
     * @param leftKey  The key for moving left.
     * @param rightKey The key for moving right.
     * @param fireKey  The key for firing.
     * @param jumpKey  The key for jumping.
     */
    public NumericKeyboardManager(char upKey, char downKey, char leftKey, char rightKey, char fireKey, char jumpKey) {
        super(upKey, downKey, leftKey, rightKey, fireKey, jumpKey);
        posX = "";
        posY = "";
        isHorizontal = true;
    }

    /**
     * Gets the x-coordinate input value.
     *
     * @return The x-coordinate input value.
     */
    public String getPosX() {
        if (posX == null || posX.isEmpty()) {
            return "0";

        }
        int x = Integer.parseInt(posX);
        if (x > 10) {
            posX = "10";
        }
        return posX;
    }

    public String getPosY() {
        if (posY == null || posY.isEmpty()) {
            return "0";
        }
        int y = Integer.parseInt(posY);
        if (y > 10) {
            posY = "10";
        }
        return posY;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void clearPosX() {
        posX = "";
    }

    public void clearPosY() {
        posY = "";
    }

    public boolean isNextTurn() {
        return next;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

    public void setNext(boolean next) {
        this.next = next;
    }


    /**
     * Invoked when a key has been typed.
     * Handles appending digits to the x or y coordinates.
     *
     * @param e The KeyEvent object.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (isDigit(c)) {
            if (posX.length() < 2) {
                posX += c;
            } else if (posY.length() < 2) {
                posY += c;
            }
        }
    }

    /**
     * Invoked when a key has been pressed.
     * Handles changing the input mode, deleting characters, and detecting special keys.
     *
     * @param e The KeyEvent object.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isHorizontal = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            isHorizontal = true;
        } else if (key == KeyEvent.VK_BACK_SPACE) {
            if (posY.length() > 0) {
                posY = posY.substring(0, posY.length() - 1);
            } else if (posX.length() > 0) {
                posX = posX.substring(0, posX.length() - 1);
            }
        } else if (key == KeyEvent.VK_ENTER) {
            enterPressed = true;
        } else if (key == KeyEvent.VK_SPACE) {
            next = true;
        }
    }

    /**
     * Invoked when a key has been released.
     * Resets the special key states.
     *
     * @param e The KeyEvent object.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        enterPressed = false;
        next = false;
    }
}
