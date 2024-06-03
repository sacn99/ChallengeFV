package com.felix.sacn.demo.service;

import com.felix.sacn.demo.entity.OnboardingForm;
import com.felix.sacn.demo.entity.Recommendation;
import com.felix.sacn.demo.repository.OnboardingFormRepository;
import com.felix.sacn.demo.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OnboardingFormService {

    @Autowired
    private OnboardingFormRepository onboardingFormRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    public OnboardingForm saveOnboardingForm(OnboardingForm onboardingForm) {
        OnboardingForm savedForm = onboardingFormRepository.save(onboardingForm);
        assignRecommendations(savedForm);
        return savedForm;
    }

    private void assignRecommendations(OnboardingForm savedForm) {
        String[] symptoms = savedForm.getCurrentSymptoms().split(", ");
        String[] healthGoals = savedForm.getHealthGoals().split(", ");
        
        List<Recommendation> matchingRecommendations = Stream.concat(
            recommendationRepository.findBySymptomIn(symptoms).stream(),
            recommendationRepository.findByHealthGoalIn(healthGoals).stream()
        ).distinct().collect(Collectors.toList());

        for (Recommendation recommendation : matchingRecommendations) {
            recommendation.setUser(savedForm.getUser());
            recommendationRepository.save(recommendation);
        }
    }

    public List<OnboardingForm> getAllOnboardingForms() {
        return onboardingFormRepository.findAll();
    }
}
