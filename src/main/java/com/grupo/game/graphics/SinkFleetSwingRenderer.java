package com.grupo.game.graphics;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.graphics.swing.SwingRenderer;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;
import com.grupo.game.gameentities.ShipFragments;

import java.util.List;

import java.awt.*;

/**
 * A Swing-based renderer for the Sink Fleet game.
 */
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
        for (PlayableEntity jugador : players) {
            if (jugador instanceof Player) {
                Player fleetPlayer = (Player) jugador;
                List<Ship> shipsPlayer = fleetPlayer.getShips();
                for (Ship ship : shipsPlayer) {
                    drawEntity(g2, ship);
                }
            }
        }
        //drawShots(g2);
    }

    /**
     * Draws the shots on the screen.
     *
     * @param g2 The Graphics2D object.
     */
    private void drawShots1(Graphics2D g2) {
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
            g2.setColor(Settings.COLOR_SHIP);
            for (ShipFragments fragment : ship.getShipFragments()) {
                int x = Math.round(fragment.getX()) * Blackboard.cellSize;
                int y = Math.round(fragment.getY()) * Blackboard.cellSize;
                g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            }
        }
    }

    /**
     * Dibuja el fondo del juego, incluyendo los tableros de barcos y de disparos.
     *
     * @param g2 El contexto gráfico en el que se realizará el dibujo.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        // Calcula el desplazamiento para el segundo tablero
        int offset = Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS;

        // Dibuja el fondo del primer tablero (Tablero Barcos)
        g2.setColor(backgroundColor1);
        g2.fillRect(0, 0, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize);

        // Dibuja el fondo del segundo tablero (Tablero de Disparos)
        g2.setColor(backgroundColor2);
        g2.fillRect(offset, 0, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize);

        // Dibuja las líneas del primer tablero (Tablero Barcos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize, row * Blackboard.cellSize, Blackboard.cellSize, Blackboard.cellSize);
            }
        }

        drawShots2(g2);
    }

    //TODO: FALTA PROBAR:
    private void drawShots2(Graphics2D g2) {
        int offset = Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS;
        int[][] shots = currentPlayer.getDisparosRealizados();

        for (int row = 0; row < shots.length; row++) {
            for (int col = 0; col < shots[row].length; col++) {
                int x = col * Blackboard.cellSize + offset;
                int y = row * Blackboard.cellSize;

                switch (shots[row][col]) {
                    case 1: // Falla
                        g2.setColor(Color.WHITE);
                        g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
                        break;
                    case 2: // Acierto
                        g2.setColor(Color.RED);
                        g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
                        break;
                    default:
                        // No se ha disparado
                        break;
                }

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



