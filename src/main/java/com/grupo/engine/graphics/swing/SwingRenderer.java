package com.grupo.engine.graphics.swing;

import com.grupo.engine.core.Blackboard;
import com.grupo.engine.core.ResizeListener;
import com.grupo.engine.entities.Entity;
import com.grupo.engine.graphics.RenderAPI;
import com.grupo.game.core.BlackBoard2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The SwingRenderer class is an abstract renderer that extends JPanel and provides basic rendering functionality for Swing-based graphics.
 * It implements the RenderAPI interface and defines methods for drawing entities and background.
 */
public abstract class SwingRenderer extends JPanel implements RenderAPI {

    /**
     * Constructs a SwingRenderer with the specified width, height, and resize listener.
     *
     * @param width          The width of the rendering area.
     * @param height         The height of the rendering area.
     * @param resizeListener The listener for resize events.
     */
    public SwingRenderer(int width, int height, ResizeListener resizeListener) {
        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);
        setFocusable(true);


        addKeyListener(BlackBoard2.currentPlayer.getKeyboardManager());


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                resizeListener.onResize(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });
    }


    /**
     * Renders the scene.
     * This method is called to trigger the rendering process.
     */
    @Override
    public void render() {
        repaint();
    }

    /**
     * Overrides the paintComponent method to perform custom rendering.
     *
     * @param g The graphics context to render on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBackground(g2);
        Entity[] entities = Blackboard.entityManager.getEntities();
        for (int i = 0; i < Blackboard.entityManager.getNumEntities(); i++) {
            drawEntity(g2, entities[i]);
        }
    }

    /**
     * Draws the specified entity on the graphics context.
     *
     * @param g2 The graphics context.
     * @param e  The entity to draw.
     */
    public abstract void drawEntity(Graphics2D g2, Entity e);

    /**
     * Draws the background of the scene.
     *
     * @param g2 The graphics context.
     */
    public abstract void drawBackground(Graphics2D g2);
}
