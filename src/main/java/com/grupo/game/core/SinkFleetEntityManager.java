package com.grupo.game.core;

import com.grupo.engine.core.AssetManager;
import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.EntityManager;
import com.grupo.game.gameentities.Ship;

public class SinkFleetEntityManager extends EntityManager {

    public SinkFleetEntityManager(int maxEntities) {
        super(maxEntities);
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
        Ship ship = new Ship(x, y, Blackboard.cellSize, Blackboard.cellSize, size, isHorizontal, 1, 1);
        addEntity(ship);
        System.out.println("Ship spawned at (" + x + ", " + y + "), size: " + size + ", isHorizontal: " + isHorizontal);
        return ship;
    }
}
