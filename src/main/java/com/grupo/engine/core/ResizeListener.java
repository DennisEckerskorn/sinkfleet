package com.grupo.engine.core;

/**
 * The ResizeListener interface defines a contract for objects that listen for resize events.
 */
public interface ResizeListener {
    /**
     * Called when the game window is resized.
     *
     * @param width  The new width of the game window.
     * @param height The new height of the game window.
     */
    void onResize(int width, int height);
}
