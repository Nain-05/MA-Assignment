package com.lab.jweather.JModel;

public class JSys {
    private int id;
    private int type;
    private double message;
    private double sunrise;
    private double sunset;
    private String country;

    public JSys(int id, int type, double message, double sunrise, double sunset, String country) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
