package com.gherex.trainingplanner.repositories;

import com.gherex.trainingplanner.entities.AssignedExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedExerciseRepository extends JpaRepository<AssignedExercise, Long> {

}