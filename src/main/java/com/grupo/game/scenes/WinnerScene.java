package com.grupo.game.scenes;

import com.grupo.engine.entities.Entity;
import com.grupo.game.config.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the scene displayed when a player wins the game.
 */
public class WinnerScene extends Scene {
    private SceneManager sceneManager;
    private final String winner;

    /**
     * Constructs a WinnerScene with the specified SceneManager and winner's name.
     *
     * @param sceneManager The manager that handles scene transitions and updates.
     * @param winner       The name of the winning player.
     */
    public WinnerScene(SceneManager sceneManager, String winner) {
        this.sceneManager = sceneManager;
        this.winner = winner;
    }

    /**
     * Renders the winner message on the screen.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    @Override
    public void render(Graphics2D g2) {
        drawWinnerMessage(g2);
    }

    /**
     * Draws the winner message in the center of the screen.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    private void drawWinnerMessage(Graphics2D g2) {
        String message = "CONGRATULATIONS!" + winner + " YOU'RE THE WINNER!";
        g2.setColor(Color.GREEN);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics fm = g2.getFontMetrics();
        int x = (Settings.WIDTH - fm.stringWidth(message)) / 2;
        int y = (Settings.HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(message, x, y);
    }

    /**
     * Draws the background of the winner scene.
     *
     * @param g2 The Graphics2D object used for drawing.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Settings.WIDTH, Settings.HEIGHT);
    }


    /**
     * This method is called when the scene is set on the parent panel. Currently, it has no implementation.
     *
     * @param parentPanel The JPanel that contains this scene.
     */
    @Override
    public void onSceneSet(JPanel parentPanel) {
    }

    /**
     * Draws an entity on the screen. Currently, it has no implementation for the winner scene.
     *
     * @param g2 The Graphics2D object used for drawing.
     * @param e  The entity to be drawn.
     */
    @Override
    public void drawEntity(Graphics2D g2, Entity e) {
    }


}
