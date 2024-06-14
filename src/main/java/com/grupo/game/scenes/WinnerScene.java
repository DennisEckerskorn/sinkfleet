package com.grupo.game.scenes;

import com.grupo.engine.entities.Entity;
import com.grupo.game.config.Settings;

import javax.swing.*;
import java.awt.*;

public class WinnerScene extends Scene {
    private SceneManager sceneManager;
    private final String winner;

    public WinnerScene(SceneManager sceneManager, String winner) {
        this.sceneManager = sceneManager;
        this.winner = winner;
    }

    @Override
    public void render(Graphics2D g2) {
        drawWinnerMessage(g2);
    }

    private void drawWinnerMessage(Graphics2D g2) {
        String message = "CONGRATULATIONS!" + winner + " YOU'RE THE WINNER!";
        g2.setColor(Color.GREEN);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics fm = g2.getFontMetrics();
        int x = (Settings.WIDTH - fm.stringWidth(message)) / 2;
        int y = (Settings.HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(message, x, y);
    }

    @Override
    public void drawBackground(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Settings.WIDTH, Settings.HEIGHT);
    }

    @Override
    public void onSceneSet(JPanel parentPanel) {
    }

    @Override
    public void drawEntity(Graphics2D g2, Entity e) {
    }


}
