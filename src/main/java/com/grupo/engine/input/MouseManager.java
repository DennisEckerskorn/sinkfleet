package com.denniseckerskorn.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener {
    private float mouseX;
    private float mouseY;
    private boolean leftKey;
    private boolean rightKey;

    public MouseManager() {
        this.mouseX = 0.0f;
        this.mouseY = 0.0f;
        this.leftKey = false;
        this.rightKey = false;
    }

    public float getMouseX() {
        return mouseX;
    }

    public float getMouseY() {
        return mouseY;
    }

    public boolean isLeftKey() {
        return leftKey;
    }

    public boolean isRightKey() {
        return rightKey;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftKey = true;
            rightKey = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightKey = true;
            leftKey = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftKey = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightKey = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftKey = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightKey = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
