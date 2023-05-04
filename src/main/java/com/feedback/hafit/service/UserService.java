package com.feedback.hafit.service;

import com.feedback.hafit.entity.User;
import com.feedback.hafit.entity.UserFormDTO;
import com.feedback.hafit.entity.UserLoginDTO;
import com.feedback.hafit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void userJoin(UserFormDTO userFormDTO) {
        userRepository.save(userFormDTO.toEntity());
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findUserByEmail(userLoginDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(userLoginDTO.getPassword())) {
                return true;
            }
        }
        return false;
    }
    /*
    public UserLoginDTO login(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findUserByEmail(userLoginDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(userLoginDTO.getPassword())) {
                return user.toLoginDTO();
            }
        }
        return null;
    }**/
}
