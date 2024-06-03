package com.felix.sacn.demo.repository;

import com.felix.sacn.demo.entity.OnboardingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingFormRepository extends JpaRepository<OnboardingForm, Long> {
}

