# Fitness API
Spring Boot backend API for a fitness tracking application.

## Tech Stack
Languages, frameworks, tools, and services used in this application:
- Git / GitHub
- H2 Browser Console
- H2 Database
- Hibernate
- Jakarta Validation (Bean Validation)
- Java
- JUnit 5
- Maven
- Maven Wrapper
- MockMvc
- Mockito
- RESTful APIs
- Spring Boot
- Spring Boot Test
- Spring Data JPA

## Current Features
- Health check endpoint to verify API status
- Root endpoint confirming the API is running
- POST `/workouts` endpoint using DTO-based request input
- GET `/workouts` endpoint to retrieve all workouts
- GET `/workouts/{id}` endpoint to retrieve a workout by ID
- DELETE `/workouts/{id}` endpoint to remove a workout
- Request validation using `@NotBlank`, `@NotNull`, and `@Positive`
- Proper 201 Created responses for successful POST requests
- Proper 204 No Content responses for successful DELETE requests
- Proper 404 Not Found responses for missing workout IDs
- Global exception handling with consistent JSON error responses
- Custom `WorkoutNotFoundException` for missing resources
- Service layer separation (controller → service → repository)
- Database persistence using Spring Data JPA
- H2 in-memory database for development
- Auto-generated database IDs for workouts
- H2 browser console enabled for inspecting tables and data
- Automated application context testing
- Controller-level endpoint testing using MockMvc
- Service-layer unit testing with Mockito
- Validation behavior verified through automated tests
- Isolated and repeatable test suite



## API Endpoints
| Method | Endpoint           | Description |
|--------|--------------------|-------------|
| GET    | /                  | Confirms the API is running |
| GET    | /health            | API health status check |
| GET    | /workouts          | Retrieve all workouts |
| GET    | /workouts/{id}     | Retrieve a workout by ID |
| POST   | /workouts          | Create a new workout (validated request body) |
| DELETE | /workouts/{id}     | Delete a workout by ID |



## Run Locally
mvn spring-boot:run

----------------------------------------------------------------------------------------
## Development Log & Time Tracking

### January 13, 2026 — Milestones 2.11 & 2.12  
**Time Spent:** 1.5 hours  

**Summary:**  
Work focused on implementing a complete automated testing foundation for the API. Application startup, controller endpoints, request validation, and service-layer logic were tested using Spring Boot Test, MockMvc, and Mockito. Tests were structured to ensure isolation, fast execution, and long-term maintainability.

**Breakdown:**
- Added application context startup test
- Implemented HealthController endpoint tests
- Implemented WorkoutController endpoint tests
- Verified HTTP responses (200, 201, 400)
- Mocked service dependencies using Mockito
- Added service-layer unit tests for WorkoutService
- Verified repository interaction and service behavior
- Confirmed all tests pass with zero failures


### January 12, 2026 — Milestone 2.10  
**Time Spent:** 1 hour

**Summary:**  
Work focused on expanding the API to support full CRUD robustness. GET-by-ID and DELETE endpoints were implemented, and proper 404 handling was enforced for missing workout resources. 
The service and controller layers were refined to prevent internal server errors and ensure REST-compliant behavior. End-to-end workflows were verified using curl, including create, 
retrieve, delete, and confirm-not-found scenarios.

**Breakdown:**
- Implemented GET `/workouts/{id}` endpoint
- Implemented DELETE `/workouts/{id}` endpoint
- Added `WorkoutNotFoundException` for missing resources
- Updated global exception handling to return proper 404 responses
- Prevented 500 errors caused by missing entities
- Verified full POST → GET → DELETE → GET (404) workflow
- Confirmed consistent JSON error responses across endpoints


### January 11, 2026 — Milestone 2.8 & 2.9  
**Time Spent:** 1.0 hour

**Summary:**  
Work focused on improving API structure and completing the transition from request validation to full database persistence. DTOs were introduced 
to separate API input from domain models, validation was enforced at the request layer, and the application was migrated from in-memory storage 
to database-backed persistence using Spring Data JPA with an H2 in-memory database. End-to-end functionality was verified using curl, browser testing, and the H2 console.

**Breakdown:**
- Implemented DTO-based request handling for POST /workouts
- Added `WorkoutRequestDTO` to decouple API input from the entity
- Added `spring-boot-starter-validation` dependency
- Enforced validation using `@NotBlank`, `@NotNull`, and `@Positive`
- Updated controller to use `@Valid` on request bodies
- Verified 400 Bad Request responses for invalid input
- Added `WorkoutRepository` extending `JpaRepository`
- Refactored `WorkoutService` to use repository instead of in-memory lists
- Enabled H2 in-memory database for development
- Configured JPA and Hibernate auto table creation
- Verified persistence through H2 browser console
- Confirmed database-generated IDs replaced null/in-memory IDs
- Tested full POST → DB → GET flow successfully

----------------------------------------------------------------------------------------
### January 9, 2026 — Day 3  
**Time Spent:** 1.0 hour

**Summary:**  
Work focused on upgrading the application from in-memory storage to real database persistence. The project was migrated to use Spring Data JPA with an H2 in-memory database. 
Entity scanning, repository wiring, and service refactoring were completed, and the database schema was verified directly through the H2 console.

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
Initial project setup was completed. The Spring Boot application was successfully built and run, confirming the environment was configured correctly. 
The foundation of the API was established so future features could be added cleanly.

**Breakdown:**
- Initialized Spring Boot project
- Verified application startup
- Created first real API endpoint
- Implemented controller and service layer separation
- Organized project structure for scalability

----------------------------------------------------------------------------------------

### Total Time Invested
**11.0 hours**

----------------------------------------------------------------------------------------
## Files Created

### `ApiError.java`
- Defines the structure of error responses returned as JSON.
- Allows control over error formatting instead of using Spring defaults.
- Includes:
  - timestamp (when the error occurred)
  - status (400/404/500)
  - error (short label such as “Bad Request”)
  - message (detailed description)
  - path (endpoint that caused the error)
  - fieldErrors (validation-specific error messages)

### `ApiExceptionHandler.java`
- Global exception handler for the entire API.
- Centralizes error handling so controllers do not need try/catch blocks.
- Returns errors using the `ApiError` format for consistency.
- Includes:
  - validation error handling (MethodArgumentNotValidException → 400)
  - NotFoundException handling → 404
  - generic exception handling → 500

### `FitnessapiApplication.java`
- Main Spring Boot application entry point.
- Contains the `main()` method that starts the application.
- Includes:
  - Spring Boot auto-configuration
  - `@EntityScan` configuration for JPA entities

### `FitnessapiApplicationTests.java`
- Application-level test class used to verify the project boots correctly.
- Ensures the Spring Boot application context loads without configuration errors.
- Includes:
  - `contextLoads()` test method
  - full application context initialization using `@SpringBootTest`

### `HealthController.java`
- Simple controller used to confirm the API is running.
- Provides a lightweight endpoint for system health verification.
- Includes:
  - `GET /health` endpoint
  - basic API availability confirmation

### `HealthControllerTest.java`
- Controller-level test class for the HealthController.
- Verifies the health endpoint is reachable and returns the correct HTTP status.
- Includes:
  - MockMvc-based controller testing
  - `GET /health` request validation expecting 200 OK

### `HomeController.java`
- Controller used to handle the root application endpoint.
- Prevents errors when accessing the base URL.
- Includes:
  - `GET /` endpoint
  - API running confirmation message

### `NotFoundException.java`
- Custom exception thrown when a requested resource cannot be found.
- Used to represent missing entities in the application.
- Includes:
  - custom exception definition
  - integration with global exception handling for 404 responses

### `Workout.java`
- JPA entity representing the workouts database table.
- Defines the persistent structure of workout records.
- Includes:
  - `@Entity` and `@Table` annotations
  - database-generated `Long` ID
  - workout fields: type, durationMinutes, workoutDate

### `WorkoutController.java`
- Controller responsible for workout-related API endpoints.
- Routes HTTP requests to the service layer.
- Includes:
  - POST `/workouts` endpoint using DTO-based input
  - GET `/workouts` and GET `/workouts/{id}` endpoints
  - DELETE `/workouts/{id}` endpoint
  - request validation using `@Valid`

### `WorkoutControllerTest.java`
- Controller-level test class for workout-related endpoints.
- Verifies HTTP behavior and validation at the web layer.
- Includes:
  - MockMvc-based controller testing
  - mocked `WorkoutService` dependency
  - GET `/workouts` endpoint test
  - POST `/workouts` valid request test (201 Created)
  - POST `/workouts` invalid request test (400 Bad Request)

### `WorkoutRepository.java`
- Repository responsible for database access for workouts.
- Acts as the persistence layer between service and database.
- Includes:
  - extension of `JpaRepository`
  - built-in CRUD operations
  - automatic query method support

### `WorkoutRequestDTO.java`
- Data Transfer Object used for workout creation requests.
- Separates API input validation from persistence models.
- Includes:
  - request field definitions
  - validation annotations (`@NotBlank`, `@NotNull`, `@Positive`)
  - mapping support to the Workout entity

### `WorkoutService.java`
- Service layer containing workout-related business logic.
- Coordinates between controllers and repositories.
- Includes:
  - DTO-to-entity mapping logic
  - database persistence delegation
  - retrieval and deletion logic

### `WorkoutServiceTest.java`
- Service-layer unit test class for WorkoutService.
- Verifies business logic in isolation from Spring and the database.
- Includes:
  - mocked `WorkoutRepository` dependency
  - `getWorkouts()` behavior verification
  - `createWorkout()` save and return verification

----------------------------------------------------------------------------------------
## Build / Project Files

### `.mvn/`
- This folder is part of the Maven Wrapper setup.
- It stores wrapper configuration so the wrapper works correctly.

### `application.properties`
- Configures the H2 in-memory database.
- Sets JPA behavior for auto-creating tables.
- Enables SQL logging for debugging.
- Enables the H2 console for manual inspection.
- Replaced previous PostgreSQL configuration.
- **Updated January 11, 2026**
- Added H2 datasource configuration to support JPA persistence.
- Enabled Hibernate auto table creation and SQL logging.
- Required to verify database persistence through the H2 browser console.

### `mvnw` / `mvnw.cmd`
- These are the Maven Wrapper files.
- They allow the project to run Maven without a global Maven install.
- Ensures consistent Maven versions across environments.
- `mvnw` is used on Mac/Linux.
- `mvnw.cmd` is used on Windows.

### `pom.xml`
- This is the Maven project configuration file.
- It controls project dependencies and build behavior.
- **Updated January 11, 2026**
- Added:
  - `spring-boot-starter-validation` for DTO request validation
  - `spring-boot-starter-data-jpa` for ORM and repositories
  - `h2` for an in-memory development database
- Removed:
  - JDBC-only configuration that was no longer needed
  - PostgreSQL runtime dependency for now
- These updates were required to support validation and database persistence milestones.


----------------------------------------------------------------------------------------

## Roadmap
Planned features for future development:
- Workout → Exercise relationships
- Exercise, set, and rep tracking
- Complete CRUD REST endpoints (PUT / PATCH remaining)
- Production database integration (PostgreSQL or MySQL)
- User authentication and authorization
- Dockerized deployment

## Statement Notes******
This project is under active development and will continue to evolve as new features and improvements are added.
