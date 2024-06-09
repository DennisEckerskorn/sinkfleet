package com.grupo.engine.graphics;

/**
 * The RenderAPI interface defines methods for rendering and handling resize events.
 */
public interface RenderAPI {
    /**
     * Renders the graphics.
     * This method should be implemented to render the graphics of the application.
     */
    void render();

    /**
     * Handles the resize event.
     *
     * @param width  The new width after resizing.
     * @param height The new height after resizing.
     */
    void onResize(int width, int height);
}
