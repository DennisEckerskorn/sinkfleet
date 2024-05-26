package com.denniseckerskorn.engine.entities;

import com.denniseckerskorn.engine.core.Collider;
import com.denniseckerskorn.engine.core.Updateable;
import com.denniseckerskorn.engine.math.MathUtil;
import com.denniseckerskorn.engine.math.Vector2;
import com.denniseckerskorn.engine.pool.Poolable;

import java.awt.image.BufferedImage;

public abstract class Entity implements Updateable, Poolable {
    private static int autoincrement = 0;
    private final int id;
    private final Vector2 position;
    private float width;
    private float height;
    private float hp;
    private float damage;
    private Collider collider;
    private BufferedImage sprite;

    public Entity(float x, float y, float width, float height, float hp, float damage,
                  float colliderXLeft, float colliderXRight, float colliderYDown, float colliderYUp, int colliderMask, BufferedImage sprite) {
        this(x, y, width, height, hp, damage, sprite);
        this.collider = new Collider(colliderXLeft, colliderXRight, colliderYDown, colliderYUp, colliderMask);
    }


    public Entity(float x, float y, float width, float height, float hp, float damage, float colliderOffset, int colliderMask, BufferedImage sprite) {
        this(x, y, width, height, hp, damage, colliderOffset, colliderOffset, colliderOffset, colliderOffset, colliderMask, sprite);
    }

    public Entity(float x, float y, float width, float height, float hp, float damage, float colliderOffsetX, float colliderOffsetY, int colliderMask, BufferedImage sprite) {
        this(x, y, width, height, hp, damage, colliderOffsetX, colliderOffsetX, colliderOffsetY, colliderOffsetY, colliderMask, sprite);
    }

    public Entity(float x, float y, float width, float height, float hp, float damage, BufferedImage sprite) {
        this.id = ++autoincrement;
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.damage = damage;
        this.collider = null;
        this.sprite = sprite;
    }

    public Entity(float x, float y, float width, float height, float hp, float damage) {
        this(x, y, width, height, hp, damage, null);
    }

    @Override
    public void reset() {
        //this.position.setPosition(0, 0);
        this.width = 0;
        this.height = 0;
        this.hp = 0;
        this.damage = 0;
        this.collider = null;
        this.sprite = null;
    }

    public Collider getCollider() {
        return collider;
    }

    public float getDamage() {
        return damage;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getHp() {
        return hp;
    }

    public int getId() {
        return id;
    }

    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }

    public Vector2 getPosition() {
        return position;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setCollider(float colliderXLeft, float colliderXRight, float colliderYDown, float colliderYUp, int colliderMask) {
        if (collider == null) {
            collider = new Collider(colliderXLeft, colliderXRight, colliderYDown, colliderYUp, colliderMask);
        } else {
            collider.setxRight(colliderXLeft);
            collider.setxLeft(colliderXRight);
            collider.setyUp(colliderYDown);
            collider.setyDown(colliderYUp);
            collider.setMask(colliderMask);
        }
    }

    public void setCollider(float colliderX, float colliderY, int colliderMask) {
        setCollider(colliderX, colliderX, colliderY, colliderY, colliderMask);
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setPosition(float x, float y) {
        position.setX(x);
        position.setY(y);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void hit(Entity e) {
        hit(e, damage);

    }

    public void hit(Entity e, float damage) {
        e.hp -= damage;
        e.hp = MathUtil.clamp(e.hp, 0);
    }

    @Override
    public abstract void update(double deltaTime);

    @Override
    public abstract void lastUpdate(double deltaTime);

    @Override
    public abstract void postUpdate(double deltaTime);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
