package com.grupo.game.scenes;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.math.Vector2;
import com.grupo.game.config.Settings;
import com.grupo.game.core.BlackBoard2;
import com.grupo.game.gameentities.NPCPlayer;
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
 * Represents a scene for single-player gameplay.
 */
public class SinglePlayerScene extends Scene {
    private Color backgroundColor1;
    private Color backgroundColor2;
    private Player currentPlayer;
    private SceneManager sceneManager;
    private JTextField textFieldPlayerShips;
    private JTextField textFieldNPCShots;
    private JTextField textFieldPlayerShots;
    private JTextField textFieldNPCShips;
    private JTextField textFieldInputPlayer;
    private JTextField textFieldInputNPC;
    private Timer timer;

    /**
     * Constructs a SinglePlayerScene with specified background colors and scene manager.
     *
     * @param backgroundColor1 The color of the first background.
     * @param backgroundColor2 The color of the second background.
     * @param sceneManager     The scene manager.
     */
    public SinglePlayerScene(Color backgroundColor1, Color backgroundColor2, SceneManager sceneManager) {
        this.backgroundColor1 = backgroundColor1;
        this.backgroundColor2 = backgroundColor2;
        this.sceneManager = sceneManager;
        textFieldPlayerShips = new JTextField();
        textFieldNPCShots = new JTextField();
        textFieldPlayerShots = new JTextField();
        textFieldNPCShips = new JTextField();
        textFieldInputPlayer = new JTextField();
        textFieldInputNPC = new JTextField();


        // Hacer los JTextField no editables
        textFieldPlayerShips.setEditable(false);
        textFieldNPCShots.setEditable(false);
        textFieldPlayerShots.setEditable(false);
        textFieldNPCShips.setEditable(false);

        textFieldInputPlayer.setEditable(false);
        textFieldInputNPC.setEditable(false);
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
        if (e instanceof NPCPlayer) {
            for (int row = 0; row < Settings.ROWS; row++) {
                for (int col = 0; col < Settings.COLS; col++) {
                    g2.setColor(Settings.COLOR_BACKGROUND_LINES);
                    g2.fillRect(col * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET, row * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET, Blackboard.cellSize, Blackboard.cellSize);
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
        } else if (e instanceof Player) {
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
        infoPanel.setLayout(new GridLayout(3, 2));
        //infoPanel.setPreferredSize(new Dimension(800, 100));


        // Add text fields to the info panel
        infoPanel.add(new JLabel("Barcos Jugador:"));
        infoPanel.add(textFieldPlayerShips);
        infoPanel.add(new JLabel("Barcos NPC:"));
        infoPanel.add(textFieldNPCShips);

        infoPanel.add(new JLabel("Disparos Jugador:"));
        infoPanel.add(textFieldPlayerShots);
        infoPanel.add(new JLabel("Disparos NPC:"));
        infoPanel.add(textFieldNPCShots);

        infoPanel.add(new JLabel("Posicion Jugador:"));
        infoPanel.add(textFieldInputPlayer);
        infoPanel.add(new JLabel("Posicion NPC:"));
        infoPanel.add(textFieldInputNPC);

        parentPanel.add(infoPanel, BorderLayout.NORTH);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameInfo(BlackBoard2.currentPlayer);
            }
        });
        timer.start();
    }

    /**
     * Updates the game information displayed on the UI.
     *
     * @param player The current player.
     */
    public void updateGameInfo(Player player) {
        if (player == null) {
            setNullPlayerInfo();
            return;
        }

        // Update player's information
        updatePlayerInfo(player);

        // Update NPC's information
        updateNPCInfo();
    }

    /**
     * Sets UI fields to "-" when the player is null.
     */
    private void setNullPlayerInfo() {
        textFieldPlayerShips.setText("-");
        textFieldNPCShips.setText("-");
        textFieldPlayerShots.setText("-");
        textFieldNPCShots.setText("-");
        textFieldInputPlayer.setText("-");
        textFieldInputNPC.setText("-");
    }

    /**
     * Updates UI fields with information related to the player.
     *
     * @param player The current player.
     */
    private void updatePlayerInfo(Player player) {
        int shipCountPlayer = player.getShips().size();
        List<Coordinates> playerShots = player.getDisparos();
        int totalPlayerShots = playerShots.size();

        // Update text fields for the player
        textFieldPlayerShips.setText(String.valueOf(shipCountPlayer));
        textFieldPlayerShots.setText(String.valueOf(totalPlayerShots));
        //formatPlayerCoordinate(player);
    }

    /**
     * Updates UI fields with information related to the NPC.
     */
    private void updateNPCInfo() {
        int shipCountNPC = BlackBoard2.opponentPlayer.getShips().size();
        List<Coordinates> npcShots = BlackBoard2.opponentPlayer.getDisparos();
        int totalNPCShots = npcShots.size();

        // Update text fields for the NPC
        textFieldNPCShips.setText(String.valueOf(shipCountNPC));
        textFieldNPCShots.setText(String.valueOf(totalNPCShots));
        textFieldInputNPC.setText(String.format("X: %d, Y: %d",
                BlackBoard2.opponentPlayer.getActualPostionX(),
                BlackBoard2.opponentPlayer.getActualPostionY()));
    }

    /**
     * Formats player's ship coordinates and updates the corresponding text field.
     *
     * @param player The current player.
     */
    private void formatPlayerCoordinate(Player player) {
        List<Ship> playerShips = player.getShips();
        StringBuilder playerCoordinatesText = new StringBuilder();

        for (Ship ship : playerShips) {
            List<ShipFragments> fragments = ship.getShipFragments();

            // Append X coordinates
            playerCoordinatesText.append("X: ");
            for (ShipFragments fragment : fragments) {
                playerCoordinatesText.append(String.format("%.1f, ", fragment.getX()));
                break; // Salir después de añadir la primera coordenada X
            }
            //playerCoordinatesText.delete(playerCoordinatesText.length() - 2, playerCoordinatesText.length()); // Remove last comma and space
            //playerCoordinatesText.append("\n");

            // Append Y coordinates
            playerCoordinatesText.append("Y: ");
            for (ShipFragments fragment : fragments) {
                playerCoordinatesText.append(String.format("%.1f, ", fragment.getY()));
                break; // Salir después de añadir la primera coordenada Y
            }
           // playerCoordinatesText.delete(playerCoordinatesText.length() - 2, playerCoordinatesText.length()); // Remove last comma and space
            //playerCoordinatesText.append("\n");
        }

        textFieldInputPlayer.setText(playerCoordinatesText.toString());
        playerCoordinatesText.setLength(0);
    }
}
