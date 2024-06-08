package com.grupo;

import com.grupo.engine.core.AssetManager;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetAssetManager;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;
import com.grupo.game.scenes.ButtonClickListener;
import com.grupo.game.scenes.MenuScene;

import javax.swing.*;
import java.awt.*;

public class GameManager implements ButtonClickListener {
    private JFrame frame;
    private SinkFleetGame sinkFleetGame;
    private SinkFleetSwingRenderer renderer;
    private AssetManager assetManager;
    private MenuScene menuScene;

    public void run() {
        setupGame();
        setupRenderer();
        setupMenuScene();
        frame = new JFrame("Battleship");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(renderer.getCurrentScenePanel());
        frame.pack();
        frame.setSize(1920,1080);
        frame.setVisible(true);
    }

    private void setupGame() {
        sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES, BlackBoard2.Mode.SINGLE_PLAYER);
    }

    private void setupRenderer() {
        renderer = new SinkFleetSwingRenderer(Settings.WIDTH, Settings.HEIGHT, sinkFleetGame, Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND);
    }

    private void setupMenuScene() {
        assetManager = new SinkFleetAssetManager();
        menuScene = new MenuScene(assetManager, this);
        menuScene.setButtonClickListener(this);
        sinkFleetGame.setRenderAPI(renderer);
        renderer.setCurrentScene(menuScene);
    }

    private void setupGameScene() {
        //gameScene = new GameScene(sinkFleetGame, renderer);
        renderer.setCurrentScene(null);
    }

    private void startGame(BlackBoard2.Mode mode) {
        sinkFleetGame.setMode(mode);
        setupGameScene();
        frame.getContentPane().removeAll();
        frame.setContentPane(renderer);
        frame.revalidate();
        frame.repaint();
        sinkFleetGame.start();
    }

    @Override
    public void onSinglePlayerClicked() {
        startGame(BlackBoard2.Mode.SINGLE_PLAYER);
    }

    @Override
    public void onMultiPlayerClicked() {
        // TODO: Implement multiplayer game logic
        //startGame(BlackBoard2.Mode.MULTI_PLAYER);
    }

    @Override
    public void onExitClicked() {
        System.exit(0);
    }
}