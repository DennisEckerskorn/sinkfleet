package com.grupo.game.gameentities;

import java.util.Random;

/**
 * Represents a non-player controlled player entity in the Sink Fleet game.
 */
public class NPCPlayer extends Player {
    private Random random;

    /**
     * Constructs an NPCPlayer object with the specified parameters.
     *
     * @param x      The initial X-coordinate of the NPCPlayer.
     * @param y      The initial Y-coordinate of the NPCPlayer.
     * @param width  The width of the NPCPlayer.
     * @param height The height of the NPCPlayer.
     * @param hp     The health points of the NPCPlayer.
     * @param damage The damage the NPCPlayer can cause.
     * @param rows   The number of rows in the game board grid.
     * @param cols   The number of columns in the game board grid.
     */
    public NPCPlayer(float x, float y, float width, float height, int hp, float damage, int rows, int cols) {
        super("NPC",x, y, width, height, hp, damage, null, rows, cols);
        this.random = new Random();
        //TODO Auto-generated constructor stub
    }

    /**
     * Updates the NPCPlayer's state based on the current game state.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        //setActualPostionX(random.nextInt(getRows()));
        // setActualPostionY(random.nextInt(getCols()));
        // setHorizontal(random.nextBoolean());
    }

    /**
     * Performs the last update of the NPCPlayer.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void lastUpdate(double deltaTime) {
    }

    /**
     * Performs post-update operations on the NPCPlayer.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void postUpdate(double deltaTime) {
    }

    /**
     * Processes input for the NPCPlayer.
     */
    @Override
    public void processInput() {
    }


}
