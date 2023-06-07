package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @PostMapping("/emailCheck")
    @ResponseBody
    public int idCheck(@RequestParam("email") String email) {
        int cnt = userService.emailCheck(email);
        return cnt;
    }

    @PostMapping("hi")
    public String hi() {
        return "hi";
    }

    /*
    @PostMapping("/update")
    public boolean update(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam String userId) {
        Long id = Long.parseLong(userId);
        return userService.deleteAccount(id);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        return userService.changePassword(userChangePasswordDTO);
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo(@RequestParam Long userId) {
        UserDTO userDTO = userService.getUserInfoById(userId);
        return ResponseEntity.ok(userDTO);
    }


     */
}
