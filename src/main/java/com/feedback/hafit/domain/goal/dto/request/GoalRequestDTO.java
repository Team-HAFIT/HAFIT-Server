package com.feedback.hafit.domain.goal.dto.request;

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
    private Long keywordId;

    public void setGoalId(Long goalId) {
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

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }
}
