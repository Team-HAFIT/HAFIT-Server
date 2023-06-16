package com.feedback.hafit.domain.goal.service;

import com.feedback.hafit.domain.goal.dto.request.GoalRequestDTO;
import com.feedback.hafit.domain.goal.dto.response.GoalResponseDTO;
import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.goal.repository.GoalRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoalService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    GoalRepository goalRepository;

    public GoalResponseDTO createGoal(GoalRequestDTO goalRequestDTO, String email) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with Email: " + email));

            Goal goal = new Goal();
            goal.setUser(user);
            goal.setGoal_target_date(goalRequestDTO.getGoal_target_date());
            goal.setGoal_content(goalRequestDTO.getGoal_content());

            Goal savedGoal = goalRepository.save(goal);

            return new GoalResponseDTO(savedGoal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a goal.");
        }
    }


    public List<GoalResponseDTO> getGoalsByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Email: " + email));

        List<Goal> goals = goalRepository.findByUser(user);
        List<GoalResponseDTO> goalResponseDTOs = new ArrayList<>();
        for (Goal goal : goals) {
            GoalResponseDTO goalResponseDTO = new GoalResponseDTO(goal);
            goalResponseDTOs.add(goalResponseDTO);
        }

        return goalResponseDTOs;
    }

    public GoalResponseDTO updateGoal(Long goalId, GoalRequestDTO goalRequestDTO) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with ID: " + goalId));

        // Update the goal properties based on the goalRequestDTO
        goal.setGoal_target_date(goalRequestDTO.getGoal_target_date());
        goal.setGoal_content(goalRequestDTO.getGoal_content());

        Goal updatedGoal = goalRepository.save(goal);

        return new GoalResponseDTO(updatedGoal);
    }

    public boolean deleteGoal(Long goalId) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with ID: " + goalId));

        goalRepository.delete(goal);

        return true;
    }

}
