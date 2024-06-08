package com.grupo.game.scenes;

import javax.swing.*;
import java.awt.*;

public abstract class Scene {
    public Scene() {

    }

    public abstract void render(Graphics2D g2);
    public abstract JPanel getPanel();
}
