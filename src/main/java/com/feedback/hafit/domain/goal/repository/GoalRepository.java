package com.feedback.hafit.domain.goal.repository;

import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);

    Optional<Goal> findFirstByUserUserIdOrderByCreatedAtDesc(Long userId);

    List<Goal> findByUserUserIdOrderByCreatedAtDesc(Long userId);

    List<Goal> findByUserUserIdAndGoalTargetDateAfter(Long userId, LocalDate today);
}
