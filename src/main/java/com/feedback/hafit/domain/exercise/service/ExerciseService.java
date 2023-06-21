package com.feedback.hafit.domain.exercise.service;

import com.feedback.hafit.domain.exercise.dto.request.ExerciseRequestDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseForKeywordDTO;
import com.feedback.hafit.domain.exercise.dto.response.ExerciseResponseDTO;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseKeywordRepository;
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
    private final ExerciseKeywordRepository exerciseKeywordRepository;

    public void createExercise(ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = Exercise.builder()
                .exerciseName(exerciseRequestDTO.getExerciseName())
                .exerciseCalorie(exerciseRequestDTO.getExerciseCalorie())
                .exerciseDescription(exerciseRequestDTO.getExerciseDescription())
                .exerciseImg(exerciseRequestDTO.getExerciseImg())
                .build();
        exerciseRepository.save(exercise);
    }

    public void updateExercise(Long exerciseId, ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found with id: " + exerciseId));
        exercise.setExerciseDescription(exerciseRequestDTO.getExerciseDescription());
        exercise.setExerciseImg(exerciseRequestDTO.getExerciseImg());
        exercise.setExerciseCalorie(exerciseRequestDTO.getExerciseCalorie());
        exercise.setExerciseName(exerciseRequestDTO.getExerciseName());
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

    public List<ExerciseForKeywordDTO> getGroupsByKeywordId(Long keywordId) {
        List<Exercise> exercises = exerciseRepository.findByKeywordId(keywordId);

        List<ExerciseForKeywordDTO> exerciseDTOs = new ArrayList<>();

        for (Exercise exercise : exercises) {
            ExerciseForKeywordDTO exerciseDTO = new ExerciseForKeywordDTO(exercise);
            exerciseDTOs.add(exerciseDTO);
        }

        return exerciseDTOs;
    }
}