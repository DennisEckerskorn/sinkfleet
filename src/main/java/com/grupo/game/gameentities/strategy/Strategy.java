package com.grupo.game.gameentities.strategy;

import com.grupo.game.math.Coordinates;

/**
 * The Strategy interface defines methods for strategies used in the Sink Fleet game,
 * specifically for adding ships and performing attacks.
 */
public interface Strategy {

    /**
     * Retrieves the coordinates for adding ships on the game board.
     *
     * @return Coordinates representing where to add the next ship, or null if no more ships are to be added.
     */
    Coordinates addShips();

    /**
     * Retrieves the coordinates for attacking the opponent's game board.
     *
     * @return Coordinates representing where to attack next, or null if no more attacks are to be performed.
     */
    Coordinates attack();
} 