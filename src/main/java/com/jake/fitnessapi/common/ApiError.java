/* Created – 4 Jan 2026
 * Last updated – 12 Jan 2026
 */

package com.jake.fitnessapi.common;

import java.time.Instant;
import java.util.List;

/*
 * Standard error response model used across the API.
 * Ensures consistent and predictable error payloads.
 */
public class ApiError {

    // Timestamp when the error occurred
    private Instant timestamp;

    // HTTP status code (400, 404, 500, etc.)
    private int status;

    // Short error label
    private String error;

    // Detailed error message
    private String message;

    // Request path that caused the error
    private String path;

    // Validation errors (only present for 400 responses)
    private List<FieldViolation> fieldErrors;

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
     * Represents a single field-level validation error.
     */
    public static class FieldViolation {

        private String field;
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
