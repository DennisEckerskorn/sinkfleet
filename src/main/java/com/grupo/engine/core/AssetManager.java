package com.grupo.engine.core;

import com.grupo.engine.audio.Sound;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The AssetManager class manages loading and accessing game assets such as images and sounds.
 */
public abstract class AssetManager {
    private final Map<String, BufferedImage> sprites;
    private final Map<String, Sound> sounds;

    /**
     * Constructs a new AssetManager and initializes the sprite and sound maps.
     */
    public AssetManager() {
        sprites = new HashMap<>();
        sounds = new HashMap<>();
        loadAll();
    }

    /**
     * Loads a sprite into the asset manager using the specified name and file path.
     *
     * @param name The name to associate with the loaded sprite.
     * @param path The file path to the sprite image.
     */
    public void loadSprite(String name, String path) {
        try {
            BufferedImage bufferedImage = ImageIO.read(getClass().getResourceAsStream(path));
            sprites.put(name, bufferedImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a sound into the asset manager using the specified name and file path.
     *
     * @param name The name to associate with the loaded sound.
     * @param path The file path to the sound file.
     */
    public void loadSound(String name, String path) {
        String fullPath = getClass().getResource(path).getPath();
        Sound sound = new Sound(fullPath);
        sounds.put(name, sound);
    }

    /**
     * Retrieves a sprite from the asset manager based on its name.
     *
     * @param name The name of the sprite to retrieve.
     * @return The BufferedImage representing the sprite.
     */
    public BufferedImage getSprite(String name) {
        return sprites.get(name);
    }

    /**
     * Retrieves a sound from the asset manager based on its name.
     *
     * @param name The name of the sound to retrieve.
     * @return The Sound object representing the sound.
     */
    public Sound getSound(String name) {
        return sounds.get(name);
    }

    /**
     * Abstract method to be implemented by subclasses to load all assets.
     */
    public abstract void loadAll();
}
