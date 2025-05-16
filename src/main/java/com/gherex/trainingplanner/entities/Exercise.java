package com.gherex.trainingplanner.entities;

import com.gherex.trainingplanner.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "muscle_group")
    private MuscleGroup muscleGroup;
    @Setter
    @Basic
    private String name;
    @Setter
    private String description;
    @Setter
    @Column(name = "image_url")
    private String imageUrl;

    public Exercise() {
    }

    public Exercise(MuscleGroup muscleGroup, String name, String description, String imageUrl) {
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }
}
