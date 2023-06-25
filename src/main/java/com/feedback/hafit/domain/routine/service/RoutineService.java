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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public void createRoutine(PRoutineDTO pRoutineDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        Goal goal = goalRepository.findById(pRoutineDTO.getGoalId())
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with GoalId: " + pRoutineDTO.getGoalId()));
        Exercise exercise = exerciseRepository.findById(pRoutineDTO.getExerciseId())
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found with ExerciseId: " + pRoutineDTO.getExerciseId()));

        Routine routine = new Routine();
        routine.setRoutineCount(pRoutineDTO.getRoutineCount());
        routine.setRoutineSet(pRoutineDTO.getRoutineSet());
        routine.setRoutineWeight(pRoutineDTO.getRoutineWeight());
        routine.setUser(user);
        routine.setGoal(goal);
        routine.setExercise(exercise);
        routine.setRepeatDays(pRoutineDTO.getRepeatDays());
        List<DayOfWeek> targetDayOfWeek = pRoutineDTO.getRepeatDays();

        RoutineDTO routineDTO = new RoutineDTO(routineRepository.save(routine));

        LocalDate endDate = routineDTO.getEndDate();
        if (endDate != null && targetDayOfWeek != null) {
            List<RoutineDate> routineDates = new ArrayList<>();
            LocalDate current = LocalDate.from(routine.getStartDate());

            while (!current.isAfter(endDate)) {
                java.time.DayOfWeek dayOfWeek = current.getDayOfWeek();
                String weekDay = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US);
                DayOfWeek convertedDayOfWeek = DayOfWeek.valueOf(weekDay.toUpperCase());
                if (targetDayOfWeek.contains(convertedDayOfWeek)) {
                    RoutineDate routineDate = new RoutineDate();
                    routineDate.setDays(current);
                    routineDate.setRoutine(routine);
                    routineDate.setPerform("N");
                    routineDates.add(routineDate);
                }
                current = current.plusDays(1);
            }
            routineDateRepository.saveAll(routineDates);
        }

    }

    @Transactional
    public RoutineDTO updateRoutine(Long routineId, PRoutineDTO pRoutineDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        Optional<Routine> routine = routineRepository.findById(routineId);
        Optional<Goal> goal = goalRepository.findById(pRoutineDTO.getGoalId());
        Optional<Exercise> exercise = exerciseRepository.findById(pRoutineDTO.getExerciseId());
        Routine routines = routine.get();
        routines.setRoutineCount(pRoutineDTO.getRoutineCount());
        routines.setRoutineSet(pRoutineDTO.getRoutineSet());
        routines.setRoutineWeight(pRoutineDTO.getRoutineWeight());
        routines.setRepeatDays(pRoutineDTO.getRepeatDays());
        routines.setGoal(goal.get());
        routines.setExercise(exercise.get());
        routines.setUser(user);
        routines.setStartDate(pRoutineDTO.getStartDate());

        RoutineDTO routineDTO = new RoutineDTO(routineRepository.save(routines));

        routineDateRepository.deleteAllByroutine(routine.get());

        LocalDate endDate = goal.map(Goal::getGoalTargetDate).orElse(null);
        List<DayOfWeek> targetDayOfWeek = routines.getRepeatDays();

        if (endDate != null && targetDayOfWeek != null) {
            List<RoutineDate> routineDates = new ArrayList<>();
            LocalDate current = routines.getStartDate();

            while (!current.isAfter(endDate)) {
                java.time.DayOfWeek dayOfWeek = current.getDayOfWeek();
                String weekDay = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US);
                DayOfWeek convertedDayOfWeek = DayOfWeek.valueOf(weekDay.toUpperCase());
                if (targetDayOfWeek.contains(convertedDayOfWeek)) {
                    RoutineDate routineDate = new RoutineDate();
                    routineDate.setDays(current);
                    routineDate.setRoutine(routines);
                    routineDate.setPerform("N");
                    routineDates.add(routineDate);
                }
                current = current.plusDays(1);
            }
            routineDateRepository.saveAll(routineDates);
        }

        return routineDTO;
    }

    @Transactional
    public void deleteRoutine(Long routineId) {
        Optional<Routine> routine = routineRepository.findById(routineId);
        routineDateRepository.deleteAllByroutine(routine.get());
        routineRepository.deleteById(routineId);
    }

}
