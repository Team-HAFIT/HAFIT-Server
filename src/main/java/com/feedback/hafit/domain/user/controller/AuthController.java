package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/loginPage")
//    public String loginPage() {
//        return "user/loginPage";
//    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserFormDTO userFormDTO) throws Exception {
        userService.signup(userFormDTO);
        return "회원가입 성공";
    }

    @GetMapping("/index")
    public void index() {
    }
}