package com.feedback.hafit.repository;

import com.feedback.hafit.domain.Plan;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query("SELECT p FROM Plan p JOIN FETCH p.goal JOIN FETCH p.exercise WHERE p.id = :id")
    Plan findByIdWithGoalAndExercise(@Param("id") Long id);
}
