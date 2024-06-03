package com.felix.sacn.demo.repository;

import com.felix.sacn.demo.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findBySymptomIn(String[] symptom);
    List<Recommendation> findByHealthGoalIn(String[] healthGoal);
    List<Recommendation> findByUserId(Long userId);
}
