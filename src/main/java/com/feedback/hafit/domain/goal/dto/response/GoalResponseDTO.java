package com.feedback.hafit.domain.goal.dto.response;

import com.feedback.hafit.domain.goal.entity.ExerciseKeyword;
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

    private LocalDate goal_target_date;

    private String keyword_name;

    public GoalResponseDTO(Goal goal, ExerciseKeyword keyword) {
        this.goalId = goal.getGoalId();
        this.goal_content = goal.getGoal_content();
        this.goal_target_date = goal.getGoal_target_date();
        this.goal_start_date = goal.getCreatedAt();
        this.keyword_name = keyword.getKeyword_name();
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public void setGoal_content(String goal_content) {
        this.goal_content = goal_content;
    }

    public void setGoal_target_date(LocalDate goal_target_date) {
        this.goal_target_date = goal_target_date;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }

    public void setGoal_start_date(LocalDateTime goal_start_date) {
        this.goal_start_date = goal_start_date;
    }

}
