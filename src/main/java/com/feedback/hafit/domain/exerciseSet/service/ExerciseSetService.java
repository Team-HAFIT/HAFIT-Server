package com.feedback.hafit.domain.exerciseSet.service;

import com.feedback.hafit.domain.exerciseSet.dto.request.ExerciseSetRequestDTO;
import com.feedback.hafit.domain.exerciseSet.dto.response.ExerciseSetResponseDTO;
import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import com.feedback.hafit.domain.exerciseSet.repository.ExerciseSetRepository;
import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.plan.repository.PlanRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    // 운동 한 세트 종료 후 저장 메서드
    @Transactional
    public ExerciseSetResponseDTO save(ExerciseSetRequestDTO execSetDTO) {
        Long planId = execSetDTO.getPlan();
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found with planId: " + planId));

        if (plan.getPlan_target_set() == execSetDTO.getRealSet()) {
            plan.setPlan_perform_status("Y");
            planRepository.save(plan);
        }

        ExerciseSet execSet = ExerciseSet.builder()
                .weight(execSetDTO.getWeight())
                .score(execSetDTO.getScore())
                .realCount(execSetDTO.getRealCount())
                .realSet(execSetDTO.getRealSet())
                .startTime(execSetDTO.getStartTime())
                .limitTime(execSetDTO.getLimitTime())
                .realTime(execSetDTO.getRealTime())
                .plan(plan)
                .build();

        ExerciseSet savedExerciseSet = exerciseSetRepository.save(execSet);
        ExerciseSetResponseDTO exerciseSetResponseDTO = new ExerciseSetResponseDTO(savedExerciseSet);
        exerciseSetResponseDTO.setSetId(savedExerciseSet.getSetId());

        return exerciseSetResponseDTO;
    }

    // 휴식 시간 종료 후 휴식 시간 저장 메서드 (휴식 시간 -> 운동 화면 / 휴식 시간 -> 결과 화면)
    @Transactional
    public ExerciseSetResponseDTO update(ExerciseSetRequestDTO exerciseSetDTO) {
        try {
            Long planId = exerciseSetDTO.getPlan();
            Plan plan = planRepository.findById(planId)
                    .orElseThrow(() -> new EntityNotFoundException("Plan not found with planId: " + planId));

            ExerciseSet exerciseSets = exerciseSetRepository.findFirstByplanOrderBySetIdDesc(plan);

            exerciseSets.setRestTime(exerciseSetDTO.getRestTime());
            ExerciseSet updatedExerciseSet = exerciseSetRepository.save(exerciseSets);

            ExerciseSetResponseDTO exerciseDTO = new ExerciseSetResponseDTO(updatedExerciseSet);

            return exerciseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("휴식 시간 저장에 실패하였습니다.");
        }
    }

    // 하나의 계획에 해당하는 세트 조회용 메서드
    @Transactional
    public List<ExerciseSetResponseDTO> getByPlanId(Long planId) {
        try {
            Plan plan = planRepository.findById(planId)
                    .orElseThrow(() -> new EntityNotFoundException("Plan not found with planId: " + planId));

            List<ExerciseSet> sets = exerciseSetRepository.findByplan(plan);
            List<ExerciseSetResponseDTO> exerciseSets = new ArrayList<>();

            for (ExerciseSet exerciseSet : sets) {
                ExerciseSetResponseDTO exerciseSetDTO = new ExerciseSetResponseDTO(exerciseSet);
                exerciseSets.add(exerciseSetDTO);
            }

            return exerciseSets;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("세트 조회에 실패하였습니다.");
        }
    }


//    @Transactional // 운동 업데이트 전 조회용 테스트 메서드
//    public ExerciseSetRequestDTO getget(Long planId, String email) {
//        Plan plan = planService.getById(planId);
//        ExerciseSet execSet = exerciseSetRepository.findFirstByplanOrderBySetIdDesc(plan);
//        return new ExerciseSetRequestDTO(execSet);
//    }

    // 모든 세트 정보 조회용 메서드
    @Transactional
    public List<ExerciseSetResponseDTO> getAllSets() {
        try {
            List<ExerciseSet> sets = exerciseSetRepository.findAll();
            List<ExerciseSetResponseDTO> exerciseSets = new ArrayList<>();

            for (ExerciseSet exerciseSet : sets) {
                ExerciseSetResponseDTO execSetDTO = new ExerciseSetResponseDTO(exerciseSet);
                exerciseSets.add(execSetDTO);
            }

            return exerciseSets;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("모든 세트 정보 조회에 실패하였습니다.");
        }
    }

    @Transactional
    public ExerciseSetResponseDTO getLastestPlan(Long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("Plan not found with planId: " + planId));

        ExerciseSet exerciseSets = exerciseSetRepository.findFirstByplanOrderBySetIdDesc(plan);
        ExerciseSetResponseDTO setDTO = new ExerciseSetResponseDTO(exerciseSets);
        return setDTO;
    }

    @Transactional
    public List<ExerciseSetResponseDTO> findYearSets(String email, LocalDateTime current) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        LocalDateTime currentDateTime = current;
        LocalDateTime oneYearAgo = currentDateTime.minusYears(1);

        // plans 테이블을 기준으로 최근 1년간의 plan들을 조회
        List<Plan> plans = planRepository.findByUserAndCreatedAtBetween(user, oneYearAgo, currentDateTime, Sort.by(Sort.Direction.ASC, "createdAt"));

        List<ExerciseSetResponseDTO> sets = plans.stream()
                .flatMap(plan -> exerciseSetRepository.findByplan(plan).stream())
                .map(ExerciseSetResponseDTO::new)
                .collect(Collectors.toList());

        return sets;
    }

    @Transactional
    public List<ExerciseSetResponseDTO> findMonthSets(String email, LocalDateTime current) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        LocalDateTime currentDateTime = current;
        LocalDateTime oneMonthAgo = currentDateTime.minusMonths(1);

        // plans 테이블을 기준으로 최근 1달간의 plan들을 조회
        List<Plan> plans = planRepository.findByUserAndCreatedAtBetween(user, oneMonthAgo, currentDateTime, Sort.by(Sort.Direction.ASC, "createdAt"));

        List<ExerciseSetResponseDTO> sets = plans.stream()
                .flatMap(plan -> exerciseSetRepository.findByplan(plan).stream())
                .map(ExerciseSetResponseDTO::new)
                .collect(Collectors.toList());

        return sets;
    }
}