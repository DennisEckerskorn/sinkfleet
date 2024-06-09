package com.grupo.game.scenes;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.entities.PlayableEntity;
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

public class SinglePlayerScene extends Scene {
    private Color backgroundColor1;
    private Color backgroundColor2;
    private Player currentPlayer;
    private SceneManager sceneManager;
    private JButton button;
    private JTextField textField1;
    private JTextField textField2;

    public SinglePlayerScene(Color backgroundColor1, Color backgroundColor2, SceneManager sceneManager) {
        this.backgroundColor1 = backgroundColor1;
        this.backgroundColor2 = backgroundColor2;
        this.sceneManager = sceneManager;
        button = new JButton("Button");
        textField1 = new JTextField();
        textField2 = new JTextField();
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public void render(Graphics2D g2) {
        //drawBackground(g2);
        drawEntity(g2, BlackBoard2.currentPlayer);
    }

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

            //TODO: Implementar disparos, es correcto, updated???
            disparos = ((Player) e).getDisparos();
            g2.setColor(Color.RED);
            for (int i = 0; i < disparos.size(); i++) {
            
                g2.setColor(Color.ORANGE);
                
                int x = disparos.get(i).getX() * Blackboard.cellSize + Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS + Settings.GAMEBOARD_OFFSET;
                int y = disparos.get(i).getY() * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET;
                g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            }
            //for (Coordinates disparo : disparos) {
            //    int x = disparo.getX() * Blackboard.cellSize + Settings.COLS * Blackboard.cellSize + Settings.SPACE_BETWEEN_GAMEBOARDS + Settings.GAMEBOARD_OFFSET;
            //    int y = disparo.getY() * Blackboard.cellSize + Settings.GAMEBOARD_OFFSET;
            //    g2.fillRect(x, y, Blackboard.cellSize, Blackboard.cellSize);
            //}
        }
    }

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
            g2.drawString(Integer.toString(row + 1), offset - 20, (row + 1) * Blackboard.cellSize + 4);
        }

        //Dibujar numero de columnas:
        for (int col = 0; col < Settings.COLS; col++) {
            g2.drawString(Integer.toString(col + 1), offset + col * Blackboard.cellSize + Blackboard.cellSize / 2, 20);
        }
    }

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

        // Create and add the button
        button = new JButton("Button");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Implementar click en boton
            }
        });
        parentPanel.add(button, BorderLayout.SOUTH);

        // Create the text fields
        textField1 = new JTextField();
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Implementar recibir coordenadas barcos???
            }
        });
        textField2 = new JTextField();
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Implementar recibir coordenadas disparos???

            }
        });

        // Create a panel for the text fields
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(1, 2)); // Set GridLayout to arrange components horizontally

        // Add the text fields to the panel
        textFieldsPanel.add(textField1);
        textFieldsPanel.add(textField2);

        // Add the panel containing the text fields to the parent panel
        parentPanel.add(textFieldsPanel, BorderLayout.NORTH);

    }


}
