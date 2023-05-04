package com.feedback.hafit.controller;

import com.feedback.hafit.entity.UserFormDTO;
import com.feedback.hafit.entity.UserLoginDTO;
import com.feedback.hafit.repository.UserRepository;
import com.feedback.hafit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://172.26.12.239:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signupPage")
    public ModelAndView signupPage() {  // 회원 가입 페이지
        ModelAndView mav = new ModelAndView("/user/signupPage");
        return mav;
    }

    @GetMapping("/loginPage")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("/user/loginPage");
        return mav;
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://172.26.12.239:3000")
    public ResponseEntity<String> signup(@RequestBody UserFormDTO userFormDTO) {
        userService.userJoin(userFormDTO);
        return ResponseEntity.ok("/user/loginPage");
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://172.26.12.239:3000")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        // UserLoginDTO authenticatedUser = userService.login(userLoginDTO);
        boolean authenticatedUser = userService.login(userLoginDTO);
        if (authenticatedUser == false) {
            System.out.println("실패");
            return ResponseEntity.badRequest().body("/user/loginPage");
        }
        System.out.println("성공");
        return ResponseEntity.ok("Redirect:/");
    }

}
