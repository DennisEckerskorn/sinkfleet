package com.grupo.engine.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The Sound class represents a sound file that can be played in the game.
 * It provides methods to play, loop, stop, and dispose of the sound.
 */
public class Sound implements LineListener {
    private final Clip clip;
    private final AudioInputStream audioInputStream;
    private volatile boolean done;
    private Thread thread;

    /**
     * Constructs a Sound object from the specified file path.
     *
     * @param path The path to the sound file.
     */
    public Sound(String path) {
        try {
            File file = new File(path);
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.addLineListener(this);
            clip.open(audioInputStream);
            done = false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts playing the sound.
     */
    public void play() {
        done = false;
        Runnable r = new Runnable() {
            public void run() {
                while (!done) {
                    clip.setFramePosition(0);
                    clip.start();
                    // clip.drain();
                }
            }
        };
        thread = new Thread(r);
        thread.start();
    }

    public void setPosition(long microseconds) {
        clip.setMicrosecondPosition(microseconds);
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void loop(int count) {
        clip.loop(count);
    }

    /**
     * Stops playing the sound.
     */
    public void stop() {
        if (thread != null) {
            clip.stop();
            thread.interrupt();
        }
    }

    /**
     * Disposes of the sound resources.
     */
    public void dispose() {
        try {
            audioInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.close();
        clip.stop();
    }

    /**
     * The update method updates the state of the Sound object based on the LineEvent received.
     *
     * @param event The LineEvent representing the change in the sound's playback state.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type eventType = event.getType();
        if (eventType == LineEvent.Type.OPEN) {
            System.out.println("Open");
        } else if (eventType == LineEvent.Type.START) {
            System.out.println("Play start");
        } else if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
            done = true;
            System.out.println("Stop");
        }
    }
}
