package com.gherex.trainingplanner.controllers;

import com.gherex.trainingplanner.entities.RoutineDay;
import com.gherex.trainingplanner.services.RoutineDayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dias-rutina")
public class RoutineDayController {

    private final RoutineDayService routineDayService;

    public RoutineDayController(RoutineDayService routineDayService) {
        this.routineDayService = routineDayService;
    }

    @GetMapping
    public ResponseEntity<List<RoutineDay>> getAllRoutineDays() {
        List<RoutineDay> routineDays = routineDayService.getAllRoutineDays();
        return ResponseEntity.ok(routineDays);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineDay> getRoutineDayById(@PathVariable Long id) {
        Optional<RoutineDay> routineDay = routineDayService.getRoutineDayById(id);
        return routineDay.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoutineDay> createRoutineDay(@RequestBody RoutineDay routine) {
        RoutineDay savedRoutineDay = routineDayService.createRoutineDay(routine);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoutineDay);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutineDay(@PathVariable Long id) {
        if (!routineDayService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        routineDayService.deleteRoutineDay(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Optional<RoutineDay>> updateRoutineDay(@RequestBody RoutineDay updatedRoutine) {
        Optional<RoutineDay> savedRoutineDay = routineDayService.updateRoutineDay(updatedRoutine);
        if (savedRoutineDay.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedRoutineDay);
    }

    @PatchMapping
    public ResponseEntity<Optional<RoutineDay>> partialUpdateRoutineDay(@RequestBody RoutineDay updatedRoutine) {
        Optional<RoutineDay> savedRoutineDay = routineDayService.partialUpdateRoutineDay(updatedRoutine);
        if (savedRoutineDay.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(savedRoutineDay);
    }

}