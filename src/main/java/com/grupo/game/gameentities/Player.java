package com.grupo.game.gameentities;

import com.grupo.engine.entities.Entity;
import com.grupo.engine.input.KeyboardManager;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    private List<Ship> ships;
    private int[][] disparosRealizados;
    private boolean turno;
    private List<String> stack;

    public Player(float x, float y, float width, float height, float hp, float damage) {
        super(x, y, width, height, hp, damage);
        this.ships = ships;
        this.disparosRealizados = disparosRealizados;
        this.stack = stack;
        this.turno = turno;
    }

    public void procesarInput(String input) {
        if (turno) {
            // Procesar la entrada del teclado y agregarla al stack
            KeyboardManager km = ;

            if (km.isUp())
                stack.add("W");
            if (km.isDown())
                stack.add("S");
            if (km.isLeft())
                stack.add("A");
            if (km.isRight())
                stack.add("D");
            if (km.isFire())
                stack.add("X");

            if (stack.size() > 2) {
                stack.clear(); // Reiniciar el stack si tiene más de 2 elementos
            }
        }
    }


    @Override
    public void update(double deltaTime) {
        if (turno && stack.size() == 2) {
            disparar();
            stack.clear(); // Limpiar el stack después de disparar
        }
    }


    private void disparar() {
        // Convertir la combinación de entrada en coordenadas
        String xString = stack.get(0);
        String yString = stack.get(1);
        int x = Character.getNumericValue(Integer.parseInt(xString));
        int y = Character.getNumericValue(Integer.parseInt(yString));

        // Marcar la matriz de disparos realizados
        if (isHitBoard(x, y)) {
            disparosRealizados[x][y] = 2; // Acertado
        } else {
            disparosRealizados[x][y] = 1; // Agua
        }
    }

    public boolean isHitBoard(float x, float y) {
        for (Ship ship : ships) {
            if (ship.isHitPosition(x, y)) {
                return true;
            }
        }
        return false;
    }

    public int barcosHundidos() {
        int count = 0;
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                count++;
            }
        }
        return count;
    }

    public boolean añadirBarco(Ship ship) {
        return ships.add(ship);
    }

    @Override
    public void lastUpdate(double deltaTime) {
        // Implementación pendiente si es necesario
    }

    @Override
    public void postUpdate(double deltaTime) {
        // Implementación pendiente si es necesario
    }
}
