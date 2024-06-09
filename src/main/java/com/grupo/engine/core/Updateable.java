package com.grupo.engine.core;

/**
 * The Updateable interface defines a contract for objects that can be updated during the game loop.
 */
public interface Updateable {
    /**
     * Updates the object's state based on the elapsed time since the last update.
     *
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    void update(double deltaTime);

    /**
     * Performs final updates after the main update cycle.
     *
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    void lastUpdate(double deltaTime);

    /**
     * Performs post-update tasks after the last update cycle.
     *
     * @param deltaTime The time elapsed since the last update, in seconds.
     */
    void postUpdate(double deltaTime);
}


