package com.feedback.hafit.controller;

import com.feedback.hafit.entity.UserDTO;
import com.feedback.hafit.entity.UserFormDTO;
import com.feedback.hafit.entity.UserLoginDTO;
import com.feedback.hafit.repository.UserRepository;
import com.feedback.hafit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://172.26.12.239:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://172.26.12.239:3000")
    public boolean signup(@RequestBody UserFormDTO userFormDTO) {
        return  userService.signup(userFormDTO);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://172.26.12.239:3000")
    public boolean login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestBody UserDTO userDTO) {
        return userService.deleteAccount(userDTO);
    }
}
