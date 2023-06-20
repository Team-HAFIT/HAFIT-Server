package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.UserDTO;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
public class UserController {

    private final UserService userService;
    private final S3Service s3Service;

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

    @PostMapping("/profile")
    public ResponseEntity<Boolean> uploadProfileImage(@RequestParam("file") MultipartFile imageFile, Principal principal) {
        // userId를 사용하여 User 정보를 조회하거나 인증된 사용자 정보를 사용할 수 있음
        // 필요한 인증, 권한 검사 등을 수행
        // 프로필 이미지 저장 및 UserFile 엔티티 반환
        userService.uploadProfileImage(imageFile, principal.getName());
        return ResponseEntity.ok(true);
    }

    @PutMapping("/profile") // 프로필 이미지 업데이트
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> updateProfileImage(@RequestParam(value = "file", required = false) MultipartFile imageFile, Principal principal) {
        boolean isUpdated = userService.updateProfileImage(imageFile, principal.getName());
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/profile/{userId}") // 프로필 이미지 조회
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getProfileImage(@PathVariable("userId") Long userId) {
        String imageUrl = userService.getProfileImageByUserId(userId);
        if (imageUrl != null) {
            return ResponseEntity.ok(imageUrl);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/profile") // 프로필 이미지 삭제
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteProfileImage(Principal principal) {
        boolean isDeleted = userService.deleteProfileImage(principal.getName());
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }


}
