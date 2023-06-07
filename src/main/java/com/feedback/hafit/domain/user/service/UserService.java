package com.feedback.hafit.domain.user.service;

import com.feedback.hafit.domain.user.dto.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.UserDTO;
import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        return userOptional.get();
    }

    public void signup(UserFormDTO userFormDTO) {
        User user = userFormDTO.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public boolean deleteUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) { // Optional에서 User를 가져올 수 있는지 확인
            User user = userOptional.get();
            userRepository.delete(user);
            return true; // 삭제 성공을 나타내는 true 반환
        } else {
            return false; // 삭제 실패를 나타내는 false 반환
        }
    }

    public int emailCheck(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent() ? 1 : 0;
    }

    public boolean updateUser(String email, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setCarrier(user.getCarrier());
            user.setPhone(userDTO.getPhone());
            user.setHeight(userDTO.getHeight());
            user.setWeight(userDTO.getWeight());
            user.setSex(userDTO.getSex());
            user.setBirthday(userDTO.getBirthday());
            user.setImageUrl(userDTO.getImageUrl());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean changePassword(String email, UserChangePasswordDTO changePasswordDTO) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
                String encodedNewPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
                // 비밀번호 유효성 검사 이후에 암호화 및 저장을 수행합니다.
                user.setPassword(encodedNewPassword);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public UserDTO getUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        return mapToUserDTO(user);
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .carrier(user.getCarrier())
                .phone(user.getPhone())
                .sex(user.getSex())
                .imageUrl(user.getImageUrl())
                .weight(user.getWeight())
                .height(user.getHeight())
                .birthday(user.getBirthday())
                .build();
    }

}
