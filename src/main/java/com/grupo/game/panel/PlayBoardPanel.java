package com.grupo.game.panel;

import com.grupo.game.graphics.SinkFleetSwingRendererPlayer;

import javax.swing.*;
import java.awt.*;

public class PlayBoardPanel extends JPanel {
    private SinkFleetSwingRendererPlayer renderer;

    public PlayBoardPanel(SinkFleetSwingRendererPlayer renderer) {
        this.renderer = renderer;
        setLayout(new BorderLayout());
        add(renderer, BorderLayout.CENTER);
    }

}
