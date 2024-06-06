package com.grupo.game.core;

import java.awt.RenderingHints.Key;

import com.grupo.engine.core.AssetManager;
import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.config.Settings;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;

public class SinkFleetEntityManager extends EntityManager {

    private final Ship[] ships;
    private int shipIndex;
    

    public SinkFleetEntityManager(int maxEntities) {
        super(maxEntities);
        this.ships = new Ship[maxEntities * Settings.NUM_SHIPS];
        this.shipIndex = 0;

    }

    

    /**
     * Creates a playeable entity and adds it to the entity manager.
     *
     * @param x               The x-coordinate where the player will be spawned.
     * @param y               The y-coordinate where the player will be spawned.
     * @param keyboardManager The keyboard manager that will be used by the player.
     * @return The created player entity.
     */
    public Player creatPlayer(float x, float y, KeyboardManager keyboardManager, int rows, int cols) {
        Player player = new Player(x, y, Settings.WIDTH, Settings.HEIGHT,
                Settings.PLAYER_HP, Settings.PLAYER_DAMAGE, keyboardManager, rows, cols);

        addEntity(player);

        return player;
    }

    /**
     * Adds an entity to the entity manager.
     * If the entity is a Ship, it will be added to the ships array.
     * Otherwise, it will be added to the entities array.
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

    @Override
    public AssetManager createAssetManager() {
        return new SinkFleetAssetManager();
    }

    /**
     * Spawns a Ship entity at the specified coordinates and adds it to
     * the entity manager.
     *
     * @param x The x-coordinate where the ship will be spawned.
     * @param y The y-coordinate where the ship will be spawned.
     * @return The spawned Ship entity.
     */
    public Ship spawnShip(float x, float y, int size, boolean isHorizontal) {
        Ship fleet = new Ship(x, y, size, x, size, isHorizontal, y, size);
        addEntity(fleet);
        return fleet;
    }

}
