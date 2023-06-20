package com.feedback.hafit.domain.goal.repository;

import com.feedback.hafit.domain.goal.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
