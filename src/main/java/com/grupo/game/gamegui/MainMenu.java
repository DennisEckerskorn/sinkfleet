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
        setSize(Settings.WIDTH, Settings.HEIGHT);
        setLocationRelativeTo(null);

        try {
            // Cargar la imagen de fondo
            Image backgroundImage = ImageIO.read(getClass().getResource("/images/battleship.png"));
            setContentPane(new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            });
            revalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Panel Contenedor:
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Hacer el panel transparente
        panel.setLayout(new GridLayout(3, 1));

        //Boton iniciar Juego:
        JButton pvpButton = new JButton("PLAYER VS PLAYER");
        pvpButton.setPreferredSize(new Dimension(200, 50));
        pvpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Iniciar el Juego Player vs Player (SinglePlayer)
                startGame(BlackBoard2.Mode.SINGLE_PLAYER);
            }
        });
        panel.add(pvpButton);

        //Boton para inciar el juego Player vs NPC:
        JButton pvnpcButton = new JButton("PLAYER VS NPC");
        pvnpcButton.setPreferredSize(new Dimension(200, 50));
        pvnpcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Iniciar el Juego Player vs NPC
            }
        });
        panel.add(pvnpcButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(200, 50));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Salir del juego:
                System.exit(0);
            }
        });
        panel.add(exitButton);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
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
}
