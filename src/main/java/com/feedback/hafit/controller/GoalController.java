package com.feedback.hafit.controller;

import com.feedback.hafit.entity.GoalDTO;
import com.feedback.hafit.repository.GoalRepository;
import com.feedback.hafit.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goal")
public class GoalController {

    @Autowired
    private GoalService goalService;

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
}
