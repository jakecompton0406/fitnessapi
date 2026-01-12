/* Created – 8 Jan 2026
 * Last updated – 12 Jan 2026
 */

package com.jake.fitnessapi.service;

import com.jake.fitnessapi.exception.WorkoutNotFoundException;
import com.jake.fitnessapi.model.Workout;
import com.jake.fitnessapi.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Base read: return all workouts
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    // GET by ID — added 12 Jan 2026
    // Throws WorkoutNotFoundException when ID does not exist (mapped to 404)
    @Transactional(readOnly = true)
    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
    }

    // Create new workout (POST)
    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    // PUT update — added 12 Jan 2026
    // Full replacement update; all fields are overwritten
    public Workout updateWorkout(Long id, Workout updated) {
        Workout existing = getWorkoutById(id);

        existing.setType(updated.getType());
        existing.setDurationMinutes(updated.getDurationMinutes());
        existing.setWorkoutDate(updated.getWorkoutDate());

        return workoutRepository.save(existing);
    }

    // PATCH update — added 12 Jan 2026
    // Partial update; only non-null fields are applied
    public Workout patchWorkout(Long id, Workout patch) {
        Workout existing = getWorkoutById(id);

        if (patch.getType() != null) {
            existing.setType(patch.getType());
        }
        if (patch.getDurationMinutes() != null) {
            existing.setDurationMinutes(patch.getDurationMinutes());
        }
        if (patch.getWorkoutDate() != null) {
            existing.setWorkoutDate(patch.getWorkoutDate());
        }

        return workoutRepository.save(existing);
    }

    // DELETE — added 12 Jan 2026
    // Removes workout if found, otherwise triggers 404
    public void deleteWorkout(Long id) {
        Workout existing = getWorkoutById(id);
        workoutRepository.delete(existing);
    }
}
