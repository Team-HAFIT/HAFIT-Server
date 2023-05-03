package com.feedback.hafit.service;

import com.feedback.hafit.entity.User;
import com.feedback.hafit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

//    @Autowired
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User signupUser(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
    }
    public void validateDuplicateUser(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
