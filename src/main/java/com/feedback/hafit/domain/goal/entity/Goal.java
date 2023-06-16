package com.feedback.hafit.domain.goal.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "goals")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal extends BaseEntity {
    // 운동 목표 엔티티
    // 시작일은 BaseEntity의 createdAt(goal_start_date)을 사용

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long goalId; // 목표 번호

    @Column(length = 300)
    private String goal_content; // 목표 내용

    private LocalDate goal_target_date; // 종료일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_goal_user"))
    private User user; // 회원 번호

    @OneToOne
    @JoinColumn(name = "exercise_keyword_id")
    private ExerciseKeyword exerciseKeyword;

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public void setGoal_content(String goal_content) {
        this.goal_content = goal_content;
    }

    public void setGoal_target_date(LocalDate goal_target_date) {
        this.goal_target_date = goal_target_date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExerciseKeyword(ExerciseKeyword exerciseKeyword) {
        this.exerciseKeyword = exerciseKeyword;
    }
}
