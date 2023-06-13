package com.feedback.hafit.domain.plan.dto;

import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PlanDTO {

    private Long userId;

    private Long execId;

    private Long targetCount;

    private Long targetSet;

    private Long weight;

    private Long restTime;

    private String performStatus;

    @Builder
    public PlanDTO(Plan plan) {
        this.userId = plan.getUser().getUserId();
        this.execId = plan.getExercise().getExec_id();
        this.targetCount = plan.getTargetCount();
        this.targetSet = plan.getTargetSet();
        this.weight = plan.getWeight();
        this.restTime = plan.getRestTime();
        this.performStatus = plan.getPerformStatus();
    }
}