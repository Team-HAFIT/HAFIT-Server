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
    private Long plan_target_count; // 목표 갯수

    @Column(name="target_set")
    private Long plan_target_set; // 목표 세트

    @Column(name="weight")
    private Long plan_weight; // 무게

    @Column(name="rest_time")
    private Long plan_rest_time; // 휴식 시간

    // 날짜는 BaseEntity의 createdAt을 사용

    @Column(columnDefinition = "char(1) default 'N'")
    private String plan_perform_status; // 수행 여부

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