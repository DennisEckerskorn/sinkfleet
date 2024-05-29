package com.grupo;


import com.grupo.game.config.Settings;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRendererEnemy;
import com.grupo.game.graphics.SinkFleetSwingRendererPlayer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Crear la instancia del juego:
        SinkFleetGame sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT,Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES);
        //Crear renderizadores para jugador y enemigo
        SinkFleetSwingRendererPlayer sinkFleetSwingRendererPlayer = new SinkFleetSwingRendererPlayer(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);
        SinkFleetSwingRendererEnemy sinkFleetSwingRendererEnemy = new SinkFleetSwingRendererEnemy(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);

        //Configurar el renderizado del juego
        sinkFleetGame.setRenderAPI(sinkFleetSwingRendererPlayer);

        //Crear los paneles para cada renderizador
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.add(sinkFleetSwingRendererPlayer, BorderLayout.CENTER);

        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyPanel.add(sinkFleetSwingRendererEnemy, BorderLayout.CENTER);

        JPanel spacePanel = new JPanel();
        Dimension spaceSize = new Dimension(100, Settings.HEIGHT);
        spacePanel.setPreferredSize(spaceSize);

        //Configurar el frame:
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Sink the Fleet");

        //Configurar el layout del frame:
        frame.setLayout(new BorderLayout());

        //Agregar los paneles al frame:
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(playerPanel, BorderLayout.WEST);
        containerPanel.add(spacePanel, BorderLayout.CENTER);
        containerPanel.add(enemyPanel, BorderLayout.EAST);

        frame.add(containerPanel, BorderLayout.CENTER);
        //Iniciar Frame...
        frame.pack();
        frame.setVisible(true);
        sinkFleetGame.start();
    }
}