package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.RoutineDay;
import com.gherex.trainingplanner.repositories.RoutineDayRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineDayServiceImpl implements RoutineDayService {

    private final RoutineDayRepository routineDayRepository;

    public RoutineDayServiceImpl(RoutineDayRepository routineDayRepository) {
        this.routineDayRepository = routineDayRepository;
    }

    @Override
    public List<RoutineDay> getAllRoutineDays() {
        return routineDayRepository.findAll();

    }

    @Override
    public Optional<RoutineDay> getRoutineDayById(Long id) {
        return routineDayRepository.findById(id);
    }

    @Override
    public RoutineDay createRoutineDay(RoutineDay routineDay) {
        return routineDayRepository.save(routineDay);
    }

    @Override
    public Optional<RoutineDay> updateRoutineDay(RoutineDay routineDay) {
        Long id = routineDay.getId();
        if (id != null && routineDayRepository.existsById(id)) {
            routineDayRepository.save(routineDay);
        }
        return Optional.empty();
    }

    @Override
    public Optional<RoutineDay> partialUpdateRoutineDay(RoutineDay routineDay) {
        Long id = routineDay.getId();
        if (id != null && routineDayRepository.existsById(id)) {
            Optional<RoutineDay> rd = routineDayRepository.findById(id);
            if (routineDay.getWeekDay() != null) rd.get().setWeekDay(routineDay.getWeekDay());
            if (routineDay.getRoutine() != null) rd.get().setRoutine(routineDay.getRoutine());
            if (routineDay.getDayOrder() != null) rd.get().setDayOrder(routineDay.getDayOrder());
            if (routineDay.getAssignedExercises() != null)
                rd.get().setAssignedExercises(routineDay.getAssignedExercises());

            routineDayRepository.save(rd.get());
        }
        return Optional.empty();
    }

    @Override
    public void deleteRoutineDay(Long id) {
        routineDayRepository.deleteById(id);
    }
}