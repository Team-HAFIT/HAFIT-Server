package com.feedback.hafit.domain.exercise.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "exercises")
@NoArgsConstructor
@AllArgsConstructor
public class Exercise extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId; //운동 번호

    @Column
    private String exercise_name; // 이름

    @Column
    private Long exercise_calorie; // 칼로리

    @Column
    private String exercise_description; // 설명

    @Column
    private String exercise_img; // 사진

    // Plan 참조
    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public void setExercise_calorie(Long exercise_calorie) {
        this.exercise_calorie = exercise_calorie;
    }

    public void setExercise_description(String exercise_description) {
        this.exercise_description = exercise_description;
    }

    public void setExercise_img(String exercise_img) {
        this.exercise_img = exercise_img;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
