package com.feedback.hafit.domain.exercise.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExerciseRequestDTO {

    private Long execId;

    private String name;

    private Long calorie;

    private String exec_description;

    private String exec_img;

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