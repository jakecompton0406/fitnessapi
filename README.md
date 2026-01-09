# Fitness API

Spring Boot backend API for a fitness tracking application.

## Tech Stack
- Java
- Spring Boot
- Maven

## Current Features
- Health check endpoint to verify API status
- POST /workouts endpoint with validation
- Service layer separation
- Global exception handling with consistent JSON errors

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /health  | API health status check |
| POST   | /workouts | Create a new workout |

## Run Locally
mvn spring-boot:run

## Development Log & Time Tracking

### January 8, 2026 — Day 2  
**Time Spent:** 4.0 hours

**Summary:**  
Work focused on making the API production-ready. Input validation, proper HTTP status codes, and centralized error handling were implemented to ensure clean and consistent responses.

**Breakdown:**
- Finalized POST /workouts endpoint
- Implemented proper 201 Created responses
- Added request validation using @NotBlank and @NotNull
- Created ApiError model for custom error responses
- Implemented global ApiExceptionHandler
- Added consistent JSON errors for 400, 404, and 500 responses
- Debugged and refactored code to ensure clean builds

---

### January 4, 2026 — Day 1  
**Time Spent:** 2.5 hours

**Summary:**  
Initial project setup was completed. The Spring Boot application was successfully built and run, confirming the environment was configured correctly. The foundation of the API was established so future features could be added cleanly.

**Breakdown:**
- Initialized Spring Boot project
- Verified application startup
- Created first real API endpoint
- Implemented controller and service layer separation
- Organized project structure for scalability

---

### Total Time Invested
**6.5 hours**

## Roadmap
Planned features for future development:
- Workout → Exercise relationships
- Exercise, set, and rep tracking
- Full CRUD REST endpoints
- Database integration (PostgreSQL or MySQL)
- User authentication and authorization
- Dockerized deployment

## Notes
This project is under active development.
