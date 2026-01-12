package com.jake.fitnessapi.service;

import com.jake.fitnessapi.dto.WorkoutRequestDTO;
import com.jake.fitnessapi.model.Workout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService {

    private final List<Workout> workouts = new ArrayList<>();

    // Returns all workouts currently stored in memory
    public List<Workout> getWorkouts() {
        return workouts;
    }

// Accepts DTO input and maps it into a Workout object for internal storage
public Workout createWorkout(WorkoutRequestDTO dto) {

    Workout workout = new Workout();
    workout.setType(dto.getType());
    workout.setDurationMinutes(dto.getDurationMinutes());
    workout.setWorkoutDate(dto.getWorkoutDate());

    workouts.add(workout);

    return workout;
}

}
