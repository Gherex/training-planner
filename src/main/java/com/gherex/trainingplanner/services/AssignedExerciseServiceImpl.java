package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.AssignedExercise;
import com.gherex.trainingplanner.entities.Exercise;
import com.gherex.trainingplanner.repositories.AssignedExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignedExerciseServiceImpl implements AssignedExerciseService {

    private final AssignedExerciseRepository assignedExerciseRepository;

    public AssignedExerciseServiceImpl(AssignedExerciseRepository assignedExerciseRepository) {
        this.assignedExerciseRepository = assignedExerciseRepository;
    }

    @Override
    public List<AssignedExercise> getAllAssignedExercises() {
        return assignedExerciseRepository.findAll();

    }

    @Override
    public Optional<AssignedExercise> getAssignedExerciseById(Long id) {
        return assignedExerciseRepository.findById(id);
    }

    @Override
    public AssignedExercise createAssignedExercise(AssignedExercise assignedExercise) {
        return assignedExerciseRepository.save(assignedExercise);
    }

    @Override
    public Optional<AssignedExercise> updateAssignedExercise(AssignedExercise assignedExercise) {
        Long id = assignedExercise.getId();
        if (id != null && assignedExerciseRepository.existsById(id)) {
            AssignedExercise savedAssignedExercise = assignedExerciseRepository.save(assignedExercise);
            return Optional.of(savedAssignedExercise);
        }
        return Optional.empty();
    }

    @Override
    public Optional<AssignedExercise> partialUpdateAssignedExercise(AssignedExercise assignedExercise) {
        Long id = assignedExercise.getId();
        if (id != null && assignedExerciseRepository.existsById(id)) {
            Optional<AssignedExercise> ae = assignedExerciseRepository.findById(id);
            if (assignedExercise.getRoutineDay() != null) ae.get().setRoutineDay(assignedExercise.getRoutineDay());
            if (assignedExercise.getExercise() != null) ae.get().setExercise(assignedExercise.getExercise());
            if (assignedExercise.getSets() != null) ae.get().setSets(assignedExercise.getSets());
            if (assignedExercise.getRepetitions() != null) ae.get().setRepetitions(assignedExercise.getRepetitions());

            AssignedExercise savedAssignedExercise = assignedExerciseRepository.save(ae.get());
            return Optional.of(savedAssignedExercise);
        }
        return Optional.empty();
    }

    @Override
    public void deleteAssignedExercise(Long id) {
        assignedExerciseRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return assignedExerciseRepository.existsById(id);
    }
}