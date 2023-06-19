package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.request.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.request.UserDTO;
import com.feedback.hafit.domain.user.dto.response.UserResponseDTO;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
public class UserController {

    private final UserService userService;

    @PutMapping
    public void updateUser(Principal principal, @RequestBody UserDTO userDTO) {
        userService.updateUser(principal.getName(), userDTO);
    }

    @DeleteMapping
    public void deleteUser(Principal principal) {
        userService.deleteUser(principal.getName());
    }

    @PutMapping("/password-change")
    public void changePassword(Principal principal, @RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        userService.changePassword(principal.getName(), userChangePasswordDTO);
    }

    // 회원 정보 가져오기
    @GetMapping
    public UserResponseDTO getUserInfo(Principal principal) {
        return userService.getUserInfoByEmail(principal.getName());
    }

}
