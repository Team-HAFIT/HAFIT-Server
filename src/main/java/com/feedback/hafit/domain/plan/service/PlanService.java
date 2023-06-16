package com.feedback.hafit.domain.plan.service;

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

import javax.persistence.EntityNotFoundException;
@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final PlanRepository planRepository;

    @Transactional
    public boolean settingPlan(PlanRequestDTO planRequestDTO, String email) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not find with email: " + email));
            Long exerciseId = planRequestDTO.getExerciseId();
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new EntityNotFoundException("Exercise not find Exercise exerciseId: " + exerciseId));
            Plan plan = Plan.builder()
                    .user(user)
                    .exercise(exercise)
                    .plan_target_count(planRequestDTO.getPlan_target_count())
                    .plan_target_set(planRequestDTO.getPlan_target_set())
                    .plan_weight(planRequestDTO.getPlan_weight())
                    .plan_rest_time(planRequestDTO.getPlan_rest_time())
                    .build();
            planRepository.save(plan);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PlanResponseDTO getPlanById(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계획입니다."));

        return new PlanResponseDTO(plan);
    }

}
