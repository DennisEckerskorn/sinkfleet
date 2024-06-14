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

    /**
     * Retrieves whether the game is in the ship placement phase.
     *
     * @return true if the game is in the ship placement phase, false otherwise.
     */
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
            initPlayer(rows, cols);
        } else if (mode == BlackBoard2.Mode.MULTI_PLAYER) {
            initPlayers(rows, cols);

        }
    }

    /**
     * Initializes the player for single player mode.
     *
     * @param rowsshoot The number of rows in the shooting board.
     * @param cols      The number of columns in the game board.
     */
    public void initPlayer(int rowsshoot, int cols) {
        BlackBoard2.resetListenner = true;
        KeyboardManager km1 = new NumericKeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga
        this.player1 = sinkFleetEntityManager.creatPlayer("player1", 0, 0, km1, rows, cols);

        this.player2 = sinkFleetEntityManager.creatNPCPlayer(cols, cols, rowsshoot, cols);
        sinkFleetEntityManager.removeAllPlayeableEntities();
        BlackBoard2.currentPlayer = player1;
        BlackBoard2.opponentPlayer = player2;
    }

    /**
     * Initializes players for multiplayer mode.
     *
     * @param rowsshoot The number of rows in the shooting board.
     * @param cols      The number of columns in the game board.
     */
    private void initPlayers(int rowsshoot, int cols) {
        BlackBoard2.resetListenner = true;
        KeyboardManager km2 = new NumericKeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga

        KeyboardManager km1 = new NumericKeyboardManager('w', 's', 'a', 'd', 'f', ' '); //! Cambiar cuando Diego lo tenga
        this.player1 = sinkFleetEntityManager.creatPlayer("player1", 0, 0, km1, rows, cols);

        this.player2 = sinkFleetEntityManager.creatPlayer("Player2", 0, 0, km2, rows, cols);

        sinkFleetEntityManager.removeAllPlayeableEntities();

        BlackBoard2.currentPlayer = player1;
        BlackBoard2.opponentPlayer = player2;
        //System.out.println(player1.addShip(99, 99, cols, principio));


    }

    /**
     * Creates an instance of the entity manager for Sink Fleet game.
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

    /**
     * Handles resizing of the game window by adjusting the rendering cell size.
     * This method ensures that the game board and its elements scale proportionately
     * to fit within the new dimensions of the window.
     */
    @Override
    public void gameResized() {
        // Problems with resizing, needs to be solved in the future, adjusted to certain resize.
        //Blackboard.cellSize = getWidth() < getHeight() ? getWidth() / (cols * 2) : getHeight() / rows;

        // Determine the larger dimension of the game window
        int largerDimension = Math.max(getWidth(), getHeight());

        // Determine the maximum size of the game board
        int largerDimensionOfGameBoard = Math.max(cols, rows);

        // Calculate the new cell size based on the larger dimension and game board size
        Blackboard.cellSize = Math.round((float) largerDimension / (2 * largerDimensionOfGameBoard) * 0.91f);
    }
}
