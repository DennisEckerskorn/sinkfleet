package com.denniseckerskorn.engine.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound implements LineListener {
    private final Clip clip;
    private final AudioInputStream audioInputStream;
    private volatile boolean done;
    private Thread thread;

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

    public void stop() {
        if (thread != null) {
            clip.stop();
            thread.interrupt();
        }
    }

    public void dispose() {
        try {
            audioInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.close();
        clip.stop();
    }

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
