package com.feedback.hafit.controller;

import com.feedback.hafit.entity.UserChangePasswordDTO;
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
@CrossOrigin(origins = "http://172.26.9.191:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "redirect:/user/loginPage";
    }
    @PostMapping("/signup")
    @CrossOrigin(origins = "http://172.26.9.191:3000")
    public boolean signup(@RequestBody UserFormDTO userFormDTO) {
        return  userService.signup(userFormDTO);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://172.26.9.191:3000")
    public boolean login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "http://172.26.9.191:3000")
    public boolean update(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PostMapping("/delete")
    @CrossOrigin(origins = "http://172.26.9.191:3000")
    public boolean delete(@RequestBody UserDTO userDTO) {
        return userService.deleteAccount(userDTO);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        return userService.changePassword(userChangePasswordDTO);
    }
}
