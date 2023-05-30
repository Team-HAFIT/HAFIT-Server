package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "`SET`")
@NoArgsConstructor
@AllArgsConstructor
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long set_id; //sequence, auto_increment

    @Column(length = 30)
    private String GroupID;

    @Column(nullable = true)
    private Long weight;

    @Column(nullable = false)
    private Long restTime;

    @Column(nullable = true)
    private Long score;

    @Column(nullable = false)
    private Long targetCount;

    @Column(nullable = true)
    private Long realCount;

    @Column(nullable = false)
    private Long targetSet;

    @Column(nullable = true)
    private Long realSet;

    private LocalDateTime startTime;

    @Column(nullable = false)
    private Long limitTime;

    @Column(nullable = true)
    private Long realTime;

    @Column(nullable = false)
    private Character perform;

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void setStartTime() {
        this.startTime = LocalDateTime.now();
    }

    @Builder
    public Set(String GroupID,
               Long weight,
               Long restTime,
               Long score,
               Long targetCount,
               Long realCount,
               Long targetSet,
               Long realSet,
               LocalDateTime startTime,
               Long limitTime,
               Long realTime,
               Character perform) {
        this.GroupID = GroupID;
        this.weight = weight;
        this.restTime = restTime;
        this.score = score;
        this.targetCount = targetCount;
        this.targetSet = targetSet;
        this.realSet = realSet;
        this.realCount = realCount;
        this.startTime = startTime;
        this.limitTime = limitTime;
        this.realTime = realTime;
        this.perform = perform;
    }

    public SetExecDTO toSetExecDTO() {
        return SetExecDTO.builder()
                .targetCount(this.targetCount)
                .targetSet(this.targetSet)
                .weight(this.weight)
                .restTime(this.restTime)
                .build();
    }
}
