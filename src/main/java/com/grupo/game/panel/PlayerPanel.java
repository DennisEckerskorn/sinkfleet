package com.grupo.game.panel;

import com.grupo.game.graphics.SinkFleetSwingRendererPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de crear el panel del jugador
 */
public class PlayerPanel extends JPanel {
    private PlayBoardPanel playBoardPanel;
    private TargetPanel targetPanel;
    private SpacePanel spacePanel;

    public PlayerPanel(PlayBoardPanel playBoardPanel,SpacePanel spacePanel, TargetPanel targetPanel) {
        this.playBoardPanel = playBoardPanel;
        this.spacePanel = spacePanel;
        this.targetPanel = targetPanel;
        setLayout(new BorderLayout());
        add(playBoardPanel, BorderLayout.WEST);
        add(spacePanel, BorderLayout.CENTER);
        add(targetPanel, BorderLayout.EAST);
    }
}

