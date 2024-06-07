package com.grupo.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NumericKeyboardManager implements KeyListener {
    private String posX;
    private String posY;
    private boolean isHorizontal;

    public NumericKeyboardManager() {
        posX = "";
        posY = "";
        isHorizontal = true;
    }

    public String getPosX() {
        return posX;
    }

    public String getPosY() {
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

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

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
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
