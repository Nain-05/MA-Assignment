package com.lab.jweather.JModel;

import java.util.List;

public class JOpenWeatherMap {
    private JCoord coord;
    private List<JWeather> weather;
    private String base;
    private JMain main;
    private JSys sys;
    private JWind wind;
    private JCloud cloud;
    private int id;
    private String name;
    private int cod;
    private int dt;
    private int visibility;
    private int timezone;

    public JOpenWeatherMap() {
    }

    public JOpenWeatherMap(JCoord coord, List<JWeather> weather, String base, JMain main, JSys sys, JWind wind, JCloud cloud, int id, String name, int cod, int dt, int visibility, int timezone) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.sys = sys;
        this.wind = wind;
        this.cloud = cloud;
        this.id = id;
        this.name = name;
        this.cod = cod;
        this.dt = dt;
        this.visibility = visibility;
        this.timezone = timezone;
    }

    public JCoord getCoord() {
        return coord;
    }

    public void setCoord(JCoord coord) {
        this.coord = coord;
    }

    public List<JWeather> getWeather() {
        return weather;
    }

    public void setWeather(List<JWeather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public JMain getMain() {
        return main;
    }

    public void setMain(JMain main) {
        this.main = main;
    }

    public JSys getSys() {
        return sys;
    }

    public void setSys(JSys sys) {
        this.sys = sys;
    }

    public JWind getWind() {
        return wind;
    }

    public void setWind(JWind wind) {
        this.wind = wind;
    }

    public JCloud getCloud() {
        return cloud;
    }

    public void setCloud(JCloud cloud) {
        this.cloud = cloud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }
}