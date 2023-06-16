package com.feedback.hafit.domain.routine.service;

import com.feedback.hafit.domain.routine.repository.RoutineRepository;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;

}
