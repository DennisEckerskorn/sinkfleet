package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;
import com.grupo.engine.input.KeyboardManager;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    private List<Ship> ships;
    private int[][] disparosRealizados;
    private boolean turno;
    private List<String> stack;
    private KeyboardManager keyboardManager;

    public Player(float x, float y, float width, float height, float hp, float damage, KeyboardManager keyboardManager) {
        super(x, y, width, height, hp, damage);
        this.ships = ships;
        this.disparosRealizados = disparosRealizados;
        this.stack = stack;
        this.turno = turno;
        this.keyboardManager = keyboardManager;
    }

    public void procesarInput() {
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
    public void update(double deltaTime) {
        if (turno && stack.size() == 2) {
            disparar();
            stack.clear();
        }
    }


    private void disparar() {
        String xString = stack.get(0);
        String yString = stack.get(1);
        int x = Character.getNumericValue(Integer.parseInt(xString));
        int y = Character.getNumericValue(Integer.parseInt(yString));

        if (isHitBoard(x, y)) {
            disparosRealizados[x][y] = 2; //acierta
        } else {
            disparosRealizados[x][y] = 1; //falla-agua
        }
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

    public boolean anyadirBarco(Ship ship) {
        return ships.add(ship);
    }

    @Override
    public void lastUpdate(double deltaTime) {

    }

    @Override
    public void postUpdate(double deltaTime) {

    }
}