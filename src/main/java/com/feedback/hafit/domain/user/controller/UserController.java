package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.post.dto.response.PostForUserDTO;
import com.feedback.hafit.domain.user.dto.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.UserDTO;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
public class UserController {

    private final UserService userService;

    // 회원 정보 수정
    @PutMapping
    public boolean updateUser(Principal principal, @RequestBody UserDTO userDTO) {
        return userService.updateUser(principal.getName(), userDTO);
    }

    // 회원 삭제
    @DeleteMapping
    public boolean deleteUser(Principal principal) {
        return userService.deleteUser(principal.getName());
    }

    // 비밀번호 변경
    @PutMapping("/password-change")
    public boolean changePassword(Principal principal, @RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        return userService.changePassword(principal.getName(), userChangePasswordDTO);
    }

    // 회원 정보 가져오기
    @GetMapping
    public ResponseEntity<UserDTO> getUserInfo(Principal principal) {
        UserDTO userDTO = userService.getUserInfoByEmail(principal.getName());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/liked-posts")
    public ResponseEntity<List<PostForUserDTO>> getLikedPostsByEmail(Principal principal) {
        try {
            List<PostForUserDTO> likedPosts = userService.getLikedPostsByUserEmail(principal.getName());
            return ResponseEntity.ok(likedPosts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
