package com.denniseckerskorn.engine.core;

import com.denniseckerskorn.engine.entities.Entity;
import com.denniseckerskorn.engine.entities.PlayableEntity;
import com.denniseckerskorn.engine.pool.ObjectPool;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityManager implements Updateable {
    private final Entity[] entities;
    private int numEntities;
    private final List<PlayableEntity> playableEntities;
    private final AssetManager assetManager;
    //private final ObjectPool<Entity> entityObjectPool;

    public EntityManager(int maxEntities) {
        entities = new Entity[maxEntities];
        numEntities = 0;
        playableEntities = new ArrayList<>();
        assetManager = createAssetManager();
        //entityObjectPool = new ObjectPool<>(Entity.class, maxEntities);
    }

    public boolean addEntity(Entity entity) {
        if (numEntities < entities.length) {
            entities[numEntities++] = entity;
            if (entity instanceof PlayableEntity) {
                playableEntities.add((PlayableEntity) entity);
            }
            return true;
        }
        return false;
    }

    public void removeEntity(Entity entity) {
        //TODO: Cambios de arrays, pool, evitar eliminar, tener en cuenta eliminar instanceof tambien
        for(int i = 0; i < numEntities; i++) {
            Entity other = entities[i];
            if(entity.equals(other)) {
                entities[i] = entities[numEntities - 1];
                entities[numEntities - 1] = other;
                numEntities--;
               // entityObjectPool.returnObject(entity);
                if (entity instanceof PlayableEntity) {
                    playableEntities.remove(entity);
                }
                break;

            }
        }
    }

  // TODO: Add borrow object method

    public abstract AssetManager createAssetManager();

    public Entity[] getEntities() {
        return entities;
    }

    public int getNumEntities() {
        return numEntities;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void update(double deltaTime) {
        for (int i = 0; i < numEntities; i++) {
            entities[i].update(deltaTime);
        }
    }

    @Override
    public void lastUpdate(double deltaTime) {
        for (int i = 0; i < numEntities; i++) {
            entities[i].lastUpdate(deltaTime);
        }
    }

    @Override
    public void postUpdate(double deltaTime) {
        for (int i = 0; i < numEntities; i++) {
            entities[i].postUpdate(deltaTime);
        }
    }

    public void processInput() {
        for (PlayableEntity playableEntity : playableEntities) {
            playableEntity.processInput();
        }
    }


}
