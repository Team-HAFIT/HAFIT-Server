package com.feedback.hafit.domain.exerciseSet.repository;

import com.feedback.hafit.domain.exerciseSet.entity.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
}