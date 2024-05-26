package com.denniseckerskorn.engine.entities;

import com.denniseckerskorn.engine.input.KeyboardManager;
import com.denniseckerskorn.engine.math.Vector2;

import java.awt.image.BufferedImage;

public abstract class DynamicEntity extends Entity {
    private Vector2 velocity;
    private float linearVelocity;
    private float acceleration;


    public DynamicEntity(float x, float y, float width, float height, float hp, float damage,
                         float colliderXLeft, float colliderXRight, float colliderYDown, float colliderYUp, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        super(x, y, width, height, hp, damage, colliderXLeft, colliderXRight, colliderYDown, colliderYUp, colliderMask, sprite);
        this.velocity = new Vector2(velocityX, velocityY);
        this.linearVelocity = linearVelocity;
        this.acceleration = acceleration;
    }

    private void initDynamicEntity(float velocityX, float velocityY, float linearVelocity, float acceleration) {
        this.velocity = new Vector2(velocityX, velocityY);
        this.linearVelocity = linearVelocity;
        this.acceleration = acceleration;
    }

    public DynamicEntity(float x, float y, float width, float height, float hp, float damage, float colliderOffset, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        this(x, y, width, height, hp, damage, colliderOffset, colliderOffset, colliderOffset, colliderOffset, colliderMask, sprite, velocityX, velocityY, linearVelocity, acceleration);

    }

    public DynamicEntity(float x, float y, float width, float height, float hp, float damage, float colliderOffsetX, float colliderOffsetY, int colliderMask, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        this(x, y, width, height, hp, damage, colliderOffsetX, colliderOffsetX, colliderOffsetY, colliderOffsetY, colliderMask, sprite, velocityX, velocityY, linearVelocity, acceleration);

    }

    public DynamicEntity(float x, float y, float width, float height, float hp, float damage, BufferedImage sprite, float velocityX, float velocityY, float linearVelocity, float acceleration) {
        super(x, y, width, height, hp, damage, sprite);
        initDynamicEntity(velocityX, velocityY, linearVelocity, acceleration);
    }


    @Override
    public void update(double deltaTime) {
        linearVelocity += (float) (acceleration * deltaTime);
        velocity.normalize().mul(linearVelocity);
        getPosition().mulAdd(velocity, (float) deltaTime);

    }

    @Override
    public abstract void lastUpdate(double deltaTime);

    @Override
    public abstract void postUpdate(double deltaTime);
}
