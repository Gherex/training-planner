package com.gherex.trainingplanner.controllers;

import com.gherex.trainingplanner.entities.AssignedExercise;
import com.gherex.trainingplanner.services.AssignedExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ejercicios-asignados")
public class AssignedExerciseController {

    private final AssignedExerciseService assignedExerciseService;

    public AssignedExerciseController(AssignedExerciseService assignedExerciseService) {
        this.assignedExerciseService = assignedExerciseService;
    }

    @GetMapping
    public ResponseEntity<List<AssignedExercise>> getAllAssignedExercises() {
        List<AssignedExercise> assignedExercise = assignedExerciseService.getAllAssignedExercises();
        return ResponseEntity.ok(assignedExercise);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignedExercise> getAssignedExerciseById(@PathVariable Long id) {
        Optional<AssignedExercise> assignedExercise = assignedExerciseService.getAssignedExerciseById(id);
        return assignedExercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AssignedExercise> createAssignedExercise(@RequestBody AssignedExercise assignedExercise) {
        AssignedExercise savedAssignedExercise = assignedExerciseService.createAssignedExercise(assignedExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAssignedExercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignedExercise(@PathVariable Long id) {
        if (!assignedExerciseService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        assignedExerciseService.deleteAssignedExercise(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Optional<AssignedExercise>> updateAssignedExercise(@RequestBody AssignedExercise updatedAssignedExercise) {
        Optional<AssignedExercise> savedAssignedExercise = assignedExerciseService.updateAssignedExercise(updatedAssignedExercise);
        if (savedAssignedExercise.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedAssignedExercise);
    }

    @PatchMapping
    public ResponseEntity<Optional<AssignedExercise>> partialUpdateAssignedExercise(@RequestBody AssignedExercise updatedAssignedExercise) {
        Optional<AssignedExercise> savedAssignedExercise = assignedExerciseService.partialUpdateAssignedExercise(updatedAssignedExercise);
        if (savedAssignedExercise.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedAssignedExercise);
    }

}