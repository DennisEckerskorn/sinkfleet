package com.grupo.game.config;

import java.awt.*;

/**
 * The Settings class contains various configuration parameters for the Sink Fleet game.
 * These parameters include game dimensions, target frame rate, colors, and window settings.
 */
public class Settings {
    //Game Settings
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static final int ROWS = 10;
    public static final int COLS = 10;
    public static final int TARGET_FPS = 60;
    public static final int TARGET_UPS = 60;
    public static final int MAX_ENTITIES = 10;
    public static final int NUM_SHIPS = 7;
    public static final int PLAYER_HP = NUM_SHIPS;
    public static final int PLAYER_DAMAGE = 0;

    // Color Settings
    public static final Color COLOR_BACKGROUND = Color.cyan;
    public static final Color COLOR_BACKGROUND_LINES = Color.black;
    public static final Color COLOR_SHIP = Color.black;

    //Windows Settings
    public static final int SPACE_BETWEEN_GAMEBOARDS = 30;
    public static final int GAMEBOARD_OFFSET = 30;

    /**
     * The Direction enum represents the four cardinal directions: UP, DOWN, LEFT, and RIGHT.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

}
