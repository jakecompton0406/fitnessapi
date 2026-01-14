/* Created – 13 Jan 2026
 * Tests for WorkoutController endpoints
 */

package com.jake.fitnessapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jake.fitnessapi.model.Workout;
import com.jake.fitnessapi.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutController.class)
class WorkoutControllerTest {

    @Autowired
    private MockMvc mockMvc;
//Mockbean to inject into controller by sping context
    @MockBean
    private WorkoutService workoutService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getWorkoutsReturnsOk() throws Exception {
        when(workoutService.getWorkouts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/workouts"))
                .andExpect(status().isOk());
    }

    @Test
    void createWorkoutValidReturns201() throws Exception {
        // Build a valid Workout based on your schema (durationMinutes and type are NOT NULL)
        Workout workout = new Workout();
        workout.setType("Strength");
        workout.setDurationMinutes(45);
        workout.setWorkoutDate(LocalDate.now());

        when(workoutService.createWorkout(org.mockito.ArgumentMatchers.any(Workout.class)))
                .thenReturn(workout);

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(workout)))
                .andExpect(status().isCreated());
    }

    @Test
    void createWorkoutInvalidReturns400() throws Exception {
        // Missing required fields (type, durationMinutes) should trigger validation => 400
        Workout invalidWorkout = new Workout();

        mockMvc.perform(post("/workouts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidWorkout)))
                .andExpect(status().isBadRequest());
    }
}
