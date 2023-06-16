package com.feedback.hafit.domain.exercise.service;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseResponseDTO;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public boolean createExercise(ExerciseRequestDTO exerciseRequestDTO) {
        try {
            Exercise exercise = Exercise.builder()
                    .exercise_name(exerciseRequestDTO.getExercise_name())
                    .exercise_calorie(exerciseRequestDTO.getExercise_calorie())
                    .exercise_description(exerciseRequestDTO.getExercise_description())
                    .exercise_img(exerciseRequestDTO.getExercise_img())
                    .build();
            exerciseRepository.save(exercise);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateExercise(Long exerciseId, ExerciseRequestDTO exerciseRequestDTO) {
        try {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseId);
            if (optionalExercise.isPresent()) {
                Exercise exercise = optionalExercise.get();
                exercise.setExercise_description(exerciseRequestDTO.getExercise_description());
                exercise.setExercise_img(exerciseRequestDTO.getExercise_img());
                exercise.setExercise_calorie(exerciseRequestDTO.getExercise_calorie());
                exercise.setExercise_name(exerciseRequestDTO.getExercise_name());
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

    public boolean deleteExercise(Long execId) {
        try {
            Optional<Exercise> optionalExercise = exerciseRepository.findById(execId);
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

    public List<ExerciseResponseDTO> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return convertToDTOList(exercises);
    }

    private List<ExerciseResponseDTO> convertToDTOList(List<Exercise> exercises) {
        List<ExerciseResponseDTO> exerciseDTOs = new ArrayList<>();
        for (Exercise exercise : exercises) {
            ExerciseResponseDTO exerciseDTO = new ExerciseResponseDTO();
            exerciseDTO.setExerciseId(exercise.getExerciseId());
            exerciseDTO.setExercise_name(exercise.getExercise_name());
            exerciseDTO.setExercise_calorie(exercise.getExercise_calorie());
            exerciseDTO.setExercise_description(exercise.getExercise_description());
            exerciseDTO.setExercise_img(exercise.getExercise_img());
            exerciseDTOs.add(exerciseDTO);
        }
        return exerciseDTOs;
    }
}