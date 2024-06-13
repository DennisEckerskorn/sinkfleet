package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;
import com.grupo.engine.math.Vector2;
import com.grupo.game.config.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ship entity in the Sink Fleet game.
 */
public class Ship extends Entity {
    private List<ShipFragments> shipFragments;
    private Vector2 position;
    private int size;
    private boolean isHorizontal;

    /**
     * Constructs a Ship entity with the specified parameters.
     *
     * @param x            The initial X-coordinate of the ship.
     * @param y            The initial Y-coordinate of the ship.
     * @param width        The total width of the ship.
     * @param height       The height of the ship.
     * @param size         The number of segments of the ship.
     * @param isHorizontal Indicates whether the ship is horizontal.
     * @param hp           The health points of the ship.
     * @param damage       The damage the ship can cause.
     * @param direction    The direction of the ship (true for positive direction, false for negative direction).
     */
    public Ship(float x, float y, float width, float height, int size, boolean isHorizontal, float hp, float damage, boolean direction) {
        super(x, y, width, height, hp, damage);
        this.shipFragments = new ArrayList<>();
        this.position = new Vector2(x, y);
        this.size = size;
        this.isHorizontal = isHorizontal;

        // Generate ship fragments based on direction
        if (direction) {
            for (int i = 0; i < size; i++) {
                if (isHorizontal) {
                    shipFragments.add(new ShipFragments(x + i, y, width, height, hp, damage));
                } else {
                    shipFragments.add(new ShipFragments(x, y + i, width, height, hp, damage));
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (isHorizontal) {
                    shipFragments.add(new ShipFragments(x - i, y, width, height, hp, damage));
                } else {
                    shipFragments.add(new ShipFragments(x, y - i, width, height, hp, damage));
                }
            }
        }

    }

    /**
     * Checks if the given position corresponds to a hit on the ship.
     *
     * @param hitX The X-coordinate of the hit.
     * @param hitY The Y-coordinate of the hit.
     * @return true if the hit is within the ship's area and has not been hit before, false otherwise.
     */
    public boolean isHitPosition(float hitX, float hitY) {
        for (ShipFragments fragment : shipFragments) {
            if (fragment.getX() == hitX && fragment.getY() == hitY) {
                fragment.hit();
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the ship has been sunk.
     *
     * @return true if the ship has been sunk, false otherwise.
     */
    public boolean isSunk() {
        for (ShipFragments fragment : shipFragments) {
            if (!fragment.isHit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the ship collides with another ship.
     *
     * @param fleet The ship to check collision with.
     * @return true if the ship collides with the given ship, false otherwise.
     */
    public boolean collides(Ship fleet) {
        for (ShipFragments fragment1 : shipFragments) {
            for (ShipFragments fragment2 : fleet.getShipFragments()) {
                if (fragment1.getX() == fragment2.getX() && fragment1.getY() == fragment2.getY()) {
                    return true; //Hay colision
                }
            }
        }
        return false; // No hay colision
    }

    public int getSize() {
        return size;
    }

    public List<ShipFragments> getShipFragments() {
        return shipFragments;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    public void setSize(int size) {
        this.size = size;
    }



    /**
     * Updates the ship.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {

    }

    /**
     * Performs the last update of the ship.
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ship{");
        sb.append("position=").append(position);
        sb.append(", fragnent position=");
        for (ShipFragments fragment : shipFragments) {
            sb.append(fragment.getPosition() + " ");
        }

        return sb.toString();
    }
}
