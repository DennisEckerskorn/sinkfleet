package com.grupo.game.scenes;

import com.grupo.engine.entities.Entity;
import com.grupo.game.config.Settings;

import javax.swing.*;
import java.awt.*;

public class GameOverScene extends Scene {
    private SceneManager sceneManager;

    public GameOverScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void render(Graphics2D g2) {
        drawGameOverMessage(g2);
    }

    private void drawGameOverMessage(Graphics2D g2) {
        String message = "GAME OVER";
        g2.setColor(Color.RED);
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
    public void drawEntity(Graphics2D g2, Entity e) {

    }

    @Override
    public void onSceneSet(JPanel parentPanel) {

    }
}
