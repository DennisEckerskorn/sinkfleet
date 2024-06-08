package com.grupo.game.scenes;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;
import com.grupo.game.gameentities.ShipFragments;
import com.grupo.game.math.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SinglePlayerScene extends Scene{
    private Color backgroundColor1;
    private Color backgroundColor2;
    private Player currentPlayer;
    private SceneManager sceneManager;

    public SinglePlayerScene(Color backgroundColor1, Color backgroundColor2, SceneManager sceneManager) {
        this.backgroundColor1 = backgroundColor1;
        this.backgroundColor2 = backgroundColor2;
        this.sceneManager = sceneManager;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void render(Graphics2D g2) {
        drawBackground(g2);
        List<PlayableEntity> playableEntities = Blackboard.entityManager.getPlayableEntities();
        for (PlayableEntity playableEntity : playableEntities) {
            drawEntity(g2, playableEntity);
        }
    }

    private void drawEntity(Graphics2D g2, Entity e) {
        List<Ship> ships;
        List<Coordinates> disparos;
        if (e instanceof Player) {
            if (BlackBoard2.currentPlayer.equals((Player) e)) {
                ships = ((Player) e).getShips();
                for (Ship ship : ships) {
                    g2.setColor(Settings.COLOR_SHIP);
                    for (ShipFragments fragment : ship.getShipFragments()) {
                        int x = Math.round(fragment.getX()) * Blackboard.cellSize;
                        int y = Math.round(fragment.getY()) * Blackboard.cellSize;
                        g2.fillRect(x + 30, y + 30, Blackboard.cellSize, Blackboard.cellSize);
                    }
                }
            }

            //TODO: Implementar disparos, es correcto???
            disparos = ((Player) e).getDisparos();
            g2.setColor(Color.RED);
            for (Coordinates disparo : disparos) {
                int x = Math.round(disparo.getX()) * Blackboard.cellSize;
                int y = Math.round(disparo.getY()) * Blackboard.cellSize;
                g2.fillRect(x + 30, y + 30, Blackboard.cellSize, Blackboard.cellSize);
            }
        }
    }

    private void drawBackground(Graphics2D g2) {
        // Calcula el desplazamiento para el segundo tablero
        int offset = Settings.COLS * 50 + Settings.SPACE_BETWEEN_GAMEBOARDS;

        // Dibuja el fondo del primer tablero (Tablero Barcos)
        g2.setColor(backgroundColor1);
        g2.fillRect(0, 0, Settings.COLS * 50, Settings.ROWS * 50);

        // Dibuja el fondo del segundo tablero (Tablero de Disparos)
        g2.setColor(backgroundColor2);
        g2.fillRect(Settings.COLS * 50 + Settings.SPACE_BETWEEN_GAMEBOARDS, 0, Settings.COLS * 50, Settings.ROWS * 50);

        // Dibuja las líneas del primer tablero (Tablero Barcos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * 50, row * 50, 50, 50);
            }
        }

        // Dibuja las líneas del segundo tablero (Tablero de Disparos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * 50 + offset, row * 50, 50, 50);
            }
        }

        //drawNumberCoordinates(g2, 30);
        //drawNumberCoordinates(g2, offset + 30);
    }

    /**
     * Draws number coordinates around the board.
     *
     * @param g2     The Graphics2D object.
     * @param offset The offset for drawing the coordinates.
     */
    private void drawNumberCoordinates(Graphics2D g2, int offset) {
        g2.setColor(Color.BLACK);

        //Dibujar numero de filas:
        for (int row = 0; row < Settings.ROWS; row++) {
            g2.drawString(Integer.toString(row + 1), offset - 20, (row + 1) * Blackboard.cellSize + 4);
        }

        //Dibujar numero de columnas:
        for (int col = 0; col < Settings.COLS; col++) {
            g2.drawString(Integer.toString(col + 1), offset + col * Blackboard.cellSize + Blackboard.cellSize / 2, 20);
        }
    }

    @Override
    public void onSceneSet(JPanel parentPanel) {
        //parentPanel.setLayout(new BorderLayout());
    }




}
