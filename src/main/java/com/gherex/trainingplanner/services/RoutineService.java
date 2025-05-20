package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.Routine;

import java.util.List;
import java.util.Optional;

public interface RoutineService {

    public List<Routine> getAllRoutines();

    public Optional<Routine> getRoutineById(Long id);

    public Routine createRoutine(Routine routine);

    public Optional<Routine> updateRoutine(Routine routine);

    public Optional<Routine> partialUpdateRoutine(Routine routine);

    public void deleteRoutine(Long id);

    boolean existsById(Long id);
}
