package com.gherex.trainingplanner.controllers;

import com.gherex.trainingplanner.entities.Routine;
import com.gherex.trainingplanner.services.RoutineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rutinas")
public class RoutineController {

    private final RoutineService routineService;

    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping
    public ResponseEntity<List<Routine>> getAllRoutines() {
        List<Routine> routines = routineService.getAllRoutines();
        return ResponseEntity.ok(routines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable Long id) {
        Optional<Routine> routine = routineService.getRoutineById(id);
        return routine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Routine> createRoutine(@RequestBody Routine routine) {
        Routine savedRoutine = routineService.createRoutine(routine);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoutine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        if (!routineService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Optional<Routine>> updateRoutine(@RequestBody Routine updatedRoutine) {
        Optional<Routine> savedRoutine = routineService.updateRoutine(updatedRoutine);
        if (savedRoutine.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedRoutine);
    }

    @PatchMapping
    public ResponseEntity<Optional<Routine>> partialUpdateRoutine(@RequestBody Routine updatedRoutine) {
        Optional<Routine> savedRoutine = routineService.partialUpdateRoutine(updatedRoutine);
        if (savedRoutine.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedRoutine);
    }

}
