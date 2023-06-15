package com.feedback.hafit.domain.exerciseSet.controller;

import com.feedback.hafit.domain.exerciseSet.dto.ExerciseSetDTO;
import com.feedback.hafit.domain.exerciseSet.service.ExerciseSetService;
import com.feedback.hafit.domain.post.dto.response.PostWithLikesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/sets")
@RequiredArgsConstructor
public class ExerciseSetController {

    @Autowired
    private ExerciseSetService exerciseSetService;

    @PostMapping("")
    public ResponseEntity<ExerciseSetDTO> exec(@RequestBody ExerciseSetDTO exerciseSetDTO, Principal principal) {
        return ResponseEntity.ok(exerciseSetService.save(exerciseSetDTO, principal.getName()));
    }

    @GetMapping("/{planId}") // 하나의 계획에 해당하는 운동 기록 조회
    public ResponseEntity<List<ExerciseSetDTO>> getPlan(@PathVariable Long planId, Principal principal) {
        return ResponseEntity.ok(exerciseSetService.getByPlanId(planId, principal.getName()));
    }

    @GetMapping("") // 하나의 계획에 해당하는 운동 기록 조회
    public ResponseEntity<List<ExerciseSetDTO>> getAllPlan(Principal principal) {
        return ResponseEntity.ok(exerciseSetService.getAllSets(principal.getName()));
    }
}