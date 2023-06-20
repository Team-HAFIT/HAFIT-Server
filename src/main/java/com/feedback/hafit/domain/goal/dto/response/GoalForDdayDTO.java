package com.feedback.hafit.domain.goal.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoalForDdayDTO {
    private String goal_content;
    private long d_day;

    public GoalForDdayDTO(String goal_content, long d_day) {
        this.goal_content = goal_content;
        this.d_day = d_day;
    }

    public void setGoal_content(String goal_content) {
        this.goal_content = goal_content;
    }

    public void setD_day(long d_day) {
        this.d_day = d_day;
    }
}
