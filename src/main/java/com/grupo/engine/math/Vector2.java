package com.denniseckerskorn.engine.math;

import java.util.Vector;

public class Vector2 {
    private float x;
    private float y;

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

    public float len() {
        //return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return (float) Math.hypot(x, y);
    }

    public Vector2 normalize() {
        float len = len();
        if (len != 0) {
            x /= len;
            y /= len;
        }
        return this;
    }

    public Vector2 mul(float value) {
        x *= value;
        y *= value;
        return this;
    }

    public Vector2 mulAdd(Vector2 v, float value) {
        x += v.x * value;
        y += v.y * value;
        return this;
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
