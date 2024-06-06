package com.grupo.game.gamegui;

import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JFrame {
    public MainMenu() {
        super("Battleship Main Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);

        try {
            // Cargar la imagen de fondo
            Image backgroundImage = ImageIO.read(getClass().getResource("/images/battle2.jpg"));
            JPanel panel = new JPanel(new BorderLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };
            panel.setOpaque(true);
            setContentPane(panel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Panel para los botones:
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setOpaque(false);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        //Boton iniciar Juego:
        JButton pvpButton = new JButton("PLAYER VS PLAYER");
        customizeButton(pvpButton);
        pvpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Iniciar el Juego Player vs Player (SinglePlayer)
                startGame(BlackBoard2.Mode.SINGLE_PLAYER);
            }
        });
        buttonPanel.add(pvpButton);

        //Boton para inciar el juego Player vs NPC:
        JButton pvnpcButton = new JButton("PLAYER VS NPC");
        customizeButton(pvnpcButton);
        pvnpcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Iniciar el Juego Player vs NPC
            }
        });
        buttonPanel.add(pvnpcButton);

        JButton exitButton = new JButton("EXIT");
        customizeButton(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Salir del juego:
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);
        setVisible(true);
    }

    private void startGame(BlackBoard2.Mode mode) {
        SinkFleetGame sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES, mode);

        //Renderizados que maneja ambos tableros con colores de fondo diferentes:
        SinkFleetSwingRenderer sinkFleetSwingRenderer = new SinkFleetSwingRenderer(Settings.WIDTH * 2 + 100, Settings.HEIGHT, sinkFleetGame, Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND);
        sinkFleetGame.setRenderAPI(sinkFleetSwingRenderer);

        //Configuración del frame:
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Battleship");

        //Configuración y creación del Panel Contenedor:
        JPanel container = new JPanel(new BorderLayout());
        container.add(sinkFleetSwingRenderer, BorderLayout.CENTER);

        // Crear el botón START GAME
        JButton button = new JButton("START GAME");

        //Ajustar el tamaño del boton:
        button.setPreferredSize(new Dimension(150, button.getPreferredSize().height));

        // Lógica para el boton CAMBIAR TURNO:
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackBoard2.buttonPressed = true;
            }
        });

        //Creación del panel para el boton:
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(button, BorderLayout.CENTER);

        // Añadir el panel del boton al Border Sur
        container.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(container);
        frame.pack();
        frame.setVisible(true);
        sinkFleetGame.start();
    }

    private void customizeButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
    }
}
