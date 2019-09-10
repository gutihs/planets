package com.example.planets.job.impl;

import com.example.planets.constant.PlanetsData;
import com.example.planets.job.Job;
import com.example.planets.model.PredictionData;
import com.example.planets.model.PredictionRepository;
import com.example.planets.service.PlanetCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class PredictionJob implements Job {

    Logger 	log = Logger.getLogger(this.getClass().getName());

    @Autowired
    private PlanetCalculationService planetCalculationService;

    @Autowired
    private PredictionRepository predictionRepository;

    @Override
    public void execute() {
        log.info("PredictionJob iniciando...");
        List<PredictionData> predictionsList = new ArrayList<>();


        for (int day = 1; day <= PlanetsData.MAX_DAYS; day++) {
            PredictionData prediction = new PredictionData();

            log.info("*** dia: " + day + "***");
            double w1t = PlanetsData.W1 * day;
            log.info("w1t --> " + w1t);
            double w2t = PlanetsData.W2 * day;
            log.info("w2t --> " + w2t);
            double w3t = PlanetsData.W3 * day;
            log.info("w3t --> " + w3t);

            prediction.setDay(day);
            prediction.setFirstPlanetDegrees(w1t);
            prediction.setSecondPlanetDegrees(w2t);
            prediction.setThirdPlanetDegrees(w3t);


            if (planetCalculationService.areAligned(w1t, w2t, w3t, PlanetsData.R1, PlanetsData.R2, PlanetsData.R3)) {

                if (planetCalculationService.areAlignedWithSun(w1t, w2t, w3t)) {
                    log.info("Estan alineados con el Sol -> clima: sequia");
                    prediction.setDrought(true);
                } else {
                    log.info("Estan alineados -> clima: optimo");
                    prediction.setOptimum(true);
                }

            } else {
                log.info("Forman un triangulo");

                if (planetCalculationService.isSunInside(w1t, w2t, w3t, PlanetsData.R1, PlanetsData.R2, PlanetsData.R3)) {
                    log.info("El Sol esta dentro del triangulo -> clima: lluvia");
                    prediction.setRain(true);

                    double trianglePerimeter = planetCalculationService.calculateTrianglePerimeter(w1t, w2t, w3t, PlanetsData.R1, PlanetsData.R2, PlanetsData.R3);
                    log.info("Perimetro total: " + trianglePerimeter);
                    prediction.setPerimeter(trianglePerimeter);
                }
            }
            predictionsList.add(prediction);
        }

        predictionRepository.saveAll(predictionsList);

    }

}
