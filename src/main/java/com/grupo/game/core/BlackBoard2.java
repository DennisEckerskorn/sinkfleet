package com.grupo.game.core;

import com.grupo.game.gameentities.Player;

public class BlackBoard2 {
    public static Player currentPlayer;


    public static Mode mode;
    public enum Mode {
        SINGLE_PLAYER,
        MULTI_PLAYER
    }
    
}
