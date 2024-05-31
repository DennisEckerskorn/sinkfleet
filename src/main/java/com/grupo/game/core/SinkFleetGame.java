package com.grupo.game.core;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.core.Game;
import com.grupo.game.config.Settings;

import java.util.Random;

public class SinkFleetGame extends Game {
    private final int rows;
    private final int cols;
    private final Random random;
    private final SinkFleetEntityManager sinkFleetEntityManager;

    public SinkFleetGame(int width, int height, int rows, int cols, float fpsLimit, float updateLimit, int maxEntities) {
        super(width, height, fpsLimit, updateLimit, maxEntities);
        this.rows = rows;
        this.cols = cols;
        random = new Random(); //TODO: No hará falta en el futuro???
        sinkFleetEntityManager = (SinkFleetEntityManager) Blackboard.entityManager;
        spawnShips();
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


    @Override
    public EntityManager createEntityManager(int maxEntities) {
        return new SinkFleetEntityManager(maxEntities);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void gameResized() {
        Blackboard.cellSize = getWidth() < getHeight() ? getWidth() / cols : getHeight() / rows;
    }
}
