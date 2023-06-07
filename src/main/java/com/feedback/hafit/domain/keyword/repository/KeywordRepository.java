package com.feedback.hafit.domain.keyword.repository;

import com.feedback.hafit.domain.goal.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Goal, Long> {
}
