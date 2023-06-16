package com.feedback.hafit.domain.exerciseSet.dto.request;

import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ExerciseSetRequestDTO{

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

    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    @Builder
    public ExerciseSetRequestDTO(ExerciseSet execSet) {
        this.setId = execSet.getSetId();
        this.restTime = execSet.getRestTime();
        this.weight = execSet.getWeight();
        this.score = execSet.getScore();
        this.realCount = execSet.getRealCount();
        this.realSet = execSet.getRealSet();
        this.startTime = execSet.getStartTime();
        this.limitTime = execSet.getLimitTime();
        this.realTime = execSet.getRealTime();
        this.plan = execSet.getPlan().getPlanId();
        this.targetSet = execSet.getPlan().getTargetSet();
        this.createdAt = execSet.getCreatedAt();
        this.modifiedAt = execSet.getModifiedAt();
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public void setRestTime(Long restTime) {
        this.restTime = restTime;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public void setRealCount(Long realCount) {
        this.realCount = realCount;
    }

    public void setRealSet(Long realSet) {
        this.realSet = realSet;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setLimitTime(Long limitTime) {
        this.limitTime = limitTime;
    }

    public void setRealTime(Long realTime) {
        this.realTime = realTime;
    }

    public void setPlan(Long plan) {
        this.plan = plan;
    }

    public void setTargetSet(Long targetSet) {
        this.targetSet = targetSet;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
