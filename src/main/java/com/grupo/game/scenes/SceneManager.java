package com.grupo.game.scenes;

import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;

/**
 * Manages different scenes and their transitions in the game.
 */
public class SceneManager implements ButtonClickListener {
    private Scene currentScene;
    private SinkFleetSwingRenderer renderer;
    private SinkFleetGame sinkFleetGame;

    /**
     * Constructs a SceneManager with the specified renderer and game instance.
     *
     * @param renderer      The renderer for rendering scenes.
     * @param sinkFleetGame The SinkFleetGame instance managing the game state.
     */
    public SceneManager(SinkFleetSwingRenderer renderer, SinkFleetGame sinkFleetGame) {
        this.renderer = renderer;
        this.sinkFleetGame = sinkFleetGame;
    }

    /**
     * Sets the current scene.
     *
     * @param scene The scene to set as the current scene.
     */
    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
        if (renderer != null) {
            renderer.setCurrentScene(scene);
        }
    }

    /**
     * Handles the action when the single player button is clicked.
     */
    @Override
    public void onSinglePlayerClicked() {
        SinglePlayerScene singlePlayerScene = new SinglePlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND, this);
        setCurrentScene(singlePlayerScene);
    }

    /**
     * Handles the action when the multiplayer button is clicked.
     */
    @Override
    public void onMultiPlayerClicked() {
        //sinkFleetGame.setMode(BlackBoard2.Mode.MULTI_PLAYER);
        MultiPlayerScene multiPlayerScene = new MultiPlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND, this);
        setCurrentScene(multiPlayerScene);
    }

    /**
     * Handles the action when the exit button is clicked.
     * Exits the game.
     */
    @Override
    public void onExitClicked() {
        // Lógica para salir del juego
        System.exit(0);
    }
}