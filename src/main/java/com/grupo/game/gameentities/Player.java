package com.grupo.game.gameentities;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetEntityManager;
import com.grupo.game.input.NumericKeyboardManager;
import com.grupo.game.math.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Player extends PlayableEntity {
    //Propiedades principales del jugador
    private List<Ship> ships;
    private List<Coordinates> disparos;
  
   
    //Disparo actual
    private String actualPostionX;
    private String actualPostionY;
    //Posicionamiento de los barcos
    private boolean isHorizontal;

    private boolean enterPressed;

    //Tamaño del tablero
    private int rows;
    private int cols;

    public Player(float x, float y, float width, float height, int hp, float damage, KeyboardManager keyboardManager, int rows, int cols) {
        super(x, y, width, height, hp, damage, 0, 0, 0, 0, keyboardManager);
        this.ships = new ArrayList<>(hp);
        this.disparos = new ArrayList<>();
        
        //Coordenada de pisparo
        this.actualPostionX = "-1";
        this.actualPostionY = "-1";

        //Orientacion de los barcos
        this.isHorizontal = true;

        //Tamaño del tablero
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

    public boolean addShip(int x, int y, int size, boolean isHorizontal) {
        Ship ship = ((SinkFleetEntityManager) Blackboard.entityManager).spawnShip(x, y, size, isHorizontal);
        if (ship == null) {
            return false;
        }
        return ships.add(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Coordinates> getDisparos() {
        return disparos;
    }

  

    public int getActualPostionX() {
        return Integer.parseInt(actualPostionX);
    }

    
    public int getActualPostionY() {
        return Integer.parseInt(actualPostionY);
    }


    public KeyboardManager getKeyboardManager() {
        return super.getKeyboardManager();
    }

    @Override
    public void update(double deltaTime) {
        
        if (enterPressed) {
            
            System.out.println(addShip(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY), 3, isHorizontal));
            ((NumericKeyboardManager) getKeyboardManager()).setEnterPressed(false);
            ((NumericKeyboardManager) getKeyboardManager()).clearPosX();
            ((NumericKeyboardManager) getKeyboardManager()).clearPosY();
            System.out.println("Ship added");

        }
    }

    @Override
    public void lastUpdate(double deltaTime) {}

    @Override
    public void postUpdate(double deltaTime) {}

    @Override
    public void processInput() {
        NumericKeyboardManager keyboardManager = (NumericKeyboardManager) getKeyboardManager();
        actualPostionX = keyboardManager.getPosX();
        actualPostionY = keyboardManager.getPosY();
        isHorizontal = keyboardManager.isHorizontal();
        enterPressed = keyboardManager.isEnterPressed();
        System.out.println( "x: " + actualPostionX + " y: " + actualPostionY + " horizontal: " + isHorizontal + " enter: " + enterPressed);
    }

    @Override
    public String toString() {
        return "Player [ships=" + ships + ", disparos=" + disparos + ", hp=" + super.getHp() + ", keyboardManager=" + getKeyboardManager() + "]";
    }
}
