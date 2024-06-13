package com.grupo.game.gameentities.strategy;

import com.grupo.game.math.Coordinates;

public interface Strategy {

    Coordinates addShips();
    
    Coordinates attack();
} 