package com.jake.fitnessapi.common;

import java.time.Instant;
import java.util.List;

/*
 * This class defines the structure of error responses returned by the API.
 * Creating this class allows us to return consistent, readable error messages
 * instead of using Spring Boot’s default error format.
 */
public class ApiError {

    /*
     * Stores the time when the error occurred.
     */
    private Instant timestamp;

    /*
     * HTTP status code (ex: 400, 404, 500).
     */
    private int status;

    /*
     * Short description of the error type.
     */
    private String error;

    /*
     * Detailed message explaining what went wrong.
     */
    private String message;

    /*
     * The endpoint path that caused the error.
     */
    private String path;

    /*
     * List of validation errors (only used for validation failures).
     */
    private List<FieldViolation> fieldErrors;

    /*
     * Constructor used to create an ApiError object.
     */
    public ApiError(
            Instant timestamp,
            int status,
            String error,
            String message,
            String path,
            List<FieldViolation> fieldErrors
    ) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    // Getters required for JSON serialization

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<FieldViolation> getFieldErrors() {
        return fieldErrors;
    }

    /*
     * Inner class used to represent individual field validation errors.
     */
    public static class FieldViolation {

        /*
         * Name of the field that failed validation.
         */
        private String field;

        /*
         * Validation error message for the field.
         */
        private String message;

        public FieldViolation(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }
}
