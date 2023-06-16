package com.feedback.hafit.domain.exercise.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExerciseResponseDTO {

    private Long execId;

    private String name;

    private Long calorie;

    private String exec_description;

    private String exec_img;

    @Builder
    public ExerciseResponseDTO(Long execId, String name, Long calorie, String exec_description, String exec_img) {
        this.execId = execId;
        this.name = name;
        this.calorie = calorie;
        this.exec_description = exec_description;
        this.exec_img = exec_img;
    }

    public void setExecId(Long execId) {
        this.execId = execId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalorie(Long calorie) {
        this.calorie = calorie;
    }

    public void setExec_description(String exec_description) {
        this.exec_description = exec_description;
    }

    public void setExec_img(String exec_img) {
        this.exec_img = exec_img;
    }
}