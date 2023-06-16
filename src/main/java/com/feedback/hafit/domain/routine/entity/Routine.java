package com.feedback.hafit.domain.routine.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "routines")
public class Routine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId; // auto_increment

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", foreignKey = @ForeignKey(name = "fk_routine_goal"))
    private Goal goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_routine_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exec_id", foreignKey = @ForeignKey(name = "fk_routine_exec"))
    private Exercise exercise;

    @Column
    private int count; // 개수

    @Column
    private int set; // 세트

    @Column
    private int weight; // 무게

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> repeatDays;

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
