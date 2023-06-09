package com.feedback.hafit.domain.plan.repository;

import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.user.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByUser(User user);

    List<Plan> findByUserAndCreatedAtBetween(User user, LocalDateTime Ago, LocalDateTime currentDate, Sort createdAt);
}