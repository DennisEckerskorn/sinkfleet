package com.grupo.game.panel;

import com.grupo.game.graphics.SinkFleetSwingRendererTarget;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de crear el panel del enemigo
 */
public class EnemyPanel extends JPanel {
    private SinkFleetSwingRendererTarget sinkFleetSwingRendererEnemy;

    public EnemyPanel(SinkFleetSwingRendererTarget renderer) {
        this.sinkFleetSwingRendererEnemy = renderer;
        setLayout(new BorderLayout());
        add(sinkFleetSwingRendererEnemy, BorderLayout.CENTER);
    }
}
