package com.grupo.engine.entities;

import com.grupo.engine.core.Collider;
import com.grupo.engine.core.Updateable;
import com.grupo.engine.math.MathUtil;
import com.grupo.engine.math.Vector2;

import java.awt.image.BufferedImage;

/**
 * The Entity class represents a basic entity within the game world.
 * It contains properties such as position, dimensions, health points, damage, collider, and sprite.
 * This class is intended to be extended by more specific entity types.
 */
public abstract class Entity implements Updateable {
    private static int autoincrement = 0;
    private final int id;
    private final Vector2 position;
    private float width;
    private float height;
    private float hp;
    private float damage;
    private Collider collider;
    private BufferedImage sprite;

    /**
     * Constructs an Entity with specified attributes.
     *
     * @param x              The x-coordinate of the entity's position.
     * @param y              The y-coordinate of the entity's position.
     * @param width          The width of the entity.
     * @param height         The height of the entity.
     * @param hp             The hit points of the entity.
     * @param damage         The damage dealt by the entity.
     * @param colliderXLeft  The left offset of the entity's collider.
     * @param colliderXRight The right offset of the entity's collider.
     * @param colliderYDown  The bottom offset of the entity's collider.
     * @param colliderYUp    The top offset of the entity's collider.
     * @param colliderMask   The collision mask of the entity.
     * @param sprite         The sprite image of the entity.
     */
    public Entity(float x, float y, float width, float height, float hp, float damage,
                  float colliderXLeft, float colliderXRight, float colliderYDown, float colliderYUp, int colliderMask, BufferedImage sprite) {
        this(x, y, width, height, hp, damage, sprite);
        this.collider = new Collider(colliderXLeft, colliderXRight, colliderYDown, colliderYUp, colliderMask);
    }

    /**
     * Constructs an Entity with specified attributes, using the same offset for both x and y colliders.
     *
     * @param x              The x-coordinate of the entity's position.
     * @param y              The y-coordinate of the entity's position.
     * @param width          The width of the entity.
     * @param height         The height of the entity.
     * @param hp             The hit points of the entity.
     * @param damage         The damage dealt by the entity.
     * @param colliderOffset The offset of the entity's colliders (both x and y).
     * @param colliderMask   The collision mask of the entity.
     * @param sprite         The sprite image of the entity.
     */
    public Entity(float x, float y, float width, float height, float hp, float damage, float colliderOffset, int colliderMask, BufferedImage sprite) {
        this(x, y, width, height, hp, damage, colliderOffset, colliderOffset, colliderOffset, colliderOffset, colliderMask, sprite);
    }

    /**
     * Constructs an Entity with specified attributes, using separate offsets for x and y colliders.
     *
     * @param x               The x-coordinate of the entity's position.
     * @param y               The y-coordinate of the entity's position.
     * @param width           The width of the entity.
     * @param height          The height of the entity.
     * @param hp              The hit points of the entity.
     * @param damage          The damage dealt by the entity.
     * @param colliderOffsetX The offset of the entity's collider along the x-axis.
     * @param colliderOffsetY The offset of the entity's collider along the y-axis.
     * @param colliderMask    The collision mask of the entity.
     * @param sprite          The sprite image of the entity.
     */
    public Entity(float x, float y, float width, float height, float hp, float damage, float colliderOffsetX, float colliderOffsetY, int colliderMask, BufferedImage sprite) {
        this(x, y, width, height, hp, damage, colliderOffsetX, colliderOffsetX, colliderOffsetY, colliderOffsetY, colliderMask, sprite);
    }

    /**
     * Constructs an Entity with specified attributes and without collider offsets.
     *
     * @param x      The x-coordinate of the entity's position.
     * @param y      The y-coordinate of the entity's position.
     * @param width  The width of the entity.
     * @param height The height of the entity.
     * @param hp     The hit points of the entity.
     * @param damage The damage dealt by the entity.
     * @param sprite The sprite image of the entity.
     */
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

    /**
     * Sets the collider of the entity with specified values.
     *
     * @param colliderXLeft  The left offset of the collider.
     * @param colliderXRight The right offset of the collider.
     * @param colliderYUp    The top offset of the collider.
     * @param colliderYDown  The bottom offset of the collider.
     * @param colliderMask   The collision mask of the collider.
     */
    public void setCollider(float colliderXLeft, float colliderXRight, float colliderYUp, float colliderYDown, int colliderMask) {
        if (collider == null) {
            collider = new Collider(colliderXLeft, colliderXRight, colliderYUp, colliderYDown, colliderMask);
        } else {
            collider.setxLeft(colliderXLeft);
            collider.setxRight(colliderXRight);
            collider.setyUp(colliderYUp);
            collider.setyDown(colliderYDown);
            collider.setMask(colliderMask);
        }
    }

    public void setCollider(float colliderX, float colliderY, int colliderMask) {
        setCollider(colliderX, colliderX, colliderY, colliderY, colliderMask);
    }

    /**
     * Sets the sprite image of the entity.
     *
     * @param sprite The sprite image of the entity.
     */
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets the position of the entity to the specified coordinates.
     *
     * @param x The x-coordinate of the entity's position.
     * @param y The y-coordinate of the entity's position.
     */
    public void setPosition(float x, float y) {
        position.setX(x);
        position.setY(y);
    }

    /**
     * Sets the damage value of the entity.
     *
     * @param damage The damage value of the entity.
     */
    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void hit(Entity e) {
        hit(e, damage);

    }

    /**
     * Applies damage to the specified entity.
     *
     * @param e      The entity to apply damage to.
     * @param damage The amount of damage to apply.
     */
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
