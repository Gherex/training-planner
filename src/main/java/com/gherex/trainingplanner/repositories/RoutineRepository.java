package com.gherex.trainingplanner.repositories;

import com.gherex.trainingplanner.entities.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

}
