package com.grupo.engine.core;

import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The EntityManager class manages the entities within the game world.
 * It provides methods for adding, removing, and updating entities, as well as accessing playable entities and the asset manager.
 */
public abstract class EntityManager implements Updateable {
    private final Entity[] entities;
    private int numEntities;
    private final List<PlayableEntity> playableEntities;
    private final AssetManager assetManager;

    /**
     * Constructs an EntityManager with a specified maximum number of entities.
     *
     * @param maxEntities The maximum number of entities the manager can handle.
     */
    public EntityManager(int maxEntities) {
        entities = new Entity[maxEntities];
        numEntities = 0;
        playableEntities = new ArrayList<>();
        assetManager = createAssetManager();
    }

    /**
     * Adds an entity to the entity manager.
     *
     * @param entity The entity to be added.
     * @return True if the entity was added successfully, false otherwise.
     */
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

    public abstract AssetManager createAssetManager();

    /**
     * Removes an entity from the entity manager.
     *
     * @param entity The entity to be removed.
     */
    public void removeEntity(Entity entity) {
        for (int i = 0; i < numEntities; i++) {
            Entity other = entities[i];
            if (entity.equals(other)) {
                entities[i] = entities[numEntities - 1];
                entities[numEntities - 1] = other;
                numEntities--;
            }
        }
    }

    public List<PlayableEntity> getPlayableEntities() {
        return playableEntities;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public int getNumEntities() {
        return numEntities;
    }

    /**
     * Updates all entities managed by the entity manager.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        for (int i = 0; i < numEntities; i++) {
            entities[i].update(deltaTime);
        }
    }

    /**
     * Executes the last update phase for all entities managed by the entity manager.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void lastUpdate(double deltaTime) {
        for (int i = 0; i < numEntities; i++) {
            entities[i].lastUpdate(deltaTime);
        }
    }

    /**
     * Executes the post-update phase for all entities managed by the entity manager.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void postUpdate(double deltaTime) {
        for (int i = 0; i < numEntities; i++) {
            entities[i].postUpdate(deltaTime);
        }
    }

    /**
     * Processes input for playable entities.
     */
    public void processInput() {
        for (PlayableEntity playableEntity : playableEntities) {
            playableEntity.processInput();
        }
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}