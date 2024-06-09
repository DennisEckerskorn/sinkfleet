package com.grupo.engine.math;

/**
 * A class representing a two-dimensional vector.
 */
public class Vector2 {
    private float x;
    private float y;

    /**
     * Constructs a Vector2 with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the vector
     * @param y the y-coordinate of the vector
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Calculates the length (magnitude) of the vector.
     *
     * @return the length of the vector
     */
    public float len() {
        // return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return (float) Math.hypot(x, y);
    }

    /**
     * Normalizes the vector, making its length equal to 1.
     *
     * @return the normalized vector
     */
    public Vector2 normalize() {
        float len = len();
        if (len != 0) {
            x /= len;
            y /= len;
        }
        return this;
    }

    /**
     * Multiplies the vector by a scalar value.
     *
     * @param value the value to multiply the vector by
     * @return the resulting vector
     */
    public Vector2 mul(float value) {
        x *= value;
        y *= value;
        return this;
    }

    /**
     * Adds another vector multiplied by a scalar value to this vector.
     *
     * @param v     the vector to add
     * @param value the value to multiply the added vector by
     * @return the resulting vector
     */
    public Vector2 mulAdd(Vector2 v, float value) {
        x += v.x * value;
        y += v.y * value;
        return this;
    }

    /**
     * Returns a string representation of the vector.
     *
     * @return a string representation of the vector
     */
    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
