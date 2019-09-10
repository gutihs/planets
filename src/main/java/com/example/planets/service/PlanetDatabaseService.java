package com.example.planets.service;

import com.example.planets.model.PredictionData;
import com.example.planets.model.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanetDatabaseService {

    @Autowired
    private PredictionRepository predictionRepository;

    public PredictionData getPredictionByDay(int day) {
        List<PredictionData> queryResults = predictionRepository.findByDay(day);

        return queryResults.get(0);
    }

    public int getRainDaysCount() {
        return predictionRepository.findByIsRain(true).size();
    }

    public int getDroughtDaysCount() {
        return predictionRepository.findByIsDrought(true).size();
    }

    public int getOptimumDaysCount() {
        return predictionRepository.findByIsOptimum(true).size();
    }

    public int getMaximumPerimeterDay() {
        List<PredictionData> predictionData = predictionRepository.findByIsRainOrderByPerimeterDesc(true);

        return predictionData.get(0).getDay();
    }

}
