package com.feedback.hafit.domain.routine.repository;

import com.feedback.hafit.domain.routine.entity.Routine;
import com.feedback.hafit.domain.routine.entity.RoutineDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineDateRepository extends JpaRepository<RoutineDate, Long> {

    List<RoutineDate> findByroutine(Routine routineId);
    void deleteAllByroutine(Routine routineId);

    List<RoutineDate> findByRoutine(Routine routine);
}
