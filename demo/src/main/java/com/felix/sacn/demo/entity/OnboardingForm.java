package com.felix.sacn.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OnboardingForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String healthGoals;

    @Column(nullable = false)
    private String currentSymptoms;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHealthGoals() {
        return healthGoals;
    }

    public void setHealthGoals(String healthGoals) {
        this.healthGoals = healthGoals;
    }

    public String getCurrentSymptoms() {
        return currentSymptoms;
    }

    public void setCurrentSymptoms(String currentSymptoms) {
        this.currentSymptoms = currentSymptoms;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
