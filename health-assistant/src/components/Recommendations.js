// src/components/Recommendations.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../App.css';

const Recommendations = () => {
  const [recommendations, setRecommendations] = useState([]);
  const [userId, setUserId] = useState(null);

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const userId = params.get('userId');
    setUserId(userId);
    fetchRecommendations(userId);
  }, []);

  const fetchRecommendations = async (userId) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/recommendations/user/${userId}`);
      setRecommendations(response.data);
    } catch (error) {
      console.error('Error fetching recommendations:', error);
    }
  };

  return (
    <div className="container">
      <h2>Recommendations</h2>
      <ul>
        {recommendations.map((rec) => (
          <li key={rec.recommendationId}>{rec.content}</li>
        ))}
      </ul>
    </div>
  );
};

export default Recommendations;
