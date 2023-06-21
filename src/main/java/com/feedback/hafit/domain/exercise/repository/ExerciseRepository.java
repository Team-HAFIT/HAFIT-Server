package com.feedback.hafit.domain.exercise.repository;

import com.feedback.hafit.domain.exercise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT ek.exercise FROM ExerciseKeyword ek WHERE ek.keyword.keywordId = :keywordId")
    List<Exercise> findByKeywordId(@Param("keywordId") Long keywordId);
}
