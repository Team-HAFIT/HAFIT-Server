package com.feedback.hafit.service;

import com.feedback.hafit.entity.*;
import com.feedback.hafit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean signup(UserFormDTO userFormDTO) {
        try {
            userRepository.save(userFormDTO.toEntity());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findUserByEmail(userLoginDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(userLoginDTO.getPassword()) && user.getUser_status() == UserStatus.ACTIVE) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteAccount(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findUserByEmail(userDTO.getEmail());
        if (userOptional.isPresent()) { // Optional에서 User를 가져올 수 있는지 확인
            User user = userOptional.get();
            user.setUser_status(UserStatus.DELETED); // user_status 값을 'n'으로 변경
            userRepository.save(user);
            return true; // 삭제 성공을 나타내는 true 반환
        } else {
            return false; // 삭제 실패를 나타내는 false 반환
        }
    }

    public boolean updateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findUserByEmail(userDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPhone(userDTO.getPhone());
            user.setHeight(userDTO.getHeight());
            user.setWeight(userDTO.getWeight());
            user.setSex(userDTO.getSex());
            user.setBirth_year(userDTO.getBirth_year());
            user.setBirth_month(userDTO.getBirth_month());
            user.setBirth_day(userDTO.getBirth_day());
            user.setUser_img(userDTO.getUser_img());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
