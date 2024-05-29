package com.grupo.game.core;

import com.grupo.engine.core.AssetManager;
import com.grupo.engine.core.EntityManager;

public class SinkFleetEntityManager extends EntityManager {


    public SinkFleetEntityManager(int maxEntities) {
        super(maxEntities);
    }

    @Override
    public AssetManager createAssetManager() {
        return null;
    }
}
