package com.example.kun.heatmapdemo;


public class Position {

    private static final double DEFAULT_INTENSITY = 1.0;

    public final int x;

    public final int y;

    public final double intensity;

    public Position(int x, int y, double intensity) {
        this.x = x;
        this.y = y;
        if (intensity < 0) {
            throw new IllegalStateException("Intensity must be over zero!");
        }
        this.intensity = intensity;
    }
}
