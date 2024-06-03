package com.felix.sacn.demo.service;

import com.felix.sacn.demo.entity.Recommendation;
import com.felix.sacn.demo.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public List<Recommendation> getRecommendationsForUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation saveRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
}
