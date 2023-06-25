package com.feedback.hafit.domain.routine.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PerformRoutineRequestDTO {
    private Long routineId;

    private LocalDate days;

    private String perform;

}
