package com.grupo.game.core;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.core.Game;
import com.grupo.engine.input.KeyboardManager;

import com.grupo.game.gameentities.Player;
import com.grupo.game.input.NumericKeyboardManager;


/**
 * Represents the main game logic for the Sink Fleet game.
 */
public class SinkFleetGame extends Game {
    private final int rows;
    private final int cols;
    private final SinkFleetEntityManager sinkFleetEntityManager;
    private BlackBoard2.Mode mode;

    private Player player1;
    private Player player2;

    //Idica si el jugador esta en la fase de colocar barcos
    private boolean principio;

    /**
     * Constructs a SinkFleetGame instance with the specified parameters.
     *
     * @param width       The width of the game window.
     * @param height      The height of the game window.
     * @param rows        The number of rows in the game board grid.
     * @param cols        The number of columns in the game board grid.
     * @param fpsLimit    The frame rate limit for the game.
     * @param updateLimit The update rate limit for the game.
     * @param maxEntities The maximum number of entities in the game.
     * @param mode        The game mode (single player or multi player).
     */
    public SinkFleetGame(int width, int height, int rows, int cols, float fpsLimit, float updateLimit, int maxEntities, BlackBoard2.Mode mode) {
        super(width, height, fpsLimit, updateLimit, maxEntities);
        this.rows = rows;
        this.cols = cols;
        sinkFleetEntityManager = (SinkFleetEntityManager) Blackboard.entityManager;
        this.principio = true;
        setMode(mode);
    }

    public boolean isPrincipio() {
        return principio;
    }

    /**
     * Sets the game mode for the Sink Fleet game.
     *
     * @param mode The game mode to set (single player or multiplayer).
     */
    public void setMode(BlackBoard2.Mode mode) {
        this.mode = mode;
        if (mode == BlackBoard2.Mode.SINGLE_PLAYER) {
            //!TODO: Implement single player mode
        } else if (mode == BlackBoard2.Mode.MULTI_PLAYER) {
            initPlayers(rows, cols);

        }
    }

    /**
     * Initializes the players for the Sink Fleet game.
     *
     * @param rowsshoot The number of rows in the game board.
     * @param cols      The number of columns in the game board.
     */
    private void initPlayers(int rowsshoot, int cols) {
        KeyboardManager km2 = new NumericKeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga

        KeyboardManager km1 = new NumericKeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga
        this.player1 = sinkFleetEntityManager.creatPlayer("player1",0, 0, km1, rows, cols);
        BlackBoard2.currentPlayer = player1;
        this.player2 = sinkFleetEntityManager.creatPlayer("Player2",0, 0, km2, rows, cols);
        BlackBoard2.opponentPlayer = player2;
        //System.out.println(player1.addShip(99, 99, cols, principio));


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
     * Updates the game state based on user input and game phase.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        BlackBoard2.currentPlayer.update(deltaTime);
    }


    //TODO: REMOVE????
    /**
     * Adds a new ship to the current player's fleet.
     * This method creates a new ship with the specified size and adds it to the fleet of the current player.
     * The position and orientation of the new ship are determined by the current position and orientation
     * of the player on the game board.
     *
     * @param size The size of the ship to be added to the fleet.
     */
    //public void addShip(int size) {
    //    BlackBoard2.currentPlayer.addShip(BlackBoard2.currentPlayer.getActualPostionX(),
    //            BlackBoard2.currentPlayer.getActualPostionY(), size, BlackBoard2.currentPlayer.getIsHorizontal());

    //}

    /**
     * Handles resizing of the game window.
     * Adjusts the cell size used for rendering based on the new window dimensions.
     */
    @Override
    public void gameResized() {
        //Blackboard.cellSize = getWidth() < getHeight() ? getWidth() / (cols * 2) : getHeight() / rows;

        int largerDimension = Math.max(getWidth(), getHeight());
        int largerDimensionOfGameBoard = Math.max(cols, rows);
        Blackboard.cellSize = Math.round((float) largerDimension / (2 * largerDimensionOfGameBoard) * 0.91f);
    }
}
