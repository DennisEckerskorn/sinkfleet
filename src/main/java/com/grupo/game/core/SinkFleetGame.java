package com.grupo.game.core;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.core.Game;
import com.grupo.engine.input.KeyboardManager;

import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;
import com.grupo.game.gameentities.ShipFragments;


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

    /**
     * Initializes the players for the Sink Fleet game.
     *
     * @param rowsshoot The number of rows in the game board.
     * @param cols      The number of columns in the game board.
     */
    private void initPlayers(int rowsshoot, int cols) {
        KeyboardManager km2 = new KeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga

        KeyboardManager km1 = new KeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga
        this.player1 = sinkFleetEntityManager.creatPlayer(0, 0, km1, rows, cols);
        BlackBoard2.currentPlayer = player1;
        this.player2 = sinkFleetEntityManager.creatPlayer(0, 0, km2, rows, cols);
        Blackboard.entityManager.addEntity(player1);
        Blackboard.entityManager.addEntity(player2);
    }

    /**
     * Creates an instance of the entity manager.
     *
     * @param maxEntities The maximum number of entities the manager can handle.
     * @return An instance of the SinkFleetEntityManager.
     */
    @Override
    public EntityManager createEntityManager(int maxEntities) {
        return new SinkFleetEntityManager(maxEntities);
    }

    /**
     * Updates the game state.
     * <p>
     * This method is called to update the game state based on the user input and current game phase.
     * It checks if a button is pressed and if it's the beginning phase of the game. If so, it verifies
     * whether the player has selected a valid position to place a ship. If the position is valid, it
     * checks for collision with existing ships. If there's no collision, a new ship is added to the player's
     * fleet. If a collision is detected, an error message is displayed, indicating that ships cannot overlap.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        if (BlackBoard2.buttonPressed) {
            if (principio) {
                if (BlackBoard2.currentPlayer.getActualPostionX() != -1 && BlackBoard2.currentPlayer.getActualPostionY() != -1) {
                    // TODO: Hay que mirar que el barco no se sobreponga con otro sale error sino.
                    if (!checkCollision(4)) {
                        addShip(4);
                    } else {
                        //TODO: Lanzar excepcion en vez del mensaje???
                        System.out.println("Error: Los barcos no se pueden sobreponer");
                    }

                    BlackBoard2.buttonPressed = false;
                }
            }
        }
    }

    /**
     * Checks if there is a collision between the current player's ship and existing ships on the game board.
     * <p>
     * This method iterates through all existing ships on the game board and checks if the placement of the
     * current player's ship with the specified size would overlap with any existing ships. It considers both
     * horizontal and vertical orientations of the current player's ship when performing the collision check.
     *
     * @param size The size of the ship to be checked for collision.
     * @return true if there is a collision, false otherwise.
     */
    private boolean checkCollision(int size) {
        //Obtener la posicion actual del jugador:
        int x = Math.round(BlackBoard2.currentPlayer.getActualPostionX());
        int y = Math.round(BlackBoard2.currentPlayer.getActualPostionY());

        //Recorrer todos los barcos existentes
        for (Ship ship : BlackBoard2.currentPlayer.getShips()) {
            for (ShipFragments fragment : ship.getShipFragments()) {
                if (BlackBoard2.currentPlayer.getIsHorizontal()) {
                    for (int i = 0; i < size; i++) {
                        if (fragment.getX() == x + i && fragment.getY() == y) {
                            return true; // Hay colision
                        }
                    }
                } else {
                    for (int i = 0; i < size; i++) {
                        if (fragment.getX() == x && fragment.getY() == y + i) {
                            return true; // Hay colision
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Adds a new ship to the current player's fleet.
     * This method creates a new ship with the specified size and adds it to the fleet of the current player.
     * The position and orientation of the new ship are determined by the current position and orientation
     * of the player on the game board.
     *
     * @param size The size of the ship to be added to the fleet.
     */
    public void addShip(int size) {
        BlackBoard2.currentPlayer.addShip(sinkFleetEntityManager.spawnShip(BlackBoard2.currentPlayer.getActualPostionX(),
                BlackBoard2.currentPlayer.getActualPostionY(), size, BlackBoard2.currentPlayer.getIsHorizontal()));

    }

    /**
     * Handles the resizing of the game window.
     * This method adjusts the cell size used for rendering based on the new dimensions of the game window.
     * It calculates the cell size to ensure that the game board remains properly scaled within the resized window.
     */
    @Override
    public void gameResized() {
        Blackboard.cellSize = getWidth() < getHeight() ? getWidth() / cols : getHeight() / rows;
    }

}
