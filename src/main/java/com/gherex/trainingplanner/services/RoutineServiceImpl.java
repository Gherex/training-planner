package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.Routine;
import com.gherex.trainingplanner.repositories.RoutineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;

    public RoutineServiceImpl(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public List<Routine> getAllRoutines() {
        return routineRepository.findAll();

    }

    @Override
    public Optional<Routine> getRoutineById(Long id) {
        return routineRepository.findById(id);
    }

    @Override
    public Routine createRoutine(Routine routine) {
        return routineRepository.save(routine);
    }

    @Override
    public Optional<Routine> updateRoutine(Routine routine) {
        Long id = routine.getId();
        if (id != null && routineRepository.existsById(id)) {
            routineRepository.save(routine);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Routine> partialUpdateRoutine(Routine routine) {
        Long id = routine.getId();
        if (id != null && routineRepository.existsById(id)) {
            Optional<Routine> r = routineRepository.findById(id);
            if (routine.getName() != null) r.get().setName(routine.getName());
            if (routine.getGoal() != null) r.get().setGoal(routine.getGoal());
            if (routine.getDescription() != null) r.get().setDescription(routine.getDescription());
            if (routine.getDurationWeeks() != null) r.get().setDurationWeeks(routine.getDurationWeeks());
            if (routine.getRoutineDays() != null) r.get().setRoutineDays(routine.getRoutineDays());

            routineRepository.save(r.get());
        }
        return Optional.empty();
    }

    @Override
    public void deleteRoutine(Long id) {
        routineRepository.deleteById(id);
    }
}
