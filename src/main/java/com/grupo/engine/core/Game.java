package com.grupo.engine.core;

import com.grupo.engine.graphics.RenderAPI;

public abstract class Game implements Runnable, Updateable, ResizeListener {
    private int width;
    private int height;
    private final float fpsLimit;
    private final float upsLimit;
    private Thread thread;
    private boolean finished;
    private RenderAPI renderAPI;


    public Game(int width, int height, float fpsLimit, float upsLimit, int maxEntities) {
        this.width = width;
        this.height = height;
        this.fpsLimit = fpsLimit;
        this.upsLimit = upsLimit;
        this.finished = false;
        Blackboard.entityManager = createEntityManager(maxEntities);
    }

    public void setRenderAPI(RenderAPI renderAPI) {
        this.renderAPI = renderAPI;
    }

    public abstract EntityManager createEntityManager(int maxEntities);

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        final long NANOS_IN_SECOND = 1_000_000_000;
        final double NANOS_BETWEEN_UPDATES = 1_000_000_000 / upsLimit;
        final double NANOS_BETWEEN_FRAMES = 1_000_000_000 / fpsLimit;
        long currentFrame;
        long lastFrame = currentFrame = System.nanoTime();
        long currentUpdate;
        long lastUpdate = currentUpdate = System.nanoTime();
        double deltaTime;

        System.out.println("Iniciando hilo ...");
        while (!finished) {
            currentFrame = System.nanoTime();
            currentUpdate = System.nanoTime();

            processInput();
            // Frames per second
            if (currentFrame - lastFrame > NANOS_BETWEEN_FRAMES) {
                render();
                lastFrame = currentFrame;
            }
            // Updates per second
            if (currentUpdate - lastUpdate > NANOS_BETWEEN_UPDATES) {
                deltaTime = (double)(currentUpdate - lastUpdate) / NANOS_IN_SECOND;
                
                update(deltaTime);
                postUpdate(deltaTime);
                lastUpdate(deltaTime);
                lastUpdate = currentUpdate;
            }
        }
    }

    private void render() {
        renderAPI.render();
    }

    @Override
    public void update(double deltaTime) {
        Blackboard.entityManager.update(deltaTime);
    }

    @Override
    public void lastUpdate(double deltaTime) {
        Blackboard.entityManager.lastUpdate(deltaTime);
    }

    @Override
    public void postUpdate(double deltaTime) {
        Blackboard.entityManager.postUpdate(deltaTime);
    }

    private void processInput() {
        Blackboard.entityManager.processInput();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void onResize(int width, int height) {
        this.width = width;
        this.height = height;
        gameResized();
    }

    public abstract void gameResized();
}