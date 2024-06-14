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
import java.util.List;

/**
 * Represents a scene for multiplayer gameplay.
 */
public class MultiPlayerScene extends Scene {
    private Color backgroundColor1;
    private Color backgroundColor2;
    private SceneManager sceneManager;
    private JTextField textFieldPlayerOneShips;
    private JTextField textFieldPlayerOneShots;
    private JTextField textFieldInputPlayerOne;
    private JButton exitButton;
    //private JTextField textFieldPlayerTwoShots;
    //private JTextField textFieldInputPlayerTwo;
    //private JTextField textFieldPlayerTwoShips;



    
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
        textFieldPlayerOneShips = new JTextField();
        //textFieldPlayerTwoShots = new JTextField();
        textFieldPlayerOneShots = new JTextField();
        //textFieldPlayerTwoShips = new JTextField();
        textFieldInputPlayerOne = new JTextField();
        //textFieldInputPlayerTwo = new JTextField();
        exitButton = new JButton();
      

        textFieldPlayerOneShips.setEditable(false);
        //textFieldPlayerTwoShots.setEditable(false);
        textFieldPlayerOneShots.setEditable(false);
        //textFieldPlayerTwoShips.setEditable(false);
        textFieldInputPlayerOne.setEditable(false);
        //textFieldInputPlayerTwo.setEditable(false);
    }

    /**
     * Renders the scene with the given Graphics2D object.
     *
     * @param g2 The Graphics2D object.
     */
    @Override
    public void render(Graphics2D g2) {
        try {
            updateGameInfo(BlackBoard2.currentPlayer);
            drawEntity(g2, BlackBoard2.currentPlayer);
        } catch (Exception e) {
            System.out.println("\u001B[32mError: " + e.getMessage() + "\u001B[0m");
            System.out.println(g2);
        }
        
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
                if (BlackBoard2.opponentPlayer.isHitBoard(disparos.get(i).getX(), disparos.get(i).getY()))
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.BLUE);
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

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1));
        infoPanel.setPreferredSize(new Dimension(800, 100));


        // Add text fields to the info panel
        infoPanel.add(new JLabel("Barcos Jugador:"));
        infoPanel.add(textFieldPlayerOneShips);

        infoPanel.add(new JLabel("Disparos Jugador:"));
        infoPanel.add(textFieldPlayerOneShots);

        infoPanel.add(new JLabel("Posicion Jugador:"));
        infoPanel.add(textFieldInputPlayerOne);


        parentPanel.add(infoPanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        // Exit Button
        exitButton.setText("Salir del Juego");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sceneManager.onExitClicked();
            }
        });

       
        buttonPanel.add(exitButton);

        parentPanel.add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameInfo(BlackBoard2.currentPlayer);
            }
        });
        timer.start();
    }

    public void updateGameInfo(Player player) {
        if (player == null) {
            setNullPlayerInfo();
            return;
        }

        // Update players information
        updatePlayerInfo(player);

        // Determine the opponent player
        

        // Update opponent's information
        //updateOpponentInfo(opponentPlayer);
    }

    /**
     * Sets UI fields to "-" when the player is null.
     */
    private void setNullPlayerInfo() {
        textFieldPlayerOneShips.setText("-");
        //textFieldPlayerTwoShips.setText("-");
        textFieldPlayerOneShots.setText("-");
        //textFieldPlayerTwoShots.setText("-");
        textFieldInputPlayerOne.setText("-");
        //textFieldInputPlayerTwo.setText("-");
    }

    /**
     * Updates UI fields with information related to the player.
     *
     * @param player The current player.
     */
    private void updatePlayerInfo(Player player) {
        textFieldPlayerOneShips.setText(String.valueOf(player.getShips().size()) + " Horizontal: " + player.getIsHorizontal());
        textFieldPlayerOneShots.setText(String.valueOf(player.getDisparos().size()));

        textFieldInputPlayerOne.setText(player.getActualPostionX() + " - " + player.getActualPostionY());
    }

   
}