package com.feedback.hafit.domain.plan.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Builder
@Table(name = "plans")
@NoArgsConstructor
@AllArgsConstructor
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId; // 계획 번호

    @Column(name="target_count")
    private Long targetCount; // 목표 갯수

    @Column(name="target_set")
    private Long targetSet; // 목표 세트

    @Column(name="weight")
    private Long weight; // 무게

    @Column(name="rest_time")
    private Long restTime; // 휴식 시간

    // 날짜는 BaseEntity의 createdAt을 사용

    @Column(columnDefinition = "char(1) default 'N'")
    private String performStatus; // 수행 여부

    // ExerciseSet 참조
    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
    private List<ExerciseSet> exerciseSets = new ArrayList<>();

    // User 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", foreignKey = @ForeignKey(name="fk_plan_user"))
    private User user;

    // Exercise 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "fk_plan_exercise"))
    private Exercise exercise;

    public void setPlanId(Long planId) {
        this.planId = planId;
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

    public void setExerciseSets(List<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}