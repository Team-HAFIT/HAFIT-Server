package com.feedback.hafit.domain.routine.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.global.enumerate.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "routines")
public class Routine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId; // auto_increment

    @Column
    private int routine_count; // 개수

    @Column
    private int routine_set; // 세트

    @Column
    private int routine_weight; // 무게

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", foreignKey = @ForeignKey(name = "fk_routine_goal"))
    private Goal goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_routine_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "fk_routine_exercise"))
    private Exercise exercise;

    @ElementCollection
    @CollectionTable(name = "routine_repeat_days", joinColumns = @JoinColumn(name = "routine_id"))
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> repeatDays;

}
