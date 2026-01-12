/* Created – 4 Jan 2026
 * Last updated – 12 Jan 2026
 */

package com.jake.fitnessapi.controller;

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

    // GET – return all workouts
    @GetMapping("/workouts")
    public List<Workout> getWorkouts() {
        return workoutService.getWorkouts();
    }

    // GET – return workout by ID
    @GetMapping("/workouts/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    // POST – create a new workout using a request DTO
    @PostMapping("/workouts")
    @ResponseStatus(HttpStatus.CREATED)
    public Workout createWorkout(@Valid @RequestBody WorkoutRequestDTO workoutRequestDTO) {

        Workout workout = new Workout();
        workout.setType(workoutRequestDTO.getType());
        workout.setDurationMinutes(workoutRequestDTO.getDurationMinutes());
        workout.setWorkoutDate(workoutRequestDTO.getWorkoutDate());

        return workoutService.createWorkout(workout);
    }

    // DELETE – remove workout by ID
    @DeleteMapping("/workouts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);
    }
}
