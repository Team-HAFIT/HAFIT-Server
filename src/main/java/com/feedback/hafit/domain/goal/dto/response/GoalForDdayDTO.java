package com.feedback.hafit.domain.goal.dto.response;

import com.feedback.hafit.domain.goal.entity.Goal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoalForDdayDTO {
    private Long goalId;
    private String goal_content;
    private long d_day;

    public GoalForDdayDTO(Goal goal, long d_day) {
        this.goalId = goal.getGoalId();
        this.goal_content = goal.getGoal_content();
        this.d_day = d_day;
    }

    public void setGoal_content(String goal_content) {
        this.goal_content = goal_content;
    }

    public void setD_day(long d_day) {
        this.d_day = d_day;
    }
}
