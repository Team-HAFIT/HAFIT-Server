package com.feedback.hafit.domain.exercise.service;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseResponseDTO;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public void createExercise(ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = Exercise.builder()
                .exercise_name(exerciseRequestDTO.getExercise_name())
                .exercise_calorie(exerciseRequestDTO.getExercise_calorie())
                .exercise_description(exerciseRequestDTO.getExercise_description())
                .exercise_img(exerciseRequestDTO.getExercise_img())
                .build();
        exerciseRepository.save(exercise);
    }

    public void updateExercise(Long exerciseId, ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found with id: " + exerciseId));
        exercise.setExercise_description(exerciseRequestDTO.getExercise_description());
        exercise.setExercise_img(exerciseRequestDTO.getExercise_img());
        exercise.setExercise_calorie(exerciseRequestDTO.getExercise_calorie());
        exercise.setExercise_name(exerciseRequestDTO.getExercise_name());
        exerciseRepository.save(exercise);
    }


    public void deleteExercise(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found with id: " + exerciseId));
        exerciseRepository.delete(exercise);
    }

    public List<ExerciseResponseDTO> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        List<ExerciseResponseDTO> exerciseDTOs = new ArrayList<>();

        for (Exercise exercise : exercises) {
            ExerciseResponseDTO exerciseDTO = new ExerciseResponseDTO(exercise);
            exerciseDTOs.add(exerciseDTO);
        }

        return exerciseDTOs;
    }

}