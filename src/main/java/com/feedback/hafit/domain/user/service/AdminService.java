package com.feedback.hafit.domain.user.service;

import com.feedback.hafit.domain.user.dto.response.UserResponseDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserResponseDTO userDTO = new UserResponseDTO(user);
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }
}
