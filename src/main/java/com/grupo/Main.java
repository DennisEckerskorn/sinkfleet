package com.grupo;


import com.grupo.engine.graphics.swing.SwingRenderer;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetAssetManager;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;
import com.grupo.game.scenes.MenuScene;
import com.grupo.game.scenes.SceneManager;
import com.grupo.game.scenes.SinglePlayerScene;

import javax.swing.*;

/**
 * The Main class serves as the entry point for the Sink Fleet game.
 * It initializes the game components, including the game instance, asset manager, renderer, and scene manager.
 * The main method sets up the game window and starts the game loop.
 */
public class Main {
    /**
     * The main method is the entry point for the Sink Fleet game.
     * It initializes and sets up the game components, creates the game window, and starts the game loop.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create an instance of SinkFleetGame with the specified settings
        SinkFleetGame sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES, BlackBoard2.Mode.SINGLE_PLAYER);

        // Create an instance of SinkFleetAssetManager
        SinkFleetAssetManager assetManager = new SinkFleetAssetManager();

        // Create an instance of SinkFleetSwingRenderer for rendering the game
        SinkFleetSwingRenderer renderer = new SinkFleetSwingRenderer(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame);

        // Set the render API for the game
        sinkFleetGame.setRenderAPI(renderer);

        // Create an instance of SceneManager for managing game scenes
        SceneManager sceneManager = new SceneManager(renderer, sinkFleetGame);

        // Create an instance of MenuScene for the main menu
        MenuScene menuScene = new MenuScene(assetManager, sceneManager);

        // Set the button click listener for the menu scene
        menuScene.setButtonClickListener(sceneManager);

        // Set the current scene to the menu scene
        sceneManager.setCurrentScene(menuScene);

        // Create and configure the game window
        JFrame frame = new JFrame("Battleship");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(renderer);
        frame.pack();
        frame.setVisible(true);

        // Start the game loop
        sinkFleetGame.start();
    }
}