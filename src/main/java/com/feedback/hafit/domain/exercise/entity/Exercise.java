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
    private String exerciseName; // 이름

    @Column
    private int exerciseCalorie; // 칼로리

    @Column
    private String exerciseDescription; // 설명

    @Column
    private String exerciseImg; // 사진

    // Plan 참조
    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();


    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseCalorie(int exerciseCalorie) {
        this.exerciseCalorie = exerciseCalorie;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setExerciseImg(String exerciseImg) {
        this.exerciseImg = exerciseImg;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }
}
