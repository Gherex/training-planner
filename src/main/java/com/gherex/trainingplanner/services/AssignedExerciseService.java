package com.gherex.trainingplanner.services;

import com.gherex.trainingplanner.entities.AssignedExercise;

import java.util.List;
import java.util.Optional;

public interface AssignedExerciseService {

    public List<AssignedExercise> getAllAssignedExercises();

    public Optional<AssignedExercise> getAssignedExerciseById(Long id);

    public AssignedExercise createAssignedExercise(AssignedExercise assignedExercise);

    public Optional<AssignedExercise> updateAssignedExercise(AssignedExercise assignedExercise);

    public Optional<AssignedExercise> partialUpdateAssignedExercise(AssignedExercise assignedExercise);

    public void deleteAssignedExercise(Long id);

}