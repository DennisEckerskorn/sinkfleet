package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;

public class Ship extends Entity {

    //Damos por hecho que el tamaño del barco es de 3 posiciones(temporalmente para probar)
    private float pos1;
    private float pos2;
    private float pos3;
    private boolean hit;

    public Ship(float x, float y, float width, float height, float hp, float damage) {
        super(x, y, width, height, hp, damage);
        pos1 = x;
        pos2 = x + width / 2;
        pos3 = x + width;
        hit = false;
    }

    /**
     * Verifica la posición del golpe en el barco.
     *
     * @param hitX Coordenada X del golpe.
     * @param hitY Coordenada Y del golpe.
     * @return true si el golpe está dentro del área del barco y el barco no ha sido golpeado.
     */
    public boolean checkHitPosition(float hitX, float hitY) {
        if (!hit) {
            if (hitX >= pos1 && hitX <= pos3 && hitY == getY()) {
                hit = true;
                return true;
            }
        }
        return false;
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
