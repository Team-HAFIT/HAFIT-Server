package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.jwt.filter.JwtAuthenticationProcessingFilter;
import com.feedback.hafit.global.jwt.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter;

    @PostMapping("/signup")
    public String signup(@RequestBody UserFormDTO userFormDTO) {
        userService.signup(userFormDTO);
        return "회원가입 성공";
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> reissueAccessToken(HttpServletResponse response, @RequestBody String refreshToken) {
        log.info("dfdfdf");
        // AccessToken 재발급
        userRepository.findByRefreshToken(refreshToken)
                .ifPresent(user -> {
                    String reIssuedRefreshToken = reIssueRefreshToken(user);
                    String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getAuthorities());
                    response.setHeader(accessHeader, accessToken);
                    response.setHeader(refreshHeader, reIssuedRefreshToken);
                });
        return ResponseEntity.ok("AccessToken이 재발급되었습니다.");
    }

    private String reIssueRefreshToken(User user) {
        String reIssuedRefreshToken = jwtTokenProvider.createRefreshToken();
        user.updateRefreshToken(reIssuedRefreshToken);
        userRepository.saveAndFlush(user);
        return reIssuedRefreshToken;
    }
}