package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.UserDTO;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
public class UserController {

    private final UserService userService;

    @PutMapping
    public ResponseEntity<Boolean> updateUser(Principal principal, @RequestBody UserDTO userDTO) {
        boolean isUpdated = userService.updateUser(principal.getName(), userDTO);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(Principal principal) {
        boolean isDeleted = userService.deleteUser(principal.getName());
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PutMapping("/password-change")
    public ResponseEntity<Boolean> changePassword(Principal principal, @RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        boolean isChanged = userService.changePassword(principal.getName(), userChangePasswordDTO);
        if (isChanged) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // 회원 정보 가져오기
    @GetMapping
    public ResponseEntity<UserDTO> getUserInfo(Principal principal) {
        UserDTO userDTO = userService.getUserInfoByEmail(principal.getName());
        return ResponseEntity.ok(userDTO);
    }

}
