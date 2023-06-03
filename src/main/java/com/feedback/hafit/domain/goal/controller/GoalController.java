package com.feedback.hafit.domain.goal.controller;

import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.goal.repository.GoalRepository;
import com.feedback.hafit.domain.goal.service.GoalService;
import com.feedback.hafit.domain.goal.dto.GoalDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoalRepository goalRepository;

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody GoalDTO goalDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        boolean result = goalService.write(goalDTO, userId);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/read")
    public List<GoalDTO> getUserGoals(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        List<Goal> goals = goalRepository.findByUser(user);
        return goals.stream()
                .map(goal -> GoalDTO.builder()
                        .goal_id(goal.getGoal_id())
                        .goal_content(goal.getGoal_content())
                        .goal_date(goal.getGoal_date())
                        .build())
                .collect(Collectors.toList());
    }

}
