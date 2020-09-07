package com.lab.jweather.JModel;

public class JWind {
    private double deg;
    private double speed;

    public JWind(double deg, double speed) {
        this.deg = deg;
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
