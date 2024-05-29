package com.grupo.engine.core;

import com.grupo.engine.entities.Entity;

public class Collider {
    private float xRight;
    private float xLeft;
    private float yUp;
    private float yDown;
    private int mask;

    private static Collider collider1WorldCoordinates = new Collider();
    private static Collider collider2WorldCoordinates = new Collider();

    private Collider() { }

    public Collider(float xLeft, float xRight, float yDown, float yUp, int mask) {
        this.xLeft = xLeft;
        this.xRight = xRight;
        this.yDown = yDown;
        this.yUp = yUp;
        this.mask = mask;
    }

    public float getxLeft() {
        return xLeft;
    }

    public float getxRight() {
        return xRight;
    }

    public float getyDown() {
        return yDown;
    }

    public float getyUp() {
        return yUp;
    }

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public void setxLeft(float xLeft) {
        this.xLeft = xLeft;
    }

    public void setxRight(float xRight) {
        this.xRight = xRight;
    }

    public void setyDown(float yDown) {
        this.yDown = yDown;
    }

    public void setyUp(float yUp) {
        this.yUp = yUp;
    }

    public static boolean checkCollision(Entity e1, Entity e2) {
        Collider c1 = e1.getCollider();
        Collider c2 = e2.getCollider();
        if (c1 == null || c2 == null)
            return false;
        if ((c1.mask & c2.mask) == 0)
            return false;
        translateToWorldCoords(e1, collider1WorldCoordinates);
        translateToWorldCoords(e2, collider2WorldCoordinates);
        if (collider2WorldCoordinates.xLeft > collider1WorldCoordinates.xRight)
            return false;
        if (collider1WorldCoordinates.xLeft > collider2WorldCoordinates.xRight)
            return false;
        if (collider1WorldCoordinates.yDown > collider2WorldCoordinates.yUp)
            return false;
        if (collider2WorldCoordinates.yDown > collider1WorldCoordinates.yUp)
            return false;
        return true;
    }

    private static void translateToWorldCoords(Entity e, Collider c) {
        Collider c1 = e.getCollider();
        c.xLeft = e.getX() + c1.xLeft;
        c.xRight = e.getX() + e.getWidth() - c1.xRight;
        c.yDown = e.getY() + e.getHeight() - c1.yDown;
        c.yUp = e.getY() + c1.yUp;
        c.mask = c1.mask;
    }


    @Override
    public String toString() {
        return "Collider{" +
                "mask=" + mask +
                ", xRight=" + xRight +
                ", xLeft=" + xLeft +
                ", yUp=" + yUp +
                ", yDown=" + yDown +
                '}';
    }
}