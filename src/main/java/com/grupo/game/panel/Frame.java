package com.grupo.game.panel;

import com.grupo.game.config.Settings;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRendererTarget;
import com.grupo.game.graphics.SinkFleetSwingRendererPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que se encarga de la ventana del juego, instancia todos los objetos necesarios para añadir el juego a la ventana.
 */
public class Frame extends JFrame {
    private SinkFleetGame sinkFleetGame;
    private SpacePanel spacePanel;
    private PlayerPanel playerPanel;
    private EnemyPanel enemyPanel;
    private SinkFleetSwingRendererPlayer playerPlayRenderer;
    private SinkFleetSwingRendererTarget playerTargetRenderer;
    private PlayBoardPanel playerPlayBoardPanel;
    private TargetPanel playerTargetPanel;

    public Frame() {
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Sink the Fleet");

        this.setLayout(new BorderLayout());

        // Crear la instancia del juego
        sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES);

        // Crear los renderizadores
        playerPlayRenderer = new SinkFleetSwingRendererPlayer(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);
        playerTargetRenderer = new SinkFleetSwingRendererTarget(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);

        // Configurar el renderizado del juego
        sinkFleetGame.setRenderAPI(playerPlayRenderer);

        // Crear los paneles
        spacePanel = new SpacePanel(100, Settings.HEIGHT);

        //Tablero Jugador 1:
        playerPlayBoardPanel = new PlayBoardPanel(playerPlayRenderer);
        playerTargetPanel = new TargetPanel(playerTargetRenderer);
        playerPanel = new PlayerPanel(playerPlayBoardPanel, spacePanel, playerTargetPanel);

       //TODO: Tablero Jugador 2 o CPU:

        // Agregar el panel contenedor al frame
        this.add(playerPanel, BorderLayout.CENTER);

        //Iniciar Frame...
        pack();
        sinkFleetGame.start();
    }
}
