package com.feedback.hafit.domain.exercise.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class ExerciseRequestDTO {

    @Id
    private Long exerciseId; //운동 번호

    private String exerciseName; // 이름

    private int exerciseCalorie; // 칼로리

    private String exerciseDescription; // 설명

    private String exerciseImg; // 사진

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseCalorie(int exerciseCalorie) {
        this.exerciseCalorie = exerciseCalorie;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setExerciseImg(String exerciseImg) {
        this.exerciseImg = exerciseImg;
    }
}