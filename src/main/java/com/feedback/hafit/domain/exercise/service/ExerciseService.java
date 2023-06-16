package com.feedback.hafit.domain.exercise.service;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseResponseDTO;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new EntityNotFoundException("Exercise not found with id: " + exerciseId));
            exercise.setExercise_description(exerciseRequestDTO.getExercise_description());
            exercise.setExercise_img(exerciseRequestDTO.getExercise_img());
            exercise.setExercise_calorie(exerciseRequestDTO.getExercise_calorie());
            exercise.setExercise_name(exerciseRequestDTO.getExercise_name());
            exerciseRepository.save(exercise);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteExercise(Long exerciseId) {
        try {
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new EntityNotFoundException("Exercise not found with id: " + exerciseId));
            exerciseRepository.delete(exercise);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ExerciseResponseDTO> getAllExercises() {
        try {
            List<Exercise> exercises = exerciseRepository.findAll();
            List<ExerciseResponseDTO> exerciseDTOs = new ArrayList<>();

            for (Exercise exercise : exercises) {
                ExerciseResponseDTO exerciseDTO = new ExerciseResponseDTO(exercise);
                exerciseDTOs.add(exerciseDTO);
            }

            return exerciseDTOs;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}