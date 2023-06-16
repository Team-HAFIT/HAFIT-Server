package com.feedback.hafit.domain.goal.dto.response;

import com.feedback.hafit.domain.goal.entity.Goal;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GoalResponseDTO {

    private Long goalId;

    private String goal_content;
    private LocalDateTime goal_start_date;

    private LocalDate goal_date;

    private String userEmail;

    public GoalResponseDTO(Goal goal) {
        this.goalId = goal.getGoalId();
        this.goal_content = goal.getGoal_content();
        this.goal_date = goal.getGoal_date();
        this.goal_start_date = goal.getCreatedAt();
        this.userEmail = goal.getUser().getEmail();
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public void setGoal_content(String goal_content) {
        this.goal_content = goal_content;
    }

    public void setGoal_date(LocalDate goal_date) {
        this.goal_date = goal_date;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
