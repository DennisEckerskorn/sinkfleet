package com.grupo.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The MouseManager class manages mouse input for the game.
 * It implements the MouseListener interface to handle mouse events such as clicks, presses, releases, and movements.
 */
public class MouseManager implements MouseListener {
    private float mouseX;
    private float mouseY;
    private boolean leftKey;
    private boolean rightKey;


    /**
     * Constructs a MouseManager object with default values.
     */
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

    /**
     * Handles mouse clicks. Sets the corresponding flags to true based on the clicked mouse button.
     *
     * @param e The MouseEvent object containing information about the mouse event.
     */
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

    /**
     * Handles mouse presses. Sets the corresponding flag to true based on the pressed mouse button.
     *
     * @param e The MouseEvent object containing information about the mouse event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftKey = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightKey = true;
        }
    }

    /**
     * Handles mouse releases. Sets the corresponding flag to false based on the released mouse button.
     *
     * @param e The MouseEvent object containing information about the mouse event.
     */
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

    /**
     * Updates the mouse coordinates when the mouse is moved.
     *
     * @param e The MouseEvent object containing information about the mouse event.
     */
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
