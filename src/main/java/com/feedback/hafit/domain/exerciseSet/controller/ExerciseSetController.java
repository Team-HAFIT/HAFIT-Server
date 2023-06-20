package com.feedback.hafit.domain.exerciseSet.controller;

import com.feedback.hafit.domain.exerciseSet.dto.request.ExerciseSetRequestDTO;
import com.feedback.hafit.domain.exerciseSet.dto.response.ExerciseSetResponseDTO;
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

    @PostMapping // 운동 종료 후 저장 및 반환
    public ResponseEntity<ExerciseSetResponseDTO> endExec(@RequestBody ExerciseSetRequestDTO dto) {
        return ResponseEntity.ok(exerciseSetService.save(dto));
    }

    @PutMapping("/rest") // 휴식 시간 종료 후 휴식 시간 저장 및 반환
    public ResponseEntity<ExerciseSetResponseDTO> endRest(@RequestBody ExerciseSetRequestDTO dto) {
        return ResponseEntity.ok(exerciseSetService.update(dto));
    }

    @GetMapping("/all/{planId}") // 하나의 계획에 해당하는 모든 운동 기록 조회
    public ResponseEntity<List<ExerciseSetResponseDTO>> getPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(exerciseSetService.getByPlanId(planId));
    }

    @GetMapping("/{planId}") // 이전 운동 세트의 정보 조회
    public ResponseEntity<ExerciseSetResponseDTO> getLastestPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(exerciseSetService.getLastestPlan(planId));
    }

    @GetMapping // 모든 운동 기록 조회
    public ResponseEntity<List<ExerciseSetResponseDTO>> getAllPlan() {
        return ResponseEntity.ok(exerciseSetService.getAllSets());
    }
}