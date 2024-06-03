package com.felix.sacn.demo.controller;

import com.felix.sacn.demo.entity.Recommendation;
import com.felix.sacn.demo.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsForUser(@PathVariable Long userId) {
        List<Recommendation> recommendations = recommendationService.getRecommendationsForUser(userId);
        return new ResponseEntity<>(recommendations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recommendation> createRecommendation(@RequestBody Recommendation recommendation) {
        Recommendation savedRecommendation = recommendationService.saveRecommendation(recommendation);
        return new ResponseEntity<>(savedRecommendation, HttpStatus.CREATED);
    }
}
