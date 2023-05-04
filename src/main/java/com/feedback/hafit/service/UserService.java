package com.feedback.hafit.service;

import com.feedback.hafit.entity.UserDTO;
import com.feedback.hafit.entity.UserFormDTO;
import com.feedback.hafit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void userJoin(UserFormDTO userFormDTO) {
        userRepository.save(userFormDTO.toEntity());
    }

    public UserDTO login(UserDTO userDTO) {
        return null;
    }
}
