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
            Routine savedRoutine = routineRepository.save(routine);
            return Optional.of(savedRoutine);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Routine> partialUpdateRoutine(Routine updatedRoutine) {
        return routineRepository.findById(updatedRoutine.getId())
                .map(existingRoutine -> {
                    // Copiar propiedades simples
                    if (updatedRoutine.getGoal() != null) {
                        existingRoutine.setGoal(updatedRoutine.getGoal());
                    }
                    if (updatedRoutine.getDescription() != null) {
                        existingRoutine.setDescription(updatedRoutine.getDescription());
                    }
                    // Para la colección, limpia y añade los nuevos elementos
                    if (updatedRoutine.getRoutineDays() != null) {
                        existingRoutine.getRoutineDays().clear();
                        existingRoutine.getRoutineDays().addAll(updatedRoutine.getRoutineDays());
                        // Asegúrate de establecer el lado propietario de la relación
                        updatedRoutine.getRoutineDays().forEach(day -> day.setRoutine(existingRoutine));
                    }
                    return routineRepository.save(existingRoutine);
                });
    }

    @Override
    public void deleteRoutine(Long id) {
        routineRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return routineRepository.existsById(id);
    }

}
