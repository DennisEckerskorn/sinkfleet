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

public class Main {
    public static void main(String[] args) {
        SinkFleetGame sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES, BlackBoard2.Mode.SINGLE_PLAYER);
        SinkFleetAssetManager assetManager = new SinkFleetAssetManager();
        SinkFleetSwingRenderer renderer = new SinkFleetSwingRenderer(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame, Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND);
        sinkFleetGame.setRenderAPI(renderer);

        SceneManager sceneManager = new SceneManager(renderer);
        MenuScene menuScene = new MenuScene(assetManager, sceneManager);
        SinglePlayerScene singlePlayerScene = new SinglePlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND, sceneManager);
        sceneManager.setCurrentScene(singlePlayerScene);

        JFrame frame = new JFrame("Battleship");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(renderer);
        frame.pack();
        frame.setVisible(true);
        sinkFleetGame.start();
    }
}