package com.feedback.hafit.dto;


import com.feedback.hafit.domain.Exercise;
import com.feedback.hafit.repository.ExerciseRepository;
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
    private Long exec_id;

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
    public ExerciseDTO(Long exec_id, String name, int calorie, String exec_description, String exec_img) {
        this.exec_id = exec_id;
        this.name = name;
        this.calorie = calorie;
        this.exec_description = exec_description;
        this.exec_img = exec_img;
    }

}
