package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.Routine;
import com.gherex.trainingplanner.entities.RoutineDay;

import java.util.List;
import java.util.Optional;

public interface RoutineDayService {

    public List<RoutineDay> getAllRoutineDays();

    public Optional<RoutineDay> getRoutineDayById(Long id);

    public RoutineDay createRoutineDay(RoutineDay routineDay);

    public Optional<RoutineDay> updateRoutineDay(RoutineDay routineDay);

    public Optional<RoutineDay> partialUpdateRoutineDay(RoutineDay routineDay);

    public void deleteRoutineDay(Long id);

    boolean existsById(Long id);
}