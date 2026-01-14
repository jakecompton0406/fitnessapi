/* Created – 13 Jan 2026
 
   Summary:
   Unit tests for the WorkoutService layer. These tests validate service behavior
   in isolation using Mockito by mocking the repository dependency. No Spring
   context or database is involved.
 */

package com.jake.fitnessapi.service;

import com.jake.fitnessapi.model.Workout;
import com.jake.fitnessapi.repository.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {

    // Mocked repository dependency
    @Mock
    private WorkoutRepository workoutRepository;

    // Service under test with mocked dependencies injected
    @InjectMocks
    private WorkoutService workoutService;

    // Initialize Mockito annotations before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWorkoutsReturnsListFromRepository() {
        when(workoutRepository.findAll()).thenReturn(Collections.emptyList());

        List<Workout> result = workoutService.getWorkouts();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(workoutRepository).findAll();
    }

    @Test
    void createWorkoutSavesAndReturnsWorkout() {
        Workout workout = new Workout();
        workout.setType("Strength");
        workout.setDurationMinutes(30);
        workout.setWorkoutDate(LocalDate.now());

        when(workoutRepository.save(any(Workout.class))).thenReturn(workout);

        Workout saved = workoutService.createWorkout(workout);

        assertNotNull(saved);
        assertEquals("Strength", saved.getType());
        assertEquals(30, saved.getDurationMinutes());
        verify(workoutRepository).save(workout);
    }
}
