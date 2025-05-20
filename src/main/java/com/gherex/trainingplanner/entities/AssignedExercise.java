package com.gherex.trainingplanner.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "assigned_exercises")
public class AssignedExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne
    @JoinColumn(name = "routine_day_id", nullable = false)
    @JsonBackReference
    private RoutineDay routineDay;
    @Setter
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
    @Setter
    @Basic
    private Integer sets;
    @Setter
    private Integer repetitions;

    public AssignedExercise() {
    }

    public AssignedExercise(RoutineDay routineDay, Exercise exercise, Integer sets, Integer repetitions) {
        this.routineDay = routineDay;
        this.exercise = exercise;
        this.sets = sets;
        this.repetitions = repetitions;
    }
}
