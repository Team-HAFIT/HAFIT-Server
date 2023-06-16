package com.feedback.hafit.domain.exerciseSet.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "sets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet extends BaseEntity {

    // 세트 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long setId;

    // 실제 휴식한 시간
    @Column(name="rest_time")
    private Long restTime;

    // 실제 든 무게
    @Column
    private Long weight;

    // 점수
    @Column
    private Long score;

    // 실제 반복 갯수
    @Column(name = "real_count")
    private Long realCount;

    // 현재 누적 세트 수
    @Column(name = "real_set")
    private Long realSet;

    // 시작 시간
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    // 제한 시간
    @Column(name = "limit_time")
    private Long limitTime;

    // 소요 시간
    @Column(name="real_time")
    private Long realTime;

    // 계획 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", foreignKey = @ForeignKey(name = "fk_set_plan"))
    private Plan plan;

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

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
