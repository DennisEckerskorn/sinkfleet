package com.grupo.engine.entities;

import com.grupo.engine.math.Vector2;

import java.awt.image.BufferedImage;

/**
 * The DynamicEntity class represents an entity with dynamic movement behavior.
 * It extends the basic Entity class and adds properties related to velocity and acceleration.
 */
public abstract class DynamicEntity extends Entity {
    private Vector2 velocity;
    private float linearVelocity;
    private float acceleration;

    /**
     * Constructs a DynamicEntity with specified attributes.
     *
     * @param x              The x-coordinate of the entity's position.
     * @param y              The y-coordinate of the entity's position.
     * @param width          The width of the entity.
     * @param height         The height of the entity.
     * @param hp             The hit points of the entity.
     * @param damage         The damage dealt by the entity.
     * @param colliderXLeft  The left offset of the entity's collider.
     * @param colliderXRight The right offset of the entity's collider.
     * @param colliderYUp    The top offset of the entity's collider.
     * @param colliderYDown  The bottom offset of the entity's collider.
     * @param colliderMask   The collision mask of the entity.
     * @param sprite         The sprite image of the entity.
     * @param velocityX      The initial velocity along the x-axis.
     * @param velocityY      The initial velocity along the y-axis.
     * @param linearVelocity The initial linear velocity of the entity.
     * @param acceleration   The acceleration of the entity.
     */
    public DynamicEntity(float x, float y, float width, float height, float hp, float damage,
                         float colliderXLeft, float colliderXRight, float colliderYUp, float colliderYDown, int colliderMask,
                         BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        super(x, y, width, height, hp, damage, colliderXLeft, colliderXRight, colliderYUp, colliderYDown, colliderMask, sprite);
        initDynamicEntity(velocityX, velocityY, linearVelocity, acceleration);
    }

    /**
     * Constructs a DynamicEntity with specified attributes, using the same offset for both x and y colliders.
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
     * @param velocityX      The initial velocity along the x-axis.
     * @param velocityY      The initial velocity along the y-axis.
     * @param linearVelocity The initial linear velocity of the entity.
     * @param acceleration   The acceleration of the entity.
     */
    public DynamicEntity(float x, float y, float width, float height, float hp, float damage,
                         float colliderOffset, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        this(x, y, width, height, hp, damage, colliderOffset, colliderOffset, colliderOffset, colliderOffset, colliderMask, sprite, velocityX, velocityY, linearVelocity, acceleration);
    }

    /**
     * Constructs a DynamicEntity with specified attributes, using different offsets for x and y colliders.
     *
     * @param x               The x-coordinate of the entity's position.
     * @param y               The y-coordinate of the entity's position.
     * @param width           The width of the entity.
     * @param height          The height of the entity.
     * @param hp              The hit points of the entity.
     * @param damage          The damage dealt by the entity.
     * @param colliderOffsetX The offset of the entity's colliders along the x-axis.
     * @param colliderOffsetY The offset of the entity's colliders along the y-axis.
     * @param colliderMask    The collision mask of the entity.
     * @param sprite          The sprite image of the entity.
     * @param velocityX       The initial velocity along the x-axis.
     * @param velocityY       The initial velocity along the y-axis.
     * @param linearVelocity  The initial linear velocity of the entity.
     * @param acceleration    The acceleration of the entity.
     */
    public DynamicEntity(float x, float y, float width, float height, float hp, float damage,
                         float colliderOffsetX, float colliderOffsetY, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        this(x, y, width, height, hp, damage, colliderOffsetX, colliderOffsetX, colliderOffsetY, colliderOffsetY, colliderMask, sprite, velocityX, velocityY, linearVelocity, acceleration);
    }

    /**
     * Constructs a DynamicEntity with specified attributes, using the default collider offsets (0).
     *
     * @param x              The x-coordinate of the entity's position.
     * @param y              The y-coordinate of the entity's position.
     * @param width          The width of the entity.
     * @param height         The height of the entity.
     * @param hp             The hit points of the entity.
     * @param damage         The damage dealt by the entity.
     * @param sprite         The sprite image
     * @param velocityX      The initial velocity along the x-axis.
     * @param velocityY      The initial velocity along the y-axis.
     * @param linearVelocity The initial linear velocity of the entity.
     * @param acceleration   The acceleration of the entity.
     */
    public DynamicEntity(float x, float y, float width, float height, float hp, float damage, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        super(x, y, width, height, hp, damage, sprite);
        initDynamicEntity(velocityX, velocityY, linearVelocity, acceleration);
    }

    /**
     * Initializes the dynamic entity with velocity and acceleration.
     *
     * @param velocityX      The initial velocity along the x-axis.
     * @param velocityY      The initial velocity along the y-axis.
     * @param linearVelocity The initial linear velocity of the entity.
     * @param acceleration   The acceleration of the entity.
     */
    private void initDynamicEntity(float velocityX, float velocityY, float linearVelocity, float acceleration) {
        this.velocity = new Vector2(velocityX, velocityY);
        this.linearVelocity = linearVelocity;
        this.acceleration = acceleration;
    }

    /**
     * Updates the position of the dynamic entity based on its velocity and acceleration.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        linearVelocity += (float) (acceleration * deltaTime);
        velocity.normalize().mul(linearVelocity);
        getPosition().mulAdd(velocity, (float) deltaTime);
    }

    /**
     * Sets the velocity of the dynamic entity.
     *
     * @param x The velocity along the x-axis.
     * @param y The velocity along the y-axis.
     */
    public void setVelocity(float x, float y) {
        this.velocity.setX(x);
        this.velocity.setY(y);
    }

    /**
     * Abstract method to be implemented for the last update of the dynamic entity.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public abstract void lastUpdate(double deltaTime);


    /**
     * Abstract method to be implemented for the post update of the dynamic entity.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public abstract void postUpdate(double deltaTime);

}
