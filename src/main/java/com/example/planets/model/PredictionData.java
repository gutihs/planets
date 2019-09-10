package com.example.planets.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "predictions")
public class PredictionData {

    @Id
    private String id;

    private int day;

    private double firstPlanetDegrees;

    private double secondPlanetDegrees;

    private double thirdPlanetDegrees;

    private double perimeter;

    private boolean isRain;

    private boolean isDrought;

    private boolean isOptimum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getFirstPlanetDegrees() {
        return firstPlanetDegrees;
    }

    public void setFirstPlanetDegrees(double firstPlanetDegrees) {
        this.firstPlanetDegrees = firstPlanetDegrees;
    }

    public double getSecondPlanetDegrees() {
        return secondPlanetDegrees;
    }

    public void setSecondPlanetDegrees(double secondPlanetDegrees) {
        this.secondPlanetDegrees = secondPlanetDegrees;
    }

    public double getThirdPlanetDegrees() {
        return thirdPlanetDegrees;
    }

    public void setThirdPlanetDegrees(double thirdPlanetDegrees) {
        this.thirdPlanetDegrees = thirdPlanetDegrees;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public boolean isRain() {
        return isRain;
    }

    public void setRain(boolean rain) {
        isRain = rain;
    }

    public boolean isDrought() {
        return isDrought;
    }

    public void setDrought(boolean drought) {
        isDrought = drought;
    }

    public boolean isOptimum() {
        return isOptimum;
    }

    public void setOptimum(boolean optimum) {
        isOptimum = optimum;
    }
}
