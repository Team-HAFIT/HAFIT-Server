package com.feedback.hafit.domain.exercise.service;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public boolean createExercise(ExerciseRequestDTO exerciseRequestDTO) {
        try {
            Exercise exercise = Exercise.builder()
                    .name(exerciseRequestDTO.getName())
                    .calorie(exerciseRequestDTO.getCalorie())
                    .exec_description(exerciseRequestDTO.getExec_description())
                    .exec_img(exerciseRequestDTO.getExec_img())
                    .build();
            exerciseRepository.save(exercise);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(ExerciseRequestDTO exerciseRequestDTO) {
        try {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseRequestDTO.getExecId());
            if(optionalExercise.isPresent()) {
                Exercise exercise = optionalExercise.get();
                exercise.setExec_description(exerciseRequestDTO.getExec_description());
                exercise.setExec_img(exerciseRequestDTO.getExec_img());
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

    public boolean delete(ExerciseRequestDTO exerciseRequestDTO) {
        try {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseRequestDTO.getExecId());
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