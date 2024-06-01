package com.grupo.game.graphics;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.graphics.swing.SwingRenderer;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.Ship;

import java.awt.*;

public class SinkFleetSwingRendererPlayer extends SwingRenderer {

    public SinkFleetSwingRendererPlayer(int width, int height, ResizeListener resizeListener) {
        super(width, height, resizeListener);
    }

    @Override
    public void drawEntity(Graphics2D g2, Entity e) {
        if (e instanceof Ship) {
            Ship ship = (Ship) e;
            g2.setColor(Color.ORANGE);
            for (int i = 0; i < ship.getSize(); i++) {
                int x = Math.round(ship.getPositionsX().get(i)) * Blackboard.cellSize;
                int y = Math.round(ship.getPositionsY().get(i)) * Blackboard.cellSize;
                g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            }
        } else {
            g2.setColor(Color.ORANGE);
            g2.fillRect(Math.round(e.getX() * Blackboard.cellSize), Math.round(e.getY() * Blackboard.cellSize), Blackboard.cellSize, Blackboard.cellSize);
        }
    }

    @Override
    public void drawBackground(Graphics2D g2) {
        g2.setColor(Settings.COLOR_BACKGROUND);
        g2.fillRect(0, 0, getWidth(), getHeight());
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize, row * Blackboard.cellSize, Blackboard.cellSize, Blackboard.cellSize);
            }
        }
    }

    @Override
    public void onResize(int width, int height) {

    }
}
