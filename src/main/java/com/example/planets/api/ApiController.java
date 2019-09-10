package com.example.planets.api;

import com.example.planets.dto.ApiResponse;
import com.example.planets.job.impl.PredictionJob;
import com.example.planets.model.PredictionData;
import com.example.planets.service.PlanetDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private PredictionJob predictionJob;

    @Autowired
    private PlanetDatabaseService planetDatabaseService;

    @RequestMapping("/status")
    public ApiResponse checkStatus() {
        ApiResponse response = new ApiResponse();
        response.setStatus("Funcionando");

        return response;
    }

    @RequestMapping("/startPredictionJob")
    public ApiResponse startPredictionJob() {
        ApiResponse response = new ApiResponse();

        predictionJob.execute();
        response.setStatus("Se ha iniciado el job de prediccion");

        return response;
    }

    @RequestMapping("/clima")
    public ApiResponse getPrediction(@RequestParam(name = "dia") int day) {
        ApiResponse response = new ApiResponse();

        PredictionData prediction = planetDatabaseService.getPredictionByDay(day);

        response.setDay(prediction.getDay());

        if (prediction.isRain()) {
            response.setWeather("lluvia");
        } else if (prediction.isDrought()) {
            response.setWeather("sequia");
        } else if (prediction.isOptimum()) {
            response.setWeather("optimo");
        } else {
            response.setWeather("normal");
        }

        return response;
    }

    @RequestMapping("/periodsInfo")
    public ApiResponse getPeriodsInfo() {
        ApiResponse response = new ApiResponse();

        int rainDayscount = planetDatabaseService.getRainDaysCount();
        int droughtDayscount = planetDatabaseService.getDroughtDaysCount();
        int optimumDayscount = planetDatabaseService.getOptimumDaysCount();
        int maximumPerimeterDay = planetDatabaseService.getMaximumPerimeterDay();

        response.setRainPeriods(rainDayscount);
        response.setDroughtPeriods(droughtDayscount);
        response.setOptimumPeriods(optimumDayscount);
        response.setMaximumDayOfRain(maximumPerimeterDay);

        return response;
    }


}
