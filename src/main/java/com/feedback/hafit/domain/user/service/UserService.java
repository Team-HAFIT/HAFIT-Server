package com.feedback.hafit.domain.user.service;

import com.feedback.hafit.domain.user.dto.request.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.request.UserDTO;
import com.feedback.hafit.domain.user.dto.request.UserFormDTO;
import com.feedback.hafit.domain.user.dto.response.UserResponseDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserFormDTO userFormDTO) {
        User user = userFormDTO.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        userRepository.delete(user);
    }

    public int emailCheck(String email) {
        boolean exists = userRepository.existsByEmail(email);
        return exists ? 1 : 0;
    }

    public void updateUser(String email, UserDTO userDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        user.setCarrier(user.getCarrier());
        user.setPhone(userDTO.getPhone());
        user.setHeight(userDTO.getHeight());
        user.setWeight(userDTO.getWeight());
        user.setSex(userDTO.getSex());
        user.setBirthday(userDTO.getBirthday());
        user.setImageUrl(userDTO.getImageUrl());
        userRepository.save(user);
    }

    public void changePassword(String email, UserChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            String encodedNewPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
            user.setPassword(encodedNewPassword);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid old password");
        }
    }

    public UserResponseDTO getUserInfo(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        return new UserResponseDTO(user);
    }

}
