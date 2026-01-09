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

----------------------------------------------------------------------------------------

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

----------------------------------------------------------------------------------------

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

----------------------------------------------------------------------------------------

### Total Time Invested
**6.5 hours**

## Personnel Notes********

### `ApiError.java`
- This class is basically the shape of the error response we return as JSON.
- Instead of Spring returning a random default error format, we control it.
- It includes stuff like:
  - timestamp (when it happened)
  - status (400/404/500)
  - error (short label like “Bad Request”)
  - message (more detail)
  - path (what endpoint caused it)
  - fieldErrors (a list of validation problems like “title is required”)

### `ApiExceptionHandler.java`
- This is the global error handler for the whole API.
- It catches exceptions in one place so controllers don’t need try or catch everywhere.
- It returns our `ApiError` format so the clients always gets consistent JSON errors.
- Handles:
  - validation errors (MethodArgumentNotValidException → 400)
  - NotFoundException → 404
  - generic exceptions → 500

### `NotFoundException.java`
- This is a custom exception we throw when something isn’t found.
- Example: user requests a workout ID that does not exist.
- The handler catches it and turns it into a clean 404 response.

### `HealthController.java`
- Simple controller used to confirm the API is running.
- The `GET /health` endpoint lets me quickly test the server.

### `WorkoutController.java`
- This is the controller for workout related endpoints.
- It receives the HTTP request, validates input, and calls the service layer.
- It should NOT contain business logic that goes in the service.

### `WorkoutService.java`
- This is where the workout “business logic” lives.
- It keeps the controller clean and makes the app easier to scale later.
- This is where we would do things like:
  - create a workout
  - check if something exists
  - throw NotFoundException when needed

### `Workout.java`
- This is the data model for a workout.
- It represents the fields we store and/or receive (like workout name/title, etc.).
- It also contains validation annotations like `@NotBlank` and `@NotNull`.

### `FitnessapiApplication.java`
- This is the main Spring Boot starter file.
- It contains the `main()` method that starts the whole application.
- When we run the project, Spring Boot starts here.

----------------------------------------------------------------------------------------

## Build / Project Files

### `pom.xml`
- This is the Maven project file.
- It’s basically the list of dependencies and build settings for the project.
- Example dependencies include Spring Boot Web and Validation.
- If something doesn’t compile because of missing libraries, this file is usually the fix.

### `mvnw` / `mvnw.cmd`
- These are the Maven Wrapper files.
- They let the project run Maven without needing Maven installed globally.
- `mvnw` is usually for Mac/Linux, and `mvnw.cmd` is for Windows.
- This helps keep everyone on the same Maven version.

### `.mvn/`
- This folder is part of the Maven Wrapper setup.
- It stores wrapper config so the wrapper works correctly.

----------------------------------------------------------------------------------------

## Roadmap
Planned features for future development:
- Workout → Exercise relationships
- Exercise, set, and rep tracking
- Full crud rest endpoints
- Database integration (PostgreSQL or MySQL)
- User authentication and authorization
- Dockerized deployment

## Statement Notes******
This project is under active development.
