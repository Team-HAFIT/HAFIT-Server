package com.feedback.hafit.domain.exercise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "exercise_body_parts")
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseBodyPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseBodyPartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "fk_exercise_body_parts_exercise"))
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", foreignKey = @ForeignKey(name = "fk_exercise_body_parts_part"))
    private BodyPart bodyPart;

    public void setExerciseBodyPartId(Long exerciseBodyPartId) {
        this.exerciseBodyPartId = exerciseBodyPartId;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }
}