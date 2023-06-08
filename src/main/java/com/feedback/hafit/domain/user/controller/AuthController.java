package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.jwt.filter.JwtAuthenticationProcessingFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AuthController {

    private final UserService userService;
    private final JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter;
    @PostMapping("/signup")
    public String signup(@RequestBody UserFormDTO userFormDTO) {
        userService.signup(userFormDTO);
        return "회원가입 성공";
    }

    @GetMapping("/reissue")
    public ResponseEntity<String> reissueAccessToken(HttpServletResponse response, String refreshToken) {
        log.info("dfdfdf");
        // AccessToken 재발급
        jwtAuthenticationProcessingFilter.checkRefreshTokenAndReIssueAccessToken(response, refreshToken);

        return ResponseEntity.ok("AccessToken이 재발급되었습니다.");
    }

}