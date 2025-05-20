package com.gherex.trainingplanner.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gherex.trainingplanner.enums.WeekDay;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table(name = "routine_days")
public class RoutineDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    private WeekDay weekDay;
    @Setter
    @Basic
    @Column(name = "day_order")
    private Integer dayOrder;
    @Setter
    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    @JsonBackReference // para excluir esta propiedad del JSON
    private Routine routine;
    @Setter
    @OneToMany(mappedBy = "routineDay", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AssignedExercise> assignedExercises = new ArrayList<>();

    public RoutineDay() {
    }

    public RoutineDay(WeekDay weekDay, Integer dayOrder, Routine routine, List<AssignedExercise> assignedExercises) {
        this.weekDay = weekDay;
        this.dayOrder = dayOrder;
        this.routine = routine;
        this.assignedExercises = assignedExercises;
    }

    // Métodos helper:

    public void addAssignedExercise(AssignedExercise ae) {
        assignedExercises.add(ae);
        ae.setRoutineDay(this);
    }

    public void removeAssignedExercise(AssignedExercise ae) {
        assignedExercises.remove(ae);
        ae.setRoutineDay(null); // opcional
    }

}
