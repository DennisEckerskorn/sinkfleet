package com.grupo.engine.math;

/**
 * A utility class providing common mathematical functions.
 */
public class MathUtil {

    /**
     * Function to clamp a value within a range of integers.
     *
     * @param value the value to clamp
     * @param min   the minimum value of the range
     * @param max   the maximum value of the range
     * @return the clamped value within the range [min, max]
     */
    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }


    /**
     * Function to clamp a value within a range of integers.
     * If the value is less than the minimum, it returns the minimum.
     *
     * @param value the value to clamp
     * @param min   the minimum value of the range
     * @return the clamped value within the range [min, +∞)
     */
    public static int clamp(int value, int min) {
        if (value < min) {
            return min;
        }
        return value;
    }

    /**
     * Function to clamp a value within a range of floating-point numbers.
     *
     * @param value the value to clamp
     * @param min   the minimum value of the range
     * @param max   the maximum value of the range
     * @return the clamped value within the range [min, max]
     */
    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * Function to clamp a value within a range of floating-point numbers.
     * If the value is less than the minimum, it returns the minimum.
     *
     * @param value the value to clamp
     * @param min   the minimum value of the range
     * @return the clamped value within the range [min, +∞)
     */
    public static float clamp(float value, float min) {
        if (value < min) {
            return min;
        }
        return value;
    }

    /**
     * Function to clamp a value within a range of double-precision floating-point numbers.
     *
     * @param value the value to clamp
     * @param min   the minimum value of the range
     * @param max   the maximum value of the range
     * @return the clamped value within the range [min, max]
     */
    public static double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * Function to clamp a value within a range of double-precision floating-point numbers.
     * If the value is less than the minimum, it returns the minimum.
     *
     * @param value the value to clamp
     * @param min   the minimum value of the range
     * @return the clamped value within the range [min, +∞)
     */
    public static double clamp(double value, double min) {
        if (value < min) {
            return min;
        }
        return value;
    }

}
