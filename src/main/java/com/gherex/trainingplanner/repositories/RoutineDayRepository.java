package com.gherex.trainingplanner.repositories;

import com.gherex.trainingplanner.entities.RoutineDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineDayRepository extends JpaRepository<RoutineDay, Long> {

}