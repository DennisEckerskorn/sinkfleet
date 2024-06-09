package com.grupo.engine.core;

import com.grupo.engine.entities.Entity;

/**
 * The Collider class represents collision detection boundaries for entities in the game world.
 * It provides methods for checking collisions between entities and handling their collision masks.
 */
public class Collider {
    private float xRight;
    private float xLeft;
    private float yUp;
    private float yDown;
    private int mask;

    private static Collider collider1WorldCoordinates = new Collider();
    private static Collider collider2WorldCoordinates = new Collider();

    /**
     * Private constructor to prevent external instantiation of colliders without parameters.
     */
    private Collider() {
    }

    /**
     * Creates a collider with the specified boundaries and collision mask.
     *
     * @param xLeft  The left boundary.
     * @param xRight The right boundary.
     * @param yDown  The lower boundary.
     * @param yUp    The upper boundary.
     * @param mask   The collision mask.
     */
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

    /**
     * Checks for collision between two entities based on their colliders.
     *
     * @param e1 The first entity.
     * @param e2 The second entity.
     * @return True if the entities collide, false otherwise.
     */
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

    /**
     * Translates the collider coordinates to world coordinates relative to the entity.
     *
     * @param e The entity.
     * @param c The collider.
     */
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