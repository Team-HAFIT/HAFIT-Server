package com.feedback.hafit.domain.routine.repository;

import com.feedback.hafit.domain.routine.entity.Routine;
import com.feedback.hafit.domain.routine.entity.RoutineDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoutineDateRepository extends JpaRepository<RoutineDate, Long> {

    List<RoutineDate> findByroutine(Routine routineId);
    void deleteAllByroutine(Routine routineId);

    List<RoutineDate> findByRoutine(Routine routine);

    Optional<RoutineDate> findByRoutine_RoutineIdAndDays(Long routineId, LocalDate days);
}
