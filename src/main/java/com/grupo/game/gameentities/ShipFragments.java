package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;

/**
 * Represents fragments of a ship in the game.
 */
public class ShipFragments extends Entity {
    private boolean hit;

    /**
     * Constructor for creating ShipFragments.
     *
     * @param x      The x-coordinate of the ship fragment.
     * @param y      The y-coordinate of the ship fragment.
     * @param width  The width of the ship fragment.
     * @param height The height of the ship fragment.
     * @param hp     The hit points of the ship fragment.
     * @param damage The damage caused by the ship fragment.
     */
    public ShipFragments(float x, float y, float width, float height, float hp, float damage) {
        super(x, y, width, height, hp, damage);
        this.hit = false;
    }

    /**
     * Checks if the ship fragment has been hit.
     *
     * @return true if the ship fragment is hit, false otherwise.
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Marks the ship fragment as hit.
     */
    public void hit() {
        this.hit = true;
    }

    /**
     * Checks if the ship fragment is within the grid bounds.
     *
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     * @return true if the ship fragment is within the grid bounds, false otherwise.
     */
    public boolean inGrid(int rows, int cols) {
        return (getX() >= 0 && getX() < cols) && (getY() >= 0 && getY() < rows);
    }

    /**
     * Updates the ship fragment.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {

    }

    /**
     * Performs the last update of the ship fragment.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void lastUpdate(double deltaTime) {

    }

    /**
     * Performs post-update operations on the ship fragment.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void postUpdate(double deltaTime) {

    }

    /**
     * Returns a string representation of the ship fragment.
     *
     * @return A string representing the ship fragment's state.
     */
    @Override
    public String toString() {
        return "ShipFragments{" +
                "hit=" + hit +
                '}';
    }
}
