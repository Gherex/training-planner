package com.gherex.trainingplanner.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "routines")
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
    @Setter
    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // indica el lado padre
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
