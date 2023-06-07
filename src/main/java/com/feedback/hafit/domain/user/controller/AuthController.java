package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserFormDTO userFormDTO) {
        userService.signup(userFormDTO);
        return "회원가입 성공";
    }

}