package com.feedback.hafit.domain.plan.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanRequestDTO {

    private Long userId;
    private Long exerciseId;

    private Long plan_target_count; // 목표 갯수

    private Long plan_target_set; // 목표 세트

    private Long plan_weight; // 무게

    private Long plan_rest_time; // 휴식 시간

    private String plan_performStatus; // 수행 여부

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setPlan_target_count(Long plan_target_count) {
        this.plan_target_count = plan_target_count;
    }

    public void setPlan_target_set(Long plan_target_set) {
        this.plan_target_set = plan_target_set;
    }

    public void setPlan_weight(Long plan_weight) {
        this.plan_weight = plan_weight;
    }

    public void setPlan_rest_time(Long plan_rest_time) {
        this.plan_rest_time = plan_rest_time;
    }

    public void setPlan_performStatus(String plan_performStatus) {
        this.plan_performStatus = plan_performStatus;
    }
}