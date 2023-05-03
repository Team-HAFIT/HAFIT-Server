package com.feedback.hafit.controller;

import com.feedback.hafit.Entity.User;
import com.feedback.hafit.Entity.UserFormDTO;
import com.feedback.hafit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/signupPage")
    public String signupPage() {  // 회원 가입 페이지
            return "/user/signupPage";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
            return "/user/loginPage";
    }

    @PostMapping("/signup")
    public String createUser(@Valid UserFormDTO userFormDTO, BindingResult bindingResult, Model model) {
//        userService.signupUser(userFormDTO);
//        return "redirect:/user/loginPage";
        try {
            User user = User.createUser(userFormDTO, passwordEncoder);
            userService.signupUser(user);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/user/signupPage";
        }

        return "redirect:/";
    }

}
