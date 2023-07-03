package com.feedback.hafit.domain.goal.dto.response;

import com.feedback.hafit.domain.goal.entity.Goal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoalForDdayDTO {
    private Long goalId;
    private String goal_content;
    private String goal_target_date;

    public GoalForDdayDTO(Goal goal, String d_day) {
        this.goalId = goal.getGoalId();
        this.goal_content = goal.getGoal_content();
        this.goal_target_date = d_day;
    }

    public void setGoal_content(String goal_content) {
        this.goal_content = goal_content;
    }

    public void setGoal_target_date(String goal_target_date) {
        this.goal_target_date = goal_target_date;
    }
}
