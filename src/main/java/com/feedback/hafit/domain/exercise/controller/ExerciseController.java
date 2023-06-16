package com.feedback.hafit.domain.exercise.controller;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController { // 운동 컨트롤러

    private final ExerciseService exerciseService;

    @PostMapping // 운동 추가
    public ResponseEntity<Boolean> create(@RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        boolean isExerciseCreated = exerciseService.createExercise(exerciseRequestDTO);
        if (isExerciseCreated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{execId}") // 운동 수정
    public ExerciseRequestDTO update(@RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        boolean isExerciseUpdated = exerciseService.update(exerciseRequestDTO);
        if (!isExerciseUpdated) {
            System.out.println("운동 수정 성공");
            return null;
        }
        System.out.println("운동 수정 성공");
        return exerciseRequestDTO;
    }

    @DeleteMapping("{execId}") // 운동 삭제
    public boolean delete(@RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        boolean isExerciseDeleted = exerciseService.delete(exerciseRequestDTO);
        if(!isExerciseDeleted) {
            System.out.println("삭제 실패");
            return false;
        }
        System.out.println("삭제 성공");
        return true;
    }

    @GetMapping // 운동 목록 조회
    public ResponseEntity<List<ExerciseRequestDTO>> getAllExercise() {
        List<Exercise> exercisesList = exerciseService.getAllExercises();
        List<ExerciseRequestDTO> exerciseRequestDTOList = new ArrayList<>();

        for(Exercise exercise : exercisesList) {
            ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
            exerciseRequestDTO.setExecId(exercise.getExecId());
            exerciseRequestDTO.setExec_img(exercise.getExec_img());
            exerciseRequestDTO.setExec_description(exercise.getExec_description());

            exerciseRequestDTOList.add(exerciseRequestDTO);
        }
        if(!exerciseRequestDTOList.isEmpty()) {
            return ResponseEntity.ok(exerciseRequestDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}