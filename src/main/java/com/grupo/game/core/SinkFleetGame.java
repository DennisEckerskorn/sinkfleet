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
        spawnShipTest();
    }

    /**
     * Prueba para ver si se genera un cuadrado en el tablero...
     * TODO: Hay que hacer que se pueda ajustar manualmente la posicion por el usuario...
     */
    private void spawnShipTest() {
        int row = random.nextInt(Settings.ROWS);
        int col = random.nextInt(Settings.COLS);

        sinkFleetEntityManager.spawnShip(row, col);
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
