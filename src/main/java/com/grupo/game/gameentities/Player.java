package com.grupo.game.gameentities;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.entities.PlayableEntity;
import com.grupo.engine.input.KeyboardManager;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.core.SinkFleetEntityManager;
import com.grupo.game.input.NumericKeyboardManager;
import com.grupo.game.math.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Player extends PlayableEntity {
    private static final int[] SHIP_SIZES = {5, 4, 3, 3, 3, 2 ,2};

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

    private int shipIndex;

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

        this.shipIndex = 0;
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

    
    public void hit(){
        disparos.add(new Coordinates(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY)));
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

    //public boolean addShip(int x, int y, int size, boolean isHorizontal) {
    //    Ship ship = ((SinkFleetEntityManager) Blackboard.entityManager).createShip(x, y, size, isHorizontal);
    //    if (ship == null) {
    //        return false;
    //    }
    //    return ships.add(ship);
    //}

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
                ((NumericKeyboardManager) getKeyboardManager()).setEnterPressed(false);
                ((NumericKeyboardManager) getKeyboardManager()).clearPosX();
                ((NumericKeyboardManager) getKeyboardManager()).clearPosY();
            if (BlackBoard2.beginGame) {
                //System.out.println(addShip(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY), 3, isHorizontal));
                addShips();
                if (shipIndex == SHIP_SIZES.length) {
                    Player tmp = BlackBoard2.currentPlayer;
                    BlackBoard2.currentPlayer = BlackBoard2.opponentPlayer;
                    BlackBoard2.opponentPlayer = tmp;                
            
                }
                System.out.println("Numero de barcos: " + BlackBoard2.currentPlayer.getShips().size() + " " + BlackBoard2.opponentPlayer.getShips().size());
                if (BlackBoard2.currentPlayer.getShips().size() == 7 && BlackBoard2.opponentPlayer.getShips().size() == 7) {
                    BlackBoard2.beginGame = false;
                }

            }
            else{
                //TODO: Implementar disparos
                hit();
                System.out.println("Disparo en: " + actualPostionX + " " + actualPostionY);
                Player tmp = BlackBoard2.currentPlayer;
                BlackBoard2.currentPlayer = BlackBoard2.opponentPlayer;
                BlackBoard2.opponentPlayer = tmp;  
            }
                
        }
    }
    public void addShips(){
        Ship barco  = ((SinkFleetEntityManager) Blackboard.entityManager).createShip(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY), SHIP_SIZES[shipIndex], isHorizontal, true);
        boolean a = inGrid(barco);
        System.out.println(barco.toString() + " barco");
        System.out.println(a);
        if (!collision(barco) && a) {
            ((SinkFleetEntityManager) Blackboard.entityManager).addEntity(barco);
            shipIndex++;
            ships.add(barco);
            
        }else{
            Ship barco2  = ((SinkFleetEntityManager) Blackboard.entityManager).createShip(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY), SHIP_SIZES[shipIndex], isHorizontal, false);
            boolean b = inGrid(barco2);

            if (!collision(barco2) && b) {
                ((SinkFleetEntityManager) Blackboard.entityManager).addEntity(barco2);
                shipIndex++;
                ships.add(barco2);
            }else{
                System.out.println("No se puede colocar el barco");
            }
        }
        
    }

    public boolean inGrid(Ship ship) {
        for (ShipFragments fragment : ship.getShipFragments()) {
            if (!fragment.inGrid(rows, cols)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Comprueba si hay colision entre el barco actual y los barcos existentes en el tablero de juego de ese jugador.
     * @param ship El barco a comprobar si colisiona.
     * @return
     */
    public boolean collision(Ship ship) {
        for (Ship ship1 : ships) {
            if (ship1.collides(ship)) 
                return true; //Hay colision
        }
        return false; // No hay colision
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
        //System.out.println( "x: " + actualPostionX + " y: " + actualPostionY + " horizontal: " + isHorizontal + " enter: " + enterPressed);
    }

    @Override
    public String toString() {
        return "Player [ships=" + ships + ", disparos=" + disparos + ", hp=" + super.getHp() + ", keyboardManager=" + getKeyboardManager() + "]";
    }

    public Ship createShip(float x, float y, int size, boolean isHorizontal, boolean direction) {
        Ship fleet = new Ship(x, y, size, x, size, isHorizontal, y, size, direction);
        
        return fleet;
    }
}
