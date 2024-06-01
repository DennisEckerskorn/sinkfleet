package com.grupo.game.panel;

import com.grupo.engine.core.ResizeListener;
import com.grupo.game.config.Settings;
import com.grupo.game.core.SinkFleetGame;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada del panel contenedor, ajusta la dirección de cada panel dentro del frame.
 */
public class ContainerPanel extends JPanel {
    private final PlayerPanel playerPanel;
    private final EnemyPanel enemyPanel;
    private final SpacePanel spacePanel;

    public ContainerPanel(PlayerPanel playerPanel, EnemyPanel enemyPanel, SpacePanel spacePanel) {
        this.playerPanel = playerPanel;
        this.enemyPanel = enemyPanel;
        this.spacePanel = spacePanel;

        setLayout(new BorderLayout());
        add(playerPanel, BorderLayout.WEST);
        add(spacePanel, BorderLayout.CENTER);
        add(enemyPanel, BorderLayout.EAST);

    }


}
