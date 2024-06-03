package com.grupo;


import com.grupo.game.config.Settings;
import com.grupo.game.core.SinkFleetGame;
import com.grupo.game.graphics.SinkFleetSwingRenderer;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SinkFleetGame sinkFleetGame = new SinkFleetGame(Settings.WIDTH, Settings.HEIGHT, Settings.ROWS, Settings.COLS, Settings.TARGET_FPS, Settings.TARGET_UPS, Settings.MAX_ENTITIES);
        SinkFleetSwingRenderer sinkFleetSwingRenderer = new SinkFleetSwingRenderer(Settings.WIDTH * 2 + 20, Settings.HEIGHT, sinkFleetGame, null);
        sinkFleetGame.setRenderAPI(sinkFleetSwingRenderer);
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Sink the Fleet");
        frame.add(sinkFleetSwingRenderer);
        frame.pack();
        frame.setVisible(true);
        sinkFleetGame.start();
    }
}