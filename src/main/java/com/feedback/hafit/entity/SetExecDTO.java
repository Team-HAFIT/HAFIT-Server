package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class SetExecDTO {

    private Long set_id;
    private Long targetCount;
    private Long targetSet;
    private Long weight;
    private Long restTime;

    public Set toEntity() {
        Set set = Set.builder()
                .targetCount(targetCount)
                .targetSet(targetSet)
                .weight(weight)
                .restTime(restTime)
                .build();
        return set;
    }

    @Builder
    public SetExecDTO(
            Long targetCount,
            Long targetSet,
            Long weight,
            Long restTime) {
        this.targetCount = targetCount;
        this.targetSet = targetSet;
        this.weight = weight;
        this.restTime = restTime;
    }
}