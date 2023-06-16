package com.feedback.hafit.domain.exerciseSet.dto;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseSetDTO extends BaseEntity {

    // 세트 번호
    private Long setId;

    // 실제 휴식한 시간
    private Long restTime;

    // 실제 든 무게
    private Long weight;

    // 점수
    private Long score;

    // 실제 반복 갯수
    private Long realCount;

    // 현재 누적 세트 수
    private Long realSet;

    // 시작 시간
    private LocalDateTime startTime;

    // 제한 시간
    private Long limitTime;

    // 소요 시간
    private Long realTime;

    // 계획 번호
    private Long plan;

    // 목표 세트
    private Long targetSet;

    @Builder
    public ExerciseSetDTO(ExerciseSet execSet) {
        this.setId = execSet.getSetId();
        this.restTime = execSet.getRestTime();
        this.weight = execSet.getWeight();
        this.score = execSet.getScore();
        this.realCount = execSet.getRealCount();
        this.realSet = execSet.getRealSet();
        this.startTime = execSet.getStartTime();
        this.limitTime = execSet.getLimitTime();
        this.realTime = execSet.getRealTime();
        this.plan = execSet.getPlan().getPlan_id();
        this.targetSet = execSet.getPlan().getTargetSet();
        this.createdAt = execSet.getCreatedAt();
        this.modifiedAt = execSet.getModifiedAt();
    }
}
