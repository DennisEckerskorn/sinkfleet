package com.grupo.game.scenes;

import com.grupo.engine.entities.Entity;

import javax.swing.*;
import java.awt.*;

/**
 * Represents an abstract scene in the game.
 */
public abstract class Scene {
    /**
     * Constructs a Scene.
     */
    public Scene() {

    }

    /**
     * Renders the scene.
     *
     * @param g2 The Graphics2D object used for rendering.
     */
    public abstract void render(Graphics2D g2);

    /**
     * Draws the specified entity on the scene.
     *
     * @param g2 The Graphics2D object used for drawing.
     * @param e  The entity to draw.
     */
    public abstract void drawEntity(Graphics2D g2, Entity e);

    /**
     * Draws the background of the scene.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    public abstract void drawBackground(Graphics2D g2);

    /**
     * Called when the scene is set.
     *
     * @param parentPanel The parent panel containing the scene.
     */
    public abstract void onSceneSet(JPanel parentPanel);

}
