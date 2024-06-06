package com.grupo.game.core;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.core.Game;
import com.grupo.engine.input.KeyboardManager;

import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;


public class SinkFleetGame extends Game {
    private final int rows;
    private final int cols;
    private final SinkFleetEntityManager sinkFleetEntityManager;

    private Player player1;
    private Player player2;

    //Idica si el jugador esta en la fase de colocar barcos
    private boolean principio;
    

    public SinkFleetGame(int width, int height, int rows, int cols, float fpsLimit, float updateLimit, int maxEntities, BlackBoard2.Mode mode) {
        super(width, height, fpsLimit, updateLimit, maxEntities);
        this.rows = rows;
        this.cols = cols;
        sinkFleetEntityManager = (SinkFleetEntityManager) Blackboard.entityManager;
        this.principio = true;
        if (mode == BlackBoard2.Mode.SINGLE_PLAYER) {
            initPlayers(rows, cols);
        } else if (mode == BlackBoard2.Mode.MULTI_PLAYER) {
            //!TODO Implementar modo multijugador
        } 

       
        
        
    }


    private void initPlayers(int rowsshoot, int cols) {
        KeyboardManager km2 = new KeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga
        
        KeyboardManager km1 = new KeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga
        this.player1 = sinkFleetEntityManager.creatPlayer(0, 0, km1, rows, cols);
        BlackBoard2.currentPlayer = player1;
        this.player2 = sinkFleetEntityManager.creatPlayer(0, 0,km2, rows, cols);
        Blackboard.entityManager.addEntity(player1);
        Blackboard.entityManager.addEntity(player2);
    }

    @Override
    public EntityManager createEntityManager(int maxEntities) {
        return new SinkFleetEntityManager(maxEntities);
    }

    @Override
    public void update(double deltaTime) {
        if (BlackBoard2.buttonPressed) {
            if (principio) {
                addShip(3);
            }
        }
    }

    public void addShip(int size){
        BlackBoard2.currentPlayer.addShip(sinkFleetEntityManager.spawnShip(BlackBoard2.currentPlayer.getActualPostionX(), 
                                          BlackBoard2.currentPlayer.getActualPostionY(), size, BlackBoard2.currentPlayer.getIsHorizontal()));
            
    }

    @Override
    public void gameResized() {
        Blackboard.cellSize = getWidth() < getHeight() ? getWidth() / cols : getHeight() / rows;
    }

}
