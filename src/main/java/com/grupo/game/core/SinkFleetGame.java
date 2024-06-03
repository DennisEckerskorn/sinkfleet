package com.grupo.game.core;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.core.Game;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;

import java.util.Random;

public class SinkFleetGame extends Game {
    private final int rows;
    private final int cols;
    private final SinkFleetEntityManager sinkFleetEntityManager;
    private Player player1;
    private Player player2;
    private Player actualPlayer;

    public SinkFleetGame(int width, int height, int rows, int cols, float fpsLimit, float updateLimit, int maxEntities) {
        super(width, height, fpsLimit, updateLimit, maxEntities);
        this.rows = rows;
        this.cols = cols;
        sinkFleetEntityManager = (SinkFleetEntityManager) Blackboard.entityManager;
        initPlayers(rows, cols);
        spawnShips();
    }


    private void initPlayers(int rowsshoot, int cols) {
        KeyboardManager km1 = new KeyboardManager('w', 's', 'a', 'd', 'f', ' ');
        KeyboardManager km2 = new KeyboardManager('i', 'k', 'j', 'l', 'h', ' ');
        this.player1 = sinkFleetEntityManager.creatPlayer(0, 0, km1, rows, cols);
        this.actualPlayer = player1;
        // TODO: NO estoy seguro del todo si esto esta bien del todo
        player2 = new Player(0, 0, 0, 0, 100, 0, km2, rows, cols);
        Blackboard.entityManager.addEntity(player1);
        Blackboard.entityManager.addEntity(player2);
    }

    /**
     * PRUEBA para ver si se genera un cuadrado en el tablero...
     * TODO: Hay que hacer que se pueda ajustar manualmente la posicion por el usuario...
     */
    private void spawnShips() {
        // Ejemplo de barcos colocados en posiciones específicas
        player1.addShip(sinkFleetEntityManager.spawnShip(2, 3, 4, false));
        player1.addShip(sinkFleetEntityManager.spawnShip(5, 5, 4, true));
        player2.addShip(sinkFleetEntityManager.spawnShip(0, 0, 2, false));

    }

   


    @Override
    public EntityManager createEntityManager(int maxEntities) {
        return new SinkFleetEntityManager(maxEntities);
    }

    @Override
    public void update(double deltaTime) {
        //!TODO MirarTodo
    }

    @Override
    public void gameResized() {
        Blackboard.cellSize = getWidth() < getHeight() ? getWidth() / cols : getHeight() / rows;
    }

}
