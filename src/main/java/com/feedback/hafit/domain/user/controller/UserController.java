package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.request.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.request.UserDTO;
import com.feedback.hafit.domain.user.dto.response.UserResponseDTO;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
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
        return userService.getUserInfo(principal.getName());
    }

    @PostMapping("/profile")
    public void uploadProfileImage(@RequestParam("file") MultipartFile imageFile, Principal principal) {
        // userId를 사용하여 User 정보를 조회하거나 인증된 사용자 정보를 사용할 수 있음
        // 필요한 인증, 권한 검사 등을 수행
        // 프로필 이미지 저장 및 UserFile 엔티티 반환
        userService.uploadProfileImage(imageFile, principal.getName());
    }

    @PutMapping("/profile") // 프로필 이미지 업데이트
    @ResponseBody
    public void updateProfileImage(@RequestParam(value = "file", required = false) MultipartFile imageFile, Principal principal) {
        boolean isUpdated = userService.updateProfileImage(imageFile, principal.getName());
    }

    @GetMapping("/profile/{userId}") // 프로필 이미지 조회
    @ResponseBody
    public String getProfileImage(@PathVariable("userId") Long userId) {
        String imageUrl = userService.getProfileImageByUserId(userId);

        return imageUrl;
    }


    @DeleteMapping("/profile") // 프로필 이미지 삭제
    @ResponseBody
    public void deleteProfileImage(Principal principal) {
        boolean isDeleted = userService.deleteProfileImage(principal.getName());

    }


}
