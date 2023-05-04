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

import java.util.Optional;

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
    public ResponseEntity<String> signup(@RequestBody UserFormDTO userFormDTO) {
        userService.userJoin(userFormDTO);
        System.out.println(userFormDTO.getEmail());
        return ResponseEntity.ok("/user/loginPage");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        Optional<UserLoginDTO> userOptional = userRepository.findUserByEmail(userLoginDTO.getEmail());
        if (userOptional.isPresent()) {
            UserLoginDTO user = userOptional.get();
            if (user.getPassword().equals(userLoginDTO.getPassword())) {
                System.out.println("로그인 성공시");
                return ResponseEntity.ok("로그인 성공");
            }
        }
                System.out.println("로그인 실패시");
        return ResponseEntity.badRequest().body("로그인 실패");
    }

}
