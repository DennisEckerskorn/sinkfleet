package com.grupo.game.gameentities;

import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.math.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player extends PlayableEntity {
    private List<Ship> ships;
    private List<Coordinates> disparos;
    private Stack<String> stack;
    private int hp;
    private KeyboardManager keyboardManager;

    private float actualPostionX;
    private float actualPostionY;
    private boolean isHorizontal;
    private int rows;
    private int cols;

    public Player(float x, float y, float width, float height, int hp, float damage, KeyboardManager keyboardManager, int rows, int cols) {
        super(x, y, width, height, hp, damage, x, y, y, cols, keyboardManager);
        this.ships = new ArrayList<>(hp);
        this.hp = hp;
        this.disparos = new ArrayList<>();
        this.stack = new Stack<>();
        this.keyboardManager = keyboardManager;
        this.actualPostionX = 1;
        this.actualPostionY = 1;
        this.isHorizontal = true;
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean getIsHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
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

    public boolean addShip(Ship ship) {
        return ships.add(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Coordinates> getDisparos() {
        return disparos;
    }

    public List<String> getStack() {
        return stack;
    }

    public float getActualPostionX() {
        return actualPostionX;
    }

    public void setActualPostionX(float actualPostionX) {
        this.actualPostionX = actualPostionX;
    }

    public float getActualPostionY() {
        return actualPostionY;
    }

    public void setActualPostionY(float actualPostionY) {
        this.actualPostionY = actualPostionY;
    }

    public KeyboardManager getKeyboardManager() {
        return keyboardManager;
    }

    @Override
    public void update(double deltaTime) {}

    @Override
    public void lastUpdate(double deltaTime) {}

    @Override
    public void postUpdate(double deltaTime) {}

    @Override
    public void processInput() {
        if (keyboardManager.isUp()) stack.add("W");
        if (keyboardManager.isDown()) stack.add("S");
        if (keyboardManager.isLeft()) stack.add("A");
        if (keyboardManager.isRight()) stack.add("D");
        if (keyboardManager.isFire()) stack.add("F");

        if (stack.size() > 2) {
            stack.clear();
        }
    }

    @Override
    public String toString() {
        return "Player [ships=" + ships + ", disparos=" + disparos + ", stack=" + stack + ", hp=" + hp + ", keyboardManager=" + keyboardManager + "]";
    }
}
