/* Created: 4 Jan 2026
    Last updated: 12 Jan 2026 */

package com.jake.fitnessapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/*
 * Workout entity
 * Represents the workouts table in the database
 */
@Entity
@Table(name = "workouts")
public class Workout {

    // Primary key auto gen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // workout type (Running, rucking, chest, etc)
    @Column(nullable = false)
    private String type;

    // time duration is stored in minutes
    @Column(nullable = false)
    private Integer durationMinutes;

    // optional date field
    private LocalDate workoutDate;

    // required by JPA
    public Workout() {}

    // used when creating a new workout
    public Workout(String type, Integer durationMinutes, LocalDate workoutDate) {
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.workoutDate = workoutDate;
    }

    public Long getId() { return id; }
    public String getType() { return type; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public LocalDate getWorkoutDate() { return workoutDate; }

    public void setId(Long id) { this.id = id; }
    public void setType(String type) { this.type = type; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public void setWorkoutDate(LocalDate workoutDate) { this.workoutDate = workoutDate; }
}
