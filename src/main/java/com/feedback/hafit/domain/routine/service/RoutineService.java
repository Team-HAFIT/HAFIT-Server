package com.feedback.hafit.domain.routine.service;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.goal.repository.GoalRepository;
import com.feedback.hafit.domain.routine.dto.PRoutineDTO;
import com.feedback.hafit.domain.routine.dto.RoutineDTO;
import com.feedback.hafit.domain.routine.entity.Routine;
import com.feedback.hafit.domain.routine.entity.RoutineDate;
import com.feedback.hafit.domain.routine.repository.RoutineDateRepository;
import com.feedback.hafit.domain.routine.repository.RoutineRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.global.enumerate.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;

    private final RoutineDateRepository routineDateRepository;

    private final UserRepository userRepository;

    private final GoalRepository goalRepository;

    private final ExerciseRepository exerciseRepository;

    public List<RoutineDTO> getUserRoutine(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        List<Routine> userRoutine = routineRepository.findByuser(user);
        List<RoutineDTO> userRoutines = new ArrayList<>();

        for (Routine routine : userRoutine) {
            RoutineDTO routineDTO = new RoutineDTO(routine);
            userRoutines.add(routineDTO);
        }

        return userRoutines;

    }

    public List<RoutineDTO> getAllRoutines() {
        List<Routine> routine = routineRepository.findAll();
        List<RoutineDTO> routines = new ArrayList<>();

        for (Routine routinedto : routine) {
            RoutineDTO routineDTO = new RoutineDTO(routinedto);
            routines.add(routineDTO);
        }

        return routines;
    }

    public List<PRoutineDTO> getRoutineById(Long routineId) {
        Optional<Routine> routine = routineRepository.findById(routineId);
        List<RoutineDate> routineDates = routineDateRepository.findByroutine(routine.get());

        List<PRoutineDTO> routines = new ArrayList<>();

        for (RoutineDate rou : routineDates) {
            PRoutineDTO pRoutineDTO = new PRoutineDTO(routine.get(), rou);
            routines.add(pRoutineDTO);
        }

        return routines;
    }

    public Routine createRoutine(PRoutineDTO pRoutineDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        Optional<Goal> goal = goalRepository.findById(pRoutineDTO.getGoalId());
        Optional<Exercise> exercise = exerciseRepository.findById(pRoutineDTO.getExerciseId());

        Routine routine = new Routine();
        routine.setRoutineCount(pRoutineDTO.getRoutineCount());
        routine.setRoutineSet(pRoutineDTO.getRoutineSet());
        routine.setRoutineWeight(pRoutineDTO.getRoutineWeight());
        routine.setUser(user);
        routine.setGoal(goal.get());
        routine.setExercise(exercise.get());
        routine.setStartDate(pRoutineDTO.getStartDate());

        LocalDate endDate = goal.get().getGoal_target_date();
        List<DayOfWeek> targetDayOfWeek = pRoutineDTO.getRepeatDays();

        LocalDate current = routine.getStartDate();

        int i = 0;
        while (!current.isAfter(endDate)) {
            if (current.getDayOfWeek().equals(targetDayOfWeek.get(i))) {
                RoutineDate routineDate = new RoutineDate();
                routineDate.setDays(current);
                routineDate.setRoutine(routine);
                routine.getRoutineDates().add(routineDate);
            }

            current = current.plusDays(1);  // 다음 날짜로 이동
            i++;
            if (i >= targetDayOfWeek.size()) {
                i = 0;  // i 값이 리스트의 인덱스 범위를 벗어났을 경우 0으로 초기화
            }
        }

        return routine;

    }
}
