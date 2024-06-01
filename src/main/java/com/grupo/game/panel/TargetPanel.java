package com.grupo.game.panel;

import com.grupo.game.graphics.SinkFleetSwingRendererTarget;

import javax.swing.*;
import java.awt.*;

public class TargetPanel extends JPanel {

    private SinkFleetSwingRendererTarget renderer;

    public TargetPanel(SinkFleetSwingRendererTarget renderer) {
        this.renderer = renderer;
        setLayout(new BorderLayout());
        add(renderer, BorderLayout.CENTER);
    }

}
