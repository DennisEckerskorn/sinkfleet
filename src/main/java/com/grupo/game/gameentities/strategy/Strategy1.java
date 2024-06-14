package com.grupo.game.gameentities.strategy;

import com.grupo.engine.core.Blackboard;
import com.grupo.game.config.Settings;
import com.grupo.game.math.Coordinates;

import java.util.Random;

/**
 * Strategy1 is a basic implementation of the Strategy interface for the Sink Fleet game,
 * providing simple strategies for ship placement and attacking.
 */
public class Strategy1 implements Strategy {
    private final Coordinates[] ships;
    int shipIndex = 0;

    private final Coordinates[] attacks;
    int attackIndex = 0;
    private final Random random;

    /**
     * Constructs a Strategy1 object.
     * Initializes arrays for ship positions and attack coordinates based on game settings.
     */
    public Strategy1() {
        this.ships = new Coordinates[Settings.NUM_SHIPS];
        this.attacks = new Coordinates[Settings.ROWS * Settings.COLS];
        this.random = new Random();

        // Initialize ships array with dummy coordinates
        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Coordinates(i, i);
        }

        // Initialize attacks array with all possible grid coordinates
        int rows = 0;
        int cols = 0;
        for (int i = 0; i < attacks.length; i++) {
            attacks[i] = new Coordinates(rows, cols);
            cols++;
            if (cols >= Settings.COLS) {
                cols = 0;
                rows++;
            }
        }
    }

    /**
     * Retrieves the next ship coordinates to be added.
     *
     * @return Coordinates of the ship to be added, or null if all ships are added.
     */
    @Override
    public Coordinates addShips() {
        if (shipIndex >= ships.length) {
            return null;
        }
        return ships[shipIndex++];
    }

    /**
     * Retrieves the next attack coordinates.
     *
     * @return Coordinates of the next attack, or null if all attacks are made.
     */
    @Override
    public Coordinates attack() {
        if (attackIndex >= attacks.length) {
            return null;
        }
        return attacks[attackIndex++];
    }
}
