// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserForm from './components/UserForm';
import OnboardingForm from './components/OnboardingForm';
import Recommendations from './components/Recommendations';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<UserForm />} />
          <Route path="/onboarding" element={<OnboardingForm />} />
          <Route path="/recommendations" element={<Recommendations />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
