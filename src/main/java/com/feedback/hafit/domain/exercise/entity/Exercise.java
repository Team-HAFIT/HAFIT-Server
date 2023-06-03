package com.feedback.hafit.domain.exercise.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "exercises")
@NoArgsConstructor
@AllArgsConstructor
public class Exercise extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exec_id; //운동 번호

    @Column
    private String name; // 이름

    @Column
    private int calorie; // 칼로리

    @Column
    private String exec_description; // 설명

    @Column
    private String exec_img; // 사진

    // Plan 참조
    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();

}
