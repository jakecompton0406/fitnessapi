package com.jake.fitnessapi.service;

import com.jake.fitnessapi.dto.WorkoutRequestDTO;
import com.jake.fitnessapi.model.Workout;
import com.jake.fitnessapi.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    // Constructor injection for repository
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Returns all workouts from the database
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    // Maps DTO to entity and saves workout to the database
    public Workout createWorkout(WorkoutRequestDTO dto) {

        Workout workout = new Workout();
        workout.setType(dto.getType());
        workout.setDurationMinutes(dto.getDurationMinutes());
        workout.setWorkoutDate(dto.getWorkoutDate());

        return workoutRepository.save(workout);
    }
}
