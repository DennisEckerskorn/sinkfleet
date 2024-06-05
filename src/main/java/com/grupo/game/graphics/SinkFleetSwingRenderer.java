package com.grupo.game.graphics;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.graphics.swing.SwingRenderer;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;

import java.awt.*;

public class SinkFleetSwingRenderer extends SwingRenderer {
    private Color backgroundColor1;
    private Color backgroundColor2;
    private Player currentPlayer;

    public SinkFleetSwingRenderer(int width, int height, ResizeListener resizeListener, Color backgroundColor1, Color backgroundColor2) {
        super(width, height, resizeListener);
        this.backgroundColor1 = backgroundColor1;
        this.backgroundColor2 = backgroundColor2;
    }

    public Color getBackgroundColor1() {
        return backgroundColor1;
    }

    public Color getBackgroundColor2() {
        return backgroundColor2;
    }

    public void setBackgroundColor1(Color backgroundColor1) {
        this.backgroundColor1 = backgroundColor1;
    }

    public void setBackgroundColor2(Color backgroundColor2) {
        this.backgroundColor2 = backgroundColor2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void drawEntity(Graphics2D g2, Entity e) {
        if (e instanceof Ship) {
            Ship ship = (Ship) e;
            g2.setColor(Settings.COLOR_SHIP);
            for (int i = 0; i < ship.getSize(); i++) {
                int x = Math.round(ship.getPositionsX().get(i)) * Blackboard.cellSize;
                int y = Math.round(ship.getPositionsY().get(i)) * Blackboard.cellSize;
                g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            }
        } else {
            g2.setColor(Settings.COLOR_SHIP);
            g2.fillRect(Math.round(e.getX() * Blackboard.cellSize), Math.round(e.getY() * Blackboard.cellSize), Blackboard.cellSize, Blackboard.cellSize);
        }
    }

    @Override
    public void drawBackground(Graphics2D g2) {
        int offset = Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS;

        // Fondo del primer Tablero:
        g2.setColor(backgroundColor1);
        g2.fillRect(0, 0, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize);

        // Fondo del segundo Tablero:
        g2.setColor(backgroundColor2);
        g2.fillRect(offset, 0, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize);


        // Dibuja el primer tablero (Tablero Barcos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize, row * Blackboard.cellSize, Blackboard.cellSize, Blackboard.cellSize);
            }
        }

        //TODO: Falta implementar el player correctamente

        // Dibuja el segundo tablero a la derecha del primero con offset. (Disparos)
        Player currentPlayer = getCurrentPlayer();
        //if(currentPlayer != null) {
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                int x = col * Blackboard.cellSize + offset;
                int y = row * Blackboard.cellSize;

                /*
                int result = currentPlayer.getDisparosRealizados()[row][col];
                switch (result) {
                    case 0: //No se ha disparado.
                        break;
                    case 1: //
                        g2.setColor(Color.WHITE);
                        g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
                        break;
                    case 2:
                        g2.setColor(Color.RED);
                        g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
                        break;
                }

                 */

                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            }
        }
        //  }

    }

    @Override
    public void onResize(int width, int height) {

    }

    public int getCellSize() {
        return Blackboard.cellSize;
    }
}
