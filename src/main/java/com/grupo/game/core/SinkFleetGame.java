package com.grupo.game.core;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.core.Game;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.Player;

import java.util.Random;

public class SinkFleetGame extends Game {
    private final int rows;
    private final int cols;
    private final Random random;
    private final SinkFleetEntityManager sinkFleetEntityManager;
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;

    public SinkFleetGame(int width, int height, int rows, int cols, float fpsLimit, float updateLimit, int maxEntities) {
        super(width, height, fpsLimit, updateLimit, maxEntities);
        this.rows = rows;
        this.cols = cols;
        random = new Random(); //TODO: No hará falta en el futuro???
        sinkFleetEntityManager = (SinkFleetEntityManager) Blackboard.entityManager;
        initPlayers();
        spawnShips();
    }


    private void initPlayers() {
        KeyboardManager km1 = new KeyboardManager('w', 's', 'a', 'd', 'f', ' ');
        KeyboardManager km2 = new KeyboardManager('i', 'k', 'j', 'l', 'h', ' ');
        player1 = new Player(0, 0, 0, 0, 100, 0, km1);
        // Poner coordenadas y medidas, de momento 0

        // TODO: NO estoy seguro del todo si esto esta bien del todo
        player2 = new Player(0, 0, 0, 0, 100, 0, km2);
        Blackboard.entityManager.addEntity(player1);
        Blackboard.entityManager.addEntity(player2);
    }

    /**
     * PRUEBA para ver si se genera un cuadrado en el tablero...
     * TODO: Hay que hacer que se pueda ajustar manualmente la posicion por el usuario...
     */
    private void spawnShips() {
        // Ejemplo de barcos colocados en posiciones específicas
        spawnShipAt(2, 3, 4, true);  // Barco de tamaño 4 en posición (2, 3) horizontal
        spawnShipAt(5, 6, 3, false); // Barco de tamaño 3 en posición (5, 6) vertical
        spawnShipAt(1, 1, 2, true);  // Barco de tamaño 2 en posición (1, 1) horizontal
        spawnShipAt(7, 2, 5, true);  // Barco de tamaño 5 en posición (7, 2) horizontal
        spawnShipAt(3, 8, 3, false); // Barco de tamaño 3 en posición (3, 8) vertical
    }

    private void spawnShipAt(int row, int col, int size, boolean isHorizontal) {
        float x = col;
        float y = row;
        sinkFleetEntityManager.spawnShip(x, y, size, isHorizontal);
    }

    /**
     * Método que devuelve al jugador actual.
     *
     * @return Player, jugador actual
     */
    public Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }


    @Override
    public EntityManager createEntityManager(int maxEntities) {
        return new SinkFleetEntityManager(maxEntities);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        super.update(deltaTime);
        if (isPlayer1Turn) {
            player1.procesarInput();
            player1.update(deltaTime);
        } else {
            player2.procesarInput();
            player2.update(deltaTime);
        }
    }

    @Override
    public void gameResized() {
        Blackboard.cellSize = getWidth() < getHeight() ? getWidth() / cols : getHeight() / rows;
    }
}
