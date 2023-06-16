package com.feedback.hafit.domain.plan.dto.response;

import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlanResponseDTO {

    private Long planId; // 계획 번호
    private Long userId;
    private Long exerciseId;
    private Long plan_target_count; // 목표 갯수

    private Long plan_target_set; // 목표 세트

    private Long plan_weight; // 무게

    private Long plan_rest_time; // 휴식 시간

    private String plan_perform_status; // 수행 여부

    private LocalDateTime plan_date; // 날짜

    public PlanResponseDTO(Plan plan) {
        this.userId = plan.getUser().getUserId();
        this.exerciseId = plan.getExercise().getExerciseId();
        this.plan_target_count = plan.getPlan_target_count();
        this.plan_target_set = plan.getPlan_target_set();
        this.plan_weight = plan.getPlan_weight();
        this.plan_rest_time = plan.getPlan_rest_time();
        this.plan_perform_status = plan.getPlan_perform_status();
        this.plan_date = plan.getCreatedAt();
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

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

    public void setPlan_perform_status(String plan_perform_status) {
        this.plan_perform_status = plan_perform_status;
    }

    public void setPlan_date(LocalDateTime plan_date) {
        this.plan_date = plan_date;
    }
}