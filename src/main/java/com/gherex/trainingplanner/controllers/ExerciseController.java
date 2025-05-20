package com.gherex.trainingplanner.controllers;

import com.gherex.trainingplanner.entities.Exercise;
import com.gherex.trainingplanner.services.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejercicios")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseService.getExerciseById(id);
        return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise savedExercise = exerciseService.createExercise(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        if (!exerciseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Optional<Exercise>> updateExercise(@RequestBody Exercise updatedExercise) {
        Optional<Exercise> savedExercise = exerciseService.updateExercise(updatedExercise);
        if (savedExercise.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedExercise);
    }

    @PatchMapping
    public ResponseEntity<Optional<Exercise>> partialUpdateExercise(@RequestBody Exercise updatedExercise) {
        Optional<Exercise> savedExercise = exerciseService.partialUpdateExercise(updatedExercise);
        if (savedExercise.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedExercise);
    }

}