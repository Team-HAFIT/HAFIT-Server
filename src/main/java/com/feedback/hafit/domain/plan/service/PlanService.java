package com.feedback.hafit.domain.plan.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import com.feedback.hafit.domain.plan.dto.request.PlanRequestDTO;
import com.feedback.hafit.domain.plan.dto.response.PlanResponseDTO;
import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.plan.repository.PlanRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final PlanRepository planRepository;

    @Transactional
    public PlanResponseDTO settingPlan(PlanRequestDTO planRequestDTO, String email) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("Could not find user with email: " + email));
            Long exerciseId = planRequestDTO.getExerciseId();
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new NotFoundException("Could not find Exercise with exerciseId: " + exerciseId));
            Plan plan = Plan.builder()
                    .user(user)
                    .exercise(exercise)
                    .targetCount(planRequestDTO.getTargetCount())
                    .targetSet(planRequestDTO.getTargetSet())
                    .weight(planRequestDTO.getWeight())
                    .restTime(planRequestDTO.getRestTime())
                    .build();
            Plan savedPlan = planRepository.save(plan);
            return new PlanResponseDTO(savedPlan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public PlanResponseDTO getPlanById(Long planId) {
        Optional<Plan> plan = planRepository.findById(planId);
        if(plan.isEmpty())throw new IllegalArgumentException("존재하지 않는 계획입니다.");
        PlanResponseDTO planResponseDTO = new PlanResponseDTO(plan.get());

        return planResponseDTO;
    }

}
