package com.grupo.game.scenes;

import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;


public class SceneManager implements ButtonClickListener {
    private Scene currentScene;
    private SinkFleetSwingRenderer renderer;
    private SinkFleetGame sinkFleetGame;

    public SceneManager(SinkFleetSwingRenderer renderer, SinkFleetGame sinkFleetGame) {
        this.renderer = renderer;
        this.sinkFleetGame = sinkFleetGame;
    }

    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
        if (renderer != null) {
            renderer.setCurrentScene(scene);
        }
    }

    @Override
    public void onSinglePlayerClicked() {
        SinglePlayerScene singlePlayerScene = new SinglePlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND, this);
        setCurrentScene(singlePlayerScene);
    }

    @Override
    public void onMultiPlayerClicked() {
        sinkFleetGame.setMode(BlackBoard2.Mode.MULTI_PLAYER);
        MultiPlayerScene multiPlayerScene = new MultiPlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND, this);
        setCurrentScene(multiPlayerScene);
    }

    @Override
    public void onExitClicked() {
        // Lógica para salir del juego
        System.exit(0);
    }
}