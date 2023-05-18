package com.feedback.hafit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "plans")
@NoArgsConstructor
@AllArgsConstructor
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plan_id;

    @Column(name = "targettime")
    private int targetTime;

    @Column(name = "acctime")
    private int accTime;

    // Goal 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", foreignKey = @ForeignKey(name = "fk_plan_goal"))
    private Goal goal;

    // Exercise 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exec_id", foreignKey = @ForeignKey(name = "fk_plan_exec"))
    private Exercise exercise;

    // Set 참조
    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY)
    private List<Set> sets = new ArrayList<>();
}
