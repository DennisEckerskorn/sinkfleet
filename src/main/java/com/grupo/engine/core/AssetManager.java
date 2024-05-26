package com.denniseckerskorn.engine.core;

import com.denniseckerskorn.engine.audio.Sound;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public abstract class AssetManager {
    private final Map<String, BufferedImage> sprites;
    private final Map<String, Sound> sounds;


    public AssetManager() {
        sprites = new HashMap<>();
        sounds = new HashMap<>();
        loadAll();
    }

    public void loadSprite(String name, String path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream(path));
            sprites.put(name, bufferedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSound(String name, String path) {
        String fullPath = getClass().getResource(path).getPath();
        Sound sound = new Sound(fullPath);
        sounds.put(name, sound);
    }

    public BufferedImage getSprite(String name) {
        return sprites.get(name);
    }

    public Sound getSound(String name) {
        return sounds.get(name);
    }

    public abstract void loadAll();
}
