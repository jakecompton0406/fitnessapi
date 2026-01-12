package com.jake.fitnessapi.controller;

import com.jake.fitnessapi.dto.WorkoutRequestDTO;
import com.jake.fitnessapi.model.Workout;
import com.jake.fitnessapi.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller that handles workout-related API requests
@RestController
public class WorkoutController {

    private final WorkoutService workoutService;

    // Constructor injection for the workout service
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // GET endpoint to return all workouts
    @GetMapping("/workouts")
    public List<Workout> getWorkouts() {
        return workoutService.getWorkouts();
    }

    // Updated POST endpoint to accept a DTO instead of the Workout model
    // Uses @Valid to trigger validation on the request body
    @PostMapping("/workouts")
    @ResponseStatus(HttpStatus.CREATED) // Return 201 when a workout is created
    public Workout createWorkout(@Valid @RequestBody WorkoutRequestDTO workoutRequestDTO) {
        return workoutService.createWorkout(workoutRequestDTO);
    }
}
