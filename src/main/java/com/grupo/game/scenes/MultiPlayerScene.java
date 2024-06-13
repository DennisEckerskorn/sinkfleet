package com.grupo.game.scenes;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.entities.Entity;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.gameentities.Player;
import com.grupo.game.gameentities.Ship;
import com.grupo.game.gameentities.ShipFragments;
import com.grupo.game.math.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Represents a scene for multiplayer gameplay.
 */
public class MultiPlayerScene extends Scene {
    private Color backgroundColor1;
    private Color backgroundColor2;
    private Player currentPlayer;
    private SceneManager sceneManager;
    private JButton button;
    private JTextField textFieldShips1;
    private JTextField textFieldShots1;
    private JTextField textFieldShips2;
    private JTextField textFieldShots2;
    private Timer timer;

    /**
     * Constructs a MultiPlayerScene with specified background colors and scene manager.
     *
     * @param backgroundColor1 The color of the first background.
     * @param backgroundColor2 The color of the second background.
     * @param sceneManager     The scene manager.
     */
    public MultiPlayerScene(Color backgroundColor1, Color backgroundColor2, SceneManager sceneManager) {
        this.backgroundColor1 = backgroundColor1;
        this.backgroundColor2 = backgroundColor2;
        this.sceneManager = sceneManager;
        button = new JButton("Button");
        textFieldShips1 = new JTextField();
        textFieldShots1 = new JTextField();
        textFieldShips2 = new JTextField();
        textFieldShots2 = new JTextField();

        // Hacer los JTextField no editables
        textFieldShips1.setEditable(false);
        textFieldShots1.setEditable(false);
        textFieldShips2.setEditable(false);
        textFieldShots2.setEditable(false);
    }

   /**
     * Renders the scene with the given Graphics2D object.
     *
     * @param g2 The Graphics2D object.
     */
    @Override
    public void render(Graphics2D g2) {
            updateGameInfo(BlackBoard2.currentPlayer);
            drawEntity(g2, BlackBoard2.currentPlayer);
        
        
    }

    /**
     * Draws the given entity with the specified Graphics2D object.
     *
     * @param g2 The Graphics2D object.
     * @param e  The entity to draw.
     */
    @Override
    public void drawEntity(Graphics2D g2, Entity e) {
        List<Ship> ships;
        List<Coordinates> disparos;
        if (e instanceof Player) {
            if (BlackBoard2.currentPlayer.equals((Player) e)) {
                ships = ((Player) e).getShips();
                for (Ship ship : ships) {
                    g2.setColor(Settings.COLOR_SHIP);
                    for (ShipFragments fragment : ship.getShipFragments()) {
                        int x = Math.round(fragment.getX() * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET);
                        int y = Math.round(fragment.getY() * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET);
                        g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
                    }
                }
            }
            disparos = ((Player) e).getDisparos();
            g2.setColor(Color.RED);
            for (int i = 0; i < disparos.size(); i++) {

               

                int x = disparos.get(i).getX() * Blackboard.cellSize + Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS + Settings.GAMEBOARD_OFFSET;
                int y = disparos.get(i).getY() * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET;
                if(BlackBoard2.opponentPlayer.isHitBoard(disparos.get(i).getX(), disparos.get(i).getY()))
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.BLUE  );
                g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            }
        }
    }

    /**
     * Draws the background of the scene with the given Graphics2D object.
     *
     * @param g2 The Graphics2D object.
     */
    @Override
    public void drawBackground(Graphics2D g2) {
        // Calcula el desplazamiento para el segundo tablero
        int offset = Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS;

        // Dibuja el fondo del primer tablero (Tablero Barcos)
        g2.setColor(backgroundColor1);
        g2.fillRect(Settings.GAMEBOARD_OFFSET, Settings.GAMEBOARD_OFFSET, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize);

        // Dibuja el fondo del segundo tablero (Tablero de Disparos)
        g2.setColor(backgroundColor2);
        g2.fillRect(Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS + Settings.GAMEBOARD_OFFSET, Settings.GAMEBOARD_OFFSET, Settings.COLS * Blackboard.cellSize, Settings.ROWS * Blackboard.cellSize);

        // Dibuja las líneas del primer tablero (Tablero Barcos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET, row * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET, Blackboard.cellSize, Blackboard.cellSize);
            }
        }

        // Dibuja las líneas del segundo tablero (Tablero de Disparos)
        for (int row = 0; row < Settings.ROWS; row++) {
            for (int col = 0; col < Settings.COLS; col++) {
                g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                g2.drawRect(col * Blackboard.cellSize + offset + Settings.GAMEBOARD_OFFSET, row * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET, Blackboard.cellSize, Blackboard.cellSize);
            }
        }

        drawNumberCoordinates(g2, Settings.GAMEBOARD_OFFSET);
        drawNumberCoordinates(g2, offset + Settings.GAMEBOARD_OFFSET);
    }

    /**
     * Draws number coordinates around the board.
     *
     * @param g2     The Graphics2D object.
     * @param offset The offset for drawing the coordinates.
     */
    private void drawNumberCoordinates(Graphics2D g2, int offset) {
        g2.setColor(Color.BLACK);

        //Dibujar numero de filas:
        for (int row = 0; row < Settings.ROWS; row++) {
            g2.drawString(Integer.toString(row), offset - 20, (row + 1) * Blackboard.cellSize + 4);
        }

        //Dibujar numero de columnas:
        for (int col = 0; col < Settings.COLS; col++) {
            g2.drawString(Integer.toString(col), offset + col * Blackboard.cellSize + Blackboard.cellSize / 2, 20);
        }
    }

    /**
     * Called when the scene is set.
     *
     * @param parentPanel The parent panel.
     */
    @Override
    public void onSceneSet(JPanel parentPanel) {
        // Set the layout of the parent panel to BorderLayout
        parentPanel.setLayout(new BorderLayout());

        // Create a panel for the game boards
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                // Draw the game boards
                drawBackground(g2);
                render(g2);
            }
        };

        // Add the game panel to the parent panel's CENTER
        parentPanel.add(gamePanel, BorderLayout.CENTER);

        // Add mouse listener to game panel
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Determine cell clicked based on mouse coordinates
                int cellSize = Blackboard.cellSize;
                int x = e.getX();
                int y = e.getY();
                int col = (x - Settings.GAMEBOARD_OFFSET) / cellSize;
                int row = (y - Settings.GAMEBOARD_OFFSET) / cellSize;

                // Perform actions based on the clicked cell
                // For example, you can call a method to handle placing ships or firing shots
                handleCellClick(row, col);
            }
        });

        // Create and add the button
        button = new JButton("CAMBIAR TURNO");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Implementar click en boton, REVISAR KEVIN

            }
        });
        parentPanel.add(button, BorderLayout.SOUTH);

        textFieldShips1 = new JTextField();
        textFieldShips1.setEditable(false);

        textFieldShots1 = new JTextField();
        textFieldShots1.setEditable(false);

        textFieldShips2 = new JTextField();
        textFieldShips2.setEditable(false);

        textFieldShots2 = new JTextField();
        textFieldShots2.setEditable(false);

        JPanel textFieldsPanel1 = new JPanel();
        textFieldsPanel1.setLayout(new GridLayout(1, 2));
        textFieldsPanel1.add(textFieldShips1);
        textFieldsPanel1.add(textFieldShots1);

        JPanel textFieldsPanel2 = new JPanel();
        textFieldsPanel2.setLayout(new GridLayout(1, 2));
        textFieldsPanel2.add(textFieldShips2);
        textFieldsPanel2.add(textFieldShots2);

        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(2, 1));
        textFieldsPanel.add(textFieldsPanel1);
        textFieldsPanel.add(textFieldsPanel2);

        parentPanel.add(textFieldsPanel, BorderLayout.NORTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameInfo(BlackBoard2.currentPlayer);
            }
        });
        timer.start();
    }

    /**
     * Handles the action when a cell on the game board is clicked.
     *
     * @param row The row index of the clicked cell.
     * @param col The column index of the clicked cell.
     */
    private void handleCellClick(int row, int col) {
        // Implement logic for handling the click on the cell here
        // For example, place a ship or fire a shot
        /*
       if(BlackBoard2.beginGame) {
           currentPlayer.processInput();
       } else {
           currentPlayer.processInput();
       }

         */
    }

    /**
     * Updates the game information displayed on the UI.
     *
     * @param player The current player.
     */
    public void updateGameInfo(Player player) {
        if (player == null) {
            textFieldShips1.setText("Barcos Jugador: 0");
            textFieldShots1.setText("Disparos Jugador: ");
            textFieldShips2.setText("Barcos NPC: 0");
            textFieldShots2.setText("Disparos NPC: ");
            return;
        }

        // Obtener la información actualizada del jugador
        int shipCount1 = player.getShips().size();
        List<Coordinates> coordinates1 = player.getDisparos();

        // Asignar información al primer tablero
        textFieldShips1.setText("Barcos Jugador: " + shipCount1);
        textFieldShots1.setText("Disparos Jugador: " + formatCoordinates(coordinates1));
/*
        // Asignar información al segundo tablero (ajustar según sea necesario)
        textFieldShips2.setText("Barcos NPC: " + shipCount2);
        textFieldShots2.setText("Disparos NPC: " + formatCoordinates(coordinates2));

 */
    }

    /**
     * Formats a list of coordinates into a string representation.
     *
     * @param coordinates The list of coordinates.
     * @return A string representation of the coordinates.
     */
    private String formatCoordinates(List<Coordinates> coordinates) {
        StringBuilder coordinatesString = new StringBuilder();
        for (Coordinates coord : coordinates) {
            coordinatesString.append(String.format("(%d, %d) ", coord.getX(), coord.getY()));
        }
        return coordinatesString.toString();
    }


}
