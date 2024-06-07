package com.grupo.game.gameentities;

import java.util.Random;

public class NPCPlayer extends Player {
    private Random random;

    public NPCPlayer(float x, float y, float width, float height, int hp, float damage, int rows, int cols) {
        super(x, y, width, height, hp, damage, null, rows, cols);
        this.random = new Random();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void update(double deltaTime) {
        setActualPostionX(random.nextInt(getRows()));
        setActualPostionY(random.nextInt(getCols()));
        setHorizontal(random.nextBoolean());
    }

    @Override
    public void lastUpdate(double deltaTime) {
    }

    @Override
    public void postUpdate(double deltaTime) {
    }

    @Override
    public void processInput() {
    }


}
