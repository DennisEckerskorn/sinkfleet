package com.grupo.game.graphics;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.graphics.swing.SwingRenderer;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;
import com.grupo.game.gameentities.ShipFragments;
import com.grupo.game.math.Coordinates;
import com.grupo.game.scenes.Scene;

import java.util.List;

import java.awt.*;

/**
 * A Swing-based renderer for the Sink Fleet game.
 */
public class SinkFleetSwingRenderer extends SwingRenderer {
    private Color backgroundColor1;
    private Color backgroundColor2;
    private Player currentPlayer;
    private Scene currentScene;

    public SinkFleetSwingRenderer(int width, int height, ResizeListener resizeListener, Color backgroundColor1, Color backgroundColor2) {
        super(width, height, resizeListener);
        this.backgroundColor1 = backgroundColor1;
        this.backgroundColor2 = backgroundColor2;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
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

    /**
     * Paints the component.
     *
     * @param g The Graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        //currentScene.render(g2);
        List<PlayableEntity> playableEntities = Blackboard.entityManager.getPlayableEntities();
        for (PlayableEntity playableEntity : playableEntities) {
            drawEntity(g2, playableEntity);
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

    /**
     * Draws the background of the game, including the ship and shot boards.
     *
     * @param g2 The graphics context for drawing.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        // Calcula el desplazamiento para el segundo tablero
        int offset = Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS;

        // Dibuja el fondo del primer tablero (Tablero Barcos)
        g2.setColor(backgroundColor1);
        g2.fillRect(30, 30, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize); //cambios

        // Dibuja el fondo del segundo tablero (Tablero de Disparos)
        g2.setColor(backgroundColor2);
        g2.fillRect(offset + 30, 30, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize); //cambios

        // Dibuja las líneas del primer tablero (Tablero Barcos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize + 30, row * Blackboard.cellSize + 30, Blackboard.cellSize, Blackboard.cellSize);
            }
        }

        // Dibuja las líneas del segundo tablero (Tablero de Disparos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize + 30 + offset, row * Blackboard.cellSize + 30, Blackboard.cellSize, Blackboard.cellSize);
            }
        }

        drawNumberCoordenates(g2, 30);
        drawNumberCoordenates(g2, offset + 30);
    }

    /**
     * Draws number coordinates around the board.
     *
     * @param g2     The Graphics2D object.
     * @param offset The offset for drawing the coordinates.
     */
    private void drawNumberCoordenates(Graphics2D g2, int offset) {
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

    /**
     * Handles the resize event.
     *
     * @param width  The new width of the renderer.
     * @param height The new height of the renderer.
     */
    @Override
    public void onResize(int width, int height) {

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



