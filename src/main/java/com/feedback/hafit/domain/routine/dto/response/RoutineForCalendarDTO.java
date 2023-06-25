package com.feedback.hafit.domain.routine.dto.response;

import com.feedback.hafit.domain.routine.entity.Routine;
import com.feedback.hafit.domain.routine.entity.RoutineDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineForCalendarDTO {
    private Long routineId;
    private Long routineCount;
    private Long routineSet;
    private Long routineWeight;
    private Long goalId;
    private String exerciseName;
    private LocalDate days;
    private String perform;

    public RoutineForCalendarDTO(Routine routine, RoutineDate routineDate) {
        this.routineId = routine.getRoutineId();
        this.routineCount = routine.getRoutineCount();
        this.routineSet = routine.getRoutineSet();
        this.routineWeight = routine.getRoutineWeight();
        this.goalId = routine.getGoal().getGoalId();
        this.exerciseName = routine.getExercise().getExerciseName();
        this.days = routineDate.getDays();
        this.perform = routineDate.getPerform();
    }
}
