package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class Ship extends Entity {

    private List<Float> positionsX;
    private List<Float> positionsY;
    private List<Boolean> hits;
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
        positionsX = new ArrayList<>(size);
        positionsY = new ArrayList<>(size);
        hits = new ArrayList<>(size);
        this.size = size;
        this.isHorizontal = isHorizontal;

        for (int i = 0; i < size; i++) {
            if (isHorizontal) {
                positionsX.add(x + i * (width / (size - 1)));
                positionsY.add(y);
            } else {
                positionsX.add(x);
                positionsY.add(y + i * (height / (size - 1)));
            }
            hits.add(false);
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
        for (int i = 0; i < positionsX.size(); i++) {
            if (positionsX.get(i).equals(hitX) && positionsY.get(i).equals(hitY) && !hits.get(i)) {
                hits.set(i, true);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un barco ha sido destruido
     *
     * @return true si ha sido destruido, de lo contrario false.
     */
    public boolean isSunk() {
        for (Boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }

    public int getSize() {
        return size;
    }

    public List<Float> getPositionsX() {
        return positionsX;
    }

    public List<Float> getPositionsY() {
        return positionsY;
    }

    public List<Boolean> getHits() {
        return hits;
    }

    public boolean isHorizontal() {
        return isHorizontal;
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
}
