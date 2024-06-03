// src/components/OnboardingForm.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../App.css';

const OnboardingForm = () => {
  const [healthGoals, setHealthGoals] = useState([]);
  const [currentSymptoms, setCurrentSymptoms] = useState([]);
  const [userId, setUserId] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const userId = params.get('userId');
    setUserId(userId);
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/onboarding', {
        user: { id: userId },
        healthGoals: healthGoals.join(', '),
        currentSymptoms: currentSymptoms.join(', '),
      });
      navigate(`/recommendations?userId=${userId}`);
    } catch (error) {
      console.error('Error creating onboarding form:', error);
    }
  };

  const handleCheckboxChange = (setFunction, value) => {
    setFunction((prev) => {
      if (prev.includes(value)) {
        return prev.filter((item) => item !== value);
      } else {
        return [...prev, value];
      }
    });
  };

  return (
    <div className="container">
      <h2>Health Assistant Onboarding</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Health Goals:</label>
          <div>
            <label>
              <input
                type="checkbox"
                value="Lose weight"
                onChange={(e) => handleCheckboxChange(setHealthGoals, e.target.value)}
              />
              Lose weight
            </label>
            <label>
              <input
                type="checkbox"
                value="Build muscle"
                onChange={(e) => handleCheckboxChange(setHealthGoals, e.target.value)}
              />
              Build muscle
            </label>
            <label>
              <input
                type="checkbox"
                value="Improve endurance"
                onChange={(e) => handleCheckboxChange(setHealthGoals, e.target.value)}
              />
              Improve endurance
            </label>
          </div>
        </div>
        <div>
          <label>Current Symptoms:</label>
          <div>
            <label>
              <input
                type="checkbox"
                value="Fatigue"
                onChange={(e) => handleCheckboxChange(setCurrentSymptoms, e.target.value)}
              />
              Fatigue
            </label>
            <label>
              <input
                type="checkbox"
                value="Muscle pain"
                onChange={(e) => handleCheckboxChange(setCurrentSymptoms, e.target.value)}
              />
              Muscle pain
            </label>
            <label>
              <input
                type="checkbox"
                value="Headache"
                onChange={(e) => handleCheckboxChange(setCurrentSymptoms, e.target.value)}
              />
              Headache
            </label>
          </div>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default OnboardingForm;
