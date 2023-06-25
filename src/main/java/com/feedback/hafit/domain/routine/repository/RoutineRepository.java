package com.feedback.hafit.domain.routine.repository;

import com.feedback.hafit.domain.routine.entity.Routine;
import com.feedback.hafit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findByuser(User userId);

    List<Routine> findByUser(User user);
}
