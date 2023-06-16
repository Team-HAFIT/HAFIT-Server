package com.feedback.hafit.domain.exerciseSet.repository;

import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import com.feedback.hafit.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {

    List<ExerciseSet> findByPlan(@Param("planId") Plan planId);

    ExerciseSet findFirstByPlanOrderBySetIdDesc(@Param("planId") Plan planId);

}