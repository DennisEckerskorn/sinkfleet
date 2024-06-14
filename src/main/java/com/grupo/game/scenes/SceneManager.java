package com.grupo.game.scenes;

import com.grupo.engine.core.AssetManager;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;

import javax.swing.*;

/**
 * Manages different scenes and their transitions in the game.
 */
public class SceneManager implements ButtonClickListener {
    private Scene currentScene;
    private SinkFleetSwingRenderer renderer;
    private SinkFleetGame sinkFleetGame;
    private MenuScene menuScene;
    private AssetManager assetManager;

    /**
     * Constructs a SceneManager with the specified renderer and game instance.
     *
     * @param renderer      The renderer for rendering scenes.
     * @param sinkFleetGame The SinkFleetGame instance managing the game state.
     */
    public SceneManager(SinkFleetSwingRenderer renderer, SinkFleetGame sinkFleetGame, AssetManager assetManager) {
        this.renderer = renderer;
        this.sinkFleetGame = sinkFleetGame;
        this.assetManager = assetManager;
        this.menuScene = new MenuScene(assetManager);

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
        JOptionPane.showMessageDialog(null, "Bienvenido, por favor coloca tus barcos, ingresa las coordenadas", "Start Single Player", JOptionPane.INFORMATION_MESSAGE);
        sinkFleetGame.setMode(BlackBoard2.Mode.SINGLE_PLAYER);
        SinglePlayerScene singlePlayerScene = new SinglePlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND, this);
        setCurrentScene(singlePlayerScene);

    }

    /**
     * Handles the action when the multiplayer button is clicked.
     */
    @Override
    public void onMultiPlayerClicked() {
        JOptionPane.showMessageDialog(null, "Bienvenido, por favor coloca tus barcos, ingresa las coordenadas", "Start Multiplayer Player", JOptionPane.INFORMATION_MESSAGE);
        sinkFleetGame.setMode(BlackBoard2.Mode.MULTI_PLAYER);
        MultiPlayerScene multiPlayerScene = new MultiPlayerScene(Settings.COLOR_BACKGROUND, Settings.COLOR_BACKGROUND, this);
        setCurrentScene(multiPlayerScene);
    }

    /**
     * Handles the action when the exit button is clicked.
     * Exits the game.
     */
    @Override
    public void onExitClicked() {
        int option = JOptionPane.showConfirmDialog(null, "Seguro que quieres salir?");
        if (option == JOptionPane.YES_OPTION) {
            exitGame();
        }
    }

    public void exitGame() {
        System.exit(0);
    }

    /**
     * Sets the game over scene.
     */
    public void onGameOver() {
        JOptionPane.showMessageDialog(null, "Game Over! Has perdido!.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        GameOverScene gameOverScene = new GameOverScene(this);
        setCurrentScene(gameOverScene);
    }

    /**
     * Sets the winner scene.
     */
    public void onWinner(String nombre) {
        JOptionPane.showMessageDialog(null, "Enhorabuena! Has ganado el juego!", "Win", JOptionPane.INFORMATION_MESSAGE);
        WinnerScene winnerScene = new WinnerScene(this, nombre);
        setCurrentScene(winnerScene);
    }

    /**
     * Handles the action return to main menu
     */
    public void onReturnToMainMenu() {
        setCurrentScene(menuScene);
    }

    /**
     * Handles the action when a successful hit is made.
     */
    public void onSuccessfulHit() {
        JOptionPane.showMessageDialog(null, "Impacto exitoso!", "Hit", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Handles the action when a ship is sunk.
     */
    public void onShipSunk() {
        JOptionPane.showMessageDialog(null, "Barco hundido!", "Ship Sunk", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Notifies the player that it's their turn.
     */
    public void notifyPlayerTurn() {
        JOptionPane.showMessageDialog(null, "Es tu turno!", "Turno", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Notifies the player that the game has started.
     */
    public void notifyGameStart() {
        JOptionPane.showMessageDialog(null, "El juego ha comenzado!", "Inicio del juego", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Informs the player that they cannot shoot at a specific position.
     */
    public void notifyCannotShoot() {
        JOptionPane.showMessageDialog(null, "No se puede disparar en esta posicion.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Informs the player that all ships have been placed and it's time to start the game.
     */
    public void notifyAllShipsPlaced() {
        JOptionPane.showMessageDialog(null, "Todos los barcos han sido colocados! Comienza el juego.", "Comienzo del juego", JOptionPane.INFORMATION_MESSAGE);
    }
}