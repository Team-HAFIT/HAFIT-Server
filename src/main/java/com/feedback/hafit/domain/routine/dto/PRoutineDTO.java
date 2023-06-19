package com.feedback.hafit.domain.routine.dto;

import com.feedback.hafit.domain.routine.entity.Routine;
import com.feedback.hafit.domain.routine.entity.RoutineDate;
import com.feedback.hafit.global.enumerate.DayOfWeek;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PRoutineDTO {

    private Long routineId;

    private Long routineCount;

    private Long routineSet;

    private Long routineWeight;

    private Long userId;

    private Long goalId;

    private Long exerciseId;

    private List<DayOfWeek> repeatDays;

    private LocalDate days;

    private String perform;

    private LocalDate startDate;

    public PRoutineDTO(Routine routine, RoutineDate routineDate) {
        this.routineId = routine.getRoutineId();
        this.routineCount = routine.getRoutineCount();
        this.routineSet = routine.getRoutineSet();
        this.routineWeight = routine.getRoutineWeight();
        this.userId = routine.getUser().getUserId();
        this.goalId = routine.getGoal().getGoalId();
        this.exerciseId = routine.getExercise().getExerciseId();
        this.repeatDays = routine.getRepeatDays();
        this.days = routineDate.getDays();
        this.perform = routineDate.getPerform();
        this.startDate = routine.getStartDate();
    }
}
