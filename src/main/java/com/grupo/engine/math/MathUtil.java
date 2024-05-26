package com.denniseckerskorn.engine.math;

/**
 * Una clase de utilidad que proporciona funciones matemáticas comunes.
 */
public class MathUtil {

    /**
     * Función para limitar un valor dentro de un intervalo de números enteros.
     *
     * @param value el valor a limitar
     * @param min el valor mínimo del intervalo
     * @param max el valor máximo del intervalo
     * @return el valor limitado dentro del intervalo [min, max]
     */
    public static int clamp(int value, int min, int max) {
        if(value < min) {
            return min;
        }
        if(value > max) {
            return max;
        }
        return value;
    }

    /**
     * Función para limitar un valor dentro de un intervalo de números enteros.
     * Si el valor es menor que el mínimo, devuelve el mínimo.
     *
     * @param value el valor a limitar
     * @param min el valor mínimo del intervalo
     * @return el valor limitado dentro del intervalo [min, +∞)
     */
    public static int clamp(int value, int min) {
        if(value < min) {
            return min;
        }
        return value;
    }

    /**
     * Función para limitar un valor dentro de un intervalo de números de punto flotante.
     *
     * @param value el valor a limitar
     * @param min el valor mínimo del intervalo
     * @param max el valor máximo del intervalo
     * @return el valor limitado dentro del intervalo [min, max]
     */
    public static float clamp(float value, float min, float max) {
        if(value < min) {
            return min;
        }
        if(value > max) {
            return max;
        }
        return value;
    }

    /**
     * Función para limitar un valor dentro de un intervalo de números de punto flotante.
     * Si el valor es menor que el mínimo, devuelve el mínimo.
     *
     * @param value el valor a limitar
     * @param min el valor mínimo del intervalo
     * @return el valor limitado dentro del intervalo [min, +∞)
     */
    public static float clamp(float value, float min) {
        if(value < min) {
            return min;
        }
        return value;
    }

    /**
     * Función para limitar un valor dentro de un intervalo en coma flotante de doble precisión.
     *
     * @param value el valor a limitar
     * @param min el valor mínimo del intervalo
     * @param max el valor máximo del intervalo
     * @return el valor limitado dentro del intervalo [min, max]
     */
    public static double clamp(double value, double min, double max) {
        if(value < min) {
            return min;
        }
        if(value > max) {
            return max;
        }
        return value;
    }

    /**
     * Función para limitar un valor dentro de un intervalo de números en coma flotante de doble precisión.
     * Si el valor es menor que el mínimo, devuelve el mínimo.
     *
     * @param value el valor a limitar
     * @param min el valor mínimo del intervalo
     * @return el valor limitado dentro del intervalo [min, +∞)
     */
    public static double clamp(double value, double min) {
        if(value < min) {
            return min;
        }
        return value;
    }

}
