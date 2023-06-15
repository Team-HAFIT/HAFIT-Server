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
import java.util.Map;

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

    // 내가 좋아요 표시한 글 조회
    @GetMapping("/liked-posts")
    public ResponseEntity<Map<String, Object>> getLikedPostsByEmail(Principal principal) {
        try {
            String email = principal.getName();
            Map<String, Object> result = userService.getLikedPostsByEmail(email);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // 작성한 글 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostForUserDTO>> getUserPosts(Principal principal) {
        try {
            List<PostForUserDTO> userPosts = userService.getUserPosts(principal.getName());
            return ResponseEntity.ok(userPosts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
