package com.grupo.game.scenes;

import com.grupo.engine.core.AssetManager;
import com.grupo.engine.entities.Entity;
import com.grupo.game.config.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Represents a menu scene with buttons for single player, multiplayer, and exit options.
 */
public class MenuScene extends Scene {
    private ButtonClickListener buttonClickListener;
    private BufferedImage backgroundImage;
    private JPanel buttonPanel;
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton exitButton;

    /**
     * Constructs a MenuScene with the specified asset manager and game manager.
     * Initializes all attributes and creates a menu with buttons.
     * Constructs the panel with 3 buttons in a GridLayout, with one column and three rows.
     * Each button has its own functionality:
     * Start SINGLEPLAYER
     * Start MULTIPLAYER
     * Exit EXIT
     *
     * @param assetManager The asset manager responsible for managing images, sprites, and more.
     * @param gameManager  The game manager responsible for managing scenes.
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
     * Customizes a button's appearance.
     *
     * @param button The JButton to customize.
     */
    private void customizeButton(JButton button) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setPreferredSize(new Dimension(250, 50));

        button.setOpaque(true);
    }


    @Override
    public void render(Graphics2D g2) {

    }

    @Override
    public void drawEntity(Graphics2D g2, Entity e) {

    }

    /**
     * Draws the background image of the scene.
     *
     * @param g2 The Graphics2D object.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, Settings.WIDTH, Settings.HEIGHT, null);
        }
    }

    /**
     * Called when the scene is set.
     *
     * @param parentPanel The parent panel containing the scene.
     */
    @Override
    public void onSceneSet(JPanel parentPanel) {
        parentPanel.setLayout(new BorderLayout());

        // Create a container for background and buttons
        JPanel container = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                drawBackground(g2);
            }
        };
        container.setOpaque(false);

        // Center the button panel
        container.add(buttonPanel, BorderLayout.CENTER);

        // Add the container to the parent panel
        parentPanel.add(container, BorderLayout.CENTER);

        // Ensure the parent panel is revalidated and repainted
        parentPanel.revalidate();
        parentPanel.repaint();
    }

}