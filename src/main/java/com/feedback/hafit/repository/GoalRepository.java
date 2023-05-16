package com.feedback.hafit.repository;

import com.feedback.hafit.entity.Goal;
import com.feedback.hafit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);

}
