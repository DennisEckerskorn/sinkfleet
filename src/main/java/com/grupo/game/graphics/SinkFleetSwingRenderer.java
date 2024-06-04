package com.grupo.game.graphics;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.graphics.swing.SwingRenderer;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;

import java.util.List;

import java.awt.*;

/**
 * A Swing-based renderer for the Sink Fleet game.
 */
public class SinkFleetSwingRenderer extends SwingRenderer {

    private Player currentPlayer;

    /**
     * Constructs a new SinkFleetSwingRenderer with the specified width, height,
     * resize listener, and current player.
     *
     * @param width          The width of the renderer.
     * @param height         The height of the renderer.
     * @param resizeListener The resize listener for handling window resizing.
     * @param currentPlayer  The current player to render.
     */
    public SinkFleetSwingRenderer(int width, int height, ResizeListener resizeListener, Player currentPlayer) {
        super(width, height, resizeListener);
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets the current player to be rendered.
     *
     * @param currentPlayer The current player to render.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Paints the component.
     *
     * @param g The Graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);

        List<PlayableEntity> players = Blackboard.entityManager.getPlayableEntities();
        for (int i = 0; i < players.size(); i++) {
            PlayableEntity jugador = players.get(i);
            if (jugador instanceof Player) {
                Player fleetPlayer = (Player) jugador;
                List<Ship> shipsPlayer = fleetPlayer.getShips();
                for (Ship ship : shipsPlayer) {
                    drawEntity(g2, ship);
                }
            }
        }
        //TODO: NullPointer exception por el player...
        //drawShots(g2);
    }

    /**
     * Draws the shots on the screen.
     *
     * @param g2 The Graphics2D object.
     */
    private void drawShots(Graphics2D g2) {
        int offset = Settings.COLS * Blackboard.cellSize + 20;
        int[][] shots = currentPlayer.getDisparosRealizados();

        for (int row = 0; row < shots.length; row++) {
            for (int col = 0; col < shots[row].length; col++) {
                if (shots[row][col] == 1) { // Falla
                    g2.setColor(Color.BLUE);
                    g2.fillRect(col * Blackboard.cellSize + offset, row * Blackboard.cellSize, Blackboard.cellSize, Blackboard.cellSize);
                } else if (shots[row][col] == 2) { // Acierto
                    g2.setColor(Color.RED);
                    g2.fillRect(col * Blackboard.cellSize + offset, row * Blackboard.cellSize, Blackboard.cellSize, Blackboard.cellSize);
                }
            }
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
        if (e instanceof Ship) {
            Ship ship = (Ship) e;
            g2.setColor(Color.ORANGE);
            for (int i = 0; i < ship.getSize(); i++) {
                int x = Math.round(ship.getPositionsX().get(i)) * Blackboard.cellSize;
                int y = Math.round(ship.getPositionsY().get(i)) * Blackboard.cellSize;
                g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            }
        }
    }

    /**
     * Draws the background on the screen.
     *
     * @param g2 The Graphics2D object.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        g2.setColor(Settings.COLOR_BACKGROUND);
        g2.fillRect(0, 0, getWidth(), getHeight());
        System.out.println("asd");
        int offset = Settings.COLS * Blackboard.cellSize + 20;

        // Dibuja el primer tablero (Player 1)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize, row * Blackboard.cellSize, Blackboard.cellSize, Blackboard.cellSize);
            }
        }

        // Dibuja el segundo tablero (Player 2) a la derecha del primero
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize + offset, row * Blackboard.cellSize, Blackboard.cellSize, Blackboard.cellSize);
            }
        }
    }


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
