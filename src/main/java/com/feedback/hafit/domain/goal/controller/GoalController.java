package com.feedback.hafit.domain.goal.controller;


import com.feedback.hafit.domain.goal.dto.request.GoalRequestDTO;
import com.feedback.hafit.domain.goal.dto.response.GoalResponseDTO;
import com.feedback.hafit.domain.goal.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<Boolean> createGoal(@RequestBody GoalRequestDTO goalRequestDTO, Principal principal) {
        String email = principal.getName();
        boolean createdGoal = goalService.createGoal(goalRequestDTO, email);
        if (createdGoal) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{goalId}")
    public ResponseEntity<GoalResponseDTO> updateGoal(@PathVariable Long goalId, @RequestBody GoalRequestDTO goalRequestDTO) {
        GoalResponseDTO updatedGoal = goalService.updateGoal(goalId, goalRequestDTO);
        if (updatedGoal != null) {
            return ResponseEntity.ok(updatedGoal);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Boolean> deleteGoal(@PathVariable Long goalId) {
        boolean isDeleted = goalService.deleteGoal(goalId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/my")
    public ResponseEntity<List<GoalResponseDTO>> getMyGoals(Principal principal) {
        String email = principal.getName();
        List<GoalResponseDTO> goalResponseDTOs = goalService.getGoalsByUser(email);
        if (goalResponseDTOs != null) {
            return ResponseEntity.ok(goalResponseDTOs);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
