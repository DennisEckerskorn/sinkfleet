package com.grupo.game.graphics;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.graphics.swing.SwingRenderer;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.scenes.Scene;

import java.awt.*;

/**
 * A Swing-based renderer for the Sink Fleet game.
 */
public class SinkFleetSwingRenderer extends SwingRenderer {
    private Scene currentScene;

    /**
     * Constructs a SinkFleetSwingRenderer with the specified width, height, and resize listener.
     *
     * @param width          The initial width of the renderer.
     * @param height         The initial height of the renderer.
     * @param resizeListener The resize listener to handle resize events.
     */
    public SinkFleetSwingRenderer(int width, int height, ResizeListener resizeListener) {
        super(width, height, resizeListener);
    }

    /**
     * Sets the current scene to render.
     *
     * @param currentScene The scene to render.
     */
    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
        removeAll();
        if (currentScene != null) {
            currentScene.onSceneSet(this);
        }
        revalidate();
        repaint();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    /**
     * Paints the component.
     *
     * @param g The Graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        if (getKeyListeners()[0] != BlackBoard2.currentPlayer.getKeyboardManager()) {
            removeKeyListener(BlackBoard2.opponentPlayer.getKeyboardManager());
            addKeyListener(BlackBoard2.currentPlayer.getKeyboardManager());

        }
        //super.paintComponent(g);
        if (currentScene != null) {
            Graphics2D g2 = (Graphics2D) g;
            currentScene.render(g2);
        }
    }

    /**
     * Draws the entity on the screen.
     *
     * @param g2 The Graphics2D object.
     * @param e  The entity to draw.
     */
    @Override
    public void drawEntity(Graphics2D g2, Entity e) {
        // Forward the call to the current scene

        if (currentScene != null) {
            currentScene.drawEntity(g2, e);
        }

    }

    /**
     * Draws the background of the game, including the ship and shot boards.
     *
     * @param g2 The graphics context for drawing.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        // Forward the call to the current scene
        if (currentScene != null) {
            currentScene.drawBackground(g2);
        }
    }


    /**
     * Handles the resize event.
     *
     * @param width  The new width of the renderer.
     * @param height The new height of the renderer.
     */
    @Override
    public void onResize(int width, int height) {
        this.setSize(width, height); // Establecer el nuevo tamaño de la escena
        revalidate(); // Volver a validar la disposición de los componentes
        repaint();
    }

    /**
     * Gets the cell size of the renderer.
     *
     * @return The cell size.
     */
    public int getCellSize() {
        return Blackboard.cellSize;
    }
}



