package com.feedback.hafit.domain.routine.dto;

import com.feedback.hafit.domain.routine.entity.Routine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoutineDTO {

    @NotNull
    private Long routineId;
    private Long goalId;
    private String userEmail;
    private Long execId;
    private int count; // 개수
    private int set; // 세트
    private int weight; // 무게
    private List<Routine.DayOfWeek> repeatDays; // 반복 요일
    private String memo; // 메모

    public RoutineDTO(Routine routine) {
        this.routineId = routine.getRoutineId();
        this.goalId = routine.getGoal().getGoal_id();
        this.userEmail = routine.getUser().getEmail();
//        this.execId = routine.getExercise().getExecId();
        this.count = routine.getCount();
        this.set = routine.getSet();
        this.weight = routine.getWeight();
        this.repeatDays = routine.getRepeatDays();
    }
}
