package com.grupo.game.scenes;

import com.grupo.engine.entities.Entity;
import com.grupo.game.config.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the game over scene.
 * Displays a game over message when the game ends.
 */
public class GameOverScene extends Scene {
    private SceneManager sceneManager;

    /**
     * Constructs a GameOverScene with the specified scene manager.
     *
     * @param sceneManager The scene manager responsible for managing scenes.
     */
    public GameOverScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Renders the game over message.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    @Override
    public void render(Graphics2D g2) {
        drawGameOverMessage(g2);
    }

    /**
     * Draws the game over message in the center of the screen.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawGameOverMessage(Graphics2D g2) {
        String message = "GAME OVER";
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics fm = g2.getFontMetrics();
        int x = (Settings.WIDTH - fm.stringWidth(message)) / 2;
        int y = (Settings.HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(message, x, y);
    }

    /**
     * Draws the background of the game over scene.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Settings.WIDTH, Settings.HEIGHT);
    }

    /**
     * Empty implementation for drawing entities in the game over scene.
     * This scene does not contain any entities to draw.
     *
     * @param g2 The Graphics2D object used for drawing.
     * @param e  The entity to draw.
     */
    @Override
    public void drawEntity(Graphics2D g2, Entity e) {

    }

    /**
     * Called when the scene is set.
     * Initializes the scene within the parent panel.
     *
     * @param parentPanel The parent panel containing the scene.
     */
    @Override
    public void onSceneSet(JPanel parentPanel) {

    }
}
