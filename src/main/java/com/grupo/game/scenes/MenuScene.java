package com.grupo.game.scenes;

import com.grupo.engine.core.AssetManager;
import com.grupo.engine.entities.Entity;
import com.grupo.game.config.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MenuScene extends Scene {
    private ButtonClickListener buttonClickListener;
    private BufferedImage backgroundImage;
    private JPanel buttonPanel;
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton exitButton;

    /**
     * Constructor que inicializa todos los atributos y crea un menú con botones.
     * Construye el panel con 3 botones en un GridLayout, con una columan y 3 filas.
     * Cada boton tiene su propia funcionalidad.
     * Iniciar SINGLEPLAYER
     * Iniciar MULTIPLAYER
     * Salir EXIT
     *
     * @param assetManager recibe el assetmanager que se encarga de gestionar imagenes, sprites y demás.
     */
    public MenuScene(AssetManager assetManager, SceneManager gameManager) {
        backgroundImage = assetManager.getSprite("battleship");
        buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setOpaque(false);
        singlePlayerButton = new JButton("SINGLE PLAYER MODE");
        //customizeButton(singlePlayerButton);
        singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonClickListener != null) {
                    buttonClickListener.onSinglePlayerClicked();
                }
            }
        });
        buttonPanel.add(singlePlayerButton);

        multiPlayerButton = new JButton("MULTIPLAYER MODE");
        //customizeButton(multiPlayerButton);
        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonClickListener != null) {
                    buttonClickListener.onMultiPlayerClicked();
                }
            }
        });
        buttonPanel.add(multiPlayerButton);

        exitButton = new JButton("EXIT GAME");
        //customizeButton(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonClickListener != null) {
                    buttonClickListener.onExitClicked();
                }
            }
        });
        buttonPanel.add(exitButton);
    }

    public JPanel getPanel() {
        return buttonPanel;
    }

    public void setButtonClickListener(ButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    /**
     * Method which allows us to customize a button.
     *
     * @param button JButton to customize
     */
    private void customizeButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setOpaque(true);
    }


    @Override
    public void render(Graphics2D g2) {
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, Settings.WIDTH, Settings.HEIGHT, null);
        }
    }

    @Override
    public void drawEntity(Graphics2D g2, Entity e) {

    }

    @Override
    public void drawBackground(Graphics2D g2) {

    }

    @Override
    public void onSceneSet(JPanel parentPanel) {
        // Configura el layout del panel principal
        parentPanel.setLayout(new BorderLayout());
        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.gridx = 0;
        //gbc.gridy = 0;

        // Añade el panel de botones al panel principal
        parentPanel.add(buttonPanel, null);
    }

}