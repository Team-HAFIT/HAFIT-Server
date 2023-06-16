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
    public ResponseEntity<GoalResponseDTO> createWrite(@RequestBody GoalRequestDTO goalRequestDTO, Principal principal) {
        String email = principal.getName();
        log.info(String.valueOf(goalRequestDTO.getKeywordId()));
        GoalResponseDTO createdGoal = goalService.createGoal(goalRequestDTO, email);
        if (createdGoal != null) {
            return ResponseEntity.ok(createdGoal);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{goalId}")
    public GoalResponseDTO updateGoal(@PathVariable Long goalId, @RequestBody GoalRequestDTO goalRequestDTO) {
        return goalService.updateGoal(goalId, goalRequestDTO);
    }

    @DeleteMapping("/{goalId}")
    public boolean deleteGoal(@PathVariable Long goalId) {
        return goalService.deleteGoal(goalId);
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
