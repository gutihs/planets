package com.example.planets.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PredictionRepository extends MongoRepository<PredictionData, String> {

    List<PredictionData> findByDay(int day);

    List<PredictionData> findByIsRain(boolean isRain);

    List<PredictionData> findByIsDrought(boolean isDrought);

    List<PredictionData> findByIsOptimum(boolean isOptimum);

    List<PredictionData> findByIsRainOrderByPerimeterDesc(boolean isRain);

}
