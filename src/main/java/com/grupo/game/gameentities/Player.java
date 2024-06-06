package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.config.Settings;
import com.grupo.game.math.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends PlayableEntity {
    private List<Ship> ships;
    private List<Coordinates> disparos;
    private boolean turno;
    private List<String> stack;
    private int hp;
    private KeyboardManager keyboardManager;

    public Player(float x, float y, float width, float height, int hp, float damage, KeyboardManager keyboardManager, int rows, int cols) {
        super(x, y, width, height, hp, damage, x, y, y, cols, keyboardManager);
        this.ships = new ArrayList<>(hp);
        this.hp = hp;
        this.disparos = new ArrayList<>();
        this.stack = new ArrayList<>();
        this.turno = false;
        this.keyboardManager = keyboardManager;
    }


    


    private void disparar() {
        //TODO: Implementar disparo
    }

    public boolean isHitBoard(float x, float y) {
        for (Ship ship : ships) {
            if (ship.isHitPosition(x, y)) {
                return true;
            }
        }
        return false;
    }

    public int barcosHundidos() {
        int count = 0;
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                count++;
            }
        }
        return count;
    }

    //#region Getters and Setters
    public boolean addShip(Ship ship) {
        return ships.add(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Coordinates> getDisparos() {
        return disparos;
    }

    public boolean isTurno() {
        return turno;
    }

    public List<String> getStack() {
        return stack;
    }

    public KeyboardManager getKeyboardManager() {
        return keyboardManager;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    //#endregion


    @Override
    public void update(double deltaTime) {
        
    }

    @Override
    public void lastUpdate(double deltaTime) {

    }

    @Override
    public void postUpdate(double deltaTime) {

    }

    @Override
    public void processInput() {
        if (turno) {
            if (keyboardManager.isUp())
                stack.add("W");
            if (keyboardManager.isDown())
                stack.add("S");
            if (keyboardManager.isLeft())
                stack.add("A");
            if (keyboardManager.isRight())
                stack.add("D");
            if (keyboardManager.isFire())
                stack.add("F");

            if (stack.size() > 2) {
                stack.clear();
            }
        }
    }





    @Override
    public String toString() {
        return "Player [ships=" + ships + ", disparos=" + disparos + ", turno=" + turno + ", stack=" + stack + ", hp="
                + hp + ", keyboardManager=" + keyboardManager + "]";
    }

   

}
