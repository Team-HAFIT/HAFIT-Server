package com.feedback.hafit.controller;

import com.feedback.hafit.entity.*;
import com.feedback.hafit.repository.UserRepository;
import com.feedback.hafit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://172.26.9.191:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/loginPage")
//    public String loginPage() {
//        return "user/loginPage";
//    }

    @PostMapping("/signup")
    public boolean signup(@RequestBody UserFormDTO userFormDTO) {
        return  userService.signup(userFormDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO, HttpSession session) {
        User user = userService.login(userLoginDTO);
        if (user != null) {
            session.setAttribute("loginState", "login");
            session.setAttribute("userId", user.getUser_id());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRole());
            System.out.println(session.getAttribute("loginState") + " " + session.getAttribute("role")+ " " + session.getAttribute("email"));
            return ResponseEntity.ok(Map.of("userId", user.getUser_id()));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/session")
    public Object session(HttpSession session) {
        System.out.println(session.getAttribute("userId"));
        return session.getAttribute("loginState");
    }

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

}
