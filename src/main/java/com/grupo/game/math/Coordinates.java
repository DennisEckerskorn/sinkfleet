package com.grupo.game.math;

/**
 * Represents a coordinate point in a two-dimensional space.
 */
public class Coordinates {

    private int x;
    private int y;

    /**
     * Constructs a coordinate with the specified x and y values.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}