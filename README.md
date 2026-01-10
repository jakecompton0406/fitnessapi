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

### January 9, 2026 — Day 3  
**Time Spent:** 1.0 hour

**Summary:**  
Work focused on upgrading the application from in-memory storage to real database persistence. The project was migrated to use Spring Data JPA with an H2 in-memory database. Entity scanning, repository wiring, and service refactoring were completed, and the database schema was verified directly through the H2 console.

**Breakdown:**
- Migrated workouts from in-memory list storage to database persistence
- Added Spring Data JPA and H2 dependencies
- Configured H2 datasource and JPA settings
- Refactored `Workout` into a proper JPA entity
- Created `WorkoutRepository` using JpaRepository
- Refactored `WorkoutService` to remove UUID logic and use database-generated IDs
- Debugged entity scanning and repository wiring issues
- Verified table creation and column mappings via H2 console
- Confirmed end-to-end persistence with Hibernate auto-generated schema

----------------------------------------------------------------------------------------

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
**7.5 hours**

----------------------------------------------------------------------------------------

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
- It returns our `ApiError` format so the client always gets consistent JSON errors.
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
- This is the controller for workout-related endpoints.
- It receives the HTTP request and passes it to the service layer.
- It should NOT contain business logic.
- After persistence was added, it no longer works with in-memory data.
- The controller now relies entirely on the service + database.

### `WorkoutService.java`
- This is where the workout “business logic” lives.
- Originally used an in-memory list for storage.
- Refactored to use `WorkoutRepository` for database persistence.
- UUID generation and manual ID setting were removed.
- The database now handles ID creation.
- Keeps the controller clean and makes the app easier to scale later.

### `WorkoutRepository.java`
- This repository was added to handle database access for workouts.
- It extends `JpaRepository`, which provides CRUD operations automatically.
- Replaces the need for manual lists or SQL queries.
- Allows the service layer to:
  - save workouts
  - retrieve all workouts
  - support updates and deletes later
- No implementation code is required.

### `Workout.java`
- This class was refactored into a proper JPA entity.
- It now represents a real database table instead of an in-memory object.
- Uses JPA annotations like:
  - `@Entity`
  - `@Table(name = "workouts")`
  - `@Id` and `@GeneratedValue`
- The ID type was changed from `UUID` to `Long`.
- Validation annotations were removed from this class.
- Validation will live in DTOs later instead of the entity.
- Field names were updated to reflect database usage:
  - `type`
  - `durationMinutes`
  - `workoutDate`

### `FitnessapiApplication.java`
- This is the main Spring Boot starter file.
- It contains the `main()` method that starts the whole application.
- `@EntityScan` was added to ensure Hibernate detects entity classes.
- This fixed issues where repositories could not find managed entities.
- All component scanning now works as expected.

----------------------------------------------------------------------------------------
## Build / Project Files

### `pom.xml`
- This is the Maven project configuration file.
- It controls project dependencies and build behavior.
- It was updated to support database persistence.
- Added:
  - `spring-boot-starter-data-jpa` for ORM and repositories
  - `h2` for an in-memory development database
- Removed:
  - JDBC-only configuration that was no longer needed
  - PostgreSQL runtime dependency for now
- This file was key in fixing JPA, Hibernate, and entity errors.

### `application.properties`
- Configures the H2 in-memory database.
- Sets JPA behavior for auto-creating tables.
- Enables SQL logging for debugging.
- Enables the H2 console for manual inspection.
- Replaced previous PostgreSQL configuration.

### `mvnw` / `mvnw.cmd`
- These are the Maven Wrapper files.
- They allow the project to run Maven without a global Maven install.
- Ensures consistent Maven versions across environments.
- `mvnw` is used on Mac/Linux.
- `mvnw.cmd` is used on Windows.

### `.mvn/`
- This folder is part of the Maven Wrapper setup.
- It stores wrapper configuration so the wrapper works correctly.

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
