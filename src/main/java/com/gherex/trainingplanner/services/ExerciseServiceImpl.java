package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.Exercise;
import com.gherex.trainingplanner.entities.RoutineDay;
import com.gherex.trainingplanner.repositories.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();

    }

    @Override
    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Optional<Exercise> updateExercise(Exercise exercise) {
        Long id = exercise.getId();
        if (id != null && exerciseRepository.existsById(id)) {
            Exercise savedExercise = exerciseRepository.save(exercise);
            return Optional.of(savedExercise);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Exercise> partialUpdateExercise(Exercise exercise) {
        Long id = exercise.getId();
        if (id != null && exerciseRepository.existsById(id)) {
            Optional<Exercise> e = exerciseRepository.findById(id);
            if (exercise.getMuscleGroup() != null) e.get().setMuscleGroup(exercise.getMuscleGroup());
            if (exercise.getName() != null) e.get().setName(exercise.getName());
            if (exercise.getDescription() != null) e.get().setDescription(exercise.getDescription());
            if (exercise.getImageUrl() != null) e.get().setImageUrl(exercise.getImageUrl());

            Exercise savedExercise = exerciseRepository.save(e.get());
            return Optional.of(savedExercise);
        }
        return Optional.empty();
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return exerciseRepository.existsById(id);
    }
}