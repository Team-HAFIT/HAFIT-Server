package com.feedback.hafit.domain.exerciseSet.service;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exerciseSet.dto.ExerciseSetDTO;
import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import com.feedback.hafit.domain.exerciseSet.repository.ExerciseSetRepository;
import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.plan.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Slf4j
public class ExerciseSetService {

    @Autowired
    ExerciseSetRepository exerciseSetRepository;

    @Autowired
    PlanService planService;



    @Transactional
    public ExerciseSetDTO save(ExerciseSetDTO execSetDTO, String email) {
        Long planId = execSetDTO.getPlan();
        Plan plan = planService.getById(planId);
        Long targetSet = plan.getTargetSet();
        Long realSet = execSetDTO.getRealSet();

        if (realSet + 1 > targetSet) {
            // 이 조건에 해당하면 휴식 시간 화면이 아닌 결과 화면으로 이동
            // 저장은 하고나서 return
//            ExerciseSet execSet = exerciseSetRepository.save(ExerciseSet.builder()
//                    .restTime(execSetDTO.getRestTime())
//                    .weight(execSetDTO.getWeight())
//                    .score(execSetDTO.getScore())
//                    .realCount(execSetDTO.getRealCount())
//                    .realSet(execSetDTO.getRealSet())
//                    .startTime(execSetDTO.getStartTime())
//                    .limitTime(execSetDTO.getLimitTime())
//                    .realTime(execSetDTO.getRealTime())
//                    .plan(plan)
//                    .build()
//            );
//            return new ExerciseSetDTO(execSet);
        }

        ExerciseSet execSet = exerciseSetRepository.save(ExerciseSet.builder()
                .restTime(execSetDTO.getRestTime())
                .weight(execSetDTO.getWeight())
                .score(execSetDTO.getScore())
                .realCount(execSetDTO.getRealCount())
                .realSet(execSetDTO.getRealSet())
                .startTime(execSetDTO.getStartTime())
                .limitTime(execSetDTO.getLimitTime())
                .realTime(execSetDTO.getRealTime())
                .plan(plan)
                .build()
        );

        execSet.setRealSet(execSet.getRealSet()+1);

        return new ExerciseSetDTO(execSet);
    }
}