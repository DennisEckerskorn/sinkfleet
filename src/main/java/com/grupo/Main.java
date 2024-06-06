package com.grupo;


import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SinkFleetGame sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES, BlackBoard2.Mode.SINGLE_PLAYER);

        //Renderizados que maneja ambos tableros con colores de fondo diferentes:
        SinkFleetSwingRenderer sinkFleetSwingRenderer = new SinkFleetSwingRenderer(Settings.WIDTH * 2 + 20, Settings.HEIGHT, sinkFleetGame, Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND);
        sinkFleetGame.setRenderAPI(sinkFleetSwingRenderer);

        //Configuración del frame:
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Sink the Fleet");

        //Configuración y creación del Panel Contenedor:
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(sinkFleetSwingRenderer, BorderLayout.CENTER);

        // Crear el botón
        JButton button = new JButton("CAMBIAR TURNO");

        // Lógica para el boton CAMBIAR TURNO:
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackBoard2.buttonPressed = true;
            }
        });

        // Añadir el botón al panel contenedor en la posición sur
        containerPanel.add(button, BorderLayout.SOUTH);

        frame.add(containerPanel);
        frame.pack();
        frame.setVisible(true);
        sinkFleetGame.start();
    }
}