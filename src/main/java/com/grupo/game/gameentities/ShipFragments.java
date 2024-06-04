package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;

public class ShipFragments extends Entity {
    private boolean hit;

    public ShipFragments(float x, float y, float width, float height, float hp, float damage) {
        super(x, y, width, height, hp, damage);
        this.hit = false;
    }

    public boolean isHit() {
        return hit;
    }

    public void hit() {
        this.hit = true;
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
        return "ShipFragments{" +
                "hit=" + hit +
                '}';
    }
}
