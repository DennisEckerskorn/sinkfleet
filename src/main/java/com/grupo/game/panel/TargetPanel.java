package com.grupo.game.panel;

import com.grupo.game.graphics.SinkFleetSwingRendererTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TargetPanel extends JPanel {

    private SinkFleetSwingRendererTarget renderer;
    PlayerPanel playerPanel;

    public TargetPanel(SinkFleetSwingRendererTarget renderer, PlayerPanel playerPanel) {
        this.renderer = renderer;
        this.playerPanel = playerPanel;
        setLayout(new BorderLayout());
        add(renderer, BorderLayout.EAST);
        //TODO: haciendo pruebas a ver como va...
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int mouseX = e.getX();
                int mouseY = e.getY();

                int cellX = mouseX / renderer.getCellSize();
                int cellY = mouseY / renderer.getCellSize();

                processShot(cellX, cellY);
            }
        });
    }
    public void processShot(int cellX, int cellY) {
        System.out.println("Disparo en celda: (" + cellX + ", " + cellY + ")");
    }

}
