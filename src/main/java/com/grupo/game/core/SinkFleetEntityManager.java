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

    private final Ship ships[];
    private int shipIndex;
    private int shipUsed;
    public SinkFleetEntityManager(int maxEntities) {
        super(maxEntities);
        this.ships = new Ship[maxEntities * Settings.NUM_SHIPS];
        this.shipIndex = 0;
        this.shipUsed = 0;
        createShips();
        
    }

    /**
     * Creates the ships that will be used in the game.
     */
    private void createShips() {
        for (int i = 0; i < ships.length; i++) {
             addEntity(new Ship(-1, -1,-1, -1, -1, true, 1, 1));    
        }
    }

    /**
     * Creates a playeable entity and adds it to the entity manager.
     *
     * @param x               The x-coordinate where the player will be spawned.
     * @param y               The y-coordinate where the player will be spawned.
     * @param keyboardManager The keyboard manager that will be used by the player.
     * @return The created player entity.
     */
    public Player creatPlayer(float x, float y, KeyboardManager keyboardManager) {
        Player player = new Player(x, y, Settings.WIDTH, Settings.HEIGHT,
               Settings.PLAYER_HP, Settings.PLAYER_DAMAGE, keyboardManager);

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

    /** PRUEBA:
     * Spawns a Ship entity at the specified coordinates and adds it to
     * the entity manager.
     *
     * @param x The x-coordinate where the ship will be spawned.
     * @param y The y-coordinate where the ship will be spawned.
     * @return The spawned Ship entity.
     */
    public Ship spawnShip(float x, float y, int size, boolean isHorizontal) {
        if (shipUsed < shipIndex) {
            Ship ship = ships[shipUsed++];
            ship.setSize(size);
            ship.setX(x);
            ship.setX(y);
            ship.setHorizontal(isHorizontal);
            return ship;
        }
        return null;
    }
       
}
