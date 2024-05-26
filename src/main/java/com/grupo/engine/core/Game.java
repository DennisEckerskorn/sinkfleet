package com.denniseckerskorn.engine.core;

import com.denniseckerskorn.engine.graphics.RenderAPI;

public abstract class Game implements Runnable, Updateable, ResizeListener {
    private int width;
    private int height;
    private float fpsLimit;
    private float upsLimit;
    private Thread thread;
    private boolean finished;
    private RenderAPI renderAPI;

    public Game(int width, int height, float fpsLimit, float updateLimit, int maxEntities) {
        this.width = width;
        this.height = height;
        this.fpsLimit = fpsLimit;
        this.upsLimit = updateLimit;
        this.finished = false;
        Blackboard.entityManager = createEntityManager(maxEntities);
    }

    public void setRenderAPI(RenderAPI renderAPI) {
        this.renderAPI = renderAPI;
    }

    public void setFpsLimit(float fpsLimit) {
        this.fpsLimit = fpsLimit;
    }

    public float getFpsLimit() {
        return fpsLimit;
    }

    public void setUpsLimit(float upsLimit) {
        this.upsLimit = upsLimit;
    }

    public float getUpsLimit() {
        return upsLimit;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract EntityManager createEntityManager(int maxEntities);

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    //TODO: update to version of German
    @Override
    public void run() {
        final long NANOS_IN_SECONDS = 1_000_000_000;
        long currentFrame;
        long lastFrame = System.nanoTime();
        long lastUpdateFrame = lastFrame;
        double deltaTime;

        System.out.println("Iniciando hilo ...");
        while (!finished) {
            currentFrame = System.nanoTime();
            deltaTime = (double) (currentFrame - lastFrame) / NANOS_IN_SECONDS;
            processInput();

            if (upsLimit > 0) {
                double nanosBetweenUpdates = NANOS_IN_SECONDS / upsLimit;
                if (currentFrame - lastUpdateFrame >= nanosBetweenUpdates) {
                    updateGame(deltaTime);
                    lastUpdateFrame = currentFrame;
                }
            } else {
                updateGame(deltaTime);
            }

            if (fpsLimit > 0) {
                double nanosBetweenFrames = NANOS_IN_SECONDS / fpsLimit;
                if (currentFrame - lastFrame > nanosBetweenFrames) {
                    render();
                    lastFrame = currentFrame;
                }
            } else {
                render();
                lastFrame = currentFrame;
            }
        }
    }

    private void updateGame(double deltaTime) {
        update(deltaTime);
        postUpdate(deltaTime);
        lastUpdate(deltaTime);
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

    @Override
    public void onResize(int width, int height) {
        this.width = width;
        this.height = height;
        gameResized();
    }

    public abstract void gameResized();
}
