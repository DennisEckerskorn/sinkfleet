package com.grupo.game.panel;

import com.grupo.game.graphics.SinkFleetSwingRendererPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayBoardPanel extends JPanel {
    private SinkFleetSwingRendererPlayer renderer;
    private TargetPanel targetPanel;

    public PlayBoardPanel(SinkFleetSwingRendererPlayer renderer, TargetPanel targetPanel) {
        this.renderer = renderer;
        this.targetPanel = targetPanel;
        setLayout(new BorderLayout());
        add(renderer, BorderLayout.WEST);

        //TODO:Haciendo pruebas...
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int mouseX = e.getX();
                int mouseY = e.getY();

                int cellX = mouseX / renderer.getCellSize();
                int cellY = mouseY / renderer.getCellSize();

                targetPanel.processShot(cellX, cellY);
            }
        });
    }

}
