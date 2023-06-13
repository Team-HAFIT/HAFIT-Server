package com.feedback.hafit.domain.plan.service;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.service.ExerciseService;
import com.feedback.hafit.domain.plan.dto.PlanDTO;
import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.plan.repository.PlanRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.Optional;

@Service
@Slf4j
public class PlanService {

    @Autowired
    UserService userService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    PlanRepository planRepository;

    @Transactional
    public PlanDTO setting(PlanDTO planDTO, String email) {
        try {
//            Long userId = settingDTO.getUserId(); // Long userId
            User user = userService.getByEmail(email);
            Long execId = planDTO.getExecId(); // Long categoryId
            Exercise exercise = exerciseService.getById(execId);
            planRepository.save(Plan.builder()
                    .user(user)
                    .exercise(exercise)
                    .targetCount(planDTO.getTargetCount())
                    .targetSet(planDTO.getTargetSet())
                    .weight(planDTO.getWeight())
                    .restTime(planDTO.getRestTime())
                    .build()
            );
            return planDTO;
        } catch (Exception e) {
            System.out.println("존재하지 않는 사용자입니다.");
        }
        return null;
    }

    public PlanDTO getDTOById(Long planId) {
        Optional<Plan> plan = planRepository.findById(planId);
        if(plan.isEmpty())throw new IllegalArgumentException("존재하지 않는 계획입니다.");
        PlanDTO planDTO = new PlanDTO(plan.get());

        return planDTO;
    }

    public Plan getById(Long planId) {
        Optional<Plan> planOptional = planRepository.findById(planId);
        if(planOptional.isEmpty())throw new IllegalArgumentException("존재하지 않는 계획입니다.");
        return planOptional.get();
    }
}
