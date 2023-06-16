package com.feedback.hafit.domain.exerciseSet.controller;

import com.feedback.hafit.domain.exerciseSet.dto.ExerciseSetDTO;
import com.feedback.hafit.domain.exerciseSet.service.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/sets")
@RequiredArgsConstructor
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    @PostMapping("/exec") // 운동 종료 후 저장 및 반환
    public ResponseEntity<ExerciseSetDTO> endExec(@RequestBody ExerciseSetDTO dto, Principal principal) {
        return ResponseEntity.ok(exerciseSetService.save(dto, principal.getName()));
    }

    @PutMapping("/rest") // 휴식 시간 종료 후 휴식 시간 저장 및 반환
    public ResponseEntity<ExerciseSetDTO> endRest(@RequestBody ExerciseSetDTO dto, Principal principal) {
        return ResponseEntity.ok(exerciseSetService.update(dto, principal.getName()));
    }

    @GetMapping("/{planId}") // 하나의 계획에 해당하는 모든 운동 기록 조회
    public ResponseEntity<List<ExerciseSetDTO>> getPlan(@PathVariable Long planId, Principal principal) {
        return ResponseEntity.ok(exerciseSetService.getByPlanId(planId, principal.getName()));
    }

    @GetMapping // 모든 운동 기록 조회
    public ResponseEntity<List<ExerciseSetDTO>> getAllPlan(Principal principal) {
        return ResponseEntity.ok(exerciseSetService.getAllSets(principal.getName()));
    }
}