package com.jake.fitnessapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

/* DTO used to receive workout data from the API request.
   Defines the request structure and enforces validation rules. */
public class WorkoutRequestDTO {

    // Workout type (Running, rucking, chest, etc). Must not be blank
    @NotBlank(message = "Workout type is required")
    private String type;

    /*
     * @NotNull ensures field is provided
     * @Positive ensures value is greater than 0
     */
    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be greater than 0")
    private Integer durationMinutes;

    // Optional date field (can be omitted in the request)
    private LocalDate workoutDate;

    // Getters and setters required for JSON binding

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }
}
