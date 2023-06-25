package com.feedback.hafit.domain.exercise.dto.response;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class ExerciseForKeywordDTO {
    @Id
    private Long exerciseId; //운동 번호

    private String exerciseName; // 이름

    public ExerciseForKeywordDTO(Exercise exercise) {
        this.exerciseId = exercise.getExerciseId();
        this.exerciseName = exercise.getExerciseName();
    }


}
