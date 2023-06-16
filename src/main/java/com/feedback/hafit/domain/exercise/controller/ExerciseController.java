package com.feedback.hafit.domain.exercise.controller;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.dto.ExerciseDTO;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import com.feedback.hafit.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("exercise")
@RequiredArgsConstructor
public class ExerciseController { // 운동 컨트롤러

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseService exerciseService;

    @PostMapping("/create") // 운동 추가
    public ExerciseDTO create(@RequestBody ExerciseDTO exerciseDTO, HttpSession session) {
        Long exec_id = (Long) session.getAttribute("exec_id");
        boolean isExerciseCreated = exerciseService.createExercise(exerciseDTO);
        if(!isExerciseCreated) {
            System.out.println("운동 추가 실패");
            return null;
        }
        System.out.println("운동 추가 성공");
        return exerciseDTO;
    }

    @PostMapping("/update") // 운동 수정
    public ExerciseDTO update(@RequestBody ExerciseDTO exerciseDTO) {
        boolean isExerciseUpdated = exerciseService.update(exerciseDTO);
        if (!isExerciseUpdated) {
            System.out.println("운동 수정 성공");
            return null;
        }
        System.out.println("운동 수정 성공");
        return exerciseDTO;
    }

    @DeleteMapping("/delete") // 운동 삭제
    public boolean delete(@RequestBody ExerciseDTO exerciseDTO) {
        boolean isExerciseDeleted = exerciseService.delete(exerciseDTO);
        if(!isExerciseDeleted) {
            System.out.println("삭제 실패");
            return false;
        }
        System.out.println("삭제 성공");
        return true;
    }

    @GetMapping("list") // 운동 목록 조회
    public ResponseEntity<List<ExerciseDTO>> getAllExercise() {
        List<Exercise> exercisesList = exerciseService.getAllExercises();
        List<ExerciseDTO> exerciseDTOList = new ArrayList<>();

        for(Exercise exercise : exercisesList) {
            ExerciseDTO exerciseDTO = new ExerciseDTO();
            exerciseDTO.setExecId(exercise.getExecId());
            exerciseDTO.setExec_img(exercise.getExec_img());
            exerciseDTO.setExec_description(exercise.getExec_description());

            exerciseDTOList.add(exerciseDTO);
        }
        if(!exerciseDTOList.isEmpty()) {
            return ResponseEntity.ok(exerciseDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}