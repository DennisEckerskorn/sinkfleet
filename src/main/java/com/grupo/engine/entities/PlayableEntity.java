package com.grupo.engine.entities;

import com.grupo.engine.input.KeyboardManager;

import java.awt.image.BufferedImage;

/**
 * The PlayableEntity class represents an entity that can be controlled by the player.
 * It extends the DynamicEntity class and adds functionality for keyboard input handling.
 */
public abstract class PlayableEntity extends DynamicEntity {
    private final KeyboardManager keyboardManager;

    /**
     * Constructs a PlayableEntity with specified attributes.
     *
     * @param x               The x-coordinate of the entity's position.
     * @param y               The y-coordinate of the entity's position.
     * @param width           The width of the entity.
     * @param height          The height of the entity.
     * @param hp              The hit points of the entity.
     * @param damage          The damage dealt by the entity.
     * @param colliderXLeft   The left offset of the entity's collider.
     * @param colliderXRight  The right offset of the entity's collider.
     * @param colliderYDown   The bottom offset of the entity's collider.
     * @param colliderYUp     The top offset of the entity's collider.
     * @param colliderMask    The collision mask of the entity.
     * @param sprite          The sprite image of the entity.
     * @param velocityX       The initial velocity along the x-axis.
     * @param velocityY       The initial velocity along the y-axis.
     * @param linearVelocity  The initial linear velocity of the entity.
     * @param acceleration    The acceleration of the entity.
     * @param keyboardManager The keyboard manager for handling input.
     */
    public PlayableEntity(float x, float y, float width, float height, float hp, float damage,
                          float colliderXLeft, float colliderXRight, float colliderYDown, float colliderYUp, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration,
                          KeyboardManager keyboardManager) {
        super(x, y, width, height, hp, damage, colliderXLeft, colliderXRight, colliderYDown, colliderYUp, colliderMask, sprite, velocityX, velocityY, linearVelocity, acceleration);
        this.keyboardManager = keyboardManager;
    }


    /**
     * Constructs a PlayableEntity with specified attributes, using the same offset for both x and y colliders.
     *
     * @param x               The x-coordinate of the entity's position.
     * @param y               The y-coordinate of the entity's position.
     * @param width           The width of the entity.
     * @param height          The height of the entity.
     * @param hp              The hit points of the entity.
     * @param damage          The damage dealt by the entity.
     * @param colliderOffset  The offset of the entity's colliders (both x and y).
     * @param colliderMask    The collision mask of the entity.
     * @param sprite          The sprite image of the entity.
     * @param velocityX       The initial velocity along the x-axis.
     * @param velocityY       The initial velocity along the y-axis.
     * @param linearVelocity  The initial linear velocity of the entity.
     * @param acceleration    The acceleration of the entity.
     * @param keyboardManager The keyboard manager for handling input.
     */
    public PlayableEntity(float x, float y, float width, float height, float hp, float damage, float colliderOffset, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration,
                          KeyboardManager keyboardManager) {
        super(x, y, width, height, hp, damage, colliderOffset, colliderOffset, colliderOffset, colliderOffset, colliderMask, sprite, velocityX, velocityY, linearVelocity, acceleration);
        this.keyboardManager = keyboardManager;
    }

    /**
     * Constructs a PlayableEntity with specified attributes, using separate offsets for x and y colliders.
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
     * @param velocityX       The initial velocity along the x-axis.
     * @param velocityY       The initial velocity along the y-axis.
     * @param linearVelocity  The initial linear velocity of the entity.
     * @param acceleration    The acceleration of the entity.
     * @param keyboardManager The keyboard manager for handling input.
     */
    public PlayableEntity(float x, float y, float width, float height, float hp, float damage, float colliderOffsetX, float colliderOffsetY, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration,
                          KeyboardManager keyboardManager) {
        super(x, y, width, height, hp, damage, colliderOffsetX, colliderOffsetX, colliderOffsetY, colliderOffsetY, colliderMask, sprite, velocityX, velocityY, linearVelocity, acceleration);
        this.keyboardManager = keyboardManager;
    }

    /**
     * Constructs a PlayableEntity with specified attributes and without collider offsets.
     *
     * @param x               The x-coordinate of the entity's position.
     * @param y               The y-coordinate of the entity's position.
     * @param width           The width of the entity.
     * @param height          The height of the entity.
     * @param hp              The hit points of the entity.
     * @param damage          The damage dealt by the entity.
     * @param sprite          The sprite image of the entity.
     * @param velocityX       The initial velocity along the x-axis.
     * @param velocityY       The initial velocity along the y-axis.
     * @param linearVelocity  The initial linear velocity of the entity.
     * @param acceleration    The acceleration of the entity.
     * @param keyboardManager The keyboard manager for handling input.
     */
    public PlayableEntity(float x, float y, float width, float height, float hp, float damage, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration, KeyboardManager keyboardManager) {
        super(x, y, width, height, hp, damage, sprite, velocityX, velocityY, linearVelocity, acceleration);
        this.keyboardManager = keyboardManager;
    }

    /**
     * Constructs a PlayableEntity with specified attributes and velocity, without collider information.
     *
     * @param x               The x-coordinate of the entity's position.
     * @param y               The y-coordinate of the entity's position.
     * @param width           The width of the entity.
     * @param height          The height of the entity.
     * @param hp              The hit points of the entity.
     * @param damage          The damage dealt by the entity.
     * @param velocityX       The initial velocity along the x-axis.
     * @param velocityY       The initial velocity along the y-axis.
     * @param linearVelocity  The initial linear velocity of the entity.
     * @param acceleration    The acceleration of the entity.
     * @param keyboardManager The keyboard manager for handling input.
     */
    public PlayableEntity(float x, float y, float width, float height, float hp, float damage, float velocityX, float velocityY, float linearVelocity, float acceleration,
                          KeyboardManager keyboardManager) {
        super(x, y, width, height, hp, damage, null, velocityX, velocityY, linearVelocity, acceleration);
        this.keyboardManager = keyboardManager;
    }

    /**
     * Retrieves the keyboard manager associated with this playable entity.
     *
     * @return The keyboard manager.
     */
    public KeyboardManager getKeyboardManager() {
        return keyboardManager;
    }

    /*
    public void setDirection(Direction direction) {
        setVelocity(direction.getDirection().getX(), direction.getDirection().getY());
    }
     */

    /**
     * Processes input for the playable entity.
     * This method should be implemented by subclasses to handle keyboard input.
     */
    public abstract void processInput();
}
