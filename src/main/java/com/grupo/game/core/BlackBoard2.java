package com.grupo.game.core;

import com.grupo.game.gameentities.Player;
import com.grupo.game.scenes.SceneManager;

/**
 * Class representing a blackboard for managing game-related data.
 */
public class BlackBoard2 {
    public static Player currentPlayer;
    public static Player opponentPlayer;
    public static Mode mode;
    public static boolean buttonPressed = false;
    public static boolean beginGame = true;
    public static SceneManager sceneManager;
    public static boolean resetListenner = false;

    /**
     * Enumeration representing different game modes.
     */
    public enum Mode {
        SINGLE_PLAYER,
        MULTI_PLAYER
    }

}
