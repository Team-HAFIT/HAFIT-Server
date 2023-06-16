package com.feedback.hafit.domain.plan.dto.response;

import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanResponseDTO {

    private Long userId;

    private Long exerciseId;

    private Long targetCount;

    private Long targetSet;

    private Long weight;

    private Long restTime;

    private String performStatus;

    public PlanResponseDTO(Plan plan) {
        this.userId = plan.getUser().getUserId();
        this.exerciseId = plan.getExercise().getExerciseId();
        this.targetCount = plan.getTargetCount();
        this.targetSet = plan.getTargetSet();
        this.weight = plan.getWeight();
        this.restTime = plan.getRestTime();
        this.performStatus = plan.getPerformStatus();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setTargetCount(Long targetCount) {
        this.targetCount = targetCount;
    }

    public void setTargetSet(Long targetSet) {
        this.targetSet = targetSet;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void setRestTime(Long restTime) {
        this.restTime = restTime;
    }

    public void setPerformStatus(String performStatus) {
        this.performStatus = performStatus;
    }
}