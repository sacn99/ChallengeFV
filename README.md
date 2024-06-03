# Health Assistant Application

## Project Overview

The Health Assistant Application is designed to help users set health and wellness goals and provide personalized recommendations based on their symptoms and objectives. The application features an onboarding form where users can input their goals and symptoms, and based on this input, the system will generate relevant recommendations.

## Features

- **User Registration:** Users can create an account with their name, email, and password.
- **Onboarding Form:** Users can select their health goals and current symptoms from a list of predefined options.
- **Personalized Recommendations:** Based on the user's input, the system provides tailored recommendations to help them achieve their health goals and address their symptoms.

## Tech Stack

- **Backend:** Spring Boot, Spring Data JPA, H2 Database
- **Frontend:** React, Axios
- **Other:** CORS configuration, responsive design with CSS

## Setting Up the Development Environment

### Prerequisites

- **Java Development Kit (JDK) 17**
- **Node.js and npm**
- **Maven**

### Backend Setup

1. Clone the repository:
   bash
   git clone <repository-url>
   cd <repository-directory>

2. Navigate to the backend directory and run the application:
  cd backend
  mvn spring-boot:run

3. The backend server should start on http://localhost:8080

### Frontend Setup

1. Navigate to the frontend directory:
    cd frontend

2. Install the dependencies:
    npm install

4. Start the frontend development server:
    npm start

5. The frontend application should be running on http://localhost:3000.

## Database Configuration

The application uses H2 in-memory database for development purposes. The database console can be accessed at [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Use the following credentials to log in:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave blank)

## Recommendation Logic

The recommendation logic is based on predefined health goals and symptoms. When a user submits the onboarding form with their selected health goals and symptoms, the system searches for matching recommendations and associates them with the user.

### Predefined Recommendations

The system initializes with a set of predefined recommendations stored in the H2 database. These recommendations have associated health goals and symptoms.

### Assigning Recommendations

When a user submits the onboarding form, the backend service (`OnboardingFormService`) performs the following steps:

1. Split the user's input (health goals and symptoms) into arrays.
2. Search the repository for recommendations matching the user's health goals and symptoms.
3. Associate the matching recommendations with the user's profile.
4. Return the list of recommendations as the response to the frontend.

The relevant code for assigning recommendations is found in the `OnboardingFormService` class:

```java
private void assignRecommendations(OnboardingForm savedForm) {
    String[] symptoms = savedForm.getCurrentSymptoms().split(", ");
    String[] healthGoals = savedForm.getHealthGoals().split(", ");
    
    List<Recommendation> matchingRecommendations = Stream.concat(
        recommendationRepository.findBySymptomIn(symptoms).stream(),
        recommendationRepository.findByHealthGoalIn(healthGoals).stream()
    ).collect(Collectors.toList());

    for (Recommendation recommendation : matchingRecommendations) {
        recommendation.setUser(savedForm.getUser());
        recommendationRepository.save(recommendation);
    }
}
```
This logic ensures that users receive personalized recommendations tailored to their specific health goals and symptoms.
