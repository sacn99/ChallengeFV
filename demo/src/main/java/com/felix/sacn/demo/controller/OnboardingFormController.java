package com.felix.sacn.demo.controller;

import com.felix.sacn.demo.entity.OnboardingForm;
import com.felix.sacn.demo.entity.Recommendation;
import com.felix.sacn.demo.service.OnboardingFormService;
import com.felix.sacn.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingFormController {

    @Autowired
    private OnboardingFormService onboardingFormService;

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<List<Recommendation>> createOnboardingForm(@RequestBody OnboardingForm onboardingForm) {
        OnboardingForm savedForm = onboardingFormService.saveOnboardingForm(onboardingForm);

        // Obtener las recomendaciones directamente desde el repositorio después de asignarlas
        List<Recommendation> recommendations = recommendationService.getRecommendationsForUser(savedForm.getUser().getId());

        return new ResponseEntity<>(recommendations, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OnboardingForm>> getAllOnboardingForms() {
        List<OnboardingForm> onboardingForms = onboardingFormService.getAllOnboardingForms();
        return new ResponseEntity<>(onboardingForms, HttpStatus.OK);
    }
}
