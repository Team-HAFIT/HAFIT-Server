package com.feedback.hafit.repository;

import com.feedback.hafit.entity.User;
import com.feedback.hafit.entity.UserLoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<UserLoginDTO> findUserByEmail(String email);
}