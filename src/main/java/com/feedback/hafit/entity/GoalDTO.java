package com.feedback.hafit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feedback.hafit.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class GoalDTO {

    @Autowired
    @JsonIgnore
    UserRepository userRepository;

    private Long goal_id;

    private String goal_content;

    private LocalDate goal_date;

    @JsonIgnore
    private Long user_id;

    @Builder
    public GoalDTO(Long goal_id, String goal_content, LocalDate goal_date, Long user_id) {
        this.goal_id = goal_id;
        this.goal_content = goal_content;
        this.goal_date = goal_date;
        this.user_id = user_id;
    }

    public Goal toEntity() {
        User user = userRepository.findById(user_id).orElse(null);
        Goal goal = Goal.builder()
                .goal_content(goal_content)
                .goal_date(goal_date)
                .user(user)
                .build();
        return goal;
    }

}
