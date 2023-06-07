package com.feedback.hafit.domain.user.service;

import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.dto.UserLoginDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.enumerate.UserStatus;
import com.feedback.hafit.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        return userOptional.get();
    }

    public User findById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    public void signup(UserFormDTO userFormDTO) {
        User user = userFormDTO.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public User login(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userLoginDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword()) && user.getUser_status() == UserStatus.ACTIVE) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteAccount(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) { // Optional에서 User를 가져올 수 있는지 확인
            User user = userOptional.get();
            user.setUser_status(UserStatus.DELETED); // user_status 값을 DELETED로 변경
            userRepository.save(user);
            return true; // 삭제 성공을 나타내는 true 반환
        } else {
            return false; // 삭제 실패를 나타내는 false 반환
        }
    }

    public int emailCheck(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent() ? 1 : 0;
    }

    /*
    public boolean updateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setCarrier(user.getCarrier());
            user.setPhone(userDTO.getPhone());
            user.setHeight(userDTO.getHeight());
            user.setWeight(userDTO.getWeight());
            user.setSex(userDTO.getSex());
            user.setBirthday(userDTO.getBirthday());
            user.setUser_img(userDTO.getUser_img());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean changePassword(UserChangePasswordDTO changePasswordDTO) {
        Optional<User> userOptional = userRepository.findByEmail(changePasswordDTO.getEmail());
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

    public UserDTO getUserInfoById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userId));
        return mapToUserDTO(user);
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .user_id(user.getUser_id())
                .email(user.getEmail())
                .name(user.getName())
                .carrier(user.getCarrier())
                .phone(user.getPhone())
                .sex(user.getSex())
                .user_img(user.getUser_img())
                .weight(user.getWeight())
                .height(user.getHeight())
                .birthday(user.getBirthday())
                .build();
    }


     */

}
