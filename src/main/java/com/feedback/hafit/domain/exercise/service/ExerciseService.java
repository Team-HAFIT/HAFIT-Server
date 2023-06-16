package com.feedback.hafit.domain.exercise.service;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.dto.ExerciseDTO;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    @PreAuthorize("hasRole('ADMIN')") // Security 작업에서 Admin 역할인 사람만 운동 추가, 어노테이션
    public boolean createExercise(ExerciseDTO exerciseDTO) {
        try {
            Exercise exercise = Exercise.builder()
                    .exec_id(exerciseDTO.getExec_id())
                    .exec_img(exerciseDTO.getExec_img())
                    .exec_description(exerciseDTO.getExec_description())
                    .build(); // Security 작업이 완성되지 않아 오류 뜸,,,
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ExerciseDTO exerciseDTO) {
        try {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseDTO.getExec_id());
            if(optionalExercise.isPresent()) {
                Exercise exercise = optionalExercise.get();
                exercise.setExec_description(exerciseDTO.getExec_description());
                exercise.setExec_img(exerciseDTO.getExec_img());
                exerciseRepository.save(exercise);
                return true;
            } else {
                System.out.println("해당하는 운동을 찾을 수 없습니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(ExerciseDTO exerciseDTO) {
        try {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseDTO.getExec_id());
            if(optionalExercise.isPresent()) {
                Exercise exercise = optionalExercise.get();
                exerciseRepository.delete(exercise);
                return true;
            } else {
                System.out.println("해당하는 운동을 삭제할 수 없습니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Exercise> getAllExercises() {
        try {
            return exerciseRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}