package com.gherex.trainingplanner.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "routine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Basic
    private String name;
    @Setter
    private String goal;
    @Setter
    private String description;
    @Setter
    @Column(name = "duration_weeks")
    private Integer durationWeeks;
    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineDay> routineDays = new ArrayList<>();

    public Routine() {
    }

    public Routine(String name, String goal, String description, Integer durationWeeks, List<RoutineDay> routineDays) {
        this.name = name;
        this.goal = goal;
        this.description = description;
        this.durationWeeks = durationWeeks;
        this.routineDays = routineDays;
    }

    // Métodos helper:

    public void addRoutineDay(RoutineDay day) {
        routineDays.add(day);
        day.setRoutine(this); // asegura consistencia
    }

    public void removeRoutineDay(RoutineDay day) {
        routineDays.remove(day);
        day.setRoutine(null); // opcional
    }

}
