package com.feedback.hafit.domain.exerciseSet.service;

import com.feedback.hafit.domain.exerciseSet.dto.ExerciseSetDTO;
import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import com.feedback.hafit.domain.exerciseSet.repository.ExerciseSetRepository;
import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.plan.repository.PlanRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Transactional // 운동 한세트 종료 후 저장 메서드
    public ExerciseSetDTO save(ExerciseSetDTO execSetDTO, String email) {
        Long planId = execSetDTO.getPlan();
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found with planId: " + planId));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        ExerciseSet execSet = exerciseSetRepository.save(ExerciseSet.builder()
//                .restTime(execSetDTO.getRestTime())
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

        return new ExerciseSetDTO(execSet);
    }

    @Transactional // 휴식 시간 종료 후 휴식 시간 저장 메서드 (휴식 시간 -> 운동 화면 / 휴식 시간 -> 결과 화면)
    public ExerciseSetDTO update(ExerciseSetDTO execSetDTO, String email) {
        Long planId = execSetDTO.getPlan();
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found with planId: " + planId));

        ExerciseSet execSet = exerciseSetRepository.findFirstByPlanOrderBySetIdDesc(plan);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        ExerciseSetDTO execDTO = new ExerciseSetDTO(execSet);
        execSet.setRestTime(execSetDTO.getRestTime());

        exerciseSetRepository.save(execSet);

        execDTO.setRealSet(execSet.getRealSet()+1);
        log.info(String.valueOf(execDTO.getRealSet()));
        execDTO.setWeight(execSetDTO.getWeight());
        log.info(String.valueOf(execDTO.getWeight()));

        return execDTO;
    }

    @Transactional // 하나의 계획에 해당하는 세트 조회용 메서드
    public List<ExerciseSetDTO> getByPlanId(Long planId, String email) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found with planId: " + planId));

        List<ExerciseSet> sets = exerciseSetRepository.findByPlan(plan);
        List<ExerciseSetDTO> execSets = new ArrayList<>();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        for (ExerciseSet exec : sets) {
            ExerciseSetDTO execSetDTO = new ExerciseSetDTO(exec);

            execSets.add(execSetDTO);
        }
        return execSets;
    }

//    @Transactional // 운동 업데이트 전 조회용 테스트 메서드
//    public ExerciseSetDTO getget(Long planId, String email) {
//        Plan plan = planService.getById(planId);
//        ExerciseSet execSet = exerciseSetRepository.findFirstByplanOrderBySetIdDesc(plan);
//        return new ExerciseSetDTO(execSet);
//    }

    @Transactional // 모든 세트 정보 조회용 메서드
    public List<ExerciseSetDTO> getAllSets(String email) {
        List<ExerciseSet> sets = exerciseSetRepository.findAll();
        List<ExerciseSetDTO> execSets = new ArrayList<>();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        for (ExerciseSet exec : sets) {
            ExerciseSetDTO execSetDTOs = new ExerciseSetDTO(exec);

            execSets.add(execSetDTOs);
        }
        return execSets;
    }
}