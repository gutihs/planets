package com.example.planets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("dia")
    private Integer day;

    @JsonProperty("clima")
    private String weather;

    @JsonProperty("periodosLluvia")
    private Integer rainPeriods;

    @JsonProperty("periodosSequia")
    private Integer droughtPeriods;

    @JsonProperty("periodosOptimo")
    private Integer optimumPeriods;

    @JsonProperty("diaPicoMaximoLluvia")
    private Integer maximumDayOfRain;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Integer getRainPeriods() {
        return rainPeriods;
    }

    public void setRainPeriods(Integer rainPeriods) {
        this.rainPeriods = rainPeriods;
    }

    public Integer getDroughtPeriods() {
        return droughtPeriods;
    }

    public void setDroughtPeriods(Integer droughtPeriods) {
        this.droughtPeriods = droughtPeriods;
    }

    public Integer getOptimumPeriods() {
        return optimumPeriods;
    }

    public void setOptimumPeriods(Integer optimumPeriods) {
        this.optimumPeriods = optimumPeriods;
    }

    public Integer getMaximumDayOfRain() {
        return maximumDayOfRain;
    }

    public void setMaximumDayOfRain(Integer maximumDayOfRain) {
        this.maximumDayOfRain = maximumDayOfRain;
    }
}
