package com.grupo.game.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que se encarga de crear un panel de espacio entre los dos otros paneles, es el panel del centro.
 */
public class SpacePanel extends JPanel {

    public SpacePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

}
