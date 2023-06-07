package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.dto.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.UserDTO;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
@Slf4j
public class UserController {

    private final UserService userService;

    // 이메일 중복 확인
    @GetMapping("/email/{email}")
    @ResponseBody
    public int checkEmailAvailability(@PathVariable("email") String email) {
        int isEmailAvailable = userService.emailCheck(email);
        return isEmailAvailable;
    }

    // 회원 정보 수정
    @PutMapping("/{email}")
    public boolean updateUser(@PathVariable("email") String email, @RequestBody UserDTO userDTO) {
        return userService.updateUser(email, userDTO);
    }

    // 회원 삭제
    @DeleteMapping("/{email}")
    public boolean deleteUser(@PathVariable("email") String email) {
        return userService.deleteUser(email);
    }

    // 비밀번호 변경
    @PutMapping("/{email}/password")
    public boolean changePassword(@PathVariable("email") String email, @RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        return userService.changePassword(email, userChangePasswordDTO);
    }

    // 회원 정보 가져오기
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable("email") String email) {
        UserDTO userDTO = userService.getUserInfoByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

}
