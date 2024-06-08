package com.grupo.game.scenes;

import com.grupo.engine.entities.Entity;

import javax.swing.*;
import java.awt.*;

public abstract class Scene {
    public Scene() {

    }

    public abstract void render(Graphics2D g2);
    public abstract void drawEntity(Graphics2D g2, Entity e);
    public abstract void drawBackground(Graphics2D g2);
    public abstract void onSceneSet(JPanel parentPanel);

}
