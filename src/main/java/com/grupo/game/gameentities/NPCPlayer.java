package com.grupo.game.gameentities;

import java.util.Random;

import com.grupo.engine.core.Game;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.gameentities.strategy.Strategy;
import com.grupo.game.math.Coordinates;
import com.grupo.lib.LibConf;

/**
 * Represents a non-player controlled player entity in the Sink Fleet game.
 */
public class NPCPlayer extends Player {
    private Random random;
    private Strategy gameStrategy;

    /**
     * Constructs an NPCPlayer object with the specified parameters.
     *
     * @param x      The initial X-coordinate of the NPCPlayer.
     * @param y      The initial Y-coordinate of the NPCPlayer.
     * @param width  The width of the NPCPlayer.
     * @param height The height of the NPCPlayer.
     * @param hp     The health points of the NPCPlayer.
     * @param damage The damage the NPCPlayer can cause.
     * @param rows   The number of rows in the game board grid.
     * @param cols   The number of columns in the game board grid.
     */
    public NPCPlayer(float x, float y, float width, float height, int hp, float damage, int rows, int cols, Strategy gameStrategy) {
        super("NPC", x, y, width, height, hp, damage, null, rows, cols);
        this.random = new Random();
        this.gameStrategy = gameStrategy;

    }

    //#region @Override Methods

    /**
     * Updates the NPCPlayer's state based on the current game state.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        // Check if the NPCPlayer has won the game
        if (isWin()) {
            BlackBoard2.sceneManager.onGameOver();
            LibConf.sleep(1300);
            BlackBoard2.sceneManager.exitGame();
        }

        if (isTurnUsed() && !isWin()) {
            LibConf.sleep(800);
            setTurnUsed(false);
            Player tmp = BlackBoard2.currentPlayer;
            BlackBoard2.currentPlayer = BlackBoard2.opponentPlayer;
            BlackBoard2.opponentPlayer = tmp;

        } else if (BlackBoard2.beginGame) {
            Coordinates s = gameStrategy.addShips();
            super.addShips(s.getX(), s.getY());
            System.out.println("Numero de barcos: " + BlackBoard2.currentPlayer.getShips().size() + " " + BlackBoard2.opponentPlayer.getShips().size());
            // If both players have placed all their ships, start the game
            if (BlackBoard2.currentPlayer.getShips().size() == Settings.NUM_SHIPS && BlackBoard2.opponentPlayer.getShips().size() == Settings.NUM_SHIPS) {
                BlackBoard2.beginGame = false;
                LibConf.sleep(1000);
                Player tmp = BlackBoard2.currentPlayer;
                BlackBoard2.currentPlayer = BlackBoard2.opponentPlayer;
                BlackBoard2.opponentPlayer = tmp;

            }
        } else if (!isTurnUsed()) {
            LibConf.sleep(1300);
            // Attack the opponent player
            Coordinates attack = gameStrategy.attack();
            System.out.println(getNombre() + " Ataque: " + attack.getX() + " " + attack.getY());

            // Safe the attack
            super.hit(attack.getX(), attack.getY());
            // Check if the attack hit a ship
            if (BlackBoard2.opponentPlayer.isHitBoard(attack.getX(), attack.getY())) {
                System.out.println("Barco tocado");
                // Check if the attack sunk a ship
                if (BlackBoard2.opponentPlayer.isSunk(attack.getX(), attack.getY())) {
                    System.out.println("Barco hundido");
                    // Check if all the opponent player's ships are sunk
                    if (BlackBoard2.opponentPlayer.barcosHundidos() == SHIP_SIZES.length) {
                        System.out.println("Juego terminado");
                        setWin(true);
                        
                    }

                }
            }
            setTurnUsed(true);
        }


    }

    /**
     * Performs the last update of the NPCPlayer.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void lastUpdate(double deltaTime) {
    }

    /**
     * Performs post-update operations on the NPCPlayer.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void postUpdate(double deltaTime) {
    }

    /**
     * Processes input for the NPCPlayer.
     */
    @Override
    public void processInput() {
    }
    //#endregion


    //#region Getters and Setters
    public void setGameStrategy(Strategy gameStrategy) {
        this.gameStrategy = gameStrategy;
    }

    //#endregion


}
