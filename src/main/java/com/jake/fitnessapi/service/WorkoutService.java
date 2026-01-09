package com.jake.fitnessapi.service;

import com.jake.fitnessapi.model.Workout;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Service class that handles workout-related logic
@Service
public class WorkoutService {

    // Temporary in-memory list (will be replaced with a database later)
    private final List<Workout> workouts = new ArrayList<>();

    // When the app starts, preload a couple workouts
    public WorkoutService() {
        workouts.add(new Workout(UUID.randomUUID(), "Upper Body", LocalDate.now()));
        workouts.add(new Workout(UUID.randomUUID(), "Lower Body", LocalDate.now().minusDays(1)));
    }

    // Returns all workouts
    public List<Workout> getWorkouts() {
        return workouts;
    }

    // Creates a new workout and returns it
    public Workout createWorkout(Workout workout) {
        workout.setId(UUID.randomUUID()); // server generates the ID
        workouts.add(workout);
        return workout;
    }
}
