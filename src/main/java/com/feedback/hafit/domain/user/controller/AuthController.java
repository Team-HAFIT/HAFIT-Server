package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserDTO;
import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.jwt.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Optional;

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

    public ResponseEntity<Boolean> updateUser(Principal principal, @RequestBody UserDTO userDTO) {
        boolean isUpdated = userService.updateUser(principal.getName(), userDTO);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UserFormDTO userFormDTO) {
        boolean isSaved = userService.signup(userFormDTO);;
        if (isSaved) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // 이메일 중복 확인
    @GetMapping("/email/{email}")
    @ResponseBody
    public int checkEmailAvailability(@PathVariable("email") String email) {
        return userService.emailCheck(email);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String reissueAccessToken(HttpServletResponse response, @RequestBody String refreshToken) {
        log.info("전달받은 refreshToken : {}", refreshToken);
        // AccessToken 재발급
        Optional<User> userOptional = userRepository.findByRefreshToken(refreshToken);
        log.info("userOptional : {}", userOptional);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        log.info("user : {}", user);
        String reIssuedRefreshToken = reIssueRefreshToken(user);
        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getAuthorities());
        log.info("accessHeader : {}", accessHeader);
        log.info("reIssuedRefreshToken : {}", reIssuedRefreshToken);
        jwtTokenProvider.sendAccessAndRefreshToken(response, accessToken, reIssuedRefreshToken);
        // response.setHeader(accessHeader, accessToken);
        // response.setHeader(refreshHeader, reIssuedRefreshToken);
        return "AccessToken이 재발급되었습니다.";
    }

    private String reIssueRefreshToken(User user) {
        String reIssuedRefreshToken = jwtTokenProvider.createRefreshToken();
        user.updateRefreshToken(reIssuedRefreshToken);
        userRepository.saveAndFlush(user);
        return reIssuedRefreshToken;
    }
}