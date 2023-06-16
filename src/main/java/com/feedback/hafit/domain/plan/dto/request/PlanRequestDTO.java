package com.feedback.hafit.domain.plan.dto.request;

import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanRequestDTO {

    private Long userId;

    private Long execId;

    private Long targetCount;

    private Long targetSet;

    private Long weight;

    private Long restTime;

    private String performStatus;

    public PlanRequestDTO(Plan plan) {
        this.userId = plan.getUser().getUserId();
        this.execId = plan.getExercise().getExecId();
        this.targetCount = plan.getTargetCount();
        this.targetSet = plan.getTargetSet();
        this.weight = plan.getWeight();
        this.restTime = plan.getRestTime();
        this.performStatus = plan.getPerformStatus();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setExecId(Long execId) {
        this.execId = execId;
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