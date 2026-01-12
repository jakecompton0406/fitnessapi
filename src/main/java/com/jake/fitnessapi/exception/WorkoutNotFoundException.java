/* Created – 12 Jan 2026
 */
package com.jake.fitnessapi.exception;

// Custom exception used when a workout cannot be found by its ID.
public class WorkoutNotFoundException extends RuntimeException {

    // Message returned to the client in the error response
    public WorkoutNotFoundException(Long id) {
        super("Workout not found with id: " + id);
    }
}
