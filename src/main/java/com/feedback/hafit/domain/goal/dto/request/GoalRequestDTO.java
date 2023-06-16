package com.feedback.hafit.domain.goal.dto.request;

import com.feedback.hafit.domain.goal.entity.Goal;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GoalRequestDTO {

    private Long goalId;

    private String goal_content;

    private LocalDate goal_target_date;

    private String userEmail;

    public GoalRequestDTO(Goal goal) {
        this.goalId = goal.getGoalId();
        this.goal_content = goal.getGoal_content();
        this.goal_target_date = goal.getGoal_target_date();
        this.userEmail = goal.getUser().getEmail();
    }

    public void setGoal_id(Long goalId) {
        this.goalId = goalId;
    }

    public void setGoal_content(String goal_content) {
        this.goal_content = goal_content;
    }

    public void setGoal_target_date(LocalDate goal_target_date) {
        this.goal_target_date = goal_target_date;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
