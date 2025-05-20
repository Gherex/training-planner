package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    public List<Exercise> getAllExercises();

    public Optional<Exercise> getExerciseById(Long id);

    public Exercise createExercise(Exercise exercise);

    public Optional<Exercise> updateExercise(Exercise exercise);

    public Optional<Exercise> partialUpdateExercise(Exercise exercise);

    public void deleteExercise(Long id);

    boolean existsById(Long id);
}