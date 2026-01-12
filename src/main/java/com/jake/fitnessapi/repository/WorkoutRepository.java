/* Created: 8 Jan 2026
    Last updated: 12 Jan 2026 */

package com.jake.fitnessapi.repository;

import com.jake.fitnessapi.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 *JpaRepository gives CRUD for free (save, findAll, delete, etc)
 * no SQL needed right now
 */
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
}
