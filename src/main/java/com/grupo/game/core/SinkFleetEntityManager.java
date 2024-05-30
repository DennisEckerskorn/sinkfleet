package com.grupo.game.core;

import com.grupo.engine.core.AssetManager;
import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.game.gameentities.Ship;

public class SinkFleetEntityManager extends EntityManager {
    private final Ship ship;

    public SinkFleetEntityManager(int maxEntities) {
        super(maxEntities);
        ship = new Ship(0, 0, Blackboard.cellSize, Blackboard.cellSize, 2, true, 1, 1);
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
    public Ship spawnShip(float x, float y) {
        ship.setPosition(x, y);
        addEntity(ship);
        return ship;
    }
}
