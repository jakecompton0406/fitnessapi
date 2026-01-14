/* Created – 13 Jan 2026
 * Last updated:
 */

package com.jake.fitnessapi.dto;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

/*
 * DTO used for partial workout updates (PATCH).
 * All fields are optional; only provided values are applied.
 */
public class WorkoutPatchDTO {

    // Optional workout type
    private String type;

    // Optional duration; must be positive if provided
    @Positive(message = "Duration must be greater than 0")
    private Integer durationMinutes;

    // Optional workout date
    private LocalDate workoutDate;

    public String getType() {
        return type;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }
}
