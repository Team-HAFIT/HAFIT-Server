package com.feedback.hafit.domain.goal.service;

import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.domain.goal.dto.GoalDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.goal.repository.GoalRepository;
import com.feedback.hafit.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GoalService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    GoalRepository goalRepository;

    public boolean write(GoalDTO goalDTO, Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return false;
        }

        LocalDate goalDate = goalDTO.getGoal_date();
        String goalContent = goalDTO.getGoal_content();

        Goal goal = new Goal();
        goal.setUser(user);
        goal.setGoal_date(goalDate);
        goal.setGoal_content(goalContent);

        goalRepository.save(goal);

        return true;
    }

    public List<Goal> getGoalsByUser(User user) {
        return goalRepository.findByUser(user);
    }
}
