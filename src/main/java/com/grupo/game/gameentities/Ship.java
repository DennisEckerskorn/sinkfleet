package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;
import com.grupo.engine.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Entity {
    private List<ShipFragments> shipFragments;
    private Vector2 position;
    private int size;
    private boolean isHorizontal;

    /**
     * Constructor de la clase Ship.
     *
     * @param x            Coordenada X inicial del barco.
     * @param y            Coordenada Y inicial del barco.
     * @param width        Ancho total del barco.
     * @param height       Altura del barco.
     * @param size         Número de segmentos del barco.
     * @param isHorizontal Indica true si el barco es horizontal.
     * @param hp           Puntos de vida del barco.
     * @param damage       Daño que puede causar el barco.
     */
    public Ship(float x, float y, float width, float height, int size, boolean isHorizontal, float hp, float damage) {
        super(x, y, width, height, hp, damage);
        this.shipFragments = new ArrayList<>();
        this.position = new Vector2(x, y);
        this.size = size;
        this.isHorizontal = isHorizontal;

        for (int i = 0; i < size; i++) {
            float fragmentX = isHorizontal ? x + i * (width / size) : x;
            float fragmentY = isHorizontal ? y : y + i * (height / size);
            shipFragments.add(new ShipFragments(fragmentX, fragmentY, width / size, height / size, hp, damage));
        }


    }

    /**
     * Verifica la posición del golpe en el barco.
     *
     * @param hitX Coordenada X del golpe.
     * @param hitY Coordenada Y del golpe.
     * @return true si el golpe está dentro del área del barco y no ha sido golpeado antes.
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
     * Verifica si un barco ha sido destruido.
     *
     * @return true si ha sido destruido, de lo contrario false.
     */
    public boolean isSunk() {
        for (ShipFragments fragment : shipFragments) {
            if (!fragment.isHit()) {
                return false;
            }
        }
        return true;
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

    public void setX(float x) {
        this.position.setX(x);  // Update the position of the ship itself
        for (int i = 0; i < size; i++) {
            ShipFragments fragment = shipFragments.get(i);
            if (isHorizontal) {
                fragment.setPosition(x + i * (getWidth() / size), fragment.getY());
            } else {
                fragment.setPosition(x, fragment.getY());
            }
        }
    }

    public void setY(float y) {
        this.position.setY(y);  // Update the position of the ship itself
        for (int i = 0; i < size; i++) {
            ShipFragments fragment = shipFragments.get(i);
            if (isHorizontal) {
                fragment.setPosition(fragment.getX(), y);
            } else {
                fragment.setPosition(fragment.getX(), y + i * (getHeight() / size));
            }
        }
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
