/* Created – 4 Jan 2026
 * Last updated – 13 Jan 2026
 */

package com.jake.fitnessapi.controller;

import com.jake.fitnessapi.dto.WorkoutPatchDTO;
import com.jake.fitnessapi.dto.WorkoutRequestDTO;
import com.jake.fitnessapi.model.Workout;
import com.jake.fitnessapi.service.WorkoutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * REST controller for workout-related endpoints.
 * Delegates business logic to the service layer.
 */
@RestController
public class WorkoutController {

    private final WorkoutService workoutService;

    // Constructor injection
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // GET: returns all workouts
    @GetMapping("/workouts")
    public List<Workout> getWorkouts() {
        return workoutService.getWorkouts();
    }

    // GET: returns workout by ID
    @GetMapping("/workouts/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    // POST: create a new workout using a request DTO
    @PostMapping("/workouts")
    @ResponseStatus(HttpStatus.CREATED)
    public Workout createWorkout(@Valid @RequestBody WorkoutRequestDTO workoutRequestDTO) {

        Workout workout = new Workout();
        workout.setType(workoutRequestDTO.getType());
        workout.setDurationMinutes(workoutRequestDTO.getDurationMinutes());
        workout.setWorkoutDate(workoutRequestDTO.getWorkoutDate());

        return workoutService.createWorkout(workout);
    }

    /* PUT: full update of an existing workout
       Replaces all workout fields with new values */
    @PutMapping("/workouts/{id}")
    public Workout updateWorkout(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutRequestDTO workoutRequestDTO
    ) {
        Workout updated = new Workout();
        updated.setType(workoutRequestDTO.getType());
        updated.setDurationMinutes(workoutRequestDTO.getDurationMinutes());
        updated.setWorkoutDate(workoutRequestDTO.getWorkoutDate());

        return workoutService.updateWorkout(id, updated);
    }

    /* PATCH: partial update of an existing workout
       Only non-null fields are applied
       @Valid enforces constraint checks on provided fields */
    @PatchMapping("/workouts/{id}")
    public Workout patchWorkout(
        @PathVariable Long id,
        @Valid @RequestBody WorkoutPatchDTO workoutPatchDTO
    ) {
        Workout patch = new Workout();
        patch.setType(workoutPatchDTO.getType());
        patch.setDurationMinutes(workoutPatchDTO.getDurationMinutes());
        patch.setWorkoutDate(workoutPatchDTO.getWorkoutDate());

        return workoutService.patchWorkout(id, patch);
    }

    // Removes workout by ID
    @DeleteMapping("/workouts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }
}
