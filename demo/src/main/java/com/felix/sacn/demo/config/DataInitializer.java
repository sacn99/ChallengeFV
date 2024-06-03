package com.felix.sacn.demo.config;

import com.felix.sacn.demo.entity.Recommendation;
import com.felix.sacn.demo.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Recommendation> recommendations = Arrays.asList(
            new Recommendation("Lose weight", "Fatigue", "Consider a low-carb diet."),
            new Recommendation("Build muscle", "Muscle pain", "Incorporate strength training."),
            new Recommendation("Improve endurance", "Headache", "Stay hydrated and rest well.")
            // Añadir más recomendaciones según sea necesario
        );
        
        recommendationRepository.saveAll(recommendations);
    }
}
