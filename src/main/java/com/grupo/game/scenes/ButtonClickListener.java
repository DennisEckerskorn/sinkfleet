package com.grupo.game.scenes;

/**
 * An interface for listening to button clicks in a scene.
 */
public interface ButtonClickListener {
    /**
     * Called when the single player button is clicked.
     */
    void onSinglePlayerClicked();

    /**
     * Called when the multiplayer button is clicked.
     */
    void onMultiPlayerClicked();

    /**
     * Called when the exit button is clicked.
     */
    void onExitClicked();
}
