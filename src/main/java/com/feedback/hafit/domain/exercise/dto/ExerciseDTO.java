package com.feedback.hafit.domain.exercise.dto;


import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseDTO {

    @Autowired
    ExerciseRepository exerciseRepository;
    private Long execId;

    private String name;

    private int calorie;

    private String exec_description;

    private String exec_img;

    public Exercise toEntity() {
        Exercise exercise = Exercise.builder()
                .name(name)
                .calorie(calorie)
                .exec_description(exec_description)
                .exec_img(exec_img)
                .build();
        return exercise;
    }

    @Builder
    public ExerciseDTO(Long execId, String name, int calorie, String exec_description, String exec_img) {
        this.execId = execId;
        this.name = name;
        this.calorie = calorie;
        this.exec_description = exec_description;
        this.exec_img = exec_img;
    }

}
