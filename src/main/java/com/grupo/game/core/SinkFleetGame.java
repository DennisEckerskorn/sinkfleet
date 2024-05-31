package com.grupo.game.core;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.core.Game;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.gameentities.Player;

public class SinkFleetGame extends Game {
    private final int rows;
    private final int cols;
    private Player player1;
    private Player player2;
    private boolean isPlayer1Turn;

    public SinkFleetGame(int width, int height, int rows, int cols, float fpsLimit, float updateLimit, int maxEntities) {
        super(width, height, fpsLimit, updateLimit, maxEntities);
        this.rows = rows;
        this.cols = cols;
        initPlayers();
        isPlayer1Turn = true;
    }

    private void initPlayers() {
        KeyboardManager km1 = new KeyboardManager('w', 's', 'a', 'd', 'f', ' ');
        KeyboardManager km2 = new KeyboardManager('i', 'k', 'j', 'l', 'h', ' ');
        player1 = new Player(0, 0, 0, 0, 100, 0, km1);
        // Poner coordenadas y medidas, de momento 0
        player2 = new Player(0, 0, 0, 0, 100, 0, km2);
        Blackboard.entityManager.addEntity(player1);
        Blackboard.entityManager.addEntity(player2);
    }

    @Override
    public EntityManager createEntityManager(int maxEntities) {
        return new SinkFleetEntityManager(maxEntities);

    }

    @Override
    public void update(double deltaTime) {
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
