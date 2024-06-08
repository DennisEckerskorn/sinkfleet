package com.grupo.game.scenes;

import com.grupo.game.graphics.SinkFleetSwingRenderer;


public class SceneManager implements ButtonClickListener {
    private Scene currentScene;
    private SinkFleetSwingRenderer renderer;

    public SceneManager(SinkFleetSwingRenderer renderer) {
        this.renderer = renderer;
    }

    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
        if (renderer != null) {
            renderer.setCurrentScene(scene);
        }
    }

    @Override
    public void onSinglePlayerClicked() {
        // Lógica para iniciar el modo de un solo jugador
        // Por ejemplo:
        //SinglePlayerScene singlePlayerScene = new SinglePlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND);
        //setCurrentScene(singlePlayerScene);
    }

    @Override
    public void onMultiPlayerClicked() {
        // Lógica para iniciar el modo multijugador
        // Por ejemplo:
        // MultiPlayerScene multiPlayerScene = new MultiPlayerScene();
        // setCurrentScene(multiPlayerScene);
    }

    @Override
    public void onExitClicked() {
        // Lógica para salir del juego
        System.exit(0);
    }
}