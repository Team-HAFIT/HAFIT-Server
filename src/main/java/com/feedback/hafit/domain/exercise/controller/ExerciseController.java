package com.feedback.hafit.domain.exercise.controller;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseResponseDTO;
import com.feedback.hafit.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController { // 운동 컨트롤러

    private final ExerciseService exerciseService;

    @PostMapping // 운동 추가
    public ResponseEntity<Boolean> createExercise(@RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        boolean isExerciseCreated = exerciseService.createExercise(exerciseRequestDTO);
        if (isExerciseCreated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{execId}") // 운동 수정
    public ResponseEntity<Boolean> updateExercise(@PathVariable Long execId, @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        boolean isExerciseUpdated = exerciseService.updateExercise(execId, exerciseRequestDTO);
        if (isExerciseUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{execId}") // 운동 삭제
    public ResponseEntity<Boolean> deleteExercise(@PathVariable Long execId) {
        boolean isExerciseDeleted = exerciseService.deleteExercise(execId);
        if(isExerciseDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> getAllExercises() {
        List<ExerciseResponseDTO> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok(exercises);
    }
}