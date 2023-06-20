package com.feedback.hafit.domain.routine.dto;

import com.feedback.hafit.domain.routine.entity.Routine;
import com.feedback.hafit.global.enumerate.DayOfWeek;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoutineDTO {

    private Long routineId;

    private Long routineCount;

    private Long routineSet;

    private Long routineWeight;

    private Long userId;

    private Long exerciseId;

    private Long goalId;

    private List<DayOfWeek> repeatDays;



    public RoutineDTO(Routine routine) {
        this.routineId = routine.getRoutineId();
        this.routineCount = routine.getRoutineCount();
        this.routineSet = routine.getRoutineSet();
        this.routineWeight = routine.getRoutineWeight();
        this.userId = routine.getUser().getUserId();
        this.goalId = routine.getGoal().getGoalId();
        this.exerciseId = routine.getExercise().getExerciseId();
        this.repeatDays = routine.getRepeatDays();
    }
}
