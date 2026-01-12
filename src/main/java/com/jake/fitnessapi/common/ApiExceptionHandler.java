/* Created – 4 Jan 2026
 * Last updated – 12 Jan 2026
 */

package com.jake.fitnessapi.common;

import com.jake.fitnessapi.exception.WorkoutNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Centralized API exception handling.
 * Keeps controller code clean and ensures consistent JSON error responses.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    // 400 – Validation errors from @Valid (ex: @NotBlank / @NotNull)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {

        List<ApiError.FieldViolation> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ApiError.FieldViolation(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Validation failed",
                extractPath(request),
                fieldErrors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    // 404 – Workout not found (thrown from service layer when an ID does not exist)
    @ExceptionHandler(WorkoutNotFoundException.class)
    public ResponseEntity<ApiError> handleWorkoutNotFound(WorkoutNotFoundException ex, WebRequest request) {

        ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                extractPath(request),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    // 404 – JPA entity reference not found (covers getReferenceById/getById scenarios)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {

        ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                "Workout not found",
                extractPath(request),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    // 500 – Catch-all fallback (prevents leaking internal exception details to clients)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, WebRequest request) {

        ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Something went wrong",
                extractPath(request),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    // Extracts request path from WebRequest description (ex: "uri=/workouts/1" → "/workouts/1")
    private String extractPath(WebRequest request) {
        String desc = request.getDescription(false);
        return (desc != null && desc.startsWith("uri=")) ? desc.substring(4) : desc;
    }
}
