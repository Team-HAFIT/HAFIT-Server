package com.feedback.hafit.domain.exercise.dto.response;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class ExerciseResponseDTO {

    @Id
    private Long exerciseId; //운동 번호

    private String exercise_name; // 이름

    private Long exercise_calorie; // 칼로리

    private String exercise_description; // 설명

    private String exercise_img; // 사진

    public ExerciseResponseDTO(Exercise exercise) {
        this.exerciseId = exercise.getExerciseId();
        this.exercise_name = exercise.getExercise_name();
        this.exercise_calorie = exercise.getExercise_calorie();
        this.exercise_description = exercise.getExercise_description();
        this.exercise_img = exercise.getExercise_img();
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public void setExercise_calorie(Long exercise_calorie) {
        this.exercise_calorie = exercise_calorie;
    }

    public void setExercise_description(String exercise_description) {
        this.exercise_description = exercise_description;
    }

    public void setExercise_img(String exercise_img) {
        this.exercise_img = exercise_img;
    }
}