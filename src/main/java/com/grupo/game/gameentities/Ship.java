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
     */
    public Ship(float x, float y, float width, float height, int size, boolean isHorizontal, float hp, float damage, Settings.Direction direction) {
        super(x, y, width, height, hp, damage);
        this.shipFragments = new ArrayList<>();
        this.position = new Vector2(x, y);
        this.size = size;
        this.isHorizontal = isHorizontal;

        // TODO: los fragmentos del barco se crean en base a la direccion
        for (int i = 0; i < size; i++) {
            float fragmentX = isHorizontal ? x + i * (width / size) : x;
            float fragmentY = isHorizontal ? y : y + i * (height / size);
            shipFragments.add(new ShipFragments(fragmentX, fragmentY, width / size, height / size, hp, damage));
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
            if (fragment.getX() == hitX && fragment.getY() == hitY && !fragment.isHit()) {
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
        //TODO: COMPROBAR SI COLISIONA CON OTRO BARCO, ES CORRECTO????
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

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void lastUpdate(double deltaTime) {

    }

    @Override
    public void postUpdate(double deltaTime) {

    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipFragments=" + shipFragments +
                ", position=" + position +
                ", size=" + size +
                ", isHorizontal=" + isHorizontal +
                '}';
    }
}
