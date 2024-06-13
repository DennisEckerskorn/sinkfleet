package com.grupo.game.gameentities.strategy;

import com.grupo.engine.core.Blackboard;
import com.grupo.game.config.Settings;
import com.grupo.game.math.Coordinates;
import java.util.Random;

public class Strategy1 implements Strategy{
    private final Coordinates[] ships;
    int shipIndex = 0;

    private final Coordinates[] attacks;
    int attackIndex = 0;
    private final Random random;
    public Strategy1() {
        this.ships = new Coordinates[Settings.NUM_SHIPS];
        this.attacks = new Coordinates[Settings.ROWS * Settings.COLS];
        this.random = new Random();
        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Coordinates(i, i);
        }
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
     * Adds ships to the game board.
     *
     * @return The coordinates of the ship to be added.
     */
    @Override
    public Coordinates addShips() {
        if (shipIndex >= ships.length) {
            return null;
        }
        return ships[shipIndex++];
    }

    @Override
    public Coordinates attack() {
        if (attackIndex >= attacks.length) {
            return null;
        }
        return attacks[attackIndex++];
    }
    
}
