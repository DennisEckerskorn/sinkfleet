package com.grupo.engine.core;

import com.grupo.engine.graphics.RenderAPI;

/**
 * The Game class represents the core of a game application.
 * It manages the game loop, updates, rendering, and input processing.
 */
public abstract class Game implements Runnable, Updateable, ResizeListener {
    private int width;
    private int height;
    private final float fpsLimit;
    private final float upsLimit;
    private Thread thread;
    private boolean finished;
    private RenderAPI renderAPI;

    /**
     * Constructs a Game object with the specified parameters.
     *
     * @param width       The width of the game window.
     * @param height      The height of the game window.
     * @param fpsLimit    The maximum frames per second the game should render.
     * @param upsLimit    The maximum updates per second the game should perform.
     * @param maxEntities The maximum number of entities in the game.
     */
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

    /**
     * Starts the game loop.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Runs the game loop.
     * <p>
     * This method is responsible for running the main game loop, which consists of three major phases:
     * input processing, rendering, and updating. It calculates the time elapsed between frames and updates,
     * ensuring a smooth and consistent game experience. The loop continues until the game is finished.
     */
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
                deltaTime = (double) (currentUpdate - lastUpdate) / NANOS_IN_SECOND;

                update(deltaTime);
                postUpdate(deltaTime);
                lastUpdate(deltaTime);
                lastUpdate = currentUpdate;
            }
        }
    }

    /**
     * Renders the game using the associated rendering API.
     */
    private void render() {
        renderAPI.render();
    }

    /**
     * Updates the game state.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        Blackboard.entityManager.update(deltaTime);
    }

    /**
     * Performs the last update phase of the game.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void lastUpdate(double deltaTime) {
        Blackboard.entityManager.lastUpdate(deltaTime);
    }

    /**
     * Performs the post-update phase of the game.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void postUpdate(double deltaTime) {
        Blackboard.entityManager.postUpdate(deltaTime);
    }

    /**
     * Processes input events for the game.
     */
    private void processInput() {
        Blackboard.entityManager.processInput();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Handles resizing of the game window.
     *
     * @param width  The new width of the game window.
     * @param height The new height of the game window.
     */
    @Override
    public void onResize(int width, int height) {
        this.width = width;
        this.height = height;
        gameResized();
    }

    public abstract void gameResized();
}