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
    private PlayerPanel playerPanel1;
    private PlayerPanel playerPanel2;
    private SinkFleetSwingRendererPlayer playerRenderer1;
    private SinkFleetSwingRendererPlayer playerRenderer2;
    private SinkFleetSwingRendererTarget targetRenderer1;
    private SinkFleetSwingRendererTarget targetRenderer2;
    private PlayBoardPanel playerPlayBoardPanel1;
    private PlayBoardPanel playerPlayBoardPanel2;
    private TargetPanel playerTargetPanel1;
    private TargetPanel playerTargetPanel2;

    public Frame() {
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Sink the Fleet");

        this.setLayout(new BorderLayout());

        // Crear la instancia del juego
        sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES);

        // Crear los renderizadores
        playerRenderer1 = new SinkFleetSwingRendererPlayer(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);
        playerRenderer2 = new SinkFleetSwingRendererPlayer(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);
        targetRenderer1 = new SinkFleetSwingRendererTarget(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);
        targetRenderer2 = new SinkFleetSwingRendererTarget(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);

        // Configurar el renderizado del juego
        sinkFleetGame.setRenderAPI(playerRenderer1);

        // Crear los paneles
        spacePanel = new SpacePanel(100, Settings.HEIGHT);

        //Tablero Jugador 1:
        playerPlayBoardPanel1 = new PlayBoardPanel(playerRenderer1, playerTargetPanel2);
        playerTargetPanel1 = new TargetPanel(targetRenderer1, playerPanel2);
        playerPanel1 = new PlayerPanel(playerPlayBoardPanel1, spacePanel, playerTargetPanel1);
/*

       //TODO: Tablero Jugador 2 o CPU:
        playerPlayBoardPanel2 = new PlayBoardPanel(playerRenderer2, playerTargetPanel1);
        playerTargetPanel2 = new TargetPanel(targetRenderer2, playerPanel1);
        playerPanel2 = new PlayerPanel(playerPlayBoardPanel2, spacePanel, playerTargetPanel2);


 */

        // Agregar el panel contenedor al frame
        this.add(playerPanel1, BorderLayout.WEST);
        this.add(spacePanel, BorderLayout.CENTER);
        //this.add(playerPanel2, BorderLayout.EAST);

        //Iniciar Frame...
        pack();
        sinkFleetGame.start();
    }
}
