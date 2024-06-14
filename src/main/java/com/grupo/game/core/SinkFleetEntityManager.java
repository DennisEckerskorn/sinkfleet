package com.grupo.game.core;


import com.grupo.engine.core.AssetManager;

import com.grupo.engine.core.EntityManager;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.NPCPlayer;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;
import com.grupo.game.gameentities.strategy.Strategy1;

/**
 * Manages the entities specific to the Sink Fleet game.
 */
public class SinkFleetEntityManager extends EntityManager {

    private final Ship[] ships;
    private int shipIndex;

    /**
     * Constructs a SinkFleetEntityManager with the specified maximum number of entities.
     *
     * @param maxEntities The maximum number of entities.
     */
    public SinkFleetEntityManager(int maxEntities) {
        super(maxEntities);
        this.ships = new Ship[maxEntities * Settings.NUM_SHIPS];
        this.shipIndex = 0;

    }


    /**
     * Creates a player entity and adds it to the entity manager.
     *
     * @param x               The x-coordinate where the player will be spawned.
     * @param y               The y-coordinate where the player will be spawned.
     * @param keyboardManager The keyboard manager that will be used by the player.
     * @param rows            The number of rows on the game board.
     * @param cols            The number of columns on the game board.
     * @return The created player entity.
     */
    public Player creatPlayer(String nombre, float x, float y, KeyboardManager keyboardManager, int rows, int cols) {
        Player player = new Player(nombre, x, y, Settings.WIDTH, Settings.HEIGHT,
                Settings.PLAYER_HP, Settings.PLAYER_DAMAGE, keyboardManager, rows, cols);

        addEntity(player);

        return player;
    }

    /**
     * Creates an NPC player entity and adds it to the entity manager.
     *
     * @param x    The x-coordinate where the NPC player will be spawned.
     * @param y    The y-coordinate where the NPC player will be spawned.
     * @param rows The number of rows on the game board.
     * @param cols The number of columns on the game board.
     * @return The created NPC player entity.
     */
    public Player creatNPCPlayer(float x, float y, int rows, int cols) {
        Player player = new NPCPlayer(x, y, Settings.WIDTH, Settings.HEIGHT,
                Settings.PLAYER_HP, Settings.PLAYER_DAMAGE, rows, cols, new Strategy1());

        addEntity(player);

        return player;
    }

    /**
     * Adds an entity to the entity manager.
     * If the entity is a Ship, it will be added to the ships array. Otherwise, it will be added to the entities array.
     *
     * @param entity The entity to be added.
     * @return True if the entity was added successfully, false otherwise.
     */
    @Override
    public boolean addEntity(Entity entity) {
        if (entity instanceof Ship) {
            if (shipIndex < ships.length) {
                ships[shipIndex++] = (Ship) entity;
                return true;
            }
            return false;
        }
        return super.addEntity(entity);
    }

    /**
     * Creates an AssetManager instance specific to the Sink Fleet game.
     *
     * @return A new instance of SinkFleetAssetManager.
     */
    @Override
    public AssetManager createAssetManager() {
        return new SinkFleetAssetManager();
    }

    /**
     * Spawns a Ship entity at the specified coordinates and adds it to the entity manager.
     *
     * @param x            The x-coordinate where the ship will be spawned.
     * @param y            The y-coordinate where the ship will be spawned.
     * @param size         The size of the ship.
     * @param isHorizontal True if the ship is placed horizontally, false if vertically.
     * @param direction    The direction of the ship.
     * @return The spawned Ship entity, or null if spawning failed due to collision.
     */
    public Ship createShip(float x, float y, int size, boolean isHorizontal, boolean direction) {
        Ship fleet = new Ship(x, y, size, x, size, isHorizontal, y, size, direction);

        return fleet;
    }

    /**
     * Processes the input for the current player.
     * Delegates the input processing to the current player entity.
     */
    @Override
    public void processInput() {
        BlackBoard2.currentPlayer.processInput();
    }

    /**
     * Removes all playable entities from the entity manager.
     */
    public void removeAllPlayeableEntities() {
        for (PlayableEntity ship : super.getPlayableEntities()) {
            removeEntity(ship);
        }
    }

}
