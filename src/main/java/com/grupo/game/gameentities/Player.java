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

/**
 * Represents a player in the Sink Fleet game.
 */
public class Player extends PlayableEntity {
    private static final int[] SHIP_SIZES = {5, 4, 3, 3, 3, 2, 2};

    //Propiedades principales del jugador
    private List<Ship> ships;
    private List<Coordinates> disparos;


    //Disparo actual
    private String actualPostionX;
    private String actualPostionY;
    //Posicionamiento de los barcos
    private boolean isHorizontal;

    private boolean enterPressed; //Aplicar cambios
    private boolean nextTurn; //Siguiente turno
    private boolean turnUsed; //Turno usado

    //Tamaño del tablero
    private int rows;
    private int cols;

    private int shipIndex;

    /**
     * Constructs a Player object with the specified parameters.
     *
     * @param x               The initial X-coordinate of the player.
     * @param y               The initial Y-coordinate of the player.
     * @param width           The width of the player.
     * @param height          The height of the player.
     * @param hp              The health points of the player.
     * @param damage          The damage the player can cause.
     * @param keyboardManager The keyboard manager for controlling player input.
     * @param rows            The number of rows in the game board.
     * @param cols            The number of columns in the game board.
     */
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

        this.enterPressed = false;
        this.nextTurn = false;
        this.turnUsed = false;
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


    public void hit() {
        disparos.add(new Coordinates(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY)));
    }

    /**
     * Checks if the given coordinates correspond to a hit on any of the player's ships.
     *
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return true if the coordinates correspond to a hit, false otherwise.
     */
    public boolean isHitBoard(float x, float y) {
        for (Ship ship : ships) {
            if (ship.isHitPosition(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts the number of sunk ships belonging to the player.
     *
     * @return The number of sunk ships.
     */
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

    /**
     * Updates the player's state based on the current game state.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        // If it's the next turn and the current turn is used, swap players
        if (nextTurn && !BlackBoard2.beginGame && turnUsed) {
            turnUsed = false;
            Player tmp = BlackBoard2.currentPlayer;
            BlackBoard2.currentPlayer = BlackBoard2.opponentPlayer;
            BlackBoard2.opponentPlayer = tmp;
        }

        // Process player input and perform actions accordingly
        if (enterPressed) {
            // If the game has begun, handle ship placement and shooting
            if (BlackBoard2.beginGame) {
                //System.out.println(addShip(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY), 3, isHorizontal));
                addShips();
                // Check if all ships are placed and switch to the opponent's turn
                System.out.println("Numero de barcos: " + BlackBoard2.currentPlayer.getShips().size() + " " + BlackBoard2.opponentPlayer.getShips().size());

                if (shipIndex == SHIP_SIZES.length) {
                    Player tmp = BlackBoard2.currentPlayer;
                    BlackBoard2.currentPlayer = BlackBoard2.opponentPlayer;
                    BlackBoard2.opponentPlayer = tmp;
                }
                // If both players have placed all their ships, start the game
                if (BlackBoard2.currentPlayer.getShips().size() == 7 && BlackBoard2.opponentPlayer.getShips().size() == 7) {
                    BlackBoard2.beginGame = false;
                    //turnUsed = true;
                }

            } else {
                // If the game has started, handle shooting
                if (!turnUsed) {
                    hit();
                    System.out.println("Disparo en: " + actualPostionX + " " + actualPostionY);
                    turnUsed = true;
                }

            }
            // Reset keyboard input flags after processing
            ((NumericKeyboardManager) getKeyboardManager()).setEnterPressed(false);
            ((NumericKeyboardManager) getKeyboardManager()).setNext(false);
            ((NumericKeyboardManager) getKeyboardManager()).clearPosX();
            ((NumericKeyboardManager) getKeyboardManager()).clearPosY();

        }
    }


    /**
     * Adds a ship to the player's fleet based on the current input.
     */
    public void addShips() {
        // Attempt to create and add a ship based on the current input

        Ship barco = ((SinkFleetEntityManager) Blackboard.entityManager).createShip(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY), SHIP_SIZES[shipIndex], isHorizontal, true);
        boolean a = inGrid(barco);
        System.out.println(barco.toString() + " barco");
        System.out.println(a);
        if (!collision(barco) && a) {
            ((SinkFleetEntityManager) Blackboard.entityManager).addEntity(barco);
            shipIndex++;
            ships.add(barco);

        } else {
            // If the ship placement fails, attempt to place the ship in the opposite direction

            Ship barco2 = ((SinkFleetEntityManager) Blackboard.entityManager).createShip(Integer.parseInt(actualPostionX), Integer.parseInt(actualPostionY), SHIP_SIZES[shipIndex], isHorizontal, false);
            boolean b = inGrid(barco2);

            if (!collision(barco2) && b) {
                ((SinkFleetEntityManager) Blackboard.entityManager).addEntity(barco2);
                shipIndex++;
                ships.add(barco2);
            } else {
                System.out.println("No se puede colocar el barco");
            }
        }

    }

    /**
     * Checks if the ship's fragments are within the game board grid.
     *
     * @param ship The ship to check.
     * @return true if all ship fragments are within the grid, false otherwise.
     */
    public boolean inGrid(Ship ship) {
        for (ShipFragments fragment : ship.getShipFragments()) {
            if (!fragment.inGrid(rows, cols)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if there is a collision between the current ship and any existing ships on the player's board.
     *
     * @param ship The ship to check for collisions.
     * @return true if there is a collision, false otherwise.
     */
    public boolean collision(Ship ship) {
        for (Ship ship1 : ships) {
            if (ship1.collides(ship))
                return true; //Hay colision
        }
        return false; // No hay colision
    }

    @Override
    public void lastUpdate(double deltaTime) {
    }

    @Override
    public void postUpdate(double deltaTime) {
    }

    /**
     * Processes player input to update the current position and other flags.
     */
    @Override
    public void processInput() {
        NumericKeyboardManager keyboardManager = (NumericKeyboardManager) getKeyboardManager();
        actualPostionX = keyboardManager.getPosX();
        actualPostionY = keyboardManager.getPosY();
        isHorizontal = keyboardManager.isHorizontal();
        enterPressed = keyboardManager.isEnterPressed();
        nextTurn = keyboardManager.isNextTurn();
        //System.out.println( "x: " + actualPostionX + " y: " + actualPostionY + " horizontal: " + isHorizontal + " enter: " + enterPressed);
    }

    /**
     * Returns a string representation of the Player object.
     *
     * @return A string containing player information.
     */
    @Override
    public String toString() {
        return "Player [ships=" + ships + ", disparos=" + disparos + ", hp=" + super.getHp() + ", keyboardManager=" + getKeyboardManager() + "]";
    }

    /**
     * Creates a new ship entity based on the provided parameters.
     *
     * @param x            The X-coordinate of the ship.
     * @param y            The Y-coordinate of the ship.
     * @param size         The size of the ship.
     * @param isHorizontal true if the ship is horizontal, false otherwise.
     * @param direction    true for positive direction, false for negative direction.
     * @return The created ship entity.
     */
    public Ship createShip(float x, float y, int size, boolean isHorizontal, boolean direction) {
        Ship fleet = new Ship(x, y, size, x, size, isHorizontal, y, size, direction);

        return fleet;
    }
}
