package com.feedback.hafit.domain.exercise.controller;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseForKeywordDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseResponseDTO;
import com.feedback.hafit.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController { // 운동 컨트롤러

    private final ExerciseService exerciseService;

    @PostMapping // 운동 추가
    public void createExercise(@RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        exerciseService.createExercise(exerciseRequestDTO);
    }

    @PutMapping("/{exerciseId}") // 운동 수정
    public void updateExercise(@PathVariable Long exerciseId, @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        exerciseService.updateExercise(exerciseId, exerciseRequestDTO);
    }

    @DeleteMapping("/{exerciseId}") // 운동 삭제
    public void deleteExercise(@PathVariable Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
    }

    @GetMapping
    public List<ExerciseResponseDTO> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/keyword/{keywordId}")
    public List<ExerciseForKeywordDTO> getGroupsByKeywordId(@PathVariable Long keywordId) {
        return exerciseService.getGroupsByKeywordId(keywordId);
    }
}