package com.feedback.hafit.domain.goal.controller;


import com.feedback.hafit.domain.goal.dto.request.GoalRequestDTO;
import com.feedback.hafit.domain.goal.dto.response.GoalResponseDTO;
import com.feedback.hafit.domain.goal.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @PostMapping("/goals")
    public void createGoal(@RequestBody GoalRequestDTO goalRequestDTO, Principal principal) {
        goalService.createGoal(goalRequestDTO, principal.getName());
    }

    @PutMapping("/{goalId}")
    public void updateGoal(@PathVariable Long goalId, @RequestBody GoalRequestDTO goalRequestDTO) {
        goalService.updateGoal(goalId, goalRequestDTO);
    }

    @DeleteMapping("/{goalId}")
    public void deleteGoal(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);
    }

    @GetMapping("/all")
    public List<GoalResponseDTO> getMyGoals(Principal principal) {
        return goalService.getGoalsByUser(principal.getName());
    }

}
