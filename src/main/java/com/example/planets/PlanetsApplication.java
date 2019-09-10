package com.example.planets;

import com.example.planets.job.impl.PredictionJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlanetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanetsApplication.class, args);
    }

    /*
    @Bean(initMethod = "execute")
    public PredictionJob initiatePredictionJob() {
        return new PredictionJob();
    }*/
}
